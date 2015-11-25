ALTER TABLE public.coach ADD COLUMN rank int;

update public.coach set rank = 1;

ALTER TABLE public.coach ALTER COLUMN rank SET NOT NULL;