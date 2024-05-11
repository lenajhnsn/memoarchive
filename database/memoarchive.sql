BEGIN TRANSACTION;
DROP TABLE IF EXISTS users, memory, tag, voicememo, memorycontribution, permission CASCADE;

CREATE TABLE Users  (
user_id serial PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(50) NOT NULL,
username VARCHAR(25) NOT NULL, --TODO: add unique
account_creation_date DATE NOT NULL);


-- Create Memory Table
CREATE TABLE Memory  (
memory_id serial PRIMARY KEY,
user_id int NOT NULL,
type VARCHAR(25) NOT NULL,
content TEXT NOT NULL,
description TEXT,
memory_date DATE NOT NULL,
creation_date DATE  NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);


-- Create Tag Table
CREATE TABLE Tag (
tag_id serial PRIMARY KEY,
memory_id INT NOT NULL,
user_id int NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);

-- Create VoiceMemo Table
CREATE TABLE VoiceMemo (
voice_memo_id serial PRIMARY KEY,
memory_id INT NOT NULL,
transcript_text TEXT NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE);


-- Create MemoryContribution Table
CREATE TABLE MemoryContribution (
contribution_id serial PRIMARY KEY,
memory_id INT NOT NULL,
contributor_id INT NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE,
FOREIGN KEY (contributor_id) REFERENCES Users(user_id) ON DELETE CASCADE);


-- Create Permission Table (who can access different memories)
CREATE TABLE Permission (
permission_id serial PRIMARY KEY,
owner_user_id INT NOT NULL,
contributor_user_id INT NOT NULL,
access_type VARCHAR(50) NOT NULL,
FOREIGN KEY (owner_user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (contributor_user_id) REFERENCES Users(user_id) ON DELETE CASCADE);

-- Authority table

-- Role table (admin,
COMMIT;
