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
^XA
^CF0,30
^FO300,30^FDHU  Label^FS
^CF0,25
^FO20,100^FDHU ID:         112345678000001107^FS
^BY2.2,3,70
^FO20,130^BCN,,N^FD112345678000001107^FS
^FO20,230^FD60-Volt Cordless Electric Hedge Trimmer^FS
^FO20,260^FD13^FS
^FO650,200^BQN,2,5^FDQA,^FS
^XZ
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