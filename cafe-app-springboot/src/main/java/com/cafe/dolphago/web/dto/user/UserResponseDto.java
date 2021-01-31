package com.cafe.dolphago.web.dto.user;

import com.cafe.dolphago.domain.user.Role;
import com.cafe.dolphago.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long uuid;
    private String uid;
    private String upass;
    private String uname;
    private String uphone;
    private String uemail;
    private String unickname;
    private Role role;
    private String upic;

    public UserResponseDto(User entity) {
        this.uuid = entity.getUuid();
        this.uid = entity.getUid();
        this.upass = entity.getUpass();
        this.uemail = entity.getUemail();
        this.unickname = entity.getUnickname();
        this.role = entity.getRole();
        this.upic=entity.getUpic();
    }
}
