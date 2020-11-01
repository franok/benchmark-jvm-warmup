#!/bin/bash
#alias java11-hotspot='~/jvm-evaluation/hotspot_jdk-11.0.8+10/bin/java'
#alias java11-openj9='~/jvm-evaluation/openj9_jdk-11.0.8+10/bin/java'
#alias java11-graalvm='~/jvm-evaluation/graalvm-ce-java11-20.2.0/bin/java'

DATE_TIME=$(date +'%Y_%m%d_%H-%M')
JW="JITWatch_"

mkdir "$JW$DATE_TIME"

~/jvm-evaluation/hotspot_jdk-11.0.8+10/bin/java -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC -XX:+AlwaysPreTouch -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+LogCompilation -XX:LogFile=jitwatch-backtracking-sudoku_hotspot.log" -rf json -rff jmh-result-hotspot-backtracking-sudoku.json -i 21000 -f 1 -to 360m | tee output-hotspot-backtracking-sudoku-$(date +'%FT%H-%M').log
#~/jvm-evaluation/openj9_jdk-11.0.8+10/bin/java -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -Xgcpolicy:nogc -Xjit:count=0,verbose={compileEnd|inlining|compileTime},vlog=jitwatch-vlog" -rf json -rff jmh-result-openj9-backtracking-sudoku.json -i 21000 -f 1 -to 360m | tee output-openj9-backtracking-sudoku-$(date +'%FT%H-%M').log
#~/jvm-evaluation/graalvm-ce-java11-20.2.0/bin/java -XX:+HeapDumpOnOutOfMemoryError -jar target/benchmarks.jar Backtracking -jvmArgs "-Xms5g -Xmx5g -XX:+HeapDumpOnOutOfMemoryError -XX:+UnlockExperimentalVMOptions -Dlibgraal.MaxNewSize=6442450944 -Dlibgraal.PrintGC=true -XX:+AlwaysPreTouch -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+LogCompilation -XX:LogFile=jitwatch-backtracking-sudoku_graalvm.log" -rf json -rff jmh-result-graalvm-backtracking-sudoku.json -i 21000 -f 1 -to 360m | tee output-graalvm-backtracking-sudoku-$(date +'%FT%H-%M').log

mv jmh-result-* "$JW$DATE_TIME"
mv output-* "$JW$DATE_TIME"
mv jitwatch-* "$JW$DATE_TIME"
