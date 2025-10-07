SET SCHEMA GESTAODB;

INSERT INTO perfis_acesso (id_perfil_acesso, nome, descricao) VALUES
(1, 'ADMIN', 'Administrador'),
(2, 'USER', 'Usuário padrão');

INSERT INTO cargos (nome) VALUES ('Desenvolvedor'), ('Analista'), ('Gerente');
-- Captura do id_cargo autoincrement no H2: para simplificar vou inserir cargos_internos com ids correspondentes 1,2,3
INSERT INTO cargos_internos (id_cargo_interno, salario_base) VALUES
(1, 5000.00),
(2, 3500.00),
(3, 9000.00);

INSERT INTO departamentos (nome) VALUES ('TI'), ('RH'), ('Financeiro');
