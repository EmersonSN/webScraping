# WebScraping com Java, Selenium e Apache POI

Este projeto realiza **web scraping na Amazon Brasil** para coletar
informações de produtos (ex.: smartphones) e exportar os resultados para
um arquivo **Excel (.xlsx)**.

## 🚀 Tecnologias utilizadas

-   **Java 17**
-   **Maven**
-   **Selenium WebDriver**
-   **WebDriverManager**
-   **Apache POI** (para manipulação de arquivos Excel)
-   **SLF4J** (para logging)

## 📂 Estrutura do projeto

    src/main/java
    ├── application
    │   └── App.java            # Classe principal
    ├── controller
    │   └── DataScraping.java   # Lógica de scraping com Selenium
    ├── util
    │   └── ExcelWriter.java    # Escrita dos dados no Excel
    └── model/entities
        └── Product.java        # Entidade de produto

## ⚙️ Pré-requisitos

-   Java 17 instalado
-   Maven instalado
-   Google Chrome atualizado

## ▶️ Como executar

1.  Clone o repositório:

    ``` bash
    git clone https://github.com/EmersonSN/webScraping.git
    cd webScraping
    ```

2.  Compile e baixe as dependências:

    ``` bash
    mvn clean install
    ```

3.  Execute o programa:

    ``` bash
    mvn exec:java -Dexec.mainClass="application.App"
    ```

4.  Ao final, será gerado um arquivo **products.xlsx** na raiz do
    projeto com os resultados.

## 📝 Observações importantes

-   Evite muitas requisições em pouco tempo para não ser bloqueado.
-   Este projeto é **para fins educacionais**.

## 📖 Licença

Este projeto é de uso livre para fins de estudo e aprendizado.
