DELETE FROM users;

INSERT INTO users(username,email,password)
VALUES
    (
        'Integration',
        'integration@test.com',
        '$2a$10$7EqJtq98hPqEX7fNZaFWoOHi6D6x8YzQ0jrISFRCGDpa2BkLomqKG'
    );