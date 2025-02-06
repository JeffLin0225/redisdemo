package com.example.redisdemo.Service;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveRedisService {

    private final ReactiveRedisTemplate<String, String> template;

    public ReactiveRedisService(ReactiveRedisTemplate<String, String> template) {
        this.template = template;
    }

    public Mono<Boolean> setData(String key, String value) {
        return template.opsForValue().set(key, value);
    }

    public Mono<String> getData(String key) {
        return template.opsForValue().get(key);
    }

    public Mono<Boolean> deleteData(String key) {
        return template.opsForValue().delete(key);
    }
}


