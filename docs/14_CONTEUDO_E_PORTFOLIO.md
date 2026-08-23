# 14 — CONTEÚDO E PORTFÓLIO DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `14_CONTEUDO_E_PORTFOLIO.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir como transformar a construção real do Gambitol em evidência profissional, conteúdo técnico, documentação pública, demonstrações, materiais de portfólio e narrativa de carreira sem sacrificar a qualidade do projeto nem fabricar conquistas  
> **Fonte normativa para:** README público, GitHub, perfil profissional, conteúdo técnico, gravações, screenshots, demonstrações, vídeos, posts, seleção de marcos, curadoria de portfólio, provas de competência, narrativa de entrevista e proteção contra exposição de informações sensíveis  
> **Não cobre em detalhe:** implementação do motor, regras de xadrez, workflow Git completo, Play Store operacional, monetização, troubleshooting ou estratégia comercial ampla  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `07_GIT_WORKFLOW.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`, `10_ROADMAP_E_ESCOPO.md`, `11_DECISOES_TECNICAS.md`, `12_PLAY_STORE_E_RELEASE.md`, `13_MONETIZACAO.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

O Gambitol não existe apenas para terminar em um APK.

Ele também deve produzir evidências de que seu desenvolvedor sabe:

- Java;
- Android;
- arquitetura;
- modelagem;
- testes;
- Git;
- debugging;
- UX;
- publicação;
- tomada de decisão;
- evolução incremental;
- comunicação técnica.

Este documento responde:

- o que realmente vale mostrar;
- o que vale gravar;
- o que não vale;
- como estruturar o README;
- como apresentar o projeto no GitHub;
- como usar o projeto no LinkedIn;
- quando um vídeo técnico tem valor;
- como criar screenshots;
- como transformar um bug real em conteúdo;
- como falar do projeto em entrevista;
- como evitar claims falsos;
- como não expor secrets, dados ou chaves;
- como manter o conteúdo subordinado ao produto.

A regra central é:

> **Portfólio forte mostra evidência verificável de capacidade, não apenas uma lista de tecnologias.**

---

# 2. O projeto é a fonte; o conteúdo é derivado

## DECIDIDO

A ordem é:

```text
PROBLEMA REAL
↓
DECISÃO
↓
IMPLEMENTAÇÃO
↓
TESTE
↓
RESULTADO
↓
CONTEÚDO
```

Não:

```text
PRECISO DE POST
↓
INVENTAR FEATURE
↓
MEXER NO PROJETO SÓ PARA GRAVAR
```

---

# 3. Conteúdo não deve desviar o roadmap

## DECIDIDO

Uma gravação não justifica antecipar:

- IA;
- multiplayer;
- Billing;
- arquitetura complexa;
- framework;
- backend.

Se uma feature não pertence à fase atual, ela não entra porque “renderia vídeo”.

---

# 4. Portfólio é evidência

Um recrutador ou desenvolvedor deveria conseguir abrir o projeto e verificar:

- código;
- histórico;
- testes;
- documentação;
- releases;
- screenshots;
- decisões.

---

# 5. Portfólio não é decoração

Uma imagem bonita ajuda.

Mas não substitui:

```text
arquitetura
testes
commits
release
explicação
```

---

# 6. Três camadas de evidência

## PROPOSTO

```text
CAMADA 1 — REPOSITÓRIO
código, testes, docs, histórico

CAMADA 2 — DEMONSTRAÇÃO
screenshots, vídeo, release, app funcionando

CAMADA 3 — EXPLICAÇÃO
README, post, vídeo, entrevista
```

As três se reforçam.

---

# 7. Evidência primária

Mais forte:

- código executável;
- teste;
- Perft;
- build;
- release;
- app publicado.

---

# 8. Evidência secundária

- arquitetura documentada;
- ADR;
- screenshot;
- vídeo de funcionamento.

---

# 9. Evidência narrativa

- post;
- artigo;
- apresentação;
- explicação em entrevista.

---

# 10. Não inverter força da evidência

Um post dizendo:

> “implementei arquitetura robusta”

vale menos que um repositório que demonstra isso.

---

# 11. GitHub como núcleo técnico

## DECIDIDO COMO DIREÇÃO

O repositório deve ser o principal ponto de prova técnica pública quando a publicação pública for aprovada.

---

# 12. README do repositório

O GitHub afirma que README costuma ser uma das primeiras coisas que visitantes veem e recomenda explicar:

- o que o projeto faz;
- por que é útil;
- como começar;
- onde obter ajuda;
- quem mantém.

Fonte oficial:

https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes

Verificado em: 2026-08-22.

---

# 13. README do Gambitol precisa ser útil em poucos minutos

A documentação oficial do GitHub sobre uso do perfil em currículo lembra que avaliadores podem gastar pouco tempo em cada projeto.

Logo:

primeira leitura precisa responder rapidamente:

```text
O QUE É?
QUAL PROBLEMA?
QUAL STACK?
QUAL DIFERENCIAL?
QUAL ESTADO?
COMO TESTAR?
ONDE VER?
```

---

# 14. README não deve começar com 40 badges

## PRINCÍPIO

Badges podem ajudar.

Mas não devem empurrar a explicação do projeto para baixo.

---

# 15. Estrutura proposta do README público

## PROPOSTO

```text
# Gambitol

1. resumo
2. screenshot/preview
3. por que o projeto existe
4. principais capacidades
5. arquitetura
6. stack
7. execução
8. testes
9. roadmap/status
10. decisões relevantes
11. screenshots
12. releases
13. licença
```

Não é contrato final.

---

# 16. Resumo de abertura

Em poucas linhas:

- jogo de xadrez Android;
- Java;
- dois jogadores locais inicialmente;
- motor separado da UI;
- foco em qualidade/testes.

---

# 17. Não prometer feature futura no presente

Ruim:

```text
Gambitol possui IA avançada e multiplayer.
```

se ainda não possui.

Bom:

```text
Planejado: IA e multiplayer em fases futuras.
```

---

# 18. Estado precisa estar claro

Exemplo conceitual:

```text
Status: em desenvolvimento
```

Depois:

```text
Disponível no Google Play
```

quando realmente estiver.

---

# 19. Screenshot acima da dobra

## PROPOSTO

Quando UI estiver madura:

uma imagem forte pode aparecer cedo no README.

Não colocar mockup conceitual como se fosse screenshot do app.

---

# 20. Mockup precisa ser rotulado

Se usado:

```text
Conceito visual
```

Não:

```text
Gambitol em execução
```

---

# 21. GIF de demonstração

## CANDIDATO

Pode mostrar:

- selecionar peça;
- highlight;
- mover;
- captura.

Curto.

Sem virar arquivo gigantesco.

---

# 22. Vídeo completo

Link externo pode ser melhor que embutir mídia pesada.

---

# 23. Arquitetura no README

Não copiar todo `03_ARQUITETURA_DO_GAMBITOL.md`.

Mostrar resumo.

Exemplo conceitual:

```text
Android UI
   ↓
Application/state holder
   ↓
Pure Java chess engine
```

E linkar documentação interna/pública quando apropriado.

---

# 24. Mermaid

Pode ser usado no GitHub se agregar clareza.

Não criar diagrama ornamental.

---

# 25. Stack

Listar somente tecnologia usada.

Não:

```text
Java • Kotlin • AWS • Docker • Kubernetes
```

se o projeto não usa.

---

# 26. Stack por papel

Melhor:

```text
Java — código principal e engine
Android Views/XML — interface
Gradle Kotlin DSL — build
JUnit — testes, quando configurado
```

---

# 27. “Tecnologias estudadas” não são “tecnologias usadas”

Separar.

---

# 28. Arquitetura hexagonal?

Não chamar de algo que não implementamos.

---

# 29. Clean Architecture?

Mesma regra.

Se conceitos foram adaptados:

explicar precisamente.

---

# 30. Testes no README

Quando suite existir:

mostrar:

- o que é coberto;
- como rodar;
- Perft;
- quality gates.

---

# 31. Coverage badge

## PENDENTE

Só usar quando:

- coverage é medido automaticamente;
- badge representa estado real;
- não incentiva meta vazia.

---

# 32. Build badge

## FUTURO

Bom quando CI existir.

---

# 33. Release badge

Opcional.

---

# 34. License badge

Somente depois de licença decidida.

---

# 35. README precisa funcionar sem vídeo

Texto + código + screenshot bastam para entender.

---

# 36. Instalação/execution

Quando repo público puder ser compilado:

instruções precisam refletir ambiente real.

Não copiar comandos genéricos.

---

# 37. Pré-requisitos

Listar:

- JDK;
- Android Studio;
- SDK;
- wrapper.

Versões reais.

---

# 38. Comando de build

Quando confirmado:

```bash
./gradlew assembleDebug
```

ou equivalente real.

---

# 39. Test command

Quando confirmado.

---

# 40. README não deve expor caminhos pessoais

Evitar:

```text
C:\Users\<nome>\...
```

em documentação pública sem necessidade.

---

# 41. README público e documentação interna não são iguais

Docs profundas podem permanecer em:

- pasta documental;
- links;
- wiki;
- repo.

README é entrada.

---

# 42. GitHub profile

O GitHub oferece Profile README e pinned items para apresentar trabalho.

Fonte:

https://docs.github.com/en/account-and-profile/concepts/personal-profile

---

# 43. Repositórios fixados

A documentação atual permite selecionar até:

```text
6 itens
```

entre repositories/gists no perfil pessoal.

Fonte:

https://docs.github.com/en/account-and-profile/how-tos/profile-customization/pinning-items-to-your-profile

Verificado em: 2026-08-22.

---

# 44. Gambitol como pin

## PROPOSTO QUANDO MADURO

Se estiver público e apresentável:

deve ser candidato forte a item fixado.

---

# 45. Não fixar cedo só para mostrar atividade

Se o repo ainda contém:

- template;
- build quebrado;
- README vazio;

esperar.

---

# 46. GitHub recomenda projetos relevantes à busca profissional

A documentação oficial de currículo do GitHub sugere escolher alguns dos melhores projetos e torná-los fáceis de entender rapidamente.

Fonte:

https://docs.github.com/en/account-and-profile/tutorials/using-your-github-profile-to-enhance-your-resume

---

# 47. Gambitol precisa mostrar diversidade interna

Mesmo sendo um projeto:

- Java;
- Android;
- testes;
- arquitetura;
- algoritmo;
- release.

Isso já comunica várias competências.

---

# 48. Topics do repositório

GitHub permite tópicos para classificar finalidade/assunto.

Fonte:

https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/classifying-your-repository-with-topics

---

# 49. Topics futuros possíveis

## NÃO DEFINIR AGORA COMO LISTA FINAL

Exemplos que podem ser avaliados:

- java;
- android;
- chess;
- game;
- chess-engine.

Somente usar tópicos verdadeiros.

---

# 50. GitHub Releases

Releases são associadas a tags e podem empacotar notas/artefatos.

Fonte:

https://docs.github.com/en/repositories/releasing-projects-on-github/about-releases

---

# 51. Gambitol e GitHub Release

## FUTURO / CANDIDATO

Pode ser útil para:

- changelog;
- milestones;
- portfólio.

Mas não precisa distribuir APK/AAB publicamente fora da Play.

---

# 52. Segurança de distribuição

Antes de anexar APK release:

rever:

- assinatura;
- package ownership;
- developer verification;
- distribuição fora da Play.

---

# 53. Release notes públicas

Podem mostrar evolução profissional:

```text
1.0
→ engine completa
→ UI local
→ release
```

---

# 54. GitHub Pages

GitHub Pages pode hospedar site estático de usuário, organização ou projeto diretamente de um repositório.

Fonte:

https://docs.github.com/en/pages/getting-started-with-github-pages/what-is-github-pages

---

# 55. Página específica do Gambitol

## FUTURO / NÃO NECESSÁRIA

Pode ser criada depois para:

- screenshots;
- features;
- Play link;
- tech details.

Não é requisito para portfólio.

---

# 56. Não criar site só porque GitHub Pages existe

README pode ser suficiente.

---

# 57. LinkedIn

## DIREÇÃO

Usar o Gambitol como evidência de trabalho técnico, não como sequência diária de “hoje criei uma classe”.

---

# 58. Seção Em destaque

LinkedIn permite destacar:

- posts;
- artigos;
- links externos;
- imagens;
- documentos;
- vídeos.

A própria documentação descreve a seção como espaço para mostrar amostras de trabalho como evidência de habilidades e experiência.

Fonte:

https://www.linkedin.com/help/linkedin/answer/a550399/feature-samples-of-your-work-on-your-linkedin-profile

---

# 59. Curadoria > volume

LinkedIn permite muitos itens, mas destaque precisa ser curado.

A FAQ atual informa que é possível adicionar muitos itens, embora apenas até 400 sejam exibidos.

Isso não significa que deveríamos colocar 400.

Fonte:

https://www.linkedin.com/help/linkedin/answer/a548496

---

# 60. Itens candidatos para destaque

Quando existirem:

- vídeo de arquitetura;
- release;
- demonstração do app;
- Perft;
- post técnico forte;
- GitHub.

---

# 61. Não destacar todo post

Seção destacada deve mostrar melhor evidência.

---

# 62. Post bom

Tem:

```text
PROBLEMA
DECISÃO
IMPLEMENTAÇÃO
RESULTADO
APRENDIZADO
```

---

# 63. Post ruim

```text
Hoje dei mais um passo incrível na minha jornada...
```

seguido de captura de terminal sem contexto.

---

# 64. Não inflar dificuldade

Se o trabalho foi simples:

dizer simples.

Credibilidade acumula.

---

# 65. Não chamar tudo de “desafio”

---

# 66. Não chamar cada correção de “arquitetura”

---

# 67. Não chamar todo código de “escalável”

---

# 68. Não usar “enterprise-grade” sem contexto

---

# 69. Linguagem profissional

Falar:

- o problema;
- a decisão;
- o trade-off;
- o resultado.

---

# 70. Mostrar código com contexto

Trecho pequeno.

Não screenshot de 150 linhas.

---

# 71. Screenshot de código

Pode ser visualmente bom.

Mas texto copiável no post/artigo é melhor quando conteúdo técnico depende dele.

---

# 72. Vídeos

## PRINCÍPIO

Vídeo deve mostrar algo que movimento/execução explicam melhor que texto.

---

# 73. O que vale gravar

Exemplos:

- bug real;
- refactor arquitetural;
- engine separada;
- Perft;
- UI responsiva;
- acessibilidade;
- release;
- profiling;
- teste vermelho → verde.

---

# 74. O que NÃO vale gravar por padrão

- criar pasta;
- `git add`;
- abrir Android Studio;
- renomear variável;
- importar classe;
- build sem contexto.

---

# 75. Marcador já definido

O método de ensino usa:

```text
🎥 MOMENTO BOM PARA GRAVAR
```

Este documento define como usar esse material.

---

# 76. Critério para marcar momento

Pelo menos um:

- conceito importante;
- transformação visual;
- bug educativo;
- decisão não óbvia;
- milestone;
- evidência de competência.

---

# 77. Critério de não marcar

Se exige cinco minutos de explicação para justificar por que seria interessante:

provavelmente não é.

---

# 78. Formatos de vídeo

## PROPOSTO

### Curto

30–90 segundos.

Um conceito.

### Médio

3–8 minutos.

Problema + solução.

### Longo

10–30 minutos.

Deep dive.

Não são limites rígidos.

---

# 79. Short-form

Bom para:

- antes/depois;
- uma armadilha;
- um conceito;
- demonstração.

---

# 80. Long-form

Bom para:

- arquitetura;
- Perft;
- engine;
- debugging;
- release.

---

# 81. Não postar gravação bruta de 45 minutos

Editar.

---

# 82. Roteiro técnico curto

```text
1. problema
2. por que importa
3. demonstração
4. decisão
5. resultado
```

---

# 83. Roteiro técnico profundo

```text
CONTEXTO
↓
REQUISITO
↓
ALTERNATIVAS
↓
DECISÃO
↓
CÓDIGO
↓
TESTE
↓
RESULTADO
↓
TRADE-OFF
```

---

# 84. YouTube: título

A ajuda atual informa limite de:

```text
100 caracteres
```

para o título.

Fonte:

https://support.google.com/youtube/answer/57404

---

# 85. Descrição

Limite atual:

```text
5.000 caracteres
```

---

# 86. Título deve explicar valor

Bom:

```text
Como validei um motor de xadrez Java com Perft
```

Ruim:

```text
VOCÊ NÃO VAI ACREDITAR NESSA ENGINE!!!
```

---

# 87. Thumbnail

YouTube recomenda custom thumbnails grandes, com:

```text
16:9
```

para vídeos normais.

Fonte:

https://support.google.com/youtube/answer/72431

---

# 88. Thumbnail técnica

Poucos elementos:

- board;
- conceito;
- palavra curta;
- contraste.

---

# 89. Não colocar 12 logos de tecnologia

---

# 90. Thumbnail não promete feature inexistente

---

# 91. Título e thumbnail precisam combinar com o vídeo

---

# 92. Correções

YouTube permite registrar correções na descrição com timestamps.

Útil para conteúdo técnico quando descoberta posterior exige ajuste.

---

# 93. Não apagar erro técnico silenciosamente

Se vídeo publicado contém informação incorreta:

corrigir descrição/comentário ou atualizar conteúdo.

---

# 94. Conteúdo técnico envelhece

Especialmente:

- Android;
- Play Store;
- Gradle;
- Billing.

Registrar versão/data quando necessário.

---

# 95. Conteúdo de regra FIDE

Citar versão/regra quando relevante.

---

# 96. Gravação Android

Android Studio/Emulator oferecem screen recording oficial.

A documentação atual do Emulator permite gravar:

- vídeo;
- áudio em cenário de emulator;
- WebM/GIF.

Fonte:

https://developer.android.com/studio/run/emulator-record-screen

---

# 97. Dispositivo via Android Studio

Também é possível gravar MP4 do dispositivo via ferramentas Android Studio/Logcat.

Fonte:

https://developer.android.com/studio/debug/am-video

---

# 98. `adb screenrecord`

ADB também permite gravar tela de dispositivo.

Bom para demos reproduzíveis.

---

# 99. Screenshots Android

O Emulator permite capturas PNG pela interface e linha de comando.

Fonte:

https://developer.android.com/studio/run/emulator-take-screenshots

---

# 100. Screenshot profissional

Antes de capturar:

- estado limpo;
- sem notificações;
- sem debug overlay;
- sem dado pessoal;
- board interessante;
- UI final.

---

# 101. Screenshot de bug

Pode conter logs/dev UI.

Mas não usar como imagem de portfólio principal.

---

# 102. Diferenciar screenshot técnico e marketing

### Técnico

prova comportamento.

### Marketing

comunica produto.

---

# 103. Screenshot técnico pode mostrar Logcat

Quando o assunto é debug.

---

# 104. Marketing screenshot não mostra terminal ao fundo

---

# 105. Não expor dados

Antes de gravar:

fechar:

- e-mail;
- mensagens;
- browser pessoal;
- secrets;
- tokens;
- paths sensíveis;
- contas.

---

# 106. Terminal

Revisar prompt.

Pode revelar:

- username;
- path;
- repo private;
- branch.

Nem sempre é problema, mas avaliar.

---

# 107. `.env`

Nunca em gravação.

---

# 108. Keystore

Nunca.

---

# 109. Password dialog

Nunca.

---

# 110. Play Console financeiro

Evitar expor dados.

---

# 111. API keys

Nunca.

---

# 112. Git remote URL

Normalmente público se repo público.

Mas não mostrar credenciais/token embutido.

---

# 113. Logcat

Pode conter:

- IDs;
- tokens;
- paths;
- PII.

Revisar.

---

# 114. Blur

Pode ser usado em edição.

Melhor evitar capturar.

---

# 115. Conteúdo e direitos autorais

Não usar:

- música sem licença;
- assets de terceiros sem autorização;
- fontes sem licença;
- screenshots de conteúdo protegido desnecessário.

---

# 116. Peças/board

Se assets próprios/licenciados:

pode mostrar.

---

# 117. Música em vídeo

Usar biblioteca/licença apropriada.

---

# 118. Código de terceiros

Pequenos trechos podem aparecer conforme licença/contexto, mas conteúdo deve focar no próprio trabalho.

---

# 119. Não mostrar código confidencial de outros projetos

Gambitol é o foco.

---

# 120. Antes/depois

## FORMATO FORTE

Exemplo:

```text
ANTES
Activity com regra

DEPOIS
UI → engine Java puro
```

Isso mostra evolução.

---

# 121. Bug → teste → correção

Outro formato forte.

---

# 122. Performance antes/depois

Só se medido.

---

# 123. Não fabricar benchmark

---

# 124. Comparar branch real

Pode mostrar diff.

---

# 125. `git diff`

Excelente para conteúdo de refactor.

---

# 126. Git history como evidência

Commits coerentes contam história.

---

# 127. Não reescrever histórico apenas para parecer perfeito

Histórico de aprendizado tem valor.

---

# 128. Mas não publicar secrets antigos

Se secret entrou no Git:

remover do histórico e rotacionar.

---

# 129. Commit messages

Documento 07 orienta.

Conteúdo pode usar bons commits como prova de processo.

---

# 130. Branches

Não precisam virar conteúdo.

---

# 131. Pull requests

Se self-PR/review for usado:

pode mostrar processo.

---

# 132. Issues

Podem demonstrar:

- bug tracking;
- decisão;
- planejamento.

Mas não criar issues artificiais.

---

# 133. Discussions

Não necessárias.

---

# 134. Releases

Boa prova de produto.

---

# 135. Play Store

A publicação real é forte evidência.

---

# 136. Link Play no README

Quando existir.

---

# 137. Badge Play

Pode ser usado se respeitar branding oficial.

---

# 138. Store screenshot no portfolio

Pode mostrar produto final.

---

# 139. Pre-launch report

Pode virar conteúdo técnico.

Cuidado com dados de conta.

---

# 140. Android vitals

Pode mostrar melhoria agregada sem dados sensíveis.

---

# 141. Perft

## CONTEÚDO PRIORITÁRIO

Perft tem excelente valor porque conecta:

- algoritmo;
- xadrez;
- testes;
- debugging;
- engenharia.

---

# 142. Perft vídeo/post

Estrutura:

```text
O PROBLEMA:
Como saber se o gerador de movimentos está correto?

TÉCNICA:
Perft

DEMO:
contagens

BUG:
divergência

DIAGNÓSTICO:
divide

RESULTADO:
correção
```

---

# 143. Não explicar Perft como benchmark de IA

Documento 08 já diferencia.

---

# 144. Conteúdo Java

O Gambitol pode demonstrar:

- classes;
- objetos;
- encapsulamento;
- enum;
- collections;
- generics;
- exceptions;
- interfaces;
- composição;
- immutability.

---

# 145. Não fazer post “aprendi variável”

Se objetivo profissional já é software development.

---

# 146. Java avançado contextual

Melhor:

> Como representei estado imutável numa engine de xadrez.

---

# 147. OOP contextual

Melhor:

> Por que não usei uma hierarquia gigante de peças.

---

# 148. Collections

Melhor:

> Como repetition tracking depende da identidade completa da posição.

---

# 149. `equals/hashCode`

Ótimo conteúdo com Position/state.

---

# 150. Exceptions

Mostrar contrato, não “try/catch everywhere”.

---

# 151. Java records

Se decisão futura ocorrer:

bom tema sobre compatibilidade e trade-offs.

---

# 152. Conteúdo Android

Possíveis assuntos:

- lifecycle;
- Activity;
- ViewModel;
- XML;
- WindowInsets;
- edge-to-edge;
- Custom View;
- accessibility;
- testing.

---

# 153. Board custom rendering

Se Custom View escolhida:

forte.

---

# 154. 64 Views versus Canvas

A decisão pode gerar excelente conteúdo de ADR.

---

# 155. Accessibility virtual nodes

Se implementado:

conteúdo muito forte.

---

# 156. Edge-to-edge

Antes/depois visual forte.

---

# 157. Lifecycle bug

Exemplo:

partida reinicia na recriação.

Mostrar causa e correção.

---

# 158. UI state

Mostrar que pixels não são source of truth.

---

# 159. Touch mapping

Excelente:

```text
pixel x/y
→ square
```

---

# 160. Responsividade

Mostrar board em várias janelas.

---

# 161. Android minSdk

Conteúdo só se houver caso real de compatibilidade.

---

# 162. Gradle

Bom quando problema real:

- JDK;
- module;
- dependency;
- source compatibility.

---

# 163. Não transformar cada sync em conteúdo

---

# 164. Conteúdo de arquitetura

Melhores temas:

- engine isolada;
- dependency direction;
- state ownership;
- UI sem regra;
- ADR.

---

# 165. Diagrama simples

Mais útil que 40 caixas.

---

# 166. Conteúdo de testes

- AAA;
- regression;
- parameterized;
- Perft;
- mutation;
- flaky UI test.

---

# 167. Mutation testing

Quando existir:

excelente demonstração de “coverage não é qualidade”.

---

# 168. Coverage

Mostrar limite da métrica.

---

# 169. Teste vermelho

Visualmente compreensível.

---

# 170. Bug regressão

Ótima história.

---

# 171. Conteúdo de Git

Só quando ensina processo real:

- branch;
- diff;
- revert;
- bisect;
- reflog;
- commit design.

---

# 172. `git bisect`

Se um bug real exigir:

conteúdo excelente.

---

# 173. Não simular bug só para usar `bisect`

---

# 174. Conteúdo de Play Store

Forte:

- AAB;
- signing;
- internal testing;
- target API;
- pre-launch;
- release.

---

# 175. Chaves

Nunca mostrar secret.

Usar diagrama.

---

# 176. Conteúdo de monetização

Somente quando implementado.

---

# 177. Billing flow

Bom tema futuro.

---

# 178. Data Safety

Bom tema quando dependência realmente mudar declaração.

---

# 179. Conteúdo de decisão

ADR real é excelente.

---

# 180. Conteúdo de erro

## DECIDIDO COMO VALIOSO

Não esconder toda dificuldade.

Mostrar:

- hipótese errada;
- evidência;
- correção.

---

# 181. Erro não deve virar autopunição

Foco técnico.

---

# 182. “Eu errei porque sou iniciante”

Menos útil.

Melhor:

> A primeira abordagem falhava neste caso por X.

---

# 183. Conteúdo transparente

Dizer:

- o que foi feito;
- o que IA ajudou;
- o que foi revisado;
- o que foi testado.

---

# 184. IA no desenvolvimento

## PRINCÍPIO

Não fingir que cada linha foi digitada sem assistência.

Também não dizer:

> “a IA fez o projeto”.

Mostrar engenharia humana:

- requisito;
- decisão;
- validação;
- teste;
- review.

---

# 185. Como mencionar IA

Exemplo:

> Usei IA como apoio para explorar alternativas e acelerar código mecânico, mas validei a arquitetura, os testes e o comportamento do motor.

---

# 186. Não usar “vibe coding” como explicação final

Pode ser método de velocidade.

Portfólio precisa demonstrar entendimento.

---

# 187. Authorship

Ser capaz de explicar qualquer código destacado.

---

# 188. Se não consegue explicar

Não usar como exemplo de competência ainda.

---

# 189. Conteúdo de aprendizado

Pode mostrar evolução:

```text
antes eu pensava X
depois testei Y
escolhi Z
```

---

# 190. Isso é mais forte que “aprendi muito”

---

# 191. Métricas do projeto

Usar somente reais.

Exemplos futuros:

- número de testes;
- Perft depths;
- release version;
- minSdk;
- Play installs.

---

# 192. Não usar linhas de código como qualidade

---

# 193. “10.000 linhas”

Não prova arquitetura.

---

# 194. “100% cobertura”

Pode ser enganoso.

---

# 195. “Zero bugs”

Não afirmar.

Melhor:

```text
nenhum P0/P1 conhecido no release
```

---

# 196. “Produção”

Só se realmente publicado/usado.

---

# 197. “Escalável”

Só com contexto.

---

# 198. “Alta performance”

Só com benchmark.

---

# 199. “Seguro”

Só com threat/context.

---

# 200. “Acessível”

Só depois de testes adequados.

---

# 201. Portfólio e entrevista

O objetivo não é decorar frases.

É conseguir navegar:

```text
PROBLEMA
DECISÃO
TRADE-OFF
IMPLEMENTAÇÃO
TESTE
RESULTADO
```

---

# 202. História principal do Gambitol

## PROPOSTO

> Construir um jogo Android real em Java, mantendo o motor de xadrez independente da interface, com regras testadas e uma trajetória até publicação.

---

# 203. História de arquitetura

> Separei o domínio do Android para testar regras rapidamente na JVM e evitar lógica duplicada na UI.

---

# 204. História de escopo

> Mantive a primeira versão local para dois jogadores e adiei IA/multiplayer para reduzir risco.

---

# 205. História de qualidade

> Usei testes de regra e Perft antes de confiar na interface.

---

# 206. História de Android

> A UI renderiza estado e traduz toque em coordenadas sem conhecer legalidade.

---

# 207. História de release

> Tratei publicação como pipeline de signing, testing e políticas.

---

# 208. Cada história precisa de evidência

---

# 209. Pergunta de entrevista: “por que Java?”

Responder usando objetivo real.

---

# 210. “Porque eu gosto”

Fraco.

---

# 211. “Porque o projeto também foi desenhado como aprofundamento de Java em um produto Android real”

Forte.

---

# 212. Pergunta: “por que motor separado?”

Falar de:

- determinismo;
- testes;
- Android dependency;
- evolução.

---

# 213. Pergunta: “por que não Compose?”

Falar:

- tecnologia escolhida;
- objetivo;
- Views/XML;
- não adotar framework só por tendência.

---

# 214. Não atacar Compose

É trade-off, não torcida.

---

# 215. Pergunta: “por que não Stockfish?”

V1 foca engine própria/learning? Somente responder conforme decisão real futura.

Não inventar.

---

# 216. Pergunta: “qual foi o bug mais difícil?”

Guardar exemplos reais.

---

# 217. Registro de histórias

## PROPOSTO

Durante desenvolvimento, quando ocorrer algo valioso:

anotar no comentário/conversa/issue.

Não criar arquivo extra obrigatório.

---

# 218. Content backlog

Pode ser lista simples.

---

# 219. Não parar coding para editar vídeo imediatamente

Capturar material.

Editar depois.

---

# 220. Raw capture

Pode ser guardada localmente.

---

# 221. Organização de arquivos de mídia

## PENDENTE

Não inventar estrutura/pasta do projeto agora.

Mídia pode ficar fora do repo.

---

# 222. Não commit de vídeos grandes no Git

Preferir hosting externo.

---

# 223. Screenshots pequenas podem entrar em docs

Quando aprovadas.

---

# 224. Git LFS

Só se realmente necessário.

---

# 225. Não adicionar Git LFS por causa de 2 screenshots

---

# 226. Assets marketing

Podem ficar fora do código.

---

# 227. Separar source asset de output

Exemplo futuro:

- fonte editável;
- PNG exportado.

Estrutura definida quando necessário.

---

# 228. Portfólio público não inclui documentação interna inteira obrigatoriamente

Alguns documentos podem ser:

- públicos;
- resumidos;
- internos.

---

# 229. Qual documentação publicar

## PENDENTE

Avaliar perto de abrir o repo.

---

# 230. Segurança da documentação

Antes de tornar pública:

buscar:

- paths pessoais;
- e-mails;
- chaves;
- tokens;
- dados privados;
- decisões comerciais sensíveis.

---

# 231. Source maps de pesquisas podem ser públicos

Em geral sim.

Mas revisar URLs e conteúdo.

---

# 232. Documentação excessiva pode afastar leitor

README deve guiar.

Docs profundas ficam opcionais.

---

# 233. Portfólio não exige que o recrutador leia 15 documentos

---

# 234. Melhor entrada

```text
README
↓
arquitetura
↓
testes
↓
docs detalhadas
```

---

# 235. GitHub profile README

Pode mencionar Gambitol em “projetos em destaque”.

---

# 236. Não duplicar README inteiro no perfil

Uma frase + link.

---

# 237. Pin + profile README

Reforçam o projeto.

---

# 238. LinkedIn Featured

Pode apontar:

- GitHub;
- vídeo;
- release.

---

# 239. Currículo

Gambitol pode aparecer em:

- Projetos;
- Portfólio.

---

# 240. Linha de currículo

Deve conter:

- o que;
- tecnologia;
- resultado.

---

# 241. Exemplo conceitual

> Jogo de xadrez Android em Java com engine desacoplada da UI, testes automatizados e pipeline de publicação.

Ajustar quando fatos forem reais.

---

# 242. Não colocar “publicado na Play Store” antes de publicar

---

# 243. Não colocar “milhares de usuários” sem dados

---

# 244. Não colocar “arquitetura limpa” sem explicar

---

# 245. Link direto

Currículo pode incluir:

- GitHub;
- Play;
- demo.

---

# 246. Portfólio web

Se já existir portfolio pessoal:

Gambitol pode ter case study.

---

# 247. Case study

Estrutura:

```text
CONTEXTO
DESAFIO
ARQUITETURA
DECISÕES
IMPLEMENTAÇÃO
TESTES
RESULTADO
APRENDIZADOS
```

---

# 248. Case study não precisa mostrar todo código

---

# 249. Screenshots no case study

Mostrar evolução.

---

# 250. Diagrama

Útil.

---

# 251. Release link

Quando existir.

---

# 252. Conteúdo recorrente

## NÃO OBRIGATÓRIO

Não precisamos postar toda semana por calendário.

Postar quando houver algo relevante.

---

# 253. Cadência orientada por milestone

Melhor que:

```text
segunda = post Java
quarta = post Git
sexta = post Android
```

sem conteúdo real.

---

# 254. Milestones naturais

- build;
- engine boundary;
- primeira peça;
- legal moves;
- Perft;
- board;
- primeira partida;
- accessibility;
- release.

---

# 255. Não postar cada milestone

Escolher melhores.

---

# 256. Série de conteúdo

## CANDIDATO

Pode existir uma série:

```text
Construindo Gambitol
```

Mas nome/branding de série não deve ser definido sem aprovação.

---

# 257. Não inventar nome de série agora

---

# 258. Formatos complementares

Um milestone pode virar:

- post curto;
- vídeo;
- README update.

Não precisa virar tudo.

---

# 259. Reuso de conteúdo

É aceitável adaptar.

Exemplo:

deep dive → short.

---

# 260. Não copiar a mesma legenda em todas redes

Adaptar.

---

# 261. LinkedIn

Mais contexto profissional.

---

# 262. YouTube

Mais profundidade.

---

# 263. Shorts/Reels

Uma ideia visual.

---

# 264. GitHub

Evidência técnica.

---

# 265. Play

Produto.

---

# 266. Conteúdo de “build in public”

## PENDENTE

Pode ser benéfico.

Mas não publicar:

- decisões imaturas;
- informações sensíveis;
- promessas de datas.

---

# 267. Promessas

Não anunciar:

> “lança semana que vem”

sem certeza.

---

# 268. Roadmap público

Pode ser alto nível.

Não precisa data.

---

# 269. Issue tracker público

Pode mostrar bugs.

Isso não é vergonha.

---

# 270. Security issues

Não expor vulnerabilidade explorável antes de corrigir.

---

# 271. Responsible disclosure

Se terceiros reportarem futuro.

---

# 272. Conteúdo de falha de release

Pode ser educativo depois de resolvido.

---

# 273. Não publicar credenciais para “mostrar erro”

---

# 274. Conteúdo de debugging

Gravar:

- mensagem;
- hipótese;
- evidência;
- fix.

---

# 275. Não gravar 20 minutos limpando cache

---

# 276. Um bug de Gradle

Pode ensinar muito.

---

# 277. Um typo

Não.

---

# 278. Content quality gate

Antes de publicar:

- [ ] tecnicamente correto;
- [ ] evidencia fato real;
- [ ] sem secret;
- [ ] sem PII;
- [ ] sem claim exagerado;
- [ ] áudio/imagem legíveis;
- [ ] link correto;
- [ ] data/versão se temporal.

---

# 279. Quality gate de screenshot

- [ ] estado real;
- [ ] resolução boa;
- [ ] sem overlay;
- [ ] sem notificação;
- [ ] sem dado privado;
- [ ] crop coerente;
- [ ] não distorce app.

---

# 280. Quality gate de vídeo

- [ ] problema claro;
- [ ] demo clara;
- [ ] sem espera inútil;
- [ ] código legível;
- [ ] áudio compreensível;
- [ ] cortes;
- [ ] conclusão;
- [ ] links/fontes.

---

# 281. Quality gate de post

- [ ] primeira frase tem contexto;
- [ ] não começa com autopromoção vazia;
- [ ] explica decisão;
- [ ] mostra resultado;
- [ ] link/evidência.

---

# 282. Quality gate de README

- [ ] estado atual;
- [ ] screenshot;
- [ ] arquitetura;
- [ ] stack real;
- [ ] build/test;
- [ ] roadmap real;
- [ ] licença;
- [ ] links.

---

# 283. Quality gate de portfólio

- [ ] case study;
- [ ] repo;
- [ ] demo;
- [ ] tech;
- [ ] decisões;
- [ ] resultado.

---

# 284. Evitar jargão sem necessidade

---

# 285. Explicar “Perft”

Não assumir que recrutador conhece.

---

# 286. Explicar “UDF”

Se usar termo.

---

# 287. Explicar ADR

Primeira vez.

---

# 288. Não explicar `if` para público técnico experiente

Contexto define profundidade.

---

# 289. Conteúdo para recrutador

Foco:

- resultado;
- decisões;
- stack;
- qualidade.

---

# 290. Conteúdo para desenvolvedor

Mais implementação.

---

# 291. Conteúdo para iniciante

Mais conceito.

---

# 292. Um conteúdo não precisa agradar todos

---

# 293. CTA

Pode existir:

- ver repo;
- ver release;
- assistir demo.

Não precisa “comenta EU QUERO”.

---

# 294. Hashtags

Poucas, relevantes.

Não é escopo central.

---

# 295. Não usar 20 hashtags

---

# 296. SEO de GitHub

Topics + README claro.

---

# 297. SEO YouTube

Título/descritivo verdadeiro.

---

# 298. Não keyword stuffing

---

# 299. Metadata de projeto

GitHub description deve ser curta.

---

# 300. Website field

Pode apontar para Play/site futuro.

---

# 301. Social preview

GitHub permite imagem social em repos.

## FUTURO / CANDIDATO

Pode usar identidade Gambitol.

---

# 302. Não criar agora sem design final

---

# 303. Release screenshot

Pode virar social preview.

---

# 304. Conteúdo e logo

Usar consistentemente.

---

# 305. Brand colors

Conforme doc09.

---

# 306. Não deixar visual de conteúdo redefinir app

---

# 307. Thumbnail pode usar branding

Sem transformar logo em 80% da imagem.

---

# 308. Antes de gravar milestone

Checklist:

- branch correta;
- build funcionando;
- cenário preparado;
- notificações desligadas;
- resolução;
- microfone;
- roteiro curto.

---

# 309. Durante gravação

Falar do problema.

Não narrar cada clique.

---

# 310. Depois

Cortar:

- espera;
- erro irrelevante;
- digitação longa.

---

# 311. Manter erro relevante

Se ensina.

---

# 312. Terminal commands

Pode acelerar vídeo.

---

# 313. Zoom do código

Legível.

---

# 314. Fonte do editor

Aumentar temporariamente se necessário.

---

# 315. Não editar código só para screenshot e esquecer de voltar

---

# 316. Dark/light editor

Preferência visual.

---

# 317. Cursor

Não tampar trecho.

---

# 318. Show taps

Android Studio/recorder permite exibir toques em gravações de dispositivo.

Pode ajudar demos touch.

---

# 319. Emulator frames

Pode incluir moldura de device se útil.

---

# 320. Marketing screenshot

Normalmente clean frame.

---

# 321. FPS da gravação

Suficiente para animações.

Não precisa obsessão cinematográfica.

---

# 322. Áudio

Conteúdo técnico ruim de ouvir perde valor.

---

# 323. Legendas

## RECOMENDADO

Aumentam acessibilidade.

---

# 324. Transcrição

Pode gerar artigo/post depois.

---

# 325. Captions automáticas precisam revisão técnica

Termos Java/Android/xadrez podem sair errados.

---

# 326. “Gradle” virando “grade”

Corrigir.

---

# 327. Código na legenda

Evitar excesso.

---

# 328. Descrição do vídeo

Pode incluir:

- repo;
- docs;
- versão;
- timestamps;
- fontes.

---

# 329. Não colocar secrets em descrição

---

# 330. Conteúdo temporal

Exemplo:

> Requisitos da Play Store em agosto de 2026.

Isso evita parecer eterno.

---

# 331. Atualização posterior

Se regra mudar:

adicionar correção/nota.

---

# 332. GitHub README temporal

Evitar congelar políticas.

Linkar docs atuais.

---

# 333. Portfolio case study

Pode indicar:

```text
Desenvolvimento iniciado em 2026
```

se quiser.

Não inventar datas de conclusão.

---

# 334. “Projeto concluído”

Só depois de release/escopo.

---

# 335. “Em desenvolvimento”

É aceitável.

---

# 336. Open source

## PENDENTE

Se código ficar público:

definir licença.

---

# 337. Sem licença

GitHub público não significa permissão geral de reutilização.

Mas isso pode confundir visitantes.

Definir antes de promover como open-source.

---

# 338. CONTRIBUTING

Só quando contribuições externas forem aceitas.

---

# 339. CODE_OF_CONDUCT

Só quando comunidade justificar.

---

# 340. SECURITY.md

Pode fazer sentido no futuro.

---

# 341. Issue templates

Só quando volume justificar.

---

# 342. Pull request template

Pode ser usado se colaborar.

Não necessário solo.

---

# 343. Repository health

Não perseguir checklist só por porcentagem.

---

# 344. Arquivo LICENSE

Decisão real.

---

# 345. Portfolio e propriedade intelectual

Se assets próprios:

bom.

Se terceiros:

atribuição/licença.

---

# 346. FIDE

Regras podem ser referenciadas.

Não usar logos/marcas indevidamente.

---

# 347. Stockfish futuro

Se integrado, licença precisa ser comunicada corretamente.

---

# 348. Copyright de música

Reforço.

---

# 349. Conteúdo do código

Não reproduzir trechos extensos de fontes.

---

# 350. Credibilidade

## PRINCÍPIO

Melhor dizer:

> “implementei e testei”

do que:

> “dominei completamente”.

---

# 351. Aprendizado contínuo

Pode ser força.

---

# 352. “Projeto educativo”

Não precisa diminuir valor.

É produto real construído para aprender profundamente.

---

# 353. Portfólio deve mostrar autonomia

Como?

- decisões;
- debugging;
- trade-offs;
- tests.

---

# 354. Não apenas seguir tutorial

README pode explicar escolhas específicas.

---

# 355. Tutorial clone

Menos valor.

Gambitol possui domínio próprio e decisões reais.

---

# 356. Chess engine ajuda diferenciação

Algoritmo real.

---

# 357. Android release ajuda diferenciação

Produto real.

---

# 358. Documentação profunda ajuda diferenciação

Mas só se código acompanhar.

---

# 359. Não usar documentação como substituta de implementação

---

# 360. Sequência de portfolio por maturidade

## PROPOSTO

```text
FASE A
repo privado/em desenvolvimento

FASE B
primeiros conteúdos técnicos pontuais

FASE C
repo apresentável

FASE D
demo completa

FASE E
release Play

FASE F
case study final
```

---

# 361. Repo público antes de B?

Pode.

Mas precisa segurança/revisão.

---

# 362. Não promover repo vazio

---

# 363. Conteúdo durante Fase B

Mostrar aprendizado real.

---

# 364. Fase C

README e pins.

---

# 365. Fase D

Vídeo demo.

---

# 366. Fase E

Play link.

---

# 367. Fase F

Case study retrospectivo.

---

# 368. Portfolio final não precisa documentar cada fase

Curar.

---

# 369. “Best of”

Selecionar:

- 3–5 decisões;
- 2–3 bugs;
- 1 demo;
- 1 release.

---

# 370. Não apresentar 200 screenshots

---

# 371. LinkedIn Featured funciona como vitrine curada

Usar poucos itens fortes.

---

# 372. GitHub pins também são curadoria

---

# 373. Recrutador chega por LinkedIn

Pode ir:

```text
LinkedIn
↓
Featured
↓
GitHub
↓
README
↓
code/tests
```

---

# 374. Recrutador chega por currículo

```text
CV
↓
GitHub/Play
```

---

# 375. Usuário chega por Play

```text
Play
↓
app
```

Não precisa ver documentação.

---

# 376. Desenvolvedor chega por GitHub

Precisa build/readme.

---

# 377. Diferentes portas de entrada

Conteúdo deve respeitar.

---

# 378. Não colocar arquitetura detalhada na Play listing

---

# 379. Não colocar marketing no Javadoc

---

# 380. Não colocar recrutamento dentro do app

---

# 381. Conteúdo de entrevista: 60 segundos

Preparar resumo curto.

---

# 382. Conteúdo de entrevista: 5 minutos

Arquitetura + desafio + teste.

---

# 383. Conteúdo de entrevista: deep dive

Engine.

---

# 384. Elevator pitch conceitual

> Gambitol é um jogo de xadrez Android em Java em que o motor de regras foi separado da interface para permitir testes rápidos e evolução independente. O projeto foi desenvolvido incrementalmente até uma release real.

Ajustar ao estado real.

---

# 385. Não decorar palavra por palavra

---

# 386. Diagramas em entrevista

Ser capaz de desenhar:

```text
UI → state/orchestration → engine
```

---

# 387. Board state

Explicar.

---

# 388. Move validation

Explicar.

---

# 389. Check detection

Explicar.

---

# 390. Perft

Explicar.

---

# 391. Android lifecycle

Explicar.

---

# 392. Release

Explicar.

---

# 393. Git

Explicar.

---

# 394. Test trade-offs

Explicar.

---

# 395. AI assistance

Explicar com maturidade.

---

# 396. Conteúdo como memória

Vídeos/posts também ajudam revisar decisões.

Mas docs continuam fonte técnica.

---

# 397. Não depender de vídeo para documentação operacional

Vídeo envelhece mal.

---

# 398. README deve ser textual.

---

# 399. Source of truth

Docs/repo.

---

# 400. Portfolio artifacts derivados

- screenshot;
- vídeo;
- artigo.

---

# 401. Conteúdo e mudanças

Quando arquitetura mudar:

post antigo não é atualizado automaticamente.

Pode publicar follow-up.

---

# 402. “O que eu faria diferente”

Excelente conteúdo após release.

---

# 403. Post-mortem de arquitetura

Bom.

---

# 404. Post-mortem de release

Bom.

---

# 405. Métricas reais pós-release

Podem ser usadas com cuidado.

---

# 406. Não expor receita sem querer

---

# 407. Não expor dados de usuários

---

# 408. Agregação

Preferir.

---

# 409. Consentimento

Se usuário aparece em conteúdo, obter.

Não relevante a captura de app local normalmente.

---

# 410. Review de conteúdo antes de publicação

Pode ser feito como diff mental:

```text
FACT
CLAIM
EVIDENCE
RISK
```

---

# 411. Fact

Algo verificável.

---

# 412. Claim

Interpretação.

---

# 413. Evidence

Link/test/demo.

---

# 414. Risk

Exagero/privacidade.

---

# 415. Exemplo

Claim:

> motor desacoplado.

Evidence:

- módulo Java;
- build;
- imports.

---

# 416. Claim:

> regras completas.

Evidence:

- doc;
- tests;
- Perft;
- game.

---

# 417. Claim:

> publicado.

Evidence:

- Play link.

---

# 418. Claim:

> acessível.

Evidence:

- TalkBack tests;
- contrast;
- virtual nodes.

---

# 419. Claim:

> performant.

Evidence:

- benchmark.

---

# 420. Claim:

> secure.

Evidence:

- scope-specific controls.

---

# 421. Não publicar claims antes da evidence

---

# 422. Conteúdo de milestone: engine completa

Só depois de doc08 gates.

---

# 423. Conteúdo: first playable

Depois do marco real.

---

# 424. Conteúdo: V1

Depois de release.

---

# 425. Conteúdo: premium

Depois de implementar.

---

# 426. Content backlog proposto

Sem nomes fixos:

- arquitetura;
- Java domain modeling;
- legal moves;
- Perft;
- UI responsive;
- accessibility;
- release;
- lessons learned.

---

# 427. Cada item deve esperar momento real

---

# 428. Não criar roteiro de 30 vídeos agora

---

# 429. Evitar compromisso de série longa

---

# 430. Conteúdo espontâneo com padrão

Melhor.

---

# 431. Reutilizar `🎥 MOMENTO BOM PARA GRAVAR`

Ao longo da implementação.

---

# 432. Capturar primeiro, decidir publicar depois

---

# 433. Não publicar bug inseguro antes da correção

---

# 434. Demo branch

Pode ser usada.

Não precisa contaminar main.

---

# 435. Seed/demo state

Pode ser útil para screenshots.

Se só debug:

não release.

---

# 436. Chess positions para demo

Usar posições próprias/públicas.

---

# 437. Partida famosa

Pode exigir citar fonte/notação.

---

# 438. Posição simples criada para demo

Mais fácil.

---

# 439. Não usar nomes de jogadores reais desnecessariamente

---

# 440. Test data

Genérico.

---

# 441. Profile README do GitHub

## FUTURO

Pode incluir:

- stack;
- projetos;
- Gambitol;
- links.

Não precisa ser refeito agora.

---

# 442. LinkedIn project entry

Pode adicionar Gambitol como projeto quando houver material suficiente.

---

# 443. Featured

Priorizar prova visual/técnica.

---

# 444. Certificates não substituem projeto

---

# 445. Projeto também não substitui experiência profissional

É complemento.

---

# 446. Curriculum

Não usar 10 linhas.

Uma ou duas bullets.

---

# 447. Interview

Aprofunda.

---

# 448. GitHub

Prova.

---

# 449. Play

Prova de entrega.

---

# 450. Conteúdo

Prova de comunicação.

---

# 451. Quatro sinais profissionais

```text
CONSTRUIR
EXPLICAR
TESTAR
ENTREGAR
```

Gambitol deve mostrar os quatro.

---

# 452. “Construir”

Código.

---

# 453. “Explicar”

Docs/post.

---

# 454. “Testar”

suite/Perft.

---

# 455. “Entregar”

Play/release.

---

# 456. Portfólio forte precisa dos quatro

---

# 457. Conteúdo sobre erros mostra debugging

---

# 458. Conteúdo sobre ADR mostra decisão

---

# 459. Conteúdo sobre release mostra delivery

---

# 460. Não depender de fancy UI apenas

---

# 461. Portfólio backend-like dentro do engine

Algoritmo/domínio.

---

# 462. Frontend-like

Android UI/UX.

---

# 463. DevOps-like

build/release/CI.

---

# 464. QA-like

tests.

---

# 465. Product-like

scope/monetization.

---

# 466. Isso torna o projeto rico

Sem precisar inventar microservices.

---

# 467. Microservices não têm lugar aqui

---

# 468. Cloud não entra por portfólio

---

# 469. Docker não entra por currículo

---

# 470. AWS não entra por badge

---

# 471. Mostrar tecnologias necessárias

Mais credível.

---

# 472. Conteúdo de “por que não usei X”

Pode ser excelente se trade-off real.

---

# 473. Exemplo futuro

> Por que não usei Hilt na primeira versão.

---

# 474. Exemplo futuro

> Por que um módulo Java puro simplificou meus testes.

---

# 475. Não transformar escolha em guerra de framework

---

# 476. Comparar com respeito técnico

---

# 477. Portfolio credibility checklist

- [ ] projeto roda;
- [ ] README claro;
- [ ] testes;
- [ ] histórico;
- [ ] screenshot real;
- [ ] claims verdadeiros;
- [ ] links vivos;
- [ ] nenhuma credencial;
- [ ] licença definida quando público;
- [ ] status real.

---

# 478. LinkedIn credibility checklist

- [ ] título contextual;
- [ ] problema;
- [ ] aprendizado específico;
- [ ] resultado;
- [ ] evidência;
- [ ] sem exagero.

---

# 479. YouTube credibility checklist

- [ ] demo real;
- [ ] versão citada;
- [ ] fonte;
- [ ] correções;
- [ ] código explicável.

---

# 480. Case study checklist

- [ ] contexto;
- [ ] objetivo;
- [ ] restrições;
- [ ] arquitetura;
- [ ] decisões;
- [ ] testes;
- [ ] release;
- [ ] resultados;
- [ ] aprendizados.

---

# 481. Screenshots finais recomendados

Quando V1 pronta:

1. tela principal;
2. movimento legal;
3. promoção;
4. resultado.

A quantidade final depende de Play/portfolio.

---

# 482. Video demo final

## PROPOSTO

60–120 segundos pode ser suficiente para portfólio.

Mostrar:

- abrir;
- mover;
- special;
- resultado;
- identidade.

---

# 483. Deep dive separado

Arquitetura/Perft.

---

# 484. Não misturar demo comercial com tutorial de Gradle

---

# 485. Demo é usuário.

---

# 486. Deep dive é engenharia.

---

# 487. Trailer

Futuro.

---

# 488. Portfolio reel

Futuro.

---

# 489. Social post do lançamento

Quando real.

---

# 490. Não anunciar “lançado” enquanto em closed test

---

# 491. Closed test post

Pode dizer:

> entrou em teste fechado.

---

# 492. Internal test não é produção

---

# 493. Tag não é release Play

---

# 494. AAB não é lançamento

---

# 495. Estado preciso evita confusão

---

# 496. Métrica após publicação

Se compartilhar:

usar período.

Exemplo:

```text
X instalações nos primeiros 30 dias
```

Não:

```text
X usuários
```

se são installs.

---

# 497. Ratings

Contexto de número de avaliações.

---

# 498. Crash-free

Se compartilhar, dizer fonte/janela.

---

# 499. Conteúdo sobre monetização futura

Ser transparente.

---

# 500. Portfolio de projeto sem monetização continua forte

---

# 501. Fontes — GitHub README

## About READMEs

https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes

Usado para:

- papel do README;
- conteúdo;
- localização;
- comunicação do projeto.

Verificado em: 2026-08-22.

---

# 502. Fontes — GitHub profile

## About your profile

https://docs.github.com/en/account-and-profile/concepts/personal-profile

Usado para:

- profile README;
- pinned items;
- apresentação pública.

---

# 503. Fontes — GitHub resume guidance

## Using your GitHub profile to enhance your resume

https://docs.github.com/en/account-and-profile/tutorials/using-your-github-profile-to-enhance-your-resume

Usado para:

- curadoria de melhores projetos;
- tornar projetos fáceis de entender;
- pins;
- README profissional.

Verificado em: 2026-08-22.

---

# 504. Fontes — GitHub pins

https://docs.github.com/en/account-and-profile/how-tos/profile-customization/pinning-items-to-your-profile

Usado para:

- até seis items;
- curadoria.

---

# 505. Fontes — GitHub topics

https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/classifying-your-repository-with-topics

Usado para:

- classificação;
- descoberta.

---

# 506. Fontes — GitHub Releases

https://docs.github.com/en/repositories/releasing-projects-on-github/about-releases

Usado para:

- releases;
- tags;
- software iterations.

---

# 507. Fontes — GitHub Pages

https://docs.github.com/en/pages/getting-started-with-github-pages/what-is-github-pages

Usado para:

- site de projeto futuro.

---

# 508. Fontes — LinkedIn Featured

## Featured section FAQs

https://www.linkedin.com/help/linkedin/answer/a548496

## Feature samples of your work

https://www.linkedin.com/help/linkedin/answer/a550399/feature-samples-of-your-work-on-your-linkedin-profile

Usado para:

- samples de trabalho;
- posts;
- artigos;
- links;
- imagens;
- documentos;
- vídeos;
- curadoria.

Verificado em: 2026-08-22.

---

# 509. Fontes — YouTube video metadata

https://support.google.com/youtube/answer/57404?hl=pt-BR

Usado para:

- título;
- descrição;
- correções.

Verificado em: 2026-08-22.

---

# 510. Fontes — YouTube thumbnails

https://support.google.com/youtube/answer/72431

Usado para:

- custom thumbnails;
- aspect ratio;
- resolução;
- políticas.

Verificado em: 2026-08-22.

---

# 511. Fontes — Android recording

## Emulator screen recording

https://developer.android.com/studio/run/emulator-record-screen

## Device video

https://developer.android.com/studio/debug/am-video

Usado para:

- gravação;
- MP4/WebM/GIF;
- demonstrações técnicas.

Verificado em: 2026-08-22.

---

# 512. Fontes — Android screenshots

https://developer.android.com/studio/run/emulator-take-screenshots

Usado para:

- screenshots do Emulator;
- PNG;
- linha de comando.

---

# 513. Regra de fontes

Para conteúdo técnico temporal:

preferir documentação oficial.

---

# 514. Comunidade

Pode complementar:

- experiência;
- discussão;
- comparação.

Não substituir fonte normativa.

---

# 515. Posts não precisam parecer paper

Mas facts críticos precisam estar corretos.

---

# 516. Decisões normativas

## DECIDIDO

1. projeto vem antes do conteúdo;
2. gravações não mudam roadmap;
3. portfólio prioriza evidência;
4. README é porta de entrada técnica;
5. claims precisam ser verdadeiros;
6. mockup não será apresentado como app real;
7. secrets nunca aparecem;
8. conteúdo técnico temporal recebe contexto de versão/data;
9. `🎥 MOMENTO BOM PARA GRAVAR` permanece seletivo;
10. Perft, arquitetura, acessibilidade e release são marcos de alto valor;
11. LinkedIn/GitHub devem ser curados, não preenchidos por volume;
12. conteúdo deve ser explicável pelo desenvolvedor;
13. IA pode ser mencionada com transparência;
14. portfólio não adiciona tecnologia desnecessária ao projeto;
15. release real vale mais que claim de release.

---

# 517. Pendências

## PENDENTE

1. repo público ou privado durante desenvolvimento;
2. licença;
3. profile README;
4. topics finais;
5. screenshot final;
6. formato de demo;
7. canais/plataformas de conteúdo;
8. idioma de vídeos;
9. página GitHub Pages;
10. case study final;
11. série/branding de conteúdo;
12. mídia dentro ou fora do repo;
13. frequência;
14. publicação de docs internas;
15. estrutura final da seção Featured.

---

# 518. Próximo passo deste documento

Nenhuma conta, post ou vídeo é criado agora.

Na implementação:

usar os marcadores de gravação e acumular evidência real.

---

# 519. Checklist antes de tornar o repositório público

- [ ] secrets scan;
- [ ] paths pessoais;
- [ ] README;
- [ ] licença decidida;
- [ ] assets licenciados;
- [ ] build possível;
- [ ] status real;
- [ ] docs revisadas;
- [ ] nenhum claim futuro como presente.

---

# 520. Checklist antes de destacar Gambitol no perfil

- [ ] repo apresentável;
- [ ] screenshot real;
- [ ] README;
- [ ] testes;
- [ ] arquitetura;
- [ ] demo ou milestone forte.

---

# 521. Checklist antes de vídeo técnico

- [ ] conceito real;
- [ ] roteiro;
- [ ] build;
- [ ] sem secrets;
- [ ] fontes;
- [ ] demo reproduzível;
- [ ] conclusão.

---

# 522. Checklist antes do case study final

- [ ] V1/release real;
- [ ] decisões principais;
- [ ] screenshots;
- [ ] architecture diagram;
- [ ] tests;
- [ ] Perft;
- [ ] release;
- [ ] lessons.

---

# 523. Resumo operacional

```text
DURANTE O DESENVOLVIMENTO
→ construir
→ testar
→ marcar bons momentos
→ capturar quando útil

A CADA MILESTONE FORTE
→ atualizar README
→ considerar post/vídeo

QUANDO O REPO ESTIVER MADURO
→ tornar apresentável
→ pin/Featured

QUANDO V1 ESTIVER PUBLICADA
→ demo
→ case study
→ release content

DEPOIS
→ usar feedback real
→ mostrar evolução
```

---

# 524. Frase norteadora

> **O melhor conteúdo do Gambitol não será uma propaganda de que o projeto é bom. Será a evidência concreta de como decisões, código, testes, depuração e publicação transformaram uma ideia em um produto real.**

---

# 525. Próximo documento

Após aprovação:

`15_TROUBLESHOOTING.md`

Ele deverá ser o último documento da fundação e definir:

- método de diagnóstico;
- erros de JDK;
- Gradle;
- Android Studio;
- emulator/device;
- ADB;
- build;
- manifest;
- resources;
- Java;
- testes;
- Git;
- engine;
- UI;
- lifecycle;
- Play release;
- Billing futuro;
- logs;
- coleta de evidência;
- problemas recorrentes;
- o que nunca fazer por superstição;
- quando limpar cache;
- quando não limpar;
- como registrar soluções confirmadas.

O documento 14 define:

> **como transformar trabalho real em evidência profissional.**

O documento 15 definirá:

> **como investigar quando esse trabalho real inevitavelmente quebrar, porque software ainda é software.**
