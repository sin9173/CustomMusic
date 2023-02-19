package com.yg.cm.entity;

import com.yg.cm.entity.member.enums.DelIt;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class TimeAndDelete {

    @Column(updatable = false)
    private LocalDateTime regDate; // 생성 일시

    private LocalDateTime updateDate; // 수정 일시

    @Enumerated(EnumType.STRING)
    private DelIt delIt; //삭제여부

    @PrePersist
    public void create() {
        LocalDateTime now = LocalDateTime.now();
        regDate = now;
        updateDate = now;
        delIt = DelIt.ACTIVE;
    }

    @PreUpdate
    public void update() {
        updateDate = LocalDateTime.now();
    }
}
