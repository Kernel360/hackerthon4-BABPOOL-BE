package org.example.hana.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.example.hana.chatting.repository.ChatMessageRepository;
import org.example.hana.chatting.controller.dto.ChatMessageRequestDto;
import org.example.hana.chatting.controller.dto.ChatMessageResponseDto;
import org.example.hana.chatting.entity.ChatMessage;
import org.example.hana.user.entity.User;
import org.example.hana.user.repository.UserRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Controller
@Transactional
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @MessageMapping("/chat/rooms/{roomId}")    // /pub/chat/room/{roomId}/send 로 전송
    @SendTo("/sub/chat/rooms/{roomId}")
    public ChatMessageResponseDto sendMessage(
            @DestinationVariable("roomId") Long roomId,
            @Payload ChatMessageRequestDto requestDto
    ) {
        System.out.println("request: " + requestDto.userId());
        System.out.println("request: " + requestDto.content());

        ChatMessage chatMessage = ChatMessage.builder()
                .roomId(roomId)
                .userId(requestDto.userId())
                .content(requestDto.content())
                .build();
        chatMessageRepository.save(chatMessage);

        return ChatMessageResponseDto.builder()
                .id(chatMessage.getId())
                .userId(requestDto.userId())
                .nickname(requestDto.nickname())
                .content(chatMessage.getContent())
                .build();
    }
}
