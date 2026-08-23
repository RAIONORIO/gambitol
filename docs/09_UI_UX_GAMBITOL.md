# 09 — UI/UX DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `09_UI_UX_GAMBITOL.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** transformar a direção visual já aprovada do Gambitol em regras de interface, interação, responsividade, acessibilidade, feedback e experiência de jogo sem confundir conceito visual com feature já implementada  
> **Fonte normativa para:** hierarquia visual, tabuleiro, seleção, movimentos, turno, jogadores, timers quando existirem, histórico quando existir, estados de partida, feedback, touch targets, contraste, acessibilidade, responsividade, system bars, edge-to-edge, animações e critérios de qualidade visual  
> **Não cobre em detalhe:** implementação de classes Android, regras internas do motor, arquitetura de módulos, estratégia completa de testes, Git workflow, roadmap, monetização ou processo de publicação  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`, `06_PADROES_JAVA_E_ANDROID.md`, `08_TESTES_E_QUALIDADE.md`  
> **Tecnologia visual atual:** Android Views/XML + Java  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

Este documento responde:

- como o Gambitol deve parecer;
- como o tabuleiro deve se comportar;
- como o jogador entende de quem é a vez;
- como seleção e movimentos válidos são comunicados;
- como captura, xeque, mate e promoção são apresentados;
- como controles funcionam por toque;
- como o layout se adapta a diferentes janelas;
- como não destruir a experiência em telas pequenas;
- como evitar que a UI contradiga o motor;
- como tornar uma eventual Custom View acessível;
- quais elementos do mockup são identidade visual;
- quais elementos são apenas visão futura e ainda não são features confirmadas.

A regra central é:

> **A interface deve tornar o estado do xadrez óbvio sem disputar atenção com o próprio tabuleiro.**

---

# 2. Direção visual já aprovada

## DECIDIDO COMO REFERÊNCIA VISUAL

O conceito visual aprovado para o Gambitol possui:

- orientação predominantemente vertical;
- fundo escuro;
- atmosfera premium;
- preto/carvão como base;
- dourado como destaque de marca;
- verde profundo como cor de apoio;
- tabuleiro em verde e marfim/tons claros;
- logotipo “Gambitol” com linguagem de coroa;
- blocos de jogador;
- indicação de turno;
- possibilidade visual de cronômetros;
- indicação de peças capturadas;
- área de histórico de jogadas;
- controles inferiores;
- destaque de casas e movimentos.

## IMPORTANTE

“Existe no conceito visual” NÃO significa:

```text
feature aprovada para o MVP
```

Por exemplo:

- cronômetro;
- undo;
- histórico completo;
- configurações avançadas;
- ratings;
- avatares;
- personalização;

podem aparecer na visão visual e ainda assim permanecer fora da primeira entrega.

---

# 3. Mockup não é contrato de pixel

## DECIDIDO

O mockup é referência de:

- hierarquia;
- identidade;
- direção;
- atmosfera;
- prioridades.

Ele NÃO deve ser interpretado como:

- coordenada fixa;
- tamanho fixo;
- margem imutável;
- obrigação de manter todo elemento;
- layout incapaz de adaptar.

A interface precisa sobreviver a:

- diferentes larguras;
- diferentes alturas;
- system bars;
- recortes;
- escalas de fonte;
- futuras telas maiores.

---

# 4. Hierarquia principal da tela de partida

## PROPOSTO

Ordem conceitual para telefone em portrait:

```text
SYSTEM AREA / SAFE INSET
↓
IDENTIDADE / CABEÇALHO COMPACTO
↓
JOGADOR SUPERIOR
↓
TABULEIRO
↓
JOGADOR INFERIOR
↓
STATUS / HISTÓRICO RESUMIDO
↓
AÇÕES PRINCIPAIS
↓
SYSTEM NAVIGATION INSET
```

A ordem física exata pode mudar durante prototipação.

---

# 5. O tabuleiro é o elemento dominante

## DECIDIDO

Na tela de partida:

> o tabuleiro possui prioridade visual máxima.

Nenhum elemento secundário deve roubar espaço a ponto de:

- casas ficarem pequenas demais;
- peças perderem legibilidade;
- toque ficar impreciso;
- usuário precisar rolar para ver tabuleiro inteiro em uso normal.

---

# 6. Tabuleiro quadrado

## DECIDIDO

A área visual do board deve manter:

```text
aspect ratio 1:1
```

Independentemente da tela.

Uma casa deve continuar quadrada.

---

# 7. O board não usa tamanho em pixels fixos

## DECIDIDO

Não assumir:

```text
board = 960px
square = 120px
```

O tamanho deve derivar da área disponível.

---

# 8. Dimensão da casa

Conceitualmente:

```text
squareSize = boardSize / 8
```

O renderer visual pode trabalhar com float quando necessário.

---

# 9. Bordas e coordenadas

## PROPOSTO

O board pode exibir:

```text
a b c d e f g h
1 2 3 4 5 6 7 8
```

de forma discreta.

Objetivos:

- aprendizado;
- orientação;
- leitura de notação;
- debug visual.

Não deixar coordenadas competirem com peças.

---

# 10. Orientação padrão

A FIDE determina que o canto próximo à direita do jogador seja uma casa clara.

Para o modo local padrão:

## PROPOSTO

Brancas embaixo inicialmente.

Isso produz a orientação tradicional:

```text
a1 no canto inferior esquerdo das brancas
h1 no canto inferior direito
```

A FIDE também estabelece que o canto inferior direito seja claro.  
Fonte oficial: FIDE Laws of Chess, Artigo 2.1.

---

# 11. Flip board

## FUTURO / PENDENTE

Possibilidades:

- board sempre com brancas embaixo;
- girar automaticamente a cada turno;
- botão de flip;
- configuração.

Não decidir neste documento.

---

# 12. Dois jogadores no mesmo dispositivo

## DECIDIDO COMO PRIMEIRA MODALIDADE

A UI deve considerar que duas pessoas podem estar fisicamente em lados opostos do aparelho.

Isso influencia:

- orientação;
- nome dos jogadores;
- timer;
- leitura;
- ações.

---

# 13. Rotação automática do board por turno

## NÃO DECIDIDO

Pode ajudar partidas frente a frente.

Também pode:

- desorientar;
- tornar histórico/coordinates instáveis;
- gerar animações excessivas.

Precisa teste de UX.

---

# 14. Seleção por toque

A FIDE Online Chess Regulations estabelece como mínimo em uma zona de jogo virtual a possibilidade de selecionar origem e destino do movimento.

## PROPOSTO PARA GAMBITOL

Fluxo principal:

```text
tocar peça própria
↓
seleção visual
↓
tocar destino
↓
motor valida
↓
movimento ou feedback
```

Isso combina com interação touch natural.

---

# 15. Drag-and-drop

## FUTURO / CANDIDATO

Pode coexistir com tap-tap.

Mas a primeira interação pode ser apenas:

```text
source tap + destination tap
```

por simplicidade e acessibilidade.

---

# 16. Não tornar drag obrigatório

## DECIDIDO COMO PRINCÍPIO

Um usuário deve conseguir realizar uma jogada sem depender de precisão de arrastar.

Isso ajuda:

- acessibilidade motora;
- telas pequenas;
- TalkBack/teclado futuros;
- previsibilidade.

---

# 17. Estado visual da peça selecionada

## PROPOSTO

Quando selecionada, comunicar por mais de uma pista possível:

- contorno;
- glow discreto;
- alteração de fundo da casa;
- pequena elevação visual;
- outro sinal consistente.

Não depender apenas de mudança mínima de cor.

---

# 18. Casa de origem selecionada

Precisa ser distinguível de:

- último movimento;
- casa em xeque;
- destino possível;
- captura possível.

---

# 19. Movimentos legais

## PROPOSTO

Após selecionar peça:

o board pode destacar destinos legais.

Mas:

> somente o motor fornece a lista.

A UI não calcula movimento.

---

# 20. Highlight de movimento normal

Pode ser:

- ponto central;
- preenchimento discreto;
- outra marca.

Precisa continuar visível sobre:

- casa clara;
- casa escura.

---

# 21. Highlight de captura

## PROPOSTO

Diferenciar captura de movimento vazio.

Exemplo visual:

- anel ao redor da peça destino;
- contorno externo;
- marcador distinto.

Não usar apenas cor vermelha.

---

# 22. Cor não pode ser a única informação

A documentação Android recomenda usar mais de uma affordance para informações importantes e alerta para daltonismo.

Logo:

```text
verde = movimento
vermelho = captura
```

sozinho é insuficiente.

---

# 23. Último movimento

## PROPOSTO

Destacar:

- origem;
- destino;

do último lance.

Com prioridade visual menor que seleção atual.

---

# 24. Precedência de highlights

## PROPOSTO

Da maior prioridade:

```text
CHECK / DANGER
↓
SELEÇÃO ATUAL
↓
DESTINOS LEGAIS
↓
ÚLTIMO MOVIMENTO
↓
DECORAÇÃO
```

Essa prioridade precisa ser validada visualmente.

---

# 25. Xeque

## DECIDIDO COMO NECESSIDADE DE UX

Quando rei está em xeque:

o usuário precisa perceber.

A UI pode usar:

- contorno;
- halo;
- fundo da casa;
- ícone/status textual.

Não usar animação alarmante contínua.

---

# 26. Xeque não deve parecer game over

Diferenciar:

```text
CHECK
```

de:

```text
CHECKMATE
```

---

# 27. Xeque-mate

## PROPOSTO

Quando o motor retornar mate:

1. board mantém posição final;
2. input de movimento é bloqueado;
3. resultado é apresentado claramente;
4. vencedor é indicado;
5. ações pós-jogo aparecem.

Evitar trocar imediatamente para outra tela e esconder a posição final.

---

# 28. Stalemate / empate

Mesma lógica:

- manter posição;
- informar razão;
- não apenas escrever “Empate”.

Exemplos futuros:

```text
Empate por afogamento
Empate por repetição
Empate por regra dos 75 movimentos
```

---

# 29. Razão do resultado

O documento 05 determina que razões de empate/vitória são semanticamente diferentes.

A UI deve preservar essa diferença.

---

# 30. Promoção

## DECIDIDO COMO FLUXO OBRIGATÓRIO

Quando promoção precisa de escolha:

o usuário deve escolher entre:

- dama;
- torre;
- bispo;
- cavalo.

---

# 31. Modal de promoção

## PROPOSTO

Usar diálogo/overlay simples:

```text
Escolha a promoção
[Q] [R] [B] [N]
```

com peças visuais da mesma cor do peão.

---

# 32. Promoção não pode esconder underpromotion

Mesmo que dama seja mais comum:

- torre;
- bispo;
- cavalo;

precisam estar disponíveis.

---

# 33. Autoqueen

## FUTURO / PENDENTE

A FIDE Online Chess Regulations permite auto-promotion to queen como opção configurável em ambiente online.

No Gambitol:

pode ser futura preferência.

Não ativar como comportamento invisível inicialmente.

---

# 34. Move confirmation

## FUTURO

A FIDE Online Chess Regulations também reconhece confirmação de movimento como opção.

Para jogo local touch:

confirmação em todo lance provavelmente adicionaria fricção.

Não usar por padrão.

Pode fazer sentido em:

- modo acessível;
- configuração;
- partidas muito específicas.

---

# 35. Premove

## NÃO PERTENCE AO MVP LOCAL

É conceito relevante a jogo online.

Não incluir agora.

---

# 36. Smart move

## NÃO PERTENCE AO MVP INICIAL

Seleção de uma única casa quando só existe um destino possível pode ser confusa para aprendizado e consistência.

---

# 37. Feedback de jogada ilegal

## DECIDIDO

Tentativa ilegal não deve:

- mover peça visualmente e depois teleportar;
- trocar turno;
- apagar seleção sem necessidade;
- emitir erro técnico.

---

# 38. Forma de feedback ilegal

## PROPOSTO

Uma combinação discreta:

- pequeno feedback háptico futuro;
- breve animação de resistência/shake opcional;
- mensagem contextual somente quando agrega valor.

Não mostrar toast para cada toque inválido.

---

# 39. Não punir exploração

Um jogador pode tocar casas para entender o board.

A UI não deve parecer hostil.

---

# 40. Selecionar outra peça própria

## PROPOSTO

Quando uma peça está selecionada e o usuário toca outra peça própria:

trocar seleção.

Isso reduz necessidade de “desselecionar” primeiro.

---

# 41. Tocar seleção novamente

## PROPOSTO

Pode cancelar seleção.

---

# 42. Tocar fora do board

## PROPOSTO

Não deve produzir movimento.

Pode manter seleção ou limpar dependendo de teste de UX.

---

# 43. Tocar peça adversária sem seleção

## PROPOSTO

Não seleciona para movimento.

Pode não fazer nada.

---

# 44. Feedback tátil

## FUTURO / CANDIDATO

Haptics podem acompanhar:

- seleção;
- captura;
- movimento inválido;
- fim de partida.

Devem ser sutis e configuráveis.

---

# 45. Áudio

## FUTURO / CANDIDATO

Sons possíveis:

- move;
- capture;
- check;
- game over.

Não obrigatórios.

---

# 46. Áudio não carrega informação sozinho

Usuário pode:

- estar sem som;
- ter deficiência auditiva.

Todo estado importante precisa de feedback visual/textual.

---

# 47. Player panels

## DECIDIDO COMO REFERÊNCIA VISUAL

A direção aprovada possui blocos para os dois jogadores.

Podem conter futuramente:

- nome;
- cor;
- timer;
- peças capturadas;
- indicador de turno.

---

# 48. Jogador ativo

## DECIDIDO COMO NECESSIDADE

Deve ser identificável rapidamente.

Possíveis sinais:

- borda dourada;
- indicador textual;
- ponto/ícone;
- maior contraste;
- timer ativo.

Não depender só de um brilho sutil.

---

# 49. Cor do jogador

Brancas/pretas devem ser representadas de forma perceptível.

Não usar somente texto:

```text
Jogador 1
Jogador 2
```

se isso dificultar associar ao board.

---

# 50. Nome dos jogadores

## PENDENTE

MVP pode usar:

```text
Brancas
Pretas
```

ou nomes configuráveis.

Não definir produto aqui.

---

# 51. Ratings

## FUTURO / NÃO MVP

Não mostrar rating fictício.

Se não existe sistema de rating:

não simular como feature real.

---

# 52. Avatares

## FUTURO / PENDENTE

Elementos visuais podem ter placeholders no conceito.

Não criar sistema de perfil sem necessidade.

---

# 53. Timer

## FUTURO / PENDENTE DE REGRA

O conceito aprovado suporta cronômetros.

Mas:

- tempo inicial;
- incremento;
- pause;
- comportamento em background;

ainda não estão definidos.

---

# 54. Timer visual

Quando existir:

deve ser:

- extremamente legível;
- estável;
- associável ao jogador correto.

---

# 55. Dígitos tabulares

## PROPOSTO

Para timer, preferir fonte/configuração com números tabulares quando disponível.

Isso evita que largura “pule” quando números mudam.

---

# 56. Timer ativo

Pode receber maior contraste.

Timer inativo não deve desaparecer.

---

# 57. Low-time warning

## FUTURO

Se houver:

não depender apenas de vermelho.

Pode usar:

- ícone;
- pulse discreto;
- texto;
- haptic.

---

# 58. Animação de timer

Não fazer pulse contínuo agressivo.

Pode distrair durante cálculo.

---

# 59. Peças capturadas

## FUTURO / CANDIDATO

Podem aparecer no painel de cada jogador.

---

# 60. Captured pieces não são placar

Evitar sugerir que quantidade capturada determina resultado.

---

# 61. Material advantage

## FUTURO

Se mostrar `+3`, explicar semântica.

Não pertence às regras da partida.

---

# 62. Histórico de jogadas

## FUTURO / CANDIDATO

O mockup prevê faixa de histórico.

No MVP inicial pode ser:

- ausente;
- última jogada apenas;
- lista resumida.

---

# 63. Histórico compacto

Em telefone:

não sacrificar board para mostrar 20 linhas.

Pode usar:

- uma faixa;
- bottom sheet;
- tela separada.

---

# 64. Notação

Quando existir:

usar notação consistente definida pelo motor/formatter.

Não construir string manual na View.

---

# 65. Ações inferiores

O conceito visual possui ações como:

- Undo;
- Restart;
- History;
- Settings;
- ação central de marca.

## IMPORTANTE

A posição no mockup é referência.

A existência funcional de cada ação depende do roadmap.

---

# 66. Restart

## CANDIDATO FORTE PARA MVP

Precisa confirmação se apagar partida em andamento.

---

# 67. Confirmação de restart

## PROPOSTO

Se já houve jogada:

```text
Reiniciar a partida?
Cancelar / Reiniciar
```

Evitar reset por toque acidental.

---

# 68. Undo

## PENDENTE

Em jogo local casual pode ser útil.

Mas altera experiência/regra do produto.

Não implementar só porque existe botão no conceito.

---

# 69. History

## PENDENTE

Se não existir histórico funcional:

não deixar botão morto.

---

# 70. Settings

## PENDENTE

Somente quando houver configurações reais.

---

# 71. Ação central de coroa

## IDENTIDADE VISUAL, FUNÇÃO PENDENTE

Não inventar comportamento.

Pode ser:

- home;
- menu;
- branding;
- nenhuma ação.

Precisa decisão.

---

# 72. Botão sem função é proibido em release

## DECIDIDO

Elemento interativo aparente precisa responder ou ser removido.

---

# 73. Disabled controls

Se ação temporariamente indisponível:

estado disabled precisa ser perceptível.

Mas não encher UI de controles disabled para features futuras.

---

# 74. Estados da tela

A UI precisa contemplar:

- partida inicial;
- seleção;
- movimento possível;
- movimento inválido;
- xeque;
- promoção;
- jogo terminado;
- confirmação de restart;
- loading apenas se algum dado realmente carregar;
- erro técnico quando existir.

---

# 75. Não criar loading fictício

O jogo local inicia instantaneamente.

Não mostrar spinner só porque layouts modernos têm um.

---

# 76. Empty state

Histórico vazio pode simplesmente estar ausente ou mostrar:

```text
Nenhuma jogada ainda
```

se a área existir.

---

# 77. Erro técnico

Se algo realmente falhar:

não expor stack trace.

---

# 78. Erro do motor

Idealmente, inconsistência interna é bug.

Não transformar em popup casual e continuar com partida corrompida.

---

# 79. Feedback imediato

A seleção deve responder rapidamente.

Jogadas locais não devem parecer atrasadas.

---

# 80. Latência percebida

Para interação direta:

feedback visual inicial pode ocorrer no toque, mas estado lógico final deve continuar vindo do motor.

---

# 81. Animação de peça

## FUTURO / PROPOSTO

Movimento pode ser animado de origem ao destino.

Mas:

- duração curta;
- não bloquear por muito tempo;
- não virar fonte de estado;
- respeitar configurações de animação do sistema quando possível.

---

# 82. Movimento instantâneo é aceitável no MVP

Correção primeiro.

Polimento depois.

---

# 83. Animação deve explicar mudança

A documentação Android recomenda movimento sutil para ajudar o usuário a entender mudanças na UI.

Não usar animação como efeito gratuito.

---

# 84. Captura animada

Pode usar:

- fade;
- scale curto;
- movimento da peça atacante.

Não precisa explosão de partículas numa partida de xadrez local, por mais que a indústria de jogos insista em transformar qualquer clique num evento pirotécnico.

---

# 85. Check animation

Pode ser discreta.

Não piscar incessantemente.

---

# 86. Mate animation

Pode ter maior ênfase que check.

---

# 87. Reduced motion

## PROPOSTO

Evitar depender de movimento para comunicar estado e respeitar preferências de animação do sistema quando APIs usadas permitirem.

---

# 88. Duração das animações

## PENDENTE

Definir após protótipo.

Princípio:

```text
rápida o suficiente para não atrasar o jogo
lenta o suficiente para comunicar mudança
```

---

# 89. MotionLayout

## NÃO NECESSÁRIO POR PADRÃO

Existe e pode ser útil.

Não adicionar dependência/complexidade antes de uma animação justificar.

---

# 90. Property animations

Views possuem APIs suficientes para transições simples.

---

# 91. Tipografia

## DECIDIDO COMO PRINCÍPIO

A tipografia precisa criar hierarquia sem competir com o tabuleiro.

Categorias úteis:

- marca;
- jogador;
- timer;
- status;
- labels;
- histórico.

---

# 92. Fonte da marca

## PENDENTE

Não escolher arquivo/família tipográfica neste documento.

A identidade pode ter uma fonte de display própria.

Licença precisa ser verificada antes de incluir.

---

# 93. Fonte funcional

## PROPOSTO

Preferir fonte altamente legível do sistema/Android ou família aprovada para:

- timers;
- labels;
- histórico;
- status.

---

# 94. Não usar fonte decorativa para tudo

Marca pode ser estilizada.

Informação de jogo precisa ser lida rápido.

---

# 95. Tamanho mínimo

A orientação de acessibilidade do Android recomenda não usar body text menor que 12sp.

No Gambitol:

## DECIDIDO COMO FLOOR GERAL

Evitar texto funcional menor que:

```text
12sp
```

salvo micro-rótulo decorativo que não carregue informação essencial.

---

# 96. Timer maior

Timer é informação de alta prioridade quando feature existir.

Deve ter hierarquia acima de labels.

---

# 97. Text scaling

Não travar altura de containers de forma que texto seja cortado com escala maior.

---

# 98. Ellipsize

Usar apenas quando perda de conteúdo é aceitável.

Nome de jogador pode ser truncado.

Resultado da partida não deveria desaparecer.

---

# 99. Contraste textual

A documentação Android recomenda:

- pelo menos 4.5:1 para texto menor;
- pelo menos 3:1 para texto grande.

## DECIDIDO COMO GATE VISUAL

A paleta final precisa respeitar isso.

---

# 100. Contraste de ícones e elementos gráficos

Alvo recomendado:

```text
3:1
```

contra superfície quando informação relevante.

---

# 101. Dourado não é automaticamente acessível

## CONCEITO IMPORTANTE

Uma cor pode parecer “premium” e ainda ter contraste ruim sobre carvão.

Toda combinação final precisa ser medida.

---

# 102. Verde profundo também precisa ser medido

Especialmente:

- verde sobre preto;
- dourado sobre verde;
- texto cinza sobre preto.

---

# 103. Paleta semântica proposta

Sem hex ainda:

```text
surface_base
surface_elevated
brand_gold
brand_green
text_primary
text_secondary
board_light
board_dark
selection
legal_move
capture_target
danger_check
disabled
```

Nomes concretos de resources serão definidos na implementação.

---

# 104. Cores exatas

## PENDENTE

Não registrar hex sem validação:

- visual;
- contraste;
- board pieces;
- system bars.

---

# 105. Fundo

## DECIDIDO COMO DIREÇÃO

Carvão/preto, não preto absoluto obrigatório.

Um preto levemente elevado pode melhorar:

- profundidade;
- contraste entre superfícies;
- conforto.

---

# 106. Dourado

Usar com parcimônia.

Papéis:

- marca;
- foco;
- turno;
- ação premium/primária.

Não pintar cada borda de dourado.

---

# 107. Verde

Pode conectar a identidade ao xadrez.

Não deve conflitar com:

- board dark squares;
- legal move highlights.

---

# 108. Board light squares

Marfim/creme é direção visual.

Precisa preservar contraste das peças.

---

# 109. Board dark squares

Verde médio/profundo.

Não tão escuro que peças pretas desapareçam.

---

# 110. Peças brancas

Precisam contraste em ambas as casas.

---

# 111. Peças pretas

Mesmo requisito.

---

# 112. Outline das peças

## CANDIDATO

Pode melhorar legibilidade quando cores aproximarem.

Não aplicar sem teste visual.

---

# 113. Sombras

Use apenas se ajudam separação.

Não transformar tabuleiro 2D em relevo excessivo.

---

# 114. Peças

## DIREÇÃO VISUAL

Devem ter:

- silhueta reconhecível;
- consistência;
- tamanho adequado;
- centro visual consistente.

---

# 115. Staunton-like readability

A FIDE usa formas tradicionais reconhecíveis como referência de peças.

Para um app:

## PROPOSTO

Priorizar silhuetas convencionais o suficiente para reconhecimento imediato.

A arte pode ser própria.

---

# 116. Peças abstratas demais

Evitar no padrão inicial.

Tema futuro pode permitir.

---

# 117. Tamanho da peça na casa

## PROPOSTO

Ocupar grande parte da casa sem tocar bordas.

Precisamos deixar espaço para:

- highlight;
- selection ring;
- coordinate labels.

---

# 118. Coordenadas sobre o board

Podem ficar:

- dentro das casas de borda;
- fora do board;
- opcionalmente ocultáveis.

Escolha após protótipo.

---

# 119. Coordenada e acessibilidade

TalkBack não deve depender do texto visual minúsculo.

Cada square acessível precisa anunciar a coordenada.

---

# 120. Acessibilidade: princípio

## DECIDIDO

O Gambitol deve ser utilizável além da percepção puramente visual sempre que tecnicamente razoável.

Android destaca necessidades envolvendo:

- visão;
- daltonismo;
- audição;
- destreza;
- cognição.

---

# 121. Touch target mínimo

## DECIDIDO

Elementos interativos externos ao board:

```text
mínimo recomendado 48dp × 48dp
```

A documentação Android recomenda esse tamanho focalizável/tocável.

---

# 122. Ícone menor pode ter target maior

O desenho pode ter:

```text
24dp
```

e botão/área:

```text
48dp
```

ou maior.

---

# 123. Board squares e 48dp

Em phone compacto, uma square pode ficar menor que 48dp dependendo da largura.

Isso cria um caso especial.

O board é uma grade semântica de 64 alvos adjacentes.

Precisamos:

- maximizar board;
- suportar tap por célula;
- oferecer alternativas acessíveis;
- testar precisão.

Não aumentar cada square a 48dp quebrando o board.

---

# 124. Acessibilidade do board com Views individuais

Se cada casa for uma View:

pode fornecer:

- focus;
- contentDescription;
- state;
- click.

Mas 64 Views possuem trade-offs.

---

# 125. Acessibilidade do board com Custom View

## CRÍTICO

Se uma única Custom View desenhar 64 casas:

TalkBack enxergará apenas a View host se não fizermos trabalho extra.

A documentação oficial Android recomenda expor uma **hierarquia virtual de views** para controles internos de custom views.

---

# 126. ExploreByTouchHelper

## PROPOSTO SE CUSTOM VIEW FOR ESCOLHIDA

AndroidX `ExploreByTouchHelper` pode fornecer:

- nós virtuais;
- accessibility focus;
- ações;
- bounds;
- descrições.

Fonte oficial:

Android Developers — Make custom views more accessible.

---

# 127. Nó virtual por square

## PROPOSTO

Cada casa pode ser um elemento acessível virtual com informação como:

```text
e4, peão branco, selecionado
```

ou:

```text
f6, vazio, movimento disponível
```

A frase final será testada com TalkBack.

---

# 128. Não anunciar informação demais

Um square com 40 palavras torna navegação lenta.

Precisamos priorizar:

1. coordenada;
2. peça;
3. estado relevante;
4. ação.

---

# 129. Ordem de foco do board

## PENDENTE

Pode seguir orientação visual:

- ranks/files;
- perspectiva do jogador;
- ordem lógica fixa.

Precisa teste com usuários/TalkBack.

---

# 130. Ação acessível

Uma square selecionável precisa oferecer equivalente de:

```text
click
```

---

# 131. Seleção acessível

TalkBack deve anunciar mudança de estado.

---

# 132. Movimento legal acessível

Podemos anunciar destinos legais após seleção.

Mas cuidado com verbosity.

---

# 133. Xeque acessível

Precisa texto/evento, não apenas glow.

---

# 134. Mate acessível

Resultado deve ser anunciado.

---

# 135. Timer acessível

Não anunciar cada segundo com TalkBack.

Isso seria tortura.

Pode expor valor quando focado e anunciar milestones/alertas relevantes.

---

# 136. Peças capturadas acessíveis

Somente se informação disponível na UI.

---

# 137. `contentDescription`

Android recomenda descrições que comuniquem propósito, sem dizer redundâncias como “botão” quando o serviço já anuncia o papel.

---

# 138. Decorative graphics

Devem ser removidos da árvore de acessibilidade quando não carregam informação.

---

# 139. Logo

Pode ser decorativo na tela de jogo se título já existe.

Não obrigar TalkBack a anunciar “logo dourado de coroa” toda vez.

---

# 140. Ícones de ação

Precisam descrição única e clara.

---

# 141. Teste com TalkBack

## GATE FUTURO

Antes de release:

navegar fluxo principal.

---

# 142. Scanner de acessibilidade

Pode ajudar detectar:

- touch targets;
- contraste;
- labels.

Não substitui uso real.

---

# 143. Acessibilidade e cor

Movimentos/capturas/check precisam forma ou texto adicional.

---

# 144. Acessibilidade e movimento

Não depender de animação.

---

# 145. Acessibilidade e áudio

Não depender de som.

---

# 146. Acessibilidade e tamanho de fonte

Containers precisam tolerar.

---

# 147. Acessibilidade cognitiva

Evitar:

- excesso de controles;
- ícones obscuros;
- mudanças de posição inesperadas;
- animações gratuitas.

---

# 148. Ícones

## DECIDIDO COMO PRINCÍPIO

Ícone precisa ter significado reconhecível ou label.

Não usar símbolos crípticos só porque parecem sofisticados.

---

# 149. Undo icon

Se existir:

ícone padrão de desfazer pode ser reconhecível.

Ainda pode precisar contentDescription.

---

# 150. Restart icon

Cuidado para diferenciar de:

- refresh;
- rematch.

Label ajuda.

---

# 151. Histórico icon

Precisa significado consistente.

---

# 152. Settings

Engrenagem é convencional.

---

# 153. Ação destrutiva

Restart não deve ficar visualmente igual a ação segura se um toque puder apagar progresso.

Confirmação resolve parte.

---

# 154. Hierarquia de ações

## PROPOSTO

Na partida:

Primárias:

- jogar no board.

Secundárias:

- restart;
- history;
- settings;
- undo futuro.

O board é a ação principal, não um botão gigante “Jogar”.

---

# 155. Botões de texto versus ícone

Em tela pequena:

ícone pode economizar largura.

Mas se significado ficar ambíguo:

usar label.

---

# 156. Bottom controls e insets

Não colocar touch target atrás da navigation bar.

---

# 157. Edge-to-edge

## OBRIGATÓRIO CONSIDERAR

Android 15/API 35 aplica edge-to-edge por padrão quando o app segmenta SDK 35 ou maior.

Logo, o layout precisa lidar com:

- status bar;
- navigation bar;
- cutouts;
- insets.

---

# 158. Edge-to-edge não significa colocar board sob botões do sistema

Conteúdo decorativo pode ocupar fundo.

Controles importantes precisam safe insets.

---

# 159. System bars

## PROPOSTO

No tema escuro:

system bar icons precisam contraste apropriado.

Pode usar fundo translúcido/scrim quando necessário.

---

# 160. Status bar

Cabeçalho pode se estender visualmente atrás.

Conteúdo textual não deve ser encoberto.

---

# 161. Navigation bar

Bottom actions precisam padding/inset.

---

# 162. Gesture navigation

Área de gesto inferior não deve competir com botões pequenos encostados na borda.

---

# 163. Three-button navigation

Também testar.

Android pode aplicar proteção/scrim diferente.

---

# 164. Display cutout

Não colocar informação crítica sob câmera/notch.

---

# 165. WindowInsets

## DECIDIDO COMO PRINCÍPIO TÉCNICO

Layouts Android modernos precisam considerar insets.

Implementação específica ficará no código.

---

# 166. Immersive mode

## NÃO PROPOSTO PARA MVP

Esconder system bars pode aumentar board, mas:

- remove affordances de sistema;
- adiciona complexidade;
- pode prejudicar UX.

Só considerar se teste mostrar benefício real.

---

# 167. Responsividade

## DECIDIDO

Não usar lógica:

```text
if (tablet)
```

como base estrutural.

Android recomenda adaptar à **janela disponível**.

---

# 168. Window Size Classes

Documentação Android atual para Views classifica larguras:

```text
Compact   < 600dp
Medium    600dp–839dp
Expanded  840dp–1199dp
Large     1200dp–1599dp
XLarge    >= 1600dp
```

A altura também é classificada.

---

# 169. Prioridade do Gambitol

## PROPOSTO

Primeiro otimizar:

```text
Compact width / Medium height
```

que representa praticamente os phones portrait.

Depois:

- Medium;
- Expanded;

sem degradar.

---

# 170. Portrait phone

## DIREÇÃO PRINCIPAL DA PRIMEIRA VERSÃO

Board central.

Player panels acima/abaixo.

Ações compactas.

---

# 171. Phone compacto

Se altura for limitada:

reduzir primeiro:

- espaços decorativos;
- logo/cabeçalho;
- histórico expandido.

Não reduzir board até ficar difícil jogar.

---

# 172. Phone curto

Possíveis adaptações:

- header menor;
- history recolhido;
- player panel condensado;
- bottom actions menores visualmente mantendo touch target.

---

# 173. Medium width

Pode aproveitar espaço para:

- painel lateral parcial;
- histórico maior;
- controles menos condensados.

Mas não precisa criar layout completamente diferente no MVP.

---

# 174. Expanded width

Futuro:

```text
board à esquerda
painel de partida à direita
```

é candidato natural.

---

# 175. Expanded layout conceitual

```text
┌────────────────────┬─────────────────────┐
│                    │ players / timers    │
│       BOARD        │ history             │
│                    │ controls            │
│                    │ status              │
└────────────────────┴─────────────────────┘
```

## PENDENTE

Não implementar antes de escopo large screen ser definido.

---

# 176. Não esticar board indefinidamente

Em tablet:

um board de 1000dp pode ser exagerado.

Definir max size visual futuro.

---

# 177. Max width

## PENDENTE

Será calibrado por protótipo.

---

# 178. Multi-window

Mesmo phone/tablet pode oferecer janela menor.

UI deve adaptar à janela, não dispositivo.

---

# 179. Foldables

## FUTURO

Window size classes e WindowManager ajudam.

Não desenhar layout específico agora.

---

# 180. Orientation

## PENDENTE DE PRODUTO

Portrait é foco.

Mas Android moderno está cada vez mais adaptativo, e restrições rígidas de orientação podem ser ignoradas em alguns contextos large-screen.

Melhor construir layout que não exploda quando tamanho muda.

---

# 181. Landscape

Não precisa ser experiência premium no primeiro milestone.

Mas deve evitar:

- crash;
- state loss;
- board cortado sem recuperação.

---

# 182. State preservation

Mudança de janela/orientação não pode reiniciar partida.

Conecta com ViewModel/estado definidos nos documentos 03/08.

---

# 183. Scroll

## PROPOSTO

Tela de partida principal não deve exigir scroll para jogar em tamanho alvo normal.

Em alturas extremamente compactas:

um layout alternativo é melhor que scroll do board.

---

# 184. Board dentro de ScrollView

## EVITAR

Gestos e tamanho podem ficar problemáticos.

---

# 185. Responsividade não é apenas diminuir tudo

Mudar hierarquia quando necessário.

---

# 186. Spacing system

## PROPOSTO

Usar uma escala consistente de espaçamento.

Exemplo conceitual:

```text
4dp
8dp
12dp
16dp
24dp
```

Não é contrato final.

Evitar margens aleatórias:

```text
7dp
13dp
19dp
```

sem motivo.

---

# 187. Touch target e spacing são conceitos diferentes

Um ícone 24dp pode ter target 48dp e margem 8dp.

---

# 188. Cards/panels

Player panels podem usar superfície elevada.

Não precisam sombras pesadas.

---

# 189. Corner radius

## PENDENTE

Manter consistente.

Não usar um raio diferente em cada botão.

---

# 190. Shape hierarchy

Pode haver:

- board quase reto;
- panels médios;
- buttons arredondados.

Definir depois do protótipo.

---

# 191. Material Design

## INFLUÊNCIA, NÃO IDENTIDADE OBRIGATÓRIA

Usaremos fundamentos úteis:

- touch targets;
- type hierarchy;
- semantic color;
- responsive layouts;
- motion funcional;
- accessibility.

O Gambitol não precisa parecer um app Material genérico.

---

# 192. Componentes Material

Podem ser usados quando ajudam:

- dialogs;
- buttons;
- sheets.

Mas tema deve preservar identidade.

---

# 193. Dialogs

Usar para ações que exigem decisão:

- promoção;
- restart;
- resultado talvez.

Não abrir dialog para cada movimento.

---

# 194. Bottom sheet

## FUTURO / CANDIDATO

Pode ser bom para:

- histórico;
- configurações rápidas.

Não necessário inicialmente.

---

# 195. Snackbar

Pode comunicar ação breve não crítica.

Mas não é obrigatório.

---

# 196. Toast

## EVITAR COMO FEEDBACK PRINCIPAL

Tem:

- duração;
- posição;
- acessibilidade/controle limitados.

Não usar para status central de jogo.

---

# 197. Status persistente

Informação como turno/check deve ficar na própria tela.

---

# 198. Microcopy

## DECIDIDO COMO PRINCÍPIO

Texto deve ser curto e claro.

---

# 199. Exemplo

Bom:

```text
Xeque
```

Ruim:

```text
O jogador das peças brancas encontra-se atualmente em uma situação de xeque.
```

---

# 200. Confirmação destrutiva

Clara:

```text
Reiniciar a partida?
Todo o progresso desta partida será perdido.
```

Quando persistência/undo existirem, texto pode mudar.

---

# 201. Termos de xadrez em português

UI principal pode usar:

- Xeque;
- Xeque-mate;
- Empate;
- Roque;
- Promoção.

Código continua inglês.

---

# 202. Tradução futura

Strings em resources.

Não montar frase por concatenação.

---

# 203. Notação permanece padrão internacional

Não traduzir arquivos/ranks.

---

# 204. “Cavalo” visualmente

UI pode dizer Cavalo.

Código:

```text
Knight
```

---

# 205. Loading/error terminology

Só quando aplicável.

---

# 206. Primeira execução

## PENDENTE

Não criar onboarding gigantesco.

Um jogador de xadrez deve poder chegar rápido à partida.

---

# 207. Tutorial

## FUTURO

Se público incluir iniciantes:

pode haver explicações de movimento.

Não faz parte do core UI agora.

---

# 208. Legal move highlights já ajudam aprendizado

Sem tutorial obrigatório.

---

# 209. Hints

## FUTURO

Não confundir:

- highlight de movimento legal;
- dica estratégica.

A segunda é feature separada.

---

# 210. IA hints

Futuro.

---

# 211. Menu inicial

## PENDENTE

O projeto pode começar indo direto para partida durante desenvolvimento.

Tela Home só entra quando roadmap aprovar.

---

# 212. Configurações

Possíveis futuras:

- som;
- haptics;
- board orientation;
- coordinates;
- autoqueen;
- animations.

Não construir agora.

---

# 213. Persistência de preferências

Data layer futura.

UI só expõe.

---

# 214. Restart versus New Game

Terminologia precisa ser consistente.

Durante partida:

```text
Reiniciar
```

Após final:

```text
Jogar novamente
```

pode ser mais natural.

---

# 215. Rematch

Em modo local:

pode manter cores ou inverter.

## PENDENTE

---

# 216. Abandonar

## FUTURO / PENDENTE

Em partida local sem rating, botão de abandonar pode ser desnecessário.

---

# 217. Offer draw

## FUTURO

Regra/UI específica.

---

# 218. Claim draw

Quando threefold/50 estiver implementado:

UI precisa disponibilizar claim de forma compreensível.

---

# 219. Claim não pode encerrar automaticamente

Documento 05 já diferencia.

---

# 220. Notificação de claim

## PROPOSTO

Quando disponível:

mostrar opção discreta.

Não interromper partida com modal obrigatório.

---

# 221. Fivefold/75

Automáticos.

Resultado é apresentado pelo app.

---

# 222. Timer timeout

Futuro.

Resultado precisa indicar razão.

---

# 223. Estado disabled após game over

Board continua visível, mas movimentos bloqueados.

---

# 224. Análise pós-jogo

## FUTURO

Pode permitir navegar histórico.

Não MVP.

---

# 225. Undo pós-jogo

Pendente.

---

# 226. Board coordinates after game

Continuam.

---

# 227. Capturas após game

Continuam visíveis se feature existe.

---

# 228. Resultado overlay

## PROPOSTO

Não cobrir 80% do board.

Preferir card/area que permita ainda ver posição.

---

# 229. Modal bloqueante de resultado

Pode ser fechado/minimizado.

---

# 230. Vitória visual

Evitar confetti exagerado por padrão.

Pode haver polish futuro.

---

# 231. Empate visual

Não tratar como erro.

---

# 232. Input state machine de UI

## PROPOSTO

Conceitualmente:

```text
NO_SELECTION
SELECTED
PROMOTION_PENDING
GAME_OVER
```

Com subestados/feedback.

Não é nome de enum aprovado.

---

# 233. Seleção durante promoção

Bloquear board até escolha/cancelamento conforme regra.

---

# 234. Cancelar promoção

## PROPOSTO

Se jogada ainda não aplicada:

cancelar volta à posição anterior e seleção coerente.

---

# 235. Back button durante promoção

Precisa comportamento definido:

- fecha escolha;
- não sai do app silenciosamente.

---

# 236. Android back

Quando houver múltiplas telas/dialogs:

seguir expectativa do sistema.

---

# 237. Predictive back

## FUTURO

Android moderno possui predictive back.

Quando navegação existir, usar APIs atuais.

---

# 238. Home gesture

Não é controle do jogo.

---

# 239. Ações próximas à borda

Cuidado com system gesture areas.

---

# 240. Hit testing do board

## DECIDIDO

Conversão de toque deve:

- considerar bounds;
- considerar orientation;
- retornar square determinística;
- rejeitar fora.

---

# 241. Touch slop

Se drag entrar:

usar padrões Android para distinguir drag/tap.

Não criar threshold arbitrário em pixels.

---

# 242. Multi-touch

## NÃO NECESSÁRIO

Um movimento não precisa de dois dedos.

Ignorar/controlar eventos adicionais.

---

# 243. Long press

## PENDENTE

Pode futuramente mostrar informação da peça.

Não atribuir função agora.

---

# 244. Double tap

## EVITAR

Pouco descobrível e pode conflitar.

---

# 245. Gesture-only actions

Evitar ação importante sem alternativa visível.

---

# 246. Feedback pressed state

Botões precisam responder visualmente ao toque.

---

# 247. Ripple

Material ripple pode ser usado quando combina.

Board squares podem ter feedback próprio.

---

# 248. Selection persistence

Se Activity recriar:

selection visual deve ser restaurada se fizer sentido.

---

# 249. Animation during recreation

Não precisa continuar exatamente no meio.

Estado final correto é prioridade.

---

# 250. State-driven UI

## DECIDIDO

Renderizar a partir de:

- estado lógico;
- estado de apresentação.

Não mover Views como fonte de verdade.

---

# 251. Re-render completo versus incremental

## PENDENTE DE IMPLEMENTAÇÃO

Board pode redesenhar tudo após jogada.

Em 8×8, isso pode ser simples.

Otimizar somente se necessário.

---

# 252. Custom View performance

Android recomenda evitar alocações caras em `onDraw`.

Se Custom View:

- cache Paints;
- derivar geometry em `onSizeChanged`;
- invalidar quando necessário.

---

# 253. Canvas

## CANDIDATO

Vantagens:

- board compacto;
- controle total;
- animação;
- render eficiente.

Custos:

- accessibility extra;
- hit testing manual;
- drawing manual.

---

# 254. 64 Views

## CANDIDATO

Vantagens:

- accessibility/focus mais naturais;
- IDs/listeners possíveis;
- estrutura intuitiva.

Custos:

- mais Views;
- styling/re-render mais verboso.

---

# 255. Recycler/Grid

## CANDIDATO MENOR

Pode ser usado, mas tabuleiro possui layout fixo e interação específica.

Avaliar antes de escolher.

---

# 256. Decisão do renderer

## PENDENTE

Não escolher neste documento.

Mas accessibility é critério obrigatório da decisão.

---

# 257. Critérios de escolha do renderer

- clareza do código;
- touch mapping;
- accessibility;
- animação;
- performance;
- responsividade;
- testes;
- aprendizado.

---

# 258. Renderer não conhece regras

Qualquer tecnologia:

```text
View
Canvas
Grid
```

apenas apresenta estado.

---

# 259. Asset format

## PENDENTE

Peças podem ser:

- VectorDrawable;
- WebP/PNG;
- desenho custom.

---

# 260. Vector

Vantagem:

- escala;
- pequeno;
- nitidez.

Mas arte complexa pode não converter bem.

---

# 261. Raster

Precisa densidade/resolução adequada.

---

# 262. SVG

Android não usa SVG arbitrário diretamente como drawable padrão; normalmente converte para VectorDrawable ou raster.

---

# 263. Assets de peças não podem pixelar

Gate visual.

---

# 264. Licença de assets

Obrigatória se não forem próprios.

---

# 265. Logo

Asset de marca separado de launcher icon até decisão.

---

# 266. Launcher icon

Não precisa ser a mesma composição completa do logo.

---

# 267. Splash

## FUTURO

Pode usar marca/coroa.

Não atrasar entrada na partida.

---

# 268. Splash Android moderno

Quando implementado, seguir SplashScreen API atual.

---

# 269. Theme startup

Evitar flash branco antes do tema escuro.

Testar launch.

---

# 270. Surface hierarchy

## PROPOSTO

Três níveis no máximo na partida:

- background;
- panel;
- interactive/highlight.

Não criar dez tons de preto.

---

# 271. Elevation

Usar visualmente, não como decoração automática.

---

# 272. Borders

Dourado pode marcar foco/turno.

Não contornar tudo.

---

# 273. Shadows

Em tema escuro, podem ser pouco eficazes.

Borders/tonal surfaces podem funcionar melhor.

---

# 274. Board border

Pode usar moldura discreta.

Não desperdiçar largura em phone.

---

# 275. Logo durante partida

## PROPOSTO

Compacto.

Marca não deve ocupar altura que pertence ao board.

---

# 276. Header em phone baixo

Pode reduzir/ocultar logo textual.

---

# 277. Status da partida

Precisa posição estável.

Exemplos:

```text
Vez das brancas
Xeque
Promoção
```

Não mudar layout inteiro quando texto varia.

---

# 278. Toast de “vez”

Não.

Turn indicator persistente é melhor.

---

# 279. Status e timer

Se timer existir, o painel ativo já pode comunicar turno.

Ainda assim accessibility precisa estado textual.

---

# 280. Move history e status

Não misturar a ponto de usuário não saber qual é qual.

---

# 281. Screen reader announcement de turno

Pode ser útil após movimento.

Não anunciar redundâncias excessivas.

---

# 282. Screen reader announcement de move

## FUTURO

Exemplo:

```text
Cavalo branco de g1 para f3
```

Pode ajudar usuário cego.

Precisa teste de verbosity.

---

# 283. Board navigation sem visão

Uma experiência de xadrez acessível de alto nível exige mais que contentDescription.

## FUTURO AMBICIOSO

Possibilidades:

- navegação square a square;
- ações custom;
- lista de peças;
- anúncio de posição.

Não bloquear MVP, mas arquitetura de renderer não deve impossibilitar.

---

# 284. Accessibility debt

Se escolher Canvas sem nós virtuais:

registrar como bloqueador de release acessível, não esquecer.

---

# 285. Contrast testing

## GATE

Testar combinações reais:

- primary text/background;
- secondary text/background;
- gold/background;
- board pieces/squares;
- legal markers/squares;
- check indicator/squares.

---

# 286. Contrast de board piece

WCAG text ratio não se aplica literalmente a toda arte, mas legibilidade precisa teste visual e, quando elemento funcional gráfico, buscar contraste adequado.

---

# 287. Color blindness

Testar simuladores para:

- deuteranopia;
- protanopia;
- tritanopia;

especialmente highlights.

---

# 288. Verde + vermelho

Evitar ser única distinção entre:

- legal;
- capture.

---

# 289. Text primary

Claro sobre dark.

---

# 290. Text secondary

Não deixar cinza baixo demais.

---

# 291. Disabled

Precisa parecer indisponível sem desaparecer.

---

# 292. Focus

Com teclado/accessibility:

foco deve ser perceptível.

---

# 293. Mouse/trackpad

Futuro em large screens.

Hover pode ser adicionado.

Não necessário no phone MVP.

---

# 294. Keyboard/D-pad

Se large-screen/ChromeOS entrar:

mapear navegação.

---

# 295. Pointer target

Android permite menor que touch em entrada precisa, mas não precisa otimizar agora.

---

# 296. Adaptive typography

Não simplesmente dobrar fonte em tablet.

Usar hierarquia consistente.

---

# 297. Large screen layout

Aproveitar espaço por reorganização, não por gigantismo.

---

# 298. Width classes dinâmicas

Janela pode mudar enquanto app roda.

UI deve recalcular/adaptar.

---

# 299. Qualifiers XML

Views podem usar:

```text
layout/
layout-land/
layout-sw600dp/
```

Mas Android atual também recomenda window size classes para decisões dinâmicas.

---

# 300. Qualifiers não devem proliferar sem necessidade

Começar simples.

---

# 301. Layout duplication

Se vários XMLs tiverem 95% igual:

avaliar estratégia.

---

# 302. ConstraintLayout

Pode ajudar board/panels.

Não obrigatório.

---

# 303. Percent dimensions

Board pode usar constraints/aspect ratio.

---

# 304. `layout_constraintDimensionRatio`

## CANDIDATO

Pode ajudar manter board quadrado em Views.

Implementação real depois.

---

# 305. Hardcoded board height

Evitar.

---

# 306. `wrap_content` em textos dinâmicos

Evita corte, mas precisa layout test.

---

# 307. Bottom action layout

Em phone estreito:

pode usar ações em linha com targets >=48dp.

Se não couber:

reduzir quantidade visível, não targets.

---

# 308. Overflow menu

## FUTURO / CANDIDATO

Ações raras podem ir para menu.

---

# 309. Hierarquia de frequência

Ações frequentes ficam visíveis.

Raras podem ficar secundárias.

---

# 310. Settings durante partida

Rara.

Pode ir overflow.

---

# 311. Restart

Moderada.

---

# 312. Undo

Se existir, frequente no casual.

---

# 313. History

Pode ser secundária.

---

# 314. Brand action

Não deve ocupar slot mais valioso se não tiver função importante.

---

# 315. Ergonomia de uma mão

Board ocupa centro.

Bottom controls acessíveis ao polegar.

Mas jogo naturalmente usa foco visual central.

---

# 316. Jogador superior

No modo dois jogadores frente a frente, alcance não precisa ser do mesmo usuário.

---

# 317. Timer touch

Se relógio de xadrez exigir “pressionar timer” manualmente:

## NÃO PROPOSTO

O motor pode trocar turno automaticamente ao movimento.

Não imitar relógio físico sem necessidade.

---

# 318. Turn after move

UI deve atualizar de forma imediata e inequívoca.

---

# 319. Illegal move

Timer futuro não muda.

UI precisa refletir.

---

# 320. Promotion modal e timer

## PENDENTE

Definir se relógio continua durante escolha.

É regra de produto, não detalhe visual.

---

# 321. Dialog focus

Promoção precisa foco acessível nas opções.

---

# 322. Promotion option labels

Exemplo:

```text
Promover para dama
Promover para torre
...
```

---

# 323. Board pieces contentDescription

Exemplo conceitual:

```text
Cavalo branco em f3
```

---

# 324. Empty square contentDescription

```text
e4, vazia
```

quando foco precisa.

---

# 325. Legal destination state

```text
e5, vazio, destino permitido
```

pode ser útil.

---

# 326. Capture destination state

```text
d5, peão preto, captura permitida
```

---

# 327. Selected square

```text
e4, peão branco, selecionado
```

---

# 328. Last move

Talvez não precise ser anunciado em cada square.

Pode ter status separado.

---

# 329. Check

```text
Rei branco em xeque
```

---

# 330. Game over

Anúncio global.

---

# 331. Accessibility live regions

## FUTURO / CANDIDATO

Podem ser usados para status que deve ser anunciado.

Usar com parcimônia.

---

# 332. Não transformar timer em live region assertivo

Reforço.

---

# 333. Focus after move

## PENDENTE

TalkBack pode manter foco no destino.

Isso parece natural.

Precisa teste.

---

# 334. Focus after promotion

Pode ir para peça promovida/destino.

---

# 335. Focus after dialog close

Restaurar ao contexto de jogo.

---

# 336. Focus after game over

Resultado pode receber foco/anúncio.

---

# 337. Manual accessibility test plan

Futuro:

1. iniciar com TalkBack;
2. encontrar board;
3. selecionar peça;
4. encontrar destino;
5. mover;
6. ouvir turno;
7. promoção;
8. resultado;
9. restart.

---

# 338. Visual test plan

- smallest supported phone;
- typical phone;
- tall phone;
- API 24;
- recent API;
- gesture nav;
- 3-button nav;
- font scale;
- dark surface;
- different brightness.

---

# 339. Brightness

Tema escuro precisa continuar legível em baixa/alta luminosidade.

---

# 340. OLED black

Não assumir que preto absoluto é sempre melhor.

---

# 341. Burn-in

Não é preocupação central para app de uso normal.

---

# 342. Screen timeout

Partidas podem durar.

## FUTURO / PENDENTE

Decidir se app mantém tela ativa durante partida.

Isso afeta bateria e UX.

---

# 343. Keep screen on

Não ativar globalmente sem decisão.

---

# 344. Haptics preference

Se existir:

configurável.

---

# 345. Sound preference

Mesmo.

---

# 346. Animation preference

Pode haver app setting, mas respeitar system first.

---

# 347. Board theme

## FUTURO / MONETIZAÇÃO POSSÍVEL

Não criar sistema agora.

Documento 13 tratará monetização.

---

# 348. Piece theme

Futuro.

---

# 349. Premium visual baseline

Mesmo versão gratuita precisa parecer completa e profissional.

Não degradar core UX para monetização.

---

# 350. Ads

## FUTURO / FORA DA UI BASE

Se existirem:

não interromper:

- cálculo;
- movimento;
- promoção;
- partida crítica.

Documento 13 definirá.

---

# 351. Ad near board

## EVITAR

Risco de toque acidental e distração.

---

# 352. Purchase CTA

Não deve competir com partida.

---

# 353. Analytics event

UI não depende dele.

---

# 354. Privacy

MVP local pode ser simples.

Não pedir permissões sem motivo.

---

# 355. Permissions UI

Se futuramente houver:

explicar contexto antes do system dialog.

---

# 356. No permissions for chess core

O jogo local básico não precisa:

- câmera;
- contatos;
- localização;
- microfone.

---

# 357. Quality gate visual

## DECIDIDO

Uma tela não está pronta porque “parece com o mockup”.

Precisa:

- comportamento;
- adaptação;
- contraste;
- touch;
- accessibility;
- state correctness.

---

# 358. DoD do board visual

- [ ] quadrado;
- [ ] 8×8 correto;
- [ ] orientação correta;
- [ ] peças legíveis;
- [ ] touch mapping correto;
- [ ] seleção;
- [ ] legal moves;
- [ ] captures;
- [ ] last move;
- [ ] check;
- [ ] custom-view accessibility se aplicável;
- [ ] tamanhos testados.

---

# 359. DoD do player panel

- [ ] cor/jogador claro;
- [ ] turno;
- [ ] timer quando feature existir;
- [ ] texto escalável;
- [ ] contraste;
- [ ] sem fake rating/avatar.

---

# 360. DoD do bottom controls

- [ ] só features reais;
- [ ] >=48dp targets;
- [ ] labels/content descriptions;
- [ ] safe inset;
- [ ] destructive actions protegidas.

---

# 361. DoD da promoção

- [ ] 4 opções;
- [ ] mesma cor;
- [ ] touch;
- [ ] keyboard/accessibility;
- [ ] cancel policy;
- [ ] board state consistente.

---

# 362. DoD do game over

- [ ] razão;
- [ ] vencedor quando existe;
- [ ] board visível;
- [ ] input bloqueado;
- [ ] ações pós-game;
- [ ] accessibility announcement.

---

# 363. DoD responsivo

- [ ] compact width;
- [ ] height limitada;
- [ ] insets;
- [ ] recent Android edge-to-edge;
- [ ] no clipped controls;
- [ ] board jogável.

---

# 364. DoD acessibilidade

- [ ] touch targets;
- [ ] contrast;
- [ ] descriptions;
- [ ] color-independent state;
- [ ] TalkBack critical flow;
- [ ] font scaling;
- [ ] custom view virtual nodes se necessário.

---

# 365. UI anti-patterns proibidos

- regra de xadrez dentro da View;
- piece position derivada de pixels;
- button morto;
- text hardcoded;
- cor como única informação;
- target minúsculo;
- board não quadrado;
- board cortado por navigation bar;
- Activity como estado da partida;
- timer como TextView state;
- promoção apenas para queen sem opção;
- custom Canvas invisível ao TalkBack;
- animação bloqueando jogo;
- toast para tudo;
- layout fixo em pixels;
- fake rating;
- feature futura apresentada como funcional.

---

# 366. Anti-pattern: mover peça antes de validar

Evitar:

```text
UI anima
↓
motor rejeita
↓
UI volta
```

como fluxo padrão.

Pode causar sensação de bug.

---

# 367. Estratégia melhor

```text
input
↓
motor valida
↓
anima estado confirmado
```

Feedback imediato de seleção pode preceder.

---

# 368. Anti-pattern: highlight calculado na UI

Lista vem do engine.

---

# 369. Anti-pattern: view IDs como coordenadas de domínio

Evitar lógica do tipo:

```text
R.id.square_37 significa e4
```

como domínio.

Adapter pode mapear, mas engine usa Position.

---

# 370. Anti-pattern: strings definindo peças

Não:

```text
if (contentDescription == "cavalo")
```

---

# 371. Anti-pattern: board orientation altera engine coordinates

Nunca.

---

# 372. Anti-pattern: player panel define turno

Panel renderiza turno.

Motor define.

---

# 373. Anti-pattern: timer troca turno por UI event separado

Movimento aprovado é owner da troca, conforme regra do produto.

---

# 374. Anti-pattern: usar disabled opacity baixa demais

Pode quebrar contraste.

---

# 375. Anti-pattern: gold-on-ivory

Provavelmente baixo contraste.

Medir.

---

# 376. Anti-pattern: black piece on dark green without outline

Testar.

---

# 377. Anti-pattern: tiny coordinates

Se informação relevante, legível.

Se decorativa, não depende dela.

---

# 378. Anti-pattern: labels em inglês misturados com português

UI deve ser consistente.

---

# 379. Anti-pattern: “CHECKMATE!” e “Xeque” em estilos desconexos

Design system semântico.

---

# 380. Design tokens

## PROPOSTO

Mesmo com XML/Views:

criar recursos semânticos para:

- colors;
- dimensions;
- text styles;
- shapes quando aplicável.

---

# 381. Não criar design system empresarial enorme

Alguns tokens consistentes bastam.

---

# 382. Typography roles

Exemplo conceitual:

```text
brand
player_name
timer
status
body
label
```

---

# 383. Spacing roles

Exemplo:

```text
space_xs
space_sm
space_md
space_lg
```

Nomes exatos pendentes.

---

# 384. Board colors como tokens

Sim.

---

# 385. Selection colors como tokens

Sim.

---

# 386. Theme resource

Pode centralizar superfícies/textos.

---

# 387. Shape resources

Somente quando repetição justificar.

---

# 388. Drawable states

Buttons podem usar Material/state list conforme tecnologia.

---

# 389. Pressed/selected/focused

Todos precisam ser distinguíveis quando relevantes.

---

# 390. Accessibility focus

Visualmente diferente do selection de chess se ambos aparecem simultaneamente.

---

# 391. Keyboard focus versus chess selection

Não confundir.

---

# 392. Screen reader focus

Também.

---

# 393. Visual density

Premium não significa vazio enorme.

Phone precisa eficiência.

---

# 394. Information density

Chess tem muita informação natural.

Evitar adicionar ruído.

---

# 395. Header

Pequeno.

---

# 396. Branding

Presente, não dominante.

---

# 397. Board

Dominante.

---

# 398. Controls

Descobertos, não intrusivos.

---

# 399. Status

Sempre localizável.

---

# 400. Consistency

Mesma ação → mesmo ícone/texto/posição quando possível.

---

# 401. Discoverability

Ação importante não deve estar escondida em gesture secreta.

---

# 402. Forgiveness

Ação destrutiva confirmada.

Jogada ilegal não destrói estado.

---

# 403. Feedback

Toda ação válida tem resposta perceptível.

---

# 404. Constraints

UI não oferece opção impossível.

Exemplo:

não habilitar claim draw sem direito.

---

# 405. Disabled versus hidden

Se usuário precisa entender que feature existe mas indisponível por estado:

disabled pode ser melhor.

Se feature nem existe no MVP:

hidden/ausente.

---

# 406. Promotion choices

Enabled conforme regra, sempre as quatro.

---

# 407. Castling

Não precisa botão especial.

Usuário seleciona rei/destino.

---

# 408. En passant

Também aparece como movimento legal normal.

Não exige UI especial além de animação/captura correta.

---

# 409. Draw claim

Precisa ação especial quando implementada.

---

# 410. Resignation

Ação especial futura.

---

# 411. Move history scrolling

Se lista:

manter último lance visível.

Não auto-scroll de forma que usuário perca leitura se estiver revisando histórico sem intenção.

---

# 412. Review mode

## FUTURO

Pode permitir navegar lances.

Não confundir com estado da partida.

---

# 413. Board ghost state

Se revisando histórico:

mostrar claramente que não é posição atual.

---

# 414. Undo

Se implementado:

animação reversa opcional.

Engine continua owner.

---

# 415. Restart

Após confirmação:

estado inicial + feedback.

---

# 416. Loading assets

Peças devem estar disponíveis localmente.

Sem placeholder de rede.

---

# 417. Offline-first visual

MVP não mostra indicadores de conexão.

---

# 418. App bars

Pode não precisar top app bar tradicional na partida.

Custom layout é aceitável.

---

# 419. System back affordance

Se tela de partida estiver dentro de navegação:

precisa comportamento seguro.

---

# 420. Exit game

Se back sair e partida não salva:

confirmação talvez necessária.

## PENDENTE

---

# 421. Save/resume future

Pode reduzir necessidade de confirmar saída.

---

# 422. Orientation lock future decision

Não resolver neste arquivo.

---

# 423. Window resizing

Mesmo com portrait preference, não depender de medidas fixas.

---

# 424. Testar com fontScale

Mínimo:

- 1.0;
- aumento relevante.

Detalhe final no plano de teste.

---

# 425. Testar com display size aumentado

Pode mudar dimensões efetivas.

---

# 426. Screenshot de referência

## REGRA

Mockup aprovado deve ser guardado como referência documental, não resource runtime.

---

# 427. Versionar evolução visual

Antes/depois pode entrar no portfólio/documento 14.

---

# 428. Visual regression

Futuro screenshot tests.

---

# 429. A/B testing

## NÃO NECESSÁRIO

Projeto ainda não tem base de usuários.

---

# 430. Analytics para UX

Futuro.

Não coletar por padrão.

---

# 431. Usability testing

## RECOMENDADO ANTES DE PUBLICAÇÃO

Mesmo poucas pessoas podem revelar:

- ícone confuso;
- board pequeno;
- seleção pouco visível;
- restart perigoso.

---

# 432. Teste com jogador experiente

Verifica velocidade e convenções.

---

# 433. Teste com iniciante

Verifica clareza.

---

# 434. Teste com baixa visão/daltonismo

Quando possível.

---

# 435. Acessibilidade não é checklist puramente técnico

TalkBack funcionando não significa UX boa.

---

# 436. First-time-to-move

Métrica de UX conceitual:

usuário deve conseguir iniciar e fazer primeira jogada rapidamente.

Não precisa analytics agora.

---

# 437. Cognitive load

Não mostrar tudo ao mesmo tempo.

---

# 438. Progressive disclosure

Features avançadas em menus/sheets.

Board continua simples.

---

# 439. Menu overflow futuro

Pode concentrar:

- settings;
- flip board;
- resign;
- draw.

---

# 440. Bottom bar minimalista

Ações mais usadas.

---

# 441. Logo/coroa central

Se não tiver função importante:

pode ser visual, não Button.

---

# 442. Branding as ornament

Se decorativo:

não deve ter ripple/focus.

---

# 443. “Premium” não significa dourado em tudo

Reforço necessário.

---

# 444. Deep green + gold

Direção de identidade.

Testar contraste.

---

# 445. Dark theme always

## PENDENTE

Direção principal é dark.

Ainda não está decidido se haverá light theme.

Não construir light agora.

---

# 446. System dark theme

Mesmo se app for dark-only:

ícones das system bars precisam ser configurados corretamente.

---

# 447. Dynamic color

## NÃO PROPOSTO

Material You dynamic color poderia destruir identidade do board/marca.

Não adotar por padrão.

---

# 448. User theme customization

Futuro.

---

# 449. Board theme consistency

Highlights precisam funcionar em qualquer tema futuro.

---

# 450. Contrast matrix futura

Para cada board theme:

testar pieces + highlights.

---

# 451. Color token names sem “green1”

Semânticos ajudam tema.

---

# 452. Asset tint

Não tintar peça de forma que perca contraste.

---

# 453. SVG/vector stroke

Testar em densidades pequenas.

---

# 454. Board lines

Talvez nenhuma grade explícita seja necessária.

Alternância de cor já separa squares.

---

# 455. Outer frame

Discreto.

---

# 456. Coordinate orientation on flip

Se board girar:

coordinates precisam acompanhar visual corretamente.

---

# 457. Piece movement animation on flip

Não misturar duas animações.

---

# 458. Flip animation

Futuro.

Pode ser instantânea ou 180º.

Testar motion comfort.

---

# 459. Opponent panel inverted

No local two-player mode, talvez o painel superior possa ser girado 180º para o oponente.

## PENDENTE

Isso pode melhorar leitura frente a frente, mas complicar UI/acessibilidade.

---

# 460. Timer orientation

Mesmo debate.

---

# 461. Board orientation settings

Futuro.

---

# 462. Screen rotation versus board flip

Conceitos diferentes.

---

# 463. Player perspective

Engine não muda.

---

# 464. Move coordinates in history

Sempre notação canônica.

---

# 465. Promotion modal orientation

Deve ser legível pelo jogador da vez se layout two-player for frente a frente.

Pendente.

---

# 466. Device passing mode

Alternativa:

aparelho fica sempre orientado para um jogador e players revezam.

Pode simplificar.

Não decidido.

---

# 467. Local multiplayer UX precisa teste real

Não dá para resolver completamente por diagrama.

---

# 468. Timer presence changes layout

Não reservar 80dp para timer antes de feature existir.

---

# 469. Layout should degrade gracefully

Feature flags/config podem ocultar áreas.

---

# 470. Invisible View taking space

Evitar `INVISIBLE` quando feature removida deveria liberar espaço; usar estratégia correta.

---

# 471. `GONE`

Pode ser apropriado quando área não existe.

---

# 472. Dynamic layout

State drives visibility.

---

# 473. Layout shifts

Evitar durante interação normal.

Exemplo ruim:

status “Xeque” aparece e empurra board 20dp.

Reservar espaço/overlay quando necessário.

---

# 474. Status area fixed enough

Planejar altura para mensagens curtas.

---

# 475. Promotion overlay

Não redimensiona board.

---

# 476. Result overlay/card

Também não deve reorganizar tudo.

---

# 477. Keyboard

Partida normalmente não abre IME.

Settings/names podem.

Edge-to-edge IME insets quando entrar.

---

# 478. Input names

Futuro.

---

# 479. Auto-capitalization

Futuro.

---

# 480. Errors in name form

Futuro.

---

# 481. Touch feedback on board

Pode usar:

- selection highlight;
- subtle scale.

No MVP, highlight basta.

---

# 482. Hover legal moves

Futuro pointer devices.

---

# 483. Drag piece visual

Se drag:

peça segue dedo e origem mantém indication.

---

# 484. Drag cancellation

Soltar fora → volta sem mutar engine.

---

# 485. Drag legal destination

Motor list already known.

---

# 486. Drag accessibility

Tap-tap continua alternativa.

---

# 487. Move animation after tap

Pode interpolar centers.

---

# 488. Capture animation order

Atacante move e alvo desaparece de maneira compreensível.

---

# 489. En passant animation

Precisa mostrar peão lateral removido.

---

# 490. Castling animation

Rei e torre movem no mesmo evento.

Pode animar simultaneamente.

---

# 491. Promotion animation

Move peão → troca asset no destino.

---

# 492. Mate state after animation

Resultado pode aparecer depois de movimento visual concluir, sem atraso longo.

---

# 493. Animation and state

State já pode estar final.

Renderer anima transição entre snapshots.

---

# 494. User input during animation

## PROPOSTO

Bloquear novos movimentos até animação curta terminar ou processar de forma segura.

MVP pode não ter animação, eliminando problema.

---

# 495. Fast users

Não perder input silenciosamente.

---

# 496. Accessibility during animation

TalkBack deve receber estado final coerente.

---

# 497. Screenshot stable state

Testes visuais devem desativar/esperar animação.

---

# 498. Golden board state

Futuro.

---

# 499. Design review checklist

- [ ] board domina;
- [ ] marca não domina;
- [ ] turno claro;
- [ ] seleção clara;
- [ ] legal/capture diferenciados;
- [ ] check claro;
- [ ] dark contrast medido;
- [ ] actions reais;
- [ ] target >=48dp;
- [ ] safe insets;
- [ ] compact phone;
- [ ] font scaling;
- [ ] accessibility.

---

# 500. UX review checklist

- [ ] primeira jogada óbvia;
- [ ] illegal move não assusta;
- [ ] troca seleção fácil;
- [ ] promotion sem confusão;
- [ ] restart protegido;
- [ ] game over explica razão;
- [ ] estado não some na rotação;
- [ ] não há botão morto.

---

# 501. Responsiveness checklist

- [ ] board 1:1;
- [ ] sem px fixo;
- [ ] compact width;
- [ ] medium width quando suportado;
- [ ] short height;
- [ ] system bars;
- [ ] gesture nav;
- [ ] 3-button nav;
- [ ] cutout;
- [ ] multi-window sanity.

---

# 502. Accessibility checklist

- [ ] 48dp controls;
- [ ] 4.5:1 small text;
- [ ] 3:1 large text/graphics quando aplicável;
- [ ] descriptions;
- [ ] decorative elements excluded;
- [ ] no color-only state;
- [ ] custom board virtual accessibility;
- [ ] TalkBack;
- [ ] font scale;
- [ ] focus visibility.

---

# 503. Mockup fidelity checklist

- [ ] carvão/preto;
- [ ] dourado;
- [ ] verde profundo;
- [ ] board verde/marfim;
- [ ] premium restraint;
- [ ] player hierarchy;
- [ ] lower actions where real;
- [ ] Gambitol identity;
- [ ] no fake features.

---

# 504. Critério para desviar do mockup

Desvio é aceitável quando melhora:

- legibilidade;
- acessibilidade;
- responsividade;
- implementação correta;
- clareza;
- feature scope.

Registrar mudança importante.

---

# 505. Critério para mudar cor

Medir contraste + avaliar identidade.

---

# 506. Critério para remover elemento

Se:

- feature não existe;
- polui;
- reduz board;
- não agrega ação/informação.

---

# 507. Critério para adicionar elemento

Precisa responder:

> qual problema do jogador resolve?

---

# 508. Não adicionar porque “ficou vazio”

Espaço vazio pode ser intencional.

---

# 509. Não adicionar card porque Material tem card

---

# 510. Não adicionar FAB porque Android tem FAB

Chessboard já é ação principal.

---

# 511. Não adicionar bottom nav sem múltiplos destinos primários

---

# 512. Não adicionar hamburger menu por padrão

---

# 513. Navegação do produto

Será definida quando telas existirem.

---

# 514. Home, history, settings

Ainda podem mudar.

---

# 515. Visualização de histórico

Futuro.

---

# 516. Modalidade IA

Futuro.

UI poderá precisar indicar:

- thinking;
- difficulty;
- side.

---

# 517. AI thinking indicator

Não usar spinner bloqueante se board pode continuar visível.

---

# 518. AI move animation

Mesmo renderer.

---

# 519. Multiplayer online

Futuro.

Novos estados:

- connecting;
- opponent disconnected;
- reconnecting;
- draw offer.

Não contaminam MVP local.

---

# 520. Network latency

Futuro.

---

# 521. Offline indicator

Só online.

---

# 522. Notifications

Futuro.

---

# 523. Board interaction while remote pending

Futuro.

---

# 524. Portfolio visual

A UI precisa ser boa o suficiente para screenshots.

Mas screenshots não devem guiar decisões contra UX.

---

# 525. Play Store screenshots

Documento 12/14.

---

# 526. Recording moments

## 🎥 MOMENTO BOM PARA GRAVAR — board responsivo

Mostrar:

- uma mesma UI;
- phone pequeno;
- phone grande;
- board permanece quadrado.

Conceito:

```text
responsive layout ≠ escala fixa
```

---

# 527. 🎥 MOMENTO BOM PARA GRAVAR — pixels para squares

Mostrar touch:

```text
MotionEvent x/y
↓
visual coordinate
↓
chess square
```

e explicar por que engine nunca vê pixel.

---

# 528. 🎥 MOMENTO BOM PARA GRAVAR — acessibilidade de Custom View

Se escolhermos Canvas:

mostrar que TalkBack inicialmente enxerga uma View.

Depois:

virtual nodes para 64 squares.

Conteúdo técnico muito forte.

---

# 529. 🎥 MOMENTO BOM PARA GRAVAR — color-only bug

Mostrar highlights verde/vermelho.

Simular daltonismo.

Adicionar shape/ring.

---

# 530. 🎥 MOMENTO BOM PARA GRAVAR — edge-to-edge

Mostrar bottom controls sendo cobertos pela navigation bar.

Corrigir com insets.

---

# 531. 🎥 MOMENTO BOM PARA GRAVAR — 48dp touch target

Ícone visual pequeno com área de toque acessível.

---

# 532. 🎥 MOMENTO BOM PARA GRAVAR — Activity recreation

UI recomposta do estado sem perder posição.

---

# 533. 🎥 MOMENTO BOM PARA GRAVAR — promoção

Motor entra em estado de escolha e UI oferece 4 peças.

---

# 534. 🎥 MOMENTO BOM PARA GRAVAR — state-driven animation

Animação deriva de dois estados, não move estado do domínio.

---

# 535. COMO EXPLICAR EM ENTREVISTA — UI

> “Na tela de partida eu tratei o tabuleiro como elemento dominante e mantive a UI orientada pelo estado do motor. A interface recebe movimentos legais, seleção e status, mas não calcula regra. Isso evita divergência entre o que o usuário vê e o estado real da partida.”

---

# 536. COMO EXPLICAR EM ENTREVISTA — responsividade

> “Em vez de fixar o layout por modelo de aparelho, a interface foi pensada em função da janela disponível e de window size classes. O board mantém proporção 1:1 e os elementos secundários se reorganizam ou condensam conforme o espaço.”

---

# 537. COMO EXPLICAR EM ENTREVISTA — acessibilidade

> “Como um tabuleiro desenhado em uma Custom View pode parecer um único elemento para serviços de acessibilidade, planejei a exposição das casas como elementos virtuais navegáveis, além de manter touch targets, contraste e estados que não dependem só de cor.”

Usar somente se implementado.

---

# 538. COMO EXPLICAR EM ENTREVISTA — edge-to-edge

> “A UI também trata window insets porque apps que segmentam Android 15 ou superior passam a operar edge-to-edge por padrão em dispositivos compatíveis. Isso evita que controles de partida sejam encobertos pelas barras do sistema.”

---

# 539. Decisões normativas deste documento

## DECIDIDO

Após aprovação:

1. board é elemento visual dominante;
2. board mantém 1:1;
3. UI não calcula regras;
4. movimentos legais vêm do motor;
5. interação primária pode funcionar por source + destination tap;
6. drag nunca será a única forma necessária de jogar;
7. seleção, movimento, captura e check precisam estados distintos;
8. cor não é única pista de informação crítica;
9. resultado mantém posição final visível;
10. promoção oferece Q/R/B/N;
11. botões sem feature real não aparecem como ativos;
12. touch targets externos devem buscar mínimo 48dp;
13. contraste será medido;
14. layout trata system insets;
15. layout não usa tamanho fixo em pixels;
16. responsividade considera janela, não apenas dispositivo;
17. Custom View, se escolhida, exige plano de acessibilidade virtual;
18. estado da partida não vive na UI;
19. animação é feedback, não regra;
20. mockup é direção, não prisão de pixel.

---

# 540. Decisões visuais já estabelecidas

## DECIDIDO COMO DIREÇÃO

- dark charcoal/black base;
- gold brand accent;
- deep green support;
- green/ivory board;
- premium/minimal mood;
- Gambitol/crown identity;
- player panels;
- turn emphasis.

---

# 541. Pontos pendentes

## PENDENTE

1. renderer do board;
2. hex exatos;
3. tipografia final;
4. piece assets;
5. board coordinates on/off;
6. auto flip;
7. drag-and-drop;
8. haptics;
9. sound;
10. timers;
11. undo;
12. history;
13. settings;
14. função da ação central/coroa;
15. home screen;
16. large-screen scope;
17. landscape policy;
18. light theme;
19. animation timings;
20. autoqueen;
21. draw/resign UI;
22. renderer accessibility implementation;
23. max board size em telas grandes.

---

# 542. Fontes — acessibilidade geral Android

## Make apps more accessible (Views)

https://developer.android.com/guide/topics/ui/accessibility/views/apps-views

Usado para:

- touch target 48dp;
- content descriptions;
- controles simples;
- elementos decorativos;
- princípios para Views.

Verificado em: 2026-08-22.

---

# 543. Fontes — contraste e acessibilidade

## Make apps more accessible

https://developer.android.com/guide/topics/ui/accessibility/apps

Usado para:

- 4.5:1 para texto pequeno;
- 3:1 para texto grande;
- 48dp touch targets.

---

## Accessibility — Android Design

https://developer.android.com/design/ui/mobile/guides/foundations/accessibility

Usado para:

- contraste;
- texto;
- gráficos;
- múltiplas affordances.

Verificado em: 2026-08-22.

---

# 544. Fontes — cores

## Android color for mobile design

https://developer.android.com/design/ui/mobile/guides/styles/color

Usado para:

- cor semântica;
- daltonismo;
- contraste;
- paleta controlada.

Verificado em: 2026-08-22.

---

# 545. Fontes — custom view accessibility

## Make custom views more accessible

https://developer.android.com/guide/topics/ui/accessibility/views/custom-views

Usado para:

- virtual view hierarchy;
- AccessibilityNodeProvider;
- ExploreByTouchHelper;
- accessibility focus em componentes internos.

---

## ExploreByTouchHelper

https://developer.android.com/reference/androidx/customview/widget/ExploreByTouchHelper

Usado para:

- virtual nodes;
- ações;
- foco;
- bounds.

Verificado em: 2026-08-22.

---

# 546. Fontes — responsividade Views

## Use window size classes — Views

https://developer.android.com/develop/ui/views/layout/use-window-size-classes

Usado para:

- compact;
- medium;
- expanded;
- large;
- extra-large;
- largura/altura;
- janela dinâmica;
- teste de breakpoints.

Verificado em: 2026-08-22.

---

## Responsive/adaptive design with views

https://developer.android.com/develop/ui/views/layout/responsive-adaptive-design-with-views

Usado para:

- qualifiers;
- layouts adaptáveis;
- integração de Views com window size classes.

Verificado em: 2026-08-22.

---

# 547. Fontes — edge-to-edge

## Display content edge-to-edge in Views

https://developer.android.com/develop/ui/views/layout/edge-to-edge

Usado para:

- Android 15/API 35;
- system bars;
- insets;
- Java `WindowCompat`;
- navigation bar;
- status bar.

---

## Lay out your app within window insets — Views

https://developer.android.com/develop/ui/views/layout/insets

Usado para:

- safe content;
- gestures;
- cutouts;
- system UI.

Verificado em: 2026-08-22.

---

# 548. Fontes — system bars

## Android system bars

https://developer.android.com/design/ui/mobile/guides/foundations/system-bars

Usado para:

- safe zones;
- status bar;
- navigation;
- WindowInsets;
- transparent/translucent bars.

Verificado em: 2026-08-22.

---

# 549. Fontes — animação

## Introduction to animations — Views

https://developer.android.com/develop/ui/views/animations/overview

Usado para:

- animação como comunicação de mudança;
- property animations;
- transições sutis.

---

## MotionLayout

https://developer.android.com/develop/ui/views/animations/motionlayout

Usado para:

- movimento funcional;
- não usar motion como efeito especial desnecessário.

Verificado em: 2026-08-22.

---

# 550. Fontes — Material/typography

## Material Design 3 in Android

https://developer.android.com/develop/ui/compose/designsystems/material3

A página é centrada em Compose, mas os princípios de:

- type hierarchy;
- display/headline/title/body/label;

foram usados apenas como referência de design, não como exigência de tecnologia.

---

## Create an accessible and personalized theme and brand with Material 3

https://developer.android.com/codelabs/m3-design-theming

Usado para:

- hierarquia tipográfica;
- roles;
- shape scale como referência.

Verificado em: 2026-08-22.

---

# 551. Fontes — qualidade visual Android

## Core app quality guidelines

https://developer.android.com/docs/quality-guidelines

A documentação de qualidade do Android reforça critérios como:

- touch targets;
- contraste;
- descrição de elementos;
- adaptação;
- qualidade visual.

Antes da release, consultar versão corrente.

---

# 552. Fontes — FIDE Laws

## FIDE Laws of Chess

https://handbook.fide.com/chapter/e012023

Usado para:

- board 8×8;
- orientação com casa clara à direita;
- terminologia;
- relação com regras do documento 05.

Verificado em: 2026-08-22.

---

# 553. Fontes — FIDE Online Chess Regulations

## Online Chess Regulations

https://handbook.fide.com/chapter/OnlineChessRegulations

Usado como referência complementar para UX digital de xadrez:

- seleção de source/target;
- smart move;
- premove;
- autoqueen;
- move confirmation;
- board virtual.

Esses itens não são automaticamente features do Gambitol.

Verificado em: 2026-08-22.

---

# 554. Hierarquia de autoridade

Para regras:

```text
05_REGRAS_DO_MOTOR + FIDE
```

Para interface Android:

```text
este documento
↓
Android Developers
↓
Material como referência
```

Para mockup:

```text
direção aprovada
↓
acessibilidade/responsividade/correção
```

Se o mockup conflitar com legibilidade ou sistema:

corrigir a implementação, não sacrificar o usuário em nome do screenshot.

---

# 555. Sequência recomendada de implementação da UI

## PROPOSTO

```text
1. board estático correto
2. peças
3. touch mapping
4. seleção
5. legal moves
6. movimento confirmado
7. turno
8. capture feedback
9. check
10. promotion
11. result
12. player panels
13. bottom actions reais
14. responsive polish
15. accessibility
16. animation
17. history/timer conforme roadmap
```

A ordem final pertence ao documento 10.

---

# 556. Primeiro milestone visual

Um board 8×8:

- quadrado;
- responsivo;
- com peças;
- sem regra na View.

---

# 557. Segundo milestone visual

Seleção + destinos legais.

---

# 558. Terceiro milestone visual

Movimento completo + turn indicator.

---

# 559. Quarto milestone visual

Special moves e game states.

---

# 560. Quinto milestone visual

Polimento + accessibility + device matrix.

---

# 561. Regra para implementation review

Antes de aprovar uma tela:

```text
ESTÁ BONITA?
```

é apenas uma pergunta.

Também perguntar:

```text
ESTÁ CORRETA?
É JOGÁVEL?
É LEGÍVEL?
É ACESSÍVEL?
SE ADAPTA?
O ESTADO É CLARO?
```

---

# 562. Frase norteadora

> **No Gambitol, a melhor interface é aquela que faz o jogador pensar na posição do xadrez, e não em como operar o aplicativo.**

---

# 563. Próximo documento

Após aprovação:

`10_ROADMAP_E_ESCOPO.md`

Ele deverá organizar:

- fundação;
- engine;
- tabuleiro;
- interação;
- regras;
- testes;
- UI;
- polish;
- release;
- features futuras;
- limites do MVP;
- critérios de entrada e saída de cada fase;
- dependências entre etapas;
- o que explicitamente não fazer cedo demais.

O documento 09 define:

> **como o jogador vê e controla o Gambitol.**

O documento 10 definirá:

> **em que ordem tudo isso será construído e até onde vai cada versão.**
