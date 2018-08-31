package com.xdd.coffeecoin.blockchain;

/**
 * Created by qingying.xdd on 18/8/31.
 */
public class Block {
  private final String ALGORITHM = "SHA";

  private int once;
  private String preBlockHash;
  private String transactions;

  public Block() {

  }

  private void genPreBlockHash() {
    preBlockHash = "preBlockHash";
  }

  private void genOnce() {

  }
}
