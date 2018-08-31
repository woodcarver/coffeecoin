package com.xdd.coffeecoin.wallet;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qingying.xdd on 18/8/30.
 * 数据加密： result by using private to encrypt msg
 * 数字签名: result by using public to encrypt msg
 *
 * https://www.jianshu.com/p/aff5492d64f0  -- for rsa encrypt
 * https://blog.csdn.net/u014143369/article/details/53156194 -- for sha or md5
 */
public class Transaction {
  private final String ENCRPT_ALGORITHM = "SHA";

  private String targetAddress;
  private String previousTransactionHash;
  private double amount;
  private byte[] signature;

  public Transaction(String targetAddress, double amount, Key privateKey)
          throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
    this.targetAddress = targetAddress;
    this.amount = amount;

    getPreviousTransactionHash();
    genSignature(privateKey);
  }

  private void getPreviousTransactionHash() {
    previousTransactionHash = "previousTransactionHash";
  }

  private void genSignature(Key privateKey)
          throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
    MessageDigest messageDigest;
    messageDigest = MessageDigest.getInstance(ENCRPT_ALGORITHM);

    byte[] content = messageDigest.digest((targetAddress + previousTransactionHash).getBytes());

    Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    signature = cipher.doFinal(content);
  }

  public String getTargetAddress() {
    return targetAddress;
  }

  public double getAmount() {
    return amount;
  }
}
