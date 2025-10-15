CREATE TABLE tbl_like (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    CONSTRAINT like_unique UNIQUE (user_id, post_id),
    CONSTRAINT fk_like_user FOREIGN KEY (user_id)
        REFERENCES tbl_user (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_like_post FOREIGN KEY (post_id)
        REFERENCES tbl_post (id)
        ON DELETE CASCADE
);