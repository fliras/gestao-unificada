# 🏢 Sistema de Gestão de Pessoas Internas, Terceirizadas e Visitantes

## 📘 Visão Geral

O **Sistema de Gestão** é uma aplicação desenvolvida para realizar o **controle centralizado de todos os perfis de pessoas que circulam pela empresa**, incluindo **funcionários internos**, **terceirizados** e **visitantes**.  

Seu principal objetivo é **otimizar o controle interno, reforçar a segurança e facilitar a geração de relatórios gerenciais**, garantindo rastreabilidade e eficiência no gerenciamento de acessos, vínculos e atividades de cada colaborador.

---

## ⚙️ Tecnologias Utilizadas

O sistema foi construído utilizando **frameworks e ferramentas modernas do ecossistema Java**, buscando equilíbrio entre simplicidade, desempenho e facilidade de manutenção.

- **Java 17+**
- **Spring Boot / Spring MVC**
- **Thymeleaf** (para renderização de páginas dinâmicas)
- **H2 Database** (banco de dados em memória para ambiente de desenvolvimento)
- **JPA / Hibernate** (mapeamento objeto-relacional)
- **Bootstrap 5** (estilização responsiva da interface)
- **Maven** (gerenciador de dependências)
- **NetBeans IDE** (ambiente de desenvolvimento principal)

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- **Java JDK 17** ou superior instalado  
- **NetBeans IDE** com suporte a projetos Maven  
- **Conexão com a internet** (para baixar dependências do Maven)

---

### 🔧 Passos para Execução

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/sistema-gestao.git
2. Abra o projeto no NetBeans
  - Vá em File → Open Project
  - Selecione o diretório clonado
  - Aguarde o Maven baixar as dependências

3. Execute o projeto
- Clique com o botão direito no projeto → Run
- O sistema iniciará no endereço padrão, bastando abrir o link abaixo no navegador:
``` bash
http://localhost:8080
```

4. Se o projeto não iniciar automaticamente:
- Pode ser necessário ajustar o script de execução no NetBeans:
- Vá em Project Properties → Actions
- Em Run Project, altere o comando para:
```bash
mvnw spring-boot:run
```
- Salve e execute novamente.

---

🧰 Banco de Dados H2

O sistema utiliza o banco H2 em modo in-memory, o que significa que os dados são carregados a partir dos scripts schema.sql e data.sql a cada inicialização.

- Console do H2 (opcional):

```bash
http://localhost:8080/h2-console
```

- Configuração padrão:
```bash
JDBC URL: jdbc:h2:mem:gestaodb
Username: sa
Password: (vazio)
```
