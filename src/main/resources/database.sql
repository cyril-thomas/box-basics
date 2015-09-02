--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

ALTER TABLE ONLY public.blog DROP CONSTRAINT fk_rall6e8uuux6dewfggmhf5j50;
ALTER TABLE ONLY public.about DROP CONSTRAINT fk_q1tef8yvh6edl5yy3ue5dwx38;
ALTER TABLE ONLY public.custom_link DROP CONSTRAINT fk_pqoj9h047l3bl65xlpbnvpkpp;
ALTER TABLE ONLY public.schedule DROP CONSTRAINT fk_p5rum5gpit1dvbm0c73jkt8ee;
ALTER TABLE ONLY public.organization DROP CONSTRAINT fk_l070gahmlj4g2sqbm72btw64e;
ALTER TABLE ONLY public.member DROP CONSTRAINT fk_kpn37wgkqt65eh470e2nn2mg;
ALTER TABLE ONLY public.schedule DROP CONSTRAINT fk_jed4v9iu84hemcg1s24de0ndm;
ALTER TABLE ONLY public.classes DROP CONSTRAINT fk_d86t12ld6jq4925osdyqbts60;
ALTER TABLE ONLY public.payment DROP CONSTRAINT fk_ctvskou1xh26obtbvta4d2o4l;
ALTER TABLE ONLY public.service DROP CONSTRAINT fk_bvj7wpe2chyt8p7ci0qh1k10j;
ALTER TABLE ONLY public.coach DROP CONSTRAINT fk_bf8qggp2dw21lvbe4tskf1k20;
ALTER TABLE ONLY public.classes DROP CONSTRAINT fk_8lbogbk50htx1ktbjrewxf976;
ALTER TABLE ONLY public.announcement DROP CONSTRAINT fk_8fbwm4qmj8r3x19qfcndh0jq0;
ALTER TABLE ONLY public.home DROP CONSTRAINT fk_8bxovwjrhxli4c03n86bf0whk;
ALTER TABLE ONLY public.blog DROP CONSTRAINT fk_2w3pdbun3aox1mn2ox4xxnj7d;
ALTER TABLE ONLY public.payment DROP CONSTRAINT fk_2f6yo5l5gh3yf7l8yos6ib96a;
ALTER TABLE ONLY public.wod DROP CONSTRAINT wod_pkey;
ALTER TABLE ONLY public.service DROP CONSTRAINT service_pkey;
ALTER TABLE ONLY public.schedule DROP CONSTRAINT schedule_pkey;
ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_pkey;
ALTER TABLE ONLY public.organization DROP CONSTRAINT organization_pkey;
ALTER TABLE ONLY public.member DROP CONSTRAINT member_pkey;
ALTER TABLE ONLY public.home DROP CONSTRAINT home_pkey;
ALTER TABLE ONLY public.custom_link DROP CONSTRAINT custom_link_pkey;
ALTER TABLE ONLY public.coach DROP CONSTRAINT coach_pkey;
ALTER TABLE ONLY public.classes DROP CONSTRAINT classes_pkey;
ALTER TABLE ONLY public.blog DROP CONSTRAINT blog_pkey;
ALTER TABLE ONLY public.announcement DROP CONSTRAINT announcement_pkey;
ALTER TABLE ONLY public.address DROP CONSTRAINT address_pkey;
ALTER TABLE ONLY public.about DROP CONSTRAINT about_pkey;
DROP TABLE public.wod;
DROP TABLE public.service;
DROP SEQUENCE public.seq_wod;
DROP SEQUENCE public.seq_service;
DROP SEQUENCE public.seq_schedule;
DROP SEQUENCE public.seq_payment;
DROP SEQUENCE public.seq_organization;
DROP SEQUENCE public.seq_member;
DROP SEQUENCE public.seq_home;
DROP SEQUENCE public.seq_custom_link;
DROP SEQUENCE public.seq_coach;
DROP SEQUENCE public.seq_classes;
DROP SEQUENCE public.seq_blog;
DROP SEQUENCE public.seq_announcement;
DROP SEQUENCE public.seq_address;
DROP SEQUENCE public.seq_about;
DROP TABLE public.schedule;
DROP TABLE public.payment;
DROP TABLE public.organization;
DROP TABLE public.member;
DROP TABLE public.home;
DROP TABLE public.custom_link;
DROP TABLE public.coach;
DROP TABLE public.classes;
DROP TABLE public.blog;
DROP TABLE public.announcement;
DROP TABLE public.address;
DROP TABLE public.about;
DROP SCHEMA public;
--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: about; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE about (
    about_id bigint NOT NULL,
    about_content text,
    facebook_url text,
    google_plus_url text,
    about_title text NOT NULL,
    twitter_url text,
    org_id bigint
);


ALTER TABLE public.about OWNER TO dbuser;

--
-- Name: address; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE address (
    address_id bigint NOT NULL,
    city text,
    first_line text,
    second_line text,
    state text,
    zip_code text
);


ALTER TABLE public.address OWNER TO dbuser;

--
-- Name: announcement; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE announcement (
    announcement_id bigint NOT NULL,
    content text,
    created_date date,
    end_date date,
    title text,
    org_id bigint
);


ALTER TABLE public.announcement OWNER TO dbuser;

--
-- Name: blog; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE blog (
    blog_id bigint NOT NULL,
    content text,
    post_date date,
    title text,
    coach_id bigint,
    org_id bigint
);


ALTER TABLE public.blog OWNER TO dbuser;

--
-- Name: classes; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE classes (
    class_id bigint NOT NULL,
    description text,
    name text,
    coach_id bigint,
    org_id bigint
);


ALTER TABLE public.classes OWNER TO dbuser;

--
-- Name: coach; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE coach (
    coach_id bigint NOT NULL,
    description text,
    facebook_url text,
    job_title text,
    linked_in text,
    profile_pic text,
    twitter_url text,
    user_id bigint
);


ALTER TABLE public.coach OWNER TO dbuser;

--
-- Name: custom_link; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE custom_link (
    custom_link_id bigint NOT NULL,
    link_name text,
    link_url text,
    org_id bigint
);


ALTER TABLE public.custom_link OWNER TO dbuser;

--
-- Name: home; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE home (
    home_id bigint NOT NULL,
    alt_bg_url text,
    bg_url text,
    custom_css text,
    intro_content text,
    logo_url text,
    registration_banner text,
    registration_content text,
    registration_title text,
    services_title text,
    title text NOT NULL,
    video_url text,
    org_id bigint
);


ALTER TABLE public.home OWNER TO dbuser;

--
-- Name: member; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE member (
    user_id bigint NOT NULL,
    city text,
    dob date,
    user_email text,
    first_name text NOT NULL,
    gender character varying(1),
    last_name text NOT NULL,
    user_phone text,
    zip character varying(5),
    org_id bigint
);


ALTER TABLE public.member OWNER TO dbuser;

--
-- Name: organization; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE organization (
    org_id bigint NOT NULL,
    email text,
    hash_tag text,
    hours text,
    name text NOT NULL,
    phone text,
    web_domain text,
    address_id bigint
);


ALTER TABLE public.organization OWNER TO dbuser;

--
-- Name: payment; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE payment (
    payment_id bigint NOT NULL,
    amount numeric(19,2),
    confirmation_id text,
    customer_id text,
    status text,
    org_id bigint,
    user_id bigint
);


ALTER TABLE public.payment OWNER TO dbuser;

--
-- Name: schedule; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE schedule (
    schedule_id bigint NOT NULL,
    wod_date date,
    workout text,
    org_id bigint,
    wod_id bigint
);


ALTER TABLE public.schedule OWNER TO dbuser;

--
-- Name: seq_about; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_about
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_about OWNER TO dbuser;

--
-- Name: seq_address; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_address
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_address OWNER TO dbuser;

--
-- Name: seq_announcement; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_announcement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_announcement OWNER TO dbuser;

--
-- Name: seq_blog; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_blog
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_blog OWNER TO dbuser;

--
-- Name: seq_classes; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_classes
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_classes OWNER TO dbuser;

--
-- Name: seq_coach; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_coach
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_coach OWNER TO dbuser;

--
-- Name: seq_custom_link; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_custom_link
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_custom_link OWNER TO dbuser;

--
-- Name: seq_home; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_home
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_home OWNER TO dbuser;

--
-- Name: seq_member; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_member
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_member OWNER TO dbuser;

--
-- Name: seq_organization; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_organization
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_organization OWNER TO dbuser;

--
-- Name: seq_payment; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_payment
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_payment OWNER TO dbuser;

--
-- Name: seq_schedule; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_schedule
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_schedule OWNER TO dbuser;

--
-- Name: seq_service; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_service
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_service OWNER TO dbuser;

--
-- Name: seq_wod; Type: SEQUENCE; Schema: public; Owner: dbuser
--

CREATE SEQUENCE seq_wod
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_wod OWNER TO dbuser;

--
-- Name: service; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE service (
    service_id bigint NOT NULL,
    content text,
    title text,
    org_id bigint
);


ALTER TABLE public.service OWNER TO dbuser;

--
-- Name: wod; Type: TABLE; Schema: public; Owner: dbuser; Tablespace: 
--

CREATE TABLE wod (
    wod_id bigint NOT NULL,
    description text NOT NULL,
    name text NOT NULL,
    notes text
);


ALTER TABLE public.wod OWNER TO dbuser;

--
-- Name: about_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY about
    ADD CONSTRAINT about_pkey PRIMARY KEY (about_id);


--
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- Name: announcement_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY announcement
    ADD CONSTRAINT announcement_pkey PRIMARY KEY (announcement_id);


--
-- Name: blog_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY blog
    ADD CONSTRAINT blog_pkey PRIMARY KEY (blog_id);


--
-- Name: classes_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY classes
    ADD CONSTRAINT classes_pkey PRIMARY KEY (class_id);


--
-- Name: coach_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY coach
    ADD CONSTRAINT coach_pkey PRIMARY KEY (coach_id);


--
-- Name: custom_link_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY custom_link
    ADD CONSTRAINT custom_link_pkey PRIMARY KEY (custom_link_id);


--
-- Name: home_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY home
    ADD CONSTRAINT home_pkey PRIMARY KEY (home_id);


--
-- Name: member_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY member
    ADD CONSTRAINT member_pkey PRIMARY KEY (user_id);


--
-- Name: organization_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (org_id);


--
-- Name: payment_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (payment_id);


--
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (schedule_id);


--
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (service_id);


--
-- Name: wod_pkey; Type: CONSTRAINT; Schema: public; Owner: dbuser; Tablespace: 
--

ALTER TABLE ONLY wod
    ADD CONSTRAINT wod_pkey PRIMARY KEY (wod_id);


--
-- Name: fk_2f6yo5l5gh3yf7l8yos6ib96a; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY payment
    ADD CONSTRAINT fk_2f6yo5l5gh3yf7l8yos6ib96a FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_2w3pdbun3aox1mn2ox4xxnj7d; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY blog
    ADD CONSTRAINT fk_2w3pdbun3aox1mn2ox4xxnj7d FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_8bxovwjrhxli4c03n86bf0whk; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY home
    ADD CONSTRAINT fk_8bxovwjrhxli4c03n86bf0whk FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_8fbwm4qmj8r3x19qfcndh0jq0; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY announcement
    ADD CONSTRAINT fk_8fbwm4qmj8r3x19qfcndh0jq0 FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_8lbogbk50htx1ktbjrewxf976; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY classes
    ADD CONSTRAINT fk_8lbogbk50htx1ktbjrewxf976 FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_bf8qggp2dw21lvbe4tskf1k20; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY coach
    ADD CONSTRAINT fk_bf8qggp2dw21lvbe4tskf1k20 FOREIGN KEY (user_id) REFERENCES member(user_id);


--
-- Name: fk_bvj7wpe2chyt8p7ci0qh1k10j; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk_bvj7wpe2chyt8p7ci0qh1k10j FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_ctvskou1xh26obtbvta4d2o4l; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY payment
    ADD CONSTRAINT fk_ctvskou1xh26obtbvta4d2o4l FOREIGN KEY (user_id) REFERENCES member(user_id);


--
-- Name: fk_d86t12ld6jq4925osdyqbts60; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY classes
    ADD CONSTRAINT fk_d86t12ld6jq4925osdyqbts60 FOREIGN KEY (coach_id) REFERENCES coach(coach_id);


--
-- Name: fk_jed4v9iu84hemcg1s24de0ndm; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_jed4v9iu84hemcg1s24de0ndm FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_kpn37wgkqt65eh470e2nn2mg; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY member
    ADD CONSTRAINT fk_kpn37wgkqt65eh470e2nn2mg FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_l070gahmlj4g2sqbm72btw64e; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY organization
    ADD CONSTRAINT fk_l070gahmlj4g2sqbm72btw64e FOREIGN KEY (address_id) REFERENCES address(address_id);


--
-- Name: fk_p5rum5gpit1dvbm0c73jkt8ee; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT fk_p5rum5gpit1dvbm0c73jkt8ee FOREIGN KEY (wod_id) REFERENCES wod(wod_id);


--
-- Name: fk_pqoj9h047l3bl65xlpbnvpkpp; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY custom_link
    ADD CONSTRAINT fk_pqoj9h047l3bl65xlpbnvpkpp FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_q1tef8yvh6edl5yy3ue5dwx38; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY about
    ADD CONSTRAINT fk_q1tef8yvh6edl5yy3ue5dwx38 FOREIGN KEY (org_id) REFERENCES organization(org_id);


--
-- Name: fk_rall6e8uuux6dewfggmhf5j50; Type: FK CONSTRAINT; Schema: public; Owner: dbuser
--

ALTER TABLE ONLY blog
    ADD CONSTRAINT fk_rall6e8uuux6dewfggmhf5j50 FOREIGN KEY (coach_id) REFERENCES coach(coach_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

