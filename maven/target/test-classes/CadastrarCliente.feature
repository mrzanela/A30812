# language: pt

Funcionalidade: Cadastro de novo cliente

  Cenário: Cadastrar novo cliente com sucesso
    Dado que "Fulano" está no sistema com suas credenciais
    Quando "Fulano" cadastra um novo cliente
    Então o banco de dados deve salvar o registro do novo cliente
    
  Cenário: Tentar cadastrar um cliente com informações inválidas
    Dado que "Beltrano" está no sistema com suas credenciais
    Quando "Beltrano" tenta cadastrar um novo cliente com informações inválidas
    Então o sistema deve exibir uma mensagem de erro
  