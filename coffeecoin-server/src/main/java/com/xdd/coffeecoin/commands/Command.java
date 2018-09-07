package com.xdd.coffeecoin.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingying.xdd on 18/8/21.
 *
 * OOD: 使用策略模型？
 *
 */
public class Command {
  private final String SPERATOR = " ";

  private String cmd;
  private String clazz;
  private String method;

  private List<Object> args;

  private static final String[][] commands = {
          { "help", "com.xdd.coffeecoin.commands.HelpCommand", "execute" },
          { "balance", "Transaction", "getBalance" },
          { "log", "Transaction", "getBalance"},
          { "quit", "Transaction", "getBalance" },
          { "wallet", "com.xdd.coffeecoin.commands.WalletCommand", "execute" }
  };

  public Command() {

  }
  public Command(String cmdStr) {
    if (cmdStr == null) {
      return;
    }
    args = new ArrayList<Object>();
    String[] cmdParts = cmdStr.split(SPERATOR);
    cmd = cmdParts[0];
    for (String cmdPart : cmdParts) {
      args.add(cmdPart);
    }

    // generate executable class
    for (String[] command : commands) {
      if (command[0].equals(cmd)) {
        clazz = command[1];
        method = command[2];
      }
    }
  }

  public boolean isQuit() {
    return "quit".equals(cmd);
  }

  public Object execute() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
    Class<?> clazzz = null;

    clazzz = Class.forName(this.clazz);
//    Method m = clazzz.getMethod(method, String[].class);
    Method m = clazzz.getMethod(method, List.class);
    return m.invoke(clazzz.newInstance(), args);
  }

  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public String getClazz() {
    return clazz;
  }

  public void setClazz(String clazz) {
    this.clazz = clazz;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public List<Object> getArgs() {
    return args;
  }

  public void setArgs(List<Object> args) {
    this.args = args;
  }

}
