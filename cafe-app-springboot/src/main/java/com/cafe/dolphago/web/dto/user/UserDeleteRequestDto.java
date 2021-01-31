package com.cafe.dolphago.web.dto.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDeleteRequestDto {
    String uid;
    UserDeleteRequestDto(String uid){
        this.uid=uid;
    }
}
