package com.cursojava.curso.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component(value = "MD5Util")
public class EncryptorMD5 implements EncryptorUtil{

    @Override
    public String encriptarCadena(String cadena) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            //getBytes devuelve un array de bytes en el que cada caracter representa el codigo ascii

            byte[] messageDigest = md.digest(cadena.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verificarIgualdadDeCadenas(String hashString, String notHashString) {
        return hashString.equals(encriptarCadena(notHashString));
    }


}
