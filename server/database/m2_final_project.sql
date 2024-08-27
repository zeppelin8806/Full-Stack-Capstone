-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users, shards, orders, characters, location, orders_characters CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	name varchar(50) NOT NULL,
	address varchar(100) NULL,
	city varchar(50) NULL,
	state_code char(2) NULL,
	zip varchar(5) NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table location (
	location_id INT PRIMARY KEY,
	name VARCHAR(10) NOT NULL,
	population INT NOT NULL
);

create table characters (
	character_id SERIAL PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	gender VARCHAR(60) NOT NULL,
	nationality VARCHAR(60) NOT NULL,
	location_id int REFERENCES location(location_id),
	image VARCHAR (100) UNIQUE,
	user_id int REFERENCES users(user_id) NULL
);

create table shards (
	shardblade_id SERIAL PRIMARY KEY,
	shardblade_name VARCHAR(20) UNIQUE,
	shardblade_type VARCHAR(30) NOT NULL,
	character_id int REFERENCES characters(character_id),
	user_id int REFERENCES users(user_id) NULL
);

create table orders (
	order_id INT PRIMARY KEY,
	order_name VARCHAR(20) UNIQUE,
	second_ideal VARCHAR (200) NOT NULL,
	third_ideal VARCHAR (200) NOT NULL,
	fourth_ideal VARCHAR (200) NOT NULL,
	fifth_ideal VARCHAR (200) NOT NULL,
	glyph VARCHAR (100) UNIQUE
);

CREATE TABLE orders_characters(
	order_id int REFERENCES orders(order_id),
	character_id int REFERENCES characters(character_id),
	ideal INT DEFAULT(1) NOT NULL,
	PRIMARY KEY (order_id, character_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO users (username, password_hash, role, name, address, city, state_code, zip) VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER',  'Jack O''Lantern', null, 'Cleveland', 'OH', '44123'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN', 'Jill O''Lantern', null, 'Beverly Hills', 'CA', '90210');

insert into location (location_id, name, population)
values (1, 'Thaylenah', 51378),
(2, 'Alethkar', 78104),
(3, 'Herdaz', 33787),
(4, 'Jah Keved', 86287),
(5, 'Tukar', 57314),
(6, 'Yezier', 78006),
(7, 'Aimia', 25079),
(8, 'Shinovar', 79684),
(9, 'Kharbranth', 94311);

insert into characters (name, gender, nationality, location_id, image)
values ('Kaladin Stormbless', 'Male', 'Alethkar', 2, 'https://uploads.coppermind.net/Kaladin_by_Randy_Vargas.jpg'),
('Dalinar Kholin', 'Male', 'Alethkar', 2, 'https://uploads.coppermind.net/A_Man%27s_Worth_by_Randy_Vargas.jpg'),
('Adolin Kholin', 'Male', 'Alethkar', 2, 'https://uploads.coppermind.net/Duelist_by_Randy_Vargas.jpg'),
('Renarin Kholin', 'Male', 'Alethkar', 2, 'https://uploads.coppermind.net/Truthwatcher_by_Randy_Vargas.jpg'),
('Jasnah Kholin', 'Female', 'Alethkar', 2, 'https://uploads.coppermind.net/Elsecaller_by_Randy_Vargas.jpg'),
('Lift', 'Female', 'Azir', 4, 'https://uploads.coppermind.net/Edgedancer_by_Randy_Vargas.jpg'),
('Szeth', 'Male', 'Shin', 9, 'https://uploads.coppermind.net/Szeth.jpg'),
('Shallan Davar', 'Female', 'Veden', 9, 'https://uploads.coppermind.net/thumb/Shallan-antti.jpg/800px-Shallan-antti.jpg');

insert into shards (shardblade_name, shardblade_type, character_id)
values ('Thunderclash', 'Dead Shardblade', 1),
('Starshard', 'Shardplate', 1),
('Ironfang', 'Honorblade', 3),
('Shadowstrike', 'Dead Shardblade', 4),
('Whisperblade', 'Dead Shardblade', 5),
('Flamespike', 'Living Shardblade', 5),
('Oathbringer', 'Shardplate', 6),
('Deepcutter', 'Honorblade', 7),
('Mercysong', 'Living Shardblade', 8),
('Frostcleaver', 'Shardplate',2 ),
('Dawnslayer', 'Honorblade', 2);


insert into orders (order_id, order_name, second_ideal, third_ideal, fourth_ideal, fifth_ideal, glyph)
values (1, 'Bondsmiths', 'I will unite instead of divide. I will bring men together.', 'I will take responsibility for what I have done. If I must fall, I will rise each time a better man.', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Ishi_glyph.svg'),
(2, 'Dustbringers', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Dustbringers_glyph.svg'),
(3, 'Truthwatchers', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Truthwatchers_glyph.svg'),
(4, 'Windrunners', 'I will protect those who cannot protect themselves.', 'I will protect even those I hate, so long as it is right.', 'I accept that there will be those I cannot protect.', 'UNKNOWN', 'https://uploads.coppermind.net/Jeseh_glyph.svg'),
(5, 'Stonewards', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Stonewards_glyph.svg'),
(6, 'Edgedancers', 'I will remember those who have been forgotten.', 'I will listen to those who have been ignored.', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Edgedancers_glyph.svg'),
(7, 'Willshapers', 'I will seek freedom for those in bondage.', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Willshapers_glyph.svg'),
(8, 'Skybreakers', 'I will put the law before all else.', '"I swear to follow the will of Dalinar Kholin. This is my oath." -Spoken by Szeth', '"I will cleanse the Shin of their false leaders, so long as Dalinar Kholin agrees." -Spoken by Szeth', 'UNKNOWN', 'https://uploads.coppermind.net/Skybreakers_glyph.svg'),
(9, 'Elsecallers', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'UNKNOWN', 'https://uploads.coppermind.net/Beteb_glyph.svg'),
(10, 'Lightweavers', '"SPEAK TRUTHS"', '"SPEAK TRUTHS"', '"SPEAK TRUTHS"', '"SPEAK TRUTHS"', 'https://uploads.coppermind.net/Shash_glyph.svg');


insert into orders_characters(order_id, character_id, ideal)
values (1, 2, 2),
(3, 3, 3),
(2, 4, 2),
(4, 1, 4),
(3, 5, 4),
(5, 6, 1),
(4, 7, 1),
(6, 8, 2);


COMMIT TRANSACTION;
