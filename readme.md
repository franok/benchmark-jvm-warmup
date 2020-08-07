# benchmarking JVM warmup

## setup desription

```bash
mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=de.franok \
          -DartifactId=benchmark-jvm-warmup \
          -Dversion=0.1.0
```

## run instructions

Display JMH help:
```bash
$ java -jar target/benchmarks.jar -h
```

Write results to a file (`-rf <type>`, `-rff <filename>`):
```bash
$ java -jar target/benchmarks.jar -rf json -rff jmh-result.json
```




