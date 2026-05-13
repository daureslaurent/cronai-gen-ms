package lda.cronai.gen.application.api.rest.agent.config;

import com.mongodb.client.MongoClient;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.nosql.mongo.MongoDBStorageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunrConfig {

    @Value("${spring.mongodb.database}")
    private String database;

    @Bean
    public StorageProvider storageProvider(MongoClient mongoClient, JobMapper jobMapper) {
        final MongoDBStorageProvider storageProvider = new MongoDBStorageProvider(mongoClient, database);
        storageProvider.setJobMapper(jobMapper);

        return storageProvider;
    }

}
