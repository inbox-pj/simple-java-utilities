package com.clover.labs.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class ECC {
    public static void main(String args[]) {
        try {
            Provider p[] = Security.getProviders();
            Provider p1 = Security.getProvider("SunEC");
            System.out.println(p1.getName());

          
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
            System.out.println(kpg.getAlgorithm());
            Cipher cipher = Cipher.getInstance("AES");
            System.out.println("provider=" + cipher.getProvider());

            ECGenParameterSpec ecsp = new ECGenParameterSpec("sect163r2");
            kpg.initialize(256);
            KeyPair kyp = kpg.genKeyPair();
            
            PublicKey pubKey = kyp.getPublic();

            int zz=pubKey.toString().length();
            System.out.println("Size of key" + zz +" and key is "+pubKey.toString());

            PrivateKey privKey = kyp.getPrivate();
            int pp=pubKey.toString().length();
            System.out.println("Size of key "+pp+" and key is "+privKey.toString());
            
            System.out.println("\n\n");
        
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            String cleartextFile = "cleartext.txt";
            String ciphertextFile = "ciphertextECIES.txt";

            byte[] block = new byte[64];
            FileInputStream fis = new FileInputStream(cleartextFile);
            FileOutputStream fos = new FileOutputStream(ciphertextFile);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

            int i;
            while ((i = fis.read(block)) != -1) {
                cos.write(block, 0, i);
            }
            cos.close();

            // Decrypt
            String cleartextAgainFile = "cleartextAgainECIES.txt";
            cipher.init(Cipher.DECRYPT_MODE, privKey, ecsp);
            fis = new FileInputStream(ciphertextFile);
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            fos = new FileOutputStream(cleartextAgainFile);
            while ((i = cis.read(block)) != -1) {
                fos.write(block, 0, i);
            }
            fos.close();
        } 
catch (Exception e) {            e.printStackTrace(); }
    }
}
