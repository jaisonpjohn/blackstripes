package com.bugdays.blackstripes.zpl.zplanalyzer;


import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.TextElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldDataZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern DATA_PATTERN = Pattern.compile("\\^FD(?<data>.+)\\^FS");

    public FieldDataZplCommandAnalyzer(VirtualPrinter printer) {
        super("^FD", printer);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = DATA_PATTERN.matcher(command);
        if (matcher.find()) {
            String data = matcher.group("data");
            Point currentPosition = virtualPrinter.getCurrentPosition();
            virtualPrinter.addElement(new TextElement(data, currentPosition, virtualPrinter));
        } else {
            virtualPrinter.addError("Invalid FD command: " + command);
        }
    }
}
