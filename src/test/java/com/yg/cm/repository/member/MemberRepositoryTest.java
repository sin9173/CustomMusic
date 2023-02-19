package com.yg.cm.repository.member;

import com.yg.cm.entity.file.ProfileFile;
import com.yg.cm.entity.member.Member;
import com.yg.cm.entity.member.Profile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Rollback(value = false)
    public void memberSaveTest() {
        Member member = new Member("member1", "12345");
        memberRepository.save(member);
        Profile profile = new Profile("1번", "aaa", member);
        ProfileFile profileFile = new ProfileFile("file1", "file1", 3, profile);
        profile.addFile(profileFile);
        member.addProfile(profile);

        memberRepository.flush();

        Assertions.assertThat(memberRepository.findById(member.getId()).get().getProfiles().get(0).getName()).isEqualTo("1번");

        memberRepository.delete(member);
    }
}