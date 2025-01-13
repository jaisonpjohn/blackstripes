package com.bugdays.blackstripes.zpl.zplanalyzer;


import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldOriginZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern ORIGIN_PATTERN = Pattern.compile("\\^FO(?<x>\\d+),(?<y>\\d+)");

    public FieldOriginZplCommandAnalyzer(VirtualPrinter printer) {
        super("^FO", printer);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = ORIGIN_PATTERN.matcher(command);
        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            virtualPrinter.setCurrentPosition(new Point(x, y));
        } else {
            virtualPrinter.addError("Invalid FO command: " + command);
        }
    }
}
