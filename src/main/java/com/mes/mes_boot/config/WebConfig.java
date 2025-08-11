package com.mes.mes_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 클래스임을 명시
public class WebConfig implements WebMvcConfigurer {// Web MVC 설정 인터페이스 구현

    @Override
    public void addCorsMappings(CorsRegistry registry) { // CORS 매핑 설정 메서드 오버라이드
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins(
                        "http://localhost:3000",          // 로컬 개발 환경
                        "https://mes-web.vercel.app"      // Vercel 배포 주소
                )// 허용할 Origin (프론트엔드 주소)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 인증 정보 포함 허용
                .maxAge(3600); // preflight 요청 캐시 시간 (1시간)
    }
}