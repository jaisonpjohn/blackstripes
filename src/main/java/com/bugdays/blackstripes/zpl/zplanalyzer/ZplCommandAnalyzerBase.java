package com.bugdays.blackstripes.zpl.zplanalyzer;


import com.bugdays.blackstripes.zpl.VirtualPrinter;

public abstract class ZplCommandAnalyzerBase {
    protected String commandPrefix;
    protected VirtualPrinter virtualPrinter;

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    public VirtualPrinter getVirtualPrinter() {
        return virtualPrinter;
    }

    public void setVirtualPrinter(VirtualPrinter virtualPrinter) {
        this.virtualPrinter = virtualPrinter;
    }

    public ZplCommandAnalyzerBase(String prefix, VirtualPrinter printer) {
        this.commandPrefix = prefix;
        this.virtualPrinter = printer;
    }

    public abstract void analyze(String command);
}
