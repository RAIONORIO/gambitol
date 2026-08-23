# 04 — ESTRUTURA DO PROJETO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `04_ESTRUTURA_DO_PROJETO.md`  
> **Versão:** 1.1  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-23  
> **Responsabilidade:** definir como o projeto Gambitol deve ser organizado fisicamente no repositório, nos módulos Gradle, source sets, packages Java, recursos Android e testes  
> **Fonte normativa para:** localização de código, divisão por módulo, convenções de package, source sets, diretórios de recursos, testes, arquivos de build e regras para criação de novos arquivos  
> **Não cobre em detalhe:** regras do xadrez, implementação de classes, padrões completos de Java, workflow Git, especificação visual, roadmap, monetização ou publicação  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo deste documento

A arquitetura define **o que deve ser separado**.

Este documento define **onde cada coisa deve ficar**.

Ele existe para impedir que o projeto cresça de forma acidental.

Sem uma regra de estrutura, é comum que um projeto Android comece assim:

```text
MainActivity.java
```

e meses depois termine assim:

```text
MainActivity.java
GameManager.java
Utils.java
Utils2.java
Helper.java
HelperNew.java
ChessHelper.java
DatabaseHelper.java
Whatever.java
```

com responsabilidades distribuídas de acordo com o humor do dia em que cada arquivo foi criado.

O Gambitol não seguirá esse caminho.

Ao mesmo tempo, também não vamos criar uma floresta de packages vazios, módulos prematuros e interfaces sem uso.

O princípio deste documento é:

> **estrutura suficiente para proteger responsabilidades, mas simples o bastante para continuar compreensível.**

---

# 2. O que este documento considera “estrutura”

No Gambitol, “estrutura” inclui seis níveis diferentes.

## 2.1 Repositório

Arquivos e pastas na raiz do projeto.

Exemplos:

```text
README.md
gradlew
settings.gradle.kts
app/
```

## 2.2 Módulos Gradle

Unidades independentes de build.

Exemplos conceituais:

```text
:app
:<motor>
```

## 2.3 Source sets

Conjuntos de fontes usados por determinadas variantes.

Exemplos:

```text
src/main/
src/test/
src/androidTest/
```

## 2.4 Packages Java

Namespaces lógicos.

Exemplo:

```text
br.com.raionorio.gambitol
```

## 2.5 Recursos Android

Arquivos não Java.

Exemplos:

```text
res/layout/
res/drawable/
res/values/
```

## 2.6 Testes

Estruturas separadas conforme ambiente de execução.

Exemplos:

```text
src/test/java/
src/androidTest/java/
```

Confundir esses níveis gera erros conceituais.

Por exemplo:

> package Java não é módulo Gradle.

e:

> pasta no Android Studio “Android view” não representa necessariamente a estrutura física real do disco.

A documentação oficial do Android alerta exatamente para essa diferença.

Fonte:

- Android Studio — Projects overview  
  https://developer.android.com/studio/projects

---

# 3. Estrutura real confirmada

## ESTADO CONFIRMADO EM 2026-08-23

Após a conclusão da Fase 2 e a integração do Pull Request `#1`, a raiz do Gambitol possui dois módulos Gradle:

```text
:app
:chess-engine
```

A estrutura relevante confirmada é:

```text
gambitol/
├── .git/
├── .gitignore
├── README.md
├── app/
│   ├── build.gradle.kts
│   └── src/
├── chess-engine/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   └── java/
│       │       └── br/com/raionorio/gambitol/engine/
│       │           └── Side.java
│       └── test/
│           └── java/
│               └── br/com/raionorio/gambitol/engine/
│                   └── SideTest.java
├── build.gradle.kts
├── gradle.properties
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

O módulo Android continua contendo, entre outros elementos:

```text
app/
├── build.gradle.kts
└── src/
    ├── main/
    │   ├── AndroidManifest.xml
    │   ├── java/
    │   │   └── br/com/raionorio/gambitol/MainActivity.java
    │   └── res/
    ├── test/
    └── androidTest/
```

O estado completo continua devendo ser conferido no repositório real antes de qualquer mudança estrutural.

Este documento não substitui comandos como:

```bash
git status -sb
```

ou inspeções da árvore real quando precisarmos saber o que existe naquele momento.

---

# 4. Estrutura exibida pelo Android Studio versus estrutura física

## CONCEITO IMPORTANTE

O Android Studio possui uma visualização chamada **Android View**.

Ela reorganiza arquivos para facilitar navegação.

Ela NÃO representa literalmente as pastas do disco.

A documentação oficial explica que a Android View agrupa:

- manifests;
- Java/Kotlin;
- resources;
- Gradle scripts;

e esconde alguns detalhes.

Quando precisarmos entender a estrutura verdadeira:

> usar a visualização **Project** no Android Studio ou Git Bash.

Fonte:

https://developer.android.com/studio/projects

---

# 5. Unidade estrutural principal: módulo

A documentação oficial define módulo como conjunto de:

- código;
- recursos;
- configuração de build;

que pode ser construído/testado como unidade.

No estado inicial, o Gambitol possuía apenas:

```text
:app
```

No estado confirmado em 2026-08-23, existem:

```text
:app
:chess-engine
```

O módulo `:app` é Android.

O módulo `:chess-engine` é uma biblioteca Java pura.

Fonte:

https://developer.android.com/studio/projects

---

# 6. Estrutura multimódulo adotada pela arquitetura

## DECIDIDO E IMPLEMENTADO

O documento `03_ARQUITETURA_DO_GAMBITOL.md` estabelece uma fronteira física entre:

```text
Android
```

e:

```text
motor de xadrez Java puro
```

Essa fronteira já foi implementada como:

```text
gambitol/
├── app/
└── chess-engine/
```

Os paths Gradle são:

```text
:app
:chess-engine
```

O nome aprovado do módulo do motor é:

```text
chess-engine
```

## MOTIVO

- comunica claramente a responsabilidade;
- é compreensível fora do projeto;
- não mistura nome técnico com marca;
- combina com o conceito de motor de xadrez;
- cria uma fronteira verificável entre Android e domínio.

A decisão está registrada em `11_DECISOES_TECNICAS.md`.

A implementação foi integrada à `main` pelo Pull Request `#1`.

---

# 7. Alternativas consideradas para o nome do módulo

Antes da implementação, foram consideradas as seguintes opções.

## Opção A — adotada

```text
chess-engine
```

Vantagens:

- explícito;
- profissional;
- fácil de entender.

## Opção B — não adotada

```text
engine
```

Vantagem:

- curto.

Desvantagem:

- genérico se futuramente existir outra engine.

## Opção C — não adotada

```text
chess
```

Vantagem:

- simples.

Desvantagem:

- não comunica claramente se contém regra, UI ou assets.

## Opção D — não adotada

```text
core
```

Desvantagem principal:

- genérico e propenso a se tornar depósito de responsabilidades.

### Status

```text
chess-engine → APROVADO E IMPLEMENTADO
```

As alternativas são preservadas apenas como contexto histórico da decisão.

---

# 8. Estrutura raiz vigente e documentação externa

## ESTADO ATUAL

A estrutura de alto nível vigente é:

```text
gambitol/
│
├── .git/
├── .gitignore
├── README.md
│
├── app/
│
├── chess-engine/
│
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── gradlew.bat
```

## Documentação `00–15`

Por decisão operacional tomada em 2026-08-23, a documentação extensa `00–15` permanece fora do repositório por enquanto.

Portanto, a pasta:

```text
docs/
```

**não faz parte da estrutura atual do GitHub**.

Uma integração futura da documentação ao repositório deverá ser tratada como mudança separada e consciente, sem ser misturada a branches de feature do produto.

---

# 9. Arquivos que pertencem à raiz

## `.gitignore`

Responsável por ignorar:

- outputs;
- caches;
- arquivos locais;
- artefatos não versionáveis.

Não deve esconder arquivos que fazem parte do build reproduzível.

---

## `README.md`

Entrada rápida do repositório.

Não substitui os documentos extensos.

---

## `settings.gradle.kts`

Responsável pela estrutura do build Gradle:

- nome do projeto;
- repositórios;
- módulos incluídos.

No estado atual, este arquivo registra os dois subprojects:

```kotlin
include(":app")
include(":chess-engine")
```

A documentação oficial do Gradle mostra que multi-project builds são definidos a partir de `settings.gradle(.kts)`.

Fonte:

https://docs.gradle.org/current/userguide/multi_project_builds.html

---

## `build.gradle.kts`

Configuração de build de nível raiz.

Não deve receber dependências específicas do app por conveniência.

---

## `gradle.properties`

Propriedades globais relevantes ao build.

Não armazenar segredos.

---

## `gradlew`

Wrapper Unix-like, utilizado pelo Git Bash:

```bash
./gradlew
```

---

## `gradlew.bat`

Wrapper Windows CMD.

Deve ser versionado mesmo que nosso fluxo principal use Git Bash.

---

## `gradle/wrapper/`

Arquivos do Gradle Wrapper.

Devem permanecer no Git.

---

## `gradle/libs.versions.toml`

Version catalog.

Centraliza aliases e versões quando o projeto usa esse mecanismo.

O projeto atual já possui esse arquivo segundo o `git status` exibido.

Fonte:

https://developer.android.com/build/migrate-to-catalogs

---

# 10. Arquivos que NÃO devem aparecer aleatoriamente na raiz

Evitar:

```text
Test.java
teste.txt
notes.txt
temp/
backup/
old/
old2/
final/
final-final/
```

Arquivos temporários devem ser:

- descartados;
- ignorados;
- ou colocados em local apropriado.

A raiz deve permanecer previsível.

---

# 11. Estrutura do módulo Android `app`

O módulo `app` deve conter tudo que depende diretamente da plataforma Android.

Estrutura-base:

```text
app/
├── build.gradle.kts
├── src/
│   ├── main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/
│   │   └── res/
│   ├── test/
│   └── androidTest/
└── ...
```

A estrutura oficial do Android usa exatamente esse conceito de source sets.

Fontes:

- https://developer.android.com/studio/projects
- https://developer.android.com/build

---

# 12. `app/src/main/`

Esse é o source set principal.

Ele contém código e recursos comuns ao aplicativo.

Estrutura:

```text
app/src/main/
├── AndroidManifest.xml
├── java/
└── res/
```

Pode futuramente conter:

```text
assets/
```

ou outras pastas somente se houver necessidade real.

---

# 13. `AndroidManifest.xml`

Local:

```text
app/src/main/AndroidManifest.xml
```

Responsabilidades:

- declarar componentes;
- permissões;
- capabilities;
- configurações exigidas pelo sistema.

Não deve ser usado como arquivo genérico de configuração.

Fonte:

https://developer.android.com/guide/topics/manifest/manifest-intro

---

# 14. Código Java do aplicativo

Raiz atual:

```text
app/src/main/java/br/com/raionorio/gambitol/
```

Esse namespace foi definido no início do projeto.

Ele continuará sendo a base do código Android.

---

# 15. Package base do app

## DECIDIDO

```text
br.com.raionorio.gambitol
```

A Java Language Specification explica que packages formam uma estrutura hierárquica e que nomes qualificados ajudam a evitar conflitos em software distribuído.

Fontes:

- JLS 25 — Packages and Modules  
  https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html

---

# 16. Package não é applicationId

## CONCEITO IMPORTANTE

No Android moderno existem conceitos distintos:

### `applicationId`

Identidade da aplicação instalada/publicada.

### `namespace`

Namespace usado pelo build Android, inclusive para `R`.

### package Java

Namespace da classe Java.

No início, frequentemente usam valores semelhantes.

Mas não são conceitualmente a mesma coisa.

A documentação oficial deixa claro que `namespace` e `applicationId` têm papéis diferentes.

Fonte:

https://developer.android.com/build

---

# 17. Valor normativo atual do applicationId

## DECIDIDO

```text
br.com.raionorio.gambitol
```

Esse valor deverá ser confirmado no `app/build.gradle.kts` antes de publicação.

---

# 18. Valor normativo atual do namespace

## DECIDIDO COMO META ESTRUTURAL

```text
br.com.raionorio.gambitol
```

O arquivo real deverá ser lido antes de afirmar o estado atual.

---

# 19. Convenção geral de packages

Packages do Gambitol devem:

- usar letras minúsculas;
- ser descritivos;
- evitar abreviação obscura;
- evitar nomes genéricos demais;
- refletir responsabilidade;
- evitar profundidade sem benefício.

Exemplo correto:

```text
br.com.raionorio.gambitol.ui.game
```

Exemplo indesejado:

```text
br.com.raionorio.gambitol.misc.stuff.helpers.common.utils
```

---

# 20. Package por responsabilidade e feature

## PROPOSTO

Dentro do módulo `app`, a estrutura deve tender a organizar código visual por **feature/tela**, não criar um package global gigantesco para cada tipo.

Exemplo conceitual:

```text
br.com.raionorio.gambitol
└── ui
    ├── game
    ├── home
    └── settings
```

Se apenas `game` existir inicialmente:

> criar somente `ui.game`.

Não criar `home` e `settings` vazios “para preparar”.

---

# 21. Estrutura inicial mínima do app

## PROPOSTO

Na primeira etapa:

```text
br.com.raionorio.gambitol
├── MainActivity.java
└── ui/
    └── game/
```

Mas a localização final de `MainActivity` só deve mudar quando o fluxo de navegação for definido.

Não mover arquivo apenas para “ficar bonito”.

---

# 22. Onde colocar ViewModel

Se a tela do jogo usar ViewModel:

```text
br.com.raionorio.gambitol.ui.game
```

é uma localização coerente.

Exemplo conceitual:

```text
ui/game/
├── <Tela>.java
├── <ViewModel>.java
└── <UiState>.java
```

Nomes concretos não estão aprovados por este documento.

---

# 23. Por que não criar package global `viewmodel`

Estrutura ruim para um app pequeno:

```text
viewmodel/
activity/
adapter/
fragment/
model/
```

Isso agrupa classes pelo tipo técnico, mas separa arquivos que mudam juntos.

Para features visuais, manter componentes relacionados próximos costuma facilitar navegação.

O guia oficial de modularização Android também enfatiza coesão e agrupamento de funcionalidades relacionadas.

Fonte:

https://developer.android.com/topic/modularization/patterns

---

# 24. Quando package por tipo pode ser aceitável

Dentro de uma feature muito pequena:

não é necessário criar subpackage para cada conceito.

Se `ui.game` tiver:

- Activity;
- ViewModel;
- UiState;

esses arquivos podem coexistir.

Criar:

```text
ui.game.activity
ui.game.viewmodel
ui.game.state
```

para três arquivos adiciona navegação sem valor.

---

# 25. Regra de profundidade de package

Criar novo nível somente quando:

- existe grupo coeso;
- há número suficiente de classes;
- existe boundary;
- melhora entendimento.

Não criar nível apenas para corresponder ao nome de uma classe.

---

# 26. Package `ui`

Responsável por código diretamente relacionado à experiência Android.

Pode conter:

- telas;
- Views;
- ViewModels;
- adapters de UI;
- renderização;
- estado de apresentação.

Não conter:

- regras FIDE;
- persistência bruta;
- IA;
- engine.

---

# 27. Package `data`

## FUTURO

Só deverá existir quando o app possuir data layer real.

Possíveis responsabilidades:

- repositories;
- sources locais;
- conversão de persistência;
- preferências.

Não criar agora se não há dados externos.

---

# 28. Package `platform`

## FUTURO / CANDIDATO

Pode ser útil para adapters altamente Android-específicos:

- som;
- vibração;
- share;
- clock Android;
- integração Play.

Mas só criar se surgir conjunto real de classes.

---

# 29. Package `navigation`

## FUTURO

Criar somente quando houver navegação suficiente para justificar responsabilidade própria.

---

# 30. Package `di`

## NÃO CRIAR AGORA

Não haverá package de dependency injection enquanto não houver configuração real.

Manual DI simples pode ocorrer próximo de onde o objeto é construído.

---

# 31. Package `utils`

## EVITAR

`utils` tende a virar depósito.

Antes de criar utilitário, perguntar:

> “Qual conceito realmente possui essa função?”

Exemplo:

```text
CoordinateUtils
```

pode indicar que transformação de coordenadas pertence a um objeto/adapter de tabuleiro.

---

# 32. Package `helpers`

## EVITAR

“Helper” não comunica responsabilidade.

Preferir nome que diga:

- o que faz;
- para quem;
- qual domínio.

---

# 33. Package `common`

## EVITAR NO INÍCIO

Código compartilhado deve ser compartilhado porque é realmente usado por áreas distintas.

Não criar `common` como lixeira preventiva.

---

# 34. Package `manager`

Não usar `manager` como namespace genérico.

Classes chamadas `SomethingManager` devem justificar:

> o que gerenciam exatamente?

---

# 35. Estrutura do motor Java

## DECIDIDO E IMPLEMENTADO

O módulo `chess-engine` possui estrutura Java pura:

```text
chess-engine/
├── build.gradle.kts
└── src/
    ├── main/
    │   └── java/
    │       └── br/com/raionorio/gambitol/engine/
    │           └── Side.java
    └── test/
        └── java/
            └── br/com/raionorio/gambitol/engine/
                └── SideTest.java
```

Como módulo Java puro, ele não possui necessidade de:

```text
AndroidManifest.xml
res/
androidTest/
```

por padrão.

A documentação Android explica que bibliotecas Java/Kotlin não possuem os grupos `manifest` e `res` típicos de módulo Android.

Fonte:

https://developer.android.com/studio/projects

---

# 36. Package base do motor

## DECIDIDO E IMPLEMENTADO

O package base aprovado do motor é:

```text
br.com.raionorio.gambitol.engine
```

Antes da decisão, foram consideradas:

### A — adotada

```text
br.com.raionorio.gambitol.engine
```

Vantagens:

- claro;
- curto;
- separado do package Android visual;
- não repete `chess` desnecessariamente porque Gambitol já é um projeto de xadrez.

### B — não adotada

```text
br.com.raionorio.gambitol.chess
```

Vantagem:

- linguagem de domínio.

### C — não adotada

```text
br.com.raionorio.gambitol.chessengine
```

Desvantagem:

- nome maior sem benefício proporcional.

A primeira classe criada nesse package foi:

```text
Side
```

e seu teste correspondente é:

```text
SideTest
```

A decisão está registrada em `11_DECISOES_TECNICAS.md`.

---

# 37. Estrutura interna do engine: estratégia

## DECIDIDO NO ESTADO ATUAL / REVISÁVEL

O engine começa no package base:

```text
br.com.raionorio.gambitol.engine
```

sem criar antecipadamente:

```text
board/
piece/
move/
rule/
game/
```

todos vazios.

A primeira entidade `Side` foi criada diretamente no package base.

Conforme classes reais surgirem, o engine poderá ser dividido em subpackages por conceitos coesos.

A regra permanece:

> criar divisão estrutural quando a responsabilidade real justificar, não para antecipar um diagrama.

---

# 38. Possíveis packages futuros do engine

Quando o volume justificar:

```text
engine/
├── board/
├── piece/
├── move/
├── game/
└── rule/
```

Esses nomes são candidatos.

Não são ordem de criação.

---

# 39. Critério para criar `board`

Criar quando existirem responsabilidades coesas relacionadas a:

- tabuleiro;
- posição;
- ocupação;
- consulta espacial.

---

# 40. Critério para criar `piece`

Criar quando classes de peças e conceitos diretamente ligados a peça formarem grupo suficientemente grande.

---

# 41. Critério para criar `move`

Criar quando movimento possuir:

- representação;
- resultado;
- histórico;
- geração;

que justifiquem grupo.

---

# 42. Critério para criar `rule`

Criar quando regras que não pertencem naturalmente a uma entidade precisarem de agrupamento.

Evitar um package contendo 25 classes chamadas:

```text
SomethingRule
```

sem organização.

---

# 43. Critério para criar `game`

Pode agrupar:

- sessão;
- turno;
- resultado;
- estado global.

Somente quando classes realmente existirem.

---

# 44. Estrutura plana inicialmente

Para poucos arquivos, isto é aceitável:

```text
engine/
├── Position.java
├── PieceColor.java
├── ...
```

Não existe prêmio por número de packages.

Quando o package começar a exigir rolagem interminável ou conter responsabilidades claramente distintas, dividir.

---

# 45. Package-private como ferramenta

Java permite tipos/membros sem `public`.

Isso pode ser usado para esconder detalhes internos do package.

Estrutura de package afeta encapsulamento.

Portanto, dividir packages demais também pode aumentar necessidade de `public`.

Essa é mais uma razão para não fragmentar prematuramente.

---

# 46. Gradle module não é Java module JPMS

## CONCEITO IMPORTANTE

O módulo:

```text
:chess-engine
```

é um **subproject Gradle**.

Isso não significa que precisamos criar:

```text
module-info.java
```

JPMS é outro sistema.

O Gambitol não usa JPMS no estado atual.

---

# 47. Nome de módulo Gradle versus package Java

É perfeitamente válido:

```text
Gradle:
:chess-engine
```

e:

```text
Java:
br.com.raionorio.gambitol.engine
```

O hífen pode ser usado no nome da pasta/módulo Gradle.

Não pode aparecer como parte de identificador Java.

---

# 48. `settings.gradle.kts`

## ESTADO IMPLEMENTADO

O build registra:

```kotlin
include(":app")
include(":chess-engine")
```

Isso transforma `chess-engine` em subproject do build Gradle.

A inclusão foi validada por:

```bash
./gradlew projects
```

e posteriormente pelo build integrado da `main`.

---

# 49. `chess-engine/build.gradle.kts`

## ESTADO IMPLEMENTADO

O módulo utiliza o plugin Java Library:

```kotlin
plugins {
    `java-library`
}
```

O nível de linguagem atual é Java 17:

```kotlin
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
```

Os testes JVM usam JUnit Jupiter 6.1.3 e a JUnit Platform:

```kotlin
dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
```

Esse arquivo não aplica plugin Android, não possui Manifest e não declara recursos Android.

---

# 50. Java Library versus Android Library

## DECIDIDO

### Java Library — adotada para o motor

- JVM;
- Java;
- sem Android Manifest;
- sem `R`;
- sem Android SDK.

### Android Library — não adotada para o motor

- Android;
- resources;
- Manifest;
- AAR.

Para o motor, Java Library foi escolhida porque materializa a independência da plataforma Android.

---

# 51. Dependência do app no engine

## DECIDIDO E IMPLEMENTADO

O módulo `app` depende do engine por dependência de projeto:

```kotlin
implementation(project(":chess-engine"))
```

A direção é:

```text
:app -> :chess-engine
```

Essa relação foi validada pelo build do aplicativo após a criação do módulo e novamente após o merge na `main`.

---

# 52. Engine não depende do app

Nenhuma dependência reversa.

Se isso parecer necessário:

parar e revisar arquitetura.

---

# 53. Source sets do módulo Android

A documentação oficial Android define source sets.

Principais:

```text
src/main/
src/test/
src/androidTest/
```

Possíveis futuros:

```text
src/debug/
src/release/
```

Não criar source set se não houver conteúdo específico.

Fonte:

https://developer.android.com/build/build-variants

---

# 54. `src/main/`

Código de produção comum.

---

# 55. `src/test/`

Testes locais executados na máquina/JVM.

No módulo Android, são úteis para código que não exige dispositivo.

Fonte:

https://developer.android.com/training/testing/fundamentals

---

# 56. `src/androidTest/`

Testes instrumentados que executam em dispositivo/emulador.

Exemplos:

- UI;
- integração com framework Android;
- Espresso.

Fonte:

https://developer.android.com/training/testing/espresso/setup

---

# 57. Source sets do engine

Como módulo Java puro:

```text
src/main/java/
src/test/java/
```

Esses devem cobrir a maioria dos testes do domínio.

---

# 58. Não duplicar testes do engine no app

Errado:

```text
engine/src/test/... KnightTest
app/src/test/... KnightTest duplicado
```

A regra deve ser testada onde pertence.

App testa integração.

---

# 59. Estrutura espelhada de testes

## PROPOSTO

Testes devem usar package correspondente ao código testado.

Exemplo conceitual:

Produção:

```text
src/main/java/br/com/raionorio/gambitol/engine/...
```

Teste:

```text
src/test/java/br/com/raionorio/gambitol/engine/...
```

Isso facilita navegação.

---

# 60. Nomes de testes

Detalhes completos ficam em `08_TESTES_E_QUALIDADE.md`.

Regra estrutural:

- teste perto logicamente da unidade testada;
- classe de teste identificável;
- sem diretório genérico `tests/all`.

---

# 61. Fixtures

## FUTURO

Se testes repetirem criação de estados:

considerar fixtures/builders de teste.

Não criar infraestrutura de fixture antes da repetição existir.

---

# 62. `testFixtures`

Gradle/AGP possuem mecanismos de test fixtures em alguns tipos de módulos.

Não ativar automaticamente.

Somente se compartilhamento entre suites justificar.

---

# 63. Recursos Android

A pasta:

```text
app/src/main/res/
```

deve conter recursos não Java.

A documentação oficial organiza recursos por tipo.

Fonte:

https://developer.android.com/guide/topics/resources/providing-resources

---

# 64. `res/layout/`

XML de layouts.

Exemplos futuros podem incluir:

- tela principal;
- partida;
- diálogo.

Não criar layouts vazios.

---

# 65. `res/drawable/`

Para:

- shapes;
- vetores;
- imagens usadas como drawable;
- backgrounds.

---

# 66. `res/mipmap/`

Prioritariamente launcher icons.

Não usar `mipmap` como pasta genérica de imagens de peças.

---

# 67. `res/values/`

Valores estruturados.

Arquivos comuns:

```text
strings.xml
colors.xml
themes.xml
dimens.xml
```

`dimens.xml` só deve aparecer se houver valores compartilhados que justifiquem.

---

# 68. `values-night/`

Variações para modo noturno.

O projeto gerado já possui `values-night/themes.xml`.

Não apagar sem entender tema.

---

# 69. `res/xml/`

XML de configuração.

O projeto gerado possui:

```text
backup_rules.xml
data_extraction_rules.xml
```

Eles devem ser compreendidos antes de alterar.

---

# 70. `res/raw/`

## FUTURO

Pode armazenar arquivos acessados como recurso bruto.

Exemplos possíveis:

- sons;
- dados estáticos.

Se precisarmos manter nomes/hierarquia original, `assets/` pode ser mais adequado.

A documentação Android diferencia `raw/` de `assets/`.

---

# 71. `assets/`

## FUTURO

Arquivos empacotados preservando estrutura/nome.

Não criar sem necessidade.

---

# 72. Assets das peças

## PENDENTE

Precisaremos definir formato:

- vector drawable;
- PNG/WebP;
- SVG convertido;
- desenho programático.

Onde colocar dependerá da decisão.

Não importar dezenas de imagens antes de definir padrão visual.

---

# 73. Nome de recursos

## PROPOSTO

Usar:

```text
lowercase_with_underscores
```

Exemplo:

```text
game_title
button_restart
piece_white_king
```

A documentação Android usa identificadores de recursos em formato compatível com esse padrão, e seus materiais de treinamento recomendam nomes minúsculos separados por underscore para recursos string.

Fonte:

https://developer.android.com/codelabs/basic-android-kotlin-compose-add-images

---

# 74. Prefixos de recursos

## PROPOSTO

Para reduzir ambiguidades:

### ícones

```text
ic_
```

### backgrounds

```text
bg_
```

### peças

```text
piece_
```

### elementos de tabuleiro

```text
board_
```

### strings de tela

podem usar prefixo da feature:

```text
game_
settings_
home_
```

Essas convenções só devem ser aplicadas se melhorarem clareza.

---

# 75. Layout naming

## PROPOSTO

Padrão Android tradicional:

```text
activity_<nome>
fragment_<nome>
view_<nome>
dialog_<nome>
item_<nome>
```

Mas não criar `fragment_` se não usamos Fragment.

---

# 76. Strings

Textos visíveis não devem ficar espalhados em Java.

Usar:

```text
res/values/strings.xml
```

Isso facilita:

- manutenção;
- tradução;
- reuso.

---

# 77. Cores

Cores de branding ficam em:

```text
res/values/colors.xml
```

ou estrutura de theme adequada.

Não colocar hex repetido em vários layouts se representar token visual recorrente.

---

# 78. Dimensões

Evitar hardcodes repetidos.

Mas também não mover cada `8dp` para `dimens.xml` apenas por ritual.

Extrair quando:

- compartilhado;
- semanticamente importante;
- parte de sistema visual.

---

# 79. Launcher icons

Manter em `mipmap-*`.

Android Studio gerencia densidades.

---

# 80. `drawable-nodpi`

Pode ser útil para imagens que não devem ser escaladas conforme densidade automática.

Decisão depende dos assets.

---

# 81. Nome de ID de View

## PROPOSTO

Usar nomes semânticos:

```text
board_view
current_player_text
restart_button
```

Evitar:

```text
text1
button2
viewA
```

---

# 82. Binding entre Java e Views

Se usarmos `findViewById`, IDs precisam ser claros.

Se futuramente usarmos View Binding, a qualidade dos IDs também afeta nomes gerados.

---

# 83. `keepRules`

O projeto atual mostrou:

```text
app/src/main/keepRules/rules.keep
```

Esse arquivo foi gerado pelo template/versão atual do Android Studio.

Não remover automaticamente.

Antes de alterar:

- ler;
- verificar build;
- entender integração do AGP atual.

---

# 84. ProGuard/R8

Se houver:

```text
proguard-rules.pro
```

ou estrutura moderna equivalente:

não copiar regra genérica da internet sem necessidade.

Minificação será tratada no documento de release.

---

# 85. Código gerado

Diretórios como:

```text
build/
```

não devem ser editados manualmente.

O build pode regenerá-los.

---

# 86. `.gradle/`

Cache local.

Não é código-fonte.

---

# 87. `.idea/`

Configuração de IDE.

A política exata de versionamento será definida em Git workflow.

No início foi ignorada.

---

# 88. `local.properties`

Contém caminhos locais do SDK.

Não deve ser versionado.

---

# 89. APK e AAB

Outputs.

Não devem ser commitados como código-fonte normal.

---

# 90. Estrutura da documentação

## PROPOSTO

Quando integrada:

```text
docs/
├── 00_GUIA_MESTRE.md
├── 01_VISAO_E_OBJETIVOS.md
├── 02_METODO_DE_ENSINO.md
├── 03_ARQUITETURA_DO_GAMBITOL.md
├── 04_ESTRUTURA_DO_PROJETO.md
...
```

Sem subpastas inicialmente.

Motivo:

- só 16 documentos;
- numeração já organiza;
- navegação simples.

Se crescer muito:

reavaliar.

---

# 91. Não guardar documentação em `app/`

Documentação é do projeto, não da aplicação Android exclusivamente.

Fica na raiz em:

```text
docs/
```

---

# 92. Não guardar screenshots soltos na raiz

Se documentação precisar de imagens futuramente:

avaliar:

```text
docs/assets/
```

ou:

```text
docs/images/
```

Nome deve ser aprovado quando surgir necessidade.

---

# 93. Código de produção versus documentação

Nunca colocar `.md` de governança dentro de package Java.

Separação física ajuda clareza.

---

# 94. Estratégia de crescimento por demanda

## DECIDIDO COMO PRINCÍPIO

Nova pasta/package só deve existir quando houver arquivo real para colocar.

Não construir cidade fantasma.

---

# 95. Regra “dois arquivos não obrigam package”

Dois arquivos relacionados podem coexistir no mesmo package.

Package novo deve representar conceito, não contagem.

---

# 96. Regra “dez arquivos não proíbem package único”

Quantidade sozinha também não decide.

Perguntar:

- responsabilidades são diferentes?
- mudam por motivos diferentes?
- acesso package-private importa?
- navegação melhoraria?

---

# 97. Coesão

Arquivos que resolvem o mesmo problema devem ficar próximos.

---

# 98. Acoplamento

Packages/módulos não devem depender uns dos outros sem necessidade.

---

# 99. Ciclos

Evitar:

```text
A depende de B
B depende de A
```

entre grupos arquiteturais.

---

# 100. Dependências físicas permitidas

Se dois módulos:

```text
app → chess-engine
```

Permitido.

---

# 101. Dependências físicas proibidas

```text
chess-engine → app
```

Proibido.

---

# 102. Dependências internas do app

UI pode depender da API pública do engine.

Data futura pode depender de representações/contratos necessários.

Engine não depende de data Android.

---

# 103. Dependência de UI em data

Quando persistência surgir:

preferir coordenação por repository/state holder.

Evitar View lendo banco diretamente.

---

# 104. `MainActivity.java`

Estado atual:

existe.

## REGRA

Enquanto for entry point, pode permanecer no package base.

Mover somente quando:

- estratégia de navegação estiver definida;
- mudança melhorar estrutura.

Não mover por estética.

---

# 105. `ExampleUnitTest.java`

Arquivo gerado pelo template.

Não faz parte do produto final por obrigação.

Depois de termos testes reais:

- pode ser substituído/removido com propósito.

---

# 106. `ExampleInstrumentedTest.java`

Mesma regra.

É arquivo de template.

Não tratá-lo como teste de qualidade real.

---

# 107. Testes de template

Antes de remover:

- entender;
- verificar configuração;
- substituir por teste real.

Isso é bom exercício didático.

---

# 108. Estrutura por fases

## Fase A — projeto gerado — CONCLUÍDA

```text
:app
```

Foi o estado inicial antes da criação do engine.

## Fase B — boundary do motor — CONCLUÍDA

```text
:app
:chess-engine
```

A fronteira foi criada, testada, integrada pelo Pull Request `#1` e validada na `main`.

## Fase C — primeiras classes — ESTADO ATUAL

O engine ainda permanece relativamente plano.

A primeira entidade criada foi:

```text
Side
```

com:

```text
SideTest
```

## Fase D — domínio cresce — FUTURA

Criar subpackages apenas onde houver necessidade.

## Fase E — UI cresce — FUTURA

Criar packages por feature quando a UI possuir responsabilidades reais.

## Fase F — persistência — FUTURA

Adicionar package/data layer no `app` quando houver persistência concreta.

## Fase G — features futuras

Avaliar novos módulos somente com evidência.

---

# 109. Estrutura alvo conceitual do app em fase intermediária

## EXEMPLO PROPOSTO, NÃO ESTADO ATUAL

```text
app/
└── src/main/java/br/com/raionorio/gambitol/
    ├── MainActivity.java
    │
    ├── ui/
    │   ├── game/
    │   ├── home/
    │   └── settings/
    │
    ├── data/             # somente quando existir persistência real
    │
    └── platform/         # somente se adapters Android justificarem
```

---

# 110. Estrutura alvo conceitual do engine em fase intermediária

## EXEMPLO PROPOSTO

```text
chess-engine/
└── src/main/java/br/com/raionorio/gambitol/engine/
    ├── board/
    ├── piece/
    ├── move/
    ├── game/
    └── rule/
```

Não criar todos simultaneamente.

---

# 111. O perigo do package `model`

`model` frequentemente mistura:

- dados de UI;
- dados de domínio;
- DTO;
- entidade;
- banco.

Evitar package global `model`.

Preferir colocar modelo junto ao contexto responsável.

---

# 112. O perigo do package `adapter`

“Adapter” possui significados diferentes:

- RecyclerView adapter;
- architecture adapter;
- mapper.

Nome de package precisa de contexto.

---

# 113. O perigo do package `service`

No Android, `Service` tem significado específico.

Não usar package `service` para classes genéricas de regra.

---

# 114. O perigo de `controller`

Se usarmos Views/ViewModel, não criar controller paralelo sem necessidade.

---

# 115. `repository` não é package obrigatório

Repository só existe se data architecture exigir.

---

# 116. `usecase` não é package obrigatório

Use cases só quando complexity/reuse justificar.

---

# 117. Package por feature para UI

Se no futuro houver:

```text
ui/game
ui/home
ui/settings
ui/history
```

cada feature pode manter:

- tela;
- state holder;
- adapters próprios.

Isso aumenta coesão.

---

# 118. Compartilhamento entre features

Se uma classe for usada por duas features:

não mover automaticamente para `common`.

Perguntar:

- é realmente conceito compartilhado?
- pertence ao domínio?
- pertence a um widget reutilizável?

---

# 119. Componentes visuais reutilizáveis

## FUTURO

Se houver vários widgets próprios:

considerar:

```text
ui/components
```

dentro do app.

Não criar módulo de design system com duas Views.

---

# 120. Tema do Gambitol

Resources relacionados ao theme podem continuar em:

```text
res/values/
res/values-night/
```

A organização detalhada fica em UI/UX.

---

# 121. Sons do Gambitol

## FUTURO

Se aprovados:

provavelmente:

```text
res/raw/
```

ou alternativa adequada.

Não colocar MP3 em `drawable`.

---

# 122. Dados estáticos do jogo

Regras não precisam de arquivo JSON por padrão.

Código Java é suficiente para conceitos fixos.

Se existir dataset:

avaliar `raw` ou `assets`.

---

# 123. Arquivos PGN de teste

## FUTURO

Se usarmos partidas reais como fixtures:

não misturar com assets de produção.

Podem ficar em resources de teste do engine.

Exemplo conceitual:

```text
src/test/resources/
```

---

# 124. Java Library source sets

Gradle Java plugin usa convencionalmente:

```text
src/main/java
src/test/java
```

e pode usar:

```text
src/test/resources
```

para recursos de teste.

Fonte:

https://docs.gradle.org/current/userguide/java_plugin.html

---

# 125. Local unit tests do Android

O Android coloca testes locais em:

```text
module/src/test/
```

Esses executam na máquina host e são rápidos.

Fonte:

https://developer.android.com/training/testing/local-tests

---

# 126. Instrumented tests

No app:

```text
app/src/androidTest/java/
```

Executam em dispositivo/emulador.

Não colocar testes de regra do cavalo aqui se não dependem de Android.

---

# 127. Testes de UI

Quando surgirem:

organizar por feature.

Exemplo conceitual:

```text
androidTest/java/br/com/raionorio/gambitol/ui/game/
```

---

# 128. Testes de integração app-engine

Podem ficar em `app/src/test` se forem JVM compatíveis.

Se exigirem Android:

`androidTest`.

Escolher ambiente mínimo necessário.

---

# 129. Regra de fixtures de teste

Fixtures não vão em `src/main`.

Dados usados apenas para teste não devem ser empacotados no app.

---

# 130. Build types

O Android possui por padrão conceitos como:

- debug;
- release.

Não criar source dirs `debug` e `release` até precisar de arquivos específicos.

Fonte:

https://developer.android.com/build/build-variants

---

# 131. Product flavors

## NÃO NECESSÁRIO AGORA

Não criar:

```text
free
paid
demo
pro
```

antes de existir estratégia real.

---

# 132. Debug-specific code

Se no futuro precisarmos:

```text
src/debug/
```

pode conter configuração exclusiva de debug.

Não espalhar `if (BuildConfig.DEBUG)` por todo o app se source set resolver melhor.

---

# 133. Release-specific resources

Somente se realmente divergirem.

---

# 134. Source set priority

A documentação Android define precedência entre:

- variant;
- build type;
- flavor;
- main.

Não manipular source sets sem compreender merge.

Fonte:

https://developer.android.com/build/build-variants

---

# 135. Namespace de teste

O Android Gradle Plugin possui test namespace.

Não alterar sem motivo.

Mudanças erradas podem fazer instrumented tests falharem.

---

# 136. Application ID não é pasta

Alterar package físico de uma classe não é necessariamente alterar `applicationId`.

Esses conceitos devem permanecer separados.

---

# 137. Nomes de classes

Detalhes completos em `06_PADROES_JAVA_E_ANDROID.md`.

Estruturalmente:

- PascalCase;
- um conceito claro por top-level class;
- arquivo com nome correspondente ao tipo público.

---

# 138. Nomes de packages Java

Minúsculos.

Evitar:

```text
br.com.raionorio.gambitol.UI.Game
```

Preferir:

```text
br.com.raionorio.gambitol.ui.game
```

---

# 139. Imports

Evitar wildcard quando prejudica clareza.

Regra detalhada ficará no documento 06.

---

# 140. Um arquivo por classe pública principal

Padrão normal Java.

Classes pequenas internas podem existir quando semanticamente ligadas.

---

# 141. Nested classes

Usar quando tipo só faz sentido dentro do dono.

Não usar para esconder desorganização.

---

# 142. Arquivos utilitários

Antes de criar classe estática utilitária:

verificar se método pertence naturalmente a algum conceito.

---

# 143. Builders

## FUTURO

Podem ser úteis para testes ou objetos complexos.

Não criar para objetos com construtor simples.

---

# 144. Factories

Mesma regra.

---

# 145. `constants`

Evitar package global de constantes.

Constante deve ficar próxima do contexto.

---

# 146. Strings de domínio

Não mover mensagens de regra para Android resources se motor precisa de enum/estado.

UI traduz significado para texto.

---

# 147. `R`

`R` pertence ao módulo Android.

Engine Java puro nunca referencia `R`.

---

# 148. Manifest

Somente `app` deve precisar no modelo inicial.

Engine puro não possui Manifest.

---

# 149. Namespace da engine

Como Java Library pura:

não possui Android namespace DSL.

Possui packages Java.

---

# 150. ApplicationId da engine

Não existe.

`applicationId` pertence a app instalável.

---

# 151. Artefato do engine

Java Library gera artefato de biblioteca JVM, não APK.

Não publicaremos esse artefato externamente inicialmente.

---

# 152. Artefato do app

Debug/release Android.

APK para testes locais.

AAB para Play Store futuramente.

---

# 153. Regra sobre código compartilhado

Motor é compartilhado conceitualmente porque é independente.

Não mover qualquer código “reutilizável” para engine.

Som, UI, Android não pertencem lá.

---

# 154. Regra de entrada no engine

Para um arquivo entrar no engine, perguntar:

> “Esse código ainda faria sentido se amanhã o motor fosse usado em um programa Java sem Android?”

Se não:

provavelmente pertence ao app.

---

# 155. Regra de entrada no app

Se depende de:

- Context;
- Activity;
- View;
- resources;
- lifecycle;
- Android storage;
- Play;

fica no app ou módulo Android futuro.

---

# 156. Regra de entrada em data futura

Se responsabilidade é:

- carregar;
- salvar;
- sincronizar;
- buscar dados;

avaliar data layer.

---

# 157. Regra de organização do histórico

Não criar package de histórico antes de decidir se histórico é:

- domínio;
- UI;
- persistência.

Pode haver partes nos três.

---

# 158. Regra de organização do timer

Timer pode envolver:

- domínio;
- UI;
- platform clock.

Não concentrar tudo em um package por nome de feature sem separar responsabilidade interna.

---

# 159. Regra de organização da IA

Futuramente, provavelmente não pertence à UI.

Pode virar:

- package do engine;
- módulo separado;

dependendo de complexidade.

Não decidir agora.

---

# 160. Regra de organização de multiplayer

Provavelmente app/data/network ou módulos futuros.

Não engine base.

---

# 161. Estrutura de documentos versus código

Não espelhar:

```text
05_REGRAS...
```

como package `rules` apenas porque existe documento.

Documentação e código têm granularidades diferentes.

---

# 162. Dependência de documentos

Arquivo novo relevante deve ser coerente com:

- arquitetura;
- estrutura;
- padrões;
- regras.

---

# 163. Criação de novo arquivo: checklist

Antes de criar arquivo:

- [ ] Qual responsabilidade?
- [ ] Em qual módulo?
- [ ] Precisa Android?
- [ ] Qual package?
- [ ] Já existe classe responsável?
- [ ] Nome foi aprovado se for importante?
- [ ] Precisa teste?
- [ ] É produção ou teste?
- [ ] Cria nova dependência?
- [ ] Estamos criando package vazio sem necessidade?

---

# 164. Criação de novo package: checklist

- [ ] Existem classes reais para agrupar?
- [ ] Possuem responsabilidade comum?
- [ ] O nome comunica domínio?
- [ ] Evita `utils/common/helpers`?
- [ ] Profundidade é necessária?
- [ ] Package-private será afetado?
- [ ] A navegação ficará melhor?

---

# 165. Criação de novo módulo: checklist

- [ ] Existe boundary importante?
- [ ] Precisa impedir dependências?
- [ ] Precisa build/test isolado?
- [ ] Benefício supera configuração?
- [ ] Nome foi aprovado?
- [ ] Documento 03 permite?
- [ ] `settings.gradle.kts` será atualizado?
- [ ] Dependências continuam acíclicas?

---

# 166. Movimentação de arquivo: checklist

- [ ] Por que mover?
- [ ] Imports serão atualizados?
- [ ] Manifest referencia classe?
- [ ] Testes usam package?
- [ ] Nome de package muda?
- [ ] Build será validado?
- [ ] Git reconhecerá rename?

---

# 167. Exclusão de arquivo: checklist

- [ ] É gerado?
- [ ] É template?
- [ ] Está referenciado?
- [ ] É necessário ao build?
- [ ] Há substituto?
- [ ] Build/teste passa após exclusão?

---

# 168. Regra: não editar `build/`

Nunca.

---

# 169. Regra: não editar `.gradle/`

Nunca como solução normal.

---

# 170. Regra: não mover Gradle Wrapper

Manter estrutura padrão.

---

# 171. Regra: não renomear `app` sem motivo

`app` é nome padrão claro.

Renomear adiciona custo sem valor atual.

---

# 172. Regra: não criar `src/java`

Android espera:

```text
src/main/java
```

seguir convenção padrão reduz configuração.

---

# 173. Regra: não customizar sourceSets sem necessidade

Gradle permite caminhos customizados.

Mas estrutura padrão é mais compreensível.

Preferir convenção.

---

# 174. Regra: não criar pasta Kotlin

O projeto é Java.

Se não há `.kt`, não criar `src/main/kotlin`.

Kotlin DSL dos arquivos Gradle não exige pasta Kotlin de produção.

---

# 175. Regra: não confundir Kotlin DSL

```text
build.gradle.kts
```

não transforma app em Kotlin.

---

# 176. Regra: Android resources só no app

Se engine continuar Java puro:

nenhum `res`.

---

# 177. Regra: documentação técnica não vai para resources

`res/raw` não é lugar de Markdown do projeto.

---

# 178. Regra: testes não vão no código de produção

Nenhum `Test` em `src/main` salvo caso extremamente específico que não é teste.

---

# 179. Regra: produção não deve depender de classe de teste

Direção errada.

---

# 180. Regra: test helpers ficam nos source sets de teste

Quando surgirem.

---

# 181. Estrutura e acessibilidade

Se recursos de acessibilidade forem textos:

strings.

Se descrições forem dinâmicas:

UI gera a partir de estado.

Não engine.

---

# 182. Estrutura e localização

Arquivos:

```text
values/
values-pt/
values-en/
```

podem surgir futuramente.

Não criar idiomas antes da decisão de produto.

---

# 183. Estrutura e tema noturno

Já há `values-night`.

Manter alinhado ao tema quando UI avançar.

---

# 184. Estrutura e dimensões de tela

Android permite qualifiers como:

```text
layout-sw600dp/
values-sw600dp/
```

Somente criar quando design adaptativo exigir.

Não antecipar tablet com pastas vazias.

---

# 185. Estrutura e densidade

Assets raster devem seguir densidades apropriadas quando necessário.

Vetores podem reduzir duplicação.

Detalhes no UI/UX.

---

# 186. Estrutura e ícone

Launcher icon continuará nos mipmaps gerados até identidade final ser aprovada.

---

# 187. Estrutura e splash

Android moderno possui SplashScreen API.

Assets/configuração serão organizados quando feature for implementada.

Não criar pasta “splash” arbitrária.

---

# 188. Estrutura e áudio

Se som curto:

avaliar resource.

Se sistema crescer:

package/controller Android apropriado.

Não engine.

---

# 189. Estrutura e vibração

Adapter Android.

Não motor.

---

# 190. Estrutura e notificações

## FUTURO

Se algum dia existir:

Android integration.

Não engine.

---

# 191. Estrutura e analytics

## FUTURO

Idealmente package/integration separado no app.

Não espalhar SDK por todas as telas.

---

# 192. Estrutura e billing

## FUTURO

Integração Android/Play.

Não engine.

---

# 193. Estrutura e crash reporting

## FUTURO

Configuração app/infrastructure.

Não engine.

---

# 194. Estrutura e rede

## FUTURO

Se multiplayer:

package/data/network ou módulo futuro.

Não criar agora.

---

# 195. Estrutura e banco

## FUTURO

Se Room:

- entities de banco;
- DAO;
- database;

ficam em data local.

Não confundir entidade Room com peça de domínio.

---

# 196. Modelo persistido versus domínio

Mesmo que dados pareçam iguais:

um objeto de persistência pode possuir preocupações diferentes.

Não anotar automaticamente classes do engine com annotations de Room.

Isso quebraria independência do motor.

---

# 197. Estrutura e serialização

Evitar adicionar annotations de biblioteca externa nas entidades do engine sem avaliar acoplamento.

Pode ser aceitável em certos casos, mas exige decisão.

---

# 198. Estrutura de adapters de mapping

Se persistência exigir DTO/Entity:

mapper fica na fronteira da data layer.

Não no motor.

---

# 199. Estrutura e build config

Build config específica de app:

`app/build.gradle.kts`.

Build config do engine:

arquivo próprio.

Não misturar dependencies.

---

# 200. Dependências por módulo

App:

- AndroidX;
- UI;
- engine;
- futuras integrações.

Engine:

- Java;
- testes;
- mínimo possível.

---

# 201. Version catalog e engine

Se biblioteca de teste for compartilhada:

version catalog pode fornecer alias.

Não declarar versão repetida em múltiplos lugares sem necessidade.

---

# 202. Estrutura e JUnit

## DECIDIDO E IMPLEMENTADO PARA O ENGINE

Os testes JVM do `:chess-engine` usam:

```text
JUnit Jupiter 6.1.3
```

O version catalog fornece os aliases utilizados pelo módulo.

A execução ocorre com:

```kotlin
useJUnitPlatform()
```

Essa decisão vale para o engine.

Ela não migra automaticamente os testes Android existentes do módulo `:app`.

---

# 203. Android instrumented tests

Dependências de Espresso/AndroidX ficam no app.

Nunca engine.

---

# 204. Estrutura e Robolectric

## PENDENTE

Se usado, pertence a testes locais do app.

Não precisa no motor puro.

---

# 205. Estrutura e screenshot tests

## FUTURO

Pertencem à UI/app.

Não engine.

---

# 206. Estrutura e benchmark

## FUTURO

Se performance virar preocupação, Android possui benchmark modules.

Não criar agora.

---

# 207. Estrutura e baseline profiles

## FUTURO

Não necessários na fundação.

---

# 208. Estrutura e NDK

## NÃO PLANEJADO AGORA

Nenhuma pasta:

```text
cpp/
jni/
jniLibs/
```

deve ser criada sem decisão.

---

# 209. Estrutura e Stockfish nativo

Se um dia Stockfish for considerado:

isso pode introduzir NDK/native code.

Será decisão futura grande.

---

# 210. Estrutura e licença de assets

Assets externos devem ter:

- origem;
- licença;
- permissão de uso.

Considerar documento auxiliar se necessário.

Não importar imagens aleatórias da internet.

---

# 211. Estrutura e autoria de assets

Se imagens próprias forem geradas:

guardar arquivos finais usados pelo app no resource adequado.

Arquivos-fonte grandes de design podem ficar fora do app ou em local dedicado se necessário.

---

# 212. Estrutura e mockup aprovado

A imagem de referência visual não precisa ser empacotada no APK.

Pode ficar em documentação/assets de projeto.

Não usar mockup como drawable do app.

---

# 213. Estrutura e screenshots Play Store

## FUTURO

Assets de marketing não pertencem necessariamente a `res/`.

Podem ficar em documentação/release assets separados.

Definir no documento 12/14.

---

# 214. Estrutura e arquivos de configuração secretos

Não versionar:

- keystore;
- passwords;
- tokens;
- secrets.

Estrutura segura será definida em release.

---

# 215. Estrutura e `.env`

Android não usa `.env` como padrão universal.

Não introduzir apenas por hábito de projetos web.

---

# 216. Estrutura e local.properties

Permanecer local.

---

# 217. Estrutura e keystore

## FUTURO

Não colocar em:

```text
app/src/main/
```

sem estratégia segura.

---

# 218. Estrutura e docs gerados

Se futuramente houver JavaDoc/report:

outputs gerados não devem misturar-se com docs escritos manualmente sem convenção.

---

# 219. Estrutura e cobertura de testes

Relatórios de coverage são build outputs.

Não versionar por padrão.

---

# 220. Estrutura e snapshots de teste

Se framework usar snapshots:

definir diretório conforme ferramenta.

Não inventar agora.

---

# 221. Estrutura e CI

## FUTURO

Workflows GitHub podem ficar:

```text
.github/workflows/
```

quando CI for aprovado.

Não faz parte do Android module.

---

# 222. Estrutura e templates de issue

## FUTURO

`.github/`

somente quando houver necessidade de colaboração/processo.

---

# 223. Estrutura e LICENSE

Antes de publicação pública/comercial, decidir licença do repositório.

Não escolher licença automaticamente.

---

# 224. Estrutura e CONTRIBUTING

Só necessário quando houver contribuidores externos ou processo que justifique.

---

# 225. Estrutura e CHANGELOG

## FUTURO

Pode surgir quando releases reais começarem.

---

# 226. Estrutura e versionamento do app

Configuração em app Gradle.

Não criar arquivo paralelo de versão sem necessidade.

---

# 227. Estrutura e scripts

Se scripts auxiliares surgirem:

avaliar pasta:

```text
scripts/
```

Somente quando houver mais de um ou utilidade clara.

---

# 228. Script temporário

Não guardar para sempre apenas porque resolveu um caso único.

---

# 229. Estrutura e ferramentas locais

Ferramenta que não é parte do produto pode ficar fora dos módulos de produção.

---

# 230. Regra de árvore atualizada

Quando estrutura mudar significativamente:

atualizar uma árvore de referência neste documento.

Não precisa atualizar por cada arquivo novo.

---

# 231. Árvore estrutural confirmada — estado pós-Fase 2

```text
gambitol/
│
├── .gitignore
├── README.md
│
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/
│       │   │   └── br/com/raionorio/gambitol/
│       │   └── res/
│       ├── test/
│       └── androidTest/
│
├── chess-engine/
│   ├── build.gradle.kts
│   └── src/
│       ├── main/
│       │   └── java/
│       │       └── br/com/raionorio/gambitol/engine/
│       │           └── Side.java
│       └── test/
│           └── java/
│               └── br/com/raionorio/gambitol/engine/
│                   └── SideTest.java
│
├── gradle/
│   ├── libs.versions.toml
│   └── wrapper/
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
└── gradlew.bat
```

A documentação `00–15` permanece fora do repositório por enquanto.

Esta árvore deve ser atualizada quando a estrutura mudar significativamente, não a cada novo arquivo de domínio.

---

# 232. Árvore normativa proposta — app quando UI crescer

```text
br.com.raionorio.gambitol/
│
├── MainActivity.java
│
└── ui/
    ├── game/
    ├── home/          # somente quando existir
    ├── history/       # somente quando existir
    └── settings/      # somente quando existir
```

---

# 233. Árvore normativa proposta — app com persistência futura

```text
br.com.raionorio.gambitol/
│
├── ui/
│   └── ...
│
├── data/
│   ├── local/
│   └── ...
│
└── platform/
    └── ...            # somente se necessário
```

---

# 234. Árvore normativa proposta — engine maduro

```text
br.com.raionorio.gambitol.engine/
│
├── board/
├── piece/
├── move/
├── game/
└── rule/
```

Novamente:

> isso é direção de crescimento, não comando para criar todas as pastas agora.

---

# 235. Regra para documentação de package

Quando package importante for criado:

registrar neste documento se ele representar boundary duradouro.

Não documentar cada subpackage transitório.

---

# 236. Regra para renomear package

Antes:

1. verificar referências;
2. atualizar declaração `package`;
3. atualizar imports;
4. atualizar Manifest se necessário;
5. build;
6. testes;
7. Git diff.

---

# 237. Regra para mover Activity

Activity registrada no Manifest pode usar nome relativo.

Após mover package:

verificar Manifest.

---

# 238. Regra para renomear módulo

Renomear módulo afeta:

- pasta;
- settings;
- project dependencies;
- paths Gradle;
- scripts.

Não fazer depois de crescimento sem motivo forte.

Por isso nome deve ser aprovado antes da criação.

---

# 239. Regra para namespace do engine proposto

Se usarmos Java Library:

não configurar Android namespace.

Package Java continua sendo suficiente.

---

# 240. Regra de domínio de nomes

Nenhum novo nome central será criado pelo tutor sem aprovação.

Exemplos:

- módulo;
- classe central;
- feature;
- package principal.

O tutor pode propor.

---

# 241. Nomes triviais

Não é necessário pedir aprovação para qualquer variável local.

A regra de aprovação vale para nomes com impacto estrutural/produto.

---

# 242. Estrutura e refatoração automática do Android Studio

Android Studio possui refactor/move.

Pode ser mais seguro que editar package manualmente em muitos arquivos.

Mas Git Bash continua útil para:

- confirmar diff;
- build;
- teste.

---

# 243. Git Bash para inspecionar estrutura

Comandos úteis:

```bash
find . -maxdepth 4 -type f | sort
```

ou:

```bash
find app/src -type f | sort
```

Explicar antes quando forem usados.

---

# 244. Git Bash para packages

Exemplo:

```bash
find app/src/main/java -type f | sort
```

Mostra arquivos Java reais.

---

# 245. Git Bash para detectar imports Android no engine

Futuramente:

```bash
grep -R "^import android\." -n chess-engine/src
```

Se retornar produção:

investigar boundary violado.

---

# 246. Validação estrutural por build

Depois de criar módulo:

```bash
./gradlew projects
```

pode ajudar a listar projetos Gradle.

Depois:

```bash
./gradlew build
```

ou tasks específicas conforme definido.

---

# 247. `./gradlew projects`

## CONCEITO

Mostra os subprojects reconhecidos pelo Gradle.

Útil para confirmar inclusão.

---

# 248. `./gradlew tasks`

Mostra tasks disponíveis.

Pode ensinar diferenças entre módulo app e Java library.

---

# 249. Build isolado do engine

Depois de criado:

uma task de teste do subproject deve validar independência.

Sintaxe exata dependerá do nome aprovado.

---

# 250. Build completo

Depois:

validar integração com app.

---

# 251. Estrutura e IntelliJ/Android Studio

Ao criar package:

usar IDE pode atualizar caminho e package corretamente.

Ao criar via Git Bash:

precisamos escrever diretório + declaração package corretamente.

Ambos são válidos.

---

# 252. Vibe coding estrutural

Para rapidez, Git Bash pode criar árvore.

Mas antes:

- nome aprovado;
- documento aprovado;
- estado limpo/conhecido.

Depois:

- revisar diff.

---

# 253. Não gerar dezenas de arquivos placeholder

Uma árvore bonita com arquivos vazios não é arquitetura implementada.

Criar código conforme feature.

---

# 254. Estrutura e commits

Cada mudança estrutural significativa deve ser isolável no Git.

Exemplo:

- criar módulo engine;
- mover primeira classe;
- adicionar teste.

Não combinar com redesign inteiro da UI no mesmo commit.

Detalhes em `07_GIT_WORKFLOW.md`.

---

# 255. Estrutura e refactoring safety

Antes de move massivo:

- testes;
- build;
- status Git.

Depois:

- build;
- testes;
- diff.

---

# 256. Estrutura e documentação de decisão

Se o módulo Java for aprovado:

registrar em `11_DECISOES_TECNICAS.md`.

Se package root for aprovado:

registrar se considerado relevante.

---

# 257. Critério de sucesso da estrutura inicial

A estrutura estará adequada quando:

- Android depende do engine;
- engine é Java puro;
- regras não estão na Activity;
- testes do engine são locais;
- UI está agrupada por feature;
- resources estão em diretórios corretos;
- não existem packages vazios desnecessários;
- não existe `utils` genérico;
- build passa;
- estrutura pode ser explicada.

---

# 258. Indicadores de desorganização futura

Revisar estrutura quando aparecer:

- 20+ classes no mesmo package sem coesão;
- `utils` crescente;
- package com classes de três camadas;
- imports circulares conceituais;
- Android entrando no engine;
- classes de banco no domínio;
- UI no data;
- testes difíceis de localizar;
- assets misturados;
- diretórios `old`, `backup`, `temp`;
- classes duplicadas.

---

# 259. Estratégia de refatoração de packages

Quando package crescer:

1. identificar grupos;
2. analisar dependências;
3. escolher boundary;
4. mover pequeno lote;
5. compilar;
6. testar;
7. repetir.

Não mover tudo de uma vez.

---

# 260. Estrutura e onboarding futuro

Um novo desenvolvedor deve conseguir olhar a árvore e entender:

```text
app = Android
engine = xadrez
docs = decisões
```

Se isso exigir apresentação de 40 minutos, estrutura está complexa demais para o estágio atual.

---

# 261. Estrutura e portfólio

A estrutura deve ser legível no GitHub.

Um recrutador deve perceber:

- separação;
- testes;
- documentação;
- build.

Sem precisar abrir 15 módulos com nomes abstratos.

---

# 262. Estrutura e ensino

Cada nova pasta relevante será oportunidade para aprender:

- módulo;
- package;
- source set;
- resource.

O tutor deve explicar no momento de criação.

---

# 263. 🎥 MOMENTO BOM PARA GRAVAR — módulo Java puro

Quando criarmos o motor separado:

**Tema:**  
“Como separar regras de negócio do Android em um jogo Java.”

**Mostrar:**  
árvore antes e depois.

**Conceito:**  
módulo Gradle + direção de dependência.

**Erro comum:**  
colocar tudo na Activity.

---

# 264. 🎥 MOMENTO BOM PARA GRAVAR — `src/test` vs `src/androidTest`

Quando criarmos os primeiros testes:

**Tema:**  
“Por que nem todo teste Android precisa de emulador.”

**Mostrar:**  
engine test em JVM e UI test no dispositivo.

---

# 265. 🎥 MOMENTO BOM PARA GRAVAR — package não é applicationId

Quando surgir confusão real:

**Tema:**  
“Package, namespace e applicationId não são a mesma coisa.”

Esse é um ponto que confunde bastante iniciante Android.

---

# 266. COMO EXPLICAR EM ENTREVISTA

Após implementação:

> “Estruturei o Gambitol com o módulo Android responsável pela plataforma e um módulo Java puro responsável pelo motor de xadrez. Isso torna a direção de dependências explícita, permite testes rápidos na JVM e evita que regras de negócio dependam de Activity, Context ou Views.”

---

# 267. Questões pendentes deste documento

## PENDENTE

As seguintes decisões continuam abertas:

1. estratégia inicial de packages do `app` quando novas telas surgirem;
2. renderer do tabuleiro;
3. navegação quando houver múltiplas telas;
4. persistência;
5. estrutura concreta do timer, caso entre no escopo;
6. localização final de `MainActivity` quando a arquitetura visual amadurecer;
7. estratégia de assets das peças;
8. integração futura da documentação em uma pasta `docs/`, caso seja aprovada.

## RESOLVIDO EM 2026-08-23

- nome do módulo: `chess-engine`;
- package base do engine: `br.com.raionorio.gambitol.engine`;
- módulo Java puro criado;
- direção `:app -> :chess-engine` implementada;
- JUnit Jupiter 6.1.3 configurado no engine;
- documentação extensa mantida fora do GitHub por enquanto.

---

# 268. O que NÃO está pendente

Já está estabelecido:

- package base do app;
- Java como linguagem principal;
- Android como plataforma;
- Gradle com Kotlin DSL;
- Git Bash;
- Android Studio;
- motor separado da UI;
- `:app` como módulo Android;
- `:chess-engine` como módulo Java puro;
- dependência `:app -> :chess-engine`;
- package `br.com.raionorio.gambitol.engine`;
- engine independente de Android;
- source sets padrão;
- testes do motor na JVM;
- JUnit Jupiter 6.1.3 no engine;
- Java source/target 17 no estado atual;
- JDK 21 executando o Gradle no estado atual.

---

# 269. Quality gate aplicado na criação do módulo engine

## CONCLUÍDO EM 2026-08-23

Antes:

- [x] build inicial Android passou;
- [x] `git status` conhecido;
- [x] nome do módulo aprovado;
- [x] package base aprovado;
- [x] arquitetura 03 aprovada;
- [x] estrutura 04 usada como referência.

Durante:

- [x] criar somente arquivos necessários;
- [x] configurar `settings.gradle.kts`;
- [x] configurar build do módulo;
- [x] adicionar dependência no app.

Depois:

- [x] `./gradlew projects`;
- [x] teste JVM real do módulo;
- [x] build do app;
- [x] `git diff`;
- [x] revisão de staging;
- [x] Pull Request;
- [x] merge na `main`;
- [x] validação pós-merge.

Validação final executada:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

Resultado:

```text
BUILD SUCCESSFUL
```

---

# 270. Quality gate para criar package

- [ ] responsabilidade clara;
- [ ] nome claro;
- [ ] possui arquivos reais;
- [ ] não duplica conceito;
- [ ] boundary melhora entendimento.

---

# 271. Quality gate para resources

- [ ] tipo de resource correto;
- [ ] nome minúsculo;
- [ ] não é asset de documentação;
- [ ] licença conhecida se externo;
- [ ] uso real no app.

---

# 272. Quality gate para testes

- [ ] ambiente correto;
- [ ] package corresponde;
- [ ] teste do domínio não está no instrumented sem motivo;
- [ ] dados de teste não entram em produção.

---

# 273. Quality gate para arquivos Gradle

- [ ] alteração necessária;
- [ ] versão compatível;
- [ ] fonte oficial consultada quando necessário;
- [ ] sync/build validado;
- [ ] diff revisado.

---

# 274. Fontes pesquisadas — estrutura Android

## Projects overview

https://developer.android.com/studio/projects

Base para:

- módulos;
- Android View versus Project View;
- estrutura do módulo;
- `src/main`;
- `src/test`;
- `src/androidTest`;
- `java`;
- `res`;
- `assets`;
- build files.

Verificado em: 2026-08-22.

---

# 275. Fontes — build e source sets

## Configure your build

https://developer.android.com/build

Base para:

- módulos;
- namespace;
- applicationId;
- source sets;
- build configuration.

---

## Configure build variants

https://developer.android.com/build/build-variants

Base para:

- `src/main`;
- `src/debug`;
- `src/release`;
- flavors;
- source set precedence;
- test source sets.

Verificado em: 2026-08-22.

---

# 276. Fontes — Manifest

## App manifest overview

https://developer.android.com/guide/topics/manifest/manifest-intro

Base para:

- localização;
- componentes;
- permissões;
- nomes de Activity;
- namespace.

Verificado em: 2026-08-22.

---

# 277. Fontes — modularização

## Guide to app modularization

https://developer.android.com/topic/modularization

Base para:

- módulo como boundary;
- encapsulamento;
- testabilidade;
- manutenção;
- cautela com granularidade.

---

## Common modularization patterns

https://developer.android.com/topic/modularization/patterns

Base para:

- coesão;
- acoplamento;
- feature modules;
- common modules;
- data modules;
- modularização adaptável.

Verificado em: 2026-08-22.

---

# 278. Fontes — Gradle multi-project

## Multi-Project Builds

https://docs.gradle.org/current/userguide/multi_project_builds.html

Base para:

- root project;
- subprojects;
- `settings.gradle.kts`;
- dependências entre projetos.

---

## Java Plugin

https://docs.gradle.org/current/userguide/java_plugin.html

Base para:

- `src/main/java`;
- `src/test/java`;
- estrutura convencional de biblioteca Java.

Verificado em: 2026-08-22.

---

# 279. Fontes — Java packages

## Java Language Specification 25 — Packages and Modules

https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html

Base para:

- packages;
- hierarquia;
- visibilidade;
- nomes qualificados;
- relação com diretórios.

---

## Java Language Specification — Names

Referência histórica de convenções:

https://docs.oracle.com/javase/specs/jls/se7/html/jls-6.html

Base para:

- package names minúsculos;
- nomes qualificados;
- tipos em PascalCase.

Verificado em: 2026-08-22.

---

# 280. Fontes — testes Android

## Fundamentals of testing Android apps

https://developer.android.com/training/testing/fundamentals

Base para:

- testes locais;
- instrumentados;
- decoupling;
- testabilidade.

---

## Local tests

https://developer.android.com/training/testing/local-tests

Base para:

- `src/test`;
- execução host/JVM.

---

## Espresso setup

https://developer.android.com/training/testing/espresso/setup

Base para:

- `src/androidTest`;
- instrumentação;
- execução no dispositivo.

Verificado em: 2026-08-22.

---

# 281. Fontes — recursos Android

## App resources overview

https://developer.android.com/guide/topics/resources/providing-resources

Base para:

- `res`;
- drawable;
- mipmap;
- values;
- raw;
- assets;
- alternative resources.

---

## Android Resources API

https://developer.android.com/reference/android/content/res/Resources

Base para:

- resources compilados;
- `R`;
- configuração;
- raw resources.

Verificado em: 2026-08-22.

---

# 282. Fontes — version catalog

## Migrate your build to version catalogs

https://developer.android.com/build/migrate-to-catalogs

Base para:

- `libs.versions.toml`;
- centralização de versões/dependências.

Verificado em: 2026-08-22.

---

# 283. Fontes — namespace e applicationId

## Configure your build

https://developer.android.com/build

## Prepare your library for release — namespace explanation

https://developer.android.com/build/publish-library/prep-lib-release

Base para:

- namespace;
- application identity;
- bibliotecas não possuem applicationId.

Verificado em: 2026-08-22.

---

# 284. Regra sobre fontes mutáveis

Android Studio, AGP e Gradle evoluem.

Antes de alterar:

- plugins;
- source sets;
- namespace;
- build DSL;
- test DSL;

consultar documentação atual.

Este documento registra estrutura conceitual e convenções, não congela sintaxe de ferramentas para sempre.

---

# 285. Resumo operacional

Quando surgir novo código:

```text
PRECISA ANDROID?
│
├── SIM
│   ↓
│  app
│   ↓
│  qual feature/responsabilidade?
│
└── NÃO
    ↓
É REGRA/ESTADO DO XADREZ?
    │
    ├── SIM → engine
    └── NÃO → avaliar responsabilidade
```

Quando surgir persistência:

```text
app/data
```

ou estrutura equivalente aprovada.

Quando surgir UI:

```text
app/ui/<feature>
```

Quando surgir teste:

```text
engine logic → engine/src/test
Android local → app/src/test
device/UI → app/src/androidTest
```

---

# 286. Regra norteadora da estrutura

> **Coloque cada arquivo no lugar cuja responsabilidade explica sua existência. Se para justificar a localização for necessário dizer “coloquei aqui porque era mais fácil”, pare e revise.**

---

# 287. Continuidade estrutural

O documento `05_REGRAS_DO_MOTOR_DE_XADREZ.md` já existe e permanece como fonte normativa para:

- tabuleiro;
- coordenadas;
- peças;
- movimentos;
- capturas;
- xeque;
- xeque-mate;
- afogamento;
- roque;
- en passant;
- promoção;
- empates;
- estados da partida;
- situações especiais;
- referência às regras oficiais FIDE;
- casos que precisarão de testes.

A relação entre os documentos permanece:

> **`04_ESTRUTURA_DO_PROJETO.md` define onde o motor vive e como o código é organizado.**

> **`05_REGRAS_DO_MOTOR_DE_XADREZ.md` define o que o motor deve saber fazer.**

Com a fronteira `:app -> :chess-engine` já implementada, o próximo crescimento estrutural deve acontecer apenas quando novos conceitos reais da Fase 3 exigirem arquivos, tipos ou packages adicionais.
