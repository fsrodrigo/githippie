CREATE TABLE public.usuarios
(
  id bigint NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
  nome_usuario character varying,
  sobre_nome_usuario character varying,
  email_usuario character varying,
  url_linkedin character varying,
  sexo character(1),
  plano character varying,
  data_cadastro timestamp without time zone,
  ativo boolean NOT NULL DEFAULT true,
  CONSTRAINT pk_id_usuario PRIMARY KEY (id)
);

CREATE TABLE public.organizacoes
(
  id bigint NOT NULL DEFAULT nextval('organizacoes_id_seq'::regclass),
  nome_organizacao character varying,
  descricao_organizacao character varying,
  data_criacao timestamp without time zone,
  usuario_owner bigint NOT NULL,
  ativo boolean NOT NULL DEFAULT true,
  CONSTRAINT pk_id_organizacao PRIMARY KEY (id),
  CONSTRAINT fk_usuario_owner FOREIGN KEY (usuario_owner)
      REFERENCES public.usuarios (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.usuarios_organizacao
(
  id bigint NOT NULL DEFAULT nextval('usuarios_organizacao_id_seq'::regclass),
  id_usuario bigint,
  id_organizacao bigint,
  adm boolean,
  CONSTRAINT pk_id_user_org PRIMARY KEY (id),
  CONSTRAINT fk_organizacao_user_org FOREIGN KEY (id_organizacao)
      REFERENCES public.organizacoes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario_user_org FOREIGN KEY (id_usuario)
      REFERENCES public.usuarios (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE public.repositorios
(
  id bigint NOT NULL DEFAULT nextval('repositorios_id_seq'::regclass),
  nome_repositorio character varying,
  descricao_repositorio character varying,
  data_criacao timestamp without time zone,
  tipo_repositorio character varying,
  usuario_owner bigint
);

CREATE TABLE public.usuarios_repositorios
(
  id bigint NOT NULL DEFAULT nextval('usuarios_repositorios_id_seq'::regclass),
  id_usuario bigint,
  id_repositorio bigint
);

ALTER TABLE usuarios 
   ADD CONSTRAINT pk_id_usuario PRIMARY KEY (id);

ALTER TABLE organizacoes 
   ADD CONSTRAINT fk_usuario_owner FOREIGN KEY (usuario_owner) REFERENCES usuarios (id);

ALTER TABLE organizacoes 
   ADD CONSTRAINT pk_id_organizacao PRIMARY KEY (id);


ALTER TABLE usuarios_organizacao 
   ADD CONSTRAINT pk_id_user_org PRIMARY KEY (id);

ALTER TABLE usuarios_organizacao 
   ADD CONSTRAINT fk_usuario_user_org FOREIGN KEY (id_usuario) REFERENCES usuarios (id);

ALTER TABLE usuarios_organizacao 
   ADD CONSTRAINT fk_organizacao_user_org FOREIGN KEY (id_organizacao) REFERENCES organizacoes (id);
   
ALTER TABLE repositorios 
   ADD CONSTRAINT pk_id_repositorios PRIMARY KEY (id);

ALTER TABLE repositorios 
   ADD CONSTRAINT fk_usuario_owner FOREIGN KEY (usuario_owner) REFERENCES usuarios (id);
   
ALTER TABLE usuarios_repositorios 
   ADD CONSTRAINT pk_id_usuarios_repositorios PRIMARY KEY (id);

ALTER TABLE usuarios_repositorios 
   ADD CONSTRAINT fk_usuario_user_repo FOREIGN KEY (id_usuario) REFERENCES usuarios (id);

ALTER TABLE usuarios_repositorios 
   ADD CONSTRAINT fk_repositorio_user_repo FOREIGN KEY (id_repositorio) REFERENCES repositorios (id);

   
   