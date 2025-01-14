package com.bugdays.blackstripes.zpl;

import com.bugdays.blackstripes.zpl.zplelement.ZplElementBase;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualPrinter {
    private String fontRotation= "N";
    private Map<String, BufferedImage> images = new HashMap<>();
    private double barcodeModuleWidth = 2.0;
    private int barcodeWideToNarrowRatio = 3;
    private int barcodeHeight = 70;

    public String getFontRotation() {
        return fontRotation;
    }

    public void addImage(String name, BufferedImage image) {
        images.put(name, image);
    }

    public boolean isFieldOriginOpen() {
        return fieldOriginOpen;
    }
    public void openFieldOrigin() {
        fieldOriginOpen = true;
    }

    public void closeFieldOrigin() {
        fieldOriginOpen = false;
        elements.addAll(inProgressElements);
        inProgressElements.clear();
        resetPosition();
        resetRotation();
    }

    private void resetRotation() {
        this.fontRotation = "N";
    }

    public void setBarcodeDefaults(double moduleWidth, int wideToNarrowRatio, int height) {
        this.barcodeModuleWidth = moduleWidth;
        this.barcodeWideToNarrowRatio = wideToNarrowRatio;
        this.barcodeHeight = height;
    }

    public int getBarcodeHeight() {
        return barcodeHeight;
    }

    public double getBarcodeModuleWidth() {
        return barcodeModuleWidth;
    }

    private List<ZplElementBase> elements = new ArrayList<>();
    private List<ZplElementBase> inProgressElements = new ArrayList<>();
    private boolean fieldOriginOpen = false;
    private List<String> errors = new ArrayList<>();
    private String fontName = "Arial";
    private int fontHeight = 9;
    private int fontWidth = 0;
    private Point currentPosition = new Point(0, getFontHeight());

    public List<ZplElementBase> getInProgressElements() {
        return inProgressElements;
    }

    public void addElement(ZplElementBase element) {
        if (fieldOriginOpen) {
            inProgressElements.add(element);
        } else {
            elements.add(element);
        }
    }

    public void addError(String error) {
        errors.add(error);
    }

    public List<ZplElementBase> getElements() {
        return elements;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point position) {
        this.currentPosition = position;
    }

    // Font-related methods
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public void setFontWidth(int fontWidth) {
        this.fontWidth = fontWidth;
    }

    public int getFontWidth() {
        return fontWidth;
    }

    private void resetPosition() {
        this.currentPosition = new Point(0, getFontHeight());
    }

    public BufferedImage getImage(String imageName) {
        return images.get(imageName);
    }

    public void setFontRotation(String rotation) {
        this.fontRotation = rotation;
    }


}
