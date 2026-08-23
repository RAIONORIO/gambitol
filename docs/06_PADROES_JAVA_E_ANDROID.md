# 06 — PADRÕES JAVA E ANDROID DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `06_PADROES_JAVA_E_ANDROID.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir padrões de escrita, organização, legibilidade, segurança, nullability, imutabilidade, tratamento de erros, recursos Android, ViewModel, XML, logs, Gradle, dependências, comentários e qualidade estática do código  
> **Fonte normativa para:** estilo Java, nomes, visibilidade, collections, exceptions, contratos, null, enums, igualdade, imutabilidade, Android Views, recursos, Activity, ViewModel, logging, Lint e build conventions  
> **Não cobre em detalhe:** arquitetura de alto nível, estrutura física completa do repositório, regras do xadrez, estratégia detalhada de testes, Git workflow, UI/UX visual completa, roadmap ou publicação  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo deste documento

Este documento define como o código do Gambitol deve ser escrito.

Não basta saber:

> “onde a classe fica?”

Também precisamos saber:

- como ela deve ser nomeada;
- quanto deve expor;
- quando usar `final`;
- quando permitir `null`;
- como comparar objetos;
- quando usar enum;
- quando lançar exceção;
- como retornar coleções;
- como tratar erros;
- como escrever comentários;
- como usar recursos Android;
- como evitar que ViewModel dependa de Activity;
- como logar sem transformar Logcat em esgoto textual;
- como usar Lint;
- como manter Gradle previsível;
- como revisar dependências;
- como impedir que estilo pessoal vire inconsistência de projeto.

O objetivo não é estética.

O objetivo é reduzir:

- ambiguidades;
- bugs;
- acoplamento;
- esforço de leitura;
- comportamento inesperado;
- diferenças desnecessárias entre arquivos.

---

# 2. Filosofia dos padrões

## DECIDIDO

O Gambitol seguirá cinco princípios.

### 1. Consistência vence preferência pessoal

Se duas formas são equivalentes, escolher uma e manter.

### 2. Clareza vence esperteza

Código “inteligente” que demora para ser entendido é dívida.

### 3. Regra deve ter motivo

Nenhum padrão existe apenas porque alguém escreveu num blog.

### 4. Ferramentas ajudam a impor padrões

Formatter, Lint, compiler e testes devem fazer parte do processo.

### 5. Padrões podem evoluir

Se uma regra atrapalhar mais do que ajudar, ela pode ser revisada conscientemente.

---

# 3. Fontes de estilo utilizadas

Nenhuma única fonte será copiada integralmente.

Foram consideradas:

- Google Java Style Guide;
- AOSP Java code style;
- Java Language Specification;
- Java SE API;
- Android Developers;
- recomendações oficiais de arquitetura Android;
- Android Lint.

A própria documentação do AOSP deixa claro que suas regras são voltadas a contribuições à plataforma Android e que desenvolvedores de aplicativos podem adotar outro padrão, como Google Java Style.

Portanto, o Gambitol adota um **padrão próprio consistente**, inspirado nessas referências.

---

# 4. Formatação oficial do Gambitol

## DECIDIDO

### Indentação

```text
4 espaços
```

Não usar tabs.

Motivos:

- compatível com estilo tradicional Java/Android Studio;
- legível em código orientado a objetos;
- próximo do código Java gerado pelo template Android;
- evita misturar estilo de 2 e 4 espaços.

---

# 5. Limite de linha

## DECIDIDO

Preferência:

```text
100 caracteres
```

Exceções razoáveis:

- URL;
- package;
- import;
- string cuja quebra piora muito a leitura;
- comando shell documentado.

O Google Java Style também usa 100 colunas como limite geral.

A regra não é competição de régua.

Quando linha longa indica expressão confusa:

extrair:

- variável;
- método;
- objeto.

---

# 6. Chaves

## DECIDIDO

Usar chaves mesmo em blocos de uma linha.

Correto:

```java
if (isLegal) {
    applyMove();
}
```

Evitar:

```java
if (isLegal)
    applyMove();
```

e:

```java
if (isLegal) applyMove();
```

Isso reduz erro em manutenção.

Google Java Style também exige braces em `if`, `else`, `for`, `while` e `do`.

---

# 7. Estilo das chaves

## DECIDIDO

K&R:

```java
public void move() {
    if (condition) {
        doSomething();
    } else {
        doSomethingElse();
    }
}
```

A chave abre na mesma linha da declaração/controle.

---

# 8. Uma instrução por linha

## DECIDIDO

Evitar:

```java
move(); changeTurn(); updateState();
```

Preferir:

```java
move();
changeTurn();
updateState();
```

---

# 9. Espaços

Usar:

```java
if (condition) {
```

não:

```java
if(condition){
```

Operadores binários:

```java
row + 1
```

não:

```java
row+1
```

---

# 10. Linhas vazias

Usar para separar blocos lógicos.

Evitar cinco linhas vazias para “respirar”.

---

# 11. Alinhamento artificial

Evitar:

```java
private int     row;
private String  name;
private boolean enabled;
```

Preferir:

```java
private int row;
private String name;
private boolean enabled;
```

Alinhamento manual gera diffs inúteis.

---

# 12. Encoding

## DECIDIDO

```text
UTF-8
```

Google Java Style também exige UTF-8 para source files.

---

# 13. Estrutura de arquivo Java

Ordem:

```text
package
imports
classe principal
```

Se houver cabeçalho legal/licença no futuro:

fica antes de `package`.

Exemplo:

```java
package br.com.raionorio.gambitol.engine;

import java.util.List;
import java.util.Objects;

public final class Example {
}
```

---

# 14. Uma classe top-level principal por arquivo

## DECIDIDO

Arquivo:

```text
Position.java
```

deve conter:

```java
public final class Position
```

como tipo top-level principal.

Google Java Style também exige um top-level type por arquivo.

---

# 15. Imports wildcard

## PROIBIDO

Não usar:

```java
import java.util.*;
```

Preferir:

```java
import java.util.List;
import java.util.Map;
```

Isso torna dependências visíveis.

Google Java Style proíbe wildcard imports.

---

# 16. Imports estáticos

Permitidos quando melhoram legibilidade.

Especialmente em testes:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

Evitar uso que esconda origem de operações importantes.

---

# 17. Ordem dos imports

## DECIDIDO

Usar o formatter/IDE para ordenar automaticamente.

Evitar discutir manualmente ordem de imports a cada arquivo.

A consistência do projeto importa mais do que decorar alfabeto ASCII do guia do Google.

---

# 18. Nomes de package

## DECIDIDO

```text
lowercase
```

Exemplo:

```text
br.com.raionorio.gambitol.engine
```

Não:

```text
br.com.raionorio.gambitol.Engine
```

---

# 19. Nomes de classes

## DECIDIDO

```text
UpperCamelCase
```

Exemplos:

```text
Position
GameState
MoveResult
ChessBoard
```

Nomes normalmente devem ser:

- substantivos;
- frases nominais;
- conceitos reconhecíveis.

Google Java Style também usa UpperCamelCase.

---

# 20. Acrônimos em nomes de classes

## DECIDIDO

Tratar acrônimos como palavras.

Preferir:

```text
FenParser
PgnWriter
SanFormatter
UiState
ApiClient
```

Evitar:

```text
FENParser
PGNWriter
SANFormatter
UIState
APIClient
```

Exceção:

nomes definidos por framework, como:

```text
ViewModel
```

seguem o framework.

---

# 21. Métodos

## DECIDIDO

```text
lowerCamelCase
```

Preferir verbo/frase verbal.

Exemplos:

```java
isLegalMove()
applyMove()
generateLegalMoves()
resetGame()
```

Evitar:

```java
moveThing()
doStuff()
process()
handleEverything()
```

---

# 22. Booleans

Nomes devem comunicar pergunta/estado.

Preferir:

```text
isInCheck
hasCastlingRight
canPromote
isGameOver
```

Evitar:

```text
checkFlag
statusBoolean
valid
```

quando contexto não é óbvio.

---

# 23. Getters booleanos

Preferir semântica consistente:

```java
boolean isGameOver()
boolean canCastle()
boolean hasPiece()
```

não obrigatoriamente:

```java
getGameOver()
```

---

# 24. Campos

## DECIDIDO

```text
lowerCamelCase
```

Exemplos:

```java
sideToMove
halfmoveClock
selectedSquare
```

---

# 25. Constantes

## DECIDIDO

Para constantes reais:

```text
UPPER_SNAKE_CASE
```

Exemplo:

```java
private static final int BOARD_SIZE = 8;
```

Mas:

```java
private static final List<String> mutableList = new ArrayList<>();
```

não deve ser chamado automaticamente de constante apenas por ser `static final`, pois o conteúdo continua mutável.

Google Java Style faz essa mesma distinção.

---

# 26. Parâmetros

## DECIDIDO

```text
lowerCamelCase
```

Evitar parâmetro público de uma letra:

```java
move(P p)
```

Preferir:

```java
move(Position target)
```

---

# 27. Variáveis locais

Nomes curtos são aceitáveis apenas quando contexto é evidente.

Exemplo em loop:

```java
for (int row = 0; row < BOARD_SIZE; row++) {
```

melhor que:

```java
for (int r = 0; r < BOARD_SIZE; r++) {
```

quando a clareza importa.

Em matemática local simples, `dx`, `dy`, `dr`, `dc` podem ser apropriados se documentados pelo contexto.

---

# 28. Uma variável por declaração

Preferir:

```java
int row;
int column;
```

não:

```java
int row, column;
```

Exceção aceitável:

header de `for` quando legível.

---

# 29. Declarar perto do uso

Evitar declarar todas as variáveis no início do método.

Preferir escopo mínimo.

---

# 30. Métodos pequenos

Não existe limite mágico de linhas.

Um método deve ter responsabilidade compreensível.

Sinal de problema:

método faz:

- validação;
- persistência;
- logging;
- navegação;
- mutação;
- formatação;

tudo junto.

---

# 31. Classes pequenas

Mesma regra.

Não dividir uma classe apenas porque passou de X linhas.

Dividir quando responsabilidades divergirem.

---

# 32. `final` em classes

## PROPOSTO COMO PADRÃO

Se uma classe não foi projetada para herança:

```java
public final class Position {
```

é preferível.

Isso comunica:

> não estenda isso por acidente.

---

# 33. Herança deve ser deliberada

Uma classe não deve ficar não-final apenas “caso alguém queira herdar”.

Extensão é parte de design.

---

# 34. `final` em campos

## DECIDIDO COMO PREFERÊNCIA

Campos que não mudam após construção:

```java
private final int row;
```

Usar `final`.

---

# 35. `final` em variáveis locais

## NÃO OBRIGATÓRIO

Não poluir todo método com:

```java
final int row
final int column
final Piece piece
```

apenas por dogma.

Usar quando ajuda:

- captura em lambda;
- comunicação de imutabilidade;
- prevenção de reatribuição relevante.

---

# 36. `final` em parâmetros

## NÃO OBRIGATÓRIO

Não será padrão obrigatório.

---

# 37. Visibilidade

## DECIDIDO

Usar a menor visibilidade necessária.

Ordem de preferência:

```text
private
package-private
protected/public quando necessário
```

---

# 38. `public`

Toda API pública cria compromisso.

Não tornar método `public` apenas para o teste alcançar.

Primeiro revisar estrutura/package.

---

# 39. Package-private

É ferramenta importante no módulo Java.

Pode ocultar detalhes internos sem criar outra abstração.

---

# 40. `protected`

Usar somente quando herança foi intencionalmente projetada.

Não como “quase público”.

---

# 41. Campos públicos

## PROIBIDO POR PADRÃO

Evitar:

```java
public int row;
```

Estado deve ser controlado.

Exceções raras:

constantes públicas realmente imutáveis.

---

# 42. Encapsulamento

Encapsular para proteger invariantes.

Exemplo:

```java
position.setRow(99);
```

não deve ser possível se isso viola domínio.

---

# 43. Getters e setters

Não criar automaticamente para todos os campos.

Perguntar:

> essa operação faz sentido no domínio?

Uma `Position` pode ter:

```java
getRow()
getColumn()
```

mas não necessariamente:

```java
setRow()
setColumn()
```

---

# 44. Setter genérico versus operação semântica

Evitar:

```java
game.setSideToMove(BLACK);
```

em API pública.

Preferir operação:

```java
game.applyMove(move);
```

que atualiza turno como consequência correta.

---

# 45. Imutabilidade

## DECIDIDO COMO PREFERÊNCIA PARA VALUE OBJECTS

Objetos que representam valores simples devem ser candidatos fortes a imutabilidade.

Exemplos conceituais:

- Position;
- Move;
- resultados;
- snapshots.

---

# 46. Benefícios da imutabilidade

- menos estados intermediários;
- igualdade mais previsível;
- uso seguro em collections;
- debugging mais simples;
- melhor teste;
- menor risco de UI alterar engine.

---

# 47. Imutabilidade não significa tudo immutable

Uma partida naturalmente evolui.

Objeto dono do estado pode ser mutável.

O importante é:

> mutação controlada.

---

# 48. Collections expostas

## DECIDIDO

Não retornar collection interna mutável diretamente.

Evitar:

```java
public List<Move> getMoves() {
    return moves;
}
```

se chamador puder executar:

```java
getMoves().clear();
```

---

# 49. Cópias defensivas

Quando necessário:

```java
return List.copyOf(moves);
```

ou estratégia compatível com o nível Java suportado.

A API Java 21 define `List.copyOf` como criação de lista não modificável cujo conteúdo não reflete alterações posteriores da collection de origem.

---

# 50. Compatibilidade antes de usar APIs modernas

## CONCEITO CRÍTICO

Ter JDK 21 instalado NÃO significa automaticamente que qualquer API Java 21 pode ser usada no app Android.

Existem quatro conceitos distintos:

```text
JDK que roda Gradle
sourceCompatibility
targetCompatibility
compileSdk/minSdk
desugaring
```

A documentação oficial do Android deixa isso explícito.

---

# 51. JDK do build

O JDK executa:

- Gradle;
- compiler;
- testes JVM.

O projeto havia selecionado um JDK 21 para o ambiente.

## IMPORTANTE

Ainda precisamos confirmar no build real:

- JDK efetivamente usado;
- sourceCompatibility;
- targetCompatibility.

---

# 52. Java language level

## PENDENTE DE CONFIRMAÇÃO

Não assumir:

```text
Java source 21
```

apenas porque o JDK é 21.

A documentação Android indica que:

```text
sourceCompatibility
```

determina as features de linguagem disponíveis.

---

# 53. Java APIs no Android

`compileSdk` determina quais APIs Android/Java estão disponíveis durante compilação.

`minSdk` determina o dispositivo mínimo.

Desugaring pode oferecer parte de APIs modernas em Android antigo.

---

# 54. Regra do Gambitol para API Java

Antes de usar recurso Java mais novo:

1. verificar `sourceCompatibility`;
2. verificar `targetCompatibility`;
3. verificar API disponível no Android;
4. verificar desugaring se necessário;
5. build;
6. teste em API mínima.

---

# 55. Records

Java 21 suporta `record`.

Eles são úteis para data carriers imutáveis.

Mas no Gambitol:

## PENDENTE

Não usar `record` antes de confirmar compatibilidade de source/Android build.

Até lá, value object normal é perfeitamente aceitável.

---

# 56. Switch expressions

Mesmo raciocínio.

Não usar sintaxe moderna apenas porque IDE aceita editar.

Verificar nível de linguagem.

---

# 57. `var`

Java possui inferência local.

## PROPOSTO

Evitar como padrão no início do projeto.

Motivo educacional:

tipos explícitos ajudam leitura enquanto Java está sendo consolidado.

Pode ser revisado futuramente.

---

# 58. Lambdas

Permitidas quando melhoram clareza.

Exemplo:

listeners simples.

Não transformar lógica de xadrez em cadeia funcional obscura só para parecer moderna.

---

# 59. Streams

## USO MODERADO

Streams podem ser úteis.

Mas loops claros são perfeitamente aceitáveis.

Preferir:

```java
for (Move move : moves) {
```

quando isso é mais fácil de ler.

---

# 60. Streams em código de engine

Não usar pipeline complexo em hot path sem medir.

Clareza primeiro.

---

# 61. Optional

A API oficial Java afirma que `Optional` é destinado principalmente como retorno quando existe necessidade clara de representar ausência e `null` pode causar erro.

## PADRÃO DO GAMBITOL

`Optional<T>` pode ser usado em retorno quando melhora contrato.

Exemplo conceitual:

```java
Optional<Piece> findPiece(Position position)
```

Mas não usar em todo lugar.

---

# 62. Optional como campo

## EVITAR POR PADRÃO

Não armazenar:

```java
private Optional<Piece> selectedPiece;
```

sem forte motivo.

Preferir modelar estado diretamente.

---

# 63. Optional como parâmetro

## EVITAR

Não exigir chamador a criar Optional para chamar método.

---

# 64. Optional nunca deve ser null

Se tipo é `Optional<T>`:

retornar:

```java
Optional.empty()
```

não:

```java
null
```

A própria documentação Java destaca isso.

---

# 65. Nullability

## DECIDIDO

`null` precisa ter significado claro.

Não permitir `null` “porque talvez”.

---

# 66. Contratos non-null por padrão

No engine:

## PROPOSTO

Parâmetros e retornos são não nulos por padrão, salvo documentação explícita.

Isso reduz combinatória de estados.

---

# 67. `Objects.requireNonNull`

Para argumentos obrigatórios:

```java
this.position = Objects.requireNonNull(position, "position");
```

é apropriado.

A documentação Java define `Objects.requireNonNull` especialmente para validação de parâmetros e construtores.

---

# 68. Mensagens de null

Preferir nome do contrato:

```text
"position"
```

ou:

```text
"position must not be null"
```

Não escrever mensagem longa irrelevante.

---

# 69. AndroidX `@NonNull` e `@Nullable`

No módulo Android podem ser úteis.

No engine Java puro:

## EVITAR DEPENDÊNCIA ANDROIDX APENAS POR ANOTAÇÃO

Não quebrar pureza do módulo só para usar annotation Android.

Se no futuro quisermos nullability annotations Java-only:

avaliar dependência apropriada separadamente.

---

# 70. Null object

Não introduzir Null Object pattern sem necessidade.

Às vezes:

```text
EMPTY
```

é útil.

Às vezes esconde ausência real.

---

# 71. `null` em collections

## EVITAR

Lists/sets/maps do domínio não devem conter `null` sem propósito explícito.

---

# 72. Empty collection

Preferir:

```text
lista vazia
```

a:

```text
null
```

para “nenhum movimento”.

---

# 73. Collections

Escolher pela semântica.

### `List`

ordem importa, duplicação permitida.

### `Set`

unicidade.

### `Map`

associação chave → valor.

Não escolher por hábito.

---

# 74. Tipo da variável

Preferir interface quando não precisa da implementação:

```java
List<Move> moves = new ArrayList<>();
```

não:

```java
ArrayList<Move> moves = new ArrayList<>();
```

na API/variável quando métodos específicos de ArrayList não são necessários.

---

# 75. Exceção

Se performance/tipo concreto importa:

usar explicitamente.

---

# 76. Collections imutáveis

`List.of` e `List.copyOf` produzem listas não modificáveis em Java moderno.

Mas:

## REGRA

Verificar compatibilidade Android/desugaring antes de usar APIs Java modernas.

---

# 77. `Collections.unmodifiableList`

Pode criar view não modificável sobre collection mutável.

Isso não é igual a cópia imutável.

Exemplo:

origem muda → view reflete mudança.

O desenvolvedor deve entender diferença.

---

# 78. Arrays

Arrays são apropriados para estruturas fixas.

Tabuleiro 8x8 é candidato conceitual.

Mas não expor array mutável interno diretamente.

---

# 79. Arrays em APIs públicas

Se retornar array:

considerar cópia defensiva.

---

# 80. Generics

Usar generics para segurança de tipo.

Evitar raw types:

```java
List list;
```

Preferir:

```java
List<Move> moves;
```

---

# 81. Wildcards

Não usar até necessidade real.

Quando surgir:

explicar PECS e variance no contexto.

---

# 82. Enums

## RECOMENDADO PARA CONJUNTOS FECHADOS

Exemplos conceituais:

```text
PieceColor
PieceType
GameResult
```

Enum evita strings soltas.

---

# 83. Comparação de enum

Pode usar:

```java
color == PieceColor.WHITE
```

A JLS garante singleton por constante enum.

---

# 84. Enum com comportamento

É permitido.

Mas não transformar enum em classe gigante de regras sem avaliar legibilidade.

---

# 85. Strings mágicas

Evitar:

```java
if (pieceType.equals("KNIGHT")) {
```

Preferir enum.

---

# 86. Números mágicos

Evitar:

```java
if (row == 7) {
```

quando significado não é evidente.

Pode existir constante semântica:

```java
private static final int LAST_RANK_INDEX = 7;
```

Mas não criar constante para todo número evidente como `0` em loop.

---

# 87. `BOARD_SIZE`

Boa constante:

```java
private static final int BOARD_SIZE = 8;
```

---

# 88. Equality

## CONCEITO CRÍTICO

`==` em objetos compara referência.

`equals` compara igualdade lógica quando sobrescrito.

Value objects provavelmente precisarão de `equals`.

---

# 89. `equals` e `hashCode`

## DECIDIDO

Se sobrescrever `equals`, sobrescrever `hashCode` de forma consistente.

Especialmente se objeto for usado em:

- HashMap;
- HashSet;
- repetition keys.

---

# 90. `Objects.equals`

Pode simplificar comparação null-safe.

Não usar para esconder contrato onde null deveria ser proibido.

---

# 91. `toString`

Value objects devem considerar `toString` útil para debugging.

Exemplo conceitual:

```text
Position{file=e, rank=4}
```

ou formato compacto aprovado.

Não incluir dados sensíveis.

---

# 92. `toString` não é serialização

Nunca depender de formato de `toString` para persistência.

Pode mudar.

---

# 93. Comparable

Só implementar se existir ordem natural real.

Uma `Position` não precisa ser Comparable apenas para sort ocasional.

---

# 94. Comparator

Usar para ordens externas/específicas.

---

# 95. Exceptions

## DECIDIDO

Não ignorar exceções silenciosamente.

Google Java Style e AOSP alertam explicitamente contra catch vazio sem justificativa.

---

# 96. Catch vazio

## PROIBIDO POR PADRÃO

Não:

```java
try {
    ...
} catch (Exception e) {
}
```

---

# 97. Catch genérico

Evitar:

```java
catch (Exception e)
```

se existe tipo específico conhecido.

---

# 98. `Throwable`

Não capturar por padrão.

Isso inclui erros graves da JVM.

---

# 99. Checked versus unchecked

Escolher pela natureza do contrato.

### Programador violou pré-condição

`IllegalArgumentException` pode ser apropriada.

### Estado interno impossível

`IllegalStateException` pode ser apropriada.

### Falha externa recuperável

Pode exigir exception específica.

---

# 100. Movimento ilegal não é necessariamente exception

Conforme documento 05:

jogada ilegal é input esperado.

Preferir resultado de validação/retorno apropriado.

---

# 101. Exceções não controlam fluxo normal

Evitar:

```text
tentar mover
↓
lançar exception
↓
catch para saber se era válido
```

em fluxo comum.

---

# 102. Mensagem de exception

Incluir contexto útil.

Ruim:

```text
"error"
```

Melhor:

```text
"target position is outside the board"
```

Sem vazar dados sensíveis.

---

# 103. Rethrow

Se capturar e não conseguir tratar:

rethrow ou converta com contexto apropriado.

Não logar e engolir automaticamente.

---

# 104. Log + throw duplicado

Cuidado:

logar exception em várias camadas gera mesma stack repetida.

Definir owner do log.

---

# 105. Try-with-resources

Quando usar recurso `AutoCloseable`:

preferir try-with-resources.

Exemplo futuro:

- streams;
- files.

---

# 106. Finalizers

## PROIBIDO

Não sobrescrever `finalize()`.

Google Java Style também proíbe.

---

# 107. Assertions Java

Podem ser úteis internamente em teste/debug.

Não usar `assert` para validar input externo porque pode estar desativado.

---

# 108. Retornos antecipados

Permitidos quando reduzem nesting.

Exemplo:

```java
if (!isInsideBoard(target)) {
    return false;
}

if (hasFriendlyPiece(target)) {
    return false;
}

return isPathClear(from, target);
```

Preferível a três níveis de `if`.

---

# 109. Nesting profundo

Se método chega a:

```text
if
  if
    for
      if
        if
```

considerar extração.

---

# 110. Boolean expression

Não compactar lógica de xadrez complexa em uma linha monstruosa.

Nomear partes.

---

# 111. Método booleano

Preferir nome que lê como pergunta:

```java
if (isSquareAttacked(target, opponent)) {
```

---

# 112. Comentários

## REGRA CENTRAL

Comentário explica:

- por quê;
- invariantes;
- decisão;
- detalhe não óbvio.

Não repetir código.

Ruim:

```java
// increment row
row++;
```

---

# 113. Comentários para regra de xadrez

Bom:

```java
// The rook may be attacked during castling; only the king's path must be safe.
```

quando a regra for contraintuitiva.

---

# 114. Idioma dos comentários

## PROPOSTO

Código, nomes e comentários técnicos permanentes em inglês.

Motivos:

- ecossistema Java/Android;
- portfólio;
- interoperabilidade;
- termos técnicos;
- nomes de API.

Documentação pedagógica continua em português.

---

# 115. Comentário temporário

Evitar deixar:

```java
// teste
// arrumar
// talvez
```

em produção.

---

# 116. TODO

Se realmente necessário:

```java
// TODO: explain the pending work clearly.
```

Mas TODO não substitui issue/roadmap quando tarefa é importante.

---

# 117. TODO sem contexto

Evitar:

```java
// TODO
```

---

# 118. Código comentado

## PROIBIDO

Não deixar blocos antigos comentados.

Git já guarda histórico.

---

# 119. Javadoc

Usar quando API pública possui contrato que não é óbvio.

Exemplos:

- engine public API;
- tipos de domínio importantes;
- método com semântica especial.

---

# 120. Javadoc redundante

Evitar:

```java
/**
 * Gets the row.
 *
 * @return the row
 */
public int getRow()
```

se não há nada adicional para explicar.

Google Java Style permite omitir Javadoc em members autoexplicativos.

---

# 121. Javadoc útil

Exemplo conceitual:

```java
/**
 * Returns whether the square is attacked by the specified side.
 *
 * <p>Attack semantics are independent from full move legality, so a pinned piece
 * can still attack a square for king-safety purposes.
 */
```

Isso documenta regra importante.

---

# 122. `@Override`

## DECIDIDO

Sempre usar quando sobrescrevendo método.

Ajuda compiler e leitor.

---

# 123. Anotações

Uma annotation por linha quando melhora clareza.

Não empilhar cinco em mesma linha.

---

# 124. `@SuppressWarnings`

## EVITAR SEM JUSTIFICATIVA

Antes:

- entender warning;
- resolver se possível.

Se suprimir:

- escopo mínimo;
- motivo claro.

---

# 125. Android Lint suppression

Mesma regra.

Não:

```text
@SuppressLint("all")
```

---

# 126. Android Lint

## DECIDIDO

Lint será parte do quality gate.

A documentação Android recomenda corrigir erros de lint antes de publicar e destaca que Lint detecta problemas estruturais, APIs depreciadas e incompatibilidades.

---

# 127. Comando de lint

Quando configurado:

```bash
./gradlew lint
```

ou task específica da variante.

Não assumir task exata sem consultar projeto.

---

# 128. Lint baseline

## NÃO USAR NO INÍCIO SEM NECESSIDADE

Baseline serve para projetos com dívida existente.

Gambitol é novo.

Melhor corrigir problemas do que escondê-los num baseline desde o começo.

---

# 129. Warning zero

Objetivo:

não acumular warnings relevantes.

Mas warning precisa ser entendido, não eliminado mecanicamente.

---

# 130. Formatter

## PROPOSTO

Usar formatter consistente do Android Studio configurado para o padrão do projeto.

Não reformar o arquivo inteiro manualmente em cada mudança.

---

# 131. google-java-format

## FUTURO / CANDIDATO

Pode automatizar estilo, mas usa indentação de 2 espaços, diferente do padrão proposto de 4.

Logo:

não adotar sem decisão.

---

# 132. Checkstyle

## FUTURO / NÃO NECESSÁRIO AGORA

Pode impor regras.

Não adicionar plugin antes de necessidade real.

Lint + formatter + revisão são suficientes inicialmente.

---

# 133. SpotBugs / PMD

## FUTURO

Podem ser avaliados se projeto crescer.

Não instalar uma pequena polícia de static analysis antes do primeiro peão andar.

---

# 134. Activity

## DECIDIDO

Activity deve permanecer fina.

Responsabilidades:

- lifecycle;
- inflar/configurar UI;
- observar estado;
- encaminhar ações.

Não conter regras do xadrez.

---

# 135. `onCreate`

Não transformar em método de 300 linhas.

Extrair configuração coerente:

```text
setupViews
setupObservers
setupListeners
```

se isso realmente melhorar leitura.

---

# 136. Métodos `setup*`

Não exagerar.

Se Activity tem 4 linhas, não criar sete métodos apenas por padrão.

---

# 137. ViewModel

## DECIDIDO COMO DIREÇÃO ARQUITETURAL

Se usado, não deve guardar:

- Activity;
- Fragment;
- View;
- Context;
- Resources;

como dependência normal.

A recomendação oficial Android diz explicitamente para manter ViewModel agnóstico ao lifecycle e evitar essas referências.

---

# 138. AndroidViewModel

## EVITAR

A documentação oficial recomenda preferir `ViewModel` e não `AndroidViewModel`.

Se Context for necessário:

avaliar responsabilidade.

---

# 139. ViewModel e engine

ViewModel coordena.

Não reimplementa regras.

---

# 140. ViewModel e estado

Expor estado de forma observável.

No ecossistema Java/Views:

a tecnologia concreta será decidida quando implementarmos.

---

# 141. LiveData

Pode ser opção pragmática para Java/Views.

Mas não será imposto por este documento.

---

# 142. StateFlow

É recomendação moderna do Android em Kotlin.

O Gambitol é Java.

Não adicionar Kotlin ao projeto só para seguir exemplo de documentação.

---

# 143. UDF

## DECIDIDO COMO PRINCÍPIO

```text
UI envia ação
↓
state holder processa
↓
estado muda
↓
UI renderiza
```

Evitar View alterar estado de domínio diretamente.

---

# 144. Eventos one-shot

Não inventar `SingleLiveEvent` automaticamente.

Primeiro modelar se aquilo é:

- estado;
- efeito;
- navegação.

---

# 145. Context

## REGRA

Manter Context perto da camada Android.

Não passar Context para engine.

---

# 146. Activity Context versus Application Context

Quando Context for necessário:

entender lifetime antes.

Não trocar por `getApplicationContext()` como solução universal.

---

# 147. Memory leaks

Evitar referências de longa duração a:

- Activity;
- View;
- Fragment.

Especialmente em objetos com lifecycle maior.

---

# 148. Listeners

Remover/desregistrar quando API exige.

Lifecycle deve controlar.

---

# 149. Static View

## PROIBIDO

Nunca guardar View/Activity em campo `static`.

---

# 150. Resources

## DECIDIDO

Textos visíveis:

```text
strings.xml
```

Cores:

```text
colors.xml
```

Temas:

```text
themes.xml
```

Drawables:

```text
drawable/
```

Icons de launcher:

```text
mipmap/
```

---

# 151. Strings hardcoded

## PROIBIDO PARA UI FINAL

Não:

```java
textView.setText("Xeque-mate");
```

Preferir:

```java
textView.setText(R.string.game_checkmate);
```

A documentação Android recomenda externalizar strings para facilitar manutenção e localização.

---

# 152. Strings técnicas internas

Log/debug strings não precisam virar recurso Android.

`strings.xml` é para conteúdo de UI/localizável.

---

# 153. Nome de string

## PROPOSTO

Prefixo pela feature/contexto:

```text
game_checkmate
game_restart
game_draw
settings_sound
```

Evitar:

```text
text1
label
ok2
```

---

# 154. Formatação de string

Preferir placeholders em resource:

```xml
<string name="game_turn">Turn: %1$s</string>
```

em vez de concatenar texto localizável.

---

# 155. Plurals

Usar `plurals` quando quantidade exigir flexão.

Não assumir plural português vale em todos idiomas.

---

# 156. Texto dentro de imagem

Evitar quando possível.

Dificulta localização/acessibilidade.

---

# 157. Default resources

A documentação Android alerta que recurso alternativo deve ter correspondente default adequado para evitar crash em configurações não previstas.

---

# 158. Cores hardcoded em layout

## EVITAR

Não:

```xml
android:textColor="#FFD700"
```

repetido por várias telas.

Preferir recurso/tema semântico.

---

# 159. Nome de cor semântico versus literal

Preferir quando for token de interface:

```text
game_board_light
game_board_dark
game_accent
```

em vez de:

```text
dark_green_2
```

se a cor possui papel semântico.

Para paleta base, nomes literais podem coexistir.

---

# 160. Theme

A documentação Android distingue:

- style de uma View;
- theme aplicado a app/Activity/hierarquia.

O Gambitol deve usar theme para identidade global.

---

# 161. Theme attributes

Quando componente deve acompanhar theme:

preferir atributo semântico.

Isso facilita evolução dark/light.

---

# 162. XML

## DECIDIDO

Layouts devem ser legíveis.

Ordem consistente de atributos.

Android Studio pode formatar.

---

# 163. IDs de View

```text
lowercase_with_underscores
```

Exemplos:

```text
game_board
current_player_text
restart_button
```

---

# 164. IDs com tipo no final

## PROPOSTO

Formato:

```text
<significado>_<tipo>
```

Exemplo:

```text
restart_button
timer_text
board_view
```

Ajuda Java/Views.

---

# 165. `textView1`

## PROIBIDO

Renomear IDs gerados para intenção real.

---

# 166. Width/height

Evitar números mágicos em px.

Usar:

- `dp`;
- `sp`;
- constraints;
- dimensões apropriadas.

---

# 167. Texto

Tamanho de texto em:

```text
sp
```

não `dp`.

---

# 168. Espaçamento

Usar `dp`.

---

# 169. `match_parent` e `wrap_content`

Escolher pela intenção.

Não usar `match_parent` por hábito em tudo.

---

# 170. Layout nesting

Evitar hierarquia profunda sem necessidade.

Pode afetar leitura/performance.

---

# 171. ConstraintLayout

Pode ser adequado para telas complexas.

Não precisa ser usado para toda estrutura simples.

---

# 172. Custom View

Se tabuleiro for Custom View:

- `onDraw` deve desenhar;
- não conter engine;
- evitar alocações repetidas pesadas em `onDraw`;
- medidas derivadas do tamanho real.

---

# 173. Touch

Converter MotionEvent para intenção semântica.

Não enviar pixel para engine.

---

# 174. `contentDescription`

Imagens/controles relevantes devem possuir descrição acessível quando aplicável.

Elementos decorativos não devem gerar ruído desnecessário.

---

# 175. Touch target

Projetar botões com área tocável adequada.

Não criar ícones microscópicos só porque cabem no mockup.

---

# 176. Estado visual por cor

Não depender apenas de cor para comunicar algo crítico.

Pode combinar:

- highlight;
- forma;
- borda;
- texto;
- contraste.

---

# 177. Accessibility

Detalhamento maior em `09_UI_UX_GAMBITOL.md`.

Padrão técnico:

não ignorar warnings de acessibilidade do Lint.

---

# 178. `android.R` drawables

Evitar depender de drawables internos da plataforma para identidade visual.

A documentação Android alerta que recursos do package `android` podem mudar entre versões.

---

# 179. Resource prefix

Se futuramente houver Android Library separada:

considerar prefixo para evitar colisões.

No app único não é obrigatório.

---

# 180. Logs

## DECIDIDO

Logs servem para diagnóstico.

Não para registrar cada linha executada.

---

# 181. TAG

Em classes Android:

usar TAG consistente.

Exemplo:

```java
private static final String TAG = "GameActivity";
```

ou derivação segura conforme padrão aprovado.

---

# 182. Nível de log

### `Log.v`

detalhe extremo.

### `Log.d`

debug.

### `Log.i`

evento informativo importante.

### `Log.w`

situação inesperada recuperável.

### `Log.e`

falha.

Não usar `Log.e` para tudo.

---

# 183. Stack trace

Quando houver exception:

passar exception ao log quando apropriado:

```java
Log.e(TAG, "Failed to restore game", exception);
```

Isso preserva stack trace.

---

# 184. Log de domínio

Engine Java puro não deve depender de:

```java
android.util.Log
```

Se logging interno for necessário:

usar abstração Java ou remover após debugging.

---

# 185. Logs de produção

Evitar dados sensíveis.

Nunca logar:

- token;
- senha;
- key;
- dados pessoais desnecessários;
- credenciais.

---

# 186. Logcat

A documentação Android mostra que Logcat exibe mensagens e stack traces em tempo real.

O tutor deve usá-lo como ferramenta de diagnóstico, não como console de spam.

---

# 187. `System.out.println`

## EVITAR NO APP ANDROID

Para Android runtime, usar logging adequado.

No engine/testes:

pode ser útil temporariamente, mas não como logging permanente.

---

# 188. Debug prints temporários

Remover quando problema resolvido, salvo se log continuar útil.

---

# 189. Dependências

## REGRA

Antes de adicionar biblioteca:

- problema;
- alternativa;
- licença;
- tamanho;
- manutenção;
- compatibilidade;
- impacto no minSdk;
- impacto no build;
- necessidade de Android;
- necessidade de teste.

---

# 190. Biblioteca para uma função trivial

Evitar.

---

# 191. Dependência duplicada

Antes de adicionar:

verificar se AndroidX/Java já resolve.

---

# 192. Versões de dependências

Preferir version catalog quando projeto já utiliza:

```text
gradle/libs.versions.toml
```

---

# 193. `+` em versão

## PROIBIDO

Não:

```text
1.+
latest.release
```

Build deve ser reproduzível.

---

# 194. Snapshot dependency

Evitar em produção salvo decisão explícita.

---

# 195. Dependência sem uso

Remover.

---

# 196. Transitive dependency

Não confiar cegamente em transitiva se API é usada diretamente e contrato exigir declaração.

Avaliar caso.

---

# 197. Gradle Wrapper

## DECIDIDO

Executar via:

```bash
./gradlew
```

no Git Bash.

Não depender de Gradle global.

---

# 198. Build files

O projeto usa Kotlin DSL:

```text
build.gradle.kts
```

Isso não muda a linguagem principal do app.

---

# 199. Build script readability

Evitar lógica complexa em Gradle sem necessidade.

Configuração de app deve ser legível.

---

# 200. Versões Java explícitas

A documentação Android recomenda especificar toolchain ou compatibilidade explicitamente para builds consistentes.

## PROPOSTO

Após confirmar versões do projeto, registrar explicitamente:

- JDK/toolchain;
- sourceCompatibility;
- targetCompatibility.

Não alterar agora sem ler arquivos reais.

---

# 201. `JAVA_HOME`

Quando build é executado no terminal, a documentação Android informa que `JAVA_HOME`, se definido, determina o JDK usado por Gradle.

Isso será importante para nosso fluxo Git Bash.

---

# 202. Android Studio Gradle JDK

O JDK usado pela IDE e o usado pelo terminal devem ser mantidos consistentes quando possível.

Isso reduz comportamento “funciona na IDE, falha no Git Bash”.

---

# 203. AGP

Android Gradle Plugin possui requisitos de JDK.

Antes de alterar versão:

consultar compatibility/release notes.

---

# 204. compileSdk

Atualizar conscientemente.

Não confundir com minSdk.

---

# 205. minSdk

Define versão mínima suportada.

O projeto inicial foi configurado com API 24.

Alterar somente por decisão registrada.

---

# 206. targetSdk

Afeta comportamentos e publicação.

Não alterar apenas para “tirar warning” sem entender impacto.

---

# 207. Desugaring

Quando API Java moderna for desejada em minSdk baixo:

avaliar core library desugaring.

Não ativar sem saber qual API precisa.

---

# 208. BuildConfig

Não usar como depósito de segredos.

Valores embutidos no APK podem ser extraídos.

---

# 209. Secrets

Nunca commitar.

---

# 210. Configuração local

Paths locais ficam em:

```text
local.properties
```

quando aplicável.

---

# 211. Data classes manuais

Como Java não possui `data class`:

value objects devem ser escritos conscientemente.

---

# 212. Lombok

## NÃO ADOTAR AGORA

Motivos:

- esconde código durante aprendizado;
- plugin/processamento;
- debugging menos direto;
- não necessário no tamanho atual.

Pode ser revisado futuramente, mas não há necessidade.

---

# 213. AutoValue

## NÃO ADOTAR AGORA

Mesma filosofia.

O projeto quer aprender Java.

---

# 214. Code generation

Permitida onde plataforma exige/beneficia.

Mas evitar gerar domínio central para não esconder comportamento.

---

# 215. Reflection

## EVITAR NO ENGINE

Não usar para resolver problema que tipagem normal resolve.

Pode complicar:

- performance;
- obfuscation;
- debugging.

---

# 216. Serialization automática

Não anotar entidades do engine com SDK externo sem decisão.

---

# 217. Static state

## EVITAR

Não guardar partida atual em static.

---

# 218. Singleton

Não usar por padrão.

Se objeto realmente deve ser singleton:

justificar lifetime e testabilidade.

---

# 219. Global mutable state

## PROIBIDO POR PADRÃO

Estado global mutável torna testes frágeis.

---

# 220. Constructor injection

## RECOMENDADO

Quando classe depende de outra:

```java
public GameViewModel(GameEngine engine) {
    this.engine = Objects.requireNonNull(engine);
}
```

Preferir dependência explícita.

---

# 221. Service locator

## EVITAR

Não esconder:

```text
GlobalDependencies.getEngine()
```

sem necessidade.

---

# 222. Hilt

## NÃO AGORA

Arquitetura 03 já definiu que manual DI é suficiente inicialmente.

---

# 223. Interface para tudo

## PROIBIDO COMO DOGMA

Não criar:

```text
IPosition
IMove
IBoard
IGame
```

sem necessidade real.

---

# 224. Sufixo `Impl`

## EVITAR

Se classe pública precisa ser chamada:

```text
GameRepositoryImpl
```

talvez responsabilidade/nome possam melhorar.

AOSP API guidelines também desencorajam expor detalhes `Impl`.

---

# 225. Classes utilitárias

Se realmente necessária:

```java
public final class SomethingUtils {
    private SomethingUtils() {
    }
}
```

Mas primeiro perguntar se função pertence a um conceito.

---

# 226. Métodos estáticos

Úteis para funções puras ou factory.

Não usar como substituto de objeto que possui estado/contrato.

---

# 227. Factory methods

Podem melhorar nomes:

```java
Position.fromAlgebraic("e4")
```

em vez de construtor obscuro.

Mas só se necessário.

---

# 228. Constructors

Devem criar objeto válido ou falhar.

Não deixar objeto “meio construído”.

---

# 229. Telescoping constructors

Se houver muitos parâmetros:

reavaliar design.

Não introduzir builder automaticamente.

---

# 230. Builder

Útil quando:

- muitos opcionais;
- objeto complexo;
- testes.

Não para `Position(row, column)`.

---

# 231. Value object validation

Se Position aceita 0..7:

validar no boundary adequado.

Não permitir objeto inválido se o tipo promete posição válida.

---

# 232. Defensive programming

Não duplicar validação em toda camada.

Definir owner.

---

# 233. Fail fast

Para erro de programação/invariante:

falhar perto da origem costuma ser melhor.

---

# 234. User input

Entrada do usuário deve ser validada sem crash.

UI recebe toque; engine retorna legalidade.

---

# 235. Strings vindas do usuário

Se no futuro houver username/chat:

tratamento será específico.

Não faz parte do MVP.

---

# 236. Threading

## REGRA

Não criar thread manual sem necessidade.

Para trabalho pesado futuro:

usar mecanismo apropriado.

---

# 237. Main thread

Nunca executar operação longa:

- rede;
- banco pesado;
- IA profunda;

na main thread.

---

# 238. Engine inicial

Provavelmente rápida o suficiente para interação humana.

Não mover tudo para background antes de medir.

---

# 239. Synchronized

Não adicionar por medo.

Concorrência precisa existir de fato.

---

# 240. Volatile

Mesmo princípio.

---

# 241. Collections concorrentes

Não usar sem necessidade.

---

# 242. Handler

Não usar para timer só porque é Android.

Definir semântica e mecanismo apropriado quando feature chegar.

---

# 243. Timer

Estado de relógio não depende de TextView.

---

# 244. Date/time

Se precisar:

usar API adequada e considerar desugaring/API level.

Não usar `System.currentTimeMillis()` espalhado se regra precisa ser testável.

---

# 245. Random

IA/aleatoriedade futura deve permitir teste determinístico quando necessário.

---

# 246. Magic boolean

Evitar métodos:

```java
move(position, true, false, true);
```

Preferir objeto/opções/enum semântico.

---

# 247. Parameter explosion

Se método possui muitos parâmetros correlacionados:

provavelmente existe objeto faltando.

---

# 248. Boolean return ambíguo

Se `false` pode significar cinco coisas:

considerar resultado estruturado.

---

# 249. Enums para status

Bom candidato quando estados são fechados.

---

# 250. Strings para status

Evitar.

---

# 251. Result object

Pode conter:

- sucesso;
- motivo;
- estado.

Só criar se API precisar.

---

# 252. Exceptions e UI

UI não mostra `exception.getMessage()` diretamente ao usuário.

Mensagem técnica não é texto de produto.

---

# 253. Localização de mensagens

UI traduz erro semântico para string resource.

---

# 254. Logging e exceptions

Logar falha técnica onde há contexto suficiente.

---

# 255. Logging e movimento inválido

Não gerar `Log.e` porque usuário tentou mover torre diagonal.

Isso é input normal.

---

# 256. Analytics e regra

Não misturar:

```java
engine.applyMove();
analytics.logEvent();
```

dentro do engine.

Integração fica fora.

---

# 257. Unit boundaries

Método deve depender de abstração mínima necessária.

---

# 258. Public API do engine

Deve ser pequena.

Quanto menos `public`, menor acoplamento.

---

# 259. Package cycles

Evitar.

Se aparecer:

revisar responsabilidades.

---

# 260. Naming de engine

Termos de xadrez devem ser consistentes.

Escolher:

```text
rank/file
```

ou:

```text
row/column
```

para cada contexto, documentando conversão.

Não misturar aleatoriamente dentro da mesma API.

---

# 261. Inglês técnico do xadrez

Código deve preferir termos internacionais:

```text
king
queen
rook
bishop
knight
pawn
castling
enPassant
checkmate
stalemate
```

em vez de nomes em português.

Motivo:

- bibliografia;
- interoperabilidade;
- FEN/PGN;
- portfólio.

---

# 262. `horse`

## PROIBIDO

O nome correto da peça em inglês é:

```text
Knight
```

não:

```text
Horse
```

---

# 263. `tower`

Preferir:

```text
Rook
```

não `Tower`.

---

# 264. `draw`

Usar para empate.

Não `tie` como termo principal do domínio.

---

# 265. `sideToMove`

Preferir a:

```text
currentPlayerColor
```

quando representa cor cujo turno é atual.

Ambos podem ser claros; manter um termo.

---

# 266. Chess vocabulary consistency

Criar glossário se necessário no documento 05.

Não inventar sinônimos por arquivo.

---

# 267. `isValidMove` versus `isLegalMove`

## IMPORTANTE

Documento 05 distingue:

- pseudo-legal;
- legal.

Evitar `valid` quando o significado é ambíguo.

Preferir:

```text
isPseudoLegalMove
isLegalMove
```

se a arquitetura usar essa distinção.

---

# 268. Naming do ataque

Preferir:

```text
isSquareAttacked
```

ao invés de:

```text
canMoveTo
```

porque ataque e movimento legal são diferentes.

---

# 269. Flag de roque

Nomes devem indicar:

```text
canCastleKingside
hasKingsideCastlingRight
```

dependendo se é:

- direito histórico;
- legalidade atual.

Não confundir.

---

# 270. Historical right versus current availability

Exemplo:

```text
hasKingsideCastlingRight = true
```

mas:

```text
canCastleKingside = false
```

porque caminho está bloqueado.

Nomes devem refletir diferença.

---

# 271. En passant naming

Distinguir:

- target;
- eligibility;
- move.

---

# 272. Promotion naming

`promotionPieceType` ou equivalente.

Não `newPiece`.

---

# 273. Check state

`isInCheck(color)` pode ser melhor do que campo redundante.

---

# 274. Game result

Nome deve indicar terminal.

---

# 275. Claim flags

Não chamar claimable draw de resultado final.

---

# 276. Test class names

## PROPOSTO

```text
<ClassName>Test
```

quando testa uma classe.

Para comportamento transversal:

```text
CastlingRulesTest
EnPassantRulesTest
```

Nomes definitivos dependem da estrutura.

Google Java Style também usa sufixo `Test`.

---

# 277. Test method names

Detalhes no documento 08.

Mas nomes devem expressar:

```text
cenário + comportamento esperado
```

---

# 278. AAA nos testes

Comentários:

```text
// Arrange
// Act
// Assert
```

podem ser usados enquanto aprendemos.

Depois podem ser removidos quando estrutura estiver óbvia.

---

# 279. Produção sem comentários de tutorial

Código do app não deve conter aula inteira.

Ensino fica em:

- documentação;
- comentários relevantes;
- conteúdo.

---

# 280. Performance comments

Se algoritmo estranho existe por performance:

documentar motivo e benchmark.

---

# 281. Workaround comments

Todo workaround deve dizer:

- problema;
- por que existe;
- condição para remover;
- link/issue quando possível.

---

# 282. Deprecated API

## EVITAR EM CÓDIGO NOVO

Se documentação atual oferece substituto suportado:

usar substituto.

---

# 283. `@Deprecated`

Se nossa API interna for substituída:

pode ser útil durante migração.

Não manter legado sem prazo em projeto novo.

---

# 284. Lint `NewApi`

Nunca suprimir sem entender minSdk/guard.

---

# 285. API level checks

Quando necessário:

```java
if (Build.VERSION.SDK_INT >= ...) {
```

Mas preferir AndroidX compatibility APIs quando apropriadas.

---

# 286. Compat libraries

AndroidX pode oferecer comportamento uniforme.

Avaliar antes de branch manual por versão.

---

# 287. Backward compatibility

MinSdk 24 exige testar recursos usados.

---

# 288. Java desugaring

Não confundir:

```text
linguagem Java
```

com:

```text
biblioteca Java
```

Alguma sintaxe pode ser desugared enquanto API não existe sem core library desugaring.

---

# 289. Records novamente

Antes de usar:

- verificar D8/AGP;
- sourceCompatibility;
- target;
- desugaring;
- minSdk.

Não aprender compatibilidade do jeito divertido, que é descobrir no aparelho antigo depois da release.

---

# 290. Android Resources e ViewModel

A recomendação oficial diz não passar Resources/Context ao ViewModel sem avaliar camada.

Logo:

ViewModel idealmente produz estado sem strings localizadas.

UI converte:

```text
GameResult.CHECKMATE
```

para:

```text
R.string.game_checkmate
```

---

# 291. UI state e resources

Evitar colocar `R.string.*` dentro do engine.

No app, UiState pode usar:

- semantic status;
- raw values;

e View resolve recursos.

---

# 292. Se UiState precisar de mensagem

Preferir tipo/estado estruturado.

Não `String` pronta produzida pelo engine.

---

# 293. ViewModel factory

Se manual DI exigir:

usar factory apropriada.

Não construir engine diferente em cada rotação.

---

# 294. Lifetime do engine

Definir ownership.

Não static.

---

# 295. SavedState

Usar para estado pequeno/reconstrução quando necessário.

Não guardar grafo inteiro indiscriminadamente.

---

# 296. Parcelable/Serializable

Não anotar engine inteira apenas para passar entre Activities.

Preferir IDs/snapshots quando necessário.

---

# 297. Java Serializable

## EVITAR COMO PERSISTÊNCIA DE LONGO PRAZO

É conveniente, mas formato não é ideal para versionamento de dados do app.

---

# 298. Parcelable

É Android-specific.

Não colocar no engine Java puro.

---

# 299. DTO Android

Se precisar atravessar Android boundary:

adapter converte.

---

# 300. XML comments

Usar para explicar estrutura não óbvia.

Não comentar todo atributo.

---

# 301. XML hardcoded text

Lint detecta.

Corrigir.

---

# 302. XML tools namespace

Usar `tools:` para preview/dev quando apropriado.

Não confundir com runtime.

---

# 303. IDs estáveis

Renomear View IDs conscientemente.

Refactor IDE ajuda.

---

# 304. Binding listeners

Não colocar regras dentro de `setOnClickListener`.

Errado:

```java
button.setOnClickListener(v -> {
    // 80 lines of game logic
});
```

---

# 305. Listener ideal

```java
button.setOnClickListener(v -> viewModel.onRestartRequested());
```

ou equivalente.

---

# 306. Listener e lambda

Lambda curta é legível.

Se lógica cresce:

extrair método.

---

# 307. Anonymous classes

Aceitáveis quando API exige/clareza.

Não usar lambda se torna menos legível.

---

# 308. Method reference

Pode ser usado:

```java
button.setOnClickListener(this::onRestartClick);
```

quando assinatura e intenção ficam claras.

---

# 309. Recycler adapters

Quando surgirem:

não colocar business rules dentro.

---

# 310. UI formatting

Formatação de tempo/texto pertence à UI.

Regra do relógio pertence a owner apropriado.

---

# 311. Time formatting

Usar locale apropriado quando texto for para usuário.

---

# 312. Numbers em UI

Não concatenar indiscriminadamente.

Usar recursos/formatação quando localizável.

---

# 313. Date/time locale

Se entrar no produto, considerar locale.

---

# 314. Audio

Adapters Android.

Não engine.

---

# 315. Haptics

Adapter Android.

---

# 316. Themes

Não definir cor em Java:

```java
view.setBackgroundColor(0xFF123456);
```

se recurso resolve.

---

# 317. Dynamic colors

Não necessário para identidade do Gambitol inicialmente.

---

# 318. Dark mode

O projeto possui visual escuro aprovado.

Ainda assim theme deve ser organizado semanticamente.

---

# 319. Night resources

Não duplicar arquivo inteiro se apenas valores mudam.

---

# 320. Accessibility strings

Descrições localizáveis também vão em resources.

---

# 321. Content descriptions dinâmicas

Podem incluir:

- peça;
- posição;
- status.

UI gera usando recursos formatados.

---

# 322. Lint no CI

## FUTURO

Quando CI existir:

incluir Lint.

---

# 323. Warnings as errors

## FUTURO / AVALIAR

Pode ser útil quando base estiver limpa.

Não ativar no escuro.

---

# 324. Compiler warnings

Não ignorar.

---

# 325. Deprecated warnings

Investigar.

---

# 326. Unchecked warnings

Entender generics.

Não `@SuppressWarnings("unchecked")` amplo.

---

# 327. Null warnings

Tratar contrato.

---

# 328. Resource warnings

Corrigir ou justificar.

---

# 329. Accessibility warnings

Tratar como qualidade, não “só warning”.

---

# 330. Security lint

Alta prioridade.

---

# 331. Logging lint

Revisar.

---

# 332. Build variants

Debug pode ter ferramentas extras.

Release deve ser limpo.

---

# 333. Debug-only code

Preferir source set quando apropriado.

---

# 334. Feature flags temporárias

Não deixar boolean escondido:

```java
private static final boolean NEW_BOARD = true;
```

por meses.

---

# 335. Dead code

Remover.

Git guarda histórico.

---

# 336. Unused imports

Remover automaticamente.

---

# 337. Unused methods

Se não há plano concreto:

remover.

---

# 338. Premature abstractions

Não criar base class sem duas necessidades reais.

---

# 339. DRY

Não aplicar cegamente.

Duas linhas iguais não significam abstração.

Duplicação pode ser mais clara enquanto conceitos ainda divergem.

---

# 340. WET versus DRY

Repetir duas vezes pode revelar padrão.

Extrair na terceira quando semântica é realmente igual.

Não é lei numérica, é heurística.

---

# 341. YAGNI

## DECIDIDO COMO PRINCÍPIO

Não implementar feature porque “pode precisar”.

---

# 342. KISS

Preferir solução simples que atende regras/testes.

---

# 343. SOLID

Usar para diagnosticar design, não para decorar.

---

# 344. SRP

Pergunta:

> “essa classe muda por quantos motivos diferentes?”

---

# 345. OCP

Não criar abstração infinita para possíveis peças futuras.

Xadrez padrão já tem conjunto conhecido.

---

# 346. LSP

Se herança de Piece exigir subclasses quebrando contrato:

reavaliar herança.

---

# 347. ISP

Não criar interface enorme com métodos que implementações ignoram.

---

# 348. DIP

Engine não depende de Android.

Essa é aplicação concreta importante.

---

# 349. Composition over inheritance

Preferência quando comportamento varia e herança criaria rigidez.

Mas não dogma.

---

# 350. Data versus behavior

Modelagem deve refletir domínio.

Não forçar tudo para service externo.

---

# 351. Getter chains

Evitar:

```java
game.getBoard().getPieces().get(0).setPosition(...)
```

Isso expõe demais.

---

# 352. Tell, don't ask

Quando faz sentido:

pedir operação ao dono do estado.

---

# 353. Law of Demeter

Usar como heurística.

Não criar wrappers absurdos só para “obedecer”.

---

# 354. Immutability boundary

UI recebe snapshot/estado que não pode corromper engine.

---

# 355. Defensive copies e performance

Primeiro segurança.

Se profiling mostrar custo:

otimizar com contrato claro.

---

# 356. Caching

Não cachear estado derivado sem necessidade.

Cache cria invalidação.

---

# 357. Memoization

Só se medido.

---

# 358. Premature optimization

## EVITAR

Engine humana não precisa bit twiddling antes de estar correta.

---

# 359. Algorithm clarity

Comentários podem citar:

- FIDE;
- Perft;
- regra especial.

---

# 360. Chess rule references

Em edge cases muito estranhos, comentário curto pode apontar para seção documentada.

Não copiar parágrafos FIDE para código.

---

# 361. Error messages em testes

Assertions devem produzir contexto suficiente.

---

# 362. Logging durante testes

Evitar print massivo.

Quando teste falha:

diagnóstico deve vir da assertion/board dump.

---

# 363. Board dump

Pode ser ferramenta utilitária de teste/debug.

Não UI.

---

# 364. FEN para debugging

Se implementado:

excelente para falhas.

---

# 365. Determinismo

Código de regra deve ser determinístico.

Evitar dependência de relógio/random global.

---

# 366. Clock abstraction

Quando timer vier:

injeção de fonte de tempo pode facilitar teste.

---

# 367. Random abstraction

Quando IA randômica vier:

seed.

---

# 368. Thread safety docs

Não declarar classe thread-safe sem garantia.

---

# 369. Immutable docs

Não chamar collection de immutable se elementos são mutáveis e isso importa.

---

# 370. Unmodifiable ≠ deeply immutable

## CONCEITO IMPORTANTE

Uma lista pode não permitir `add`, mas objetos dentro dela podem mudar.

---

# 371. Deep immutability

Se contrato exigir:

elementos também precisam ser imutáveis ou defensivamente copiados.

---

# 372. Equality de collections

List considera ordem.

Set não.

Isso importa em testes.

---

# 373. HashMap iteration order

Não depender se ordem não é garantida pela implementação usada.

Se ordem importa:

usar estrutura apropriada.

---

# 374. EnumMap

Pode ser útil para chave enum.

Não usar por micro-otimização prematura.

---

# 375. EnumSet

Pode ser útil para direitos/flags enum.

Candidato futuro.

---

# 376. Bitmask manual

Evitar inicialmente se EnumSet deixa intenção mais clara.

---

# 377. Primitive obsession

Se vários `int` representam conceitos diferentes:

considerar value objects/enums.

---

# 378. Boolean blindness

Se método recebe vários booleans:

rever modelagem.

---

# 379. Nullability blindness

Se `null` pode significar:

- vazio;
- não carregado;
- erro;
- não aplicável;

modelar estados distintos.

---

# 380. UI loading states

Talvez não sejam relevantes no jogo local.

Não criar `Loading/Success/Error` padrão se não há async data.

---

# 381. Template architecture cargo cult

Não copiar estrutura de app de notícias para jogo local.

---

# 382. Repository cargo cult

Não criar repository sem fonte de dados.

---

# 383. UseCase cargo cult

Não criar wrapper de uma linha para cada método do engine.

---

# 384. ViewModel cargo cult

Não criar ViewModel para cada componente.

---

# 385. Dependency injection cargo cult

Não instalar Hilt para ensinar DI antes de haver graph.

---

# 386. Clean code cargo cult

Nomes longos demais também podem piorar leitura.

---

# 387. Abreviações aceitas

Termos universais do xadrez podem aparecer:

```text
Fen
Pgn
San
```

Mas em nomes Java tratar como palavras.

---

# 388. Abreviações não aceitas

Evitar:

```text
mgr
proc
util
tmp
obj
data2
```

quando domínio tem nome claro.

---

# 389. Sufixos úteis

```text
Parser
Formatter
Repository
Mapper
Factory
ViewModel
UiState
Result
```

somente quando papel existe.

---

# 390. Sufixos suspeitos

```text
Manager
Helper
Util
Processor
Handler
```

exigem justificativa.

---

# 391. Nome “Handler”

No Android já existe `Handler`.

Evitar usar genericamente para lógica de domínio.

---

# 392. Nome “Service”

No Android possui significado de componente.

Evitar para classe comum sem contexto.

---

# 393. Nome “Activity”

Somente subclasses de Activity.

---

# 394. Nome “ViewModel”

Somente tipo que cumpre esse papel.

---

# 395. Resource names

Sempre minúsculos com underscore.

---

# 396. File names Java

Case-sensitive class name + `.java`.

---

# 397. XML file names

lowercase_with_underscores.

---

# 398. Drawable names

Sem espaço, hífen ou uppercase.

---

# 399. Version catalog aliases

Manter legíveis.

Não criar alias criptográfico.

---

# 400. Test dependency scopes

Usar:

```text
testImplementation
androidTestImplementation
```

conforme ambiente.

Não colocar JUnit instrumentado em produção.

---

# 401. `implementation` versus `api`

Se módulo Java expõe tipos de dependência na API pública:

avaliar `api`.

Por padrão:

```text
implementation
```

para encapsular dependência.

---

# 402. Engine dependencies

Idealmente muito poucas.

---

# 403. AndroidX no engine

## PROIBIDO POR PADRÃO

Motor Java puro não deve depender de AndroidX.

---

# 404. JUnit no `main`

## PROIBIDO

Test framework só em test configuration.

---

# 405. Debug libraries em release

Evitar.

---

# 406. Dependency updates

Não atualizar todas as versões automaticamente sem build/test.

---

# 407. Version bump

Dependência nova/atualizada deve ser mudança consciente.

---

# 408. Release notes

Para atualização importante:

ler breaking changes.

---

# 409. API deprecada

Trocar quando possível antes de acumular.

---

# 410. Android Studio suggestions

Sugestão da IDE não é ordem.

Entender antes de aplicar.

---

# 411. Quick fix

Mesma regra.

---

# 412. AI quick fix

Mesma regra em dobro.

---

# 413. Código gerado por IA

Deve seguir estes padrões antes de ser aceito.

Não aceitar:

- wildcard import;
- public fields;
- Android no engine;
- hardcoded string UI;
- exception engolida;
- nome genérico.

---

# 414. Revisão de IA

Checklist:

- estilo;
- contrato;
- null;
- mutabilidade;
- dependência;
- API level;
- resource;
- lint;
- teste.

---

# 415. Exemplos de código em documentação

Podem simplificar.

Mas não devem ser confundidos com classe aprovada.

---

# 416. Snippet didático versus produção

Tutor precisa avisar quando snippet é apenas exemplo.

---

# 417. Copy-paste de Stack Overflow

Não aceitar sem:

- entender;
- verificar data/API;
- adaptar;
- testar.

---

# 418. Código de tutorial antigo

Android muda rápido.

Consultar docs atuais.

---

# 419. AOSP style versus app style

AOSP é boa referência, mas não autoridade absoluta para nosso app.

O próprio AOSP diz que app developers podem escolher outro padrão.

---

# 420. Google Java Style

É referência principal para:

- naming;
- imports;
- braces;
- Javadoc;
- clarity.

O Gambitol diverge em:

```text
indentação de 4 espaços
```

em vez de 2.

Essa divergência é intencional.

---

# 421. Automação de estilo

Se futuramente adotarmos formatter:

atualizar este documento se formatter impuser padrão diferente.

Não manter regra que ferramenta contradiz continuamente.

---

# 422. EditorConfig

## FUTURO / CANDIDATO

Pode ser adicionado para:

- UTF-8;
- spaces;
- indentation;
- trailing whitespace.

Não criar automaticamente antes de revisar suporte/benefício.

---

# 423. Trailing whitespace

Remover.

---

# 424. Newline no fim do arquivo

## DECIDIDO

Todo arquivo texto deve terminar com newline.

---

# 425. Line endings

No Windows/Git Bash:

definir política Git separadamente no documento 07.

Não permitir que conversão gere diffs gigantes.

---

# 426. JavaDoc encoding

UTF-8.

---

# 427. Comentários em português

Permitidos temporariamente durante ensino.

Mas produção deve convergir para inglês técnico se comentário permanecer.

---

# 428. Nomes de teste em inglês

## PROPOSTO

Manter código inteiro em inglês.

---

# 429. UI em português

Pode ser idioma default inicial.

Isso não muda nomes de classes.

---

# 430. Domain language

Código usa vocabulário de xadrez inglês.

Documentação explica em português.

---

# 431. Publicação internacional futura

Essa separação facilita localização.

---

# 432. Android resources e tradução

Todas strings default devem existir.

Arquivos localizados podem conter subset e cair no default.

A documentação Android descreve esse fallback.

---

# 433. Pluralization

Não fazer:

```java
count + " jogadas"
```

em UI final.

---

# 434. Formatting locale

Não usar `String.format` sem locale quando texto/localização exige comportamento definido.

---

# 435. Decimal formatting

Se rating/estatística surgir:

locale.

---

# 436. Accessibility e strings

Não concatenar informação inacessível.

---

# 437. Semantic state

Engine retorna conceito.

UI retorna apresentação.

---

# 438. Deprecation annotations

Quando API nossa ficar obsoleta:

Javadoc deve apontar substituto.

---

# 439. Public API stability

Enquanto engine é interno ao app, refatoração pode ser mais livre.

Ainda assim manter boundary consistente.

---

# 440. Semantic versioning do engine

## NÃO NECESSÁRIO AGORA

Não é biblioteca publicada.

---

# 441. API surface

Menor é melhor.

---

# 442. Package-private helpers

Preferir antes de `public`.

---

# 443. Nested enum

Se enum só faz sentido dentro de classe:

pode ser nested.

Mas shared domain enum deve ser top-level quando melhora uso.

---

# 444. Nested class

Mesma regra.

---

# 445. Static nested class

Preferir a inner class quando não precisa referência externa.

---

# 446. Inner class

Usar apenas quando precisa do objeto externo.

---

# 447. Memory leak com inner class Android

Cuidado com non-static inner classes de longa duração referenciando Activity.

---

# 448. Callback interfaces

Definir pequenas e sem dependência excessiva.

---

# 449. Listener naming

```text
OnMoveSelectedListener
```

só se pattern for realmente necessário.

Não proliferar listeners se ViewModel action resolve.

---

# 450. Error handling em UI

Erro técnico:

- log;
- estado de erro apropriado;
- mensagem amigável.

Não exibir stack trace.

---

# 451. Crash versus recover

Não engolir bug interno só para manter app aberto em estado corrompido.

Às vezes falhar em debug é melhor.

---

# 452. StrictMode

## FUTURO / CANDIDATO DE DEBUG

Pode ajudar detectar operações inadequadas na main thread.

Não necessário no engine inicial.

---

# 453. Assertions de debug

Podem validar invariantes.

Não substituir testes.

---

# 454. Precondition methods

Podem centralizar validação simples.

Não criar framework próprio.

---

# 455. `Objects.checkIndex`

Pode ser útil dependendo da compatibilidade Java/Android.

Verificar antes.

---

# 456. Manual bounds check

Perfeitamente aceitável:

```java
return row >= 0 && row < BOARD_SIZE;
```

---

# 457. Domain error enum

Pode ser útil para motivo de jogada inválida.

Não criar 30 valores sem UI/teste usar.

---

# 458. Exception hierarchy própria

## EVITAR NO INÍCIO

Não criar:

```text
ChessException
InvalidChessException
MoveChessException
```

sem necessidade.

---

# 459. Checked custom exception

Só se caller realmente puder/precisar recuperar de condição excepcional.

---

# 460. Validation result

Provavelmente mais adequado para movimento ilegal.

---

# 461. `boolean` first version

Pode ser suficiente inicialmente.

Evoluir se UI precisar de motivo.

---

# 462. API evolution

Não prever todas as necessidades futuras.

---

# 463. Refactor safely

Teste antes/depois.

---

# 464. Rename safely

IDE refactor + Git diff.

---

# 465. Extract method

Usar quando nome explica intenção melhor que comentário.

---

# 466. Extract class

Quando responsabilidades divergem.

---

# 467. Inline method

Se abstração não ajuda mais.

---

# 468. Dead abstraction

Remover.

---

# 469. Build after refactor

Obrigatório para mudança estrutural relevante.

---

# 470. Lint after UI/resource change

Recomendado.

---

# 471. Test engine after domain change

Obrigatório.

---

# 472. Test instrumented after Android-specific change

Quando feature exige.

---

# 473. Comments and tests

Se regra pode ser expressa por teste, teste é melhor proteção.

Comentário ainda pode explicar porquê.

---

# 474. Public Javadoc e tests

Ambos cumprem papéis diferentes.

---

# 475. Source-of-truth docs

Este documento define padrão.

Se código antigo divergir:

novo código deve convergir, mas não fazer refactor massivo sem objetivo.

---

# 476. Boy Scout Rule

Melhorar área tocada quando seguro.

Não usar como desculpa para alterar 50 arquivos em feature pequena.

---

# 477. Diff hygiene

Mudança funcional não deve vir misturada com reformatação não relacionada.

---

# 478. Formatter before commit

Pode ser aplicado somente aos arquivos alterados.

---

# 479. Import optimize

Mesma regra.

---

# 480. Build output

Nunca editar.

---

# 481. IDE generated files

Entender antes de versionar/remover.

---

# 482. Gradle deprecation warnings

Investigar cedo.

---

# 483. Android deprecated APIs

Investigar cedo.

---

# 484. Java deprecated APIs

Investigar.

---

# 485. Unstable APIs

Evitar alpha/beta em core sem necessidade.

---

# 486. Experimental AndroidX

Exige justificativa.

---

# 487. API annotations

Se biblioteca exige opt-in:

entender risco.

---

# 488. ProGuard/R8 rules

Não adicionar regra ampla:

```text
-keep class ** { *; }
```

para “resolver”.

Isso pode inutilizar shrinker.

---

# 489. Reflection libraries

Precisam regras R8 adequadas.

---

# 490. Serialization libraries

Mesma preocupação.

---

# 491. Engine sem reflection

Preferível.

---

# 492. Release logging

Revisar logs antes da publicação.

---

# 493. Release debug flags

Não deixar comportamento debug ativo.

---

# 494. BuildConfig.DEBUG

Pode ser usado para debug-only behavior, mas preferir source sets quando apropriado.

---

# 495. Deterministic builds

Fixar versões e wrapper.

---

# 496. Gradle daemon JDK

Documentação Android moderna distingue JDK de IDE/Gradle/toolchain.

Quando setup for retomado, confirmar.

---

# 497. Java 21 instalado ≠ Android Java 21

## ARMADILHA COMUM

Este é um ponto obrigatório de ensino.

Pode existir:

```text
JDK 21 rodando Gradle
```

enquanto:

```text
sourceCompatibility = 17
```

ou outro nível.

Não confundir.

---

# 498. 🎥 MOMENTO BOM PARA GRAVAR — JDK versus sourceCompatibility

Excelente conteúdo.

Mostrar:

- `java -version`;
- Gradle JDK;
- `sourceCompatibility`;
- minSdk/compileSdk;
- por que são coisas diferentes.

---

# 499. 🎥 MOMENTO BOM PARA GRAVAR — `==` versus `equals`

Quando Position surgir.

Mostrar:

- duas instâncias;
- `==`;
- `equals`;
- HashSet.

---

# 500. 🎥 MOMENTO BOM PARA GRAVAR — mutable list vazando engine

Mostrar:

```java
getMoves().clear();
```

quebrando estado.

Depois:

cópia/coleção não modificável.

---

# 501. 🎥 MOMENTO BOM PARA GRAVAR — hardcoded string

Mostrar:

- texto Java;
- mover para `strings.xml`;
- tradução/fallback.

---

# 502. 🎥 MOMENTO BOM PARA GRAVAR — ViewModel com Context

Mostrar por que Activity/Context no ViewModel pode criar acoplamento/lifecycle ruim.

---

# 503. 🎥 MOMENTO BOM PARA GRAVAR — Android Lint

Criar/mostrar warning real:

- hardcoded string;
- accessibility;
- API level.

Explicar como ferramenta encontra bug antes da loja.

---

# 504. COMO EXPLICAR EM ENTREVISTA — padrões

> “No Gambitol adotei padrões de Java e Android para manter a engine independente da plataforma. Os value objects tendem a ser imutáveis, as collections internas não são expostas mutavelmente, exceptions não são usadas para fluxo normal de jogadas ilegais, e a UI usa resources em vez de strings e cores hardcoded.”

---

# 505. COMO EXPLICAR EM ENTREVISTA — Java no Android

> “Também diferenciei o JDK que executa o Gradle do nível de linguagem e das APIs disponíveis no Android. Isso evita assumir que, por usar JDK 21 no ambiente, qualquer API ou feature Java 21 está automaticamente disponível no app.”

---

# 506. Checklist de uma nova classe Java

- [ ] nome comunica conceito;
- [ ] package correto;
- [ ] visibilidade mínima;
- [ ] `final` se não foi feita para herança;
- [ ] campos privados;
- [ ] campos final quando apropriado;
- [ ] invariantes protegidas;
- [ ] nullability clara;
- [ ] equals/hashCode se value object;
- [ ] toString útil se ajuda debug;
- [ ] sem dependência Android se está no engine;
- [ ] teste quando comportamento relevante.

---

# 507. Checklist de um novo método

- [ ] nome expressa ação/pergunta;
- [ ] responsabilidade única;
- [ ] parâmetros necessários;
- [ ] nenhum boolean misterioso;
- [ ] null contract definido;
- [ ] retorno claro;
- [ ] exception apropriada;
- [ ] visibilidade mínima;
- [ ] não duplica regra;
- [ ] teste quando crítico.

---

# 508. Checklist de uma collection

- [ ] ordem importa?
- [ ] duplicação importa?
- [ ] caller pode mutar?
- [ ] null é permitido?
- [ ] implementação concreta precisa ser exposta?
- [ ] cópia defensiva é necessária?

---

# 509. Checklist de exception

- [ ] isso é realmente excepcional?
- [ ] caller pode tratar?
- [ ] tipo é específico?
- [ ] catch não está vazio?
- [ ] mensagem ajuda?
- [ ] estamos logando duas vezes?
- [ ] exception não está sendo usada como fluxo normal?

---

# 510. Checklist de ViewModel

- [ ] não guarda Activity;
- [ ] não guarda Fragment;
- [ ] não guarda View;
- [ ] Context só se responsabilidade justificar;
- [ ] não reimplementa engine;
- [ ] expõe estado;
- [ ] recebe ações;
- [ ] lifecycle da UI não vaza.

---

# 511. Checklist de Activity

- [ ] lifecycle;
- [ ] setup de UI;
- [ ] listeners;
- [ ] observers;
- [ ] sem regra de peça;
- [ ] sem persistência pesada;
- [ ] sem estado global escondido.

---

# 512. Checklist de XML

- [ ] ids semânticos;
- [ ] strings em resources;
- [ ] cores em resources;
- [ ] dp/sp corretos;
- [ ] accessibility;
- [ ] sem nesting inútil;
- [ ] formatter aplicado.

---

# 513. Checklist de resource

- [ ] nome lower_snake_case;
- [ ] default existe;
- [ ] tipo correto;
- [ ] não hardcoded duplicado;
- [ ] texto localizável;
- [ ] asset possui licença quando externo.

---

# 514. Checklist de log

- [ ] nível correto;
- [ ] mensagem útil;
- [ ] exception anexada quando precisa;
- [ ] sem segredo;
- [ ] sem spam;
- [ ] não usa android Log no engine.

---

# 515. Checklist de dependência

- [ ] problema real;
- [ ] biblioteca necessária;
- [ ] licença;
- [ ] manutenção;
- [ ] versão fixa;
- [ ] compatível com minSdk;
- [ ] impacto em build;
- [ ] alternativa nativa avaliada;
- [ ] module correto;
- [ ] teste após adicionar.

---

# 516. Checklist de Gradle

- [ ] syntax compatível com Kotlin DSL;
- [ ] versão verificada;
- [ ] source/target não presumidos;
- [ ] JDK compatível;
- [ ] dependency no scope correto;
- [ ] sync/build;
- [ ] diff revisado.

---

# 517. Checklist de Lint

- [ ] errors = 0;
- [ ] warnings relevantes revisados;
- [ ] suppressions justificadas;
- [ ] accessibility revisada;
- [ ] NewApi revisado;
- [ ] deprecated APIs revisadas.

---

# 518. Anti-patterns proibidos por padrão

- wildcard imports;
- public mutable fields;
- static global game state;
- catch vazio;
- `Exception` como fluxo de jogada;
- Android dentro do engine;
- Context dentro do engine;
- hardcoded strings UI;
- hardcoded colors repetidas;
- `textView1`;
- `Utils` como lixeira;
- `Manager` sem responsabilidade;
- `Service` como nome genérico;
- `Impl` exposto sem necessidade;
- mutable collection vazando internals;
- código comentado;
- TODO sem contexto;
- dependência com versão dinâmica;
- Hilt sem necessidade;
- repository sem fonte de dados;
- use case vazio;
- suppress warnings global.

---

# 519. Padrões normativos principais

Após aprovação deste documento:

## DECIDIDO

1. Java e código técnico em inglês.
2. UTF-8.
3. 4 espaços.
4. sem tabs.
5. K&R braces.
6. braces sempre em estruturas de controle.
7. linha alvo de 100 caracteres.
8. UpperCamelCase para classes.
9. lowerCamelCase para métodos/campos/variáveis.
10. UPPER_SNAKE_CASE para constantes reais.
11. wildcard imports proibidos.
12. menor visibilidade necessária.
13. campos privados por padrão.
14. final em fields imutáveis.
15. value objects candidatos a imutabilidade.
16. null proibido salvo contrato explícito.
17. collection vazia em vez de null.
18. collections internas não devem vazar mutabilidade.
19. equals/hashCode juntos.
20. exceptions não são fluxo normal de jogada inválida.
21. catch vazio proibido.
22. Android resources para strings/cores/UI.
23. engine sem Android/AndroidX.
24. Activity fina.
25. ViewModel sem Activity/Fragment/View/Context normal.
26. Lint faz parte da qualidade.
27. Gradle Wrapper obrigatório.
28. dependências justificadas e fixadas.
29. JDK não define sozinho o Java disponível no app.
30. APIs modernas exigem verificação de compatibilidade.

---

# 520. Pontos pendentes

## PENDENTE

- versão efetiva de sourceCompatibility;
- targetCompatibility;
- Java toolchain;
- core library desugaring;
- uso ou não de records;
- tecnologia de state observable Java/Views;
- View Binding;
- formatter automatizado;
- EditorConfig;
- Checkstyle;
- static analysis extra;
- escolha final de nullability annotation Java-only;
- conventions específicas de tests.

---

# 521. Fontes pesquisadas — Google Java Style

## Google Java Style Guide

https://google.github.io/styleguide/javaguide.html

Usado para:

- estrutura de arquivo;
- UTF-8;
- imports;
- braces;
- limite de linha;
- nomes;
- constants;
- Javadoc;
- exceptions;
- finalizers;
- arrays;
- variables.

Verificado em: 2026-08-22.

---

# 522. Fontes pesquisadas — AOSP Java Style

## AOSP Java code style for contributors

https://source.android.com/docs/setup/contribute/code-style

Usado como referência complementar para:

- consistência;
- exceptions;
- Java conventions;
- Javadoc;
- deprecated APIs;
- naming.

Importante:

A própria página esclarece que essas regras são para contribuições à plataforma Android e não são obrigatórias para aplicativos.

Verificado em: 2026-08-22.

---

# 523. Fontes — Java Language Specification

## Java Language Specification SE 21

https://docs.oracle.com/javase/specs/jls/se21/html/

Usado para:

- classes;
- enums;
- igualdade semântica da linguagem;
- packages;
- tipos;
- regras Java.

---

# 524. Fontes — Java records

## Java SE 21 Language Updates

https://docs.oracle.com/en/java/javase/21/language/java-se-language-updates.pdf

Usado para compreender records e linguagem moderna.

A existência de records na linguagem Java não significa adoção automática no Android do Gambitol.

---

# 525. Fontes — Objects

## `java.util.Objects`

https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Objects.html

Usado para:

- `requireNonNull`;
- equals;
- null checks;
- index checks.

---

# 526. Fontes — Optional

## `java.util.Optional`

https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Optional.html

Usado para:

- ausência em retorno;
- Optional não nulo;
- uso primário como return type.

---

# 527. Fontes — Collections/List

## `java.util.List`

https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

## `java.util.Collections`

https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Collections.html

Usado para:

- unmodifiable;
- copyOf;
- contracts de collections.

---

# 528. Fontes — Android Java/JDK

## Java versions in Android builds

https://developer.android.com/build/jdks

Usado para:

- JDK de Gradle;
- Java toolchain;
- sourceCompatibility;
- targetCompatibility;
- compileSdk;
- minSdk;
- desugaring;
- distinção entre JDK e APIs Android.

Verificado em: 2026-08-22.

A documentação atualizada em 2026-08-14 recomenda especificar toolchain e/ou níveis de compatibilidade de forma explícita para maior consistência de build.

---

# 529. Fontes — Android build

## Configure your build

https://developer.android.com/build

Usado para:

- compileSdk;
- minSdk;
- target;
- Java APIs;
- Gradle configuration.

---

# 530. Fontes — Android architecture

## Recommendations for Android architecture

https://developer.android.com/topic/architecture/recommendations

## Recommendations for Android architecture (Views)

https://developer.android.com/topic/architecture/views/recommendations-views

Usado para:

- UDF;
- ViewModel;
- lifecycle;
- dependências;
- Context;
- constructor injection;
- screen-level state.

Verificado em: 2026-08-22.

---

# 531. Fontes — Android resources

## App resources overview

https://developer.android.com/guide/topics/resources/providing-resources

Usado para:

- externalização de resources;
- `R`;
- default resources;
- alternative resources;
- drawables;
- values;
- assets/raw.

---

# 532. Fontes — Strings

## String resources

https://developer.android.com/guide/topics/resources/string-resource

## String resources (Views)

https://developer.android.com/topic/architecture/views/resources/string-resource-views

Usado para:

- `strings.xml`;
- placeholders;
- plurals;
- Java/Views.

---

# 533. Fontes — localização

## Localize your app

https://developer.android.com/guide/topics/resources/localization

Usado para:

- strings fora do código;
- default resources;
- localização;
- fallback.

Verificado em: 2026-08-22.

---

# 534. Fontes — themes

## Styles and themes

https://developer.android.com/develop/ui/views/theming/themes

Usado para:

- diferença style/theme;
- atributos semânticos;
- manutenção visual.

---

# 535. Fontes — Android Lint

## Improve your code with lint checks

https://developer.android.com/studio/write/lint

Usado para:

- qualidade estática;
- problemas estruturais;
- deprecated APIs;
- API level;
- baseline;
- linha de comando.

Verificado em: 2026-08-22.

---

# 536. Fontes — Logcat

## View logs with Logcat

https://developer.android.com/studio/debug/logcat

Usado para:

- níveis de log;
- stack traces;
- diagnóstico.

---

# 537. Hierarquia de aplicação dos padrões

Quando houver conflito:

```text
decisão explícita do projeto
↓
este documento
↓
arquitetura/estrutura
↓
formatter/lint configurado
↓
Google Java Style/AOSP como referência externa
↓
preferência individual
```

Preferência individual é a última.

---

# 538. Regra de mudança do padrão

Se quisermos mudar:

```text
4 espaços → 2 espaços
```

ou:

```text
Java 17 → 21 source
```

não fazer parcialmente.

Processo:

1. justificar;
2. verificar compatibilidade;
3. atualizar documento;
4. configurar ferramenta;
5. aplicar de forma controlada;
6. evitar misturar com feature funcional.

---

# 539. Critério de aprovação deste documento

- [ ] estilo é simples;
- [ ] Java continua prioridade;
- [ ] Android não entra no engine;
- [ ] nullability está clara;
- [ ] imutabilidade não virou dogma;
- [ ] exceptions estão bem definidas;
- [ ] resources estão corretos;
- [ ] ViewModel está coerente com arquitetura;
- [ ] Lint faz parte da qualidade;
- [ ] compatibilidade Java/Android não foi presumida;
- [ ] dependências não são adicionadas por moda;
- [ ] código permanece ensinável.

---

# 540. Resumo executivo

O padrão do Gambitol pode ser resumido assim:

```text
CLARO
↓
CONSISTENTE
↓
ENCAPSULADO
↓
TESTÁVEL
↓
SEM DEPENDÊNCIA ANDROID NO MOTOR
↓
SEM ESTADO GLOBAL
↓
SEM NULL AMBÍGUO
↓
SEM MUTABILIDADE VAZANDO
↓
SEM EXCEPTION ENGOLIDA
↓
SEM STRING/COR HARDCODED NA UI
↓
SEM DEPENDÊNCIA SEM MOTIVO
↓
COM LINT + BUILD + TESTES
```

---

# 541. Frase norteadora

> **O melhor padrão é aquele que reduz a quantidade de coisas que um futuro leitor precisa adivinhar.**

No Gambitol, código deve deixar claro:

- o que representa;
- quem pode alterá-lo;
- quem depende dele;
- qual estado é válido;
- qual camada é responsável;
- como falha;
- como é testado.

Se isso estiver claro, estilo cumpriu sua função.

---

# 542. Próximo documento

Após aprovação:

`07_GIT_WORKFLOW.md`

Ele deverá definir:

- branches;
- staging;
- commits;
- mensagens;
- diff;
- merges;
- push;
- pull;
- tags;
- releases;
- rollback;
- conflitos;
- segurança;
- branches de feature;
- proteção da `main`;
- fluxo Git Bash;
- checkpoints antes de commit.

O documento 06 define:

> **como escrevemos o código.**

O documento 07 definirá:

> **como registramos e integramos sua evolução.**
