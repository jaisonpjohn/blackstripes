package com.bugdays.blackstripes.zpl;

import com.bugdays.blackstripes.zpl.zplelement.ZplElementBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualPrinter {
    private List<ZplElementBase> elements = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private String fontName = "Arial";
    private int fontHeight = 9;
    private int fontWidth = 0;
    private Point currentPosition = new Point(0, getFontHeight());


    public void addElement(ZplElementBase element) {
        elements.add(element);
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

    public void markFieldComplete() {
        this.currentPosition = new Point(0, getFontHeight());
    }
}
