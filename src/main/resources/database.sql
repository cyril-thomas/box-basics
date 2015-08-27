-- Table: address

DROP SEQUENCE IF EXISTS seq_address CASCADE;

CREATE SEQUENCE seq_address
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_address OWNER TO woddojo;

DROP TABLE IF EXISTS address CASCADE;

CREATE TABLE address
(
  address_id bigint NOT NULL DEFAULT nextval('seq_address'::regclass),
  city character varying(255),
  first_line character varying(255),
  second_line character varying(255),
  state character varying(255),
  zip_code character varying(255),
  CONSTRAINT address_pkey PRIMARY KEY (address_id)
);
ALTER TABLE address OWNER TO woddojo;

-- Table: organization
DROP SEQUENCE IF EXISTS seq_organization CASCADE;

CREATE SEQUENCE seq_organization
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_organization OWNER TO woddojo;

DROP TABLE IF EXISTS organization CASCADE;

CREATE TABLE organization
(
  org_id bigint NOT NULL DEFAULT nextval('seq_organization'::regclass),
  email character varying(255),
  hash_tag character varying(255),
  name character varying(255) NOT NULL,
  phone character varying(255),
  web_domain character varying(255),
  address_id bigint,
  CONSTRAINT organization_pkey PRIMARY KEY (org_id),
  CONSTRAINT fk_org_address FOREIGN KEY (address_id)
      REFERENCES address (address_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE organization OWNER TO woddojo;

-- Table: home
DROP SEQUENCE IF EXISTS seq_home CASCADE;

CREATE SEQUENCE seq_home
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_home OWNER TO woddojo;

DROP TABLE IF EXISTS home CASCADE;

CREATE TABLE home
(
  home_id bigint NOT NULL DEFAULT nextval('seq_home'::regclass),
  intro_content text,
  registration_banner text,
  registration_content text,
  custom_css text,
  registration_title character varying(255),
  services_title character varying(255),
  title character varying(255) NOT NULL,
  video_url character varying(255),
  org_id bigint,
  CONSTRAINT home_pkey PRIMARY KEY (home_id),
  CONSTRAINT fk_home_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE home OWNER TO woddojo;


-- Table: about

DROP SEQUENCE IF EXISTS seq_about CASCADE;

CREATE SEQUENCE seq_about
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_about OWNER TO woddojo;

DROP TABLE IF EXISTS about CASCADE;

CREATE TABLE about
(
  about_id bigint NOT NULL DEFAULT nextval('seq_about'::regclass),
  about_content text,
  facebook_url character varying(255),
  google_plus_url character varying(255),
  about_title character varying(255) NOT NULL,
  twitter_url character varying(255),
  org_id bigint,
  CONSTRAINT about_pkey PRIMARY KEY (about_id),
  CONSTRAINT fk_about_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE about OWNER TO woddojo;

-- Table: service

DROP SEQUENCE IF EXISTS seq_service CASCADE;

CREATE SEQUENCE seq_service
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_service OWNER TO woddojo;

DROP TABLE IF EXISTS service CASCADE;

CREATE TABLE service
(
  service_id bigint NOT NULL DEFAULT nextval('seq_service'::regclass),
  content character varying(255),
  title character varying(255),
  org_id bigint,
  CONSTRAINT service_pkey PRIMARY KEY (service_id),
  CONSTRAINT fk_service_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE service OWNER TO woddojo;



-- Table: announcement

DROP SEQUENCE IF EXISTS seq_announcement CASCADE;

CREATE SEQUENCE seq_announcement
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_announcement OWNER TO woddojo;

DROP TABLE IF EXISTS announcement CASCADE;

CREATE TABLE announcement
(
  announcement_id bigint NOT NULL DEFAULT nextval('seq_announcement'::regclass),
  content character varying(255),
  created_date date,
  end_date date,
  title character varying(255),
  org_id bigint,
  CONSTRAINT announcement_pkey PRIMARY KEY (announcement_id),
  CONSTRAINT fk_announcement_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE announcement OWNER TO woddojo;

-- Table: member

DROP SEQUENCE IF EXISTS seq_member CASCADE;

CREATE SEQUENCE seq_member
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_member OWNER TO woddojo;

DROP TABLE IF EXISTS member CASCADE;

CREATE TABLE member
(
  user_id bigint NOT NULL DEFAULT nextval('seq_member'::regclass),
  user_email character varying(255),
  first_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  user_phone character varying(255),
  dob date,
  city character varying(255),
  zip character varying(5),
  org_id bigint,
  CONSTRAINT member_pkey PRIMARY KEY (user_id),
  CONSTRAINT fk_member_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE member OWNER TO woddojo;



-- Table: coach
DROP SEQUENCE IF EXISTS seq_coach CASCADE;

CREATE SEQUENCE seq_coach
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_coach OWNER TO woddojo;

DROP TABLE IF EXISTS coach CASCADE;

CREATE TABLE coach
(
  coach_id bigint NOT NULL DEFAULT nextval('seq_coach'::regclass),
  description character varying(255),
  facebook_url character varying(255),
  job_title character varying(255),
  linked_in character varying(255),
  profile_pic character varying(255),
  twitter_url character varying(255),
  user_id bigint,
  CONSTRAINT coach_pkey PRIMARY KEY (coach_id),
  CONSTRAINT fk_coach_member FOREIGN KEY (user_id)
      REFERENCES member (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE coach OWNER TO woddojo;



-- Table: classes

DROP SEQUENCE IF EXISTS seq_classes CASCADE;

CREATE SEQUENCE seq_classes
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_classes OWNER TO woddojo;

DROP TABLE IF EXISTS classes CASCADE;

CREATE TABLE classes
(
  class_id bigint NOT NULL DEFAULT nextval('seq_classes'::regclass),
  description character varying(255),
  name character varying(255),
  coach_id bigint,
  org_id bigint,
  CONSTRAINT classes_pkey PRIMARY KEY (class_id),
  CONSTRAINT fk_classes_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_classes_coach FOREIGN KEY (coach_id)
      REFERENCES coach (coach_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE classes OWNER TO woddojo;


-- Table: payment

DROP SEQUENCE IF EXISTS seq_payment CASCADE;

CREATE SEQUENCE seq_payment
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_payment OWNER TO woddojo;

DROP TABLE IF EXISTS payment CASCADE;

CREATE TABLE payment
(
  payment_id bigint NOT NULL DEFAULT nextval('seq_payment'::regclass),
  amount numeric(19,2),
  confirmation_id character varying(255),
  customer_id character varying(255),
  status character varying(255),
  org_id bigint,
  user_id bigint,
  CONSTRAINT payment_pkey PRIMARY KEY (payment_id),
  CONSTRAINT fk_payment_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_payment_member FOREIGN KEY (user_id)
      REFERENCES member (user_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE payment OWNER TO woddojo;


-- Table: wod

DROP SEQUENCE IF EXISTS seq_wod CASCADE;

CREATE SEQUENCE seq_wod
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_wod OWNER TO woddojo;

DROP TABLE IF EXISTS wod CASCADE;

CREATE TABLE wod
(
  wod_id bigint NOT NULL DEFAULT nextval('seq_wod'::regclass),
  description text NOT NULL,
  name character varying(255) NOT NULL,
  notes character varying(255),
  CONSTRAINT wod_pkey PRIMARY KEY (wod_id)
);
ALTER TABLE wod OWNER TO woddojo;

-- Table: schedule

DROP SEQUENCE IF EXISTS seq_schedule CASCADE;

CREATE SEQUENCE seq_schedule
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_schedule OWNER TO woddojo;

DROP TABLE IF EXISTS schedule CASCADE;

CREATE TABLE schedule
(
  schedule_id bigint NOT NULL DEFAULT nextval('seq_schedule'::regclass),
  workout character varying(255),
  wod_date date,
  org_id bigint,
  wod_id bigint,
  CONSTRAINT schedule_pkey PRIMARY KEY (schedule_id),
  CONSTRAINT fk_schedule_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_schedule_wod FOREIGN KEY (wod_id)
      REFERENCES wod (wod_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE schedule OWNER TO woddojo;


-- Table: blog

DROP SEQUENCE IF EXISTS seq_blog CASCADE;

CREATE SEQUENCE seq_blog
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_blog OWNER TO woddojo;

DROP TABLE IF EXISTS blog CASCADE;

CREATE TABLE blog
(
  blog_id bigint NOT NULL DEFAULT nextval('seq_blog'::regclass),
  title character varying(255),
  content text,
  post_date date,
  org_id bigint,
  coach_id bigint,
  CONSTRAINT blog_pkey PRIMARY KEY (blog_id),
  CONSTRAINT fk_blog_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_blog_coach FOREIGN KEY (coach_id)
      REFERENCES coach (coach_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE blog OWNER TO woddojo;

-- Table: Custom Links

DROP SEQUENCE IF EXISTS seq_custom_link CASCADE;

CREATE SEQUENCE seq_custom_link
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_custom_link OWNER TO woddojo;

DROP TABLE IF EXISTS custom_link CASCADE;

CREATE TABLE custom_link
(
  custom_link_id bigint NOT NULL DEFAULT nextval('seq_custom_link'::regclass),
  link_name character varying(255),
  link_url character varying(255),
  org_id bigint,
  CONSTRAINT custom_link_pkey PRIMARY KEY (custom_link_id),
  CONSTRAINT fk_custom_link_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE custom_link OWNER TO woddojo;
