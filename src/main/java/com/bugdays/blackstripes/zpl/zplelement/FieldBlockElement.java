package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;

public class FieldBlockElement extends ZplElementBase {
    private String text;
    private int width;
    private int maxLines;
    private int lineSpacing;
    private String rotation;

    public FieldBlockElement(String text, int width, int maxLines, int lineSpacing, Point position) {
        System.out.println("FieldBlockElement======");
        this.text = text;
        this.width = width;
        this.maxLines = maxLines;
        this.lineSpacing = lineSpacing;
        this.setX(position.x);
        this.setY(position.y);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getText() {
        return text;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public String getRotation() {
        return rotation;
    }

    @Override
    public void draw(Graphics2D graphics) {
        FontMetrics metrics = graphics.getFontMetrics();
        int lineHeight = metrics.getHeight() + lineSpacing;
        int y = this.getY();

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int lineCount = 0;

        for (String word : words) {
            String testLine = line + word + " ";
            int lineWidth = metrics.stringWidth(testLine);
            if (lineWidth > width && line.length() > 0) {
                drawRotatedText(graphics, line.toString(), this.getX(), y);
                line = new StringBuilder(word + " ");
                y += lineHeight;
                lineCount++;
                if (lineCount >= maxLines) {
                    break;
                }
            } else {
                line.append(word).append(" ");
            }
        }
        if (lineCount < maxLines) {
            drawRotatedText(graphics, line.toString(), this.getX(), y);
        }
    }

    private void drawRotatedText(Graphics2D graphics, String text, int x, int y) {
        System.out.println("Drawing text with rotation: " + rotation);
        if ("B".equals(rotation)) {
            graphics.rotate(Math.toRadians(270), x, y);
            graphics.drawString(text, x, y);
            graphics.rotate(Math.toRadians(-270), x, y);
        } else if ("I".equals(rotation)) {
            graphics.rotate(Math.toRadians(180), x, y);
            graphics.drawString(text, x, y);
            graphics.rotate(Math.toRadians(-180), x, y);
        } else if ("R".equals(rotation)) {
            graphics.rotate(Math.toRadians(90), x, y);
            graphics.drawString(text, x, y);
            graphics.rotate(Math.toRadians(-90), x, y);
        } else {
            graphics.drawString(text, x, y);
        }
    }
}