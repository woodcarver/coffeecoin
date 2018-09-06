package com.xdd.coffeecoin.commands;

import java.util.List;

/**
 * Created by qingying.xdd on 18/8/24.
 */
public class HelpCommand {
  public Object execute(List<Object> args) {
    return String.format("Please input those command: \n" +
            "  - help \n" +
            "  - wallet genkey \n" +
            "  - balance <address> \n" +
            "  - log <address>");
  }
}
