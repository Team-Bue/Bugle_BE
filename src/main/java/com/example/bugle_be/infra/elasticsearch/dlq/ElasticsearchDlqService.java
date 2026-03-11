package com.example.bugle_be.infra.elasticsearch.dlq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticsearchDlqService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;


    public void enqueue(String key, DlqEntry.DomainType domainType, Object event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            DlqEntry entry = DlqEntry.of(domainType, payload);
            String json = objectMapper.writeValueAsString(entry);
            redisTemplate.opsForList().rightPush(key, json);
        } catch (JsonProcessingException ignored) {
        }
    }

    public void requeue(String key, DlqEntry entry) {
        try {
            DlqEntry incremented = entry.incrementRetryCount();
            String json = objectMapper.writeValueAsString(incremented);
            redisTemplate.opsForList().rightPush(key, json);
        } catch (JsonProcessingException ignored) {
        }
    }

    public DlqEntry dequeue(String key) {
        try {
            String json = redisTemplate.opsForList().leftPop(key);
            if (json == null) return null;
            return objectMapper.readValue(json, DlqEntry.class);
        } catch (JsonProcessingException ignored) {
            return null;
        }
    }

    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }
}


