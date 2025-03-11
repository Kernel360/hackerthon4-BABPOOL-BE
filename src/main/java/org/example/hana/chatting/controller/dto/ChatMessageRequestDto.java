package org.example.hana.chatting.controller.dto;

public record ChatMessageRequestDto(Long userId, String nickname, String content) {
}
