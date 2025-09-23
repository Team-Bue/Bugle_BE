ALTER TABLE tbl_user
    MODIFY COLUMN profile_image_url VARCHAR(255) NOT NULL DEFAULT 'default_user.png';