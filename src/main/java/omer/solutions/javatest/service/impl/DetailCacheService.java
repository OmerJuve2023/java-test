package omer.solutions.javatest.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class DetailCacheService {

    private static final Cache<UUID, String> descriptionCache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public static String getDescriptionFromCache(UUID id) {
        return descriptionCache.getIfPresent(id);
    }

    public static void putDescriptionInCache(UUID id, String description) {
        descriptionCache.put(id, description);
    }
}
