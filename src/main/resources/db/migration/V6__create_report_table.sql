CREATE TABLE tbl_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reported_id BIGINT NOT NULL,
    reason VARCHAR(100) NOT NULL,
    report_type VARCHAR(6) NOT NULL,
    report_status VARCHAR(8) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_report_user FOREIGN KEY (user_id) REFERENCES tbl_user(id)
        ON DELETE CASCADE,
    CONSTRAINT ux_report_unique UNIQUE (user_id, report_type, reported_id)
);