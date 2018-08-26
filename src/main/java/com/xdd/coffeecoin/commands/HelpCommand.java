package com.xdd.coffeecoin.commands;

/**
 * Created by qingying.xdd on 18/8/24.
 */
public class HelpCommand {
  public void execute(String[] args) {
    System.out.println(String.format("Please input those command:" +
            "help" +
            "balance <address>" +
            "log <address>"));
  }
}
