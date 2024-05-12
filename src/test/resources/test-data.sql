START TRANSACTION

CREATE TABLE Users  (
user_id serial PRIMARY KEY,
first_name VARCHAR(25) NOT NULL,
last_name VARCHAR(25) NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(25) NOT NULL,
account_creation_date DATE NOT NULL);


-- Create Memory Table
CREATE TABLE Memory  (
id serial PRIMARY KEY,
user_id int NOT NULL,
type VARCHAR(25) NOT NULL,
content TEXT NOT NULL,
description TEXT,
memory_date DATE NOT NULL,
creation_date DATE NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);


-- Create Tag Table
CREATE TABLE Tag (
tag_id serial PRIMARY KEY,
memory_id INT NOT NULL,
user_id int NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);

-- INSERT TEST DATA HERE, based on test plan

-- Data for Users Table
INSERT INTO Users (first_name, last_name, email, password, account_creation_date) VALUES ('Alice', 'Smith', 'alice.smith@example.com', 'password123', '2022-01-01');
INSERT INTO Users (first_name, last_name, email, password, account_creation_date) VALUES ('Bob', 'Johnson', 'bob.johnson@example.com', 'password123', '2022-01-02');
INSERT INTO Users (first_name, last_name, email, password, account_creation_date) VALUES ('Carol', 'Williams', 'carol.williams@example.com', 'password123', '2022-01-03');

-- This entry will test the unique constraint for email
INSERT INTO Users (first_name, last_name, email, password, account_creation_date) VALUES ('Duplicate', 'Email', 'alice.smith@example.com', 'password123', '2022-01-04');

-- Data for Memory Table
INSERT INTO Memory (user_id, type, content, description, memory_date, creation_date) VALUES (1, 'Holiday', 'We went to Hawaii.', 'It was a great trip!', '2021-12-25', '2022-01-01');
INSERT INTO Memory (user_id, type, content, description, memory_date, creation_date) VALUES (2, 'Anniversary', 'Celebrated 10 years.', 'Dinner at the plaza.', '2022-02-14', '2022-02-15');

-- This will test the non-null constraint on user_id
INSERT INTO Memory (user_id, type, content, description, memory_date, creation_date) VALUES (NULL, 'Invalid', 'This should fail.', 'No user id', '2022-01-01', '2022-01-01');

-- Inserts for Tag Table
INSERT INTO Tag (memory_id, user_id) VALUES (1, 1);
INSERT INTO Tag (memory_id, user_id) VALUES (2, 2);

-- These will test foreign key constraints
INSERT INTO Tag (memory_id, user_id) VALUES (999, 1); -- Non-existent memory_id
INSERT INTO Tag (memory_id, user_id) VALUES (1, 999); -- Non-existent user_id

COMMIT;