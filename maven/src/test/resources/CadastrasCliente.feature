# language: pt

Funcionalidade: Cadastro de Cliente

    Cenário: Consultar a lista de clientes
        Dado que o sistema tem clientes cadastrados
        Quando a lista de clientes é verificada
        Então o sistema deve mostrar uma lista de clientes
        E a lista deve conter os dados corretos de cada cliente

    Cenário: Cadastrar cliente com CPF inválido
        Dado que o sistema possui clientes cadastrados
        Quando um cliente é registrado com CPF inválido
        Então o sistema deve mostrar o erro "Falha ao cadastrar cliente. CPF inválido."
        E nenhum cliente deve ser salvo.