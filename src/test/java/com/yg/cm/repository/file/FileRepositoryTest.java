package com.yg.cm.repository.file;

import com.yg.cm.entity.file.ProfileFile;
import com.yg.cm.entity.member.Member;
import com.yg.cm.entity.member.Profile;
import com.yg.cm.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FileRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FileRepository fileRepository;


    @Test
    public void fileDeleteTest() {
        Member member1 = new Member("user1", "111");
        Profile profile1 = new Profile("member1", "aaa", member1);
        ProfileFile profileFile1 = new ProfileFile("파일명", "파일경로", 3, profile1);

        member1.addProfile(profile1);

        profile1.addFile(profileFile1);

        memberRepository.save(member1);

        fileRepository.delete(profileFile1);
    }

}