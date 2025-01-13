package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class DataMatrixElement extends ZplElementBase {
    private String data;
    private int moduleWidth; // Smallest unit width in pixels
    private int height;      // Total height in dots

    public DataMatrixElement(String data, Point position, int moduleWidth, int height) {
        this.data = data;
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
            System.out.println("DataMatrixElement.drawing at " + this.getX() + "," + this.getY()+" with data: "+data + " moduleWidth: "+moduleWidth + " height: "+height);
            // Generate the Data Matrix barcode image
            BufferedImage barcodeImage = generateDataMatrix(data, moduleWidth, height);
            graphics.drawImage(barcodeImage, this.getX(), this.getY(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage generateDataMatrix(String data, int moduleWidth, int height) throws Exception {
        DataMatrixWriter writer = new DataMatrixWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.DATA_MATRIX, moduleWidth * 10, height);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
