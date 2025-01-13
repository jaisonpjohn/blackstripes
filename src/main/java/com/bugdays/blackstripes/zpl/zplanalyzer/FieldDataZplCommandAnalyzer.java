package com.bugdays.blackstripes.zpl.zplanalyzer;


import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.BarcodeElement;
import com.bugdays.blackstripes.zpl.zplelement.TextElement;
import com.bugdays.blackstripes.zpl.zplelement.ZplElementBase;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldDataZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern DATA_PATTERN = Pattern.compile("FD(?<data>.+)");

    public FieldDataZplCommandAnalyzer(VirtualPrinter printer) {
        super("FD", printer);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = DATA_PATTERN.matcher(command);
        if (matcher.find()) {
            String data = matcher.group("data");
            boolean barcodeFound = false;

            for (ZplElementBase element : virtualPrinter.getInProgressElements()) {
                if (element instanceof BarcodeElement) {
                    BarcodeElement barcodeElement = (BarcodeElement) element;
                    barcodeElement.setData(data);
                    barcodeFound = true;
                    if (barcodeElement.isHumanReadable()) {
                        virtualPrinter.addElement(new TextElement(data, virtualPrinter));
                    }
                    break;
                }
            }

            if (!barcodeFound) {
                virtualPrinter.addElement(new TextElement(data, virtualPrinter));
            }
        } else {
            virtualPrinter.addError("Invalid FD command: " + command);
        }
    }
}
