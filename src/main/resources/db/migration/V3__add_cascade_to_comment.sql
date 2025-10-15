ALTER TABLE tbl_comment
DROP FOREIGN KEY FK_comment_post;

ALTER TABLE tbl_comment
    ADD CONSTRAINT FK_comment_post
        FOREIGN KEY (post_id) REFERENCES tbl_post(id)
            ON DELETE CASCADE;

ALTER TABLE tbl_comment
DROP FOREIGN KEY FK_comment_user;

ALTER TABLE tbl_comment
    ADD CONSTRAINT FK_comment_user
        FOREIGN KEY (user_id) REFERENCES tbl_user(id)
            ON DELETE CASCADE;