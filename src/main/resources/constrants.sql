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