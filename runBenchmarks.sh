#!/bin/bash
alias java11-hotspot='~/jvm-evaluation/hotspot_jdk-11.0.8+10/bin/java'
alias java11-openj9='~/jvm-evaluation/openj9_jdk-11.0.8+10/bin/java'
alias java11-graalvm='~/jvm-evaluation/graalvm-ce-java11-20.2.0/bin/java'

DATE_TIME=$(date +'%Y_%m%d_%H-%M')

mkdir $DATE_TIME

java11-hotspot -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch" -rf json -rff jmh-result-hotspot-backtracking-sudoku.json -i 21000 -f 20 -to 360m | tee output-hotspot-backtracking-sudoku-$(date +'%FT%H-%M').log
java11-openj9 -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -Xgcpolicy:nogc" -rf json -rff jmh-result-openj9-backtracking-sudoku.json -i 21000 -f 20 -to 360m | tee output-openj9-backtracking-sudoku-$(date +'%FT%H-%M').log
java11-graalvm -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+UnlockExperimentalVMOptions -Dlibgraal.MaxNewSize=6442450944 -Dlibgraal.PrintGC=true -XX:+AlwaysPreTouch" -rf json -rff jmh-result-graalvm-backtracking-sudoku.json -i 21000 -f 20 -to 360m | tee output-graalvm-backtracking-sudoku-$(date +'%FT%H-%M').log

mv jmh-result-* $DATE_TIME
mv output-* $DATE_TIME
