# 08 — TESTES E QUALIDADE DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `08_TESTES_E_QUALIDADE.md`  
> **Versão:** 1.1  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-23  
> **Responsabilidade:** definir a estratégia de testes e os critérios de qualidade do Gambitol, desde o motor Java até a interface Android e a preparação de uma release  
> **Fonte normativa para:** testes JVM, testes locais Android, testes instrumentados, testes de UI, regressão, Perft, cobertura, mutation testing, Lint, estabilidade, flakiness, matriz de dispositivos, quality gates e definição de pronto  
> **Não cobre em detalhe:** regras de xadrez, implementação das classes, workflow Git, identidade visual, roadmap completo, assinatura da aplicação ou publicação operacional na Play Store  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`, `06_PADROES_JAVA_E_ANDROID.md`, `07_GIT_WORKFLOW.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Propósito

No Gambitol, “funcionou no meu celular” não será considerado evidência suficiente de qualidade.

Também não vamos cair no extremo oposto e construir uma usina nuclear de testes para validar um botão que muda de tela.

A estratégia precisa responder:

- o que precisa ser testado;
- em qual nível;
- onde o teste deve rodar;
- com que frequência;
- qual risco ele cobre;
- como evitar testes frágeis;
- como detectar regressões;
- como validar regras complexas do xadrez;
- como medir cobertura sem transformar porcentagem em religião;
- como testar diferentes dispositivos;
- como decidir se uma mudança está pronta;
- como decidir se uma release está pronta.

A regra central é:

> **teste deve produzir confiança útil, não apenas números bonitos.**

---

# 2. Autoridades e referências principais

A estratégia foi construída principalmente a partir de:

- documentação oficial Android sobre fundamentos de testes;
- estratégia de testes Android;
- Espresso;
- AndroidJUnitRunner;
- Build-Managed Devices;
- Firebase Test Lab;
- Android Lint;
- cobertura de código do Android Gradle Plugin;
- documentação oficial do Gradle sobre testes;
- documentação oficial do JUnit;
- JaCoCo;
- PIT Mutation Testing;
- Chess Programming Wiki para Perft.

Fontes completas aparecem no final.

---

# 3. Princípio 1 — testar faz parte da implementação

## DECIDIDO

Uma feature não está “pronta para testar”.

Ela é construída **com testes ao longo do desenvolvimento**.

Fluxo desejado:

```text
definir comportamento
↓
implementar pequeno incremento
↓
testar
↓
corrigir
↓
refatorar
↓
testar novamente
```

Não:

```text
implementar o jogo inteiro
↓
descobrir no fim se funciona
```

---

# 4. Princípio 2 — testar no nível mais barato que preserve confiança

A documentação Android diferencia:

- testes locais;
- testes instrumentados;
- testes pequenos;
- médios;
- grandes.

Quanto maior o teste:

- mais componentes envolvidos;
- maior fidelidade;
- maior custo;
- maior tempo;
- maior probabilidade de flakiness.

Logo:

> se uma regra pode ser provada na JVM em milissegundos, não precisamos abrir um emulador para prová-la.

---

# 5. Princípio 3 — motor de xadrez deve ser testável sem Android

## DECIDIDO E IMPLEMENTADO

O motor é o maior ativo lógico do Gambitol.

A fronteira já foi materializada no módulo Java puro:

```text
:chess-engine
```

e seus testes de domínio executam na JVM:

```text
motor → testes JVM
```

sem:

- Activity;
- Context;
- View;
- emulator;
- device;
- AndroidJUnitRunner.

O módulo foi validado sem dependências `android.*` ou `androidx.*`.

Isso deixou de ser apenas um critério arquitetural e passou a ser uma propriedade verificável do build.

---

# 6. Princípio 4 — UI deve ser testada como UI

Não tentar validar regra do cavalo clicando 50 vezes na tela.

Teste da UI deve verificar principalmente:

- interação;
- renderização;
- fluxo;
- estado apresentado;
- lifecycle;
- acessibilidade;
- integração com Android.

A regra do cavalo pertence aos testes do motor.

---

# 7. Princípio 5 — teste manual continua existindo

A documentação Android ressalta que teste manual pode explorar:

- dispositivos;
- idiomas;
- fluxos;
- erros.

Mas escala mal e deixa regressões escaparem.

No Gambitol:

## DECIDIDO

Teste manual complementa automação.

Não substitui testes automatizados de comportamento crítico.

---

# 8. Pirâmide de testes do Gambitol

## PROPOSTO

```text
                    ┌────────────────────┐
                    │ RELEASE / DEVICE   │
                    │ poucos e caros     │
                    └─────────┬──────────┘
                              │
                  ┌───────────▼───────────┐
                  │ UI / INSTRUMENTADOS   │
                  │ poucos e focados      │
                  └───────────┬───────────┘
                              │
              ┌───────────────▼───────────────┐
              │ INTEGRAÇÃO / COMPONENTE      │
              │ quantidade moderada          │
              └───────────────┬───────────────┘
                              │
          ┌───────────────────▼───────────────────┐
          │ TESTES JVM DO MOTOR E LÓGICA        │
          │ muitos, rápidos e determinísticos   │
          └──────────────────────────────────────┘
```

---

# 9. A pirâmide não é proporção matemática

Não existe regra:

```text
70% unit
20% integration
10% UI
```

obrigatória.

A quantidade depende de:

- risco;
- arquitetura;
- feature;
- custo.

A documentação Android usa a pirâmide como orientação de distribuição, não como fórmula universal.

---

# 10. Classificação por ambiente

## Local / host-side

Executa na máquina de desenvolvimento ou CI.

Exemplos:

- motor;
- utilitários Java;
- ViewModel desacoplado;
- componentes sem framework.

## Instrumented

Executa em:

- dispositivo físico;
- emulador.

Pode usar Android framework real.

---

# 11. Classificação por escopo

### Small / unit

Uma pequena unidade.

### Component / medium

Várias classes trabalhando juntas.

### Feature

Uma feature funcional.

### Application / end-to-end

Aplicativo ou fluxo grande.

### Release candidate

Validação final em configuração próxima da produção.

Essa classificação é compatível com a estratégia oficial Android.

---

# 12. Estratégia específica do Gambitol

## Motor

Muitos testes pequenos e de componente.

## Integração Android ↔ engine

Quantidade moderada.

## UI

Poucos testes de alto valor.

## Release

Matriz de dispositivos + smoke tests + validação manual.

---

# 13. Não testar implementação interna sem motivo

Teste deve preferir:

> comportamento observável.

Evitar teste que quebra simplesmente porque um método privado foi renomeado.

---

# 14. Exemplo ruim

```text
verificar que validateKnightStep() foi chamado exatamente duas vezes
```

se o que importa é:

```text
movimento legal do cavalo foi aceito
```

---

# 15. Exemplo bom

```text
DADO cavalo em b1
QUANDO mover para c3
ENTÃO movimento é legal
```

---

# 16. Teste como especificação executável

Um bom teste explica a regra.

Exemplo conceitual:

```text
kingCannotMoveIntoAttackedSquare
```

é quase uma frase da especificação.

---

# 17. Testes e documento 05

## DECIDIDO

`05_REGRAS_DO_MOTOR_DE_XADREZ.md` é a fonte normativa do comportamento.

Testes transformam essa regra em verificação executável.

Se teste e documento divergem:

1. investigar;
2. confirmar FIDE quando necessário;
3. corrigir um deles;
4. não “fazer o teste passar” cegamente.

---

# 18. Framework de teste JVM

## DECIDIDO E IMPLEMENTADO PARA O ENGINE

O módulo Java puro `:chess-engine` utiliza:

```text
JUnit Jupiter 6.1.3
```

A configuração real do módulo usa o version catalog e a JUnit Platform:

```kotlin
dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
```

A versão foi confirmada pela resolução de dependências do Gradle antes da integração.

Essa decisão vale para os testes JVM do `:chess-engine`. Ela não migra automaticamente os testes locais ou instrumentados do módulo Android `:app`.

---

# 19. JUnit Jupiter

## ADOTADO NO `:chess-engine`

JUnit Jupiter oferece recursos como:

- `@Test`;
- lifecycle;
- parameterized tests;
- nested tests;
- repeated tests;
- extensões.

No estado atual do Gambitol, ele já é o framework de testes JVM do motor.

A primeira evidência executável é `SideTest`, cobrindo:

```text
WHITE.opposite() == BLACK
BLACK.opposite() == WHITE
```

Os dois testes foram executados com sucesso antes do merge e a suíte do engine voltou a passar na validação pós-merge da `main`.

---

# 20. AndroidJUnitRunner

A documentação atual do Android define `AndroidJUnitRunner` como runner de instrumentação compatível com testes JUnit 3/4 em pacotes Android.

Logo:

> framework JVM do engine e runner instrumentado do app não precisam ser a mesma coisa.

Essa distinção deve ser compreendida antes de configurar.

---

# 21. Testes do engine não dependem do runner Android

## DECIDIDO E IMPLEMENTADO

O `:chess-engine` possui estratégia própria de testes JVM com JUnit Jupiter e JUnit Platform.

O runner Android continua sendo uma preocupação do módulo `:app` para testes instrumentados.

A separação foi comprovada executando o engine sem emulador e, depois do merge, pelo comando:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

com `BUILD SUCCESSFUL`.

---

# 22. AAA

## DECIDIDO COMO MÉTODO DIDÁTICO INICIAL

Estrutura:

```text
Arrange
Act
Assert
```

Exemplo:

```java
// Arrange
...

// Act
...

// Assert
...
```

Comentários podem ser mantidos enquanto ajudam a leitura.

Depois podem ser removidos quando a estrutura estiver óbvia.

---

# 23. Given / When / Then

É outra forma de pensar:

```text
Given → estado inicial
When → ação
Then → resultado
```

Pode ser usado nos nomes/descrições.

---

# 24. Um teste deve contar uma história

O leitor precisa identificar:

- contexto;
- ação;
- expectativa.

---

# 25. Um teste não precisa ter uma única assertion

## DECIDIDO

A regra correta não é:

> “um assert por teste”.

A regra melhor é:

> um comportamento coerente por teste.

Exemplo:

aplicar uma captura pode justificar verificar:

- peça saiu da origem;
- peça adversária foi removida;
- atacante está no destino;
- turno mudou.

Essas assertions verificam um único comportamento composto.

---

# 26. Assertions demais podem esconder foco

Se um teste verifica 30 coisas não relacionadas:

quebrar.

---

# 27. Testes independentes

## DECIDIDO

Nenhum teste deve depender de outro ter rodado antes.

Ruim:

```text
test1 cria jogo
test2 move peça usando jogo do test1
```

---

# 28. Ordem de execução

Nunca depender de ordem do framework.

---

# 29. Estado novo por teste

Preferir construir um estado conhecido.

---

# 30. Setup compartilhado

Pode existir quando reduz repetição sem esconder a situação.

---

# 31. `@BeforeEach` / equivalente

Útil para setup realmente comum.

Não colocar metade da lógica do cenário nele.

---

# 32. Setup mágico

Ruim:

```text
beforeEach()
→ cria 15 peças
→ faz 9 movimentos
→ altera flags
```

e o teste parece pequeno, mas ninguém sabe a posição.

Para xadrez, estado precisa ser visível.

---

# 33. Fixtures explícitas

Posições complexas podem usar:

- builder de teste;
- FEN futuramente;
- factory de teste;
- helper específico.

Mas a representação deve permanecer legível.

---

# 34. Test fixture não é produção

Infraestrutura usada apenas em teste fica no source set de teste.

---

# 35. Nomes de testes

## PROPOSTO

Preferir nomes que descrevam comportamento.

Exemplos:

```text
knightCanMoveTwoByOne
knightCannotMoveStraight
rookCannotJumpOverPiece
kingCannotMoveIntoCheck
castlingIsRejectedAfterKingMoved
```

---

# 36. Nome não precisa repetir `test`

Classe já comunica.

Evitar:

```text
testKnightTest
```

---

# 37. Nome longo é aceitável se comunica regra

Mas se vira uma sentença de 180 caracteres:

considerar simplificar cenário.

---

# 38. Teste positivo

Verifica comportamento permitido.

---

# 39. Teste negativo

Verifica comportamento proibido.

## DECIDIDO

Regras de xadrez precisam dos dois.

---

# 40. Boundary tests

Testar limites:

- borda do tabuleiro;
- rank 1/8;
- file a/h;
- contador 99/100;
- contador 149/150;
- último lance de en passant.

---

# 41. Equivalence classes

Não é necessário testar cada casa para cada regra se grupos são equivalentes.

Mas usar testes parametrizados quando ajudam.

---

# 42. Testes parametrizados

## CANDIDATO

São especialmente úteis para:

- oito movimentos do cavalo;
- direções do rei;
- diagonais;
- casas fora do tabuleiro;
- promoções Q/R/B/N.

JUnit moderno suporta parameterized tests quando configurado.

---

# 43. Não parametrizar até ficar ilegível

Se cada caso possui semântica diferente:

testes separados são melhores.

---

# 44. Dynamic tests

## FUTURO

Podem ser úteis para gerar muitos casos.

Não necessários inicialmente.

---

# 45. Testes de propriedade

## FUTURO / RECOMENDADO DEPOIS DA BASE

Exemplos:

- movimento legal nunca deixa o próprio rei em xeque;
- make/unmake restaura estado;
- nenhuma peça termina fora do tabuleiro;
- espelhamento branco/preto preserva regras equivalentes.

---

# 46. Property-based testing framework

## NÃO ESCOLHIDO

Não adicionar biblioteca antes de os testes normais estarem maduros.

---

# 47. Fuzz testing

## FUTURO

Sequências aleatórias de jogadas legais podem validar invariantes.

Útil para:

- corrupção de estado;
- undo;
- histórico;
- direitos de roque.

---

# 48. Aleatoriedade determinística

Se teste usa random:

seed fixa ou registrada.

Teste que falha uma vez por semana sem reproduzir é um pequeno demônio administrativo.

---

# 49. Testes determinísticos

## DECIDIDO

Mesmo input deve produzir mesmo resultado.

Dependências variáveis devem ser controladas:

- relógio;
- random;
- storage;
- rede futura.

---

# 50. Teste e tempo

Não depender do relógio real quando regra pode receber fonte de tempo.

---

# 51. `Thread.sleep` em testes

## PROIBIDO COMO ESTRATÉGIA DE SINCRONIZAÇÃO

A documentação Android alerta que sleeps arbitrários tornam testes lentos e flaky.

Se assíncrono existir:

usar mecanismo de sincronização adequado.

---

# 52. Espresso e sincronização

Espresso espera por condições conhecidas de idle antes de ações/assertions.

Isso melhora estabilidade.

Se houver trabalho assíncrono que Espresso não conhece:

usar mecanismo apropriado, como Idling Resource quando necessário.

---

# 53. Flaky test

Um teste flaky alterna entre:

- passar;
- falhar;

sem mudança real no comportamento.

---

# 54. Flaky test é bug do sistema de teste

## DECIDIDO

Não ignorar.

Uma suite que ninguém confia deixa de proteger o projeto.

---

# 55. Não resolver flaky com retry infinito

Retry pode ajudar a diagnosticar infraestrutura.

Não deve mascarar causa.

---

# 56. Causas comuns de flakiness

- tempo real;
- sleep;
- ordem de testes;
- estado global;
- animação;
- operações async;
- dispositivo sujo;
- locale;
- rede;
- dependência externa;
- race condition.

---

# 57. Quarentena

## ÚLTIMO RECURSO

Se teste bloqueia pipeline e ainda está sendo investigado:

pode ser isolado temporariamente.

Deve possuir:

- motivo;
- tarefa de correção;
- prazo.

Não criar cemitério de testes ignorados.

---

# 58. `@Disabled` / ignore

Só com justificativa.

---

# 59. Testes que nunca rodam são decoração

Não contam como qualidade.

---

# 60. Test doubles

Categorias conceituais:

- fake;
- stub;
- mock;
- spy.

Não precisamos usar todos.

---

# 61. Fake

Implementação funcional simplificada.

Exemplo futuro:

```text
FakeGameRepository
```

em memória.

A documentação Android favorece dependências substituíveis para arquitetura testável.

---

# 62. Stub

Retorna respostas predeterminadas.

---

# 63. Mock

Normalmente verifica interações.

---

# 64. Preferência do Gambitol

## PROPOSTO

Preferir:

- objetos reais simples;
- fakes;

antes de mocks pesados.

Mocks são úteis quando interação é parte do contrato.

---

# 65. Não mockar value object

Criar uma Position real é melhor do que mockar Position.

---

# 66. Não mockar List

Use uma lista real.

---

# 67. Mock framework

## NÃO ESCOLHIDO

Mockito ou outro só entra se necessidade real aparecer.

---

# 68. Teste sem framework de mocking é uma virtude possível

O engine deve ser suficientemente simples para muitos testes com objetos reais.

---

# 69. Architecture testability

A documentação Android ressalta que arquitetura testável tende a ser:

- mais legível;
- mais desacoplada;
- mais reutilizável.

Se uma classe é impossível de testar sem Activity:

isso pode ser smell arquitetural.

---

# 70. Regra de feedback rápido

## DECIDIDO

Durante desenvolvimento do engine:

o comando de teste deve retornar rápido o suficiente para ser executado frequentemente.

---

# 71. Comando de teste do engine

Quando o módulo e framework forem aprovados:

algo equivalente a:

```bash
./gradlew :<engine>:test
```

será o principal feedback.

Nome real dependerá do módulo aprovado.

---

# 72. `./gradlew test`

Pode executar suites locais de múltiplos módulos dependendo da configuração.

Não assumir sem olhar tasks.

---

# 73. Gradle Test task

A documentação Gradle permite:

- filtering;
- reports;
- fail-fast;
- paralelismo;
- JVM configuration.

Não ajustar knobs antes de necessidade.

---

# 74. Fail fast local

## PENDENTE

`failFast` pode acelerar feedback em suites grandes.

No início, rodar suite completa normalmente é mais útil para saber todos os erros.

---

# 75. Fail fast no CI

Também depende de objetivo.

Um CI pode preferir:

- descobrir todas falhas em uma rodada;

em vez de parar na primeira.

Não ativar por reflexo.

---

# 76. Filtering

Durante desenvolvimento:

rodar teste/classe específica pode acelerar.

Antes de commit:

rodar suite relevante completa.

---

# 77. Test report

Gradle gera relatórios.

Quando teste falha:

consultar:

- nome;
- expected;
- actual;
- stack trace;
- report.

---

# 78. Não corrigir teste pela última linha apenas

Ler primeira evidência útil.

---

# 79. Testes do motor — posição

Cobrir:

- posição válida;
- limites;
- igualdade;
- hashCode;
- representação textual;
- conversão notação se existir.

---

# 80. Testes do motor — board

Cobrir:

- posição inicial;
- ocupação;
- consulta;
- limites;
- movimentação atômica;
- capturas.

---

# 81. Testes do motor — turno

- brancas começam;
- movimento legal alterna;
- movimento ilegal não alterna;
- game over bloqueia nova ação.

---

# 82. Testes do bispo

Conforme documento 05:

- quatro diagonais;
- bloqueio;
- captura;
- movimento não diagonal;
- próprio rei.

---

# 83. Testes da torre

- rank;
- file;
- bloqueio;
- captura;
- diagonal;
- own king safety.

---

# 84. Testes da dama

- diagonal;
- horizontal;
- vertical;
- bloqueio;
- padrão inválido.

---

# 85. Testes do cavalo

- oito destinos do centro;
- bordas;
- captura;
- peça própria;
- pula peças.

---

# 86. Testes do rei

- oito direções;
- casa atacada;
- captura protegida;
- rei adjacente;
- sair do check;
- permanecer em check.

---

# 87. Testes do peão

- avanço;
- duplo;
- bloqueio;
- captura;
- direção;
- en passant;
- promoção.

---

# 88. Testes de attack map

## CRÍTICO

Cobrir:

- peões atacam diagonal, não frente;
- king adjacency;
- knight;
- sliding;
- first blocker;
- pinned piece ainda ataca para king-safety.

---

# 89. Testes de legalidade global

- peça cravada;
- discovered check;
- double check;
- capture attacker;
- block ray;
- king move.

---

# 90. Testes de roque

Obrigatórios:

- kingside legal;
- queenside legal;
- king moved;
- rook moved;
- moved-and-returned;
- path blocked;
- king in check;
- transit square attacked;
- destination attacked;
- rook attacked mas legal;
- b-file attacked no long castle;
- rook absent.

---

# 91. Testes de en passant

Obrigatórios:

- available immediately;
- expires;
- one-square move;
- non-adjacent double;
- correct capture;
- removes correct pawn;
- king exposure;
- resolves check.

---

# 92. Testes de promoção

- Q;
- R;
- B;
- N;
- capture;
- white;
- black;
- invalid king;
- invalid pawn;
- immediate check;
- immediate mate.

---

# 93. Testes de checkmate

Combinar:

```text
isInCheck = true
legalMoves = 0
```

---

# 94. Testes de stalemate

```text
isInCheck = false
legalMoves = 0
```

---

# 95. Testes de dead position

Começar por casos seguros.

Não criar heurística e depois escrever teste só para confirmar a própria heurística.

---

# 96. Testes de repetição

Cobrir identidade da posição:

- piece placement;
- side to move;
- castling rights;
- en passant possibility.

Não incluir indevidamente:

- halfmove clock;
- fullmove number;

na equivalência de repetição.

---

# 97. Threefold

Testar:

- 1ª ocorrência;
- 2ª;
- 3ª claimable;
- partida não termina automaticamente apenas pela 3ª.

---

# 98. Fivefold

5ª ocorrência:

terminal automático.

---

# 99. 50-move

- 99 plies;
- 100;
- pawn reset;
- capture reset.

---

# 100. 75-move

- 149;
- 150;
- reset;
- checkmate precedence.

---

# 101. Resignação

Quando feature existir:

- vitória normal;
- impossibilidade de mate → draw conforme regra documentada.

---

# 102. Timeout

Quando cronômetro existir:

- perda normal;
- impossibilidade de mate pelo adversário → draw.

---

# 103. Testes de restart

Depois de estado complexo:

- board;
- turn;
- castling rights;
- en passant;
- counters;
- repetition;
- status.

---

# 104. Regression test

## DECIDIDO

Todo bug relevante corrigido deve, quando possível, produzir um teste que falhava antes da correção.

Fluxo:

```text
reproduzir
↓
teste falha
↓
corrigir
↓
teste passa
↓
manter teste
```

---

# 105. Por que regressão importa no xadrez

Regras interagem.

Corrigir:

```text
en passant
```

pode quebrar:

```text
check detection
```

Um teste preserva aprendizado.

---

# 106. Naming de regression tests

Nome deve descrever comportamento, não ID do bug apenas.

Pode incluir issue no comentário/metadata se existir.

---

# 107. Perft

## DECIDIDO COMO FERRAMENTA DE VALIDAÇÃO DO ENGINE

Perft percorre árvore de movimentos legais e conta posições/folhas em determinada profundidade.

Ele é uma técnica clássica para validar:

- move generation;
- make/unmake;
- castling;
- en passant;
- promotion;
- check legality.

---

# 108. Perft não é teste de força da IA

Não mede:

- avaliação;
- estratégia;
- qualidade de jogada.

---

# 109. Perft deve usar posições de referência externas

Não calcular valor esperado com o próprio engine.

Isso seria:

```text
engine confirma engine
```

e prova muito pouco.

---

# 110. Vetores Perft

## PENDENTE DE IMPLEMENTAÇÃO

Quando Perft for criado:

usar posições e contagens de fontes reconhecidas.

Registrar:

- FEN;
- profundidade;
- nodes esperados;
- fonte.

---

# 111. Perft da posição inicial

Será um dos primeiros vetores.

Não congelar números neste documento sem o teste implementado e fonte registrada junto.

---

# 112. Perft positions especiais

Precisamos incluir posições que estressem:

- castling;
- en passant;
- promotions;
- checks;
- pins.

---

# 113. Perft divide

## RECOMENDADO

Quando contagem divergir:

contar por movimento raiz.

Isso localiza o ramo problemático.

---

# 114. Perft e performance

Primeiro usar para correção.

Benchmark depois.

---

# 115. Perft em cada commit?

## NÃO NECESSARIAMENTE

Profundidade alta pode ser cara.

Estratégia:

- depths pequenas em teste frequente;
- depths maiores em gate mais lento/nightly/release quando necessário.

---

# 116. Perft determinístico

Deve sempre produzir mesma contagem.

---

# 117. Cross-validation

## FUTURO / FORTEMENTE RECOMENDADO

Comparar o Gambitol com implementação independente confiável.

Exemplos:

- Stockfish;
- biblioteca madura;
- datasets Perft.

---

# 118. Cross-validation não entra no APK

Pode ser ferramenta de desenvolvimento.

---

# 119. Testes metamórficos

## FUTURO

Exemplo:

espelhar posição e trocar cores deveria preservar número equivalente de movimentos em muitos cenários simétricos.

---

# 120. Make/unmake

Se engine usar essa estratégia:

## OBRIGATÓRIO TESTAR

```text
state before
↓
make move
↓
unmake
↓
state equals before
```

Incluindo:

- castling;
- en passant;
- promotion;
- captures.

---

# 121. Snapshot equality

Será muito útil para esses testes.

---

# 122. Hash consistency

Se Zobrist hashing entrar:

testar:

- make/unmake;
- equivalent position;
- castling rights;
- en passant;
- side to move.

---

# 123. Hash collision

Não é prático provar ausência absoluta por testes.

A arquitetura deve considerar estratégia segura se hash for usado para regra normativa.

---

# 124. Testes de parser FEN

Quando entrar:

- valid;
- invalid;
- round trip;
- castling;
- EP;
- counters.

---

# 125. Testes de SAN/PGN

Quando entrar:

- ambiguity;
- captures;
- check;
- mate;
- castling;
- promotion;
- disambiguation.

---

# 126. Round-trip tests

FEN:

```text
parse(format(position)) ≈ position
```

conforme campos representados.

---

# 127. Testes de Android integration

O app precisa provar que:

- recebe estado do engine;
- renderiza;
- encaminha ações;
- sobrevive lifecycle relevante.

---

# 128. ViewModel tests

Se ViewModel for usado:

testar localmente quando possível.

Casos:

- seleção;
- comando de movimento;
- estado de UI;
- restart;
- promoção pendente;
- mensagens/efeitos semânticos.

---

# 129. ViewModel não deve exigir Activity em teste

Se exige:

revisar arquitetura.

---

# 130. LiveData/state observable

Se adotado:

testes precisam controlar execução síncrona de forma apropriada.

Não criar utilitário antes de escolher tecnologia.

---

# 131. Teste de mapper de UI

Se houver mapping:

```text
PieceType.KNIGHT → drawable esperado
```

pode ser testado no nível adequado.

Se depende de resources Android:

pode precisar teste Android.

---

# 132. Testes de custom View

Se tabuleiro for Canvas:

verificar:

- cálculo de casa;
- dimensões;
- touch mapping;
- seleção;
- invalidação.

Separar matemática pura para teste JVM quando possível.

---

# 133. Touch mapping

Idealmente extrair função:

```text
pixel → square
```

que possa ser testada sem MotionEvent quando possível.

---

# 134. UI tests

## PROPOSTO

Usar Espresso para Views quando automação instrumentada for necessária.

A documentação oficial o descreve como framework para testes concisos e confiáveis da UI Android.

---

# 135. Espresso test scope

UI tests devem verificar:

- ações de usuário;
- estado visível;
- navegação;
- lifecycle;
- integração.

---

# 136. Não verificar private fields em Espresso

Teste como usuário.

---

# 137. IDs de View estáveis

Facilitam Espresso.

---

# 138. Match por texto

Pode quebrar com localização.

Quando intenção é elemento específico:

ID/semantics adequado pode ser melhor.

---

# 139. Match por posição na hierarquia

## EVITAR

Teste frágil:

```text
terceiro filho do segundo LinearLayout
```

Quebra com refactor visual.

---

# 140. Teste de fluxo crítico

Exemplos:

- iniciar partida;
- selecionar peça;
- mover legalmente;
- tentar movimento ilegal;
- reiniciar;
- promoção;
- final de partida.

---

# 141. Não duplicar todas regras na UI

Um ou poucos movimentos podem provar integração.

Engine já cobre regras.

---

# 142. Activity recreation

## TESTE IMPORTANTE

Se estado deve sobreviver a configuração:

- iniciar partida;
- alterar estado;
- recriar Activity;
- confirmar estado.

---

# 143. Process death

Mais difícil.

Quando persistência/restore entrar:

testar mecanismo correspondente.

Não assumir que `ActivityScenario.recreate()` simula process death completo.

---

# 144. Background/foreground

Quando timer entrar:

testar comportamento de lifecycle definido no produto.

---

# 145. Orientação

O layout inicial é portrait, mas mudanças de configuração ainda podem ocorrer conforme manifest/config.

Testar política real.

---

# 146. UI screenshot tests

## FUTURO / CANDIDATO

Podem detectar regressões visuais.

Mas:

- precisam baseline;
- podem variar por rendering;
- exigem manutenção.

Não adotar antes do layout estabilizar.

---

# 147. Golden tests

Mesmo conceito.

Úteis para tabuleiro/tema quando visual estiver estável.

---

# 148. Screenshot test não substitui comportamento

Imagem correta não prova que botão funciona.

---

# 149. Accessibility tests

## DECIDIDO COMO PARTE DA QUALIDADE

Acessibilidade deve ser verificada por:

- Lint;
- testes de UI quando útil;
- inspeção manual com ferramentas/serviços.

---

# 150. Espresso accessibility

O ecossistema Espresso possui suporte de acessibilidade.

Avaliar quando a UI estiver implementada.

---

# 151. Contraste

Pertence também à revisão visual.

Não depender apenas de screenshot automático.

---

# 152. TalkBack

## FUTURO / MANUAL IMPORTANTE

Antes de release pública:

testar fluxos principais com TalkBack se a UI tiver elementos interativos relevantes.

---

# 153. Keyboard/focus

Se aplicável a tablets/periféricos.

---

# 154. Touch target

Lint e inspeção manual.

---

# 155. Localização

Quando múltiplos idiomas existirem:

testar:

- textos longos;
- truncamento;
- layout;
- plurals.

---

# 156. RTL

Se idioma RTL entrar:

testar direção.

Não necessário enquanto não suportado oficialmente.

---

# 157. Fonte maior

Mesmo em jogo, testar font scaling nos painéis/textos.

---

# 158. Screen sizes

A documentação Android recomenda testar UI em diferentes tamanhos sem duplicar toda a suite.

## PROPOSTO

Rodar:

- suite principal em phone;
- subset responsivo em tamanhos diferentes.

---

# 159. Device matrix inicial

## PROPOSTO QUANDO UI ESTIVER MADURA

No mínimo:

- API mínima suportada;
- API alvo/recente;
- phone compacto;
- phone maior;
- ao menos um dispositivo físico antes de release.

Tablet/foldable entram conforme escopo do produto.

---

# 160. API 24

O projeto foi criado com minSdk 24.

## DECIDIDO COMO META DE TESTE

Quando o app usar APIs Android relevantes:

validar pelo menos uma configuração API 24 antes de considerar compatibilidade confirmada.

---

# 161. API recente

Também testar em versão Android recente/target.

---

# 162. Um único emulador não prova compatibilidade

---

# 163. Dispositivo físico

## DECIDIDO PARA RELEASE

Antes de release real:

executar smoke test em aparelho físico.

Firebase/Test Lab complementa.

---

# 164. Build-Managed Devices

A documentação Android atual permite configurar devices gerenciados no Gradle para testes instrumentados de forma reproduzível.

## FUTURO / CANDIDATO

Útil quando CI entrar.

---

# 165. Limite de API dos build-managed devices

A documentação atual informa suporte da feature a API 27+.

Logo:

para validar minSdk 24, ainda precisaremos de outra estratégia:

- emulator manual;
- device;
- Test Lab;
- outro mecanismo suportado.

---

# 166. Firebase Test Lab

## FUTURO / RECOMENDADO PRÉ-RELEASE

Permite matriz com:

- modelos;
- versões Android;
- orientação;
- locale;
- devices físicos/virtuais.

---

# 167. Test Lab não substitui testes locais

É camada mais cara e de alta fidelidade.

Usar de maneira seletiva.

---

# 168. Test matrix

Conceito oficial:

```text
devices × test executions
```

Se uma execução falha, a matriz pode ser marcada como falha.

---

# 169. Test Lab physical devices

Valiosos para:

- diferenças de fabricante;
- hardware;
- rendering;
- comportamento real.

---

# 170. Emulator local

Valioso para:

- feedback rápido;
- APIs;
- tamanhos;
- repetibilidade.

---

# 171. Pre-launch report da Play Console

## FUTURO / OBRIGATÓRIO REVISAR ANTES DE PRODUÇÃO

O relatório de pré-lançamento pode apontar problemas de:

- estabilidade;
- performance;
- acessibilidade.

Ele complementa nossos testes.

---

# 172. Pre-launch report não substitui suite

Automação genérica não conhece todas regras do xadrez.

---

# 173. Robo test

Firebase Test Lab pode executar exploração automática sem teste escrito.

Pode encontrar:

- crash;
- fluxo inesperado.

Não prova regras.

---

# 174. Game Loop test

Firebase possui suporte a game loop em cenários próprios.

## FUTURO / AVALIAR

Pode ser relevante para jogo Android, mas não é necessário no MVP.

---

# 175. Robolectric

A documentação Android atual descreve Robolectric como ambiente simulado Android na JVM.

## PROPOSTO COMO ÚLTIMO RECURSO PARA UNIT TESTS

Se arquitetura puder remover dependência Android:

preferir teste puro.

---

# 176. Quando Robolectric faz sentido

- código legado;
- API Android difícil de separar;
- certos testes de UI locais;
- lifecycle/behavior com fidelidade suficiente.

---

# 177. Quando não usar Robolectric

Para regra do motor.

Nunca.

---

# 178. Android framework test

Se comportamento depende realmente do framework:

instrumented test pode ser mais fiel.

---

# 179. Test sizes annotations

AndroidJUnitRunner suporta filtros por tamanho/annotation.

## FUTURO

Podem ajudar CI:

- small;
- medium;
- large.

Não anotar tudo antes de existir estratégia de execução.

---

# 180. Test filtering

Usar para ciclos rápidos.

Mas gate final deve executar conjunto completo relevante.

---

# 181. Cobertura de código

## DECIDIDO COMO MÉTRICA AUXILIAR

Coverage responde:

> quais partes foram executadas pelos testes?

Não responde:

> os testes são bons?

---

# 182. Sem meta arbitrária de 100%

## DECIDIDO

100% line coverage pode coexistir com testes inúteis.

Não perseguir número por vaidade.

---

# 183. Sem meta inicial de 80%

Também não escolher 80 só porque a humanidade decidiu repetir esse número em apresentações.

---

# 184. Coverage orientada por risco

Prioridade alta:

- movimento;
- legalidade;
- check;
- mate;
- castling;
- en passant;
- promotion;
- repetition;
- counters.

---

# 185. Código trivial

Getter simples não precisa ser testado só para aumentar porcentagem, salvo se contrato tiver comportamento.

---

# 186. Line coverage

Indica linhas executadas.

---

# 187. Branch coverage

Mais interessante para regras com condicionais.

Exemplo:

```text
legal / illegal
blocked / unblocked
check / safe
```

---

# 188. JaCoCo counters

A documentação JaCoCo define métricas como:

- instructions;
- branches;
- lines;
- methods;
- classes;
- complexity.

---

# 189. Android Gradle Plugin coverage

A documentação Android atual permite habilitar cobertura para:

- unit tests;
- instrumentation tests;

e gerar relatórios.

Também possui recursos recentes de agregação, alguns marcados experimentais.

---

# 190. Não adotar feature experimental sem necessidade

Para primeira cobertura:

relatório simples é suficiente.

---

# 191. Quality gate de coverage

## PENDENTE

Somente definir percentual depois de:

1. possuir suite madura;
2. medir baseline;
3. identificar código crítico;
4. decidir threshold útil.

---

# 192. Coverage pode cair por código não testável

Isso pode sinalizar problema arquitetural.

---

# 193. Coverage pode subir com teste ruim

Logo:

revisão ainda necessária.

---

# 194. Mutation testing

## FUTURO / FERRAMENTA AVANÇADA

Mutation testing altera o bytecode/código de pequenas maneiras.

Exemplo:

```text
>=
```

vira:

```text
>
```

Se testes não falham:

o mutante “sobreviveu”.

Isso revela teste fraco.

---

# 195. PIT

PIT é ferramenta de mutation testing para Java/JVM.

Pode ser excelente para regras do engine.

---

# 196. Por que mutation testing combina com xadrez

Regras possuem muitos boundaries.

Exemplo:

```text
rowDiff == 2
```

alterar para:

```text
rowDiff != 2
```

deveria ser detectado rapidamente por testes bons.

---

# 197. Mutation testing não é gate inicial

É mais lento e mais complexo.

Entrar depois de:

- suite estável;
- engine funcional.

---

# 198. Mutation score

Também não deve virar número mágico.

Mutantes equivalentes podem existir.

Analisar sobreviventes importantes.

---

# 199. Mutation testing em UI

Baixo valor relativo inicialmente.

Priorizar engine.

---

# 200. Static analysis

## DECIDIDO

Qualidade não é só teste dinâmico.

Usaremos:

- compiler;
- Android Lint;
- warnings;
- revisão.

---

# 201. Android Lint

Pode detectar:

- APIs incorretas;
- hardcoded strings;
- accessibility;
- performance;
- manifest;
- recursos;
- compatibilidade.

---

# 202. Lint errors

## GATE

Nenhum Lint error conhecido deve entrar em release sem decisão explícita/documentada.

---

# 203. Lint warnings

Revisar.

Não necessariamente todos bloqueiam.

---

# 204. Suppressions

Precisam:

- escopo mínimo;
- justificativa;
- revisão.

---

# 205. Baseline de Lint

Não criar em projeto novo apenas para zerar painel.

---

# 206. Compiler warnings

Tratar cedo.

---

# 207. Deprecated APIs

Não ignorar.

---

# 208. Gradle warnings

Também fazem parte da saúde do projeto.

---

# 209. Security warnings

Alta prioridade.

---

# 210. Manual code review

## DECIDIDO

Antes do commit relevante:

- diff;
- testes;
- nomes;
- responsabilidades;
- erro handling;
- logs;
- secrets.

---

# 211. Self-review

Mesmo projeto solo precisa.

---

# 212. Review checklist de engine

- [ ] regra corresponde ao doc 05;
- [ ] não depende de Android;
- [ ] legalidade protege rei;
- [ ] movimento inválido não muta;
- [ ] edge cases;
- [ ] teste positivo;
- [ ] teste negativo;
- [ ] estados históricos atualizados.

---

# 213. Review checklist Android

- [ ] Activity fina;
- [ ] ViewModel sem Android desnecessário;
- [ ] resources;
- [ ] lifecycle;
- [ ] accessibility;
- [ ] minSdk;
- [ ] logs;
- [ ] testabilidade.

---

# 214. Performance

## PRINCÍPIO

Não otimizar sem medir.

---

# 215. Performance do engine humano

Meta inicial:

resposta imperceptível ao usuário.

Não precisamos benchmark competitivo antes de IA.

---

# 216. Benchmark

## FUTURO

Quando IA/perft profundo entrar:

usar benchmark controlado.

Não `System.nanoTime()` espalhado como prova definitiva.

---

# 217. Android benchmark tools

Podem entrar depois para:

- startup;
- frame timing;
- hotspots.

Não necessários para primeira regra.

---

# 218. Macrobenchmark

## FUTURO

Pode medir flows/startup em app real.

---

# 219. Baseline Profiles

## FUTURO

Somente após performance de startup/runtime justificar.

---

# 220. ANR

Antes de release:

nenhuma operação longa deve bloquear main thread.

Testes/StrictMode/performance podem ajudar.

---

# 221. Crash

Smoke tests devem cobrir fluxos críticos.

---

# 222. Memory

UI de tabuleiro deve ser revisada por vazamentos quando houver lifecycle complexo.

---

# 223. Leak tooling

## FUTURO

Adicionar ferramenta somente se necessidade.

---

# 224. Qualidade visual

Testes automatizados ajudam, mas revisão manual precisa checar:

- alinhamento;
- escala;
- corte;
- contraste;
- legibilidade;
- estado selecionado.

---

# 225. Qualidade de toque

Dispositivo físico é melhor para sentir:

- target;
- responsividade;
- gestos.

---

# 226. Qualidade em device lento

Se possível, testar hardware mais modesto antes de release.

---

# 227. Offline

MVP local deve funcionar sem internet.

## TESTE MANUAL FUTURO

Ativar modo avião e jogar.

Se app local falhar sem rede:

investigar dependência desnecessária.

---

# 228. Fresh install

Antes de release:

testar instalação limpa.

---

# 229. Upgrade install

Quando existir versão anterior:

testar atualização preservando dados compatíveis.

---

# 230. Uninstall/reinstall

Entender comportamento de dados/backup quando persistência entrar.

---

# 231. Process death

Quando partida salva existir:

testar restauração real.

---

# 232. Low memory

Futuro.

---

# 233. Rotation/recreation

Mesmo app portrait pode sofrer recreation por outros fatores.

Não depender de objeto Activity vivo.

---

# 234. Theme changes

Se suportar:

testar.

---

# 235. Locale change

Se suportar múltiplos idiomas:

testar recreation/strings.

---

# 236. Dark mode

Visual principal já é escuro, mas theme behavior precisa ser validado.

---

# 237. Accessibility font scale

Testar quando UI tiver texto significativo.

---

# 238. System bars

Em APIs novas, edge-to-edge pode afetar layout.

Testar em API recente.

---

# 239. Back navigation

Quando múltiplas telas existirem:

testar.

---

# 240. Home/resume

Quando timer existir:

obrigatório.

---

# 241. Notification interruptions

Futuro se relevante.

---

# 242. Incoming call

Comportamento real de lifecycle pode ser simulado/manual quando necessário.

---

# 243. Device matrix não precisa ser enorme em todo commit

## DECIDIDO

A estratégia cresce por estágio.

---

# 244. Cadência proposta — durante coding

Rodar:

- teste/classe afetada;
- engine suite quando mudança de regra.

---

# 245. Cadência proposta — antes de commit

- testes relevantes;
- build relevante;
- Lint quando mudança Android.

---

# 246. Cadência proposta — antes de merge

- suite local completa;
- build app;
- Lint;
- instrumented tests afetados.

---

# 247. Cadência proposta — pós-merge

- build;
- unit suite;
- checks automáticos quando CI existir.

---

# 248. Cadência proposta — pré-release

- release-like build;
- local/JVM;
- instrumented;
- device matrix;
- físico;
- Play pre-launch report;
- smoke manual.

---

# 249. CI

## FUTURO PRÓXIMO

Quando workflow remoto estiver pronto:

GitHub Actions ou solução equivalente deve automatizar gates.

Não escolher plataforma sem necessidade porque GitHub está sendo usado, embora seja candidata natural.

---

# 250. CI mínimo proposto

```text
checkout
↓
configure JDK
↓
./gradlew test
↓
./gradlew lint
↓
./gradlew assembleDebug
```

Tasks exatas dependerão dos módulos.

---

# 251. CI de engine

Se módulo puro existir:

testar isoladamente para feedback rápido.

---

# 252. CI instrumented

Mais caro.

Pode rodar:

- PRs críticos;
- nightly;
- release.

---

# 253. Não rodar 30 devices a cada typo

Custo sem valor.

---

# 254. Required checks

Quando CI ficar estável:

proteger `main` exigindo checks.

---

# 255. Flaky CI

Não ativar required check que falha aleatoriamente.

Primeiro estabilizar.

---

# 256. Test artifacts

CI deve preservar:

- test report;
- Lint report;
- screenshots/logs quando falha.

---

# 257. Stack trace útil

Não esconder com output resumido demais.

---

# 258. Test logs

Evitar imprimir cada movimento de milhares de Perft nodes.

---

# 259. Diagnóstico Perft

Em falha:

usar divide/posição.

---

# 260. Definition of Done

## DECIDIDO

“Código escrito” não significa pronto.

---

# 261. DoD de regra do engine

- [ ] regra documentada;
- [ ] implementação;
- [ ] positive test;
- [ ] negative test;
- [ ] edge cases relevantes;
- [ ] own king safety;
- [ ] suite passa;
- [ ] nenhuma regressão conhecida;
- [ ] diff revisado.

---

# 262. DoD de UI

- [ ] estado correto;
- [ ] interação;
- [ ] strings/resources;
- [ ] accessibility básica;
- [ ] layout no tamanho alvo;
- [ ] lifecycle relevante;
- [ ] teste automatizado onde possui valor;
- [ ] smoke manual;
- [ ] Lint.

---

# 263. DoD de bugfix

- [ ] bug reproduzido;
- [ ] causa entendida;
- [ ] regression test quando possível;
- [ ] correção mínima;
- [ ] teste passa;
- [ ] suite relacionada passa;
- [ ] nenhum workaround escondendo causa.

---

# 264. DoD de refactor

- [ ] comportamento anterior coberto;
- [ ] testes antes verdes;
- [ ] refactor;
- [ ] testes depois verdes;
- [ ] sem alteração funcional não planejada.

---

# 265. DoD de dependência

- [ ] necessidade;
- [ ] compatibilidade;
- [ ] licença;
- [ ] build;
- [ ] testes;
- [ ] Lint;
- [ ] diff.

---

# 266. DoD de documentação

- [ ] consistente com código;
- [ ] não afirma feature inexistente;
- [ ] decisões/status claros;
- [ ] links atualizados.

---

# 267. Quality gate antes de commit

## PROPOSTO

Dependendo do tipo:

```text
engine change:
test

Android code:
test + assembleDebug

UI/resources:
assembleDebug + lint + relevant test

build config:
assembleDebug + test + lint
```

Comandos reais serão confirmados pelo Gradle.

---

# 268. Quality gate antes de merge

## PROPOSTO

```text
working tree reviewed
unit/local tests green
lint green/reviewed
debug build green
affected instrumented tests green
documentation aligned
```

---

# 269. Quality gate antes de release candidate

- [ ] release-like build;
- [ ] engine tests;
- [ ] Perft suite;
- [ ] Lint;
- [ ] instrumented critical flows;
- [ ] physical device;
- [ ] min API validation;
- [ ] recent API validation;
- [ ] accessibility pass;
- [ ] no critical crash;
- [ ] no known blocker.

---

# 270. Release gate

Detalhes operacionais em `12_PLAY_STORE_E_RELEASE.md`.

Mas qualidade exige:

- nenhum P0/P1;
- regressões críticas resolvidas;
- Play pre-launch report revisado;
- data/persistence upgrade test quando existir.

---

# 271. Severidade de bugs

## PROPOSTO

### P0 / Blocker

- app não abre;
- dados corrompem;
- motor produz regra fundamentalmente ilegal;
- crash frequente;
- release impossível.

### P1 / Critical

- xeque/mate incorreto;
- roque/en passant/promoção quebrados;
- estado perdido;
- fluxo principal inutilizável.

### P2 / Major

- feature importante parcialmente quebrada;
- UI severamente comprometida.

### P3 / Minor

- visual menor;
- texto;
- edge pouco impactante não-regra.

---

# 272. Bug de regra é prioridade alta

Mesmo que raro.

Um jogo de xadrez que permite rei entrar em xeque é funcionalmente inválido.

---

# 273. Bug visual

Pode ser menos grave, salvo se impedir uso/acessibilidade.

---

# 274. Test coverage de bug crítico

Depois de corrigido:

regression test obrigatório quando tecnicamente possível.

---

# 275. Test debt

Assim como código, testes podem acumular dívida:

- nomes ruins;
- helpers mágicos;
- duplicação;
- flakiness;
- lentidão.

Refatorar.

---

# 276. Não refatorar teste para esconder cenário

Legibilidade é prioridade.

---

# 277. Test code é código de produção da engenharia

Não precisa ser “bonito demais”, mas deve ser mantível.

---

# 278. Copiar setup excessivamente

Extrair somente quando semântica é a mesma.

---

# 279. Test builder

Pode melhorar posições complexas.

---

# 280. FEN como fixture

Quando parser estiver confiável:

ótimo para posições compactas.

---

# 281. Circularidade de FEN tests

Não usar parser FEN ainda não validado como única forma de montar todos testes do parser.

Para testar parser:

construir expectativas independentemente.

---

# 282. Oráculo independente

## PRINCÍPIO

Esperado deve vir de:

- regra;
- fonte externa;
- cálculo independente;

não da mesma implementação.

---

# 283. Exemplo de teste ruim

```java
expected = engine.generateMoves();
actual = engine.generateMoves();
assertEquals(expected, actual);
```

Impressionante apenas pela capacidade humana de formalizar tautologia.

---

# 284. Teste de mutation state

Após movimento inválido:

snapshot antes = snapshot depois.

Muito importante.

---

# 285. Turn invariants

Após legal:

turn flips.

Após illegal:

turn same.

---

# 286. Piece count invariants

Quiet move:

mesma quantidade.

Capture:

-1 adversária.

Promotion:

mesma quantidade de peças daquele lado, tipo muda.

---

# 287. King count

Sempre:

```text
1 white king
1 black king
```

em partida normal.

---

# 288. Board occupancy

Nenhuma casa com duas peças.

---

# 289. Position bounds

Nenhuma peça fora 8×8.

---

# 290. Game over invariant

Terminal state rejeita movimento.

---

# 291. Castling rights invariant

Nunca recuperam após perder.

---

# 292. En passant invariant

Expira corretamente.

---

# 293. Halfmove invariant

Resets exatos.

---

# 294. Repetition invariant

Movimento inválido não altera histórico.

---

# 295. Hash invariant

Se houver hash:

invalid move não altera.

---

# 296. Move history invariant

Só movimento aplicado entra.

---

# 297. Concurrency tests

## NÃO NECESSÁRIOS AGORA

Só quando IA/network criar concorrência.

---

# 298. Thread-safety

Não testar propriedade inexistente.

---

# 299. Performance tests do engine

## FUTURO

Perft pode fornecer referência de throughput, mas não misturar teste de correção com threshold de performance frágil.

---

# 300. Benchmark separado

Correção:

```text
count == expected
```

Performance:

```text
nodes/sec
```

são objetivos diferentes.

---

# 301. Timing tests frágeis

Evitar:

```text
assert duration < 5 ms
```

em unit test normal.

Máquina CI varia.

---

# 302. Macrobenchmark

Ferramenta adequada para performance Android quando necessário.

---

# 303. Cold start

Futuro.

---

# 304. Frame drops

Futuro quando animações/tabuleiro maduro.

---

# 305. Battery

Futuro se timer/IA/background impactarem.

---

# 306. Memory profiling

Futuro.

---

# 307. Qualidade de build

Build deve ser reproduzível via Wrapper.

---

# 308. Debug build

Principal durante desenvolvimento.

---

# 309. Release build

Precisa ser testado antes da Play Store.

Minification/resource shrinking podem revelar problemas não presentes em debug.

---

# 310. Testar build minified

Quando R8/shrink entrar:

smoke test obrigatório no release candidate.

---

# 311. ProGuard rules

Se feature só funciona em debug:

investigar reflection/R8.

---

# 312. Test coverage em release

Não é necessário instrumentar app final com coverage.

Coverage é ferramenta de desenvolvimento.

---

# 313. Test-only dependencies

Não entram em runtime release.

---

# 314. Debug-only tooling

Não vazar para release.

---

# 315. Version matrix do Android

MinSdk + target/compile + devices reais.

---

# 316. OEM differences

Test Lab/físicos podem capturar.

---

# 317. Screen density

Assets/tabuleiro devem ser verificados.

---

# 318. Small phone

Importante para layout aprovado.

---

# 319. Large phone

Também.

---

# 320. Tablet

## PENDENTE DE ESCOPO

Se Play listing permitir e layout não for adaptado, avaliar suporte.

---

# 321. Foldable

## PENDENTE

Não obrigatório ao MVP.

---

# 322. Orientation

Portrait é referência inicial.

Definir policy antes de test matrix.

---

# 323. Locale principal

Português Brasil inicialmente.

---

# 324. English future

Quando traduzido:

adicionar ao matrix.

---

# 325. Network conditions

MVP local não precisa.

Se multiplayer entrar:

testar:

- offline;
- slow;
- disconnect;
- retry;
- duplicate messages.

---

# 326. Persistence conditions

Quando saving entrar:

- empty;
- existing;
- corrupted;
- migration;
- partial failure.

---

# 327. Room tests

Se Room for escolhido:

seguir documentação atual da ferramenta.

Não especificar agora.

---

# 328. DataStore tests

Se escolhido:

idem.

---

# 329. Backup/restore

Release future.

---

# 330. Time zone

Se timestamps/analytics existirem.

---

# 331. Locale-sensitive notation

FEN/SAN não deve mudar por locale se formato internacional.

UI pode traduzir labels.

---

# 332. Unit test locales

Só quando formatter depender.

---

# 333. Test isolation de filesystem

Usar temp directory quando necessário.

---

# 334. Não gravar no diretório real do usuário durante teste

---

# 335. Test cleanup

Remover recursos temporários.

---

# 336. External services

Futuro:

fakes/staging.

Não depender de produção em unit test.

---

# 337. Test against production service

Somente em release candidate controlado e quando política permitir.

---

# 338. Offline engine

100% local.

---

# 339. Security testing

## FUTURO

Quando houver:

- network;
- account;
- billing;
- storage sensível.

---

# 340. Secrets scanning

Git/CI futuro.

---

# 341. Dependency vulnerabilities

Revisar alerts quando remoto estiver configurado.

---

# 342. Privacy testing

Quando analytics/ads entrarem:

verificar fluxo/consentimento/dados.

---

# 343. Ads

Nunca devem interferir com engine tests.

---

# 344. Billing

Test environment próprio.

Documento 13/12.

---

# 345. Save game corruption

P1 ou P0 dependendo impacto.

---

# 346. Crash on launch

P0.

---

# 347. Illegal chess move accepted

P1.

---

# 348. Wrong checkmate

P1.

---

# 349. Wrong draw

P1.

---

# 350. Miscolored square

P3 salvo impacto de UX/acessibilidade.

---

# 351. Quality dashboard

## FUTURO

Pode reunir:

- tests;
- coverage;
- lint;
- mutation;
- Perft;
- build.

Não criar dashboard antes de dados.

---

# 352. Coverage trend

Mais útil que número isolado.

---

# 353. Test duration trend

Ajuda detectar suite ficando lenta.

---

# 354. Flaky rate

Se CI crescer:

monitorar.

---

# 355. Mutation trend

Futuro.

---

# 356. Defect escape rate

Futuro, depois de usuários.

---

# 357. Crash-free users

Futuro via Play/observability.

---

# 358. Android vitals

Será relevante após publicação.

Documento 12 detalhará.

---

# 359. Pre-launch report

Revisar toda release relevante.

---

# 360. Test tracks

Internal/closed/open pertencem à estratégia de release.

---

# 361. Testing requirements Play

Podem mudar conforme tipo/data da conta.

Antes da publicação, consultar requisitos atuais da Play Console.

---

# 362. Não congelar políticas Google neste documento

Elas mudam.

Documento 12 será atualizado perto da publicação.

---

# 363. Test data

Não usar dados pessoais reais.

---

# 364. Screenshots de falha

Revisar antes de compartilhar.

---

# 365. Logcat de teste

Não conter segredo.

---

# 366. Test naming em CI

Nome precisa ajudar localizar falha sem abrir código.

---

# 367. Parameterized display names

Se framework permitir:

usar nomes legíveis.

---

# 368. Nested tests

## FUTURO / CANDIDATO

Podem organizar:

```text
Castling
 ├─ Kingside
 └─ Queenside
```

Como JUnit Jupiter já está adotado no `:chess-engine`, o recurso está disponível quando realmente melhorar a organização dos testes.

Não usar apenas porque o framework oferece.

---

# 369. Assertion library

## PENDENTE

JUnit assertions podem ser suficientes.

Truth/AssertJ só se melhorarem clareza.

---

# 370. Não adicionar AssertJ por moda

---

# 371. Custom assertions

Podem ser excelentes para domínio.

Exemplo futuro:

```text
assertThatPosition(position).hasPiece("e4", WHITE_PAWN)
```

Mas só quando repetição justificar.

---

# 372. Error messages em custom assertion

Devem mostrar:

- expected;
- actual;
- board dump.

---

# 373. Board dump em falha

## FORTEMENTE RECOMENDADO

Uma posição visual textual reduz tempo de diagnóstico.

---

# 374. FEN em falha

Quando disponível:

incluir.

---

# 375. Move history em falha

Para sequência:

incluir últimos movimentos.

---

# 376. Seed em falha randomized

Incluir.

---

# 377. Device info em falha instrumented

Incluir:

- API;
- model;
- orientation;
- locale quando útil.

---

# 378. Test reports não devem ser commitados

Build output.

---

# 379. Screenshots baseline

Se screenshot tests entrarem, baselines podem precisar ser versionadas.

Definir política na implementação.

---

# 380. Golden update

Nunca atualizar baseline automaticamente só porque teste falhou.

Primeiro verificar se mudança visual é desejada.

---

# 381. Snapshot approval

Mudança intencional precisa revisão humana.

---

# 382. Test code coverage do test code

Não importa como métrica de produto.

---

# 383. Generated code

Pode ser excluído de coverage dependendo da ferramenta/valor.

Não mascarar domain code como generated para melhorar número.

---

# 384. Resources coverage

Line coverage Java não captura qualidade de XML.

Lint/UI tests cobrem outros riscos.

---

# 385. Manifest quality

Lint + instrumented + review.

---

# 386. Build script quality

Build/sync/tests.

---

# 387. Docs quality

Review, links, consistency.

---

# 388. Teste de arquitetura

## FUTURO

Poderíamos automatizar regra:

```text
engine não importa android.*
```

Mas módulo Java puro já impõe grande parte.

Não adicionar ArchUnit sem necessidade.

---

# 389. ArchUnit

## NÃO ADOTAR AGORA

---

# 390. Static dependency test

## ESTADO ATUAL

Como o `:chess-engine` já é um módulo Java puro, o compiler e a própria configuração Gradle são o primeiro guardião da fronteira.

A Fase 2 também verificou explicitamente a ausência de imports `android.*` e `androidx.*` no código do engine.

Não adicionar ArchUnit apenas para provar uma restrição que o build já impõe adequadamente.

---

# 391. Nullability tests

Testar inputs nulos apenas onde contrato relevante.

Não testar toda variável final privada.

---

# 392. Exception tests

Quando constructor/API promete rejeitar estado inválido:

testar tipo/condição.

---

# 393. Mensagem de exception

Só testar texto exato se for parte do contrato.

Caso contrário, torna teste frágil.

---

# 394. `toString` tests

Só se formato for contrato ou ferramenta crítica.

---

# 395. equals/hashCode tests

Value objects:

sim.

---

# 396. HashCode exact value

Não testar valor numérico específico.

Testar contrato:

```text
equal objects → equal hash
```

---

# 397. Collection order tests

Só se ordem for parte do contrato.

---

# 398. Legal moves order

## PENDENTE

Se UI/IA não exige ordem específica:

não testar ordem arbitrária.

Pode comparar sets.

---

# 399. Deterministic move order

IA futura pode querer.

Decisão depois.

---

# 400. Test smell — fragile exact list

Se apenas legal moves importam, ordem talvez irrelevante.

---

# 401. Test smell — excessive mocks

Se teste configura 12 mocks:

design pode estar acoplado demais.

---

# 402. Test smell — huge setup

Se leva 100 linhas para criar situação:

criar fixture clara.

---

# 403. Test smell — assertion roulette

Muitas assertions sem contexto.

---

# 404. Test smell — mystery guest

Teste depende de arquivo/database externo sem ser evidente.

---

# 405. Test smell — test code duplication

Refatorar quando atrapalha manutenção.

---

# 406. Test smell — slow unit test

Se teste unit leva segundos:

investigar IO/sleep/complexidade.

---

# 407. Test smell — test only passes alone

Estado compartilhado.

---

# 408. Test smell — test only passes in suite

Também.

---

# 409. Test smell — ignored forever

Delete/fix.

---

# 410. Test smell — no assertion

Pode ser válido em “does not throw” explícito, mas geralmente suspeito.

---

# 411. Test smell — tautology

Esperado produzido pelo mesmo código.

---

# 412. Test smell — implementation coupling

Mocks privados/métodos internos.

---

# 413. Test smell — over-specified UI

Valida posição exata de cada View quando comportamento não exige.

---

# 414. Test smell — sleep

Banido como sincronização.

---

# 415. Test smell — retry hides failure

Investigar.

---

# 416. Mutation testing detecta alguns test smells

Especialmente assertions fracas.

---

# 417. Code coverage detecta ausência, não qualidade

Reforço intencional.

---

# 418. Manual exploratory testing

## RECOMENDADO ANTES DE MILESTONES

Não seguir roteiro apenas.

Tentar quebrar:

- tocar rápido;
- selecionar peça errada;
- reiniciar durante estado especial;
- background;
- edge cases.

---

# 419. Exploratory session

Pode ter charter:

> tentar quebrar promoção.

---

# 420. Bug bash

## FUTURO

Quando outras pessoas puderem testar.

---

# 421. Dogfooding

Jogar partidas reais no Gambitol.

Excelente para descobrir:

- UX;
- timers;
- regras raras.

---

# 422. Partidas conhecidas

## FUTURO

Reproduzir jogos PGN conhecidos pode validar:

- sequência;
- notação;
- state.

---

# 423. Tactical positions

Não são necessários para legalidade se IA não existe.

---

# 424. Checkmate puzzles

Podem validar mate.

---

# 425. Draw studies

Podem validar dead positions/repetition.

---

# 426. FIDE edge cases

Registrar no doc 05 + teste.

---

# 427. Bug report mínimo

Quando falha for encontrada:

- versão/commit;
- passos;
- estado inicial;
- esperado;
- atual;
- logs/FEN;
- device se Android.

---

# 428. Reproduction first

## DECIDIDO

Não corrigir bug que não conseguimos descrever/reproduzir sem entender evidência.

---

# 429. Intermittent bug

Registrar frequência/condições.

---

# 430. Device-only bug

Capturar:

- model;
- API;
- orientation;
- locale.

---

# 431. Test failure triage

Perguntar:

1. produto quebrou?
2. teste quebrou?
3. ambiente quebrou?
4. expectativa está errada?

---

# 432. Não culpar teste primeiro

---

# 433. Não culpar código primeiro

Evidência.

---

# 434. CI failure after dependency update

Pode ser build/environment.

---

# 435. Instrumented failure

Verificar device state/sync.

---

# 436. Perft divergence

Localizar branch.

---

# 437. Coverage drop

Revisar código novo e testes.

---

# 438. Lint new warning

Entender.

---

# 439. Release smoke checklist

## FUTURO

- launch;
- new game;
- legal move;
- illegal move;
- capture;
- castling;
- en passant;
- promotion;
- mate;
- restart;
- background/resume;
- settings;
- no crash.

---

# 440. Não executar todas regras manualmente em toda release

Automação assume a maior parte.

Smoke verifica integração.

---

# 441. Teste físico da promoção

Importante para UX do modal.

---

# 442. Teste físico do tabuleiro

Touch mapping.

---

# 443. Teste físico de timer

Quando existir.

---

# 444. Teste físico de sound/haptics

Quando existir.

---

# 445. Accessibility manual

TalkBack.

---

# 446. Pre-release device matrix

## PROPOSTO

Quando próximo da publicação:

```text
API mínima
API intermediária
API recente
device físico
tamanho compacto
tamanho grande
```

Expandir conforme dados/escopo.

---

# 447. Release candidate em build semelhante ao release

A documentação Android sugere que RC tests se aproximem do ambiente de produção.

Não validar só debug.

---

# 448. Minification differences

Reforço.

---

# 449. Signing differences

Assinatura normalmente não muda lógica, mas instalação/update sim.

---

# 450. Store pre-launch

Depois de upload em track/test:

revisar relatório.

---

# 451. Crash symbolication

Se R8 entrar:

guardar mapping conforme release process.

Documento 12.

---

# 452. Post-release monitoring

## FUTURO

Qualidade continua depois da publicação:

- crashes;
- ANRs;
- ratings;
- vitals.

---

# 453. Regression from production

Criar teste local quando reproduzível.

---

# 454. User report sem reprodução

Coletar contexto sem dados pessoais desnecessários.

---

# 455. Test maintenance ownership

Em projeto solo:

o mesmo desenvolvedor.

Mas regra é:

> teste quebrado não fica abandonado.

---

# 456. Quality debt log

Pode entrar em issues/roadmap.

Não criar outro documento sem necessidade.

---

# 457. Known issues

Release pode possuir known issue não crítico.

Precisa decisão consciente.

---

# 458. Blocker known issue

Não publicar.

---

# 459. “Não consegui reproduzir”

Não significa inexistente.

Registrar evidência.

---

# 460. Test framework upgrades

Separar de feature.

---

# 461. JUnit upgrade

Build + suite.

---

# 462. Espresso upgrade

Instrumented suite.

---

# 463. AGP upgrade

Todos gates relevantes.

---

# 464. Emulator update

Pode mudar rendering.

---

# 465. Test Lab device deprecation

Matriz precisa ser revisada periodicamente.

---

# 466. Android new version

Adicionar à validação conforme target.

---

# 467. MinSdk change

Se subir:

remover necessidade de testar abaixo.

Decisão de produto/release.

---

# 468. Device coverage não é market share perfeito

Escolher por risco e distribuição futura.

---

# 469. Play device catalog

Futuro pode ajudar a conhecer base suportada.

---

# 470. Quality versus velocidade

## DECIDIDO

Testes devem acelerar confiança.

Se suite lenta demais para rodar:

reorganizar.

Não abandonar.

---

# 471. Fast lane

Durante coding:

unit/engine.

---

# 472. Medium lane

Pre-merge:

full local + lint + selected instrumented.

---

# 473. Slow lane

Nightly/pre-release:

device matrix + large tests.

---

# 474. Essa separação evita duas tragédias

1. feedback lento demais;
2. release sem validação suficiente.

---

# 475. Quality gates incrementais

No início:

- build;
- engine tests;
- Lint.

Depois:

- UI;
- devices;
- coverage;
- mutation.

---

# 476. Não instalar toda ferramenta agora

## DECIDIDO COMO PRINCÍPIO

Este documento define o destino.

Implementação das ferramentas será incremental.

---

# 477. Primeiro teste real

## CONCLUÍDO NO ENGINE EM 2026-08-23

O primeiro teste real do domínio foi criado em:

```text
chess-engine/src/test/java/br/com/raionorio/gambitol/engine/SideTest.java
```

O fluxo foi executado em Red → Green:

1. `SideTest` foi criado antes de `Side`;
2. o teste falhou por ausência do tipo `Side`;
3. `Side` foi implementado;
4. os dois testes passaram.

Isso já substitui, no engine, o valor pedagógico de depender apenas de teste de template.

---

# 478. ExampleUnitTest

Arquivo de template não conta como cobertura do produto.

---

# 479. ExampleInstrumentedTest

Também não.

---

# 480. Remoção dos testes de template

Quando:

- entendidos;
- substituídos;
- build de teste validado.

---

# 481. Primeiro milestone de qualidade

## CONCLUÍDO EM 2026-08-23

O milestone foi atingido:

```text
:chess-engine criado
+
primeiro teste JVM real passando
```

Evidências:

- módulo Java puro integrado à `main` pelo Pull Request `#1`;
- `SideTest` com dois testes;
- `:chess-engine:test` passando;
- `:app:assembleDebug` passando após a integração.

---

# 482. Segundo milestone

Primeira regra com:

- positive;
- negative;
- edge.

---

# 483. Terceiro milestone

Attack + king safety suite.

---

# 484. Quarto milestone

Perft básico.

---

# 485. Quinto milestone

UI integration test.

---

# 486. Sexto milestone

Instrumented critical flow.

---

# 487. Sétimo milestone

Device matrix pre-release.

---

# 488. 🎥 MOMENTO BOM PARA GRAVAR — primeiro teste que falha

Mostrar:

```text
teste vermelho
↓
implementação
↓
verde
```

Tema:

> “Teste não é prova depois do código; é feedback durante a construção.”

---

# 489. 🎥 MOMENTO BOM PARA GRAVAR — cavalo parametrizado

Mostrar vários movimentos válidos/inválidos sem duplicar teste.

---

# 490. 🎥 MOMENTO BOM PARA GRAVAR — regression test

Bug real:

- reproduz;
- teste falha;
- corrige;
- teste protege.

Conteúdo de alto valor.

---

# 491. 🎥 MOMENTO BOM PARA GRAVAR — Perft

Provavelmente um dos melhores conteúdos técnicos do projeto.

---

# 492. 🎥 MOMENTO BOM PARA GRAVAR — coverage não é qualidade

Criar um teste que executa linha sem verificar resultado.

Coverage sobe, bug permanece.

Excelente demonstração.

---

# 493. 🎥 MOMENTO BOM PARA GRAVAR — mutation testing

Mostrar mutante sobrevivente revelando assertion fraca.

---

# 494. 🎥 MOMENTO BOM PARA GRAVAR — flaky test com sleep

Mostrar por que tempo fixo falha.

Depois sincronização adequada.

---

# 495. 🎥 MOMENTO BOM PARA GRAVAR — Activity recreation

Mover peça, recriar Activity, provar que estado permanece.

---

# 496. 🎥 MOMENTO BOM PARA GRAVAR — matriz de dispositivos

Perto da release.

Mostrar mesma UI em API/tamanhos diferentes.

---

# 497. COMO EXPLICAR EM ENTREVISTA — estratégia

> “No Gambitol concentrei a maior parte da validação no motor Java puro, porque as regras de xadrez são determinísticas e podem ser testadas rapidamente na JVM. Mantive testes instrumentados menores e focados em integração, lifecycle e UI. Para o motor usei também casos de regressão e Perft para validar a geração de movimentos.”

Usar quando implementado.

---

# 498. COMO EXPLICAR EM ENTREVISTA — coverage

> “Eu não usei cobertura como meta isolada. Coverage serviu para identificar lacunas, enquanto testes de comportamento, edge cases e posteriormente mutation testing avaliavam se as assertions realmente protegiam as regras.”

---

# 499. COMO EXPLICAR EM ENTREVISTA — flakiness

> “Nos testes de UI evitei sleeps arbitrários e preferi mecanismos de sincronização do Espresso, porque waits fixos tornam a suite dependente da velocidade do ambiente.”

---

# 500. COMO EXPLICAR EM ENTREVISTA — devices

> “A estratégia separava feedback rápido local de validação de alta fidelidade. Antes de release, os fluxos críticos eram executados em múltiplas APIs e tamanhos, incluindo dispositivo físico e testes automatizados em matriz.”

---

# 501. Quality gate do engine

## PROPOSTO

Para merge de regra do engine:

- [ ] compile;
- [ ] unit suite;
- [ ] relevant Perft quando disponível;
- [ ] no Android dependency;
- [ ] regression test se bugfix;
- [ ] code review;
- [ ] doc 05 aligned.

---

# 502. Quality gate da UI

- [ ] assemble;
- [ ] Lint;
- [ ] local tests;
- [ ] relevant instrumented test;
- [ ] manual visual/touch;
- [ ] accessibility basics;
- [ ] no hardcoded resource issues.

---

# 503. Quality gate de Gradle

- [ ] sync;
- [ ] build;
- [ ] tests;
- [ ] lint;
- [ ] wrapper untouched unless intentional;
- [ ] no dynamic dependency.

---

# 504. Quality gate de persistência futura

- [ ] save;
- [ ] load;
- [ ] empty state;
- [ ] invalid/corrupt behavior;
- [ ] migration;
- [ ] process death restore;
- [ ] no data loss.

---

# 505. Quality gate de timer futuro

- [ ] countdown;
- [ ] switch turns;
- [ ] pause/background policy;
- [ ] timeout;
- [ ] impossible-mate draw;
- [ ] recreation;
- [ ] deterministic clock tests.

---

# 506. Quality gate de IA futura

- [ ] only legal moves;
- [ ] never corrupts engine;
- [ ] cancellable;
- [ ] off main thread;
- [ ] deterministic tests where needed;
- [ ] difficulty constraints.

---

# 507. Quality gate de multiplayer futuro

- [ ] authoritative state policy;
- [ ] disconnect;
- [ ] reconnect;
- [ ] duplicate;
- [ ] ordering;
- [ ] invalid remote move;
- [ ] sync conflict;
- [ ] security.

---

# 508. Quality gate de release

- [ ] all P0/P1 closed;
- [ ] engine suite green;
- [ ] Perft green;
- [ ] lint reviewed;
- [ ] instrumented critical paths green;
- [ ] release-like build smoke;
- [ ] min API;
- [ ] recent API;
- [ ] physical device;
- [ ] pre-launch report reviewed;
- [ ] known issues documented;
- [ ] version/signing checks in doc 12.

---

# 509. Critério de falha de gate

Uma falha não deve ser ignorada sem decisão.

Opções:

- corrigir;
- provar falso positivo;
- documentar exceção temporária.

---

# 510. Exceção de qualidade

Deve conter:

- problema;
- risco;
- motivo;
- prazo;
- responsável.

No projeto solo, responsável continua claro.

---

# 511. “Depois a gente testa”

## PROIBIDO PARA REGRA CRÍTICA

Especialmente:

- king safety;
- castling;
- en passant;
- promotion;
- mate;
- draw.

---

# 512. “Teste visualmente”

Insuficiente para regras.

---

# 513. “Coverage passou”

Insuficiente.

---

# 514. “Lint passou”

Insuficiente.

---

# 515. “Build passou”

Confirma compilação/empacotamento, não comportamento.

---

# 516. “Unit tests passaram”

Não confirma UI/dispositivo.

---

# 517. “UI tests passaram”

Não confirma todos edge cases do engine.

---

# 518. Qualidade é composição de evidências

```text
compiler
+
tests
+
static analysis
+
manual review
+
device validation
+
release monitoring
```

---

# 519. Fontes pesquisadas — Android testing fundamentals

## Fundamentals of testing Android apps

https://developer.android.com/training/testing/fundamentals

Usado para:

- testes locais vs instrumentados;
- small/medium/big;
- arquitetura testável;
- isolamento;
- importância da automação;
- framework dependency.

Verificado em: 2026-08-22.

---

# 520. Fontes — Android testing strategy

## Testing strategies

https://developer.android.com/training/testing/fundamentals/strategies

Usado para:

- test pyramid;
- unit/component/feature/application/release candidate;
- frequência;
- fidelity;
- estratégia por camadas;
- device matrix como princípio.

Verificado em: 2026-08-22.

---

# 521. Fontes — Espresso

## Espresso

https://developer.android.com/training/testing/espresso

Usado para:

- UI tests com Views;
- actions;
- assertions;
- sincronização.

---

## Big test stability

https://developer.android.com/training/testing/instrumented-tests/stability

Usado para:

- flakiness;
- synchronization;
- evitar `sleep`;
- idling resources;
- estabilidade.

Verificado em: 2026-08-22.

---

# 522. Fontes — AndroidJUnitRunner

## AndroidJUnitRunner API

https://developer.android.com/reference/androidx/test/runner/AndroidJUnitRunner

Usado para:

- instrumented tests;
- JUnit 3/4 no runner Android;
- filtering;
- sizes;
- annotations.

Verificado em: 2026-08-22.

---

# 523. Fontes — command line tests

## Test from the command line

https://developer.android.com/studio/test/command-line

Usado para:

- execução de testes via Gradle;
- integração com Git Bash;
- instrumented tests.

Verificado em: 2026-08-22.

---

# 524. Fontes — diferentes telas

## Libraries and tools to test different screen sizes

https://developer.android.com/training/testing/different-screens/tools

Usado para:

- não duplicar toda UI suite em todos devices;
- subset por tamanho;
- filtering;
- device-specific tests.

Verificado em: 2026-08-22.

---

# 525. Fontes — Build-Managed Devices

## Scale your tests with build-managed devices

https://developer.android.com/studio/test/managed-devices

Usado para:

- devices configurados em Gradle;
- automated instrumented tests;
- virtual/remote physical devices;
- API support.

Verificado em: 2026-08-22.

---

# 526. Fontes — Firebase Test Lab

## Firebase Test Lab

https://firebase.google.com/docs/test-lab

## Get started testing for Android

https://firebase.google.com/docs/test-lab/android/get-started

## Available devices

https://firebase.google.com/docs/test-lab/android/available-testing-devices

Usado para:

- test matrix;
- devices físicos/virtuais;
- Android versions;
- locale;
- orientation;
- CI;
- instrumentation;
- Robo/Game Loop tests.

Verificado em: 2026-08-22.

---

# 527. Fontes — Robolectric

## Robolectric strategies

https://developer.android.com/training/testing/local-tests/robolectric

Usado para:

- execução local simulada;
- quando usar;
- limitações;
- recomendação de evitar em unit tests quando arquitetura permite isolamento puro.

Verificado em: 2026-08-22.

---

# 528. Fontes — coverage Android

## View code coverage reports

https://developer.android.com/studio/test/coverage-report

Usado para:

- unit coverage;
- instrumentation coverage;
- AGP/JaCoCo;
- relatórios;
- branch/line visualization;
- agregação experimental.

Verificado em: 2026-08-22.

---

# 529. Fontes — Android Lint

## Improve your code with lint checks

https://developer.android.com/studio/write/lint

Usado para:

- static analysis;
- correctness;
- API;
- accessibility;
- performance;
- baseline;
- command line.

Verificado em: 2026-08-22.

---

# 530. Fontes — JUnit

## JUnit User Guide

https://docs.junit.org/

Usado para:

- testes;
- lifecycle;
- parameterized tests;
- assertions;
- organização.

A versão concreta do Gambitol deverá ser confirmada no build antes de usar APIs específicas.

Verificado em: 2026-08-22.

---

# 531. Fontes — Gradle testing

## Testing in Java & JVM projects

https://docs.gradle.org/current/userguide/java_testing.html

Usado para:

- Test task;
- filtering;
- reports;
- failFast;
- JVM tests.

Verificado em: 2026-08-22.

---

# 532. Fontes — JaCoCo

## JaCoCo documentation

https://www.jacoco.org/jacoco/trunk/doc/

## Coverage counters

https://www.jacoco.org/jacoco/trunk/doc/counters.html

Usado para:

- code coverage;
- instruction;
- branch;
- line;
- method;
- complexity counters.

Verificado em: 2026-08-22.

---

# 533. Fontes — mutation testing

## PIT Mutation Testing

https://pitest.org/

## Basic concepts

https://pitest.org/quickstart/basic_concepts/

## Quickstart

https://pitest.org/quickstart/

Usado para:

- mutation testing;
- mutators;
- sobrevivência de mutantes;
- avaliação da qualidade dos testes JVM.

Verificado em: 2026-08-22.

---

# 534. Fontes — Perft

## Chess Programming Wiki — Perft

https://www.chessprogramming.org/Perft

Usado para:

- validação de move generation;
- contagem de árvore;
- debugging.

## Chess Programming Wiki — Perft Results

https://www.chessprogramming.org/Perft_Results

Será usado futuramente para vetores canônicos quando testes forem implementados.

A Chess Programming Wiki é referência técnica, não autoridade normativa das regras. A FIDE continua sendo a autoridade do comportamento.

---

# 535. Fontes — Play pre-launch reports

## Use a pre-launch report to identify issues

https://support.google.com/googleplay/android-developer/answer/9842757

## Understand your pre-launch report

https://support.google.com/googleplay/android-developer/answer/9844487

Usado para:

- estabilidade;
- performance;
- acessibilidade;
- validação pré-release.

Verificado em: 2026-08-22.

---

# 536. Hierarquia de confiança

Para comportamento do xadrez:

```text
FIDE / doc 05
↓
testes
↓
fontes técnicas
```

Para Android testing:

```text
Android Developers
↓
framework docs
↓
tool docs
```

Para ferramentas:

```text
JUnit / Gradle / JaCoCo / PIT oficiais
↓
artigos/comunidade
```

---

# 537. Pontos pendentes de decisão

## PENDENTE

1. JUnit exato do módulo `:app` para testes locais, caso a estratégia atual precise mudar.
2. assertion library além das assertions do próprio JUnit, se houver necessidade real.
3. mocking framework.
4. property-based framework.
5. coverage threshold.
6. JaCoCo configuration.
7. PIT integration.
8. UI screenshot testing.
9. Build-Managed Devices.
10. Firebase Test Lab cadence.
11. CI provider.
12. device matrix final.
13. test naming definitivo conforme a suíte crescer.
14. instrumented test strategy concreta.
15. Robolectric.
16. release performance benchmarks.

## RESOLVIDO EM 2026-08-23

- framework JVM do engine: JUnit Jupiter 6.1.3;
- execução dos testes do engine pela JUnit Platform;
- primeiro teste real do domínio: `SideTest`;
- testes do engine executáveis sem Android/emulador.

---

# 538. Decisões normativas já estabelecidas

## DECIDIDO

1. testes fazem parte da implementação;
2. engine deve ser testada sem Android;
3. `:chess-engine` usa JUnit Jupiter 6.1.3;
4. testes JVM do engine executam pela JUnit Platform;
5. maioria dos testes do motor será rápida/local;
6. UI tests serão focados;
7. teste manual complementa automação;
8. bug relevante gera regression test quando possível;
9. Perft será ferramenta de validação do engine;
10. coverage não é meta de qualidade isolada;
11. não haverá threshold arbitrário inicial;
12. mutation testing é futuro, prioritariamente engine;
13. `Thread.sleep` não é estratégia de sincronização;
14. flaky tests precisam ser corrigidos;
15. Android Lint faz parte da qualidade;
16. build passing não prova comportamento;
17. release exige dispositivo físico;
18. minSdk precisa ser validado;
19. pre-launch report será revisado antes de produção;
20. P0/P1 bloqueiam release.

## EVIDÊNCIA ATUAL

O primeiro comportamento do domínio testado é `Side.opposite()`, com dois testes JVM em `SideTest`.

A validação integrada mais recente executou:

```bash
./gradlew :chess-engine:test :app:assembleDebug
```

com `BUILD SUCCESSFUL` na `main`.

---

# 539. Checklist de aprovação deste documento

- [ ] Testes do engine recebem prioridade adequada.
- [ ] Não existe dependência de emulator para regras puras.
- [ ] Pirâmide não virou proporção artificial.
- [ ] UI tests não duplicam o engine.
- [ ] Perft está integrado conceitualmente.
- [ ] Coverage não é tratado como objetivo isolado.
- [ ] Mutation testing está corretamente adiado.
- [ ] Flakiness possui regras claras.
- [ ] Matriz de devices cresce por fase.
- [ ] API 24 continua considerada.
- [ ] Release gate é mais forte que commit gate.
- [ ] Ferramentas ainda não escolhidas estão marcadas como pendentes.
- [ ] Estratégia continua ensinável e prática.

---

# 540. Resumo operacional

Durante uma feature de engine:

```text
REGRA
↓
TESTE
↓
IMPLEMENTAÇÃO
↓
TESTE VERDE
↓
EDGE CASES
↓
PERFT QUANDO RELEVANTE
↓
REFACTOR
↓
SUITE
↓
DIFF
```

Durante uma feature Android:

```text
COMPORTAMENTO
↓
TESTE LOCAL QUANDO POSSÍVEL
↓
IMPLEMENTAÇÃO
↓
BUILD
↓
LINT
↓
INSTRUMENTED TEST QUANDO AGREGA VALOR
↓
MANUAL VISUAL/TOUCH
↓
DIFF
```

Antes de release:

```text
LOCAL TESTS
+
PERFT
+
LINT
+
INSTRUMENTED
+
RELEASE-LIKE BUILD
+
DEVICE MATRIX
+
PHYSICAL DEVICE
+
PRE-LAUNCH REPORT
+
MANUAL SMOKE
```

---

# 541. Frase norteadora

> **Um teste bom não existe para provar que o código atual está certo. Ele existe para tornar difícil que o comportamento correto seja quebrado no futuro sem que percebamos.**

---

# 542. Relação com o documento 09

O documento `09_UI_UX_GAMBITOL.md` já existe e define como o estado correto produzido pelo motor deve ser apresentado e controlado pelo jogador.

A divisão de responsabilidade permanece:

`08_TESTES_E_QUALIDADE.md` define:

> **como provamos que o Gambitol continua correto.**

`09_UI_UX_GAMBITOL.md` define:

> **como essa correção é apresentada e controlada pelo jogador.**

Mudanças futuras de UI que introduzam novos riscos devem consultar os dois documentos, sem duplicar regras de domínio na interface.
