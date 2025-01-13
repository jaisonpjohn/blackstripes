package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.AztecBarcodeElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AztecBarcodeZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("BO(?<magnification>\\d+),(?<errorControl>\\d+),");

    public AztecBarcodeZplCommandAnalyzer(VirtualPrinter printer) {
        super("BO", printer);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            int magnificationFactor = Integer.parseInt(matcher.group("magnification"));
            int errorControl = Integer.parseInt(matcher.group("errorControl"));

            Point position = virtualPrinter.getCurrentPosition();
            AztecBarcodeElement element = new AztecBarcodeElement("Sample Aztec Content", position, magnificationFactor, errorControl);
            virtualPrinter.addElement(element);
        } else {
            virtualPrinter.addError("Invalid BO command: " + command);
        }
    }
}
