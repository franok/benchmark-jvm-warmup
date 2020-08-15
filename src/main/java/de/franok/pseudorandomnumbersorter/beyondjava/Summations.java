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

public class Summations {

    private static final int MAX_LOOP_ITERATIONS = 5000;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = BENCHMARK_ITERATIONS)
    @Fork(value = BENCHMARK_FORKS, warmups = BENCHMARK_WARMUPS)
    public void benchmark(Blackhole blackhole) {
        long sum = 0;
        for (int i = 1; i <= MAX_LOOP_ITERATIONS; i++) {
            sum = sum + random(i);
        }
        blackhole.consume(sum);
    }

    private int random(int i) {
        int x = (int) (i * 2.3d / 2.7d); // This is a simulation
        int y = (int) (i * 2.36d);     // of time-consuming
        return x % y;                 // business logic.
    }
}
