DELETE FROM actor_movie am WHERE am.movie_id = (SELECT m.id FROM movie m WHERE m.title LIKE 'Title');
DELETE FROM actor a WHERE a.first_name LIKE 'ActorFirstName%';
DELETE FROM movie m WHERE m.title LIKE 'Title';
DELETE FROM director d WHERE d.first_name LIKE 'FirstName' AND d.last_name LIKE 'LastName';

