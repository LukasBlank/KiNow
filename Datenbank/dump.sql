--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: buchung; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buchung (
    bid integer NOT NULL,
    nid integer,
    vid integer
);


ALTER TABLE public.buchung OWNER TO postgres;

--
-- Name: buchung_bid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.buchung_bid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.buchung_bid_seq OWNER TO postgres;

--
-- Name: buchung_bid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buchung_bid_seq OWNED BY public.buchung.bid;


--
-- Name: film; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.film (
    fid integer NOT NULL,
    titel character varying(30),
    beschreibung character varying(500),
    bewertung integer,
    preis numeric(4,2),
    fsk integer,
    dauer integer,
    dreid boolean
);


ALTER TABLE public.film OWNER TO postgres;

--
-- Name: film_fid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.film_fid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.film_fid_seq OWNER TO postgres;

--
-- Name: film_fid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.film_fid_seq OWNED BY public.film.fid;


--
-- Name: kino; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kino (
    kinoid integer NOT NULL,
    name character varying(20),
    ort character varying(20)
);


ALTER TABLE public.kino OWNER TO postgres;

--
-- Name: kino_kinoid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kino_kinoid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kino_kinoid_seq OWNER TO postgres;

--
-- Name: kino_kinoid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kino_kinoid_seq OWNED BY public.kino.kinoid;


--
-- Name: kino_spielt_filme; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kino_spielt_filme (
    kinoid integer,
    fid integer
);


ALTER TABLE public.kino_spielt_filme OWNER TO postgres;

--
-- Name: kinosaal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kinosaal (
    saalid integer NOT NULL,
    kinoid integer,
    barrierefrei boolean
);


ALTER TABLE public.kinosaal OWNER TO postgres;

--
-- Name: kinosaal_saalid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kinosaal_saalid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kinosaal_saalid_seq OWNER TO postgres;

--
-- Name: kinosaal_saalid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kinosaal_saalid_seq OWNED BY public.kinosaal.saalid;


--
-- Name: kunden_haben_zahlungsmethoden; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kunden_haben_zahlungsmethoden (
    nid integer,
    zid integer
);


ALTER TABLE public.kunden_haben_zahlungsmethoden OWNER TO postgres;

--
-- Name: nutzer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nutzer (
    nid integer NOT NULL,
    vorname character varying(30),
    nachname character varying(30),
    geburtstag date,
    email character varying(50),
    passwort character varying(69)
);


ALTER TABLE public.nutzer OWNER TO postgres;

--
-- Name: nutzer_nid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nutzer_nid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nutzer_nid_seq OWNER TO postgres;

--
-- Name: nutzer_nid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nutzer_nid_seq OWNED BY public.nutzer.nid;


--
-- Name: platzbelegung; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.platzbelegung (
    vid integer,
    reihe integer,
    sitzplatz integer,
    frei boolean
);


ALTER TABLE public.platzbelegung OWNER TO postgres;

--
-- Name: sitz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sitz (
    sitzid integer NOT NULL,
    saalid integer,
    reihe integer,
    sitzplatz integer,
    loge boolean
);


ALTER TABLE public.sitz OWNER TO postgres;

--
-- Name: sitz_sitzid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sitz_sitzid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sitz_sitzid_seq OWNER TO postgres;

--
-- Name: sitz_sitzid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sitz_sitzid_seq OWNED BY public.sitz.sitzid;


--
-- Name: vorfuehrung; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vorfuehrung (
    vid integer NOT NULL,
    fid integer,
    saalid integer,
    zeitpunkt timestamp without time zone
);


ALTER TABLE public.vorfuehrung OWNER TO postgres;

--
-- Name: vorfuehrung_vid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vorfuehrung_vid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vorfuehrung_vid_seq OWNER TO postgres;

--
-- Name: vorfuehrung_vid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vorfuehrung_vid_seq OWNED BY public.vorfuehrung.vid;


--
-- Name: zahlungsmethode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zahlungsmethode (
    zid integer NOT NULL,
    methode character varying(20)
);


ALTER TABLE public.zahlungsmethode OWNER TO postgres;

--
-- Name: zahlungsmethode_zid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zahlungsmethode_zid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zahlungsmethode_zid_seq OWNER TO postgres;

--
-- Name: zahlungsmethode_zid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zahlungsmethode_zid_seq OWNED BY public.zahlungsmethode.zid;


--
-- Name: buchung bid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buchung ALTER COLUMN bid SET DEFAULT nextval('public.buchung_bid_seq'::regclass);


--
-- Name: film fid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film ALTER COLUMN fid SET DEFAULT nextval('public.film_fid_seq'::regclass);


--
-- Name: kino kinoid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kino ALTER COLUMN kinoid SET DEFAULT nextval('public.kino_kinoid_seq'::regclass);


--
-- Name: kinosaal saalid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kinosaal ALTER COLUMN saalid SET DEFAULT nextval('public.kinosaal_saalid_seq'::regclass);


--
-- Name: nutzer nid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nutzer ALTER COLUMN nid SET DEFAULT nextval('public.nutzer_nid_seq'::regclass);


--
-- Name: sitz sitzid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sitz ALTER COLUMN sitzid SET DEFAULT nextval('public.sitz_sitzid_seq'::regclass);


--
-- Name: vorfuehrung vid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vorfuehrung ALTER COLUMN vid SET DEFAULT nextval('public.vorfuehrung_vid_seq'::regclass);


--
-- Name: zahlungsmethode zid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zahlungsmethode ALTER COLUMN zid SET DEFAULT nextval('public.zahlungsmethode_zid_seq'::regclass);


--
-- Data for Name: buchung; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buchung (bid, nid, vid) FROM stdin;
\.


--
-- Data for Name: film; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.film (fid, titel, beschreibung, bewertung, preis, fsk, dauer, dreid) FROM stdin;
\.


--
-- Data for Name: kino; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kino (kinoid, name, ort) FROM stdin;
\.


--
-- Data for Name: kino_spielt_filme; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kino_spielt_filme (kinoid, fid) FROM stdin;
\.


--
-- Data for Name: kinosaal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kinosaal (saalid, kinoid, barrierefrei) FROM stdin;
\.


--
-- Data for Name: kunden_haben_zahlungsmethoden; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kunden_haben_zahlungsmethoden (nid, zid) FROM stdin;
\.


--
-- Data for Name: nutzer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nutzer (nid, vorname, nachname, geburtstag, email, passwort) FROM stdin;
\.


--
-- Data for Name: platzbelegung; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.platzbelegung (vid, reihe, sitzplatz, frei) FROM stdin;
\.


--
-- Data for Name: sitz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sitz (sitzid, saalid, reihe, sitzplatz, loge) FROM stdin;
\.


--
-- Data for Name: vorfuehrung; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vorfuehrung (vid, fid, saalid, zeitpunkt) FROM stdin;
\.


--
-- Data for Name: zahlungsmethode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.zahlungsmethode (zid, methode) FROM stdin;
\.


--
-- Name: buchung_bid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buchung_bid_seq', 1, false);


--
-- Name: film_fid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.film_fid_seq', 1, false);


--
-- Name: kino_kinoid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kino_kinoid_seq', 1, false);


--
-- Name: kinosaal_saalid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kinosaal_saalid_seq', 1, false);


--
-- Name: nutzer_nid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nutzer_nid_seq', 1, false);


--
-- Name: sitz_sitzid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sitz_sitzid_seq', 1, false);


--
-- Name: vorfuehrung_vid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vorfuehrung_vid_seq', 1, false);


--
-- Name: zahlungsmethode_zid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zahlungsmethode_zid_seq', 1, false);


--
-- Name: buchung buchung_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buchung
    ADD CONSTRAINT buchung_pkey PRIMARY KEY (bid);


--
-- Name: film film_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (fid);


--
-- Name: kino kino_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kino
    ADD CONSTRAINT kino_pkey PRIMARY KEY (kinoid);


--
-- Name: kinosaal kinosaal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kinosaal
    ADD CONSTRAINT kinosaal_pkey PRIMARY KEY (saalid);


--
-- Name: nutzer nutzer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nutzer
    ADD CONSTRAINT nutzer_pkey PRIMARY KEY (nid);


--
-- Name: sitz sitz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sitz
    ADD CONSTRAINT sitz_pkey PRIMARY KEY (sitzid);


--
-- Name: vorfuehrung vorfuehrung_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vorfuehrung
    ADD CONSTRAINT vorfuehrung_pkey PRIMARY KEY (vid);


--
-- Name: zahlungsmethode zahlungsmethode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zahlungsmethode
    ADD CONSTRAINT zahlungsmethode_pkey PRIMARY KEY (zid);


--
-- Name: buchung buchung_nid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buchung
    ADD CONSTRAINT buchung_nid_fkey FOREIGN KEY (nid) REFERENCES public.nutzer(nid);


--
-- Name: buchung buchung_vid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buchung
    ADD CONSTRAINT buchung_vid_fkey FOREIGN KEY (vid) REFERENCES public.vorfuehrung(vid);


--
-- Name: kino_spielt_filme kino_spielt_filme_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kino_spielt_filme
    ADD CONSTRAINT kino_spielt_filme_fid_fkey FOREIGN KEY (fid) REFERENCES public.film(fid);


--
-- Name: kino_spielt_filme kino_spielt_filme_kinoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kino_spielt_filme
    ADD CONSTRAINT kino_spielt_filme_kinoid_fkey FOREIGN KEY (kinoid) REFERENCES public.kino(kinoid);


--
-- Name: kinosaal kinosaal_kinoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kinosaal
    ADD CONSTRAINT kinosaal_kinoid_fkey FOREIGN KEY (kinoid) REFERENCES public.kino(kinoid);


--
-- Name: kunden_haben_zahlungsmethoden kunden_haben_zahlungsmethoden_nid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kunden_haben_zahlungsmethoden
    ADD CONSTRAINT kunden_haben_zahlungsmethoden_nid_fkey FOREIGN KEY (nid) REFERENCES public.nutzer(nid);


--
-- Name: kunden_haben_zahlungsmethoden kunden_haben_zahlungsmethoden_zid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kunden_haben_zahlungsmethoden
    ADD CONSTRAINT kunden_haben_zahlungsmethoden_zid_fkey FOREIGN KEY (zid) REFERENCES public.zahlungsmethode(zid);


--
-- Name: platzbelegung platzbelegung_vid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.platzbelegung
    ADD CONSTRAINT platzbelegung_vid_fkey FOREIGN KEY (vid) REFERENCES public.vorfuehrung(vid);


--
-- Name: sitz sitz_saalid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sitz
    ADD CONSTRAINT sitz_saalid_fkey FOREIGN KEY (saalid) REFERENCES public.kinosaal(saalid);


--
-- Name: vorfuehrung vorfuehrung_fid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vorfuehrung
    ADD CONSTRAINT vorfuehrung_fid_fkey FOREIGN KEY (fid) REFERENCES public.film(fid);


--
-- Name: vorfuehrung vorfuehrung_saalid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vorfuehrung
    ADD CONSTRAINT vorfuehrung_saalid_fkey FOREIGN KEY (saalid) REFERENCES public.kinosaal(saalid);


--
-- PostgreSQL database dump complete
--

