/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package de.franok.specjvm2008.crypto.rsa;

import de.franok.specjvm2008.crypto.Util;
import org.openjdk.jmh.annotations.*;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import static de.franok.Constants.*;

@State(Scope.Thread)
public class RSA {

    static PublicKey rsaPub;
    static PrivateKey rsaPriv;

    static byte[] testData3;
    static byte[] testData5;

    public byte[] encrypt(byte[] indata, String algorithm) {

        try {
            Cipher c = Cipher.getInstance(algorithm);
            byte[] result = indata;

            c.init(Cipher.ENCRYPT_MODE, rsaPub);
            result = c.doFinal(result);

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Exception in encrypt for " + algorithm + ".", e);
        }
    }

    public byte[] decrypt(byte[] indata, String algorithm) {

        try {
            Cipher c = Cipher.getInstance(algorithm);

            byte[] result = indata;

            c.init(Cipher.DECRYPT_MODE, rsaPriv);
            result = c.doFinal(result);

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Exception in decrypt for " + algorithm + ".", e);
        }
    }

    public void runSingleEncryptDecrypt(String algorithm, byte[] indata) {
        System.out.println("Algorithm=" + algorithm + " indata length=" + indata.length);
        byte[] cipher = encrypt(indata, algorithm);
        byte[] plain = decrypt(cipher, algorithm);
        boolean match = Util.check(indata, plain);
        System.out.println(algorithm + ":"
                + " plaincheck=" + Util.checkSum(plain)
                + (match ? " PASS" : " FAIL"));
    }

    public void runMultiEncryptDecrypt(String algorithm, byte[] fullIndata) {
        int blockSize = 64;
        byte[] indata = new byte[blockSize];
        int pass = 0;
        int fail = 0;
        int check = 0;
        System.out.println("Algorithm=" + algorithm + " indata length=" + fullIndata.length);
        for (int i = 0; i + blockSize < fullIndata.length; i += blockSize) {
            System.arraycopy(fullIndata, i, indata, 0, blockSize);
            byte[] cipher = encrypt(indata, algorithm);
            byte[] plain = decrypt(cipher, algorithm);
            if (Util.check(indata, plain)) {
                pass++;
                check += Util.checkSum(plain);
            } else {
                fail++;
            }
        }
        System.out.println(algorithm + ":"
                + " checksum=" + check
                + " pass=" + pass
                + " fail=" + fail);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = BENCHMARK_ITERATIONS)
    @Fork(value = BENCHMARK_FORKS, warmups = BENCHMARK_WARMUPS)
    public void harnessMain() {
        System.out.println();
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", testData3);
        runMultiEncryptDecrypt("RSA/ECB/PKCS1Padding", testData5);
        // Run some more, in order to increase operation workload.
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", testData3);
        runMultiEncryptDecrypt("RSA/ECB/PKCS1Padding", testData5);
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", testData3);
    }

    @Setup
    public static void setupBenchmark() {
        try {
            KeyPairGenerator rsaKeyPairGen = KeyPairGenerator.getInstance("RSA");
            // 512, 768 and 1024 are commonly used
            rsaKeyPairGen.initialize(1024);

            KeyPair rsaKeyPair = rsaKeyPairGen.generateKeyPair();

            rsaPub = rsaKeyPair.getPublic();
            rsaPriv = rsaKeyPair.getPrivate();

            testData3 = Util.getTestData(Util.TEST_DATA_3);
            testData5 = Util.getTestData(Util.TEST_DATA_5);

        } catch (Exception e) {
            throw new RuntimeException("Error in setup of crypto.aes." + e);
        }
    }
}
