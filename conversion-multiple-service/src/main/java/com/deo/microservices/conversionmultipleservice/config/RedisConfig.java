package com.deo.microservices.conversionmultipleservice.config;

import com.deo.microservices.conversionmultipleservice.model.Currency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Currency> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Currency> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
//        template.setHashValueSerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        // Add some specific configuration here. Key serializers, etc.
        return template;
    }
}
