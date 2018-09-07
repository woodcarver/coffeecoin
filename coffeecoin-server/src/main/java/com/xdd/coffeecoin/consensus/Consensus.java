package com.xdd.coffeecoin.consensus;

/**
 * Created by qingying.xdd on 18/9/7.
 */
public interface Consensus {
  /**
   * reach a consensus
   * @param hashResult
   * @return
   */
  boolean isCorrectAnswer(byte[] hashResult);
}
