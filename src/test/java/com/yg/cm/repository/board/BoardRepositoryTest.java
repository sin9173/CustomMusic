package com.yg.cm.repository.board;

import com.yg.cm.entity.board.AlbumBoard;
import com.yg.cm.entity.board.Board;
import com.yg.cm.entity.board.reple.Reply;
import com.yg.cm.entity.member.Member;
import com.yg.cm.entity.member.Profile;
import com.yg.cm.repository.member.MemberRepository;
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
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Rollback(value = false)
    public void boardSaveTest() {
        Member member1 = new Member("user1", "111");
        memberRepository.save(member1);
        Profile profile1 = new Profile("member1", "aaa", member1);
        member1.addProfile(profile1);

        Member member2 = new Member("user2", "111");
        memberRepository.save(member2);
        Profile profile2 = new Profile("member2", "bbb", member1);
        member1.addProfile(profile2);

        AlbumBoard albumBoard = new AlbumBoard("안녕제목", "안녕내용", member1);
        boardRepository.save(albumBoard);

        Reply reply1 = new Reply("안녕댓글1", member2, albumBoard);
        albumBoard.addReply(reply1);

        Reply reply2 = new Reply("안녕대댓글2", member1, reply1);
        reply1.addReply(reply2);


        entityManager.flush();

        String content = albumBoard.getReplyList().get(0)
                .getReplyList().get(0)
                .getContent();

        Assertions.assertThat(content).isEqualTo("안녕대댓글2");

    }
}