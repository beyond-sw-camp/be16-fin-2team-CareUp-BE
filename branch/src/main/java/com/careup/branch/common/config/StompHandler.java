package com.careup.branch.common.config;


import com.careup.branch.common.dto.SessionInfo;
import com.careup.branch.common.repository.WebSocketSessionRegistry;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    @Value("${jwt.secretKeyAt}")
    private String secretKey;


    //connect, subscribe, disconnect 요청 시에 presend 메서드가 실행됨
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if(StompCommand.CONNECT == accessor.getCommand()){
            String bearerToken = accessor.getFirstNativeHeader("Authorization");
            String token = bearerToken.substring(7);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        if(StompCommand.SUBSCRIBE == accessor.getCommand()){
            String bearerToken = accessor.getFirstNativeHeader("Authorization");
            String token = bearerToken.substring(7);
            String destination = accessor.getDestination(); // ex: /topic/room1
            String roomId = destination.split("/")[2];

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            SessionInfo sessionInfo = SessionInfo.builder()
                    .email(claims.getSubject())
                    .roomId(roomId)
                    .build();
            WebSocketSessionRegistry.register(accessor.getSessionId(), sessionInfo);
        }

        if(StompCommand.DISCONNECT == accessor.getCommand()){
            String bearerToken = accessor.getFirstNativeHeader("Authorization");
        }
    return message;
    }
}
