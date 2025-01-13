package com.bugdays.blackstripes.zpl;

import java.util.List;

public class AnalyzeInfo {
    private List<String> errors;
    private List<String> unknownCommands;
    private List<LabelInfo> labelInfos;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getUnknownCommands() {
        return unknownCommands;
    }

    public void setUnknownCommands(List<String> unknownCommands) {
        this.unknownCommands = unknownCommands;
    }

    public List<LabelInfo> getLabelInfos() {
        return labelInfos;
    }

    public void setLabelInfos(List<LabelInfo> labelInfos) {
        this.labelInfos = labelInfos;
    }
}
