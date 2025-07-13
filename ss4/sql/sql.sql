use ss4_java_web_service

INSERT INTO category(name) VALUES ('Science Fiction');
INSERT INTO category(name) VALUES ('Fantasy');
INSERT INTO category(name) VALUES ('Mystery');
INSERT INTO category(name) VALUES ('Non-fiction');
INSERT INTO category(name) VALUES ('Romance');


INSERT INTO book(name, author, publisher, category_id, year, price) VALUES
('Dune', 'Frank Herbert', 'Chilton Books', 1, 1965, 15.99),
('Neuromancer', 'William Gibson', 'Ace Books', 1, 1984, 12.50),
('Foundation', 'Isaac Asimov', 'Gnome Press', 1, 1951, 14.00),
('Ender\'s Game', 'Orson Scott Card', 'Tor Books', 1, 1985, 13.20),

('The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 2, 1937, 10.99),
('Harry Potter', 'J.K. Rowling', 'Bloomsbury', 2, 1997, 11.50),
('Mistborn', 'Brandon Sanderson', 'Tor Books', 2, 2006, 14.75),
('Eragon', 'Christopher Paolini', 'Knopf', 2, 2002, 9.90),

('Gone Girl', 'Gillian Flynn', 'Crown Publishing', 3, 2012, 10.80),
('The Girl with the Dragon Tattoo', 'Stieg Larsson', 'Norstedts Förlag', 3, 2005, 11.60),
('The Da Vinci Code', 'Dan Brown', 'Doubleday', 3, 2003, 13.40),
('Sherlock Holmes', 'Arthur Conan Doyle', 'Ward Lock & Co', 3, 1892, 8.99),

('Sapiens', 'Yuval Noah Harari', 'Harvill Secker', 4, 2011, 16.20),
('Educated', 'Tara Westover', 'Random House', 4, 2018, 12.30),
('Brief Answers to the Big Questions', 'Stephen Hawking', 'Bantam Books', 4, 2018, 14.99),
('The Selfish Gene', 'Richard Dawkins', 'Oxford University Press', 4, 1976, 13.99),

('Pride and Prejudice', 'Jane Austen', 'T. Egerton', 5, 1813, 9.99),
('Me Before You', 'Jojo Moyes', 'Michael Joseph', 5, 2012, 11.75),
('The Notebook', 'Nicholas Sparks', 'Warner Books', 5, 1996, 10.60),
('Twilight', 'Stephenie Meyer', 'Little, Brown and Company', 5, 2005, 12.40);

INSERT INTO flight (flight_number, departure, destination, price) VALUES
                                                                      ('VN101', 'Hanoi', 'Ho Chi Minh', 1500000),
                                                                      ('VN102', 'Hanoi', 'Da Nang', 1200000),
                                                                      ('VN103', 'Da Nang', 'Ho Chi Minh', 1300000),
                                                                      ('VN104', 'Hue', 'Hanoi', 1100000),
                                                                      ('VN105', 'Nha Trang', 'Hue', 1400000),
                                                                      ('VN106', 'Ho Chi Minh', 'Phu Quoc', 1600000),
                                                                      ('VN107', 'Da Nang', 'Nha Trang', 1250000),
                                                                      ('VN108', 'Hanoi', 'Phu Quoc', 1700000),
                                                                      ('VN109', 'Ho Chi Minh', 'Hanoi', 1500000),
                                                                      ('VN110', 'Hue', 'Da Nang', 1000000);
