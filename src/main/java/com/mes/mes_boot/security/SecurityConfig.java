// Spring Security 설정 클래스
package com.mes.mes_boot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration // 설정 클래스임을 명시
public class SecurityConfig {

    @Bean  // Spring 컨테이너에 빈으로 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF(Cross-Site Request Forgery) 공격 방지 기능을 끔
                // 개발 단계나 REST API에서는 보통 비활성화함
                .csrf(AbstractHttpConfigurer::disable)

                // CORS(Cross-Origin Resource Sharing) 설정
                // 다른 도메인에서 이 서버로 요청을 보낼 수 있게 허용하는 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 각 URL별로 접근 권한을 설정하는 부분
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // 모든 요청 허용
                );

        return http.build();  // 설정이 완료된 SecurityFilterChain 객체를 반환
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // CORS 설정을 담는 객체 생성
        CorsConfiguration configuration = new CorsConfiguration();

        // 어떤 도메인에서 요청을 보낼 수 있는지 설정
        // 현재는 http://localhost:3000 (프론트엔드 서버)만 허용
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));// 허용할 오리진 설정

        // 허용할 HTTP 메서드들 설정 (GET, POST, PUT, DELETE, OPTIONS)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 모든 헤더를 허용 (Authorization, Content-Type 등)
        configuration.setAllowedHeaders(List.of("*"));

        // 쿠키나 인증 정보를 포함한 요청을 허용할지 설정
        configuration.setAllowCredentials(true);

        // URL 패턴별로 CORS 설정을 적용하는 객체
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 모든 경로("/**")에 위에서 만든 CORS 설정을 적용
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

