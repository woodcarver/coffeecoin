package com.xdd.coffeecoin.blockchain;

import com.xdd.coffeecoin.consensus.Consensus;
import com.xdd.coffeecoin.consensus.POS;
import com.xdd.coffeecoin.wallet.Transaction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qingying.xdd on 18/8/31.
 */
public class Block {
  private final String ENCRPT_ALGORITHM = "SHA";
  private final int MAX_ITERATOR = 10;

  private Consensus consensus;
  private Integer once;
  private String preBlockHash;
  private byte[] blockHash;
  private Transaction transactions;

  public Block() throws Exception {
    this.consensus = new POS();
    genPreBlockHash();
    genOnce();
  }

  public Block(Consensus consensus) throws Exception {
    this.consensus = consensus;
    genPreBlockHash();
    genOnce();
  }

  private void genPreBlockHash() {
    preBlockHash = "preBlockHash";
  }

  private void genOnce() throws NoSuchAlgorithmException, IllegalAccessException {
    if (preBlockHash == null) throw new IllegalAccessException("preBlockHash is null");

    MessageDigest messageDigest = MessageDigest.getInstance(ENCRPT_ALGORITHM);

    for (int i = 0; i < MAX_ITERATOR; i++) {
      blockHash = messageDigest.digest((once.toString() + preBlockHash + transactions).getBytes());
      if (consensus.isCorrectAnswer(blockHash)) {
        break;
      }
      once++;
    }
  }
}
