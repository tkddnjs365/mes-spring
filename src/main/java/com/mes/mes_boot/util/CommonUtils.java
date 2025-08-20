package com.mes.mes_boot.util;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * 공통 유틸리티 클래스
 * 애플리케이션 전반에서 재사용되는 공통 기능들을 제공
 */
public class CommonUtils {

    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 문자열이 null이거나 빈 문자열인지 검사
     *
     * @param str 검사할 문자열
     * @return null이거나 빈 문자열이면 true
     */
    public static boolean isEmpty(String str) {
        return !StringUtils.hasText(str);
    }

    /**
     * 문자열이 null이 아니고 공백이 아닌 문자가 포함되어 있는지 검사
     *
     * @param str 검사할 문자열
     * @return 유효한 문자열이면 true
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.hasText(str);
    }

    /**
     * 컬렉션이 null이거나 비어있는지 검사
     *
     * @param collection 검사할 컬렉션
     * @return null이거나 비어있으면 true
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 컬렉션이 null이 아니고 요소가 존재하는지 검사
     *
     * @param collection 검사할 컬렉션
     * @return 유효한 컬렉션이면 true
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 현재 시간을 기본 형식으로 포맷팅
     *
     * @return 포맷된 현재 시간 문자열
     */
    public static String getCurrentTimeString() {
        return LocalDateTime.now().format(DEFAULT_DATE_FORMATTER);
    }

    /**
     * 지정된 형식으로 현재 시간을 포맷팅
     *
     * @param formatter 날짜 포맷터
     * @return 포맷된 현재 시간 문자열
     */
    public static String getCurrentTimeString(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * null 값을 빈 문자열로 변환
     *
     * @param str 입력 문자열
     * @return null이면 빈 문자열, 아니면 원본 문자열
     */
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    /**
     * 빈 문자열을 null로 변환
     *
     * @param str 입력 문자열
     * @return 빈 문자열이면 null, 아니면 원본 문자열
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }
}