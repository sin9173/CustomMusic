package com.yg.cm.dto.response;

public class ResultCode {
    
    public static final Integer SUCCESS = 20000; //처리 성공
    
    public static final Integer CONFLICT = 40901; //중복
    
    public static final Integer UNAUTHORIZED = 40101; //인증 에러
    
    public static final Integer BAD_INFO = 42201; //잘못된 입력 정보

    public static final Integer ERROR = 50000; //에러
}
