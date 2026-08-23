# 00 — GUIA MESTRE DE DOCUMENTAÇÃO DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** 00_GUIA_MESTRE.md  
> **Versão inicial:** 1.0  
> **Status:** ATIVO após aprovação do mantenedor  
> **Data de criação:** 22/08/2026  
> **Idioma principal:** Português do Brasil  
> **Função:** documento-raiz e regra de navegação de toda a documentação do projeto  
> **Escopo:** governança documental, precedência, atualização, pesquisa, uso das fontes e prevenção de perda de contexto  
> **Não substitui:** arquitetura detalhada, regras de xadrez, padrões de código, workflow Git, testes, UI/UX, roadmap, release ou monetização  

---

## 1. Por que este documento existe

O Gambitol não será tratado apenas como um aplicativo que precisa “funcionar”. Ele será desenvolvido simultaneamente como:

1. um jogo de xadrez mobile para Android;
2. um projeto real de engenharia de software;
3. um projeto de aprendizado prático de Java e Android;
4. uma peça de portfólio;
5. um produto com intenção de publicação na Google Play Store;
6. um possível ativo comercial e de construção de reputação profissional.

Esses objetivos criam um risco previsível: conforme o projeto crescer, decisões podem ficar espalhadas entre conversas, código, README, memória, prints, documentação externa e novas ideias. Sem um sistema claro, o projeto começa a sofrer de “amnésia arquitetural”: decisões são refeitas, regras entram em conflito, o código diverge da intenção original e a documentação passa a descrever um sistema que já não existe.

Este documento existe para impedir isso.

O `00_GUIA_MESTRE.md` é o ponto de entrada da documentação do Gambitol. Ele explica:

- quais documentos compõem a fonte de verdade do projeto;
- qual é a responsabilidade de cada documento;
- qual fonte prevalece quando duas informações entram em conflito;
- como diferenciar decisão, proposta, estado atual e informação externa;
- quando uma documentação precisa ser atualizada;
- como novas decisões devem ser registradas;
- como informações externas devem ser pesquisadas e verificadas;
- como o tutor de IA deve consultar as fontes antes de orientar mudanças;
- como evitar que documentação, código e objetivos se afastem entre si.

A função deste arquivo é governar a documentação. Ele não deve virar um depósito onde todas as outras documentações são repetidas.

---

## 2. Decisões já estabelecidas no início do projeto

As seguintes decisões já foram definidas e não devem ser alteradas silenciosamente:

| Item | Decisão atual |
|---|---|
| Nome do jogo | Gambitol |
| Nome do repositório/pasta principal | `gambitol` |
| Plataforma inicial | Android |
| Categoria | Jogo mobile de xadrez |
| Linguagem principal | Java |
| Package/Application namespace definido | `br.com.raionorio.gambitol` |
| IDE principal para Android | Android Studio |
| Terminal principal durante o desenvolvimento | Git Bash no Windows |
| Sistema de build | Gradle, usando o Gradle Wrapper do projeto |
| Objetivo de distribuição | Google Play Store |
| Estratégia didática | desenvolvimento rápido com acompanhamento de tutor, sem transformar código em caixa-preta |
| Regra arquitetural já aprovada | lógica de xadrez separada da interface Android |
| Referência visual inicial | mockup premium escuro do Gambitol, com dourado, verde, tabuleiro central, jogadores, cronômetros e controles |

Esses itens são fatos aprovados do projeto.

Qualquer proposta futura que contradiga um deles deve ser apresentada explicitamente como **mudança de decisão**, com justificativa e aprovação. Não se deve modificar um deles “porque parece melhor” durante uma implementação.

---

## 3. O que ainda NÃO deve ser tratado como decisão

Ideias futuras não são automaticamente requisitos.

Enquanto não forem aprovadas, itens como os seguintes devem ser tratados como possibilidades:

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
- arquitetura final de todas as camadas;
- bibliotecas externas ainda não escolhidas;
- sistema definitivo de navegação;
- motor externo de xadrez;
- engine própria versus biblioteca para IA;
- orientation lock;
- suporte a tablets ou dobráveis como requisito de primeira versão.

A documentação deve distinguir claramente:

**DECIDIDO**  
Algo aprovado e vigente.

**PROPOSTO**  
Uma ideia em avaliação.

**PENDENTE**  
Algo que precisa de decisão antes de avançar.

**FUTURO**  
Algo considerado para versões posteriores, sem compromisso atual.

Isso evita uma das formas mais comuns de desvio de escopo: uma sugestão aparecer em uma conversa e semanas depois ser tratada como se sempre tivesse sido requisito.

---

# 4. Princípios de documentação adotados

A estrutura documental do Gambitol foi desenhada a partir de práticas consolidadas de documentação e arquitetura de software, adaptadas para um projeto individual que também possui objetivo didático.

Não será adotado nenhum framework de documentação de forma religiosa. O projeto usará somente aquilo que trouxer valor real.

## 4.1 Documentação deve ter um ponto de entrada claro

O GitHub recomenda que todo repositório possua um README capaz de explicar o projeto e facilitar sua navegação. O próprio GitHub também observa que documentação extensa não precisa ficar concentrada no README.

Aplicação no Gambitol:

- `README.md` continua sendo a apresentação rápida do repositório;
- `00_GUIA_MESTRE.md` é a porta de entrada para a documentação interna;
- os demais documentos aprofundam assuntos específicos;
- o README não deve duplicar integralmente as documentações;
- links relativos devem ser usados quando os arquivos estiverem no repositório.

O usuário que chega ao repositório precisa entender rapidamente “o que é o Gambitol”.  
O desenvolvedor ou tutor que precisa tomar uma decisão precisa saber “qual documento consultar”.

São necessidades diferentes.

---

## 4.2 Uma informação importante deve possuir um lugar oficial

A arquitetura Android recomenda o princípio de **Single Source of Truth (SSOT)** para dados: uma informação deve possuir um proprietário claro, responsável por suas alterações.

O Gambitol aplica o mesmo raciocínio à documentação.

Exemplos:

- regras oficiais do projeto sobre ensino pertencem a `02_METODO_DE_ENSINO.md`;
- estrutura de packages pertence a `04_ESTRUTURA_DO_PROJETO.md`;
- regras do jogo pertencem a `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
- decisões arquitetônicas aceitas pertencem ao registro de decisões;
- requisitos de publicação pertencem a `12_PLAY_STORE_E_RELEASE.md`.

Outro documento pode referenciar a informação, mas deve evitar criar uma segunda versão independente dela.

Duplicação documental é perigosa porque as cópias inevitavelmente envelhecem em velocidades diferentes.

---

## 4.3 Documentação não é um único tipo de texto

O modelo Diátaxis distingue quatro necessidades documentais:

- **tutorial:** ajuda alguém a aprender;
- **how-to:** orienta alguém a executar uma tarefa;
- **referência:** descreve informações precisas para consulta;
- **explicação:** ajuda a entender conceitos e decisões.

O Gambitol usará essa distinção como ferramenta de qualidade, não como obrigação burocrática.

Um mesmo arquivo pode ter um propósito dominante e pequenas seções de outro tipo quando isso facilitar a leitura.

Exemplos:

- `02_METODO_DE_ENSINO.md` é principalmente orientação didática;
- `05_REGRAS_DO_MOTOR_DE_XADREZ.md` será principalmente referência;
- `07_GIT_WORKFLOW.md` será principalmente how-to + política;
- `03_ARQUITETURA_DO_GAMBITOL.md` será principalmente explicação e referência;
- `15_TROUBLESHOOTING.md` será principalmente how-to baseado em incidentes reais.

Não serão criadas pastas vazias apenas para satisfazer uma taxonomia. A própria orientação do Diátaxis desencoraja criar estruturas vazias sem conteúdo real.

---

## 4.4 Arquitetura deve registrar objetivos, restrições e decisões

O arc42 enfatiza que arquitetura não deve ser apenas desenho de caixas. Ela precisa tornar claros:

- objetivos;
- requisitos de qualidade;
- restrições;
- contexto;
- estratégia da solução;
- blocos principais;
- decisões relevantes.

Aplicação no Gambitol:

- não documentaremos arquitetura como decoração;
- cada camada ou package importante precisa ter responsabilidade explicável;
- uma decisão arquitetural deve existir porque resolve um problema real;
- restrições como “Java como linguagem principal” devem ser registradas;
- requisitos de qualidade como testabilidade, clareza e manutenção devem influenciar as decisões.

O projeto deve ser arquitetado para seu tamanho real. Não haverá “arquitetura enterprise” teatral para um jogo mobile individual.

---

## 4.5 Decisões significativas precisam preservar o “por quê”

A AWS recomenda o uso de Architectural Decision Records (ADRs) para registrar decisões arquiteturalmente relevantes, incluindo contexto, decisão e consequências. Um princípio particularmente útil é preservar decisões aceitas em vez de reescrevê-las retroativamente.

Aplicação no Gambitol:

- `11_DECISOES_TECNICAS.md` funcionará inicialmente como registro central das decisões;
- decisões importantes devem guardar contexto, alternativas relevantes, decisão e consequência;
- uma decisão antiga não deve ser apagada para fazer parecer que a decisão nova sempre existiu;
- se uma decisão for substituída, o histórico deverá indicar por qual decisão ela foi substituída;
- se o volume crescer, decisões arquitetônicas grandes poderão ganhar ADRs individuais posteriormente.

O objetivo não é produzir atas. É impedir que o mesmo problema seja rediscutido do zero e permitir explicar por que o projeto chegou à arquitetura atual.

---

## 4.6 Diagramas devem revelar níveis de detalhe progressivamente

O modelo C4 propõe níveis de zoom para arquitetura: contexto do sistema, containers, componentes e código. O próprio C4 deixa claro que nem todo projeto precisa de todos os níveis; os diagramas devem existir apenas quando agregarem valor.

Aplicação futura no Gambitol:

- começar pelo contexto geral do aplicativo;
- documentar a divisão entre Android/UI e motor de xadrez;
- detalhar componentes somente quando a complexidade justificar;
- evitar diagramas enormes de classes que ficam obsoletos após duas refatorações;
- todo diagrama deve responder a uma pergunta concreta.

Diagrama sem objetivo é apenas decoração com setas.

---

## 4.7 O código e a documentação cumprem papéis diferentes

Um princípio essencial deste projeto:

> **Código mostra o que existe. Documentação normativa mostra o que foi decidido que deve existir.**

Se `04_ESTRUTURA_DO_PROJETO.md` disser que o motor não deve depender de Android, mas aparecer uma importação de `android.*` dentro do motor, o código não “vence” automaticamente. Isso pode indicar uma violação arquitetural.

Por outro lado, se a documentação afirmar que uma classe existe, mas o repositório real mostrar que ela foi removida, a documentação pode estar desatualizada.

Portanto, conflitos devem ser classificados antes de serem resolvidos.

---

# 5. Dois tipos de fonte de verdade: normativa e descritiva

O Gambitol adota dois conceitos complementares.

## 5.1 Fonte normativa

Responde:

> Como o projeto deve funcionar, ser organizado ou ser conduzido?

Exemplos:

- decisões aprovadas;
- arquitetura;
- regras de domínio;
- padrões;
- escopo;
- workflow;
- critérios de qualidade.

Fontes normativas principais:

- decisão explícita atual do mantenedor;
- documentos ATIVOS;
- registro de decisões aceitas.

## 5.2 Fonte descritiva

Responde:

> Qual é o estado real do projeto neste momento?

Exemplos:

- arquivos que existem;
- conteúdo atual de uma classe;
- branch atual;
- dependências instaladas;
- resultado de um build;
- resultado de testes;
- versão realmente configurada;
- erro que realmente ocorreu.

Fontes descritivas principais:

- repositório real;
- `git status`;
- `git diff`;
- arquivos atuais;
- saída do Gradle;
- testes;
- Android Studio;
- Logcat;
- ferramentas oficiais de diagnóstico.

Essa distinção impede dois erros:

1. inventar o estado do projeto a partir de uma documentação antiga;
2. aceitar uma violação do projeto apenas porque “o código está assim”.

---

# 6. Regra de precedência em caso de conflito

Quando duas fontes se contradisserem, NÃO escolher arbitrariamente. Aplicar esta sequência.

## Nível 0 — decisão explícita e atual do mantenedor

Uma decisão explícita tomada agora pelo responsável do projeto prevalece sobre documentos antigos.

Ela deve, porém, ser registrada na documentação apropriada se tiver efeito duradouro.

Exemplo:

> “Não vamos mais usar a biblioteca X.”

Essa decisão passa a valer e a documentação deve ser corrigida.

---

## Nível 1 — este Guia Mestre para regras de governança

O `00_GUIA_MESTRE.md` governa:

- qual documento consultar;
- como resolver conflito;
- como atualizar documentação;
- como classificar informação;
- como pesquisar fontes externas.

Ele não deve sobrescrever detalhes técnicos pertencentes aos documentos especializados.

---

## Nível 2 — decisão técnica aceita e vigente

Uma decisão registrada como vigente em `11_DECISOES_TECNICAS.md` ou ADR equivalente prevalece sobre uma descrição arquitetural mais antiga que ainda não foi atualizada.

Isso exige depois corrigir o documento desatualizado.

---

## Nível 3 — documento especializado ATIVO

Para o assunto específico, consultar o documento responsável.

Exemplos:

- regras do xadrez → `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
- Git → `07_GIT_WORKFLOW.md`;
- UI/UX → `09_UI_UX_GAMBITOL.md`;
- Play Store → `12_PLAY_STORE_E_RELEASE.md`.

---

## Nível 4 — estado real do repositório e ferramentas

Para perguntas descritivas como “qual arquivo existe?”, “qual versão está configurada?” ou “o build passou?”, o estado real do projeto prevalece.

Nunca usar documentação como prova de que um build passou.

---

## Nível 5 — fonte externa oficial e atualizada

Para fatos controlados por terceiros, como:

- requisitos da Google Play;
- APIs Android;
- compatibilidade Gradle;
- políticas;
- regras oficiais do xadrez;

uma fonte oficial atualizada prevalece sobre uma documentação interna desatualizada.

Porém, antes de agir em uma mudança significativa, o documento interno correspondente deve ser atualizado ou claramente marcado como desatualizado.

---

## Nível 6 — fontes secundárias e comunidade

Artigos, fóruns, Stack Overflow, Reddit, vídeos e discussões podem fornecer contexto, casos reais e pistas de troubleshooting.

Eles não devem substituir:

- documentação oficial de API;
- política oficial da Play Store;
- regra oficial da FIDE;
- resultado real de build/teste.

Comunidade é excelente para descobrir problemas. É péssima como autoridade automática.

---

## Nível 7 — sugestão do tutor/IA

Uma sugestão do tutor nunca se torna decisão apenas porque foi escrita.

Antes de alterar:

- nome;
- arquitetura;
- tecnologia;
- dependência;
- escopo;
- monetização;
- identidade;
- package;
- estratégia de release;

a proposta precisa ser apresentada e aprovada quando representar decisão relevante.

---

# 7. Catálogo oficial de documentos

A numeração define ordem lógica de leitura, não “importância absoluta”.

| Nº | Arquivo | Responsabilidade | Tipo dominante | Status inicial |
|---|---|---|---|---|
| 00 | `00_GUIA_MESTRE.md` | governança e navegação documental | referência/explicação | ATIVO |
| 01 | `01_VISAO_E_OBJETIVOS.md` | propósito, metas e princípios do produto | explicação | PLANEJADO |
| 02 | `02_METODO_DE_ENSINO.md` | método didático do tutor e aprendizagem | how-to/explicação | PLANEJADO |
| 03 | `03_ARQUITETURA_DO_GAMBITOL.md` | arquitetura, limites e responsabilidades | explicação/referência | PLANEJADO |
| 04 | `04_ESTRUTURA_DO_PROJETO.md` | packages, diretórios e localização do código | referência | PLANEJADO |
| 05 | `05_REGRAS_DO_MOTOR_DE_XADREZ.md` | regras de domínio do xadrez implementadas | referência | PLANEJADO |
| 06 | `06_PADROES_JAVA_E_ANDROID.md` | padrões de código e plataforma | referência | PLANEJADO |
| 07 | `07_GIT_WORKFLOW.md` | branches, staging, commits, merges e releases | how-to/política | PLANEJADO |
| 08 | `08_TESTES_E_QUALIDADE.md` | estratégia e critérios de testes/qualidade | referência/how-to | PLANEJADO |
| 09 | `09_UI_UX_GAMBITOL.md` | interface, experiência e referência visual | referência/explicação | PLANEJADO |
| 10 | `10_ROADMAP_E_ESCOPO.md` | fases, MVP e fronteiras de escopo | referência | PLANEJADO |
| 11 | `11_DECISOES_TECNICAS.md` | histórico das decisões relevantes | referência histórica | PLANEJADO |
| 12 | `12_PLAY_STORE_E_RELEASE.md` | publicação, versionamento e Play Console | how-to/referência | PLANEJADO |
| 13 | `13_MONETIZACAO.md` | hipóteses e decisões de monetização | explicação/referência | PLANEJADO |
| 14 | `14_CONTEUDO_E_PORTFOLIO.md` | conteúdo técnico e comunicação profissional | how-to | PLANEJADO |
| 15 | `15_TROUBLESHOOTING.md` | problemas reais, diagnóstico e solução | how-to/referência | PLANEJADO |

### Regra

Um documento com status `PLANEJADO` não possui autoridade sobre o projeto porque ainda não foi criado/aprovado.

Não inventar seu conteúdo com base apenas no nome.

---

# 8. Ordem mínima de consulta por tipo de trabalho

O tutor não precisa reler todos os documentos para cada vírgula alterada. Deve carregar o conjunto mínimo relevante.

## Antes de qualquer trabalho importante

Consultar:

1. `00_GUIA_MESTRE.md`;
2. o documento especializado do assunto;
3. `11_DECISOES_TECNICAS.md` quando a ação envolver decisão relevante.

## Alteração de arquitetura

Consultar:

- 00;
- 01;
- 03;
- 04;
- 06;
- 08;
- 10;
- 11.

## Criação de classe/package estrutural

Consultar:

- 00;
- 03;
- 04;
- 06;
- 11.

## Implementação de regra de xadrez

Consultar:

- 00;
- 03;
- 05;
- 08;
- 11.

## Trabalho de UI

Consultar:

- 00;
- 03;
- 09;
- 10;
- 11.

## Git, branch, commit ou release

Consultar:

- 00;
- 07;
- 10;
- 11;
- 12 quando envolver distribuição.

## Ensino ou explicação

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
- 13 se houver monetização;
- fontes oficiais atuais do Google.

## Troubleshooting

Consultar:

- 00;
- 15;
- documento técnico relacionado;
- estado real do projeto;
- documentação oficial atualizada;
- comunidade somente como complemento.

---

# 9. Status documental

Todo documento deverá possuir um status explícito.

## PLANEJADO

Foi previsto, mas ainda não existe como regra.

## RASCUNHO

Existe conteúdo, porém ainda está em elaboração.

Um rascunho pode orientar discussão, mas não deve ser citado como decisão definitiva.

## EM_REVISÃO

Conteúdo suficientemente maduro para avaliação, aguardando aprovação.

## ATIVO

Documento aprovado e vigente.

É fonte normativa dentro de seu escopo.

## DESATUALIZADO

Há evidência de que parte de seu conteúdo não representa mais a realidade ou uma fonte externa mudou.

Não utilizar a parte afetada como autoridade até revisão.

## SUBSTITUÍDO

Foi trocado por uma versão/decisão posterior.

Permanece apenas para histórico quando isso tiver valor.

## ARQUIVADO

Não pertence mais ao fluxo ativo, mas é preservado por histórico.

---

# 10. Cabeçalho obrigatório dos documentos

Os documentos futuros devem começar com informações suficientes para evitar ambiguidade.

Modelo:

```text
# NN — TÍTULO

> Projeto: Gambitol
> Documento: NN_NOME.md
> Versão: X.Y
> Status: RASCUNHO | ATIVO | ...
> Criado em: AAAA-MM-DD
> Última revisão: AAAA-MM-DD
> Responsabilidade: ...
> Fonte normativa para: ...
> Não cobre: ...
```

Campos podem ser adaptados quando não fizerem sentido.

O objetivo não é burocracia. É conseguir responder rapidamente:

- este arquivo ainda vale?
- quando foi revisado?
- o que ele governa?
- o que não devo procurar aqui?

---

# 11. Regra de versionamento dos documentos

Não usar versionamento como ritual.

Para documentos ativos:

- correção pequena sem mudança de decisão → incremento de revisão textual, sem necessidade de “versão maior”;
- mudança relevante de conteúdo → atualizar versão;
- mudança que substitui uma decisão importante → registrar também em `11_DECISOES_TECNICAS.md`;
- requisito externo alterado → atualizar data de verificação e fonte.

Sugestão de leitura de versão:

- `1.0` = primeira versão aprovada;
- `1.1` = expansão compatível;
- `2.0` = reestruturação ou mudança significativa do significado.

Git continuará sendo o histórico detalhado. A versão no cabeçalho é um sinal humano rápido.

---

# 12. Quando atualizar documentação

A documentação deve acompanhar mudanças importantes, não cada alteração cosmética.

## Atualização necessária quando

- uma decisão arquitetural muda;
- uma nova camada/package estrutural é criada;
- uma regra de xadrez muda;
- uma biblioteca relevante é adotada ou removida;
- um fluxo de Git muda;
- um requisito de qualidade muda;
- o MVP muda;
- uma feature entra ou sai do roadmap;
- uma exigência da Play Store muda;
- são adicionadas permissões;
- coleta de dados é introduzida;
- monetização é definida;
- uma estratégia de testes muda;
- um problema recorrente ganha solução consolidada;
- o layout aprovado muda de direção;
- uma informação do documento fica comprovadamente desatualizada.

## Atualização normalmente desnecessária quando

- nome de variável local muda;
- formatação é ajustada;
- refatoração interna não altera contrato, estrutura ou comportamento relevante;
- comentário é corrigido;
- detalhe visual microscópico é ajustado sem impacto de guideline.

Documentação excessivamente sensível a cada linha vira obrigação e depois é abandonada.

---

# 13. Protocolo para uma mudança de decisão

Quando uma decisão importante precisar mudar:

1. identificar qual decisão atual será afetada;
2. consultar o documento que a contém;
3. explicar o motivo da mudança;
4. avaliar impacto em código, testes, documentação e roadmap;
5. apresentar alternativas quando houver;
6. obter aprovação;
7. registrar a nova decisão;
8. marcar decisão anterior como substituída quando necessário;
9. atualizar documentos dependentes;
10. somente então executar mudanças estruturais.

Isso evita “refatoração por impulso”.

---

# 14. Política de decisões técnicas e ADR

Nem toda escolha merece um ADR.

Registrar decisão quando ela afetar de forma relevante:

- estrutura;
- dependências entre partes;
- tecnologias centrais;
- requisitos não funcionais;
- segurança;
- persistência;
- API/contrato;
- estratégia de build;
- publicação;
- processo de desenvolvimento;
- arquitetura;
- decisões difíceis de reverter.

Não criar ADR para:

- nome de variável;
- ajuste pequeno de margem;
- correção trivial;
- refatoração sem consequência arquitetural.

## Conteúdo mínimo de uma decisão significativa

```text
Título
Status
Data
Contexto
Problema
Alternativas relevantes
Decisão
Motivo
Consequências positivas
Consequências negativas / trade-offs
Impactos
Decisão substituída, se houver
Fontes, quando aplicável
```

A AWS destaca que preservar o motivo da decisão reduz rediscussões e ajuda futuros participantes a entender por que o sistema foi construído daquela forma.

---

# 15. Protocolo de pesquisa externa

Quando um documento exigir pesquisa, o objetivo não é “achar uma página que concorde com a solução”. É obter evidência confiável.

## 15.1 Ordem preferencial das fontes

### 1. Fonte oficial primária

Exemplos:

- Android Developers;
- Google Play Console Help;
- Gradle Docs;
- GitHub Docs;
- documentação oficial Java/OpenJDK;
- FIDE Handbook para regras de xadrez.

### 2. Especificação ou documentação do mantenedor da tecnologia

Exemplos:

- repositório oficial;
- release notes;
- migration guide;
- documentação da biblioteca.

### 3. Fontes técnicas consolidadas

Exemplos:

- AWS Prescriptive Guidance;
- arc42;
- C4 Model;
- Diátaxis.

### 4. Comunidade

Exemplos:

- Stack Overflow;
- fóruns;
- GitHub Issues;
- Reddit;
- discussões de desenvolvedores;
- vídeos técnicos.

Comunidade é particularmente útil para:

- bugs reais;
- comportamento de versões específicas;
- limitações de hardware;
- problemas de IDE;
- dificuldades que documentação oficial não deixa óbvias.

Toda informação crítica encontrada apenas na comunidade deve ser validada antes de virar regra de projeto.

---

## 15.2 Fontes por assunto

### Android

Prioridade:

1. `developer.android.com`;
2. documentação oficial das bibliotecas AndroidX/Jetpack;
3. issues oficiais quando necessário.

### Google Play

Prioridade:

1. `support.google.com/googleplay/android-developer`;
2. `developer.android.com/google/play`;
3. políticas oficiais.

Requisitos da Play Store são mutáveis. Não confiar apenas em conhecimento previamente memorizado.

### Gradle

Prioridade:

1. `docs.gradle.org`;
2. release notes oficiais;
3. documentação do Android Gradle Plugin quando o assunto for Android.

A documentação atual do Gradle recomenda usar o Wrapper (`gradlew`/`gradlew.bat`) porque ele fixa a versão do Gradle e padroniza builds entre ambientes.

### Git/GitHub

Prioridade:

1. documentação oficial Git;
2. GitHub Docs;
3. Pro Git quando útil.

### Java

Prioridade:

1. documentação/especificações oficiais do Java;
2. OpenJDK;
3. documentação do fornecedor do JDK quando o comportamento for específico.

### Regras do xadrez

Prioridade:

1. FIDE Laws of Chess;
2. fontes oficiais complementares;
3. interpretação específica de software documentada separadamente.

A implementação do jogo não deve depender de “eu sempre joguei assim”.

---

# 16. Regras especiais para fatos que envelhecem

Algumas informações possuem prazo de validade implícito:

- target API exigida pela Play Store;
- políticas de publicação;
- quantidade/duração de testadores exigidos;
- APIs depreciadas;
- versões de SDK;
- compatibilidade AGP/Gradle/JDK;
- política de dados;
- requisitos de billing;
- regras de anúncios;
- limites de plataforma.

Para essas informações, todo documento deve registrar:

```text
Verificado em: AAAA-MM-DD
Fonte oficial: ...
```

Antes de uma ação importante, como publicar o aplicativo, pesquisar novamente.

### Exemplo atual

Em agosto de 2026, a documentação oficial do Android informa que, a partir de 31/08/2026, novos apps e atualizações destinados a celulares devem mirar Android 16 / API 36 ou superior.

Isso é um exemplo perfeito de informação que não deve ser eternizada como se fosse uma constante. O documento de release deverá verificar a regra novamente quando o Gambitol estiver próximo da publicação.

---

# 17. Não confundir “pesquisado” com “decidido”

Uma pesquisa pode concluir:

> “A recomendação oficial do Android é X.”

Isso não significa automaticamente:

> “O Gambitol adotará X.”

O processo correto é:

```text
evidência externa
↓
análise no contexto do Gambitol
↓
alternativas
↓
decisão
↓
registro
↓
implementação
```

Exemplo:

O Android pode recomendar determinada arquitetura moderna. O Gambitol é Java e possui objetivos didáticos específicos. A recomendação deve ser adaptada ao projeto, como o próprio guia de arquitetura Android admite ao tratar suas recomendações como orientação que pode ser adaptada.

---

# 18. Governança do tutor de IA

O tutor de IA é uma ferramenta ativa do projeto, mas não é autoridade final.

Antes de orientar uma mudança relevante, deverá:

1. identificar o objetivo da tarefa atual;
2. consultar este Guia Mestre;
3. consultar os documentos especializados relevantes;
4. verificar decisões já tomadas;
5. verificar o estado real do código quando a resposta depender dele;
6. pesquisar fontes oficiais quando a informação puder ter mudado;
7. separar claramente fato, sugestão e decisão;
8. não inventar arquivos ou estados;
9. não renomear elementos aprovados sem autorização;
10. ensinar o conceito relevante enquanto conduz a implementação.

## Quando houver conflito entre memória e documentação

A documentação ativa deve ser preferida.

## Quando houver conflito entre documentação e conversa atual

A decisão explícita atual do mantenedor prevalece e deve disparar atualização documental.

## Quando houver conflito entre documentação e repositório

Primeiro determinar se:

- o código está violando a regra; ou
- a documentação ficou desatualizada.

Nunca “corrigir” automaticamente um lado sem diagnóstico.

## Quando o repositório oficial estiver disponível no GitHub

Após o Gambitol ser enviado ao repositório remoto oficial no GitHub e esse repositório estar acessível ao tutor, o GitHub deverá ser usado como referência principal para consultar o estado versionado atual do código.

Antes de orientar uma mudança que dependa da implementação existente, o tutor deverá, quando possível:

- consultar o repositório oficial do Gambitol;
- verificar a branch relevante;
- ler os arquivos atuais envolvidos na tarefa;
- considerar o histórico versionado quando ele for necessário para entender a evolução do código;
- atualizar seu entendimento do projeto a partir do repositório antes de depender de memória de conversas anteriores;
- não pedir novamente ao mantenedor conteúdo que possa ser consultado diretamente no repositório acessível.

Essa regra não altera a precedência documental:

- a documentação ativa continua sendo a fonte normativa para decisões, arquitetura, padrões e regras do projeto;
- o repositório no GitHub representa o estado versionado da implementação;
- alterações locais ainda não commitadas ou não enviadas ao remoto não podem ser inferidas a partir do GitHub e devem ser verificadas no ambiente local quando forem relevantes.

O objetivo é permitir que o tutor acompanhe a evolução real do Gambitol pelo código versionado, reduzindo perda de contexto e evitando que o mantenedor precise reapresentar manualmente arquivos que já estejam disponíveis no repositório oficial.

---

# 19. Regra de não invenção

O Gambitol adota uma regra rígida:

> Nada que dependa do estado atual do projeto deve ser presumido quando puder ser verificado.

Antes de sugerir alteração em um arquivo desconhecido, obter seu conteúdo.

Antes de afirmar branch atual, usar Git.

Antes de afirmar que compila, rodar build.

Antes de afirmar que teste passa, rodar teste.

Antes de afirmar versão configurada, ler configuração.

Antes de afirmar requisito atual da Play Store, consultar fonte atualizada.

Antes de afirmar regra oficial do xadrez, consultar a referência de regras quando houver dúvida.

---

# 20. README versus documentação interna

O `README.md` da raiz deve permanecer relativamente enxuto.

Ele deve responder principalmente:

- o que é Gambitol;
- objetivo;
- stack principal;
- estado geral;
- como executar quando isso estiver definido;
- onde está a documentação.

O README não deve virar:

- manual completo de arquitetura;
- catálogo de todas as regras do xadrez;
- log de decisão;
- tutorial de Android;
- changelog de cada feature.

Quando a pasta de documentação estiver integrada ao repositório, o README poderá apontar para:

```text
docs/00_GUIA_MESTRE.md
```

ou para a estrutura que for aprovada posteriormente.

A localização definitiva dos documentos no repositório deve ser decidida antes da integração. Este ZIP é uma fonte documental independente e não pressupõe que a pasta já exista no código.

---

# 21. Estrutura documental não significa estrutura de código

A lista de documentos já está definida.

A estrutura de packages do aplicativo NÃO está.

O fato de existirem documentos sobre:

- arquitetura;
- motor;
- testes;
- UI;

não autoriza criar packages arbitrários com esses mesmos nomes.

A estrutura real do código será definida em `04_ESTRUTURA_DO_PROJETO.md` após análise do projeto Android criado e das necessidades reais.

---

# 22. Uso futuro de diagramas

Diagramas deverão possuir:

- título;
- objetivo;
- escopo;
- legenda quando necessária;
- nível de abstração conhecido;
- correspondência razoável com o código atual.

Preferência inicial:

1. contexto do sistema;
2. visão de alto nível da aplicação;
3. componentes somente onde ajudam.

O C4 recomenda usar apenas os níveis que acrescentam valor. Para um aplicativo mobile isolado, não há mérito em criar dez diagramas simplesmente porque existem dez ferramentas capazes de desenhá-los.

---

# 23. Qualidade documental

Uma documentação do Gambitol é considerada de boa qualidade quando:

- responde claramente ao seu propósito;
- não contradiz decisões vigentes;
- distingue fato de proposta;
- não inventa estado de implementação;
- registra fonte para fatos externos importantes;
- registra data de verificação quando o fato pode envelhecer;
- evita duplicação desnecessária;
- usa termos consistentes;
- possui exemplos compatíveis com o código real;
- é suficientemente detalhada para orientar o projeto;
- continua legível por um humano;
- ajuda o tutor a não perder contexto;
- explica o “por quê” quando isso é relevante;
- não cria burocracia maior que o problema resolvido.

---

# 24. Checklist antes de considerar um documento ATIVO

- [ ] O propósito do documento está explícito.
- [ ] O que ele não cobre está claro quando necessário.
- [ ] O conteúdo corresponde ao estado e às decisões atuais.
- [ ] Propostas estão marcadas como propostas.
- [ ] Decisões estão claramente identificadas.
- [ ] Caminhos de arquivos citados foram verificados.
- [ ] Versões citadas foram verificadas.
- [ ] Requisitos externos têm fonte adequada.
- [ ] Fatos mutáveis possuem data de verificação.
- [ ] Não há regra duplicada conflitante em outro documento.
- [ ] O documento não invade desnecessariamente a responsabilidade de outro.
- [ ] Trade-offs importantes estão registrados.
- [ ] O mantenedor aprovou o documento.

---

# 25. Checklist antes de uma implementação estrutural

Antes de criar uma feature que altere estrutura:

- [ ] Qual é o objetivo?
- [ ] Está dentro do escopo atual?
- [ ] Existe decisão já tomada sobre isso?
- [ ] Quais documentos são relevantes?
- [ ] Conhecemos o estado atual dos arquivos?
- [ ] A arquitetura permite essa dependência?
- [ ] Precisamos pesquisar uma API ou requisito atual?
- [ ] Como vamos testar?
- [ ] A mudança merece uma decisão técnica?
- [ ] Existe um bom ponto de validação antes de continuar?

Esse checklist não precisa aparecer antes de cada método. Ele é para mudanças significativas.

---

# 26. Exemplos de resolução de conflito

## Exemplo A — documentação diz Java 21, build usa outro JDK

Pergunta:

> O documento técnico está errado ou o ambiente está errado?

Ação:

1. verificar configuração real;
2. verificar compatibilidade necessária;
3. consultar decisão vigente;
4. decidir qual lado corrigir;
5. atualizar documentação se necessário.

Não alterar JDK por reflexo.

---

## Exemplo B — regra do motor contradiz a FIDE

Se `05_REGRAS_DO_MOTOR_DE_XADREZ.md` divergir de regra oficial:

1. verificar se a divergência é intencional;
2. se não for, corrigir regra e testes;
3. se for uma adaptação deliberada do jogo, documentá-la explicitamente.

---

## Exemplo C — arquitetura proíbe Android no motor, mas uma classe usa `Context`

Não assumir que “a arquitetura mudou”.

Investigar:

1. por que a dependência entrou;
2. se pode ser removida;
3. se existe necessidade real;
4. se a arquitetura deve mudar.

A decisão vem antes da normalização do desvio.

---

## Exemplo D — requisito da Play Store mudou

Não manter regra antiga apenas porque está documentada.

1. confirmar em fonte oficial;
2. marcar documento como desatualizado se necessário;
3. atualizar;
4. avaliar impacto no build;
5. registrar mudança relevante.

---

# 27. Definição de “fonte de verdade do projeto”

A expressão “fonte de verdade” não significa que qualquer texto dentro do ZIP é eternamente correto.

Significa que existe um processo claro para determinar qual informação é válida.

A fonte de verdade do Gambitol é formada pelo conjunto:

```text
decisões explícitas do mantenedor
+
documentação ativa
+
registro de decisões
+
estado real do repositório
+
resultados de build/teste
+
fontes externas oficiais para fatos controlados por terceiros
```

O `00_GUIA_MESTRE.md` define como essas partes se relacionam.

---

# 28. Relação entre documentação e aprendizado

Como o Gambitol também é projeto educacional, a documentação possui uma segunda função: permitir revisitar o raciocínio.

Por isso, documentos técnicos não devem conter apenas ordens como:

> “Use X.”

Quando a decisão for importante, registrar:

> “Usamos X porque resolve Y, considerando Z.”

Esse padrão ajuda a:

- revisar conhecimento;
- produzir conteúdo;
- responder entrevistas;
- explicar arquitetura;
- defender escolhas técnicas;
- reconhecer quando uma antiga decisão deixou de fazer sentido.

---

# 29. Relação entre documentação e conteúdo público

Nem toda informação interna deve virar conteúdo.

O documento `14_CONTEUDO_E_PORTFOLIO.md` poderá aproveitar:

- erros interessantes;
- decisões arquiteturais;
- testes;
- conceitos Java;
- conceitos Android;
- refatorações;
- antes/depois;
- publicação.

Porém:

- segredos nunca entram em conteúdo;
- chaves, senhas e credenciais nunca devem entrar nos documentos;
- informações privadas do Play Console não devem ser copiadas sem necessidade;
- materiais públicos devem refletir o estado real, não uma feature planejada como se estivesse pronta.

---

# 30. Segurança documental

Nunca registrar em Markdown ou Git:

- senha;
- token;
- chave privada;
- keystore password;
- credencial de Play Console;
- segredo de API;
- arquivo de serviço contendo segredo, salvo quando uma plataforma exigir e houver estratégia segura;
- dados pessoais desnecessários.

Se uma configuração exigir segredo, documentar:

- nome da variável;
- finalidade;
- local seguro esperado;

mas não seu valor.

---

# 31. Gradle Wrapper como padrão de execução

O projeto já contém arquivos do Gradle Wrapper.

A documentação oficial do Gradle recomenda executar builds por meio do Wrapper porque ele usa a versão de Gradle declarada pelo projeto e padroniza o comportamento entre ambientes.

No Git Bash, o padrão será preferencialmente:

```bash
./gradlew <tarefa>
```

em vez de depender de um Gradle instalado globalmente.

Isso será detalhado nos documentos técnicos, mas é registrado aqui como princípio de reprodutibilidade.

Os arquivos do Wrapper fazem parte do projeto e, segundo a documentação oficial do Gradle, devem ser versionados.

---

# 32. Princípios arquiteturais Android que influenciarão as documentações futuras

O guia oficial de arquitetura Android destaca:

- separação de responsabilidades;
- limites claros entre partes do aplicativo;
- evitar concentrar lógica na `Activity`;
- modelar dados e estado fora de componentes de vida curta;
- reduzir dependências desnecessárias do framework Android;
- testabilidade;
- fonte única de verdade;
- camadas bem definidas quando apropriado.

Esses princípios são particularmente compatíveis com a decisão já tomada de manter o motor do Gambitol majoritariamente em Java puro e separado da UI Android.

Isso NÃO significa que a arquitetura final já está definida.

A forma concreta será desenhada em:

- `03_ARQUITETURA_DO_GAMBITOL.md`;
- `04_ESTRUTURA_DO_PROJETO.md`;
- `06_PADROES_JAVA_E_ANDROID.md`.

---

# 33. Política contra overengineering documental

Documentação também pode ser overengineering.

Sinais de excesso:

- mesmo texto copiado em vários arquivos;
- diagramas que ninguém consulta;
- decisões registradas para trivialidades;
- documentos enormes que nunca são atualizados;
- processo tão pesado que impede desenvolvimento;
- regras criadas antes de existir problema;
- estrutura documental criada apenas porque “projetos profissionais têm”.

Regra:

> Documentar o suficiente para preservar contexto, ensinar, orientar e permitir manutenção. Não documentar para encenar complexidade.

---

# 34. Política contra subdocumentação

O extremo oposto também é proibido.

Sinais:

- “depois a gente lembra”;
- arquitetura só existe na cabeça;
- mudança relevante sem motivo registrado;
- regra de xadrez implementada sem especificação;
- feature considerada pronta sem critérios;
- dependência adicionada sem saber por quê;
- Play Store tratada somente na semana de publicar.

O Gambitol precisa deixar rastros das decisões que importam.

---

# 35. Gatilhos obrigatórios de revisão deste Guia Mestre

Revisar este documento quando:

- a estrutura de documentação mudar;
- um documento for adicionado/removido;
- a regra de precedência mostrar falha prática;
- a estratégia de ADR mudar;
- a documentação passar a ser mantida em outro local;
- novas pessoas passarem a contribuir;
- o uso de IA no projeto mudar de forma relevante;
- houver repetição de conflitos que este guia não consiga resolver.

Não revisar apenas porque passou um mês.

---

# 36. Próximos documentos prioritários

Após aprovação deste guia, a sequência planejada permanece:

1. `01_VISAO_E_OBJETIVOS.md`;
2. `02_METODO_DE_ENSINO.md`;
3. `03_ARQUITETURA_DO_GAMBITOL.md`;
4. `04_ESTRUTURA_DO_PROJETO.md`;
5. `05_REGRAS_DO_MOTOR_DE_XADREZ.md`;
6. `06_PADROES_JAVA_E_ANDROID.md`;
7. `07_GIT_WORKFLOW.md`;
8. `08_TESTES_E_QUALIDADE.md`;
9. `09_UI_UX_GAMBITOL.md`;
10. `10_ROADMAP_E_ESCOPO.md`;
11. `11_DECISOES_TECNICAS.md`;
12. documentos de release, monetização, conteúdo e troubleshooting.

A ordem pode ser adaptada quando o desenvolvimento exigir, desde que a alteração seja consciente.

---

# 37. Resumo operacional para o tutor

Antes de orientar:

```text
QUAL É A TAREFA?
        ↓
CONSULTAR 00
        ↓
CONSULTAR DOCUMENTOS DO TEMA
        ↓
HÁ DECISÃO ANTERIOR?
        ↓
PRECISO VER O CÓDIGO REAL?
        ↓
A INFORMAÇÃO EXTERNA PODE TER MUDADO?
        ↓
PESQUISAR FONTE OFICIAL SE NECESSÁRIO
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

Esse é o fluxo central do projeto.

---

# 38. Resumo operacional para o mantenedor

Quando surgir dúvida sobre “onde isso deveria estar?”:

- propósito do produto → 01;
- como ensinar → 02;
- arquitetura → 03;
- onde o código fica → 04;
- como o xadrez funciona → 05;
- como escrever Java/Android → 06;
- como trabalhar com Git → 07;
- como testar → 08;
- como deve parecer/comportar-se → 09;
- o que entra agora/depois → 10;
- por que uma decisão foi tomada → 11;
- como publicar → 12;
- como monetizar → 13;
- como transformar em conteúdo → 14;
- como resolver um problema já investigado → 15.

Se nenhuma categoria servir, avaliar se:

1. a informação realmente precisa ser documentada;
2. pertence a uma seção existente;
3. justifica novo documento.

Não criar novo arquivo como primeira reação.

---

# 39. Fontes pesquisadas para esta versão

As fontes abaixo foram usadas como base conceitual. O texto deste documento é uma síntese aplicada especificamente ao Gambitol.

## Android Developers — arquitetura

**Guide to app architecture**  
https://developer.android.com/topic/architecture

Usado como base para:

- separação de responsabilidades;
- limites de componentes;
- testabilidade;
- single source of truth;
- evitar lógica concentrada em Activity;
- redução de acoplamento a classes Android.

**Recommendations for Android architecture**  
https://developer.android.com/topic/architecture/recommendations

Usado para:

- tratar recomendações como orientações adaptáveis;
- camadas claramente definidas;
- preocupação com testabilidade e manutenção.

---

## GitHub Docs — documentação de repositório

**About the repository README file**  
https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes

**Best practices for repositories**  
https://docs.github.com/en/repositories/creating-and-managing-repositories/best-practices-for-repositories

**Setting guidelines for repository contributors**  
https://docs.github.com/en/communities/setting-up-your-project-for-healthy-contributions/setting-guidelines-for-repository-contributors

Usados para:

- README como porta de entrada;
- documentação navegável;
- expectativas explícitas para quem trabalha no repositório.

---

## AWS Prescriptive Guidance — decisões arquiteturais

**Using architectural decision records to streamline decision-making during development**  
https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/introduction.html

**Architectural decision record process**  
https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/adr-process.html

**Best practices for using architectural decision records**  
https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/best-practices.html

Usados para:

- registrar contexto, decisão e consequências;
- preservar histórico;
- evitar rediscussão recorrente;
- distinguir decisões arquiteturalmente relevantes de escolhas triviais.

---

## Diátaxis

**Diátaxis framework**  
https://diataxis.fr/

**Start here — Diátaxis in five minutes**  
https://diataxis.fr/start-here/

**How to use Diátaxis**  
https://diataxis.fr/how-to-use-diataxis/

Usados para:

- distinguir tutorial, how-to, referência e explicação;
- organizar documentação conforme necessidade do leitor;
- evitar criação artificial de estruturas vazias.

---

## arc42

**arc42 Template Overview**  
https://arc42.org/overview/

**arc42 Documentation — Introduction and Goals**  
https://docs.arc42.org/section-1/

**arc42 Documentation — Constraints**  
https://docs.arc42.org/section-2/

Usados para:

- objetivos;
- requisitos de qualidade;
- restrições;
- contexto;
- decisões;
- visão estrutural da arquitetura.

---

## C4 Model

**C4 model**  
https://c4model.com/

**System context diagram**  
https://c4model.com/diagrams/system-context

**Diagrams**  
https://c4model.com/diagrams

Usados para:

- documentação arquitetural em níveis de zoom;
- começar pela visão geral;
- usar apenas diagramas que tragam valor.

---

## Gradle

**Gradle Wrapper Basics**  
https://docs.gradle.org/current/userguide/gradle_wrapper_basics.html

**Gradle Wrapper**  
https://docs.gradle.org/current/userguide/wrapper_plugin.html

Usados para:

- Wrapper como forma recomendada de executar builds;
- padronização da versão;
- versionamento dos arquivos do Wrapper.

---

## Google Play

**Meet Google Play's target API level requirement**  
https://developer.android.com/google/play/requirements/target-sdk

**App testing requirements for new personal developer accounts**  
https://support.google.com/googleplay/android-developer/answer/14151465

Usados como exemplos de requisitos externos que mudam com o tempo e precisam de data de verificação.

---

## FIDE

**FIDE Laws of Chess**  
https://handbook.fide.com/chapter/E012023

Usado para estabelecer a FIDE como fonte primária para a futura documentação das regras do motor de xadrez.

---

# 40. Conclusão

O Gambitol deve possuir documentação suficiente para que, meses depois, seja possível responder:

- por que usamos Java?
- por que o motor está separado do Android?
- qual regra de xadrez foi adotada?
- qual decisão está vigente?
- qual classe deve ficar em qual camada?
- o que pertence ao MVP?
- qual requisito da Play Store foi verificado?
- por que uma tecnologia foi escolhida?
- qual erro já enfrentamos?
- como o projeto deve ser ensinado?
- como saber se uma informação ainda vale?

Se essas respostas dependerem de memória ou de procurar centenas de mensagens antigas, a documentação falhou.

O objetivo deste sistema é simples:

> **preservar contexto, decisões, aprendizado e direção sem transformar o Gambitol em uma burocracia de Markdown.**

Este documento é o mapa. Os demais documentos serão as fontes especializadas.
