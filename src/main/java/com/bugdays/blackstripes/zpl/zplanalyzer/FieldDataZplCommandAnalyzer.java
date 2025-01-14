package com.bugdays.blackstripes.zpl.zplanalyzer;


import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.*;

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
            // TODO, need to revisit this. I dont know whethere we will exit with the first element we encounter or,
            //  we should just get the last one, or we should get all of them
            for (ZplElementBase element : virtualPrinter.getInProgressElements()) {
                if (element instanceof BarcodeElement) {
                    BarcodeElement barcodeElement = (BarcodeElement) element;
                    barcodeElement.setData(data);
                    barcodeFound = true;
                    if (barcodeElement.isHumanReadable()) {
                        //TODO: circular reference problem
                        // Calculate the position for the text element to be centered under the barcode
                        int textX = barcodeElement.getX() + (barcodeElement.calculateTotalWidth(data) / 2) - (180 / 2);
                        TextElement textElement = getTextElement(barcodeElement, data, textX);

                        virtualPrinter.addElement(textElement);
                    }
                    break;
                } else if (element instanceof DataMatrixElement) {
                    DataMatrixElement dataMatrixElement = (DataMatrixElement) element;
                    dataMatrixElement.setData(data);
                    barcodeFound = true;
                    break;
                } else if (element instanceof FieldBlockElement) {
                    FieldBlockElement fieldBlockElement = (FieldBlockElement) element;
                    fieldBlockElement.setText(data);
                    barcodeFound = true;
                    break;
                }
            }

            if (!barcodeFound) {
                //TODO: circular reference problem
                virtualPrinter.addElement(new TextElement(data, virtualPrinter));
            }
        } else {
            virtualPrinter.addError("Invalid FD command: " + command);
        }
    }

    private TextElement getTextElement(BarcodeElement barcodeElement, String data, int textX) {
        int textY = barcodeElement.getY() + barcodeElement.getHeight() + 12; // Adjust the Y position as needed

        TextElement textElement = new TextElement(data, virtualPrinter);
        textElement.setX(textX);
        textElement.setY(textY);
        textElement.setFontName("Courier New"); // Set a different font
        textElement.setFontHeight(12); // Adjust the font size as needed
        return textElement;
    }
}
