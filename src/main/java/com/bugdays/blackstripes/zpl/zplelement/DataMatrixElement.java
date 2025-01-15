package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class DataMatrixElement extends ZplElementBase {
    private String data;
    private final String orientation;
    private int moduleWidth; // Smallest unit width in pixels
    private int height;      // Total height in dots

    public DataMatrixElement(String data, Point position, int moduleWidth, int height, String orientation) {
        this.data = data;
        this.orientation = orientation;
        this.setX(position.x);
        this.setY(position.y);
        this.moduleWidth = moduleWidth;
        this.height = height;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public void draw(Graphics2D graphics) {
        try {
            System.out.println("DataMatrixElement.drawing at " + this.getX() + "," + this.getY() + " with data: " + data + " moduleWidth: " + moduleWidth + " height: " + height);
            // Generate the Data Matrix barcode image
            BufferedImage barcodeImage = generateDataMatrix(data, moduleWidth, height);

            // Save the original transform
            AffineTransform originalTransform = graphics.getTransform();

            // Calculate the center of the image
            int centerX = barcodeImage.getWidth() / 2;
            int centerY = barcodeImage.getHeight() / 2;

            // Translate to the center of the image
            graphics.translate(this.getX() + centerX, this.getY() + centerY);

            // Apply rotation based on orientation
            if ("R".equals(orientation)) {
                graphics.rotate(Math.toRadians(90));
            } else if ("I".equals(orientation)) {
                graphics.rotate(Math.toRadians(180));
            } else if ("B".equals(orientation)) {
                graphics.rotate(Math.toRadians(270));
            }

            // Draw the barcode image centered at the origin
            graphics.drawImage(barcodeImage, -centerX, -centerY, null);

            // Restore the original transform
            graphics.setTransform(originalTransform);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage generateDataMatrix(String data, int moduleWidth, int height) throws Exception {
        DataMatrixWriter writer = new DataMatrixWriter();
        // Increase the moduleWidth and height to generate a larger image
        int largerModuleWidth = 100;
        int largerHeight = height * 30;
        BitMatrix matrix = writer.encode(data, BarcodeFormat.DATA_MATRIX, largerModuleWidth, largerHeight);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
