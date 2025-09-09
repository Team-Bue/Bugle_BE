ALTER TABLE tbl_post
DROP FOREIGN KEY FK2xe5ubrtvdd68bg3uf44vcgrt;

ALTER TABLE tbl_post
    ADD CONSTRAINT FK2xe5ubrtvdd68bg3uf44vcgrt
        FOREIGN KEY (user_id)
            REFERENCES tbl_user (id)
            ON DELETE CASCADE;