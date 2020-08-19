package de.franok.beer;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static de.franok.Constants.*;

public class NinetyNineBottlesOfBeer {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = BENCHMARK_ITERATIONS)
    @Fork(value = BENCHMARK_FORKS, warmups = BENCHMARK_WARMUPS)
    public void benchmark() {
        for (int i = 99; i >= 0; i--) {
            this.printLyrics(i);
        }
    }

    private void printLyrics(int bottles) {
        if (bottles == 0) {
            System.out.println();
            System.out.println("No bottles of beer on the wall, no bottles of beer, ya' can't take one");
            System.out.println("down, ya' can't pass it around, 'cause there are no more bottles  of beer on the wall!");
        } else {
            System.out.println(bottles + " bottles of beer on the wall," + bottles + " bottles of beer, ya' take one");
            System.out.println("down, ya' pass it around," + (bottles - 1) + " bottles of beer on the wall.");
            System.out.println();
        }
    }
}
