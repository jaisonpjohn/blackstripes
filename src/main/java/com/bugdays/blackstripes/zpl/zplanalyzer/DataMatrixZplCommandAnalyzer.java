package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.DataMatrixElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataMatrixZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("BX(?<orientation>[NRIB]),(?<height>\\d+)?,(?<moduleWidth>\\d+)?");

    public DataMatrixZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("BX", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            String orientation = matcher.group("orientation"); // Handle if needed
            int height = matcher.group("height") != null ? Integer.parseInt(matcher.group("height")) : 10; // Default height
            int moduleWidth = matcher.group("moduleWidth") != null ? Integer.parseInt(matcher.group("moduleWidth")) : 2; // Default module width

            Point position = virtualPrinter.getCurrentPosition();
            DataMatrixElement element = new DataMatrixElement(null, position, moduleWidth, height);
            virtualPrinter.addElement(element);

        } else {
            virtualPrinter.addError("Invalid BX command: " + command);
        }
    }
}
