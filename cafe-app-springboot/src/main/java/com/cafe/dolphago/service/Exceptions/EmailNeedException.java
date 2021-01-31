package com.cafe.dolphago.service.Exceptions;

public class EmailNeedException extends RuntimeException{
    public String EmailNeedException() {
        return "이메일 동의가 필요합니다.";
    }
}
