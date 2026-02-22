ALTER TABLE tbl_user
    CHANGE profile_image_url profile_image_object_key VARCHAR(255)
    NOT NULL DEFAULT 'default_user.png';