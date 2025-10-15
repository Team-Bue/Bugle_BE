CREATE TABLE tbl_follow (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    follower_id BIGINT NOT NULL,
    following_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT follow_unique UNIQUE (follower_id, following_id),
    CONSTRAINT fk_follow_follower FOREIGN KEY (follower_id)
        REFERENCES tbl_user (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_follow_following FOREIGN KEY (following_id)
        REFERENCES tbl_user (id)
        ON DELETE CASCADE
);