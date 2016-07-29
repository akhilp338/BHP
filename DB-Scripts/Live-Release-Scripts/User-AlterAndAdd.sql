ALTER TABLE belhopat_backoffice.User
MODIFY column `password` varbinary(255); 

ALTER TABLE belhopat_backoffice.User
ADD COLUMN salt varbinary(255),
ADD COLUMN forgot_password_status BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN forgot_password_url varchar(255);