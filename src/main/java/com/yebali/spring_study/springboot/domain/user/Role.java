package com.yebali.spring_study.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    //권한코드 앞에는 항상 'ROLE'으로 시작해야한다.
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    public final String key;
    public final String title;
}
