package de.franok;

public class Constants {
    public static final int BENCHMARK_ITERATIONS = 10; // overwritten by jmh command line flag -i <int>
    public static final int BENCHMARK_FORKS = 3; // overwritten by jmh command line flag -f <int>
    public static final int BENCHMARK_WARMUPS = 0; // overwritten by jmh command line flag -wi <int>

    // SPECjvm2008 constants
    public static final String SPEC_JVM_2008_BASE_PATH = "src/main/java/de/franok/specjvm2008";
}
