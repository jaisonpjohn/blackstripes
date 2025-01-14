package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldAlphanumericZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("A(?<fontName>[^,]+),(?<fontHeight>\\d+),(?<fontWidth>\\d*)");

    public FieldAlphanumericZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("A", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            String fontName = matcher.group("fontName");
            int fontHeight = Integer.parseInt(matcher.group("fontHeight"));
            int fontWidth = matcher.group("fontWidth").isEmpty() ? 0 : Integer.parseInt(matcher.group("fontWidth"));

            virtualPrinter.setFontName(fontName);
            virtualPrinter.setFontHeight(fontHeight);
            virtualPrinter.setFontWidth(fontWidth);
        } else {
            virtualPrinter.addError("Invalid A command: " + command);
        }
    }
}