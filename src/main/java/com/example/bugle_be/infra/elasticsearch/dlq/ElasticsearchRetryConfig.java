package com.example.bugle_be.infra.elasticsearch.dlq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class ElasticsearchRetryConfig {

    @Bean(name = "elasticsearchRetryTemplate")
    public RetryTemplate elasticsearchRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(DlqConstants.RETRY_INITIAL_INTERVAL);
        backOffPolicy.setMaxInterval(DlqConstants.RETRY_MAX_INTERVAL);
        backOffPolicy.setMultiplier(DlqConstants.RETRY_MULTIPLIER);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(DlqConstants.MAX_RETRY_ATTEMPTS);

        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}
