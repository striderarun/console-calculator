package com.arun.main;

import com.arun.CalculatorLexer;
import com.arun.CalculatorParser;
import com.arun.console.Console;
import com.arun.parser.EvalVisitor;
import io.airlift.airline.SingleCommand;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main { 
 
  public static void main(String[] args) throws Exception {
    Console console = SingleCommand.singleCommand(Console.class).parse(args);
    console.run();
  }  
  
} 
