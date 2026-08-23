# 15 — TROUBLESHOOTING DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `15_TROUBLESHOOTING.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir como investigar, reproduzir, diagnosticar, corrigir e registrar falhas no Gambitol com evidência, reduzindo tentativas aleatórias, “soluções mágicas”, perda de contexto e regressões  
> **Fonte normativa para:** método de diagnóstico, coleta de evidência, triagem, JDK, Gradle, Android Studio, ADB, Emulator, dispositivo físico, build, dependências, Manifest, resources, Java, testes, motor de xadrez, UI, lifecycle, performance, ANR, crashes, Git, release, R8, assinatura, Play Console e futuras integrações de Billing  
> **Não cobre em detalhe:** arquitetura completa, regras FIDE, estratégia de testes inteira, fluxo Git completo, UI/UX completa, publicação inteira ou monetização completa; este documento aponta para os documentos especializados quando necessário  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `02_METODO_DE_ENSINO.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `04_ESTRUTURA_DO_PROJETO.md`, `05_REGRAS_DO_MOTOR_DE_XADREZ.md`, `06_PADROES_JAVA_E_ANDROID.md`, `07_GIT_WORKFLOW.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`, `10_ROADMAP_E_ESCOPO.md`, `11_DECISOES_TECNICAS.md`, `12_PLAY_STORE_E_RELEASE.md`, `13_MONETIZACAO.md`, `14_CONTEUDO_E_PORTFOLIO.md`  
> **Plataforma:** Android  
> **Linguagem principal:** Java  
> **Build:** Gradle com Kotlin DSL  
> **Application ID:** `br.com.raionorio.gambitol`  
> **minSdk atual:** API 24  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

Software falha.

Isso não é sinal de que o projeto “deu errado”. É o estado natural de qualquer sistema suficientemente interessante para ultrapassar um `Hello World`.

O problema real começa quando o diagnóstico vira:

```text
deu erro
↓
limpar cache
↓
reiniciar tudo
↓
trocar três versões
↓
apagar pasta
↓
funcionou
↓
ninguém sabe por quê
```

Este documento existe para substituir superstição por investigação.

A regra central é:

> **Primeiro preserve a evidência. Depois formule uma hipótese. Só então altere o sistema.**

---

# 2. Troubleshooting não é “tentar coisas”

## DECIDIDO

Diagnóstico do Gambitol segue:

```text
OBSERVAR
↓
REPRODUZIR
↓
ISOLAR
↓
LER A EVIDÊNCIA
↓
FORMULAR HIPÓTESE
↓
TESTAR UMA HIPÓTESE
↓
CORRIGIR
↓
VALIDAR
↓
CRIAR REGRESSÃO QUANDO CABÍVEL
↓
REGISTRAR
```

---

# 3. Sintoma ≠ causa

Exemplo:

```text
SINTOMA:
app fecha ao abrir.

CAUSA POSSÍVEL:
NullPointerException durante onCreate.

OUTRA CAUSA POSSÍVEL:
resource ausente.

OUTRA:
manifest/configuração incorreta.
```

Não resolver pelo sintoma.

---

# 4. Erro principal ≠ todas as linhas vermelhas

Builds Android frequentemente produzem centenas de linhas depois de uma falha.

Nosso trabalho é encontrar:

1. primeiro erro relevante;
2. causa raiz;
3. cadeia de “caused by”;
4. arquivo/linha;
5. task que falhou.

---

# 5. Não começar pelo final do stack trace automaticamente

Stack traces têm estrutura.

Em Java/Android:

- exceção superior mostra o que chegou ao chamador;
- `Caused by` pode apontar causa mais profunda;
- primeira linha do nosso código costuma ser ponto inicial de investigação.

---

# 6. Protocolo mínimo de diagnóstico

Antes de alterar qualquer coisa, responder:

```text
O QUE EU FIZ?
O QUE ESPERAVA?
O QUE ACONTECEU?
QUAL A MENSAGEM EXATA?
É REPRODUZÍVEL?
EM QUAL AMBIENTE?
QUAL FOI A ÚLTIMA MUDANÇA RELEVANTE?
```

---

# 7. Preservar mensagem exata

## DECIDIDO

Não resumir:

> “deu erro no Gradle”.

Guardar:

- task;
- exception;
- trecho principal;
- stack trace quando útil.

---

# 8. Screenshot versus texto

Screenshot pode ajudar.

Mas texto copiável é melhor para:

- busca;
- diff;
- análise;
- documentação.

---

# 9. Não cortar justamente o topo do erro

É um talento estranhamente difundido.

Capturar contexto suficiente.

---

# 10. Reproduzir antes de corrigir

## DECIDIDO

Se não sabemos como reproduzir:

não sabemos se a correção funcionou.

---

# 11. Reprodução mínima

Ideal:

```text
1. abrir app
2. tocar em X
3. selecionar Y
4. crash
```

---

# 12. Reproduzir duas vezes quando viável

Evita corrigir evento acidental.

---

# 13. Reproduzir em estado limpo quando necessário

Exemplo:

- app recém-instalado;
- dados preservados;
- dados limpos;
- rotação;
- background/resume.

Cada um testa hipótese diferente.

---

# 14. Não apagar dados antes de testar a hipótese

Se o bug depende de dados existentes:

`Clear storage` pode destruir a evidência.

---

# 15. Reduzir variáveis

Mudar uma coisa por vez.

---

# 16. Anti-pattern

```text
trocar JDK
+ atualizar Gradle
+ atualizar AGP
+ limpar caches
+ recriar AVD
```

e então declarar:

> “o JDK estava errado”.

Não sabemos.

---

# 17. Hipótese

Uma boa hipótese é específica e testável.

Ruim:

> Android Studio bugou.

Bom:

> O build no terminal usa um JDK diferente do Gradle JDK configurado no Android Studio.

---

# 18. Evidência antes da hipótese

Exemplo:

```bash
java -version
echo "$JAVA_HOME"
./gradlew --version
```

No Windows/Git Bash, adaptar conforme shell.

---

# 19. Diagnóstico é experimento

Estrutura:

```text
HIPÓTESE:
X causa Y.

TESTE:
mudar apenas X.

RESULTADO:
Y desaparece / permanece.

CONCLUSÃO:
hipótese suportada / rejeitada.
```

---

# 20. Correlação não basta

Se reiniciar PC resolveu:

não sabemos necessariamente a causa.

Pode ter:

- liberado lock;
- reiniciado ADB;
- reiniciado daemon;
- liberado memória;
- reiniciado hipervisor.

Registrar como mitigação, não causa confirmada.

---

# 21. Mitigação versus correção

## CONCEITO IMPORTANTE

Mitigação:

```text
reiniciar ADB
```

Correção:

```text
resolver driver/cabo/configuração que fazia o device cair.
```

---

# 22. Workaround versus solução

Workaround:

contorna.

Solução:

remove causa.

---

# 23. “Funcionou” não encerra diagnóstico crítico

Se bug grave:

entender o suficiente para:

- evitar retorno;
- criar teste;
- documentar.

---

# 24. Critérios para parar investigação

Podemos parar quando:

- causa identificada;
- correção aplicada;
- reprodução falha após correção;
- testes/regressão verdes;
- efeito colateral revisado.

Ou:

- workaround aprovado;
- causa externa conhecida;
- risco documentado.

---

# 25. Template de incidente

## PROPOSTO

```text
SINTOMA:
AMBIENTE:
PASSOS:
ESPERADO:
ATUAL:
MENSAGEM:
ÚLTIMA MUDANÇA:
EVIDÊNCIA:
HIPÓTESE:
TESTE:
RESULTADO:
CAUSA:
CORREÇÃO:
REGRESSÃO:
STATUS:
```

Não precisa virar arquivo para toda falha pequena.

---

# 26. Severidade

Usar severidade do documento 08.

Conceitualmente:

- P0 — impede produto/release ou corrompe dados/regra crítica;
- P1 — feature principal gravemente quebrada;
- P2 — problema relevante com workaround;
- P3 — problema menor/polimento.

---

# 27. Prioridade ≠ dificuldade

Bug fácil pode ser P0.

Bug difícil pode ser P3.

---

# 28. Primeiro eixo de triagem

Perguntar:

```text
O PROBLEMA É:
AMBIENTE?
BUILD?
EXECUÇÃO?
DOMÍNIO?
UI?
TESTE?
GIT?
RELEASE?
```

---

# 29. Segundo eixo

```text
LOCAL?
REPRODUZ EM OUTRA MÁQUINA?
REPRODUZ NO CI?
REPRODUZ EM DEVICE?
REPRODUZ EM EMULATOR?
```

---

# 30. Terceiro eixo

```text
COMEÇOU APÓS QUAL MUDANÇA?
```

---

# 31. Baseline conhecido

O projeto deve manter um estado que sabemos que funciona.

Git ajuda.

---

# 32. Último commit bom

Quando regressão aparece:

identificar.

---

# 33. Git bisect

O Git fornece `git bisect` para usar pesquisa binária e localizar o commit que introduziu uma propriedade/bug.

Uso futuro:

```bash
git bisect start
git bisect bad
git bisect good <commit-bom>
```

Depois marcar estados até encontrar o culpado.

Fonte:

https://git-scm.com/docs/git-bisect

---

# 34. Não usar bisect para bug que já sabemos qual diff causou

Ferramenta existe para reduzir espaço de busca.

---

# 35. Bisect automatizado

Quando existe teste determinístico:

```bash
git bisect run <comando>
```

pode automatizar.

---

# 36. Reflog

`git reflog` registra movimentos locais de refs/HEAD.

Pode salvar trabalho após:

- reset errado;
- checkout;
- rebase;
- branch deletada localmente.

Fonte:

https://git-scm.com/docs/git-reflog

---

# 37. Reflog não é backup remoto

É histórico local.

---

# 38. Git fsck

`git fsck` verifica conectividade e validade dos objetos do repositório.

Fonte:

https://git-scm.com/docs/git-fsck

---

# 39. `git fsck` não é primeiro comando para conflito comum

Use quando houver suspeita de:

- corrupção;
- objeto ausente;
- inconsistência grave.

---

# 40. Antes de qualquer comando Git destrutivo

## DECIDIDO

Rodar:

```bash
git status
git branch --show-current
git log --oneline --decorate -n 10
```

Quando necessário.

---

# 41. Não usar `reset --hard` por impulso

Pode apagar trabalho não commitado.

---

# 42. Não usar `clean -fd` por impulso

Pode apagar arquivos não rastreados.

---

# 43. Antes de recuperar

Entender:

- tracked;
- staged;
- committed;
- pushed;
- untracked.

---

# 44. Erro de ambiente: JDK

O Android distingue várias coisas que humanos generosamente chamam apenas de “Java”:

- JDK que executa Android Studio;
- JDK que executa Gradle;
- Java toolchain;
- `sourceCompatibility`;
- `targetCompatibility`;
- APIs disponíveis no Android SDK.

---

# 45. JDK do Android Studio ≠ JDK do Gradle

## CRÍTICO

A documentação atual do Android diz:

- build iniciado pela interface do Android Studio usa o Gradle JDK configurado no IDE;
- Gradle iniciado pelo terminal usa `JAVA_HOME`, se definido;
- se `JAVA_HOME` não existir, usa `java` do `PATH`.

Fonte:

https://developer.android.com/build/jdks

---

# 46. Sintoma clássico

```text
Android Studio build passa
./gradlew build falha
```

ou o contrário.

---

# 47. Primeira hipótese

JDK diferente.

---

# 48. Evidência

No terminal:

```bash
java -version
javac -version
./gradlew --version
```

Também:

```bash
which java
```

no Git Bash.

---

# 49. Android Studio

Verificar:

```text
Settings
→ Build, Execution, Deployment
→ Build Tools
→ Gradle
→ Gradle JDK
```

conforme versão atual da IDE.

---

# 50. Alinhar JDKs

A documentação Android recomenda alinhar `JAVA_HOME` e Gradle JDK para resultados consistentes.

---

# 51. AGP e JDK

AGP impõe versão mínima de JDK.

Exemplo documentado:

```text
AGP 8.x requer JDK 17
```

Versões futuras podem mudar.

---

# 52. Não resolver mensagem “requires Java X” atualizando Android Studio aleatoriamente

Ler a versão real do AGP e JDK.

---

# 53. JDK 21 do projeto

Se usarmos Java 21 em alguma parte:

precisamos distinguir:

- JDK usado para executar Gradle;
- linguagem/bytecode da source;
- compatibilidade Android;
- toolchain.

Não assumir que “JDK 21 instalado” significa “tudo deve compilar como Java 21”.

---

# 54. Toolchain

A documentação atual recomenda especificar explicitamente Java toolchain para builds consistentes.

Quando adotada:

registrar decisão.

---

# 55. `sourceCompatibility`

Controla recursos da linguagem Java disponíveis para compilação.

---

# 56. `targetCompatibility`

Controla formato de bytecode/target Java.

---

# 57. `compileSdk`

Restringe APIs Android/Java disponíveis no SDK.

---

# 58. `minSdk`

Define piso de Android suportado.

---

# 59. Erro de API não disponível no minSdk

Pode aparecer em Lint/build.

Não “resolver” aumentando minSdk sem decisão.

Alternativas:

- API compatível;
- check de versão;
- desugaring quando aplicável;
- implementação alternativa.

---

# 60. Desugaring

Pode disponibilizar recursos/APIs Java modernas em Android antigo, dentro do conjunto suportado.

Não supor que qualquer API Java moderna funciona por desugaring.

---

# 61. JDK mismatch checklist

- [ ] `java -version`;
- [ ] `javac -version`;
- [ ] `./gradlew --version`;
- [ ] `JAVA_HOME`;
- [ ] `PATH`;
- [ ] Gradle JDK no IDE;
- [ ] AGP version;
- [ ] Gradle version;
- [ ] toolchain;
- [ ] source/target compatibility.

---

# 62. Gradle Wrapper

## DECIDIDO

Usar:

```bash
./gradlew
```

não Gradle global como fonte principal.

---

# 63. “gradle command not found”

Se usamos wrapper:

não é necessário instalar Gradle globalmente.

---

# 64. “JAVA_HOME is set to an invalid directory”

Gradle documenta esse erro explicitamente.

Verificar:

- caminho existe;
- é JDK;
- não tem aspas malformadas;
- shell recebeu atualização;
- variável está correta.

Fonte:

https://docs.gradle.org/current/userguide/troubleshooting.html

---

# 65. `./gradlew help`

Ferramenta útil.

Gradle documenta que `help` executa a fase de configuração sem rodar tasks de build.

Se `help` falhar:

há forte sinal de problema na configuração/build scripts.

---

# 66. `./gradlew tasks`

Ajuda a descobrir tasks reais.

---

# 67. Não inventar task

Verificar.

---

# 68. Build com mais evidência

Quando necessário:

```bash
./gradlew <task> --stacktrace
```

---

# 69. `--info`

Mais logs.

---

# 70. `--debug`

Muito verboso.

Usar quando necessário, não por padrão.

---

# 71. `--scan`

Pode gerar Build Scan dependendo da configuração/consentimento.

Avaliar privacidade antes de compartilhar.

---

# 72. Não colar `--debug` inteiro sem filtrar

Pode ter milhares de linhas.

---

# 73. Gradle daemon

Gradle usa daemon para reutilizar JVM e acelerar builds.

JDKs/Gradles diferentes podem criar daemons diferentes.

---

# 74. `./gradlew --stop`

## FERRAMENTA DE DIAGNÓSTICO

Pode ajudar quando há suspeita de daemon travado/estado ruim.

Não deve ser ritual depois de todo erro.

---

# 75. Reiniciar daemon não corrige build script inválido

---

# 76. Cache Gradle

Cache existe para acelerar/reutilizar artefatos.

Deletar cache é último recurso relativo, não primeiro.

---

# 77. Antes de limpar cache

Perguntar:

- erro indica cache corrompido?
- checksum/artifact inconsistente?
- download parcial?
- build limpo reproduz?
- outra máquina reproduz?

---

# 78. `clean`

`clean` apaga outputs de build do projeto.

Não é equivalente a apagar caches globais.

---

# 79. `clean` não corrige lógica

Se `clean` resolve consistentemente um bug incremental:

isso é evidência de problema de build incremental/configuração.

Investigar.

---

# 80. Build Analyzer

Android Studio oferece Build Analyzer para investigar performance/configuração do build.

Não usar para erro lógico do xadrez.

---

# 81. Gradle sync

Sync importa/configura modelo do projeto no IDE.

Erro de sync pode vir de:

- build script;
- plugin;
- dependency;
- JDK;
- network;
- repositories.

---

# 82. “Sync failed” é sintoma

Ler erro específico.

---

# 83. AGP known issues

Android mantém página atual de problemas conhecidos do Android Studio/AGP.

Antes de desmontar o projeto por um bug de tooling:

consultar.

Fonte:

https://developer.android.com/studio/known-issues

---

# 84. Não migrar para Canary como primeira correção

Pré-release pode conter fix.

Também pode conter novos bugs.

Só usar quando issue conhecida justificar.

---

# 85. Android Studio “Invalidate Caches”

## PRINCÍPIO

Usar apenas quando houver evidência de índice/cache da IDE inconsistente.

Não para:

- erro Java real;
- teste falhando;
- Gradle dependency conflict;
- Manifest inválido.

---

# 86. Sintomas que podem sugerir índice da IDE

Exemplo:

- código compila no Gradle;
- IDE marca símbolo existente como inexistente;
- navigation/indexing claramente inconsistente.

Mesmo assim, primeiro:

- sync;
- verificar build real;
- verificar known issues.

---

# 87. IDE vermelho, Gradle verde

Distinguir:

```text
IDE ANALYSIS
versus
BUILD REAL
```

---

# 88. Build verde, IDE vermelho

Problema pode ser:

- sync;
- index;
- plugin/IDE;
- JDK da IDE.

---

# 89. IDE verde, build vermelho

Confiar no build.

---

# 90. Android Studio logs

Para problemas da IDE, logs internos podem ser necessários.

Usar Help/Show Log conforme versão.

---

# 91. Report bug

Se problema reproduzível de tooling e não é nosso projeto:

usar tracker oficial com dados mínimos.

---

# 92. Dependências

Erros comuns:

- duplicate class;
- version conflict;
- artifact unavailable;
- repository missing;
- transitive dependency inesperada.

---

# 93. Não adicionar `exclude` no escuro

Primeiro descobrir por que a dependência está no grafo.

---

# 94. Árvore de dependências

Android documenta:

```bash
./gradlew app:dependencies
```

como forma de inspecionar grafo.

Nome do módulo deve ser confirmado.

Fonte:

https://developer.android.com/build/gradle-dependency-resolution

---

# 95. `dependencyInsight`

Gradle também oferece análise específica de uma dependência.

Usar quando necessário.

---

# 96. Duplicate class

Primeiro descobrir:

- quais dois artefatos fornecem a classe;
- se um é transitive;
- se existe local + remoto.

---

# 97. Não forçar versão maior automaticamente

Gradle pode resolver para versão mais alta por padrão, mas mudança major pode quebrar runtime.

---

# 98. Dependência dinâmica

Android recomenda evitar versões como:

```text
3.+
```

pois podem atualizar inesperadamente.

---

# 99. Dependency verification

Android/Gradle oferecem verificação por checksum/assinatura de dependências.

## FUTURO / CANDIDATO

Pode ser adotada se o projeto/CI justificar.

Fonte:

https://developer.android.com/build/dependency-verification

---

# 100. Erro de rede ao baixar dependência

Separar:

- DNS;
- proxy;
- firewall;
- repositório fora;
- coordenada errada;
- versão inexistente.

---

# 101. Offline mode

Se ativado por engano:

dependency nova não baixa.

---

# 102. Proxy corporativo

Pode causar TLS/repository failures.

Não relaxar segurança SSL permanentemente.

---

# 103. Certificado

Não usar `trustAll` para “resolver” Gradle.

---

# 104. Repositórios

Confirmar:

- `google()`;
- `mavenCentral()`;
- outros somente quando necessários.

---

# 105. Manifest merge

Um AAB/APK final tem um Manifest mesclado de:

- app;
- variants;
- libraries.

Fonte:

https://developer.android.com/build/manage-manifests

---

# 106. Erro de Manifest pode vir de dependência

Não olhar apenas `src/main/AndroidManifest.xml`.

---

# 107. Merged Manifest

Android Studio fornece view de Manifest mesclado.

Usar para:

- origem de atributo;
- permission inesperada;
- conflict;
- exported.

---

# 108. `tools:replace`

Não adicionar por reflexo.

Primeiro entender por que os dois manifests discordam.

---

# 109. `tools:node="remove"`

Mesma cautela.

---

# 110. Permission inesperada

Inspecionar Manifest mesclado.

Pode vir de SDK transitivo.

---

# 111. `android:exported`

Erros podem aparecer com intent filters em targets modernos.

Configurar conscientemente.

---

# 112. Manifest package/applicationId

Não corrigir pacote mexendo aleatoriamente no manifest.

Entender namespace/applicationId/package conforme doc04.

---

# 113. Resources

Erros comuns:

- resource not found;
- duplicate resources;
- invalid XML;
- atributo inexistente;
- type mismatch;
- nome inválido;
- resource linking failed.

---

# 114. `AAPT2`

Muitos erros de resources vêm do Android resource compiler/linker.

A linha relevante costuma apontar:

- arquivo;
- linha;
- resource.

---

# 115. Não apagar `R`

`R` é gerado.

Se `R` “sumiu”, procurar erro de resource/build anterior.

---

# 116. Importar `android.R`

## ARMADILHA COMUM

Pode acontecer por auto-import.

Então referências apontam para resources do framework em vez do app.

Verificar imports.

---

# 117. `R` não gerado

Causas comuns:

- XML inválido;
- Manifest error;
- resource name inválido;
- build falhou antes.

---

# 118. XML parser error

Corrigir XML.

Não limpar cache.

---

# 119. Resource name

Seguir convenções Android.

---

# 120. `@string/...` inexistente

Criar/corrigir resource, não hardcode silenciosamente.

---

# 121. Tema/atributo

Se atributo Material/AndroidX não existe:

verificar dependência/theme correto.

---

# 122. VectorDrawable

Erro pode vir de pathData/XML.

---

# 123. Drawable density

Problema visual não necessariamente erro de build.

---

# 124. Java compile errors

Categorias:

- symbol not found;
- incompatible types;
- method not applicable;
- access modifier;
- checked exception;
- duplicate class;
- package missing.

---

# 125. “cannot find symbol”

Perguntar:

- classe existe?
- package correto?
- import correto?
- nome mudou?
- source set correto?
- módulo tem dependência?
- arquivo compilado nesta variant?

---

# 126. Não criar import até entender o símbolo

IDE pode sugerir classe errada.

---

# 127. Incompatible types

Ler tipos reais.

Não resolver com cast cego.

---

# 128. Cast

Pode apenas mover erro para runtime.

---

# 129. NullPointerException

Não corrigir com:

```java
if (x != null)
```

automaticamente.

Perguntar:

> x deveria poder ser null?

Se não:

corrigir origem/invariante.

---

# 130. IllegalArgumentException

Indica contrato de argumento inválido.

---

# 131. IllegalStateException

Indica estado incompatível com operação.

Boa para invariantes, mas precisa mensagem útil.

---

# 132. IndexOutOfBounds

No Gambitol:

pode indicar:

- mapping errado;
- file/rank fora;
- array indexing;
- board orientation bug.

Criar teste de borda.

---

# 133. StackOverflowError

Pode indicar recursão sem base/ciclo.

Não confundir com Stack Overflow website, embora ambos causem sofrimento.

---

# 134. OutOfMemoryError

Investigar:

- leak;
- assets enormes;
- loop/alocação;
- emulator/host.

Não aumentar heap do app como primeira resposta.

---

# 135. Runtime crash Android

Logcat normalmente mostra stack trace associado.

Fonte:

https://developer.android.com/studio/debug/logcat

---

# 136. Procedimento de crash

1. reproduzir;
2. capturar Logcat;
3. localizar `FATAL EXCEPTION`;
4. localizar exception;
5. seguir `Caused by`;
6. achar primeira linha nossa;
7. inspecionar estado;
8. criar teste se possível.

---

# 137. Filtrar Logcat

Usar:

- package;
- PID;
- tag;
- priority.

---

# 138. `adb logcat`

Pode capturar via terminal.

---

# 139. Buffers relevantes

Android documenta buffers como:

- `main`;
- `system`;
- `crash`.

---

# 140. Não compartilhar log inteiro sem revisar

Pode conter:

- dados;
- apps;
- IDs.

---

# 141. Logging do Gambitol

Logs devem ser:

- úteis;
- sem segredo;
- sem informação sensível;
- removidos/condicionados em release quando debug-only.

---

# 142. Não logar posição a cada frame

Sem necessidade.

---

# 143. Log de regra

Durante diagnóstico:

pode imprimir:

- move;
- side;
- check state;
- castling rights;
- en passant state.

Depois reduzir.

---

# 144. Debugger

Android Studio permite breakpoints em Java e inspeção de variáveis.

Fonte:

https://developer.android.com/studio/debug

---

# 145. Quando debugger é melhor que log

- estado complexo;
- sequência curta;
- breakpoint reproduzível;
- verificar valor antes/depois.

---

# 146. Quando log é melhor

- lifecycle;
- eventos assíncronos;
- device remoto;
- sequência longa;
- timing.

---

# 147. Não escolher ferramenta por hábito

---

# 148. Conditional breakpoint

Útil para bug que ocorre em uma casa/move específico.

---

# 149. Watch expression

Útil.

---

# 150. Não “fixar” bug no debugger e esquecer código

---

# 151. ADB

`adb` é cliente-servidor:

- client;
- server no host;
- daemon no device.

Fonte:

https://developer.android.com/tools/adb

---

# 152. Device não aparece

Primeiro:

```bash
adb devices
```

---

# 153. Estados comuns

- `device`;
- `offline`;
- `unauthorized`.

---

# 154. Unauthorized

Olhar device:

prompt de autorização USB/RSA.

---

# 155. Revogar autorizações

Pode ser necessário quando trust ficou inconsistente.

---

# 156. ADB server

Pode reiniciar:

```bash
adb kill-server
adb start-server
```

ou usar Connection Assistant.

---

# 157. Reiniciar ADB é diagnóstico legítimo

Quando problema é conexão ADB.

Não quando torre move diagonalmente.

---

# 158. Connection Assistant

Android Studio oferece:

```text
Tools → Troubleshoot Device Connections
```

e inclui restart/rescan.

Fonte:

https://developer.android.com/studio/run/device

---

# 159. Cabo USB

Fonte oficial recomenda testar cabo/dispositivo alternativo para isolar.

---

# 160. Driver Windows

Alguns fabricantes exigem driver USB.

Consultar OEM/Android docs.

---

# 161. Modo USB

Cabo pode ser apenas carga.

---

# 162. Wireless debugging

Android 11+ suporta pairing via Wi‑Fi.

Problemas podem envolver:

- mesma rede;
- mDNS;
- firewall;
- pairing expirado.

---

# 163. Mais de um device

ADB retorna:

```text
more than one device/emulator
```

Usar `-s`, `-d` ou `-e`.

---

# 164. Install failure

Capturar mensagem exata.

Exemplos:

- version downgrade;
- signature mismatch;
- insufficient storage;
- incompatible SDK.

---

# 165. Signature mismatch

Se app instalado tem outra assinatura:

não pode atualizar com assinatura diferente.

Para debug local, desinstalar pode resolver, mas apaga dados.

Antes, decidir se dados importam.

---

# 166. Version downgrade

APK com versionCode menor pode ser recusado.

---

# 167. Emulator

Android mantém troubleshooting oficial atualizado.

Fonte:

https://developer.android.com/studio/run/emulator-troubleshooting

---

# 168. Emulator não inicia

Checklist:

- espaço em disco;
- RAM;
- virtualization;
- hypervisor;
- image/AVD;
- GPU;
- versão do Emulator;
- known issues.

---

# 169. Espaço mínimo

A página atual informa que o Emulator pode recusar início quando não há pelo menos cerca de:

```text
5 GB livres
```

---

# 170. Virtualização

No Windows:

- VT-x/SVM precisa estar habilitado;
- hypervisor compatível.

---

# 171. Windows Hypervisor Platform

Android recomenda WHPX como caminho atual em Windows compatível.

---

# 172. AEHD sunset

## FATO TEMPORAL

A documentação atual informa que o Android Emulator Hypervisor Driver será descontinuado em:

```text
31 de dezembro de 2026
```

e usuários Windows devem migrar para Windows Hypervisor Platform.

Fonte:

https://developer.android.com/studio/run/emulator-acceleration

Verificado em: 2026-08-22.

---

# 173. Não construir setup novo em tecnologia prestes a ser retirada

Se configurarmos aceleração hoje:

preferir caminho atual suportado.

---

# 174. `emulator -accel-check`

Ferramenta útil para verificar aceleração.

---

# 175. Emulator lento

Antes de culpar app:

- host CPU/RAM;
- hardware acceleration;
- GPU;
- antivirus;
- AVD muito pesado.

---

# 176. App lento no Emulator e rápido no device

Pode ser ambiente.

---

# 177. App lento nos dois

Mais provável app.

---

# 178. AVD corrompido

Pode acontecer.

Mas antes de apagar:

testar outro AVD.

---

# 179. Cold boot

Pode isolar snapshot ruim.

---

# 180. Wipe data

Apaga estado do AVD.

Usar conscientemente.

---

# 181. Recriar AVD

Último passo razoável quando AVD específico está corrompido.

Não como solução para código.

---

# 182. Teste em dispositivo real

Android recomenda sempre testar em hardware real antes de release.

---

# 183. Device físico é evidência independente

Ajuda separar:

- Emulator;
- app.

---

# 184. Motor de xadrez: regra de troubleshooting

## DECIDIDO

Quando uma jogada está errada:

não começar pela UI.

Primeiro reproduzir no motor com teste.

---

# 185. Bug de movimento

Perguntar:

```text
PSEUDO-LEGAL ESTÁ ERRADO?
ATTACK DETECTION?
KING SAFETY?
STATE TRANSITION?
HISTORY?
UI MAPPING?
```

---

# 186. Caso mínimo

Criar posição mínima com poucas peças.

---

# 187. Não depurar regra numa partida de 40 lances se podemos reduzir a 4 peças

---

# 188. Board dump

Representação textual/FEN futura pode ajudar.

---

# 189. Teste unitário do bug

## OBRIGATÓRIO QUANDO VIÁVEL

Se encontramos bug de regra:

criar teste antes ou junto da correção.

---

# 190. Exemplo

Bug:

en passant deixa próprio rei em xeque.

Teste:

posição mínima que reproduz.

---

# 191. Perft mismatch

Procedimento:

1. confirmar posição inicial;
2. confirmar depth;
3. comparar contagem de referência;
4. usar divide;
5. localizar ramo divergente;
6. reduzir posição;
7. criar regression test.

---

# 192. Perft maior não é primeira reação

Se depth 5 diverge:

compare depth 1–4.

---

# 193. Primeiro depth divergente

É mais útil.

---

# 194. Perft divide

Mostra contagem por movimento raiz.

Reduz busca.

---

# 195. Bug de castling

Verificar separadamente:

- rights;
- peças entre;
- check atual;
- casas atravessadas atacadas;
- destination atacado;
- king/rook history.

---

# 196. Bug de en passant

Verificar:

- último double push;
- rank/file;
- expiry;
- captured pawn removal;
- king safety após remoção.

---

# 197. Bug de promoção

Verificar:

- rank final;
- escolha;
- peça criada;
- cor;
- check/mate imediato;
- history/counters.

---

# 198. Bug de repetition

Verificar chave inclui:

- side to move;
- piece placement;
- castling rights;
- en passant possibility conforme regra.

---

# 199. Bug 50/75

Verificar halfmove reset em:

- pawn move;
- capture.

---

# 200. “Insufficient material”

Usar semântica correta de dead position do doc05.

Não corrigir por tabelinha simplista.

---

# 201. UI bug

Primeiro classificar:

```text
STATE CORRETO, RENDER ERRADO?
STATE ERRADO?
MAPPING DE TOUCH?
LIFECYCLE?
RESOURCE/LAYOUT?
```

---

# 202. Board visual errado

Comparar:

- engine state;
- UI model;
- renderer.

---

# 203. Peça em casa errada

Pode ser:

- orientation mapping;
- row inversion;
- file/rank mapping;
- state.

---

# 204. Teste de mapping

Criar casos para:

- a1;
- h1;
- a8;
- h8;
- centro.

---

# 205. Board flip futuro

Se houver:

testes de ambos orientations.

---

# 206. Touch cai na casa errada

Capturar:

- board bounds;
- x/y;
- squareSize;
- row/column calculados.

---

# 207. Não corrigir com “+1” mágico

Derivar fórmula.

---

# 208. Layout cortado

Investigar:

- constraints;
- insets;
- system bars;
- font scale;
- window size.

---

# 209. Só ocorre em um device

Comparar:

- resolution;
- density;
- API;
- navigation mode;
- font scale;
- cutout.

---

# 210. Layout Inspector

Android Studio pode inspecionar hierarchy/layout em runtime.

Usar quando layout não corresponde ao XML esperado.

---

# 211. Custom View não desenha

Investigar:

- bounds;
- `onSizeChanged`;
- `onDraw`;
- invalidation;
- Paint;
- clipping;
- visibility.

---

# 212. `invalidate()`

Redesenha.

Não chamar em loop sem necessidade.

---

# 213. UI não atualiza após move

Perguntar:

- engine retornou estado novo?
- ViewModel/state holder atualizou?
- observer recebeu?
- renderer foi chamado?
- main thread?

---

# 214. UI mostra estado antigo

Pode ser stale state/cache.

Não assumir engine bug.

---

# 215. Estado correto após restart, errado após rotação

Lifecycle/configuration issue.

---

# 216. Lifecycle

Activity pode ser destruída/recriada.

Estado da partida não deve depender de campos frágeis da View/Activity.

---

# 217. Reproduzir recreation

Usar:

- rotação quando aplicável;
- “Don’t keep activities” apenas como ferramenta específica;
- `ActivityScenario.recreate()` em testes.

---

# 218. “Don’t keep activities”

Não deixar ativado e depois achar que Android enlouqueceu.

---

# 219. Process death

Diferente de configuration change.

ViewModel não sobrevive process death.

---

# 220. Bug após process death

Decisão de persistência/restauração precisa ser considerada.

Não prometer estado que V1 não definiu.

---

# 221. Background/foreground

Timer futuro especialmente sensível.

---

# 222. Main thread

Não fazer trabalho pesado na UI.

---

# 223. ANR

ANR ocorre quando app não responde dentro de limites do sistema.

Fonte:

https://developer.android.com/topic/performance/vitals/anr

---

# 224. ANR no Gambitol

Possível futuro:

- IA pesada no main;
- I/O;
- deadlock;
- loop infinito;
- renderer absurdamente pesado.

---

# 225. Engine local simples

Movimento humano deve ser rápido.

Se legal move generation congela UI perceptivelmente:

medir.

---

# 226. Não mover tudo para thread sem medir

Threading adiciona complexidade.

---

# 227. Perfetto/Profiler

Ferramentas futuras se performance real exigir.

---

# 228. Crash ≠ ANR

Crash termina processo por exception/fatal.

ANR é falta de resposta.

Diagnósticos diferentes.

---

# 229. OOM ≠ ANR necessariamente

---

# 230. StrictMode

## FUTURO / FERRAMENTA

Pode ajudar detectar disk/network indevidos em main thread.

Não necessário no engine puro.

---

# 231. Memory leak

Sintomas:

- memória cresce;
- Activity não libera;
- OOM após ciclos.

---

# 232. LeakCanary

## NÃO ADOTADO

Pode ser ferramenta futura.

Não adicionar sem necessidade.

---

# 233. Android Profiler

Preferir ferramentas oficiais primeiro.

---

# 234. Testes locais falham

Classificar:

- assertion;
- test setup;
- production bug;
- flakiness;
- environment.

---

# 235. Assertion failure

Ler:

```text
expected
actual
```

Não alterar expected para ficar verde sem validar regra.

---

# 236. “Consertar teste” alterando expected

Pode mascarar bug.

Perguntar qual comportamento é correto.

---

# 237. Teste novo falha

Isso pode ser ótimo: encontrou bug.

---

# 238. Teste antigo falha após mudança

Regressão ou requisito mudou.

Se requisito mudou:

atualizar teste + documentação conscientemente.

---

# 239. Teste flaky

Não rerodar até passar e ignorar.

---

# 240. Flaky investigation

- timing;
- shared state;
- order dependency;
- random;
- async;
- device;
- network.

---

# 241. `Thread.sleep`

Documento 08 já desaconselha como sincronização de UI tests.

Se teste só passa aumentando sleep:

problema não resolvido.

---

# 242. Random tests

Fixar seed ao reproduzir.

---

# 243. Parameterized tests

Se apenas um caso falha:

isolar entrada.

---

# 244. Test environment mismatch

Local passa, CI falha:

comparar:

- JDK;
- OS;
- Gradle;
- locale;
- timezone;
- file paths;
- line endings.

---

# 245. Chess engine deve ser timezone-independent

Se teste de regra depende de timezone:

arquitetura está errada.

---

# 246. Locale

Notação/UI pode depender.

Engine não.

---

# 247. Instrumented test não encontra device

ADB/emulator issue.

Não test logic.

---

# 248. Espresso test trava

Pode ser:

- idling;
- dialog;
- animation;
- wrong view;
- activity state.

---

# 249. Accessibility test falha

Corrigir:

- label;
- target;
- contrast;
- semantics.

Não desativar regra sem justificativa.

---

# 250. Lint

Lint é análise estática.

Warning não é automaticamente bug.

Mas não ignorar todos.

---

# 251. Lint baseline

## FUTURO / CUIDADO

Pode ser útil em projeto legado.

Gambitol novo deve preferir corrigir antes de acumular baseline enorme.

---

# 252. `@SuppressLint`

Exige justificativa.

Não “faz o erro sumir”; apenas silencia.

---

# 253. Warnings de deprecation

Planejar atualização.

Não são sempre bloqueadores imediatos.

---

# 254. Error de compileSdk/target

Consultar docs atuais.

Não usar números de outro tutorial.

---

# 255. Release build falha, debug passa

Investigar diferenças:

- minification;
- signing;
- resources shrinking;
- BuildConfig;
- proguard rules;
- dependencies variant-specific.

---

# 256. R8

Se minificação estiver ativa:

erros podem aparecer apenas release.

---

# 257. Missing classes no R8

Não adicionar keep rules gigantes automaticamente.

Primeiro:

- qual classe?
- por que referenciada?
- dependency opcional?
- reflection?

---

# 258. Keep all

```text
-keep class ** { *; }
```

destrói propósito da minificação.

Não usar como “fix” final.

---

# 259. Reflection

Se biblioteca usa reflection:

pode exigir regras específicas.

Consultar documentação da lib.

---

# 260. Mapping

Guardar mapping para deobfuscate stack traces quando R8 ativo.

---

# 261. Signing error

Classificar:

- keystore path;
- alias;
- password;
- certificate;
- upload key;
- app signing key.

---

# 262. Não regenerar chave imediatamente

Pode quebrar update.

---

# 263. Upload key reset

Com Play App Signing, existe processo.

Seguir doc12.

---

# 264. “App not installed”

Pode ser assinatura/version/device.

Usar `adb install`/logs para mensagem concreta.

---

# 265. Play upload rejeitado

Ler erro exato:

- versionCode;
- target;
- signing;
- policy;
- package;
- manifest.

---

# 266. versionCode já usado

Incrementar conforme estratégia.

Não deletar release achando que número volta a ficar livre.

---

# 267. target API

Verificar política atual.

---

# 268. Play review rejection

Não tratar como bug técnico automaticamente.

Classificar:

- policy;
- metadata;
- privacy;
- functionality;
- account.

---

# 269. Pre-launch report

Usar como evidência adicional.

Não como prova de regras do xadrez.

---

# 270. Crash no pre-launch que não reproduz local

Pegar:

- device;
- API;
- stack;
- orientation;
- locale.

Reproduzir em AVD/device equivalente.

---

# 271. Accessibility report

Pode apontar:

- contrast;
- touch target;
- labels.

Relacionar ao doc09.

---

# 272. Android vitals

Pós-release:

cluster real pode exigir hotfix.

---

# 273. Release regression

Usar:

- tag/commit;
- previous version;
- diff;
- bisect se necessário.

---

# 274. Rollback Play

Não existe “voltar versionCode”.

Hotfix precisa versionCode maior.

Doc12.

---

# 275. Billing futuro

Quando existir, troubleshooting precisa separar:

- Billing connection;
- product configuration;
- Play account;
- purchase state;
- verification;
- entitlement;
- acknowledgement.

---

# 276. Produto não aparece

Verificar:

- product active/configured;
- account/tester;
- app version/track;
- package;
- country;
- Billing query.

---

# 277. Compra retorna PENDING

Não é erro.

É estado legítimo.

---

# 278. Compra cobrou mas premium não liberou

P0/P1.

Investigar:

```text
purchase state
↓
verification
↓
entitlement
↓
ack
↓
UI
```

---

# 279. Acknowledge perdido

Pode gerar refund/revoke.

---

# 280. Tester Billing

Usar fluxos oficiais.

Não pagar produção para diagnosticar primeiro.

---

# 281. Não logar purchase token completo

Segurança.

---

# 282. Backend futuro

Logs server-side entram no diagnóstico.

---

# 283. Troubleshooting de rede futuro

Separar:

- DNS;
- TLS;
- timeout;
- HTTP status;
- auth;
- server bug;
- client parsing.

Não existe no V1 local.

---

# 284. Falha de JSON futura

Guardar payload sanitizado.

---

# 285. 401 ≠ 403 ≠ 500

Interpretar semântica.

---

# 286. Security

Não “resolver” 401 removendo autenticação.

---

# 287. Secrets

Não colar em bug report.

---

# 288. Sanitização de evidência

Antes de compartilhar:

remover:

- tokens;
- passwords;
- keys;
- PII;
- financial data.

---

# 289. Minimização

Compartilhar o mínimo que reproduz.

---

# 290. Bug report bom

Inclui:

- versão;
- ambiente;
- passos;
- esperado;
- atual;
- logs relevantes;
- reprodução.

---

# 291. Bug report ruim

> “não funciona”.

---

# 292. Stack trace externo

Android Studio pode analisar stack traces colados e mapear para código.

Útil.

---

# 293. Obfuscated stack

Precisa mapping.

---

# 294. ProGuard/R8 stack

Deobfuscate antes de investigar.

---

# 295. Logs de release

Debug logs podem estar removidos.

Usar crash reporting/Play vitals futuro se adotado.

---

# 296. Crash reporting SDK

## PENDENTE

Não adicionar automaticamente.

Pode ser avaliado pós-release.

---

# 297. Play Console já fornece vitals

Começar com isso.

---

# 298. Firebase Crashlytics

Candidato futuro, não aprovado.

Introduz:

- SDK;
- Data Safety;
- privacy.

---

# 299. Não adicionar ferramenta para resolver problema que Play já cobre suficientemente

---

# 300. Troubleshooting por camadas

## ORDEM PROPOSTA PARA BUG FUNCIONAL

```text
INPUT
↓
UI MAPPING
↓
PRESENTATION STATE
↓
ENGINE API
↓
DOMAIN STATE
↓
RULE
```

---

# 301. Para bug de build

```text
JDK
↓
WRAPPER
↓
SETTINGS/PLUGINS
↓
DEPENDENCIES
↓
MODULE CONFIG
↓
SOURCE/RESOURCES
↓
TASK
```

---

# 302. Para device

```text
USB/WIFI
↓
OS/DRIVER
↓
ADB
↓
DEVICE AUTH
↓
INSTALL
↓
APP
```

---

# 303. Para release

```text
SOURCE COMMIT
↓
VERSION
↓
BUILD
↓
R8
↓
SIGNING
↓
AAB
↓
PLAY
```

---

# 304. Diagnóstico binário

Tentar encontrar fronteira:

```text
motor certo / UI errada
```

é melhor que examinar sistema inteiro.

---

# 305. Test double

Pode isolar UI do engine.

---

# 306. Fake state

UI pode renderizar snapshot conhecido em teste/debug.

---

# 307. Engine CLI/test

Pode isolar domínio do Android.

---

# 308. Separação arquitetural também é ferramenta de debugging

Uma boa arquitetura reduz espaço de busca.

---

# 309. Esse é um motivo concreto para o motor separado

---

# 310. Regra “mudar uma coisa por vez”

Especialmente para:

- Gradle;
- JDK;
- dependencies.

---

# 311. Upgrade matrix

Se atualização quebra:

anotar:

- antes;
- depois;
- AGP;
- Gradle;
- JDK;
- compileSdk;
- targetSdk.

---

# 312. Toolchain changes

Não atualizar cinco peças juntas sem motivo.

---

# 313. Android Studio Upgrade Assistant

Pode ajudar em upgrades de AGP/target.

Ainda exige review.

---

# 314. Backup Git antes de upgrade

Commit/branch limpo.

---

# 315. Não fazer upgrade em working tree cheia de feature incompleta

---

# 316. Dependabot/Renovate

## FUTURO

Não necessário agora.

---

# 317. Upgrade de plugin

Ler release notes.

---

# 318. Gradle/AGP compatibility

Consultar tabela oficial atual.

---

# 319. Java compatibility

Consultar `build/jdks`.

---

# 320. Known issues

Consultar antes de workaround exótico.

---

# 321. Cache hierarchy

Distinguir:

- IDE indexes;
- project build outputs;
- Gradle cache;
- dependency cache;
- emulator snapshot/data.

Cada “limpeza” destrói coisa diferente.

---

# 322. `clean`

Project outputs.

---

# 323. Invalidate Caches

IDE indices/caches.

---

# 324. Gradle global cache delete

Dependências/caches globais.

---

# 325. Wipe Data

AVD user data.

---

# 326. Clear app storage

App data.

---

# 327. Uninstall app

App + data, geralmente.

---

# 328. Não aplicar todas as limpezas juntas

Depois não sabemos qual camada estava ruim.

---

# 329. Cache-clearing ladder

## PROPOSTO

Só avançar conforme evidência:

```text
1. rebuild específico
2. Gradle sync
3. stop daemon se daemon suspeito
4. clean project output se incremental suspeito
5. IDE cache se IDE/index suspeito
6. dependency cache apenas se artifact/cache suspeito
7. AVD wipe/recreate apenas se AVD suspeito
```

---

# 330. “Delete .gradle”

Não fazer sem saber qual `.gradle`:

- project;
- user home.

Impacto diferente.

---

# 331. `.idea`

Não apagar como primeiro passo.

Pode destruir configurações do projeto/IDE.

---

# 332. Reimport

Pode ajudar quando metadata IDE realmente corrompida.

---

# 333. “Nuclear reset”

## ÚLTIMO RECURSO

Se realmente necessário:

documentar o que foi removido.

---

# 334. Diagnóstico de performance de build

Antes de tweaks:

medir.

---

# 335. Build Analyzer

Pode identificar tasks/plugins custosos.

---

# 336. `--profile`

Gradle pode gerar profile.

Usar se necessário.

---

# 337. Não aumentar `org.gradle.jvmargs` porque build parece lento

Sem medir memory/GC.

---

# 338. RAM baixa

Emulator + Android Studio + Gradle podem competir.

---

# 339. Fechar Chrome com 97 abas às vezes é engenharia de performance host surpreendentemente eficaz

Mas registrar como ambiente, não correção do app.

---

# 340. CPU alta

Separar:

- Gradle daemon;
- Emulator;
- indexing;
- app loop.

---

# 341. Fan high não é benchmark

---

# 342. Battery

Device físico pode mostrar problemas.

---

# 343. App freeze

Se UI sem crash:

capturar thread dump/ANR quando possível.

---

# 344. Infinite loop engine

Unit test pode travar.

Usar timeout apenas como detector, depois corrigir loop.

---

# 345. Deadlock

Futuro com threads.

Capturar thread states.

---

# 346. Concurrency

Minimizar no V1.

---

# 347. Timer futuro

Timekeeping bugs são difíceis.

Usar monotonic clock adequado.

Não `System.currentTimeMillis()` cegamente para duração.

Decisão futura.

---

# 348. Time travel/device clock

Não deve alterar duração local se timer usar monotonic source.

---

# 349. UI animation bug

Desativar animação temporariamente pode isolar state versus transition.

---

# 350. Animation não pode alterar domain state

---

# 351. Haptic/sound bug

Desabilitar componente para isolar.

---

# 352. Theme bug

Testar resource qualifier/theme overlay.

---

# 353. Dark mode

Se app dark-only, ainda testar system UI/insets.

---

# 354. Locale bug

Strings/resources.

---

# 355. Font scale bug

Layout.

---

# 356. Orientation bug

Lifecycle/layout.

---

# 357. Multi-window bug

Window sizing.

---

# 358. API-specific bug

Use emulator/device na API.

---

# 359. MinSdk API 24

Não confiar só em latest emulator.

---

# 360. Recent API

Também necessário.

---

# 361. Device matrix

Documento 08.

---

# 362. API 24 crash

Pode ser missing method/class.

Verificar:

- API level;
- desugaring;
- support library;
- guards.

---

# 363. `NoSuchMethodError`

Pode ser API/dependency mismatch.

---

# 364. `ClassNotFoundException`

Pode ser:

- shrinker;
- dependency;
- classloader.

---

# 365. `VerifyError`

Pode indicar bytecode/compatibility.

---

# 366. `Resources$NotFoundException`

Verificar resource/qualifier/context.

---

# 367. `InflateException`

Geralmente causa interna aparece em `Caused by`.

Pode ser:

- view class;
- theme attr;
- constructor;
- resource.

---

# 368. `ActivityNotFoundException`

Manifest/intent.

---

# 369. `SecurityException`

Permission/exported/operation.

Não suprimir.

---

# 370. `NetworkOnMainThreadException`

Futuro se rede.

Mover I/O corretamente, não desativar proteção.

---

# 371. `TransactionTooLargeException`

Não colocar estado enorme em Bundle.

Futuro se salvar board/history indevidamente.

---

# 372. Board state é pequeno

Mas histórico longo pode crescer.

---

# 373. State restoration

Não serializar objeto gigante por preguiça.

---

# 374. Test bug versus product bug

Um teste pode estar errado.

Validar contra doc05/FIDE.

---

# 375. FIDE como oracle normativo

Para regra:

documento 05 + fonte oficial.

---

# 376. Perft como oracle técnico

Para geração de movimento.

---

# 377. UI screenshot como oracle

Limitado.

Não prova regra.

---

# 378. Golden screenshot

Futuro.

---

# 379. User report

É evidência inicial.

Não assumir causa.

---

# 380. “Cavalo atravessou peça”

Isso é comportamento correto.

Não corrigir porque usuário relatou sem validar regra.

---

# 381. “Roque não funciona”

Perguntar posição/histórico.

---

# 382. Reproduzir com FEN futuro

Excelente para suporte.

---

# 383. Build bug report

Guardar:

- `./gradlew --version`;
- relevant build files;
- task;
- stacktrace.

---

# 384. Device bug report

Guardar:

- model;
- API;
- app version;
- navigation mode quando relevante.

---

# 385. Release bug report

Guardar:

- versionCode;
- versionName;
- track;
- device/API;
- install/update path.

---

# 386. Não pedir dados pessoais desnecessários a tester

---

# 387. Tester screenshot

Pode ajudar.

---

# 388. Reproduction video

Pode ajudar UI bug.

---

# 389. Log extraction

Orientar tester quando realmente necessário.

---

# 390. Remote debugging

Futuro.

---

# 391. Source of truth de troubleshooting

Este documento define método.

Problemas específicos confirmados podem ser adicionados em uma seção de casos conhecidos.

---

# 392. Não transformar documento em cemitério de erros únicos

Registrar apenas:

- recorrentes;
- importantes;
- não óbvios;
- úteis pedagogicamente.

---

# 393. Formato de caso conhecido

## PROPOSTO

```text
SINTOMA
CAUSA CONFIRMADA
COMO CONFIRMAR
CORREÇÃO
NÃO FAZER
VERSÕES AFETADAS
FONTE
```

---

# 394. Causa desconhecida

Não registrar como confirmada.

---

# 395. “Resolveu reiniciando”

Registrar:

```text
workaround observado
causa não confirmada
```

---

# 396. Knowledge base honesta

Melhor pouca informação correta.

---

# 397. Problema conhecido externo

Linkar:

- Android known issues;
- Gradle;
- Git;
- biblioteca.

---

# 398. Atualização de versões

Se workaround era para versão antiga:

marcar.

---

# 399. Android Studio/AGP known issues são temporais

Rever.

---

# 400. Emulator issues são temporais

Rever.

---

# 401. Play policies são temporais

Rever.

---

# 402. Java/Gradle compatibility é temporal

Rever.

---

# 403. Troubleshooting e ADR

Se correção exige mudança arquitetural:

criar/revisar decisão no doc11.

---

# 404. Troubleshooting e testes

Bug corrigido → regression test quando possível.

---

# 405. Troubleshooting e Git

Não commit fix sem revisão/aprovação.

---

# 406. Troubleshooting e roadmap

Bug crítico pode interromper fase.

---

# 407. Troubleshooting e conteúdo

Bug educativo pode virar:

```text
🎥 MOMENTO BOM PARA GRAVAR
```

depois de resolvido e sanitizado.

---

# 408. Não filmar crise antes de salvar trabalho

Prioridade é sistema.

---

# 409. COMO EXPLICAR EM ENTREVISTA — debugging

> “Eu evitava troubleshooting por tentativa e erro. Primeiro reproduzia, coletava stack trace ou logs, formulava uma hipótese e alterava uma variável por vez. Quando o bug era de domínio, eu o reduzia a uma posição mínima e criava um teste de regressão.”

---

# 410. COMO EXPLICAR EM ENTREVISTA — Gradle

> “Quando havia diferença entre build do Android Studio e terminal, eu verificava o JDK efetivamente usado pelo Gradle, porque o IDE e o shell podem resolver JDKs diferentes.”

---

# 411. COMO EXPLICAR EM ENTREVISTA — Android

> “Para crashes eu começava pelo `FATAL EXCEPTION` no Logcat, seguia a cadeia de `Caused by` e localizava a primeira linha do código do app antes de modificar qualquer coisa.”

---

# 412. COMO EXPLICAR EM ENTREVISTA — engine

> “Em erros de regra eu separava pseudo-legalidade, attack detection, king safety e state transition. Perft Divide ajudava a localizar o primeiro ramo divergente.”

---

# 413. 🎥 MOMENTO BOM PARA GRAVAR — JDK mismatch

Quando ocorrer de verdade:

mostrar:

```text
IDE usa JDK A
terminal usa JDK B
```

e provar via `./gradlew --version`.

---

# 414. 🎥 MOMENTO BOM PARA GRAVAR — stack trace

Mostrar método de leitura.

---

# 415. 🎥 MOMENTO BOM PARA GRAVAR — dependency conflict

Usar árvore/insight.

---

# 416. 🎥 MOMENTO BOM PARA GRAVAR — manifest merge

Mostrar dependência inserindo permission/attribute.

---

# 417. 🎥 MOMENTO BOM PARA GRAVAR — ADB offline

Diagnóstico com `adb devices`.

---

# 418. 🎥 MOMENTO BOM PARA GRAVAR — Perft divergence

Altíssimo valor.

---

# 419. 🎥 MOMENTO BOM PARA GRAVAR — lifecycle bug

Activity recreation.

---

# 420. 🎥 MOMENTO BOM PARA GRAVAR — R8 release-only

Quando existir.

---

# 421. 🎥 MOMENTO BOM PARA GRAVAR — `git bisect`

Somente se bug real justificar.

---

# 422. Checklist universal antes do fix

- [ ] reproduzi;
- [ ] capturei erro exato;
- [ ] conheço ambiente;
- [ ] sei última mudança;
- [ ] formulei hipótese;
- [ ] vou alterar uma coisa;
- [ ] sei como validar.

---

# 423. Checklist universal depois do fix

- [ ] reprodução não falha;
- [ ] teste passa;
- [ ] suite relacionada passa;
- [ ] build passa;
- [ ] efeito colateral revisado;
- [ ] regression criada quando cabe;
- [ ] causa/correção documentada se relevante.

---

# 424. Checklist JDK/Gradle

- [ ] `java -version`;
- [ ] `javac -version`;
- [ ] `./gradlew --version`;
- [ ] `JAVA_HOME`;
- [ ] Gradle JDK IDE;
- [ ] wrapper;
- [ ] AGP;
- [ ] toolchain;
- [ ] source/target;
- [ ] compile/min/target SDK.

---

# 425. Checklist build

- [ ] task exata;
- [ ] primeira mensagem relevante;
- [ ] `--stacktrace` se necessário;
- [ ] config ou execução?;
- [ ] dependencies;
- [ ] Manifest;
- [ ] resources;
- [ ] source compile.

---

# 426. Checklist device/ADB

- [ ] `adb devices`;
- [ ] auth;
- [ ] USB debugging;
- [ ] cabo;
- [ ] driver;
- [ ] restart ADB;
- [ ] outro device;
- [ ] emulator control case.

---

# 427. Checklist emulator

- [ ] disk;
- [ ] RAM;
- [ ] virtualization;
- [ ] hypervisor;
- [ ] accel check;
- [ ] latest supported emulator;
- [ ] cold boot;
- [ ] another AVD;
- [ ] physical device.

---

# 428. Checklist crash

- [ ] reproduce;
- [ ] `FATAL EXCEPTION`;
- [ ] exception;
- [ ] `Caused by`;
- [ ] first app frame;
- [ ] state;
- [ ] regression test.

---

# 429. Checklist chess rule

- [ ] minimal position;
- [ ] expected FIDE rule;
- [ ] pseudo-legal;
- [ ] attack;
- [ ] king safety;
- [ ] state transition;
- [ ] history flags;
- [ ] regression;
- [ ] Perft when applicable.

---

# 430. Checklist UI

- [ ] engine state;
- [ ] presentation state;
- [ ] renderer input;
- [ ] coordinates;
- [ ] bounds;
- [ ] lifecycle;
- [ ] insets;
- [ ] API/device.

---

# 431. Checklist tests

- [ ] expected correto;
- [ ] setup;
- [ ] shared state;
- [ ] deterministic;
- [ ] async;
- [ ] environment;
- [ ] flakiness;
- [ ] production behavior.

---

# 432. Checklist release

- [ ] source commit;
- [ ] versionCode/name;
- [ ] release task;
- [ ] R8;
- [ ] signing;
- [ ] AAB;
- [ ] Play error;
- [ ] pre-launch;
- [ ] target API.

---

# 433. Checklist Git recovery

- [ ] `git status`;
- [ ] branch;
- [ ] log;
- [ ] reflog;
- [ ] stash;
- [ ] remote state;
- [ ] backup before destructive command.

---

# 434. Comandos de observação úteis

## Git

```bash
git status -sb
git branch --show-current
git log --oneline --decorate -n 10
git reflog -n 20
```

---

# 435. Gradle

```bash
./gradlew --version
./gradlew help
./gradlew tasks
./gradlew <task> --stacktrace
```

---

# 436. Java

```bash
java -version
javac -version
```

---

# 437. ADB

```bash
adb devices
adb kill-server
adb start-server
```

Somente quando ADB é o problema.

---

# 438. Logcat

```bash
adb logcat
```

Com filtros quando necessário.

---

# 439. Emulator

```bash
emulator -accel-check
```

Quando diagnóstico de aceleração exigir e executable estiver acessível.

---

# 440. Não copiar comandos cegamente

Paths/tasks variam.

Verificar ambiente.

---

# 441. Ordem preferida de fontes

## DECIDIDO

1. mensagem/stack trace real;
2. documentação oficial da ferramenta;
3. known issues/release notes;
4. issue tracker oficial;
5. reproduções independentes;
6. comunidades;
7. respostas antigas de fórum.

---

# 442. Stack Overflow

Útil.

Mas resposta de 2018 pode sugerir:

- HAXM antigo;
- AGP velho;
- limpar cache;
- API descontinuada.

Validar.

---

# 443. Reddit

Útil para relatos.

Não normativo.

---

# 444. Blog

Pode ser bom.

Comparar com docs atuais.

---

# 445. IA

Pode acelerar hipótese.

Não substituir evidência.

---

# 446. Quando pedir ajuda à IA

Fornecer:

- erro exato;
- ambiente;
- arquivo relevante;
- comando;
- o que mudou.

---

# 447. O que não enviar

- secret;
- keystore;
- senha;
- token;
- dados pessoais.

---

# 448. Não pedir “como resolver?” sem contexto

Isso produz loteria de troubleshooting.

---

# 449. Resposta de IA deve ser testada como hipótese

---

# 450. Não executar comando destrutivo sugerido sem entender

---

# 451. Troubleshooting pedagógico

O método do projeto exige explicar:

```text
o que a mensagem significa
por que a hipótese faz sentido
como confirmar
por que a correção resolve
```

---

# 452. Não apenas mandar 15 comandos

---

# 453. Uma etapa por vez

Especialmente em ambiente.

---

# 454. Fazer → verificar → explicar → continuar

Mantém causalidade.

---

# 455. Quando pode haver bloco de comandos

Quando todos pertencem ao mesmo teste observacional e não alteram estado perigoso.

---

# 456. Exemplo

```bash
java -version
javac -version
./gradlew --version
```

é um bloco razoável.

---

# 457. Exemplo ruim

```bash
rm -rf ~/.gradle
rm -rf .idea
rm -rf ~/.android
git reset --hard
```

é uma cerimônia de destruição, não diagnóstico.

---

# 458. Cache superstition ban

## DECIDIDO

Não recomendar “limpar cache” sem nomear:

- qual cache;
- qual evidência;
- qual efeito;
- qual risco.

---

# 459. Restart superstition ban

Mesma regra.

Reiniciar pode ser teste.

Não explicação.

---

# 460. Upgrade superstition ban

Não atualizar tudo para “latest” como tentativa.

---

# 461. Downgrade superstition ban

Também.

---

# 462. Reinstall Android Studio

Último recurso raro.

---

# 463. Reinstall JDK

Só se instalação realmente corrompida/incompleta.

---

# 464. Reclone repository

Pode esconder problema local.

Antes:

descobrir.

---

# 465. Fresh clone como experimento

## VÁLIDO

Um clone limpo em outro diretório pode responder:

> o problema está versionado ou é estado local?

Isso é diagnóstico.

---

# 466. Fresh clone não substitui recuperação de arquivos não commitados

Cuidado.

---

# 467. Outra máquina/CI

Ótimo experimento.

---

# 468. Container/VM

Não pertence ao workflow atual.

Não introduzir só para troubleshooting.

---

# 469. Path com espaços

Git Bash/Gradle lidam, mas scripts podem falhar por quoting.

Sempre citar paths.

---

# 470. Windows line endings

Pode afetar scripts.

Git config relevante.

---

# 471. Wrapper executable no Unix

Windows não percebe bit executável igual.

CI Linux pode falhar.

---

# 472. Case sensitivity

Windows é geralmente case-insensitive.

Linux CI é case-sensitive.

Arquivo/import com case errado pode passar local e falhar CI.

---

# 473. Path length

Windows moderno melhorou, mas ferramentas ainda podem sofrer.

Não criar hierarquia absurda.

---

# 474. Antivírus

Pode interferir com Emulator/build I/O.

Android docs mencionam impacto no Emulator.

Testar antes de excluir tudo permanentemente.

---

# 475. Firewall

Pode afetar ADB Wi‑Fi/dependencies.

---

# 476. Proxy

Pode afetar Gradle.

---

# 477. Time sync

Pode afetar TLS/Play/account.

---

# 478. Disk full

Pode causar erros bizarros.

Sempre simples de verificar.

---

# 479. RAM pressure

Pode matar Emulator/daemon.

---

# 480. CPU virtualization disabled

Emulator.

---

# 481. Windows Hypervisor Platform

Atual em 2026.

---

# 482. JDK path com espaços

Funciona se configurado corretamente.

Não mover JDK só por isso sem evidência.

---

# 483. SDK path

Confirmar via Android Studio/`local.properties` quando necessário.

---

# 484. `local.properties`

Máquina-local.

Não deve conter lógica compartilhada.

---

# 485. SDK not found

Verificar `sdk.dir`/configuração.

---

# 486. Não commitar path absoluto pessoal como solução compartilhada

---

# 487. `gradle.properties`

Pode conter configurações compartilhadas ou user-level.

Não colocar secret.

---

# 488. `.idea`

Alguns arquivos podem ser versionados, outros não, conforme estratégia do projeto.

Não apagar arbitrariamente.

---

# 489. Emulator image

Atualizar quando issue conhecido.

---

# 490. Android 17/API 37 preview

Não usar como ambiente principal da V1 se target/test matrix não exigir.

---

# 491. Preview API bug

Pode ser plataforma preview.

Reproduzir stable API.

---

# 492. Stable API bug

Mais relevante.

---

# 493. OEM-specific behavior

Device físico específico pode divergir.

Testar outro fabricante.

---

# 494. Battery optimization

Futuro para background.

Não V1.

---

# 495. Accessibility service effects

TalkBack muda foco/interação.

Testar como modo próprio.

---

# 496. Developer options

Podem alterar comportamento:

- animation scale;
- don't keep activities;
- background limits.

Registrar se ativas.

---

# 497. “Show taps”

Só visual.

---

# 498. GPU rendering debug overlays

Podem afetar screenshot.

---

# 499. Emulator snapshot

Pode preservar developer settings inesperadas.

---

# 500. Fresh AVD

Útil para baseline.

---

# 501. App data baseline

Para bugs de primeiro launch:

fresh install.

---

# 502. Update baseline

Para bugs de migration:

não fresh install.

---

# 503. Diferenciar install paths

```text
fresh install
update
reinstall
clear data
```

todos diferentes.

---

# 504. Release signing baseline

Debug install não prova release update.

---

# 505. Test track baseline

Play-delivered app pode diferir de local APK em split/signing.

---

# 506. AAB issue

Test via internal track/bundletool quando necessário.

---

# 507. Split APK issue

Raro no Gambitol simples.

Mas possível com resources/ABI futuros.

---

# 508. Native library future

Se Stockfish/native entrar:

diagnóstico ganha:

- ABI;
- JNI;
- native symbols;
- crashes.

Criar seção futura quando existir.

---

# 509. Não antecipar JNI troubleshooting agora

---

# 510. Database future

Se persistence entrar:

diagnóstico ganha:

- schema;
- migration;
- corruption;
- transactions.

Não inventar agora.

---

# 511. Network future

Mesma regra.

---

# 512. Billing future

Já mapeado minimamente.

---

# 513. Known issues iniciais do projeto

## PENDENTE DE CONFIRMAÇÃO

Não registrar como problema atual:

```text
“JDK quebrado”
```

só porque houve uma mensagem anterior sem validação final.

Ao retomar desenvolvimento, revalidar baseline.

---

# 514. Primeiro troubleshooting real após documentação

Segundo roadmap:

1. Git status;
2. Gradle files;
3. JDK;
4. sync;
5. build;
6. tests;
7. run.

---

# 515. Se build falhar

Aplicar este método.

---

# 516. Não corrigir antes de capturar estado

---

# 517. Resultado esperado da Fase 1

Baseline reproduzível.

---

# 518. Troubleshooting como parte da qualidade

Uma equipe/projeto que sabe diagnosticar reduz MTTR.

---

# 519. MTTR

Mean Time To Recovery/Repair, conforme contexto.

Não precisa ser métrica formal no projeto solo.

---

# 520. Valor profissional

Debugging é habilidade central.

---

# 521. Portfólio de debugging

Um bug bem explicado mostra mais engenharia que cinco telas estáticas.

---

# 522. Fontes — Java/JDK no Android

## Java versions in Android builds

https://developer.android.com/build/jdks

Usado para:

- Gradle JDK;
- `JAVA_HOME`;
- PATH;
- JDK/toolchain;
- source/target;
- compileSdk;
- desugaring;
- consistência IDE/terminal.

Verificado em: 2026-08-22.

---

# 523. Fontes — Gradle troubleshooting

## Troubleshooting builds

https://docs.gradle.org/current/userguide/troubleshooting.html

Usado para:

- JAVA_HOME inválido;
- `help`;
- dependency resolution;
- slow builds;
- daemon/IDE troubleshooting.

Verificado em: 2026-08-22.

---

# 524. Fontes — Android Gradle Plugin troubleshooting

https://developer.android.com/build/troubleshoot

Usado para:

- troubleshooting de AGP;
- known issues;
- bug reporting.

---

# 525. Fontes — Android Studio known issues

https://developer.android.com/studio/known-issues

Usado para:

- problemas conhecidos;
- workarounds;
- distinguir bug de tooling.

Verificado em: 2026-08-22.

---

# 526. Fontes — Build/run

https://developer.android.com/studio/run

Usado para:

- build output;
- Gradle errors;
- `--stacktrace`;
- Build Analyzer.

---

# 527. Fontes — dependency resolution

https://developer.android.com/build/dependency-resolution-errors

https://developer.android.com/build/gradle-dependency-resolution

Usado para:

- duplicate classes;
- transitive dependencies;
- classpath conflicts;
- dependency tree.

Verificado em: 2026-08-22.

---

# 528. Fontes — dependencies

https://developer.android.com/build/dependencies

Usado para:

- dependency configurations;
- dynamic versions;
- version catalogs;
- dependency verification recommendation.

---

# 529. Fontes — dependency verification

https://developer.android.com/build/dependency-verification

Usado para:

- checksum/signatures;
- supply-chain risk;
- verification metadata.

---

# 530. Fontes — Manifest merge

https://developer.android.com/build/manage-manifests

Usado para:

- merge;
- conflicts;
- Merged Manifest;
- tools markers;
- transitive manifest effects.

Verificado em: 2026-08-22.

---

# 531. Fontes — Logcat

## Android Studio Logcat

https://developer.android.com/studio/debug/logcat

## Command-line Logcat

https://developer.android.com/tools/logcat

Usado para:

- crash;
- stack trace;
- filters;
- buffers;
- severity.

Verificado em: 2026-08-22.

---

# 532. Fontes — Debugger

https://developer.android.com/studio/debug

Usado para:

- breakpoints;
- variables;
- expressions;
- debug variant.

---

# 533. Fontes — stack traces

https://developer.android.com/studio/debug/stacktraces

Usado para:

- leitura;
- frames;
- navegação para linha.

---

# 534. Fontes — ADB

https://developer.android.com/tools/adb

Usado para:

- arquitetura cliente/servidor;
- devices;
- instalação;
- targeting;
- connection.

Verificado em: 2026-08-22.

---

# 535. Fontes — dispositivo físico

https://developer.android.com/studio/run/device

Usado para:

- Connection Assistant;
- USB debugging;
- cabos;
- restart ADB;
- Wi‑Fi;
- recomendação de teste em hardware real.

Verificado em: 2026-08-22.

---

# 536. Fontes — Emulator troubleshooting

https://developer.android.com/studio/run/emulator-troubleshooting

Usado para:

- disk;
- RAM;
- known issues;
- GPU;
- host environment.

Verificado em: 2026-08-22.

---

# 537. Fontes — Emulator acceleration

https://developer.android.com/studio/run/emulator-acceleration

Usado para:

- WHPX;
- virtualization;
- AEHD sunset 2026;
- acceleration diagnostics.

Verificado em: 2026-08-22.

---

# 538. Fontes — Emulator command line

https://developer.android.com/studio/run/emulator-commandline

Usado para:

- `-accel-check`;
- command-line diagnostics.

---

# 539. Fontes — Git bisect

https://git-scm.com/docs/git-bisect

Usado para:

- regressions;
- binary search;
- automated bisect.

Verificado em: 2026-08-22.

---

# 540. Fontes — Git reflog

https://git-scm.com/docs/git-reflog

Usado para:

- recovery local;
- HEAD/ref history.

---

# 541. Fontes — Git fsck

https://git-scm.com/docs/git-fsck

Usado para:

- integrity;
- connectivity;
- corruption diagnostics.

---

# 542. Fontes — ANR

https://developer.android.com/topic/performance/vitals/anr

Usado para:

- ANR;
- main-thread blocking;
- diagnosis.

---

# 543. Relação com `02_METODO_DE_ENSINO.md`

Este documento operacionaliza o protocolo:

```text
ler erro
→ interpretar
→ hipótese
→ confirmar
→ corrigir
```

---

# 544. Relação com `08_TESTES_E_QUALIDADE.md`

Todo bug de domínio corrigível por teste deve gerar regression coverage.

---

# 545. Relação com `07_GIT_WORKFLOW.md`

Git é ferramenta de recuperação e localização de regressões.

Não substituir processo.

---

# 546. Relação com `05_REGRAS_DO_MOTOR_DE_XADREZ.md`

FIDE/documento 05 definem expected behavior.

---

# 547. Relação com `09_UI_UX_GAMBITOL.md`

Layout/accessibility/insets definem expected UI.

---

# 548. Relação com `12_PLAY_STORE_E_RELEASE.md`

Release troubleshooting segue version/signing/Play gates.

---

# 549. Relação com `13_MONETIZACAO.md`

Billing troubleshooting só entra quando feature existir.

---

# 550. Política final de troubleshooting

## DECIDIDO

1. preservar evidência antes de alterar;
2. reproduzir antes de corrigir;
3. distinguir sintoma de causa;
4. uma hipótese por vez;
5. ler erro exato;
6. usar fonte oficial primeiro;
7. caches só são limpos com hipótese específica;
8. restart não é causa;
9. não apagar dados/AVD/repo sem entender impacto;
10. não usar comandos Git destrutivos por impulso;
11. bugs de xadrez viram casos mínimos e testes;
12. Perft divergence é reduzida pelo primeiro depth/ramo divergente;
13. UI e engine são isoladas antes de culpar uma à outra;
14. JDK do IDE e JDK do terminal devem ser verificados quando builds divergem;
15. ADB/device/emulator têm diagnósticos próprios;
16. release-only bugs são testados na build release;
17. R8/signing/Play não são tratados como debug build;
18. problemas recorrentes relevantes são documentados;
19. causas não confirmadas são rotuladas como não confirmadas;
20. toda correção crítica precisa de validação e, quando possível, regressão automatizada.

---

# 551. Resumo executivo

```text
NÃO ENTRE EM PÂNICO
↓
NÃO LIMPE TUDO
↓
CAPTURE O ERRO
↓
REPRODUZA
↓
DESCUBRA A CAMADA
↓
FORMULE UMA HIPÓTESE
↓
FAÇA UM TESTE PEQUENO
↓
CORRIJA A CAUSA
↓
RODE A REGRESSÃO
↓
DOCUMENTE O QUE REALMENTE FOI PROVADO
```

---

# 552. Primeira aplicação prática futura

Depois da conclusão da documentação:

```text
git status
↓
inspecionar Gradle
↓
confirmar JDK
↓
./gradlew --version
↓
build
↓
testes
↓
run
```

Se algo falhar:

não improvisar.

Usar este documento.

---

# 553. Frase norteadora

> **Um bom desenvolvedor não é aquele cujo projeto nunca quebra. É aquele que consegue transformar uma falha observável em uma causa testável, uma correção verificável e conhecimento reutilizável.**

---

# 554. Encerramento da fundação documental

Com `15_TROUBLESHOOTING.md`, a fundação planejada do Gambitol passa a cobrir:

```text
VISÃO
ENSINO
ARQUITETURA
ESTRUTURA
REGRAS
PADRÕES
GIT
TESTES
UI/UX
ROADMAP
DECISÕES
RELEASE
MONETIZAÇÃO
PORTFÓLIO
TROUBLESHOOTING
```

A próxima etapa não é criar mais documentação por reflexo.

É voltar ao projeto real, validar o baseline e começar a construir seguindo os gates definidos.

Porque, em algum momento, até Markdown precisa permitir que o software exista.
