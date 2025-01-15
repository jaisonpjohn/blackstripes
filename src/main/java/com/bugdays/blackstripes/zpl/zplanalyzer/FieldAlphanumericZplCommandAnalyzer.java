package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;
import com.bugdays.blackstripes.zpl.zplelement.FieldBlockElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldAlphanumericZplCommandAnalyzer extends ZplCommandAnalyzerBase {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("A@?(?<rotation>[NBRI]),(?<fontHeight>\\d+),(?<fontWidth>\\d*),?(?<fontName>[^,]*)");

    public FieldAlphanumericZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("A", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        Matcher matcher = COMMAND_PATTERN.matcher(command);
        if (matcher.find()) {
            String rotation = matcher.group("rotation");
            int fontHeight = Integer.parseInt(matcher.group("fontHeight"));
            int fontWidth = matcher.group("fontWidth").isEmpty() ? 0 : Integer.parseInt(matcher.group("fontWidth"));
            String fontName = matcher.group("fontName");
            System.out.println("GG-fontName: " + fontName);
            System.out.println("GG-fontHeight: " + fontHeight);
            System.out.println("fGG-ontWidth: " + fontWidth);
            System.out.println("GG-rotation: " + rotation);
            virtualPrinter.setFontName(fontName);
            virtualPrinter.setFontHeight(fontHeight);
            virtualPrinter.setFontWidth(fontWidth);
            virtualPrinter.setFontRotation(rotation);

            //print all these values that set
            System.out.println("AAAA-GG-virtualPrinter.getFontName(): " + virtualPrinter.getFontName());
            System.out.println("AAAA-GG-virtualPrinter.getFontHeight(): " + virtualPrinter.getFontHeight());
            System.out.println("AAAA-GG-virtualPrinter.getFontWidth(): " + virtualPrinter.getFontWidth());
            System.out.println("AAAA-GG-virtualPrinter.getFontRotation(): " + virtualPrinter.getFontRotation());


            // Also, I think this shouldnt mutate the entire virtual printer, but rather the current element since its name
            // I think this check will always resolve to true
            if (virtualPrinter.isFieldOriginOpen()) {
                System.out.println("GG-FieldOriginOpen: " + virtualPrinter.isFieldOriginOpen());

                /*virtualPrinter.getInProgressElements().forEach(element -> {
                    if (element instanceof FieldBlockElement) {
                        System.out.println("GG-FieldBlockElement: " + element);
                        FieldBlockElement element1 = (FieldBlockElement) element;
                        element1.setRotation(rotation);
                        element1.setWidth(fontHeight);
                        element1.setWidth(fontWidth);
                    }
                });*/
            }
        } else {
            virtualPrinter.addError("Invalid A command: " + command);
        }
    }
}