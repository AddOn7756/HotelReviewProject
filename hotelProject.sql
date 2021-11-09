select * from all_tables;

DROP TABLE member;
DROP TABLE review;
DROP TABLE hotel;
DROP TABLE image;

/* 맴버 테이블 */
CREATE TABLE member(
	memnum int PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	id VARCHAR(50) NOT NULL,
	pw VARCHAR(50) NOT NULL,
	email VARCHAR(60) NOT NULL,
	birth VARCHAR(30) NOT NULL,
	image VARCHAR(100) DEFAULT 'defaultImg.png',
	hp VARCHAR(50) NOT NULL,
	gender VARCHAR(5) NOT NULL,
	addr VARCHAR(255) NOT NULL
);

/* 리뷰 테이블 */
CREATE TABLE review(
	renum int PRIMARY KEY,
	hotelnum int NOT NULL,
	memnum int NOT NULL,
	content VARCHAR(2000),
	rdate DATE DEFAULT sysdate,
	writer VARCHAR(50) NOT NULL,
	image VARCHAR(100) DEFAULT 'defaultImg.png',
	CONSTRAINT review_hotel_cons FOREIGN KEY (hotelnum) REFERENCES hotel(hotelnum) ON DELETE CASCADE,
	CONSTRAINT review_memnum_cons FOREIGN KEY (memnum) REFERENCES member(memnum) ON DELETE CASCADE
);

/* 호텔 테이블 */
CREATE TABLE hotel(
	hotelnum INT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	pnum VARCHAR(20) NOT NULL,
	addr VARCHAR(100) NOT NULL,
	expln LONG NOT NULL,
	rating int DEFAULT 0,
	recnt int DEFAULT 0,
	lttlng VARCHAR(50)
);

/* 이미지 테이블 */
CREATE TABLE image(
	imgnum INT PRIMARY KEY,
	hotelnum INT NOT NULL,
	imgid VARCHAR(100) NOT NULL,
	oname VARCHAR(100) NOT NULL,
	CONSTRAINT img_hotel_cons FOREIGN KEY (hotelnum) REFERENCES hotel(hotelnum) ON DELETE CASCADE
);

SELECT * FROM member;
SELECT * FROM review ORDER BY rdate DESC;
select * from image;
select * from hotel;

