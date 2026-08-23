# 03 — ARQUITETURA DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `03_ARQUITETURA_DO_GAMBITOL.md`  
> **Versão:** 1.1  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-23  
> **Responsabilidade:** definir a arquitetura de alto nível do Gambitol, seus limites, responsabilidades, direção de dependências, fluxo de estado e princípios de evolução  
> **Fonte normativa para:** separação entre Android e motor de xadrez, fronteiras arquiteturais, dependências permitidas/proibidas, fluxo UI → aplicação → domínio → UI, tratamento arquitetural de estado, persistência futura, testes e modularização  
> **Não cobre em detalhe:** nomes definitivos de packages/classes, estrutura física completa de arquivos, implementação integral das regras do xadrez, padrão de commits, especificação visual detalhada, roadmap de entregas, monetização ou processo operacional da Play Store  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`  
> **Documento que detalhará a estrutura física:** `04_ESTRUTURA_DO_PROJETO.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo deste documento

Este documento responde:

- Quais são as grandes partes do Gambitol?
- Qual parte pode conhecer qual outra parte?
- Onde ficam as regras do xadrez?
- Qual componente é a fonte de verdade da partida?
- O que pertence ao Android e o que deve continuar Java puro?
- Como um toque na tela se transforma em uma jogada?
- Quem atualiza o estado?
- Como a interface descobre que o jogo mudou?
- Como o aplicativo deve reagir a recriação de tela e encerramento de processo?
- Onde persistência entrará?
- Onde IA entrará no futuro?
- Onde multiplayer entrará no futuro?
- Como evitar que `MainActivity` vire o sistema inteiro?
- Como manter a solução compreensível sem criar arquitetura teatral?

O documento NÃO tem o objetivo de antecipar cada classe.

A arquitetura deve estabelecer **fronteiras estáveis** e deixar detalhes locais evoluírem.

---

# 2. Decisões anteriores que esta arquitetura precisa respeitar

## DECIDIDO

A arquitetura parte das seguintes decisões já aprovadas:

| Item | Decisão |
|---|---|
| Produto | jogo de xadrez mobile |
| Plataforma inicial | Android |
| Linguagem principal | Java |
| Interface inicial | Android Views/XML |
| Package base | `br.com.raionorio.gambitol` |
| Build | Gradle |
| Terminal principal | Git Bash |
| IDE | Android Studio |
| Primeira modalidade | duas pessoas no mesmo aparelho |
| Objetivo futuro | publicação na Google Play Store |
| Objetivo educacional | aprender Java e Android durante o desenvolvimento |
| Regra arquitetural central | motor de xadrez separado da interface Android |

A arquitetura descrita aqui não pode apagar essas decisões por conveniência.

---

# 3. Restrições atuais conhecidas

## DECIDIDO

1. O motor deve ser utilizável sem depender de `Activity`, `View`, XML ou `Context`.
2. Regras do xadrez não devem estar espalhadas pela interface.
3. O projeto precisa permanecer ensinável.
4. A estrutura não deve ser mais complexa que o problema exige.
5. IA e multiplayer não fazem parte da fundação atual.
6. Persistência durável ainda não possui tecnologia escolhida.
7. O layout aprovado é referência de produto, não definição arquitetural completa.
8. O projeto foi criado inicialmente com um módulo Android `app`; qualquer modularização adicional deve ser feita conscientemente.
9. O build inicial deve ser validado antes de mudanças estruturais relevantes.

---

# 4. Princípios oficiais Android considerados

A documentação oficial do Android recomenda arquitetura em camadas, separação de responsabilidades, estado orientando a interface, fluxo unidirecional, `ViewModel` para estado de tela quando seus benefícios se aplicam, redução de dependências do framework Android e limites claros entre componentes.

O próprio Android ressalta que suas recomendações são **recomendações**, não leis, e precisam ser adaptadas ao aplicativo.

Isso é especialmente importante para o Gambitol porque:

- é um jogo;
- usa Java;
- usa Views/XML;
- possui um núcleo de domínio local complexo;
- não possui inicialmente rede ou banco;
- o objetivo educacional é explícito.

Portanto, não copiaremos uma arquitetura de aplicativo de notícias e trocaremos “Article” por “Pawn”. Arquitetura por cosplay continua sendo arquitetura ruim.

Fontes principais:

- Guide to app architecture  
  https://developer.android.com/topic/architecture
- Recommendations for Android architecture  
  https://developer.android.com/topic/architecture/recommendations
- Recommendations for Android architecture (Views)  
  https://developer.android.com/topic/architecture/views/recommendations-views

Verificado em: **2026-08-22**.

---

# 5. Qualidade arquitetural desejada

## DECIDIDO

A arquitetura deve favorecer:

- correção;
- clareza;
- testabilidade;
- manutenção;
- baixo acoplamento;
- alta coesão;
- previsibilidade;
- evolução;
- aprendizado;
- isolamento do Android onde ele não é necessário.

Não é objetivo:

- maximizar número de camadas;
- maximizar número de interfaces;
- maximizar número de módulos Gradle;
- aplicar todos os patterns conhecidos;
- reproduzir uma arquitetura empresarial de backend em um jogo mobile.

---

# 6. A ideia arquitetural central

A arquitetura do Gambitol será orientada por este princípio:

```text
┌──────────────────────────────────┐
│          CAMADA ANDROID          │
│                                  │
│  Tela • Views • Toque • Lifecycle│
│  Recursos • Navegação • Render   │
└────────────────┬─────────────────┘
                 │ comandos
                 ▼
┌──────────────────────────────────┐
│      ORQUESTRAÇÃO / ESTADO UI    │
│                                  │
│ coordena intenção do usuário     │
│ mantém/produz estado de tela     │
└────────────────┬─────────────────┘
                 │ operações
                 ▼
┌──────────────────────────────────┐
│       MOTOR DE XADREZ JAVA       │
│                                  │
│ partida • tabuleiro • regras     │
│ movimentos • turnos • resultado │
└──────────────────────────────────┘
```

Quando persistência surgir:

```text
          ┌──────────────────┐
          │  PERSISTÊNCIA    │
          │ arquivos/db/etc. │
          └────────┬─────────┘
                   │
                   ▼
             repositório/adaptador
```

A persistência não será colocada dentro das peças nem dentro da `Activity`.

---

# 7. Arquitetura adotada em nível 1

## DECIDIDO E IMPLEMENTADO

O Gambitol é organizado em dois grandes blocos técnicos:

### Bloco A — Aplicativo Android

Responsável por:

- inicialização Android;
- telas;
- Views;
- recursos;
- eventos de toque;
- lifecycle;
- estado de apresentação;
- mapeamento domínio → UI;
- persistência Android futura;
- integração com Play Store;
- integração futura com rede.

### Bloco B — Motor de xadrez

Responsável por:

- estado lógico da partida;
- tabuleiro;
- peças;
- movimentos;
- regras;
- validação;
- turnos;
- estados de fim;
- geração de movimentos legais;
- informações necessárias para histórico;
- regras especiais.

O motor NÃO é uma tela.

O aplicativo Android NÃO é a autoridade das regras.

---

# 8. Modularização física adotada

## DECIDIDO E IMPLEMENTADO

A fronteira arquitetural foi materializada em dois módulos Gradle:

```text
:app
:chess-engine
```

onde:

```text
:app
```

é o módulo Android e:

```text
:chess-engine
```

é uma biblioteca Java pura configurada com `java-library`.

A implementação foi integrada à `main` pelo Pull Request `#1`.

A validação pós-merge executou:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

com `BUILD SUCCESSFUL`.

### Por que isso foi adotado

O requisito “motor separado de Android” já existia independentemente da modularização.

Um módulo Java puro transforma essa regra em limite físico do build.

Como `:chess-engine` não possui dependência Android:

```java
import android.app.Activity;
```

não deveria sequer compilar dentro dele.

Isso é mais forte do que escrever em documentação:

> “por favor, não use Android aqui”.

---

# 9. Por que dois módulos não é necessariamente overengineering

O guia oficial de modularização Android aponta benefícios como:

- encapsulamento;
- testabilidade;
- reutilização;
- controle de dependências;
- manutenção.

Ele também alerta para granularidade excessiva.

O Gambitol não precisa nascer com:

```text
:core:model
:core:rules
:core:common
:feature:board
:feature:menu
:feature:timer
:data:local
:data:remote
:domain
...
```

Isso seria custo antes de necessidade.

Mas separar **uma única fronteira realmente importante** — aplicativo Android versus motor de xadrez Java — possui justificativa clara.

Fonte:

- Guide to Android app modularization  
  https://developer.android.com/topic/modularization
- Common modularization patterns  
  https://developer.android.com/topic/modularization/patterns
- Gradle Multi-Project Builds  
  https://docs.gradle.org/current/userguide/multi_project_builds.html

---

# 10. Alternativa considerada: um único módulo

## ALTERNATIVA

Manter tudo em:

```text
:app
```

e separar apenas por packages.

### Vantagens

- configuração mais simples;
- menos Gradle;
- menos arquivos;
- início mais rápido.

### Desvantagens

- Android fica disponível a todas as classes;
- separação é apenas disciplina;
- motor é menos reutilizável;
- fronteira arquitetural é mais fácil de violar.

---

# 11. Alternativa considerada: muitos módulos

## ALTERNATIVA REJEITADA NESTE MOMENTO

Criar módulos independentes para:

- UI;
- domínio;
- data;
- timer;
- histórico;
- peças;
- IA;
- feature.

### Por que não agora

- base ainda pequena;
- aumenta build;
- aumenta configuração;
- adiciona abstrações;
- aumenta custo didático;
- cria fronteiras antes de sabermos onde realmente doem.

A documentação oficial Android alerta que granularidade excessiva pode tornar modularização um fardo.

---

# 12. Decisão vigente sobre modularização

## DECIDIDO E IMPLEMENTADO

A estrutura inicial aprovada é:

```text
:app
:chess-engine
```

Criar novos módulos somente mediante necessidade comprovada.

O nome `chess-engine` foi aprovado e passou a ser fato estrutural do projeto.

Essa decisão está registrada em `11_DECISOES_TECNICAS.md`.

---

# 13. Regra de dependência entre módulos

## DECIDIDO E IMPLEMENTADO

A dependência entre módulos é:

```text
:app
  │
  └──── depende de ────> :chess-engine
```

Nunca:

```text
:chess-engine
  └──── depende de ────> :app
```

Direção:

```text
ANDROID → MOTOR
```

O motor não deve conhecer o aplicativo Android.

---

# 14. Dependências permitidas no motor

## DECIDIDO NO ESTADO ATUAL / REVISÁVEL

O motor pode depender de:

- Java;
- biblioteca de testes no source set de testes;
- pequenas bibliotecas Java puras somente se futuramente aprovadas e justificadas.

No estado atual:

- o código de produção do `:chess-engine` não possui dependência Android;
- os testes JVM usam JUnit Jupiter 6.1.3;
- dependências de produção adicionais não foram introduzidas.

A preferência continua sendo dependência de produção mínima.

---

# 15. Dependências proibidas no motor

## DECIDIDO COMO PRINCÍPIO

O motor não deve depender diretamente de:

- `android.*`;
- `androidx.activity.*`;
- `androidx.fragment.*`;
- `android.view.*`;
- `android.content.Context`;
- `android.widget.*`;
- XML;
- recursos `R`;
- `Activity`;
- `Fragment`;
- `ViewModel`;
- Play Services;
- banco Android;
- rede Android.

Se aparecer necessidade, primeiro investigar se a responsabilidade pertence realmente ao motor.

---

# 16. O motor não é a “domain layer” oficial do Android no sentido estrito

A documentação Android usa “domain layer” para uma camada opcional de use cases entre UI e data layer.

No Gambitol, “motor de xadrez” possui significado mais próximo de:

> núcleo de domínio do jogo.

Isso não é exatamente a mesma definição.

Para evitar confusão:

## Neste projeto

**Motor / núcleo do xadrez**

= regras e estado lógico do jogo.

**Domain layer Android**

= camada opcional de casos de uso entre UI e data, se um dia houver necessidade.

Não misturar os termos apenas porque ambos usam a palavra “domain”.

Fonte:

- Android Domain layer  
  https://developer.android.com/topic/architecture/domain-layer

---

# 17. Arquitetura lógica proposta

Sem escolher packages definitivos ainda, os papéis lógicos são:

```text
PRESENTATION
    ↓
APPLICATION / SCREEN STATE
    ↓
CHESS CORE
```

e, quando houver dados duráveis:

```text
PRESENTATION
    ↓
APPLICATION / SCREEN STATE
    ↓             ↘
CHESS CORE       DATA/PERSISTENCE
```

O fluxo exato será refinado conforme as features surgirem.

---

# 18. Presentation / UI

Responsabilidades:

- receber toque;
- renderizar tabuleiro;
- mostrar jogador atual;
- mostrar relógio;
- destacar seleção;
- destacar movimentos;
- mostrar mensagens;
- navegar;
- adaptar tamanhos;
- responder ao lifecycle;
- usar recursos Android.

Não deve:

- decidir se um cavalo pode mover;
- calcular xeque;
- calcular roque;
- alterar posição lógica de peça diretamente;
- manter uma segunda verdade do tabuleiro.

---

# 19. State holder da tela

## PROPOSTO

Utilizar `ViewModel` Android no nível da tela quando seus benefícios forem necessários.

A documentação oficial Android para Views mostra `ViewModel` em Java e destaca:

- gerenciamento de estado de tela;
- sobrevivência a mudanças de configuração;
- separação da Activity;
- exposição de estado observável.

Fonte:

- ViewModel overview (Views)  
  https://developer.android.com/topic/libraries/architecture/views/viewmodel

---

# 20. Por que ViewModel faz sentido no Gambitol

Uma partida pode estar em andamento quando:

- dispositivo gira;
- janela muda;
- Activity é recriada.

Se estado estiver apenas em campos da `Activity`, a recriação pode destruir a referência.

`ViewModel` fornece uma fronteira adequada para estado de tela de maior duração que a instância da Activity.

Mas:

> `ViewModel` NÃO substitui persistência durável.

A documentação oficial esclarece que `ViewModel` ajuda em mudanças de configuração, mas não é garantia de sobreviver a encerramento de processo.

---

# 21. ViewModel não deve virar motor

## DECIDIDO COMO PRINCÍPIO

A existência do `ViewModel` não muda a separação.

Errado:

```text
GameViewModel
├── movePawn()
├── validateBishop()
├── detectCheck()
├── detectCheckmate()
├── castle()
└── enPassant()
```

O `ViewModel` pode coordenar:

```text
receber ação
↓
pedir operação ao motor
↓
receber estado
↓
produzir estado de UI
```

As regras permanecem no motor.

---

# 22. Activity não deve virar controller gigante

A Activity é componente do framework Android.

Ela pode:

- configurar a tela;
- conectar Views;
- observar estado;
- encaminhar eventos;
- lidar com concerns estritamente de UI/lifecycle.

Ela não deve se tornar:

```text
MainActivity
├── regras
├── timer
├── persistência
├── histórico
├── engine
├── IA
└── navegação inteira
```

A documentação oficial recomenda manter entry points Android sem se tornarem fonte principal dos dados.

Fonte:

https://developer.android.com/topic/architecture

---

# 23. Fluxo unidirecional adaptado ao Gambitol

## PROPOSTO

O fluxo principal deverá seguir:

```text
USUÁRIO TOCA
     ↓
VIEW CAPTURA EVENTO
     ↓
STATE HOLDER RECEBE INTENÇÃO
     ↓
MOTOR PROCESSA
     ↓
NOVO ESTADO LÓGICO
     ↓
ESTADO DE UI É PRODUZIDO
     ↓
VIEW RENDERIZA
```

A tela não move uma peça e depois “avisa o motor”.

O motor decide o estado.

---

# 24. Por que fluxo unidirecional

A documentação Android recomenda Unidirectional Data Flow porque reduz ambiguidades sobre:

- origem da mudança;
- estado atual;
- quem atualiza quem.

No Gambitol isso é especialmente valioso.

Fluxo problemático:

```text
View move peça
Engine move peça
Activity corrige peça
Timer troca turno
ViewModel corrige timer
```

Agora existem várias autoridades.

Fluxo desejado:

```text
evento
↓
processamento
↓
novo estado
↓
render
```

Fontes:

- UI layer  
  https://developer.android.com/topic/architecture/ui-layer
- UI layer (Views)  
  https://developer.android.com/topic/architecture/views/ui-layer
- UI events (Views)  
  https://developer.android.com/topic/architecture/views/ui-layer/events-views

---

# 25. Fonte de verdade da partida

## DECIDIDO

O estado lógico da partida deve possuir uma única autoridade.

A interface visual NÃO é a fonte de verdade.

Se uma peça parece estar em `e4`, isso não significa que o modelo deve descobrir o estado olhando a View.

O fluxo é inverso:

```text
MOTOR DIZ:
peão está em e4
        ↓
UI DESENHA:
peão em e4
```

---

# 26. Estado lógico versus estado visual

## Estado lógico

Exemplos:

- posições das peças;
- jogador atual;
- direitos de roque;
- último movimento relevante;
- possibilidade de en passant;
- resultado;
- histórico lógico.

## Estado visual

Exemplos:

- casa selecionada;
- animação;
- brilho;
- menu aberto;
- scroll;
- orientação visual do tabuleiro.

Misturar ambos dificulta regras e testes.

---

# 27. Estado de UI derivado

## PROPOSTO

A tela pode consumir um modelo de apresentação que represente:

- casas;
- peças visíveis;
- seleção;
- casas permitidas;
- jogador atual;
- status;
- cronômetros;
- mensagens.

Esse estado deve ser produzido a partir de:

- estado lógico;
- estado visual necessário.

O nome exato da classe NÃO está definido aqui.

---

# 28. Imutabilidade na fronteira da UI

## PROPOSTO

Quando estado for exposto para renderização, preferir snapshots que a UI não possa alterar arbitrariamente.

Motivo:

A UI deve ler:

> “qual estado devo mostrar?”

e não receber acesso a estruturas internas mutáveis do motor.

A documentação Android recomenda dados expostos entre camadas de modo a evitar mutações externas inconsistentes.

---

# 29. Não expor tabuleiro interno mutável

Evitar API conceitual como:

```java
Piece[][] getBoard();
```

se a UI puder então executar:

```java
board[0][0] = null;
```

Isso fura o motor.

Preferir:

- leitura controlada;
- snapshot;
- consulta;
- estrutura imutável;
- cópia apropriada.

A forma concreta será decidida durante modelagem.

---

# 30. Comandos semânticos

A UI deve expressar intenção.

Exemplo conceitual:

```text
selecionar casa
tentar movimento
reiniciar partida
promover peça
```

e não operações estruturais:

```text
mover bitmap da posição X para Y
apagar View do peão
trocar array manualmente
```

A camada de UI fala em ações do usuário.

O motor fala em regras do jogo.

---

# 31. Fluxo de uma seleção

Exemplo arquitetural:

```text
1. toque em uma coordenada da View
2. UI converte coordenada visual → casa
3. state holder interpreta intenção
4. motor informa conteúdo/ações possíveis
5. estado de UI passa a conter seleção
6. View redesenha destaque
```

Nenhuma regra de movimento precisa estar no cálculo visual do toque.

---

# 32. Fluxo de uma jogada

```text
1. usuário toca origem
2. usuário toca destino
3. UI envia intenção de movimento
4. motor valida
5. se inválido:
       estado lógico não muda
       UI recebe feedback apropriado
6. se válido:
       motor aplica movimento
       motor atualiza turno/estado
7. apresentação obtém novo snapshot
8. UI renderiza
```

---

# 33. Fluxo de xeque

A UI não pergunta:

> “a View do rei está ameaçada?”

O motor determina:

- posição;
- ataques;
- estado.

A UI apenas recebe informação suficiente para comunicar.

---

# 34. Fluxo de promoção

Promoção tem uma peculiaridade:

há uma regra lógica e uma escolha de UI.

Arquitetura:

```text
movimento chega a casa de promoção
↓
motor identifica necessidade de escolha
↓
UI exibe opções
↓
usuário escolhe
↓
comando semântico retorna ao motor
↓
motor conclui estado
```

Não colocar modal Android dentro do motor.

---

# 35. Fluxo de roque

O motor precisa saber:

- rei moveu?
- torre moveu?
- caminho livre?
- rei está em xeque?
- atravessa casa atacada?
- destino atacado?

A UI apenas solicita movimento e mostra resultado.

---

# 36. Fluxo de en passant

O motor precisa do histórico/estado necessário para decidir elegibilidade.

Isso demonstra por que estado do jogo não é apenas:

```text
posição atual das peças
```

A arquitetura deve permitir estado histórico mínimo necessário.

Detalhes pertencem a `05_REGRAS_DO_MOTOR_DE_XADREZ.md`.

---

# 37. Renderização do tabuleiro

## PENDENTE DE DECISÃO DE UI

Existem pelo menos duas abordagens razoáveis:

### A. Grid de Views

64 elementos visuais.

### B. Custom View

Uma View desenha tabuleiro, peças e highlights.

A arquitetura não decide isso ainda.

O documento `09_UI_UX_GAMBITOL.md` e experimentos deverão considerar:

- clareza;
- acessibilidade;
- responsividade;
- performance;
- complexidade;
- aprendizado;
- animação.

---

# 38. Se Custom View for escolhida

A documentação Android mostra que uma Custom View pode:

- sobrescrever `onDraw(Canvas)`;
- pré-criar objetos `Paint`;
- recalcular dimensões em `onSizeChanged()`;
- tratar diferentes tamanhos;
- desenhar bitmaps e formas.

Arquiteturalmente:

> a Custom View deverá ser renderer/input adapter, não engine.

Fonte:

- Create a custom drawing  
  https://developer.android.com/develop/ui/views/layout/custom-views/custom-drawing

---

# 39. Touch mapping

Se uma View customizada for usada:

```text
MotionEvent (x,y)
↓
cálculo visual
↓
casa do tabuleiro
↓
ação semântica
```

A View conhece pixels.

O motor conhece casas.

O motor não conhece `MotionEvent`.

Fontes:

- Use touch gestures  
  https://developer.android.com/develop/ui/views/touch-and-input/gestures
- MotionEvent  
  https://developer.android.com/reference/android/view/MotionEvent

---

# 40. Responsividade do tabuleiro

A documentação Android recomenda que custom views não façam suposições rígidas sobre tamanho, densidade ou proporção.

Se houver desenho customizado:

- tamanho real vem do layout;
- coordenadas devem ser calculadas a partir da área disponível;
- não hardcodar “cada casa = 120 px”.

Isso é princípio de UI, mas afeta arquitetura do renderer.

---

# 41. Regra sobre pixels no domínio

## DECIDIDO

O motor nunca deve receber:

```text
x = 742 px
y = 918 px
```

O motor recebe conceito de domínio:

```text
casa
posição
movimento
```

Conversão de pixels pertence à UI.

---

# 42. Regra sobre recursos Android no domínio

## DECIDIDO

O motor não deve retornar:

```java
R.drawable.white_knight
```

Ele pode retornar:

```text
tipo = KNIGHT
cor = WHITE
```

A UI decide qual recurso visual representa isso.

---

# 43. Regra sobre strings Android no domínio

O motor não deve depender de:

```java
context.getString(...)
```

Para mensagens, preferir:

- códigos/estados;
- tipos;
- resultados;
- dados estruturados.

A UI localiza e apresenta.

---

# 44. Regra sobre tempo

Cronômetro será uma preocupação interessante.

## PROPOSTO

Separar:

- medição/regra de tempo;
- atualização visual do relógio.

O domínio da partida pode precisar conhecer tempo lógico quando relógio fizer parte das regras.

A UI precisa apenas renderizar.

Não usar o texto `"08:24"` como fonte de verdade do relógio.

---

# 45. Fonte de tempo injetável

## FUTURO / PROPOSTO

Se o relógio afetar lógica, evitar chamar relógio do sistema em todos os lugares.

Uma abstração de fonte de tempo pode permitir:

- testes determinísticos;
- pausar;
- simular passagem de tempo.

Não criar essa abstração antes da implementação do timer ser definida.

---

# 46. Thread principal

## DECIDIDO COMO PRINCÍPIO

Operações de UI precisam respeitar a main thread.

O motor local de xadrez convencional provavelmente será rápido para jogadas humanas.

Não criar threading complexo sem medir.

---

# 47. IA e threads

## FUTURO

Busca de IA pode ser custosa.

Quando IA surgir:

```text
UI
↓
solicita jogada
↓
execução fora da main thread
↓
resultado
↓
estado
↓
UI
```

Nunca bloquear a interface com busca profunda.

A estratégia exata dependerá da implementação de IA.

---

# 48. Persistência: situação atual

## PENDENTE

Ainda não há tecnologia de persistência aprovada.

Necessidades potenciais:

- partida em andamento;
- configurações;
- histórico;
- preferências visuais;
- estatísticas.

Arquitetura não escolhe banco antes de definir dados.

---

# 49. Data layer futura

Quando persistência aparecer, seguir princípio Android:

```text
UI / state holder
       ↓
repository
       ↓
data source
```

A UI não deve escrever diretamente em:

- arquivo;
- SharedPreferences;
- DataStore;
- Room;

se a persistência se tornar parte relevante da aplicação.

Fonte:

- Android Data layer  
  https://developer.android.com/topic/architecture/data-layer

---

# 50. Repositório só quando houver dado para persistir

A recomendação oficial Android é forte sobre data layer e repositories.

Mas arquitetura deve ser adaptada.

No estado atual do Gambitol:

- o núcleo do valor é motor em memória;
- persistência ainda não existe.

Não criaremos um `Repository` vazio apenas para a palavra aparecer no diagrama.

Quando fonte de dados real surgir, a abstração ganha motivo.

---

# 51. Persistência da partida versus estado de configuração

Distinguir:

## Mudança de configuração

Exemplo:

- rotação;
- recriação rápida de Activity.

`ViewModel` pode ajudar.

## Process death

Android encerra processo.

`ViewModel` não é armazenamento durável.

## Fechamento voluntário / retomada posterior

Requer persistência se quisermos restaurar partida.

Esses problemas não têm a mesma solução.

---

# 52. SavedStateHandle

A documentação Android oferece `SavedStateHandle` para estado associado ao `ViewModel` que precisa ser recriado após process death.

## PROPOSTO

Usá-lo apenas para estado leve quando fizer sentido.

Não serializar automaticamente toda a arquitetura dentro de um Bundle sem avaliação.

Fonte:

- SavedStateHandle  
  https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle
- Save UI states  
  https://developer.android.com/topic/libraries/architecture/saving-states

---

# 53. Snapshot de partida

## PROPOSTO COMO CONCEITO

O motor deverá poder, em algum momento, representar estado suficiente para:

- salvar;
- restaurar;
- testar.

Não estamos definindo o formato.

Possibilidades futuras:

- objeto de snapshot;
- FEN + metadados;
- estrutura própria;
- histórico de movimentos.

A decisão precisa respeitar roque, en passant, turno e demais estados.

---

# 54. FEN e PGN

## PENDENTE

FEN e PGN são formatos conhecidos no ecossistema do xadrez.

Não decidir agora que serão persistência oficial.

Eles poderão ser avaliados no documento de regras/persistência.

Não confundir:

- representação de posição;
- registro de partida;
- modelo interno.

---

# 55. Dependência de persistência

O motor não deve precisar saber:

> “estou sendo salvo no Room”.

Ideal:

```text
motor expõe estado
↓
adapter converte
↓
persistência salva
```

Ao restaurar:

```text
persistência lê
↓
adapter converte
↓
motor é reconstruído
```

---

# 56. Interface de persistência

Não definir interface prematuramente.

Mas princípio:

> dependências de armazenamento não devem infiltrar-se nas entidades de xadrez.

Nenhuma `Piece` deveria precisar de DAO.

---

# 57. DataStore

## FUTURO / CANDIDATO

DataStore pode ser adequado para:

- preferências;
- configurações pequenas.

Não é automaticamente a escolha para histórico complexo de partidas.

A documentação oficial recomenda mantê-lo na data layer.

Fonte:

https://developer.android.com/topic/libraries/architecture/datastore

---

# 58. Banco de dados

## FUTURO / PENDENTE

Room ou outra solução só deve entrar se houver requisito como:

- muitas partidas;
- consultas;
- estatísticas;
- histórico estruturado.

Não instalar banco “porque apps profissionais usam banco”.

---

# 59. Rede

## FUTURO

O MVP local não precisa de rede.

Se multiplayer surgir:

arquitetura terá novo boundary externo.

```text
Android App
   ↓
Network Adapter
   ↓
Backend
```

O motor local continua responsável por regras.

---

# 60. Multiplayer não deve substituir validação local

## FUTURO / PRINCÍPIO

Mesmo com servidor autoritativo futuro, cliente precisa de modelo consistente para:

- UX;
- pré-validação;
- renderização;
- replay.

A autoridade final online será decisão futura.

---

# 61. IA

## FUTURO

A IA deverá consumir uma interface lógica do motor.

Ela não deve:

- ler pixels;
- clicar Views;
- interpretar recursos gráficos.

Fluxo:

```text
estado do motor
↓
geração de movimentos
↓
busca/avaliação
↓
movimento escolhido
↓
motor aplica
```

---

# 62. Testabilidade como driver arquitetural

## DECIDIDO

A arquitetura deve permitir testar:

- peça;
- movimento;
- regra;
- estado;
- resultado;

sem:

- Android Studio UI;
- emulador;
- Activity;
- dispositivo.

Essa é uma das maiores justificativas para motor Java puro.

---

# 63. Pirâmide de testes arquitetural

Conceitualmente:

```text
            UI / instrumentados
                 poucos
               /      \
          integração Android
             moderados
           /            \
        TESTES DO MOTOR
           numerosos
```

A estratégia detalhada ficará em `08_TESTES_E_QUALIDADE.md`.

---

# 64. Fakes e test doubles

Quando data layer surgir, a documentação Android recomenda fakes para testes em muitos casos.

Arquitetura deve facilitar substituição de dependências externas.

Não introduzir mocking framework sem necessidade.

Fonte:

https://developer.android.com/topic/architecture/recommendations

---

# 65. Manual dependency injection inicialmente

## PROPOSTO

Para o tamanho inicial:

preferir constructor injection/manual injection.

Não adicionar Hilt apenas para criar um objeto do motor.

A documentação Android recomenda Hilt quando complexidade justificar, e manual DI pode ser suficiente em apps simples.

Fonte:

https://developer.android.com/topic/architecture/recommendations

---

# 66. Quando Hilt pode fazer sentido

## FUTURO

Avaliar quando houver:

- vários ViewModels;
- múltiplos repositories;
- navegação complexa;
- WorkManager;
- muitas dependências compartilhadas.

Até lá:

simplicidade vence.

---

# 67. Single Activity

A recomendação Android moderna favorece single-activity em apps com várias telas.

## PENDENTE

O Gambitol ainda não precisa congelar estratégia de navegação.

Se o aplicativo tiver:

- menu;
- partida;
- configurações;
- histórico;

poderemos avaliar:

- single Activity + fragments/navigation;
- outras abordagens compatíveis com Views.

Não decidir só porque uma recomendação geral existe.

Fonte:

https://developer.android.com/topic/architecture/recommendations

---

# 68. Navegação não é regra de xadrez

Qualquer solução:

- Activity;
- Fragment;
- Navigation Component;

permanece na camada Android.

Motor não conhece:

- rota;
- destination;
- back stack.

---

# 69. Estado de navegação

UI pode decidir:

> “partida terminou, mostrar resultado”.

Motor decide:

> “resultado = xeque-mate”.

Separar fato do domínio da ação visual.

---

# 70. Eventos versus estado

A documentação Android destaca diferença:

- eventos acontecem;
- estado existe.

No Gambitol:

Evento:

```text
usuário tocou e4
```

Estado:

```text
e4 está selecionada
```

Evento:

```text
jogada aplicada
```

Estado:

```text
turno = BLACK
```

Essa distinção reduz “eventos perdidos” e UI inconsistente.

Fonte:

- UI State production  
  https://developer.android.com/topic/architecture/ui-layer/state-production

---

# 71. Mensagens transitórias

Exemplo:

> “Movimento inválido.”

Pode ser tratado como estado de UI/efeito.

A implementação exata dependerá de Views.

Não fazer motor chamar `Toast`.

---

# 72. Erros de domínio versus falhas técnicas

## Erro/regra esperada

- movimento ilegal;
- seleção inválida;
- promoção pendente.

## Falha técnica

- estado corrompido;
- persistência falhou;
- recurso ausente.

Não misturar os dois.

---

# 73. Resultado de operação

## PROPOSTO

Operações do motor devem permitir distinguir claramente:

- sucesso;
- rejeição por regra;
- estado resultante.

Forma exata da API será definida durante modelagem.

Evitar exceção como controle normal para uma jogada ilegal sem justificativa.

---

# 74. Exceções no motor

Exceções podem representar:

- violação de contrato;
- estado impossível;
- erro de programação.

Movimento ilegal é comportamento esperado do usuário.

A arquitetura deve incentivar essa distinção.

---

# 75. Invariantes

O motor deve proteger invariantes.

Exemplos conceituais:

- coordenadas válidas;
- uma casa não contém duas peças;
- turno pertence a uma cor válida;
- estado final não continua recebendo jogadas sem regra definida.

Entidades não devem permitir mutação arbitrária externa.

---

# 76. Encapsulamento arquitetural

Encapsulamento não é só `private`.

É controlar o que cada bloco expõe.

O módulo de engine deverá expor apenas API necessária.

Detalhes internos podem mudar sem quebrar Android.

---

# 77. Public API do motor

## PENDENTE

A API pública exata será definida em `04` e `05`.

Princípio:

UI não deve conhecer detalhes demais.

Desejável:

```text
consultar estado
tentar ação
receber resultado
```

e não:

```text
manipular internamente listas de peças
```

---

# 78. Classes conceituais NÃO são nomes aprovados

Este documento pode usar rótulos como:

```text
Game
Board
Move
Position
Piece
GameState
```

apenas para explicar arquitetura.

Esses rótulos NÃO autorizam criar classes com esses nomes.

Nomes definitivos serão definidos antes da implementação e documentados em `04_ESTRUTURA_DO_PROJETO.md`.

---

# 79. Organização por responsabilidade

Quando estrutura física for criada:

- alta coesão;
- baixo acoplamento;
- dependências explícitas.

A documentação Android de modularização e arc42 enfatizam responsabilidades e limites compreensíveis.

Fontes:

- https://developer.android.com/topic/modularization/patterns
- https://docs.arc42.org/section-5/

---

# 80. C4 como linguagem de diagrama

## PROPOSTO

Usar conceitos do C4 apenas onde agregarem valor.

Para Gambitol:

### Diagrama de contexto

Útil para:

- usuário;
- Gambitol;
- Android/Play;
- sistemas futuros.

### Diagrama de container

Talvez simples, porque inicialmente o sistema é basicamente app mobile.

### Diagrama de componentes

Útil para mostrar:

- Android;
- state holder;
- motor;
- persistência futura.

Não criar diagramas de classe completos por rotina.

Fonte:

https://c4model.com/diagrams

---

# 81. Diagrama de contexto inicial

```text
┌─────────────────┐
│    JOGADOR      │
└────────┬────────┘
         │ joga
         ▼
┌─────────────────────────────┐
│          GAMBITOL           │
│ Android chess mobile game   │
└──────────────┬──────────────┘
               │ distribuição
               ▼
       ┌─────────────────┐
       │  GOOGLE PLAY    │
       └─────────────────┘
```

Futuros sistemas, como backend, não aparecem porque ainda não existem.

---

# 82. Diagrama de construção interna inicial

```text
┌──────────────────────────────────────┐
│              GAMBITOL                │
│                                      │
│  ┌───────────────────────────────┐   │
│  │          ANDROID APP          │   │
│  │                               │   │
│  │ UI → state holder → adapters  │   │
│  └───────────────┬───────────────┘   │
│                  │                   │
│                  ▼                   │
│  ┌───────────────────────────────┐   │
│  │       CHESS ENGINE JAVA       │   │
│  │                               │   │
│  │ estado + regras + movimentos  │   │
│  └───────────────────────────────┘   │
└──────────────────────────────────────┘
```

---

# 83. Arc42 como referência de documentação arquitetural

Arc42 recomenda uma Building Block View que mostre decomposição estática e responsabilidades.

Aplicação:

- documentar nível 1;
- aprofundar apenas blocos relevantes;
- não tentar documentar cada classe;
- mapear arquitetura para código no documento 04.

Fonte:

https://docs.arc42.org/section-5/

---

# 84. Regra de diagramas

Todo diagrama importante deve informar:

- objetivo;
- escopo;
- significado das setas;
- status;
- data quando conteúdo puder mudar.

Não manter diagrama bonito e falso.

---

# 85. Runtime view: jogada normal

```text
Jogador
  │
  │ toque
  ▼
UI Android
  │
  │ intenção
  ▼
State holder
  │
  │ comando
  ▼
Motor
  │
  │ valida/aplica
  ▼
Novo estado
  │
  ▼
State holder
  │
  │ estado de UI
  ▼
UI renderiza
```

---

# 86. Runtime view: jogada inválida

```text
toque
↓
tentativa de movimento
↓
motor valida
↓
rejeita sem alterar estado lógico
↓
apresentação produz feedback
↓
UI informa usuário
```

Regra:

> rejeição não deve deixar estado parcialmente alterado.

---

# 87. Atomicidade lógica do movimento

## PROPOSTO

Aplicar movimento deve ser conceitualmente atômico:

ou:

- movimento completo e estado consistente;

ou:

- nenhum movimento.

Evitar:

```text
move peça
↓
descobre que rei ficou em xeque
↓
tenta consertar manualmente
```

sem mecanismo controlado.

A implementação pode simular/cópia/desfazer, mas a API deve preservar consistência.

---

# 88. Simulação de movimento

Xeque e legalidade podem exigir simulação.

## PENDENTE

Estratégias possíveis:

- copiar estado;
- aplicar/desfazer;
- representação imutável;
- snapshot.

A decisão será tomada na modelagem do motor.

Arquitetura exige apenas:

> simulação não pode corromper partida real.

---

# 89. Undo

## FUTURO / PENDENTE

O mockup possui undo.

Se for feature:

precisa decisão de produto e regra.

Arquiteturalmente, histórico consistente facilitará implementação.

Mas não moldar toda arquitetura agora apenas por esse botão.

---

# 90. Histórico

Histórico possui dois papéis:

## Domínio

Sequência de movimentos necessária para regras/registro.

## UI

Lista/notação apresentada.

Não confundir os dois.

---

# 91. Notação

## FUTURO / PENDENTE

Notação algébrica pode ser gerada a partir do movimento e contexto.

Não armazenar somente string de UI como verdade do movimento.

---

# 92. Orientação do tabuleiro

Visualmente, brancas podem ficar embaixo ou tabuleiro pode girar.

Isso é UI.

Estado lógico das coordenadas não deve mudar apenas porque desenho foi invertido.

---

# 93. Coordenadas internas

## PENDENTE

Escolher representação:

- 0..7;
- file/rank;
- enum;
- objeto.

Qualquer escolha deve permitir conversão clara.

UI deve possuir adaptador entre coordenada visual e lógica.

---

# 94. Dependências de regra

Uma peça não deve receber Activity para saber se pode mover.

Regras dependem de:

- estado do jogo;
- posição;
- peças;
- histórico necessário.

Isso mantém domínio testável.

---

# 95. Evitar circular dependencies

Exemplo ruim:

```text
Board depende de Piece
Piece depende de Game
Game depende de Board
```

Nem toda referência circular conceitual é erro, mas dependências reais precisam ser controladas.

A modelagem definirá direção.

---

# 96. Evitar God Object

Possível risco:

uma classe “Game” fazer:

- regras;
- persistência;
- timer;
- renderização;
- som;
- IA;
- histórico;
- navegação.

Arquitetura deve dividir responsabilidades quando complexidade surgir.

---

# 97. Evitar anemic model por dogma inverso

Também não criar 40 serviços para uma entidade que poderia possuir comportamento natural.

Equilíbrio será decidido pelo código real.

---

# 98. Separation of concerns

Android recomenda limites claros.

No Gambitol, concerns principais:

- domínio do xadrez;
- apresentação;
- plataforma;
- persistência;
- infraestrutura externa.

Cada um muda por motivos diferentes.

Esse é bom sinal de separação.

---

# 99. Razões de mudança

Pergunta arquitetural útil:

> “Por que esta classe mudaria?”

Se uma classe mudaria quando:

- regra FIDE muda;
- layout muda;
- Play Store muda;
- banco muda;

ela provavelmente mistura responsabilidades diferentes.

---

# 100. UI model versus domain model

## PROPOSTO

Não obrigar UI a usar todas as entidades internas diretamente.

Exemplo:

A UI pode precisar:

- símbolo;
- cor;
- seleção;
- highlight.

O domínio precisa:

- tipo;
- cor;
- posição/regras.

Mapeamento pode existir quando necessário.

---

# 101. Não duplicar sem motivo

Modelos separados por camada possuem custo.

Criar apenas quando:

- responsabilidades divergem;
- mutabilidade diverge;
- dependência precisa ser isolada;
- formato de UI é diferente.

---

# 102. Mapping

Se houver modelos separados:

mapping deve ficar na fronteira.

Não espalhar conversão pelo app inteiro.

---

# 103. Estado observável para Views

A documentação Android para Views permite estado observável via `LiveData` ou outros holders.

## PROPOSTO

Para Java + Views, avaliar `LiveData` como opção inicial pragmática.

Não adotar Flow/coroutines apenas porque exemplos modernos Kotlin usam.

Fonte:

https://developer.android.com/topic/architecture/views/ui-layer

---

# 104. Por que LiveData pode ser adequado

- API Java;
- lifecycle-aware;
- integração natural com ViewModel;
- suficiente para estado de tela relativamente simples.

## PENDENTE

A decisão final será registrada quando implementarmos o primeiro state holder.

---

# 105. Por que não escolher Flow agora

Flow é excelente no ecossistema Kotlin.

O projeto é Java.

Adicionar Kotlin apenas para infraestrutura de estado contraria o objetivo atual sem necessidade comprovada.

Isso não é crítica a Flow.

É adequação ao contexto.

---

# 106. View Binding

## FUTURO / CANDIDATO

View Binding pode reduzir `findViewById` e fornecer referências tipadas.

A escolha pertence a padrões/UI.

Não é requisito da arquitetura.

---

# 107. Data Binding

## NÃO DECIDIDO

Data Binding adiciona outra camada de expressão e build.

Não adotar automaticamente.

Para um projeto educacional em Java/Views, simplicidade pode ser mais valiosa.

---

# 108. RecyclerView

Se histórico de jogadas crescer, RecyclerView pode ser adequado.

Isso é implementação da UI.

Não faz parte do motor.

---

# 109. Sons

Som é concern de apresentação/plataforma.

Motor pode emitir estado/evento semântico.

Não chamar `MediaPlayer` dentro de regra.

---

# 110. Vibração/haptics

Mesmo princípio.

UI interpreta ação e fornece feedback.

Motor não depende de hardware.

---

# 111. Animação

Animação move representação visual.

O estado lógico pode já ter sido atualizado.

Precisamos evitar que animação vire fonte de verdade.

---

# 112. Animação e input

## FUTURO

Se animação estiver em andamento:

decidir se input é bloqueado ou enfileirado.

Essa é lógica de UI/interação, não regra FIDE.

---

# 113. Acessibilidade

Se custom board for escolhido, acessibilidade precisa ser considerada arquiteturalmente.

Uma View única desenhada no Canvas pode exigir trabalho extra para:

- elementos virtuais;
- descrições;
- foco;
- navegação acessível.

Isso será fator na decisão Grid vs Custom View.

---

# 114. Testabilidade da UI

A UI deve permitir validar:

- estado renderizado;
- ações encaminhadas.

Não duplicar lógica de xadrez em testes UI.

O motor já terá testes próprios.

---

# 115. Lifecycle

Android components são temporários.

O motor lógico não deve depender do lifecycle.

State holder faz a ponte.

Fonte:

- Handle lifecycles with lifecycle-aware components (Views)  
  https://developer.android.com/topic/architecture/views/lifecycle-views
- Activity lifecycle  
  https://developer.android.com/guide/components/activities/activity-lifecycle

---

# 116. Context

Regra:

> Context deve permanecer próximo da plataforma.

Se uma classe central pede `Context`, investigar.

Talvez ela esteja:

- carregando recurso;
- acessando armazenamento;
- usando serviço Android.

Essas responsabilidades podem pertencer a adapter.

---

# 117. Resources

Motor não deve carregar:

- cor;
- string;
- drawable.

UI faz mapping.

---

# 118. Build configuration

Arquitetura também depende de build boundaries.

Como o engine é um módulo Java puro:

- testes JVM rápidos;
- dependência clara;
- build independente do Android em muitas tarefas.

Gradle suporta multi-project builds e project dependencies.

Fonte:

https://docs.gradle.org/current/userguide/multi_project_builds.html

---

# 119. Version Catalog

O projeto atual já possui `gradle/libs.versions.toml` segundo o estado mostrado no Git.

Isso é compatível com múltiplos módulos.

O Android recomenda version catalogs para centralizar dependências em projetos que crescem.

Não precisamos alterar isso agora.

Fonte:

https://developer.android.com/build/migrate-to-catalogs

---

# 120. Regra sobre dependências externas

Antes de adicionar:

- propósito;
- licença;
- manutenção;
- tamanho;
- alternativas;
- impacto de testes;
- impacto de Android minimum API;
- impacto no motor puro.

Dependência no motor merece atenção extra.

---

# 121. Engine externa de xadrez

## FUTURO / PENDENTE

Não utilizar engine pronta para substituir o motor que é parte central do objetivo educacional.

Uma engine externa pode ser avaliada futuramente para:

- IA;
- benchmarking;
- validação cruzada.

Mas não deve remover o aprendizado das regras.

---

# 122. Stockfish

## FUTURO / NÃO DECIDIDO

Stockfish pode existir como possível engine de IA/validação futura.

Não integrar agora.

Além de arquitetura, qualquer integração precisa considerar:

- licença;
- distribuição;
- NDK/native se aplicável;
- tamanho;
- interface;
- objetivo educacional.

---

# 123. Regra de arquitetura para monetização

## FUTURO

Monetização deve entrar como integração externa.

Ela não deve alterar regras do motor.

Exemplo:

```text
billing/ad
↓
camada Android/infraestrutura
```

Não colocar SDK de anúncios dentro de engine.

---

# 124. Analytics

## FUTURO

Mesma regra:

- eventos de produto podem ser observados;
- engine não depende diretamente de SDK de analytics.

Idealmente adapters na borda.

---

# 125. Crash reporting

## FUTURO

Ferramenta de crash pode ser adicionada à aplicação.

Motor não precisa conhecê-la.

---

# 126. Play Games Services

## FUTURO

Achievements/leaderboards podem surgir.

Integração no lado Android.

Motor pode fornecer fatos:

- partida venceu;
- número de movimentos.

Adapter converte em chamadas externas.

---

# 127. Segurança de arquitetura

Nenhum segredo deve:

- entrar no motor;
- ficar hardcoded;
- ser commitado.

Integrações futuras precisam de configuração apropriada.

---

# 128. Privacidade

Arquitetura do MVP local deve aproveitar simplicidade:

- sem conta;
- sem rede;
- sem coleta desnecessária.

Adicionar integração externa aumenta superfície e deve passar por decisão.

---

# 129. Performance

Não otimizar arquitetura para problema inexistente.

Para cada preocupação:

1. medir;
2. localizar;
3. corrigir.

O motor deve ser eficiente o suficiente para interação humana.

IA futura muda perfil.

---

# 130. Objetos criados em `onDraw`

Se Custom View for usada, Android recomenda evitar criar objetos caros dentro de `onDraw`, porque View pode redesenhar frequentemente.

Isso é implementação, mas afeta design do renderer.

Fonte:

https://developer.android.com/develop/ui/views/layout/custom-views/custom-drawing

---

# 131. `invalidate()`

Se renderer customizado depender de estado:

UI recebe novo estado e solicita redraw conforme API apropriada.

Motor não chama `invalidate()`.

---

# 132. Lógica de layout

Cálculo:

```text
pixels → retângulos/casas
```

é concern visual.

Não colocar em `Position`.

---

# 133. Configuração de cores

Tema/recursos Android.

Não em enum de peça.

---

# 134. Estado de seleção

Seleção de UI é diferente de posição lógica.

Uma peça pode existir sem estar selecionada.

O motor pode fornecer movimentos possíveis, mas “brilho verde” pertence à UI.

---

# 135. Legal moves

## PENDENTE DE API

O motor provavelmente precisará expor forma de consultar movimentos legais.

Motivos:

- UI highlights;
- IA;
- testes.

Isso sugere uma API central reutilizável.

Detalhes no documento de regras.

---

# 136. Separar pseudo-legal de legal

## PENDENTE

Em xadrez, pode ser útil distinguir:

- movimentos geométricos/pseudo-legais;
- movimentos legais considerando rei.

Se essa distinção for usada, deve ficar interna/claramente modelada.

UI não deveria duplicar.

---

# 137. Regra de turno

O motor é autoridade.

UI não alterna cor manualmente.

---

# 138. Resultado da partida

Motor determina:

- em andamento;
- xeque-mate;
- empate;
- outras condições definidas.

UI traduz em apresentação.

---

# 139. Reiniciar partida

Fluxo:

```text
usuário confirma
↓
state holder solicita
↓
novo estado de domínio
↓
UI renderiza
```

Se houver persistência, atualizar fonte durável conforme política.

---

# 140. Desfazer

Se aprovado:

domínio deve controlar reversão consistente.

UI não “move imagem de volta”.

---

# 141. Histórico visual

UI pode mostrar lista baseada no histórico lógico.

Não manter histórico separado divergente.

---

# 142. Estado do timer e lifecycle

Quando timer for implementado, decidir comportamento em:

- background;
- pause;
- process death.

Isso é interseção entre regra de produto e Android.

Documentar antes de implementar.

---

# 143. Rotação

## PROPOSTO

Partida não deve reiniciar por rotação.

ViewModel ajuda a preservar estado durante configuration changes.

UI deve reconstruir a partir do estado atual.

---

# 144. Process death

## PROPOSTO

Quando retomada de partida for feature, estado necessário deverá ser persistido/restaurável.

Não confiar apenas em objeto vivo em memória.

---

# 145. Teste de arquitetura

Podemos futuramente automatizar algumas regras.

Exemplo:

- módulo engine não compila com Android por natureza;
- dependências de package podem ser verificadas por testes/linters se necessário.

Não adicionar framework de arquitetura agora.

A modularização já faz bastante trabalho.

---

# 146. Contrato entre app e engine

O contrato precisa ser pequeno e estável.

Perguntas:

- como iniciar partida?
- como consultar estado?
- como solicitar movimento?
- como saber resultado?
- como escolher promoção?

Não expor detalhes internos desnecessários.

---

# 147. Encapsular mutações

Preferir operações semânticas.

Evitar setters como:

```text
setTurn
setKingInCheck
setPiecePosition
setCastleAllowed
```

disponíveis livremente.

Esses estados devem derivar das regras.

---

# 148. Regra de dependência conceitual

```text
UI conhece API do motor
MOTOR não conhece UI
```

```text
persistência conhece representação persistível
MOTOR não conhece banco
```

```text
IA conhece API do motor
MOTOR não conhece IA
```

```text
multiplayer adapter conhece motor/protocol
MOTOR não conhece socket
```

---

# 149. Ports and adapters: influência, não dogma

A arquitetura lembra alguns princípios de ports and adapters:

- domínio no centro;
- infraestrutura nas bordas.

Mas o projeto NÃO está declarando “Arquitetura Hexagonal completa” como framework obrigatório.

Usaremos o princípio útil:

> regra de negócio não depende de detalhe externo.

Sem criar ports para absolutamente tudo.

---

# 150. Clean Architecture: influência, não rótulo

O Gambitol não precisa se vender como “Clean Architecture” para ser organizado.

Se princípios coincidirem:

- dependency direction;
- separation;
- testability;

ótimo.

Mas não criaremos entidades, use cases, gateways e presenters apenas para cumprir diagrama de livro.

---

# 151. MVC/MVP/MVVM

O app pode ter elementos que lembrem MVVM por causa de ViewModel.

Não é necessário transformar o projeto em debate de sigla.

Arquitetura será descrita por responsabilidades e dependências.

Se um rótulo ajudar comunicação, usar depois.

---

# 152. Por que evitar guerra de arquitetura

Dois sistemas podem ser bons com estruturas diferentes.

Pergunta útil:

> “Consigo mudar UI sem reescrever regra?”

> “Consigo testar regra sem Android?”

> “Consigo entender quem é dono do estado?”

Essas respostas importam mais que o nome do pattern.

---

# 153. Evolução prevista: MVP local

Arquitetura suficiente:

```text
Android UI
↓
ViewModel/state holder
↓
Chess engine
```

Persistência mínima somente se feature exigir.

---

# 154. Evolução prevista: salvar partida

Adicionar:

```text
repository
↓
local data source
```

sem mover regras para data layer.

---

# 155. Evolução prevista: IA

Adicionar:

```text
AI adapter/service
↓
engine API
```

possivelmente fora da main thread.

---

# 156. Evolução prevista: online

Adicionar:

- network;
- protocol;
- repository/service;
- sincronização;
- identidade.

Sem destruir motor local.

---

# 157. Evolução prevista: múltiplas telas

Adicionar navegação quando fluxo exigir.

Não criar navegação complexa agora.

---

# 158. Evolução prevista: monetização

Integrar SDK na borda Android.

Motor permanece independente.

---

# 159. Riscos arquiteturais atuais

## Risco 1 — `MainActivity` crescer demais

Mitigação:

- state holder;
- engine;
- responsabilidades claras.

## Risco 2 — UI virar fonte de verdade

Mitigação:

- render derivado do motor.

## Risco 3 — motor depender de Android

Mitigação:

- módulo Java puro implementado como fronteira física do build.

## Risco 4 — modularização excessiva

Mitigação:

- máximo de dois módulos inicialmente.

## Risco 5 — persistência prematura

Mitigação:

- escolher após requisito.

## Risco 6 — IA antes do motor

Mitigação:

- engine confiável primeiro.

## Risco 7 — lifecycle destruir partida

Mitigação:

- state holder + estratégia de restore.

## Risco 8 — classe gigante de regras

Mitigação:

- decomposição orientada por responsabilidade após pressão real.

---

# 160. Smells arquiteturais a vigiar

- `Activity` com regras;
- classe de peça importando Android;
- UI alterando array de tabuleiro;
- `Context` no motor;
- `R.drawable` no domínio;
- banco sendo chamado por View;
- timer baseado em TextView;
- IA clicando na interface;
- repository sem fonte de dados nem propósito;
- dezenas de interfaces de uma implementação;
- package “utils” virando depósito;
- singleton global mutável;
- estado duplicado;
- `static` usado como armazenamento de partida;
- build quebrando por dependência circular.

---

# 161. Sobre singleton global

## EVITAR POR PADRÃO

Uma partida atual armazenada em singleton global pode:

- dificultar testes;
- sobreviver/encerrar de forma imprevisível;
- esconder dependência;
- criar estado compartilhado.

Preferir ownership explícito.

---

# 162. Sobre `static`

`static` não é proibido.

É adequado para:

- constantes;
- funções puras quando apropriado.

Evitar usar como banco improvisado de estado mutável da partida.

---

# 163. Sobre `Application`

Não armazenar estado da partida em `Application` apenas para sobreviver Activity.

ViewModel/persistência existem para responsabilidades mais adequadas.

---

# 164. Sobre Service

Não criar Android Service para cronômetro ou engine sem requisito real de execução em background.

Service não é “classe de serviço” genérica.

É componente Android com semântica própria.

---

# 165. Sobre background

Se no futuro houver relógio que precisa continuar enquanto app sai da tela, definir produto primeiro.

Isso pode envolver:

- timestamp;
- cálculo por diferença;
- lifecycle;

e não necessariamente Service rodando constantemente.

---

# 166. Arquitetura e consumo de bateria

Preferir representar tempo e calcular diferenças a manter loop desnecessário quando possível.

Decisão concreta será feita com timer.

---

# 167. Dependência inversa onde fizer sentido

Se engine precisar de algo externo, antes perguntar:

> isso pertence mesmo ao engine?

Se sim, uma abstração pode ser usada.

Mas não criar interface para cada classe “porque DIP”.

---

# 168. Contratos testáveis

Boas fronteiras devem permitir testes.

Exemplo conceitual:

```text
dado estado X
quando movimento Y
resultado deve ser Z
```

Sem Activity.

---

# 169. Determinismo

Regras de xadrez devem ser determinísticas.

Mesmo estado + mesma ação:

> mesmo resultado.

Isso facilita testes.

IA pode envolver escolhas, mas pode usar seed/estratégia quando teste exigir.

---

# 170. Mutabilidade controlada

Uma partida naturalmente muda.

Não precisamos tornar todo motor funcional/imutável.

Mas mutação deve possuir proprietário claro.

---

# 171. Ownership do estado

## PROPOSTO

Objeto central de sessão/partida no motor possui estado lógico mutável controlado.

State holder Android possui referência/coordenação.

UI recebe visão/snapshot.

Nome exato não definido.

---

# 172. Concurrency

Enquanto motor for usado por interação humana na main thread, concorrência é pequena.

Se IA/network aparecer:

precisamos decidir:

- thread safety;
- snapshot;
- locking;
- serialization de comandos.

Não resolver agora.

---

# 173. Testes de concorrência

Somente se concurrency entrar.

Não adicionar locks preventivamente.

---

# 174. Responsabilidade da data layer futura

Persistência lida com:

- converter;
- salvar;
- carregar;
- versionar formato.

Motor lida com:

- validar estado restaurado;
- continuar partida.

---

# 175. Migração de dados

Quando formato persistido existir, versões futuras podem exigir migração.

Não armazenar objeto Java serializado arbitrariamente como estratégia de longo prazo sem avaliar compatibilidade.

---

# 176. Serialização

## PENDENTE

Escolha futura deve considerar:

- estabilidade;
- legibilidade;
- tamanho;
- versão;
- segurança.

---

# 177. Atualizações da Play Store

Arquitetura deve permitir evoluir sem apagar dados arbitrariamente.

Isso importa quando persistência entrar.

---

# 178. Feature flags

## FUTURO

Não necessárias agora.

Podem ser úteis para rollout/testes no futuro.

Não criar sistema de flags sem necessidade.

---

# 179. Logging arquitetural

Domínio pode possuir logs técnicos apenas se abstraídos/úteis, mas preferencialmente não depender de Android Log.

Testes e debugging podem observar resultados.

Se logging central surgir, projetar na borda.

---

# 180. Error reporting

Crash reporter deve capturar falhas da aplicação.

Não criar dependência de fornecedor no motor.

---

# 181. Telemetria de jogadas

## FUTURO / PRIVACIDADE

Se analytics medir eventos:

- definir eventos sem dados desnecessários;
- UI/application pode emitir;
- motor não precisa conhecer SDK.

---

# 182. Replays

## FUTURO

Histórico estruturado pode facilitar:

- replay;
- análise;
- compartilhamento.

Não implementar agora.

Mas evitar formato de histórico impossível de reutilizar.

---

# 183. Compartilhamento

## FUTURO

Android share intent pertence ao app.

Motor fornece dados.

---

# 184. Internacionalização

Texto visual fica em resources.

Motor pode usar enums/códigos.

Isso facilita idioma sem alterar regras.

---

# 185. Acessibilidade e arquitetura de renderer

Se Canvas único prejudicar acessibilidade, isso pesa na decisão.

Arquitetura não sacrifica acessibilidade apenas por performance hipotética.

---

# 186. Testes em múltiplas densidades

UI deve ser independente de pixels fixos.

Motor não é afetado.

---

# 187. Tema

Escuro/dourado/verde pertence ao app/resources.

Engine não sabe tema.

---

# 188. Assets de peças

UI assets.

Engine sabe tipo e cor.

---

# 189. Som de captura

UI decide som quando estado indica captura.

Não peça chama áudio.

---

# 190. Promoção visual

UI apresenta opções baseadas em tipos permitidos pelo motor.

Motor valida escolha.

---

# 191. Notificação de xeque

Motor fornece status.

UI decide:

- highlight;
- texto;
- som.

---

# 192. Checkmate

Motor determina.

UI mostra tela/overlay.

---

# 193. Draw

Motor determina condição.

UI apresenta motivo.

---

# 194. Abandono da partida

Isso é ação de produto.

Motor pode receber comando de encerramento/resign se regra for modelada.

UI captura confirmação.

---

# 195. Pausa

Definir se jogo local pode pausar timer.

Regra de produto primeiro.

Arquitetura depois.

---

# 196. Rematch

Cria nova partida.

Não mutar estado antigo de forma que histórico fique incoerente se histórico for salvo.

---

# 197. Estatísticas

## FUTURO

Derivadas de resultados persistidos.

Não colocar contador global em Activity.

---

# 198. Ranking

## FUTURO

Depende de identidade/servidor ou serviço externo.

Não faz parte da engine local.

---

# 199. Arquitetura de release

Build/release fica no módulo Android/root.

Engine é dependência de build.

Detalhes em `12_PLAY_STORE_E_RELEASE.md`.

---

# 200. Compilação independente do engine

## IMPLEMENTADO E VALIDADO

O motor pode ser testado isoladamente com:

```bash
./gradlew :chess-engine:test
```

A integração com o app pode ser validada junto com:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

Esse fluxo já foi executado com sucesso antes e depois do merge na `main`.

---

# 201. Benefício de build rápido

Testes JVM do engine podem executar sem emulador.

Isso acelera ciclo:

```text
regra
↓
teste
↓
resultado
```

---

# 202. Benefício de reuso

Mesmo que nunca reutilizemos motor fora do Android, separação tem valor.

Mas teoricamente ele poderia ser usado por:

- CLI;
- servidor;
- desktop;
- testes.

Reuso é benefício, não objetivo atual.

---

# 203. API pública mínima do engine

Princípio:

> expor o mínimo necessário.

Quanto menor a superfície pública:

- menos acoplamento;
- mais liberdade de refatoração;
- menos uso indevido.

---

# 204. Encapsulation entre módulos Java

Java não possui `internal` igual Kotlin.

Podemos usar:

- `public`;
- package-private;
- `protected`;
- `private`.

Estrutura de packages será importante para ocultar detalhes.

---

# 205. JPMS

## NÃO NECESSÁRIO AGORA

Java Platform Module System (`module-info.java`) não precisa ser adicionado apenas porque teremos Gradle module.

Gradle subproject e Java module system são conceitos diferentes.

Evitar complexidade sem benefício.

---

# 206. Build Gradle module versus Java module

Importante pedagogicamente:

```text
Gradle module/subproject
≠
Java Platform Module (JPMS)
```

Se engine virar subproject Gradle, isso não obriga `module-info.java`.

---

# 207. Android module versus Java library module

`:app`:

- Android plugin;
- Manifest;
- resources;
- Android SDK.

Engine:

- Java library plugin;
- fonte Java;
- testes JVM;
- sem resources Android.

Essa separação é justamente o benefício.

---

# 208. Kotlin DSL não significa código Kotlin

Build files `.gradle.kts` usam Kotlin DSL.

Código do produto continua Java.

Isso já foi decidido durante setup.

---

# 209. Estrutura física ficará no próximo documento

`04_ESTRUTURA_DO_PROJETO.md` definirá:

- nomes aprovados;
- directories;
- packages;
- source sets;
- testes;
- localização de adapters;
- localização do motor;
- convenções.

Este documento não deve duplicar.

---

# 210. Critérios usados para aprovar o módulo Java separado

## CONCLUÍDO EM 2026-08-23

- [x] benefício de fronteira está claro;
- [x] nome `chess-engine` aprovado;
- [x] build base estava saudável;
- [x] impacto Gradle foi compreendido e revisado;
- [x] `:app` depende de `:chess-engine`;
- [x] testes JVM foram configurados e executados;
- [x] nenhum módulo extra foi criado junto.

---

# 211. Critérios para adicionar um novo módulo futuro

Novo módulo só se pelo menos um motivo forte existir:

- reuso;
- boundary que precisa ser imposto;
- build muito pesado;
- responsabilidade grande;
- dependências que precisam ser isoladas;
- feature opcional;
- ownership relevante.

Não criar módulo porque package ficou com cinco classes.

---

# 212. Critérios para adicionar uma nova camada

Camada nova precisa resolver:

- duplicação;
- acoplamento;
- complexidade;
- reuso;
- isolamento de infraestrutura.

Não criar camada para satisfazer diagrama.

---

# 213. Critérios para adicionar uma interface

Interface precisa:

- representar contrato;
- permitir múltiplas implementações relevantes;
- isolar dependência;
- melhorar teste;
- expressar conceito.

Não criar `IPawn`, `IBoard`, `IGame` automaticamente.

---

# 214. Critérios para adicionar repository

Repository precisa existir quando:

- há dados fora da memória do motor;
- precisamos centralizar acesso;
- precisamos abstrair fonte;
- precisamos combinar fontes.

Não criar porque “arquitetura Android manda” fora do contexto.

---

# 215. Critérios para adicionar use case

Use case faz sentido quando:

- regra de aplicação complexa;
- reutilização em múltiplos state holders;
- ViewModel cresce;
- operação orquestra engine + data.

Não criar classe `MovePieceUseCase` apenas para encaminhar uma chamada trivial sem benefício.

---

# 216. Critérios para adicionar mapper

Mapper quando há dois modelos realmente diferentes.

Não duplicar classe para criar trabalho de conversão.

---

# 217. Critérios para state holder separado

ViewModel de tela quando:

- estado precisa sobreviver configuração;
- tela possui lógica de apresentação;
- múltiplas Views dependem do mesmo estado.

Não criar ViewModel para cada botão.

---

# 218. Arquitetura e ensino

Toda fronteira importante deverá ser ensinada quando implementada.

Exemplo:

## CONCEITO IMPORTANTE

> dependência aponta da camada Android para o motor; o motor não sabe que existe Android.

Esse é um dos melhores pontos de portfólio/entrevista do projeto.

---

# 219. 🎥 MARCO REGISTRADO — modularização do motor

A criação do motor Java separado tornou-se um marco real do projeto.

**Por que vale gravar/mostrar:**  
Mostra separação real, não apenas pasta.

**Conceito:**  
dependency direction + testabilidade.

**Erro comum:**  
colocar toda regra em Activity.

**Demonstração possível:**  
mostrar que o `:chess-engine` é Java puro, que `:app` depende dele e que os testes JVM executam sem emulador.

---

# 220. COMO EXPLICAR EM ENTREVISTA

> “No Gambitol, separei o núcleo de xadrez da camada Android. O app Android depende do motor, mas o motor não depende de Activity, View ou Context. Isso permite testar as regras na JVM, reduz acoplamento e deixa a interface livre para mudar sem reescrever a lógica do jogo.”

Essa explicação agora é sustentada pela implementação integrada à `main` e pelos testes JVM do `:chess-engine`.

---

# 221. Arquitetura versus implementação atual

## ESTADO CONFIRMADO EM 2026-08-23

- existe o módulo Android `:app`;
- existe o módulo Java puro `:chess-engine`;
- `:app` depende de `:chess-engine`;
- o engine não possui dependências `android.*` ou `androidx.*`;
- existe o package base `br.com.raionorio.gambitol.engine`;
- existe o primeiro tipo de domínio `Side`;
- existem testes JVM em `SideTest`;
- JUnit Jupiter 6.1.3 está configurado no engine;
- `./gradlew :chess-engine:test :app:assembleDebug` passa na `main`;
- existe Gradle Wrapper e version catalog.

## AINDA NÃO IMPLEMENTADO / NÃO CONFIRMADO

- ViewModel/state holder da partida;
- data layer;
- persistência;
- renderer definitivo do board;
- módulos adicionais além de `:app` e `:chess-engine`.

A documentação arquitetural passa, portanto, a distinguir a fronteira já implementada das camadas que continuam futuras.

---

# 222. Sequência de implementação arquitetural

O primeiro trecho desta sequência já foi concluído:

```text
1. aprovar arquitetura                         ✅
↓
2. definir estrutura física                   ✅
↓
3. aprovar nome do módulo/package             ✅
↓
4. criar boundary do motor                    ✅
↓
5. validar build                              ✅
↓
6. criar primeiro modelo mínimo (`Side`)      ✅
↓
7. criar e executar testes JVM                ✅
↓
8. integrar gradualmente com Android          PRÓXIMAS FASES
```

A regra continua sendo não criar classes, interfaces ou packages sem comportamento real que os justifique.

---

# 223. Skeleton architecture anti-pattern

Evitar criar:

```text
30 packages
20 interfaces
15 classes vazias
```

antes de comportamento.

Arquitetura deve crescer junto com casos reais.

---

# 224. Walking skeleton

## PROPOSTO

Depois da estrutura mínima, criar uma fatia extremamente pequena funcionando de ponta a ponta.

Exemplo conceitual:

```text
tela
↓
evento
↓
motor mínimo
↓
estado
↓
render
```

Sem ainda implementar xadrez inteiro.

Isso prova integração.

---

# 225. Vertical slice inicial

Uma possibilidade futura:

- exibir tabuleiro;
- selecionar casa;
- ler estado do motor;
- atualizar highlight.

A feature exata será decidida no roadmap.

---

# 226. Test first architecture boundary

## VALIDADO

Antes de UI sofisticada, um teste JVM real comprovou que o engine funciona isolado.

`SideTest` cobre o comportamento `Side.opposite()` para `WHITE` e `BLACK`.

Isso fornece a primeira evidência executável da fronteira.

---

# 227. Build boundary validation

## VALIDADO

A validação conjunta adotada é:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

Ambas as partes passaram antes do merge e novamente na `main` após o Pull Request `#1`.

---

# 228. Arquitetura observável pelo Git

Cada mudança estrutural relevante deverá ser:

- pequena;
- revisada no diff;
- explicada;
- testada;
- commitada somente após aprovação.

---

# 229. Registro da decisão arquitetural

A decisão:

> “módulo Java separado para motor”

é arquiteturalmente relevante e foi registrada em `11_DECISOES_TECNICAS.md`.

O registro vigente documenta:

- módulo `:chess-engine`;
- direção `:app -> :chess-engine`;
- independência de Android;
- contexto de aprendizagem e testabilidade;
- alternativa de manter apenas packages dentro de `:app`;
- consequências positivas e negativas;
- evidências do Pull Request `#1` e da validação pós-merge.

---

# 230. ADR de state management

Quando decidirmos:

- ViewModel;
- LiveData;
- outro holder;

registrar se tiver impacto relevante.

---

# 231. ADR de renderer

Se escolhermos:

- GridLayout;
- Recycler/Grid;
- custom Canvas;

registrar quando trade-off for relevante.

---

# 232. ADR de persistência

Quando tecnologia for escolhida:

registrar motivo.

---

# 233. ADR de IA

Quando arquitetura da IA surgir:

registrar.

---

# 234. ADR de online

Multiplayer certamente exigirá decisão arquitetural.

---

# 235. Revisão arquitetural por milestone

Revisar arquitetura quando:

- motor ficar grande;
- persistência entrar;
- IA entrar;
- online entrar;
- múltiplas telas entrarem;
- build modular ficar problemático;
- testes ficarem difíceis.

Não revisar toda semana por ritual.

---

# 236. Indicadores de arquitetura saudável

- regras testáveis sem Android;
- Activity pequena;
- UI derivada do estado;
- poucos caminhos de mutação;
- dependências claras;
- mudança visual não quebra regra;
- mudança de persistência não quebra peças;
- engine não conhece Android;
- build compreensível.

---

# 237. Indicadores de deterioração

- regras duplicadas;
- vários donos do estado;
- Activity enorme;
- estado escondido em `static`;
- engine importando `Context`;
- UI alterando tabuleiro;
- banco acessado de View;
- interfaces sem propósito;
- módulos demais;
- testes exigindo emulador para lógica pura.

---

# 238. Quality gate arquitetural

## STATUS PARCIAL EM 2026-08-23

- [x] build passa;
- [x] boundary Android/engine existe;
- [x] engine não depende de Android;
- [x] primeiro teste JVM do engine passa;
- [ ] UI integrada ainda precisa demonstrar que não se torna fonte de verdade;
- [ ] fluxo completo de ação UI → engine → estado → UI ainda não foi implementado;
- [x] `MainActivity` não contém regras de xadrez;
- [ ] documentação 04 ainda precisa ser alinhada ao código atual;
- [x] decisão modular foi registrada em `11_DECISOES_TECNICAS.md`;
- [x] Git diff da mudança estrutural foi revisado antes da integração.

---

# 239. Questões ainda abertas

## PENDENTE

1. ~~Nome definitivo do módulo Java.~~ **RESOLVIDO:** `chess-engine`.
2. Primeiras entidades e nomes além de `Side`.
3. Representação do tabuleiro.
4. Representação de posição.
5. Estratégia de mutabilidade.
6. API pública do engine.
7. LiveData ou outro mecanismo de estado para Views.
8. Renderer do tabuleiro.
9. Persistência.
10. Formato de snapshot.
11. Navegação.
12. Cronômetro.
13. Orientação da tela.
14. IA futura.
15. multiplayer futuro.

Nenhum desses itens deve ser “resolvido” silenciosamente pelo tutor.

---

# 240. Decisões arquiteturais vigentes neste documento

No estado atual, ficam estabelecidos como princípios:

## DECIDIDO

1. regras do xadrez ficam fora da UI;
2. motor não depende de Android;
3. UI não é fonte de verdade da partida;
4. fluxo deve ser previsível e preferencialmente unidirecional;
5. Android recebe ações e renderiza estado;
6. lifecycle não controla regras;
7. persistência será adaptador externo ao motor;
8. IA futura consumirá motor, não UI;
9. multiplayer futuro será integração externa;
10. arquitetura cresce por necessidade;
11. overengineering é explicitamente evitado;
12. testes do motor devem rodar sem emulador.

## DECIDIDO E IMPLEMENTADO

13. separar motor em módulo Java puro `:chess-engine`;
14. manter inicialmente apenas `:app` e `:chess-engine`, sem módulos adicionais sem necessidade comprovada.

## AINDA PROPOSTO / PENDENTE DE DECISÃO ESPECÍFICA

15. usar ViewModel como state holder de tela;
16. avaliar LiveData como mecanismo inicial Java/Views;
17. usar manual dependency injection inicialmente.

---

# 241. Checklist para aprovação do documento

- [ ] A separação Android ↔ motor está correta.
- [x] O módulo Java separado foi aprovado, implementado e validado.
- [x] O nível atual de modularização permanece limitado a `:app` e `:chess-engine`.
- [ ] Nenhum nome de classe futuro foi imposto.
- [ ] Estado lógico e visual estão claramente separados.
- [ ] UI não manipula estado interno do engine.
- [ ] ViewModel não vira motor.
- [ ] persistência futura está na borda.
- [ ] IA futura está na borda.
- [ ] multiplayer futuro está na borda.
- [ ] lifecycle está contemplado.
- [ ] process death está distinguido de configuração.
- [ ] renderer continua pendente.
- [ ] data layer só entra com necessidade.
- [ ] dependency injection não foi exagerada.
- [ ] arquitetura continua ensinável.
- [x] documento 04 continua sendo dono da estrutura física e deve ser alinhado ao estado implementado.

---

# 242. Fontes pesquisadas — Android Architecture

## Guide to app architecture

https://developer.android.com/topic/architecture

Base para:

- UI/data/domain;
- separação de responsabilidades;
- redução de dependências Android;
- fonte de verdade;
- testabilidade;
- boundaries.

Verificado em: 2026-08-22.

---

## Recommendations for Android architecture

https://developer.android.com/topic/architecture/recommendations

Base para:

- UDF;
- ViewModel;
- layered architecture;
- DI;
- testes;
- recomendação versus regra;
- single-activity como contexto, sem adoção automática.

Verificado em: 2026-08-22.

---

## Recommendations for Android architecture (Views)

https://developer.android.com/topic/architecture/views/recommendations-views

Base para:

- aplicação das recomendações no ecossistema Views;
- UDF;
- lifecycle.

Verificado em: 2026-08-22.

---

# 243. Fontes — UI e estado

## UI layer

https://developer.android.com/topic/architecture/ui-layer

Base para:

- UI como representação de estado;
- eventos;
- lógica de negócio fora da UI;
- UDF.

---

## UI layer (Views)

https://developer.android.com/topic/architecture/views/ui-layer

Base para:

- estado observável em Views;
- LiveData/StateFlow;
- pipeline de UI.

---

## UI events (Views)

https://developer.android.com/topic/architecture/views/ui-layer/events-views

Base para:

- eventos da UI;
- business logic versus UI logic;
- estado após eventos.

---

## UI State production

https://developer.android.com/topic/architecture/ui-layer/state-production

Base para:

- diferença entre eventos e estado;
- produção incremental do estado.

Verificado em: 2026-08-22.

---

# 244. Fontes — ViewModel e lifecycle

## ViewModel overview (Views)

https://developer.android.com/topic/libraries/architecture/views/viewmodel

Base para:

- state holder;
- Java;
- configuration changes;
- não manter View/Activity no ViewModel.

---

## ViewModel API

https://developer.android.com/reference/androidx/lifecycle/ViewModel

Base para:

- escopo;
- retenção.

---

## SavedStateHandle

https://developer.android.com/reference/androidx/lifecycle/SavedStateHandle

Base para:

- recuperação de estado após process death.

---

## Save UI states

https://developer.android.com/topic/libraries/architecture/saving-states

Base para:

- diferença ViewModel/saved state/persistência.

---

## Activity lifecycle

https://developer.android.com/guide/components/activities/activity-lifecycle

Base para:

- lifecycle;
- recriação;
- estado.

Verificado em: 2026-08-22.

---

# 245. Fontes — camada de dados

## Data layer

https://developer.android.com/topic/architecture/data-layer

Base para:

- repository;
- data source;
- responsabilidades;
- imutabilidade;
- boundaries.

---

## DataStore

https://developer.android.com/topic/libraries/architecture/datastore

Base para:

- DataStore na data layer;
- futuras preferências.

Verificado em: 2026-08-22.

---

# 246. Fontes — domain layer oficial Android

## Domain layer

https://developer.android.com/topic/architecture/domain-layer

Base para:

- use cases;
- optional domain layer;
- distinção entre definição Android e motor de domínio do Gambitol.

Verificado em: 2026-08-22.

---

# 247. Fontes — modularização e Gradle

## Guide to Android app modularization

https://developer.android.com/topic/modularization

Base para:

- benefícios;
- encapsulamento;
- testabilidade;
- risco de granularidade excessiva.

---

## Common modularization patterns

https://developer.android.com/topic/modularization/patterns

Base para:

- coesão;
- acoplamento;
- patterns adaptáveis.

---

## Gradle Multi-Project Builds

https://docs.gradle.org/current/userguide/multi_project_builds.html

Base para:

- root project;
- subprojects;
- project dependencies;
- build order.

---

## Version catalogs

https://developer.android.com/build/migrate-to-catalogs

Base para:

- `libs.versions.toml`;
- dependências em múltiplos módulos.

Verificado em: 2026-08-22.

---

# 248. Fontes — Views e desenho

## Create a custom drawing

https://developer.android.com/develop/ui/views/layout/custom-views/custom-drawing

Base para:

- `onDraw`;
- Canvas;
- Paint;
- `onSizeChanged`;
- diferentes tamanhos;
- performance de objetos no draw.

---

## Use touch gestures

https://developer.android.com/develop/ui/views/touch-and-input/gestures

Base para:

- touch;
- gestures.

---

## MotionEvent

https://developer.android.com/reference/android/view/MotionEvent

Base para:

- coordenadas;
- eventos de toque.

Verificado em: 2026-08-22.

---

# 249. Fontes — documentação de arquitetura

## C4 Model — Diagrams

https://c4model.com/diagrams

Base para:

- contexto;
- container;
- component;
- níveis de zoom;
- evitar diagramas desnecessários.

---

## C4 — Container diagram

https://c4model.com/diagrams/container

Base para:

- responsabilidades de alto nível;
- tecnologia;
- relações.

---

## C4 — Component diagram

https://c4model.com/diagrams/component

Base para:

- detalhamento somente quando agrega valor.

---

## arc42 — Building Block View

https://docs.arc42.org/section-5/

Base para:

- decomposição;
- responsabilidades;
- interfaces;
- níveis;
- foco em blocos importantes.

Verificado em: 2026-08-22.

---

# 250. Como este documento deve ser usado pelo tutor

Antes de alteração estrutural, o tutor deverá:

1. consultar este documento;
2. identificar bloco afetado;
3. verificar direção de dependência;
4. consultar `04_ESTRUTURA_DO_PROJETO.md` quando criado;
5. verificar estado real no Git;
6. apresentar mudança;
7. explicar conceito;
8. implementar passo pequeno;
9. rodar build/testes;
10. revisar diff.

---

# 251. Regra para conflitos futuros

Se uma proposta futura disser:

> “é mais fácil colocar isso na Activity”,

isso não basta para violar boundary.

Precisa justificar mudança arquitetural.

Se necessidade real tornar regra inviável:

- discutir;
- registrar decisão;
- atualizar documento.

Arquitetura serve ao software, mas não deve evaporar na primeira conveniência.

---

# 252. Resumo executivo

A arquitetura recomendada do Gambitol é deliberadamente simples:

```text
ANDROID
  │
  │ intenção do usuário
  ▼
STATE HOLDER / ORQUESTRAÇÃO
  │
  │ operação
  ▼
MOTOR DE XADREZ JAVA
  │
  │ estado
  ▲
  │
UI RENDERIZA
```

Com persistência futura:

```text
ANDROID
 ├── UI
 ├── state holder
 └── data adapters/repositories
          │
          ▼
     armazenamento

ANDROID
   │
   ▼
MOTOR JAVA PURO
```

A estrutura física atual é:

```text
:app
:chess-engine
```

e nada além disso sem necessidade comprovada.

O motor:

- conhece xadrez;
- não conhece Android.

O app:

- conhece Android;
- conversa com o motor;
- apresenta o jogo.

A UI:

- não decide regras;
- não é fonte de verdade.

O state holder:

- coordena a tela;
- não vira engine.

Persistência:

- fica na borda.

IA:

- fica na borda.

Multiplayer:

- fica na borda.

Essa estrutura existe para que o Gambitol possa crescer sem transformar a `MainActivity` em uma criatura mitológica de quatro mil linhas que também calcula en passant, toca som e conversa com a Play Store.

---

# 253. Frase norteadora da arquitetura

> **O centro do Gambitol deve entender xadrez. As bordas devem entender Android, armazenamento, rede e serviços externos. Quanto menos o centro souber sobre as bordas, mais fácil será testar, ensinar, manter e evoluir o jogo.**

---

# 254. Próximo documento

Após aprovação desta arquitetura, o próximo documento deverá ser:

`04_ESTRUTURA_DO_PROJETO.md`

Sua função será transformar estas fronteiras em estrutura concreta:

- módulos;
- diretórios;
- source sets;
- packages;
- localização de testes;
- regras de criação de arquivos;
- convenções de dependência;
- nomes aprovados antes da implementação.

A arquitetura define **o que deve ser separado**.

A estrutura definirá **onde cada coisa ficará**.
