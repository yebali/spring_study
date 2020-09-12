package com.yebali.spring_study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // Jap Auditing기능(생성, 수정시간자동저장) 활성화
//@EnableJpaAuditing를 사용하기 위해서는 하나 이상의 @Entity가 필요함. Test에는 없어서 테스트가 불가능 하기때문에 class로 분리함
@SpringBootApplication//:스프링 부트의 자동설정 Bean읽기와 생성을 모두 자동으로 설정 항상 프로젝트 최상단에 있어야함
public class Application {
    public static void main(String[] args) {
        //SpringApplicaion으로 인해 내장 WAS를 실행
        SpringApplication.run(Application.class, args);
    }
}
