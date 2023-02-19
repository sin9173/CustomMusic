package com.yg.cm.entity.member;

import com.yg.cm.entity.TimeAndDelete;
import com.yg.cm.entity.file.ProfileFile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends TimeAndDelete { //회원 정보

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String name;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<ProfileFile> profileFiles = new ArrayList<>();

    public Profile(String name, String email, Member member) {
        this.name = name;
        this.email = email;
        this.member = member;
    }

    public void addFile(ProfileFile profileFile) {
        if(profileFile!=null) this.profileFiles.add(profileFile);
    }

    public void updateProfile(String name, String email) {
        if(StringUtils.hasText(name)) this.name = name;
        if(StringUtils.hasText(email)) this.email = email;
    }
}
