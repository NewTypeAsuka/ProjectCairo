package com.newtypeblog.projectcairo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 부트스트랩 어노테이션: 시작을 시작하는 코드 */

/*
1. JVM에서 main() 실행
2. SpringApplication 객체 생성
3. SpringApplication.run()
4. ApplicationContext 생성
5. @SpringBootApplication 해석
    ├─ @ComponentScan
    ├─ @EnableAutoConfiguration
6. 모든 빈 등록 및 의존성 주입
7. 내장 Tomcat 생성
8. 8080 포트 바인딩
9. 서버 대기 상태 진입
* */

@SpringBootApplication
public class ProjectCairoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectCairoApplication.class, args);
    }
}