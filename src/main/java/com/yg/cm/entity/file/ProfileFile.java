package com.yg.cm.entity.file;


import com.yg.cm.entity.member.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("PROFILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileFile extends Files { //프로필 파일 정보

    private int seq; //순서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;


    public ProfileFile(String fileName, String filePath, int seq, Profile profile) {
        updateFileInfo(fileName, filePath);
        this.seq = seq;
        this.profile = profile;
    }
}
