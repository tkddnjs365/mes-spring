package com.mes.mes_boot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 통일된 API 응답 형태를 위한 제네릭 응답 클래스
 * 모든 API 응답은 이 클래스를 통해 일관된 구조로 반환
 *
 * @param <T> 응답 데이터 타입
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null 값은 JSON에 포함하지 않음
public class ApiResponse<T> {

    /**
     * 요청 성공 여부
     */
    private boolean success;

    /**
     * 응답 메시지
     */
    private String message;

    /**
     * 응답 데이터
     */
    private T data;

    /**
     * 오류 상세 정보 (유효성 검증 오류 등)
     */
    private Object errors;

    /**
     * 성공 응답 생성 (데이터 포함)
     *
     * @param data 응답 데이터
     * @param <T>  데이터 타입
     * @return 성공 응답
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("성공")
                .data(data)
                .build();
    }

    /**
     * 성공 응답 생성 (메시지와 데이터 포함)
     *
     * @param message 응답 메시지
     * @param data    응답 데이터
     * @param <T>     데이터 타입
     * @return 성공 응답
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 실패 응답 생성 (메시지만 포함)
     *
     * @param message 에러 메시지
     * @return 실패 응답
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    /**
     * 실패 응답 생성 (메시지와 상세 에러 정보 포함)
     *
     * @param message 에러 메시지
     * @param errors  상세 에러 정보
     * @return 실패 응답
     */
    public static <T> ApiResponse<T> error(String message, Object errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}