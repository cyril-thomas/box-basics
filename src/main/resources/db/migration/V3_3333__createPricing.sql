DROP SEQUENCE if exists seq_pricing ;
DROP TABLE if exists pricing;

CREATE SEQUENCE seq_pricing
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_pricing
  OWNER TO dbuser;

CREATE TABLE pricing
(
  price_id bigint NOT NULL,
  membership_level text,
  cost decimal,
  org_id bigint,
  CONSTRAINT pricing_pkey PRIMARY KEY (price_id),
  CONSTRAINT fk_pricing_org FOREIGN KEY (org_id)
      REFERENCES organization (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE blog
  OWNER TO dbuser;
