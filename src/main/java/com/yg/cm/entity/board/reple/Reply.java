package com.yg.cm.entity.board.reple;

import com.yg.cm.entity.TimeAndDelete;
import com.yg.cm.entity.board.Board;
import com.yg.cm.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends TimeAndDelete { //댓글

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    private String content; //내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board parentBoard; //게시판

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_reply_id")
    private Reply parentReply; //상위 댓글

    @OneToMany(mappedBy = "parentReply", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>(); //대댓글 리스트

    public Reply(String content, Member member, Board parentBoard) { //Create
        this.content = content;
        this.member = member;
        this.parentBoard = parentBoard;
    }
    public Reply(String content, Member member, Reply parentReply) { //Create
        this.content = content;
        this.member = member;
        this.parentReply = parentReply;
    }

    public void updateReply(String content) { // 댓글 수정
        if(StringUtils.hasText(content)) this.content = content;
    }

    public void addReply(Reply reply) { //댓글 추가
        if(reply!=null) this.replyList.add(reply);
    }
}
