package com.mes.mes_boot.exception;

/**
 * 비즈니스 로직 예외 클래스
 * 애플리케이션의 비즈니스 규칙 위반 시 발생하는 예외를 나타냄
 */
public class BusinessException extends RuntimeException {

    /**
     * 메시지를 포함한 비즈니스 예외 생성
     *
     * @param message 예외 메시지
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 메시지와 원인을 포함한 비즈니스 예외 생성
     *
     * @param message 예외 메시지
     * @param cause   원인 예외
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}