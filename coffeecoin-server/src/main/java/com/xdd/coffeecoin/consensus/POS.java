package com.xdd.coffeecoin.consensus;

/**
 * Created by qingying.xdd on 18/8/20.
 */
public class POS implements Consensus {
  private final static int ZERO_CNT_REQUIRE = 3;

  public boolean isCorrectAnswer(byte[] hashResult) {
    // counter 0 in hash
    int cnt = 0;
    for (int i = 0; i < hashResult.length; i++) {
      if (hashResult[i] == 0) {
        cnt++;
      }
    }
    if (cnt > ZERO_CNT_REQUIRE) {
      return true;
    }
    return false;
  }
}
