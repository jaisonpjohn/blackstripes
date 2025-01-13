package com.bugdays.blackstripes.zpl;



import com.bugdays.blackstripes.zpl.zplanalyzer.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZplParser {
    private List<ZplCommandAnalyzerBase> analyzers = new ArrayList<>();
    private VirtualPrinter virtualPrinter = new VirtualPrinter();

    public ZplParser() {
        analyzers.add(new FieldOriginZplCommandAnalyzer(virtualPrinter));
        analyzers.add(new FieldDataZplCommandAnalyzer(virtualPrinter));
        analyzers.add(new AztecBarcodeZplCommandAnalyzer(virtualPrinter));
        analyzers.add(new ChangeAlphanumericDefaultFontZplCommandAnalyzer(virtualPrinter));
        analyzers.add(new FieldSeparatorZplCommandAnalyzer(virtualPrinter));

        // Add other analyzers here
    }

    public AnalyzeInfo parse(String zplData) {
        AnalyzeInfo info = new AnalyzeInfo();
        LabelInfo labelInfo = new LabelInfo();
        labelInfo.setZplElements(new ArrayList<>());

        String[] lines = zplData.split("\\^");
        for (String line : lines) {
            boolean matched = false;
            for (ZplCommandAnalyzerBase analyzer : analyzers) {
                if (line.startsWith(analyzer.getCommandPrefix())) {
                    analyzer.analyze(line);
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                virtualPrinter.addError("Unknown command: " + line);
            }
        }

        labelInfo.setZplElements(virtualPrinter.getElements());
        info.setLabelInfos(Collections.singletonList(labelInfo));
        info.setErrors(virtualPrinter.getErrors());

        return info;
    }
}
