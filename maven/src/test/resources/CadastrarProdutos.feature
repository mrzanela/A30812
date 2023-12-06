# language: pt
Funcionalidade: Cadastro de Produto

  Cenário: Cadastrar um novo produto com sucesso
    Dado que o sistema está configurado para o cadastro de produtos
    Quando um novo produto é cadastrado 
    Então o produto deve ser salvo no banco de dados
    E o sistema deve exibir a mensagem "Produto cadastrado com sucesso"

  Cenário: Falha ao cadastrar um produto com informações inválidas
    Dado que o sistema está configurado para o cadastro de produtos
    Quando um novo produto com nome em branco, preço negativo e quantidade negativa é cadastrado
    Então o sistema deve exibir a mensagem de erro "Falha ao cadastrar produto. Verifique as informações fornecidas."
    E nenhum produto deve ser salvo no banco de dados