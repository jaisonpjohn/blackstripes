package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.BarcodeElement;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarcodeZplCommandAnalyzer extends ZplCommandAnalyzerBase {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("BC(?<type>\\w),(?<height>\\d*)?,(?<humanReadable>[YN])");

    public BarcodeZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("BC", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            String type = matcher.group("type");
            String heightStr = matcher.group("height");
            int height = (heightStr != null && !heightStr.isEmpty()) ? Integer.parseInt(heightStr) : virtualPrinter.getBarcodeHeight();
            boolean humanReadable = matcher.group("humanReadable").equals("Y");

            Point position = virtualPrinter.getCurrentPosition();
            BarcodeElement element = new BarcodeElement(null, type, position, virtualPrinter.getBarcodeModuleWidth(),height, humanReadable);
            virtualPrinter.addElement(element);
        } else {
            virtualPrinter.addError("Invalid BC command: " + command);
        }
    }
}
