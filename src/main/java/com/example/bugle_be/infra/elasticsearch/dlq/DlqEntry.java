package com.example.bugle_be.infra.elasticsearch.dlq;

public record DlqEntry(
    DomainType domainType,
    String payload,
    int retryCount
) {
    public enum DomainType {
        POST, USER
    }

    public static DlqEntry of(DomainType domainType, String payload) {
        return new DlqEntry(domainType, payload, 0);
    }

    public DlqEntry incrementRetryCount() {
        return new DlqEntry(domainType, payload, retryCount + 1);
    }

    public boolean isExhausted() {
        return retryCount >= DlqConstants.MAX_DLQ_RETRY_COUNT;
    }
}
