package com.example.liumeng.quanminfu2.javaTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**私钥公钥加密解密
 * Created by liumeng on 2016/12/23 on 10:15
 */
public class AsymmetricalEncrypt {
    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        KeyPair rsa1 = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        PublicKey aPublic = rsa1.getPublic();
        PrivateKey aPrivate = rsa1.getPrivate();

        String input = "刘蒙";
        Cipher cipher=Cipher.getInstance("RSA");
        //RSA公钥加密
        cipher.init(Cipher.ENCRYPT_MODE,aPrivate);
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(new String(bytes));

        //RSA私钥解密(反之亦然)
        cipher.init(Cipher.DECRYPT_MODE,aPublic);
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1));
    }
}
