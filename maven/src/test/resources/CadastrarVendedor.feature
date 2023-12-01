# language: pt

Funcionalidade: Cadastro de Vendedor

    Cenário: Consultar a lista de vendedores
        Dado que o sistema possui vendedores cadastrados
        Quando a lista de vendedores é consultada
        Então o sistema deve retornar uma lista de vendedores
        E a lista deve conter as informações corretas de cada vendedor

    Cenário: Cadastrar vendedor com CNPJ inválido
        Dado que o sistema está configurado para o cadastro de vendedores
        Quando um novo vendedor é cadastrado com CNPJ inválido
        Então o sistema deve indicar o erro "Falha ao cadastrar vendedor. CNPJ inválido."
        E nenhum vendedor deve ser salvo no banco de dados