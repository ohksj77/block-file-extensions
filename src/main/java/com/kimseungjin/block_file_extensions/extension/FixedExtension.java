package com.kimseungjin.block_file_extensions.extension;

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

    public static boolean contains(final String extension) {
        return Arrays.stream(values()).anyMatch(element -> element.extension.equals(extension));
    }

    public static FixedExtension valueOfLowerCase(final String extension) {
        return Arrays.stream(values())
                .filter(element -> element.extension.equals(extension))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return this.extension;
    }
}
