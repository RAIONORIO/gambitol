# 01 — VISÃO E OBJETIVOS DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `01_VISAO_E_OBJETIVOS.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir por que o Gambitol existe, qual valor pretende entregar, quais objetivos orientam o projeto e quais limites impedem desvio de propósito  
> **Fonte normativa para:** visão do produto, objetivos gerais, princípios de produto, objetivos de aprendizado, objetivos de portfólio, objetivos comerciais e critérios de sucesso de alto nível  
> **Não cobre em detalhe:** arquitetura, estrutura de packages, regras completas de xadrez, padrões de código, workflow Git, estratégia detalhada de testes, especificação visual completa, roadmap operacional, monetização detalhada ou procedimento de publicação  
> **Documento mestre relacionado:** `00_GUIA_MESTRE.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Propósito deste documento

Este documento responde às perguntas:

- O que é o Gambitol?
- Por que ele está sendo criado?
- Para quem ele pretende gerar valor?
- O que caracteriza uma boa primeira versão?
- O que significa “sucesso” para este projeto?
- Quais objetivos técnicos, educacionais, profissionais e comerciais precisam permanecer alinhados?
- O que o Gambitol não deve se tornar?
- Quais ideias ainda são apenas hipóteses?
- Como evitar que o desenvolvimento vire uma sequência de features sem direção?

O Gambitol não possui apenas um objetivo.

Ele combina:

1. **produto** — um jogo de xadrez para Android;
2. **engenharia** — um software desenvolvido com cuidado técnico;
3. **aprendizado** — um projeto prático de Java e Android;
4. **portfólio** — uma demonstração pública de capacidade profissional;
5. **publicação** — um aplicativo destinado à Google Play Store;
6. **negócio** — um produto com possibilidade real de gerar receita no futuro.

Esses objetivos se complementam, mas às vezes entram em tensão.

Exemplo:

- adicionar uma biblioteca pronta pode acelerar o desenvolvimento;
- implementar uma parte manualmente pode ensinar mais;
- construir tudo manualmente pode atrasar o produto;
- acelerar tudo com código gerado pode destruir o objetivo educacional.

Este documento existe para tornar essas tensões explícitas e fornecer critérios para decisões futuras.

---

# 2. Classificação das informações deste documento

Para evitar que hipótese seja confundida com decisão, este documento usa quatro marcadores.

## DECIDIDO

Decisão aprovada e vigente.

## PROPOSTO

Recomendação considerada boa para o projeto, mas ainda passível de ajuste.

## PENDENTE

Questão que deverá ser decidida antes de determinada etapa.

## FUTURO

Possibilidade conscientemente deixada para versões posteriores.

Sempre que uma seção contiver metas numéricas ou escolhas que ainda não foram aprovadas, elas deverão ser tratadas como **PROPOSTAS**, não como obrigação silenciosamente imposta ao projeto.

---

# 3. Identidade básica do produto

## DECIDIDO

| Item | Estado atual |
|---|---|
| Nome | Gambitol |
| Tipo de produto | Jogo mobile |
| Gênero | Xadrez |
| Plataforma inicial | Android |
| Linguagem principal | Java |
| Package definido | `br.com.raionorio.gambitol` |
| IDE Android | Android Studio |
| Terminal principal | Git Bash no Windows |
| Distribuição desejada | Google Play Store |
| Repositório/pasta principal | `gambitol` |
| Primeira modalidade | partida local entre duas pessoas no mesmo dispositivo |
| Regra arquitetural inicial | motor de xadrez separado da interface Android |
| Referência visual inicial | interface premium escura com dourado e verde, tabuleiro central e foco mobile |

---

# 4. Visão do Gambitol

## Visão curta

> **Gambitol deve ser um jogo de xadrez Android confiável, elegante e agradável de usar, construído com uma base Java bem estruturada, capaz de evoluir de projeto de aprendizado para produto real publicado na Google Play Store.**

Essa frase é propositalmente curta.

Ela não tenta listar features.

Uma visão deve sobreviver a alterações de implementação.

O Gambitol pode trocar:

- biblioteca;
- layout interno;
- técnica de persistência;
- estrutura de navegação;
- estratégia de IA;

sem perder sua visão.

Se uma mudança exige reescrever completamente esta visão, provavelmente estamos mudando o produto, não apenas implementando uma feature.

---

# 5. Visão ampliada

O Gambitol pretende oferecer uma experiência de xadrez mobile que transmita:

- confiança nas regras;
- clareza de interação;
- identidade visual própria;
- resposta rápida ao toque;
- legibilidade do tabuleiro;
- facilidade para iniciar uma partida;
- estabilidade;
- cuidado técnico;
- sensação de produto acabado.

Ao mesmo tempo, seu código deve permitir explicar:

- como o tabuleiro é modelado;
- como movimentos são validados;
- como xeque e xeque-mate são detectados;
- como Android e motor de domínio se comunicam;
- como testes garantem regras;
- como Git organiza a evolução;
- como decisões arquiteturais foram tomadas.

O usuário final não precisa saber nada disso.

Mas o desenvolvedor deve.

Essa dupla exigência é central ao Gambitol:

> **por fora, jogo simples de usar; por dentro, projeto tecnicamente explicável.**

---

# 6. Por que um jogo de xadrez

## DECIDIDO

O xadrez é adequado ao objetivo do projeto porque exige lógica suficientemente rica para ensinar engenharia de software sem depender de gráficos 3D, física complexa ou infraestrutura online desde a primeira versão.

O domínio oferece problemas reais de programação:

- representação de estado;
- coordenadas;
- regras;
- validação;
- exceções às regras gerais;
- turnos;
- histórico;
- detecção de condições;
- composição de objetos;
- algoritmos;
- testes positivos e negativos;
- estados impossíveis;
- persistência;
- evolução futura para IA.

Isso permite que o Gambitol funcione como laboratório de:

- orientação a objetos;
- design de domínio;
- testes;
- arquitetura;
- Android;
- Git;
- depuração.

A complexidade não precisa ser inventada. O próprio xadrez já fornece complexidade suficiente.

---

# 7. Fidelidade às regras do xadrez

## DECIDIDO

O motor do Gambitol deve buscar comportamento consistente com as regras oficiais do xadrez.

A referência normativa externa principal para regras deverá ser a **FIDE Laws of Chess**, mantida pela FIDE.

A FIDE define, entre outras bases:

- tabuleiro 8×8 com 64 casas;
- alternância de turnos;
- brancas iniciando;
- objetivo de dar xeque-mate;
- proibição de deixar ou colocar o próprio rei sob ataque;
- condições de empate;
- regras de movimentos especiais.

O documento detalhado será:

`05_REGRAS_DO_MOTOR_DE_XADREZ.md`

Este documento de visão estabelece apenas o princípio:

> **O Gambitol não deverá inventar silenciosamente regras próprias de xadrez.**

Caso alguma adaptação seja feita por motivos de UX ou modalidade, ela deverá ser:

1. intencional;
2. documentada;
3. distinguida da regra oficial.

Fonte oficial pesquisada:

- FIDE Handbook — Laws of Chess  
  https://handbook.fide.com/chapter/e012023
- FIDE Rules Commission — documentação atual  
  https://rcc.fide.com/documentation/

Verificado em: **2026-08-22**.

---

# 8. Por que Android

## DECIDIDO

O produto inicial será Android e deverá ser tratado como aplicativo mobile real, não como exercício de desktop empacotado para celular.

Isso implica pensar desde cedo em:

- toque;
- diferentes tamanhos de tela;
- ciclo de vida da aplicação;
- interrupções;
- rotação/configuração;
- retomada após sair e voltar;
- recursos Android;
- desempenho;
- bateria;
- acessibilidade;
- políticas de publicação;
- Android App Bundle;
- Play Console.

O Google organiza qualidade de apps e jogos Android em quatro pilares:

1. valor central;
2. experiência do usuário;
3. qualidade técnica;
4. privacidade e segurança.

Esses quatro pilares serão usados como lente de produto do Gambitol.

Fontes:

- Android App Quality  
  https://developer.android.com/quality
- Core app quality guidelines  
  https://developer.android.com/docs/quality-guidelines/core-app-quality

---

# 9. Por que Java

## DECIDIDO

Java não é apenas uma escolha de implementação.

É parte explícita do objetivo educacional e profissional do projeto.

Portanto, quando houver uma escolha entre:

- esconder completamente um conceito relevante; ou
- implementá-lo de modo didático, claro e testável;

o aprendizado deve ser considerado na decisão.

Isso não significa rejeitar bibliotecas úteis.

Também não significa reconstruir APIs inteiras “para aprender”.

A regra é:

> **usar Java de forma real, compreensível e profissional, sem transformar o projeto em um exercício artificial.**

O Gambitol deve permitir praticar, quando fizer sentido:

- classes e objetos;
- encapsulamento;
- herança;
- composição;
- polimorfismo;
- abstração;
- enums;
- coleções;
- generics;
- exceções;
- imutabilidade;
- testes;
- separação de responsabilidades;
- princípios de design.

O documento `02_METODO_DE_ENSINO.md` definirá como esses conceitos serão ensinados.

O documento `06_PADROES_JAVA_E_ANDROID.md` definirá como serão aplicados no código.

---

# 10. Problema de produto que o Gambitol pretende resolver

Não é necessário inventar uma “dor revolucionária” para justificar um jogo.

Jogos também existem para proporcionar diversão, desafio, concentração e uma experiência agradável.

O próprio guia de qualidade Android afirma que o propósito de um app ou jogo é entregar valor sendo útil ou divertido, tanto no primeiro uso quanto ao longo do tempo.

Para o Gambitol, o problema de produto pode ser expresso assim:

> **Permitir que uma pessoa abra o celular e tenha acesso rápido a uma partida de xadrez clara, confiável e visualmente agradável, sem que a tecnologia atrapalhe a experiência do jogo.**

Essa formulação não afirma que o mercado “não possui bons jogos de xadrez”.

Tal afirmação exigiria pesquisa competitiva específica.

O Gambitol não precisa fingir que inventou o xadrez para justificar sua existência.

Ele precisa entregar bem sua própria experiência.

Fonte:

- Android — What great core value looks like  
  https://developer.android.com/quality/core-value

---

# 11. Proposta de valor

## PROPOSTO

A proposta de valor inicial pode ser resumida em cinco elementos:

### 1. Xadrez correto

O usuário deve confiar que um movimento permitido é válido e um movimento proibido é realmente inválido.

### 2. Interação mobile simples

Selecionar peça, identificar movimentos possíveis e jogar deve exigir pouca fricção.

### 3. Identidade visual

O Gambitol deve parecer um produto próprio, não um exemplo genérico do Android Studio.

### 4. Experiência estável

Partidas não podem ser arruinadas por crashes, perda arbitrária de estado ou interface quebrada.

### 5. Evolução consciente

Recursos futuros devem melhorar o xadrez, não soterrar o jogo sob funcionalidades irrelevantes.

---

# 12. Público: o que sabemos e o que ainda não sabemos

## DECIDIDO

O Gambitol é destinado a usuários Android interessados em jogar xadrez.

Isso ainda é amplo.

## PENDENTE

Ainda não foi definido se o posicionamento principal será:

- jogadores casuais;
- iniciantes;
- jogadores intermediários;
- jogadores competitivos;
- pessoas que querem jogar localmente com outra pessoa;
- jogadores que desejam enfrentar IA;
- usuários que querem estudo de xadrez;
- combinação de públicos.

Portanto, **personas detalhadas ainda não devem ser inventadas como fato**.

---

# 13. Hipóteses de público para validar futuramente

## PROPOSTO

Podemos investigar pelo menos três hipóteses.

### Hipótese A — jogador casual mobile

Quer:

- abrir rápido;
- começar rápido;
- interface bonita;
- regras confiáveis;
- pouca configuração.

### Hipótese B — duas pessoas compartilhando o aparelho

Querem:

- jogar uma partida local;
- identificar claramente de quem é a vez;
- tabuleiro legível;
- reiniciar facilmente;
- talvez controlar tempo.

### Hipótese C — pessoa interessada em xadrez com experiência mais completa

Pode valorizar futuramente:

- histórico;
- notação;
- IA;
- estatísticas;
- personalização.

Essas hipóteses deverão ser validadas por:

- observação;
- testes com pessoas;
- feedback;
- dados posteriores de uso, caso analytics seja aprovado;
- avaliações da Play Store.

Não transformar hipótese em arquitetura prematuramente.

---

# 14. Público primário da primeira versão

## PROPOSTO

Para impedir que o MVP tente agradar a todos, a recomendação inicial é considerar como experiência primária:

> **duas pessoas que desejam jogar uma partida completa de xadrez no mesmo celular Android.**

Esse posicionamento combina com a primeira modalidade já definida.

Ele reduz a dependência inicial de:

- backend;
- conta;
- rede;
- matchmaking;
- autenticação;
- anti-cheat online;
- ranking remoto.

A aprovação definitiva desse recorte deverá constar também no documento de escopo.

---

# 15. Objetivo de produto

## DECIDIDO

O Gambitol deve chegar ao ponto em que uma pessoa consiga:

1. instalar o aplicativo;
2. abrir;
3. iniciar uma partida;
4. jogar uma partida de xadrez legalmente válida;
5. entender de quem é a vez;
6. identificar as peças e o tabuleiro;
7. executar movimentos por toque;
8. receber feedback claro sobre movimentos;
9. chegar corretamente a estados de fim de jogo;
10. utilizar o aplicativo sem depender de conhecimento técnico.

O usuário não deve precisar saber:

- o que é `Activity`;
- o que é Gradle;
- que o motor foi escrito em Java;
- como as regras foram testadas.

Esses são detalhes de engenharia.

---

# 16. Objetivo educacional

## DECIDIDO

Ao final do desenvolvimento, o mantenedor deve ser capaz de explicar o projeto e não apenas demonstrá-lo.

Isso significa compreender:

- estrutura de um projeto Android;
- papel do Gradle;
- ciclo de build;
- Activity;
- lifecycle;
- layouts;
- recursos;
- eventos de toque;
- estado;
- Java aplicado a domínio real;
- orientação a objetos;
- testes unitários;
- integração UI/domínio;
- Git;
- branches;
- commits;
- diagnóstico de erros;
- build de release;
- processo de publicação.

O sucesso educacional não é medido por quantidade de aulas.

É medido pela capacidade de:

> **ler, alterar, depurar e explicar o próprio projeto.**

---

# 17. Resultados de aprendizado esperados

## PROPOSTO

Ao longo do Gambitol, o desenvolvedor deverá conseguir demonstrar competências progressivas.

### Java

- modelar entidades;
- escolher composição ou herança conscientemente;
- usar enums;
- trabalhar com coleções;
- escrever métodos com responsabilidade clara;
- entender referências e estado;
- escrever testes;
- interpretar exceptions;
- refatorar código.

### Android

- compreender estrutura do módulo `app`;
- navegar por `src/main`;
- entender Manifest e resources;
- construir e manipular Views;
- responder a input;
- compreender lifecycle;
- preservar estado quando necessário;
- testar em dispositivo/emulador;
- usar Logcat;
- produzir APK/AAB.

### Engenharia

- separar domínio e interface;
- trabalhar incrementalmente;
- validar build;
- revisar `git diff`;
- diagnosticar antes de corrigir;
- manter documentação;
- justificar decisões.

### Produto

- distinguir MVP de ideia futura;
- avaliar trade-offs;
- considerar UX;
- considerar qualidade de release;
- interpretar feedback.

---

# 18. Objetivo de portfólio

## DECIDIDO

O Gambitol deverá ser capaz de servir como projeto real de portfólio.

Isso exige mais do que screenshots.

O projeto deve permitir mostrar:

- código organizado;
- decisões técnicas;
- testes;
- documentação;
- histórico Git coerente;
- aplicativo funcional;
- processo de evolução;
- publicação quando concluída.

---

# 19. O que torna o Gambitol relevante como portfólio

## PROPOSTO

O valor profissional do projeto deverá estar na capacidade de demonstrar:

### Domínio

Um conjunto de regras não trivial.

### Arquitetura

Separação entre motor e Android.

### Testabilidade

Regras que podem ser testadas sem depender da tela.

### Mobile

Aplicação real para Android.

### Qualidade

Build, testes, compatibilidade e revisão.

### Produto

Uma feature pode ser ligada a uma necessidade do usuário.

### Comunicação

Documentação e capacidade de explicar decisões.

### Entrega

Aplicativo efetivamente distribuído.

Isso é mais forte do que um repositório que apenas contém muito código.

---

# 20. Objetivo de reputação profissional

## DECIDIDO

O desenvolvimento do Gambitol também deverá ajudar a construir nome profissional.

Isso pode acontecer por meio de:

- evolução pública do projeto;
- demonstrações;
- conteúdo técnico;
- aprendizados;
- resolução de problemas;
- vídeos de desenvolvimento;
- explicações de Java;
- explicações de Android;
- publicação na Play Store.

O projeto não deve ser artificialmente complicado apenas para gerar conteúdo.

Conteúdo deve nascer de problemas reais e soluções reais.

O método será detalhado em:

`14_CONTEUDO_E_PORTFOLIO.md`

---

# 21. Objetivo comercial

## DECIDIDO

Existe intenção de explorar possibilidade de receita com o Gambitol.

## PENDENTE

Ainda NÃO estão definidos:

- modelo de monetização;
- presença de anúncios;
- produto pago;
- compra única;
- compras internas;
- temas premium;
- assinatura;
- recursos premium.

Nenhum deles deve ser implementado silenciosamente.

O modelo será estudado em:

`13_MONETIZACAO.md`

---

# 22. Princípio comercial

## PROPOSTO

Monetização deverá ser consequência de valor, não substituto de valor.

A ordem desejável é:

```text
jogo confiável
↓
boa experiência
↓
uso real
↓
feedback
↓
compreensão do que os jogadores valorizam
↓
monetização apropriada
```

Evitar:

```text
anúncios
↓
loja
↓
assinatura
↓
...e algum dia talvez o xadrez funcione
```

O Google destaca qualidade e valor ao usuário como fatores relevantes para a experiência e descoberta na Play Store.

Fontes:

- https://developer.android.com/quality
- https://support.google.com/googleplay/android-developer/answer/9958766

---

# 23. Objetivo de distribuição

## DECIDIDO

O Gambitol deve ser preparado para publicação real na Google Play Store.

Isso significa que “funciona no meu celular” não é definição suficiente de pronto.

A preparação deverá incluir, no momento adequado:

- build de release;
- Android App Bundle;
- assinatura;
- versionamento;
- compatibilidade;
- testes;
- políticas;
- ficha da loja;
- recursos gráficos;
- screenshots;
- privacidade;
- canais de teste;
- revisão de qualidade;
- release.

O procedimento detalhado pertence a:

`12_PLAY_STORE_E_RELEASE.md`

---

# 24. Objetivo de qualidade baseado no Android

A documentação oficial do Android define quatro pilares de qualidade.

O Gambitol adota esses pilares como objetivos de produto.

---

## 24.1 Pilar 1 — valor central

O jogo deve ser:

- funcional;
- divertido ou útil no contexto de xadrez;
- coerente com a necessidade do público;
- suficientemente completo para justificar instalação.

O Google Play não permite apps que sejam essencialmente sem propósito, instáveis ou com funcionalidade/conteúdo inadequadamente limitados.

Aplicação ao Gambitol:

> A primeira release pública deve ser capaz de sustentar uma experiência real de xadrez, não apenas mostrar um tabuleiro clicável.

Fontes:

- https://developer.android.com/quality/core-value
- https://support.google.com/googleplay/android-developer/answer/9898783

---

## 24.2 Pilar 2 — experiência do usuário

O usuário deve compreender o que está acontecendo.

Objetivos:

- tabuleiro legível;
- seleção clara;
- feedback visual;
- ações previsíveis;
- botões compreensíveis;
- estado de turno evidente;
- identidade visual consistente;
- controles adequados ao toque.

A documentação recente do Android destaca que apps e jogos de alta qualidade devem ser intuitivos, agradáveis e possuir identidade diferenciada.

Fonte:

https://developer.android.com/quality/user-experience

---

## 24.3 Pilar 3 — qualidade técnica

Objetivos:

- evitar crashes;
- evitar ANRs;
- iniciar adequadamente;
- responder ao toque;
- preservar estado de forma coerente;
- funcionar nas versões Android suportadas;
- usar recursos de maneira responsável;
- evitar consumo desnecessário de memória e bateria.

Fonte:

https://developer.android.com/docs/quality-guidelines/core-app-quality

---

## 24.4 Pilar 4 — privacidade e segurança

O MVP local possui uma oportunidade valiosa:

> não coletar o que não precisa ser coletado.

Princípios:

- minimizar permissões;
- minimizar dados;
- evitar acesso desnecessário a sensores;
- declarar adequadamente qualquer futura coleta;
- proteger dados se futuramente houver conta, multiplayer ou pagamento.

O Android recomenda explicitamente minimização de permissões, localização e exposição de dados.

Fonte:

https://developer.android.com/quality/privacy-and-security

---

# 25. Experiência pretendida

## PROPOSTO

O Gambitol deve transmitir quatro sensações principais.

### Clareza

“Eu sei qual peça selecionei, para onde posso mover e de quem é a vez.”

### Confiança

“O jogo não está permitindo jogadas absurdas nem quebrando a partida.”

### Controle

“Meu toque produz um resultado previsvisível.”

### Acabamento

“Isso parece um jogo real, não um exercício de curso.”

---

# 26. Identidade visual como parte do produto

## DECIDIDO

Existe um mockup inicial aprovado como referência visual.

Características aprovadas conceitualmente:

- fundo predominantemente escuro;
- detalhes dourados;
- verde como cor de destaque;
- marca Gambitol no topo;
- tabuleiro como elemento principal;
- área dos jogadores;
- cronômetros;
- indicação de turno;
- histórico/controles na parte inferior;
- sensação premium.

## IMPORTANTE

A imagem é **referência**, não contrato pixel-perfect.

Ela não prova que todas as features mostradas já pertencem ao MVP.

Exemplos presentes no mockup que ainda podem depender de decisão:

- rating;
- avatares;
- determinadas ações;
- posição final de todos os controles.

O documento especializado será:

`09_UI_UX_GAMBITOL.md`

---

# 27. Princípios de UX

## PROPOSTO

### 27.1 Xadrez primeiro

O tabuleiro é o protagonista.

Elementos secundários não devem competir com ele.

### 27.2 Pouca fricção para jogar

A primeira partida deve exigir o mínimo de passos razoável.

### 27.3 Estado sempre compreensível

A interface deve comunicar:

- jogador atual;
- seleção;
- movimentos válidos;
- captura;
- xeque;
- fim da partida.

### 27.4 Feedback imediato

Toque sem feedback gera dúvida.

### 27.5 Evitar decoração que prejudique leitura

Premium não significa visual carregado.

### 27.6 Consistência

A mesma ação deve se comportar da mesma forma em todo o jogo.

---

# 28. Escopo funcional de alto nível da primeira versão

## DECIDIDO / JÁ DOCUMENTADO NA BASE INICIAL

A primeira versão deve caminhar para:

- tabuleiro 8×8;
- peças completas;
- seleção por toque;
- movimentação;
- alternância de turnos;
- capturas;
- movimentos válidos;
- xeque;
- xeque-mate;
- empate;
- roque;
- en passant;
- promoção.

O detalhamento e critérios de aceite pertencem aos documentos:

- `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
- `08_TESTES_E_QUALIDADE.md`;
- `10_ROADMAP_E_ESCOPO.md`.

---

# 29. Recursos que não devem bloquear o núcleo inicial

## FUTURO

Os seguintes recursos podem ser valiosos, mas não devem ser tratados como pré-requisito para validar o motor básico:

- IA;
- multiplayer online;
- ranking;
- contas;
- sincronização em nuvem;
- chat;
- amigos;
- torneios;
- marketplace;
- assinatura;
- analytics sofisticado;
- achievements;
- leaderboards;
- integração com Play Games Services.

Isso não significa que foram rejeitados.

Significa apenas:

> **não são necessários para provar que o Gambitol consegue ser um bom jogo de xadrez local.**

---

# 30. Não objetivos da primeira etapa

## DECIDIDO

A etapa inicial de engenharia não deve tentar resolver simultaneamente:

- monetização;
- backend;
- multiplayer;
- marketing;
- ranking;
- IA avançada;
- todos os formatos Android;
- todos os idiomas;
- customização extensa.

Primeiro construir a base.

---

# 31. Antiobjetivos permanentes

O Gambitol não deve:

- permitir jogadas ilegais por conveniência de UI;
- concentrar toda lógica em uma Activity;
- depender de comportamento invisível que o desenvolvedor não sabe explicar;
- usar bibliotecas sem saber por que foram adicionadas;
- coletar dados sem necessidade;
- solicitar permissões sem propósito;
- fazer commit de segredo;
- esconder erro com workaround sem diagnóstico;
- tratar código gerado como intocável;
- confundir quantidade de features com qualidade;
- implementar monetização que torne a partida desagradável;
- sacrificar legibilidade do tabuleiro para decoração;
- declarar feature pronta sem validação.

---

# 32. Objetivo de arquitetura em nível de produto

## DECIDIDO

O motor de xadrez deve possuir independência conceitual em relação à interface.

Visão:

```text
┌────────────────────────────┐
│       INTERFACE ANDROID    │
│                            │
│  renderização / interação  │
└──────────────┬─────────────┘
               │
               ▼
┌────────────────────────────┐
│       MOTOR GAMBITOL       │
│                            │
│ estado / regras / domínio  │
└────────────────────────────┘
```

Objetivo:

> ser possível testar grande parte das regras sem precisar abrir uma tela Android.

A arquitetura concreta será definida posteriormente.

Este documento não escolhe agora:

- packages;
- número de camadas;
- patterns;
- repositories;
- ViewModel;
- use cases;
- interfaces específicas.

Essas decisões pertencem aos documentos técnicos.

---

# 33. Qualidade arquitetural desejada

## PROPOSTO

A arquitetura deverá privilegiar:

- clareza;
- separação de responsabilidades;
- baixo acoplamento;
- testabilidade;
- manutenção;
- facilidade de aprendizado;
- evolução incremental.

Evitar:

- abstração sem uso;
- interfaces de um único uso criadas apenas por dogma;
- camadas sem responsabilidade;
- frameworks desnecessários.

---

# 34. Objetivo de testabilidade

## DECIDIDO

Regras críticas não deverão depender somente de testes manuais na tela.

Exemplos que merecem testes automatizados:

- movimento legal;
- movimento ilegal;
- bloqueio;
- captura;
- xeque;
- xeque-mate;
- roque;
- promoção;
- en passant;
- empate.

O objetivo é conseguir corrigir o motor sem depender da esperança de não ter quebrado outra regra.

---

# 35. Objetivo de estabilidade

## DECIDIDO

Uma partida não deve ser considerada adequada para release quando:

- o app fecha inesperadamente;
- trava;
- perde estado sem explicação;
- permite estado impossível;
- corrompe a partida;
- ignora regra crítica.

A Play Store utiliza estabilidade e qualidade técnica como sinais importantes.

Os relatórios de pré-lançamento podem detectar:

- crashes;
- ANRs;
- compatibilidade;
- desempenho;
- acessibilidade.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9842757

---

# 36. Objetivo de desempenho

Xadrez não é um jogo que exige o mesmo perfil de renderização de um shooter 3D.

Portanto, o objetivo não será perseguir métricas de FPS sem contexto.

## DECIDIDO

O usuário deve perceber:

- resposta rápida ao toque;
- transições suaves;
- ausência de congelamentos;
- baixo tempo de espera;
- comportamento estável durante a partida.

As diretrizes atuais do Google Play Games possuem metas específicas de frame rate para determinados jogos, mas também reconhecem exceções para experiências que não renderizam continuamente ou usam mecanismos como HWUI/Composer.

Portanto:

> **não transformar “60 FPS” em requisito cego antes de conhecer o modelo de renderização real do Gambitol.**

Fonte:

https://developer.android.com/games/guidelines

---

# 37. Objetivo de acessibilidade

## PROPOSTO

Mesmo sendo um jogo visual, acessibilidade deve ser considerada desde a construção.

Aspectos:

- contraste;
- tamanho de toque;
- legibilidade;
- labels quando aplicável;
- não depender apenas de cor para informação importante;
- escala de texto nos elementos textuais quando possível;
- feedback compreensível.

O relatório de pré-lançamento da Play Store testa oportunidades relacionadas a:

- labels;
- touch targets;
- implementação;
- contraste.

Fontes:

- https://developer.android.com/guide/topics/ui/accessibility/testing
- https://support.google.com/googleplay/android-developer/answer/9844487

---

# 38. Objetivo de adaptação a telas

## DECIDIDO

O Gambitol nasce como jogo mobile para celular Android.

## PROPOSTO

A arquitetura visual deve evitar decisões que impossibilitem adaptação futura.

O ecossistema Android inclui:

- celulares compactos;
- tablets;
- dobráveis;
- janelas redimensionáveis;
- ChromeOS e outros formatos.

Isso NÃO significa que a primeira release precise possuir UI diferenciada para todos.

A recomendação é:

> construir primeiro uma experiência excelente em smartphone, evitando hardcodes desnecessários que tornem outras telas impossíveis.

Fontes:

- https://developer.android.com/develop/adaptive-apps
- https://developer.android.com/guide/topics/large-screens

---

# 39. Objetivo de privacidade

## PROPOSTO COMO PRINCÍPIO FORTE

Para o núcleo local:

> **zero coleta desnecessária é melhor que coleta “porque talvez usemos depois”.**

Se uma feature não precisa de:

- câmera;
- microfone;
- contatos;
- localização;
- arquivos externos;
- telefone;

não solicitar essas permissões.

Se analytics for considerado futuramente, ele deverá passar por:

- decisão;
- definição de eventos;
- minimização;
- política de privacidade;
- Data Safety;
- transparência.

Fonte:

https://developer.android.com/quality/privacy-and-security

---

# 40. Objetivo de segurança

A primeira versão local possui superfície menor do que uma versão online.

Ainda assim:

- dependências devem ser revisadas;
- segredos não podem entrar no Git;
- dados locais devem ser tratados de forma apropriada;
- futuras comunicações de rede deverão ser seguras;
- futuras autenticações deverão usar práticas modernas.

A segurança deve crescer junto com a superfície do produto.

Não adicionar complexidade de autenticação onde ainda não existe identidade de usuário.

---

# 41. Objetivo de publicação: produto, não apenas build

Um APK gerado não significa que o produto está pronto.

Uma release pública deverá considerar:

1. funcionalidade;
2. regras;
3. testes;
4. estabilidade;
5. UI;
6. acessibilidade;
7. compatibilidade;
8. privacidade;
9. metadados da loja;
10. screenshots;
11. ícone;
12. políticas;
13. canais de teste;
14. feedback.

A qualidade da presença na loja também influencia descoberta e percepção.

O Google Play informa que a qualidade pré-instalação inclui:

- título;
- ícone;
- screenshots;
- vídeos;
- descrição;
- links.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9958766

---

# 42. Critério de sucesso da primeira versão pública

## PROPOSTO

Uma primeira release pública bem-sucedida não precisa ter centenas de funcionalidades.

Ela deve conseguir afirmar honestamente:

> “O Gambitol é um jogo de xadrez Android funcional, estável, compreensível e visualmente consistente, no qual uma partida pode ser iniciada e concluída respeitando as regras implementadas.”

Critérios de alto nível:

- partida completa funcional;
- regras críticas cobertas;
- interface utilizável;
- sem bloqueadores conhecidos;
- testes relevantes passando;
- build de release válido;
- fluxo de instalação e abertura funcionando;
- qualidade de Play Console revisada;
- documentação coerente com o estado real.

---

# 43. O que NÃO define sucesso

Não considerar sucesso automaticamente:

- quantidade de linhas;
- quantidade de classes;
- quantidade de padrões de projeto;
- quantidade de telas;
- número de branches;
- UI bonita sem motor confiável;
- motor complexo sem produto utilizável;
- download na Play Store sem estabilidade;
- código que só funciona quando o tutor explica;
- feature implementada sem capacidade de manutenção.

---

# 44. Métricas antes do lançamento

Antes de existir base de usuários, as métricas devem ser predominantemente de engenharia e produto.

## PROPOSTO

### Funcionalidade

- cenários principais executáveis;
- regras críticas validadas.

### Qualidade

- build;
- testes;
- lint;
- crashes conhecidos;
- ANRs conhecidos;
- problemas de acessibilidade conhecidos.

### Cobertura funcional

Não confundir necessariamente com porcentagem de cobertura de linhas.

Medir principalmente:

- quais regras possuem testes;
- quais fluxos foram verificados;
- quais dispositivos/versões foram testados.

### Experiência

- tempo até iniciar partida;
- clareza de seleção;
- legibilidade;
- feedback de usuários de teste.

---

# 45. Métricas após publicação

## FUTURO / DEPENDENTE DE PRIVACIDADE E FERRAMENTAS APROVADAS

Possíveis métricas:

### Aquisição

- visualizações da ficha;
- conversão da loja;
- instalações.

### Ativação

- abertura inicial;
- início de primeira partida.

### Uso

- partidas iniciadas;
- partidas concluídas;
- duração de sessão.

### Retenção

- usuários retornando;
- sinais fornecidos pelo Play Console.

### Qualidade

- crash rate;
- ANR rate;
- Android vitals;
- avaliações;
- reviews.

### Negócio

- receita, apenas quando existir monetização.

Essas métricas não justificam adicionar analytics automaticamente.

---

# 46. Sinais atuais do Google Play que merecem acompanhamento

A documentação atual do Play Console apresenta Android vitals e sinais relacionados a qualidade e valor.

Entre os sinais documentados atualmente estão:

- DAU / MAU;
- taxa de perda de usuários;
- crashes;
- ANRs;
- permissões negadas.

A documentação atual indica que certos sinais de valor podem gerar alertas ou afetar elegibilidade em superfícies de descoberta.

Esses números podem mudar.

Portanto:

> **não congelar thresholds de 2026 como metas eternas do Gambitol.**

Quando estivermos próximos da publicação e durante operação, consultar novamente.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9844486

Verificado em: **2026-08-22**.

---

# 47. Objetivos propostos por horizonte

Esta seção não substitui o roadmap.

Ela define resultados, não tarefas.

---

## Horizonte A — Fundação

Resultado:

> Projeto Android compila, executa e possui base compreensível.

Inclui conceitualmente:

- ambiente;
- JDK;
- SDK;
- Gradle;
- Git;
- projeto inicial.

---

## Horizonte B — Domínio

Resultado:

> O jogo consegue representar uma partida e validar suas regras principais independentemente da UI.

---

## Horizonte C — Jogabilidade

Resultado:

> O usuário consegue jogar pelo celular.

---

## Horizonte D — Qualidade

Resultado:

> O projeto é testado, estável e compreensível.

---

## Horizonte E — Produto

Resultado:

> A interface possui identidade e acabamento suficientes para ser apresentada publicamente.

---

## Horizonte F — Distribuição

Resultado:

> O Gambitol passa por teste e publicação na Google Play.

---

## Horizonte G — Evolução

Resultado:

> Feedback real orienta IA, online, monetização e outras features.

---

# 48. Objetivos que devem permanecer separados

Alguns assuntos parecem iguais, mas não são.

## Aprender Java ≠ implementar tudo manualmente

Uma biblioteca pode ser correta quando a parte terceirizada não é o foco do aprendizado.

## Publicar rápido ≠ publicar incompleto

Velocidade sem qualidade pode gerar reviews ruins e retrabalho.

## Ter interface premium ≠ sobrecarregar a tela

Identidade visual deve melhorar experiência.

## Monetizar ≠ maximizar interrupções

Receita depende de preservar valor.

## Ter arquitetura ≠ criar muitas camadas

Arquitetura serve para controlar dependências e responsabilidades.

---

# 49. Trade-offs esperados

O projeto deve reconhecer antecipadamente alguns conflitos.

## Velocidade versus aprendizado

Solução:

- automatizar tarefas repetitivas;
- desacelerar nos conceitos importantes.

## Simplicidade versus extensibilidade

Solução:

- construir para necessidades reais atuais;
- evitar decisões irreversíveis quando o custo for baixo.

## Visual versus lógica

Solução:

- implementar incrementos verticais;
- não deixar estética bloquear validação do motor.

## Feature versus qualidade

Solução:

- priorizar comportamento confiável antes de adicionar mais superfície.

## Monetização versus UX

Solução:

- escolher modelo depois de entender valor e contexto.

---

# 50. Princípios de priorização

## PROPOSTO

Quando duas tarefas competirem, avaliar nesta ordem:

1. bloqueia o funcionamento?
2. afeta regra de xadrez?
3. afeta estabilidade?
4. afeta aprendizado atual?
5. afeta arquitetura futura de forma difícil de reverter?
6. afeta experiência principal?
7. é requisito de publicação?
8. é polimento?
9. é feature futura?

Isso não é algoritmo rígido.

É filtro contra prioridades aleatórias.

---

# 51. Perguntas obrigatórias antes de uma feature grande

Antes de adicionar algo grande:

- Qual problema resolve?
- Para quem?
- É necessário agora?
- Está no MVP?
- Que complexidade adiciona?
- Exige backend?
- Exige conta?
- Exige permissão?
- Exige coleta de dados?
- Exige nova dependência?
- Afeta arquitetura?
- Como será testada?
- Como será mantida?
- O valor compensa o custo?

Se a resposta principal for:

> “porque seria legal”

a feature deve permanecer FUTURA até haver motivo melhor.

---

# 52. Definição de MVP em nível de visão

## PROPOSTO

O MVP do Gambitol deve ser o menor produto capaz de provar simultaneamente:

1. motor de xadrez confiável;
2. integração Android funcional;
3. experiência mobile utilizável;
4. identidade mínima de produto.

Isso é diferente de “menor quantidade de código”.

O MVP pode precisar de regras relativamente complexas porque xadrez incompleto deixa de ser uma experiência confiável.

---

# 53. O que significa “xadrez completo” para o MVP

O detalhamento ficará no documento de regras, mas a visão exige considerar:

- movimentos básicos;
- captura;
- xeque;
- xeque-mate;
- empate relevante;
- roque;
- promoção;
- en passant.

Se uma regra for conscientemente deixada para depois, o produto deverá deixar isso explícito e o escopo deverá ser revisto.

---

# 54. IA como objetivo futuro

## FUTURO

A IA é uma excelente evolução técnica porque pode ensinar:

- geração de jogadas;
- avaliação de posição;
- árvores;
- minimax;
- poda alpha-beta;
- heurísticas;
- desempenho.

Porém, não deve ser introduzida antes de o motor de regras ser confiável.

Uma IA procura jogadas.

Se o gerador de jogadas estiver errado, a IA apenas errará mais sofisticadamente.

---

# 55. Multiplayer como objetivo futuro

## FUTURO

Multiplayer online muda significativamente o produto.

Pode exigir:

- backend;
- autenticação;
- sincronização;
- reconexão;
- persistência remota;
- latência;
- segurança;
- matchmaking;
- anti-abuso;
- política de dados;
- custos.

Portanto, online não deve “entrar aos poucos” sem uma decisão arquitetural.

---

# 56. Cronômetro

## PROPOSTO / A VALIDAR NO ESCOPO

O mockup prevê cronômetro.

É uma feature coerente com xadrez e visualmente importante.

Porém, sua regra exata precisa ser definida:

- sem tempo;
- tempo total;
- incremento;
- presets;
- comportamento ao pausar app;
- comportamento ao bloquear tela;
- fim por tempo.

Não implementar timer antes de decidir semântica.

---

# 57. Histórico e notação

## PROPOSTO

Histórico de movimentos tem alto valor porque conecta:

- UX;
- domínio;
- testes;
- persistência;
- notação algébrica.

A FIDE possui convenções de notação que poderão orientar o documento de regras.

O histórico não deve ser apenas texto montado pela UI.

Idealmente deve derivar de movimentos reais do domínio.

A arquitetura específica será decidida posteriormente.

---

# 58. Persistência

## PENDENTE

Ainda deverá ser decidido o que precisa sobreviver ao fechamento do aplicativo.

Possibilidades:

- partida atual;
- preferências;
- histórico;
- temas;
- estatísticas.

Não escolher tecnologia de banco antes de definir necessidade.

---

# 59. Localização

## FUTURO / PROPOSTO PARA SER FACILITADA

O Android recomenda localização adequada para mercados onde o app é distribuído.

Mesmo que a primeira versão seja em português, o código deve evitar práticas que tornem tradução dolorosa.

Isso inclui usar recursos adequados para textos visíveis.

O idioma inicial e mercados de lançamento serão definidos posteriormente.

Fonte:

https://developer.android.com/quality/user-experience

---

# 60. Objetivo de branding

## DECIDIDO

O nome Gambitol deve possuir identidade própria.

## PENDENTE

Antes de uso comercial definitivo, deverão ser avaliados:

- disponibilidade de marca;
- possíveis conflitos jurídicos;
- domínio;
- handles;
- identidade gráfica final.

O fato de um nome ter sido escolhido para o projeto não é prova de disponibilidade jurídica.

Esse assunto não deve bloquear a aprendizagem inicial, mas não pode ser esquecido antes da comercialização.

---

# 61. Objetivo de conteúdo técnico

## DECIDIDO

O projeto deve gerar oportunidades reais de conteúdo.

O conteúdo deve mostrar:

- problema;
- raciocínio;
- implementação;
- erro;
- correção;
- aprendizado.

Exemplos:

- separando motor da Activity;
- implementando movimento de cavalo;
- testando roque;
- entendendo Gradle;
- corrigindo bug de lifecycle;
- publicando primeiro AAB;
- interpretando pre-launch report.

Não fabricar erro proposital apenas para ter o que postar.

---

# 62. Objetivo de explicabilidade profissional

Ao final de cada etapa relevante, o desenvolvedor deve conseguir responder:

- O que foi implementado?
- Por que dessa forma?
- Qual alternativa existia?
- Como foi testado?
- Que problema apareceu?
- O que aprendeu?
- Como isso afeta o produto?

Essa capacidade é parte explícita do resultado do Gambitol.

---

# 63. Critérios de qualidade para decisões

Uma decisão é considerada boa quando equilibra:

- objetivo do produto;
- aprendizado;
- simplicidade;
- testabilidade;
- manutenção;
- experiência;
- custo de mudança;
- tempo;
- publicação.

Nenhum critério vence sempre.

---

# 64. Regra contra modismo técnico

O Gambitol não trocará tecnologia simplesmente porque ela é:

- mais nova;
- mais popular;
- recomendada em vídeo;
- usada por uma big tech;
- tendência.

Exemplo:

Kotlin pode ser recomendado para Android moderno, mas Java foi escolhido conscientemente como parte do objetivo educacional.

Uma recomendação geral não invalida automaticamente uma restrição específica do projeto.

---

# 65. Regra de atualização de objetivos

Objetivos podem mudar.

Quando isso acontecer:

1. identificar o motivo;
2. verificar impacto;
3. registrar decisão;
4. atualizar este documento;
5. atualizar roadmap;
6. atualizar escopo;
7. evitar alteração silenciosa.

---

# 66. Pergunta norteadora permanente

Antes de uma decisão importante, perguntar:

> **Isso ajuda o Gambitol a ser um jogo de xadrez Android melhor, um projeto de engenharia melhor, uma experiência de aprendizado melhor ou um produto mais viável?**

Se a resposta for “nenhum”, a mudança provavelmente não merece prioridade.

---

# 67. Matriz de objetivos

| Objetivo | Prioridade estratégica | Horizonte |
|---|---:|---|
| Jogo de xadrez funcional | máxima | primeira versão |
| Regras confiáveis | máxima | primeira versão |
| Aprender Java | máxima | todo o projeto |
| Aprender Android | máxima | todo o projeto |
| Arquitetura explicável | alta | desde a base |
| Testes | alta | incremental |
| UI própria | alta | produto |
| Publicar na Play Store | máxima | release |
| Portfólio | alta | incremental |
| Conteúdo técnico | média/alta | oportunidades reais |
| Monetização | futura | após valor |
| IA | futura | após motor |
| Multiplayer | futura | após base |
| Ranking | futura | depende de online |
| Conta | futura | somente se necessária |

---

# 68. Critérios de “pronto para avançar”

Uma fase não precisa estar perfeita.

Mas antes de aumentar complexidade, devemos conseguir responder:

- funciona?
- sabemos por que funciona?
- foi validado?
- existe débito conhecido?
- o próximo passo depende disso?
- a documentação relevante está coerente?

Se a fundação está quebrada, adicionar feature apenas aumenta a superfície do problema.

---

# 69. Play Store como restrição positiva

Publicar na Play Store força o projeto a enfrentar assuntos que tutoriais frequentemente ignoram:

- assinatura;
- versionamento;
- compatibilidade;
- privacidade;
- qualidade;
- screenshots;
- distribuição;
- feedback;
- atualização.

Isso é parte do valor educacional.

A loja não é apenas destino.

Ela é também uma fonte de requisitos reais.

---

# 70. Uso de testes fechados como aprendizagem

Quando chegarmos à distribuição, testers não deverão ser vistos somente como obstáculo de política.

Eles podem ajudar a descobrir:

- confusão de UI;
- bug de regra;
- problemas de tela;
- crashes;
- perda de estado;
- lentidão;
- diferenças entre aparelhos.

Feedback real é uma etapa de produto.

---

# 71. Pre-launch report como gate de qualidade

## PROPOSTO

Antes da produção, revisar o relatório de pré-lançamento.

Ele pode apontar:

- estabilidade;
- compatibilidade;
- desempenho;
- acessibilidade.

O relatório não substitui nossos testes.

A própria documentação do Google explica que ele não garante encontrar todos os problemas.

Portanto:

```text
testes próprios
+
testes com usuários
+
pre-launch report
+
Android vitals pós-release
```

formam camadas complementares.

Fontes:

- https://support.google.com/googleplay/android-developer/answer/9842757
- https://support.google.com/googleplay/android-developer/answer/9844487

---

# 72. Qualidade da ficha da loja

O produto começa antes do primeiro toque.

Objetivos para a presença futura:

- nome claro;
- ícone profissional;
- screenshots reais;
- descrição honesta;
- nenhuma promessa de feature inexistente;
- identidade visual coerente.

A página da loja não deve vender um produto diferente do app.

---

# 73. Avaliações e reviews

## FUTURO

Reviews deverão ser tratados como fonte de informação, não como sentença isolada.

Agrupar padrões:

- bugs repetidos;
- pedido recorrente;
- confusão de UX;
- problemas de dispositivo;
- elogios a features.

Não mudar produto inteiro por uma única avaliação sem contexto.

---

# 74. Objetivo de retenção

## FUTURO

Retenção fará sentido depois de publicação.

Antes disso, não otimizar artificialmente “engajamento” com:

- notificações inúteis;
- dark patterns;
- recompensa diária sem propósito;
- fricção para sair.

O objetivo é o usuário voltar porque o jogo entrega valor.

---

# 75. Monetização e confiança

## PROPOSTO

Qualquer modelo futuro deverá respeitar:

- partida não deve ser sabotada;
- anúncio não deve causar jogada acidental;
- compra não deve ser apresentada de forma enganosa;
- recursos pagos devem ser claros;
- privacidade deve ser considerada.

A monetização detalhada terá documento próprio.

---

# 76. Critérios propostos para uma feature premium futura

Uma feature candidata a premium deveria:

- entregar valor real;
- ser compreensível;
- não destruir a funcionalidade básica;
- não tornar xadrez incompleto para quem não paga;
- não exigir prática manipulativa;
- possuir custo de manutenção justificável.

Ainda não há modelo aprovado.

---

# 77. Valor educacional da publicação

Publicar também deve ensinar:

- build variants;
- release;
- assinatura;
- AAB;
- versionCode;
- versionName;
- Play Console;
- testes;
- políticas;
- monitoramento;
- rollout.

Assim o projeto cobre parte do ciclo real de software:

```text
ideia
↓
implementação
↓
teste
↓
build
↓
distribuição
↓
feedback
↓
manutenção
```

---

# 78. Manutenção como objetivo

## DECIDIDO

A primeira publicação não encerra o projeto automaticamente.

Software publicado precisa poder:

- receber correções;
- adaptar-se a novas versões Android;
- ajustar políticas;
- corrigir crashes;
- responder a feedback;
- evoluir.

Por isso legibilidade e testes importam antes da Play Store.

---

# 79. Compatibilidade e versões Android

Os valores exatos de:

- `minSdk`;
- `targetSdk`;
- `compileSdk`;

são decisões técnicas sujeitas ao estado atual do projeto e requisitos externos.

Eles devem ser documentados nos arquivos técnicos e no documento de release.

Este documento não deve congelar números que envelhecem.

Regra:

> antes de publicar ou atualizar, verificar novamente requisitos oficiais.

---

# 80. Objetivo de baixo acoplamento à plataforma no domínio

O motor deve ser concebido de modo que regras de xadrez não precisem “saber”:

- qual Activity está aberta;
- qual cor a casa possui;
- qual botão foi tocado;
- qual resolução tem a tela;
- qual View representa um peão.

Isso ajuda:

- testes;
- clareza;
- futura IA;
- manutenção.

---

# 81. Objetivo de UI fina

“UI fina” não significa UI simples visualmente.

Significa:

> a camada visual não deve ser proprietária das regras de negócio.

A tela pode ser sofisticada.

A regra do cavalo continua pertencendo ao domínio.

---

# 82. Objetivo de confiabilidade do estado

Uma partida é um estado.

O sistema precisa saber, entre outros pontos:

- posição das peças;
- jogador atual;
- histórico necessário;
- direitos de roque;
- condições para en passant;
- estado de jogo;
- promoção.

Esse estado não pode ser derivado apenas do que “parece estar na tela”.

O domínio deve ser fonte de verdade da partida.

A arquitetura detalhará como.

---

# 83. Objetivo de feedback de erro para o jogador

Jogada inválida deve resultar em comportamento compreensível.

Evitar:

- nada acontecer sem explicação visual;
- tela quebrar;
- exceção aparecer;
- peça sumir;
- estado divergir.

A forma final do feedback será decidida em UI/UX.

---

# 84. Objetivo de feedback de erro para o desenvolvedor

Erros internos devem ser diagnosticáveis.

Durante desenvolvimento:

- logs;
- stack traces;
- testes;
- mensagens claras;
- Git diff.

Não “engolir” exceção para esconder problema.

---

# 85. Objetivo de documentação

## DECIDIDO

A documentação deve permitir retomar o projeto sem depender de memória de conversas antigas.

Os documentos serão usados como fonte de orientação do tutor.

Isso inclui preservar:

- objetivos;
- decisões;
- arquitetura;
- regras;
- método de ensino;
- workflow;
- troubleshooting.

---

# 86. Objetivo de Git

## DECIDIDO

Git não é apenas backup.

O histórico deve ajudar a entender a evolução.

Desejável:

- mudanças coerentes;
- commits compreensíveis;
- branches quando justificadas;
- revisão antes de commit;
- nada de segredo.

Detalhes em `07_GIT_WORKFLOW.md`.

---

# 87. Objetivo de build reproduzível

## DECIDIDO

O projeto deverá usar o Gradle Wrapper do próprio repositório.

Objetivo:

> outra máquina com ambiente adequado deve conseguir usar a versão de Gradle definida pelo projeto sem depender de instalação global arbitrária.

---

# 88. Experiência de primeira execução

## PROPOSTO

O usuário não deve enfrentar um onboarding enorme para jogar localmente.

Pergunta norteadora:

> “Qual é o menor número razoável de ações entre abrir o app e começar uma partida?”

Ainda não definir número rígido.

O fluxo será projetado no documento de UI/UX.

---

# 89. Publicidade do estado atual

No portfólio e conteúdo:

- “planejado” deve ser chamado de planejado;
- “em desenvolvimento” deve ser chamado de em desenvolvimento;
- “publicado” somente após publicação;
- “IA” somente depois de existir;
- “multiplayer” somente depois de existir.

Isso protege credibilidade.

---

# 90. Critério de autenticidade técnica

Um bom resultado é aquele que o desenvolvedor consegue abrir meses depois e compreender sem depender integralmente de IA.

A IA pode:

- orientar;
- acelerar;
- pesquisar;
- gerar;
- revisar.

Ela não deve ser o único lugar onde a lógica do projeto existe.

---

# 91. Critério de aprendizado ativo

Quando surgir um conceito relevante, o processo pode intencionalmente desacelerar.

Exemplos:

- herança versus composição;
- lifecycle;
- estado;
- testes;
- algoritmo de xeque;
- Gradle;
- assinatura.

Esses momentos têm prioridade educacional.

---

# 92. Critério para gravar conteúdo

Uma etapa merece gravação quando possui pelo menos um destes:

- erro comum;
- conceito importante;
- comparação antes/depois;
- decisão arquitetural;
- teste interessante;
- bug difícil;
- resultado visível;
- etapa de publicação.

Conteúdo é subproduto do desenvolvimento real.

---

# 93. Objetivo de diferenciação

O Gambitol não precisa competir inicialmente por ter “mais features”.

Pode se diferenciar por:

- acabamento;
- identidade;
- clareza;
- confiabilidade;
- narrativa de construção;
- evolução.

A diferenciação real deverá ser validada com usuários e mercado posteriormente.

Não declarar vantagem competitiva sem pesquisa.

---

# 94. Pesquisa de concorrência

## PENDENTE / FORA DESTE DOCUMENTO

Antes de decisões comerciais relevantes, deverá existir pesquisa específica sobre:

- jogos de xadrez Android;
- avaliações;
- reclamações recorrentes;
- monetização;
- UX;
- preços;
- features;
- posicionamento.

Este documento não inventa essa análise.

Ela pode ser incorporada ao roadmap/monetização ou gerar um documento auxiliar se realmente necessária.

---

# 95. Critério de sucesso técnico antes de monetização

## PROPOSTO

Não priorizar monetização antes de:

- motor confiável;
- fluxo jogável;
- UI adequada;
- build estável;
- testes básicos.

Monetizar instabilidade é apenas cobrar pelo privilégio de encontrar bugs.

---

# 96. Objetivo de feedback humano

O tutor e testes automatizados não substituem usuários.

Antes de publicação, pessoas reais devem experimentar:

- seleção;
- movimentação;
- entendimento de turno;
- leitura;
- reinício;
- fim de jogo.

Observar onde hesitam.

Perguntar menos “você gostou?” e observar mais “conseguiu usar?”.

---

# 97. Requisitos não funcionais de alto nível

Os detalhes ficarão nos documentos técnicos.

A visão estabelece:

### Correção

Regras críticas devem ser corretas.

### Estabilidade

Partidas não devem ser interrompidas por falha do app.

### Responsividade

Interações devem responder rapidamente.

### Usabilidade

Ações principais devem ser compreensíveis.

### Testabilidade

Domínio deve permitir automação.

### Manutenibilidade

Código deve poder evoluir.

### Privacidade

Coletar apenas o necessário.

### Compatibilidade

Respeitar plataforma alvo definida.

### Acessibilidade

Não ignorar necessidades básicas.

---

# 98. Critérios de cancelamento ou revisão de feature

Uma feature deve ser revista quando:

- aumenta complexidade sem valor;
- exige infraestrutura desproporcional;
- prejudica UX;
- dificulta publicação;
- contradiz objetivo educacional sem motivo;
- cria risco de privacidade;
- não pode ser mantida;
- desvia o foco do xadrez.

Cancelar uma ideia ruim também é progresso.

---

# 99. Definição de sucesso global do projeto

O Gambitol será considerado um sucesso completo quando conseguir combinar quatro resultados:

## 1. Produto

Existe um jogo Android real e utilizável.

## 2. Engenharia

O código possui organização, testes e decisões explicáveis.

## 3. Aprendizado

O desenvolvedor entende o que construiu.

## 4. Entrega

O produto chega a usuários por um canal real de distribuição.

A receita é um quinto resultado desejado, mas não deve ser usada como único critério de sucesso técnico ou educacional.

---

# 100. Objetivos obrigatórios versus desejáveis

## Obrigatórios

- aprender;
- construir em Java;
- construir para Android;
- jogo de xadrez funcional;
- arquitetura compreensível;
- publicação planejada;
- qualidade suficiente para distribuição.

## Desejáveis

- boa recepção;
- conteúdo;
- reputação;
- receita;
- evolução do produto.

A diferença importa porque resultados de mercado não são totalmente controláveis.

---

# 101. Controle versus influência

O projeto controla:

- código;
- testes;
- documentação;
- qualidade;
- decisões;
- experiência;
- frequência de evolução.

O projeto influencia, mas não controla:

- installs;
- reviews;
- ranking;
- receita;
- viralização;
- destaque na Play Store.

Metas devem distinguir os dois grupos.

---

# 102. Objetivos mensuráveis propostos

Os seguintes são candidatos para serem refinados em roadmap e qualidade.

## Release 1

- 100% dos movimentos/regras definidos como MVP implementados;
- nenhum bug bloqueador conhecido nas regras;
- suíte de testes do motor passando;
- build de release gerado;
- pre-launch report revisado;
- fluxo completo testado em mais de um perfil de dispositivo quando possível;
- documentação crítica atualizada;
- ficha da loja coerente com o produto.

“100%” aqui significa **100% do escopo explicitamente aprovado**, não cobertura de linhas.

---

# 103. Métricas que NÃO serão inventadas agora

Não estabelecer sem pesquisa/uso real:

- “10 mil downloads em 30 dias”;
- “D7 de 40%”;
- “R$ X no primeiro mês”;
- “rating 4,9”;
- “mil usuários ativos”.

Esses números podem virar objetivos posteriormente.

Hoje seriam decoração quantitativa.

---

# 104. Critério de maturidade para IA

Antes de IA:

- geração de movimentos legais confiável;
- estado confiável;
- aplicação de movimento confiável;
- desfazer/simular movimento se necessário;
- testes.

Somente depois avaliar algoritmo ou engine.

---

# 105. Critério de maturidade para online

Antes de multiplayer:

- regras locais confiáveis;
- modelo de estado estável;
- serialização/representação de movimentos bem compreendida;
- persistência definida;
- fluxo local testado.

Online não deve ser usado para corrigir modelo de domínio mal resolvido.

---

# 106. Critério de maturidade para monetização

Antes de monetização:

- jogo jogável;
- experiência coerente;
- hipótese de valor;
- entendimento de impacto de política;
- modelo escolhido conscientemente.

---

# 107. Critério de maturidade para expansão de telas

Antes de otimizações específicas para tablet/dobrável:

- smartphone funcional;
- layout não rigidamente quebrado;
- comportamento de estado estável.

Depois avaliar oportunidades específicas.

---

# 108. Filosofia de versão inicial

A primeira versão não precisa provar tudo que o Gambitol poderá ser.

Ela precisa provar que:

> **a base é boa o suficiente para merecer evolução.**

---

# 109. Relação com os próximos documentos

Este documento define **por quê** e **para onde**.

Os próximos respondem outros níveis.

`02_METODO_DE_ENSINO.md`

> Como aprenderemos durante o processo?

`03_ARQUITETURA_DO_GAMBITOL.md`

> Como o sistema será dividido?

`04_ESTRUTURA_DO_PROJETO.md`

> Onde cada parte ficará?

`05_REGRAS_DO_MOTOR_DE_XADREZ.md`

> Quais são as regras exatas?

`06_PADROES_JAVA_E_ANDROID.md`

> Como escreveremos código?

`07_GIT_WORKFLOW.md`

> Como evoluiremos o repositório?

`08_TESTES_E_QUALIDADE.md`

> Como provaremos que funciona?

`09_UI_UX_GAMBITOL.md`

> Como o usuário perceberá e controlará o jogo?

`10_ROADMAP_E_ESCOPO.md`

> Em que ordem faremos e o que entra em cada etapa?

`11_DECISOES_TECNICAS.md`

> Por que decisões relevantes foram tomadas?

`12_PLAY_STORE_E_RELEASE.md`

> Como chegaremos à loja?

`13_MONETIZACAO.md`

> Como receita poderá existir sem destruir valor?

`14_CONTEUDO_E_PORTFOLIO.md`

> Como transformar trabalho real em demonstração profissional?

`15_TROUBLESHOOTING.md`

> Como preservar soluções de problemas reais?

---

# 110. Checklist para aprovar este documento

Antes de mudar o status para ATIVO:

- [ ] A visão representa o Gambitol que realmente queremos construir.
- [ ] Java continua sendo linguagem principal.
- [ ] Android continua sendo plataforma inicial.
- [ ] Publicação na Play Store continua sendo objetivo.
- [ ] Aprendizado continua tendo prioridade explícita.
- [ ] Portfólio continua sendo objetivo.
- [ ] Possibilidade de monetização continua desejada.
- [ ] A primeira modalidade local está correta.
- [ ] IA continua futura.
- [ ] Multiplayer continua futuro.
- [ ] Nenhum público específico foi inventado como definitivo.
- [ ] Nenhum modelo de monetização foi inventado como definitivo.
- [ ] Nenhuma meta financeira foi inventada.
- [ ] A referência visual está descrita corretamente.
- [ ] O conceito de motor separado da UI continua válido.
- [ ] Os critérios de qualidade estão alinhados ao objetivo do projeto.

---

# 111. Resumo executivo

O Gambitol existe para ser:

> **um jogo de xadrez Android real, elegante e confiável, desenvolvido em Java como produto e como escola prática de engenharia de software.**

Ele deve:

- jogar xadrez corretamente;
- funcionar bem no celular;
- possuir identidade;
- ser testável;
- ser explicável;
- poder ser publicado;
- poder evoluir;
- servir como portfólio;
- gerar aprendizado;
- possuir caminho para monetização futura.

Ele não deve:

- virar coleção aleatória de features;
- trocar aprendizado por cópia cega;
- trocar qualidade por pressa;
- trocar simplicidade por arquitetura teatral;
- trocar experiência por monetização prematura.

A ordem lógica é:

```text
VALOR
↓
CORREÇÃO
↓
QUALIDADE
↓
EXPERIÊNCIA
↓
PUBLICAÇÃO
↓
FEEDBACK
↓
EVOLUÇÃO
↓
MONETIZAÇÃO QUANDO FIZER SENTIDO
```

---

# 112. Fontes pesquisadas

Esta documentação foi construída com base em decisões já estabelecidas no projeto e pesquisa externa em fontes oficiais.

## Android — qualidade geral

### Build high-quality apps and games
https://developer.android.com/quality

Base para:

- quatro pilares de qualidade;
- valor central;
- UX;
- qualidade técnica;
- segurança/privacidade.

Verificado em: 2026-08-22.

---

## Android — valor central

### What great core value looks like
https://developer.android.com/quality/core-value

Base para:

- valor ao usuário;
- utilidade/diversão;
- alinhamento ao público;
- profundidade de features guiada por necessidade.

Verificado em: 2026-08-22.

---

## Android — experiência do usuário

### Como é uma ótima experiência do usuário
https://developer.android.com/quality/user-experience?hl=pt-BR

Base para:

- intuitividade;
- identidade;
- branding;
- acessibilidade;
- localização;
- apelo visual.

Verificado em: 2026-08-22.

---

## Android — qualidade principal

### Core app quality guidelines
https://developer.android.com/docs/quality-guidelines/core-app-quality

Base para:

- estabilidade;
- interrupções;
- retorno ao app;
- compatibilidade;
- permissões;
- testes;
- qualidade básica.

Verificado em: 2026-08-22.

---

## Android — privacidade e segurança

### Design for Safety
https://developer.android.com/quality/privacy-and-security

Base para:

- minimização;
- permissões;
- dados;
- segurança;
- transparência.

Verificado em: 2026-08-22.

---

## Android — jogos

### Develop Android games
https://developer.android.com/games

### Android game design guidelines
https://developer.android.com/games/design/overview

### Google Play Games — quality guidelines
https://developer.android.com/games/guidelines

Base para:

- qualidade de jogos;
- desempenho;
- diferentes dispositivos;
- estabilidade;
- interpretação contextual de frame rate.

Verificado em: 2026-08-22.

---

## Android — desempenho

### App performance guide
https://developer.android.com/topic/performance/overview

### Analyze and optimize game performance
https://developer.android.com/games/optimize/gameperformance

Base para:

- desempenho mensurável;
- resposta;
- consumo;
- análise antes de otimização.

Verificado em: 2026-08-22.

---

## Android — telas adaptáveis

### Adaptive Apps
https://developer.android.com/develop/adaptive-apps

### Get started with large screens
https://developer.android.com/guide/topics/large-screens

Base para:

- variedade de formatos;
- evitar layout excessivamente rígido;
- evolução além de smartphone.

Verificado em: 2026-08-22.

---

## Google Play — funcionalidade e experiência

### Functionality, Content, and User Experience
https://support.google.com/googleplay/android-developer/answer/9898783

Base para:

- estabilidade;
- propósito;
- conteúdo funcional;
- experiência responsiva.

Verificado em: 2026-08-22.

---

## Google Play — descoberta e ranking

### App Discovery and Ranking
https://support.google.com/googleplay/android-developer/answer/9958766

Base para:

- qualidade como sinal;
- importância de assets de loja;
- relevância;
- experiência pré-instalação.

Verificado em: 2026-08-22.

---

## Google Play — Android vitals

### Monitor your app's technical quality with Android vitals
https://support.google.com/googleplay/android-developer/answer/9844486

Base para:

- crashes;
- ANRs;
- sinais pós-publicação;
- métricas de valor/uso;
- necessidade de rever thresholds no momento real.

Verificado em: 2026-08-22.

---

## Google Play — pre-launch report

### Use a pre-launch report to identify issues
https://support.google.com/googleplay/android-developer/answer/9842757

### Understand your pre-launch report
https://support.google.com/googleplay/android-developer/answer/9844487

Base para:

- estabilidade;
- compatibilidade;
- desempenho;
- acessibilidade;
- testes em vários dispositivos;
- limites do relatório automatizado.

Verificado em: 2026-08-22.

---

## Android — acessibilidade

### Test your app's accessibility
https://developer.android.com/guide/topics/ui/accessibility/testing

Base para:

- contraste;
- tamanho de alvo de toque;
- labels;
- testes automatizados de acessibilidade.

Verificado em: 2026-08-22.

---

## FIDE

### FIDE Laws of Chess
https://handbook.fide.com/chapter/e012023

### FIDE Rules Commission — Documentation
https://rcc.fide.com/documentation/

Base para:

- autoridade normativa das regras de xadrez;
- objetivo;
- tabuleiro;
- movimentos;
- estados de fim de partida.

Verificado em: 2026-08-22.

---

# 113. Nota sobre validade das fontes

Este documento mistura:

1. decisões estáveis do Gambitol;
2. princípios relativamente estáveis;
3. requisitos externos mutáveis.

Regras da Play Store, Android e programas de qualidade podem mudar.

Portanto, qualquer requisito externo usado para:

- publicação;
- monetização;
- target SDK;
- política;
- compatibilidade;
- qualidade de produção;

deverá ser verificado novamente no momento da decisão.

---

# 114. Estado final desta versão

## Confirmado

- Gambitol;
- Android;
- Java;
- xadrez;
- Play Store como destino;
- aprendizado como objetivo;
- portfólio;
- possibilidade comercial;
- motor separado da UI;
- referência visual inicial;
- primeira experiência local.

## Proposto

- público primário da primeira release;
- princípios específicos de UX;
- critérios de maturidade;
- gates de qualidade de alto nível;
- estratégia de métricas;
- princípio de minimização de dados;
- ordem produto → valor → monetização.

## Pendente

- público final;
- mercados;
- idiomas;
- modelo de monetização;
- analytics;
- persistência;
- cronômetros exatos;
- posicionamento competitivo;
- suporte específico a tablets/dobráveis;
- features premium;
- IA;
- online.

---

# 115. Encerramento

A principal obrigação deste documento é impedir que o Gambitol perca seu motivo de existir.

Toda nova ideia deverá poder responder:

> “Qual objetivo do Gambitol isso atende?”

Toda implementação importante deverá poder responder:

> “Como sabemos que isso está correto?”

Toda escolha técnica importante deverá poder responder:

> “Por que essa solução foi escolhida?”

E toda etapa educacional deverá poder responder:

> “O que foi aprendido aqui?”

Se o projeto conseguir manter essas quatro perguntas vivas, ele terá muito mais chance de chegar à Play Store como algo que vale a pena mostrar, manter e evoluir.
