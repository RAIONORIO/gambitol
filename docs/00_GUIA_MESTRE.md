# 00 — GUIA MESTRE DE DOCUMENTAÇÃO DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `00_GUIA_MESTRE.md`  
> **Versão:** 1.1  
> **Status:** ATIVO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-24  
> **Idioma principal:** Português do Brasil  
> **Função:** documento-raiz e regra de navegação de toda a documentação do projeto  
> **Escopo:** governança documental, precedência, atualização, pesquisa, uso das fontes e prevenção de perda de contexto  
> **Não substitui:** arquitetura detalhada, regras de xadrez, padrões de código, workflow Git, testes, UI/UX, roadmap, release ou monetização  

---

# 1. Por que este documento existe

O Gambitol não será tratado apenas como um aplicativo que precisa “funcionar”. Ele será desenvolvido simultaneamente como:

1. um jogo de xadrez mobile para Android;
2. um projeto real de engenharia de software;
3. um projeto de aprendizado prático de Java e Android;
4. uma peça de portfólio;
5. um produto com intenção de publicação na Google Play Store;
6. um possível ativo comercial e de construção de reputação profissional.

Esses objetivos criam um risco previsível: conforme o projeto crescer, decisões podem ficar espalhadas entre conversas, código, README, memória, prints, documentação e novas ideias.

Sem um sistema claro, decisões são refeitas, regras entram em conflito, o código diverge da intenção original e a documentação passa a descrever um sistema que já não existe.

Este documento existe para impedir isso.

O `00_GUIA_MESTRE.md` é o ponto de entrada da documentação oficial do Gambitol. Ele explica:

- quais documentos fazem parte da documentação oficial;
- qual é a responsabilidade de cada documento;
- qual fonte prevalece quando duas informações entram em conflito;
- como diferenciar decisão, proposta, estado atual e informação externa;
- quando uma documentação realmente precisa ser atualizada;
- como novas decisões devem ser registradas;
- como informações externas devem ser pesquisadas e verificadas;
- como o tutor de IA deve consultar as fontes antes de orientar mudanças;
- como evitar que documentação, código e objetivos se afastem entre si.

A função deste arquivo é governar a documentação.

Ele não deve virar:

- diário do desenvolvimento;
- changelog;
- histórico de branches;
- lista de classes criadas;
- registro de cada teste;
- duplicação dos outros documentos.

---

# 2. Localização oficial da documentação

## DECIDIDO

A documentação oficial do Gambitol é versionada junto ao código no repositório oficial:

```text
RAIONORIO/gambitol
└── docs/
```

A pasta contém:

```text
00_GUIA_MESTRE.md
01_VISAO_E_OBJETIVOS.md
02_METODO_DE_ENSINO.md
03_ARQUITETURA_DO_GAMBITOL.md
04_ESTRUTURA_DO_PROJETO.md
05_REGRAS_DO_MOTOR_DE_XADREZ.md
06_PADROES_JAVA_E_ANDROID.md
07_GIT_WORKFLOW.md
08_TESTES_E_QUALIDADE.md
09_UI_UX_GAMBITOL.md
10_ROADMAP_E_ESCOPO.md
11_DECISOES_TECNICAS.md
12_PLAY_STORE_E_RELEASE.md
13_MONETIZACAO.md
14_CONTEUDO_E_PORTFOLIO.md
15_TROUBLESHOOTING.md
```

Esses arquivos são mantidos pelo mesmo workflow Git aplicado ao restante do projeto.

Cópias existentes em:

- conversas antigas;
- arquivos locais não versionados;
- uploads antigos;
- anotações;
- mensagens;
- outras ferramentas;

não substituem a versão vigente no repositório.

Quando existir diferença entre uma cópia antiga e o arquivo correspondente na `main`, a versão do repositório oficial deve ser considerada a referência documental versionada atual, salvo alteração local ainda não integrada que esteja sendo analisada explicitamente.

---

# 3. Decisões fundamentais já estabelecidas

As seguintes decisões não devem ser alteradas silenciosamente:

| Item | Decisão vigente |
|---|---|
| Nome do jogo | Gambitol |
| Repositório/pasta principal | `gambitol` |
| Plataforma inicial | Android |
| Categoria | jogo mobile de xadrez |
| Linguagem principal | Java |
| Package/Application namespace | `br.com.raionorio.gambitol` |
| IDE principal | Android Studio |
| Terminal principal | Git Bash no Windows |
| Build | Gradle com Gradle Wrapper |
| Distribuição pretendida | Google Play Store |
| Estratégia didática | desenvolvimento rápido com acompanhamento e compreensão |
| Princípio arquitetural | regras do xadrez separadas da interface Android |
| Motor | Java puro, independente da camada Android |
| Direção de dependência | `:app -> :chess-engine` |

Decisões técnicas mais específicas pertencem aos documentos especializados e ao `11_DECISOES_TECNICAS.md`.

Qualquer proposta que contradiga uma decisão vigente deve ser tratada explicitamente como mudança de decisão, com contexto, impacto e aprovação.

---

# 4. O que não deve ser tratado automaticamente como decisão

Ideias futuras não são requisitos apenas porque foram discutidas.

Enquanto não forem aprovadas, itens como estes continuam sendo possibilidades:

- inteligência artificial adversária;
- multiplayer online;
- login;
- ranking;
- sistema de amigos;
- analytics;
- anúncios;
- compras dentro do aplicativo;
- assinatura;
- venda de temas ou peças;
- backend próprio;
- banco de dados remoto;
- tecnologia específica de persistência;
- bibliotecas externas ainda não escolhidas;
- sistema definitivo de navegação;
- motor externo de xadrez;
- IA própria versus integração externa;
- orientation lock;
- requisitos específicos de tablets ou dobráveis.

A documentação deve distinguir:

## DECIDIDO

Algo aprovado e vigente.

## PROPOSTO

Uma ideia em avaliação.

## PENDENTE

Algo que precisa de decisão antes de determinada etapa.

## FUTURO

Algo considerado para evolução posterior, sem compromisso atual.

## HISTÓRICO

Algo verdadeiro em determinado momento, preservado apenas para contexto.

## SUBSTITUÍDO

Uma decisão antiga que deixou de ser vigente por decisão posterior.

Essa classificação evita que uma sugestão apareça em uma conversa e meses depois seja tratada como requisito oficial por geração espontânea, um talento humano bastante persistente.

---

# 5. Princípios de documentação adotados

A documentação do Gambitol utiliza práticas consolidadas de documentação e arquitetura, adaptadas para um projeto individual com objetivo profissional e didático.

Nenhum framework será seguido de forma religiosa.

A documentação deve existir porque ajuda a:

- preservar decisões;
- orientar implementação;
- ensinar conceitos;
- reduzir perda de contexto;
- facilitar manutenção;
- sustentar testes;
- preparar publicação;
- explicar o projeto profissionalmente.

---

## 5.1 README e documentação possuem funções diferentes

O `README.md` é a apresentação rápida do repositório.

Ele deve ajudar alguém a entender rapidamente:

- o que é o Gambitol;
- seu objetivo;
- stack principal;
- estado geral;
- como executar quando aplicável;
- onde está a documentação.

A documentação detalhada está em:

```text
docs/
```

O ponto inicial é:

```text
docs/00_GUIA_MESTRE.md
```

O README não deve duplicar integralmente arquitetura, regras do motor, workflow, testes ou roadmap.

---

## 5.2 Uma informação importante deve possuir um lugar oficial

Uma decisão ou regra relevante deve ter um proprietário documental claro.

Exemplos:

- visão e objetivos → `01_VISAO_E_OBJETIVOS.md`;
- método de ensino → `02_METODO_DE_ENSINO.md`;
- arquitetura → `03_ARQUITETURA_DO_GAMBITOL.md`;
- estrutura física → `04_ESTRUTURA_DO_PROJETO.md`;
- regras do xadrez → `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
- padrões Java/Android → `06_PADROES_JAVA_E_ANDROID.md`;
- Git → `07_GIT_WORKFLOW.md`;
- testes → `08_TESTES_E_QUALIDADE.md`;
- UI/UX → `09_UI_UX_GAMBITOL.md`;
- roadmap → `10_ROADMAP_E_ESCOPO.md`;
- decisões técnicas → `11_DECISOES_TECNICAS.md`;
- release → `12_PLAY_STORE_E_RELEASE.md`;
- monetização → `13_MONETIZACAO.md`;
- conteúdo/portfólio → `14_CONTEUDO_E_PORTFOLIO.md`;
- troubleshooting → `15_TROUBLESHOOTING.md`.

Outro documento pode referenciar a informação, mas deve evitar criar uma segunda versão independente dela.

Duplicação documental é perigosa porque cópias envelhecem em velocidades diferentes.

---

## 5.3 Documentação não é um único tipo de texto

O Gambitol utiliza, quando útil, a distinção proposta pelo Diátaxis:

- tutorial;
- how-to;
- referência;
- explicação.

Um mesmo arquivo pode combinar categorias quando isso melhorar a leitura.

A taxonomia existe para ajudar o conteúdo, não para criar pastas e burocracia sem necessidade.

---

## 5.4 Arquitetura deve registrar objetivos, restrições e decisões

Arquitetura não deve ser apenas desenho de caixas.

Documentação arquitetural relevante deve tornar claros, quando aplicável:

- objetivos;
- requisitos de qualidade;
- restrições;
- contexto;
- estratégia;
- limites;
- responsabilidades;
- decisões importantes.

O Gambitol deve ser arquitetado para seu tamanho real.

Não haverá arquitetura complexa apenas para parecer um projeto maior do que é.

---

## 5.5 Decisões significativas precisam preservar o “por quê”

O `11_DECISOES_TECNICAS.md` é o registro central das decisões técnicas duradouras.

Uma decisão significativa deve preservar quando necessário:

```text
contexto
problema
alternativas
decisão
motivo
consequências
trade-offs
impactos
```

Uma decisão antiga não deve ser apagada apenas para fazer parecer que a nova sempre existiu.

Quando uma decisão for substituída, o histórico deve registrar a mudança.

---

## 5.6 Diagramas devem existir apenas quando agregam valor

Diagramas podem usar princípios do modelo C4 quando forem úteis.

A preferência é começar pelo nível mais simples capaz de responder à pergunta existente.

Evitar:

- diagramas gigantes;
- diagramas de classes que envelhecem a cada refatoração;
- desenho ornamental;
- duplicação visual do que o código já deixa óbvio.

Todo diagrama deve ter propósito.

---

## 5.7 Código e documentação cumprem papéis diferentes

Princípio:

> **Código mostra o que existe. Documentação normativa mostra o que foi decidido que deve existir.**

Se a documentação disser que o motor não depende de Android e aparecer `android.*` dentro do engine, isso não significa automaticamente que a arquitetura mudou.

Pode ser uma violação.

Por outro lado, se um documento descrever como atual uma configuração que o repositório já não possui, o documento pode estar desatualizado.

Conflitos devem ser diagnosticados antes de serem corrigidos.

---

# 6. Fonte normativa e fonte descritiva

O Gambitol distingue dois tipos principais de fonte.

## 6.1 Fonte normativa

Responde:

> Como o projeto deve funcionar, ser organizado ou ser conduzido?

Inclui:

- decisões aprovadas;
- arquitetura;
- regras de domínio;
- padrões;
- escopo;
- workflow;
- critérios de qualidade.

Fontes principais:

- decisão explícita atual do mantenedor;
- documentos vigentes;
- registro de decisões aceitas.

---

## 6.2 Fonte descritiva

Responde:

> Qual é o estado real do projeto neste momento?

Inclui:

- arquivos existentes;
- conteúdo atual de uma classe;
- branch atual;
- dependências;
- configuração;
- resultado de build;
- resultado de teste;
- logs;
- erros.

Fontes principais:

- repositório;
- working tree local;
- `git status`;
- `git diff`;
- arquivos atuais;
- Gradle;
- testes;
- Android Studio;
- Logcat;
- ferramentas de diagnóstico.

Essa distinção evita:

1. inventar estado atual a partir de documentação;
2. tratar qualquer estado acidental do código como decisão arquitetural.

---

# 7. Regra de precedência em caso de conflito

Quando duas fontes se contradisserem, não escolher arbitrariamente.

Aplicar esta ordem.

## Nível 0 — decisão explícita e atual do mantenedor

Uma decisão atual do responsável pelo projeto prevalece sobre registros anteriores.

Se tiver efeito duradouro, deve ser registrada na documentação apropriada.

---

## Nível 1 — este Guia Mestre

O `00_GUIA_MESTRE.md` governa:

- precedência;
- atualização;
- classificação;
- organização documental;
- uso das fontes.

Ele não substitui detalhes pertencentes aos documentos especializados.

---

## Nível 2 — decisão técnica aceita e vigente

Uma decisão vigente registrada no `11_DECISOES_TECNICAS.md` ou em ADR equivalente prevalece sobre descrição antiga ainda não corrigida.

O documento desatualizado deve então ser alinhado.

---

## Nível 3 — documento especializado vigente

Para detalhes de um assunto, consultar o documento responsável.

Exemplos:

- xadrez → `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
- Git → `07_GIT_WORKFLOW.md`;
- UI/UX → `09_UI_UX_GAMBITOL.md`;
- Play Store → `12_PLAY_STORE_E_RELEASE.md`.

---

## Nível 4 — estado real do projeto

Para perguntas descritivas como:

- qual arquivo existe?
- qual versão está configurada?
- qual branch está ativa?
- o teste passou?
- o build funcionou?

usar evidência real do projeto.

Documentação não prova que um comando funcionou.

---

## Nível 5 — fonte externa oficial e atual

Para fatos controlados por terceiros:

- requisitos da Google Play;
- APIs Android;
- compatibilidade Gradle;
- políticas;
- Java;
- regras oficiais de xadrez;

fontes oficiais atuais prevalecem sobre informação interna envelhecida.

---

## Nível 6 — fontes secundárias e comunidade

Podem incluir:

- GitHub Issues;
- Stack Overflow;
- fóruns;
- Reddit;
- vídeos técnicos;
- artigos.

Servem como apoio, experiência prática e pista de troubleshooting.

Não substituem automaticamente fonte oficial ou evidência executável.

---

## Nível 7 — sugestão do tutor

Uma sugestão do tutor não se torna decisão porque foi escrita.

Decisões relevantes sobre:

- nomes;
- arquitetura;
- tecnologia;
- dependências;
- escopo;
- monetização;
- packages;
- release;

precisam seguir o processo de decisão do projeto.

---

# 8. Catálogo oficial de documentos

A numeração define ordem lógica de referência.

| Nº | Arquivo | Responsabilidade principal |
|---|---|---|
| 00 | `00_GUIA_MESTRE.md` | governança e navegação documental |
| 01 | `01_VISAO_E_OBJETIVOS.md` | propósito, metas e princípios |
| 02 | `02_METODO_DE_ENSINO.md` | método didático e aprendizagem |
| 03 | `03_ARQUITETURA_DO_GAMBITOL.md` | arquitetura e responsabilidades |
| 04 | `04_ESTRUTURA_DO_PROJETO.md` | estrutura física, módulos e packages |
| 05 | `05_REGRAS_DO_MOTOR_DE_XADREZ.md` | regras de domínio do xadrez |
| 06 | `06_PADROES_JAVA_E_ANDROID.md` | padrões de Java e Android |
| 07 | `07_GIT_WORKFLOW.md` | workflow Git e GitHub |
| 08 | `08_TESTES_E_QUALIDADE.md` | estratégia de testes e qualidade |
| 09 | `09_UI_UX_GAMBITOL.md` | interface e experiência |
| 10 | `10_ROADMAP_E_ESCOPO.md` | fases e fronteiras de escopo |
| 11 | `11_DECISOES_TECNICAS.md` | histórico de decisões relevantes |
| 12 | `12_PLAY_STORE_E_RELEASE.md` | publicação e release |
| 13 | `13_MONETIZACAO.md` | monetização |
| 14 | `14_CONTEUDO_E_PORTFOLIO.md` | conteúdo e comunicação profissional |
| 15 | `15_TROUBLESHOOTING.md` | diagnóstico e solução de problemas |

O status de cada documento pertence ao próprio cabeçalho do arquivo.

Este catálogo não deve duplicar status, versão ou última revisão dos demais documentos, evitando manutenção redundante.

---

# 9. Ordem mínima de consulta por tipo de trabalho

O tutor não precisa reler todos os documentos para cada alteração.

Deve consultar o conjunto mínimo relevante.

## Antes de trabalho importante

Consultar:

1. este Guia Mestre;
2. documento especializado;
3. `11_DECISOES_TECNICAS.md` quando houver decisão relevante.

## Arquitetura

Consultar:

- 00;
- 01;
- 03;
- 04;
- 06;
- 08;
- 10;
- 11.

## Classe ou package estrutural

Consultar:

- 00;
- 03;
- 04;
- 06;
- 11.

## Regra de xadrez

Consultar:

- 00;
- 03;
- 05;
- 08;
- 11.

## UI

Consultar:

- 00;
- 03;
- 09;
- 10;
- 11.

## Git

Consultar:

- 00;
- 07;
- 10;
- 11.

## Release

Consultar:

- 00;
- 07;
- 10;
- 11;
- 12.

## Ensino

Consultar:

- 00;
- 02;
- documento técnico do tema.

## Play Store

Consultar:

- 00;
- 10;
- 11;
- 12;
- 13 quando houver monetização;
- fontes oficiais atuais.

## Troubleshooting

Consultar:

- 00;
- 15;
- documento técnico relacionado;
- estado real;
- documentação oficial atual.

---

# 10. Status documental

Todo documento pode possuir um status explícito no próprio cabeçalho.

## PLANEJADO

Previsto, mas ainda não criado ou elaborado.

## RASCUNHO

Existe conteúdo em elaboração.

## EM_REVISÃO

Conteúdo preparado para avaliação, mas ainda sujeito a revisão.

## ATIVO

Documento aprovado e vigente dentro de seu escopo.

## DESATUALIZADO

Existe evidência de que parte relevante de seu conteúdo não representa mais a realidade ou decisão atual.

## SUBSTITUÍDO

Foi trocado por uma decisão ou documento posterior.

## ARQUIVADO

Não pertence mais ao fluxo ativo, mas é preservado por valor histórico.

Status não deve ser alterado apenas porque o desenvolvimento avançou normalmente.

---

# 11. Cabeçalho dos documentos

Documentos oficiais devem possuir informações suficientes para evitar ambiguidade.

Modelo:

```text
# NN — TÍTULO

> Projeto: Gambitol
> Documento: NN_NOME.md
> Versão: X.Y
> Status: ...
> Criado em: AAAA-MM-DD
> Última revisão: AAAA-MM-DD
> Responsabilidade: ...
> Fonte normativa para: ...
> Não cobre: ...
```

Nem todos os campos são obrigatórios quando não fizerem sentido.

O objetivo é permitir identificar rapidamente:

- qual é a função do arquivo;
- se ele continua vigente;
- o que ele governa;
- o que não pertence a ele.

---

# 12. Versionamento documental

Git é o histórico detalhado da documentação.

A versão no cabeçalho é apenas um indicador humano de mudança relevante.

Referência:

```text
1.0 = primeira versão aprovada
1.1 = expansão ou correção relevante compatível
2.0 = mudança significativa de significado ou estrutura
```

Não aumentar versão por alteração trivial.

Uma correção puramente textual não precisa gerar ritual de versionamento.

---

# 13. Documentação não é diário

## DECIDIDO

A documentação oficial não deve acompanhar cada passo normal do desenvolvimento.

Não atualizar documento apenas porque:

- uma classe foi criada;
- um teste passou;
- um arquivo foi movido sem alterar regra estrutural;
- uma branch foi concluída;
- um commit foi feito;
- uma PR foi integrada;
- uma fase avançou normalmente;
- houve uma pequena refatoração;
- uma implementação passou a existir conforme o plano já documentado.

O Git registra a evolução normal do projeto.

Documentação deve registrar o que precisa permanecer como referência para decisões futuras.

---

# 14. Quando atualizar documentação

Atualizar quando ocorrer pelo menos uma destas situações:

1. uma decisão normativa mudar;
2. uma informação descrita como estado vigente ficar objetivamente incorreta;
3. uma decisão técnica duradoura for tomada;
4. arquitetura ou dependências estruturais mudarem;
5. uma regra do xadrez adotada mudar;
6. uma política ou requisito externo relevante mudar;
7. uma estratégia de testes, release ou monetização for alterada;
8. um problema recorrente justificar conhecimento permanente;
9. o mantenedor solicitar explicitamente a atualização.

Atualizar apenas os documentos afetados.

Não realizar “varredura de modernização” sem necessidade.

---

# 15. Quando normalmente não atualizar

Não é necessário atualizar documentação por:

- nome de variável local;
- formatação;
- comentário;
- teste adicional que apenas cobre regra já documentada;
- implementação normal prevista no roadmap;
- commit;
- merge;
- branch;
- pequeno ajuste visual;
- refatoração interna que não altera contrato ou arquitetura;
- avanço operacional sem mudança de decisão.

Isso impede que a documentação se torne uma segunda linha do tempo do Git.

---

# 16. Protocolo para mudança de decisão

Quando uma decisão importante precisar mudar:

1. identificar a decisão vigente;
2. consultar o documento responsável;
3. explicar o motivo;
4. avaliar impacto;
5. apresentar alternativas quando houver;
6. obter aprovação;
7. registrar a nova decisão;
8. preservar o histórico da anterior quando relevante;
9. atualizar documentos dependentes;
10. implementar;
11. validar.

Não alterar primeiro e documentar depois como se sempre tivesse sido planejado.

---

# 17. Política de decisões técnicas e ADR

Nem toda escolha merece ADR.

Registrar formalmente quando a decisão afetar de forma relevante:

- estrutura;
- direção de dependências;
- tecnologias centrais;
- segurança;
- persistência;
- contratos;
- build;
- publicação;
- arquitetura;
- processo;
- escolhas difíceis de reverter.

Não criar ADR para:

- nome de variável;
- correção trivial;
- margem;
- formatação;
- refatoração sem impacto arquitetural.

Uma decisão significativa pode conter:

```text
Título
Status
Data
Contexto
Problema
Alternativas
Decisão
Motivo
Consequências
Trade-offs
Impactos
Decisão substituída
Fontes
```

O `11_DECISOES_TECNICAS.md` permanece como registro central enquanto essa estratégia atender ao projeto.

---

# 18. Protocolo de pesquisa externa

Pesquisa não existe para encontrar uma página que concorde conosco.

Ela existe para obter evidência confiável.

Ordem preferencial:

## 1. Fonte oficial primária

Exemplos:

- Android Developers;
- Google Play;
- Gradle;
- GitHub;
- Java/OpenJDK;
- FIDE.

## 2. Documentação do mantenedor da tecnologia

Exemplos:

- release notes;
- migration guide;
- repositório oficial;
- issues oficiais.

## 3. Fontes técnicas consolidadas

Exemplos:

- AWS Prescriptive Guidance;
- arc42;
- C4;
- Diátaxis.

## 4. Comunidade

Exemplos:

- GitHub Issues;
- Stack Overflow;
- fóruns;
- Reddit;
- vídeos técnicos.

Informação crítica encontrada apenas na comunidade deve ser validada antes de virar regra.

---

# 19. Fontes por assunto

## Android

Prioridade:

1. `developer.android.com`;
2. documentação AndroidX/Jetpack;
3. issues oficiais.

## Google Play

Prioridade:

1. documentação oficial Google Play;
2. Android Developers;
3. políticas oficiais.

Requisitos da Play Store mudam.

Sempre reverificar perto da publicação.

## Gradle

Prioridade:

1. `docs.gradle.org`;
2. release notes;
3. documentação do Android Gradle Plugin.

Usar o Gradle Wrapper do projeto.

## Git e GitHub

Prioridade:

1. documentação oficial Git;
2. GitHub Docs;
3. Pro Git quando útil.

## Java

Prioridade:

1. especificações/documentação oficial;
2. OpenJDK;
3. fornecedor do JDK quando o comportamento for específico.

## Xadrez

Prioridade:

1. FIDE Laws of Chess;
2. fontes oficiais complementares;
3. interpretações específicas de software documentadas separadamente.

---

# 20. Fatos que envelhecem

Algumas informações não devem ser tratadas como permanentes:

- target API da Play Store;
- políticas;
- requisitos de testes;
- APIs depreciadas;
- SDKs;
- compatibilidade AGP/Gradle/JDK;
- Billing;
- anúncios;
- política de dados;
- requisitos de publicação.

Quando relevante, registrar:

```text
Verificado em: AAAA-MM-DD
Fonte oficial: ...
```

Antes de ação crítica, pesquisar novamente.

Datas e requisitos externos documentados servem como evidência histórica da verificação, não como promessa de validade eterna.

---

# 21. Não confundir pesquisado com decidido

Uma recomendação externa não vira automaticamente decisão do Gambitol.

Fluxo:

```text
evidência
↓
contexto do Gambitol
↓
alternativas
↓
decisão
↓
registro
↓
implementação
```

A recomendação de uma plataforma precisa ser adaptada às necessidades, restrições e objetivos do projeto.

---

# 22. Governança do tutor de IA

O tutor de IA é uma ferramenta ativa do projeto, mas não é autoridade final.

Antes de orientar mudança relevante, deve:

1. identificar o objetivo;
2. consultar este Guia;
3. consultar documentos especializados;
4. verificar decisões já tomadas;
5. verificar estado real quando necessário;
6. pesquisar fontes atuais quando o fato puder ter mudado;
7. separar fato, proposta e decisão;
8. não inventar arquivos;
9. não presumir resultados;
10. não renomear elementos aprovados;
11. ensinar o conceito relevante durante a implementação.

---

## 22.1 GitHub como referência versionada atual

O repositório oficial:

```text
RAIONORIO/gambitol
```

é a principal referência para o estado versionado atual do projeto.

Quando acessível ao tutor, ele deve consultar diretamente o repositório antes de pedir ao mantenedor conteúdo que já esteja disponível nele.

Isso inclui, quando necessário:

- arquivos;
- estrutura;
- commits;
- branches;
- pull requests;
- documentação em `docs/`.

O GitHub não revela alterações locais ainda não commitadas ou não enviadas.

Quando o estado local for relevante, usar evidência do ambiente local.

---

## 22.2 Documentação e GitHub não competem

A relação é:

```text
DOCUMENTAÇÃO VIGENTE
→ define regras e decisões

REPOSITÓRIO
→ mostra estado versionado

WORKING TREE LOCAL
→ pode mostrar trabalho ainda não publicado

BUILD / TESTES / LOGS
→ provam comportamento observado
```

Nenhum deles deve ser usado fora de sua responsabilidade.

---

## 22.3 Conflito entre conversa, documentação e repositório

Se houver conflito:

### Conversa atual versus documento antigo

Decisão explícita atual do mantenedor prevalece.

Depois, avaliar atualização do documento.

### Documento versus código

Determinar primeiro se:

- o código viola a regra; ou
- o documento está desatualizado.

### Memória versus GitHub

Preferir evidência atual do repositório.

### GitHub versus estado local

Se houver trabalho local não publicado, usar o terminal para verificar.

---

# 23. Regra de não invenção

O Gambitol adota:

> **Nada que dependa do estado atual do projeto deve ser presumido quando puder ser verificado.**

Antes de afirmar:

- branch;
- arquivo;
- versão;
- configuração;
- build;
- teste;
- commit;
- push;
- merge;
- requisito externo;

obter evidência adequada.

Diferenciar sempre:

```text
esperado
```

de:

```text
confirmado
```

---

# 24. Estrutura documental não define estrutura de código

Os nomes dos documentos não autorizam criar packages equivalentes.

A estrutura real do código pertence ao:

```text
04_ESTRUTURA_DO_PROJETO.md
```

e às decisões técnicas vigentes.

Documentação é organização de conhecimento.

Packages são organização de código.

Misturar os dois porque os nomes parecem bonitos é uma forma surpreendentemente eficiente de fabricar arquitetura sem necessidade.

---

# 25. Uso de diagramas

Diagramas devem possuir quando necessário:

- título;
- objetivo;
- escopo;
- legenda;
- nível de abstração;
- correspondência razoável com o sistema.

Preferir:

1. contexto;
2. visão de alto nível;
3. componentes quando a complexidade justificar.

Não criar dez diagramas porque a ferramenta oferece dez tipos.

---

# 26. Qualidade documental

Uma documentação do Gambitol é considerada boa quando:

- responde ao próprio propósito;
- não contradiz decisões vigentes;
- distingue fato de proposta;
- não inventa implementação;
- registra fontes importantes;
- registra data para fatos temporais;
- evita duplicação;
- usa termos consistentes;
- continua legível;
- preserva contexto;
- ajuda ensino e manutenção;
- não exige atualização por progresso normal;
- não cria burocracia maior do que o problema resolvido.

---

# 27. Checklist para considerar um documento vigente

- [ ] propósito explícito;
- [ ] responsabilidade clara;
- [ ] conteúdo coerente com decisões atuais;
- [ ] propostas identificadas;
- [ ] decisões identificadas;
- [ ] caminhos verificados quando relevantes;
- [ ] versões verificadas quando relevantes;
- [ ] fontes externas adequadas;
- [ ] fatos mutáveis datados quando necessário;
- [ ] ausência de conflito conhecido;
- [ ] ausência de duplicação desnecessária;
- [ ] trade-offs relevantes registrados;
- [ ] aprovação do mantenedor quando exigida.

---

# 28. Checklist antes de implementação estrutural

Antes de mudança significativa:

- [ ] qual é o objetivo?
- [ ] está no escopo?
- [ ] existe decisão anterior?
- [ ] quais documentos se aplicam?
- [ ] conhecemos o estado real?
- [ ] arquitetura permite?
- [ ] existe requisito externo relevante?
- [ ] como testar?
- [ ] merece decisão técnica?
- [ ] qual será o ponto de validação?

Esse checklist não precisa aparecer para alterações triviais.

---

# 29. Exemplos de resolução de conflito

## Exemplo A — documentação e JDK divergem

1. verificar configuração real;
2. consultar decisão vigente;
3. verificar compatibilidade;
4. identificar qual lado está incorreto;
5. corrigir apenas depois do diagnóstico.

---

## Exemplo B — regra do motor diverge da FIDE

1. confirmar a regra oficial;
2. verificar se existe adaptação deliberada;
3. corrigir regra/testes quando necessário;
4. documentar qualquer divergência intencional.

---

## Exemplo C — engine importa Android

Não concluir automaticamente que a arquitetura mudou.

Investigar:

1. por que entrou;
2. se viola decisão vigente;
3. se pode ser removido;
4. se existe motivo real para reconsiderar arquitetura.

---

## Exemplo D — requisito da Play Store mudou

1. confirmar fonte oficial;
2. avaliar impacto;
3. atualizar documento aplicável;
4. ajustar implementação quando necessário;
5. registrar decisão duradoura se houver.

---

# 30. Definição de fonte de verdade

A fonte de verdade do Gambitol não é um único arquivo mágico.

Ela é formada por:

```text
decisões explícitas atuais
+
documentação vigente
+
registro de decisões
+
estado real do repositório
+
estado local quando relevante
+
resultados de build/teste
+
fontes externas oficiais
```

Este Guia define como essas fontes se relacionam.

---

# 31. Documentação e aprendizado

Como o Gambitol também é um projeto educacional, a documentação deve preservar raciocínio suficiente para permitir entender:

- o que foi escolhido;
- por que foi escolhido;
- quais alternativas existiam;
- quais trade-offs importaram;
- como validar.

Evitar documentos compostos apenas por:

> “Use X.”

Quando a decisão for relevante, preferir:

> “Usamos X porque resolve Y dentro da restrição Z.”

Isso ajuda aprendizado, manutenção, entrevistas e portfólio.

---

# 32. Documentação e conteúdo público

O documento:

```text
14_CONTEUDO_E_PORTFOLIO.md
```

governa como transformar o projeto em conteúdo profissional.

Regras gerais:

- conteúdo deve refletir estado real;
- feature planejada não deve ser apresentada como pronta;
- segredo não entra em material público;
- evidência vale mais que claim;
- projeto não deve ser alterado apenas para produzir postagem.

---

# 33. Segurança documental

Nunca registrar em Markdown ou Git:

- senha;
- token;
- chave privada;
- keystore password;
- segredo de API;
- credencial;
- informação sensível desnecessária.

Quando configuração exigir segredo, documentar:

```text
nome
finalidade
local esperado
```

mas não o valor.

---

# 34. Gradle Wrapper

## DECIDIDO

Usar o Gradle Wrapper do projeto.

No Git Bash:

```bash
./gradlew <task>
```

Não depender de Gradle global para o fluxo normal.

Os arquivos necessários do Wrapper fazem parte do projeto versionado.

---

# 35. Princípios arquiteturais gerais

O Gambitol adota como princípios:

- separação de responsabilidades;
- limites claros;
- regras fora da `Activity`;
- motor independente de Android;
- testabilidade;
- clareza;
- dependências justificadas;
- estado controlado;
- arquitetura proporcional ao problema.

A implementação concreta pertence aos documentos:

```text
03_ARQUITETURA_DO_GAMBITOL.md
04_ESTRUTURA_DO_PROJETO.md
06_PADROES_JAVA_E_ANDROID.md
11_DECISOES_TECNICAS.md
```

---

# 36. Política contra overengineering documental

Sinais de excesso:

- mesmo texto em vários arquivos;
- status duplicado em catálogos;
- árvores atualizadas a cada nova classe;
- changelog escondido em documento técnico;
- diagramas inúteis;
- decisão formal para trivialidade;
- documentação que custa mais que o problema.

Regra:

> **Documentar o suficiente para preservar contexto, decisão e conhecimento. Não documentar para encenar complexidade.**

---

# 37. Política contra subdocumentação

Também evitar:

- “depois a gente lembra”;
- arquitetura apenas na memória;
- regra de xadrez sem especificação;
- decisão importante sem motivo;
- dependência sem justificativa;
- requisito de release verificado tarde demais;
- alteração normativa sem atualização correspondente.

O Gambitol precisa registrar o que realmente importa.

---

# 38. Gatilhos de revisão deste Guia Mestre

Este arquivo só precisa ser revisado quando sua própria governança mudar.

Exemplos:

- documentos oficiais forem adicionados ou removidos;
- localização oficial da documentação mudar;
- regra de precedência mudar;
- estratégia de ADR mudar;
- processo documental mudar;
- papel do tutor de IA mudar significativamente;
- forma de consultar o repositório mudar;
- surgir falha recorrente que este Guia não consiga resolver.

Não revisar porque:

- nova feature foi implementada;
- fase terminou;
- PR foi integrada;
- branch foi removida;
- classe foi criada;
- teste passou;
- um mês se passou.

---

# 39. Manutenção da documentação oficial

Os documentos `00–15` já compõem a base documental oficial do projeto.

Não existe necessidade de recriar essa série durante o desenvolvimento normal.

Daqui em diante, a manutenção deve ser pontual:

```text
mudou decisão relevante?
→ atualizar documento afetado

informação vigente ficou falsa?
→ corrigir documento afetado

nova decisão duradoura?
→ registrar onde pertence

avanço normal do código?
→ Git já registra
```

O objetivo é manter documentação estável e confiável.

Não buscar “atualizar todos os documentos” periodicamente apenas porque o projeto avançou.

---

# 40. Resumo operacional para o tutor

Antes de orientar:

```text
QUAL É A TAREFA?
        ↓
CONSULTAR 00
        ↓
CONSULTAR DOCUMENTO DO TEMA
        ↓
HÁ DECISÃO VIGENTE?
        ↓
PRECISO VER O ESTADO REAL?
        ↓
CONSULTAR GITHUB / GIT / ARQUIVOS
        ↓
FATO EXTERNO PODE TER MUDADO?
        ↓
PESQUISAR FONTE OFICIAL
        ↓
SEPARAR FATO / PROPOSTA / DECISÃO
        ↓
EXPLICAR
        ↓
IMPLEMENTAR UM PASSO
        ↓
VALIDAR
        ↓
CONTINUAR
```

---

# 41. Resumo operacional para o mantenedor

Quando surgir dúvida sobre onde uma informação pertence:

- propósito → 01;
- ensino → 02;
- arquitetura → 03;
- estrutura → 04;
- xadrez → 05;
- Java/Android → 06;
- Git → 07;
- testes → 08;
- UI/UX → 09;
- roadmap → 10;
- decisões → 11;
- publicação → 12;
- monetização → 13;
- conteúdo → 14;
- troubleshooting → 15.

Se nenhuma categoria servir:

1. verificar se precisa mesmo ser documentado;
2. procurar seção existente;
3. só então avaliar novo documento.

---

# 42. Fontes conceituais desta documentação

As fontes abaixo sustentam os princípios gerais utilizados na criação desta base documental.

## Android Developers

**Guide to app architecture**  
https://developer.android.com/topic/architecture

**Recommendations for Android architecture**  
https://developer.android.com/topic/architecture/recommendations

Aplicadas a:

- separação de responsabilidades;
- limites;
- testabilidade;
- estado;
- redução de acoplamento.

---

## GitHub Docs

**About READMEs**  
https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes

**Best practices for repositories**  
https://docs.github.com/en/repositories/creating-and-managing-repositories/best-practices-for-repositories

Aplicadas a:

- README como entrada;
- organização;
- documentação navegável.

---

## AWS Prescriptive Guidance

**Architectural Decision Records**  
https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/introduction.html

Aplicada a:

- contexto;
- decisão;
- consequências;
- preservação do motivo.

---

## Diátaxis

https://diataxis.fr/

Aplicado à distinção entre:

- tutorial;
- how-to;
- referência;
- explicação.

---

## arc42

https://arc42.org/

Aplicado a:

- objetivos;
- restrições;
- contexto;
- decisões;
- arquitetura compreensível.

---

## C4 Model

https://c4model.com/

Aplicado ao uso progressivo e intencional de diagramas.

---

## Gradle

**Gradle Wrapper**  
https://docs.gradle.org/current/userguide/gradle_wrapper.html

Aplicado à execução reproduzível do build.

---

## Google Play

https://developer.android.com/google/play

Aplicado ao princípio de reverificar requisitos externos antes de publicação.

---

## FIDE

**FIDE Laws of Chess**  
https://handbook.fide.com/

Aplicado à definição da autoridade primária para regras oficiais de xadrez.

---

# 43. Conclusão

O Gambitol deve possuir documentação suficiente para que decisões importantes não dependam de memória ou de centenas de mensagens antigas.

A documentação deve permitir responder:

- qual decisão está vigente?
- onde essa regra pertence?
- como o motor se relaciona com Android?
- qual fonte consultar?
- como verificar o estado real?
- quando uma atualização documental é necessária?
- quando não é?
- como impedir que sugestão vire requisito?
- como preservar o motivo das decisões?

O objetivo não é manter documentos constantemente ocupados descrevendo cada movimento do desenvolvimento.

O objetivo é manter uma base estável capaz de orientar o projeto por muito tempo.

> **Documentação deve preservar contexto e decisões, não competir com o Git para ver quem consegue registrar mais acontecimentos.**

Este documento é o mapa.

Os demais documentos são as fontes especializadas.
