package org.example.hana.global.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Basic HttpStatusCode
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD REQUEST"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR"),

    // 각 Service에서 필요한 ErrorCode 추가

    //
    // Recruitment (공고)
    RECRUITMENT_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공고를 찾을 수 없습니다."),
    RECRUITMENT_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 공고를 찾을 수 없습니다."),
    RECRUITMENT_POST_MAX_PARTICIPANTS_EXCEEDED(HttpStatus.BAD_REQUEST, "최대 참여 인원을 초과했습니다."),

    //

    APPLICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 지원요청을 찾을 수 없습니다."),



    NO_PERMISSION_TO_MODIFY(HttpStatus.FORBIDDEN, "수정 권한이 없습니다."), // 추가된 에러 코드




    // Auth
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    WITHDRAW_USER(HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다.");

    private final HttpStatus status;
    private final String message;
}
