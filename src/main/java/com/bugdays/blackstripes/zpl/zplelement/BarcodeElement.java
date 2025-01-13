package com.bugdays.blackstripes.zpl.zplelement;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BarcodeElement extends ZplElementBase {
    private String data;
    private String type;
    private int height;
    private boolean humanReadable;

    public void setData(String data) {
        this.data = data;
    }


    public BarcodeElement(String data, String type, Point position, int height, boolean humanReadable) {
        this.data = data;
        this.type = type;
        this.setX(position.x);
        this.setY(position.y);
        this.height = height;
        this.humanReadable = humanReadable;
    }

    @Override
    public void draw(Graphics2D graphics) {
        try {
            BufferedImage barcodeImage = generateBarcode(data, height, type);
            graphics.drawImage(barcodeImage, this.getX(), this.getY(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage generateBarcode(String data, int height, String type) throws Exception {
        com.google.zxing.BarcodeFormat format = com.google.zxing.BarcodeFormat.CODE_128; // Default to Code 128
        com.google.zxing.common.BitMatrix matrix = new com.google.zxing.oned.Code128Writer().encode(
                data,
                format,
                200, // Width (can be calculated based on moduleWidth)
                height
        );

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    public boolean isHumanReadable() {
        return humanReadable;
    }
}
