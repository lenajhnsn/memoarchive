BEGIN TRANSACTION;
DROP TABLE IF EXISTS users, memory, tag, voicememo, memorycontribution, permission CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id, seq_memory_id, seq_tag_id, seq_voice_memo_id, seq_permission_id, seq_contribution_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE Users  (
user_id int DEFAULT nextval('seq_user_id') PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(200) NOT NULL,
username VARCHAR(25) NOT NULL,
account_creation_date DATE NOT NULL);



-- Create Memory Table
CREATE SEQUENCE seq_memory_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE Memory  (
memory_id int DEFAULT nextval('seq_memory_id') PRIMARY KEY,
user_id int NOT NULL,
type VARCHAR(25) NOT NULL,
content TEXT NOT NULL,
description TEXT,
memory_date DATE NOT NULL,
creation_date DATE  NOT NULL,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);


-- Create Tag Table
CREATE SEQUENCE seq_tag_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE Tag (
tag_id int DEFAULT nextval ('seq_tag_id') PRIMARY KEY,
memory_id INT NOT NULL,
user_id int NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE,
FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE);

-- Create VoiceMemo Table
CREATE SEQUENCE seq_voice_memo_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE VoiceMemo (
voice_memo_id int DEFAULT nextval('seq_voice_memo_id') PRIMARY KEY,
memory_id INT NOT NULL,
transcript_text TEXT NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE);


-- Create MemoryContribution Table
CREATE SEQUENCE seq_contribution_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE MemoryContribution (
contribution_id int DEFAULT nextval('seq_contribution_id') PRIMARY KEY,
memory_id INT NOT NULL,
contributor_id INT NOT NULL,
FOREIGN KEY (memory_id) REFERENCES Memory(memory_id) ON DELETE CASCADE,
FOREIGN KEY (contributor_id) REFERENCES Users(user_id) ON DELETE CASCADE);


-- Create Permission Table
CREATE SEQUENCE seq_permission_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE Permission (
permission_id int DEFAULT nextval('seq_permission_id') PRIMARY KEY,
owner_user_id INT NOT NULL,
contributor_user_id INT NOT NULL,
access_type VARCHAR(50) NOT NULL,
FOREIGN KEY (owner_user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
FOREIGN KEY (contributor_user_id) REFERENCES Users(user_id) ON DELETE CASCADE);

COMMIT;