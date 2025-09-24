package com.careup.branch.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Bean
    @Qualifier("ssePubSub")
    public RedisConnectionFactory sseFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);

        return new LettuceConnectionFactory(configuration);
    }
    @Bean
    @Qualifier("ssePubSub")
    public RedisTemplate<String, String> sseRedisTemplate(@Qualifier("ssePubSub") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

//    // redis 리스너 객체
//    @Bean
//    @Qualifier("ssePubSub")
//    public RedisMessageListenerContainer redisMessageListenerContainer(
//            @Qualifier("ssePubSub") RedisConnectionFactory redisConnectionFactory,
//            @Qualifier("sseMessageListenerAdapter") MessageListenerAdapter messageListenerAdapter
//    ) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//
//        container.setConnectionFactory(redisConnectionFactory);
//        container.addMessageListener(messageListenerAdapter, new PatternTopic("notification-channel"));
//
//        return container;
//    }
//
//    // redis의 채널에서 수신된 메시지를 처리하는 빈객체 (위에서 수신한걸 여기서 처리한다.)
//    @Bean
//    @Qualifier("sseMessageListenerAdapter")
//    //todo : 타입 바꿔 줄 것
//    public MessageListenerAdapter messageListenerAdapter(String sseAlarmService) {
//        return new MessageListenerAdapter(sseAlarmService, "onMessage");
//    }
}
