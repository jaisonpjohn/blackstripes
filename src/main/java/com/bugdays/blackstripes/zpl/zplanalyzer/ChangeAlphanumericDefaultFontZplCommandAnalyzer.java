package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeAlphanumericDefaultFontZplCommandAnalyzer extends ZplCommandAnalyzerBase {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("CF(?<fontName>\\w+)(,(?<fontHeight>\\d+))?(,(?<fontWidth>\\d+))?");

    public ChangeAlphanumericDefaultFontZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("CF", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        // Match the command using regex
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            // Extract font name
            String fontName = matcher.group("fontName");
            virtualPrinter.setFontName(fontName);

            // Extract font height, default to 9 if not provided
            int fontHeight = matcher.group("fontHeight") != null
                    ? Integer.parseInt(matcher.group("fontHeight"))
                    : 9;

            // Extract font width, default to 0 if not provided
            int fontWidth = matcher.group("fontWidth") != null
                    ? Integer.parseInt(matcher.group("fontWidth"))
                    : 0;

            // Update the VirtualPrinter with the extracted values
            System.out.println("setting font height to " + fontHeight);
            virtualPrinter.setFontHeight(fontHeight);
            virtualPrinter.setFontWidth(fontWidth);
        } else {
            virtualPrinter.addError("Invalid CF command: " + command);
        }
    }
}
