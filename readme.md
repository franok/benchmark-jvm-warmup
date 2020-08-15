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

### PseudoRandomBenchmark

The PseudoRandomBenchmark is a benchmark that creates an object of `java.util.Random` with a given seed (42), that is guaranteed to provide (pseudo-)random, but stable (reproducible) number sequences.

Summary on the [java.util.Random](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Random.html) class (from official JDK11 docs):
> An instance of this class is used to generate a stream of pseudorandom numbers. The class uses a 48-bit seed, which is modified using a linear congruential formula. (See Donald Knuth, The Art of Computer Programming, Volume 2, Section 3.2.1.)

By invoking `nextInt(Integer.MAX_VALUE)` a number `randomInt` is generated, with a value between 0 (inclusive) and 2<sup>31</sup>-1 (exclusive).
Depending on the modulo 2 of `randomInt` an object (either `EvenInteger` or `OddInteger`) is created and added to a respective list (odd/even lists). Afterwards the lists are sorted by their object's value(int) property. Finally objects are consumed by a [Blackhole](https://javadoc.io/static/org.openjdk.jmh/jmh-core/1.23/org/openjdk/jmh/infra/Blackhole.html).

### SPECjvm2008 (remastered for JMH)


 

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
$ java -jar target/benchmarks.jar [benchmark-name] -rf json -rff jmh-result.json
```

setting -Xms equal to -Xmx to avoid resizing hiccups, write heap_dump in case of java.lang.OutOfMemoryError:
```bash
$ java  -Xms256m -Xmx256m -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar [benchmark-name] -rf json -rff jmh-result.json
```

Run with experimental/diagnostic JVM flags, e.g. use EpsilonGC (=disable GC, JDK11+):
```bash
$ java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -jar target/benchmarks.jar [benchmark-name] -rf json -rff jmh-result.json
```

+ verbose settings (class, jni, gc, compilation, compilation-log):
```bash
$ java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -verbose:class -verbose:jni -verbose:gc -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+LogCompilation -XX:LogFile=jvm-warmup-hotspot.log -jar target/benchmarks.jar [benchmark-name] -rf json -rff jmh-result.json
```

Copy-Paste-Template for benchmark execution:
```bash
$ java -Xms3g -Xmx4g -XX:+HeapDumpOnOutOfMemoryError -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -XX:+PrintCompilation -jar target/benchmarks.jar Compression -rf json -rff jmh-result-specjvm2008-compression-compression.json | tee output-specjvm2008-compression.log
```

Recommended to run in tmux!

### tmux
```bash
$ tmux new -s [session-name] // new session with name

Ctrl+b d //detach from running session

$ tmux ls // list sessions
$ tmux a -t [session-name] // re-attach to running session
```



## JVM flags

https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html







