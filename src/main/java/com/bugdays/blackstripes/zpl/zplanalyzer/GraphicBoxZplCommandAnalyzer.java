package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.GraphicBoxElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicBoxZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("GB(?<width>\\d+),(?<height>\\d+),(?<thickness>\\d+)");

    public GraphicBoxZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("GB", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            int width = Integer.parseInt(matcher.group("width"));
            int height = Integer.parseInt(matcher.group("height"));
            int thickness = Integer.parseInt(matcher.group("thickness"));

            Point position = virtualPrinter.getCurrentPosition();
            GraphicBoxElement element = new GraphicBoxElement(width, height, thickness, position);
            virtualPrinter.addElement(element);
        } else {
            virtualPrinter.addError("Invalid GB command: " + command);
        }
    }
}