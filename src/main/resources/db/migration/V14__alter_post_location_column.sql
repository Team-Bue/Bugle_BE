ALTER TABLE tbl_post
    ADD COLUMN location VARCHAR(150) NULL;

ALTER TABLE tbl_post
    DROP COLUMN country,
    DROP COLUMN region;