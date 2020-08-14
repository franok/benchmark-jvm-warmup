# benchmarking JVM warmup

## setup description

This benchmark project was generated following instructions from [offical JMH site](http://openjdk.java.net/projects/code-tools/jmh/):
```bash
mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=de.franok \
          -DartifactId=benchmark-jvm-warmup \
          -Dversion=0.1.0
```
JMH version 1.23

The PseudoRandomBenchmark is a benchmark that creates an object of `java.util.Random` with a given seed (42), that is guaranteed to provide (pseudo-)random, but stable (reproducible) number sequences.

Summary on the [java.util.Random](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html) class (from official JDK11 docs):
> An instance of this class is used to generate a stream of pseudorandom numbers. The class uses a 48-bit seed, which is modified using a linear congruential formula. (See Donald Knuth, The Art of Computer Programming, Volume 2, Section 3.2.1.)

By invoking `nextInt(Integer.MAX_VALUE)` a number `randomInt` is generated, with a value between 0 (inclusive) and 2<sup>31</sup>-1 (exclusive).
Depending on the modulo 2 of `randomInt` an object (either `EvenInteger` or `OddInteger`) is created and added to a respective list (odd/even lists). Afterwards the lists are sorted by their object's value(int) property. Finally objects are consumed by a [Blackhole](https://javadoc.io/static/org.openjdk.jmh/jmh-core/1.23/org/openjdk/jmh/infra/Blackhole.html).
 

## run instructions

Display JMH help:
```bash
$ java -jar target/benchmarks.jar -h
```

Running a certain benchmark by `[benchmark-name]`. The benchmark name is the prefix of the Java Class, i.e. `PseudoRandomBenchmark`, `PseudoRandomBench` or just `PseudoRandom`.
```bash
$ java -jar target/benchmarks.jar [benchmark-name]
```


Write results to a file (`-rf <type>`, `-rff <filename>`):
```bash
$ java -jar target/benchmarks.jar -rf json -rff jmh-result.json
```

Run with JVM flags:
```bash
$ java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -verbose:class -verbose:jni -verbose:gc -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+LogCompilation -XX:LogFile=jvm-warmup-hotspot.log -jar target/benchmarks.jar -rf json -rff jmh-result.json
```

## JVM flags

https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html







