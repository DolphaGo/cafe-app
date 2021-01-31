package com.cafe.dolphago.web.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFindIdRequestDto {
    String uname;
    String uemail;

    public UserFindIdRequestDto(String uname, String uemail){
        this.uname=uname;
        this.uname=uemail;
    }
}
