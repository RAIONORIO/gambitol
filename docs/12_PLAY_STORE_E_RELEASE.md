# 12 — PLAY STORE E RELEASE DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `12_PLAY_STORE_E_RELEASE.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir como o Gambitol será preparado, versionado, assinado, testado, submetido, publicado, monitorado e atualizado na Google Play sem confundir build local com release publicável  
> **Fonte normativa para:** Android App Bundle, assinatura, Play App Signing, upload key, versionCode/versionName, tracks, requisitos de conta, verificação de desenvolvedor Android, target API, store listing, Data Safety, política de privacidade, classificação etária, público-alvo, pre-launch report, staged rollout, Android vitals, release checklist e pós-release  
> **Não cobre em detalhe:** monetização, estratégia comercial, criação de conteúdo, portfolio, implementação do motor, regras de xadrez ou workflow Git completo  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `03_ARQUITETURA_DO_GAMBITOL.md`, `06_PADROES_JAVA_E_ANDROID.md`, `07_GIT_WORKFLOW.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`, `10_ROADMAP_E_ESCOPO.md`, `11_DECISOES_TECNICAS.md`  
> **Application ID definido:** `br.com.raionorio.gambitol`  
> **Plataforma inicial:** Android mobile  
> **Formato de publicação esperado:** Android App Bundle (`.aab`)  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

Este documento responde:

- quando um build deixa de ser apenas “funcionou na minha máquina”;
- o que precisa existir para gerar uma release;
- como versionar;
- como assinar;
- o que é Play App Signing;
- qual chave fica conosco;
- o que nunca deve entrar no Git;
- como criar e testar um App Bundle;
- quais requisitos do Google Play são atuais;
- quais requisitos mudam com o tempo;
- como preparar a página do jogo;
- como preencher Data Safety e conteúdo do app;
- como usar faixas de teste;
- como tratar contas pessoais recentes;
- como interpretar pre-launch reports;
- como publicar a primeira versão;
- como publicar atualizações;
- como reduzir risco usando staged rollout;
- como monitorar Android vitals;
- como reagir a uma release ruim.

A regra central é:

> **Uma release não é um arquivo `.aab`. É um conjunto de código, configuração, identidade, assinatura, políticas, testes e evidências de que aquela versão pode ser distribuída com segurança.**

---

# 2. Release não começa no Play Console

## DECIDIDO

O fluxo de release começa no repositório.

Conceitualmente:

```text
ESCOPO FECHADO
↓
CÓDIGO VALIDADO
↓
TESTES
↓
LINT
↓
BUILD RELEASE
↓
ASSINATURA
↓
AAB
↓
PLAY CONSOLE
↓
TEST TRACK
↓
PRE-LAUNCH REPORT
↓
REVISÃO
↓
PRODUÇÃO
↓
MONITORAMENTO
```

---

# 3. Não publicar diretamente de uma máquina “suja”

Antes de gerar release:

- branch correta;
- working tree conhecida;
- commit/tag conforme workflow aprovado;
- sem debug temporário;
- sem secrets;
- sem arquivos locais inesperados;
- dependências resolvidas;
- build reproduzível.

---

# 4. Debug build ≠ release build

## CONCEITO IMPORTANTE

`debug` costuma possuir:

- assinatura debug;
- flags de debug;
- configuração diferente;
- ausência de minificação;
- comportamento diferente em algumas integrações.

Logo:

```text
assembleDebug passou
```

não prova:

```text
release está pronta
```

---

# 5. Artefato oficial da Google Play

## DECIDIDO

O Gambitol será publicado como:

```text
Android App Bundle
.aab
```

Desde agosto de 2021, novos apps no Google Play precisam ser publicados usando Android App Bundle.

Fonte oficial:

https://developer.android.com/guide/app-bundle

Verificado em: 2026-08-22.

---

# 6. O que é AAB

Um Android App Bundle contém:

- código compilado;
- resources;
- módulos;
- metadata de entrega.

O Google Play usa o bundle para gerar APKs otimizados para cada dispositivo.

O `.aab` é formato de publicação.

---

# 7. AAB não é “o APK maior”

Não é simplesmente um APK renomeado.

O Play gera diferentes APKs a partir dele conforme:

- densidade;
- ABI;
- idioma;
- módulos.

---

# 8. Por que o Play usa AAB

Benefícios:

- menor download;
- APKs otimizados;
- distribuição simplificada;
- integração com Play Feature Delivery;
- integração com Play Asset Delivery.

O Gambitol provavelmente não precisará das duas últimas no MVP.

---

# 9. APK ainda existe

O Android executa APKs.

O Play gera APKs derivados do AAB.

Podemos continuar gerando APK debug para desenvolvimento.

---

# 10. Não publicar APK na primeira versão Play

Para novo app:

```text
AAB
```

é o formato correto.

---

# 11. Comando de bundle

Quando signing/build estiver configurado, a task típica do módulo `app` será algo como:

```bash
./gradlew :app:bundleRelease
```

## IMPORTANTE

Antes de usar:

- confirmar módulo;
- confirmar tasks;
- confirmar signing;
- confirmar buildType.

O documento não ordena executar esse comando agora.

---

# 12. Caminho típico do bundle

Em configuração padrão:

```text
app/build/outputs/bundle/release/
```

Mas o caminho real deve ser confirmado após o build.

---

# 13. Release build precisa ser testado

## DECIDIDO

Antes de upload:

- build;
- testes relevantes;
- Lint;
- smoke;
- instalação derivada/track de teste.

---

# 14. Bundletool

## FUTURO / FERRAMENTA DE DESENVOLVIMENTO

`bundletool` é a ferramenta que o ecossistema usa para manipular app bundles e gerar APK sets.

Pode ajudar a:

- inspecionar;
- gerar APKs;
- testar delivery.

Não precisa entrar no fluxo inicial se Play internal testing já atende.

---

# 15. Play internal app sharing

Pode ser útil para compartilhar rapidamente:

- AAB;
- APK;

sem formalizar uma release de teste completa.

Não substitui closed/internal track quando precisamos validar o processo real.

---

# 16. Assinatura Android

## CONCEITO CRÍTICO

Todo app Android precisa ser assinado.

A assinatura:

- identifica o autor/linha de atualização;
- permite que Android valide updates;
- impede que qualquer pessoa publique update arbitrário com outra chave.

---

# 17. Chave de assinatura do app

É a identidade criptográfica usada para assinar os APKs distribuídos aos usuários.

---

# 18. Chave de upload

É a chave que o desenvolvedor usa para assinar o AAB enviado ao Google Play.

---

# 19. Play App Signing

## OBRIGATÓRIO PARA NOVO APP PLAY

Para novos apps atuais, a publicação usa Play App Signing.

O Google:

- gerencia a app signing key;
- usa essa chave para assinar APKs entregues aos usuários.

O desenvolvedor mantém a upload key.

Fonte:

https://developer.android.com/studio/publish/app-signing

Verificado em: 2026-08-22.

---

# 20. Duas chaves, duas responsabilidades

```text
UPLOAD KEY
fica com o desenvolvedor
↓
assina AAB enviado

APP SIGNING KEY
gerenciada pelo Play App Signing
↓
assina APKs distribuídos
```

---

# 21. Separar upload key da app signing key

## RECOMENDADO

A documentação Android recomenda separar as duas.

Benefício:

se a upload key for perdida/comprometida:

pode ser redefinida via Play.

---

# 22. Perder upload key

É problema.

Mas com Play App Signing:

é possível solicitar reset da upload key.

---

# 23. Perder app signing key fora do Play App Signing

Historicamente poderia impedir futuras atualizações.

Mais um motivo para usar Play App Signing corretamente.

---

# 24. Google-generated app signing key

Ao configurar novo app, o Play pode gerar a app signing key.

## CANDIDATO FORTE

É simples e seguro para distribuição primária pelo Play.

---

# 25. Fornecer sua própria app signing key

Também é possível.

Pode ser necessário se:

- quisermos usar exatamente a mesma assinatura em outras lojas;
- existe uma estratégia multi-store específica.

---

# 26. Decisão ainda pendente

## PENDENTE

Na fase de release precisamos decidir:

```text
Google gera app signing key
ou
fornecemos nossa própria
```

Não é necessário decidir agora.

---

# 27. Se outras lojas forem objetivo

A documentação Android alerta:

se quisermos a mesma app signing key em múltiplas lojas, precisamos planejar isso durante configuração do Play App Signing.

---

# 28. Keystore

Arquivo que contém chave privada/certificado.

Extensões comuns:

```text
.jks
.keystore
```

---

# 29. Keystore nunca entra no Git

## PROIBIDO

Também não entram:

- senha do keystore;
- senha da key;
- alias secreto se tratado como credencial;
- arquivos com signing credentials.

---

# 30. Chave não deve ser enviada em chat

## REGRA DE SEGURANÇA

Durante a publicação:

não compartilhar arquivo privado de keystore com IA, fórum ou terceiros.

---

# 31. Senhas não entram no Gradle versionado

Evitar:

```kotlin
storePassword = "senha"
keyPassword = "senha"
```

em arquivo commitado.

---

# 32. Estratégia de secrets

## PENDENTE DE IMPLEMENTAÇÃO

Possíveis mecanismos:

- variável de ambiente;
- arquivo local ignorado;
- secret store do CI;
- propriedades locais seguras.

Escolher quando release/CI for configurado.

---

# 33. Backup da upload key

## PROPOSTO

Manter cópias seguras independentes.

A upload key pode ser redefinida no Play, mas perda continua causando interrupção operacional.

---

# 34. Certificado público não é secreto

Fingerprint/certificado podem ser usados para:

- APIs;
- Play;
- OAuth;
- verificação.

A chave privada é secreta.

---

# 35. SHA-256 certificate fingerprint

Será especialmente importante para:

- Android developer verification;
- APIs futuras;
- ownership.

---

# 36. Application ID

## DECIDIDO

```text
br.com.raionorio.gambitol
```

---

# 37. Application ID publicado deve ser tratado como permanente

Depois que o app existe no Play:

alterar `applicationId` significa, na prática, outro app/pacote.

Não renomear casualmente.

---

# 38. Package name e applicationId

No Android moderno, package de código e applicationId podem tecnicamente divergir.

Para o Gambitol:

não criar divergência sem motivo.

---

# 39. Android developer verification

## FATO TEMPORAL IMPORTANTE

O Android introduziu um novo programa de verificação de desenvolvedores.

A partir de **30 de setembro de 2026**, as proteções começam a valer em:

- Brasil;
- Indonésia;
- Singapura;
- Tailândia;

para apps instalados por lojas participantes em dispositivos Android certificados.

Fonte:

https://developer.android.com/developer-verification

Verificado em: 2026-08-22.

---

# 40. Requisito para Play packages

A partir de 2026-09-30:

todos os pacotes Google Play precisam estar registrados para cumprir os requisitos de verificação de desenvolvedor Android.

Apps não registrados podem ser removidos do Play conforme a política.

Fonte:

https://support.google.com/googleplay/android-developer/answer/16984799

---

# 41. O Play tenta registrar novos apps automaticamente

A documentação atual indica que, para novos apps criados no Play Console:

o Play registra/associa automaticamente o package name à conta quando elegível.

Fonte:

https://developer.android.com/developer-verification/guides/google-play-console

---

# 42. Se package já foi usado fora do Play

Pode ser necessário provar ownership da key correspondente.

Isso importa se o Gambitol for distribuído externamente antes do Play.

---

# 43. Estratégia recomendada

## PROPOSTO

Antes de distribuir APK release fora do Play:

entender implicações de package registration e signing.

---

# 44. Conta Play Console

Existem atualmente dois tipos principais:

```text
PERSONAL
ORGANIZATION
```

Ambas podem publicar e monetizar, mas possuem requisitos de verificação diferentes.

---

# 45. Conta pessoal

Pode ser apropriada para:

- hobby;
- estudante;
- desenvolvedor individual.

Requer informações/identidade verificadas.

---

# 46. Conta de organização

É apropriada quando a conta representa formalmente uma empresa/organização.

Normalmente exige:

```text
D-U-N-S
```

além de dados organizacionais.

Fonte:

https://support.google.com/googleplay/android-developer/answer/13628312

---

# 47. Escolha de tipo de conta

## PENDENTE

Não presumir se o Gambitol será publicado por:

- conta pessoal;
- conta de organização.

Essa escolha deve ser feita antes de criar/usar a conta definitiva.

---

# 48. D-U-N-S pode levar tempo

A documentação Google recomenda preparar com antecedência e alerta que obtenção pode levar até cerca de 30 dias.

Se organização for escolhida:

não deixar isso para a véspera da release.

---

# 49. Contatos precisam permanecer válidos

Play exige dados de contato verificados.

Não usar:

- e-mail descartável;
- telefone temporário.

---

# 50. Device verification de contas pessoais novas

## FATO ATUAL

Novas contas pessoais precisam verificar acesso a um dispositivo Android físico usando o app móvel Play Console antes de disponibilizar apps.

O dispositivo elegível deve ser:

- físico;
- não root;
- Android 10+.

Fonte:

https://support.google.com/googleplay/android-developer/answer/14316361

Verificado em: 2026-08-22.

---

# 51. Requisitos de teste de contas pessoais recentes

## FATO ATUAL

Para contas pessoais criadas após:

```text
13 de novembro de 2023
```

o Google Play exige atualmente closed testing antes de liberar acesso à produção.

---

# 52. Quantidade atual de testadores

Em 2026-08-22:

```text
mínimo 12 testadores
```

---

# 53. Duração atual

Eles precisam participar continuamente por pelo menos:

```text
14 dias
```

---

# 54. Depois dos 14 dias

O desenvolvedor solicita acesso à produção e responde perguntas sobre:

- app;
- processo de testes;
- feedback;
- prontidão.

Não é desbloqueio automático só porque o relógio completou 14 dias.

Fonte:

https://support.google.com/googleplay/android-developer/answer/14151465?hl=pt-BR

---

# 55. Regra condicional

## IMPORTANTE

Esse requisito não deve ser presumido para toda conta.

Depende:

- tipo;
- data de criação.

Na release:

verificar a conta real.

---

# 56. Test tracks

Google Play possui:

- internal testing;
- closed testing;
- open testing;
- production.

---

# 57. Internal testing

Uso:

- equipe pequena;
- validação rápida;
- builds iniciais.

Atualmente suporta até:

```text
100 testadores escolhidos
```

Fonte:

https://support.google.com/googleplay/android-developer/answer/9859348

---

# 58. Closed testing

Uso:

- grupo controlado maior;
- feedback pré-release;
- requisito de produção para certas contas pessoais.

---

# 59. Open testing

Permite participação pública no beta.

Para novas contas pessoais sujeitas ao requisito, o open test só fica disponível depois de obter acesso à produção.

---

# 60. Production

Distribuição pública nos países/regiões selecionados.

---

# 61. Sequência recomendada para Gambitol

## PROPOSTO

```text
INTERNAL
↓
CLOSED
↓
PRODUCTION
```

Open testing:

opcional.

---

# 62. Por que internal primeiro

Bom para:

- instalar AAB via Play;
- verificar assinatura;
- testar atualização;
- capturar pre-launch;
- validar listing/processo.

---

# 63. Closed depois

Bom para:

- pessoas fora do ambiente de desenvolvimento;
- aparelhos diversos;
- feedback real;
- cumprir regra de 12/14 quando aplicável.

---

# 64. Open testing

Não é necessário para V1 se closed test já fornecer evidência suficiente.

---

# 65. Primeira produção não aceita staged rollout

## FATO IMPORTANTE

O Google Play permite staged rollout para atualizações.

Não para a primeira publicação de produção.

Fonte:

https://support.google.com/googleplay/android-developer/answer/6346149

---

# 66. Consequência

A primeira versão precisa ser muito bem validada em tracks de teste antes de entrar em produção.

---

# 67. Staged rollout para updates

Depois da primeira release:

podemos liberar atualização para uma porcentagem dos usuários.

---

# 68. Percentual não aumenta sozinho

O Play exige aumento manual.

---

# 69. Estratégia de percentual

## PROPOSTO

Não fixar uma escada rígida enquanto a base de usuários é pequena.

Princípio:

```text
pequeno grupo
↓
observar
↓
aumentar
↓
observar
↓
100%
```

---

# 70. Quando a base for pequena

5% de 20 usuários é quase uma piada estatística.

Nesse caso:

closed/internal + validação manual pode gerar mais informação.

---

# 71. Halt staged rollout

Se problema aparecer:

é possível interromper.

Novos usuários deixam de receber aquela versão.

---

# 72. Usuários que já receberam continuam nela

## IMPORTANTE

Halt não faz rollback automático nos aparelhos.

Se a versão é ruim:

gerar nova versão corrigida.

---

# 73. Resume

Se análise provar que release está correta:

pode retomar.

---

# 74. Nova release durante staged rollout

Exige cuidado com grupos e artefatos.

Não improvisar.

---

# 75. Managed publishing

Play possui recurso de publicação gerenciada.

Serve para controlar quando alterações já aprovadas entram no ar.

---

# 76. Limitação importante

A documentação atual diz que Managed Publishing:

```text
não serve para a primeira publicação do app
```

O app precisa já estar disponível.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9859654

---

# 77. Uso futuro

Bom para:

- sincronizar release;
- controlar horário;
- separar aprovação de publicação.

---

# 78. Tempo de revisão

## FATO TEMPORAL

Google alerta que reviews podem levar:

```text
algumas horas
até 7 dias
ou mais em casos excepcionais
```

---

# 79. Não marcar lançamento para “sexta às 18h”

sem margem.

A própria documentação sugere planejar pelo menos uma semana de tolerância para revisões quando publicação controlada importa.

---

# 80. Target API

## FATO TEMPORAL CRÍTICO

Em 2026-08-22, a próxima exigência entra em vigor em:

```text
31 de agosto de 2026
```

---

# 81. A partir de 2026-08-31

Novos apps e updates de telefone/tablet precisam segmentar:

```text
Android 16
API 36
ou superior
```

Fonte oficial:

https://developer.android.com/google/play/requirements/target-sdk

Verificado em: 2026-08-22.

---

# 82. Até a virada do prazo

A exigência anterior era API 35 para novas submissões comuns.

Como a publicação do Gambitol ocorrerá depois da fase de desenvolvimento e documentação:

## RECOMENDAÇÃO

Planejar desde já compatibilidade com:

```text
targetSdk 36+
```

sem alterar o projeto no escuro.

---

# 83. Extensão 2026

A política atual prevê possibilidade de extensão até:

```text
1 de novembro de 2026
```

para casos elegíveis.

Isso não deve ser plano do Gambitol.

---

# 84. Regra do projeto

## DECIDIDO

Sempre verificar target API novamente imediatamente antes de release.

---

# 85. targetSdk ≠ minSdk

O Gambitol pode:

```text
minSdk 24
```

e:

```text
targetSdk 36
```

desde que teste e compatibilidade estejam corretos.

---

# 86. compileSdk

Normalmente deve acompanhar uma API que permita compilar contra o target e APIs usadas.

O valor concreto será definido durante atualização do build.

---

# 87. Migrar target requer testar behavior changes

Não basta trocar:

```kotlin
targetSdk = 36
```

e comemorar.

Precisamos revisar behavior changes do Android 16 e versões intermediárias.

---

# 88. Edge-to-edge

Já tratado no documento 09.

Target moderno muda comportamentos visuais.

Release deve validar.

---

# 89. versionCode

## DECIDIDO COMO MECANISMO

Inteiro positivo usado pelo Android/Play para ordenar versões.

Precisa aumentar a cada nova versão enviada.

---

# 90. versionCode não é versão comercial

Exemplo:

```text
versionCode = 17
versionName = "1.2.0"
```

é válido.

---

# 91. versionCode não pode ser reutilizado

O Play não aceita upload com versionCode já usado anteriormente.

---

# 92. Limite do Play

Atualmente:

```text
2,100,000,000
```

é o maior versionCode permitido.

Fonte:

https://developer.android.com/studio/publish/versioning

---

# 93. Estratégia inicial de versionCode

## PROPOSTO

Usar incremento simples:

```text
1
2
3
4
...
```

Sem codificar datas ou SemVer no inteiro enquanto isso não trouxer benefício.

---

# 94. Pular número

Não é problema.

---

# 95. Nunca diminuir

Release pública precisa caminhar para cima.

---

# 96. versionName

String exibida ao usuário.

---

# 97. Estratégia de versionName

## PROPOSTO

Usar SemVer-like:

```text
0.1.0
0.2.0
1.0.0
1.0.1
```

quando estratégia de release for aprovada.

---

# 98. Git tag

Documento 07 propôs tags:

```text
v0.1.0
v1.0.0
```

---

# 99. Mapeamento proposto

```text
versionName = "1.0.0"
Git tag = "v1.0.0"
```

Isso facilita rastreabilidade.

---

# 100. versionCode continua independente

Exemplo:

```text
versionCode 23
versionName 1.0.0
tag v1.0.0
```

---

# 101. Primeiro versionName publicável

## PENDENTE

Não fixar hoje.

---

# 102. Release name no Play Console

Pode ser nome interno para identificar release.

Não é necessariamente o versionName visível.

---

# 103. Release notes

Devem explicar mudanças para usuários.

Não copiar commits crus.

---

# 104. Release notes são produto

Usuário precisa saber:

- novidade;
- correção relevante;
- melhoria.

---

# 105. Release notes não são changelog técnico completo

---

# 106. Primeiro upload a internal track

## PROPOSTO

Deve ocorrer antes de produção.

Objetivos:

- validar App Bundle;
- signing;
- installation;
- Play-generated APKs;
- pre-launch;
- devices.

---

# 107. Play App Signing setup

No primeiro fluxo de publicação:

configurar antes do upload final do bundle.

---

# 108. Store listing

O Play exige uma página de produto.

---

# 109. Nome do app

Limite atual:

```text
30 caracteres
```

`Gambitol` cabe confortavelmente.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9859152

---

# 110. Short description

Limite:

```text
80 caracteres
```

---

# 111. Full description

Limite:

```text
4.000 caracteres
```

---

# 112. Não escrever a descrição agora como definitiva

## DECIDIDO

A listing final deve refletir o estado real da release.

Não anunciar:

- IA;
- online;
- ranking;
- timer;

se não existirem.

---

# 113. Metadata policy

Evitar:

- “#1”;
- “melhor”;
- “top”;
- “mais baixado”;
- descontos no título;
- fake awards;
- keyword stuffing;
- emojis irrelevantes.

---

# 114. Nome de app não é campo de SEO spam

`Gambitol - Xadrez Chess Jogo Grátis Top 2026`

não será usado.

---

# 115. App icon do Play

Requisito atual:

```text
PNG 32-bit com alpha
512 × 512
máximo 1024 KB
```

Fonte:

https://support.google.com/googleplay/android-developer/answer/9866151

---

# 116. Store icon ≠ launcher icon técnico

Podem derivar da mesma identidade.

Mas o asset Play é preparado especificamente para listing.

---

# 117. Feature graphic

Obrigatória para publicar store listing.

Requisito:

```text
JPEG ou PNG 24-bit sem alpha
1024 × 500
```

---

# 118. Feature graphic não deve duplicar icon enorme

Google recomenda usar a arte como extensão da identidade.

---

# 119. Zona segura

Elementos críticos devem ficar próximos ao centro porque diferentes superfícies podem cortar bordas.

---

# 120. Screenshots

Requisito geral atual:

- no mínimo 2 screenshots;
- JPEG ou PNG 24-bit sem alpha;
- dimensão mínima 320px;
- máxima 3840px;
- maior dimensão não pode superar 2× a menor.

---

# 121. Recomendação para games

Para formatos promocionais:

Google recomenda pelo menos:

```text
3 screenshots 9:16 portrait com mínimo 1080 × 1920
```

ou:

```text
3 screenshots 16:9 landscape com mínimo 1920 × 1080
```

Para Gambitol portrait:

9:16 é candidato natural.

---

# 122. Screenshots precisam ser verdadeiras

Mostrar experiência real.

Não criar mockup impossível.

---

# 123. Primeiros screenshots

## PROPOSTO FUTURO

Possíveis assuntos:

1. tabuleiro principal;
2. movimentos legais/turno;
3. promoção/game state;
4. resultado/identidade.

Somente se a release realmente possuir esses estados.

---

# 124. Texto sobre screenshots

Pode ser usado com parcimônia.

Não exceder a experiência.

---

# 125. Alt text

Google recomenda descrições acessíveis para assets.

Para screenshot/graphic, usar alt text conciso e útil.

---

# 126. Preview video

## OPCIONAL

Pode ser interessante depois.

Não bloqueia publicação.

---

# 127. Vídeo não deve ter ads

Se usado como preview no Play.

---

# 128. Store listing compartilhada entre tracks

Alterações na listing podem aparecer também em contextos de teste.

Planejar.

---

# 129. Localização da listing

Inicialmente:

Português do Brasil.

Futuro:

inglês e outros idiomas.

---

# 130. Não usar tradução automática sem revisão

Especialmente termos de xadrez.

---

# 131. Content rating

## OBRIGATÓRIO

Apps no Google Play precisam de classificação de conteúdo IARC.

---

# 132. Como é definida

Responder questionário no Play Console.

A classificação é derivada das respostas por autoridades regionais.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9859655

---

# 133. Não adivinhar a classificação do Gambitol

Mesmo sendo xadrez:

o questionário deve ser preenchido corretamente.

Não escrever “Livre” na documentação como fato.

---

# 134. Se conteúdo mudar

Refazer questionnaire quando mudança afetar respostas.

---

# 135. Ads também influenciam classificação/políticas

Se publicidade futura existir:

o conteúdo dos anúncios precisa ser adequado à classificação.

---

# 136. Target audience

## OBRIGATÓRIO DECLARAR

Play exige informar público-alvo/faixas etárias.

---

# 137. Escolher crianças tem consequências

Se incluirmos menores de 13 anos no público-alvo:

podem se aplicar regras da Families Policy.

---

# 138. Público-alvo do Gambitol

## PENDENTE

Não marcar automaticamente:

```text
todos, incluindo crianças
```

só porque xadrez pode ser jogado por crianças.

A declaração precisa refletir intenção real do produto.

---

# 139. Ads declaration

Antes de target audience, Play exige declarar se o app contém anúncios.

---

# 140. No core atual

O roadmap não depende de ads.

Mas declaração final depende do documento 13 e do artefato real.

---

# 141. App access

Play pede instruções de acesso para review.

---

# 142. Gambitol local sem login

Se V1 continuar sem conta:

a review deverá poder acessar tudo normalmente.

---

# 143. Se login futuro bloquear recursos

Precisamos fornecer:

- conta de demo;
- instruções;
- QR/code quando aplicável.

---

# 144. Privacy policy

## OBRIGATÓRIA

A política atual do Google Play exige privacy policy para todos os apps.

Até apps sem coleta de dados precisam fornecer.

Fonte:

https://support.google.com/googleplay/android-developer/answer/10144311

---

# 145. Privacy policy precisa estar no Play Console

Link público.

---

# 146. Também precisa estar acessível no app

A política atual exige link/texto acessível dentro do app.

---

# 147. Requisitos atuais do link

Deve ser:

- URL pública;
- ativa;
- acessível sem login;
- não geobloqueada;
- legível em browser;
- não PDF;
- não uma página editável colaborativamente.

---

# 148. Conteúdo mínimo da policy

Conforme política atual:

- identidade do desenvolvedor/app;
- contato de privacidade;
- dados acessados/coletados/usados/compartilhados;
- terceiros;
- segurança;
- retenção;
- exclusão.

Mesmo que nada seja coletado:

declarar isso claramente.

---

# 149. Policy não pode mentir

Se SDK coleta telemetria:

“não coletamos dados” está errado, mesmo que nosso próprio código não faça upload.

---

# 150. Data Safety

## OBRIGATÓRIO PARA PUBLICAÇÃO

Todos os desenvolvedores precisam preencher o formulário de Segurança dos dados para apps em:

- closed;
- open;
- production.

---

# 151. Internal testing é exceção

Apps exclusivamente em internal testing não precisam do Data Safety form naquele estágio.

Fonte:

https://support.google.com/googleplay/android-developer/answer/10787469

---

# 152. Mesmo sem coleta

O formulário ainda precisa ser preenchido e privacy policy informada.

---

# 153. Responsabilidade é do desenvolvedor

Google não decide por nós quais dados um SDK coleta.

Precisamos auditar.

---

# 154. SDK audit

Antes de Data Safety:

listar:

- todas dependências;
- permissões;
- network calls;
- analytics;
- crash reporting;
- ads;
- billing;
- Play Games;
- SDKs.

---

# 155. Core local ideal

Se a primeira release:

- não tiver analytics;
- não tiver ads;
- não tiver backend;
- não tiver conta;
- não transmitir dados;

o formulário pode ser simples.

Mas isso só será declarado depois de inspecionar o artefato real.

---

# 156. Coleta local não é automaticamente “collection” do Data Safety

A definição do formulário é centrada em dados transmitidos para fora do dispositivo, com exceções/regras específicas.

Sempre consultar documentação atual.

---

# 157. Terceiros contam

SDK de ads/analytics coleta em nome dele:

precisa ser refletido.

---

# 158. Data Safety é global por package

Deve refletir práticas das versões atualmente distribuídas.

---

# 159. Atualizar Data Safety após SDK novo

Obrigatório se práticas mudarem.

---

# 160. Account deletion

Se futuramente o app permitir criar conta:

Google possui requisitos específicos de exclusão de conta/dados.

Não se aplica ao core local sem contas.

---

# 161. Permissions

## PRINCÍPIO

Core de xadrez local não precisa de permissões sensíveis.

---

# 162. Se o bundle pedir permissão inesperada

Parar.

Investigar transitive manifest/SDK.

---

# 163. Permissions declaration

Algumas permissões de alto risco exigem formulário e aprovação.

O Gambitol deve evitar qualquer uma sem necessidade real.

---

# 164. Internet permission

Só deveria entrar se feature realmente usar rede.

Não para V1 local puro.

---

# 165. Content declarations

App Content no Play Console pode incluir:

- ads;
- app access;
- target audience;
- content rating;
- Data Safety;
- privacy;
- permissions;
- outras declarações conforme features.

---

# 166. Checklist de App Content

## GATE

Nada fica “a preencher depois” no envio final.

---

# 167. Store category

## PENDENTE

Selecionar categoria correta de jogo no Play Console.

Não definir subcategoria sem consultar opções atuais.

---

# 168. Game classification

No Create app flow:

marcar como:

```text
Game
```

se a interface atual exigir essa distinção.

---

# 169. Free ou paid

## PENDENTE

Modelo será tratado no documento 13.

---

# 170. Atenção a app gratuito virar pago

Políticas Play possuem limitações históricas relevantes sobre preço de apps.

Não decidir sem revisar monetização atual.

---

# 171. Countries/regions

## PENDENTE

Primeira distribuição pode:

- Brasil;
- vários países.

Não publicar mundialmente automaticamente sem considerar:

- idiomas;
- política;
- suporte;
- classificação;
- privacidade.

---

# 172. Estratégia inicial de países

Pode começar controlada.

Decidir perto do closed test/produção.

---

# 173. Release track e país são dimensões diferentes

---

# 174. Pre-launch report

## DECIDIDO COMO GATE

Revisar antes de produção.

---

# 175. Como é gerado

Google gera automaticamente relatório ao fazer upload de bundle/APK, sujeito à capacidade do laboratório.

---

# 176. O que testa

Atualmente inclui:

- estabilidade;
- compatibilidade Android;
- performance;
- acessibilidade.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9842757

---

# 177. Crawling automático

O laboratório:

- instala;
- abre;
- toca;
- desliza;
- interage.

---

# 178. Credenciais

Se o app tivesse login:

poderíamos fornecer acesso.

V1 local favorece crawler.

---

# 179. Pre-launch não entende xadrez profundamente

Pode detectar:

- crash;
- ANR;
- small touch target;
- baixo contraste;
- API incompatível.

Não prova:

- roque correto;
- mate correto;
- repetição correta.

---

# 180. Resultado precisa ser revisado

Não aceitar “green” como aprovação automática.

---

# 181. Erro

P0/P1 de estabilidade:

bloqueia release.

---

# 182. Warning

Avaliar contexto.

---

# 183. Accessibility issues

No Gambitol:

especial atenção a:

- touch target;
- content labels;
- contrast;
- custom board.

---

# 184. Pre-launch report também gera screenshots/vídeo

Úteis para inspeção.

---

# 185. Android vitals

## PÓS-RELEASE OBRIGATÓRIO MONITORAR

Métricas técnicas reais de usuários.

---

# 186. Core vitals atuais

Incluem:

- user-perceived crash rate;
- user-perceived ANR rate;
- excessive partial wake locks;
- outros específicos.

---

# 187. Crash threshold atual

Em 2026-08-22:

```text
user-perceived crash rate
overall: 1.09%
per phone model: 8%
```

---

# 188. ANR threshold atual

```text
user-perceived ANR rate
overall: 0.47%
per phone model: 8%
```

Fonte:

https://developer.android.com/games/optimize/vitals

Verificado em: 2026-08-22.

---

# 189. Esses thresholds afetam discoverability

Apps acima do bad behavior threshold podem ser menos descobertos/recomendados.

---

# 190. Não usar threshold como meta

Meta não é:

```text
1.08% crash
```

Meta é:

```text
o mais próximo possível de zero
```

Threshold é fronteira de mau comportamento, não objetivo de qualidade.

---

# 191. Play geralmente observa janela recente

A documentação Play indica uso frequente dos últimos 28 dias para avaliar qualidade, podendo agir antes em spikes.

---

# 192. Release dashboard

Depois de update:

comparar:

- installs;
- crashes;
- ANRs;
- ratings;
- regressões.

---

# 193. Staged rollout + vitals

Combinação ideal para updates com base significativa.

---

# 194. Primeira release

Sem staged rollout.

Por isso closed testing é ainda mais importante.

---

# 195. Release candidate

## DEFINIÇÃO

Build que:

- possui versão definida;
- signing definido;
- escopo congelado;
- passou gates;
- pode virar release sem nova feature.

---

# 196. Feature freeze

Antes do RC:

não adicionar:

- “só mais uma opção”;
- tema novo;
- efeito;
- biblioteca;
- refactor não necessário.

---

# 197. Alteração aceita no RC

- bugfix;
- policy;
- compatibility;
- blocker UX;
- release configuration.

---

# 198. Release build gate

## PROPOSTO

```text
./gradlew clean
./gradlew test
./gradlew lint
./gradlew :app:bundleRelease
```

## IMPORTANTE

A ordem/task concreta será ajustada ao Gradle real.

`clean` não é obrigatório tecnicamente em toda build, mas pode ser usado em RC para validação limpa quando houver razão.

---

# 199. Engine gate

- unit suite;
- Perft;
- regressions.

---

# 200. Android gate

- app build;
- Lint;
- instrumented critical flows;
- device.

---

# 201. MinSdk gate

API 24.

---

# 202. Target API gate

API atual requerida pelo Play.

---

# 203. Physical device

Obrigatório no plano de release.

---

# 204. Release configuration smoke

Não testar somente debug.

---

# 205. R8/minification

## PENDENTE

Se `minifyEnabled` entrar:

release precisa ser testada com a configuração real.

---

# 206. Resource shrinking

Mesma regra.

---

# 207. Mapping file

Se R8 ofuscar:

preservar mapping correspondente à release para diagnóstico.

Play pode aceitar/usar artefatos de deobfuscation.

---

# 208. Native symbols

Se não houver native:

não se aplica.

Se IA nativa futura entrar:

upload de symbols pode ser necessário.

---

# 209. Baseline profile

Não requisito de release inicial.

---

# 210. Debuggable

Release não pode ficar debug indevidamente.

---

# 211. Test flags

Remover:

- logs exagerados;
- cheats;
- dev menus;
- test data.

---

# 212. Backup flags

Revisar manifest conforme dados futuros.

---

# 213. Exported components

Revisar manifest.

Somente componentes necessários.

---

# 214. Permissions final audit

Comparar manifest final com intenção.

---

# 215. Dependency final audit

Nenhuma lib:

- não usada;
- vulnerável conhecida;
- debug-only vazando.

---

# 216. License audit

Assets/fonts/libs.

---

# 217. Copyright/trademark

Nome, logo e artes precisam ser próprios/licenciados.

---

# 218. Store screenshots license

Somente conteúdo que podemos usar.

---

# 219. App icon final

Não placeholder Android Studio.

---

# 220. Launcher name

`Gambitol`.

---

# 221. Version final

Definida antes do AAB.

---

# 222. Release notes final

Sem “TODO”.

---

# 223. Privacy final

Reflete artifact.

---

# 224. Data Safety final

Reflete artifact.

---

# 225. Content rating final

Questionário enviado.

---

# 226. Target audience final

Declarado conscientemente.

---

# 227. Ads final

Declarado corretamente.

---

# 228. App access final

Review consegue usar.

---

# 229. Store listing final

Sem feature inexistente.

---

# 230. First production checklist

- [ ] Play account verified;
- [ ] package registered;
- [ ] account type correct;
- [ ] device verification if required;
- [ ] testing requirement if applicable;
- [ ] applicationId final;
- [ ] target API compliant;
- [ ] versionCode unique;
- [ ] versionName final;
- [ ] signing configured;
- [ ] AAB signed;
- [ ] internal/closed test;
- [ ] pre-launch reviewed;
- [ ] privacy policy;
- [ ] Data Safety;
- [ ] content rating;
- [ ] target audience;
- [ ] ads declaration;
- [ ] app access;
- [ ] store listing;
- [ ] screenshots;
- [ ] icon;
- [ ] feature graphic;
- [ ] release notes;
- [ ] countries;
- [ ] P0/P1 = zero;
- [ ] release tag prepared;
- [ ] final approval.

---

# 231. First production behavior

Depois de iniciar primeira production release:

não existe staged percentage.

O app vai para os usuários elegíveis nas regiões selecionadas após review/publicação.

---

# 232. Por isso o closed test funciona como “rollout controlado”

Antes da produção.

---

# 233. Updates checklist

Para atualização futura:

- [ ] versionCode > anterior;
- [ ] versionName correto;
- [ ] migration se dados mudaram;
- [ ] regression;
- [ ] release build;
- [ ] track test;
- [ ] pre-launch;
- [ ] staged rollout quando fizer sentido;
- [ ] vitals monitorados.

---

# 234. Atualização sem mudança de dados

Ainda precisa test.

---

# 235. Atualização com persistence migration

Mais risco.

Testar:

```text
old installed
↓
update
↓
data intact
```

---

# 236. Never uninstall between upgrade test

Se objetivo é testar migration.

---

# 237. Rollback real

Android não aceita simplesmente versionCode menor.

---

# 238. “Rollback” de produção

Na prática:

criar nova versão com:

- versionCode maior;
- código revertido/corrigido.

---

# 239. Git revert ≠ Play rollback

Conceitos diferentes.

---

# 240. Halt rollout ≠ rollback

Também.

---

# 241. Se release ruim está 100%

Criar hotfix.

---

# 242. Se está staged

Halt + hotfix.

---

# 243. Unpublish

Último recurso para impedir novos usuários.

---

# 244. Unpublish não remove de quem já instalou

Usuários existentes podem continuar usando e receber updates conforme política.

---

# 245. Emergency release

## FUTURO

Processo ainda deve manter:

- teste mínimo;
- signing;
- versionCode;
- release notes;
- monitoramento.

“Urgente” não significa “sem validação”.

---

# 246. Hotfix branch

Documento 07 cobre Git.

---

# 247. Hotfix version

Normalmente patch.

Exemplo conceitual:

```text
1.0.0 → 1.0.1
```

---

# 248. Play review ainda existe no hotfix

Não existe botão universal “confia em mim, é urgente”.

---

# 249. Comunicação

Se bug impactar usuários:

release notes/store/support podem comunicar.

---

# 250. Android vitals after hotfix

Verificar se cluster cai.

---

# 251. Crash cluster

Priorizar pelo número de usuários afetados.

---

# 252. ANR cluster

Mesmo.

---

# 253. Reviews

Não responder defensivamente.

Usar como evidência, não como debugger primário.

---

# 254. Support contact

Store listing precisa contato válido.

---

# 255. Privacy contact

Policy precisa mecanismo válido.

---

# 256. Developer identity exibida

Google exibe informações diferentes dependendo do tipo de conta e monetização/região.

Antes de criar conta:

ler exatamente o que será público.

---

# 257. Organization account implications

Pode exibir:

- organização;
- endereço;
- email;
- telefone;

conforme regras atuais.

---

# 258. Personal account implications

Também possui dados públicos específicos.

Não criar conta sem entender.

---

# 259. Conta deve ser propriedade correta

## DECISÃO FUTURA IMPORTANTE

Definir:

> quem deve ser dono legal da publicação?

Antes de criar o app final no Play Console.

---

# 260. Transferência de app

É possível em alguns casos, mas possui requisitos.

Melhor escolher ownership corretamente no início.

---

# 261. Package registration e ownership

Com nova verificação Android, ownership correto ficou ainda mais importante.

---

# 262. Key ownership

Também.

---

# 263. Play Integrity API

## NÃO NECESSÁRIA NO MVP

Não adicionar apenas porque é “segurança do Play”.

Só quando houver ameaça/caso de uso:

- fraude;
- backend;
- entitlement;
- multiplayer.

---

# 264. Licensing antiga

Não usar sem necessidade.

---

# 265. In-app updates API

## FUTURO

Não necessária para primeira release.

Play já gerencia update normal.

---

# 266. Review prompt API

## FUTURO

Não colocar logo no primeiro launch.

Só depois de experiência significativa.

---

# 267. In-app review não é requisito

---

# 268. Ratings strategy

Documento 14/13 pode abordar reputação.

---

# 269. Store experiments

## FUTURO

Só com tráfego suficiente.

---

# 270. Custom store listings

Futuro.

---

# 271. Pre-registration

Não necessário para primeira publicação.

---

# 272. Managed publishing

Futuro para updates.

---

# 273. Production access

Se conta nova pessoal:

closed testing + application.

---

# 274. Test feedback

Precisamos poder responder:

- quem testou;
- o que foi testado;
- quais bugs;
- o que mudou.

Isso também ajuda o pedido de production access.

---

# 275. Tester recruitment

Não pertence ao código.

Mas precisa planejamento se requisito de 12/14 se aplicar.

---

# 276. Testers contínuos

Precisam permanecer opted in durante período exigido.

Não basta 12 instalações pontuais.

---

# 277. Tester feedback channel

Pode ser:

- formulário;
- issue;
- mensagem;
- e-mail.

Escolher quando closed test iniciar.

---

# 278. Test script

Pode orientar testers:

- iniciar partida;
- jogar;
- promover;
- rocar;
- reiniciar;
- background/resume;
- acessibilidade quando possível.

---

# 279. Não manipular testers

Precisamos de uso real e feedback legítimo.

---

# 280. Closed test build precisa ser funcional

Não usar 14 dias para distribuir build obviamente quebrada e apenas “cumprir requisito”.

---

# 281. Play review questions

Podem perguntar sobre:

- testes;
- feedback;
- readiness.

Responder com evidência real.

---

# 282. Target deadline de 31/08/2026

## AÇÃO NO ROADMAP

Como o prazo está muito próximo da data deste documento:

ao retomar o build, verificar imediatamente o `targetSdk`.

Não esperar a fase 13.

---

# 283. Android developer verification 30/09/2026

Também está próximo.

Ao criar o app no Play Console:

verificar package registration.

---

# 284. Brasil está na primeira fase regional

Isso torna a verificação particularmente relevante para distribuição local.

---

# 285. Global rollout

A documentação prevê expansão em 2027.

Logo:

não tratar como peculiaridade regional temporária a ser ignorada.

---

# 286. Release checklist temporal

Antes de cada release importante:

```text
TARGET API
PLAY POLICIES
DEVELOPER VERIFICATION
TEST REQUIREMENTS
DATA SAFETY
CONTENT RATING
```

reverificar.

---

# 287. Não confiar apenas neste Markdown daqui a um ano

## DECIDIDO

Este documento guarda processo.

As políticas externas precisam de nova pesquisa.

---

# 288. Política de atualização deste documento

Atualizar quando houver mudança relevante em:

- target API;
- developer verification;
- testing requirements;
- Data Safety;
- signing;
- App Bundle;
- release tracks.

---

# 289. Release runbook

## PROPOSTO

Na hora real, usar este documento para criar um checklist operacional específico daquela versão.

Não editar o documento inteiro a cada build.

---

# 290. Exemplo de runbook por release

```text
Release:
VersionCode:
VersionName:
Commit:
Tag:
Target:
Track:
AAB SHA-256:
Tests:
Lint:
Perft:
Devices:
Pre-launch:
Policy review:
Approver:
```

---

# 291. Hash do AAB

## PROPOSTO

Guardar SHA-256 do artefato final nos registros de release.

Ajuda rastreabilidade.

---

# 292. AAB deve ser reproduzível?

Builds Android podem incluir fatores não totalmente bit-reproducíveis dependendo do tooling.

Não prometer igualdade byte a byte sem investigar.

---

# 293. Mas artefato publicado deve ser identificável

Guardar:

- versionCode;
- versionName;
- commit;
- tag;
- hash.

---

# 294. Source commit exato

Release precisa apontar para commit limpo.

---

# 295. Tag depois de validação final

Não taggear build quebrado.

---

# 296. Ordem proposta

```text
commit aprovado
↓
release build
↓
validar
↓
tag
↓
Play
```

Ou tag após aprovação final do RC.

Detalhe pode ser refinado no momento.

---

# 297. Tag não inclui secret

---

# 298. GitHub Release

## FUTURO / OPCIONAL

Pode documentar versão.

Não é necessário para Play.

---

# 299. AAB no GitHub Release

## NÃO RECOMENDADO AUTOMATICAMENTE

Se repo público:

pode permitir distribuição fora do Play e criar implications de signing/package verification.

Decidir conscientemente.

---

# 300. APK público fora do Play

Mesma cautela.

---

# 301. Distribuição externa

Depois de 2026 verification:

registrar package/key corretamente.

---

# 302. Store exclusivity

## PENDENTE

Não decidir que Gambitol será Play-only para sempre.

---

# 303. Play App Signing + multi-store

Se multi-store for prioridade:

decisão de app signing key precisa considerar.

---

# 304. Play-only simplifica

Mas não fechar opção sem necessidade.

---

# 305. Release signing em CI

## FUTURO

Se CI gerar release:

secrets precisam estar em secret store.

---

# 306. Não colocar keystore em GitHub Actions artifact público

Óbvio, ainda assim digno de regra.

---

# 307. Limitar acesso aos secrets

Principle of least privilege.

---

# 308. Upload via CI

Futuro.

Pode usar Play Developer API/plugin.

Não necessário inicialmente.

---

# 309. Fastlane

## FUTURO / NÃO NECESSÁRIO

Pode automatizar store/release.

Não adicionar antes de processo manual ser compreendido.

---

# 310. Gradle Play Publisher

## FUTURO

Mesma regra.

---

# 311. Primeiro release manual tem valor pedagógico

Entender:

- signing;
- AAB;
- tracks;
- policies;
- review.

Depois automatizar.

---

# 312. Automation after understanding

Princípio do projeto.

---

# 313. 🎥 MOMENTO BOM PARA GRAVAR — debug vs release

Mostrar:

- build types;
- signing;
- AAB;
- por que release é diferente.

---

# 314. 🎥 MOMENTO BOM PARA GRAVAR — Play App Signing

Mostrar diagrama:

```text
upload key
→ Play
→ app signing key
→ user APK
```

Excelente conteúdo.

---

# 315. 🎥 MOMENTO BOM PARA GRAVAR — versionCode vs versionName

Demonstrar:

- versionCode técnico;
- versionName usuário;
- Git tag.

---

# 316. 🎥 MOMENTO BOM PARA GRAVAR — primeiro AAB

Gerar e inspecionar.

---

# 317. 🎥 MOMENTO BOM PARA GRAVAR — Internal testing

Upload real e instalação via Play.

---

# 318. 🎥 MOMENTO BOM PARA GRAVAR — Pre-launch report

Analisar:

- devices;
- accessibility;
- crashes;
- warnings.

---

# 319. 🎥 MOMENTO BOM PARA GRAVAR — Data Safety

Explicar que “não coletamos” precisa incluir SDKs.

---

# 320. 🎥 MOMENTO BOM PARA GRAVAR — Android developer verification

Tema atual em 2026 e muito relevante.

---

# 321. 🎥 MOMENTO BOM PARA GRAVAR — closed test → production

Se requisito 12/14 se aplicar.

---

# 322. 🎥 MOMENTO BOM PARA GRAVAR — staged rollout

Somente em update real.

---

# 323. COMO EXPLICAR EM ENTREVISTA — release

> “No Gambitol eu tratei release como um pipeline de qualidade. O bundle de produção só era gerado depois dos testes, Lint e validações do motor, e a versão era rastreada por versionCode, versionName, commit e tag.”

---

# 324. COMO EXPLICAR EM ENTREVISTA — signing

> “Usei Play App Signing separando a app signing key da upload key. A chave de upload ficava sob controle do desenvolvedor e o Google Play gerenciava a chave usada para assinar os APKs distribuídos.”

---

# 325. COMO EXPLICAR EM ENTREVISTA — testing tracks

> “Antes da produção, usei faixas de teste para validar o AAB em dispositivos reais e revisar o pre-launch report. Atualizações poderiam usar staged rollout para limitar impacto de regressões.”

---

# 326. COMO EXPLICAR EM ENTREVISTA — políticas

> “Também tratei requisitos da Play Store como dependências versionadas no tempo. Target API, Data Safety e regras de conta eram sempre reverificados antes de uma publicação, em vez de depender de documentação antiga do projeto.”

---

# 327. Checklist de segurança da assinatura

- [ ] Play App Signing configurado;
- [ ] estratégia de app signing key decidida;
- [ ] upload key separada quando possível;
- [ ] private key fora do Git;
- [ ] senha fora do código;
- [ ] backups seguros;
- [ ] certificado/fingerprint documentado;
- [ ] acesso mínimo;
- [ ] recovery entendido.

---

# 328. Checklist do AAB

- [ ] build release;
- [ ] versionCode;
- [ ] versionName;
- [ ] target API;
- [ ] signing;
- [ ] size;
- [ ] no debug;
- [ ] no secret;
- [ ] no unused SDK grave;
- [ ] hash registrado;
- [ ] source commit conhecido.

---

# 329. Checklist do Play account

- [ ] tipo pessoal/organização decidido;
- [ ] identidade verificada;
- [ ] contatos verificados;
- [ ] D-U-N-S se organização;
- [ ] device verification se pessoal novo;
- [ ] ownership correto;
- [ ] package registration.

---

# 330. Checklist de policy

- [ ] privacy policy;
- [ ] Data Safety;
- [ ] target audience;
- [ ] ads;
- [ ] content rating;
- [ ] app access;
- [ ] permissions declaration se aplicável;
- [ ] policies atuais revisadas.

---

# 331. Checklist de store listing

- [ ] nome;
- [ ] short description;
- [ ] full description;
- [ ] icon 512×512;
- [ ] feature graphic 1024×500;
- [ ] screenshots;
- [ ] alt text;
- [ ] support contact;
- [ ] category;
- [ ] localization;
- [ ] nenhum claim falso.

---

# 332. Checklist de internal test

- [ ] bundle upload;
- [ ] install via Play;
- [ ] launch;
- [ ] legal game;
- [ ] promotion;
- [ ] special moves;
- [ ] restart;
- [ ] min/recent devices;
- [ ] pre-launch report.

---

# 333. Checklist de closed test

- [ ] testers list;
- [ ] access link;
- [ ] feedback channel;
- [ ] test scenarios;
- [ ] crash monitoring;
- [ ] policy forms;
- [ ] requisito 12/14 se aplicável;
- [ ] feedback documentado.

---

# 334. Checklist de produção

- [ ] production access;
- [ ] no P0/P1;
- [ ] release final;
- [ ] current target API;
- [ ] current verification requirements;
- [ ] release notes;
- [ ] countries;
- [ ] approval;
- [ ] monitor after launch.

---

# 335. Checklist pós-release 24h

- [ ] publishing status;
- [ ] installs;
- [ ] crashes;
- [ ] ANRs;
- [ ] reviews;
- [ ] device-specific issues;
- [ ] support messages.

---

# 336. Checklist pós-release 7 dias

- [ ] Android vitals;
- [ ] crash clusters;
- [ ] ANR clusters;
- [ ] ratings trend;
- [ ] compatibility;
- [ ] top device issues;
- [ ] feature requests triaged.

---

# 337. Checklist de update staged

- [ ] current production baseline;
- [ ] new build;
- [ ] test track;
- [ ] rollout percentage;
- [ ] observation window;
- [ ] vitals;
- [ ] feedback;
- [ ] increase/halt decision.

---

# 338. Anti-patterns proibidos

- keystore no Git;
- senha no build.gradle;
- versionCode reutilizado;
- target API alterado sem testes;
- subir direto em produção;
- screenshot falsa;
- dizer “não coleta” sem auditar SDK;
- privacy policy genérica copiada;
- content rating chutada;
- marcar children sem entender Families;
- ignorar pre-launch;
- publicar com P1 conhecido;
- usar staged rollout como rollback;
- achar que halt remove update instalado;
- depender de review em prazo exato;
- publicar feature inexistente na listing;
- criar conta Play no tipo errado sem avaliar ownership.

---

# 339. Anti-pattern — chave em OneDrive público/sync indiscriminado

Backup seguro não é simplesmente jogar private key em qualquer pasta sincronizada.

---

# 340. Anti-pattern — mandar keystore por e-mail

Evitar.

---

# 341. Anti-pattern — “depois trocamos applicationId”

Não depois de publicação.

---

# 342. Anti-pattern — versionCode igual ao versionName convertido

Complica sem necessidade.

---

# 343. Anti-pattern — `versionCode = 2026082201`

Pode funcionar, mas não é necessário sem estratégia formal.

---

# 344. Anti-pattern — internal build com production secrets

Não.

---

# 345. Anti-pattern — usar production track como beta

Use testing tracks.

---

# 346. Anti-pattern — closed test só burocrático

Teste de verdade.

---

# 347. Anti-pattern — ignorar tester feedback

Então os 14 dias viram teatro administrativo.

---

# 348. Anti-pattern — publicar 100% update grande sem motivo

Depois de base relevante, staged rollout é ferramenta útil.

---

# 349. Anti-pattern — 1% rollout com 10 usuários

Sem valor prático.

---

# 350. Anti-pattern — atualizar store listing antes de todos receberem update incompatível

A documentação Play recomenda cautela durante staged rollout.

Listing deve continuar coerente com versões distribuídas.

---

# 351. Anti-pattern — privacy policy em PDF

Política atual exige URL web adequada.

---

# 352. Anti-pattern — policy em Google Doc editável

Não é formato adequado.

---

# 353. Anti-pattern — Data Safety baseado apenas no nosso código

SDKs contam.

---

# 354. Anti-pattern — esquecer artifact de test track antigo

Práticas/data podem considerar versões ainda distribuídas.

Revisar tracks ativos.

---

# 355. Anti-pattern — não verificar package registration em 2026

Especialmente com prazo de setembro.

---

# 356. Anti-pattern — usar Android Developer Console separado se já distribuímos pelo Play

Quem usa Play Console pode cumprir developer verification por lá.

---

# 357. Próxima grande decisão de release

## PENDENTE

Antes da conta definitiva:

```text
PERSONAL
ou
ORGANIZATION
```

---

# 358. Segunda decisão

App signing key:

```text
Google-generated
ou
own key
```

---

# 359. Terceira decisão

Modelo de monetização:

documento 13.

---

# 360. Quarta decisão

Países e idiomas da primeira produção.

---

# 361. Quinta decisão

VersionName da primeira release pública.

---

# 362. Não tomar essas decisões agora sem necessidade

Este documento define critérios.

---

# 363. Fatos temporais verificados em 2026-08-22

### Target API

A partir de 2026-08-31:

```text
API 36+
```

para new apps/updates comuns de phone/tablet.

### Developer verification

A partir de 2026-09-30:

package/developer verification entra em vigor em lojas participantes no Brasil e outros países iniciais.

### New personal accounts

12 testers / 14 days em closed testing antes de solicitar production access.

### Data Safety

Obrigatório em closed/open/production.

### Privacy policy

Obrigatória inclusive sem coleta.

### AAB

Obrigatório para novos apps Play.

---

# 364. Esses fatos devem ser verificados novamente

## DECIDIDO

Na release real.

---

# 365. Fontes — Android App Bundle

## About Android App Bundles

https://developer.android.com/guide/app-bundle

Usado para:

- AAB;
- geração de APKs;
- requisito desde 2021;
- delivery.

Verificado em: 2026-08-22.

---

# 366. Fontes — app signing

## Sign your app

https://developer.android.com/studio/publish/app-signing

Usado para:

- app signing key;
- upload key;
- Play App Signing;
- key reset;
- multi-store;
- signing flow.

Verificado em: 2026-08-22.

---

# 367. Fontes — versioning

## Version your app

https://developer.android.com/studio/publish/versioning

Usado para:

- versionCode;
- versionName;
- max versionCode;
- upgrade ordering.

Verificado em: 2026-08-22.

---

# 368. Fontes — target API

## Meet Google Play's target API level requirement

https://developer.android.com/google/play/requirements/target-sdk

Usado para:

- API 36;
- prazo 2026-08-31;
- extension;
- target behavior changes.

Verificado em: 2026-08-22.

---

# 369. Fontes — Play target requirement

https://support.google.com/googleplay/android-developer/answer/11926878

Fonte complementar do Play Console.

---

# 370. Fontes — developer account

## Required information

https://support.google.com/googleplay/android-developer/answer/13628312

Usado para:

- personal;
- organization;
- D-U-N-S;
- contatos;
- identidade.

---

# 371. Fontes — identity verification

https://support.google.com/googleplay/android-developer/answer/10841920

Usado para:

- documento de identidade;
- organização;
- D-U-N-S.

---

# 372. Fontes — device verification

https://support.google.com/googleplay/android-developer/answer/14316361

Usado para:

- contas pessoais novas;
- aparelho físico;
- Android 10+;
- Play Console mobile.

---

# 373. Fontes — Android developer verification

https://developer.android.com/developer-verification

Usado para:

- enforcement regional;
- 2026-09-30;
- package registration;
- expansão global.

---

# 374. Fontes — package registration Play

https://support.google.com/googleplay/android-developer/answer/16984799

Usado para:

- registrar package names;
- prazo;
- remoção se não registrado.

---

# 375. Fontes — Play verification guide

https://developer.android.com/developer-verification/guides/google-play-console

Usado para:

- auto-registration de novos packages;
- Play Console como caminho de verificação.

---

# 376. Fontes — testing requirements

https://support.google.com/googleplay/android-developer/answer/14151465?hl=pt-BR

Usado para:

- personal accounts após 2023-11-13;
- 12 testers;
- 14 days;
- production access.

Verificado em: 2026-08-22.

---

# 377. Fontes — testing tracks

https://support.google.com/googleplay/android-developer/answer/9845334?hl=pt-BR

Usado para:

- internal;
- closed;
- open;
- testing strategy.

---

# 378. Fontes — prepare/roll out release

https://support.google.com/googleplay/android-developer/answer/9859348

Usado para:

- internal até 100 testers;
- track flow;
- production;
- rollout.

---

# 379. Fontes — staged rollout

https://support.google.com/googleplay/android-developer/answer/6346149

Usado para:

- updates;
- percentages;
- halt/resume;
- first release limitation.

---

# 380. Fontes — managed publishing

https://support.google.com/googleplay/android-developer/answer/9859654?hl=pt-BR

Usado para:

- control de publicação;
- review timing;
- não aplicável ao primeiro launch.

---

# 381. Fontes — Store listing

https://support.google.com/googleplay/android-developer/answer/9859152?hl=pt-BR

Usado para:

- app name;
- 30 chars;
- short 80;
- full 4000.

---

# 382. Fontes — preview assets

https://support.google.com/googleplay/android-developer/answer/9866151

Usado para:

- icon;
- feature graphic;
- screenshots;
- dimensions;
- recommendations;
- alt text.

---

# 383. Fontes — metadata

https://support.google.com/googleplay/android-developer/answer/9898842?hl=pt-BR

Usado para:

- misleading claims;
- title;
- promotion/ranking restrictions.

---

# 384. Fontes — Data Safety

https://support.google.com/googleplay/android-developer/answer/10787469?hl=pt-BR

Usado para:

- formulário;
- tracks;
- third-party SDKs;
- no-data apps;
- internal exemption.

---

# 385. Fontes — User Data / privacy

https://support.google.com/googleplay/android-developer/answer/10144311

Usado para:

- privacy policy;
- URL;
- disclosure;
- account deletion future.

---

# 386. Fontes — Content Rating

https://support.google.com/googleplay/android-developer/answer/9859655

Usado para:

- IARC;
- questionnaire;
- accuracy;
- updates.

---

# 387. Fontes — target audience

https://support.google.com/googleplay/android-developer/answer/9867159

Usado para:

- age groups;
- Families implications;
- ads/app access/privacy prerequisites.

---

# 388. Fontes — pre-launch report

https://support.google.com/googleplay/android-developer/answer/9842757?hl=pt-BR

Usado para:

- automatic device lab;
- stability;
- compatibility;
- performance;
- accessibility.

---

# 389. Fontes — pre-launch interpretation

https://support.google.com/googleplay/android-developer/answer/9844487?hl=pt-BR

Usado para:

- errors;
- warnings;
- touch targets;
- contrast;
- screenshots;
- device info.

---

# 390. Fontes — Android vitals

https://developer.android.com/games/optimize/vitals

Usado para:

- crash threshold;
- ANR threshold;
- discoverability;
- core vitals.

Verificado em: 2026-08-22.

---

# 391. Fonte complementar — Android vitals Play Console

https://support.google.com/googleplay/android-developer/answer/9844486

Usado para:

- core vitals;
- monitoring;
- bad behavior.

---

# 392. Fonte — unpublish/update

https://support.google.com/googleplay/android-developer/answer/9859350

Usado para:

- update delivery;
- unpublish;
- comportamento para usuários existentes.

---

# 393. Hierarquia de autoridade

Para release:

```text
GOOGLE PLAY POLICY / PLAY CONSOLE HELP
↓
ANDROID DEVELOPERS
↓
ESTE DOCUMENTO
↓
PROCESSO INTERNO
```

Se política atual contradizer este arquivo:

política atual vence e este arquivo deve ser atualizado.

---

# 394. Definição de “READY FOR PLAY”

## PROPOSTO

Uma versão só recebe esse estado quando:

```text
CODE COMPLETE
+
TESTS GREEN
+
PERFT GREEN
+
LINT REVIEWED
+
RELEASE BUILD VALIDATED
+
SIGNING READY
+
POLICIES COMPLETE
+
STORE LISTING TRUE
+
TEST TRACK VALIDATED
+
PRE-LAUNCH REVIEWED
+
NO P0/P1
```

---

# 395. Frase norteadora

> **Publicar não é apertar “Start rollout”. Publicar é conseguir provar qual código está sendo distribuído, quem o assinou, quais políticas ele cumpre, como foi testado e como reagiremos se algo der errado.**

---

# 396. Próximo documento

Após aprovação:

`13_MONETIZACAO.md`

Ele deverá pesquisar e definir:

- se o Gambitol deve monetizar na primeira versão ou depois;
- anúncios;
- compra única;
- produtos no app;
- subscriptions quando fizer sentido;
- Google Play Billing;
- service fees;
- regiões/preços;
- experiência sem pay-to-win;
- impacto de ads no tabuleiro;
- privacidade/Data Safety;
- famílias/classificação;
- política de pagamentos;
- estratégia gratuita/premium;
- métricas;
- alternativas de monetização sem degradar o produto.

O documento 12 define:

> **como transformar o Gambitol validado em uma release segura e distribuível.**

O documento 13 definirá:

> **se, quando e como essa distribuição pode gerar receita sem destruir a experiência que acabamos de proteger.**
