# 07 — GIT WORKFLOW DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `07_GIT_WORKFLOW.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir como o Git e o GitHub devem ser usados durante o desenvolvimento do Gambitol, incluindo branches, staging, commits, revisão, merges, sincronização, recuperação, tags, releases e segurança  
> **Fonte normativa para:** fluxo Git Bash, proteção da `main`, criação e encerramento de branches, staging, mensagens de commit, revisão de diff, merge, rebase, pull/push, conflitos, stash, restore/reset/revert, reflog, tags e higiene do histórico  
> **Não cobre em detalhe:** estratégia completa de testes, arquitetura do código, regras de xadrez, CI/CD detalhado, processo de publicação na Play Store ou versionamento Android completo  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `06_PADROES_JAVA_E_ANDROID.md`  
> **Ferramenta operacional principal:** Git Bash no Windows  
> **Idioma principal:** Português do Brasil  

---

# 1. Propósito

Git no Gambitol não será tratado como:

> “aperta add, commit e push depois que funcionar”.

Ele será usado como ferramenta de engenharia.

O histórico precisa ajudar a responder:

- o que mudou;
- por que mudou;
- quando mudou;
- qual feature introduziu determinado comportamento;
- quando um bug apareceu;
- como voltar a um estado anterior;
- quais alterações pertencem à mesma unidade lógica;
- qual versão foi publicada;
- quais mudanças ainda não foram integradas;
- o que foi revisado antes de entrar na `main`.

O objetivo é possuir um histórico que seja útil para:

- manutenção;
- debugging;
- aprendizado;
- portfólio;
- releases;
- colaboração futura.

---

# 2. Princípio mestre

## DECIDIDO

O Git não deve ser usado apenas depois da implementação.

O fluxo normal será:

```text
VERIFICAR ESTADO
↓
CRIAR/ESCOLHER BRANCH
↓
IMPLEMENTAR PEQUENA ETAPA
↓
BUILD/TESTAR
↓
REVISAR DIFF
↓
STAGE CONSCIENTE
↓
REVISAR STAGED
↓
APROVAÇÃO
↓
COMMIT
↓
PUSH QUANDO APROPRIADO
↓
INTEGRAÇÃO
↓
VALIDAÇÃO FINAL
```

---

# 3. Regra absoluta de commit

## DECIDIDO

O tutor NÃO deve fazer, incluir ou presumir automaticamente:

```bash
git commit
```

antes de:

1. implementação concluída para aquela unidade;
2. validação;
3. revisão;
4. aprovação explícita do desenvolvedor.

Essa regra vale mesmo que a alteração pareça pequena.

---

# 4. Regra absoluta de push

## DECIDIDO

O tutor NÃO deve executar ou incluir automaticamente:

```bash
git push
```

sem que:

- o commit exista;
- o estado tenha sido revisado;
- o destino esteja claro;
- o desenvolvedor aprove.

Push publica histórico para o remoto.

Não é etapa invisível.

---

# 5. Git Bash como ferramenta principal

## DECIDIDO

Operações Git serão ensinadas preferencialmente pelo Git Bash.

Motivos:

- estado visível;
- comandos reproduzíveis;
- aprendizado transferível;
- independência de interface gráfica;
- facilidade para documentar.

Android Studio pode ser usado para visualizar diffs quando isso ajudar.

Mas a lógica do workflow deve ser compreendida sem depender de botões da IDE.

---

# 6. Modelo mental do Git

Existem pelo menos quatro estados importantes.

```text
WORKING TREE
    ↓ git add
INDEX / STAGING AREA
    ↓ git commit
LOCAL REPOSITORY
    ↓ git push
REMOTE REPOSITORY
```

Esse modelo é obrigatório para compreender o workflow.

---

# 7. Working tree

É o estado dos arquivos que estão no diretório do projeto.

Pode conter:

- arquivos iguais ao último commit;
- arquivos modificados;
- arquivos novos;
- arquivos removidos.

---

# 8. Index / staging area

O Git também chama de:

```text
index
```

É a seleção exata do conteúdo que entrará no próximo commit.

A documentação oficial do Git descreve o index como a lista do conteúdo preparado para o próximo commit.

Fonte:

https://git-scm.com/docs/gitdatamodel

---

# 9. Commit

Commit registra um snapshot do conteúdo staged, com:

- hash;
- autor;
- committer;
- data;
- mensagem;
- referência aos pais.

Um commit não é “salvar arquivo”.

É uma unidade histórica.

---

# 10. Remote

O remoto, normalmente chamado:

```text
origin
```

é uma referência para outro repositório, como GitHub.

Local e remoto são conceitos separados.

---

# 11. `git status -sb`

## DECIDIDO COMO PRIMEIRO COMANDO PADRÃO

Antes de mudança Git relevante:

```bash
git status -sb
```

Esse comando mostra:

- branch;
- tracking;
- mudanças staged;
- mudanças unstaged;
- arquivos untracked.

A documentação oficial define `-s` como formato curto e `-b` como inclusão das informações da branch.

Fonte:

https://git-scm.com/docs/git-status

---

# 12. Ler `git status -sb`

Exemplos:

```text
?? arquivo
```

arquivo não rastreado.

```text
 M arquivo
```

modificado na working tree, não staged.

```text
M  arquivo
```

mudança staged.

```text
A  arquivo
```

novo arquivo staged.

```text
D  arquivo
```

remoção staged.

A coluna esquerda corresponde ao index.

A coluna direita corresponde à working tree em situações normais.

---

# 13. `git diff`

## DECIDIDO

Antes do staging:

```bash
git diff
```

Mostra mudanças da working tree que ainda não foram staged.

---

# 14. `git diff --cached`

Depois do staging:

```bash
git diff --cached
```

Mostra exatamente as diferenças staged contra `HEAD`.

Também pode ser escrito:

```bash
git diff --staged
```

---

# 15. Revisar antes e depois de `git add`

Fluxo ideal:

```text
git diff
↓
git add ...
↓
git diff --cached
```

Isso reduz commits acidentais.

---

# 16. `git add` não “salva arquivo”

## CONCEITO IMPORTANTE

`git add` copia o conteúdo atual selecionado para o index.

Se o arquivo for alterado novamente depois:

essas alterações novas não entram automaticamente no staging.

A documentação oficial do Git deixa isso explícito.

Fonte:

https://git-scm.com/docs/git-add

---

# 17. Staging por arquivo

Preferir quando a unidade está clara:

```bash
git add caminho/do/arquivo
```

ou vários arquivos explicitamente:

```bash
git add arquivo1 arquivo2
```

---

# 18. `git add .`

## PERMITIDO COM REVISÃO

Pode ser conveniente.

Mas nunca deve significar:

> “adiciona tudo e torce”.

Antes:

```bash
git status -sb
git diff
```

Depois:

```bash
git diff --cached
```

---

# 19. `git add -A`

Inclui:

- novos;
- modificados;
- removidos;

no escopo aplicável.

Também exige revisão.

---

# 20. `git add -p`

## RECOMENDADO PARA COMMITS COESOS

Permite selecionar hunks de um mesmo arquivo.

Útil quando:

- arquivo contém duas mudanças não relacionadas;
- queremos separar refactor de feature;
- debug temporário não deve entrar.

A documentação oficial permite stage por hunk com `git add -p`.

---

# 21. Hunk

Hunk é um bloco de alterações do diff.

Com `git add -p`, podemos selecionar:

- `y` para stage;
- `n` para não;
- `s` para tentar dividir;
- outras opções interativas.

Não usar modo interativo sem entender o que está sendo staged.

---

# 22. Commit deve ser coeso

## DECIDIDO

Um commit deve representar uma mudança lógica compreensível.

Bom:

```text
feat(engine): add knight movement validation
```

Ruim:

```text
feat: knight + colors + readme + gradle upgrade + fix typo + experiment
```

---

# 23. Atomicidade de commit

“Atomic commit” aqui significa:

> uma unidade lógica que pode ser entendida e, idealmente, revertida sem levar mudanças não relacionadas junto.

Não significa necessariamente:

> uma linha ou um arquivo.

---

# 24. Commit não precisa ser minúsculo

Uma feature pode exigir vários arquivos:

```text
classe
teste
resource
integração
```

Se todos pertencem à mesma mudança lógica, podem fazer parte de um commit.

---

# 25. Commit não deve ser gigantesco sem motivo

Quanto maior:

- mais difícil revisar;
- mais difícil encontrar bug;
- mais difícil reverter;
- mais difícil explicar.

---

# 26. Não commit de código quebrado por padrão

## DECIDIDO

Um commit normal destinado ao histórico principal deve:

- compilar;
- passar testes relevantes;
- representar estado coerente.

---

# 27. WIP commit

## EXCEÇÃO

Commit local temporário pode ser usado em situação excepcional:

```text
WIP
```

por exemplo, interrupção urgente.

Mas antes de integrar à `main`:

- corrigir;
- squash/fixup/rebase conforme apropriado;
- ou substituir por commits coerentes.

Para interrupção simples, `git stash` pode ser melhor.

---

# 28. Estratégia de branches

## PROPOSTO PARA APROVAÇÃO

Adotar um workflow inspirado no **GitHub Flow**:

```text
main
  │
  ├── branch curta de feature
  ├── branch curta de fix
  └── branch curta de documentação
```

Sem branch permanente:

```text
develop
```

neste momento.

---

# 29. Por que não usar Git Flow completo

Git Flow clássico costuma introduzir:

- `develop`;
- feature branches;
- release branches;
- hotfix branches.

Para um projeto mobile individual em desenvolvimento contínuo, isso acrescentaria cerimônia sem necessidade atual.

O GitHub Flow oficial favorece branches separadas para mudanças não relacionadas, commits/push durante o trabalho, pull request, merge e exclusão da branch.

Fonte:

https://docs.github.com/en/get-started/using-github/github-flow

---

# 30. Papel da `main`

## DECIDIDO

`main` representa:

> a linha principal estável do Gambitol.

Após a fundação inicial:

- não desenvolver feature grande diretamente nela;
- não usar como rascunho;
- não fazer force push;
- não deixar commit conhecido como quebrado.

---

# 31. `main` não significa “Play Store production”

Importante:

uma `main` saudável pode conter trabalho ainda não publicado.

Release publicado será identificado por:

- tag;
- versão Android;
- release GitHub quando adotado.

---

# 32. Branches curtas

## PROPOSTO

Criar branch para uma unidade de trabalho coerente.

Exemplo conceitual:

```text
feature/engine-module
feature/knight-movement
fix/castling-check
docs/chess-rules
```

Os nomes exatos da primeira branch futura devem ser definidos conforme a tarefa.

---

# 33. Prefixos de branch propostos

## PROPOSTO PARA APROVAÇÃO

```text
feature/
fix/
refactor/
test/
docs/
chore/
build/
```

Não criar todos.

São apenas categorias disponíveis.

---

# 34. `feature/`

Nova capacidade do produto ou engine.

Exemplos:

```text
feature/knight-movement
feature/board-rendering
```

---

# 35. `fix/`

Correção de comportamento incorreto.

```text
fix/en-passant-king-safety
```

---

# 36. `refactor/`

Mudança estrutural sem alterar comportamento intencional.

```text
refactor/move-validation
```

---

# 37. `test/`

Mudança focada em testes.

```text
test/castling-edge-cases
```

---

# 38. `docs/`

Documentação.

```text
docs/update-architecture
```

---

# 39. `chore/`

Manutenção que não se encaixa em produto diretamente.

Exemplo:

- housekeeping;
- configuração auxiliar.

Usar com parcimônia.

---

# 40. `build/`

Mudanças em:

- Gradle;
- plugins;
- dependências de build;
- configuração de build.

---

# 41. Nome após prefixo

## PROPOSTO

Usar:

```text
lowercase-kebab-case
```

Exemplo:

```text
feature/legal-move-generation
```

Evitar:

```text
feature/NovaFeature
feature/teste123
feature/coisa
```

---

# 42. Sem nome gigante de branch

A branch precisa identificar trabalho, não conter descrição completa.

---

# 43. Branch por conjunto relacionado

A documentação oficial do GitHub recomenda branch separada para conjuntos de mudanças não relacionadas, facilitando feedback, entendimento e reversão.

Fonte:

https://docs.github.com/en/get-started/using-github/github-flow

---

# 44. Criar branch

Quando nome aprovado:

```bash
git switch -c <branch>
```

Preferir `git switch` para operação de branch.

---

# 45. `git switch`

É comando dedicado a trocar/criar branches.

Isso reduz a sobrecarga conceitual do antigo `git checkout`, que fazia várias funções diferentes.

---

# 46. Confirmar branch

Depois:

```bash
git status -sb
```

---

# 47. Não criar branch com working tree desconhecida

Antes:

```bash
git status -sb
```

Sempre.

---

# 48. Branch base atualizada

Antes de iniciar feature nova, idealmente:

```text
main local
=
main remota
```

quando remoto existir.

---

# 49. Sincronização recomendada da `main`

## PROPOSTO

Quando remoto existir:

```bash
git switch main
git fetch origin
git pull --ff-only origin main
```

Mas cada comando deve ser explicado antes de uso.

---

# 50. Por que `fetch` antes

`git fetch` atualiza referências remotas sem integrar automaticamente na branch atual.

Isso permite inspecionar antes.

---

# 51. `pull`

`git pull` combina:

- fetch;
- integração.

Por isso deve ser usado conscientemente.

---

# 52. `--ff-only`

## PROPOSTO PARA `main`

```bash
git pull --ff-only
```

recusa criar merge commit acidental se a branch local divergiu.

Isso protege a linearidade de atualização da `main`.

A documentação do Git define `--ff-only` como recusar merge se não puder ser fast-forward.

---

# 53. Pull em branch de feature

Não usar automaticamente.

Primeiro perguntar:

> queremos atualizar a branch com `main` ou com seu upstream remoto?

---

# 54. Push inicial da branch

Quando aprovado:

```bash
git push -u origin <branch>
```

`-u` configura upstream/tracking.

A documentação de `git push` descreve `--set-upstream`.

---

# 55. Push posterior

Depois de upstream:

```bash
git push
```

pode ser suficiente.

Ainda exige aprovação no nosso workflow.

---

# 56. Pull request

## PROPOSTO FORTEMENTE PARA MUDANÇAS RELEVANTES

Mesmo sendo inicialmente um projeto individual, PR pode funcionar como:

- checkpoint de revisão;
- registro de contexto;
- portfólio;
- ponto de CI futuro;
- documentação do motivo da mudança.

---

# 57. Nem toda mudança precisa PR

Correção minúscula de documentação pode não justificar.

Mas features/regras importantes do motor são boas candidatas.

---

# 58. PR deve explicar problema

A documentação GitHub recomenda incluir resumo das mudanças e o problema que resolvem.

Formato proposto:

```text
## O que mudou
...

## Por quê
...

## Como validar
...

## Evidências
- build
- testes
- screenshots quando UI
```

---

# 59. Draft PR

Quando remoto/processo estiver maduro:

Draft PR pode ser usado para feature ainda em andamento e feedback antecipado.

GitHub suporta PR em draft.

---

# 60. Proteção da `main`

## PROPOSTO PARA QUANDO O REPOSITÓRIO REMOTO ESTIVER PRONTO

Usar branch protection/ruleset na `main` para impedir acidentes.

Possíveis regras:

- bloquear force push;
- bloquear exclusão;
- exigir status checks quando CI existir;
- exigir PR quando fluxo amadurecer;
- exigir resolução de conversas quando houver reviewers.

GitHub permite essas proteções.

Fonte:

https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/managing-protected-branches/about-protected-branches

---

# 61. Não exigir tudo no primeiro dia

No começo, talvez ainda não existam:

- CI;
- reviewers;
- checks.

Não criar proteção impossível de satisfazer.

A proteção cresce com o projeto.

---

# 62. Force push na `main`

## PROIBIDO

Não usar:

```bash
git push --force origin main
```

em fluxo normal.

---

# 63. Force push em branch publicada

## EVITAR

Se rebase de branch própria publicada exigir reescrita:

preferir, quando realmente necessário:

```bash
git push --force-with-lease
```

em vez de:

```bash
git push --force
```

A documentação oficial explica que `--force-with-lease` tenta impedir sobrescrever mudanças remotas inesperadas.

Fonte:

https://git-scm.com/docs/git-push

---

# 64. `--force-with-lease` não é licença para descuido

Ainda reescreve histórico.

Antes:

- branch correta;
- fetch;
- status;
- log;
- confirmação;
- nenhum colaborador dependente daquele histórico.

---

# 65. Rebase

`git rebase` reaplica commits sobre outra base.

É uma operação de reescrita de histórico.

Fonte:

https://git-scm.com/docs/git-rebase

---

# 66. Rebase em commit local

## PERMITIDO COM EXPLICAÇÃO

Ótimo para:

- limpar commits locais;
- atualizar branch privada;
- corrigir mensagem;
- squash/fixup antes de integração.

---

# 67. Rebase em histórico compartilhado

## EVITAR

Se outra pessoa pode ter baseado trabalho nos commits:

não reescrever sem coordenação.

---

# 68. Rebase da `main`

## PROIBIDO COMO ROTINA

Não reescrever a `main` publicada.

---

# 69. Rebase de branch privada sobre `main`

## PROPOSTO QUANDO NECESSÁRIO

Fluxo conceitual:

```text
feature possui commits locais
main avançou
↓
git rebase main
```

Isso mantém histórico da feature sem merge intermediário.

Mas será usado somente depois de explicar e verificar.

---

# 70. `rebase --abort`

Se rebase entrou em estado indesejado:

```bash
git rebase --abort
```

tenta retornar ao estado anterior ao rebase.

---

# 71. Merge

`git merge` une históricos.

Fonte:

https://git-scm.com/docs/git-merge

---

# 72. Estratégia de integração proposta

## PROPOSTO PARA APROVAÇÃO

Para features relevantes do Gambitol:

> preservar a existência da branch no histórico usando merge commit.

Localmente:

```bash
git merge --no-ff <branch>
```

quando esse for o fluxo escolhido.

No GitHub:

usar:

```text
Create a merge commit
```

para PRs relevantes.

---

# 73. Por que `--no-ff`

Mesmo quando um fast-forward seria possível:

`--no-ff` cria um merge commit.

Isso deixa explícito:

> estes commits formaram uma branch/feature integrada aqui.

É útil para:

- aprendizado;
- navegação do histórico;
- agrupamento da feature.

---

# 74. Custo do `--no-ff`

Cria mais commits de merge.

Se o histórico ficar excessivamente ruidoso:

reavaliar.

Não existe prêmio por maior quantidade de losangos no `git log --graph`.

---

# 75. Squash merge

GitHub permite:

```text
Squash and merge
```

que integra a branch como um commit.

Fonte:

https://docs.github.com/en/pull-requests/how-tos/merge-and-close-pull-requests/merging-a-pull-request

---

# 76. Quando squash pode ser útil

Branch com:

```text
fix typo
oops
fix test
actually fix
cleanup
```

não precisa despejar esse processo na `main`.

Squash pode condensar.

---

# 77. Quando não usar squash automaticamente

Se commits da branch foram cuidadosamente organizados e têm valor histórico:

preservá-los pode ser melhor.

---

# 78. Rebase and merge no GitHub

GitHub também permite reaplicar commits individualmente na base.

A documentação informa que isso cria novos SHAs.

Não escolher sem compreender essa consequência.

---

# 79. Merge method não é religião

A decisão deve preservar:

- histórico legível;
- segurança;
- capacidade de recuperar;
- contexto.

---

# 80. Processo antes do merge

## DECIDIDO

Antes de integrar:

1. `git status -sb`;
2. build relevante;
3. testes relevantes;
4. Lint quando aplicável;
5. `git log`;
6. revisar diff contra `main`;
7. confirmar que branch não contém arquivo estranho;
8. aprovar.

---

# 81. Diff da feature contra `main`

Exemplo:

```bash
git diff main...<branch>
```

ou ferramenta apropriada.

Antes de uso, explicar a semântica de dois/três pontos quando for relevante.

---

# 82. Depois do merge

Validar novamente:

- build;
- testes;
- status.

Integração pode introduzir problema mesmo se branch isolada estava verde.

---

# 83. Excluir branch após merge

## DECIDIDO COMO PREFERÊNCIA

Depois de confirmar integração:

local:

```bash
git branch -d <branch>
```

remota:

```bash
git push origin --delete <branch>
```

somente quando remoto existir e exclusão for aprovada.

---

# 84. `-d` versus `-D`

`-d` protege contra excluir branch ainda não reconhecida como mergeada.

`-D` força.

## REGRA

Preferir:

```bash
git branch -d
```

Nunca usar `-D` por padrão.

---

# 85. Branch deletada não apaga commits integrados

Commits alcançáveis pela `main` continuam no histórico.

A branch é apenas um ponteiro.

Esse conceito precisa ser compreendido.

---

# 86. Mensagens de commit

## PROPOSTO PARA APROVAÇÃO

Adotar **Conventional Commits 1.0.0**.

Formato:

```text
<type>[optional scope]: <description>
```

Com corpo/footers quando necessário.

Fonte:

https://www.conventionalcommits.org/pt-br/v1.0.0/

---

# 87. Por que Conventional Commits

Benefícios:

- histórico legível;
- categorias consistentes;
- automação futura;
- changelog;
- SemVer futuro;
- comunicação profissional.

A especificação foi criada exatamente para fornecer significado legível por humanos e máquinas às mensagens.

---

# 88. Tipos propostos

```text
feat
fix
refactor
test
docs
build
chore
ci
perf
style
```

A especificação exige semântica especial para `feat` e `fix` e permite tipos adicionais.

---

# 89. `feat`

Nova funcionalidade.

Exemplo:

```text
feat(engine): add knight movement validation
```

---

# 90. `fix`

Correção de bug.

```text
fix(engine): prevent illegal en passant exposing king
```

---

# 91. `refactor`

Mudança interna sem mudança intencional de comportamento.

```text
refactor(engine): extract sliding path validation
```

---

# 92. `test`

Testes.

```text
test(engine): cover kingside castling restrictions
```

---

# 93. `docs`

Documentação.

```text
docs: document chess engine draw rules
```

---

# 94. `build`

Build system/dependências.

```text
build: add Java engine module
```

---

# 95. `chore`

Manutenção.

Usar apenas quando tipos mais específicos não descrevem melhor.

---

# 96. `ci`

Workflows de integração contínua.

```text
ci: run unit tests on pull requests
```

---

# 97. `perf`

Otimização comprovada.

```text
perf(engine): reduce allocations during move generation
```

Não usar antes de medir.

---

# 98. `style`

Mudança que não afeta lógica:

- whitespace;
- formatting.

Não usar para UI visual.

---

# 99. Escopo

## PROPOSTO

Escopo opcional.

Exemplos possíveis:

```text
engine
ui
game
gradle
docs
release
```

Somente usar se ajuda.

---

# 100. Não inventar escopo por classe

Evitar:

```text
feat(knightmovementvalidatorfactory)
```

Escopo deve ser domínio/área.

---

# 101. Descrição do commit

## DECIDIDO COMO PADRÃO

- inglês;
- imperativo/descrição concisa;
- sem ponto final;
- explicar mudança;
- não escrever “changes” ou “updates”.

Exemplo:

```text
feat(engine): add legal knight moves
```

---

# 102. Commit message não é diário

Ruim:

```text
feat: worked on stuff today
```

---

# 103. Commit message não é lista de arquivos

Ruim:

```text
fix: change Game.java and Board.java
```

O Git já sabe os arquivos.

Mensagem deve explicar intenção.

---

# 104. Corpo do commit

Usar quando o título não explica:

- motivo;
- trade-off;
- comportamento especial.

Exemplo conceitual:

```text
fix(engine): preserve castling rights after invalid move

An invalid move must not mutate historical state. The previous
implementation cleared kingside rights before legality was confirmed.
```

---

# 105. Footer

Pode incluir:

- issue;
- breaking change;
- co-author;
- trailers.

Não adicionar por decoração.

---

# 106. Breaking change

Quando API importante quebrar compatibilidade:

Conventional Commits usa:

```text
!
```

ou:

```text
BREAKING CHANGE:
```

No projeto interno isso ainda pode ajudar releases futuras.

---

# 107. Mensagens de merge

Merge commit pode usar mensagem específica:

```text
merge: integrate knight movement feature
```

## OBSERVAÇÃO

`merge` não é tipo exigido pelo Conventional Commits.

Podemos manter a mensagem automática de merge ou adotar `merge:` como convenção local.

### Status

PROPOSTO / NÃO OBRIGATÓRIO.

---

# 108. Primeira linha curta

Manter título fácil de ler em:

```bash
git log --oneline
```

---

# 109. `git log --oneline`

Comando básico para histórico compacto:

```bash
git log --oneline --decorate -n 20
```

---

# 110. `git log --graph`

## RECOMENDADO DIDATICAMENTE

```bash
git log --oneline --graph --decorate --all
```

Mostra branches e merges visualmente.

---

# 111. 🎥 MOMENTO BOM PARA GRAVAR — Git graph

Quando houver primeiras branches e merge:

mostrar antes/depois.

Tema:

> “O que uma branch realmente é no Git.”

---

# 112. Amend

`git commit --amend` substitui o commit mais recente.

## PERMITIDO

Quando o commit ainda não foi compartilhado, por exemplo:

- mensagem errada;
- arquivo esquecido.

---

# 113. Amend depois de push

## EVITAR

Amend muda SHA.

Se já publicado, exigirá reescrita remota.

---

# 114. Interactive rebase

## FUTURO / AVANÇADO

Pode:

- reorder;
- squash;
- fixup;
- reword.

Não usar como rotina até modelo mental estar sólido.

---

# 115. Histórico publicado não é massa de modelar

## REGRA

Depois que commits entram na `main` remota:

preferir novos commits corretivos/revert em vez de reescrever.

---

# 116. `git restore`

Comando moderno para restaurar arquivos do working tree/index.

Fonte:

https://git-scm.com/docs/git-restore

---

# 117. Descartar alteração local de arquivo

## PERIGOSO

```bash
git restore arquivo
```

substitui mudanças não staged pela versão do index.

Antes:

- diff;
- confirmar que nada precisa ser salvo.

Nunca enviar esse comando casualmente.

---

# 118. Unstage sem perder working tree

```bash
git restore --staged arquivo
```

remove do staging, preservando conteúdo local.

É a forma preferida pedagógica para “desfazer git add”.

---

# 119. `git reset`

É ferramenta poderosa e sobrecarregada.

Pode:

- mover `HEAD`;
- alterar index;
- alterar working tree dependendo do modo.

Fonte:

https://git-scm.com/docs/git-reset

---

# 120. `git reset --soft`

Move `HEAD`, preservando index e working tree.

Pode ser útil para refazer commits locais.

---

# 121. `git reset --mixed`

Move `HEAD` e atualiza index, preservando working tree.

É o modo padrão quando um commit alvo é especificado.

---

# 122. `git reset --hard`

## ALTO RISCO

Pode descartar mudanças de arquivos rastreados e atualizar working tree para o commit alvo.

Nunca usar sem:

1. status;
2. diff;
3. saber o alvo;
4. confirmar que conteúdo pode ser perdido;
5. considerar backup/branch/reflog.

---

# 123. Regra do tutor para `--hard`

## DECIDIDO

O tutor não deve sugerir `git reset --hard` como solução rápida.

Primeiro explicar exatamente o que será perdido.

---

# 124. `git revert`

Cria novo commit que inverte o efeito de commit anterior.

É apropriado para histórico compartilhado.

A documentação Git diferencia:

- restore: arquivos;
- reset: mover estado/ref;
- revert: novo commit reversor.

---

# 125. Revert na `main`

## PREFERIDO PARA COMMIT PUBLICADO PROBLEMÁTICO

Se um commit já está compartilhado e precisa ser desfeito:

```bash
git revert <commit>
```

é geralmente mais seguro do que reescrever histórico.

---

# 126. Revert de merge

É possível, mas exige escolher parent/mainline.

Não executar sem entender histórico.

---

# 127. `reflog`

## FERRAMENTA DE RECUPERAÇÃO CRÍTICA

Reflog registra movimentos locais de referências.

Pode ajudar a recuperar:

- commit “perdido” após reset;
- branch antes de rebase;
- HEAD anterior.

Fonte:

https://git-scm.com/docs/git-reflog

---

# 128. Reflog não é backup remoto

É local e sujeito a expiração/limpeza.

Não depender dele como estratégia de backup.

---

# 129. Comando básico

```bash
git reflog
```

Antes de qualquer recuperação:

ler, não executar reset às cegas.

---

# 130. 🎥 MOMENTO BOM PARA GRAVAR — recuperar commit com reflog

Excelente conteúdo quando ocorrer naturalmente:

- reset incorreto;
- commit aparentemente perdido;
- reflog encontra;
- branch temporária salva.

---

# 131. Criar branch de resgate

Ao localizar commit importante no reflog:

preferir criar branch/tag temporária antes de operações destrutivas.

Exemplo conceitual:

```bash
git branch rescue/<nome> <hash>
```

Nome deve ser definido para o caso concreto.

---

# 132. `git stash`

Stash guarda temporariamente estado da working tree/index e volta a uma working tree limpa.

Fonte:

https://git-scm.com/docs/git-stash

---

# 133. Quando usar stash

- interrupção urgente;
- precisa trocar branch;
- trabalho ainda não está em condição de commit;
- teste temporário.

---

# 134. Stash não é gaveta permanente

Não acumular:

```text
stash@{0}
stash@{1}
...
stash@{27}
```

sem saber o que contém.

---

# 135. Stash com mensagem

Preferir:

```bash
git stash push -m "describe the temporary work"
```

quando apropriado.

---

# 136. Untracked e stash

Por padrão, comportamento com untracked precisa ser considerado.

Se necessário:

```text
--include-untracked
```

Mas não adicionar flag automaticamente.

---

# 137. `stash list`

```bash
git stash list
```

---

# 138. `stash show`

Inspecionar antes de aplicar/remover.

---

# 139. `stash apply` versus `pop`

`apply`:

- reaplica;
- mantém stash.

`pop`:

- reaplica;
- remove se aplicação for bem-sucedida.

## PREFERÊNCIA DIDÁTICA

Em recuperação delicada:

usar `apply` primeiro é mais conservador.

---

# 140. `stash drop`

Somente depois de confirmar que conteúdo não é mais necessário.

---

# 141. Conflitos

Conflito não significa que Git “quebrou”.

Significa que não consegue decidir sozinho como combinar alterações.

---

# 142. Antes de merge/rebase

A documentação oficial do Git desaconselha iniciar merge com mudanças não commitadas relevantes, pois abortar pode não restaurar tudo facilmente.

Fonte:

https://git-scm.com/docs/git-merge

---

# 143. Regra antes de merge

## DECIDIDO

Working tree deve estar limpa ou conscientemente preservada.

---

# 144. Identificar conflito

`git status` mostra paths unmerged.

Arquivos podem conter marcadores:

```text
<<<<<<<
=======
>>>>>>>
```

---

# 145. Nunca apagar marcador sem entender conteúdo

Resolver significa escolher o resultado correto, não apenas remover símbolos.

---

# 146. Processo de conflito

```text
1. git status
2. abrir arquivos
3. entender ours/theirs/contexto
4. produzir conteúdo correto
5. rodar testes
6. git add arquivo resolvido
7. continuar merge/rebase
```

---

# 147. `ours` e `theirs`

O significado pode variar conforme operação, especialmente rebase.

Não ensinar “ours sempre é nossa feature” como regra universal.

---

# 148. Abort de merge

```bash
git merge --abort
```

pode tentar retornar ao estado anterior.

---

# 149. Abort de rebase

```bash
git rebase --abort
```

---

# 150. Continue merge/rebase

Depois de resolver:

```bash
git merge --continue
```

ou:

```bash
git rebase --continue
```

conforme operação.

---

# 151. Testes após conflito

## OBRIGATÓRIO

Um conflito resolvido manualmente pode produzir código que compila, mas está semanticamente errado.

Rodar testes relevantes.

---

# 152. 🎥 MOMENTO BOM PARA GRAVAR — conflito real

Bom conteúdo se conflito for compreensível.

Mostrar:

- ancestor;
- duas mudanças;
- marcador;
- decisão;
- teste.

---

# 153. `.gitignore`

Define arquivos intencionalmente **não rastreados** que o Git deve ignorar.

Fonte:

https://git-scm.com/docs/gitignore

---

# 154. `.gitignore` não remove arquivo já tracked

## CONCEITO CRÍTICO

Adicionar regra ao `.gitignore` não faz Git esquecer arquivo já versionado.

A documentação oficial diz explicitamente que arquivos já tracked não são afetados.

---

# 155. Se segredo já foi commitado

Remover do working tree/index não apaga segredo do histórico.

A credencial precisa ser:

- revogada/rotacionada;
- removida do histórico se necessário.

Não assumir que `.gitignore` resolveu.

---

# 156. Segredos proibidos no Git

Nunca commit:

- passwords;
- tokens;
- API keys;
- signing passwords;
- keystore;
- service account secret;
- certificados privados.

---

# 157. `.gitignore` atual

O projeto já possui uma base para:

- `.gradle`;
- builds;
- `.idea`;
- `local.properties`;
- APK/AAB;
- logs;
- arquivos do sistema.

Alterações futuras devem ser revisadas.

---

# 158. `.gitignore` não deve esconder fonte

Se um arquivo é necessário para reproduzir build:

não ignorar apenas porque muda muito.

---

# 159. Gradle Wrapper no Git

## DECIDIDO

Versionar:

- `gradlew`;
- `gradlew.bat`;
- wrapper properties;
- wrapper jar.

São parte da reprodutibilidade.

---

# 160. `local.properties`

## NÃO VERSIONAR

Contém configuração local, como SDK path.

---

# 161. `build/`

## NÃO VERSIONAR

Outputs gerados.

---

# 162. APK/AAB

## NÃO VERSIONAR COMO CÓDIGO NORMAL

Releases podem ser anexados a GitHub Releases se houver motivo.

---

# 163. `.idea`

A política atual ignora.

Se futuramente precisarmos compartilhar configuração específica:

avaliar conscientemente.

---

# 164. Line endings no Windows

O Gambitol é desenvolvido em Windows + Git Bash.

Isso exige atenção a:

- CRLF;
- LF;
- scripts shell;
- batch.

---

# 165. Git e line endings

A documentação oficial explica:

- `core.autocrlf`;
- `core.eol`;
- `.gitattributes`.

Git normalmente pode armazenar arquivos de texto normalizados em LF no repositório.

Fonte:

https://git-scm.com/docs/gitfaq

---

# 166. `.gitattributes`

## PROPOSTO PARA AVALIAÇÃO

Uma configuração explícita pode reduzir diffs apenas de line ending.

Exemplo conceitual:

```text
* text=auto
*.sh text eol=lf
*.bat text eol=crlf
```

Antes de criar:

- verificar estado atual;
- verificar configuração Git;
- evitar renormalização surpresa.

---

# 167. `gradlew`

Script shell deve funcionar com LF.

---

# 168. `gradlew.bat`

Batch pode usar CRLF no working tree.

---

# 169. Renormalização

Se adicionarmos `.gitattributes` depois de muitos arquivos:

pode gerar diff grande.

Fazer em commit separado e consciente.

---

# 170. Git config global versus projeto

Config global afeta outros repositórios.

Não alterar:

```bash
git config --global ...
```

sem explicar impacto.

---

# 171. Identidade Git

Configuração:

```text
user.name
user.email
```

deve corresponder à identidade profissional desejada.

Não copiar valor de tutorial.

---

# 172. E-mail e GitHub

Para contribuição aparecer corretamente no GitHub, o e-mail do commit deve estar associado/verificado na conta ou usar endereço `noreply` apropriado.

---

# 173. Assinatura de commits

## FUTURO / RECOMENDADO PARA PORTFÓLIO

GitHub suporta assinaturas:

- GPG;
- SSH;
- S/MIME.

Commits verificáveis recebem status de verificação.

Fonte:

https://docs.github.com/en/authentication/managing-commit-signature-verification/about-commit-signature-verification

---

# 174. SSH signing

GitHub informa que, para muitos usuários individuais, SSH ou GPG são escolhas adequadas, e assinatura SSH é simples de configurar.

Não configurar sem verificar versão Git e chave.

---

# 175. Assinatura não é sign-off

## CONCEITO

Commit signing:

> prova criptográfica.

`Signed-off-by`:

> trailer com significado jurídico/processual específico.

Não são a mesma coisa.

---

# 176. `git commit -s`

Não usar automaticamente.

Sign-off pode significar aceite de DCO em alguns projetos.

O Gambitol não adota DCO atualmente.

---

# 177. Branch protection e signed commits

GitHub pode exigir commits assinados em branch protegida.

Não ativar até assinatura estar configurada.

---

# 178. Tags

Tags marcam commits relevantes.

---

# 179. Lightweight versus annotated tags

A documentação Git diferencia:

### lightweight

apenas referência.

### annotated

objeto com:

- tagger;
- data;
- mensagem;
- assinatura opcional.

Git recomenda tags anotadas para releases.

Fonte:

https://git-scm.com/docs/git-tag

---

# 180. Tag de release

## PROPOSTO

Para releases do Gambitol:

```text
v<major>.<minor>.<patch>
```

Exemplos:

```text
v0.1.0
v0.2.0
v1.0.0
```

Baseado em SemVer quando adotado.

---

# 181. Tag anotada

## PROPOSTO

Release oficial:

```bash
git tag -a vX.Y.Z -m "Gambitol X.Y.Z"
```

Somente após versionamento/release aprovado.

---

# 182. Tag não deve apontar para build não validado

Antes:

- build release;
- testes;
- versionName/versionCode;
- revisão.

Detalhes no documento 12.

---

# 183. Não mover tag publicada casualmente

Uma tag de release deve ser tratada como imutável.

Se release saiu errada:

criar nova versão.

---

# 184. Tag local não vai automaticamente ao remoto

Push precisa ser explícito.

---

# 185. `git push --tags`

## EVITAR POR PADRÃO

Pode enviar tags locais não pretendidas.

Preferir push da tag específica quando necessário.

---

# 186. GitHub Releases

## FUTURO

Pode usar tag para criar release com:

- notas;
- artefatos;
- screenshots.

Play Store continua canal de distribuição Android ao usuário.

---

# 187. Semantic Versioning

## PROPOSTO PARA TAGS/RELEASE NOTES

SemVer:

```text
MAJOR.MINOR.PATCH
```

Mas Android também possui:

- `versionCode`;
- `versionName`.

Mapeamento será definido em `12_PLAY_STORE_E_RELEASE.md`.

---

# 188. Versões `0.x`

Durante desenvolvimento inicial:

```text
0.x.y
```

pode representar produto ainda pré-1.0.

Não criar versão sem release real.

---

# 189. Primeira tag

Somente quando existir milestone que mereça identificação.

Não taggear todo commit.

---

# 190. Git bisect

## FUTURO / IMPORTANTE

`git bisect` usa busca binária no histórico para encontrar commit que introduziu bug.

Excelente ferramenta quando histórico possui commits testáveis.

---

# 191. Pré-requisito para bisect útil

Commits intermediários precisam:

- compilar;
- ter comportamento coerente.

Mais um motivo para evitar commits quebrados.

---

# 192. `git bisect` + testes

Futuramente pode automatizar:

```text
good/bad
```

com teste.

Excelente momento de gravação.

---

# 193. Cherry-pick

## USO EXCEPCIONAL

`git cherry-pick` aplica um commit específico em outra branch.

Útil em:

- hotfix;
- recuperação;
- backport.

Não usar como fluxo principal.

---

# 194. Risco do cherry-pick

Cria commit com novo SHA.

Pode duplicar mudanças se usado sem entender histórico.

---

# 195. Hotfix

## FUTURO

Quando existir release pública e bug urgente:

pode ser necessário branch de correção baseada na versão apropriada.

Não criar processo de hotfix completo antes da primeira release.

---

# 196. Release branch

## NÃO NECESSÁRIA AGORA

A estratégia inicial não possui branch permanente de release.

Reavaliar quando:

- múltiplas versões precisarem manutenção;
- ciclos de release crescerem.

---

# 197. `develop`

## NÃO USAR AGORA

`main` + branches curtas é suficiente.

---

# 198. Branch longa

## EVITAR

Quanto mais tempo uma branch vive:

- mais diverge;
- mais conflito;
- mais difícil review.

Quebrar features grandes em incrementos integráveis.

---

# 199. Feature flag versus branch longa

## FUTURO

Quando produto já publicado e feature longa precisar entrar parcialmente:

feature flag pode ser melhor.

Não necessário agora.

---

# 200. Commits frequentes versus commits inúteis

Commitar com frequência é bom.

Mas:

```text
save
save2
now works
maybe
```

não é histórico profissional.

---

# 201. Checkpoint local

Se precisar preservar trabalho cedo:

- commit WIP local;
- stash;
- branch temporária;

dependendo do caso.

Depois limpar antes da integração.

---

# 202. Staging como ferramenta de ensino

Antes de commit:

o desenvolvedor precisa saber:

> exatamente o que está staged.

---

# 203. `git diff --check`

## RECOMENDADO

Pode detectar whitespace errors no diff.

Bom antes de commit.

---

# 204. `git status --short`

Útil em scripts.

Para uso humano, `-sb` permanece padrão.

---

# 205. `git show`

Para inspecionar commit:

```bash
git show <hash>
```

---

# 206. `git show --stat`

Resumo.

---

# 207. `git blame`

## USAR COM MATURIDADE

Mostra qual commit modificou linha.

Serve para contexto, não para procurar culpado.

---

# 208. `git log -p`

Mostra histórico + patch.

Útil para investigar evolução.

---

# 209. `git log -- arquivo`

Histórico específico de arquivo.

---

# 210. `git log -S`

## FUTURO

Pickaxe procura commits que alteraram ocorrência de string.

Excelente para debugging histórico.

---

# 211. `git log -G`

Busca regex no diff.

---

# 212. Git como debugger

Ferramentas:

- log;
- show;
- blame;
- bisect;
- reflog.

Fazem Git parte do troubleshooting, não só backup.

---

# 213. 🎥 MOMENTO BOM PARA GRAVAR — git bisect

Quando existir bug com histórico suficiente:

mostrar busca binária até commit culpado.

Conteúdo de alto valor.

---

# 214. Checklist antes de iniciar trabalho

- [ ] `git status -sb`;
- [ ] branch correta;
- [ ] working tree conhecida;
- [ ] `main` atualizada quando necessário;
- [ ] branch de trabalho definida;
- [ ] objetivo da mudança claro.

---

# 215. Checklist antes de `git add`

- [ ] build/test relevante executado;
- [ ] `git diff` revisado;
- [ ] arquivos temporários removidos;
- [ ] nenhum segredo;
- [ ] nenhum output gerado;
- [ ] mudança é coerente.

---

# 216. Checklist depois de staging

- [ ] `git status -sb`;
- [ ] `git diff --cached`;
- [ ] somente arquivos pretendidos;
- [ ] nenhuma mudança de debug;
- [ ] nenhuma credencial;
- [ ] nenhuma reformatação acidental.

---

# 217. Checklist antes de commit

- [ ] staged correto;
- [ ] build passa;
- [ ] testes passam;
- [ ] Lint quando relevante;
- [ ] mensagem descreve intenção;
- [ ] aprovação explícita.

---

# 218. Checklist depois de commit

- [ ] `git status -sb`;
- [ ] `git log --oneline -n 3`;
- [ ] commit correto;
- [ ] working tree esperada.

---

# 219. Checklist antes de push

- [ ] branch correta;
- [ ] commit correto;
- [ ] remoto correto;
- [ ] sem reescrita inesperada;
- [ ] aprovação.

---

# 220. Checklist antes de merge

- [ ] branch passou build;
- [ ] testes passaram;
- [ ] diff contra `main` revisado;
- [ ] commits coerentes;
- [ ] branch atualizada se necessário;
- [ ] sem conflito conhecido;
- [ ] método de merge escolhido;
- [ ] aprovação.

---

# 221. Checklist depois de merge

- [ ] `git status -sb`;
- [ ] build;
- [ ] testes;
- [ ] log;
- [ ] push da `main` somente após aprovação;
- [ ] branch pode ser excluída.

---

# 222. Checklist antes de comando destrutivo

Comandos como:

```text
reset --hard
clean
force push
branch -D
```

exigem:

- [ ] status;
- [ ] diff;
- [ ] alvo confirmado;
- [ ] conteúdo importante preservado;
- [ ] efeito explicado;
- [ ] confirmação explícita.

---

# 223. `git clean`

## ALTO RISCO

Remove arquivos não rastreados.

Não usar como rotina.

Se um dia for necessário:

primeiro dry-run:

```bash
git clean -n
```

e revisar.

---

# 224. `git clean -fd`

Pode apagar diretórios untracked.

## PROIBIDO SEM INSPEÇÃO

Nunca enviar automaticamente.

---

# 225. `git checkout .`

## EVITAR COMO RECEITA

É forma antiga/sobrecarregada de descartar alterações.

Preferir comandos modernos explícitos:

```text
restore
switch
```

conforme intenção.

---

# 226. `git reset HEAD arquivo`

Ainda funciona para unstage.

Mas para ensino inicial preferir:

```bash
git restore --staged arquivo
```

porque a intenção é mais explícita.

---

# 227. Segurança contra branch errada

Antes de:

- merge;
- push;
- reset;
- delete;

sempre mostrar branch atual.

---

# 228. `git branch --show-current`

Útil:

```bash
git branch --show-current
```

---

# 229. `HEAD`

HEAD aponta para checkout atual.

Normalmente aponta simbolicamente para uma branch.

---

# 230. Detached HEAD

Não é erro por si só.

Pode ocorrer ao:

- checkout tag/commit;
- certas operações.

Mas não desenvolver sem compreender onde commits ficarão.

---

# 231. Commit em detached HEAD

Pode ser recuperado, mas não está ancorado a branch automaticamente.

Se acontecer:

criar branch antes de perder referência.

---

# 232. Remote-tracking branches

Exemplo:

```text
origin/main
```

é referência local do estado remoto conhecido após fetch.

Não é a branch remota “ao vivo”.

---

# 233. `fetch` atualiza conhecimento remoto

Isso é conceito importante.

---

# 234. `origin/main` pode estar desatualizada

Até executar fetch.

---

# 235. Upstream

Uma branch local pode rastrear uma branch remota.

Isso permite:

- status ahead/behind;
- push/pull sem argumentos completos.

---

# 236. `ahead` e `behind`

`git status -sb` pode informar divergência.

Antes de push/pull:

entender.

---

# 237. Ahead

Commits locais não estão no upstream.

---

# 238. Behind

Upstream conhecido possui commits ausentes localmente.

---

# 239. Diverged

Ambos possuem commits exclusivos.

Não resolver com pull aleatório.

---

# 240. Divergence da `main`

## REGRA

Parar e investigar.

Não criar merge commit acidental em `main`.

---

# 241. Fetch + log para divergência

Podemos usar:

```bash
git fetch origin
git log --oneline --graph --decorate --all -n 30
```

antes de decidir.

---

# 242. Remote URL

Verificar:

```bash
git remote -v
```

antes de push importante se houver dúvida.

---

# 243. Token em URL

## PROIBIDO

Não salvar PAT diretamente em remote URL visível.

Usar mecanismo de autenticação apropriado.

---

# 244. HTTPS versus SSH

Ambos podem funcionar.

Escolha deve considerar ambiente.

Não trocar protocolo sem motivo.

---

# 245. Credenciais

Usar credential manager/SSH agent conforme configuração.

Nunca arquivo texto no repositório.

---

# 246. GitHub remote

Quando conectado, `origin` será o remoto principal, salvo decisão diferente.

---

# 247. Fork

## FUTURO

Se contribuições externas crescerem, forks podem entrar.

Não necessário para desenvolvimento do dono do repo.

---

# 248. Pull requests de terceiros

Quando existirem:

- revisar código;
- checks;
- segurança;
- licença;
- escopo.

---

# 249. CODEOWNERS

## FUTURO

Só quando equipe justificar.

---

# 250. PR template

## FUTURO / RECOMENDADO QUANDO PR SE TORNAR ROTINA

Pode padronizar:

- resumo;
- testes;
- screenshots;
- checklist.

Não criar antes de workflow remoto amadurecer.

---

# 251. Issue templates

## FUTURO

Mesma regra.

---

# 252. Branch ruleset

GitHub oferece rulesets além de branch protection tradicional.

Podem aplicar múltiplas regras e melhorar visibilidade.

Não escolher implementação administrativa agora.

---

# 253. Required checks

Quando CI existir:

proteger `main` exigindo:

- build;
- testes;
- talvez lint.

---

# 254. Required review em projeto solo

Pode ser inviável se exigir outra pessoa.

Não configurar regra impossível.

PR de auto-review ainda pode ter valor sem aprovação externa.

---

# 255. Conversation resolution

Útil quando colaboradores existirem.

---

# 256. Linear history

GitHub pode exigir.

## NÃO ATIVAR se adotarmos merge commits

Nossa proposta inicial de `--no-ff` é incompatível com regra de histórico linear.

Logo, não ativar ambos.

---

# 257. Decisão consciente de merge policy

Antes de configurar proteção:

alinhar:

```text
merge commits
```

versus:

```text
linear history
```

Não escolher duas políticas incompatíveis porque ambas parecem profissionais no menu.

---

# 258. Commit signing required

## FUTURO

Só depois de configurar assinatura local estável.

---

# 259. Branch deletion

GitHub pode bloquear delete da `main`.

Recomendado.

---

# 260. Force push protection

Recomendado para `main`.

---

# 261. Commit inicial do Gambitol

## ESTADO HISTÓRICO CONHECIDO

O repositório foi inicializado com:

```text
main
```

e, no último estado conhecido antes da pausa de desenvolvimento, ainda não havia commit confirmado.

Antes do primeiro commit:

- validar JDK/Gradle;
- conferir estrutura;
- incluir documentação aprovada conforme decisão;
- revisar `.gitignore`;
- rodar build;
- revisar diff/staging.

Não criar o primeiro commit apenas porque “já tem arquivos”.

---

# 262. Primeiro commit: significado

Deve representar:

> baseline reproduzível e conhecido do projeto.

Não necessariamente produto funcional.

---

# 263. Mensagem do primeiro commit

## PENDENTE

Não fixar agora.

Deve refletir exatamente o conteúdo que estiver sendo commitado naquele momento.

---

# 264. Branch antes do primeiro commit

Git permite branch ainda sem commits, mas alguns fluxos ficam diferentes.

Não complicar.

Primeiro estabilizar baseline.

---

# 265. Documentação no primeiro commit

## PENDENTE

Decidir se os documentos 00–15 entrarão:

- todos juntos após conclusão;
- em commits por documento;
- em um commit de documentação consolidado.

A decisão será tomada quando a série estiver concluída.

---

# 266. Recomendação para os documentos

## PROPOSTO

Como estão sendo construídos antes do primeiro baseline, é razoável integrá-los em uma unidade documental coerente após revisão.

Mas não decidir o commit agora.

---

# 267. Atualização futura de documentação

Mudança de regra/arquitetura deve normalmente incluir doc no mesmo commit da mudança de código quando ambos representam a mesma decisão.

Exemplo:

```text
refactor(engine): separate attack detection
```

pode incluir atualização arquitetural se necessário.

---

# 268. Commit só de docs

Quando não existe mudança de código correspondente:

```text
docs: ...
```

---

# 269. Commit de código sem docs necessários

Se mudança altera decisão normativa:

não considerar completo.

---

# 270. Reverter documentação

Revert acompanha a mesma lógica histórica.

---

# 271. Git e troubleshooting

Erros de Git importantes devem entrar no:

```text
15_TROUBLESHOOTING.md
```

quando possuírem valor reutilizável.

---

# 272. Exemplos de troubleshooting Git

- merge interrompido;
- rebase conflitante;
- detached HEAD;
- commit em branch errada;
- arquivo staged acidentalmente;
- arquivo secreto commitado;
- line ending massivo;
- branch remota apagada;
- commit perdido recuperado pelo reflog.

---

# 273. Commit na branch errada — sem push

Não começar com reset hard.

Primeiro:

- identificar commit;
- criar branch no ponto correto;
- mover branch errada com operação segura.

Estratégia depende do estado.

---

# 274. Commit na branch errada — já publicado

Mais cuidado.

Não reescrever branch compartilhada automaticamente.

---

# 275. Arquivo staged por engano

```bash
git restore --staged arquivo
```

normalmente resolve sem perder working tree.

---

# 276. Arquivo commitado por engano, sem push

Pode usar amend/reset conforme contexto.

---

# 277. Arquivo commitado por engano, com segredo

Mesmo sem push:

se credencial real foi exposta em logs/backup/outro local, avaliar rotação.

Com push:

rotacionar imediatamente.

---

# 278. Histórico é conteúdo público em repo público

Não assumir que apagar commit do branch significa que ninguém viu.

---

# 279. Binary files

Git não é ideal para grandes binários versionados repetidamente.

Assets finais pequenos são normais.

Arquivos grandes de design/vídeo precisam estratégia.

---

# 280. Git LFS

## FUTURO

Avaliar se grandes assets binários realmente entrarem.

Não instalar para PNGs pequenos do app.

---

# 281. Generated assets

Não versionar variações temporárias desnecessárias.

---

# 282. Source assets

Se necessário para reprodução do design, definir pasta/processo.

---

# 283. Releases e artefatos

APK/AAB final deve preferencialmente ficar no canal de release, não no histórico de source.

---

# 284. Changelog

## FUTURO

Conventional Commits facilita geração futura.

Não criar manualmente até releases justificarem.

---

# 285. Release notes

Devem ser orientadas ao usuário, não copia bruta dos commits.

---

# 286. Version tag e commit

Tag deve apontar para commit exato da release.

---

# 287. Hotfix após tag

Nova correção:

novo commit + nova versão/tag.

Não mover tag antiga.

---

# 288. Rollback Play Store versus Git

Reverter código no Git não despublica automaticamente versão da Play Store.

São sistemas distintos.

Documento 12 tratará release operacional.

---

# 289. Git tag ≠ Android versionCode

Relacionados, mas não equivalentes.

---

# 290. Git tag ≠ GitHub Release

Tag é referência Git.

Release é entidade GitHub associada a tag.

---

# 291. Git branch ≠ environment

Não usar branch para representar cada ambiente sem necessidade.

---

# 292. CI e branch

Quando chegar:

CI valida branches/PRs.

Não é owner da arquitetura.

---

# 293. Hooks Git

## FUTURO

Podem executar:

- format;
- lint;
- teste;
- commit message checks.

Não adicionar no início sem necessidade.

---

# 294. Limitação de hooks locais

Hooks em `.git/hooks` não são versionados normalmente.

Se processo depender deles, precisa mecanismo de distribuição.

---

# 295. Pre-commit framework

## FUTURO / NÃO NECESSÁRIO

Não adicionar ecossistema extra apenas para impedir whitespace.

---

# 296. Commit-msg hook

Pode validar Conventional Commits futuramente.

Não necessário enquanto disciplina manual é suficiente.

---

# 297. CI é melhor enforcement compartilhado

Quando equipe crescer, checks remotos são mais consistentes que configuração local voluntária.

---

# 298. Não usar alias antes de aprender comando

## DECIDIDO COMO MÉTODO DE ENSINO

Evitar:

```text
gst
gco
gp
```

no começo.

Digitar comando real ajuda formar modelo mental.

---

# 299. Aliases futuros

Podem ser usados quando comandos estiverem dominados.

Nunca documentação depender só de alias pessoal.

---

# 300. Scripts Git

Não criar wrapper para esconder operações básicas.

---

# 301. Git GUI

Pode complementar.

Não substituir entendimento.

---

# 302. Android Studio Git integration

Pode ser útil para:

- diff visual;
- conflict editor.

Mas comandos críticos serão explicados pelo Git.

---

# 303. GitHub Desktop

Não é necessário.

---

# 304. Commit from IDE

Permitido futuramente, mas workflow precisa continuar igual:

- status;
- diff;
- staged;
- tests;
- approval.

---

# 305. OneDrive e repositórios

Se projeto estiver em pasta sincronizada no futuro:

cuidado com:

- locks;
- arquivos gerados;
- sync conflicts.

O Gambitol atual foi indicado em:

```text
C:\Meus Projetos\gambitol
```

o que evita algumas complicações de sync automático.

---

# 306. Case sensitivity no Windows

Windows normalmente é case-insensitive.

Git e ambientes Unix podem tratar case de forma diferente.

Renomear apenas maiúscula/minúscula exige atenção.

---

# 307. Rename de classe case-only

Usar `git mv` intermediário quando necessário.

Não confiar que Windows detectará corretamente.

---

# 308. `git mv`

Registra move/rename no working tree + index.

Git detecta renames heuristicamente no histórico, mas `git mv` deixa intenção operacional clara.

---

# 309. `git rm`

Remove arquivo e stageia a remoção.

Não usar para arquivo que só queremos parar de rastrear sem apagar localmente.

---

# 310. `git rm --cached`

Pode remover do index preservando arquivo local.

Útil quando arquivo deveria ter sido ignorado.

Antes:

confirmar motivo e `.gitignore`.

---

# 311. Renames e commits

Evitar renomear centenas de arquivos junto com mudança funcional.

Separar refactor de comportamento quando possível.

---

# 312. Formatter massivo

Commit separado.

Isso preserva `git blame`.

---

# 313. Line ending massivo

Commit separado.

---

# 314. Dependency upgrade massivo

Commit separado da feature.

---

# 315. Android Studio upgrade

Não misturar com regra de xadrez.

---

# 316. Gradle wrapper upgrade

Commit próprio quando relevante.

---

# 317. Git history para portfólio

Histórico deve mostrar:

- evolução incremental;
- correções;
- testes;
- refactors;
- documentação.

Não precisa ser artificialmente perfeito.

---

# 318. Não falsificar histórico

Não reescrever meses de commits só para parecer que nunca houve erro.

Um histórico profissional pode mostrar aprendizado.

---

# 319. Limpar ruído antes da integração é diferente de falsificar

Squash WIP local é higiene.

Apagar decisões relevantes para parecer perfeito é outra coisa.

---

# 320. Commit messages em inglês

## PROPOSTO

Código já terá naming técnico em inglês.

Commit messages também em inglês.

README/documentação pode continuar em português.

---

# 321. Alternativa: commits em português

Tecnicamente válida.

Mas para portfólio internacional, inglês oferece maior interoperabilidade.

### Recomendação

inglês.

### Status

PROPOSTO até aprovação.

---

# 322. Scope em inglês

Se commits em inglês:

escopos também.

---

# 323. Branch names em inglês

## PROPOSTO

```text
feature/knight-movement
fix/castling-rights
```

para consistência com código.

---

# 324. GitHub PR title

Pode seguir Conventional Commits quando merge/squash utilizar título como commit.

Boa prática futura.

---

# 325. PR description

Pode ser português ou inglês conforme objetivo do repositório.

Para portfólio internacional, inglês é recomendado futuramente.

---

# 326. Self-review

Antes de PR/merge:

ler o diff como se tivesse sido escrito por outra pessoa.

Perguntas:

- por que essa linha mudou?
- existe debug esquecido?
- nome comunica?
- teste cobre?
- documentação bate?

---

# 327. AI-generated changes

Revisão é ainda mais importante.

Nunca stagear arquivo gerado inteiro sem ler.

---

# 328. `git add .` após IA

Só depois de:

- `git diff`;
- revisar todos os arquivos.

---

# 329. Script que altera muitos arquivos

Antes:

```bash
git status -sb
```

Depois:

```bash
git diff --stat
git diff
```

---

# 330. `git diff --stat`

Mostra resumo de arquivos/linhas.

Não substitui diff completo.

---

# 331. Binary diff

Git pode não mostrar conteúdo.

Revisar visualmente/por checksum conforme caso.

---

# 332. Large diff

Quebrar se possível.

---

# 333. `git status` limpo

Depois de commit/merge esperado:

```text
nothing to commit, working tree clean
```

é um bom checkpoint.

---

# 334. Working tree suja não é erro

Durante desenvolvimento é normal.

O problema é não saber por quê.

---

# 335. Staged + unstaged no mesmo arquivo

Git permite.

É poderoso, mas exige compreensão.

`git status` pode mostrar ambos.

---

# 336. Conteúdo do index é snapshot separado

Essa é razão.

---

# 337. Commit não leva automaticamente unstaged

Somente staged entra em commit normal.

---

# 338. `git commit -a`

## EVITAR NO INÍCIO

Faz stage automático de arquivos já tracked.

Pode esconder a etapa pedagógica de staging.

Não inclui novos untracked.

Preferir `git add` explícito.

---

# 339. `git commit <arquivo>`

Também pode ignorar staging de forma que confunde o modelo mental.

Evitar no workflow padrão.

---

# 340. `git commit --dry-run`

Pode mostrar resumo do que seria commitado.

Útil se houver dúvida.

---

# 341. Commit hooks podem impedir commit

Se futuramente hooks existirem:

não usar `--no-verify` automaticamente para ignorá-los.

---

# 342. `--no-verify`

## EVITAR

Só com motivo explícito.

---

# 343. Push hooks/checks

Não contornar branch protection para ganhar tempo.

---

# 344. Bypass admin

Se proteção existir, aplicar ao próprio administrador quando fizer sentido.

GitHub permite configurar proteções para administradores.

---

# 345. Solo project não significa zero disciplina

É justamente quando ninguém revisa que checks ajudam.

---

# 346. Solo project também não precisa de burocracia corporativa

Não exigir:

- 2 reviewers;
- CAB;
- 6 ambientes;
- merge queue;

para mover um cavalo no próprio projeto.

---

# 347. Merge queue

## NÃO NECESSÁRIA AGORA

Útil em repositórios de alta concorrência.

---

# 348. CODEOWNERS

## NÃO NECESSÁRIO AGORA

---

# 349. Required deployments

## NÃO NECESSÁRIO AGORA

---

# 350. Branch lock

Não para `main` ativa.

---

# 351. Rulesets

## FUTURO

Avaliar quando CI/proteções forem configuradas.

---

# 352. GitHub Actions

## FUTURO PRÓXIMO

Quando `08_TESTES_E_QUALIDADE.md` definir gates:

podemos criar workflow para:

- build;
- tests;
- lint.

---

# 353. CI commit type

Mudança de workflow:

```text
ci:
```

---

# 354. CI files

Provavelmente:

```text
.github/workflows/
```

quando aprovados.

---

# 355. Cache de Gradle no CI

Futuro.

Não compromete Git workflow.

---

# 356. Dependabot/Renovate

## FUTURO

Atualizações automáticas de dependência podem gerar PRs.

Não ativar até haver pipeline de validação.

---

# 357. Security alerts

Quando repo remoto público:

GitHub pode detectar dependências vulneráveis.

Ativar/usar conforme disponibilidade.

---

# 358. Dependência vulnerável

Fix em branch separada, testada.

---

# 359. Release commit

## PENDENTE

Pode haver commit específico de bump de versão antes de tag.

Definir no documento 12.

---

# 360. Tag signing

## FUTURO / RECOMENDADO

Tags podem ser assinadas.

Útil para releases públicos.

---

# 361. Verified badge

Ajuda autenticidade do portfólio.

Não é substituto de segurança do código.

---

# 362. Chave privada

Nunca no repo.

---

# 363. Revogação de chave

Se comprometida:

revogar/remover e configurar nova.

---

# 364. SSH auth key versus signing key

Pode ser a mesma chave cadastrada também como signing key, mas são usos distintos no GitHub.

A documentação GitHub explica essa possibilidade.

---

# 365. Commit author versus committer

Git distingue:

- autor;
- committer.

Em operações como cherry-pick/rebase podem diferir.

---

# 366. Rebase no GitHub e assinatura

GitHub observa que “Rebase and merge” cria commits modificados e novos SHAs, com implicações para verificação de assinatura.

Se assinatura for exigida no futuro, isso importa.

Fonte:

https://docs.github.com/en/authentication/managing-commit-signature-verification/about-commit-signature-verification

---

# 367. Self-hosted merge local

Se assinaturas forem obrigatórias e rebase for usado:

pode ser necessário integrar localmente de forma compatível.

Futuro.

---

# 368. Histórico e timestamps

Não manipular datas para estética.

---

# 369. Commit hash

Hash identifica conteúdo/histórico do commit.

Rebase/amend muda hash.

---

# 370. Tag aponta para objeto

Normalmente commit.

---

# 371. Branch é ponteiro móvel

Tag de release deve ser tratada como referência estável.

---

# 372. `HEAD~1` e `HEAD^`

Aprender gradualmente.

Não usar sintaxe relativa perigosa sem explicar.

---

# 373. `HEAD^`

Primeiro parent em commit normal.

Em merge, pais múltiplos tornam semântica mais importante.

---

# 374. `HEAD~N`

Percorre primeiros pais N vezes.

Útil, mas não decorar antes de entender grafo.

---

# 375. `main~3` não significa “três commits feitos hoje”

É relação no grafo.

---

# 376. Merge commit possui dois pais

Esse é o fundamento do grafo.

---

# 377. `--first-parent`

## FUTURO

Pode mostrar história de integrações da `main`.

Muito útil se usarmos `--no-ff`.

---

# 378. Benefício do merge commit para `--first-parent`

Cada feature integrada aparece como evento na linha principal.

---

# 379. Log proposto para roadmap/histórico

```bash
git log --first-parent --oneline main
```

quando houver histórico suficiente.

---

# 380. Bisect e merge commits

Pode ser mais complexo.

Ainda funcional, mas uma razão para commits dentro da feature serem bons.

---

# 381. Merge commit vazio

Não criar.

---

# 382. `--allow-empty`

## EVITAR

Só para casos especiais, como CI trigger deliberado.

---

# 383. Commit de “checkpoint” sem mudança

Não.

---

# 384. Metadata files

Não mudar automaticamente só para gerar commit.

---

# 385. Copyright year

Se entrar, não atualizar mecanicamente sem política.

---

# 386. Branch naming e issue IDs

Se futuramente issues forem usadas:

pode incluir ID.

Exemplo:

```text
feature/42-knight-movement
```

Não necessário agora.

---

# 387. Emoji em commit

## NÃO ADOTAR COMO PADRÃO

Pode ser simpático, mas Conventional Commits já fornece semântica.

Portfólio técnico ganha pouco com zoológico de emojis no log.

---

# 388. Commit scopes e modules

Quando engine module existir:

```text
engine
```

é bom scope.

---

# 389. UI scopes

```text
ui
game-ui
```

avaliar consistência.

---

# 390. Build scope

```text
gradle
```

pode ser útil.

---

# 391. Docs scope

Normalmente `docs:` sem scope é suficiente.

---

# 392. Commit de múltiplos módulos

Escopo pode ser omitido se mudança atravessa todos.

---

# 393. Commit description no presente imperativo

Exemplo:

```text
add
fix
prevent
extract
update
document
```

---

# 394. Evitar passado

```text
added
fixed
```

não é proibido pela especificação, mas manteremos estilo consistente.

---

# 395. Corpo em inglês

Se adotarmos commits em inglês:

corpo também.

---

# 396. Issue references

Usar quando issues existirem.

---

# 397. Closing keywords

GitHub pode fechar issue automaticamente com palavras específicas.

Usar conscientemente.

---

# 398. PR e issue

Quando feature nasce de issue:

linkar.

---

# 399. Não criar issue para cada microtarefa

Roadmap/documentos já cumprem parte desse papel.

---

# 400. Milestones GitHub

## FUTURO

Podem representar releases.

Não necessário agora.

---

# 401. Projects board

## FUTURO

Somente se melhorar gestão.

---

# 402. Git não substitui roadmap

Git diz:

> o que aconteceu.

Roadmap diz:

> o que pretendemos fazer.

---

# 403. Git não substitui documentação arquitetural

Commit mostra diff.

ADR explica decisão.

---

# 404. Commit message não deve conter segredos

Óbvio, mas humanos descobriram maneiras de errar até no campo de mensagem.

---

# 405. Branch name não deve conter segredo

Também aparece publicamente.

---

# 406. Remote URL não deve conter token

Reforço intencional.

---

# 407. Tag message não deve conter segredo

---

# 408. PR logs são públicos em repo público

Não colar dados sensíveis em comentários.

---

# 409. GitHub Actions logs

Futuro: secrets mascarados, mas não imprimir.

---

# 410. Screenshots em PR

Revisar conteúdo sensível.

---

# 411. Portfólio e transparência

PRs podem mostrar:

- raciocínio;
- testes;
- screenshots;
- evolução.

Boa prova de processo.

---

# 412. 🎥 MOMENTO BOM PARA GRAVAR — staging area

Mostrar arquivo com:

- parte staged;
- parte unstaged.

Excelente explicação de como Git realmente funciona.

---

# 413. 🎥 MOMENTO BOM PARA GRAVAR — `git add -p`

Mostrar separar refactor e bugfix do mesmo arquivo em commits diferentes.

---

# 414. 🎥 MOMENTO BOM PARA GRAVAR — revert versus reset

Mostrar diferença conceitual com commits locais de demonstração.

Não fazer em `main` real apenas por conteúdo.

---

# 415. 🎥 MOMENTO BOM PARA GRAVAR — force-with-lease

Somente quando situação real surgir.

Tema:

> por que `--force` pode apagar trabalho remoto.

---

# 416. COMO EXPLICAR EM ENTREVISTA — workflow

> “No Gambitol eu mantive a `main` estável e trabalhei com branches curtas por feature ou correção. Antes de cada commit eu revisava o diff staged e validava build e testes. Para mudanças relevantes, a integração era feita após revisão, preservando o contexto da feature no histórico.”

Usar apenas após workflow estar implementado.

---

# 417. COMO EXPLICAR EM ENTREVISTA — recuperação

> “Também usei Git como ferramenta de diagnóstico e recuperação, não só versionamento. Diferenciei `restore`, `reset` e `revert`, e usei reflog como mecanismo de recuperação quando o histórico local precisava ser reconstruído.”

Somente se realmente acontecer/praticar.

---

# 418. COMO EXPLICAR EM ENTREVISTA — Conventional Commits

> “As mensagens seguem Conventional Commits para manter o histórico legível e preparar automação futura de changelog e versionamento.”

Após adoção real.

---

# 419. Workflow padrão proposto para uma feature

```text
git status -sb
↓
main atualizada
↓
git switch -c feature/<nome>
↓
implementar
↓
./gradlew ...
↓
testes
↓
git diff
↓
git add ...
↓
git diff --cached
↓
APROVAÇÃO
↓
git commit
↓
APROVAÇÃO
↓
git push -u origin feature/<nome>
↓
PR/revisão quando aplicável
↓
merge aprovado
↓
build/testes em main
↓
push main aprovado
↓
delete branch
```

Os comandos exatos serão adaptados ao estado real.

---

# 420. Workflow padrão proposto para bugfix

Mesmo processo, branch:

```text
fix/<nome>
```

Idealmente:

1. reproduzir bug;
2. criar teste que falha;
3. corrigir;
4. teste passa;
5. commit.

---

# 421. Workflow de documentação

Para atualização isolada:

```text
docs/<nome>
```

se mudança merecer branch.

Para alteração pequena na mesma feature:

documentação pode acompanhar commit da feature.

---

# 422. Workflow de refactor

1. testes verdes;
2. branch;
3. refactor;
4. comportamento preservado;
5. testes verdes;
6. diff;
7. commit.

---

# 423. Workflow de dependência

1. motivo;
2. branch `build/` ou feature relacionada;
3. alterar version catalog/build;
4. sync;
5. build;
6. testes;
7. lint;
8. diff;
9. commit.

---

# 424. Workflow de release

Será definido no documento 12.

Em nível Git:

```text
main validada
↓
version bump
↓
release commit quando aplicável
↓
tag anotada
↓
push tag
↓
GitHub Release quando usado
```

---

# 425. Workflow de hotfix futuro

Quando houver release publicada:

```text
main/release base apropriada
↓
fix/<nome>
↓
teste
↓
merge
↓
nova patch version
↓
nova tag
```

Detalhes depois.

---

# 426. Workflow de interrupção urgente

Se feature em andamento não está pronta:

opção 1:

```text
stash com mensagem
```

opção 2:

commit WIP local consciente.

Nunca despejar WIP na `main`.

---

# 427. Workflow de recuperação

Antes de qualquer destruição:

```text
git status
git log
git reflog
```

e entender.

---

# 428. Workflow de conflito

```text
git status
↓
resolver um arquivo
↓
testar entendimento
↓
git add
↓
continuar
↓
build completo
```

---

# 429. Workflow de branch remota removida

Não panic-delete local.

Verificar:

- branch local;
- upstream;
- remoto;
- intenção.

---

# 430. Workflow de arquivo ignorado indevidamente

Usar:

```bash
git check-ignore -v caminho
```

para descobrir regra responsável.

---

# 431. `git check-ignore`

Excelente ferramenta de diagnóstico do `.gitignore`.

---

# 432. Workflow de line endings estranhos

Antes de renormalizar:

- `git diff`;
- `.gitattributes`;
- `git config`;
- identificar arquivos.

Não converter o repositório inteiro porque um arquivo apareceu modificado.

---

# 433. `core.autocrlf`

É configuração do Git, não propriedade do arquivo.

Pode variar entre máquinas.

Por isso `.gitattributes` pode ser melhor para regras do projeto.

---

# 434. `core.safecrlf`

Pode alertar/rejeitar conversões irreversíveis.

Avaliar se line endings virarem problema.

---

# 435. Workflow de arquivos gerados entrando no status

Perguntar:

> devem ser ignorados ou versionados?

Não adicionar automaticamente ao `.gitignore`.

Alguns gerados são essenciais, como Gradle Wrapper.

---

# 436. Workflow de diretório novo

Antes de `git add .`:

listar.

---

# 437. Workflow após Android Studio gerar arquivos

`git status -sb`.

Templates podem criar arquivos relevantes e caches irrelevantes.

---

# 438. Workflow após upgrade de Android Studio/AGP

Separar mudanças do projeto de arquivos de IDE.

---

# 439. Primeiro uso de um comando perigoso

## DECIDIDO COMO MÉTODO DE ENSINO

Tutor deve explicar:

- working tree;
- index;
- HEAD;
- remoto afetado;
- reversibilidade.

---

# 440. Comando seguro pode ter contexto perigoso

Exemplo:

```bash
git restore
```

é simples, mas pode apagar horas de alteração local.

Não classificar comandos apenas pelo nome.

---

# 441. Dry-run quando disponível

Usar em operações de risco.

Exemplos:

```text
git clean -n
git push --dry-run
```

quando apropriado.

---

# 442. `git push --dry-run`

Pode ajudar verificar refs sem atualizar remoto.

---

# 443. Tag delete

Não apagar tag pública casualmente.

---

# 444. Remote branch delete

Só após confirmar merge.

---

# 445. Local branch delete

`-d`.

---

# 446. Reflog antes de desespero

Boa regra humana.

---

# 447. Backup antes de reescrita complexa

Criar branch/tag temporária.

---

# 448. Nome de backup branch

Deve ser explícito e temporário.

Não manter lixo de branches para sempre.

---

# 449. Branch list

```bash
git branch
```

---

# 450. Todas branches

```bash
git branch -a
```

---

# 451. Merged branches

```bash
git branch --merged
```

pode ajudar antes de limpeza.

---

# 452. Branch remota stale

`fetch --prune` pode limpar referências remotas removidas.

Não executar sem explicar.

---

# 453. `git fetch --prune`

Não apaga branch local.

Remove remote-tracking refs que não existem mais no remoto.

---

# 454. Prune não é clean

Conceitos diferentes.

---

# 455. Branch tracking cleanup

Após excluir remote branch, upstream da local pode aparecer gone.

Então excluir local se integrada.

---

# 456. `git remote prune`

Alternativa específica.

Não necessária rotina.

---

# 457. Repository integrity

Git possui ferramentas como `git fsck`.

## FUTURO

Só para diagnóstico de corrupção/objetos.

---

# 458. `git gc`

Git faz manutenção automática.

Não rodar manualmente por hábito.

---

# 459. Shallow clone

## EVITAR PARA DESENVOLVIMENTO NORMAL

Histórico completo é útil.

---

# 460. Clone versus init

Projeto atual foi criado com `git init`.

No futuro, outra máquina usará clone.

---

# 461. Clone e Gradle

Após clone:

- `local.properties` será recriado/local;
- wrapper permitirá build;
- Android SDK/JDK precisam estar configurados.

---

# 462. Reprodutibilidade

Git + wrapper + documentação devem permitir reconstruir ambiente.

---

# 463. Submodules

## NÃO USAR AGORA

Adicionam complexidade.

Não há dependência que justifique.

---

# 464. Git subtree

## NÃO USAR AGORA

---

# 465. Monorepo

Gambitol é um repositório de um produto.

Múltiplos módulos Gradle não mudam isso.

---

# 466. Git branch não deve espelhar módulo

Não criar branch permanente:

```text
app
engine
```

por módulo.

Branches são por trabalho, não componente permanente.

---

# 467. Commit por módulo não é obrigatório

Mudança transversal pode tocar app + engine quando feature exige.

---

# 468. Commit coeso > commit por pasta

Essa regra é central.

---

# 469. Branch coesa > branch por arquivo

---

# 470. Release tag > branch eterna de versão

Enquanto não houver manutenção paralela.

---

# 471. History cleanup antes de PR

Permitido em branch privada.

---

# 472. History cleanup depois de review

Cuidado: rebase muda commits que reviewer já viu.

Se fizer:

informar.

---

# 473. Force push depois de review

Usar `--force-with-lease`, nunca `--force`, e apenas branch própria.

---

# 474. PR comments depois de force push

GitHub preserva contexto, mas diffs mudam.

---

# 475. Preferência do workflow inicial

## PROPOSTO

Enquanto o desenvolvedor ainda aprende Git:

- evitar rebase frequente;
- usar branches curtas;
- commits limpos;
- merge explícito.

Depois introduzir rebase interativo.

---

# 476. Objetivo pedagógico

Aprender primeiro:

```text
status
add
diff
commit
branch
switch
merge
push
pull/fetch
```

Depois:

```text
rebase
stash
reflog
bisect
cherry-pick
```

---

# 477. Não aprender Git por trauma

Comandos destrutivos entram depois do modelo mental.

---

# 478. Testar Git em branch descartável

Quando quisermos ensinar reset/rebase sem risco:

criar pequeno cenário seguro separado do trabalho principal.

---

# 479. Não usar projeto real para demonstração destrutiva desnecessária

Parece óbvio. A história da informática sugere que não é.

---

# 480. Documentar comandos ensinados

O documento 02 pode referenciar conceitos, mas este arquivo é a fonte operacional de Git.

---

# 481. Estado de maturidade do Git

Podemos considerar níveis:

### Básico

status/add/commit/diff/log.

### Branching

switch/branch/merge.

### Remoto

fetch/push/pull/upstream.

### Recovery

restore/reset/revert/reflog/stash.

### Avançado

rebase/bisect/cherry-pick.

---

# 482. Tutor deve identificar nível pelo uso real

Não pela lista decorada.

---

# 483. `git status` sempre pode ser pedido

Mesmo usuário experiente usa.

Não é comando de iniciante.

---

# 484. `git diff` é revisão

Não apenas debug.

---

# 485. `git log` é memória

---

# 486. `git reflog` é airbag local

Não é imortal, mas ajuda.

---

# 487. `git revert` é correção pública

---

# 488. `git reset` é cirurgia local

Metáfora útil, não definição formal.

---

# 489. `git stash` é armazenamento temporário

Não arquivo de longo prazo.

---

# 490. Branch é ponteiro

Não cópia completa de pasta.

---

# 491. Commit é snapshot

Não “diferença” isolada, embora Git mostre diffs entre commits.

---

# 492. Merge conecta histórias

---

# 493. Rebase reescreve base dos commits

---

# 494. Tag nomeia ponto estável

---

# 495. Remote é outro repositório

---

# 496. Upstream é relação de tracking

---

# 497. Origin é convenção, não palavra mágica

---

# 498. Main é convenção escolhida do projeto

Já decidida.

---

# 499. Política de idioma

## PROPOSTO

- branch names: inglês;
- commit messages: inglês;
- tags: neutras/sem idioma;
- docs: português;
- PR: pode evoluir para inglês se portfólio internacional justificar.

---

# 500. Política de branch

## PROPOSTO

- `main` estável;
- branches curtas;
- sem `develop`;
- sem force push `main`;
- apagar branch após integração;
- merge commits para features relevantes inicialmente.

---

# 501. Política de commits

## PROPOSTO

- Conventional Commits;
- unidade coesa;
- build/testes antes;
- aprovação antes;
- título conciso;
- sem WIP na `main`.

---

# 502. Política de recuperação

## DECIDIDO

- restore para working/index;
- reset somente com compreensão;
- revert para histórico publicado;
- reflog antes de assumir perda;
- comandos destrutivos exigem confirmação.

---

# 503. Política de remoto

## DECIDIDO COMO PRINCÍPIO

- push consciente;
- sem credencial em URL;
- verificar branch;
- `--force` proibido em `main`;
- `--force-with-lease` somente caso especial.

---

# 504. Política de releases

## PROPOSTO

- tags anotadas;
- `vX.Y.Z`;
- tag imutável;
- release validada antes da tag.

---

# 505. Pontos pendentes

## PENDENTE DE APROVAÇÃO

1. Prefixos exatos de branch.
2. Branch names em inglês.
3. Conventional Commits como norma oficial.
4. Commit messages em inglês.
5. Merge commit/`--no-ff` como integração padrão.
6. Uso rotineiro de PR em projeto solo.
7. Branch protection/rulesets.
8. Commit signing.
9. `.gitattributes`.
10. SemVer para tags.
11. PR template.
12. CI checks obrigatórios.

---

# 506. Decisões já obrigatórias

## DECIDIDO

1. branch principal é `main`;
2. Git Bash é ferramenta principal;
3. não commit antes de aprovação;
4. não push automático;
5. `git status -sb` deve ser usado como checkpoint;
6. diff deve ser revisado;
7. secrets não entram no Git;
8. build outputs não entram;
9. engine/app changes precisam de validação;
10. comandos destrutivos não são enviados casualmente.

---

# 507. Fontes pesquisadas — Git status e index

## git-status

https://git-scm.com/docs/git-status

Base para:

- working tree;
- index;
- short format;
- branch tracking;
- untracked.

Verificado em: 2026-08-22.

---

## Git data model — index

https://git-scm.com/docs/gitdatamodel

Base para:

- staging area;
- index;
- conteúdo preparado;
- snapshots.

Verificado em: 2026-08-22.

---

# 508. Fontes — staging e commits

## git-add

https://git-scm.com/docs/git-add

Base para:

- staging;
- partial staging;
- `git add -p`;
- conteúdo staged no momento do add.

---

## git-commit

https://git-scm.com/docs/git-commit

Base para:

- conteúdo commitado;
- patch/interactive;
- amend;
- dry run.

Verificado em: 2026-08-22.

---

# 509. Fontes — branching e GitHub Flow

## GitHub Flow

https://docs.github.com/en/get-started/using-github/github-flow

Base para:

- branch por mudança;
- commits;
- push;
- pull request;
- merge;
- branch deletion.

Verificado em: 2026-08-22.

---

# 510. Fontes — merge

## git-merge

https://git-scm.com/docs/git-merge

Base para:

- merge;
- fast-forward;
- `--no-ff`;
- `--ff-only`;
- conflitos;
- abort;
- working tree antes de merge.

---

## GitHub — Merging a pull request

https://docs.github.com/en/pull-requests/how-tos/merge-and-close-pull-requests/merging-a-pull-request

Base para:

- merge commit;
- squash;
- rebase merge;
- status checks;
- PR requirements.

Verificado em: 2026-08-22.

---

# 511. Fontes — rebase e force push

## git-rebase

https://git-scm.com/docs/git-rebase

Base para:

- reaplicação;
- reescrita;
- interactive rebase;
- abort/continue;
- reflog após rebase.

---

## git-push

https://git-scm.com/docs/git-push

Base para:

- upstream;
- push;
- `--force`;
- `--force-with-lease`;
- riscos de sobrescrever remoto.

Verificado em: 2026-08-22.

---

# 512. Fontes — recovery

## git-restore

https://git-scm.com/docs/git-restore

Base para:

- restaurar working tree;
- unstage;
- patch restore.

---

## git-reset

https://git-scm.com/docs/git-reset

Base para:

- soft;
- mixed;
- hard;
- efeitos em HEAD/index/working tree.

---

## git-reflog

https://git-scm.com/docs/git-reflog

Base para:

- histórico local de refs;
- recuperação.

---

## git-stash

https://git-scm.com/docs/git-stash

Base para:

- armazenar trabalho temporário;
- list/show/apply/pop.

Verificado em: 2026-08-22.

---

# 513. Fontes — ignore e line endings

## gitignore

https://git-scm.com/docs/gitignore

Base para:

- arquivos untracked ignorados;
- precedência;
- tracked files não são afetados.

---

## Git FAQ

https://git-scm.com/docs/gitfaq

Base para:

- line endings;
- `.gitattributes`;
- texto versus binário;
- shell LF/batch CRLF.

---

## git-config

https://git-scm.com/docs/git-config

Base para:

- `core.autocrlf`;
- `core.eol`;
- `core.safecrlf`.

Verificado em: 2026-08-22.

---

# 514. Fontes — tags

## git-tag

https://git-scm.com/docs/git-tag

Base para:

- lightweight;
- annotated;
- signed;
- tags anotadas para releases;
- mensagens.

Verificado em: 2026-08-22.

---

# 515. Fontes — Conventional Commits

## Conventional Commits 1.0.0

https://www.conventionalcommits.org/pt-br/v1.0.0/

Base para:

- `type(scope): description`;
- `feat`;
- `fix`;
- breaking changes;
- tipos adicionais;
- automação.

Verificado em: 2026-08-22.

---

# 516. Fontes — GitHub branch protection

## About protected branches

https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/managing-protected-branches/about-protected-branches

Base para:

- impedir force push/delete;
- reviews;
- status checks;
- signed commits;
- linear history;
- protections.

Verificado em: 2026-08-22.

---

# 517. Fontes — commit signing

## About commit signature verification

https://docs.github.com/en/authentication/managing-commit-signature-verification/about-commit-signature-verification

Base para:

- GPG;
- SSH;
- S/MIME;
- Verified;
- assinatura versus sign-off;
- implicações de rebase/merge.

Verificado em: 2026-08-22.

---

# 518. Regra sobre versões das fontes

Git e GitHub evoluem.

Antes de configurar:

- branch protection;
- signed commits;
- rulesets;
- merge methods;
- hooks;
- autenticação;

consultar documentação atual.

Os conceitos fundamentais deste documento são mais estáveis que a interface do GitHub.

---

# 519. Resumo operacional

O workflow desejado é:

```text
STATUS
↓
BRANCH
↓
IMPLEMENTAÇÃO
↓
BUILD + TESTES
↓
DIFF
↓
STAGING
↓
DIFF STAGED
↓
APROVAÇÃO
↓
COMMIT
↓
PUSH
↓
PR/REVISÃO
↓
MERGE
↓
VALIDAÇÃO
↓
TAG QUANDO FOR RELEASE
```

---

# 520. Regra norteadora

> **Nunca use Git para esconder o que aconteceu. Use Git para tornar a evolução do Gambitol compreensível, verificável e recuperável.**

---

# 521. Próximo documento

Após aprovação:

`08_TESTES_E_QUALIDADE.md`

Ele deverá definir:

- estratégia de testes;
- pirâmide;
- testes JVM;
- instrumentados;
- UI;
- AAA;
- unit tests;
- integration tests;
- regressão;
- coverage;
- mutation testing quando útil;
- Perft;
- Lint;
- build gates;
- quality gates;
- device matrix;
- pre-launch report;
- definição de pronto.

O documento 07 define:

> **como registramos e integramos mudanças.**

O documento 08 definirá:

> **como provamos que essas mudanças continuam corretas.**
