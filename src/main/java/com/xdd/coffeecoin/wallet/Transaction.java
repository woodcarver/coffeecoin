package com.xdd.coffeecoin.wallet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qingying.xdd on 18/8/30.
 * 数据加密： result by using private to encrypt msg
 * 数字签名: result by using public to encrypt msg
 *
 * https://www.jianshu.com/p/aff5492d64f0
 */
public class Transaction {
  private Key owner1publicKey;
  private String previousTransactionHash;
  private double amount;
  private String signature;

  public boolean translate(String targetKey, double amount) {
    return true;
  }

  private String getPreviousTransactionHash() {
    return "previousTransactionHash";
  }


  private byte[] encryptByPrivateKey(byte[] data, byte[] key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher cipher = Cipher.getInstance(owner1publicKey.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, owner1publicKey);
    return cipher.doFinal(data);
  }

  public double getAmount() {
    return amount;
  }
}
