package com.yg.cm.dto.request.member.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileAddRequestDto {

    private String name;

    private String email;
}
