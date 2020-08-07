/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.franok;

import de.franok.model.EvenInteger;
import de.franok.model.OddInteger;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PseudoRandomBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 10)
    @Fork(value = 3, warmups = 0)
    public void processRandomIntegers(Blackhole blackhole) {
        Random random = new Random(42);
        int maxIterations = 10;
        for (int i = 0; i < maxIterations; i++) {
            int currentRandomInt = random.nextInt(Integer.MAX_VALUE);
            if (currentRandomInt % 2 == 0) {
                EvenInteger evenInt = new EvenInteger(currentRandomInt);
                blackhole.consume(evenInt);
                //System.out.println(evenInt);
            } else {
                OddInteger oddInt = new OddInteger(currentRandomInt);
                blackhole.consume(oddInt);
                //System.out.println(oddInt);
            }
        }
    }

}
