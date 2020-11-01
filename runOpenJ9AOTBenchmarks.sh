#!/bin/bash
#alias java11-openj9='~/jvm-evaluation/openj9_jdk-11.0.8+10/bin/java'

DATE_TIME=$(date +'%Y_%m%d_%H-%M')

mkdir "openj9AOT/$DATE_TIME"

~/jvm-evaluation/openj9_jdk-11.0.8+10/bin/java -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -Xgcpolicy:nogc -Xshareclasses:nonpersistent,verboseAOT" -rf json -rff jmh-result-openj9-backtracking-sudoku.json -i 21000 -f 20 -to 360m | tee output-openj9-backtracking-sudoku-$(date +'%FT%H-%M').log

mv jmh-result-* "openj9AOT/$DATE_TIME"
mv output-* "openj9AOT/$DATE_TIME"
