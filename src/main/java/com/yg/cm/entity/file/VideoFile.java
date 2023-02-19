package com.yg.cm.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("VIDEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoFile extends Files { //동영상 파일

    public VideoFile(String fileName, String filePath) {
        updateFileInfo(fileName, filePath);
    }
}
