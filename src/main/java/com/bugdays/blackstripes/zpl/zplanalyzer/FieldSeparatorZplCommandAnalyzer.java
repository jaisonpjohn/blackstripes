package com.bugdays.blackstripes.zpl.zplanalyzer;

import com.bugdays.blackstripes.zpl.VirtualPrinter;

public class FieldSeparatorZplCommandAnalyzer extends ZplCommandAnalyzerBase {

    public FieldSeparatorZplCommandAnalyzer(VirtualPrinter virtualPrinter) {
        super("FS", virtualPrinter);
    }

    @Override
    public void analyze(String command) {
        virtualPrinter.closeFieldOrigin();
    }
}
