CREATE TABLE tbl_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(60),
    account_id VARCHAR(20) NOT NULL UNIQUE,
    user_name VARCHAR(20),
    profile_image_url VARCHAR(255) NOT NULL DEFAULT 'default_user.png',
    PRIMARY KEY (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_post (
    id BIGINT NOT NULL AUTO_INCREMENT,
    content VARCHAR(300) NOT NULL,
    country VARCHAR(20),
    region VARCHAR(20),
    file_url VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_post_user FOREIGN KEY (user_id)
        REFERENCES tbl_user (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE tbl_comment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    content VARCHAR(300) NOT NULL,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_comment_post FOREIGN KEY (post_id) REFERENCES tbl_post (id),
    CONSTRAINT FK_comment_user FOREIGN KEY (user_id) REFERENCES tbl_user (id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;