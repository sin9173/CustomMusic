package com.yg.cm.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("THUMBNAIL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThumbnailFile extends Files { //썸네일 파일

    public ThumbnailFile(String fileName, String filePath) {
        updateFileInfo(fileName, filePath);
    }

}
