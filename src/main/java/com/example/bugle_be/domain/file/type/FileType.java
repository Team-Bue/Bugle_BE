package com.example.bugle_be.domain.file.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum FileType {

    IMAGE_FILE("IMAGE_FILE", Set.of(".jpg", ".jpeg", ".png", ".gif", ".webp")),
    VIDEO_FILE("VIDEO_FILE", Set.of(".mp4", ".avi", ".mov"));

    private final String path;
    private final Set<String> extensions;

    public boolean isAllowedExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return extensions.contains(extension);
    }
}
