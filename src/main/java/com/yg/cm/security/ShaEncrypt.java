package com.yg.cm.security;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ShaEncrypt {

//    public String encrypt(String plainText, String salt) {
//        byte[] hashBytes;
//        int count = 3; // 3번 암호화
//        String temp = plainText;
//        for(int i=0 ; i<count ; i++) {
//            temp.concat(salt);
//            SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest384();
//            hashBytes = digestSHA3.digest(temp.getBytes());
//            temp = Base64.getEncoder().encodeToString(hashBytes);
//        }
//        return temp;
//    }
//
//    public String getSalt() { //암호화시 포함할 salt 생성
//        StringBuilder sb = new StringBuilder();
//        SecureRandom rnd = new SecureRandom();
//        byte[] temp = new byte[64];
//        rnd.nextBytes(temp);
//        for(byte a : temp) sb.append(String.format("%02x", a));
//
//        return sb.toString();
//    }
}
