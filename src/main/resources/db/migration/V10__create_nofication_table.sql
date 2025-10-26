CREATE TABLE tbl_notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    device_token VARCHAR(255) NOT NULL,
    is_read TINYINT(1) DEFAULT 0 NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES tbl_user(id)
        ON DELETE CASCADE
);