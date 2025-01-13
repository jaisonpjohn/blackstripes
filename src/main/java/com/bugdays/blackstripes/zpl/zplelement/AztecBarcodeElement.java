package com.bugdays.blackstripes.zpl.zplelement;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AztecBarcodeElement extends ZplElementBase {
    private String content;
    private int magnificationFactor;
    private int errorControl;

    public AztecBarcodeElement(String content, Point position, int magnificationFactor, int errorControl) {
        this.content = content;
        this.setX(position.x);
        this.setY(position.y);
        this.magnificationFactor = magnificationFactor;
        this.errorControl = errorControl;
    }

    public String getContent() {
        return content;
    }

    public int getMagnificationFactor() {
        return magnificationFactor;
    }

    public int getErrorControl() {
        return errorControl;
    }

    @Override
    public void draw(Graphics2D graphics) {
        try {
            BufferedImage aztecImage = generateAztecBarcode(content, magnificationFactor, errorControl);
            graphics.drawImage(aztecImage, this.getX(), this.getY()+100, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage generateAztecBarcode(String content, int magnificationFactor, int errorControl) throws Exception {
        com.google.zxing.aztec.AztecWriter writer = new com.google.zxing.aztec.AztecWriter();
        com.google.zxing.common.BitMatrix matrix = writer.encode(content,
                com.google.zxing.BarcodeFormat.AZTEC,
                100 * magnificationFactor,
                100 * magnificationFactor);
        
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
