package com.xdd.coffeecoin;

import com.xdd.coffeecoin.commands.Command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by qingying.xdd on 18/8/17.
 */
public class CoffeeCoin {
  private static final String TIP = "coffee coin> ";

  private static boolean isTerminated = false;

  public static void main(String[] args) throws Exception {
    System.out.println("Welcome to coffee coin world! All after all, " +
            "enjoy your coffee first if you have one cup.^v^");

    while(!isTerminated) {
      System.out.print(TIP);
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
      String line = stdin.readLine();
      String result = executeCmd(new Command(line));

      System.out.println(result);
    }
  }

  private static String executeCmd(Command clientCmd)
          throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    if (clientCmd.isQuit()) {
      isTerminated = true;
      return "good bye! ^v^";
    }

    return clientCmd.execute().toString();
  }
}
