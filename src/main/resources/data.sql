-- ===========================
-- 🎬 MOVIES
-- ===========================
INSERT INTO movie (title, description, genre, duration, rating, release_date, poster_url, backdrop_url, director, actors, status)
VALUES
    ('Quantum Nexus',
     'A mind-bending sci-fi thriller that explores the boundaries of reality and consciousness. When a physicist discovers a way to access parallel universes, she must race against time to prevent a catastrophic collision of worlds.',
     'Sci-Fi, Thriller, Action',
     142, 'PG-13',
     '2025-10-15',
     'https://image.tmdb.org/t/p/w500/quantum_nexus_poster.jpg',
     'https://image.tmdb.org/t/p/w1280/quantum_nexus_backdrop.jpg',
     'Sarah Chen',
     'Emma Stone, Oscar Isaac, Tilda Swinton',
     'ACTIVE'),

    ('The Last Symphony',
     'An emotional journey through music and memory. A renowned conductor must confront her past when she returns to her hometown to lead one final performance.',
     'Drama, Music',
     128, 'PG',
     '2025-10-28',
     'https://image.tmdb.org/t/p/w500/last_symphony_poster.jpg',
     'https://image.tmdb.org/t/p/w1280/last_symphony_backdrop.jpg',
     'Michael Anderson',
     'Cate Blanchett, Mahershala Ali, Saoirse Ronan',
     'ACTIVE'),

    ('Shadow Protocol',
     'A high-stakes espionage thriller where nothing is as it seems. An elite spy must uncover a mole within her organization before a global crisis unfolds.',
     'Action, Thriller, Spy',
     135, 'R',
     '2025-12-08',
     'https://image.tmdb.org/t/p/w500/shadow_protocol_poster.jpg',
     'https://image.tmdb.org/t/p/w1280/shadow_protocol_backdrop.jpg',
     'David Leitch',
     'Charlize Theron, Idris Elba, Rebecca Ferguson',
     'INACTIVE'),

    ( 'Cosmic Dreams',
     'An animated adventure that takes audiences on a journey through the cosmos. A young astronaut discovers a hidden galaxy where dreams become reality.',
     'Animation, Adventure, Family',
     98, 'G',
     '2025-11-01',
     'https://image.tmdb.org/t/p/w500/cosmic_dreams_poster.jpg',
     'https://image.tmdb.org/t/p/w1280/cosmic_dreams_backdrop.jpg',
     'Jennifer Lee',
     'Tom Hanks, Zendaya, John Boyega',
     'ACTIVE'),

    ('Eclipse Point',
    'A team of astronauts faces a cosmic mystery when the Sun suddenly begins to dim. Their mission becomes a desperate race to reignite the dying star before Earth freezes over.',
    'Sci-Fi, Adventure, Drama',
    130,
    'PG-13',
    '2025-11-10',
    'https://cineasy-images.blob.core.windows.net/cineasy-images/eclipse_point_poster.jpg',
    'https://cineasy-images.blob.core.windows.net/cineasy-images/eclipse_point_backdrop.jpg',
    'Denis Villeneuve',
    'Jessica Chastain, Dev Patel, Benedict Cumberbatch',
    'ACTIVE'),

    ('City of Glass',
     'In a futuristic metropolis made entirely of glass, a detective investigates a series of murders that expose the fragile transparency of society itself.',
     'Mystery, Thriller, Sci-Fi',
     118,
     'R',
     '2025-11-24',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/city_of_glass_poster.jpg',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/city_of_glass_backdrop.jpg',
     'Alex Garland',
     'Robert Pattinson, Ana de Armas, Riz Ahmed',
     'ACTIVE'),

    ('The Horizon Code',
     'An ex-hacker is forced back into the cyber world to decode an AI’s final message that could change humanity’s future forever.',
     'Thriller, Action, Sci-Fi',
     125,
     'PG-13',
     '2025-12-12',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/horizon_code_poster.jpg',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/horizon_code_backdrop.jpg',
     'Christopher Nolan',
     'John David Washington, Florence Pugh, Daniel Kaluuya',
     'INACTIVE'),

    ('Silent Harbor',
     'A retired naval officer living in a quiet coastal town becomes entangled in a conspiracy when a missing submarine washes ashore.',
     'Drama, Mystery, Crime',
     112,
     'PG-13',
     '2025-11-28',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/silent_harbor_poster.jpg',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/silent_harbor_backdrop.jpg',
     'Greta Gerwig',
     'Gary Oldman, Carey Mulligan, Timothée Chalamet',
     'ACTIVE'),

    ('Parallel Hearts',
     'Two strangers from different timelines fall in love through an experimental virtual reality system that bends space and time.',
     'Romance, Sci-Fi, Drama',
     121,
     'PG',
     '2025-06-14',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/parallel_hearts_poster.jpg',
     'https://cineasy-images.blob.core.windows.net/cineasy-images/parallel_hearts_backdrop.jpg',
     'Chloé Zhao',
     'Zendaya, Andrew Garfield, Rami Malek',
     'ENDED');


-- ===========================
-- 🏢 AUDITORIUMS
-- ===========================
INSERT INTO auditorium (name, total_rows, seats_per_row)
VALUES
    ('Auditorium A', 3, 4),
    ('Auditorium B', 5, 6),
    ('Auditorium C', 6, 8);


-- ===========================
-- 💺 SEATS
-- ===========================
-- ===== SEATS FOR AUDITORIUM A (3x4 = 12) =====
INSERT INTO seat (row_label, seat_number, seat_type_enum, auditorium_id)
VALUES
     ('A', 1, 'REGULAR', 1), ('A', 2, 'REGULAR', 1), ('A', 3, 'REGULAR', 1), ('A', 4, 'REGULAR', 1),
     ('B', 1, 'REGULAR', 1), ('B', 2, 'REGULAR', 1), ('B', 3, 'REGULAR', 1), ('B', 4, 'REGULAR', 1),
     ('C', 1, 'REGULAR', 1), ('C', 2, 'REGULAR', 1), ('C', 3, 'REGULAR', 1), ('C', 4, 'REGULAR', 1);

-- ===== SEATS FOR AUDITORIUM B (5x6 = 30) =====
INSERT INTO seat (row_label, seat_number, seat_type_enum, auditorium_id)
VALUES
     ('A', 1, 'REGULAR', 2), ('A', 2, 'REGULAR', 2), ('A', 3, 'REGULAR', 2), ('A', 4, 'REGULAR', 2), ('A', 5, 'REGULAR', 2), ('A', 6, 'REGULAR', 2),
     ('B', 1, 'REGULAR', 2), ('B', 2, 'REGULAR', 2), ('B', 3, 'REGULAR', 2), ('B', 4, 'REGULAR', 2), ('B', 5, 'REGULAR', 2), ('B', 6, 'REGULAR', 2),
     ('C', 1, 'REGULAR', 2), ('C', 2, 'REGULAR', 2), ('C', 3, 'REGULAR', 2), ('C', 4, 'REGULAR', 2), ('C', 5, 'REGULAR', 2), ('C', 6, 'REGULAR', 2),
     ('D', 1, 'REGULAR', 2), ('D', 2, 'REGULAR', 2), ('D', 3, 'REGULAR', 2), ('D', 4, 'REGULAR', 2), ('D', 5, 'REGULAR', 2), ('D', 6, 'REGULAR', 2),
     ('E', 1, 'REGULAR', 2), ('E', 2, 'REGULAR', 2), ('E', 3, 'REGULAR', 2), ('E', 4, 'REGULAR', 2), ('E', 5, 'REGULAR', 2), ('E', 6, 'REGULAR', 2);

-- ===== SEATS FOR AUDITORIUM C (6x8 = 48) =====
INSERT INTO seat (row_label, seat_number, seat_type_enum, auditorium_id)
VALUES
     ('A', 1, 'REGULAR', 3), ('A', 2, 'REGULAR', 3), ('A', 3, 'REGULAR', 3), ('A', 4, 'REGULAR', 3), ('A', 5, 'REGULAR', 3), ('A', 6, 'REGULAR', 3), ('A', 7, 'REGULAR', 3), ('A', 8, 'REGULAR', 3),
     ('B', 1, 'REGULAR', 3), ('B', 2, 'REGULAR', 3), ('B', 3, 'REGULAR', 3), ('B', 4, 'REGULAR', 3), ('B', 5, 'REGULAR', 3), ('B', 6, 'REGULAR', 3), ('B', 7, 'REGULAR', 3), ('B', 8, 'REGULAR', 3),
     ('C', 1, 'REGULAR', 3), ('C', 2, 'REGULAR', 3), ('C', 3, 'REGULAR', 3), ('C', 4, 'REGULAR', 3), ('C', 5, 'REGULAR', 3), ('C', 6, 'REGULAR', 3), ('C', 7, 'REGULAR', 3), ('C', 8, 'REGULAR', 3),
     ('D', 1, 'REGULAR', 3), ('D', 2, 'REGULAR', 3), ('D', 3, 'REGULAR', 3), ('D', 4, 'REGULAR', 3), ('D', 5, 'REGULAR', 3), ('D', 6, 'REGULAR', 3), ('D', 7, 'REGULAR', 3), ('D', 8, 'REGULAR', 3),
     ('E', 1, 'REGULAR', 3), ('E', 2, 'REGULAR', 3), ('E', 3, 'REGULAR', 3), ('E', 4, 'REGULAR', 3), ('E', 5, 'REGULAR', 3), ('E', 6, 'REGULAR', 3), ('E', 7, 'REGULAR', 3), ('E', 8, 'REGULAR', 3),
     ('F', 1, 'REGULAR', 3), ('F', 2, 'REGULAR', 3), ('F', 3, 'REGULAR', 3), ('F', 4, 'REGULAR', 3), ('F', 5, 'REGULAR', 3), ('F', 6, 'REGULAR', 3), ('F', 7, 'REGULAR', 3), ('F', 8, 'REGULAR', 3);


-- ===========================

