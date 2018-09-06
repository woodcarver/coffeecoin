package com.xdd.coffeecoin.wallet;

import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 * Created by qingying.xdd on 18/8/29.
 */
public class Wallet {
  private final String ALGORITHMS = "RSA";
  private final int KEY_SIZE = 1024;

  public KeyPair generateKeyPair() throws NoSuchAlgorithmException, IOException {
    SecureRandom secureRandom = new SecureRandom();
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHMS);
    keyPairGenerator.initialize(KEY_SIZE, secureRandom);
    KeyPair key = keyPairGenerator.generateKeyPair();

    Key publicKey = key.getPublic();
    Key privateKey = key.getPrivate();
    byte[] publicKeyBytes = publicKey.getEncoded();
    byte[] privateKeyBytes = privateKey.getEncoded();

    String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
    String privateBase64 = new BASE64Encoder().encode(privateKeyBytes);

    // store keys to file
    ObjectOutputStream oos1 = null;
    ObjectOutputStream oos2 = null;
    try {
      oos1 = new ObjectOutputStream(new FileOutputStream("PublicKey"));
      oos2 = new ObjectOutputStream(new FileOutputStream("PrivateKey"));
      oos1.writeObject(publicKeyBase64);
      oos2.writeObject(privateBase64);
    } catch (IOException e) {
      throw e;
    } finally {
      oos1.close();
      oos2.close();
    }

    return key;
  }
}
