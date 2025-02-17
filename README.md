# Visualizar relatório com JasperReports e MySQL

## Descrição
Esta aplicação Java conecta-se a um banco de dados MySQL (Workbench) e exibe um relatório com os dados extraídos. O JasperReports é utilizado para a formatação e exportação do relatório.

## Tecnologias Utilizadas
- **Java** (JDBC para conexão com o MySQL)
- **MySQL Workbench** (Banco de dados relacional)
- **JasperReports** (Visualizar relatório)
- **iReport Designer** (Opcional, para edição de templates de relatórios)

## Configuração do Ambiente
1. **Instale o MySQL Workbench** e crie o banco de dados com as tabelas necessárias.
2. **Configure o JDBC** para conectar ao banco de dados MySQL.
3. **Adicione as dependências do JasperReports** ao seu projeto (Maven ou .jar manualmente).

## Como Executar
1. Clone este repositório:
   ```sh
   git clone https://github.com/CarlosEduard0o/jasper-report.git
   ```
2. Configure as credenciais do banco de dados no arquivo `application.properties` ou diretamente no código JDBC.
3. Compile e execute a aplicação Java.
4. Um relatório será exibido com os dados extraídos da tabela em questão.

## Exemplo de Uso
```java
Connection conn = DriverManager.getConnection(url, user, password);
JasperPrint jasperPrint = JasperFillManager.fillReport("relatorio.jasper", new HashMap<>(), conn);
JasperExportManager.exportReportToPdfFile(jasperPrint, "relatorio.pdf");
```
