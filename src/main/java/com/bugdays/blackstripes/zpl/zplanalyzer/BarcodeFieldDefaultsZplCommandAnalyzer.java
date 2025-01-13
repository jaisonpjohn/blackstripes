package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarcodeFieldDefaultsZplCommandAnalyzer extends ZplCommandAnalyzerBase {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("BY(?<moduleWidth>\\d+(\\.\\d+)?),(?<wideToNarrowRatio>\\d+),(?<height>\\d+)");

    public BarcodeFieldDefaultsZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("BY", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            double moduleWidth = Double.parseDouble(matcher.group("moduleWidth"));
            int wideToNarrowRatio = Integer.parseInt(matcher.group("wideToNarrowRatio"));
            int height = Integer.parseInt(matcher.group("height"));

            virtualPrinter.setBarcodeDefaults(moduleWidth, wideToNarrowRatio, height);
        } else {
            virtualPrinter.addError("Invalid BY command: " + command);
        }
    }
}
