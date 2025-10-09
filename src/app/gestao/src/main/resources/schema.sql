DROP SCHEMA IF EXISTS GESTAODB;
CREATE SCHEMA IF NOT EXISTS GESTAODB;
SET SCHEMA GESTAODB;
-- =======================================================
-- SCHEMA.SQL - H2 Adaptado de MySQL Workbench Script
-- =======================================================

-- Desabilita temporariamente constraints
SET REFERENTIAL_INTEGRITY FALSE;

-- -------------------------------------------------------
-- TABELAS BÁSICAS
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS perfis_acesso (
  id_perfil_acesso INT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  hash_senha CHAR(60) NOT NULL,
  id_perfil_acesso INT NOT NULL,
  CONSTRAINT fk_usuarios_perfis_acesso FOREIGN KEY (id_perfil_acesso)
      REFERENCES perfis_acesso (id_perfil_acesso)
);

CREATE TABLE IF NOT EXISTS funcionarios (
  matricula VARCHAR(10) PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  id_usuario INT NOT NULL,
  CONSTRAINT fk_funcionarios_usuarios FOREIGN KEY (id_usuario)
      REFERENCES usuarios (id_usuario)
);

CREATE TABLE IF NOT EXISTS cargos (
  id_cargo INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS departamentos (
  id_departamento INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS cargos_internos (
  id_cargo_interno INT PRIMARY KEY,
  salario_base FLOAT NOT NULL,
  CONSTRAINT fk_cargos_internos_cargos FOREIGN KEY (id_cargo_interno)
      REFERENCES cargos (id_cargo)
);

CREATE TABLE IF NOT EXISTS funcionarios_internos (
  matricula VARCHAR(10) PRIMARY KEY,
  data_nascimento DATE NOT NULL,
  CONSTRAINT fk_funcionarios_internos_funcionarios FOREIGN KEY (matricula)
      REFERENCES funcionarios (matricula)
);

CREATE TABLE IF NOT EXISTS contratos_internos (
  id_contrato_interno INT AUTO_INCREMENT PRIMARY KEY,
  id_funcionario_interno VARCHAR(10) NOT NULL,
  id_cargo_interno INT NOT NULL,
  id_departamento INT NOT NULL,
  data_inicio DATE NOT NULL,
  date_fim DATE NOT NULL,
  CONSTRAINT fk_contratos_internos_funcionarios FOREIGN KEY (id_funcionario_interno)
      REFERENCES funcionarios_internos (matricula),
  CONSTRAINT fk_contratos_internos_cargos FOREIGN KEY (id_cargo_interno)
      REFERENCES cargos_internos (id_cargo_interno),
  CONSTRAINT fk_contratos_internos_departamentos FOREIGN KEY (id_departamento)
      REFERENCES departamentos (id_departamento)
);

-- -------------------------------------------------------
-- EMPRESAS E FUNCIONÁRIOS TERCEIROS
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS empresas_terceiras (
  id_empresa_terceira INT AUTO_INCREMENT PRIMARY KEY,
  razao_social VARCHAR(50) NOT NULL,
  cnpj VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionarios_terceiros (
  id_funcionario_terceiro INT PRIMARY KEY,
  id_empresa_terceira INT NOT NULL,
  CONSTRAINT fk_funcionarios_terceiros_empresas FOREIGN KEY (id_empresa_terceira)
      REFERENCES empresas_terceiras (id_empresa_terceira)
);

CREATE TABLE IF NOT EXISTS contratos_terceirizados (
  id_contratos_terceirizados INT AUTO_INCREMENT PRIMARY KEY,
  id_funcionario_terceiro INT NOT NULL,
  id_departamento INT NOT NULL,
  id_cargo INT NOT NULL,
  id_responsavel_interno VARCHAR(10) NOT NULL,
  data_inicio DATE NOT NULL,
  data_fim DATE NOT NULL,
  CONSTRAINT fk_contratos_terceirizados_funcionarios FOREIGN KEY (id_funcionario_terceiro)
      REFERENCES funcionarios_terceiros (id_funcionario_terceiro),
  CONSTRAINT fk_contratos_terceirizados_departamentos FOREIGN KEY (id_departamento)
      REFERENCES departamentos (id_departamento),
  CONSTRAINT fk_contratos_terceirizados_cargos FOREIGN KEY (id_cargo)
      REFERENCES cargos (id_cargo),
  CONSTRAINT fk_contratos_terceirizados_internos FOREIGN KEY (id_responsavel_interno)
      REFERENCES funcionarios_internos (matricula)
);

-- -------------------------------------------------------
-- VISITANTES E VISITAS
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS visitantes (
  id_visitante INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  documento VARCHAR(20) NOT NULL,
  numero_cracha VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS visitas (
  id_visita INT AUTO_INCREMENT PRIMARY KEY,
  motivo VARCHAR(50) NOT NULL,
  timestamp_entrada TIMESTAMP NOT NULL,
  timestamp_saida TIMESTAMP
);

CREATE TABLE IF NOT EXISTS visitas_visitantes (
  id_visitante INT NOT NULL,
  id_visita INT NOT NULL,
  PRIMARY KEY (id_visitante, id_visita),
  CONSTRAINT fk_visitas_visitantes_visitantes FOREIGN KEY (id_visitante)
      REFERENCES visitantes (id_visitante),
  CONSTRAINT fk_visitas_visitantes_visitas FOREIGN KEY (id_visita)
      REFERENCES visitas (id_visita)
);

CREATE TABLE IF NOT EXISTS visitas_funcionarios (
  id_visita INT NOT NULL,
  id_funcionario VARCHAR(10) NOT NULL,
  PRIMARY KEY (id_visita, id_funcionario),
  CONSTRAINT fk_visitas_funcionarios_visitas FOREIGN KEY (id_visita)
      REFERENCES visitas (id_visita),
  CONSTRAINT fk_visitas_funcionarios_funcionarios FOREIGN KEY (id_funcionario)
      REFERENCES funcionarios_internos (matricula)
);

CREATE TABLE IF NOT EXISTS visitas_departamentos (
  id_visita INT NOT NULL,
  id_departamento INT NOT NULL,
  PRIMARY KEY (id_visita, id_departamento),
  CONSTRAINT fk_visitas_departamentos_visitas FOREIGN KEY (id_visita)
      REFERENCES visitas (id_visita),
  CONSTRAINT fk_visitas_departamentos_departamentos FOREIGN KEY (id_departamento)
      REFERENCES departamentos (id_departamento)
);

-- -------------------------------------------------------
-- PROJETOS INTERNOS
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS projetos_internos (
  id_projeto INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS projetos_internos_funcionarios (
  id_funcionario_interno VARCHAR(10) NOT NULL,
  id_projeto_interno INT NOT NULL,
  data_inicio DATE,
  data_fim DATE,
  PRIMARY KEY (id_funcionario_interno, id_projeto_interno),
  CONSTRAINT fk_proj_func_interno FOREIGN KEY (id_funcionario_interno)
      REFERENCES funcionarios_internos (matricula),
  CONSTRAINT fk_proj_func_projeto FOREIGN KEY (id_projeto_interno)
      REFERENCES projetos_internos (id_projeto)
);

-- -------------------------------------------------------
-- REGISTROS DE PONTO
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS registros_de_ponto (
  id_registro_de_ponto INT AUTO_INCREMENT PRIMARY KEY,
  data DATE NOT NULL,
  horario_entrada TIME,
  horario_saida TIME,
  matricula_funcionario VARCHAR(10) NOT NULL,
  CONSTRAINT fk_registros_funcionario FOREIGN KEY (matricula_funcionario)
      REFERENCES funcionarios (matricula)
);

-- -------------------------------------------------------
-- ACESSOS E PERFIS
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS acessos (
  id_acesso INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS acessos_perfis (
  id_acesso INT NOT NULL,
  id_perfil_acesso INT NOT NULL,
  PRIMARY KEY (id_acesso, id_perfil_acesso),
  CONSTRAINT fk_acessos_perfis_acesso FOREIGN KEY (id_acesso)
      REFERENCES acessos (id_acesso),
  CONSTRAINT fk_acessos_perfis_perfil FOREIGN KEY (id_perfil_acesso)
      REFERENCES perfis_acesso (id_perfil_acesso)
);

-- Reativa constraints
SET REFERENTIAL_INTEGRITY TRUE;
