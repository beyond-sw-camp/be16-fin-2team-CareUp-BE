package com.careup.branch.common.config;


import com.careup.branch.common.dto.SessionInfo;
import com.careup.branch.common.repository.WebSocketSessionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class StompEventListener {
    private final Set<String> sessions = ConcurrentHashMap.newKeySet();

    @EventListener
    public void connectHandler(SessionConnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        sessions.add(accessor.getSessionId());
        log.info("connectId : " + accessor.getSessionId());
        log.info(String.valueOf(sessions.size()));
    }

    @EventListener
    public void subscribeHandle(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        SessionInfo sessionInfo = WebSocketSessionRegistry.get(sessionId);
        String roomId = sessionInfo.getRoomId();

        log.info("Session Subscribe : " + sessionId + "roomId : " + roomId);
    }

    @EventListener
    public void disConnectHandler(SessionDisconnectEvent event){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        SessionInfo sessionInfo = WebSocketSessionRegistry.get(sessionId);
        String email = sessionInfo.getEmail();
        String roomId = sessionInfo.getRoomId();

        log.info("Session DisConnected : " + sessionId + "roomId : " + roomId);
        WebSocketSessionRegistry.unregister(accessor.getSessionId());
        sessions.remove(accessor.getSessionId());
    }
}
