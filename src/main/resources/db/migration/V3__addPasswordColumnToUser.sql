ALTER TABLE public."user"
ADD COLUMN password character varying(60);

ALTER TABLE public."user"
ADD COLUMN role character varying(5);