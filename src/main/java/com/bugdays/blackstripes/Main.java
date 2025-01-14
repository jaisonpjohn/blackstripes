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
                        ^FO90,40
                        ^BXB,4,200,20,20
                        ^FD764845848839
                        ^FS
                        ^FO1100,50
                        ^XGE:1006.GRF,1,1
                        ^FS
                        ^FO160,140
                        ^GB5,400,5,B
                        ^FS
                        ^FT50,540
                        ^A@B,21,21,E:ARI001.TTF
                        ^FD105 AWSOME BLVD
                        ^FS
                        ^FT70,540
                        ^A@B,21,21,E:ARI001.TTF
                        ^FDYADAYADAVILLE, PA 95391
                        ^FS
                        ^FT90,540
                        ^A@B,21,21,E:ARI001.TTF
                        ^FD800-800-0000
                        ^FS
                        ^FT90,350
                        ^A@B,21,21,E:ARI001.TTF
                        ^FDDEA # A12345678
                        ^FS
                        ^FT110,540
                        ^A@B,21,21,E:ARI001.TTF
                        ^FDPIC: M. McCall
                        ^FS
                        ^FT110,340
                        ^A@B,21,21,E:ARI001.TTF
                        ^FD01-UU-001/KKD/TC
                        ^FS
                        ^FO115,150
                        ^A@B,21,21,E:ARI001.TTF
                        ^FD000000000650210222
                        ^FS
                        ^FO135,150
                        ^A@B,21,21,E:ARI001.TTF
                        ^FD222333444555
                        ^FS
                        ^FO200,50
                        ^A@N,33,33,E:ARI000.TTF
                        ^FDRx# 12345678
                        ^FS
                        ^FO200,95
                        ^A@N,50,50,E:ARI000.TTF
                        ^FDSHRX04123A MONITOR
                        ^FS
                        ^FO200,111
                        ^A@N,21,1
                        ^FD
                        ^FS
                        ^FO200,131
                        ^A@N,8,1
                        ^FD
                        ^FS
                        ^FO200,170
                        ^A@N,50,50,E:ARI000.TTF
                        ^FDSHARPS CONTAIN SHARPS CONTAIN SHARPS CO
                        ^FS
                        ^FO200,220
                        ^A@N,50,50,E:ARI001.TTF
                        ^FDAAAAA BBBB CCCC DDDD EEEE FFFF GGGG HHHH
                        ^FS
                        ^FO200,270
                        ^A@N,50,50,E:ARI000.TTF
                        ^FB1150,8,-2,,
                        ^FDTEST SHAKEOUT ORDER, DO NOT DISCONTINUE, DO NOT SHIP. TEST SHAKEOUT ORDER, DO NOT DISCONTINUE, DO NOT SHIP. TEST SHAKEOUT ORDER, DO NOT DISCONTINUE, DO NOT SHIP.
                        ^FS
                        ^FO1350,50
                        ^A@N,21,21,E:ARI001.TTF
                        ^FD105 AWSOME BLVD
                        ^FS
                        ^FO1350,70
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDYADAVILLE, PA, 15146-0000
                        ^FS
                        ^FO1350,90
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDDEA# BS1234567
                        ^FS
                        ^FO1350,110
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDRPH: }F
                        ^FS
                        ^FO1350,130
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDPhone:
                        ^FS
                        ^FO1430,130
                        ^A@N,21,21,E:ARI001.TTF
                        ^FD811-333-3333
                        ^FS
                        ^FO1400,260
                        ^A@N,21,21,E:ARI001.TTF
                        ^FD
                        ^FS
                        ^FO1400,280
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDDOE, JOHN MD
                        ^FS
                        ^FO1400,300
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDFilled: 09/18/2024
                        ^FS
                        ^FO1400,320
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDDescription:\s
                        ^FS
                        ^FO1400,340
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDORANGE WHITE
                        ^FS
                        ^FO1400,360
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDOBLONG
                        ^FS
                        ^FO1400,380
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDTM
                        ^FS
                        ^FO1400,440
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDBD MEDICAL SURGICAL SYSTEMS
                        ^FS
                        ^FO1400,460
                        ^A@N,21,21,E:ARI001.TTF
                        ^FB300,8,-1,,
                        ^FDSHARPS CONTAINER
                        ^FS
                        ^FO1400,480
                        ^A@N,21,21,E:ARI001.TTF
                        ^FD
                        ^FS
                        ^FO1400,500
                        ^A@N,21,21,E:ARI001.TTF
                        ^FDUse By: 10/04/2025
                        ^FS
                        ^FO1400,540
                        ^A@N,21,21,E:ARI000.TTF
                        ^FDQty: 5
                        ^FS
                        ^FO1490,540
                        ^A@N,21,21,E:ARI000.TTF
                        ^FDDEFAULT UN
                        ^FS
                        ^FO1400,560
                        ^A@N,21,21,E:ARI000.TTF
                        ^FDRefills Remaining: 5
                        ^FS
                        ^FT1750,350
                        ^A@B,21,21,E:ARI001.TTF
                        ^FDPTheBest Pharmacy
                        ^FS
                        ^FT1800,350
                        ^A@B,21,21,E:ARI001.TTF
                        ^FB400,2,-1,,
                        ^FDDirect, LLC
                        ^FS
                        ^XZ
                        """;

        ZplParser parser = new ZplParser();
        AnalyzeInfo info = parser.parse(zplData);

        ImageRenderer renderer = new ImageRenderer();
        BufferedImage image = renderer.render(info.getLabelInfos().get(0).getZplElements(), 2400, 800);

        try {
            ImageIO.write(image, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}