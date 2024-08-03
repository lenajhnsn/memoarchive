BEGIN TRANSACTION;
DROP TABLE IF EXISTS users, memory, tag, voicememo, memorycontribution, permission CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id, seq_memory_id, seq_tag_id, seq_voice_memo_id, seq_permission_id, seq_contribution_id;


CREATE SEQUENCE seq_user_id --Starts sequencing after row 1000, the mock data--
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
account_creation_date DATE DEFAULT CURRENT_DATE NOT NULL);


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
creation_date DATE DEFAULT CURRENT_DATE NOT NULL,
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

insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (1, 'Lena', 'Johnson', 'test@test.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '04/29/2023', 'lenajhnsn');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (2, 'Creighton', 'Sauvain', 'csauvain1@bloomberg.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '09/16/2023', 'csauvain1');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (3, 'Fan', 'Sisey', 'fsisey2@hubpages.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '06/05/2024', 'fsisey2');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (4, 'Torry', 'Paddeley', 'tpaddeley3@arizona.edu', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '12/31/2023', 'tpaddeley3');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (5, 'Merna', 'Atkyns', 'matkyns4@bandcamp.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '05/02/2023', 'matkyns4');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (6, 'Diego', 'Dufaire', 'ddufaire5@bandcamp.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '06/13/2023', 'ddufaire5');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (7, 'Wainwright', 'Pinnijar', 'wpinnijar6@twitpic.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '07/02/2024', 'wpinnijar6');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (8, 'Ammamaria', 'De Caville', 'adecaville7@nba.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '06/02/2024', 'adecaville7');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (9, 'Aime', 'Metzing', 'ametzing8@nature.com', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '12/29/2023', 'ametzing8');
insert into Users (user_id, first_name, last_name, email, password, account_creation_date, username) values (10, 'Bianca', 'Cheer', 'bcheer9@pagesperso-orange.fr', '$2a$10$fHpf6f0HLWSgk.leMlxlPem9lBOm.5Xw9aGYDYOv/WqcTqfRXxPUy', '12/03/2023', 'bcheer9');


insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (1, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639668/memory1_p587fs.jpg', 'welcome to the world!', '10/08/2017', '05/27/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (2, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639673/memory2_ecckul.jpg', 'Time to celebrate her FIRST birthday!', '10/08/2018', '05/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (3, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639670/memory3_iqfctd.jpg', 'Baby sister joins the family!', '07/22/2020', '04/10/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (4, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639672/memory4_fn2jjt.jpg', 'First family photo.', '07/23/2020', '07/22/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (5, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639670/memory5_ulfdcw.jpg', 'And just like that, she is ONE!', '07/22/2021', '07/20/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (6, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722639669/memory6_qibvst.jpg', 'Happy third birthday!', '10/08/2021', '07/28/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (7, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719957/IMG_8211_p36bpw.jpg', 'Flower picking.', '05/20/2024', '05/21/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (8, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719955/IMG_9325_bikplw.jpg', 'Easter Egg Hunt', '04/12/2024', '05/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (9, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719955/IMG_7962_ybxgmd.jpg', 'It is a small world after all.', '04/07/1978', '06/13/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (10, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719954/IMG_7879_eihvln.jpg', 'Octopus encouter.', '05/26/2024', '06/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (11, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719951/IMG_7763_kyuvlv.jpg', null, '05/27/2024', '05/31/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (12, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719949/IMG_7764_cwvmio.jpg', null, '05/27/2024', '05/31/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (13, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719947/IMG_3362_b3kd2k.jpg', 'Feeding the animals', '04/19/2024', '04/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (14, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719946/IMG_3244_epaqpo.jpg', 'Baby goats were born!', '03/22/2024', '05/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (15, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719941/IMG_7034_mowfrt.jpg', null, '05/23/2024', '05/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (16, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719940/IMG_6911_er3tuz.jpg', 'She has always been a climber', '06/11/2024', '07/16/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (17, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719939/IMG_6724_lfvs6w.jpg', null, '02/28/2024', '05/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (18, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719969/IMG_7961_mza54s.jpg', 'Summer hike', '07/16/2024', '07/19/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (19, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719962/IMG_8350_fod7jv.jpg', null, '06/11/2024', '07/17/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (20, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719965/IMG_7664_z7p5ii.jpg',null, '07/09/2024', '07/11/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (21, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719961/IMG_8222_yjzr5b.jpg', 'Field of dandelions', '05/20/2024', '05/21/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (22, 1, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722719960/IMG_8228_mikjfs.jpg', 'A bouquet for me!', '05/20/2024', '05/21/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (23, 3, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/cld-sample-2.jpg', 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', '09/02/1960', '05/22/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (24, 6, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/cld-sample-4.jpg', null, '02/19/2015', '05/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (25, 7, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/cld-sample.jpg', 'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti.', '11/26/1990', '07/18/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (26, 9, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/cld-sample-3.jpg', null, '07/20/2017', '07/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (27, 8, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/samples/woman-on-a-football-field.jpg', 'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', '01/05/1985', '05/16/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (28, 8, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638010/samples/dessert-on-a-plate.jpg', 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo.', '04/15/1989', '05/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (29, 3, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638009/samples/man-on-a-street.jpg', 'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', '11/29/2019', '05/13/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (30, 7, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638009/samples/man-portrait.jpg', 'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', '02/18/1984', '07/09/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (31, 7, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638008/samples/outdoor-woman.jpg', null, '08/25/1998', '05/12/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (32, 4, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638008/samples/smile.jpg', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.', '09/06/2022', '06/30/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (33, 8, 'photo', 'https://res.cloudinary.com/dou5dqfxs/video/upload/v1722638003/samples/cld-sample-video.mp4', 'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', '02/04/2001', '06/03/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (34, 3, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638008/samples/balloons.jpg', 'Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '07/03/1998', '07/27/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (35, 5, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638006/samples/two-ladies.jpg', null, '04/13/1981', '05/31/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (36, 6, 'photo', 'https://res.cloudinary.com/dou5dqfxs/video/upload/v1722638003/samples/elephants.mp4', null, '02/23/2013', '04/18/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (37, 9, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638002/samples/landscapes/nature-mountains.jpg', 'Proin at turpis a pede posuere nonummy.', '11/24/1950', '04/18/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (38, 9, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/landscapes/beach-boat.jpg', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', '10/01/1982', '07/23/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (39, 2, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/animals/three-dogs.jpg', null, '09/16/2005', '06/12/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (40, 4, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/people/bicycle.jpg', null, '06/14/1956', '04/10/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (41, 3, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/landscapes/architecture-signs.jpg', 'Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '09/04/1976', '04/11/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (42, 8, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/people/jazz.jpg', 'Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.', '05/18/1972', '05/05/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (43, 4, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/people/boy-snow-hoodie.jpg', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', '08/15/1983', '07/12/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (44, 5, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638001/samples/bike.jpg', null, '10/04/1950', '04/13/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (45, 4, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/people/smiling-man.jpg', null, '06/17/2015', '05/17/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (46, 10, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/landscapes/girl-urban-view.jpg', 'In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', '07/17/2021', '06/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (47, 2, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/animals/reindeer.jpg', null, '12/30/1971', '07/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (48, 6, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/food/pot-mussels.jpg', null, '07/06/2009', '04/23/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (49, 5, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/people/kitchen-bar.jpg', 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', '07/02/1981', '07/01/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (50, 5, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722638000/samples/food/fish-vegetables.jpg', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', '10/24/1956', '04/07/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (51, 7, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722637998/sample.jpg', null, '04/24/1991', '05/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (52, 3, 'photo', 'https://res.cloudinary.com/dou5dqfxs/image/upload/v1722637999/samples/animals/cat.jpg', 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', '03/12/1992', '05/05/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (53, 6, 'photo', 'http://dummyimage.com/202x100.png/5fa2dd/ffffff', 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo.', '01/07/2021', '06/03/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (54, 4, 'photo', 'http://dummyimage.com/113x100.png/ff4444/ffffff', 'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', '02/27/1959', '04/20/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (55, 5, 'photo', 'http://dummyimage.com/187x100.png/ff4444/ffffff', null, '02/17/1979', '05/11/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (56, 3, 'photo', 'http://dummyimage.com/151x100.png/ff4444/ffffff', null, '04/01/2012', '07/06/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (57, 4, 'photo', 'http://dummyimage.com/129x100.png/ff4444/ffffff', 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque.', '03/19/1982', '06/26/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (58, 10, 'photo', 'http://dummyimage.com/112x100.png/dddddd/000000', null, '03/06/1991', '05/14/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (59, 10, 'photo', 'http://dummyimage.com/187x100.png/dddddd/000000', 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '01/21/2013', '04/16/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (60, 4, 'photo', 'http://dummyimage.com/247x100.png/cc0000/ffffff', null, '05/07/1983', '05/19/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (61, 3, 'photo', 'http://dummyimage.com/144x100.png/ff4444/ffffff', null, '09/25/1973', '05/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (62, 9, 'photo', 'http://dummyimage.com/157x100.png/cc0000/ffffff', null, '11/05/2000', '06/13/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (63, 6, 'photo', 'http://dummyimage.com/211x100.png/ff4444/ffffff', 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', '05/29/2007', '04/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (64, 10, 'photo', 'http://dummyimage.com/121x100.png/ff4444/ffffff', null, '09/08/1960', '04/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (65, 2, 'photo', 'http://dummyimage.com/192x100.png/5fa2dd/ffffff', null, '08/21/1997', '07/10/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (66, 9, 'photo', 'http://dummyimage.com/133x100.png/cc0000/ffffff', null, '12/01/1994', '04/24/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (67, 10, 'photo', 'http://dummyimage.com/170x100.png/cc0000/ffffff', 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.', '10/07/2020', '07/04/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (68, 9, 'photo', 'http://dummyimage.com/204x100.png/cc0000/ffffff', null, '07/23/2003', '07/14/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (69, 10, 'photo', 'http://dummyimage.com/200x100.png/cc0000/ffffff', 'Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl. Duis ac nibh.', '07/24/1992', '05/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (70, 8, 'photo', 'http://dummyimage.com/246x100.png/5fa2dd/ffffff', null, '05/19/2012', '07/22/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (71, 4, 'photo', 'http://dummyimage.com/169x100.png/ff4444/ffffff', null, '08/23/1953', '04/10/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (72, 4, 'photo', 'http://dummyimage.com/229x100.png/dddddd/000000', 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio.', '09/04/1969', '04/29/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (73, 5, 'photo', 'http://dummyimage.com/231x100.png/ff4444/ffffff', 'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue.', '12/17/1964', '06/20/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (74, 6, 'photo', 'http://dummyimage.com/232x100.png/cc0000/ffffff', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', '07/14/2001', '05/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (75, 8, 'photo', 'http://dummyimage.com/140x100.png/5fa2dd/ffffff', null, '07/02/1999', '07/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (76, 8, 'photo', 'http://dummyimage.com/200x100.png/cc0000/ffffff', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque.', '02/27/1987', '04/05/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (77, 6, 'photo', 'http://dummyimage.com/158x100.png/cc0000/ffffff', 'Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', '02/25/2007', '05/04/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (78, 8, 'photo', 'http://dummyimage.com/213x100.png/ff4444/ffffff', null, '07/20/1964', '04/24/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (79, 8, 'photo', 'http://dummyimage.com/164x100.png/ff4444/ffffff', null, '03/25/1970', '07/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (80, 10, 'photo', 'http://dummyimage.com/237x100.png/dddddd/000000', 'Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', '07/01/1971', '07/19/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (81, 8, 'photo', 'http://dummyimage.com/232x100.png/cc0000/ffffff', 'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat.', '05/13/1959', '06/29/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (82, 4, 'photo', 'http://dummyimage.com/196x100.png/dddddd/000000', null, '01/17/1979', '07/25/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (83, 9, 'photo', 'http://dummyimage.com/139x100.png/5fa2dd/ffffff', 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.', '06/15/1994', '04/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (84, 7, 'photo', 'http://dummyimage.com/143x100.png/ff4444/ffffff', null, '02/21/2005', '06/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (85, 9, 'photo', 'http://dummyimage.com/243x100.png/dddddd/000000', null, '07/15/2015', '07/05/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (86, 6, 'photo', 'http://dummyimage.com/201x100.png/5fa2dd/ffffff', null, '08/18/2022', '04/28/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (87, 6, 'photo', 'http://dummyimage.com/146x100.png/ff4444/ffffff', null, '09/23/1978', '07/29/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (88, 3, 'photo', 'http://dummyimage.com/134x100.png/cc0000/ffffff', null, '02/24/1979', '06/02/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (89, 5, 'photo', 'http://dummyimage.com/170x100.png/dddddd/000000', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat.', '07/20/2004', '05/03/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (90, 10, 'photo', 'http://dummyimage.com/205x100.png/ff4444/ffffff', 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', '02/18/1984', '07/13/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (91, 7, 'photo', 'http://dummyimage.com/182x100.png/ff4444/ffffff', null, '10/13/1973', '07/19/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (92, 6, 'photo', 'http://dummyimage.com/134x100.png/dddddd/000000', 'Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '05/06/1968', '05/05/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (93, 4, 'photo', 'http://dummyimage.com/109x100.png/5fa2dd/ffffff', null, '01/29/1998', '06/15/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (94, 6, 'photo', 'http://dummyimage.com/235x100.png/5fa2dd/ffffff', 'Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', '05/28/1950', '06/08/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (95, 9, 'photo', 'http://dummyimage.com/167x100.png/dddddd/000000', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', '10/04/1951', '06/03/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (96, 5, 'photo', 'http://dummyimage.com/130x100.png/5fa2dd/ffffff', 'Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo.', '01/17/1966', '04/19/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (97, 3, 'photo', 'http://dummyimage.com/224x100.png/cc0000/ffffff', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.', '04/18/1956', '05/10/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (98, 9, 'photo', 'http://dummyimage.com/212x100.png/cc0000/ffffff', null, '03/22/1992', '05/20/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (99, 10, 'photo', 'http://dummyimage.com/116x100.png/dddddd/000000', null, '01/21/2018', '06/11/2024');
insert into Memory (memory_id, user_id, type, content, description, memory_date, creation_date) values (100, 9, 'photo', 'http://dummyimage.com/102x100.png/dddddd/000000', null, '09/07/1984', '06/21/2024');


insert into Tag (tag_id, memory_id, user_id) values (1, 4, 2);
insert into Tag (tag_id, memory_id, user_id) values (2, 30, 9);
insert into Tag (tag_id, memory_id, user_id) values (3, 19, 10);
insert into Tag (tag_id, memory_id, user_id) values (4, 49, 4);
insert into Tag (tag_id, memory_id, user_id) values (5, 82, 2);
insert into Tag (tag_id, memory_id, user_id) values (6, 61, 5);
insert into Tag (tag_id, memory_id, user_id) values (7, 6, 7);
insert into Tag (tag_id, memory_id, user_id) values (8, 58, 7);
insert into Tag (tag_id, memory_id, user_id) values (9, 55, 7);
insert into Tag (tag_id, memory_id, user_id) values (10, 33, 2);
insert into Tag (tag_id, memory_id, user_id) values (11, 86, 10);
insert into Tag (tag_id, memory_id, user_id) values (12, 32, 2);
insert into Tag (tag_id, memory_id, user_id) values (13, 31, 8);
insert into Tag (tag_id, memory_id, user_id) values (14, 50, 9);
insert into Tag (tag_id, memory_id, user_id) values (15, 61, 1);
insert into Tag (tag_id, memory_id, user_id) values (16, 75, 9);
insert into Tag (tag_id, memory_id, user_id) values (17, 55, 9);
insert into Tag (tag_id, memory_id, user_id) values (18, 28, 6);
insert into Tag (tag_id, memory_id, user_id) values (19, 53, 4);
insert into Tag (tag_id, memory_id, user_id) values (20, 19, 7);
insert into Tag (tag_id, memory_id, user_id) values (21, 50, 2);
insert into Tag (tag_id, memory_id, user_id) values (22, 77, 7);
insert into Tag (tag_id, memory_id, user_id) values (23, 7, 1);
insert into Tag (tag_id, memory_id, user_id) values (24, 39, 8);
insert into Tag (tag_id, memory_id, user_id) values (25, 84, 4);
insert into Tag (tag_id, memory_id, user_id) values (26, 75, 2);
insert into Tag (tag_id, memory_id, user_id) values (27, 85, 10);
insert into Tag (tag_id, memory_id, user_id) values (28, 35, 1);
insert into Tag (tag_id, memory_id, user_id) values (29, 71, 10);
insert into Tag (tag_id, memory_id, user_id) values (30, 90, 9);
insert into Tag (tag_id, memory_id, user_id) values (31, 87, 3);
insert into Tag (tag_id, memory_id, user_id) values (32, 70, 8);
insert into Tag (tag_id, memory_id, user_id) values (33, 41, 6);
insert into Tag (tag_id, memory_id, user_id) values (34, 56, 3);
insert into Tag (tag_id, memory_id, user_id) values (35, 100, 2);
insert into Tag (tag_id, memory_id, user_id) values (36, 69, 2);
insert into Tag (tag_id, memory_id, user_id) values (37, 60, 5);
insert into Tag (tag_id, memory_id, user_id) values (38, 67, 8);
insert into Tag (tag_id, memory_id, user_id) values (39, 42, 4);
insert into Tag (tag_id, memory_id, user_id) values (40, 39, 3);
insert into Tag (tag_id, memory_id, user_id) values (41, 25, 1);
insert into Tag (tag_id, memory_id, user_id) values (42, 2, 3);
insert into Tag (tag_id, memory_id, user_id) values (43, 81, 6);
insert into Tag (tag_id, memory_id, user_id) values (44, 62, 9);
insert into Tag (tag_id, memory_id, user_id) values (45, 100, 6);
insert into Tag (tag_id, memory_id, user_id) values (46, 80, 9);
insert into Tag (tag_id, memory_id, user_id) values (47, 43, 9);
insert into Tag (tag_id, memory_id, user_id) values (48, 11, 1);
insert into Tag (tag_id, memory_id, user_id) values (49, 42, 10);
insert into Tag (tag_id, memory_id, user_id) values (50, 8, 2);
insert into Tag (tag_id, memory_id, user_id) values (51, 76, 9);
insert into Tag (tag_id, memory_id, user_id) values (52, 70, 2);
insert into Tag (tag_id, memory_id, user_id) values (53, 1, 6);
insert into Tag (tag_id, memory_id, user_id) values (54, 94, 3);
insert into Tag (tag_id, memory_id, user_id) values (55, 20, 4);
insert into Tag (tag_id, memory_id, user_id) values (56, 71, 3);
insert into Tag (tag_id, memory_id, user_id) values (57, 11, 5);
insert into Tag (tag_id, memory_id, user_id) values (58, 19, 7);
insert into Tag (tag_id, memory_id, user_id) values (59, 88, 3);
insert into Tag (tag_id, memory_id, user_id) values (60, 58, 10);
insert into Tag (tag_id, memory_id, user_id) values (61, 68, 2);
insert into Tag (tag_id, memory_id, user_id) values (62, 73, 10);
insert into Tag (tag_id, memory_id, user_id) values (63, 46, 5);
insert into Tag (tag_id, memory_id, user_id) values (64, 19, 1);
insert into Tag (tag_id, memory_id, user_id) values (65, 87, 10);
insert into Tag (tag_id, memory_id, user_id) values (66, 93, 10);
insert into Tag (tag_id, memory_id, user_id) values (67, 61, 5);
insert into Tag (tag_id, memory_id, user_id) values (68, 17, 9);
insert into Tag (tag_id, memory_id, user_id) values (69, 29, 2);
insert into Tag (tag_id, memory_id, user_id) values (70, 23, 4);
insert into Tag (tag_id, memory_id, user_id) values (71, 93, 8);
insert into Tag (tag_id, memory_id, user_id) values (72, 92, 4);
insert into Tag (tag_id, memory_id, user_id) values (73, 66, 6);
insert into Tag (tag_id, memory_id, user_id) values (74, 64, 4);
insert into Tag (tag_id, memory_id, user_id) values (75, 33, 2);
insert into Tag (tag_id, memory_id, user_id) values (76, 91, 1);
insert into Tag (tag_id, memory_id, user_id) values (77, 86, 5);
insert into Tag (tag_id, memory_id, user_id) values (78, 32, 1);
insert into Tag (tag_id, memory_id, user_id) values (79, 26, 1);
insert into Tag (tag_id, memory_id, user_id) values (80, 79, 2);
insert into Tag (tag_id, memory_id, user_id) values (81, 56, 6);
insert into Tag (tag_id, memory_id, user_id) values (82, 5, 5);
insert into Tag (tag_id, memory_id, user_id) values (83, 39, 1);
insert into Tag (tag_id, memory_id, user_id) values (84, 50, 4);
insert into Tag (tag_id, memory_id, user_id) values (85, 97, 6);
insert into Tag (tag_id, memory_id, user_id) values (86, 48, 10);
insert into Tag (tag_id, memory_id, user_id) values (87, 52, 4);
insert into Tag (tag_id, memory_id, user_id) values (88, 99, 8);
insert into Tag (tag_id, memory_id, user_id) values (89, 50, 9);
insert into Tag (tag_id, memory_id, user_id) values (90, 73, 6);
insert into Tag (tag_id, memory_id, user_id) values (91, 95, 8);
insert into Tag (tag_id, memory_id, user_id) values (92, 17, 4);
insert into Tag (tag_id, memory_id, user_id) values (93, 84, 9);
insert into Tag (tag_id, memory_id, user_id) values (94, 16, 5);
insert into Tag (tag_id, memory_id, user_id) values (95, 47, 7);
insert into Tag (tag_id, memory_id, user_id) values (96, 23, 10);
insert into Tag (tag_id, memory_id, user_id) values (97, 54, 4);
insert into Tag (tag_id, memory_id, user_id) values (98, 9, 8);
insert into Tag (tag_id, memory_id, user_id) values (99, 13, 9);
insert into Tag (tag_id, memory_id, user_id) values (100, 65, 1);

insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (1, 83, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (2, 2, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (3, 57, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (4, 50, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (5, 41, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (6, 70, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (7, 18, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (8, 89, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (9, 53, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (10, 72, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (11, 44, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (12, 99, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (13, 95, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (14, 13, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (15, 14, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (16, 54, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (17, 39, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (18, 79, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (19, 96, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (20, 35, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (21, 93, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (22, 94, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (23, 80, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (24, 96, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (25, 15, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (26, 71, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (27, 87, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (28, 59, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (29, 14, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (30, 31, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (31, 12, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (32, 36, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (33, 58, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (34, 43, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (35, 40, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (36, 75, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (37, 32, 3);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (38, 72, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (39, 35, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (40, 27, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (41, 4, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (42, 90, 3);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (43, 91, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (44, 5, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (45, 14, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (46, 46, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (47, 74, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (48, 96, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (49, 77, 3);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (50, 72, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (51, 92, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (52, 6, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (53, 66, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (54, 36, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (55, 6, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (56, 96, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (57, 38, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (58, 88, 3);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (59, 54, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (60, 70, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (61, 58, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (62, 89, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (63, 4, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (64, 73, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (65, 28, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (66, 55, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (67, 21, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (68, 32, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (69, 26, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (70, 7, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (71, 58, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (72, 23, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (73, 49, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (74, 12, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (75, 1, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (76, 99, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (77, 47, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (78, 57, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (79, 44, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (80, 22, 3);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (81, 96, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (82, 17, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (83, 30, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (84, 38, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (85, 22, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (86, 46, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (87, 7, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (88, 28, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (89, 1, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (90, 99, 8);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (91, 68, 10);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (92, 96, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (93, 95, 1);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (94, 39, 2);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (95, 54, 9);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (96, 68, 5);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (97, 35, 7);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (98, 52, 4);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (99, 89, 6);
insert into MemoryContribution (contribution_id, memory_id, contributor_id) values (100, 71, 1);

insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (1, 93, 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (2, 31, 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (3, 5, 'Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (4, 91, 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (5, 1, 'Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (6, 21, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (7, 22, 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (8, 56, 'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (9, 12, 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.');
insert into VoiceMemo (voice_memo_id, memory_id, transcript_text) values (10, 53, 'Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim.');

insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (1, 6, 10, 'view');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (2, 7, 9, 'grant permissions');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (3, 5, 5, 'share');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (4, 6, 8, 'read');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (5, 10, 2, 'grant permissions');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (6, 2, 9, 'admin');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (7, 1, 7, 'read');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (8, 3, 6, 'admin');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (9, 9, 6, 'add');
insert into Permission (permission_id, owner_user_id, contributor_user_id, access_type) values (10, 3, 1, 'admin');

COMMIT;