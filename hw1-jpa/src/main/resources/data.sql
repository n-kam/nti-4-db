insert into groups (name, g_year)
values  ('Gryffindor', 1991),
        ('Slytherin', 1991);

insert into students (name, group_id)
values  ('Harry Potter',        1),
        ('Ronald Weasley',      1),
        ('Hermione Granger',    1),
        ('Draco Malfoy',        2),
        ('Theodore Nott',       2),
        ('Blaise Zabini',       2);

insert into courses (name)
values  ('Potions'),
        ('Charms');

insert into assignments (name, course_id)
values  ('Homework',  1),
        ('Classwork', 1),
        ('Essay',     1),
        ('Homework',  2),
        ('Classwork', 2),
        ('Essay',     2);

insert into grades (student_id, assignment_id, g_value)
values
        (1, 1, 4),
        (1, 2, 2),
        (1, 3, 3),
        (1, 4, 2),
        (1, 5, 5),
        (1, 6, 4),

        (2, 1, 3),
        (2, 2, 2),
        (2, 3, 3),
        (2, 4, 0),
        (2, 5, 3),
        (2, 6, 2),

        (3, 1, 5),
        (3, 2, 5),
        (3, 3, 5),
        (3, 4, 5),
        (3, 5, 5),
        (3, 6, 5),

        (4, 1, 4),
        (4, 2, 5),
        (4, 3, 5),
        (4, 4, 3),
        (4, 5, 4),
        (4, 6, 5),

        (5, 1, 3),
        (5, 2, 2),
        (5, 3, 4),
        (5, 4, 5),
        (5, 5, 5),
        (5, 6, 5),

        (6, 1, 3),
        (6, 2, 4),
        (6, 3, 5),
        (6, 4, 5),
        (6, 5, 3),
        (6, 6, 5);
