package com.yg.cm.entity.file;

import com.yg.cm.entity.TimeAndDelete;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED) // default -> SINGLE
@DiscriminatorColumn //DType 구성
public abstract class Files extends TimeAndDelete { //파일정보

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String fileName; //파일명

    private String filePath; //파일 경로
    
    public void updateFileInfo(String fileName, String filePath) { //파일 정보 생성, 수정
        this.fileName = fileName;
        this.filePath = filePath;
    }

}
