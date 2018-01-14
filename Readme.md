# TEST-BACKEND

A aplicação consiste em um sistema Web que visa resolver o exercício: **Teste para BackEnd para UOL HOST**, que propôs a seguinte arquitetura como modelo para implementação do desafio:

![enter image description here](https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/arquitetura.png)

O aplicativo foi criado utilizando diversos recursos do Java EE para demonstração. Este sistema é constituído por quatro projetos (pom, ear, ejb e web). Abaixo segue a descrição de cada um deles respectivamente:

- **test-backend**: projeto **pom**, responsável por organizar todos os outros projetos, dependências comuns e versionamento de APIs utilizadas também nos outros projetos.
- **test-backend-ear**: projeto **ear**, responsável por empacotar o projeto em um arquivo *.ear (Enterprise Application aRchive), que irá compor a aplicação e que será depositado no servidor de aplicação para que a aplicação possa ser executada.
- **test-backend-ejb**: projeto **ejb**, responsável por concentra todas as regras de negócio estabelecidas para as entidades.
- **test-backend-web**: projeto **web**, contém a camada de controle e visualização da aplicação.

# Requisitos da aplicação
- Wildfly 10.1.0.Final ou superior.
- HSQLDB (HyperSQL DataBase) 2.4.0 ou superior.

# Configurando a aplicação

Primeiro devemos configurar os arquivos necessários para que o Wildfly execute a aplicação normalmente. Antes de tudo, devemos ajustar os arquivos necessários para que o HSQLDB funcione corretamente com o Wildfly, para isto, devemos ter em posse o driver deste banco de dados.

No diretório do Wildfly **`modules\system\layers\base\org`** deverá ser criado o diretório **`hsqldb`** e um subdiretório **`main`** ficando:
> \modules\system\layers\base\org\hsqldb\main

No diretório *main*, devemos depositar o arquivo **`*.jar`** correspondente ao driver do *HSQLDB* na versão 2.4.0 ou superior e o seu respectivo arquivo `module.xml`.

> O diretório *main* deverá possuir os seguintes arquivos:
> - module.xml
> - driver-name.jar (**driver-name** deve ser alterado)

## Conteúdo do arquivo *module.xml*

Crie um arquivo XML vazio e adicione o conteúdo exibido abaixo:

    <?xml version="1.0" encoding="UTF-8"?>
    <module xmlns="urn:jboss:module:1.1" name="org.hsqldb">
        <resources>
            <resource-root path="driver-name.jar"/>
        </resources>
        <dependencies>
            <module name="javax.api"/>
            <module name="javax.transaction.api"/>
        </dependencies>
    </module>

> No conteúdo: **`resource-root path="driver-name.jar`** o valor de **`driver-name`** deve possuir o nome do driver do banco de dados HSQLDB.

## Configurando o arquivo: *standalone.xml* ou *standalone-full.xml*

No elemento <**datasources**> deve ser adicionado o seguinte **datasource**:

    <datasource jndi-name="java:jboss/datasources/test_backend_java" pool-name="test_backend_java" enabled="true">
        <connection-url>jdbc:hsqldb:file:caminho_banco/nome_banco</connection-url>
        <driver>hsqldb</driver>
        <pool>
            <min-pool-size>10</min-pool-size>
            <max-pool-size>100</max-pool-size>
        </pool>
        <security>
            <user-name>seu_usuario</user-name>
            <password>sua_senha</password>
        </security>
        <validation>
            <validate-on-match>false</validate-on-match>
            <background-validation>false</background-validation>
        </validation>
        <statement>
            <prepared-statement-cache-size>0</prepared-statement-cache-size>
            <share-prepared-statements>false</share-prepared-statements>
        </statement>
    </datasource>

>  Na declaração **`jdbc:hsqldb:file:caminho_banco/nome_banco`** no elemento **`connection-url`** o valor de *caminho_banco* e *nome_banco* devem ser alterados para o caminho onde está localizado os arquivos do banco de dados HSQLDB. Para criação do arquivos do HSQLDB segue um ótimo link contendo todos o passo a passo: https://www.tutorialspoint.com/hsqldb/hsqldb_installation.htm

> Na declaração <**user-name**> e <**password**> devem possuir respectivamente o usuário e senha que foi configurado ao criar os arquivos do banco de dados HSQLDB.

No elemento <**drivers**> deve ser adicionado o seguinte **driver**:

    <drivers>
        <driver name="hsqldb" module="org.hsqldb">
            <xa-datasource-class>org.hsqldb.jdbc.JDBCDataSource</xa-datasource-class>
        </driver>
    </drivers>

# Executando a aplicação

Existem duas maneiras de executar este sistema:
- IDE: através de um ambiente para desenvolvimento em Java é possível importar todos os projetos maven, gerar seus pacotes e importar o arquivo *.ear para o servidor de aplicação (exemplo: Wildfly). Este aplicativo foi desenvolvido utilizando o **Eclipse**.
- Gerando o pacote *.ear executando os comandos `clear package` com o maven. Após a geração, no exemplo do Wildfly, basta inserir o pacote gerado no diretório `standalone\deployments` e executar o servidor de aplicação.

O context-root deste aplicativo é `/test-backend`. Para acessar o sistema, por exemplo, localmente em seu computador onde o mesmo está sendo executado em um servidor de aplicação (Wildfly), basta inserir no navegador a seguinte URL
> http://localhost:8080/test-backend

Onde `localhost` é o endereço de IP e `8080` é a porta onde o servidor de aplicação está sendo executado.