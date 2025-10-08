SET SCHEMA GESTAODB;

INSERT INTO perfis_acesso(id_perfil_acesso, nome, descricao) VALUES
(1, 'ADMIN', 'Administrador do sistema'),
(2, 'USER', 'Usuário comum');

INSERT INTO cargos(nome) VALUES
('Desenvolvedor'),
('Analista');

INSERT INTO cargos_internos(id_cargo_interno, salario_base) VALUES
(1, 5000),
(2, 4000);

INSERT INTO departamentos(nome) VALUES
('TI'),
('RH');

INSERT INTO projetos_internos(nome) VALUES
('Projeto Alpha'),
('Projeto Beta');