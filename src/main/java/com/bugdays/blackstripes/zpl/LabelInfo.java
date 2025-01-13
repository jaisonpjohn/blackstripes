package com.bugdays.blackstripes.zpl;

import com.bugdays.blackstripes.zpl.zplelement.ZplElementBase;

import java.util.List;

public class LabelInfo {
    private String downloadFormatName;
    private List<ZplElementBase> zplElements;

    public String getDownloadFormatName() {
        return downloadFormatName;
    }

    public void setDownloadFormatName(String downloadFormatName) {
        this.downloadFormatName = downloadFormatName;
    }

    public List<ZplElementBase> getZplElements() {
        return zplElements;
    }

    public void setZplElements(List<ZplElementBase> zplElements) {
        this.zplElements = zplElements;
    }
}
