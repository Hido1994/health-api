INSERT INTO TAG(id, name)
VALUES (1, 'Quotes');

INSERT INTO TAG(id, name, parent_tag_id)
VALUES (100, 'Motivational', 1),
       (101, 'Movies & Series', 1),
       (102, 'Sports', 1);

INSERT INTO TAG(id, name, parent_tag_id)
VALUES (10000, 'Inspirational', 100),
       (10001, 'Funny', 100),
       (10002, 'Love', 100),
       (10100, 'Anime', 101);

INSERT INTO TAG(id, name, parent_tag_id)
VALUES (1010000, 'One Piece', 10100),
       (1010001, 'Death Note', 10100),
       (1010002, 'Naruto', 10100);

INSERT INTO QUOTE(id, text, origin)
VALUES (1, 'Life isn''t about getting and having, it''s about giving and being.', 'Kevin Kruse'),
       (2, 'Whatever the mind of man can conceive and believe, it can achieve.', 'Napoleon Hill'),
       (3, 'Strive not to be a success, but rather to be of value.', 'Albert Einstein'),
       (4, 'Two roads diverged in a wood, and I—I took the one less traveled by, And that has made all the difference.',
        'Robert Frost'),
       (5, 'I attribute my success to this: I never gave or took any excuse.', 'Florence Nightingale'),
       (6, 'You miss 100% of the shots you don''t take.', 'Wayne Gretzky'),
       (7,
        'I''ve missed more than 9000 shots in my career. I''ve lost almost 300 games. 26 times I''ve been trusted to take the game winning shot and missed. I''ve failed over and over and over again in my life. And that is why I succeed.',
        'Michael Jordan'),
       (8, 'The most difficult thing is the decision to act, the rest is merely tenacity.', 'Amelia Earhart'),
       (9, 'Every strike brings me closer to the next home run.', 'Babe Ruth'),
       (10, 'Definiteness of purpose is the starting point of all achievement.', 'W. Clement Stone');

INSERT INTO QUOTE_TAG(quote_id, tag_id)
VALUES (1, 10000),
       (2, 10000),
       (3, 10000),
       (4, 10000),
       (5, 10001),
       (6, 10001),
       (7, 10001),
       (8, 10001),
       (9, 10002),
       (10, 10002);


