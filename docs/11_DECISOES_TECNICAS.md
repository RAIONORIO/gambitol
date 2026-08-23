# 11 — DECISÕES TÉCNICAS DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `11_DECISOES_TECNICAS.md`  
> **Versão:** 1.1  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-23  
> **Responsabilidade:** preservar as decisões técnicas relevantes do Gambitol, seus motivos, alternativas, consequências, estado e relação com decisões futuras, evitando rediscussões sem evidência nova e impedindo que decisões importantes existam apenas na memória de conversas ou no código  
> **Fonte normativa para:** processo de decisão técnica, critérios para registrar decisões, ciclo de vida de decisões, relação com ADRs, supersession, revisão, decisão versus proposta, evidência, consequências, registro de alternativas e índice das decisões já estabelecidas  
> **Não cobre em detalhe:** arquitetura completa, estrutura física do código, regras de xadrez, padrões Java/Android, Git operacional, testes, UI/UX, roadmap, monetização ou release  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`, `06_PADROES_JAVA_E_ANDROID.md`, `07_GIT_WORKFLOW.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`, `10_ROADMAP_E_ESCOPO.md`  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

Este documento existe para responder, ao longo da vida do Gambitol:

> **por que estamos fazendo assim?**

Código responde muito bem:

> **como está implementado agora?**

Git responde:

> **o que mudou e quando?**

Arquitetura responde:

> **como as partes se relacionam?**

Mas nenhum deles, sozinho, preserva de maneira confiável:

- qual problema motivou uma escolha;
- quais alternativas foram consideradas;
- quais critérios realmente importavam;
- por que uma alternativa foi descartada;
- quais consequências negativas foram aceitas;
- em que contexto a decisão fazia sentido;
- se a decisão ainda está vigente;
- qual decisão substituiu outra;
- qual evidência futura justificaria reconsiderá-la.

É esse vazio que o registro de decisões deve preencher.

---

# 2. Decisão técnica não é opinião congelada

## DECIDIDO

Uma decisão registrada não significa:

> “isso nunca mais pode mudar”.

Significa:

> “esta é a decisão vigente, tomada neste contexto, por estes motivos, até que nova evidência justifique outra decisão”.

Mudanças são permitidas.

Amnésia arquitetural, não.

---

# 3. Por que registrar decisões

A AWS Prescriptive Guidance destaca três anti-patterns recorrentes em decisões arquiteturais:

1. a equipe evita decidir por medo de errar;
2. decide sem registrar a justificativa e depois repete a discussão;
3. decide, mas não registra, então o contexto se perde.

O Gambitol adota o registro de decisões justamente para combater esses três problemas.

---

# 4. Decisão versus implementação

## CONCEITO IMPORTANTE

Exemplo:

```text
DECISÃO:
o motor de xadrez não depende do Android.
```

Implementações possíveis dessa decisão:

```text
módulo Java separado
package separado
interfaces
adapters
```

O registro técnico deve preservar a decisão.

O código preserva uma implementação concreta dela.

---

# 5. Decisão versus requisito

Requisito:

```text
o jogo deve permitir roque.
```

Decisão:

```text
o motor será a fonte única de legalidade do roque.
```

São documentos e níveis diferentes.

---

# 6. Decisão versus preferência

Preferência:

```text
“eu gosto mais de records”
```

não é automaticamente uma decisão técnica relevante.

Para virar decisão precisamos responder:

- qual problema;
- quais critérios;
- qual impacto;
- qual alternativa;
- qual consequência.

---

# 7. Decisão versus proposta

## DECIDIDO

Todo registro deve deixar explícito seu estado.

Não escrever:

```text
“vamos usar X”
```

quando ainda estamos avaliando.

---

# 8. Decisão versus experimento

Um spike pode testar uma hipótese.

Exemplo:

```text
comparar Custom View com 64 Views
```

O spike produz evidência.

A decisão vem depois.

---

# 9. Decisão versus fato externo

Exemplo:

```text
Google Play exige determinado target API na data X.
```

É um fato externo temporal.

A decisão seria:

```text
o Gambitol atualizará targetSdk para cumprir a exigência vigente antes da release.
```

Separar os dois evita registrar política externa mutável como verdade eterna.

---

# 10. O que é ADR

ADR significa:

```text
Architecture Decision Record
```

É um registro pequeno e focado de uma decisão arquitetural relevante.

A estrutura clássica de Michael Nygard contém:

- Title;
- Status;
- Context;
- Decision;
- Consequences.

---

# 11. O que é decision log

A coleção dos registros forma um:

```text
decision log
```

Ele permite enxergar:

- decisões vigentes;
- decisões rejeitadas;
- decisões substituídas;
- evolução da arquitetura.

---

# 12. Por que não registrar tudo como ADR

## DECIDIDO

Não queremos transformar qualquer preferência em documento.

arc42 recomenda registrar decisões arquiteturalmente relevantes, especialmente as que afetam:

- estrutura;
- atributos de qualidade;
- dependências;
- interfaces;
- técnicas de construção;
- risco;
- custo;
- consequências duradouras.

---

# 13. Critério Gambitol: merece registro?

Uma decisão deve ser registrada quando pelo menos uma condição for relevante.

### Estrutural

Muda módulos, camadas, direção de dependência ou fronteiras.

### Difícil de reverter

Trocar depois custa caro.

### Qualidade

Afeta:

- testabilidade;
- performance;
- segurança;
- acessibilidade;
- manutenibilidade;
- compatibilidade.

### Dependência

Introduz biblioteca, framework, serviço ou SDK significativo.

### Interface

Define contrato entre componentes.

### Processo

Muda significativamente como o projeto é construído, testado ou integrado.

### Produto técnico

Fecha uma opção que afeta várias features.

### Risco

A escolha possui risco técnico relevante.

### Debate recorrente

É provável que o mesmo tema seja reaberto no futuro.

---

# 14. O que normalmente NÃO merece ADR próprio

- nome de variável;
- cor temporária;
- pequena extração de método;
- correção óbvia de bug;
- import;
- formatting;
- posição de uma View durante protótipo;
- qualquer decisão facilmente reversível e local.

Essas escolhas podem ficar em:

- código;
- commit;
- documentação especializada.

---

# 15. Exemplo: trocar `ArrayList` por `List`

Normalmente não merece ADR.

---

# 16. Exemplo: usar Canvas para todo o board

Pode merecer ADR porque afeta:

- acessibilidade;
- touch;
- rendering;
- testes;
- arquitetura da UI.

---

# 17. Exemplo: usar Stockfish

Definitivamente merece ADR.

Afeta:

- arquitetura;
- licença;
- NDK/native;
- threads;
- app size;
- produto;
- manutenção.

---

# 18. Exemplo: usar Java

É decisão arquitetural significativa.

Já está estabelecida no projeto.

---

# 19. Exemplo: usar Android Views/XML

É significativa enquanto define tecnologia principal da UI.

---

# 20. Exemplo: usar `StringBuilder` em um formatter

Não.

---

# 21. Princípio de atomicidade

## DECIDIDO

Um registro trata:

> **uma decisão principal.**

Se o texto precisa decidir simultaneamente:

- banco;
- autenticação;
- rede;
- cache;

provavelmente são decisões separadas.

---

# 22. Por que uma decisão por registro

Facilita:

- supersession;
- busca;
- revisão;
- links;
- entendimento.

---

# 23. Decisões em fases diferentes

A Microsoft recomenda separar decisões quando uma mesma direção possui abordagens distintas de curto, médio e longo prazo.

No Gambitol:

```text
V1 local
```

e:

```text
multiplayer online futuro
```

não precisam ser forçados no mesmo registro.

---

# 24. Contexto é obrigatório

## DECIDIDO

Registro sem contexto perde valor.

Contexto deve dizer:

- qual situação;
- qual problema;
- quais restrições;
- quais forças;
- o que já sabemos;
- o que ainda não sabemos.

---

# 25. Contexto não é história do projeto inteiro

Deve ser suficiente para entender aquela decisão.

---

# 26. Problema em forma de pergunta

## RECOMENDADO

Exemplo:

> Como manter as regras de xadrez testáveis sem depender do Android?

Isso força clareza.

---

# 27. Decision drivers

O MADR recomenda explicitar:

```text
Decision Drivers
```

São critérios que realmente influenciam a escolha.

---

# 28. Exemplos de drivers no Gambitol

- aprendizado Java;
- testabilidade;
- minSdk 24;
- compatibilidade Android;
- simplicidade;
- Play Store;
- tempo de build;
- acessibilidade;
- baixo acoplamento;
- manutenção individual;
- possibilidade futura de IA;
- licença de dependência.

---

# 29. Driver não deve ser decorativo

Se “performance” não influencia a escolha:

não colocar só porque parece técnico.

---

# 30. Critérios podem ter prioridades

arc42 recomenda documentar critérios e, quando necessário, importância.

No Gambitol podemos usar:

```text
OBRIGATÓRIO
IMPORTANTE
DESEJÁVEL
```

quando uma decisão realmente precisar comparar opções.

---

# 31. Matriz numérica

## NÃO USAR POR PADRÃO

Uma planilha de pesos pode ajudar em decisão complexa.

Mas não fingir precisão matemática.

Exemplo ruim:

```text
Canvas = 8.34
Grid = 8.29
```

como se arquitetura fosse campeonato de ginástica.

---

# 32. Alternativas consideradas

## DECIDIDO

Toda decisão significativa deve registrar as alternativas relevantes.

---

# 33. Não fabricar alternativas

Não listar três opções absurdas apenas para parecer que houve análise.

---

# 34. Alternativa “não fazer nada”

Pode ser legítima.

Exemplo:

```text
não adicionar biblioteca externa
```

---

# 35. Alternativa “adiar decisão”

Também pode ser legítima.

---

# 36. Decisão reversível

Se não temos informação suficiente:

adiar pode ser melhor que congelar arquitetura prematuramente.

---

# 37. Decisão irreversível ou cara

Exige pesquisa maior.

---

# 38. Consequências positivas

Registrar.

---

# 39. Consequências negativas

## OBRIGATÓRIO

A AWS e arc42 destacam que consequências não devem listar apenas vantagens.

Toda decisão séria possui trade-offs.

---

# 40. Consequência não é desculpa

Exemplo:

```text
Usar Java puro no motor
```

Consequência positiva:

- testes rápidos;
- independência Android.

Consequência negativa possível:

- adapters extras na integração;
- recursos Android não podem ser usados diretamente.

Isso torna a decisão mais honesta.

---

# 41. Consequências neutras

MADR também admite aspectos neutros.

Útil quando algo muda, mas não é claramente bom ou ruim.

---

# 42. Custo de oportunidade

Uma escolha elimina outras.

Registrar quando importante.

---

# 43. Evidence / evidência

## PROPOSTO COMO CAMPO DO GAMBITOL

Além do formato clássico, cada decisão pode registrar a evidência usada:

- documentação oficial;
- benchmark;
- spike;
- teste;
- protótipo;
- requisito;
- política externa.

---

# 44. Por que evidência importa

Permite distinguir:

```text
decisão por necessidade
```

de:

```text
decisão por gosto.
```

---

# 45. Confidence

A orientação atual da Microsoft recomenda registrar nível de confiança quando útil.

## PROPOSTO

Podemos usar:

```text
ALTA
MÉDIA
BAIXA
```

somente quando acrescentar informação.

---

# 46. Confidence baixa não invalida decisão

Significa:

> decidimos com informação limitada.

Pode indicar necessidade de revisão posterior.

---

# 47. Revisit trigger

## PROPOSTO

Campo opcional:

```text
Revisar se...
```

Exemplo:

```text
Revisar a decisão de não usar DI framework se o grafo de dependências ficar difícil de montar/testar manualmente.
```

---

# 48. Revisit trigger é melhor que data arbitrária

Não precisamos revisar mensalmente uma decisão estável.

Revisamos quando o contexto muda.

---

# 49. Status

## PROPOSTO COMO CONJUNTO PADRÃO

```text
PROPOSTA
ACEITA
REJEITADA
SUBSTITUÍDA
DEPRECADA
EM_REVISÃO
```

---

# 50. PROPOSTA

Alternativa selecionada ainda não aprovada definitivamente.

---

# 51. ACEITA

Decisão vigente.

---

# 52. REJEITADA

Foi considerada e deliberadamente não adotada.

---

# 53. SUBSTITUÍDA

Uma decisão posterior tomou seu lugar.

Deve apontar para a nova.

---

# 54. DEPRECADA

Ainda pode existir no sistema, mas não é direção desejada para novo código.

---

# 55. EM_REVISÃO

Contexto mudou e a decisão está sendo reconsiderada.

---

# 56. “CANCELADA”

## NÃO NECESSÁRIA POR PADRÃO

Pode ser representada como rejeitada/obsoleta conforme contexto.

---

# 57. Imutabilidade dos registros aceitos

## DECIDIDO COMO PRINCÍPIO

Depois de aceita, não reescrever silenciosamente a justificativa histórica.

AWS, Microsoft e arc42 convergem nessa recomendação.

---

# 58. Como corrigir erro factual em ADR aceito

Duas situações:

### Erro editorial

Pode corrigir typo sem mudar significado, registrando alteração se relevante.

### Mudança de decisão/contexto

Criar novo registro.

---

# 59. Supersession

Fluxo:

```text
DECISÃO A — ACEITA
↓
novo contexto
↓
DECISÃO B — PROPOSTA
↓
B aceita
↓
A = SUBSTITUÍDA POR B
```

---

# 60. Nunca apagar decisão substituída

Ela explica:

- código antigo;
- commits;
- histórico;
- evolução.

---

# 61. Decisão rejeitada também fica

A AWS recomenda manter razão da rejeição para impedir a mesma discussão sem informação nova.

---

# 62. Rediscutir uma rejeitada

Pode acontecer se:

- requisitos mudaram;
- biblioteca mudou;
- custo mudou;
- plataforma mudou;
- nova evidência apareceu.

---

# 63. Regra para reabrir tema

## DECIDIDO

Não reabrir apenas porque:

> “agora eu prefiro outra coisa”.

Mostrar mudança de contexto/evidência.

---

# 64. Exemplo

Antes:

```text
não usar Hilt porque grafo é pequeno.
```

Depois:

```text
20 ViewModels + 6 repositories + factories complexas.
```

Agora existe evidência nova.

---

# 65. Registro perto do código

AWS recomenda manter ADRs em local central acessível, frequentemente no próprio repositório, onde são versionados.

O Gambitol seguirá esse princípio.

---

# 66. Local físico exato

## PENDENTE

Não criar pasta de ADR neste documento.

O caminho deverá ser aprovado antes de arquivos separados existirem.

Opções futuras:

```text
docs/decisions/
docs/adr/
gambitol-docs/decisions/
```

Nenhuma está decidida aqui.

---

# 67. Por que não escolher caminho agora

O projeto já possui uma estrutura documental definida.

A localização precisa combinar com `04_ESTRUTURA_DO_PROJETO.md`.

Não vamos inventar uma segunda árvore paralela só porque uma fonte externa usa `docs/adr`.

---

# 68. Arquivos separados versus documento central

## PROPOSTO

`11_DECISOES_TECNICAS.md` funciona como:

- política;
- índice;
- registro inicial.

Decisões mais significativas podem futuramente ganhar ADRs individuais.

---

# 69. Decisão pequena mas relevante

Pode ficar registrada diretamente neste documento.

---

# 70. Decisão grande

Pode receber ADR individual.

---

# 71. Quando promover para ADR individual

Se possuir:

- várias alternativas;
- trade-offs profundos;
- impacto arquitetural;
- supersession provável;
- evidência extensa.

---

# 72. Exemplo forte

Escolher renderer do board:

```text
64 Views
vs
Custom View
vs
outra abordagem
```

merece ADR próprio.

---

# 73. Exemplo forte

Escolher engine de IA.

---

# 74. Exemplo forte

Escolher persistência.

---

# 75. Exemplo forte

Escolher estratégia multiplayer.

---

# 76. Exemplo moderado

Adotar View Binding.

Pode caber como registro pequeno dependendo do impacto.

---

# 77. Exemplo pequeno

Trocar nome de recurso.

Não.

---

# 78. Identificador de ADR

## PENDENTE

MADR usa frequentemente:

```text
NNNN-title-with-dashes.md
```

Outros projetos usam datas ou nomes.

O Gambitol ainda não escolheu o formato.

---

# 79. Por que identificador importa

Ajuda:

- links;
- supersession;
- busca;
- referência em PR/commit.

---

# 80. Numeração sequencial

Vantagens:

- simples;
- estável;
- fácil de citar.

Desvantagem:

- exige reservar próximo número.

---

# 81. Data no nome

Vantagens:

- contexto temporal.

Desvantagens:

- nome maior;
- múltiplas decisões no mesmo dia;
- ID menos compacto.

---

# 82. Título como ID

Vantagem:

- legível.

Desvantagem:

- rename pode quebrar links.

---

# 83. Decisão futura do naming

Será tomada quando o primeiro ADR individual for realmente necessário.

---

# 84. Template mínimo recomendado

## PROPOSTO

```markdown
# <título>

Status:
Data:
Responsável:

## Contexto e problema

## Drivers da decisão

## Alternativas consideradas

## Decisão

## Consequências

## Evidências

## Como confirmar conformidade

## Quando revisar

## Decisões relacionadas
```

---

# 85. Por que esse template

Combina:

- Nygard;
- AWS;
- MADR;
- Microsoft;
- necessidades do Gambitol.

---

# 86. Não preencher seção sem utilidade

Um template é guia.

Não obrigação burocrática.

---

# 87. Template mínimo ultraleve

Para decisão menor:

```markdown
Contexto:
Decisão:
Consequências:
Status:
```

---

# 88. Confirmation

MADR moderno sugere um campo de confirmação:

> como provar que o sistema realmente está seguindo a decisão?

Isso é especialmente útil no Gambitol.

---

# 89. Exemplos de confirmation

Decisão:

```text
engine não depende de Android
```

Confirmação:

```text
módulo Java puro compila sem Android SDK
```

---

# 90. Outro exemplo

Decisão:

```text
UI não valida xadrez
```

Confirmação:

- code review;
- imports/dependencies;
- testes;
- ausência de duplicate move logic.

---

# 91. Fitness functions

## FUTURO

Algumas decisões podem ser transformadas em verificações automáticas.

Exemplo:

```text
engine não importa android.*
```

O próprio módulo Java já pode agir como guardrail.

---

# 92. Nem toda decisão é automatizável

Exemplo:

```text
manter UX visual premium e contida
```

precisa revisão humana.

---

# 93. Decisão e code review

AWS recomenda usar ADRs como referência durante review.

No Gambitol:

se um diff viola decisão aceita:

1. corrigir código;
2. ou propor supersession.

Não ignorar silenciosamente.

---

# 94. Decisão e Git

O documento 07 registra evolução.

ADR pode ser alterado/proposto na mesma branch da mudança que exige a decisão.

---

# 95. Decisão antes do código

## PREFERÊNCIA

Para decisão de alto impacto:

registrar/propor antes de implementação final.

---

# 96. Spike antes do ADR aceito

Permitido.

---

# 97. ADR depois do código

Pode ser necessário em decisão já tomada informalmente.

Mas deve ser exceção, não padrão.

---

# 98. Decisões retrospectivas

A Microsoft recomenda registrar retrospectivamente decisões de sistemas existentes quando o contexto é conhecido.

O Gambitol já possui decisões estabelecidas antes deste documento.

Elas serão registradas abaixo.

---

# 99. Não inventar justificativa retrospectiva

## REGRA

Se o motivo histórico não é conhecido:

não preencher com ficção.

Registrar:

```text
motivo original não documentado
```

e, se necessário, a justificativa atual separadamente.

---

# 100. Registro inicial — Java como linguagem principal

## STATUS

ACEITA.

## Decisão

O Gambitol usa Java como linguagem principal do código Android e do motor de xadrez.

## Contexto

O projeto existe também como exercício aprofundado de Java aplicado a um produto Android real.

## Consequências positivas

- aprendizado explícito de Java;
- OOP/collections/exceptions/testes no projeto real;
- alinhamento com objetivo educacional;
- engine pode ser Java puro.

## Consequências negativas

- exemplos Android modernos frequentemente são publicados primeiro em Kotlin;
- algumas APIs/documentações usam Kotlin como linguagem principal;
- certas bibliotecas modernas podem oferecer ergonomia melhor em Kotlin.

## Não significa

- proibir Kotlin DSL no Gradle;
- impossibilitar adoção pontual futura se houver decisão específica.

---

# 101. Registro inicial — Android como plataforma inicial

## STATUS

ACEITA.

## Decisão

A primeira plataforma do Gambitol é Android mobile.

## Consequências

- UI usa APIs/ecossistema Android;
- publicação alvo é Google Play;
- minSdk/targetSdk/importante;
- compatibilidade Android entra nos quality gates.

## Fora da decisão

- iOS;
- web;
- desktop.

Nenhum deles está comprometido.

---

# 102. Registro inicial — package/applicationId

## STATUS

ACEITA.

## Decisão

O identificador definido para o app é:

```text
br.com.raionorio.gambitol
```

## Consequência

Esse identificador será tratado com cautela porque applicationId publicado possui implicações de distribuição e identidade do app.

---

# 103. Registro inicial — minSdk inicial

## STATUS

ACEITA NO ESTADO ATUAL / REVISÁVEL.

## Decisão

O projeto foi criado com:

```text
minSdk API 24
```

## Consequências

- testes de compatibilidade precisam incluir API 24;
- APIs Java/Android precisam ser avaliadas contra esse piso;
- desugaring pode ser relevante;
- subir minSdk futuramente exige decisão explícita.

---

# 104. Registro inicial — Kotlin DSL no build

## STATUS

ACEITA COMO CONFIGURAÇÃO ATUAL.

## Decisão

Os scripts Gradle do projeto usam:

```text
Kotlin DSL
build.gradle.kts
```

## Contexto

Essa escolha veio da criação do projeto Android Studio.

## Consequência

O build usa Kotlin DSL mesmo que o app seja Java.

## Não significa

Migrar código principal para Kotlin.

---

# 105. Registro inicial — motor independente do Android

## STATUS

ACEITA.

## Decisão

O motor de xadrez deve ser independente da interface e do framework Android.

## Drivers

- testabilidade;
- regras determinísticas;
- baixo acoplamento;
- portabilidade conceitual;
- IA futura;
- manutenção.

## Consequências positivas

- testes JVM rápidos;
- engine não conhece Activity/View/Context;
- regras podem evoluir separadamente.

## Consequências negativas

- integração exige adapters/mapeamento;
- resources Android não podem aparecer no motor;
- alguns tipos de UI precisam ser convertidos para tipos de domínio.

---

# 106. Registro inicial — UI não é fonte das regras

## STATUS

ACEITA.

## Decisão

A camada Android não decide legalidade de movimentos.

## Consequência

Lista de destinos legais, game state e resultados devem derivar do motor.

---

# 107. Registro inicial — primeira modalidade local

## STATUS

ACEITA.

## Decisão

A primeira versão joga:

```text
dois jogadores no mesmo dispositivo
```

## Consequências

- nenhum backend é necessário;
- nenhum login é necessário;
- multiplayer online não bloqueia V1;
- UX precisa considerar dois jogadores locais.

---

# 108. Registro inicial — IA fora da V1

## STATUS

ACEITA.

## Decisão

IA não faz parte do núcleo da primeira versão.

## Motivo

A engine precisa estar correta antes de qualquer algoritmo de escolha de jogada.

---

# 109. Registro inicial — multiplayer online fora da V1

## STATUS

ACEITA.

## Decisão

Multiplayer online não faz parte da primeira versão.

## Consequências

- sem rede obrigatória;
- sem matchmaking;
- sem reconexão;
- sem backend;
- menor superfície de segurança/privacy.

---

# 110. Registro inicial — monetização não define o core

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

O produto deve possuir experiência jogável e confiável antes de introduzir monetização.

Detalhes ficam no documento 13.

---

# 111. Registro inicial — Git Bash como ferramenta principal de Git

## STATUS

ACEITA.

## Decisão

Operações Git serão ensinadas e executadas preferencialmente no Git Bash quando tecnicamente adequado.

## Consequência

Android Studio continua sendo usado quando sua interface é superior para:

- SDK;
- emulator;
- Logcat;
- tooling;
- layout.

---

# 112. Registro inicial — commit não é automático

## STATUS

ACEITA.

## Decisão

Implementar/testar/revisar e obter aprovação antes de commit.

---

# 113. Registro inicial — documentação antes de coding estrutural

## STATUS

ACEITA PARA A FUNDAÇÃO DO PROJETO.

## Decisão

A base documental 00–15 é concluída antes de novas decisões estruturais relevantes.

## Consequência

O desenvolvimento retoma depois com regras e roadmap conhecidos.

---

# 114. Registro inicial — Views/XML

## STATUS

ACEITA COMO TECNOLOGIA ATUAL.

## Decisão

A UI Android atual é baseada em Views/XML, não Compose.

## Consequências

- Activity/ViewModel/Views/XML;
- Espresso/Views quando UI tests forem usados;
- resources XML;
- renderer do board ainda pendente.

## Não significa

“Compose jamais poderá existir”.

Mudança futura exigiria decisão própria.

---

# 115. Registro inicial — Gradle Wrapper

## STATUS

ACEITA.

## Decisão

Builds devem usar o Gradle Wrapper do projeto.

---

# 116. Registro inicial — testes como parte da implementação

## STATUS

ACEITA.

## Decisão

Testes não são etapa final.

A engine cresce junto com testes.

---

# 117. Registro inicial — Perft

## STATUS

ACEITA COMO ESTRATÉGIA DE VALIDAÇÃO FUTURA DO MOTOR.

## Decisão

Perft será usado para validar geração de movimentos quando o motor atingir maturidade suficiente.

---

# 118. Registro inicial — mockup como referência, não contrato

## STATUS

ACEITA.

## Decisão

O conceito visual aprovado guia:

- identidade;
- hierarquia;
- atmosfera.

Não obriga pixel-perfect nem transforma elementos futuros em features V1.

---

# 119. Registro inicial — board como elemento dominante

## STATUS

ACEITA.

## Decisão

A tela de partida prioriza o tabuleiro sobre branding e controles secundários.

---

# 120. Registro inicial — responsividade por espaço disponível

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

Não projetar apenas para um modelo físico de aparelho.

A UI responde à janela/espaço disponível.

---

# 121. Registro inicial — sem dependência externa por antecipação

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

Bibliotecas/frameworks entram quando resolvem um problema real e após avaliação.

---

# 122. Registro inicial — sem DI framework inicialmente

## STATUS

ACEITA COMO DIREÇÃO INICIAL / REVISÁVEL.

## Decisão

Não adicionar Hilt ou framework de DI antes de o grafo de dependências justificar.

## Trigger de revisão

Revisar se a composição manual se tornar:

- repetitiva;
- difícil de testar;
- difícil de entender;
- propensa a erros.

---

# 123. Registro inicial — sem repository sem fonte de dados

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

Não criar repository apenas por template arquitetural.

Introduzir quando existir fonte de dados/contrato que o justifique.

---

# 124. Registro inicial — sem use-case layer por dogma

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

Uma camada adicional só entra se houver lógica/orquestração que justifique.

---

# 125. Registro inicial — engine Java puro como guardrail

## STATUS

ACEITA E CONFIRMADA.

## Confirmação

Em 2026-08-23, a fronteira foi implementada com o módulo Java puro:

```text
:chess-engine
```

O módulo compila e executa seus testes JVM sem dependências `android.*` ou `androidx.*`.

A integração com o aplicativo segue a direção:

```text
:app -> :chess-engine
```

A validação pós-merge na `main` foi concluída com sucesso usando:

```text
./gradlew :chess-engine:test :app:assembleDebug
```

---

# 126. Registro inicial — nomes importantes não são presumidos

## STATUS

ACEITA COMO GOVERNANÇA.

## Decisão

Nomes estruturais importantes não definidos devem ser apresentados como opções antes de virar comandos, classes, packages, módulos ou arquivos.

## Consequência

Este documento não cria unilateralmente:

- nome do módulo engine;
- package do engine;
- nomes de classes futuras;
- nome de pasta ADR.

---

# 127. Registro inicial — sequência por gates

## STATUS

ACEITA.

## Decisão

O roadmap progride por capacidade verificada, não por calendário arbitrário.

---

# 128. Registro inicial — primeiro passo técnico após docs

## STATUS

ACEITA.

## Decisão

Antes de estruturar a engine:

- verificar Git;
- ler Gradle;
- confirmar JDK;
- executar build/testes.

---

# 129. Registro inicial — facts temporais não são congelados

## STATUS

ACEITA.

## Decisão

Requisitos externos, especialmente Google Play, precisam ser reverificados perto do uso.

---

# 130. Registro inicial — V1 sem backend

## STATUS

ACEITA.

## Decisão

O core local não introduz backend sem feature que o exija.

---

# 131. Registro inicial — V1 sem login

## STATUS

ACEITA.

---

# 132. Registro inicial — V1 sem analytics obrigatório

## STATUS

ACEITA COMO DIREÇÃO.

## Decisão

Não coletar dados apenas porque um SDK existe.

Analytics futuro exige pergunta de produto e revisão de privacidade.

---

# 133. Registro inicial — sem permissões sensíveis para o core

## STATUS

ACEITA COMO PRINCÍPIO.

O jogo local não possui justificativa atual para:

- câmera;
- microfone;
- localização;
- contatos.

---

# 134. Registro inicial — targetSdk é temporal

## STATUS

ACEITA.

## Decisão

Target API será atualizado conforme requisitos e compatibilidade atuais na fase de release.

---

# 135. Registro inicial — código técnico em inglês

## STATUS

ACEITA/PROPOSTA DOCUMENTAL PRÉVIA

O documento 06 propôs/estabeleceu código técnico em inglês.

Antes da implementação massiva, confirmar consistência se houver qualquer divergência.

---

# 136. Registro inicial — documentação pedagógica em português

## STATUS

ACEITA.

Ajuda ensino e manutenção do projeto pelo desenvolvedor.

---

# 137. Registro inicial — UI pode ser português

## STATUS

ACEITA COMO DIREÇÃO INICIAL.

Localização futura permanece possível.

---

# 138. Registro inicial — arquitetura não será overengineered

## STATUS

ACEITA COMO PRINCÍPIO.

## Decisão

Preferir estrutura mínima que preserve:

- separação;
- teste;
- evolução.

---

# 139. Registro inicial — performance baseada em medição

## STATUS

ACEITA.

## Decisão

Não otimizar engine/UI por hipótese quando ainda não há problema medido.

---

# 140. Registro inicial — correctness do xadrez prioriza otimização

## STATUS

ACEITA.

---

# 141. Registro inicial — engine não captura rei

## STATUS

ACEITA COMO REGRA DE DOMÍNIO.

Esse comportamento vem do documento 05/FIDE.

A UI termina a partida em mate; não representa “captura do rei”.

---

# 142. Registro inicial — claimable draw separado de automatic draw

## STATUS

ACEITA.

A engine/UI não devem fundir:

- threefold/50 claimable;
- fivefold/75 automático.

---

# 143. Registro inicial — renderer do board

## STATUS

PENDENTE.

Alternativas futuras incluem:

- 64 Views;
- Custom View/Canvas;
- outra solução justificada.

A acessibilidade é driver obrigatório.

---

# 144. Registro inicial — representação interna do board

## STATUS

PENDENTE.

Possibilidades:

- array 8×8;
- array 64;
- outra estrutura.

Não decidir aqui.

---

# 145. Registro inicial — estratégia make/unmake

## STATUS

PENDENTE.

Comparar com cópia/snapshot quando a engine chegar nesse ponto.

---

# 146. Registro inicial — records Java

## STATUS

PENDENTE.

Depende de:

- sourceCompatibility;
- AGP;
- Android/desugaring;
- objetivo didático.

---

# 147. Registro inicial — View Binding

## STATUS

PENDENTE.

---

# 148. Registro inicial — LiveData/observable state

## STATUS

PENDENTE.

A tecnologia concreta para Java/Views ainda será decidida.

---

# 149. Registro inicial — persistence

## STATUS

PENDENTE / PÓS-V1.

---

# 150. Registro inicial — timer

## STATUS

PENDENTE / PÓS-V1.

---

# 151. Registro inicial — history

## STATUS

PENDENTE / PÓS-V1.

---

# 152. Registro inicial — undo

## STATUS

PENDENTE.

---

# 153. Registro inicial — sound/haptics

## STATUS

PENDENTE.

---

# 154. Registro inicial — auto flip do board

## STATUS

PENDENTE.

---

# 155. Registro inicial — light theme

## STATUS

PENDENTE.

A identidade principal é dark.

---

# 156. Registro inicial — monetization model

## STATUS

PENDENTE.

Documento 13 decidirá princípios e alternativas.

---

# 157. Registro inicial — licença do código

## STATUS

PENDENTE.

Se repositório for público, isso precisa ser decidido conscientemente.

---

# 158. Registro inicial — CI provider

## STATUS

PENDENTE.

GitHub Actions é candidato natural se GitHub for o remoto, mas nenhuma ferramenta está aprovada apenas por conveniência.

---

# 159. Registro inicial — commit signing

## STATUS

PENDENTE/FUTURO.

---

# 160. Registro inicial — formato de ADR individual

## STATUS

PENDENTE.

Este documento propõe um template, mas não cria ainda arquivos individuais.

---

# 161. Registro inicial — naming de ADR

## STATUS

PENDENTE.

---

# 162. Registro inicial — local da pasta ADR

## STATUS

PENDENTE.

---

# 163. Tabela resumida de decisões vigentes

| Tema | Estado |
|---|---|
| Plataforma Android | ACEITA |
| Java como linguagem principal | ACEITA |
| `br.com.raionorio.gambitol` | ACEITA |
| minSdk 24 atual | ACEITA / REVISÁVEL |
| Kotlin DSL no Gradle | ACEITA COMO CONFIGURAÇÃO |
| Motor independente do Android | ACEITA |
| UI não valida xadrez | ACEITA |
| Dois jogadores locais primeiro | ACEITA |
| IA fora da V1 | ACEITA |
| Multiplayer online fora da V1 | ACEITA |
| Sem backend no core local | ACEITA |
| Sem login no core local | ACEITA |
| Android Views/XML | ACEITA COMO TECNOLOGIA ATUAL |
| Gradle Wrapper | ACEITA |
| Testes durante implementação | ACEITA |
| Perft para hardening | ACEITA |
| Mockup não é contrato de pixel | ACEITA |
| Board dominante | ACEITA |
| Git Bash como ferramenta Git principal | ACEITA |
| Commit após aprovação | ACEITA |
| Sem dependência externa sem necessidade | ACEITA |
| Sem DI framework inicialmente | ACEITA / REVISÁVEL |
| Sem repository/use-case por dogma | ACEITA |
| Target API reverificado na release | ACEITA |
| Módulo Java puro `:chess-engine` | ACEITA / IMPLEMENTADA |
| Dependência `:app -> :chess-engine` | ACEITA / IMPLEMENTADA |
| Package base `br.com.raionorio.gambitol.engine` | ACEITA / IMPLEMENTADA |
| Gradle executado com JDK 21 | ACEITA NO ESTADO ATUAL / REVISÁVEL |
| Java source/target 17 | ACEITA NO ESTADO ATUAL / REVISÁVEL |
| JUnit Jupiter 6.1.3 no engine | ACEITA / IMPLEMENTADA |
| Renderer do board | PENDENTE |
| Board representation | PENDENTE |
| View Binding | PENDENTE |
| Observable state | PENDENTE |
| Persistence | PENDENTE |
| IA técnica | FUTURO |
| Multiplayer técnico | FUTURO |
| Monetização | PENDENTE |
| ADR naming/path | PENDENTE |

---

# 164. Esta tabela não substitui contexto

Ela serve para consulta rápida.

Decisões grandes precisam de contexto/consequências.

---

# 165. Como lidar com contradição entre documentos

Seguir `00_GUIA_MESTRE.md`.

Em resumo:

1. decisão explícita mais atual;
2. governança;
3. decisão técnica aceita;
4. documento especializado;
5. implementação real como fato descritivo;
6. fontes externas;
7. sugestões.

---

# 166. Decisão aceita pode prevalecer sobre documento especializado antigo

Sim.

Mas o documento especializado deve ser atualizado.

---

# 167. Código pode violar decisão

Isso não muda a decisão automaticamente.

Significa:

- bug;
- dívida;
- implementação divergente;
- ou decisão desatualizada.

Investigar.

---

# 168. “O código já faz assim” não é justificativa suficiente

Brownfield pode conter acidente histórico.

---

# 169. Implementação divergente deliberada

Se precisamos mudar:

propor nova decisão.

---

# 170. Decisão e documentação especializada

Quando uma ADR altera arquitetura:

atualizar `03_ARQUITETURA_DO_GAMBITOL.md`.

---

# 171. Decisão e estrutura

Se muda packages/módulos:

atualizar `04_ESTRUTURA_DO_PROJETO.md`.

---

# 172. Decisão e padrões

Se muda Java/Android conventions:

atualizar `06_PADROES_JAVA_E_ANDROID.md`.

---

# 173. Decisão e Git

Se muda workflow:

atualizar `07_GIT_WORKFLOW.md`.

---

# 174. Decisão e testes

Se muda quality gate:

atualizar `08_TESTES_E_QUALIDADE.md`.

---

# 175. Decisão e UI

Se muda renderer/UX fundamental:

atualizar `09_UI_UX_GAMBITOL.md`.

---

# 176. Decisão e roadmap

Se muda escopo/ordem:

atualizar `10_ROADMAP_E_ESCOPO.md`.

---

# 177. Cross-linking

## RECOMENDADO

Decisões relacionadas devem apontar umas para as outras.

Exemplo:

```text
renderer do board
```

relaciona-se a:

- acessibilidade;
- responsividade;
- UI tests.

---

# 178. Não duplicar justificativa inteira

Referenciar documento especializado.

---

# 179. ADR deve permanecer compreensível sozinho

Mesmo com links, a decisão deve estar clara sem abrir dez arquivos.

Microsoft recomenda registros concisos, assertivos e autocontidos.

---

# 180. ADR não é design guide

## DECIDIDO

Não colocar:

- implementação linha a linha;
- tutorial;
- código completo;
- todos os detalhes de API.

Esses ficam em documentos/code.

---

# 181. ADR não é post-mortem

Pode referenciar incidente, mas seu foco é a decisão.

---

# 182. ADR não é task

“Implementar X” é trabalho.

“Adotar X porque...” é decisão.

---

# 183. ADR não é changelog

Git/release notes fazem isso.

---

# 184. ADR não é benchmark report

Pode linkar benchmark.

---

# 185. ADR não é reunião transcrita

Resumir os argumentos relevantes.

---

# 186. ADR não é tese

Michael Nygard defendia registros pequenos e modulares para que sejam realmente mantidos e lidos.

---

# 187. Comprimento

## REGRA

Tão curto quanto possível, tão completo quanto necessário.

Não haverá limite artificial de linhas.

---

# 188. Um ADR de 30 linhas pode ser excelente

---

# 189. Um ADR de 300 linhas pode ser necessário

Mas provavelmente precisa links/anexos ou divisão.

---

# 190. Estilo de decisão

AWS recomenda linguagem assertiva para a decisão.

Preferir:

```text
Usamos...
O Gambitol mantém...
O motor não depende...
```

Evitar:

```text
Talvez seja interessante considerar...
```

em decisão ACEITA.

---

# 191. Proposta pode ser condicional

Sim.

---

# 192. Data

## DECIDIDO

Toda decisão relevante deve possuir data.

Contexto técnico muda.

---

# 193. Responsável

## PROPOSTO

Registrar quem tomou/aprovou a decisão.

No projeto individual pode parecer óbvio, mas ainda é metadado útil.

---

# 194. Consulted/informed

MADR admite metadados de pessoas consultadas/informadas.

## NÃO NECESSÁRIO AGORA

Pode entrar se o projeto ganhar equipe.

---

# 195. Stakeholders

Podem ser técnicos ou produto.

---

# 196. Fonte externa

Linkar quando a decisão depende de:

- Android;
- FIDE;
- Play;
- Java;
- biblioteca.

---

# 197. Versão da fonte

Se relevante, registrar.

---

# 198. Data de verificação

Para requisito temporal, registrar.

---

# 199. Dependência de versão

Exemplo:

```text
AGP X exige JDK Y
```

não deve virar ADR permanente sem contexto de versão.

---

# 200. Decisão de upgrade

Pode merecer ADR se causar mudança arquitetural.

Upgrade rotineiro patch não.

---

# 201. Biblioteca nova

Perguntar se é architecturally significant.

---

# 202. Biblioteca de teste pequena

Talvez não.

---

# 203. Framework de DI

Sim.

---

# 204. Banco local

Sim.

---

# 205. SDK de analytics

Sim, pois afeta privacy/build/data.

---

# 206. SDK de ads

Sim.

---

# 207. Crash reporting

Pode merecer.

---

# 208. Google Play Games Services

Sim.

---

# 209. Material Components

Talvez, dependendo do nível de dependência.

---

# 210. View Binding

Talvez registro menor.

---

# 211. Custom View

Sim.

---

# 212. FEN

Provavelmente decisão de formato/feature, mas pode ficar no domínio se simples.

---

# 213. PGN

Sem necessidade de ADR se seguir padrão e não houver alternativa relevante.

---

# 214. Stockfish

Sim.

---

# 215. AI própria

Sim.

---

# 216. Multiplayer protocol

Sim.

---

# 217. Server authority model

Sim.

---

# 218. Offline-first persistence

Sim.

---

# 219. Cloud sync

Sim.

---

# 220. Encryption strategy

Sim se existir dado sensível.

---

# 221. App theme color

Não.

---

# 222. Board palette

Normalmente `09_UI_UX`, não ADR.

---

# 223. Auto-flip behavior

Pode ser decisão de produto/UX, provavelmente fica em doc09, salvo grande impacto arquitetural.

---

# 224. Source of truth da partida

Sim.

Já definida como motor/estado de domínio.

---

# 225. State restoration strategy

Pode merecer ADR quando persistence/lifecycle amadurecer.

---

# 226. Processo de decisão

## PROPOSTO

```text
PROBLEMA
↓
PRECISA DECIDIR AGORA?
↓
DRIVERS
↓
ALTERNATIVAS
↓
EVIDÊNCIA
↓
PROPOSTA
↓
REVISÃO
↓
ACEITAR / REJEITAR / ADIAR
↓
IMPLEMENTAR
↓
CONFIRMAR CONFORMIDADE
```

---

# 227. Passo 1 — problema

Escrever sem solução embutida.

Ruim:

> Qual biblioteca de DI Hilt devemos usar?

Bom:

> A composição manual de dependências tornou-se difícil de manter; precisamos de um mecanismo adicional?

---

# 228. Passo 2 — decidir agora?

## OBRIGATÓRIO

Perguntar:

> existe custo real em adiar?

Se não:

adiar.

---

# 229. Passo 3 — drivers

Listar o que realmente importa.

---

# 230. Passo 4 — alternativas

Incluir “manter estado atual” quando pertinente.

---

# 231. Passo 5 — pesquisa

Prioridade:

- fonte oficial;
- documentação técnica;
- protótipo;
- benchmark;
- comunidade como complemento.

---

# 232. Passo 6 — proposta

Uma opção escolhida provisoriamente.

---

# 233. Passo 7 — revisão

No projeto solo:

revisão ainda significa:

- reler;
- confrontar drivers;
- verificar consequências;
- evitar impulso.

---

# 234. Passo 8 — aceite

Mudar status.

---

# 235. Passo 9 — implementação

Código segue decisão.

---

# 236. Passo 10 — confirmação

Build/test/review comprovam.

---

# 237. Decisão sem implementação

Pode existir por algum tempo.

Mas não declarar conformidade até código existir.

---

# 238. Implementação sem decisão registrada

Se importante:

documentar assim que identificado.

---

# 239. Decisão pendente bloqueante

O roadmap deve parar antes da ação dependente.

---

# 240. Exemplo real futuro

Antes de criar módulo do motor:

precisamos decidir nome do módulo/package.

Isso não necessariamente exige ADR grande, mas exige escolha explícita.

---

# 241. Exemplo futuro: renderer

Antes da Fase 9:

comparar opções.

Pode exigir spike.

---

# 242. Exemplo futuro: persistence

Antes de implementar save:

definir requisitos.

Só então comparar soluções.

---

# 243. Anti-pattern — technology first

Ruim:

> Quero usar Room. Onde encaixamos?

Bom:

> Precisamos persistir quais dados, com quais consultas, migrações e lifecycle?

---

# 244. Anti-pattern — resume-driven development

Não adicionar tecnologia só para aparecer no portfólio.

---

# 245. Anti-pattern — architecture by trend

“Todo mundo usa” não é driver suficiente.

---

# 246. Anti-pattern — single option

Se não existe alternativa real, talvez não precise ADR.

Ou registrar que era constraint.

---

# 247. Anti-pattern — consequences só positivas

Proibido.

---

# 248. Anti-pattern — status eterno PROPOSTA

Decisão precisa fechamento quando chegar a hora.

---

# 249. Anti-pattern — ADR aceito editado até parecer que sempre soubemos

Não.

---

# 250. Anti-pattern — apagar rejeitada

Não.

---

# 251. Anti-pattern — ADR para cada commit

Não.

---

# 252. Anti-pattern — ADR como tutorial

Não.

---

# 253. Anti-pattern — ADR como ordem imposta pela IA

Não.

Decisões relevantes precisam de aprovação.

---

# 254. Anti-pattern — decisão baseada em fonte desatualizada

Rever versões.

---

# 255. Anti-pattern — copiar ADR de outro projeto

O contexto é diferente.

---

# 256. Anti-pattern — esconder incerteza

Registrar confidence/revisit trigger.

---

# 257. Anti-pattern — “sem consequência negativa”

Desconfie.

---

# 258. Anti-pattern — “revisar depois” sem gatilho

Adicionar condição.

---

# 259. Anti-pattern — debate sem critério

Definir drivers.

---

# 260. Anti-pattern — benchmark sem cenário representativo

Não.

---

# 261. Anti-pattern — medir antes de definir problema

Não.

---

# 262. Anti-pattern — decisão por autoridade externa sem adaptação

Android recommendations precisam ser adaptadas ao app.

---

# 263. Anti-pattern — registrar política externa como escolha nossa

Separar fato/decisão.

---

# 264. Anti-pattern — decision log sem índice

Quando ADRs crescerem, manter índice.

---

# 265. Índice futuro

Pode mostrar:

```text
ID | Título | Status | Data | Substitui
```

Formato exato pendente.

---

# 266. Busca

Markdown + Git facilitam grep/search.

---

# 267. Tooling

## NÃO NECESSÁRIO AGORA

MADR funciona manualmente.

Não instalar CLI para criar arquivos Markdown se copiar template basta.

---

# 268. adr-tools

Existe.

Não é necessário até volume justificar.

---

# 269. Log4brains e similares

Não necessários.

---

# 270. YAML front matter

MADR usa frequentemente metadata em YAML.

## PENDENTE

Não adotar sem decidir formato ADR.

---

# 271. Markdown simples

É forte candidato porque:

- legível;
- versionável;
- já usado no projeto.

---

# 272. Ferramenta não deve dominar processo

ADR precisa continuar legível sem plugin.

---

# 273. Git como storage

Boa opção porque preserva:

- versão;
- review;
- blame;
- histórico.

---

# 274. Wiki externa

Não é necessária para projeto atual.

---

# 275. Single source of truth

Se ADR estiver no repo:

não duplicar decisão em Confluence/Notion sem necessidade.

---

# 276. Links externos podem morrer

Resumir a evidência essencial no registro.

---

# 277. Screenshot como evidência

Pode complementar.

Não usar como única documentação de texto/config.

---

# 278. Benchmarks

Guardar resultado reproduzível quando decisão depende deles.

---

# 279. Spike code

Pode ser branch/commit separado ou descartado.

ADR registra conclusão.

---

# 280. RFC versus ADR

RFC é proposta/discussão.

ADR registra decisão.

Em projeto individual, não precisamos criar dois processos formais agora.

---

# 281. ADR PROPOSTA pode cumprir papel de RFC leve

Sim.

---

# 282. Decision backlog

## FUTURO / CANDIDATO

Lista de decisões que sabemos que virão.

Já temos parte disso nos PENDENTES.

Não criar ferramenta separada.

---

# 283. Decisões que sabemos que virão

- nome do módulo engine;
- package engine;
- board representation;
- renderer;
- observable state;
- persistence;
- IA;
- monetização;
- release signing/storage;
- licença do repositório.

---

# 284. Decisão não precisa ser tomada na ordem em que foi descoberta

Tomar quando contexto é suficiente.

---

# 285. Last responsible moment

## PRINCÍPIO

Adiar escolha reversível até ter informação útil.

Sem usar isso como desculpa para indecisão que bloqueia trabalho.

---

# 286. Decisão bloqueante

Tomar.

---

# 287. Decisão futura

Registrar como pendente.

---

# 288. Trade-off explicitado

Exemplo:

```text
Custom View
```

ganha controle/performance potencial.

Perde acessibilidade automática.

Esse é um trade-off real.

---

# 289. Outra trade-off

```text
64 Views
```

ganha semantics/focus mais natural.

Pode aumentar complexidade de rendering/layout.

---

# 290. Esse tipo de comparação pertence ao futuro ADR do renderer

Não resolver aqui.

---

# 291. Decisão orientada por atributos de qualidade

Antes de escolher tecnologia, perguntar:

- qual atributo estamos otimizando?

---

# 292. Atributos relevantes do Gambitol

- correctness;
- testability;
- maintainability;
- accessibility;
- responsiveness;
- performance suficiente;
- compatibility;
- learning value.

---

# 293. Nem todos têm peso igual em toda decisão

Renderer:

- accessibility;
- responsiveness;
- performance.

Engine representation:

- correctness;
- simplicity;
- performance;
- testability.

---

# 294. Decision criteria por contexto

Não criar ranking global de atributos.

---

# 295. Segurança como driver

Se feature tocar:

- login;
- billing;
- network;
- secrets.

---

# 296. Privacy como driver

Se SDK coleta dados.

---

# 297. Legal/licença como driver

Para:

- fonts;
- piece assets;
- Stockfish;
- SDKs.

---

# 298. Custo monetário

Para serviços futuros.

---

# 299. Vendor lock-in

Para cloud/backend futuros.

---

# 300. Offline capability

Importante no core local.

---

# 301. Build complexity

Importante para libs/plugins.

---

# 302. App size

Importante para native engines/assets.

---

# 303. Developer experience

Importante, mas não sozinho.

---

# 304. Learning value

É driver legítimo neste projeto.

---

# 305. Interview value

Não deve superar produto/correção.

---

# 306. Decisão e ensino

Quando uma decisão for tomada:

o tutor deve explicar:

- contexto;
- alternativas;
- trade-off;
- motivo.

---

# 307. 🎥 MOMENTO BOM PARA GRAVAR — ADR real

Quando tomarmos a primeira decisão nova significativa.

Mostrar:

```text
problema
→ alternativas
→ drivers
→ decisão
→ consequência
```

Excelente conteúdo de engenharia.

---

# 308. 🎥 MOMENTO BOM PARA GRAVAR — supersession

Quando uma decisão realmente mudar.

Mostrar que mudar de ideia com evidência é maturidade, não fracasso.

---

# 309. 🎥 MOMENTO BOM PARA GRAVAR — renderer

Provavelmente um ADR visual/técnico excelente.

---

# 310. 🎥 MOMENTO BOM PARA GRAVAR — persistence

Comparar soluções a partir de requisitos.

---

# 311. 🎥 MOMENTO BOM PARA GRAVAR — AI engine

Comparar engine própria versus Stockfish quando chegar a hora.

---

# 312. COMO EXPLICAR EM ENTREVISTA — decisões

> “Eu mantive um decision log para as escolhas arquiteturalmente significativas. Cada decisão registrava contexto, alternativas, critérios e consequências; quando o contexto mudava, eu criava uma nova decisão que substituía a anterior em vez de reescrever a justificativa histórica.”

---

# 313. COMO EXPLICAR EM ENTREVISTA — trade-offs

> “O objetivo não era documentar cada escolha de código. Eu registrava decisões difíceis de reverter ou que afetavam estrutura, qualidade, dependências e interfaces.”

---

# 314. COMO EXPLICAR EM ENTREVISTA — evidência

> “Para decisões importantes eu registrava a evidência usada, como documentação oficial, testes, spikes ou benchmarks, e definia como confirmar que a implementação permanecia compatível com a decisão.”

---

# 315. COMO EXPLICAR EM ENTREVISTA — supersession

> “Uma decisão aceita não era editada retroativamente quando mudava. A nova decisão supersedia a anterior, preservando o histórico do porquê a arquitetura evoluiu.”

---

# 316. Revisão periódica do log

## NÃO PRECISA CALENDÁRIO FIXO

Revisar:

- antes de fase arquitetural nova;
- durante refactor grande;
- antes de tecnologia nova;
- antes de release quando decisões temporais existirem.

---

# 317. Review antes da Fase 2

Decisões necessárias:

- module name;
- package engine;
- Java compatibility real.

---

# 318. Review antes da Fase 5

Estratégia de simulação pode aparecer.

---

# 319. Review antes da Fase 9

Renderer.

---

# 320. Review antes de persistence

Storage.

---

# 321. Review antes de IA

Approach/licença/native.

---

# 322. Review antes de multiplayer

Backend/protocol/state authority.

---

# 323. Review antes de monetização

SDK/model/privacy.

---

# 324. Review antes de release

Signing/versioning/target/policies.

---

# 325. Decision debt

## CONCEITO

Existe quando:

- escolha importante já foi feita;
- ninguém registrou;
- contexto está se perdendo.

---

# 326. Como reduzir decision debt

Registrar retrospectivamente enquanto contexto ainda é conhecido.

Este documento já inicia esse trabalho.

---

# 327. Architecture drift

Quando código muda sem atualizar decisão/documentação.

---

# 328. Como detectar drift

- review;
- tests;
- dependency graph;
- build module boundaries;
- leitura de docs.

---

# 329. Não criar checker para tudo

Alguns guardrails simples bastam.

---

# 330. Decisão como guardrail

Exemplo:

```text
motor Java puro
```

O próprio Gradle/module pode impedir Android dependency.

---

# 331. Decisão como review rule

Exemplo:

```text
sem strings hardcoded
```

Lint.

---

# 332. Decisão como test

Exemplo:

```text
movimento ilegal não altera state
```

Unit test.

---

# 333. Decisão como policy

Exemplo:

```text
não commit sem aprovação
```

Processo.

---

# 334. Decision confirmation matrix

| Tipo de decisão | Confirmação típica |
|---|---|
| Dependência de módulo | build/compiler |
| Regra de domínio | tests |
| UI/acessibilidade | tests + review |
| Git/processo | workflow |
| Compatibilidade Android | build/device |
| Performance | benchmark |
| Play requirement | docs oficiais |
| Security/privacy | review + tests/policy |

---

# 335. Nem tudo pode ser “decisions as code”

E isso está certo.

---

# 336. Documentar compliance manual

Quando necessário.

---

# 337. Alteração de decisão e Git history

A nova decisão entra em commit junto ou antes da implementação correspondente.

---

# 338. Commit message

Segue documento 07.

Não criar padrão exclusivo para ADR.

---

# 339. PR title

Pode referenciar decisão quando útil.

---

# 340. Issue

Pode linkar.

Não obrigatório.

---

# 341. ID em comentário de código

## EVITAR POR PADRÃO

Não espalhar:

```java
// ADR-0012
```

em toda classe.

Só quando contexto não óbvio e referência agrega valor.

---

# 342. Link em documentação de módulo

Pode ser melhor.

---

# 343. Decisão e README

README não precisa listar todas.

Pode apontar para documentação.

---

# 344. Decisão e onboarding futuro

Decision log ajuda novos colaboradores a entender “por quê”.

---

# 345. Decisão e manutenção solo

Ajuda o próprio autor depois de meses.

Memória humana, por algum motivo, continua sem versionamento semântico.

---

# 346. Qualidade de um bom ADR

Deve ser:

- racional;
- específico;
- datado;
- factual;
- honesto sobre trade-offs;
- legível;
- independente de ferramenta.

---

# 347. Racional

Explica o porquê.

---

# 348. Específico

Uma decisão.

---

# 349. Datado

Contexto temporal.

---

# 350. Factual

Não propaganda da opção escolhida.

---

# 351. Honesto

Inclui custos.

---

# 352. Legível

Futuro leitor entende.

---

# 353. Independente

Markdown deve ser suficiente.

---

# 354. Checklist para criar decisão

- [ ] existe problema real;
- [ ] é significativa;
- [ ] precisa ser decidida agora;
- [ ] contexto está claro;
- [ ] drivers estão claros;
- [ ] alternativas reais foram consideradas;
- [ ] evidência foi coletada;
- [ ] consequências negativas aparecem;
- [ ] status está correto;
- [ ] confirmação é possível;
- [ ] gatilho de revisão foi definido se útil.

---

# 355. Checklist antes de aceitar

- [ ] decisão é assertiva;
- [ ] não mistura múltiplos assuntos;
- [ ] não contradiz documento normativo sem tratar conflito;
- [ ] riscos estão explícitos;
- [ ] versão/fatos temporais foram verificados;
- [ ] implementação pode seguir a decisão;
- [ ] usuário aprovou se for escolha estrutural importante.

---

# 356. Checklist de supersession

- [ ] contexto realmente mudou;
- [ ] nova decisão registrada;
- [ ] alternativa antiga citada;
- [ ] antiga marcada SUBSTITUÍDA;
- [ ] nova aponta para antiga;
- [ ] docs especializadas atualizadas;
- [ ] código migrado ou dívida registrada.

---

# 357. Checklist de decisão rejeitada

- [ ] motivo da rejeição;
- [ ] drivers;
- [ ] evidência;
- [ ] data;
- [ ] não apagar.

---

# 358. Checklist de decisão temporal

- [ ] fonte oficial;
- [ ] data de verificação;
- [ ] versão/API;
- [ ] trigger de nova verificação.

---

# 359. Checklist de dependência externa

- [ ] necessidade;
- [ ] alternativas sem dependência;
- [ ] licença;
- [ ] manutenção;
- [ ] versão;
- [ ] compatibilidade;
- [ ] tamanho/build;
- [ ] privacy/security;
- [ ] exit strategy quando relevante.

---

# 360. Checklist de tecnologia Android

- [ ] minSdk;
- [ ] target/compile;
- [ ] Java compatibility;
- [ ] lifecycle;
- [ ] accessibility;
- [ ] tests;
- [ ] official docs.

---

# 361. Checklist de decisão do motor

- [ ] correctness;
- [ ] testability;
- [ ] FIDE impact;
- [ ] performance;
- [ ] state invariants;
- [ ] future AI impact;
- [ ] no Android coupling.

---

# 362. Checklist de decisão de UI

- [ ] board priority;
- [ ] touch;
- [ ] responsiveness;
- [ ] accessibility;
- [ ] state-driven;
- [ ] testability;
- [ ] mockup consistency.

---

# 363. Checklist de release decision

- [ ] Play requirement current;
- [ ] signing;
- [ ] version;
- [ ] privacy;
- [ ] Data Safety;
- [ ] testing track;
- [ ] rollback/update impact.

---

# 364. Fontes pesquisadas — AWS ADR process

## Architectural decision record process

https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/adr-process.html

Usado para:

- definição de ADR;
- decision log;
- contexto/decisão/consequências;
- estados;
- aceite;
- imutabilidade;
- supersession;
- uso em review.

Verificado em: 2026-08-22.

---

# 365. Fontes — AWS best practices

## Best practices for using ADRs

https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/best-practices.html

Usado para:

- preservar histórico;
- centralizar registros;
- ownership;
- usar Git/wiki;
- lidar com código não conforme.

Verificado em: 2026-08-22.

---

# 366. Fontes — AWS FAQ

## ADR FAQ

https://docs.aws.amazon.com/prescriptive-guidance/latest/architectural-decision-records/faq.html

Usado para:

- quando criar ADR;
- linguagem assertiva;
- status;
- changelog;
- alternativas;
- consequências.

Verificado em: 2026-08-22.

---

# 367. Fontes — Microsoft

## Maintain an architecture decision record

https://learn.microsoft.com/en-us/azure/well-architected/architect-role/architecture-decision-record

Usado para:

- decisões importantes;
- alternativas rejeitadas;
- decisões difíceis de reverter;
- append-only;
- supersession;
- confidence;
- registros concisos;
- não transformar ADR em design guide.

Verificado em: 2026-08-22.

---

# 368. Fontes — Michael Nygard

## Documenting Architecture Decisions

https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions

Usado para:

- motivação original;
- documentação pequena/modular;
- preservar razões;
- formato Title/Status/Context/Decision/Consequences.

---

# 369. Fontes — template Nygard

https://github.com/joelparkerhenderson/architecture-decision-record/blob/main/locales/en/templates/decision-record-template-by-michael-nygard/index.md

Usado para confirmar:

- Title;
- Status;
- Context;
- Decision;
- Consequences.

---

# 370. Fontes — ADR organization

## Architectural Decision Records

https://adr.github.io/

Usado para:

- AD;
- ADR;
- decision log;
- rationale;
- trade-offs;
- conhecimento arquitetural.

Verificado em: 2026-08-22.

---

# 371. Fontes — MADR

## MADR

https://adr.github.io/madr/

## MADR Template

https://adr.github.io/madr/decisions/adr-template.html

Usado para:

- Context and Problem Statement;
- Decision Drivers;
- Considered Options;
- Decision Outcome;
- Consequences;
- Confirmation;
- More Information;
- status/metadata.

Verificado em: 2026-08-22.

---

# 372. Fontes — ADR templates

https://adr.github.io/adr-templates/

Usado para comparar:

- MADR;
- Nygard;
- Y-Statement;
- outros formatos.

Verificado em: 2026-08-22.

---

# 373. Fontes — arc42 Architecture Decisions

https://docs.arc42.org/section-9/

Usado para:

- decisões importantes;
- rationale;
- não duplicar;
- Nygard structure;
- consequências positivas/negativas/neutras;
- timestamps.

Verificado em: 2026-08-22.

---

# 374. Fontes — arc42 significance

https://docs.arc42.org/tips/9-1/

Usado para:

- quais decisões merecem registro;
- estrutura;
- qualidade;
- dependencies;
- interfaces;
- risco/custo.

---

# 375. Fontes — arc42 decision criteria

https://docs.arc42.org/tips/9-2/

Usado para:

- critérios;
- prioridades;
- requisitos técnicos/organizacionais/legais.

---

# 376. Fontes — arc42 rationale

https://docs.arc42.org/tips/9-3/

Usado para:

- importância do “por quê”;
- chain of reasoning.

---

# 377. Fontes — good ADRs

https://docs.arc42.org/tips/9-9/

Usado para:

- racional;
- específico;
- timestamp;
- imutabilidade.

---

# 378. Fonte complementar — SEI

Software architecture documentation literature do SEI trata rationale como informação essencial além das views arquiteturais.

Isso reforça a distinção:

```text
diagramas mostram estrutura
decision records mostram motivo
```

---

# 379. Relação com `00_GUIA_MESTRE.md`

O Guia Mestre define:

- governança;
- precedência;
- status.

Este documento aplica isso a decisões técnicas.

---

# 380. Relação com `03_ARQUITETURA_DO_GAMBITOL.md`

Arquitetura mostra o modelo vigente.

Decision log explica como ele chegou ali.

---

# 381. Relação com `10_ROADMAP_E_ESCOPO.md`

Roadmap diz quando uma decisão precisa ser tomada.

Decision log preserva a escolha feita.

---

# 382. Relação com `15_TROUBLESHOOTING.md`

Troubleshooting registra problemas recorrentes.

Uma solução que altera arquitetura pode gerar decisão.

---

# 383. Política final

## DECIDIDO

Para o Gambitol:

1. decisões arquiteturalmente significativas devem ser registradas;
2. não registrar toda microescolha;
3. contexto, decisão e consequências são obrigatórios;
4. alternativas relevantes devem ser preservadas;
5. consequências negativas devem ser explícitas;
6. decisões possuem status;
7. decisões aceitas não são reescritas silenciosamente;
8. mudança significativa gera supersession;
9. rejeitadas permanecem no histórico;
10. evidência deve ser registrada quando relevante;
11. fatos temporais recebem data/fonte;
12. decisão importante precisa de aprovação antes de ser tratada como vigente;
13. o código deve respeitar decisões vigentes ou disparar revisão;
14. o registro vive junto da documentação versionada;
15. formato/pasta de ADR individual continuam pendentes até necessidade real.

---

# 384. Resumo executivo

O processo pode ser resumido assim:

```text
NÃO SABEMOS
↓
DEFINIMOS O PROBLEMA
↓
IDENTIFICAMOS DRIVERS
↓
PESQUISAMOS
↓
COMPARAMOS ALTERNATIVAS
↓
ACEITAMOS TRADE-OFFS
↓
REGISTRAMOS A DECISÃO
↓
IMPLEMENTAMOS
↓
CONFIRMAMOS
↓
PRESERVAMOS O HISTÓRICO
↓
MUDAMOS APENAS COM NOVA EVIDÊNCIA
```

---

# 385. Frase norteadora

> **Uma decisão técnica boa não é aquela que parece inevitável depois de pronta; é aquela cujo contexto, alternativas e consequências permitem que alguém no futuro entenda por que ela fazia sentido quando foi tomada.**

---

# 386. Próximo documento

Após aprovação:

`12_PLAY_STORE_E_RELEASE.md`

Ele deverá consolidar:

- applicationId;
- versionCode;
- versionName;
- targetSdk;
- compileSdk;
- AAB;
- signing;
- keystore;
- Play App Signing;
- tracks;
- testes;
- closed testing quando aplicável;
- Data Safety;
- privacy;
- content rating;
- store listing;
- screenshots;
- pre-launch report;
- Android vitals;
- staged rollout;
- release checklist;
- rollback/update strategy;
- fatos temporais que precisam ser reverificados perto da publicação.

O documento 11 define:

> **como preservamos por que o Gambitol tomou determinada direção técnica.**

O documento 12 definirá:

> **como transformamos um build validado em uma versão distribuível e publicável com segurança.**

---

# 387. Atualização — módulo Java puro do motor de xadrez

## STATUS

ACEITA E IMPLEMENTADA.

## Data da confirmação

2026-08-23.

## Contexto

A arquitetura do Gambitol já estabelecia que as regras de xadrez deveriam permanecer independentes da camada Android.

Durante a Fase 2 do roadmap, essa fronteira lógica precisou se tornar uma fronteira física e verificável pelo build.

## Decisão

O motor de xadrez utiliza o módulo Gradle:

```text
:chess-engine
```

O módulo é uma biblioteca Java pura configurada com:

```text
java-library
```

A direção de dependência aceita é:

```text
:app -> :chess-engine
```

O caminho inverso não é permitido:

```text
:chess-engine -> :app
```

## Consequências positivas

- o motor pode ser compilado e testado fora do Android;
- regras de xadrez não dependem de `Activity`, `View`, `Context` ou resources;
- testes JVM do domínio são rápidos;
- a camada Android pode consumir o motor sem se tornar fonte das regras;
- a fronteira arquitetural passa a ser verificável pelo build.

## Consequências negativas

- integração entre UI e domínio exigirá mapeamento explícito quando necessário;
- tipos Android não podem atravessar para o motor;
- parte da configuração Gradle passa a existir em módulo separado.

## Evidência

A implementação foi criada na branch:

```text
feature/chess-engine-module
```

com o commit:

```text
b2087a4 feat: adiciona modulo Java do motor de xadrez
```

Foi integrada à `main` pelo Pull Request `#1`, resultando no merge commit:

```text
3ad70b3 Merge pull request #1 from RAIONORIO/feature/chess-engine-module
```

A validação pós-merge foi executada com:

```text
./gradlew :chess-engine:test :app:assembleDebug
```

com resultado `BUILD SUCCESSFUL`.

Também foi verificado que o código do engine não possui dependências `android.*` ou `androidx.*`.

---

# 388. Atualização — package base do motor de xadrez

## STATUS

ACEITA E IMPLEMENTADA.

## Data da confirmação

2026-08-23.

## Contexto

O package do motor precisava permanecer sob o namespace do Gambitol e, ao mesmo tempo, deixar explícita a separação entre domínio de xadrez e camada Android.

## Decisão

O package base do motor é:

```text
br.com.raionorio.gambitol.engine
```

## Consequências

As classes iniciais do motor são criadas sob esse package.

Subpackages futuros só devem ser introduzidos quando responsabilidades reais justificarem a divisão, evitando estrutura vazia ou antecipada.

## Evidência

O primeiro tipo de domínio integrado nesse package foi:

```text
Side
```

com teste JVM correspondente em `SideTest`.

---

# 389. Atualização — JDK do build e nível de linguagem Java

## STATUS

ACEITA NO ESTADO ATUAL / REVISÁVEL.

## Data da confirmação

2026-08-23.

## Contexto

Durante a validação da base do projeto foi necessário separar dois conceitos que frequentemente são confundidos:

- o JDK que executa o Gradle;
- o nível de linguagem usado para compilar o código do projeto.

## Decisão

O Gradle do Gambitol é executado atualmente com:

```text
JDK 21
```

O nível de linguagem configurado para o código atual é:

```text
sourceCompatibility = JavaVersion.VERSION_17
targetCompatibility = JavaVersion.VERSION_17
```

Essa configuração vale atualmente para o módulo Android e para o módulo `:chess-engine`.

## Consequências

- o ambiente de build pode usar JDK 21;
- o código-fonte deve permanecer compatível com Java 17 enquanto essa decisão estiver vigente;
- a existência do JDK 21 não autoriza automaticamente recursos exclusivos de Java 21 no código;
- alteração futura do nível de linguagem exige avaliação explícita e atualização desta decisão.

## Evidência

O ambiente foi validado com Gradle executando sob JDK 21 e os módulos compilando com source/target 17.

---

# 390. Atualização — framework de testes JVM do engine

## STATUS

ACEITA E IMPLEMENTADA.

## Data da confirmação

2026-08-23.

## Contexto

O módulo `:chess-engine` é Java puro e precisa executar testes rápidos de domínio sem emulador ou instrumentação Android.

A definição exata do framework de testes JVM estava pendente na documentação inicial.

## Decisão

O módulo `:chess-engine` utiliza:

```text
JUnit Jupiter 6.1.3
```

Os testes são executados pela JUnit Platform através de:

```text
useJUnitPlatform()
```

## Consequências positivas

- testes do domínio não dependem de emulador;
- o motor utiliza a API moderna do JUnit Jupiter;
- a estratégia de testes do engine permanece separada da instrumentação Android.

## Consequência importante

A adoção do JUnit Jupiter no `:chess-engine` não implica migração automática dos testes existentes do módulo `:app`.

Qualquer mudança da estratégia de testes do módulo Android deve ser decidida separadamente.

## Primeira evidência executável

O tipo:

```text
Side
```

possui testes JVM cobrindo:

```text
WHITE.opposite() == BLACK
BLACK.opposite() == WHITE
```

Os dois testes foram executados com sucesso antes da integração e novamente no fluxo de validação da `main`.

