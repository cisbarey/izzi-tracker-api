package com.izzi.iptracker.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.stereotype.Component;

@Component
public class MongoIndexCreator {

    private final MongoTemplate mongoTemplate;
    private final MongoMappingContext mongoMappingContext;

    public MongoIndexCreator(MongoTemplate mongoTemplate, MongoMappingContext mongoMappingContext) {
        this.mongoTemplate = mongoTemplate;
        this.mongoMappingContext = mongoMappingContext;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initIndicesAfterStartup() {
        var resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
        mongoMappingContext.getPersistentEntities().forEach(persistentEntity -> {
            var indexOps = mongoTemplate.indexOps(persistentEntity.getType());
            resolver.resolveIndexFor(persistentEntity.getType()).forEach(indexOps::ensureIndex);
        });
    }
}
