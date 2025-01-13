package com.bugdays.blackstripes.zpl.zplelement;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarcodeElement extends ZplElementBase {
    private String data;
    private String type;
    private final double barcodeModuleWidth;
    private int height;
    private boolean humanReadable;

    public void setData(String data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public BarcodeElement(String data, String type, Point position, double barcodeModuleWidth, int height, boolean humanReadable) {
        this.data = data;
        this.type = type;
        this.barcodeModuleWidth = barcodeModuleWidth;
        this.setX(position.x);
        this.setY(position.y);
        this.height = height;
        this.humanReadable = humanReadable;
    }

    @Override
    public void draw(Graphics2D graphics) {
        System.out.println("BarcodeElement.drawing at " + this.getX() + "," + this.getY());
        try {
            BufferedImage barcodeImage = generateBarcode(data, height, type);
            graphics.drawImage(barcodeImage, this.getX(), this.getY(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage generateBarcode(String data, int height, String type) throws Exception {
        com.google.zxing.BarcodeFormat format = com.google.zxing.BarcodeFormat.CODE_128;
        System.out.println("calculatedTotalWidth" + calculateTotalWidth(data));
        com.google.zxing.common.BitMatrix matrix = new com.google.zxing.oned.Code128Writer().encode(
                data,
                format,
                calculateTotalWidth(data),
                height
        );

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    public boolean isHumanReadable() {
        return humanReadable;
    }
    public int calculateTotalWidth(String data) {
        // Approximate number of modules in Code 128: 11 modules per character
        int numberOfModules = data.length() * 11;

        // Convert moduleWidth to an integer by scaling (e.g., 1 module = 2 pixels)
        int moduleWidthInPixels = (int) Math.ceil(barcodeModuleWidth * 1); // Adjust scaling factor if needed

        // Total width in pixels
        return numberOfModules * moduleWidthInPixels;
    }
}
