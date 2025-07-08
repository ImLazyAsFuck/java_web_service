use ss2_java_web_service;

INSERT INTO movie (id, title, genre, duration) VALUES
                                                   (1, 'Inception', 'Sci-Fi', 148),
                                                   (2, 'Spirited Away', 'Animation', 125),
                                                   (3, 'The Dark Knight', 'Action', 152);
INSERT INTO theater (id, name, address) VALUES
                                            (1, 'Galaxy Cinema', '123 Nguyen Trai, Q1, HCM'),
                                            (2, 'CGV Vincom', '70 Le Thanh Ton, Q1, HCM');

INSERT INTO screen_room (id, name, capacity, theater_id) VALUES
                                                             (1, 'Room A', 100, 1),
                                                             (2, 'Room B', 80, 1),
                                                             (3, 'Room C', 120, 2);

INSERT INTO seat (id, seat_number, screen_room_id) VALUES
(1, 'A1', 1), (2, 'A2', 1), (3, 'A3', 1), (4, 'A4', 1), (5, 'A5', 1),
(6, 'B1', 2), (7, 'B2', 2), (8, 'B3', 2), (9, 'B4', 2), (10, 'B5', 2),
(11, 'C1', 3), (12, 'C2', 3), (13, 'C3', 3), (14, 'C4', 3), (15, 'C5', 3);

INSERT INTO showtime (id, movie_id, screen_room_id, start_time, end_time, number_seat_empty) VALUES
                                                                                                 (1, 1, 1, '2025-07-09 14:00:00', '2025-07-09 16:28:00', 95),
                                                                                                 (2, 2, 2, '2025-07-09 17:00:00', '2025-07-09 19:05:00', 77),
                                                                                                 (3, 3, 3, '2025-07-09 20:00:00', '2025-07-09 22:32:00', 118);
