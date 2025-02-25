-- Media Inserts with new fields (is_rush and weight)
INSERT INTO Media (id, type, category, price, quantity, title, value, imageUrl, is_rush, weight)
VALUES (38, 'book', 'story', 32, 12, 'book2', 29, 'images/book/book2.jpg', FALSE, 0.5),
       (39, 'book', 'adventure', 21, 2, 'book9', 20, 'images/book/book9.jpg', TRUE, 0.4),
       (40, 'book', 'adventure', 73, 11, 'book10', 69, 'images/book/book10.jpg', FALSE, 0.6),
       (41, 'book', 'story', 66, 2, 'book6', 62, 'images/book/book6.jpg', TRUE, 0.3),
       (42, 'cd', 'pop', 24, 6, 'cd7', 20, 'images/cd/cd7.jpg', FALSE, 0.2),
       (43, 'book', 'story', 50, 7, 'book12', 44, 'images/book/book12.jpg', FALSE, 0.5),
       (44, 'book', 'story', 57, 10, 'book4', 53, 'images/book/book4.jpg', TRUE, 0.4),
       (45, 'cd', 'pop', 66, 8, 'cd3', 60, 'images/cd/cd3.jpg', FALSE, 0.2),
       (46, 'book', 'bussiness', 79, 17, 'book1', 72, 'images/book/book1.jpg', TRUE, 0.7),
       (47, 'dvd', 'cartoon', 82, 1, 'dvd12', 78, 'images/dvd/dvd12.jpg', FALSE, 0.1),
       (48, 'book', 'science', 25, 10, 'book3', 22, 'images/book/book3.jpg', FALSE, 0.5),
       (49, 'dvd', 'science fiction', 75, 3, 'dvd10', 74, 'images/dvd/dvd10.jpg', TRUE, 0.1),
       (50, 'book', 'bussiness', 26, 4, 'book11', 19, 'images/book/book11.jpg', FALSE, 0.4),
       (51, 'dvd', 'action', 61, 18, 'dvd11', 52, 'images/dvd/dvd11.jpg', FALSE, 0.1),
       (52, 'cd', 'rock', 40, 4, 'cd4', 35, 'images/cd/cd4.jpg', TRUE, 0.2),
       (53, 'dvd', 'action', 70, 16, 'dvd9', 60, 'images/dvd/dvd9.jpg', FALSE, 0.1),
       (54, 'dvd', 'romance', 47, 19, 'dvd2', 39, 'images/dvd/dvd2.jpg', FALSE, 0.1),
       (55, 'cd', 'pop', 74, 6, 'cd2', 71, 'images/cd/cd2.jpg', TRUE, 0.2),
       (56, 'cd', 'rock', 70, 20, 'cd1', 60, 'images/cd/cd1.jpg', FALSE, 0.2),
       (57, 'book', 'adventure', 38, 2, 'book8', 36, 'images/book/book8.jpg', FALSE, 0.5),
       (58, 'dvd', 'cartoon', 55, 13, 'dvd3', 51, 'images/dvd/dvd3.jpg', TRUE, 0.1),
       (59, 'dvd', 'action', 28, 1, 'dvd6', 26, 'images/dvd/dvd6.jpg', FALSE, 0.1),
       (60, 'dvd', 'romance', 38, 17, 'dvd4', 33, 'images/dvd/dvd4.jpg', FALSE, 0.1),
       (61, 'cd', 'pop', 42, 15, 'cd12', 38, 'images/cd/cd12.jpg', TRUE, 0.2),
       (62, 'book', 'bussiness', 34, 15, 'book5', 29, 'images/book/book5.jpg', FALSE, 0.5),
       (63, 'cd', 'ballad', 99, 4, 'cd5', 92, 'images/cd/cd5.jpg', FALSE, 0.2),
       (64, 'cd', 'pop', 38, 10, 'cd8', 32, 'images/cd/cd8.jpg', TRUE, 0.2),
       (65, 'cd', 'classic', 37, 10, 'cd6', 31, 'images/cd/cd6.jpg', FALSE, 0.2),
       (66, 'book', 'bussiness', 93, 15, 'book7', 88, 'images/book/book7.jpg', TRUE, 0.6),
       (67, 'cd', 'classic', 25, 5, 'cd9', 23, 'images/cd/cd9.jpg', FALSE, 0.2),
       (68, 'dvd', 'romance', 71, 4, 'dvd5', 64, 'images/dvd/dvd5.jpg', TRUE, 0.1),
       (69, 'cd', 'pop', 97, 17, 'cd10', 89, 'images/cd/cd10.jpg', FALSE, 0.2),
       (70, 'dvd', 'romance', 47, 19, 'dvd8', 37, 'images/dvd/dvd8.jpg', FALSE, 0.1),
       (71, 'dvd', 'science fiction', 95, 11, 'dvd1', 92, 'images/dvd/dvd1.jpg', TRUE, 0.1),
       (72, 'dvd', 'action', 23, 9, 'dvd7', 16, 'images/dvd/dvd7.jpg', FALSE, 0.1),
       (73, 'cd', 'classic', 28, 3, 'cd11', 20, 'images/cd/cd11.jpg', FALSE, 0.2);


-- CD Inserts
INSERT INTO CD (id, artist, recordLabel, musicType, releasedDate)
VALUES (42, 'Artist Seven', 'Record Label A', 'pop', '2023-01-07'),
       (45, 'Artist Three', 'Record Label B', 'pop', '2023-03-15'),
       (52, 'Artist Four', 'Record Label C', 'rock', '2023-04-20'),
       (55, 'Artist Two', 'Record Label D', 'pop', '2023-02-10'),
       (56, 'Artist One', 'Record Label E', 'rock', '2023-01-05'),
       (61, 'Artist Twelve', 'Record Label F', 'pop', '2023-12-01'),
       (63, 'Artist Five', 'Record Label G', 'ballad', '2023-05-18'),
       (64, 'Artist Eight', 'Record Label H', 'pop', '2023-08-22'),
       (65, 'Artist Six', 'Record Label I', 'classic', '2023-06-30'),
       (67, 'Artist Nine', 'Record Label J', 'classic', '2023-09-14'),
       (69, 'Artist Ten', 'Record Label K', 'pop', '2023-10-25'),
       (73, 'Artist Eleven', 'Record Label L', 'classic', '2023-11-08');

-- Book Inserts
INSERT INTO Book (id, author, coverType, publisher, publishDate, numOfPages, language, bookCategory)
VALUES (38, 'Author Two', 'Hardcover', 'Publisher A', '2023-02-15', 300, 'English', 'story'),
       (39, 'Author Nine', 'Paperback', 'Publisher B', '2023-09-20', 250, 'English', 'adventure'),
       (40, 'Author Ten', 'Hardcover', 'Publisher C', '2023-10-05', 400, 'English', 'adventure'),
       (41, 'Author Six', 'Paperback', 'Publisher D', '2023-06-12', 350, 'English', 'story'),
       (43, 'Author Twelve', 'Hardcover', 'Publisher E', '2023-12-08', 280, 'English', 'story'),
       (44, 'Author Four', 'Paperback', 'Publisher F', '2023-04-25', 320, 'English', 'story'),
       (46, 'Author One', 'Hardcover', 'Publisher G', '2023-01-30', 450, 'English', 'bussiness'),
       (48, 'Author Three', 'Paperback', 'Publisher H', '2023-03-18', 200, 'English', 'science'),
       (50, 'Author Eleven', 'Paperback', 'Publisher I', '2023-11-15', 180, 'English', 'bussiness'),
       (57, 'Author Eight', 'Hardcover', 'Publisher J', '2023-08-10', 275, 'English', 'adventure'),
       (62, 'Author Five', 'Paperback', 'Publisher K', '2023-05-22', 220, 'English', 'bussiness'),
       (66, 'Author Seven', 'Hardcover', 'Publisher L', '2023-07-14', 500, 'English', 'bussiness');

-- DVD Inserts
INSERT INTO DVD (id, discType, director, runtime, studio, subtitle, releasedDate, filmType)
VALUES (47, 'Blu-ray', 'Director Twelve', 120, 'Studio A', 'English, French', '2023-12-10', 'cartoon'),
       (49, 'Blu-ray', 'Director Ten', 145, 'Studio B', 'English, Spanish', '2023-10-15', 'science fiction'),
       (51, 'DVD', 'Director Eleven', 135, 'Studio C', 'English, German', '2023-11-20', 'action'),
       (53, 'Blu-ray', 'Director Nine', 130, 'Studio D', 'English, Japanese', '2023-09-05', 'action'),
       (54, 'DVD', 'Director Two', 110, 'Studio E', 'English, Chinese', '2023-02-25', 'romance'),
       (58, 'Blu-ray', 'Director Three', 95, 'Studio F', 'English, Korean', '2023-03-30', 'cartoon'),
       (59, 'DVD', 'Director Six', 105, 'Studio G', 'English, Italian', '2023-06-08', 'action'),
       (60, 'Blu-ray', 'Director Four', 115, 'Studio H', 'English, Russian', '2023-04-12', 'romance'),
       (68, 'DVD', 'Director Five', 125, 'Studio I', 'English, Portuguese', '2023-05-28', 'romance'),
       (70, 'Blu-ray', 'Director Eight', 140, 'Studio J', 'English, Arabic', '2023-08-15', 'romance'),
       (71, 'Blu-ray', 'Director One', 150, 'Studio K', 'English, Hindi', '2023-01-20', 'science fiction'),
       (72, 'DVD', 'Director Seven', 100, 'Studio L', 'English, Thai', '2023-07-03', 'action');

UPDATE Media
SET price = price * 1000
WHERE id BETWEEN 38 AND 73;