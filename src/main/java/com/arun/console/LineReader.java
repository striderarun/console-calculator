package com.arun.console;

import jline.console.ConsoleReader;
import jline.console.UserInterruptException;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class LineReader extends ConsoleReader implements Closeable
{
    private boolean interrupted;
    private static final String PROMPT_NAME = "arun>";


    public LineReader() throws IOException {
        setExpandEvents(false);
        setBellEnabled(true);
        setHandleUserInterrupt(true);
        setHistoryEnabled(false);
        setEchoCharacter(Character.valueOf(' '));
        setPrompt(PROMPT_NAME);
    }

    @Override
    public String readLine(String prompt, Character mask) throws IOException {
        String line;
        interrupted = false;
        try {
            line = super.readLine(prompt, mask);
        }
        catch (UserInterruptException e) {
            interrupted = true;
            return null;
        }

        if (getHistory() instanceof Flushable) {
            ((Flushable) getHistory()).flush();
        }
        return line;
    }

    @Override
    public void close() {
        shutdown();
    }

    public boolean interrupted() {
        return interrupted;
    }
}
