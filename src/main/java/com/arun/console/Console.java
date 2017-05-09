package com.arun.console;


import com.arun.CalculatorLexer;
import com.arun.CalculatorParser;
import com.arun.parser.EvalVisitor;
import io.airlift.airline.Command;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.concurrent.atomic.AtomicBoolean;

@Command(name = "arun", description = "My console")
public class Console implements Runnable {

    private EvalVisitor evalVisitor = new EvalVisitor();

    @Override
    public void run() {
        AtomicBoolean exiting = new AtomicBoolean();
        try(LineReader reader = new LineReader()) {
            while (!exiting.get()) {
                String line = reader.readLine();
                evaluate(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void evaluate(String expression) {
        CalculatorLexer calculatorLexer = new CalculatorLexer(new ANTLRInputStream(expression));
        CalculatorParser calculatorParser = new CalculatorParser(new CommonTokenStream(calculatorLexer));
        calculatorParser.setBuildParseTree(true);
        ParseTree parseTree = calculatorParser.begin();
        evalVisitor.visit(parseTree);
    }

}

