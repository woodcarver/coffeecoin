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

  private List<String> args;

  private static final String[][] commands = {
          { "help", "com.xdd.coffeecoin.commands.HelpCommand", "execute" },
          { "balance", "Transaction", "getBalance" },
          { "log", "Transaction", "getBalance"},
          { "quit", "Transaction", "getBalance" },
          { "wallet" }
  };

  public Command() {

  }
  public Command(String cmdStr) {
    if (cmdStr == null) {
      return;
    }
    args = new ArrayList<String>();
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
    Class<?> class1 = null;

    class1 = Class.forName(clazz);
    Method m = class1.getMethod(method, String[].class);
    return m.invoke(class1.newInstance(), args);
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

  public List<String> getArgs() {
    return args;
  }

  public void setArgs(List<String> args) {
    this.args = args;
  }

}
