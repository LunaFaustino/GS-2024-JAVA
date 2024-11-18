# GS - 2024 - JAVA
## Repositório criado para realizar a entrega de Java Advanced para a Global Solution de 2024

LARISSA ARAÚJO GAMA ALVARENGA – 96496 - 2TDSPS <br>
LARISSA LOPES OLIVEIRA – 552628 - 2TDSPB <br>
LUNA FAUSTINO LIMA – 552473 - 2TDSPB

### Descrição

Criamos um sistema de cadastro de possíveis clientes, onde se coloca os pessoais, de contato, qual energia renovável é de seu interesse e a quantidade de metros quadrados. 
Assim será possível entrar em contato com um orçamento. <br>
Temos um endpoint (pensamentos) que fará conexão com nosso aplicativo mobile, onde o usuário conseguirá colocar insights e demonstrar seu interesse em energias renováveis especificas.

### Link do Deploy
https://gs-2024-java.onrender.com
(o site dá um shutdown no deploy depois de 20 minutos sem acesso, então a primeira vez que for acessar vai demorar mais para entrar por conta do "redeploy")

### Métodos POST

https://gs-2024-java.onrender.com/pessoas-fisicas

```json
{
    "nome": "João Silva",
    "email": "joao.silva@example.com",
    "telefone": "(11) 91234-5678",
    "cpf": "123.456.789-00",
    "energia": "solar",
    "metros": 100.0
}
```

https://gs-2024-java.onrender.com/pessoas-juridicas

```json
{
    "razaoSocial": "Empresa Exemplo LTDA",
    "email": "contato@exemplo.com",
    "telefone": "(11) 91234-5678",
    "cnpj": "12.345.678/0001-90",
    "energia": "solar",
    "metros": 500.0
}
```

https://gs-2024-java.onrender.com/pensamentos

```json
{
    "opiniao": "Eu acredito que a energia solar é o futuro e deve ser mais acessível a todos."
}
```

### Métodos PUT

https://gs-2024-java.onrender.com/pessoas-fisicas/{id}

https://gs-2024-java.onrender.com/pessoas-juridicas/{id}

https://gs-2024-java.onrender.com/pensamentos/{id}

### Métodos GET

https://gs-2024-java.onrender.com/pessoas-fisicas

https://gs-2024-java.onrender.com/pessoas-juridicas

https://gs-2024-java.onrender.com/pensamentos

OU

https://gs-2024-java.onrender.com/pessoas-fisicas/{id}

https://gs-2024-java.onrender.com/pessoas-juridicas/{id}

https://gs-2024-java.onrender.com/pensamentos/{id}

### Métodos Delete

https://gs-2024-java.onrender.com/pessoas-fisicas/{id}

https://gs-2024-java.onrender.com/pessoas-juridicas/{id}

https://gs-2024-java.onrender.com/pensamentos/{id}
