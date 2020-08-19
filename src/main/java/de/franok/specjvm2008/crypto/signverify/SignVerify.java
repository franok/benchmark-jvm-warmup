/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package de.franok.specjvm2008.crypto.signverify;


import de.franok.specjvm2008.crypto.Util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

//ToDo: This benchmark is to be remastered for JMH. Use RSA.java as template
public class SignVerify {
    
    private static PublicKey rsaPub;
    private static PrivateKey rsaPriv;
    
    private static PublicKey dsaPub;
    private static PrivateKey dsaPriv;
    
    private final static int iterations = 10;

    
    public byte [] sign(byte[] indata, String algorithm, PrivateKey privKey) {
        
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privKey);
            signature.update(indata);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Exception in verify for " + algorithm + ".", e);
        }
    }
    
    public boolean verify(byte[] indata, String algorithm, byte [] signed, PublicKey pubKey) {
        
        try {
            
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(pubKey);
            
            signature.update(indata);
            
            return signature.verify(signed);
            
        } catch (Exception e) {
            throw new RuntimeException("Exception in verify for " + algorithm + ".", e);
        }
    }
    
    
    
    public void runSignVerify(byte[] indata, String algorithm, PrivateKey privKey, PublicKey pubKey) {
        
        byte [] signed = sign(indata, algorithm, privKey);
        boolean verification = verify(indata, algorithm, signed, pubKey);
        
        if (verification) {
            System.out.println(algorithm + " Verification PASS");
        } else {
            System.out.println(algorithm + " Verification FAILED.");
        }
        
    }
    
    public void harnessMain() {
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iteration " + i + ".");
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA256withRSA", rsaPriv, rsaPub);
            
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA256withRSA", rsaPriv, rsaPub);
            
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA256withRSA", rsaPriv, rsaPub);
        }
    }
    
}
