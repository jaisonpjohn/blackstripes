package com.bugdays.blackstripes.zpl;

import com.bugdays.blackstripes.zpl.zplelement.ZplElementBase;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;


public class ImageRenderer {
    public BufferedImage render(List<ZplElementBase> elements, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 400);

        // Set up rendering settings
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ZplElementBase element : elements) {
            element.draw(graphics);
        }

        graphics.dispose();
        return image;
    }
}

