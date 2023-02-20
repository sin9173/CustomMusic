package com.yg.cm.entity.board;

import com.yg.cm.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("COMMUNITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityBoard extends Board { //커뮤니티 게시판 

    public CommunityBoard(String title, String content, Member member) {
        createBoard(title, content, member);
    }
}
