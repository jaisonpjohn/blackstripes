package com.bugdays.blackstripes.zpl.zplelement;

import java.awt.*;

public class FieldBlockElement extends ZplElementBase {
    private String text;
    private int width;
    private int maxLines;
    private int lineSpacing;

    public FieldBlockElement(String text, int width, int maxLines, int lineSpacing, Point position) {
        this.text = text;
        this.width = width;
        this.maxLines = maxLines;
        this.lineSpacing = lineSpacing;
        this.setX(position.x);
        this.setY(position.y);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
                graphics.drawString(line.toString(), this.getX(), y);
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
            graphics.drawString(line.toString(), this.getX(), y);
        }
    }
}