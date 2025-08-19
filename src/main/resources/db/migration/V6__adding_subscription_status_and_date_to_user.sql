ALTER TABLE users ADD COLUMN subscription_status VARCHAR(50);
ALTER TABLE users ADD COLUMN subscription_end_date DATE;

UPDATE users SET subscription_status = 'INACTIVE' WHERE subscription_status IS NULL;