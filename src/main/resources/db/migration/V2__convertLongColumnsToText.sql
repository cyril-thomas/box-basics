ALTER TABLE public.blog
ALTER COLUMN content TYPE text;

ALTER TABLE public.about
ALTER COLUMN about_content TYPE text;

ALTER TABLE public.comment
ALTER COLUMN content TYPE text;

ALTER TABLE public.home
ALTER COLUMN custom_css TYPE text,
ALTER COLUMN intro_content TYPE text,
ALTER COLUMN registration_banner TYPE text,
ALTER COLUMN registration_content TYPE text,
ALTER COLUMN registration_title TYPE text,
ALTER COLUMN services_title TYPE text,
ALTER COLUMN title TYPE text
;

ALTER TABLE public.service
ALTER COLUMN content TYPE text;
