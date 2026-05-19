# week-tech

Aplicativo Android em Java para apoio ao evento Week Tech. O app reúne telas de apresentação do evento, inscrições, programação, presença, contato e um painel administrativo com dados salvos localmente via Room.

## Funcionalidades atuais

- Tela inicial com navegação inferior e mapa do local (Google Maps, quando disponível no dispositivo).
- Cadastro de participantes com opção de Coffee Break.
- Cadastro de projetos e palestrantes.
- Listagem de programação e palestrantes.
- Tela de presença por código.
- Tela de contato e FAQ.
- Login administrativo e painel com estatísticas básicas.

## Stack técnica

- Linguagem: Java 11
- Android: `minSdk 25`, `targetSdk 36`, `compileSdk 36`
- UI: Material Components, AppCompat, ConstraintLayout, RecyclerView
- Persistência local: Room
- Mapas: Google Play Services Maps (`com.google.android.gms:play-services-maps:20.0.0`)
- Build: Gradle (Kotlin DSL)

## Estrutura do projeto

### Raiz

- `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties`, `gradlew`, `gradlew.bat`

### Módulo `app`

- `app/build.gradle.kts`: configuração de build do app.
- `app/src/main/AndroidManifest.xml`: Activities e metadados (incluindo chave de Maps).

### Código-fonte

- `app/src/main/java/com/heitor/week_tech/ui/`: telas (`Activity`) e navegação.
- `app/src/main/java/com/heitor/week_tech/ui/adapter/`: adapters atualmente usados pelas telas principais.
- `app/src/main/java/com/heitor/week_tech/data/local/`: camada Room ativa no fluxo principal (`entity`, `dao`, `database`).
- `app/src/main/java/com/heitor/week_tech/data/model`, `data/dao`, `data/database`: estrutura legada mantida no projeto.
- `app/src/main/java/com/heitor/week_tech/adapter/`: adapters legados/paralelos.

### Recursos

- `app/src/main/res/layout/`: layouts XML das telas.
- `app/src/main/res/values/`: strings, cores, temas e estilos.
- `app/src/main/res/drawable`, `mipmap-*`: ícones e assets visuais.
- `app/src/main/res/menu`: menus da navegação inferior.

## Banco de dados (Room)

Atualmente há duas estruturas de dados no código:

- **Estrutura ativa nas telas atuais:** `data/local/*`
  - Banco: `week_tech_db`
  - Entidades principais: `Participant`, `Project`, `Speaker`

- **Estrutura legada/paralela:** `data/*`
  - Banco: `week_tech_database`
  - Entidades: `Participante`, `Palestrante`, `Palestra`, `Projeto`, `Presenca`, `Patrocinador`, `FAQ`, `Admin`, `InformacaoEvento`

> Nota: parte do código usa a camada `data/local`, enquanto parte legada permanece no repositório para evolução/refatoração.

## Como executar

Pré-requisitos:

- JDK 11
- Android SDK instalado (API 36)
- Android Studio (recomendado) ou linha de comando

No Windows PowerShell (raiz do projeto):

```powershell
.\gradlew.bat assembleDebug
```

APK debug gerado em:

- `app/build/outputs/apk/debug/`

## Observações importantes

- O projeto contém código comentado/documentado em português para facilitar manutenção.
- A tela inicial inicializa o mapa apenas quando Google Play Services está disponível, evitando crash em ambientes sem suporte.
- O `AndroidManifest.xml` contém metadado de chave de Maps no estado atual; para produção, recomenda-se usar variáveis de ambiente/`local.properties` e não expor chave em repositório.

## Pontos de melhoria sugeridos

- Unificar as duas camadas de dados (`data/local` e `data/*`) para reduzir duplicidade.
- Fortalecer autenticação do login administrativo (hoje com validação simples de formulário).
- Evoluir validações de entrada e mensagens de erro por campo.
- Criar testes instrumentados para fluxos críticos (login, cadastro e navegação).

## Licença

Este projeto está licenciado sob a licença MIT.

- `LICENSE`





