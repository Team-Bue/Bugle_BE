ALTER TABLE tbl_post
DROP FOREIGN KEY FK_post_user;

ALTER TABLE tbl_post
    ADD CONSTRAINT FK_post_user
        FOREIGN KEY (user_id)
            REFERENCES tbl_user (id)
            ON DELETE CASCADE;