CREATE SEQUENCE public.customer_customer_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.customer_customer_id_seq
    OWNER TO ionut;
    
CREATE SEQUENCE public.customer_address_address_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.customer_address_address_id_seq
    OWNER TO ionut;
    
CREATE TABLE IF NOT EXISTS public.customer
(
    customer_id bigint NOT NULL DEFAULT nextval('customer_customer_id_seq'::regclass),
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date NOT NULL,
    register_time timestamp without time zone NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
)

TABLESPACE pg_default;

ALTER TABLE public.customer
    OWNER to ionut;
    
CREATE TABLE IF NOT EXISTS public.customer_address
(
    address_id bigint NOT NULL DEFAULT nextval('customer_address_address_id_seq'::regclass),
    customer_id bigint NOT NULL,
    country character varying(50) COLLATE pg_catalog."default" NOT NULL,
    city character varying(50) COLLATE pg_catalog."default" NOT NULL,
    street character varying(100) COLLATE pg_catalog."default" NOT NULL,
    address_no smallint NOT NULL,
    zip_code character(10) COLLATE pg_catalog."default",
    additional_info character varying(300) COLLATE pg_catalog."default",
    CONSTRAINT customer_address_pkey PRIMARY KEY (address_id),
    CONSTRAINT customer_address_fk FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.customer_address
    OWNER to ionut;
-- Index: fki_customer_address_fk

-- DROP INDEX public.fki_customer_address_fk;

CREATE INDEX fki_customer_address_fk
    ON public.customer_address USING btree
    (customer_id ASC NULLS LAST)
    TABLESPACE pg_default;    