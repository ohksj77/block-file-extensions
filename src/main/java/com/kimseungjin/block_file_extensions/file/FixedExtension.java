package com.kimseungjin.block_file_extensions.file;

import java.util.Arrays;
import java.util.List;

public enum FixedExtension {
    BAT("bat"),
    CMD("cmd"),
    COM("com"),
    CPL("cpl"),
    EXE("exe"),
    SCR("scr"),
    JS("js");

    private final String extension;

    FixedExtension(final String extension) {
        this.extension = extension;
    }

    public static List<String> getAllValues() {
        return Arrays.stream(values()).map(element -> element.extension).toList();
    }
}
