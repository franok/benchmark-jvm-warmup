/**
 * Credits for original code go to Stephan Rauh.
 * Source: https://www.beyondjava.net/a-close-look-at-javas-jit-dont-waste-your-time-on-local-optimizations (2020-08-15)
 * Blogpost is licensed under CC BY-NC-ND 3.0 DE (https://creativecommons.org/licenses/by-nc-nd/3.0/de/)
 */

package de.franok.pseudorandomnumbersorter.beyondjava;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static de.franok.Constants.*;

public class OptimizedSummations {

    private static final int MAX_LOOP_ITERATIONS_OUTER = 100;
    private static final int MAX_LOOP_ITERATIONS_INNER = 5000;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = BENCHMARK_ITERATIONS)
    @Fork(value = BENCHMARK_FORKS, warmups = BENCHMARK_WARMUPS)
    public void benchmark(Blackhole blackhole) {
        for (int outer = 1; outer <= MAX_LOOP_ITERATIONS_OUTER; outer++) {
            long sum = 0;
            for (int i = 1; i <= MAX_LOOP_ITERATIONS_INNER; i++) {
                int x = (int) (i * 2.3d / 2.7d); // This is a simulation
                int y = (int) (i * 2.36d);     // of time-consuming
                sum = sum + x % y;            // business logic.
            }
            blackhole.consume(sum);
        }
    }
}
