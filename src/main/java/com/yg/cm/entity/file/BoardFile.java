package com.yg.cm.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile extends Files { //게시판 파일

    public BoardFile(String fileName, String filePath) {
        updateFileInfo(fileName, filePath);
    }
}
