package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.FieldBlockElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldBlockZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("FB(?<width>\\d+),(?<maxLines>\\d+),(?<lineSpacing>-?\\d+),?");
    public FieldBlockZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("FB", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            System.out.println("FBmatcher-GG-FieldBlockZplCommandAnalyzer: " + command);
            int width = Integer.parseInt(matcher.group("width"));
            int maxLines = Integer.parseInt(matcher.group("maxLines"));
            int lineSpacing = Integer.parseInt(matcher.group("lineSpacing"));

            Point position = virtualPrinter.getCurrentPosition();
            FieldBlockElement element = new FieldBlockElement(null, width, maxLines, lineSpacing, position);
            virtualPrinter.addElement(element);
        } else {
            virtualPrinter.addError("Invalid FB command: " + command);
        }
    }
}