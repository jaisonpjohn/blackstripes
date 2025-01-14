package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.GraphicImageElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphicImageZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("XG(?<imageName>[^,]+),(?<x>\\d+),(?<y>\\d+)");

    public GraphicImageZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("XG", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            String imageName = matcher.group("imageName");
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));

            BufferedImage image = virtualPrinter.getImage(imageName);
            if (image != null) {
                Point position = new Point(x, y);
                GraphicImageElement element = new GraphicImageElement(image, position);
                virtualPrinter.addElement(element);
            } else {
                virtualPrinter.addError("Image not found: " + imageName);
            }
        } else {
            virtualPrinter.addError("Invalid XG command: " + command);
        }
    }
}