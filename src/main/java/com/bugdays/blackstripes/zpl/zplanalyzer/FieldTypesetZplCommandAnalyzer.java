package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldTypesetZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("FT(?<x>\\d+),(?<y>\\d+)");

    public FieldTypesetZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("FT", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            virtualPrinter.setCurrentPosition(new Point(x, y));
        } else {
            virtualPrinter.addError("Invalid FT command: " + command);
        }
    }
}