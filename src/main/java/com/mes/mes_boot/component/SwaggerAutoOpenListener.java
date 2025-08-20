package com.mes.mes_boot.component;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 계속 자동 실행되서 주석함//@Component // Spring 컴포넌트로 등록
// ApplicationListener<ApplicationReadyEvent>를 구현해서
// 애플리케이션이 완전히 준비되었을 때 이벤트를 받을 수 있음
public class SwaggerAutoOpenListener implements ApplicationListener<ApplicationReadyEvent> {

    // ApplicationReadyEvent가 발생했을 때 자동으로 호출되는 메서드
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 자동으로 열 Swagger UI 주소 설정
        String url = "http://localhost:7010/swagger-ui/index.html";

        // 현재 실행 중인 운영체제 이름을 소문자로 가져옴
        // 예: "Windows 10" → "windows 10"
        String os = System.getProperty("os.name").toLowerCase();


        // 환경변수에 "DOCKER_ENV"가 있는지 확인
        // 도커 컨테이너 안에서는 브라우저를 열 수 없으므로 실행하지 않음
        // 도커 환경에서는 브라우저 자동 실행하지 않음
        if (System.getenv().containsKey("DOCKER_ENV")) {
            return;
        }

        try {
            if (os.contains("win")) {
                // Windows 명령어로 기본 브라우저에서 URL 열기
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}