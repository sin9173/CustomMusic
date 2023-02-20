package com.yg.cm.entity.board;

import com.yg.cm.entity.TimeAndDelete;
import com.yg.cm.entity.board.reple.Reply;
import com.yg.cm.entity.file.BoardFile;
import com.yg.cm.entity.member.Member;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Board extends TimeAndDelete {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title; // 제목

    private String content; // 내용

    private int read_count; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원

    @OneToMany(mappedBy = "parentBoard", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>(); //댓글 리스트

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardFile> boardFiles = new ArrayList<>(); //게시판 파일

    public void createBoard(String title, String content, Member member) { //게시판 생성
        this.title = title;
        this.content = content;
        this.member = member;
        this.read_count = 0;
    }

    public void updateBoard(String title, String content) { //게시판 수정
        if(StringUtils.hasText(title)) this.title = title;
        if(StringUtils.hasText(content)) this.content = content;
    }

    public void readPlus() { //조회수 증가
        this.read_count++;
    }

    public void addReply(Reply reply) { //댓글 추가
        if(reply!=null) this.replyList.add(reply);
    }

}
