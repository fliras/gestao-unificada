DROP SCHEMA IF EXISTS GESTAODB;
CREATE SCHEMA IF NOT EXISTS GESTAODB;
SET SCHEMA GESTAODB;

CREATE TABLE IF NOT EXISTS perfis_acesso (
  id_perfil_acesso INT NOT NULL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  descricao VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  hash_senha CHAR(60) NOT NULL,
  id_perfil_acesso INT NOT NULL,
  CONSTRAINT fk_usuarios_perfis FOREIGN KEY (id_perfil_acesso) REFERENCES perfis_acesso(id_perfil_acesso)
);

CREATE TABLE IF NOT EXISTS cargos (
  id_cargo INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS cargos_internos (
  id_cargo_interno INT NOT NULL PRIMARY KEY,
  salario_base DOUBLE NOT NULL,
  CONSTRAINT fk_cargos_internos_cargos FOREIGN KEY (id_cargo_interno) REFERENCES cargos(id_cargo)
);

CREATE TABLE IF NOT EXISTS departamentos (
  id_departamento INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionarios (
  matricula VARCHAR(10) NOT NULL PRIMARY KEY,
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  id_usuario INT NOT NULL,
  CONSTRAINT fk_funcionarios_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS funcionarios_internos (
  matricula VARCHAR(10) NOT NULL PRIMARY KEY,
  data_nascimento DATE NOT NULL,
  CONSTRAINT fk_funcionarios_internos_funcionarios FOREIGN KEY (matricula) REFERENCES funcionarios(matricula)
);

CREATE TABLE IF NOT EXISTS contratos_internos (
  id_contrato_interno INT AUTO_INCREMENT PRIMARY KEY,
  id_funcionario_interno VARCHAR(10) NOT NULL,
  id_cargo_interno INT NOT NULL,
  id_departamento INT NOT NULL,
  data_inicio DATE NOT NULL,
  date_fim DATE NOT NULL,
  CONSTRAINT fk_contratos_funcionario_interno FOREIGN KEY (id_funcionario_interno) REFERENCES funcionarios_internos(matricula),
  CONSTRAINT fk_contratos_cargo_interno FOREIGN KEY (id_cargo_interno) REFERENCES cargos_internos(id_cargo_interno),
  CONSTRAINT fk_contratos_departamento FOREIGN KEY (id_departamento) REFERENCES departamentos(id_departamento)
);
