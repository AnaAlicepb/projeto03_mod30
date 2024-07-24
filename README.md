<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema de Vendas desenvolvido no módulo 30">
    <meta name="keywords" content="Java, PostgreSQL, JUnit, Eclipse">
    <meta name="author" content="Ana Alice Rodrigues">
  </head>
<body>

<header>
    <h1>Projeto VendasModulo30</h1>
  
</header>

<details>
    <summary>Índice</summary>
    <ol>
        <li><a href="#sobre-o-projeto">Sobre o projeto</a></li>
        <li><a href="#parte-tecnica">Parte Técnica</a></li>
        <li><a href="#estrutura-dos-arquivos">Estrutura dos Arquivos</a></li>
        <li><a href="#execucao-dos-testes">Execução dos Testes</a></li>
        <li><a href="#banco-de-dados">Banco de Dados</a></li>
        <li><a href="#ferramentas">Ferramentas</a></li>
        <li><a href="#contato">Contato</a></li>
    </ol>
</details>

<section id="sobre-o-projeto">
    <h2>Sobre o projeto</h2>
    <p>
        Este projeto é um sistema de vendas desenvolvido como parte do módulo 30 do curso. Ele inclui funcionalidades para gerenciar clientes, produtos e vendas. Utiliza Java, com o framework JUnit para testes unitários e PostgreSQL como banco de dados.
    </p>
    <p>
        Proposta de Valor: Oferecer uma experiência de compra conveniente, com uma seleção diversificada de produtos, facilidade de navegação e processos de compra e entrega eficientes.
    </p>
</section>

<section id="parte-tecnica">
    <h2>Parte Técnica</h2>
    <ul>
        <li><strong>Backend:</strong> Java é utilizado para a lógica do servidor, gerenciamento de sessões, autenticação de usuários e manipulação do carrinho de compras.</li>
        <li><strong>Banco de Dados:</strong> PostgreSQL para armazenar dados de usuários, produtos, pedidos e detalhes de pagamento.</li>
    </ul>
</section>

<section id="estrutura-dos-arquivos">
    <h2>Estrutura dos Arquivos</h2>
    <pre>
VendasModulo30
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── annotation
│   │   │   ├── dao
│   │   │   ├── dao.factory
│   │   │   ├── dao.generic
│   │   │   ├── dao.generic.jdbc
│   │   │   ├── domain
│   │   │   ├── exceptions
│   │   │   └── services
│   └── test
│       ├── java
│           ├── test
│           └── test.dao
├── JRE System Library [jdk-17.0.11]
├── Referenced Libraries
└── JUnit 4
    </pre>
    <h3>Descrição das Pastas</h3>
    <ul>
        <li><strong>annotation</strong>: Contém as anotações utilizadas no projeto.</li>
        <li><strong>dao</strong>: Contém as classes de acesso a dados.</li>
        <li><strong>dao.factory</strong>: Contém as classes de fábrica para criação de objetos DAO.</li>
        <li><strong>dao.generic</strong>: Contém classes genéricas para DAO.</li>
        <li><strong>dao.generic.jdbc</strong>: Contém classes de configuração JDBC.</li>
        <li><strong>domain</strong>: Contém as classes de domínio (entidades).</li>
        <li><strong>exceptions</strong>: Contém as classes de exceções personalizadas.</li>
        <li><strong>services</strong>: Contém as classes de serviço que encapsulam a lógica de negócios.</li>
        <li><strong>test</strong>: Contém os testes unitários.</li>
    </ul>
</section>

<section id="execucao-dos-testes">
    <h2>Execução dos Testes</h2>
    <ol>
        <li><strong>Abrir o Eclipse</strong>: Abra o projeto no Eclipse IDE.</li>
        <li><strong>Executar Testes</strong>: Clique com o botão direito no arquivo <code>AllTests.java</code> e selecione <code>Run As &gt; JUnit Test</code>.</li>
    </ol>
</section>

<section id="banco-de-dados">
    <h2>Banco de Dados</h2>
    <h3>Tabelas</h3>
    <h4>tb_produto</h4>
    <table>
        <thead>
            <tr>
                <th>Coluna</th>
                <th>Tipo</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>id</td>
                <td>BIGINT</td>
            </tr>
            <tr>
                <td>codigo</td>
                <td>VARCHAR(255)</td>
            </tr>
            <tr>
                <td>nome</td>
                <td>VARCHAR(255)</td>
            </tr>
            <tr>
                <td>descricao</td>
                <td>TEXT</td>
            </tr>
            <tr>
                <td>valor</td>
                <td>DECIMAL</td>
            </tr>
            <tr>
                <td>quantidade_estoque</td>
                <td>INT</td>
            </tr>
            <tr>
                <td>data_criacao</td>
                <td>TIMESTAMP</td>
            </tr>
            <tr>
                <td>data_atualizacao</td>
                <td>TIMESTAMP</td>
            </tr>
        </tbody>
    </table>
    <h4>tb_produto_quantidade</h4>
    <table>
        <thead>
            <tr>
                <th>Coluna</th>
                <th>Tipo</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>id</td>
                <td>BIGINT</td>
            </tr>
            <tr>
                <td>id_produto_fk</td>
                <td>BIGINT</td>
            </tr>
            <tr>
                <td>id_venda_fk</td>
                <td>BIGINT</td>
            </tr>
            <tr>
                <td>quantidade</td>
                <td>INT</td>
            </tr>
            <tr>
                <td>valor_total</td>
                <td>DECIMAL</td>
            </tr>
        </tbody>
    </table>
    <h3>Sequences</h3>
    <ul>
        <li>sq_cliente</li>
        <li>sq_produto</li>
        <li>sq_produto_quantidade</li>
        <li>sq_venda</li>
    </ul>
</section>

<section id="ferramentas">
    <h2>Ferramentas</h2>
    <ul>
        <li><img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white" alt="Badge Java"></li>
        <li><img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="Badge JUnit"></li>
        <li><img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="Badge PostgreSQL"></li>
        <li><img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white" alt="Badge Eclipse"></li>
    </ul>
</section>

<section id="contato">
    <h2>Contato</h2>
    <ul>
        <li><a href="https://linktr.ee/anaeanali5" target="_blank"><img src="https://img.shields.io/badge/Ana_Alice_Rodrigues-blue?style=for-the-badge" alt="Perfil de Ana Alice Rodrigues"></a></li> 
    </ul>
    
</section>

</body>
</html>
