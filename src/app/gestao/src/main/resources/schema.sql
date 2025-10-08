DROP SCHEMA IF EXISTS GESTAODB;
CREATE SCHEMA IF NOT EXISTS GESTAODB;
SET SCHEMA GESTAODB;

DROP TABLE IF EXISTS projetos_internos_funcionarios;
DROP TABLE IF EXISTS projetos_internos;
DROP TABLE IF EXISTS contratos_internos;
DROP TABLE IF EXISTS funcionarios_internos;
DROP TABLE IF EXISTS funcionarios;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS perfis_acesso;
DROP TABLE IF EXISTS cargos;
DROP TABLE IF EXISTS cargos_internos;
DROP TABLE IF EXISTS departamentos;

CREATE TABLE perfis_acesso (
  id_perfil_acesso INT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(50)
);

CREATE TABLE usuarios (
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  hash_senha CHAR(60) NOT NULL,
  id_perfil_acesso INT NOT NULL,
  CONSTRAINT fk_usuarios_perfis_acesso FOREIGN KEY (id_perfil_acesso) REFERENCES perfis_acesso(id_perfil_acesso)
);

CREATE TABLE funcionarios (
  matricula VARCHAR(10) PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  id_usuario INT NOT NULL,
  CONSTRAINT fk_funcionarios_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE funcionarios_internos (
  matricula VARCHAR(10) PRIMARY KEY,
  data_nascimento DATE NOT NULL,
  CONSTRAINT fk_funcionarios_internos_funcionarios FOREIGN KEY (matricula) REFERENCES funcionarios(matricula)
);

CREATE TABLE cargos (
  id_cargo INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE cargos_internos (
  id_cargo_interno INT PRIMARY KEY,
  salario_base FLOAT NOT NULL,
  CONSTRAINT fk_cargos_internos_cargos FOREIGN KEY (id_cargo_interno) REFERENCES cargos(id_cargo)
);

CREATE TABLE departamentos (
  id_departamento INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE contratos_internos (
  id_contrato_interno INT PRIMARY KEY AUTO_INCREMENT,
  id_funcionario_interno VARCHAR(10) NOT NULL,
  id_cargo_interno INT NOT NULL,
  id_departamento INT NOT NULL,
  data_inicio DATE NOT NULL,
  date_fim DATE NOT NULL,
  CONSTRAINT fk_contratos_internos_funcionarios FOREIGN KEY (id_funcionario_interno) REFERENCES funcionarios_internos(matricula),
  CONSTRAINT fk_contratos_internos_cargos FOREIGN KEY (id_cargo_interno) REFERENCES cargos_internos(id_cargo_interno),
  CONSTRAINT fk_contratos_internos_departamentos FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento)
);

CREATE TABLE projetos_internos (
  id_projeto INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE projetos_internos_funcionarios (
  id_funcionario_interno VARCHAR(10) NOT NULL,
  id_projeto_interno INT NOT NULL,
  data_inicio DATE,
  data_fim DATE,
  PRIMARY KEY (id_funcionario_interno, id_projeto_interno),
  CONSTRAINT fk_projetos_funcionarios FOREIGN KEY (id_funcionario_interno) REFERENCES funcionarios_internos(matricula),
  CONSTRAINT fk_projetos_internos FOREIGN KEY (id_projeto_interno) REFERENCES projetos_internos(id_projeto)
);