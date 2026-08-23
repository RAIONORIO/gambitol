# 05 — REGRAS DO MOTOR DE XADREZ DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `05_REGRAS_DO_MOTOR_DE_XADREZ.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir de forma normativa o comportamento do motor de xadrez do Gambitol e transformar as regras do xadrez padrão em requisitos verificáveis de software  
> **Fonte normativa para:** tabuleiro, posição inicial, turnos, ataques, movimentos, capturas, legalidade, xeque, xeque-mate, afogamento, roque, en passant, promoção, empates, repetição, regra dos 50/75 movimentos, estados finais, histórico mínimo necessário e critérios de validação do motor  
> **Não cobre em detalhe:** arquitetura física do código, packages, nomes definitivos de classes, UI, animações, cronômetro visual, multiplayer, IA, workflow Git ou publicação  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`  
> **Autoridade externa principal:** FIDE Laws of Chess  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo deste documento

Este documento transforma xadrez em requisitos de software.

Dizer:

> “o Gambitol terá xadrez completo”

não é especificação suficiente.

O motor precisa saber exatamente:

- qual é o estado válido de uma partida;
- quem pode jogar;
- o que significa uma casa atacada;
- quando um movimento é apenas geometricamente possível;
- quando esse movimento é legal;
- quando uma captura ocorre;
- quando o rei está em xeque;
- quando existe xeque-mate;
- quando existe afogamento;
- quando o roque pode e não pode acontecer;
- por quanto tempo existe o direito de en passant;
- como funciona promoção;
- quais estados precisam ser guardados para repetição;
- quando um empate é apenas reclamável;
- quando um empate é automático;
- quando a partida terminou;
- quais regras físicas de torneio não pertencem ao motor digital.

A função deste arquivo é impedir que regras sejam implementadas “de memória”.

---

# 2. Autoridade normativa

## DECIDIDO

A principal autoridade externa para regras do xadrez padrão será a:

**FIDE Laws of Chess**

Página oficial consultada:

https://handbook.fide.com/chapter/e012023

A página oficial atualmente publicada pela FIDE é identificada como regras em vigor a partir de 1º de janeiro de 2023.

Também foi consultada a Rules Commission da FIDE:

https://rcc.fide.com/documentation/

Verificado em: **2026-08-22**.

---

# 3. Regra de atualização

As regras FIDE podem ser revisadas.

Antes de:

- publicar uma versão que alegue fidelidade às regras;
- alterar lógica de empate;
- alterar roque;
- alterar repetição;
- alterar regras de tempo;

consultar novamente a fonte oficial.

Este documento não torna uma edição histórica da FIDE eterna.

---

# 4. Escopo do motor

## DECIDIDO

O motor inicial será de:

> **xadrez padrão/ortodoxo**

Não fazem parte da primeira especificação:

- Chess960;
- Crazyhouse;
- Atomic Chess;
- Horde;
- King of the Hill;
- Three-check;
- variantes regionais;
- variantes personalizadas.

Chess960 existe nas regras FIDE como apêndice, mas não será ativado silenciosamente no Gambitol.

Se uma variante entrar no produto no futuro, ela deverá possuir especificação separada.

---

# 5. Regras de tabuleiro físico versus regras de motor digital

As Laws of Chess da FIDE foram escritas principalmente para jogo sobre tabuleiro físico.

Elas incluem regras sobre:

- tocar peças;
- usar uma mão;
- relógio físico;
- árbitro;
- folha de anotação;
- peças deslocadas;
- conduta;
- aparelhos eletrônicos;
- penalidades.

Essas regras não devem ser copiadas mecanicamente para um aplicativo.

O motor digital deve implementar principalmente:

- posição;
- turno;
- movimento;
- ataque;
- legalidade;
- captura;
- estados finais;
- empate;
- tempo quando cronômetro fizer parte da modalidade.

---

# 6. Regra de toque físico

## NÃO APLICÁVEL AO MOTOR DIGITAL

A regra física de “peça tocada deve ser movida”, quando aplicável em torneios presenciais, não será regra central do motor.

No Gambitol:

```text
toque para selecionar
≠
movimento comprometido
```

A UI poderá permitir:

- selecionar;
- desmarcar;
- trocar seleção;
- visualizar movimentos.

O movimento só deve ser aplicado quando houver comando completo e legal.

---

# 7. Jogada ilegal em aplicativo

Em torneio físico, uma jogada ilegal pode gerar penalidade.

No Gambitol local:

## DECIDIDO COMO PRINCÍPIO

O motor deve impedir que a posição chegue a um estado ilegal por entrada normal do usuário.

Fluxo:

```text
usuário solicita movimento
↓
motor valida
↓
ilegal → rejeita
legal → aplica
```

Não permitir movimento ilegal e depois “punir” o jogador.

---

# 8. Princípio mais importante do motor

> **O rei nunca é capturado.**

A partida termina quando um rei está em xeque e não existe movimento legal capaz de eliminar essa condição.

Logo:

- xeque-mate encerra;
- capturar o rei não é uma jogada legal;
- qualquer API de captura deve impedir rei como alvo de captura.

---

# 9. Tabuleiro

## DECIDIDO

O tabuleiro possui:

```text
8 arquivos × 8 fileiras = 64 casas
```

Arquivos:

```text
a b c d e f g h
```

Fileiras:

```text
1 2 3 4 5 6 7 8
```

Cada casa é identificada unicamente por:

```text
arquivo + fileira
```

Exemplos:

```text
a1
e4
h8
```

A FIDE denomina colunas verticais de files e linhas horizontais de ranks.

---

# 10. Orientação lógica do tabuleiro

## DECIDIDO

A orientação lógica do motor é independente da orientação visual.

Convencionaremos semanticamente:

```text
brancas avançam em direção à fileira 8
pretas avançam em direção à fileira 1
```

A UI poderá desenhar o tabuleiro:

- com brancas embaixo;
- com pretas embaixo;
- girado;

sem alterar a lógica do motor.

---

# 11. Representação interna de coordenadas

## PENDENTE DE IMPLEMENTAÇÃO

O motor poderá usar internamente:

- índice 0..63;
- linha/coluna 0..7;
- objeto Position;
- outra estrutura aprovada.

Mas deve existir conversão inequívoca entre:

```text
coordenada interna
↔
notação a1-h8
```

A representação interna não pode alterar a semântica do xadrez.

---

# 12. Cor das casas

A alternância visual de casas claras/escuras não interfere diretamente nas regras de movimento, exceto em raciocínios como:

- bispos permanecem sempre na mesma cor de casa;
- certas posições mortas dependem de cores de bispos.

O motor não precisa guardar a cor da casa se puder derivá-la da coordenada.

---

# 13. Posição inicial

## DECIDIDO

Brancas:

```text
1ª fileira:
a1 torre
b1 cavalo
c1 bispo
d1 dama
e1 rei
f1 bispo
g1 cavalo
h1 torre

2ª fileira:
a2-h2 peões
```

Pretas:

```text
8ª fileira:
a8 torre
b8 cavalo
c8 bispo
d8 dama
e8 rei
f8 bispo
g8 cavalo
h8 torre

7ª fileira:
a7-h7 peões
```

Brancas jogam primeiro.

Depois os turnos alternam.

---

# 14. Quantidade inicial de peças

Cada lado começa com:

```text
1 rei
1 dama
2 torres
2 bispos
2 cavalos
8 peões
```

Total:

```text
16 peças por lado
32 peças
```

Após promoção, a quantidade por tipo pode exceder a inicial.

---

# 15. A dama na própria cor

Na posição inicial:

```text
dama branca → d1
dama preta → d8
```

Esse fato decorre da posição oficial.

Não usar a frase mnemônica como algoritmo de montagem.

A posição inicial deve ser criada explicitamente e testada.

---

# 16. Turno

## DECIDIDO

Estado da partida precisa conter:

```text
sideToMove
```

com valor equivalente a:

```text
WHITE
BLACK
```

Após um movimento legal concluído:

```text
WHITE → BLACK
BLACK → WHITE
```

Exceção:

se o movimento encerra a partida, nenhum novo movimento deve ser aceito.

---

# 17. Um movimento por turno

Cada turno representa uma jogada.

Roque é uma única jogada, embora mova:

- rei;
- torre.

Promoção também pertence à mesma jogada do peão.

En passant também é uma única jogada.

---

# 18. Ocupação de uma casa

Uma casa pode conter:

```text
nenhuma peça
```

ou:

```text
uma peça
```

Nunca duas.

---

# 19. Movimento para casa ocupada por peça própria

## ILEGAL

Nenhuma peça pode terminar seu movimento numa casa ocupada por peça da mesma cor.

---

# 20. Captura

Uma captura normal ocorre quando:

```text
peça do jogador
↓
move legalmente para casa ocupada pelo adversário
↓
peça adversária é removida
↓
peça atacante ocupa destino
```

Exceção especial:

```text
en passant
```

onde a peça capturada não está na casa de destino.

---

# 21. Captura do rei

## PROIBIDA

O rei adversário nunca deve ser removido do tabuleiro como resultado de uma jogada.

Se um movimento deixa o adversário sem resposta legal enquanto seu rei está atacado:

```text
CHECKMATE
```

A partida termina antes de qualquer “captura do rei”.

---

# 22. Conceito de ataque

Uma casa é atacada por uma peça quando aquela peça exerce ataque sobre ela segundo a geometria e regras relevantes do tipo de peça.

Esse conceito é diferente de:

```text
movimento legal completo
```

Isso é crucial.

---

# 23. Peça cravada ainda pode atacar uma casa

## CONCEITO CRÍTICO

A FIDE considera que uma peça pode atacar uma casa mesmo quando ela própria não poderia mover-se para lá por deixar o próprio rei em xeque.

Isso afeta especialmente:

- casas que um rei não pode ocupar;
- avaliação de casas atacadas;
- roque.

Portanto:

> **não definir “casa atacada” simplesmente como “existe um movimento legal do adversário para a casa”.**

Ataque e movimento legal não são a mesma coisa.

---

# 24. Reis adjacentes

Dois reis não podem ocupar casas adjacentes.

Por quê:

cada rei ataca as casas adjacentes.

Logo, mover um rei para perto do outro colocaria o próprio rei sob ataque.

---

# 25. Movimento pseudo-legal

## CONCEITO DE SOFTWARE

Um movimento é **pseudo-legal** quando respeita:

- geometria da peça;
- ocupação;
- caminho;
- regras especiais básicas;

mas ainda não foi confirmada a segurança do próprio rei.

Exemplo:

uma torre cravada pode possuir movimento geometricamente válido que expõe seu rei.

Esse movimento é pseudo-legal, mas não legal.

A terminologia é comum em programação de motores de xadrez.

Fonte técnica consultada:

https://www.chessprogramming.org/Pseudo-Legal_Move

---

# 26. Movimento legal

## DECIDIDO

Um movimento é legal somente se:

1. origem contém peça do jogador do turno;
2. destino está no tabuleiro;
3. destino não contém peça própria;
4. movimento respeita regras da peça;
5. caminho está livre quando necessário;
6. regra especial é satisfeita quando aplicável;
7. o movimento não deixa o próprio rei sob ataque.

---

# 27. Validação em duas etapas

## PROPOSTO

É didaticamente e tecnicamente útil pensar em:

```text
PSEUDO-LEGAL
↓
SIMULAR/APLICAR TEMPORARIAMENTE
↓
REI PRÓPRIO ESTÁ EM XEQUE?
├── SIM → ilegal
└── NÃO → legal
```

Essa abordagem reduz duplicação de lógica de cravadas.

Não é a única implementação possível.

---

# 28. Movimento nulo

## FORA DA JOGABILIDADE

O jogador não pode “passar a vez”.

Movimento nulo pode existir futuramente como técnica interna de IA, mas não é movimento legal de xadrez para usuário.

---

# 29. Bispo

O bispo move:

```text
qualquer quantidade de casas
em diagonal
```

desde que:

- permaneça dentro do tabuleiro;
- não atravesse peças;
- destino não possua peça própria.

---

# 30. Geometria do bispo

Para origem `(r1,c1)` e destino `(r2,c2)`:

```text
abs(r2-r1) == abs(c2-c1)
```

é condição geométrica de diagonal.

Ainda é preciso verificar o caminho.

---

# 31. Caminho do bispo

Todas as casas estritamente entre origem e destino precisam estar vazias.

A primeira peça encontrada bloqueia o raio.

Se for adversária e estiver exatamente no destino:

captura possível.

Se for própria:

destino ilegal.

---

# 32. Bispo não pula

## DECIDIDO

Bispo não atravessa peça de nenhuma cor.

---

# 33. Cor do bispo

Um bispo nunca muda de cor de casa em movimentos legais.

Isso pode virar teste de propriedade futuramente.

---

# 34. Torre

A torre move:

```text
qualquer quantidade de casas
na mesma fileira
ou no mesmo arquivo
```

sem atravessar peças.

---

# 35. Geometria da torre

Movimento pseudo-legal exige:

```text
mesma linha XOR mesma coluna
```

e origem diferente do destino.

---

# 36. Caminho da torre

Todas as casas intermediárias devem estar vazias.

---

# 37. Torre e roque

A torre também participa do roque.

Isso exige estado adicional:

> direito de roque não pode ser inferido apenas pela posição atual.

Uma torre em `h1` pode ter saído e voltado.

Nesse caso:

```text
não recupera direito de roque
```

---

# 38. Dama

A dama combina movimentos de:

```text
torre + bispo
```

Portanto move:

- fileira;
- arquivo;
- diagonal;

sem atravessar peças.

---

# 39. Dama não pula

Mesma regra de peças deslizantes.

---

# 40. Cavalo

O cavalo move em padrão:

```text
2 em um eixo
1 no outro
```

Em coordenadas:

```text
(abs(dr), abs(dc)) ∈ {(2,1),(1,2)}
```

---

# 41. Cavalo pula peças

## DECIDIDO

O cavalo é a peça normal que ignora ocupação das casas intermediárias.

Somente:

- origem;
- destino;

importam para ocupação.

---

# 42. Destino do cavalo

Se vazio:

movimento quieto.

Se adversário:

captura.

Se próprio:

ilegal.

---

# 43. Rei

O rei normalmente move uma casa em qualquer direção:

```text
horizontal
vertical
diagonal
```

mas não pode terminar sob ataque.

---

# 44. Geometria normal do rei

```text
max(abs(dr), abs(dc)) == 1
```

para movimento comum.

Roque é exceção.

---

# 45. Segurança do rei

Um movimento do rei exige:

- destino não ocupado por peça própria;
- destino não atacado pelo adversário;
- todas as demais regras normais.

---

# 46. Rei não pode capturar peça protegida

Mesmo que a peça adversária esteja em uma casa alcançável:

se a casa de destino continuar atacada após a captura:

```text
captura ilegal
```

---

# 47. Rei nunca entra em xeque

## DECIDIDO

Nenhum movimento legal pode:

- colocar o próprio rei em xeque;
- manter o próprio rei em xeque após o movimento.

---

# 48. Peão

O peão possui regras diferentes para:

- avanço;
- captura;
- avanço inicial duplo;
- en passant;
- promoção.

Não modelar peão como apenas “peça que anda para frente”.

---

# 49. Direção do peão branco

Semanticamente:

```text
rank aumenta
```

Exemplo:

```text
e2 → e3
```

---

# 50. Direção do peão preto

Semanticamente:

```text
rank diminui
```

Exemplo:

```text
e7 → e6
```

---

# 51. Avanço simples do peão

O peão pode avançar uma casa para frente quando:

```text
casa destino está vazia
```

Peão não captura para frente.

---

# 52. Peão bloqueado

Se a casa imediatamente à frente está ocupada:

```text
não pode avançar
```

Mesmo se for adversário.

---

# 53. Avanço inicial duplo

No primeiro movimento do peão, pode avançar duas casas se:

- estiver na posição de início correspondente;
- casa intermediária estiver vazia;
- casa destino estiver vazia.

---

# 54. Fileiras iniciais

Branco:

```text
rank 2
```

Preto:

```text
rank 7
```

Como um peão não anda para trás, estar na fileira inicial é equivalente, em posições alcançadas legalmente, a não ter avançado antes.

Mesmo assim, posições importadas futuramente devem preservar coerência.

---

# 55. Peão não pula no avanço duplo

Ambas as casas precisam estar livres.

---

# 56. Captura normal do peão branco

Uma casa:

```text
diagonal à frente
```

ocupada por peça adversária.

---

# 57. Captura normal do peão preto

Uma casa diagonal na direção das pretas.

---

# 58. Peão não captura casa vazia diagonalmente

Exceto:

```text
en passant
```

---

# 59. Peão não anda para trás

Nenhuma exceção.

---

# 60. En passant — definição de estado

Para suportar en passant, posição precisa guardar informação sobre o último avanço duplo relevante.

Não basta olhar o tabuleiro atual.

---

# 61. Condições do en passant

Uma captura en passant é possível somente quando:

1. o adversário acabou de mover um peão duas casas a partir da posição inicial;
2. esse peão terminou lado a lado com o peão capturador;
3. o peão capturador está na fileira adequada;
4. a captura ocorre imediatamente no próximo lance;
5. o destino corresponde à casa que o peão adversário teria atravessado;
6. após a captura, o próprio rei não fica em xeque.

---

# 62. Janela temporal do en passant

## DECIDIDO

O direito existe por:

```text
um único lance do adversário
```

Se outro movimento é jogado:

```text
direito expira
```

---

# 63. Remoção no en passant

No en passant:

```text
peão capturador → casa vazia diagonal
peão capturado → removido da casa ao lado
```

Isso é diferente da captura normal.

---

# 64. En passant e xeque descoberto

## ARMADILHA CRÍTICA

Ao testar legalidade de en passant, é necessário remover:

- peão que move;
- peão capturado;

antes de verificar ataque ao rei.

A remoção simultânea pode abrir uma linha de:

- torre;
- bispo;
- dama.

Por isso en passant é um caso clássico de bug em engines.

---

# 65. Estado de en passant para repetição

A FIDE define igualdade de posições considerando os movimentos possíveis.

Logo:

duas posições visualmente idênticas podem ser diferentes se em uma delas ainda existir uma captura en passant possível.

O mecanismo de repetição deve considerar isso.

---

# 66. Promoção — quando acontece

Quando um peão chega à fileira mais distante de sua origem:

Branco:

```text
rank 8
```

Preto:

```text
rank 1
```

ele deve ser promovido como parte da própria jogada.

---

# 67. Promoção obrigatória

## DECIDIDO

Não existe opção:

> “continuar como peão”.

A promoção faz parte do movimento.

---

# 68. Peças possíveis na promoção

Pode promover para:

```text
DAMA
TORRE
BISPO
CAVALO
```

da mesma cor.

Não pode promover para:

```text
REI
PEÃO
```

---

# 69. Underpromotion

Promoção não precisa ser para dama.

Motor deve aceitar:

- torre;
- bispo;
- cavalo.

Mesmo que UI destaque dama como escolha comum.

---

# 70. Promoção independente de capturas anteriores

Não é necessário que a peça escolhida tenha sido capturada.

Uma posição pode ter:

- duas damas;
- três damas;
- múltiplos cavalos;
- múltiplas torres;

se promoções produzirem isso.

---

# 71. Promoção e captura

Um peão pode capturar na última fileira e promover na mesma jogada.

Exemplo conceitual:

```text
g7xh8=Q
```

A captura e promoção formam um único movimento.

---

# 72. Promoção e efeito imediato

A nova peça passa a exercer seus ataques imediatamente.

Uma promoção pode:

- dar xeque;
- dar xeque-mate;
- bloquear ataque;
- criar ataque.

---

# 73. API de promoção

## PROPOSTO

O motor não deve aplicar o peão na última fileira e deixar uma posição intermediária inválida esperando a UI.

Preferir que a jogada final contenha:

```text
origem
destino
promotionChoice
```

Se escolha ainda não foi feita:

a UI pede escolha antes de confirmar a mutação do estado.

---

# 74. Roque — visão geral

Roque é uma única jogada que movimenta:

- rei;
- uma torre.

Existem:

```text
roque do lado do rei
roque do lado da dama
```

---

# 75. Roque branco lado do rei

Posição inicial relevante:

```text
rei e1
torre h1
```

Resultado:

```text
rei g1
torre f1
```

---

# 76. Roque branco lado da dama

Resultado padrão:

```text
rei c1
torre d1
```

com rook inicial em `a1`.

---

# 77. Roque preto lado do rei

```text
rei e8 → g8
torre h8 → f8
```

---

# 78. Roque preto lado da dama

```text
rei e8 → c8
torre a8 → d8
```

---

# 79. Direito de roque do rei

Se o rei já se moveu em qualquer momento:

```text
ambos os direitos de roque dessa cor são perdidos definitivamente
```

Mesmo que volte à casa original.

---

# 80. Direito de roque da torre

Se uma torre já se moveu:

```text
direito de roque com aquela torre é perdido definitivamente
```

Mesmo que ela volte.

---

# 81. Torre capturada na casa inicial

Se a torre elegível é capturada:

obviamente o roque daquele lado torna-se impossível.

O estado de direitos deve ser atualizado.

---

# 82. Torre promovida não restaura roque

Uma nova torre em `a1`, `h1`, `a8` ou `h8` não recupera direito perdido.

Direito depende da história do rei/torre original, não apenas do tipo de peça presente.

---

# 83. Casas entre rei e torre

Para roque:

todas as casas entre rei e a torre correspondente precisam estar vazias.

---

# 84. Rei em xeque não pode rocar

## DECIDIDO

A casa inicial do rei não pode estar atacada.

---

# 85. Rei não pode atravessar xeque

A casa intermediária cruzada pelo rei não pode estar atacada.

---

# 86. Rei não pode terminar em xeque

A casa final também não pode estar atacada.

---

# 87. Torre pode estar atacada

## ARMADILHA COMUM

A regra de ataque do roque se aplica às casas do rei:

- origem;
- passagem;
- destino.

A torre estar atacada não proíbe o roque por si só.

---

# 88. Casa atravessada pela torre pode estar atacada

Isso também não impede roque.

O que importa é a segurança do rei.

---

# 89. Roque lado da dama e b-file

Para roque longo, todas as peças entre rei e torre precisam sair.

No padrão:

```text
b1, c1, d1
```

precisam estar vazias para brancas.

Mas apenas:

```text
e1, d1, c1
```

são relevantes para ataque ao rei.

`b1` pode estar atacada sem impedir o roque.

---

# 90. Roque é movimento do rei para fins de entrada

## PROPOSTO

Na API, roque pode ser representado como movimento do rei:

```text
e1 → g1
e1 → c1
e8 → g8
e8 → c8
```

com flag/tipo especial.

Ao aplicar:

motor move também a torre.

Essa convenção é comum em software de xadrez.

---

# 91. Xeque

O rei está em xeque quando sua casa está atacada por uma ou mais peças adversárias.

---

# 92. Xeque não encerra partida

Xeque apenas restringe as respostas.

O jogador em xeque deve fazer um movimento legal que elimine a ameaça.

---

# 93. Formas gerais de responder ao xeque

Dependendo da posição:

- mover o rei;
- capturar peça atacante;
- bloquear linha de ataque.

Nem toda opção existe para todo tipo de xeque.

---

# 94. Xeque por cavalo

Não pode ser bloqueado.

Resposta possível:

- mover rei;
- capturar cavalo se legal;
- outra ação que elimine ataque, quando aplicável.

---

# 95. Xeque por peça deslizante

Pode ser bloqueável se houver casas entre atacante e rei.

---

# 96. Xeque duplo

Quando duas peças atacam o rei simultaneamente:

em geral, somente movimento do rei consegue resolver, porque uma única ação não pode bloquear/capturar ambas, salvo pela própria movimentação do rei.

O motor não precisa codificar essa regra como exceção se a geração legal completa já funcionar.

---

# 97. Descoberta de xeque

Mover uma peça pode abrir linha de ataque de outra.

Isso mostra por que:

```text
ataque do tabuleiro após o movimento
```

precisa ser recalculado/avaliado corretamente.

---

# 98. Peça cravada

Uma peça é absolutamente cravada quando mover-se de determinada forma expõe o próprio rei.

O movimento que expõe rei é ilegal.

Não é obrigatório manter uma flag “pinned”.

Pode:

- detectar previamente;
- ou gerar/simular e rejeitar.

---

# 99. Cravada parcial

Uma peça cravada pode ainda possuir alguns movimentos legais ao longo da linha de cravada.

Logo:

```text
pinned = cannot move
```

é simplificação incorreta.

---

# 100. Xeque-mate

## DECIDIDO

Existe xeque-mate quando:

```text
rei do jogador do turno está em xeque
E
não existe nenhum movimento legal
```

Resultado:

vitória do adversário.

---

# 101. Ordem de avaliação: mate versus ausência de movimentos

Se não há movimentos legais:

```text
rei em xeque?
├── SIM → CHECKMATE
└── NÃO → STALEMATE
```

---

# 102. Afogamento / stalemate

Existe quando:

```text
jogador da vez não está em xeque
E
não possui movimento legal
```

Resultado:

```text
EMPATE
```

---

# 103. Posição morta / dead position

A FIDE define empate automático quando nenhuma sequência possível de movimentos legais permite que qualquer lado dê mate.

Isso é mais preciso que a expressão informal:

> “material insuficiente”.

---

# 104. “Material insuficiente” não é sinônimo completo de dead position

## CONCEITO CRÍTICO

Muitos programas usam regras simplificadas de material.

Mas o critério normativo é:

> existe ou não alguma sequência legal que possa produzir xeque-mate?

Portanto, a implementação precisa tomar cuidado para não declarar empate automaticamente em posição onde mate ainda é legalmente possível com cooperação.

---

# 105. Casos obviamente mortos

Exemplos clássicos:

```text
K vs K
K+B vs K
K+N vs K
```

Nessas posições não existe sequência legal capaz de produzir mate.

---

# 106. Bispo contra bispo

Não assumir automaticamente:

```text
K+B vs K+B = sempre dead
```

A cor dos bispos e a posição podem importar para o critério de possibilidade de mate.

A implementação de dead position deve ser validada com casos concretos.

---

# 107. Dois cavalos versus rei

## ARMADILHA

```text
K+N+N vs K
```

não é simplesmente equivalente a “material sem mate”.

O lado com dois cavalos não consegue forçar mate contra defesa perfeita, mas existe posição/sequência cooperativa em que mate pode ocorrer.

Logo, sob o critério FIDE de dead position, isso não deve ser automaticamente tratado como impossível de mate apenas por material.

Essa distinção entre:

```text
não consegue forçar
```

e:

```text
não pode ocorrer por nenhuma sequência legal
```

é fundamental.

---

# 108. Regra do motor para dead position

## PROPOSTO

Implementar inicialmente apenas casos matematicamente seguros e bem testados.

Não usar tabela simplificada agressiva que gere falso empate.

Expandir com testes e referência.

---

# 109. Repetição de posição

A repetição não depende de repetir a mesma sequência de jogadas.

O que importa é a mesma posição segundo a definição legal.

---

# 110. Mesma posição — requisitos

Para repetição, devem coincidir:

1. jogador da vez;
2. peças do mesmo tipo e cor nas mesmas casas;
3. direitos de movimentos relevantes equivalentes.

Em particular:

- direitos de roque;
- possibilidade real de en passant;

afetam a identidade da posição.

---

# 111. Board arrangement sozinho não basta

Duas posições podem possuir tabuleiro visual igual e não serem iguais para repetição.

Exemplo:

um rei saiu e voltou.

Visualmente igual.

Mas:

```text
direito de roque foi perdido
```

Logo, posição não é equivalente à antiga.

---

# 112. Repetição e en passant

Se em uma ocorrência uma captura en passant está disponível e em outra não:

não são a mesma posição para a regra de repetição.

---

# 113. Três repetições

## FIDE

Quando a mesma posição ocorre pela terceira vez nas condições da regra:

o jogador com a vez pode possuir direito de **reclamar** empate.

Isso não é automaticamente a mesma coisa que:

```text
motor encerra a partida sozinho
```

---

# 114. Cinco repetições

## DECIDIDO COMO REGRA FIDE

Quando a mesma posição ocorreu pelo menos cinco vezes nas condições relevantes:

```text
empate automático
```

sem depender de reclamação.

---

# 115. Trêsfold versus fivefold

## CONCEITO CRÍTICO

```text
3 repetições → reclamável
5 repetições → automático
```

Não programar “3 = encerra imediatamente” se o objetivo é fidelidade FIDE.

---

# 116. Interface de claim

## PROPOSTO

O motor deverá conseguir expor:

```text
canClaimThreefoldRepetition
```

ou conceito equivalente.

A UI pode mostrar:

```text
Reclamar empate
```

Nome concreto não definido.

---

# 117. Repetição prestes a ocorrer

A regra FIDE também permite reclamação quando o jogador declara um movimento que produzirá a terceira ocorrência.

## PENDENTE DE UX

Um app pode:

- suportar claim antes do movimento;
- ou simplificar interação de modo explicitamente documentado.

Se a meta for fidelidade plena, o motor deve possuir capacidade de avaliar:

```text
se este movimento legal produziria terceira ocorrência
```

---

# 118. Histórico para repetição

O motor precisa guardar informação suficiente das posições anteriores.

Não é necessário guardar screenshots.

Possibilidades:

- snapshots;
- chave de posição;
- hash;
- FEN normalizada;
- estrutura própria.

Decisão técnica futura.

---

# 119. Hash de posição

## FUTURO / CANDIDATO

Zobrist hashing é técnica comum em engines para identificar posições.

Não é regra do xadrez.

Pode ser usado para performance/repetição.

Se usado, colisão precisa ser entendida e tratada conforme nível de confiabilidade desejado.

---

# 120. Regra dos 50 movimentos

A regra FIDE de 50 movimentos é uma condição reclamável.

O contador é baseado em:

```text
meios-lances desde último:
- movimento de peão
- captura
```

50 jogadas por lado:

```text
100 meios-lances
```

---

# 121. Reset do halfmove clock

Zerar quando:

- qualquer peão se move;
- qualquer captura ocorre.

Isso inclui:

- captura en passant;
- promoção, pois é movimento de peão;
- captura com promoção.

---

# 122. Não zeram o halfmove clock

Por si só:

- movimento de rei;
- movimento de torre;
- perda de direito de roque;
- roque;

não zeram o contador, desde que não envolvam captura ou peão.

---

# 123. 50 movimentos — reclamação

## DECIDIDO COMO REGRA FIDE

Ao atingir condição de 50 jogadas de cada jogador sem captura ou peão:

```text
empate pode ser reclamado
```

Não necessariamente automático.

---

# 124. 75 movimentos

## DECIDIDO COMO REGRA FIDE

Após série de 75 jogadas por lado sem:

- movimento de peão;
- captura;

o empate é automático.

Equivale a:

```text
150 meios-lances
```

---

# 125. Xeque-mate tem precedência sobre 75 movimentos

Se o lance que completa a condição de 75 movimentos também dá xeque-mate:

```text
CHECKMATE vence a regra automática de 75 movimentos
```

A ordem de avaliação do motor deve refletir isso.

---

# 126. Ordem recomendada após movimento

## PROPOSTO

Após aplicar um movimento legal:

1. resolver promoção/captura/especiais;
2. atualizar estado;
3. verificar xeque-mate;
4. verificar stalemate;
5. verificar dead position;
6. verificar cinco repetições;
7. verificar 75 movimentos;
8. atualizar flags de empate reclamável;
9. disponibilizar novo estado.

Detalhes podem variar, mas mate deve ter precedência quando aplicável.

---

# 127. Empate por acordo

A FIDE permite acordo de empate durante a partida sob regras de evento.

## PROPOSTO PARA GAMBITOL LOCAL

Poderá existir ação:

```text
Oferecer empate
```

e:

```text
Aceitar
```

Mas isso pertence ao produto/UI.

O motor pode representar resultado:

```text
DRAW_BY_AGREEMENT
```

---

# 128. Restrição inicial da FIDE ao acordo

A regra oficial indica que, na regra geral, ambos precisam ter feito pelo menos um movimento para o empate por acordo do Artigo 5.2.3.

Se a feature entrar:

seguir essa regra ou documentar deliberadamente eventual simplificação.

---

# 129. Abandono / resign

## PROPOSTO

O Gambitol pode oferecer:

```text
Abandonar partida
```

Resultado normal:

vitória do adversário.

---

# 130. Abandono em posição onde adversário não pode dar mate

A regra FIDE possui nuance:

se o adversário não poderia dar mate por nenhuma sequência legal:

o resultado é empate mesmo após resignação.

Se a feature de abandono for implementada com fidelidade FIDE:

essa condição deve ser considerada.

---

# 131. Tempo

## FUTURO / PARCIALMENTE FORA DO MOTOR BASE

O cronômetro visual aparece no conceito do Gambitol.

Mas as regras exatas do timer ainda precisam ser definidas.

O motor de xadrez sem relógio deve funcionar independentemente.

---

# 132. Perda por tempo

Quando modalidade com relógio for implementada, regra FIDE geral:

se o tempo de um jogador termina:

normalmente perde.

Mas existe exceção.

---

# 133. Tempo e impossibilidade de mate

Se o adversário não pode dar xeque-mate por qualquer sequência de movimentos legais:

a queda de tempo resulta em:

```text
EMPATE
```

não vitória.

Essa regra deverá entrar no módulo de relógio/resultado quando cronômetro competitivo for aprovado.

---

# 134. Relógio não é TextView

A UI exibirá tempo.

Mas o estado lógico do relógio não pode ser o texto visual.

---

# 135. Modalidades de tempo

## PENDENTE

Possíveis futuras:

- sem relógio;
- tempo fixo;
- incremento Fischer;
- delay;
- presets.

Não incluir na regra base sem decisão.

---

# 136. Posição ilegal

A FIDE chama de ilegal uma posição que não pode ter sido alcançada por sequência de movimentos legais.

No fluxo normal do Gambitol:

> motor nunca deve produzir posição ilegal.

---

# 137. Importação futura de posição

Se futuramente permitirmos FEN customizada:

precisaremos decidir:

- aceitar qualquer sintaxe válida?
- validar legalidade histórica?
- aceitar posição de análise ilegal?

Isso é diferente de jogar partida normal.

---

# 138. Estado mínimo de uma posição jogável

## PROPOSTO

O motor precisa conhecer pelo menos:

```text
board placement
side to move
castling rights
en passant state
halfmove clock
histórico/chaves para repetição
game status
```

Pode também manter:

```text
fullmove number
move history
```

---

# 139. Por que board placement não basta

Exemplo:

mesmas peças nas mesmas casas.

Mas estados podem diferir por:

- turno;
- roque;
- en passant;
- contador;
- repetição.

---

# 140. FEN como referência conceitual

Forsyth-Edwards Notation (FEN) representa posição com campos para:

1. peças;
2. lado a mover;
3. direitos de roque;
4. alvo de en passant;
5. halfmove clock;
6. número do movimento completo.

Isso é uma boa lista de cheque mental para estado de posição.

Fonte técnica consultada:

https://www.chessprogramming.org/Forsyth-Edwards_Notation

## IMPORTANTE

Usar FEN internamente não está decidido.

---

# 141. Diferença entre FEN en passant e repetição

Algumas convenções de FEN registram casa-alvo após avanço duplo mesmo quando nenhuma captura en passant é realmente possível.

Já a regra de repetição da FIDE considera equivalência de movimentos possíveis.

Se FEN for usada para chave de repetição:

normalização cuidadosa será necessária.

---

# 142. Fullmove number

Não é necessário para decidir movimento legal.

É útil para:

- notação;
- export;
- histórico;
- debugging.

Convencionalmente começa em 1 e incrementa após movimento das pretas em FEN.

---

# 143. Move history

O histórico pode ser necessário para:

- en passant;
- repetição;
- notação;
- replay;
- undo futuro.

Mas não necessariamente precisamos consultar toda a lista para cada regra se o estado já guarda informações derivadas.

---

# 144. Estado derivado versus fundamental

Exemplo:

```text
isKingInCheck
```

pode ser derivado do tabuleiro.

Não precisa obrigatoriamente ser armazenado de forma persistente.

Evitar duplicar estado se puder divergir.

---

# 145. Direitos de roque são estado fundamental histórico

Não podem ser derivados apenas da posição atual.

Devem ser guardados ou reconstructíveis por histórico confiável.

---

# 146. En passant é estado temporário histórico

Também não é derivável apenas da disposição visual.

---

# 147. Halfmove clock é histórico condensado

Precisa ser armazenado/atualizado.

---

# 148. Repetição exige contexto histórico

Uma posição isolada não sabe quantas vezes apareceu.

---

# 149. Status da partida

## PROPOSTO

O motor deve possuir estado equivalente a:

```text
IN_PROGRESS
CHECKMATE
STALEMATE
DRAW_DEAD_POSITION
DRAW_FIVEFOLD_REPETITION
DRAW_SEVENTY_FIVE_MOVE
DRAW_AGREEMENT
RESIGNATION
TIMEOUT
```

Além de possíveis estados:

```text
CLAIMABLE_THREEFOLD
CLAIMABLE_FIFTY_MOVE
```

Mas estes talvez sejam flags, não status final.

Nomes concretos ficam para modelagem.

---

# 150. Claimable não é terminal

## DECIDIDO COMO CONCEITO

Ter direito a reclamar empate não significa:

```text
partida encerrada
```

Jogador pode continuar.

Logo:

```text
terminal status
```

e:

```text
claim availability
```

devem ser conceitualmente separados.

---

# 151. Game over

Após resultado terminal:

motor deve rejeitar novas jogadas normais.

---

# 152. Restart

Reiniciar cria novo estado inicial.

Não “limpar alguns campos” esquecendo:

- roque;
- contador;
- histórico;
- en passant.

Teste de restart deve verificar tudo.

---

# 153. Ataques de peão

Peão ataca casas diagonais para frente.

Isso é verdadeiro mesmo quando:

- casas estão vazias;
- peão não poderia “mover” para lá sem captura.

Muito importante para:

- rei;
- roque.

---

# 154. Pawn attack ≠ pawn move

## CONCEITO CRÍTICO

Para determinar casa atacada por peão:

não usar lógica de avanço.

Peão:

```text
move para frente
ataca diagonal
```

---

# 155. Ataque do rei

Rei ataca todas as casas adjacentes geometricamente.

Isso impede reis adjacentes.

---

# 156. Ataque do cavalo

Padrão L independente de bloqueio intermediário.

---

# 157. Ataque de peças deslizantes

Bispo/torre/dama atacam ao longo de raios até primeira ocupação.

A primeira casa ocupada é atacada se contém peça adversária?

Para fins da definição de ataque e segurança do rei, precisamos modelar corretamente os raios e bloqueios.

Não atravessar a primeira peça.

---

# 158. Ataque por peça cravada

Repetindo porque é um bug sério:

> uma peça pode contar como atacante de uma casa mesmo se mover aquela peça fosse ilegal por expor o próprio rei.

A rotina `isSquareAttacked` não deve simplesmente chamar:

```text
generateLegalMoves(opponent)
```

e procurar destino.

---

# 159. Arquitetura sugerida de ataque

## PROPOSTO

Possuir lógica conceitual:

```text
isSquareAttacked(square, byColor)
```

independente de geração completa de movimentos legais.

Nome não está aprovado, mas a responsabilidade é necessária.

---

# 160. Geração de movimentos

Há duas estratégias comuns:

### A

gerar pseudo-legais e filtrar.

### B

gerar legais diretamente considerando:

- xeque;
- cravadas;
- pins.

Para primeira engine educacional:

## PROPOSTO

A tende a ser mais simples de entender e validar.

---

# 161. Pseudo-legal + make/unmake

Fluxo:

```text
gerar pseudo-legal
↓
aplicar temporariamente
↓
rei próprio atacado?
├── sim → descartar
└── não → legal
↓
desfazer temporário
```

Ou usar cópia de estado.

---

# 162. Make/unmake versus copiar estado

## PENDENTE

### Make/unmake

Vantagens:

- eficiente;
- padrão em engines.

Risco:

- bugs de restauração de estado.

### Cópia

Vantagens:

- simples;
- previsível.

Custo:

- mais alocação/cópia.

Para app sem IA profunda inicialmente:

correção e clareza podem valer mais que microperformance.

---

# 163. Regra para simulação

Qualquer abordagem deve restaurar exatamente:

- peças;
- turno;
- roque;
- en passant;
- halfmove;
- histórico;
- status;

quando usada apenas para análise.

---

# 164. Movimento e estado anterior

Se `Move` precisar suportar undo/simulação, pode carregar metadados.

Não decidir formato agora.

---

# 165. Tipos de movimento conceituais

Úteis para testes:

```text
QUIET
CAPTURE
PAWN_DOUBLE
EN_PASSANT
CASTLE_KINGSIDE
CASTLE_QUEENSIDE
PROMOTION
PROMOTION_CAPTURE
```

Não é obrigatório usar enum com esses nomes.

---

# 166. Origem e destino iguais

## ILEGAL

Nenhuma jogada normal move uma peça para a mesma casa.

---

# 167. Origem vazia

## ILEGAL

Não existe movimento sem peça.

---

# 168. Peça adversária na origem

## ILEGAL

Jogador só move sua cor.

---

# 169. Destino fora do tabuleiro

## ILEGAL

Idealmente, representação de `Position` deve impedir coordenada inválida ou a API deve rejeitar.

---

# 170. Captura própria

## ILEGAL

Nunca remover peça própria como captura.

---

# 171. Posição sem rei

## ILEGAL PARA PARTIDA NORMAL

Cada lado precisa de um rei enquanto a partida está em andamento.

---

# 172. Dois reis da mesma cor

## ILEGAL PARA PARTIDA NORMAL

Promoção não permite rei.

---

# 173. Peão na última fileira sem promoção

## ILEGAL COMO ESTADO FINAL DE JOGADA

Uma jogada que chega lá deve concluir promoção imediatamente.

---

# 174. Ambos os reis em xeque

## ILEGAL EM UMA POSIÇÃO ALCANÇADA NORMALMENTE

O motor não deve produzir.

---

# 175. Jogador que acabou de mover deixou próprio rei em xeque

## MOVIMENTO ILEGAL

---

# 176. Check antes do movimento

Se jogador está em xeque:

somente movimentos que eliminam completamente o xeque são legais.

---

# 177. Rei capturando atacante

Só legal se destino não estiver protegido.

---

# 178. Bloqueio de xeque

Só funciona contra ataque em linha com espaço intermediário.

Não bloqueia:

- cavalo;
- peão adjacente;
- rei adjacente;
- peça atacante colada sem casa intermediária.

---

# 179. Múltiplos checks

Geração legal completa deve resolver sem regras hardcoded excessivas.

---

# 180. Perft

## RECOMENDADO FORTEMENTE

Perft é técnica clássica de teste de geração de movimentos.

Ela conta o número de folhas/movimentos possíveis em profundidades sucessivas sem avaliação de posição.

Útil para encontrar bugs em:

- move generation;
- roque;
- en passant;
- promoção;
- check;
- make/unmake.

Fonte técnica:

https://www.chessprogramming.org/Perft

---

# 181. Por que Perft é valioso

Um teste unitário pode provar casos individuais.

Perft verifica enormes combinações.

Se posição conhecida deveria produzir:

```text
N nós em profundidade D
```

e o Gambitol produz outro número:

há bug de geração/aplicação.

---

# 182. Perft não testa IA

Perft testa principalmente:

- legalidade;
- geração;
- estado.

Não mede força de jogo.

---

# 183. Perft e debug divide

Uma técnica útil é contar por cada movimento raiz.

Isso ajuda localizar:

> em qual ramo o número diverge.

Excelente para conteúdo técnico futuro.

---

# 184. Posição inicial — Perft conhecido

## FUTURO PARA TESTES

A posição inicial possui contagens clássicas amplamente usadas em engines.

Ao implementar Perft, usar fontes reconhecidas e validar valores antes de registrar no teste.

Não fixar números neste documento sem necessidade operacional imediata.

---

# 185. Testes por peça

Cada peça precisa de casos:

- centro;
- borda;
- canto;
- bloqueio;
- captura;
- peça própria;
- movimento ilegal.

---

# 186. Testes do bispo

Cobrir:

- quatro diagonais;
- bloqueio imediato;
- bloqueio distante;
- captura;
- horizontal inválido;
- vertical inválido.

---

# 187. Testes da torre

Cobrir:

- quatro direções;
- bloqueio;
- captura;
- diagonal inválida.

---

# 188. Testes da dama

Cobrir:

- diagonal;
- rank;
- file;
- bloqueio;
- padrão de cavalo inválido.

---

# 189. Testes do cavalo

Cobrir oito destinos possíveis em posição central.

Também:

- borda;
- peça própria no destino;
- captura;
- peças no caminho não importam.

---

# 190. Testes do rei

Cobrir:

- 8 adjacentes;
- casa atacada;
- captura protegida;
- rei adversário adjacente.

---

# 191. Testes de peão branco

- uma casa;
- duas iniciais;
- duas fora da inicial;
- bloqueio;
- captura;
- diagonal vazia;
- para trás;
- en passant;
- promoção.

---

# 192. Testes de peão preto

Espelhados.

Esse é bom lugar para property/symmetry tests.

---

# 193. Testes de roque

Cobrir no mínimo:

- permitido kingside;
- permitido queenside;
- rei já moveu;
- torre já moveu;
- rei moveu e voltou;
- torre moveu e voltou;
- caminho ocupado;
- rei em xeque;
- casa intermediária atacada;
- destino atacado;
- torre atacada, mas roque legal;
- b-file atacada no roque longo, mas não relevante;
- torre ausente;
- torre adversária no canto;
- captura anterior da torre.

---

# 194. Testes de en passant

Cobrir:

- disponível imediatamente;
- expira após outro lance;
- peão adversário moveu apenas uma casa;
- avanço duplo não adjacente;
- captura correta;
- remoção correta;
- en passant que expõe rei → ilegal;
- en passant que resolve xeque quando válido.

---

# 195. Testes de promoção

Cobrir:

- Q;
- R;
- B;
- N;
- captura + promoção;
- branco;
- preto;
- rejeitar rei;
- rejeitar peão;
- ausência de escolha;
- efeito imediato de xeque/mate.

---

# 196. Testes de xeque

- rook;
- bishop;
- queen;
- knight;
- pawn;
- king;
- discovered;
- bloqueado;
- pinned attacker nuance.

---

# 197. Testes de xeque-mate

Usar posições conhecidas simples.

Verificar:

```text
inCheck = true
legalMoves = 0
result = mate
```

---

# 198. Testes de stalemate

Verificar:

```text
inCheck = false
legalMoves = 0
result = draw
```

---

# 199. Testes de dead position

Começar com casos incontestáveis.

Não tentar cobrir todo espaço de posições com heurística simplificada.

---

# 200. Testes de repetição

Cobrir:

- mesma board arrangement + turno diferente → diferente;
- roque diferente → diferente;
- en passant diferente → potencialmente diferente;
- terceira ocorrência → claimable;
- quinta → terminal.

---

# 201. Testes dos 50 movimentos

- 99 halfmoves → ainda não;
- 100 → claimable;
- peão reseta;
- captura reseta.

---

# 202. Testes dos 75 movimentos

- 149 → não automático;
- 150 → automático;
- pawn/capture antes reseta;
- mate no 150º halfmove → mate prevalece quando regra aplicável ao lance final.

---

# 203. Testes de resign

Quando feature existir:

- posição normal → adversário vence;
- adversário incapaz de mate por qualquer sequência → empate.

---

# 204. Testes de timeout

Quando timer existir:

- flag fall normal → derrota;
- adversário sem possibilidade de mate → empate.

---

# 205. Testes de restart

Após partida complexa:

reiniciar deve restaurar:

- peças;
- turno;
- roque;
- en passant;
- halfmove;
- repetição;
- status.

---

# 206. Testes de legalidade global

Para todo movimento legal aplicado:

- exatamente um rei branco;
- exatamente um rei preto;
- jogador que acabou de mover não fica em xeque;
- estado permanece consistente.

---

# 207. Property-based testing

## FUTURO / CANDIDATO

Pode testar propriedades gerais:

- aplicar e desfazer restaura posição;
- espelhar posição troca comportamento;
- movimento legal não remove próprio rei;
- nenhum destino fora do tabuleiro.

Não adicionar framework antes de testes normais estarem maduros.

---

# 208. Symmetry testing

Xadrez possui várias simetrias úteis.

Por exemplo:

regra de peão branco deve ter equivalente preto espelhado.

Isso ajuda detectar assimetria acidental.

---

# 209. Fuzzing de movimentos

## FUTURO

Gerar sequências legais aleatórias e validar invariantes pode descobrir bugs.

Não necessário para primeira implementação.

---

# 210. Cross-validation com engine externa

## FUTURO / EXCELENTE PARA QUALIDADE

Podemos comparar:

- movimentos legais;
- Perft;
- FEN;
- resultados;

com uma engine/biblioteca confiável.

Isso não substitui nosso motor.

Serve como oráculo de validação.

---

# 211. Stockfish como referência de validação

## FUTURO

Pode ser usado para comparar posições/movimentos.

Mas:

- não copiar código sem considerar licença;
- não integrar no produto apenas para teste sem planejamento.

---

# 212. python-chess como oráculo de desenvolvimento

## FUTURO

Uma biblioteca madura pode ser usada fora do app em scripts de comparação.

Isso não significa adicionar Python ao produto.

---

# 213. Notação algébrica

## FUTURO / CANDIDATO IMPORTANTE

A FIDE possui apêndice de Algebraic Notation.

Se histórico de jogadas for exibido:

podemos adotar SAN-like/FIDE algebraic notation.

---

# 214. Arquivos e fileiras na notação

Files:

```text
a-h
```

Ranks:

```text
1-8
```

---

# 215. Símbolos internacionais

Na notação inglesa/FIDE usual:

```text
K king
Q queen
R rook
B bishop
N knight
```

Peão não usa letra inicial.

No app em português, UI pode traduzir nomes, mas formato de notação deve permanecer consistente.

---

# 216. Notação de captura

Geralmente usa:

```text
x
```

em notação algébrica.

---

# 217. Notação de roque

FIDE usa:

```text
0-0
0-0-0
```

com zeros.

Algumas ferramentas usam letra O.

Se exportar, definir formato conscientemente.

---

# 218. Notação de promoção

Inclui a peça promovida.

---

# 219. Notação de xeque

Comumente:

```text
+
```

---

# 220. Notação de mate

Comumente:

```text
#
```

ou convenções equivalentes aceitas.

---

# 221. Ambiguidade de peças

Se duas peças iguais podem alcançar destino:

notação inclui informação suficiente da origem.

Isso deve ser calculado com base em movimentos legais.

---

# 222. Notação não deve determinar a lógica

## DECIDIDO COMO PRINCÍPIO

Primeiro:

```text
Move estruturado
```

Depois:

```text
formatter → notação
```

Não guardar apenas `"Nf3"` como estado do movimento.

---

# 223. FEN

## FUTURO / CANDIDATO

Pode ser excelente para:

- testes;
- debugging;
- carregar posições;
- compartilhar posição.

Mas a implementação precisa ser validada.

---

# 224. PGN

## FUTURO

Pode ser usado para:

- exportar partidas;
- histórico;
- interoperabilidade.

Não faz parte do núcleo inicial.

---

# 225. FEN e invariantes

Importar FEN sintaticamente válida não garante que posição seja historicamente legal.

Precisaremos distinguir:

- parser;
- validator.

---

# 226. Dead position versus tablebase

Tablebases podem dizer:

- win;
- draw;
- loss;

sob jogo perfeito.

Mas “draw sob jogo perfeito” não significa automaticamente dead position.

O critério FIDE é possibilidade de mate por qualquer sequência legal.

---

# 227. Checkmate versus tablebase draw

Não confundir conceitos.

---

# 228. Material insufficient como otimização

Pode ser um atalho para subconjuntos garantidos de dead position.

Não deve ser regra completa se gerar falso positivo.

---

# 229. Draw by repetition e hashing

Hash é ferramenta técnica.

Regra é legal.

Nunca deixar escolha da estrutura de hash mudar definição da posição.

---

# 230. Castling rights na chave de posição

Precisam participar da identidade.

---

# 231. Side to move na chave

Precisa participar.

---

# 232. En passant na chave

Precisa representar diferença de possibilidade legal de en passant conforme regra de repetição.

---

# 233. Halfmove clock na chave de repetição

Não faz parte da definição de “mesma posição” da FIDE para repetição.

Logo, não deve impedir reconhecimento de repetição.

Pode existir no snapshot, mas não necessariamente na repetition key.

---

# 234. Fullmove number na chave de repetição

Também não faz parte da igualdade da posição para repetição.

---

# 235. Piece placement na chave

Obviamente participa.

---

# 236. Rei em xeque na chave

Pode ser derivado da posição.

Não precisa de campo extra se board/turn/rights determinam.

---

# 237. Histórico e memória

Não otimizar cedo.

Uma partida humana normal possui quantidade de posições perfeitamente gerenciável em celular moderno.

Escolher clareza primeiro.

---

# 238. Max legal moves

Não definir array fixo sem entender limite.

Se futura IA usar arrays por performance, pesquisar limites e testar.

Para o MVP, collections Java claras podem ser suficientes.

---

# 239. Performance da geração

Para interação humana:

milissegundos são mais que suficientes.

Prioridade:

```text
correção > clareza > microperformance
```

até IA exigir outro perfil.

---

# 240. Bitboards

## FUTURO / NÃO NECESSÁRIO AGORA

Bitboards são representação clássica e eficiente.

Mas aumentam:

- operações bitwise;
- complexidade;
- barreira didática.

Podem ser estudados depois.

---

# 241. 0x88

## FUTURO / NÃO NECESSÁRIO AGORA

Outra técnica clássica.

Também não precisa entrar na primeira engine.

---

# 242. Array 8x8

## CANDIDATO DIDÁTICO

É simples e legível.

Mas decisão será tomada na implementação.

---

# 243. Array 64

## CANDIDATO

Também simples e facilita índices.

---

# 244. Piece list

## FUTURO

Pode melhorar consultas/performance.

Não necessária antes de medir.

---

# 245. Representação por objetos

Pode ser adequada para aprendizado OOP.

Mas evitar modelagem que torne regras difíceis.

A escolha de representação é decisão de engenharia, não regra FIDE.

---

# 246. Estado do rei

Encontrar rei precisa ser confiável.

Pode:

- procurar;
- manter referência/posição atualizada.

Se cachear posição do rei:

testes precisam garantir sincronização.

---

# 247. Ataque e performance

`isSquareAttacked` será chamada frequentemente.

Implementar primeiro corretamente.

Otimizar depois se necessário.

---

# 248. Legal move generation como API central

Uma API central de movimentos legais pode servir:

- UI;
- mate;
- stalemate;
- IA;
- testes.

Isso reduz duplicação.

---

# 249. UI não cria lista própria de movimentos

A UI pode filtrar para uma peça a lista fornecida pelo motor.

Não reimplementar regra.

---

# 250. IA não cria regra própria

IA consulta movimentos legais do motor.

Não manter dois geradores.

---

# 251. Testes e UI usam mesmo motor

Isso é exatamente a vantagem arquitetural.

---

# 252. Sequência de implementação recomendada

## PROPOSTO

1. coordenadas;
2. cor;
3. tipo de peça;
4. tabuleiro/posição inicial;
5. movimento simples;
6. peças sem especiais;
7. ataque;
8. segurança do rei;
9. movimentos legais;
10. xeque;
11. mate/stalemate;
12. roque;
13. en passant;
14. promoção;
15. histórico;
16. repetição;
17. 50/75;
18. dead position;
19. notação;
20. persistência/serialização quando necessária.

O roadmap definitivo pertence ao documento 10.

---

# 253. Por que roque depois da base

Roque depende de:

- ataque;
- histórico;
- ocupação;
- rei.

Implementar antes de `isSquareAttacked` tende a gerar regra duplicada.

---

# 254. Por que en passant depois da base

Depende de:

- histórico do último movimento;
- captura especial;
- segurança do rei após remover duas casas.

---

# 255. Por que repetição depois de estado completo

Sem:

- roque;
- en passant;
- turno;

repetition key seria incorreta.

---

# 256. Por que 50/75 depois do movimento

Exige atualização confiável de histórico/halfmove.

---

# 257. Por que dead position merece cuidado

É fácil implementar uma versão “quase certa” que declara empate indevido.

Melhor começar conservadoramente.

---

# 258. Estados de movimento inválido

## PROPOSTO

Motor deve poder distinguir motivos para:

- origem vazia;
- cor errada;
- geometria;
- bloqueio;
- rei em xeque;
- promoção faltando;
- partida encerrada.

UI pode decidir quanto mostrar.

Não precisa expor todos se API ficar complexa.

---

# 259. Boolean simples versus resultado rico

## PENDENTE

`boolean move(...)` é simples.

Mas não informa motivo.

Objeto de resultado pode informar:

- aceito;
- motivo;
- novo estado.

Decisão futura.

---

# 260. Exceptions não são substituto de validação

Movimento ilegal do usuário é evento esperado.

Não deve necessariamente lançar exception.

---

# 261. Illegal state

Exceção faz mais sentido se código interno produz:

- dois reis brancos;
- peça fora do tabuleiro;
- movimento aplicado sem validação em API interna.

---

# 262. API pública segura

Idealmente, UI não terá método interno:

```text
forceMove()
```

sem necessidade.

APIs perigosas podem ser package-private/test-only.

---

# 263. Parser de movimento

## FUTURO

Se receber string:

```text
e2e4
```

parser deve converter para MoveIntent.

Não misturar parser com regra.

---

# 264. UCI move notation

## FUTURO

Formato como:

```text
e2e4
e7e8q
```

pode ser útil para testes/IA.

Não é a notação visual obrigatória do app.

---

# 265. SAN para UI

Mais amigável para histórico de xadrez.

Pode ser gerada depois.

---

# 266. Estado inicial esperado em FEN

## REFERÊNCIA TÉCNICA FUTURA

A posição inicial padrão possui uma representação FEN conhecida.

Se implementarmos FEN, usar a forma canônica validada em fonte confiável e testar round-trip.

Não precisamos congelá-la neste documento para implementar a lógica básica.

---

# 267. Round-trip de FEN

Teste futuro:

```text
position
↓
FEN
↓
parse
↓
position equivalent
```

---

# 268. Round-trip de movimento

Se serializar movimentos:

mesma ideia.

---

# 269. Determinismo

Sem IA aleatória:

mesma posição + mesma ação legal → mesmo estado resultante.

---

# 270. Pure functions

Algumas regras podem ser funções puras:

- geometria;
- bounds;
- ataques locais.

Usar quando melhora clareza.

---

# 271. Mutação centralizada

Estado global da partida deve mudar em ponto controlado.

Evitar cada peça mutando Board diretamente sem coordenação.

---

# 272. Segurança transacional da jogada

Se movimento falha:

nenhuma alteração parcial.

---

# 273. Histórico após movimento inválido

Não adicionar entrada.

---

# 274. Halfmove após movimento inválido

Não alterar.

---

# 275. Turno após movimento inválido

Não alterar.

---

# 276. Direitos de roque após tentativa inválida

Não alterar.

---

# 277. En passant após tentativa inválida

Não expirar por tentativa inválida.

Somente após movimento legal concluído.

---

# 278. Promoção cancelada pela UI

Se usuário ainda não escolheu peça:

estado lógico da partida deve permanecer anterior, se a jogada não foi confirmada.

---

# 279. Undo futuro

Se implementado, deve restaurar:

- peças;
- capturas;
- turno;
- roque;
- en passant;
- halfmove;
- repetição;
- status;
- promoção.

Isso reforça importância de estado bem modelado.

---

# 280. Redo futuro

Mesma preocupação.

---

# 281. Checkpoint de consistência

## PROPOSTO PARA DEBUG

Em build/test, poder validar invariantes após cada jogada.

Exemplos:

- dois reis presentes;
- sideToMove válido;
- posições únicas;
- nenhuma peça fora do tabuleiro.

---

# 282. Assertion

Assertions/test validations podem ajudar no engine durante desenvolvimento.

Não substituir tratamento de input público.

---

# 283. Debug board print

## RECOMENDADO

Uma representação textual do tabuleiro será extremamente útil para testes e logs.

Exemplo:

```text
8 r n b q k b n r
7 p p p p p p p p
6 . . . . . . . .
5 . . . . . . . .
4 . . . . . . . .
3 . . . . . . . .
2 P P P P P P P P
1 R N B Q K B N R
  a b c d e f g h
```

Não é UI final.

É ferramenta de engenharia.

---

# 284. Símbolos de debug

Podem usar:

```text
K Q R B N P
k q r b n p
```

convenção semelhante a FEN.

---

# 285. Debug state print

Também útil:

```text
Turn: WHITE
Castling: KQkq
En passant: -
Halfmove: 0
Status: IN_PROGRESS
```

---

# 286. 🎥 MOMENTO BOM PARA GRAVAR — pseudo-legal vs legal

Tema excelente.

Mostrar:

- torre/cavalo cravado;
- movimento geometricamente possível;
- simulação deixa rei em xeque;
- motor rejeita.

Título possível:

> “O movimento parece válido, mas é ilegal: como um motor de xadrez pensa.”

---

# 287. 🎥 MOMENTO BOM PARA GRAVAR — en passant e rei

Um dos casos mais interessantes.

Mostrar como retirar o peão capturado pode abrir uma linha de ataque.

---

# 288. 🎥 MOMENTO BOM PARA GRAVAR — roque

Mostrar as três casas do rei:

- origem;
- passagem;
- destino;

e explicar por que torre atacada não impede o roque.

---

# 289. 🎥 MOMENTO BOM PARA GRAVAR — repetição

Mostrar duas posições visualmente idênticas, mas uma sem direito de roque.

Excelente conceito de:

> estado invisível.

---

# 290. 🎥 MOMENTO BOM PARA GRAVAR — Perft

Mostrar:

```text
motor gera movimentos
↓
contagem diverge
↓
divide localiza ramo
↓
bug corrigido
```

Conteúdo excelente para portfólio técnico.

---

# 291. COMO EXPLICAR EM ENTREVISTA

Após implementação real:

> “No motor do Gambitol eu separei movimentos pseudo-legais de legalidade global. Primeiro valido geometria, ocupação e regras especiais; depois verifico se o movimento deixa o próprio rei em xeque. Isso simplifica casos como peças cravadas e permite reutilizar a mesma geração de movimentos para UI, detecção de mate e futuramente IA.”

---

# 292. COMO EXPLICAR REPETIÇÃO EM ENTREVISTA

> “Para repetição, não considero apenas a disposição das peças. A posição também depende do lado a jogar e de direitos que alteram movimentos possíveis, como roque e en passant. Assim evitamos considerar iguais duas posições visualmente idênticas, mas juridicamente diferentes.”

---

# 293. COMO EXPLICAR O ESTADO DO MOTOR

> “A posição precisa armazenar mais que o tabuleiro: lado a jogar, direitos de roque, estado de en passant e contadores de empate. O histórico ou uma chave de posição complementa isso para detectar repetição.”

---

# 294. Decisões normativas deste documento

## DECIDIDO

Após aprovação:

1. xadrez padrão;
2. FIDE como autoridade;
3. brancas começam;
4. rei não é capturado;
5. movimento ilegal não altera estado;
6. ataque e movimento legal são conceitos diferentes;
7. próprio rei nunca pode permanecer em xeque;
8. promoção obrigatória para Q/R/B/N;
9. roque depende de direitos históricos e segurança das casas do rei;
10. en passant dura um lance;
11. checkmate encerra;
12. stalemate empata;
13. dead position empata;
14. três repetições são reclamáveis;
15. cinco repetições são automáticas;
16. 50 movimentos são reclamáveis;
17. 75 movimentos são automáticos;
18. mate tem precedência sobre a regra automática de 75 quando o lance produz mate;
19. estado lógico é autoridade;
20. engine deve guardar contexto suficiente para as regras.

---

# 295. Decisões ainda pendentes

## PENDENTE

- representação do tabuleiro;
- nomes de classes;
- estrutura de Move;
- make/unmake versus cópia;
- chave/hash de repetição;
- FEN;
- PGN;
- SAN;
- suporte a claim “antes de fazer o movimento”;
- UI de empate;
- política de timer;
- implementação completa de dead position;
- undo;
- redo;
- importação de posições;
- engine de IA.

---

# 296. Critérios de pronto para movimento de peça

Uma peça básica não está pronta apenas porque “se move na tela”.

Precisa:

- [ ] geometria correta;
- [ ] limite do tabuleiro;
- [ ] própria peça bloqueia destino;
- [ ] captura adversária;
- [ ] caminho quando aplicável;
- [ ] segurança do rei;
- [ ] testes válidos;
- [ ] testes inválidos.

---

# 297. Critérios de pronto para peão

Além dos anteriores:

- [ ] direção;
- [ ] avanço simples;
- [ ] avanço duplo;
- [ ] bloqueio;
- [ ] captura;
- [ ] en passant;
- [ ] promoção;
- [ ] branco/preto;
- [ ] exposição do rei.

---

# 298. Critérios de pronto para roque

- [ ] direitos históricos;
- [ ] rook correta presente;
- [ ] caminho vazio;
- [ ] rei não está em xeque;
- [ ] trânsito seguro;
- [ ] destino seguro;
- [ ] rook movida automaticamente;
- [ ] direitos removidos;
- [ ] testes positivos/negativos.

---

# 299. Critérios de pronto para fim de partida

- [ ] mate;
- [ ] stalemate;
- [ ] dead position segura;
- [ ] repetição reclamável;
- [ ] repetição automática;
- [ ] 50-move claim;
- [ ] 75-move auto;
- [ ] precedência;
- [ ] terminal bloqueia novas jogadas.

---

# 300. Critérios de pronto para motor MVP

- [ ] posição inicial;
- [ ] 6 tipos de peças;
- [ ] turnos;
- [ ] capturas;
- [ ] ataques;
- [ ] legalidade global;
- [ ] xeque;
- [ ] mate;
- [ ] stalemate;
- [ ] roque;
- [ ] en passant;
- [ ] promoção;
- [ ] empate;
- [ ] histórico suficiente;
- [ ] testes;
- [ ] Perft/validação cruzada quando implementado;
- [ ] nenhuma dependência Android.

---

# 301. Fontes pesquisadas — FIDE

## FIDE Handbook — Laws of Chess

https://handbook.fide.com/chapter/e012023

Usado como autoridade principal para:

- tabuleiro;
- turnos;
- movimento;
- captura;
- ataques;
- peças;
- peão;
- en passant;
- promoção;
- rei;
- roque;
- xeque;
- legalidade;
- mate;
- stalemate;
- dead position;
- resignação;
- tempo;
- repetição;
- 50/75 movimentos;
- notação.

Verificado em: 2026-08-22.

---

## FIDE Rules Commission — Documentation

https://rcc.fide.com/documentation/

Usado para confirmar a fonte oficial de documentação das regras.

Verificado em: 2026-08-22.

---

# 302. Fontes técnicas — Chess Programming Wiki

A Chess Programming Wiki NÃO substitui a FIDE como autoridade das regras.

Ela foi consultada para traduzir regras em problemas típicos de software.

---

## Pseudo-Legal Move

https://www.chessprogramming.org/Pseudo-Legal_Move

Base técnica para:

- diferença pseudo-legal/legal;
- filtro de rei;
- roque/en passant no move generation.

---

## Checks and Pinned Pieces

https://www.chessprogramming.org/Checks_and_Pinned_Pieces_(Bitboards)

Base técnica para:

- detecção de check;
- cravadas;
- ataques.

---

## Forsyth-Edwards Notation

https://www.chessprogramming.org/Forsyth-Edwards_Notation

Base técnica para:

- campos de estado;
- castling;
- en passant;
- halfmove;
- fullmove.

---

## Fifty-move Rule

https://www.chessprogramming.org/Fifty-move_Rule

Base complementar para:

- halfmove clock;
- 50/75.

Regra final continua vindo da FIDE.

---

## Irreversible Moves

https://www.chessprogramming.org/Irreversible_Moves

Base técnica para:

- pawn/capture reset do halfmove;
- castling rights e repetição.

---

## Move List

https://www.chessprogramming.org/Move_List

Base para:

- histórico;
- repetition signatures;
- relação move list/position list.

---

## Perft

https://www.chessprogramming.org/Perft

Base para:

- validação de move generation;
- testes de árvore.

---

## Debugging

https://www.chessprogramming.org/Debugging

Base complementar para:

- comparação de implementações;
- regressão;
- diagnóstico de engine.

Verificado em: 2026-08-22.

---

# 303. Hierarquia de autoridade

Quando houver conflito:

```text
FIDE
↓
decisão documentada do Gambitol
↓
fontes técnicas de implementação
↓
fóruns/comunidade
```

Uma técnica de engine nunca pode reescrever uma regra FIDE sem decisão explícita de produto.

---

# 304. Regra para interpretações duvidosas

Se surgir caso estranho:

1. não adivinhar;
2. localizar artigo FIDE;
3. verificar Rules Commission quando necessário;
4. criar posição mínima;
5. adicionar teste de regressão;
6. registrar interpretação neste documento.

---

# 305. Casos “parece óbvio” que merecem teste

- rei captura peça protegida;
- rei ao lado do rei;
- pinned piece ataca casa;
- rook atacada no roque;
- b1 atacada no roque longo;
- en passant abre rook attack;
- rook saiu e voltou;
- terceira vs quinta repetição;
- 50 vs 75;
- dois cavalos vs rei;
- mate no lance de 75.

Esses são exatamente os bugs que aparecem quando alguém implementa xadrez “pela memória”.

---

# 306. Ordem de precedência de resultados

## PROPOSTO

Quando múltiplas condições parecem coincidir, aplicar semântica FIDE.

Especialmente:

```text
CHECKMATE
```

tem precedência quando o lance final também completaria condição automática dos 75 movimentos.

Outros conflitos devem ser testados contra regra oficial.

---

# 307. Estado terminal versus display

Motor retorna resultado.

UI decide texto:

```text
Xeque-mate
Brancas venceram
Empate por afogamento
```

Strings não pertencem ao domínio.

---

# 308. Razão do resultado

## PROPOSTO

Não retornar apenas:

```text
DRAW
```

Guardar razão:

- stalemate;
- repetition;
- 75;
- dead;
- agreement.

Isso melhora:

- UI;
- histórico;
- testes;
- analytics futuro.

---

# 309. Vitória por tipo

Mesma lógica:

- checkmate;
- resignation;
- timeout.

Razão distinta.

---

# 310. Resultado e jogador vencedor

Em draw:

```text
winner = none
```

Em vitória:

identificar cor.

---

# 311. Estado de xeque

Pode ser derivado.

Mas UI precisará saber para:

- highlight;
- mensagem.

State holder pode pedir ao motor.

---

# 312. Lista de movimentos legais

UI pode usar para highlight.

Motor é único fornecedor.

---

# 313. Movimentos legais e performance

Ao selecionar uma peça:

filtrar movimentos legais daquela origem.

Não recalcular de forma diferente na UI.

---

# 314. Seleção de peça adversária

UI pode permitir visualização, mas não movimento.

Motor rejeita ação.

---

# 315. Turno após mate

Não alternar para uma “nova vez” operacional.

Pode registrar sideToMove resultante conforme representação, mas status terminal impede ação.

Decisão concreta do modelo será definida.

---

# 316. Turno e notação

Fullmove number é convenção separada.

Não confundir com número total de halfmoves.

---

# 317. Ply

Termo técnico:

```text
ply = meio-lance
```

Um movimento de um lado.

Pode ser útil em testes/IA.

Não precisa aparecer ao jogador.

---

# 318. Move number

Uma jogada completa convencional inclui:

- movimento branco;
- movimento preto.

Mas partida pode terminar após apenas um deles.

---

# 319. Halfmove clock

Não é número total de halfmoves da partida.

É:

```text
quantidade desde último peão/captura
```

---

# 320. Repetition count

Também não é contador simples global.

Conta ocorrências da posição equivalente.

---

# 321. Estado de roque recomendado

## PROPOSTO

Quatro direitos independentes:

```text
WHITE_KINGSIDE
WHITE_QUEENSIDE
BLACK_KINGSIDE
BLACK_QUEENSIDE
```

Pode ser flags/booleans/objeto.

Não usar apenas:

```text
kingMoved
```

porque cada rook é independente.

---

# 322. Atualização de direitos por captura

Se rook original é capturada em sua casa inicial:

direito correspondente precisa desaparecer.

Se já estava perdido:

continua perdido.

---

# 323. En passant state recomendado

## PROPOSTO

Guardar informação suficiente para identificar captura possível no próximo lance.

Não guardar apenas “último peão moveu” se isso obrigar reconstrução ambígua.

---

# 324. En passant e FEN target

Se adotarmos FEN:

entender semântica da target square.

Não misturar automaticamente com “captura legal disponível” para repetition key.

---

# 325. PromotionChoice

## PROPOSTO

Tipo fechado:

```text
QUEEN
ROOK
BISHOP
KNIGHT
```

Pode reaproveitar PieceType com validação.

---

# 326. Castling move

## PROPOSTO

Mover rei e rook atomicamente.

Se alguma parte falhar:

nenhuma alteração.

---

# 327. Capture move

Atomicamente:

- remover adversário;
- mover atacante;
- atualizar estado.

---

# 328. En passant move

Atomicamente:

- mover peão;
- remover peão lateral;
- atualizar en passant;
- reset halfmove;
- verificar rei.

---

# 329. Promotion move

Atomicamente:

- remover/transformar peão;
- colocar nova peça;
- registrar move;
- atualizar status.

---

# 330. Movimento que dá mate

Após aplicação:

não precisa existir comando extra:

```text
declareCheckmate()
```

Mate deve ser detectado do estado.

---

# 331. Movimento que dá check

Mesma ideia.

---

# 332. Estado redundante

Evitar guardar manualmente:

```text
isCheck = true
```

se código pode esquecer de atualizar.

Pode cachear se necessário, mas com owner claro.

---

# 333. Engine e UI offline

Motor deve funcionar em teste Java sem:

- Context;
- resources;
- internet.

Reforça arquitetura 03/04.

---

# 334. Definição de “regra completa”

Uma regra só é considerada implementada quando:

```text
caso normal
+
casos negativos
+
edge cases
+
teste
```

---

# 335. Definição de “movimento funciona”

Não é:

> peça visual mudou de casa.

É:

> motor aceitou movimento legal, alterou estado correto, rejeitou ilegais e preservou invariantes.

---

# 336. Definição de “xadrez completo” para o Gambitol

Para MVP:

- todas as seis peças;
- capturas;
- turnos;
- segurança do rei;
- xeque;
- mate;
- stalemate;
- roque;
- en passant;
- promoção;
- regras de empate definidas neste documento.

---

# 337. Critério de fidelidade

Quando Gambitol divergir da FIDE deliberadamente:

documentar como:

```text
ADAPTAÇÃO DIGITAL DO GAMBITOL
```

e justificar.

Não alterar silenciosamente.

---

# 338. Possíveis adaptações digitais futuras

- UI de claim;
- oferta de draw;
- touch interaction;
- timer;
- premove online;
- autoqueen opcional.

Cada uma precisa preservar regras ou declarar diferença.

---

# 339. Autoqueen

## FUTURO / NÃO ATIVO

Uma opção de promover automaticamente para dama poderia existir.

Mas:

- underpromotion continua legal;
- se autoqueen estiver ativa, deve ser preferência explícita;
- não remover possibilidade real do motor.

---

# 340. Premove

## FUTURO / ONLINE

Não é movimento executado fora do turno no motor.

É intenção de UI que pode ser aplicada quando o turno chega, se continuar legal.

---

# 341. Drag-and-drop

UI pode usar.

Não muda regra.

---

# 342. Tap-tap

UI pode usar.

Não muda regra.

---

# 343. Legal move hints

UI consulta motor.

Não inventa.

---

# 344. Hint de xeque

UI consulta estado.

---

# 345. Destaque de último movimento

Histórico/UI.

Não regra.

---

# 346. Captured pieces display

Pode ser derivado de:

- posição inicial + atual;
- histórico;
- lista de capturas.

Não é fonte de verdade.

---

# 347. Material score

## FUTURO

É avaliação/UX.

Não regra.

---

# 348. Rating

## FUTURO

Não motor de xadrez.

---

# 349. IA difficulty

## FUTURO

Não regra.

---

# 350. Conclusão normativa

O motor do Gambitol deve ser capaz de responder, com consistência:

```text
Qual é a posição?
De quem é a vez?
Essa casa está atacada?
Quais movimentos são pseudo-legais?
Quais movimentos são legais?
Esse movimento pode ser aplicado?
Qual é o novo estado?
Há xeque?
Há mate?
Há afogamento?
Há posição morta?
Existe empate reclamável?
Existe empate automático?
A partida terminou?
Por quê?
```

Se essas respostas forem corretas, testáveis e independentes do Android, o núcleo do Gambitol estará no caminho certo.

---

# 351. Frase norteadora

> **O motor não deve “parecer jogar xadrez”. Ele deve representar o estado e aplicar as regras de maneira suficientemente precisa para que a interface seja apenas uma forma de enxergar e controlar uma partida correta.**

---

# 352. Próximo documento

Após aprovação:

`06_PADROES_JAVA_E_ANDROID.md`

Ele definirá como o código será escrito:

- convenções Java;
- imutabilidade;
- enums;
- nullability;
- exceptions;
- collections;
- visibilidade;
- nomes;
- Android resources;
- Activities;
- ViewModels;
- XML;
- logs;
- Gradle;
- dependências;
- comentários;
- estilo;
- anti-patterns.

O documento 05 define:

> **o comportamento correto.**

O documento 06 definirá:

> **como escrever esse comportamento com consistência técnica.**
