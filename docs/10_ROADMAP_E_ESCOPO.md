# 10 — ROADMAP E ESCOPO DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `10_ROADMAP_E_ESCOPO.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir a ordem de construção do Gambitol, os limites de cada etapa, as dependências entre fases, o que pertence à primeira versão publicável, o que permanece fora dela e os critérios objetivos para avançar sem atropelar fundamentos  
> **Fonte normativa para:** sequência de desenvolvimento, escopo por fase, critérios de entrada e saída, definição de MVP técnico, primeira versão publicável, prioridades, adiamentos, riscos de escopo, checkpoints de qualidade e critérios para introduzir funcionalidades futuras  
> **Não cobre em detalhe:** implementação de classes, regras FIDE completas, padrões de código, Git operacional, estratégia detalhada de testes, especificação visual fina, política completa de monetização ou procedimento completo de publicação  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`, `06_PADROES_JAVA_E_ANDROID.md`, `07_GIT_WORKFLOW.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`  
> **Plataforma:** Android  
> **Linguagem principal:** Java  
> **Package/applicationId definidos:** `br.com.raionorio.gambitol`  
> **Modelo inicial de jogo:** dois jogadores locais no mesmo dispositivo  
> **Idioma principal deste documento:** Português do Brasil  

---

# 1. Objetivo deste documento

O Gambitol já possui visão, arquitetura, regras, padrões, Git, qualidade e UI/UX documentados.

Agora precisamos responder uma pergunta menos glamourosa e muito mais perigosa:

> **em que ordem tudo isso será construído?**

Sem um roadmap, é fácil transformar o projeto em uma coleção de ideias simultâneas:

```text
tabuleiro
+ IA
+ multiplayer
+ login
+ ranking
+ tema premium
+ anúncios
+ achievements
+ cloud save
+ engine
+ Play Store
```

e terminar com quinze estruturas pela metade e nenhum jogo publicável.

O roadmap existe para impedir isso.

---

# 2. Roadmap não é calendário

## DECIDIDO

Este documento organiza:

- dependências;
- ordem;
- gates;
- escopo.

Ele NÃO define:

- “terminar engine em 7 dias”;
- “publicar em 30 dias”;
- datas arbitrárias;
- velocidade de aprendizado.

A duração depende de:

- dificuldade;
- erros encontrados;
- tempo disponível;
- aprendizado necessário;
- qualidade obtida.

Não vamos trocar compreensão por uma data inventada em Markdown.

---

# 3. Roadmap é orientado por capacidade demonstrável

Cada fase termina quando algo concreto pode ser demonstrado.

Exemplo:

```text
“fase do cavalo concluída”
```

não significa:

> criamos `Knight.java`.

Significa algo como:

- estado representa cavalo;
- movimentos válidos funcionam;
- inválidos são rejeitados;
- bloqueios/regras relevantes estão corretos;
- testes passam;
- integração não foi quebrada.

---

# 4. Regra mestre de progressão

## DECIDIDO

O projeto avança assim:

```text
FAZER MENOS
↓
ENTENDER
↓
VALIDAR
↓
TESTAR
↓
REVISAR
↓
INTEGRAR
↓
SÓ DEPOIS EXPANDIR
```

Não:

```text
CRIAR TODAS AS CLASSES
↓
CRIAR TODAS AS TELAS
↓
TENTAR FAZER FUNCIONAR NO FIM
```

---

# 5. Dependência antes de conveniência

A ordem será definida pelo que uma funcionalidade precisa para existir corretamente.

Exemplo:

```text
xeque-mate
```

depende de:

```text
ataques
+
movimentos legais
+
segurança do rei
+
ausência de movimentos legais
```

Logo, xeque-mate não deve ser implementado antes dessas fundações.

---

# 6. Correção antes de polimento

## DECIDIDO

Prioridade:

```text
CORREÇÃO
↓
TESTABILIDADE
↓
INTEGRAÇÃO
↓
USABILIDADE
↓
POLIMENTO
↓
EXTRAS
```

Uma peça perfeitamente animada executando um movimento ilegal continua sendo um bug com efeitos especiais.

---

# 7. Escopo antes de monetização

## DECIDIDO

O jogo precisa possuir valor próprio antes de:

- anúncios;
- compra no app;
- temas pagos;
- assinatura;
- qualquer monetização.

A orientação atual do Android para apps e jogos de alta qualidade também reforça que monetização não deve interromper ou prejudicar a experiência principal. citeturn286669search2

Detalhes ficam no documento 13.

---

# 8. Escopo antes de serviços online

## DECIDIDO

Não adicionar backend, login ou serviços de rede para uma necessidade que ainda não existe.

Primeira modalidade:

```text
LOCAL
```

dois jogadores no mesmo aparelho.

---

# 9. Escopo antes de IA

## DECIDIDO

IA entra somente depois que:

- engine gera movimentos legais corretamente;
- estado é estável;
- testes estão maduros;
- Perft/validação está confiável.

IA depende do motor.

O motor não depende da IA.

---

# 10. Escopo antes de multiplayer

## DECIDIDO

Multiplayer online depende de muito mais que UI:

- identidade;
- autoridade de estado;
- rede;
- reconexão;
- sincronização;
- segurança;
- persistência;
- matchmaking potencial.

Portanto:

> não pertence à primeira versão.

---

# 11. Escopo e aprendizado

## DECIDIDO

Este projeto possui dois produtos simultâneos:

1. o aplicativo;
2. o conhecimento adquirido durante sua construção.

Uma fase não deve ser acelerada de modo que o código exista, mas o desenvolvedor não consiga explicar:

- por que existe;
- como funciona;
- como testar;
- como depurar;
- como evoluir.

---

# 12. Três níveis de entrega

Para evitar chamar tudo de “MVP”, o Gambitol separa três conceitos.

---

# 13. Nível A — milestone técnico

É uma etapa de engenharia.

Exemplo:

```text
motor consegue mover cavalo corretamente
```

Não é produto entregue ao público.

---

# 14. Nível B — MVP técnico jogável

## DEFINIÇÃO DO PROJETO

Uma versão interna em que já existe uma partida de xadrez local jogável suficientemente completa para provar a arquitetura e a integração.

Pode ainda não possuir:

- polimento final;
- store listing;
- todos os detalhes de release;
- monetização;
- extras.

---

# 15. Nível C — primeira versão publicável

É a primeira versão que consideramos adequada para chegar a usuários reais.

Ela exige:

- regras completas do escopo;
- UI consistente;
- qualidade;
- acessibilidade mínima;
- testes;
- compatibilidade;
- release process;
- Play Console.

---

# 16. MVP técnico não significa produto ruim

“Mínimo” refere-se ao conjunto de capacidades necessárias para provar o produto.

Não significa:

- bugs conhecidos graves;
- UI inutilizável;
- regras incompletas fingindo estar completas;
- ausência de testes.

---

# 17. Primeira versão publicável: núcleo já definido

Com base na visão e no README inicial, a primeira versão do Gambitol deve possuir:

- tabuleiro 8×8;
- peças;
- dois jogadores locais;
- seleção por toque;
- movimentação;
- alternância de turno;
- captura;
- validação de movimentos;
- xeque;
- xeque-mate;
- empate;
- roque;
- en passant;
- promoção.

---

# 18. O que “empate” significa para a engine

O documento 05 detalha:

- stalemate;
- dead position;
- repetição;
- 50/75 movimentos;
- outras condições aplicáveis.

O roadmap não redefine essas regras.

---

# 19. O que NÃO é requisito da primeira versão

## DECIDIDO

Não é requisito para a primeira publicação:

- IA;
- multiplayer online;
- backend;
- login;
- conta;
- ranking;
- Elo online;
- amigos;
- chat;
- achievements;
- leaderboards;
- cloud save;
- sincronização;
- temas pagos;
- anúncios;
- assinatura;
- compras in-app;
- partidas ranqueadas;
- matchmaking;
- torneios;
- análise com Stockfish;
- puzzles;
- aulas;
- engine nativa C++;
- NDK;
- Chess960;
- variantes.

---

# 20. Recursos futuros já imaginados não são dívida

Uma ideia futura não precisa de placeholder agora.

Não criar:

```text
AiManager
NetworkManager
PremiumManager
LeaderboardRepository
```

só porque esses recursos podem existir um dia.

---

# 21. Estado real conhecido antes de retomar desenvolvimento

Antes da série documental, o projeto havia sido criado no Android Studio com:

- módulo `app`;
- Java;
- package `br.com.raionorio.gambitol`;
- minSdk 24;
- Kotlin DSL no Gradle;
- `MainActivity`;
- recursos do template;
- testes de exemplo;
- Git inicializado em `main`.

Também havia uma pendência de configuração/validação do JDK.

## IMPORTANTE

Não existe confirmação documental posterior de que:

```bash
./gradlew assembleDebug
```

tenha sido executado com sucesso.

Portanto, o roadmap começa validando a fundação real.

---

# 22. Não presumir que build passa

## DECIDIDO

Ao retomar código, o primeiro gate técnico será:

```text
JDK correto
↓
Gradle sync
↓
build
↓
testes de template/configuração
```

Só então reestruturar.

---

# 23. Visão geral das fases

## PROPOSTO

```text
FASE 0  GOVERNANÇA E DOCUMENTAÇÃO
FASE 1  BASELINE ANDROID REPRODUZÍVEL
FASE 2  FRONTEIRA DO MOTOR JAVA
FASE 3  MODELO FUNDAMENTAL DO XADREZ
FASE 4  MOVIMENTOS BÁSICOS
FASE 5  ATAQUES E LEGALIDADE
FASE 6  REGRAS ESPECIAIS
FASE 7  FIM DE PARTIDA E EMPATES
FASE 8  HARDENING DO MOTOR
FASE 9  TABULEIRO ANDROID
FASE 10 INTERAÇÃO JOGADOR ↔ MOTOR
FASE 11 ESTADOS COMPLETOS DE PARTIDA NA UI
FASE 12 POLIMENTO E ADAPTAÇÃO
FASE 13 CANDIDATO A RELEASE
FASE 14 TESTES PLAY / PUBLICAÇÃO
FASE 15 PÓS-LANÇAMENTO
FASE 16+ EVOLUÇÕES
```

---

# 24. Essas fases não são branches

Uma fase pode conter:

- várias branches;
- vários commits;
- vários testes.

Git workflow permanece definido no documento 07.

---

# 25. Essas fases não são módulos

Também não representam estrutura de packages.

---

# 26. Essas fases são gates de maturidade

Cada fase existe para reduzir um conjunto de riscos.

---

# 27. FASE 0 — GOVERNANÇA E DOCUMENTAÇÃO

## OBJETIVO

Terminar a base documental antes de implementar decisões relevantes.

---

# 28. Fase 0 — entrada

Projeto Android criado.

Decisão de construir Gambitol já estabelecida.

---

# 29. Fase 0 — trabalho

Documentos:

```text
00–15
```

com decisões fundamentais.

---

# 30. Fase 0 — status atual

No momento deste documento:

```text
00 ✅
01 ✅
02 ✅
03 ✅
04 ✅
05 ✅
06 ✅
07 ✅
08 ✅
09 ✅
10 em construção
11–15 pendentes
```

---

# 31. Fase 0 — saída

Critérios:

- [ ] todos os documentos planejados concluídos;
- [ ] pendências claramente marcadas;
- [ ] nenhuma proposta confundida com decisão;
- [ ] conflitos principais resolvidos;
- [ ] roadmap aprovado.

---

# 32. Fase 0 não precisa resolver tudo

Não precisamos decidir agora:

- algoritmo final de IA;
- serviço de multiplayer;
- SDK de anúncios;
- tema premium.

Esses assuntos são deliberadamente futuros.

---

# 33. FASE 1 — BASELINE ANDROID REPRODUZÍVEL

## OBJETIVO

Provar que o projeto Android gerado compila de forma previsível antes de refatorar.

---

# 34. Fase 1 — entrada

- documentação base concluída;
- projeto existente;
- Git status conhecido.

---

# 35. Fase 1 — trabalho

1. confirmar JDK configurado;
2. confirmar JDK usado pelo Gradle;
3. ler arquivos Gradle atuais;
4. confirmar `minSdk`;
5. confirmar `compileSdk`;
6. confirmar `targetSdk`;
7. confirmar Java source/target;
8. executar sync;
9. build debug;
10. executar testes existentes;
11. iniciar app em emulator/device;
12. revisar warnings;
13. limpar apenas o que for entendido.

---

# 36. Não alterar Gradle antes de ler

## DECIDIDO

Nenhuma “modernização” automática.

Primeiro:

```text
estado atual
↓
compatibilidade
↓
necessidade
```

---

# 37. Build mínimo de saída

Algo equivalente a:

```bash
./gradlew assembleDebug
```

deve passar.

Tasks reais serão confirmadas.

---

# 38. Test gate mínimo

Os testes configurados devem rodar.

Mesmo que inicialmente sejam apenas template, isso valida pipeline.

---

# 39. Run gate

O app deve abrir.

Não precisa estar bonito.

---

# 40. Fase 1 — saída

- [ ] JDK conhecido;
- [ ] Gradle conhecido;
- [ ] debug build verde;
- [ ] app abre;
- [ ] testes executáveis;
- [ ] warnings críticos entendidos;
- [ ] status Git revisado;
- [ ] baseline pronto para primeiro commit quando aprovado.

---

# 41. 🎥 MOMENTO BOM PARA GRAVAR — primeiro build real

Se houver erro JDK/Gradle:

mostrar diagnóstico e correção.

Tem alto valor porque é ambiente real, não tutorial artificial.

---

# 42. FASE 2 — FRONTEIRA DO MOTOR JAVA

## OBJETIVO

Transformar a separação arquitetural em uma fronteira compilável.

---

# 43. Fase 2 depende de decisões pendentes

Antes de criar:

- nome do módulo do motor;
- package base do motor;

precisam ser aprovados.

O documento 04 propôs opções, mas não decidiu por conta própria.

---

# 44. Fase 2 — trabalho

Conceitualmente:

```text
app
↓ depende de
motor Java puro
```

Criar:

- subproject Java;
- source sets;
- teste JVM;
- dependência do app para o motor.

---

# 45. Fase 2 — primeira prova

Um tipo Java mínimo do motor deve:

- compilar;
- possuir teste;
- ser acessível pelo app quando necessário;
- não importar Android.

---

# 46. Fase 2 — não fazer

Não criar ainda:

- todas as peças;
- repositories;
- ViewModels;
- IA;
- persistence;
- network.

---

# 47. Fase 2 — saída

- [ ] módulo Java puro criado;
- [ ] build isolado passa;
- [ ] teste JVM real passa;
- [ ] app depende do motor;
- [ ] motor não depende do app;
- [ ] nenhuma importação `android.*`;
- [ ] estrutura revisada;
- [ ] documentação 11 registra decisão.

---

# 48. 🎥 MOMENTO BOM PARA GRAVAR — separação Android/Java

Esse é um dos melhores checkpoints arquiteturais do projeto.

---

# 49. FASE 3 — MODELO FUNDAMENTAL DO XADREZ

## OBJETIVO

Representar o estado mínimo do jogo sem ainda tentar implementar todas as regras.

---

# 50. Conceitos necessários

A ordem concreta de classes será decidida durante implementação.

Conceitualmente precisamos representar:

- cor;
- tipo de peça;
- posição/casa;
- peça;
- tabuleiro/posição;
- lado a jogar;
- movimento.

---

# 51. Não escolher herança antes do problema

## DECIDIDO

Antes de criar:

```text
abstract Piece
King extends Piece
...
```

avaliar alternativas.

A linguagem será ensinada no contexto.

---

# 52. Fase 3 — posição inicial

O motor deve conseguir criar:

```text
posição inicial padrão
```

e provar por teste:

- 32 peças;
- 16 por lado;
- peças nas casas corretas;
- brancas a jogar.

---

# 53. Board debug

Criar representação textual útil para testes/debug quando fizer sentido.

---

# 54. Fase 3 — invariantes

- uma peça por casa;
- dois reis;
- posições válidas;
- cores válidas;
- side to move conhecido.

---

# 55. Fase 3 — sem Android

Nenhum drawable.

Nenhum View.

Nenhum Context.

---

# 56. Fase 3 — saída

- [ ] estado inicial reproduzível;
- [ ] value objects confiáveis;
- [ ] equality/hash quando necessários;
- [ ] testes de posição;
- [ ] testes de board;
- [ ] estado ainda Java puro.

---

# 57. FASE 4 — MOVIMENTOS BÁSICOS

## OBJETIVO

Ensinar o motor a reconhecer geometria e ocupação das peças sem ainda misturar todos os casos globais.

---

# 58. Ordem proposta de aprendizado das peças

## PROPOSTO

Uma ordem didática possível:

```text
torre
↓
bispo
↓
dama
↓
cavalo
↓
rei básico
↓
peão básico
```

---

# 59. Por que torre primeiro

Ensina:

- linha;
- coluna;
- caminho;
- bloqueio.

---

# 60. Por que bispo depois

Reaproveita ideia de peça deslizante.

---

# 61. Dama

Combina padrões conhecidos.

---

# 62. Cavalo

Introduz:

- movimento discreto;
- salto.

---

# 63. Rei básico

Introduz adjacência.

Segurança global entra na fase seguinte.

---

# 64. Peão

É simples visualmente e complexo semanticamente.

Nesta fase:

- avanço;
- captura;
- duplo inicial.

Especiais entram depois.

---

# 65. Fase 4 — pseudo-legalidade

## OBJETIVO

Chegar a uma camada coerente de movimentos pseudo-legais.

Ainda falta:

```text
“meu rei continua seguro?”
```

---

# 66. Fase 4 — testes

Cada peça:

- positivo;
- negativo;
- borda;
- bloqueio;
- captura;
- peça própria.

---

# 67. Fase 4 — saída

- [ ] geometria de todas as peças;
- [ ] ocupação;
- [ ] paths;
- [ ] capture rules básicas;
- [ ] pseudo-legal generation confiável;
- [ ] testes verdes.

---

# 68. FASE 5 — ATAQUES E LEGALIDADE

## OBJETIVO

Transformar “a peça pode ir geometricamente” em xadrez legal.

---

# 69. Fase 5 é a virada do motor

Aqui entram:

- attack map;
- segurança do rei;
- peças cravadas;
- discovered check;
- check;
- filtro de movimentos legais.

---

# 70. `isSquareAttacked` conceitual

Responsabilidade precisa existir, nome final pendente.

---

# 71. Ataque ≠ movimento legal

Documento 05 define isso.

É obrigatório testar.

---

# 72. Simulação

Escolher:

- cópia;
- make/unmake;

somente depois de comparar simplicidade e risco.

---

# 73. Fase 5 — xeque

O motor deve detectar:

- check por cada tipo de peça;
- discovered check;
- double check.

---

# 74. Fase 5 — legal move generation

A UI futura dependerá disso.

Não haverá segundo gerador.

---

# 75. Fase 5 — saída

- [ ] attack detection correta;
- [ ] rei não entra em xeque;
- [ ] movimento não deixa próprio rei em xeque;
- [ ] peças cravadas tratadas;
- [ ] lista de movimentos legais;
- [ ] check detectado;
- [ ] regression tests dos edge cases.

---

# 76. 🎥 MOMENTO BOM PARA GRAVAR — peça cravada

Um dos melhores exemplos de pseudo-legal versus legal.

---

# 77. FASE 6 — REGRAS ESPECIAIS

## OBJETIVO

Implementar movimentos que exigem contexto histórico ou transformação de estado.

---

# 78. Regra especial 1 — roque

Depende de:

- attack detection;
- castling rights;
- path;
- posição de rei/torre.

---

# 79. Regra especial 2 — en passant

Depende de:

- último movimento/estado temporário;
- remoção especial;
- king safety.

---

# 80. Regra especial 3 — promoção

Depende de:

- última fileira;
- escolha Q/R/B/N;
- state transition atômica.

---

# 81. Ordem proposta

```text
promoção
roque
en passant
```

ou:

```text
roque
en passant
promoção
```

não é uma decisão fundamental.

Implementar um por vez.

---

# 82. Uma regra especial por checkpoint

Não implementar as três em um único bloco antes de testar.

---

# 83. Fase 6 — saída

- [ ] promoção completa;
- [ ] underpromotion;
- [ ] roque completo;
- [ ] direitos históricos;
- [ ] en passant;
- [ ] expiração;
- [ ] king-safety especial;
- [ ] testes positivos/negativos.

---

# 84. 🎥 MOMENTO BOM PARA GRAVAR — en passant abrindo ataque

Edge case excelente.

---

# 85. FASE 7 — FIM DE PARTIDA E EMPATES

## OBJETIVO

Fazer o motor saber quando a partida acabou ou quando há direito a reclamar empate.

---

# 86. Checkmate

Depende de:

```text
check
+
legalMoves == 0
```

---

# 87. Stalemate

Depende de:

```text
not check
+
legalMoves == 0
```

---

# 88. Dead position

Implementação conservadora e bem validada.

---

# 89. Histórico de posição

Necessário para repetição.

---

# 90. Repetition key

Precisa incorporar o que o documento 05 definiu.

---

# 91. Threefold

Claimable.

---

# 92. Fivefold

Automático.

---

# 93. Halfmove clock

Necessário para:

- 50;
- 75.

---

# 94. 50-move

Claimable.

---

# 95. 75-move

Automático com precedência de mate corretamente tratada.

---

# 96. Fase 7 — status terminal

Representar:

- winner;
- draw;
- reason.

---

# 97. Fase 7 — saída

- [ ] checkmate;
- [ ] stalemate;
- [ ] dead position segura;
- [ ] repetition;
- [ ] 50/75;
- [ ] claimable separado de terminal;
- [ ] game over rejeita novos movimentos;
- [ ] testes de boundary.

---

# 98. MVP técnico do motor

Ao final da Fase 7, o motor já deve ser capaz de representar uma partida completa do escopo local.

Mas ainda não consideraremos “engine pronta”.

Falta hardening.

---

# 99. FASE 8 — HARDENING DO MOTOR

## OBJETIVO

Parar de adicionar regra e tentar quebrar o que existe.

---

# 100. Hardening inclui

- regression suite;
- invariants;
- Perft;
- cross-validation;
- board dump/FEN futuro;
- revisão de API;
- refactor controlado;
- profiling somente se necessário.

---

# 101. Perft

## GATE IMPORTANTE

Criar vetores independentes.

Começar com profundidades rápidas.

---

# 102. Perft especial

Cobrir posições com:

- castling;
- en passant;
- promotions;
- checks.

---

# 103. Cross-validation

Comparar movimentos/perft com referência independente.

---

# 104. Mutation testing

## FUTURO DENTRO/DEPOIS DESTA FASE

PIT pode ser introduzido se a suite já estiver madura.

Não é obrigatório para primeira versão publicável.

---

# 105. Coverage

Medir para identificar lacunas.

Não perseguir número arbitrário.

---

# 106. API cleanup

Agora é melhor momento para revisar:

- nomes;
- visibilidade;
- mutabilidade;
- retorno;
- result types.

Antes da UI depender fortemente.

---

# 107. Fase 8 — saída

- [ ] engine suite rápida;
- [ ] Perft confiável;
- [ ] edge cases principais;
- [ ] API pública mínima;
- [ ] no Android dependency;
- [ ] nenhuma falha conhecida P1 de regra;
- [ ] motor pronto para ser fonte da UI.

---

# 108. 🎥 MOMENTO BOM PARA GRAVAR — Perft

Checkpoint de portfólio altamente relevante.

---

# 109. FASE 9 — TABULEIRO ANDROID

## OBJETIVO

Criar representação visual correta sem ainda concentrar toda a experiência final.

---

# 110. Antes da implementação

Decidir:

- renderer;
- assets;
- paleta inicial validada;
- accessibility strategy.

---

# 111. Não escolher renderer apenas por rapidez

Critérios do documento 09:

- acessibilidade;
- touch;
- clareza;
- responsividade;
- animação;
- testes.

---

# 112. Primeira entrega visual

Board estático:

- 8×8;
- 1:1;
- orientation correta;
- peças da posição inicial.

---

# 113. Sem regra visual

Board recebe estado do engine.

---

# 114. Responsividade inicial

Phone compact portrait primeiro.

---

# 115. System insets

Desde cedo.

Não deixar para corrigir no final.

---

# 116. Edge-to-edge

Precisa ser tratado de acordo com target/Android atual.

---

# 117. Acessibilidade

Se Custom View:

começar virtual hierarchy cedo.

Não “colocar depois” quando renderer já estiver fechado.

---

# 118. Fase 9 — saída

- [ ] board correto;
- [ ] peças legíveis;
- [ ] 1:1;
- [ ] compact phone;
- [ ] orientation mapping;
- [ ] state-driven rendering;
- [ ] accessibility foundation;
- [ ] debug/recreation sanity.

---

# 119. FASE 10 — INTERAÇÃO JOGADOR ↔ MOTOR

## OBJETIVO

Transformar board visual em jogo jogável.

---

# 120. Fluxo

```text
tap source
↓
selection
↓
legal destinations
↓
tap destination
↓
engine
↓
new state
↓
render
```

---

# 121. Touch mapping

Pixels ficam na UI.

Chess coordinates ficam no domínio.

---

# 122. Selection

Estado de apresentação.

Não move engine.

---

# 123. Legal highlights

Origem:

```text
engine
```

---

# 124. Movimento ilegal

Não altera estado.

---

# 125. Captura

Renderiza corretamente.

---

# 126. Turn

Panel/status atualizam.

---

# 127. Restart

Candidato forte.

Só implementar com comportamento aprovado.

---

# 128. Fase 10 — saída

- [ ] partida básica jogável;
- [ ] select/deselect;
- [ ] legal highlights;
- [ ] move;
- [ ] capture;
- [ ] turn;
- [ ] illegal attempt safe;
- [ ] recreation não perde estado importante;
- [ ] testes de integração.

---

# 129. MVP técnico jogável — marco

Ao final da Fase 10:

deve ser possível jogar sequências reais usando UI e engine.

Ainda podem faltar estados especiais de UX.

---

# 130. FASE 11 — ESTADOS COMPLETOS DE PARTIDA NA UI

## OBJETIVO

Expor corretamente tudo que o motor já entende.

---

# 131. Xeque

Visual + acessível.

---

# 132. Mate

Resultado + board final.

---

# 133. Stalemate/draw

Razão.

---

# 134. Promoção

Escolha Q/R/B/N.

---

# 135. Roque

Animação futura opcional, estado correto obrigatório.

---

# 136. En passant

Visual correto.

---

# 137. Claimable draw

Se a primeira versão decidir expor full FIDE claim behavior:

UI precisa permitir claim.

---

# 138. Decisão importante

## PENDENTE

A forma exata de interação para:

- threefold claim;
- 50-move claim;

precisa ser especificada antes de publicação.

Não encerrar automaticamente se a regra é claimable.

---

# 139. Game over

Bloqueia input e oferece ações pós-partida.

---

# 140. Fase 11 — saída

- [ ] todos special states visíveis;
- [ ] promotion UX;
- [ ] game result;
- [ ] reasons;
- [ ] no dead controls;
- [ ] accessibility announcements;
- [ ] critical UI tests.

---

# 141. FASE 12 — POLIMENTO E ADAPTAÇÃO

## OBJETIVO

Transformar “funciona” em “produto utilizável”.

---

# 142. Identidade

Aplicar de forma consistente:

- carvão/preto;
- dourado;
- verde;
- board verde/marfim;
- branding Gambitol.

---

# 143. Contraste

Medir.

---

# 144. Touch targets

Revisar.

---

# 145. Font scaling

Testar.

---

# 146. Device/window sizes

Testar:

- compact;
- short height;
- phone maior.

Medium/expanded conforme escopo.

---

# 147. Edge-to-edge

Revalidar em Android recente.

---

# 148. API mínima

Validar API 24.

---

# 149. Accessibility

TalkBack critical flow.

---

# 150. Visual polish

- spacing;
- typography;
- state colors;
- piece legibility;
- feedback.

---

# 151. Animações

Somente agora ou gradualmente se não dificultarem correctness.

---

# 152. Haptics/sound

## PENDENTE

Entram somente se aprovados.

Não são requirement da primeira publicação.

---

# 153. Histórico

## FUTURO / PENDENTE

README inicial colocou histórico como evolução futura.

Logo:

> não bloqueia V1.

---

# 154. Timer

## FUTURO / PENDENTE

Também evolução futura no README.

Não bloqueia V1.

---

# 155. Save game

## FUTURO / PENDENTE

Também evolução futura.

Não bloquear primeira versão salvo decisão posterior.

---

# 156. Fase 12 — saída

- [ ] UI coerente;
- [ ] visual aprovado;
- [ ] touch targets;
- [ ] contrast;
- [ ] TalkBack básico;
- [ ] compact phones;
- [ ] min/recent API;
- [ ] no layout blockers;
- [ ] no P0/P1 conhecidos.

---

# 157. FASE 13 — CANDIDATO A RELEASE

## OBJETIVO

Parar de desenvolver feature e testar produto como pacote.

---

# 158. Feature freeze

## PROPOSTO

Durante RC:

não adicionar recurso novo.

Somente:

- bugfix;
- compliance;
- polish necessário;
- release preparation.

---

# 159. Build próximo de produção

Testar configuração:

- release-like;
- R8/minification quando aplicável;
- resources;
- signing setup;
- versioning.

---

# 160. Test matrix

Documento 08 define.

---

# 161. Smoke físico

Obrigatório.

---

# 162. Core quality

As diretrizes atuais de qualidade do Android tratam como fundação mínima aspectos como:

- UX consistente;
- estabilidade;
- compatibilidade;
- performance;
- adaptação.

Também recomendam testar apps em diferentes formatos e janelas. citeturn286669search0turn286669search7

---

# 163. Performance

Gambitol é um jogo por turnos usando Android Views, não um renderer OpenGL/Vulkan contínuo.

Portanto, muitas métricas de jogos 3D como frame pacing do AGDK não são automaticamente requisitos.

---

# 164. Google Play Games Level Up

As diretrizes atuais do programa Level Up possuem benchmarks específicos de estabilidade/performance para jogos e reconhecem exceções para jogos que usam HWUI/Composer ou só produzem novos frames em interação, exatamente mais próximo do perfil do Gambitol. citeturn583201search2

## IMPORTANTE

Participar desse programa não é requisito inicial do projeto.

---

# 165. Não adicionar game SDK por categorização

O fato de Gambitol ser “jogo” não significa precisar:

- OpenGL;
- Vulkan;
- AGDK Frame Pacing;
- GameActivity;
- NDK.

A tecnologia deve resolver problema real.

---

# 166. Fase 13 — saída

- [ ] release candidate build;
- [ ] engine green;
- [ ] Perft green;
- [ ] Lint reviewed;
- [ ] critical UI flow green;
- [ ] physical device smoke;
- [ ] API min/recent;
- [ ] accessibility pass;
- [ ] no blocker;
- [ ] store/release documentation preparada.

---

# 167. FASE 14 — TESTES PLAY / PUBLICAÇÃO

## OBJETIVO

Validar o produto dentro do processo real da Google Play.

---

# 168. Target API é requisito temporal

## VERIFICADO EM 2026-08-22

A partir de **31 de agosto de 2026**, novos apps e atualizações submetidos ao Google Play precisam segmentar **Android 16 / API 36 ou superior** para apps Android de telefone/tablet comuns. citeturn566984search0turn566984search1

---

# 169. Roadmap não congela targetSdk

## DECIDIDO

Quando a publicação ocorrer:

consultar novamente a exigência atual.

Não assumir que API 36 continuará sendo o requisito no futuro.

---

# 170. MinSdk ≠ targetSdk

O projeto pode manter minSdk 24 e target recente, desde que compatibilidade esteja correta.

---

# 171. Test tracks

Google Play oferece:

- internal;
- closed;
- open;
- production.

A sequência concreta depende da conta e do estágio.

---

# 172. Requisito de contas pessoais recentes

## VERIFICADO EM 2026-08-22

Contas pessoais do Play Console criadas após **13 de novembro de 2023** precisam, atualmente, realizar teste fechado com pelo menos **12 testadores** participando continuamente por **14 dias** antes de solicitar acesso à produção. citeturn290955search0turn290955search3

---

# 173. Condicionalidade

## IMPORTANTE

Esse requisito depende do tipo/data da conta do desenvolvedor.

O documento não afirma que ele necessariamente se aplica à conta usada pelo Gambitol sem verificar a conta real.

---

# 174. Teste interno

Pode ser usado cedo para distribuição controlada.

---

# 175. Teste fechado

Excelente para:

- regras;
- device diversity;
- UX;
- crashes;
- feedback.

Mesmo se o requisito formal não se aplicar, pode ser útil.

---

# 176. Open testing

Somente quando estratégia justificar.

---

# 177. Produção

É o último passo.

Não o primeiro upload.

---

# 178. Fase 14 — trabalho

- Play Console;
- listing;
- policies;
- Data Safety;
- content rating;
- privacy quando aplicável;
- AAB;
- testing track;
- feedback;
- pre-launch report;
- fixes;
- production.

Detalhes no documento 12.

---

# 179. Fase 14 — saída

- [ ] requisitos Play atuais verificados;
- [ ] track adequado;
- [ ] testers/requisitos atendidos se aplicável;
- [ ] pre-launch report revisado;
- [ ] blockers corrigidos;
- [ ] produção aprovada;
- [ ] release tag/documentação alinhada.

---

# 180. FASE 15 — PÓS-LANÇAMENTO

## OBJETIVO

Não abandonar o aplicativo depois do botão “Publicar”.

---

# 181. Monitorar

- crashes;
- ANRs;
- Android vitals;
- reviews;
- feedback;
- regras relatadas;
- compatibility;
- políticas.

---

# 182. Primeiro pós-release

Prioridade:

```text
ESTABILIDADE
↓
BUGS
↓
UX
↓
SÓ DEPOIS FEATURE NOVA
```

---

# 183. Não reagir a uma review isolada com feature imediata

Agrupar evidências.

---

# 184. Regressão real

Criar teste.

---

# 185. Crash

Diagnosticar antes de novo recurso.

---

# 186. Policy changes

Monitorar Play Console.

---

# 187. Android target updates

Planejar antes do prazo.

---

# 188. Fase 15 — saída

Não possui “fim”.

É manutenção do produto.

---

# 189. FASE 16+ — EVOLUÇÕES

Depois de V1 estável, entram candidatos.

Nenhum é automático.

---

# 190. Candidato: timer

## VALOR

Transforma partida casual em chess clock.

---

# 191. Dependências do timer

- fonte de tempo;
- lifecycle;
- background;
- timeout;
- UI;
- testes;
- regras de mate possível.

---

# 192. Timer não é “só dois TextViews”

---

# 193. Candidato: histórico de jogadas

## VALOR

- revisão;
- aprendizado;
- export;
- portfolio.

---

# 194. Dependências

- Move representation;
- notation;
- UI;
- potentially persistence.

---

# 195. Candidato: salvar partidas

## VALOR

Retomar jogo.

---

# 196. Dependências

- serialization;
- versioning;
- persistence;
- process death;
- migrations futuras.

---

# 197. Candidato: FEN

## VALOR

- debug;
- compartilhar posição;
- tests;
- analysis.

---

# 198. Candidato: PGN

## VALOR

- export;
- import;
- interoperability.

---

# 199. Candidato: IA

## VALOR

Jogar sozinho.

---

# 200. Opções de IA

Podem incluir:

- engine própria;
- algoritmo educativo;
- integração com engine externa.

Nenhuma escolhida.

---

# 201. IA própria

Excelente para aprendizado:

- minimax;
- alpha-beta;
- evaluation;
- move ordering.

Mas complexidade alta.

---

# 202. Stockfish

Pode oferecer força.

Mas traz:

- integração;
- licença;
- native/NDK potencial;
- processo/threads;
- packaging.

Não usar sem decisão.

---

# 203. Candidato: níveis de dificuldade

Dependem da IA.

---

# 204. Candidato: multiplayer online

Depois de produto local maduro.

---

# 205. Candidato: Google Play Games Services

A documentação atual oferece recursos para jogos Java como:

- autenticação;
- achievements;
- leaderboards;
- events;
- friends;
- saved games. citeturn583201search4

## IMPORTANTE

Disponibilidade da API não é motivo suficiente para integração.

---

# 206. PGS authentication

Só se serviço futuro precisar.

---

# 207. Achievements

Podem aumentar engagement.

Não pertencem ao core de xadrez.

---

# 208. Leaderboards

Precisariam métrica de ranking clara.

Não inventar “wins” leaderboard sem desenho de produto.

---

# 209. Saved games

Pode ser útil depois da persistência local.

---

# 210. Friends

Futuro online.

---

# 211. Candidato: estatísticas

Exemplos futuros:

- partidas;
- vitórias;
- empates;
- derrotas;
- tempo médio.

Só quando dados existirem.

---

# 212. Candidato: personalização

- board themes;
- piece sets;
- sounds.

---

# 213. Candidato: monetização

Documento 13 decidirá.

---

# 214. Candidato: ads

Só depois de UX base.

---

# 215. Candidato: compra única

Pode remover anúncios/desbloquear temas.

Não decidido.

---

# 216. Candidato: premium cosmetics

Pode preservar integridade competitiva melhor que pay-to-win.

Não decidido.

---

# 217. Sem pay-to-win

## PRINCÍPIO PROPOSTO

Não vender vantagem em jogo de xadrez.

---

# 218. Candidato: puzzles

Produto quase separado.

Depende de:

- dataset;
- validation;
- UI;
- progression.

---

# 219. Candidato: análise de partida

Depende de engine de avaliação.

---

# 220. Candidato: tutorial

Pode aproveitar engine/hints.

---

# 221. Candidato: variantes

Somente depois da engine padrão madura.

---

# 222. Candidato: Chess960

Exigiria:

- castling diferente;
- initial positions;
- tests;
- UI.

---

# 223. Candidato: online clock

Mais complexo que timer local.

---

# 224. Candidato: anti-cheat

Só online competitivo.

---

# 225. Candidato: backend

Só quando feature online exige.

---

# 226. Candidato: analytics

Só quando perguntas de produto justificarem coleta.

---

# 227. Candidato: crash reporting

Pode ser introduzido antes de analytics, se necessário.

Ainda exige análise de SDK/privacy.

---

# 228. Não criar roadmap infinito

## DECIDIDO

Backlog futuro deve ser reavaliado após dados reais de usuários.

Não prometer cinco anos de features imaginárias.

---

# 229. Priorização futura

Perguntar:

1. resolve problema real?
2. quantos usuários se beneficiam?
3. depende de quê?
4. qual custo?
5. qual risco?
6. como medir sucesso?
7. atrapalha aprendizado/qualidade?
8. exige política/privacidade?

---

# 230. Matriz de prioridade conceitual

```text
ALTO VALOR + BAIXO CUSTO
→ candidato forte

ALTO VALOR + ALTO CUSTO
→ planejar

BAIXO VALOR + BAIXO CUSTO
→ só se não desviar foco

BAIXO VALOR + ALTO CUSTO
→ evitar
```

---

# 231. Prioridade não é só esforço

Pode haver dependência estratégica.

Exemplo:

FEN pode ter baixo valor visual, mas alto valor para:

- testes;
- debug;
- PGN;
- IA.

---

# 232. Technical enabler

Uma etapa pode não gerar UI, mas desbloquear várias.

Engine é principal exemplo.

---

# 233. User-facing milestone

Outra etapa gera valor visível.

Board/interaction.

---

# 234. Alternar invisível e visível

## PROPOSTO COMO MOTIVAÇÃO

Depois de blocos longos de engine, integrar algo visual pode ajudar aprendizado e motivação.

Mas não quebrar dependência correta só para ver pixel cedo.

---

# 235. Vertical slice

Quando foundation permitir:

implementar slices completos pequenos.

Exemplo:

```text
selecionar cavalo
↓
engine legal moves
↓
highlight
↓
move
↓
render
```

Isso testa integração de ponta a ponta.

---

# 236. Não esperar engine “perfeita para sempre”

Após núcleo confiável, UI pode avançar em paralelo incrementalmente.

---

# 237. Mas não duplicar regras enquanto espera

Se UI precisa regra não pronta:

implementar a regra no motor primeiro.

---

# 238. Entry criteria

Toda fase possui pré-condições.

Não “começar porque parece interessante”.

---

# 239. Exit criteria

Toda fase precisa prova.

---

# 240. Não confundir exit criteria com perfeição

Pode haver dívida menor documentada.

Não pode haver blocker fundamental.

---

# 241. Quality debt permitida

Exemplo:

- animação ainda instantânea.

---

# 242. Quality debt não permitida

Exemplo:

- roque às vezes deixa rei em check.

---

# 243. P0/P1 bloqueiam avanço relevante

Documento 08 define severidade.

---

# 244. Risco técnico versus produto

Roadmap precisa tratar ambos.

---

# 245. Risco 1 — engine errada

Mitigação:

- docs FIDE;
- unit;
- Perft;
- regressão.

---

# 246. Risco 2 — Android acoplado ao domínio

Mitigação:

- módulo Java;
- dependency direction;
- tests host-side.

---

# 247. Risco 3 — UI bonita antes do motor

Mitigação:

- ordem das fases.

---

# 248. Risco 4 — overengineering

Mitigação:

- packages/módulos sob demanda;
- sem Hilt/repositories/usecases prematuros.

---

# 249. Risco 5 — feature creep

Mitigação:

- primeira versão explicitamente limitada.

---

# 250. Risco 6 — IA cedo demais

Mitigação:

- após engine hardening.

---

# 251. Risco 7 — multiplayer cedo demais

Mitigação:

- V1 local.

---

# 252. Risco 8 — monetização estragando UX

Mitigação:

- produto primeiro;
- documento 13.

---

# 253. Risco 9 — Play policies mudarem

Mitigação:

- fatos temporais verificados perto da release.

---

# 254. Risco 10 — build moderno incompatível com minSdk

Mitigação:

- JDK/source/desugaring;
- API 24 testing.

---

# 255. Risco 11 — test suite lenta

Mitigação:

- engine JVM;
- pirâmide;
- slow lane separada.

---

# 256. Risco 12 — custom board inacessível

Mitigação:

- accessibility como critério de renderer.

---

# 257. Risco 13 — projeto virar curso eterno

Mitigação:

- milestones demonstráveis;
- integração;
- publicação como objetivo.

---

# 258. Risco 14 — publicar cedo demais

Mitigação:

- RC;
- tracks;
- quality gates.

---

# 259. Risco 15 — nunca publicar por perfeccionismo

Mitigação:

- V1 escopo fechado;
- extras pós-release.

---

# 260. Definition of Ready para feature

## PROPOSTO

Antes de começar:

- [ ] objetivo claro;
- [ ] escopo;
- [ ] dependências prontas;
- [ ] regra documentada;
- [ ] nomes estruturais aprovados se necessários;
- [ ] arquivos reais conhecidos;
- [ ] teste esperado conhecido;
- [ ] não pertence a fase futura.

---

# 261. Definition of Done

Usar documento 08 por tipo.

Roadmap apenas exige:

- implementação;
- teste;
- integração;
- documentação;
- validação.

---

# 262. Gate de transição de fase

Antes de dizer:

```text
FASE N concluída
```

mostrar evidência.

---

# 263. Evidência pode ser

- teste;
- build;
- screenshot;
- command output;
- demo;
- Perft;
- device run.

---

# 264. Não marcar fase por “código escrito”

---

# 265. Fase com pendência crítica

Não concluída.

---

# 266. Fase com melhoria opcional

Pode concluir e registrar backlog.

---

# 267. Branches versus fases

Uma fase longa deve ser quebrada em unidades Git pequenas.

---

# 268. Commits versus roadmap

Commit descreve mudança realizada.

Roadmap descreve capacidade.

---

# 269. Atualização deste documento

Não precisa atualizar toda semana.

Atualizar quando:

- escopo V1 muda;
- fase muda substancialmente;
- feature futura vira compromisso;
- dependência descoberta muda ordem;
- publicação exige nova etapa.

---

# 270. Não transformar roadmap em diário

Estado atual pode ser acompanhado no Git/projeto.

O documento é estrutura de direção.

---

# 271. Status macro permitido

Pode registrar:

```text
fase atual
última fase concluída
```

quando útil.

Mas sem obrigação de manutenção diária.

---

# 272. Roadmap visual compacto

```text
DOCUMENTAÇÃO
    ↓
BASELINE
    ↓
MOTOR JAVA
    ↓
MODELO
    ↓
MOVIMENTOS
    ↓
LEGALIDADE
    ↓
ESPECIAIS
    ↓
FIM/EMPATES
    ↓
HARDENING
    ↓
BOARD ANDROID
    ↓
INTERAÇÃO
    ↓
GAME STATES UI
    ↓
POLISH
    ↓
RELEASE CANDIDATE
    ↓
PLAY TESTING
    ↓
PRODUÇÃO
    ↓
EVOLUÇÕES
```

---

# 273. Dependências críticas

```text
UI MOVES
depends on
LEGAL MOVES ENGINE
```

---

# 274. Checkmate dependency

```text
ATTACK
+ LEGAL MOVES
→ CHECKMATE
```

---

# 275. Castling dependency

```text
ATTACK
+ HISTORY RIGHTS
+ PATH
→ CASTLING
```

---

# 276. Repetition dependency

```text
FULL POSITION IDENTITY
+ HISTORY
→ REPETITION
```

---

# 277. IA dependency

```text
LEGAL MOVE GENERATION
+ STATE TRANSITIONS
+ PERFORMANCE BASE
→ AI
```

---

# 278. Multiplayer dependency

```text
STABLE GAME STATE
+ SERIALIZATION
+ NETWORK ARCHITECTURE
→ MULTIPLAYER
```

---

# 279. Save dependency

```text
STABLE STATE MODEL
+ SERIALIZATION
→ PERSISTENCE
```

---

# 280. PGN dependency

```text
MOVE HISTORY
+ SAN/NOTATION
→ PGN
```

---

# 281. Analysis dependency

```text
ENGINE/EXTERNAL ENGINE
+ POSITION IMPORT
→ ANALYSIS
```

---

# 282. Roadmap e arquitetura Android

A documentação oficial Android reforça separação de responsabilidades, state-driven UI e fronteiras claras, mas também afirma que recomendações devem ser adaptadas ao aplicativo. citeturn290955search2turn290955search5

Por isso, o roadmap não força:

- repository;
- domain use case layer;
- Flow/Kotlin;

onde não houver necessidade.

---

# 283. Roadmap e qualidade adaptativa

As guidelines atuais do Android tratam apps como executáveis em uma variedade crescente de janelas e form factors. citeturn286669search0turn286669search7

O roadmap responde progressivamente:

1. phone compact primeiro;
2. não quebrar em resize;
3. expandir quando escopo justificar.

---

# 284. Não prometer Tier 2 de large-screen imediatamente

A qualidade “Adaptive optimized” envolve suporte a múltiplas telas, janelas e entradas. citeturn286669search7

Isso pode ser objetivo posterior.

---

# 285. Core quality primeiro

A primeira publicação precisa:

- ser estável;
- funcionar;
- ter UX coerente;
- suportar plataforma alvo.

---

# 286. O jogo usa Android Views

Isso importa para roadmap.

Não precisamos adotar:

- Compose;
- engine gráfica;
- Unity;

para provar modernidade.

---

# 287. Java continua a linguagem principal

Nenhuma fase exige migração para Kotlin.

---

# 288. Kotlin DSL não altera isso

Build scripts `.kts` continuam.

---

# 289. Android game-specific SDKs

A central atual de desenvolvimento de jogos Android oferece diversas ferramentas especializadas. citeturn583201search1

O roadmap só adota se houver problema correspondente.

---

# 290. Game performance

Gambitol provavelmente desenha novos estados principalmente em interação.

Portanto:

- jank;
- touch latency;
- UI main thread;

ainda importam.

Mas não temos um game loop 60 FPS obrigatório por natureza.

---

# 291. Level Up performance exception

As atuais diretrizes Level Up explicitamente reconhecem exceções de FPS para jogos que usam HWUI/Composer ou só enviam novos frames na interação. citeturn583201search2

Isso evita aplicar benchmark de shooter 3D a um tabuleiro de xadrez em Views.

---

# 292. Performance gate adequado ao Gambitol

- toque responde;
- animação fluida quando usada;
- sem ANR;
- sem travamento;
- board render rápido;
- IA futura off-main.

---

# 293. Startup

Precisa ser razoavelmente rápido.

Core quality atual inclui expectativa de startup responsivo e estabilidade. citeturn286669search3

---

# 294. Não otimizar startup antes de ter app

Medir perto do RC.

---

# 295. Sem backend = grande vantagem inicial

Menos:

- falhas;
- latência;
- privacy;
- infra;
- custos.

Aproveitar.

---

# 296. Sem login = menor onboarding

Usuário pode jogar mais rápido.

---

# 297. Sem monetização = foco no produto

Durante V1.

---

# 298. Sem IA = engine mais observável

Durante construção.

---

# 299. Escopo da primeira tela real

## PROPOSTO

Pode começar apenas com a partida.

Não precisamos de:

- splash custom;
- home;
- onboarding;
- profile.

---

# 300. Home entra quando houver escolhas

Exemplo:

- local;
- AI;
- online;
- load game.

Antes disso, Home pode ser tela extra sem função.

---

# 301. Settings entra quando houver settings

---

# 302. History entra quando houver history

---

# 303. Bottom bar deve acompanhar features reais

---

# 304. MVP técnico UI

Pode usar visual mais simples antes do design final.

---

# 305. Refactor visual depois

Desde que não comprometa arquitetura.

---

# 306. Não deixar design final para um único mega-refactor

Aplicar progressivamente.

---

# 307. Roadmap de UI em slices

Slice 1:

```text
board
```

Slice 2:

```text
pieces
```

Slice 3:

```text
selection
```

Slice 4:

```text
move
```

Slice 5:

```text
turn
```

Slice 6:

```text
special states
```

---

# 308. Roadmap de engine em slices

Slice 1:

```text
position
```

Slice 2:

```text
board
```

Slice 3:

```text
piece moves
```

Slice 4:

```text
attack
```

Slice 5:

```text
legal
```

Slice 6:

```text
special
```

Slice 7:

```text
ending
```

---

# 309. Cada slice termina verde

Não acumular seis slices quebrados.

---

# 310. Quando introduzir refactor

Quando:

- duplicação real apareceu;
- nome está errado;
- responsabilidade cresceu;
- teste ficou difícil.

---

# 311. Quando não refatorar

Porque vimos padrão novo no YouTube.

---

# 312. Quando introduzir interface

Quando múltiplas implementações/boundary justificarem.

---

# 313. Quando introduzir persistence

Quando houver feature que precisa sobreviver sessão.

---

# 314. Quando introduzir dependency injection framework

Quando graph justificar.

Provavelmente bem depois.

---

# 315. Quando introduzir CI

## PROPOSTO

Depois de:

- baseline estável;
- testes reais;
- repositório remoto.

Não precisa esperar release.

---

# 316. CI mínimo

- build;
- tests;
- lint.

---

# 317. CI não bloqueia fase 2 se remoto ainda não estiver pronto

Pode entrar durante engine.

---

# 318. Quando introduzir coverage

Depois de suite real suficiente.

---

# 319. Quando introduzir mutation

Depois de coverage/suite madura.

---

# 320. Quando introduzir Test Lab

Perto de RC ou quando device-specific bug exigir.

---

# 321. Quando introduzir signed commits

Pode ser independente de produto.

Não bloqueia feature.

---

# 322. Quando introduzir release tags

Primeiro milestone realmente distribuível.

---

# 323. Quando criar Play Console app

## PROPOSTO

Não esperar último dia.

Mas também não precisa ocorrer antes de identidade/package/políticas estarem minimamente estáveis.

---

# 324. ApplicationId já está definido

Isso ajuda.

---

# 325. Play Console setup early

Pode revelar requisitos:

- naming;
- policies;
- testers.

Documento 12 definirá o momento operacional.

---

# 326. Fechamento de escopo da V1

## REGRA

Quando entrarmos na fase RC:

nenhuma feature nova entra sem:

- bug crítico;
- compliance;
- bloqueio de usabilidade.

---

# 327. “Já que estamos aqui…”

É uma das principais origens de scope creep.

Exemplo:

> “Já que fizemos histórico, vamos colocar cloud sync.”

Não.

---

# 328. “É só um botão”

Botão pode implicar:

- state;
- persistence;
- undo;
- tests;
- UX;
- policy.

Nada é “só botão” até provar.

---

# 329. Change request

Feature nova durante V1:

classificar:

```text
NECESSÁRIA PARA V1
ou
PÓS-V1
```

---

# 330. Critério NECESSÁRIA PARA V1

Precisa:

- cumprir chess core;
- impedir bug;
- tornar jogo usável;
- cumprir Android/Play;
- cumprir qualidade.

---

# 331. Critério PÓS-V1

Aumenta valor, mas produto continua correto sem ela.

---

# 332. Exemplo V1

Roque:

```text
necessário
```

porque regras completas definidas.

---

# 333. Exemplo pós-V1

Timer:

```text
não necessário
```

porque jogo local sem relógio continua xadrez jogável.

---

# 334. Exemplo pós-V1

Histórico persistido:

não necessário.

---

# 335. Exemplo V1

Promotion UI:

necessário.

---

# 336. Exemplo V1

Game result:

necessário.

---

# 337. Exemplo pós-V1

Avatars:

não necessário.

---

# 338. Exemplo pós-V1

Custom themes:

não necessário.

---

# 339. Exemplo V1

Acessibilidade básica de controles:

qualidade necessária.

---

# 340. Exemplo V1 condicional

Custom board virtual accessibility:

necessária se Custom View for usada e quisermos interface acessível coerente.

---

# 341. Exemplo V1

Target API compatível com Play na data da publicação:

obrigatório.

---

# 342. Exemplo V1

AAB correto:

obrigatório para Play.

Detalhe no doc12.

---

# 343. Escopo release 0.x versus 1.0

## PENDENTE

Não decidir números de versão aqui.

Documento 12 fará.

---

# 344. Primeira publicação pode ser 1.0.0

Ou outra estratégia.

Não antecipar.

---

# 345. Beta interna

Pode usar versão pré-release.

---

# 346. Roadmap e tags

Tag apenas quando milestone merece.

---

# 347. Roadmap e README

README deve refletir capacidade real quando features forem concluídas.

Não marcar futuro como existente.

---

# 348. Roadmap e portfólio

Cada fase gera material.

---

# 349. Conteúdo da Fase 1

- setup;
- build;
- JDK;
- Gradle.

---

# 350. Conteúdo Fase 2

- modularização;
- Java puro.

---

# 351. Conteúdo Fase 5

- pseudo/legal;
- pins.

---

# 352. Conteúdo Fase 6

- special rules.

---

# 353. Conteúdo Fase 8

- Perft.

---

# 354. Conteúdo Fase 9

- responsive board.

---

# 355. Conteúdo Fase 12

- accessibility.

---

# 356. Conteúdo Fase 14

- Play release.

---

# 357. Portfólio não muda a ordem técnica

Não fazer feature ruim só porque rende vídeo.

---

# 358. 🎥 MOMENTO BOM PARA GRAVAR — roadmap virando código

No início da retomada:

mostrar documentação → primeira fase → build real.

---

# 359. 🎥 MOMENTO BOM PARA GRAVAR — primeira engine slice

Position/board/test.

---

# 360. 🎥 MOMENTO BOM PARA GRAVAR — primeira peça

Mostrar regra, teste e implementação.

---

# 361. 🎥 MOMENTO BOM PARA GRAVAR — legalidade global

Pin/check.

---

# 362. 🎥 MOMENTO BOM PARA GRAVAR — primeiro jogo na tela

Quando UI realmente mover peça via engine.

---

# 363. 🎥 MOMENTO BOM PARA GRAVAR — RC

Mostrar gates:

- tests;
- lint;
- device;
- Play.

---

# 364. COMO EXPLICAR EM ENTREVISTA — roadmap técnico

> “Eu organizei o Gambitol por dependências de domínio. Primeiro estabilizei o build e separei o motor Java do Android; depois implementei representação, movimentos pseudo-legais, ataques e legalidade, regras especiais e estados finais. Só então usei essa API estável para construir a UI e preparar a publicação.”

---

# 365. COMO EXPLICAR EM ENTREVISTA — escopo

> “A primeira versão foi deliberadamente local para dois jogadores. IA, multiplayer e monetização ficaram fora do núcleo inicial para evitar acoplamento e permitir validar regras, arquitetura, testes e experiência antes de aumentar a complexidade.”

---

# 366. COMO EXPLICAR EM ENTREVISTA — qualidade

> “Cada fase possuía critérios de saída. O motor precisava de testes e Perft, a integração Android precisava de build/Lint/UI tests, e a release adicionava validação em dispositivo e Play testing.”

---

# 367. COMO EXPLICAR EM ENTREVISTA — feature creep

> “Eu separava requisito de V1 de evolução futura. Uma feature só entrava cedo se fosse necessária para correção do xadrez, usabilidade, compatibilidade ou publicação.”

---

# 368. Macro roadmap por valor

```text
PROVAR QUE COMPILA
↓
PROVAR QUE O MOTOR EXISTE
↓
PROVAR QUE O XADREZ É CORRETO
↓
PROVAR QUE É JOGÁVEL
↓
PROVAR QUE É BOM DE USAR
↓
PROVAR QUE É PUBLICÁVEL
↓
APRENDER COM USUÁRIOS
↓
EXPANDIR
```

---

# 369. Macro roadmap por risco

```text
BUILD RISK
↓
ARCHITECTURE RISK
↓
DOMAIN CORRECTNESS RISK
↓
INTEGRATION RISK
↓
UX RISK
↓
DEVICE/PLATFORM RISK
↓
MARKET RISK
```

---

# 370. Por que essa ordem funciona

Um risco removido cedo evita construir sobre fundação errada.

---

# 371. Exemplo ruim

Construir multiplayer antes de saber se repetition key está correta.

---

# 372. Exemplo bom

Estabilizar state model primeiro.

Depois serialização.

Depois rede.

---

# 373. Incremento vertical após foundation

Depois da Fase 8, features podem ser desenvolvidas em slices que tocam:

- engine;
- ViewModel;
- View;
- test.

---

# 374. Não desenvolver camada inteira sem uso

Exemplo:

não criar 20 ViewModels sem telas.

---

# 375. Não desenvolver todas telas antes da engine

---

# 376. Roadmap e documentação futura

Doc 11 registrará decisões importantes conforme forem tomadas.

---

# 377. Doc 12

Vai detalhar:

- signing;
- AAB;
- versions;
- Play.

---

# 378. Doc 13

Monetização pós-core.

---

# 379. Doc 14

Conteúdo/portfólio ao longo das fases.

---

# 380. Doc 15

Problemas recorrentes.

---

# 381. Critérios para mover feature futura para roadmap ativo

## PROPOSTO

Uma feature sai de “futuro” quando:

1. problema está claro;
2. valor está claro;
3. dependências estão prontas;
4. custo foi entendido;
5. não ameaça V1;
6. foi aprovada explicitamente.

---

# 382. Critérios para abandonar feature

Pode acontecer.

Se:

- pouco valor;
- custo alto;
- política inviável;
- usuários não precisam;
- contradiz visão.

Roadmap não é promessa eterna.

---

# 383. Critérios para reordenar fases

Somente se nova evidência mostrar dependência diferente.

---

# 384. Bug crítico pode interromper roadmap

Sim.

---

# 385. Upgrade obrigatório de Android pode interromper roadmap

Sim.

---

# 386. Policy deadline pode interromper roadmap

Sim.

---

# 387. Ideia legal do fim de semana não interrompe

Não.

---

# 388. Escopo e performance

Não benchmark antes de problema.

Mas não ignorar jank.

---

# 389. Escopo e segurança

Mesmo app local precisa:

- nenhum segredo;
- components seguros;
- dependencies revisadas.

---

# 390. Escopo e privacy

Sem coleta = menor superfície.

Se SDK futuro coletar dados:

reavaliar.

---

# 391. Escopo e permissions

Core não precisa permission sensível.

---

# 392. Escopo e internet

Core não precisa.

---

# 393. Escopo e storage

Só quando save/history entra.

---

# 394. Escopo e account

Não.

---

# 395. Escopo e notifications

Não.

---

# 396. Escopo e background work

Não inicialmente.

---

# 397. Escopo e services

Não criar Android Service sem caso real.

---

# 398. Escopo e Compose

Não migrar.

---

# 399. Escopo e Kotlin

Não necessário.

---

# 400. Escopo e native

Não.

---

# 401. Escopo e external engine

Não V1.

---

# 402. Escopo e FIDE online regulations

Core local usa FIDE Laws.

Regulamentos online ficam para feature online.

---

# 403. Escopo e tournament arbiter rules

Não simular árbitro físico.

---

# 404. Escopo e touch-move rule física

Não.

Documento 05.

---

# 405. Escopo e clock tournament rules

Quando timer competitivo entrar.

---

# 406. Escopo e notation

Pode entrar após histórico.

Não bloqueia board jogável, salvo se UI final decidir mostrar moves.

---

# 407. Histórico no mockup

Visual reference não torna obrigatório.

---

# 408. Timer no mockup

Mesmo.

---

# 409. Bottom controls no mockup

Só funcionalidades reais.

---

# 410. Feature flags

Não precisamos para features inexistentes.

---

# 411. Experimental branch

Pode existir para spike.

Mas não entra main sem decisão.

---

# 412. Spike técnico

## PERMITIDO

Quando tecnologia desconhecida precisa ser explorada.

Exemplo:

- Canvas accessibility;
- renderer.

---

# 413. Spike não é produção

Resultado pode ser descartado.

---

# 414. Critério de spike

Pergunta objetiva:

> precisamos descobrir X para decidir Y.

---

# 415. Timebox de spike

Pode ser útil, mas não fixar neste documento.

---

# 416. Spike de renderer

Bom candidato antes da Fase 9.

Comparar:

- 64 Views;
- Custom View.

---

# 417. Spike de accessibility

Pode acompanhar renderer.

---

# 418. Spike de animation

Só depois.

---

# 419. Spike de Stockfish

Pós-V1.

---

# 420. Spike de multiplayer

Pós-V1.

---

# 421. Spike de billing

Pós-V1.

---

# 422. Backlog categories

## PROPOSTO

```text
NOW
NEXT
LATER
RESEARCH
```

Sem datas obrigatórias.

---

# 423. NOW

Fase atual.

---

# 424. NEXT

Dependência imediatamente posterior.

---

# 425. LATER

Aprovado conceitualmente, não ativo.

---

# 426. RESEARCH

Ideia não decidida.

---

# 427. Roadmap não precisa Jira

Um documento + Git pode bastar.

---

# 428. Issue tracker entra quando ajuda

---

# 429. Kanban não é requisito

---

# 430. Milestone GitHub pode ser adotado depois

---

# 431. Planejamento por release

Depois de V1:

pode ficar:

```text
1.x maintenance
2.0 larger feature
```

somente quando houver releases reais.

---

# 432. Não criar 2.0 agora

---

# 433. Roadmap de aprendizado Java

Fases ensinam naturalmente.

---

# 434. Fase 2

- modules;
- Gradle dependency.

---

# 435. Fase 3

- classes;
- objects;
- constructors;
- encapsulation;
- enums;
- equality.

---

# 436. Fase 4

- methods;
- inheritance/composition;
- collections;
- algorithms.

---

# 437. Fase 5

- abstraction;
- state;
- simulation;
- testing.

---

# 438. Fase 6

- historical state;
- atomic operations.

---

# 439. Fase 7

- collections/maps;
- domain modeling.

---

# 440. Fase 8

- performance/debug;
- test engineering.

---

# 441. Roadmap de aprendizado Android

Fase 1:

- Gradle;
- SDK;
- JDK.

---

# 442. Fase 9

- Views;
- XML;
- resources;
- custom drawing;
- density;
- layout.

---

# 443. Fase 10

- touch;
- state;
- lifecycle;
- ViewModel.

---

# 444. Fase 11

- dialogs;
- UI states;
- accessibility.

---

# 445. Fase 12

- responsive;
- insets;
- system bars;
- polish.

---

# 446. Fase 13/14

- release;
- Play Console.

---

# 447. Roadmap de aprendizado Git

Cada fase será branch/commit incremental conforme doc 07.

---

# 448. Não criar todos os commits agora

Naturalmente durante desenvolvimento.

---

# 449. Roadmap de testes

Engine desde Fase 2.

Não “fase de testes no fim”.

---

# 450. Testes crescem junto

Fase 3:

unit.

---

# 451. Fase 5:

legal/attack.

---

# 452. Fase 6:

edge.

---

# 453. Fase 8:

Perft.

---

# 454. Fase 10:

integration.

---

# 455. Fase 11:

UI.

---

# 456. Fase 13:

device/release.

---

# 457. Roadmap de conteúdo

Doc 14 detalhará.

Mas não precisa filmar tudo.

---

# 458. Roadmap de monetização

Só pós-core.

---

# 459. Roadmap de reputation/portfolio

Publicação real é milestone.

---

# 460. Open source

## PENDENTE

Repo público pode existir.

Licença ainda precisa ser decidida.

---

# 461. Public repo antes de licença

Pode gerar ambiguidade jurídica.

Doc 11/14 pode registrar.

---

# 462. App code visibility ≠ app free

---

# 463. Release signing key

Doc 12.

---

# 464. Naming já estabelecido

Gambitol.

Não revisitar sem necessidade.

---

# 465. Package já estabelecido

`br.com.raionorio.gambitol`.

---

# 466. Platform já estabelecida

Android.

---

# 467. Language já estabelecida

Java.

---

# 468. minSdk atual

API 24.

Pode mudar somente com decisão.

---

# 469. targetSdk

Temporal.

Não confundir com minSdk.

---

# 470. Quality target

Produto profissional o suficiente para Play e portfólio.

---

# 471. Success do roadmap

Não é completar todas features futuras.

É chegar a uma primeira versão correta e publicada, aprendendo o processo.

---

# 472. Failure do roadmap

Seria:

- dezenas de features;
- sem release;
- sem entender engine;
- sem tests.

---

# 473. Escopo mínimo de release não é pequeno tecnicamente

Implementar xadrez corretamente já é projeto significativo.

---

# 474. Não subestimar edge cases

Por isso especiais/empates possuem fases.

---

# 475. Não superestimar UI

Views conseguem representar um board sem engine gráfica dedicada.

---

# 476. Não subestimar accessibility

Especialmente renderer custom.

---

# 477. Não subestimar Play release

Policy/testing/target changes precisam planejamento.

---

# 478. Não superestimar backend

Não é necessário para local V1.

---

# 479. Não criar arquitetura para online agora

Pode distorcer engine.

---

# 480. Engine deve ser evolutiva

Independência permite online/AI depois sem projetá-los agora.

---

# 481. Freeze de domínio

Nunca absoluto.

Bugs/regras podem corrigir.

---

# 482. API stability do engine

Aumenta depois da Fase 8.

---

# 483. UI dependency cresce depois da Fase 9

Por isso limpar API antes.

---

# 484. Persistence after stable state

Bom princípio.

---

# 485. Network after serialization

Bom princípio.

---

# 486. AI after legal move generator

Obrigatório.

---

# 487. Monetization after UX

Obrigatório como estratégia.

---

# 488. Analytics after question

Coletar só porque SDK existe é desperdício/privacy.

---

# 489. Play Games Services after feature

Mesma regra.

---

# 490. Google Play Games Level Up

## FUTURO OPCIONAL

Programa atual busca elevar qualidade/engagement e possui requisitos próprios. citeturn583201search2

Não é requisito da primeira publicação.

---

# 491. Android Games APIs

## FUTURO OPCIONAL

A plataforma oferece Game Mode, performance tools etc.

Gambitol só adotará quando se aplicar.

---

# 492. Frame pacing

Provavelmente não necessário para board Views interativo.

---

# 493. Continuous rendering

Não existe inicialmente.

---

# 494. Battery optimization

Sem game loop/IA, carga deve ser baixa.

Medir depois.

---

# 495. IA pode mudar performance profile

Então roadmap de performance muda junto.

---

# 496. Online pode mudar battery/network profile

Também.

---

# 497. Observabilidade pós-release

Crash data será mais valioso que antecipação.

---

# 498. Android Vitals

Parte da manutenção futura.

---

# 499. App quality current guidance

O Android atualmente define core quality como fundação mínima e adaptive quality como complemento para diferentes tamanhos/form factors. citeturn286669search0

Roadmap traduz isso em:

```text
core correto primeiro
↓
adaptive progressivo
```

---

# 500. Escopo da V1 em uma frase

> **Um jogo de xadrez Android em Java, local para dois jogadores, com regras padrão completas do escopo documentado, interface clara, testada e pronta para distribuição na Google Play.**

---

# 501. Fora da V1 em uma frase

> **Tudo que exige IA, conta, rede, ranking, serviços online, monetização ou ecossistema adicional, salvo obrigação de publicação.**

---

# 502. O que pode entrar na V1 sem ser feature grande

Melhorias que diretamente aumentam:

- acessibilidade;
- estabilidade;
- clareza;
- compatibilidade.

---

# 503. Exemplo

Haptic discreto não é necessário.

Mas corrigir touch target sim.

---

# 504. Exemplo

Animation polish não é necessário.

Mas mostrar claramente check sim.

---

# 505. Exemplo

History screen não.

Mas indicar último movimento pode ser UX útil, ainda assim precisa aprovação de escopo se demandar estado adicional.

---

# 506. Critério final de V1

Um usuário deve conseguir:

1. abrir;
2. iniciar a partida;
3. jogar uma partida legal;
4. entender turno/estado;
5. concluir corretamente;
6. reiniciar/jogar novamente conforme fluxo aprovado;
7. fazer isso sem crash ou regra incorreta conhecida.

---

# 507. Sem tutorial obrigatório

Se usuário conhece xadrez.

---

# 508. Público iniciante

Pode exigir futura camada educativa.

Não bloqueia core.

---

# 509. Feedback dos primeiros testers

Pode promover feature pós-V1 para prioridade alta.

---

# 510. Mas feedback não muda regra FIDE

UX pode mudar.

Domínio não por votação.

---

# 511. Decision log

Quando roadmap mudar:

registrar no doc11.

---

# 512. Mudança de V1

Precisa justificar.

---

# 513. Mudança de tecnologia

Também.

---

# 514. Mudança de minSdk

Também.

---

# 515. Mudança de orientação

Também se arquitetural/produto.

---

# 516. Roadmap checkpoint template

Para cada fase ativa:

```text
FASE:
OBJETIVO:
ENTRADA:
TRABALHO ATUAL:
TESTES:
RISCO:
EVIDÊNCIA:
SAÍDA:
```

Pode ser usado em conversa, não precisa virar arquivo separado.

---

# 517. Não criar planilha de progresso obrigatória

Git/documentação já fornecem evidências.

---

# 518. Não atualizar porcentagem fictícia

“73% do projeto” quase nunca significa algo útil.

---

# 519. Usar capacidades

Melhor:

```text
legal move generation pronta
```

do que:

```text
engine 63%
```

---

# 520. Release readiness também por checklist, não porcentagem

---

# 521. First playable

## PROPOSTO

Marco após Fase 10.

---

# 522. Rules complete

Marco após Fase 7.

---

# 523. Engine validated

Marco após Fase 8.

---

# 524. UI complete

Marco após Fase 11.

---

# 525. Polish complete

Marco após Fase 12.

---

# 526. Release candidate

Marco Fase 13.

---

# 527. Play test

Fase 14.

---

# 528. Production

Fim da primeira grande jornada, não fim do produto.

---

# 529. Roadmap pós-V1 sugerido

## NÃO COMPROMISSO

Possível ordem baseada em dependências:

```text
timer
↓
history/notation
↓
save/resume
↓
statistics
↓
AI
↓
online services
↓
multiplayer
```

Mas usuários podem mudar prioridade.

---

# 530. AI antes de history?

Também possível.

Não fixar.

---

# 531. Multiplayer antes de AI?

Possível, mas muito mais infra.

Só se produto justificar.

---

# 532. Monetization timing

Pode ocorrer depois da V1 ou em release posterior.

Doc13.

---

# 533. Ads timing

Não entrar só porque app está na Play.

---

# 534. Portfolio timing

Conteúdo pode começar muito antes.

---

# 535. Public reputation

Qualidade e transparência são ativos.

---

# 536. Technical debt policy

Dívida deliberada precisa:

- ser conhecida;
- não comprometer core;
- ter motivo.

---

# 537. Hidden debt

Pior.

---

# 538. “Temporário” sem registro

Tende a virar permanente.

---

# 539. Spikes descartáveis

Marcar.

---

# 540. Experimental code

Não mergear como production sem revisão.

---

# 541. Prototype assets

Não se tornam final automaticamente.

---

# 542. Placeholder piece assets

Podem ser usados durante engine/UI.

Antes da release:

licença/qualidade final.

---

# 543. Placeholder strings

Substituir.

---

# 544. Placeholder icon

Substituir.

---

# 545. App name já final

Gambitol.

---

# 546. Launcher icon futuro

Identidade.

---

# 547. Store listing futuro

Doc12.

---

# 548. Screenshot assets futuro

Doc14/12.

---

# 549. Privacy policy

Necessidade dependerá de features/dados/policies.

Verificar no release.

---

# 550. Data Safety

Mesmo app sem coleta precisa preencher corretamente conforme Play.

Doc12.

---

# 551. Closed testing feedback loop

Se exigido/usado:

```text
build
↓
testers
↓
feedback
↓
triage
↓
fix
↓
new build
```

---

# 552. Não mudar regra por feedback incorreto

Verificar FIDE.

---

# 553. Bug triage during closed test

P0/P1 primeiro.

---

# 554. UX feedback

Agrupar padrões.

---

# 555. Feature requests

Backlog pós-V1.

---

# 556. Crash reports

Fix.

---

# 557. Device-specific bug

Reproduzir.

---

# 558. Final release decision

Não é:

> passaram 14 dias.

É:

> requisitos atendidos + produto pronto.

---

# 559. Requisito mínimo do Play não é quality target

12 testers/14 dias, quando aplicável, é entrada administrativa.

Não prova que o jogo está bom.

---

# 560. Target API não é quality target

É compatibilidade/policy.

---

# 561. Store approval não prova chess correctness

Nossa suite prova melhor.

---

# 562. User rating não prova correctness

Mas sinaliza experiência.

---

# 563. Engine tests não provam delight

UI/users.

---

# 564. Roadmap integra essas evidências

---

# 565. Checklist Fase 0

- [ ] docs 00–15;
- [ ] roadmap aprovado;
- [ ] pending decisions list;
- [ ] no coding structural decision premature.

---

# 566. Checklist Fase 1

- [ ] inspect Gradle;
- [ ] JDK;
- [ ] sync;
- [ ] build;
- [ ] run;
- [ ] tests;
- [ ] Git baseline.

---

# 567. Checklist Fase 2

- [ ] module name approved;
- [ ] package approved;
- [ ] pure Java module;
- [ ] unit test;
- [ ] dependency direction.

---

# 568. Checklist Fase 3

- [ ] state types;
- [ ] initial board;
- [ ] equality;
- [ ] invariants.

---

# 569. Checklist Fase 4

- [ ] rook;
- [ ] bishop;
- [ ] queen;
- [ ] knight;
- [ ] king basic;
- [ ] pawn basic;
- [ ] pseudo-legal.

---

# 570. Checklist Fase 5

- [ ] attacks;
- [ ] king safety;
- [ ] pins;
- [ ] legal generation;
- [ ] check.

---

# 571. Checklist Fase 6

- [ ] promotion;
- [ ] castling;
- [ ] en passant.

---

# 572. Checklist Fase 7

- [ ] mate;
- [ ] stalemate;
- [ ] dead;
- [ ] repetition;
- [ ] 50;
- [ ] 75;
- [ ] terminal state.

---

# 573. Checklist Fase 8

- [ ] Perft;
- [ ] regression;
- [ ] cross-validation;
- [ ] API review;
- [ ] coverage review.

---

# 574. Checklist Fase 9

- [ ] renderer;
- [ ] board;
- [ ] pieces;
- [ ] responsive;
- [ ] accessibility foundation.

---

# 575. Checklist Fase 10

- [ ] touch;
- [ ] selection;
- [ ] legal highlights;
- [ ] move;
- [ ] capture;
- [ ] turn.

---

# 576. Checklist Fase 11

- [ ] check;
- [ ] promotion UI;
- [ ] specials visual;
- [ ] result;
- [ ] draw claims if supported;
- [ ] game over.

---

# 577. Checklist Fase 12

- [ ] brand;
- [ ] contrast;
- [ ] touch;
- [ ] TalkBack;
- [ ] devices;
- [ ] insets;
- [ ] polish.

---

# 578. Checklist Fase 13

- [ ] feature freeze;
- [ ] release-like;
- [ ] all gates;
- [ ] physical;
- [ ] no P0/P1.

---

# 579. Checklist Fase 14

- [ ] current Play requirements;
- [ ] target;
- [ ] track;
- [ ] testers if applicable;
- [ ] pre-launch;
- [ ] production.

---

# 580. Fontes pesquisadas — arquitetura

## Guide to app architecture

https://developer.android.com/topic/architecture

Usado para:

- separation of concerns;
- Android lifecycle;
- UI não armazenar estado;
- boundaries;
- arquitetura adaptável.

Verificado em: 2026-08-22.

---

# 581. Fontes — recomendações de arquitetura

## Recommendations for Android architecture

https://developer.android.com/topic/architecture/recommendations

## Views recommendations

https://developer.android.com/topic/architecture/views/recommendations-views

Usado para:

- recomendações adaptáveis ao app;
- UI layer;
- UDF;
- testability;
- responsibilities.

Verificado em: 2026-08-22.

---

# 582. Fontes — qualidade Android

## Core app quality guidelines

https://developer.android.com/docs/quality-guidelines/core-app-quality

Usado para:

- baseline de qualidade;
- stability;
- UX;
- compatibility;
- adaptive considerations.

Verificado em: 2026-08-22.

---

# 583. Fontes — qualidade adaptativa

## Tier 2 — Adaptive optimized

https://developer.android.com/docs/quality-guidelines/adaptive-app-quality/tier-2

Usado para:

- responsive/adaptive layouts;
- múltiplos tamanhos;
- multi-window;
- inputs;
- quality tiers.

Verificado em: 2026-08-22.

---

# 584. Fontes — experiência de jogos/apps

## What a great user experience looks like

https://developer.android.com/quality/user-experience

Usado para:

- qualidade de jogos;
- marca;
- UX;
- monetização não intrusiva;
- accessibility/localization.

Verificado em: 2026-08-22.

---

# 585. Fontes — estratégia de testes

## Testing strategies

https://developer.android.com/training/testing/fundamentals/strategies

Usado para:

- testes menores mais frequentes;
- pre-merge;
- release candidate;
- feedback cedo;
- test layers.

Verificado em: 2026-08-22.

---

# 586. Fontes — Android Games

## Android Games Developer Center

https://developer.android.com/games

Usado para mapear ferramentas específicas de jogos e evitar assumir que todas se aplicam ao Gambitol.

Verificado em: 2026-08-22.

---

# 587. Fontes — Level Up

## Google Play Games Level Up guidelines

https://developer.android.com/games/guidelines

Usado para:

- benchmarks de estabilidade/performance;
- ressalva de jogos com HWUI/Composer;
- jogos que só geram frames em interação.

Verificado em: 2026-08-22.

---

# 588. Fontes — Play Games Services

## Get started with Google Play Games Services

https://developer.android.com/games/pgs/start

Usado para mapear features futuras:

- authentication;
- achievements;
- leaderboards;
- events;
- friends;
- saved games.

Não é compromisso de integração.

Verificado em: 2026-08-22.

---

# 589. Fontes — target API

## Meet Google Play's target API level requirement

https://developer.android.com/google/play/requirements/target-sdk

## Play Console Help — Target API requirements

https://support.google.com/googleplay/android-developer/answer/11926878

Fato temporal registrado:

> a partir de 2026-08-31, novos apps e updates comuns de Android precisam segmentar API 36+.

Verificado em: 2026-08-22.

---

# 590. Fontes — testing requirement de contas pessoais

## Play Console Help

https://support.google.com/googleplay/android-developer/answer/14151465?hl=pt-BR

Fato temporal registrado:

> contas pessoais criadas após 2023-11-13 possuem requisito atual de closed test com 12 testers contínuos por 14 dias antes de solicitar produção.

Esse fato é condicional à conta e deve ser reverificado perto da publicação.

Verificado em: 2026-08-22.

---

# 591. Regra para fatos temporais

## DECIDIDO

Requisitos do Google Play possuem validade datada.

Portanto:

- target API;
- testing requirements;
- políticas;
- Play Games programs;

sempre precisam de nova verificação na fase de release.

---

# 592. Fontes não decidem o escopo

Android oferecer uma API não significa:

```text
Gambitol deve usar
```

Roadmap decide com base em visão/necessidade.

---

# 593. O roadmap não deve ser “best practices bingo”

Nada entra só para podermos listar tecnologia no README.

---

# 594. Critério de tecnologia

Tecnologia entra quando resolve:

- requisito;
- risco;
- qualidade;
- aprendizado relevante.

---

# 595. Resultado esperado após este documento

Quando desenvolvimento for retomado, não devemos perguntar:

> “o que vamos programar agora?”

A resposta macro será:

```text
terminar governança
↓
validar build real
```

Depois:

```text
criar boundary do motor
```

e seguir gates.

---

# 596. Próxima ação técnica futura

## NÃO EXECUTAR NESTE DOCUMENTO

Após terminar a documentação completa:

1. abrir projeto real;
2. verificar Git status;
3. ler Gradle;
4. resolver JDK;
5. build.

Nenhum desses passos está sendo executado agora.

---

# 597. Próxima decisão estrutural futura

Antes do módulo engine:

aprovar nome do módulo/package propostos no documento 04.

---

# 598. Próxima decisão após roadmap

Documento:

```text
11_DECISOES_TECNICAS.md
```

formalizará como decisões serão registradas ao longo do projeto.

---

# 599. Frase norteadora

> **O Gambitol será construído na ordem em que a confiança precisa ser conquistada: primeiro no ambiente, depois no motor, depois na integração, depois na experiência e, por último, na distribuição e nas expansões.**

---

# 600. Próximo documento

Após aprovação:

`11_DECISOES_TECNICAS.md`

Ele deverá estabelecer:

- formato de registro;
- ID;
- status;
- contexto;
- alternativas;
- decisão;
- consequências;
- supersession;
- histórico;
- como registrar decisões já tomadas;
- quais decisões merecem ADR;
- quais não merecem;
- como evitar reabrir o mesmo debate sem evidência nova.

O documento 10 define:

> **o que entra, o que fica fora e em que ordem construímos.**

O documento 11 definirá:

> **como preservamos o motivo das decisões tomadas durante esse caminho.**
