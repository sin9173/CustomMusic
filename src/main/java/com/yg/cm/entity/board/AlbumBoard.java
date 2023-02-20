package com.yg.cm.entity.board;

import com.yg.cm.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("ALBUM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumBoard extends Board { //앨범 게시판

    public AlbumBoard(String title, String content, Member member) {
        createBoard(title, content, member);
    }
}
