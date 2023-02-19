package com.yg.cm.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("MUSIC")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicFile extends Files { //음악 파일
    
    private String genre; //장르

    public MusicFile(String fileName, String filePath, String genre) {
        updateFileInfo(fileName, filePath);
        this.genre = genre;
    }
}
