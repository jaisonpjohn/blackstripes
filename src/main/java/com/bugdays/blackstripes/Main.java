package com.bugdays.blackstripes;

import com.bugdays.blackstripes.zpl.AnalyzeInfo;
import com.bugdays.blackstripes.zpl.ImageRenderer;
import com.bugdays.blackstripes.zpl.ZplParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String zplData =
                """
                        ^FO100,200
                        ^CF0,20
                        ^FDString-ABC^FS
                        ^CF0,40
                        ^FDString-XYZ^FS
                        """;

        ZplParser parser = new ZplParser();
        AnalyzeInfo info = parser.parse(zplData);

        ImageRenderer renderer = new ImageRenderer();
        BufferedImage image = renderer.render(info.getLabelInfos().get(0).getZplElements(), 800, 600);

        try {
            ImageIO.write(image, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}