# week-tech
------
Aplicativo Android desenvolvido em Java que serve como sistema de apoio para um evento (Week Tech). Fornece telas para exibir informações do evento, cadastro de participantes e projetos, confirmação de presença, contato/localização e um painel administrativo. O projeto utiliza Room para persistência local e segue uma organização típica de um app Android com Activities e camadas de dados.

## Principais funcionalidades
-------------------------
-  Exibir informações do evento (programação, palestrantes, projetos e patrocinadores).
-  Cadastro de participantes e (opcional) cadastro de projetos.
-  Abrir localização no Google Maps.
-  Telas de contato/FAQ.
-  Confirmação de presença (por código).
-  Login administrativo e painel com estatísticas.
-  Gerenciamento do Coffee Break (exibido no painel admin).
-  Campos dinâmicos para apresentação de projetos durante a inscrição.

## Tecnologias e dependências
-------------------------
- Linguagem: Java
- Android (minSdk 25, target/compileSdk 36)
- Room (RoomDatabase, DAOs, Entities)
- Material Components, AppCompat, ConstraintLayout

## Estrutura do projeto 
-----------------------------
Raiz do projeto
- `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties`, `gradlew`/`gradlew.bat` — arquivos de build e configuração do Gradle.

Módulo `app/`
- `app/build.gradle.kts` — configurações do módulo Android (namespace, SDKs, dependências).
- `app/src/main/AndroidManifest.xml` — declara Activities e configurações do app.

Código fonte Java/Kotlin
- `app/src/main/java/com/heitor/week_tech/`
  - `ui/` — Activities e telas (ex.: `LoginActivity`, `MainActivity`, `RegistrationActivity`, `PresenceActivity`, `ContactActivity`, `AdminActivity`).
  - `data/` — camada de dados:
	- `model/` — entidades do Room (ex.: `Participante`, `Palestrante`, `Palestra`, `Projeto`, `Presenca`, `Patrocinador`, `FAQ`, `Admin`, `InformacaoEvento`).
	- `dao/` — interfaces DAO para acesso ao banco.
	- `database/` — `AppDatabase` (configuração do Room).
  - `adapter/` — adaptadores para listas/recyclers (se presentes).
  - `docs/` — documentos do projeto (requisitos, etc.).

Recursos (res/)
- `app/src/main/res/layout/` — layouts XML para Activities e componentes.
- `app/src/main/res/values/` — `strings.xml`, `colors.xml`, `themes.xml`.
- `app/src/main/res/drawable/`, `mipmap-*/` — imagens e ícones do app.

Testes
- `app/src/test/java/` — testes unitários.
- `app/src/androidTest/java/` — testes instrumentados.

Observação: esta organização é a estrutura padrão de um app Android modular. Navegue pelas pastas acima para encontrar a implementação específica de cada requisito.

## Banco de dados
--------------
O projeto define diversas entidades gerenciadas por Room: Participante, Palestrante, Palestra, Presenca, Projeto, Patrocinador, FAQ, Admin e InformacaoEvento. A classe `AppDatabase` configura o banco (`week_tech_database`) e expõe DAOs.

## Como construir e executar
------------------------
Pré-requisitos: JDK 11, Android SDK (API 36 compatível), Android Studio (recomendado).

No Windows (PowerShell) pela linha de comando, na raiz do projeto:

```
./gradlew assembleDebug
```

Ou abra o projeto no Android Studio e execute no emulador ou dispositivo físico.

## Observações e pontos a melhorar
-------------------------------
- Atualmente o Room está configurado com `.allowMainThreadQueries()` (prática não recomendada). Recomenda-se migrar para operações assíncronas (Executors, LiveData, Coroutines/Flow).
- Validações e persistência estão simples (ex.: Login apenas navega para a MainActivity). É necessário implementar autenticação real e salvar inscrições/presenças no banco.
- Alguns comportamentos (ex.: confirmação de presença e envio de inscrições) exibem apenas Toasts; implementar lógica de armazenamento/relatórios para o painel admin.

## Licença
-------
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` na raiz do repositório para os termos completos.

[Ver LICENSE](./LICENSE)





