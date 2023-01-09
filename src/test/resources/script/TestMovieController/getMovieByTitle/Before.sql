INSERT INTO director (first_name, last_name)
VALUES ('FirstName', 'LastName');

INSERT INTO movie (description, director_id, genre, title, year)
VALUES ('Description', (SELECT d.id FROM director d WHERE d.first_name LIKE 'FirstName' AND d.last_name LIKE 'LastName'), 0, 'Title', 2000);

INSERT INTO actor (first_name, last_name, birthday)
VALUES
    ('ActorFirstName 1', 'ActorLastName 1', '2000-01-01'),
    ('ActorFirstName 2', 'ActorLastName 2', '2000-01-02'),
    ('ActorFirstName 3', 'ActorLastName 3', '2000-01-03');

INSERT INTO actor_movie (movie_id, actor_id)
VALUES
    ((SELECT m.id FROM movie m WHERE m.title LIKE 'Title'), (SELECT a.id FROM actor a WHERE a.first_name LIKE 'ActorFirstName 1')),
    ((SELECT m.id FROM movie m WHERE m.title LIKE 'Title'), (SELECT a.id FROM actor a WHERE a.first_name LIKE 'ActorFirstName 2')),
    ((SELECT m.id FROM movie m WHERE m.title LIKE 'Title'), (SELECT a.id FROM actor a WHERE a.first_name LIKE 'ActorFirstName 3'));