-- company name lower case
insert into "user"(
    user_id,
    first_name,
    last_name,
    user_email,
    user_phone,
    org_id,
    password,
    role)
values
(
    nextval('seq_user'),
    'Cyril',
    'Thomas',
    'admin@woddojo.com',
    '8016969697',
    -1,
    '$2a$10$VAeVRYE8yLej.Usc9UsW4epQ4BGBI.2aQVaUun8aWhIGePXD3nP2G',
    'COACH'
);