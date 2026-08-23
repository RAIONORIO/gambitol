# 13 — MONETIZAÇÃO DO GAMBITOL

> **Projeto:** Gambitol  
> **Documento:** `13_MONETIZACAO.md`  
> **Versão:** 1.0  
> **Status:** EM_REVISÃO  
> **Criado em:** 2026-08-22  
> **Última revisão:** 2026-08-22  
> **Responsabilidade:** definir os princípios, alternativas, restrições, riscos e critérios para monetizar o Gambitol sem degradar o xadrez, a experiência do usuário, a reputação do projeto ou a conformidade com o Google Play  
> **Fonte normativa para:** estratégia de monetização, critérios para app gratuito/pago, produtos únicos, assinaturas, publicidade, Google Play Billing, service fees, segurança de compras, entitlements, pricing, testes, métricas, UX de compra e limites éticos/produtivos da monetização  
> **Não cobre em detalhe:** implementação do motor, fluxo completo de release, identidade visual completa, marketing, criação de conteúdo, portfolio ou operação fiscal/contábil detalhada  
> **Documentos relacionados:** `00_GUIA_MESTRE.md`, `01_VISAO_E_OBJETIVOS.md`, `08_TESTES_E_QUALIDADE.md`, `09_UI_UX_GAMBITOL.md`, `10_ROADMAP_E_ESCOPO.md`, `11_DECISOES_TECNICAS.md`, `12_PLAY_STORE_E_RELEASE.md`, `14_CONTEUDO_E_PORTFOLIO.md`  
> **Application ID:** `br.com.raionorio.gambitol`  
> **Plataforma inicial:** Android / Google Play  
> **Situação atual:** monetização ainda não implementada  
> **Idioma principal:** Português do Brasil  

---

# 1. Objetivo

Este documento responde:

- o Gambitol precisa monetizar na primeira versão?
- app pago ou gratuito?
- anúncios fazem sentido?
- uma compra única faz sentido?
- assinatura faz sentido?
- o que pode ser vendido sem prejudicar o xadrez?
- o que nunca deve ser vendido?
- quando Google Play Billing é obrigatório?
- como evitar uma compra falsa ou duplicada?
- o que são produtos consumíveis e não consumíveis?
- quando um back-end passa a ser justificável?
- quais taxas do Google Play precisam ser consideradas?
- como pensar em preço?
- como medir monetização sem destruir retenção?
- como publicidade afeta UX, Data Safety e público-alvo?
- como testar compras sem usar dinheiro real de produção?
- como manter monetização separada do motor de xadrez?
- como monetizar sem transformar o projeto em uma loja com um tabuleiro escondido atrás?

A regra central é:

> **A monetização só é saudável quando o usuário entende claramente o que está pagando, recebe valor real e continua tendo uma boa experiência mesmo quando decide não pagar.**

---

# 2. Monetização não é requisito do motor

## DECIDIDO

O motor de xadrez não conhece:

- preço;
- compra;
- assinatura;
- anúncio;
- SKU;
- BillingClient;
- Google Play;
- entitlement premium.

O motor continua sendo:

```text
JAVA PURO
+
REGRAS DE XADREZ
```

---

# 3. Monetização não define legalidade

## PROIBIDO

Nunca:

```text
if (isPremium) {
    allowCastling();
}
```

Nunca:

- vender movimento legal;
- vender regra;
- vender promoção correta;
- limitar xeque-mate;
- limitar jogadas por pagamento.

---

# 4. Sem pay-to-win

## DECIDIDO COMO PRINCÍPIO

O Gambitol não deve vender vantagem competitiva.

Isso significa não vender:

- movimento extra;
- informação oculta em partida competitiva;
- alteração de relógio a favor de quem paga;
- capacidade de desfazer em modo ranqueado só para premium;
- IA adversária artificialmente pior em favor do pagante em competição;
- qualquer benefício que altere a integridade de uma partida entre jogadores.

---

# 5. Valor pago pode existir fora da integridade do xadrez

Exemplos conceituais possíveis:

- temas;
- conjuntos de peças;
- sons;
- personalizações;
- remoção de anúncios;
- recursos avançados não competitivos;
- análise futura;
- estatísticas avançadas;
- recursos educacionais;
- conveniências que não alterem regras competitivas.

Nenhum desses itens está automaticamente aprovado.

---

# 6. Monetização não bloqueia a primeira versão jogável

## DECIDIDO

Conforme o roadmap:

```text
MOTOR CORRETO
↓
JOGO JOGÁVEL
↓
UX
↓
RELEASE
↓
MONETIZAÇÃO QUANDO JUSTIFICADA
```

Não inverter.

---

# 7. Primeira versão sem monetização continua válida

## DECIDIDO

É perfeitamente aceitável publicar uma versão inicial:

```text
gratuita
+
sem anúncios
+
sem compras
```

para validar:

- produto;
- estabilidade;
- experiência;
- interesse;
- reputação;
- reviews;
- retenção.

---

# 8. Monetização precoce pode criar dívida

Adicionar billing cedo introduz:

- dependência;
- estados assíncronos;
- compras pendentes;
- restoration;
- refunds;
- segurança;
- privacy;
- suporte;
- testes;
- políticas;
- métricas;
- possíveis serviços remotos.

Por isso não é “só colocar um botão Comprar”.

---

# 9. Estratégia de monetização é decisão de produto

Não deve ser tomada apenas por:

> “todo app tem anúncio”.

Perguntar:

- qual valor existe?
- quem pagaria?
- por quê?
- quando?
- o que o usuário gratuito recebe?
- o que o usuário pago recebe?
- qual custo técnico?
- qual impacto na confiança?

---

# 10. Modelos avaliados

Este documento analisa:

1. app totalmente gratuito;
2. app pago no download;
3. gratuito + anúncios;
4. gratuito + compra única premium;
5. gratuito + remoção de anúncios;
6. gratuito + cosméticos;
7. gratuito + recursos premium;
8. assinatura;
9. combinação híbrida.

---

# 11. Modelo A — totalmente gratuito

## VANTAGENS

- menor fricção de aquisição;
- simples tecnicamente;
- zero billing;
- zero suporte de compras;
- menor Data Safety complexity;
- ótimo para portfólio e validação.

## DESVANTAGENS

- não gera receita direta;
- custos futuros ficam sem financiamento direto.

---

# 12. Quando o gratuito faz sentido

Especialmente:

- primeira versão;
- fase de aprendizado;
- base de usuários pequena;
- produto ainda sem diferenciais pagos claros.

---

# 13. Modelo B — app pago no download

Usuário paga antes de instalar.

---

# 14. Vantagens do app pago

- modelo simples de entender;
- sem anúncios;
- sem store dentro do app;
- receita por aquisição;
- experiência limpa.

---

# 15. Desvantagens do app pago

- aumenta fricção antes do usuário experimentar;
- reduz potencial de downloads;
- exige definir preço antes de conhecer valor percebido;
- dificulta aquisição orgânica em mercados sensíveis a preço.

---

# 16. Restrição crítica do Google Play

## FATO ATUAL

O Google Play permite:

```text
PAID → FREE
```

Mas, depois que um app foi oferecido como gratuito:

```text
FREE → PAID
```

não é permitido para o mesmo app/package.

Para cobrar download posteriormente, seria necessário criar outro app com outro package name.

Fonte:

https://support.google.com/googleplay/android-developer/answer/6334373

Verificado em: 2026-08-22.

---

# 17. Consequência estratégica

A decisão:

```text
“lançar gratuito”
```

fecha a possibilidade de transformar posteriormente aquele mesmo package em app pago upfront.

Mas NÃO impede:

- compras no app;
- assinaturas futuras;
- anúncios;
- compra premium.

---

# 18. Não escolher app pago por medo dessa irreversibilidade

A escolha depende do modelo de negócio.

Só registrar a consequência.

---

# 19. Modelo C — gratuito + anúncios

O usuário joga sem pagar.

A receita vem de publicidade.

---

# 20. Vantagens

- baixa barreira de entrada;
- monetiza usuários não pagantes;
- pode financiar um app com base ampla.

---

# 21. Desvantagens

- SDK adicional;
- coleta de dados potencial;
- Data Safety mais complexo;
- impacto visual;
- consumo de rede/bateria;
- políticas de publicidade;
- risco de anúncios inadequados;
- possível degradação da marca premium.

---

# 22. Anúncio não pertence ao tabuleiro

## PRINCÍPIO PROPOSTO

Não colocar publicidade em posição que:

- reduza o board;
- gere toque acidental;
- distraia cálculo;
- cubra peças;
- mude layout durante partida.

---

# 23. Banner perto do board

## DESACONSELHADO

Especialmente em telefone.

Razões:

- espaço é crítico;
- board é elemento dominante;
- risco de toque;
- ruído visual.

---

# 24. Interstitial durante uma partida

## PROIBIDO COMO DIREÇÃO DE UX

Nunca entre:

```text
selecionar peça
↓
ANÚNCIO
↓
mover peça
```

---

# 25. Política atual de melhores experiências de anúncios

Google Play proíbe intersticiais inesperados em tela cheia que interrompem ações do usuário.

Exemplos explicitamente problemáticos incluem anúncios que surgem durante o jogo ou imediatamente quando o usuário espera iniciar/interagir com conteúdo.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9857753

Verificado em: 2026-08-22.

---

# 26. Anúncio na inicialização

## EVITAR / POLÍTICA RESTRITIVA

A política do Play também restringe publicidade intersticial inesperada imediatamente após a inicialização e antes da experiência esperada.

---

# 27. Ponto natural para interstitial

## FUTURO / CANDIDATO

Se publicidade for aprovada:

um ponto menos intrusivo seria depois de uma partida concluída, antes de uma ação opcional futura.

Mesmo assim:

- frequência limitada;
- fechamento claro;
- não em toda partida;
- teste de UX.

---

# 28. “Depois do placar” como exemplo de menor interferência

A própria política do Play usa situações como após uma tela de pontuação como exemplo de contexto em que um interstitial pode não interromper a ação corrente.

Isso não obriga o Gambitol a usar.

---

# 29. Rewarded ads

## FUTURO / CANDIDATO

Usuário escolhe assistir em troca de algo claramente definido.

---

# 30. Rewarded não pode virar pay-to-win indireto

Recompensas adequadas podem ser:

- cosmético temporário;
- tema de teste;
- item visual não competitivo.

Evitar:

- movimento extra;
- vantagem ranqueada;
- manipulação de relógio.

---

# 31. Rewarded precisa ser opt-in

O usuário precisa escolher.

---

# 32. Ads e público infantil

Se o público-alvo incluir crianças:

o conjunto de políticas e SDKs permitidos fica mais restritivo.

Logo:

público-alvo precisa ser decidido antes da publicidade.

---

# 33. Ads e Data Safety

SDK de publicidade pode:

- coletar identificadores;
- dados de uso;
- diagnóstico;
- localização aproximada;
- outras categorias conforme SDK/configuração.

Nunca preencher Data Safety apenas olhando nosso código.

---

# 34. Ads e privacidade

A inclusão de anúncios pode transformar um app local simples em um produto que transmite dados.

Essa mudança precisa de:

- decisão técnica;
- privacy review;
- Data Safety update;
- policy review.

---

# 35. Modelo D — gratuito + compra única premium

Usuário instala gratuitamente e pode adquirir permanentemente um upgrade.

---

# 36. Produto não consumível

A documentação atual do Play define produto único não consumível como uma compra única associada permanentemente à conta, apropriada para:

- upgrade premium;
- desbloqueio permanente;
- versão sem anúncios.

Fonte:

https://developer.android.com/google/play/billing/one-time-products

Verificado em: 2026-08-22.

---

# 37. Esse modelo combina naturalmente com um jogo pequeno

## PROPOSTO COMO CANDIDATO FORTE PÓS-V1

Exemplo de filosofia:

```text
CORE DE XADREZ GRATUITO
+
UPGRADE PREMIUM PERMANENTE
```

O conteúdo exato do premium ainda não está definido.

---

# 38. O premium não pode consertar o app gratuito

Versão gratuita precisa ser:

- correta;
- jogável;
- digna;
- não deliberadamente irritante.

---

# 39. Premium pode agregar

Futuramente:

- temas adicionais;
- peças adicionais;
- personalização;
- recursos analíticos;
- estatísticas;
- remover anúncios se anúncios existirem.

---

# 40. Produto não consumível é mais simples que assinatura

Ainda exige:

- Billing;
- restoration;
- acknowledgement;
- entitlement;
- testes;
- suporte.

Mas não possui renovação recorrente.

---

# 41. Modelo E — compra “remover anúncios”

## CANDIDATO

Se anúncios forem aprovados:

uma compra permanente para removê-los pode ser coerente.

---

# 42. Problema lógico

Não devemos adicionar anúncios ruins só para vender a solução.

---

# 43. Regra

## DECIDIDO COMO PRINCÍPIO

“Remover anúncios” só tem valor legítimo se os anúncios já forem aceitáveis na versão gratuita.

---

# 44. Modelo F — cosméticos

Produtos visuais:

- board themes;
- piece sets;
- sons;
- backgrounds.

---

# 45. Cosméticos preservam competitividade

Por isso são candidatos naturais.

---

# 46. Cosméticos também precisam qualidade

Não vender asset genérico mal acabado.

---

# 47. Licença

Todo asset premium precisa:

- ser próprio;
- ou licenciado comercialmente.

---

# 48. Produto consumível

Compra que pode ser usada e comprada novamente.

Exemplos oficiais:

- moeda;
- vida;
- boosts.

---

# 49. Consumíveis no Gambitol

## NÃO RECOMENDADOS INICIALMENTE

Não há necessidade natural de:

- energia;
- vidas;
- moedas.

Criar uma economia artificial só para monetizar acrescentaria complexidade sem melhorar xadrez.

---

# 50. Virtual currency

## NÃO PROPOSTA

Evitar “Gambicoins”, “gold”, “gems” ou equivalente sem problema real a resolver.

---

# 51. Consumível para tema temporário

Também parece artificial.

Preferir compra clara.

---

# 52. Modelo G — recursos premium

Pode fazer sentido quando o produto crescer.

Exemplos futuros:

- análise avançada;
- histórico expandido;
- export;
- estatísticas;
- treino.

---

# 53. Separar recurso de regra básica

O premium não pode bloquear:

- roque;
- promoção;
- empate;
- movimentos legais.

---

# 54. Export/analysis

São funcionalidades adicionais.

Podem ser premium futuramente.

---

# 55. Modelo H — assinatura

Cobrança recorrente.

---

# 56. Política crítica de assinaturas

## FATO ATUAL

Google Play exige que assinaturas forneçam:

```text
valor sustentado ou recorrente
```

durante todo o período.

Benefício essencialmente único não pode ser disfarçado como assinatura.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9900533

Verificado em: 2026-08-22.

---

# 57. Consequência para o Gambitol

Uma assinatura apenas para:

```text
“desbloquear tema dourado”
```

não é um bom encaixe.

É benefício permanente/único.

Produto não consumível é mais apropriado.

---

# 58. Assinatura só se houver valor contínuo real

Exemplos futuros possíveis:

- conteúdo de treinamento continuamente atualizado;
- análise contínua com serviço;
- pacote recorrente de conteúdo;
- funcionalidades cloud persistentes;
- serviço multiplayer premium sustentado.

Mesmo esses precisam avaliação.

---

# 59. Assinatura não deve existir só porque receita recorrente é atraente

## PRINCÍPIO

Receita recorrente exige valor recorrente.

---

# 60. Transparência de assinatura

Play exige divulgar claramente:

- preço;
- ciclo de cobrança;
- renovação automática;
- termos;
- se é necessária para usar o app.

---

# 61. Cancelamento

Apps com assinatura precisam fornecer uma forma clara de o usuário encontrar como gerenciar/cancelar e acesso fácil a um método online de cancelamento.

---

# 62. Uninstall não cancela assinatura

Logo, a UX precisa deixar isso claro quando relevante.

---

# 63. Trials

## FUTURO

Se assinatura existir:

trial precisa informar claramente:

- duração;
- preço posterior;
- renovação.

---

# 64. Annual pricing

Não destacar apenas equivalente mensal escondendo cobrança anual.

Política do Play alerta contra isso.

---

# 65. Modelo I — híbrido

Exemplo conceitual:

```text
gratuito
+
ads discretos
+
compra não consumível para remover ads/premium
+
assinatura apenas se existir serviço recorrente real
```

É possível.

Mas aumenta complexidade.

---

# 66. Complexidade da monetização cresce combinatoriamente

Mais modelos significam:

- mais estados;
- mais preços;
- mais UX;
- mais suporte;
- mais testes;
- mais políticas.

---

# 67. Recomendação estratégica inicial

## PROPOSTO PARA APROVAÇÃO

Para o estágio atual:

```text
V1:
gratuita
sem anúncios
sem Billing
```

Depois da validação:

```text
avaliar compra única não consumível
```

e só depois:

```text
avaliar publicidade ou assinatura
```

se dados reais justificarem.

---

# 68. Por que essa proposta

Preserva:

- foco no aprendizado;
- arquitetura simples;
- UI premium;
- Data Safety simples;
- release simples;
- reputação;
- capacidade de testar valor antes de cobrar.

---

# 69. Isso não abandona o objetivo de receita

Adia a monetização para o momento em que sabemos:

- o que usuários valorizam;
- qual feature merece preço;
- qual base existe.

---

# 70. Play Billing é obrigatório para digital goods

## FATO ATUAL

Para apps distribuídos pelo Google Play que cobram por recursos/conteúdo digital dentro do app:

o Google Play Billing é obrigatório, exceto nas exceções/programas/regiões permitidos pelas políticas.

Fonte:

https://support.google.com/googleplay/android-developer/answer/9858738

---

# 71. Exemplos que exigem Billing

- premium;
- remover anúncios;
- temas digitais;
- recursos;
- subscriptions;
- itens digitais.

---

# 72. Produto físico não usa Play Billing

Não relevante ao Gambitol.

---

# 73. Pagamento entre pessoas

Play Billing não deve ser usado.

Não relevante ao V1.

---

# 74. External billing / external offers

## NÃO ADOTAR AGORA

Em algumas regiões existem programas alternativos.

Eles possuem:

- regras;
- APIs;
- reporting;
- fees;
- UX obrigatório.

Para um jogo novo sem monetização:

complexidade desnecessária.

---

# 75. Região altera regras financeiras

Em 2026 o Google está alterando o modelo de service fees em rollout regional.

Logo:

não congelar uma única porcentagem universal.

---

# 76. Service fee não é uma constante

## FATO ATUAL

Google declara explicitamente que não existe uma única taxa de serviço.

Ela depende de:

- região;
- programa;
- tipo de transação;
- receita;
- status de instalação em algumas regiões;
- billing method.

Fonte:

https://support.google.com/googleplay/android-developer/answer/112622

Verificado em: 2026-08-22.

---

# 77. Mudanças de 2026

A nova estrutura começou em:

```text
30/06/2026
```

para:

- EEE;
- Reino Unido;
- EUA.

---

# 78. Próxima expansão anunciada

Em:

```text
30/09/2026
```

para:

- Austrália;
- Japão;

e programas relacionados em regiões anunciadas.

---

# 79. Brasil em 2026-08-22

O Brasil não aparece nas primeiras regiões de rollout informadas nessa documentação.

Google informa que, para mercados restantes, a estrutura anterior continua até o novo modelo ser lançado globalmente.

---

# 80. Estrutura atual dos mercados restantes

No modelo ainda vigente para mercados restantes:

- nível de serviço de 15% pode aplicar 15% ao primeiro US$ 1 milhão anual para desenvolvedores inscritos;
- receita acima disso segue taxa superior conforme programa;
- assinaturas de renovação automática possuem estrutura própria.

## IMPORTANTE

Não calcular o business case do Gambitol com uma taxa fixa hoje.

Rever no lançamento real.

---

# 81. Programa de nível de taxa de serviço

Pode exigir inscrição e associação de contas.

Não assumir adesão automática.

---

# 82. Revenue group

Contas associadas podem influenciar cálculo do limite de receita.

Se houver múltiplas contas Play relacionadas:

avaliar.

---

# 83. Service fee não é o único custo

Também podem existir:

- impostos;
- câmbio;
- refunds;
- chargebacks;
- marketing;
- backend;
- suporte;
- SDKs;
- design;
- conteúdo.

---

# 84. Receita bruta ≠ lucro

## REGRA

Não usar:

```text
downloads × preço
```

como “lucro”.

---

# 85. Pricing

Preço deve considerar:

- valor percebido;
- mercado;
- concorrência;
- poder de compra;
- service fee;
- impostos;
- conversão;
- positioning.

---

# 86. Não definir preço neste documento

## PENDENTE

Sem produto premium definido, qualquer valor seria chute.

---

# 87. Pricing por país

Play permite preços localizados/regionalizados.

Não usar um valor em reais convertido mecanicamente para todos os mercados sem revisar.

---

# 88. Purchasing power

Pode justificar valores diferentes.

---

# 89. Price experiments

## FUTURO

Quando houver usuários suficientes.

---

# 90. Preço de app e preço de IAP são decisões diferentes

---

# 91. Free app + premium

Permite usuário provar o produto.

---

# 92. Paid app

Exige confiança antes da instalação.

---

# 93. Reputação do portfólio

Um jogo gratuito bem-feito pode gerar mais uso e prova social.

---

# 94. Receita direta não é única forma de retorno

Gambitol também pode gerar:

- portfolio;
- oportunidades;
- conteúdo;
- reputação;
- experiência.

---

# 95. Monetização indireta

## CONCEITO

O projeto pode gerar valor econômico sem cobrar dentro do app.

Exemplos:

- demonstrar competência;
- atrair cliente;
- abrir oportunidade profissional;
- conteúdo educacional.

---

# 96. Isso não deve virar propaganda dentro do jogo

Portfolio fica fora da partida.

---

# 97. Play Billing Library

Se Billing for implementado:

usar versão suportada na data.

---

# 98. Versão atual em 2026-08-22

A documentação oficial mostra:

```text
Google Play Billing Library 9.1.0
```

lançada em 18/06/2026.

Fonte:

https://developer.android.com/google/play/billing/release-notes

---

# 99. Prazo crítico da Billing Library 7

A versão 7 deixa de ser aceita para novos apps/updates em:

```text
31/08/2026
```

salvo extensão até 01/11/2026.

---

# 100. Billing Library 8

Prazo atual para novos apps/updates:

```text
31/08/2027
```

---

# 101. Billing Library 9

Prazo atual:

```text
31/08/2028
```

Fonte:

https://developer.android.com/google/play/billing/deprecation-faq

Verificado em: 2026-08-22.

---

# 102. Regra do Gambitol

## DECIDIDO

Quando Billing entrar:

consultar versão estável suportada naquele momento.

Não implementar versão 7 apenas porque um tutorial antigo usa.

---

# 103. Não adicionar Billing dependency enquanto não houver produto

## DECIDIDO COMO PRINCÍPIO

Menos dependências.

Menor superfície.

---

# 104. BillingClient

É a interface principal entre o app e Google Play Billing.

---

# 105. Billing connection

A documentação atual recomenda manter uma conexão ativa adequada para processar eventos de compra.

Implementação só será desenhada quando a feature entrar.

---

# 106. Produto no Play precisa de ID

IDs dos produtos possuem regras e são persistentes.

---

# 107. Não inventar IDs agora

## DECIDIDO

Quando primeiro produto real for aprovado:

o nome/ID será decidido explicitamente.

Não criar unilateralmente:

```text
premium_upgrade
remove_ads
```

como IDs reais do projeto.

---

# 108. Product IDs são difíceis de alterar

Especialmente subscriptions.

Logo, nome merece decisão.

---

# 109. Entitlement

## CONCEITO IMPORTANTE

A compra não é a feature.

A compra concede um direito:

```text
ENTITLEMENT
```

Exemplo conceitual:

```text
purchase verified
↓
user owns entitlement
↓
UI unlocks feature
```

---

# 110. UI não deve perguntar apenas “purchase object exists?”

Precisamos de uma camada clara de entitlement.

---

# 111. Entitlement não pertence ao engine

Permanece app/business layer.

---

# 112. Compras precisam ser restauradas

Usuário:

- troca aparelho;
- reinstala;
- limpa dados.

Produto não consumível continua associado à conta Play.

O app precisa reconhecer.

---

# 113. Offline entitlement

## PENDENTE

Se premium permanente existir:

precisamos definir comportamento quando Play não está disponível temporariamente.

---

# 114. Não bloquear usuário pago por falha transitória

Idealmente ter estado local confiável sincronizado.

Mas segurança precisa ser considerada.

---

# 115. Purchase lifecycle

Fluxo simplificado:

```text
mostrar produto
↓
iniciar billing flow
↓
usuário paga
↓
compra retorna
↓
verificar
↓
grant entitlement
↓
acknowledge/consume
↓
persistir/sincronizar
↓
confirmar UI
```

---

# 116. PENDING

## CONCEITO CRÍTICO

Uma compra pode ficar:

```text
PENDING
```

---

# 117. Não conceder entitlement em PENDING

A documentação atual é explícita:

conceder somente quando:

```text
PURCHASED
```

---

# 118. Compra pending precisa UX

Mostrar:

> pagamento pendente

não:

> compra concluída

---

# 119. Acknowledge

Depois de conceder entitlement:

a compra precisa ser confirmada ao Google.

---

# 120. Prazo de acknowledgement

Atualmente:

```text
até 3 dias
```

Caso contrário, a compra pode ser automaticamente reembolsada e revogada.

Fonte:

https://developer.android.com/google/play/billing/integrate

---

# 121. Consumível

É consumido.

Isso permite nova compra.

---

# 122. Não consumível

É acknowledged.

---

# 123. Subscription inicial

Também precisa acknowledgement quando há novo purchase token.

Renovações posteriores não seguem exatamente o mesmo fluxo de acknowledgement.

---

# 124. Idempotência

## OBRIGATÓRIO COMO PRINCÍPIO

Processar o mesmo purchase token duas vezes não pode conceder benefício duplicado indevidamente.

---

# 125. Purchase token

É identificador crítico para verificar/acompanhar compra.

---

# 126. Não usar orderId como primary key

A documentação atual alerta que nem toda compra possui orderId.

---

# 127. Segurança de compras

## RECOMENDAÇÃO OFICIAL

Verificar compras em back-end seguro quando possível.

Fonte:

https://developer.android.com/google/play/billing/security

---

# 128. Conflito aparente com “V1 sem backend”

Não há conflito.

Conclusão:

```text
V1 sem monetização
→ sem backend

monetização robusta futura
→ backend pode passar a ser justificado
```

---

# 129. Essa é uma boa razão para monetização ser pós-V1

Evita introduzir infraestrutura antes da necessidade.

---

# 130. Verificação server-side

Fluxo recomendado:

```text
app envia purchaseToken
↓
backend
↓
Google Play Developer API
↓
verifica legitimidade
↓
backend registra
↓
grant entitlement
↓
acknowledge/consume
```

---

# 131. Backend também recebe lifecycle events

RTDN:

```text
Real-time Developer Notifications
```

podem informar:

- renewals;
- cancellations;
- refunds;
- purchase changes.

---

# 132. RTDN não é necessária sem monetização

---

# 133. Subscription praticamente exige lifecycle robusto

Mais um motivo para não começar por ela.

---

# 134. Client-only purchase handling

A documentação permite client-side acknowledgement em apps sem backend.

Mas server-side oferece maior proteção.

---

# 135. Decisão futura

## PENDENTE

Se o primeiro produto pago for uma compra única simples:

avaliar:

- client-only inicialmente;
- backend desde o começo.

Drivers:

- valor do produto;
- risco de fraude;
- esforço;
- roadmap de contas/cloud.

---

# 136. Não usar obfuscation como segurança principal

APK está no aparelho do usuário.

Qualquer lógica crítica client-side pode ser alterada por atacante suficientemente motivado.

---

# 137. R8 não substitui validação

---

# 138. Play Integrity não substitui Billing verification

Ferramentas diferentes.

---

# 139. Fraud risk de cosmético barato

Pode ser baixo.

---

# 140. Fraud risk de subscription cara

Maior.

---

# 141. Refunds

Precisamos definir como entitlement reage.

---

# 142. Voided Purchases

Google fornece API para compras anuladas.

Backend futuro pode usar.

---

# 143. Chargebacks

Em 2026, Google adicionou novos fluxos/APIs para alguns casos de chargeback review.

Não implementar até necessidade.

---

# 144. Finance reporting

Play Console fornece:

- sales;
- earnings;
- orders;
- financial reports.

---

# 145. Revenue tracking

Não criar banco paralelo se Play já fornece relatório suficiente para início.

---

# 146. Entitlement tracking

É diferente.

Precisa existir no sistema se compra controla feature.

---

# 147. Metrics de monetização

Não olhar só receita.

---

# 148. Métricas de produto

- installs;
- active users;
- retention;
- sessions;
- completed games;
- crash-free;
- rating.

---

# 149. Métricas de monetização

- conversion;
- ARPPU;
- ARPU;
- refund rate;
- ad revenue;
- purchase success;
- cancellation/churn se subscription.

---

# 150. ARPU

Average Revenue Per User.

---

# 151. ARPPU

Average Revenue Per Paying User.

---

# 152. Conversion

Percentual de usuários que compram.

---

# 153. Revenue não pode ser analisada sem retenção

Se receita sobe e retenção despenca:

podemos estar queimando o produto.

---

# 154. Ad impressions não são objetivo do jogo

---

# 155. Métrica de experiência

Tempo até primeira jogada pode ser mais relevante que impressões.

---

# 156. Rewarded completion rate

Só se rewarded existir.

---

# 157. Subscription churn

Só se subscription existir.

---

# 158. Refund rate

Sinal importante de:

- confusão;
- bug;
- mismatch de valor.

---

# 159. Support tickets pós-compra

Também.

---

# 160. Purchase restore failures

P1 para usuário pago.

---

# 161. Compra que cobra e não desbloqueia

## P0/P1

Altíssima prioridade.

---

# 162. Feature desbloqueada sem compra

Pode ser bug financeiro.

Prioridade depende do impacto.

---

# 163. Testes de Billing

## OBRIGATÓRIO QUANDO IMPLEMENTADO

---

# 164. O que testar

- product query;
- billing unavailable;
- successful purchase;
- canceled flow;
- pending purchase;
- duplicate callback;
- acknowledgement;
- restore;
- refund/revoke;
- offline;
- app restart;
- Play account switch quando aplicável.

---

# 165. License testers

Google Play oferece mecanismos de teste de Billing.

Usar contas de teste.

---

# 166. Internal track

Pode ser usado para compras de teste.

---

# 167. Não usar cartão real como único teste

---

# 168. Test purchases precisam ser distinguidas

Não misturar relatório/test data.

---

# 169. Test product IDs

Usar mecanismos oficiais.

Não inventar produto de produção só para experimentar.

---

# 170. Teste de reinstalação

Para non-consumable:

- comprar;
- apagar app;
- reinstalar;
- entitlement restaurado.

---

# 171. Teste de device change

Quando possível.

---

# 172. Teste pending

Essencial em mercados/formas de pagamento onde pending pode ocorrer.

---

# 173. Teste cancel

Usuário fecha o billing sheet.

App não deve mostrar erro dramático.

---

# 174. USER_CANCELED

É estado normal.

---

# 175. Billing unavailable

App gratuito deve continuar jogável se premium não for essencial.

---

# 176. Network failure

Não corromper entitlement.

---

# 177. Retry

Com idempotência.

---

# 178. Purchase UI

## PRINCÍPIO

Preço deve vir do Google Play.

Não hardcode:

```text
R$ 9,90
```

na lógica.

---

# 179. Localized price

Usar informações fornecidas pelo Play.

---

# 180. Não converter moeda manualmente

---

# 181. Purchase button

Mostrar:

- produto;
- benefício;
- preço.

---

# 182. Não usar dark pattern

Exemplos proibidos como direção:

- esconder “não”;
- botão falso;
- contagem regressiva falsa;
- preço recorrente escondido;
- checkbox pré-marcado confuso.

---

# 183. Assinatura precisa clareza maior

Especialmente:

- periodicidade;
- renovação;
- trial;
- cancelamento.

---

# 184. Premium lifetime

Se for realmente vitalício/permanente para aquele produto:

dizer claramente.

---

# 185. “Lifetime” exige cuidado

Se o produto/serviço terminar um dia:

promessa pode ser problemática.

Melhor:

```text
compra única
acesso permanente ao recurso enquanto suportado pelo app
```

dependendo da política/legal.

---

# 186. “Apoiar o projeto”

Pode ser uma motivação legítima.

Mas precisa haver produto/benefício claro se for IAP.

Donations/tips possuem regras e implicações que precisam ser verificadas antes de implementar.

---

# 187. Gorjeta/donation

## NÃO IMPLEMENTAR SEM PESQUISA ESPECÍFICA

Não assumir que Play Billing é um botão de doação genérico.

---

# 188. Paywall

Se existir:

não bloquear acesso ao core prometido gratuito.

---

# 189. Soft paywall

Pode mostrar benefícios premium sem impedir partida.

---

# 190. Frequência de promoção

Não abrir paywall:

- todo launch;
- toda partida;
- toda vitória.

---

# 191. Momento natural

Configuração/loja pode ser acessada voluntariamente.

---

# 192. Cross-sell dentro da partida

Evitar.

---

# 193. Premium badge

Discreto.

---

# 194. Locked cosmetic

Pode mostrar preview sem atrapalhar.

---

# 195. Não mostrar 40 cadeados na tela principal

Isso comunica “loja com xadrez”, não “xadrez premium”.

---

# 196. UX premium do usuário gratuito

## DECIDIDO COMO PRINCÍPIO

O app gratuito não deve parecer propositalmente inferior em qualidade visual.

---

# 197. Free core

Precisa:

- funcionar;
- ser bonito;
- ser correto;
- não ser humilhado por banners.

---

# 198. Premium é adição, não resgate

---

# 199. Assinatura futura

Só se produto evoluir para serviço.

---

# 200. Conteúdo educacional recorrente

Um exemplo potencial de valor contínuo.

Mas exigiria:

- produção de conteúdo;
- cadence;
- moderation;
- support.

---

# 201. Cloud analysis

Pode justificar subscription se gerar custo recorrente.

---

# 202. Online service

Também.

---

# 203. Local themes

Não justificam subscription.

---

# 204. Sem subscription para “sustentar servidor” se não há servidor

---

# 205. Service fee e pricing precisam ser atualizados

## REGRA

Antes de lançar qualquer produto pago:

consultar novamente:

- service fees;
- Payments policy;
- Billing Library requirements;
- taxes;
- price ranges.

---

# 206. Mudanças de 2026 tornam isso especialmente necessário

Modelo de fee está em transição regional.

---

# 207. Não colocar “taxa 15%” como pressuposto financeiro eterno

---

# 208. Brasil pode receber nova estrutura depois

Planejar margem.

---

# 209. Pricing net revenue

Ao definir preço:

calcular cenários.

Exemplo conceitual:

```text
gross
- Play fees
- taxes
- refunds
- operational costs
= net
```

---

# 210. Não usar valor exato sem perfil fiscal real

---

# 211. Tributação

## FORA DO ESCOPO TÉCNICO DETALHADO

Receita pode gerar obrigações:

- fiscais;
- empresariais;
- contábeis.

Antes de monetizar de verdade:

validar com profissional/contabilidade conforme titular da conta.

---

# 212. Merchant profile

Play pode exigir perfil de pagamentos.

---

# 213. Titularidade

Precisa ser consistente com decisão de conta:

- pessoal;
- organização.

---

# 214. Monetização e Yggdra/empresa

## NÃO DECIDIDO NESTE DOCUMENTO

O owner financeiro/legal do Gambitol deve ser definido explicitamente antes de ativar payments.

---

# 215. Não usar conta errada e “transferir depois” como plano

---

# 216. Ads SDK selection

## PENDENTE

AdMob é candidato natural, mas não foi aprovado.

---

# 217. Seleção de SDK de ads merece decisão técnica

Drivers:

- policy;
- Data Safety;
- consent;
- revenue;
- size;
- stability;
- child policy;
- support.

---

# 218. SDK crash afeta nosso vitals

Mesmo sendo terceiro.

---

# 219. Ads latency

Não pode bloquear partida.

---

# 220. Ads offline

App precisa continuar funcionando.

---

# 221. Ad load failure

Não mostrar espaço vazio gigante.

---

# 222. No-fill

Estado normal.

---

# 223. Ad frequency cap

Se interstitial:

definir.

---

# 224. Não usar anúncio a cada mate

Sem teste.

---

# 225. Rewarded failure

Não prometer recompensa e negar indevidamente.

---

# 226. Reward validation

Para reward valioso, server-side verification pode ser necessária.

---

# 227. Consent

Dependendo de região/SDK/dados:

pode haver requisitos de consentimento/privacy.

Não implementar ads sem pesquisar isso na data.

---

# 228. EEE/UK/US billing changes

Alternativas de billing externas mudaram em 2026.

Não são prioridade do Gambitol inicial.

---

# 229. Regional branching de Billing

Evitar adicionar complexidade antes de haver receita relevante.

---

# 230. Play Games Level Up

Em 2026 o Google anunciou modelos de fee menores associados a programas de qualidade em regiões específicas/rollout.

## FUTURO

Pode ser interessante quando Gambitol for elegível.

Não monetizar pensando em programa ainda não aplicável globalmente.

---

# 231. Quality program não muda princípio

Produto precisa ser bom primeiro.

---

# 232. Monetização e reviews

Publicidade agressiva pode afetar reviews.

---

# 233. Reviews afetam descoberta/reputação

Logo, receita imediata pode custar crescimento.

---

# 234. Lifetime value

Futuro.

Não temos dados.

---

# 235. CAC

Customer Acquisition Cost.

Sem marketing pago, pode ser zero financeiro mas não zero em tempo.

---

# 236. LTV > CAC

Só se campanha futura existir.

---

# 237. Receita por download

Não é métrica completa.

---

# 238. Conversion funnel premium

Futuro:

```text
install
↓
engage
↓
see premium
↓
view product
↓
purchase flow
↓
success
```

---

# 239. Não mostrar premium antes de o usuário conhecer o jogo

Possível princípio de UX.

---

# 240. Momento de upsell

Após valor percebido.

---

# 241. First launch paywall

## NÃO RECOMENDADO PARA FREE CORE

---

# 242. Premium tab em settings/menu

Mais natural.

---

# 243. Post-game promotion

Pode ser testada futuramente.

Sem ser agressiva.

---

# 244. Promoção após vitória

Cuidado para não parecer prêmio falso.

---

# 245. Discount

Play suporta offers em diferentes produtos.

Não implementar antes de preço base estável.

---

# 246. Sale permanente

Se tudo está sempre “50% off”, não é promoção honesta.

---

# 247. Price anchoring

Usar com transparência.

---

# 248. Subscription intro offer

Futuro.

---

# 249. Trial abuse

Play possui controles.

Não é preocupação agora.

---

# 250. Family sharing

Pode depender de tipo de produto/política.

Pesquisar na implementação.

---

# 251. Refund handling

Usuário pode receber refund por mecanismos Play.

Entitlement precisa refletir.

---

# 252. Support

Precisa haver canal para problemas de compra.

---

# 253. “Google resolve tudo”

Não.

Usuário vai falar conosco.

---

# 254. Restore purchase button

Pode ser útil mesmo que queries automáticas sejam feitas.

Depende da UX/API.

---

# 255. Compra ligada à Google account

Não criar login próprio só para Billing se não for necessário.

---

# 256. Mas backend pode precisar identidade

Se entitlements precisarem sincronizar entre plataformas/contas:

aí login pode ser justificado.

---

# 257. Cross-platform future

Se Gambitol for iOS:

Play purchase não vira automaticamente entitlement cross-platform.

Precisa estratégia de account/backend.

---

# 258. Isso é futuro

Não projetar agora.

---

# 259. Product catalog

Começar pequeno.

---

# 260. Um produto bom é melhor que 20 SKUs

---

# 261. Loja interna

## NÃO NECESSÁRIA PARA UM ÚNICO PRODUTO

Pode haver tela premium simples.

---

# 262. Shop architecture

Só quando catálogo justificar.

---

# 263. Entitlement enum

## NÃO NOMEAR AGORA

O conceito existe.

Nome/classe real só quando implementação chegar.

---

# 264. Billing package

## NÃO NOMEAR AGORA

---

# 265. Product IDs

## NÃO NOMEAR AGORA

---

# 266. Monetization module

## NÃO CRIAR AGORA

---

# 267. Backend service

## NÃO CRIAR AGORA

---

# 268. PurchaseRepository

## NÃO CRIAR AGORA

Repository só se arquitetura real justificar.

---

# 269. Use case de compra

Mesmo princípio.

---

# 270. Billing abstraction

Quando integrar:

pode ser útil evitar espalhar BillingClient por UI.

Mas estrutura concreta será decidida.

---

# 271. Activity não deve conter toda a compra

Princípio de separação continua válido.

---

# 272. Billing state

Pode incluir:

- unavailable;
- loading;
- ready;
- purchasing;
- pending;
- purchased;
- error.

Nomes finais pendentes.

---

# 273. Purchase UI deve sobreviver recreation

Não duplicar compra após rotação.

---

# 274. Não lançar dois billing flows

Controlar estado.

---

# 275. Double tap no botão Comprar

Não deve gerar dois fluxos.

---

# 276. Product details query

Preço/offer vêm do Play.

---

# 277. Offer token

Assinaturas/produtos atuais podem ter offers.

Não guardar de forma estática indevida.

---

# 278. Billing Choice

Billing Library 9.1 introduziu APIs novas relacionadas a opções de billing em programas/regiões.

Não usar sem necessidade.

---

# 279. Sempre ler migration guide

Quando versão mudar.

---

# 280. Tutorial de 2023 não é fonte de Billing em 2026

---

# 281. Backend API de subscriptions

Algumas APIs antigas entraram em deprecation em 2026.

Se subscription futura entrar:

usar APIs atuais como `subscriptionsv2` conforme documentação vigente.

---

# 282. Não construir servidor hoje com API já deprecated

---

# 283. Data model de purchase

Não duplicar todos campos do Google sem necessidade.

---

# 284. Guardar purchase token com segurança

Se backend existir.

---

# 285. Personal data

Purchase data pode ser vinculada a conta.

Privacy policy precisa refletir.

---

# 286. Logs de compra

Não logar:

- tokens completos;
- dados sensíveis;
- payment info.

---

# 287. Billing errors

Logar códigos/contexto necessário.

---

# 288. User-facing errors

Não exibir stack trace ou código cru.

---

# 289. Retry de query

Pode acontecer.

---

# 290. Retry de purchase

Precisa cautela.

Não iniciar cobrança duplicada automaticamente.

---

# 291. Pending purchase

Usuário deve concluir na forma de pagamento.

---

# 292. External purchase state

Pode chegar fora do app em APIs modernas.

Entitlement sync precisa lidar se usar esses recursos.

---

# 293. Billing disconnect

Reconectar conforme prática atual.

---

# 294. App foreground

Consulta/atualização de purchases pode ser necessária para capturar mudanças.

---

# 295. Teste de update do app

Usuário premium atualiza:

premium continua.

---

# 296. Teste de downgrade/app reinstall

Entitlement continua conforme Play.

---

# 297. Teste de refund

Premium é revogado se política/produto determinar.

---

# 298. Teste de chargeback

Também.

---

# 299. Crash durante compra

No restart:

reconciliar purchase state.

---

# 300. Compra concluída sem callback local

Query purchases depois precisa recuperar.

---

# 301. Exactly-once illusion

Sistemas distribuídos dão callbacks duplicados/perdidos.

Projetar idempotente.

---

# 302. Segurança client-only

Se não houver backend:

aceitar risco conscientemente e limitar valor/impacto.

---

# 303. Back-end como etapa de monetização

Pode ser a primeira feature que justifica servidor no Gambitol.

---

# 304. Isso exige ADR

## FUTURO

Se introduzirmos backend por monetização:

registrar decisão.

---

# 305. Serverless versus server tradicional

Não decidir agora.

---

# 306. Google Play Developer API credentials

Secret.

Nunca no app.

---

# 307. Service account key

Se existir:

backend/secret store.

Nunca APK.

---

# 308. RTDN

Usa Pub/Sub.

Introduz infraestrutura.

---

# 309. Não adicionar Pub/Sub para uma compra única de R$ X sem avaliar custo/risco

---

# 310. Security proportionality

Mas não ignorar fraude.

---

# 311. Premium cosmetics local-only

Pode tolerar solução mais simples inicialmente.

---

# 312. Subscription cloud service

Precisa solução robusta.

---

# 313. Monetização e Play Console roles

Se equipe crescer:

dar acesso financeiro apenas a quem precisa.

---

# 314. Least privilege

Aplicar.

---

# 315. Financial reports

Dados sensíveis do negócio.

---

# 316. Test accounts

Separar.

---

# 317. Production products

Não editar IDs casualmente.

---

# 318. Subscription product IDs

Depois de criados, possuem restrições de alteração/reuso.

Planejar.

---

# 319. One-time product config

Também precisa cuidado.

---

# 320. Price changes

Podem ter regras de aplicação/consentimento, especialmente subscriptions.

Pesquisar na data.

---

# 321. Não prometer preço “para sempre” sem intenção real

---

# 322. Regional taxes

Preço final pode incluir tratamentos diferentes.

Play Console auxilia, mas obrigação do desenvolvedor depende do país/perfil.

---

# 323. Receita em moeda estrangeira

Relatórios e conversão.

---

# 324. Contabilidade

Guardar relatórios.

---

# 325. Refund policy

Play tem mecanismos próprios.

Não inventar uma política que contradiga direitos do consumidor/Play.

---

# 326. Brasil

Direitos do consumidor e tributação podem adicionar obrigações.

Validar juridicamente/contabilmente se receita se tornar material.

---

# 327. Conteúdo pago e continuidade

Se remover feature premium:

considerar usuários que já pagaram.

---

# 328. Deprecating a paid product

Precisa estratégia de entitlement.

---

# 329. Não vender algo que planejamos remover na semana seguinte

---

# 330. Subscription deprecation

Mais sensível.

---

# 331. Restore support

Parte do compromisso de compra.

---

# 332. Offline longevity

Compra permanente deveria continuar útil sem conexão normal, se o recurso é local.

---

# 333. Não transformar tema comprado em cloud-only

Sem motivo.

---

# 334. Monetização e acessibilidade

Premium/paywall precisa:

- TalkBack;
- touch target;
- contraste;
- preço legível;
- estado claro.

---

# 335. Não esconder preço em texto minúsculo

---

# 336. Ads accessibility

Anúncios são terceiros, mas placement nosso.

---

# 337. Close button

Precisa ser claro/conforme policy.

---

# 338. Interstitial length

Play possui limites/regras atuais.

Rever antes de SDK.

---

# 339. Anúncios premiados

Política é diferente porque são opt-in.

Ainda precisam UX honesta.

---

# 340. Consent screen overload

Mais um custo dos ads.

---

# 341. Monetização e performance

SDKs podem:

- aumentar startup;
- network;
- memory;
- APK size.

Medir.

---

# 342. Cold start

Não inicializar 8 SDKs no Application por hábito.

---

# 343. Lazy init

Pode ser adequado.

Decidir por SDK.

---

# 344. Ads SDK crash

Pode prejudicar Android vitals e ranking.

---

# 345. Billing library stability

Também precisa monitoramento.

---

# 346. Feature flags para monetização

## FUTURO

Podem permitir desativar paywall/ads remotamente.

Mas exigem config/backend.

Não necessários inicialmente.

---

# 347. Kill switch

Interessante se ads causarem crash.

Só quando infra existir.

---

# 348. Remote config

Não adicionar por antecipação.

---

# 349. Monetization A/B tests

Somente com base e analytics suficiente.

---

# 350. Privacy-preserving measurement

Preferir mínimo necessário.

---

# 351. Analytics SDK

Não obrigatório para medir receita básica.

Play Console já fornece bastante dado.

---

# 352. Produto primeiro, analytics depois da pergunta

Reforço.

---

# 353. Dashboard financeiro custom

Não necessário no início.

---

# 354. Churn prediction

Não.

---

# 355. Revenue forecasting

Só depois de dados.

---

# 356. “Quanto o Gambitol vai render?”

Neste estágio:

não há base honesta para responder com precisão.

---

# 357. Cenários são melhores que promessas

Futuro:

```text
installs
× conversion
× net price
```

com ranges.

---

# 358. Ads revenue também depende de:

- geografia;
- fill;
- eCPM;
- sessions;
- format.

---

# 359. Não calcular por “1.000 downloads = R$ X”

Isso é fantasia sem retenção/impressions/geografia.

---

# 360. Monetization success criteria

## PROPOSTO

Monetização é saudável quando:

- não reduz significativamente satisfação;
- não aumenta crashes;
- não piora board UX;
- conversion existe;
- refund baixo;
- suporte controlável;
- receita líquida compensa complexidade.

---

# 361. Critério para remover monetização

Se:

- ads prejudicam retenção;
- receita é irrelevante;
- reviews pioram;
- SDK cria risco;
- manutenção supera retorno.

Podemos remover.

---

# 362. Decisão não precisa ser permanente

ADR pode registrar supersession.

---

# 363. Experimentar sem comprometer core

Boa estratégia.

---

# 364. Ads como experimento

Só em pequena release/track apropriada.

---

# 365. Premium price experiment

Cuidado com usuários existentes.

---

# 366. Subscription experiment

Maior obrigação.

Não usar como primeiro experimento.

---

# 367. Freemium

É provavelmente o modelo mais flexível.

Mas precisa delimitar “free core”.

---

# 368. Definição proposta de free core

## PROPOSTO

A versão gratuita deve permitir:

- iniciar;
- jogar;
- concluir;
- reiniciar;

uma partida local completa com todas as regras do xadrez suportadas.

---

# 369. Premium não remove regra da free core

---

# 370. Isso protege reputação do projeto

---

# 371. Cosmetic premium

Melhor encaixe inicial.

---

# 372. Remove ads

Somente se ads existirem.

---

# 373. Advanced tools

Futuro.

---

# 374. AI premium

## PENDENTE

Se IA futura existir:

poderia haver níveis de engine premium.

Mas isso precisa ser analisado como produto, custo e fair play.

---

# 375. Local AI não tem custo servidor

Pode ser compra única.

---

# 376. Cloud AI tem custo recorrente

Pode justificar modelo recorrente.

---

# 377. Multiplayer server tem custo recorrente

Também pode mudar economics.

---

# 378. Cosmetics have low marginal cost

Mas exigem produção de arte.

---

# 379. Themes e brand

Podem ser boa monetização sem comprometer chess.

---

# 380. Premium themes precisam preview

---

# 381. Tema não deve reduzir acessibilidade

Todo tema precisa:

- contraste;
- piece readability;
- legal highlights.

---

# 382. Premium não compra direito de ter UX pior

---

# 383. Paid piece sets também testados

---

# 384. Som premium

Acessibilidade: nunca única indicação.

---

# 385. Haptics premium?

## NÃO RECOMENDADO

Haptics de acessibilidade/feedback básico não deveriam ser trancados.

---

# 386. Accessibility features

## PRINCÍPIO

Não monetizar recursos necessários para tornar o app acessível.

---

# 387. Basic coordinates

Provavelmente free.

---

# 388. Legal-move highlights

Core UX.

Free.

---

# 389. High contrast theme

Accessibility.

Free.

---

# 390. TalkBack

Free.

---

# 391. Board size/accessibility

Free.

---

# 392. Fair monetization boundary

Esse princípio é importante para reputação.

---

# 393. Paid analytics features

Podem ser aceitáveis no futuro.

---

# 394. Paid export

Pode ser aceitável, mas avaliar.

---

# 395. Paid history limit

Pode gerar frustração.

Dados próprios do usuário merecem consideração.

---

# 396. Cloud backup premium

Pode ser aceitável se houver custo.

---

# 397. Local save

Provavelmente core/free quando feature existir.

---

# 398. Subscription content

Precisa entrega contínua.

---

# 399. No FOMO artificial

Evitar:

- timers falsos;
- scarcity falsa;
- “última chance” permanente.

---

# 400. No loot boxes

## NÃO PROPOSTO

Sem valor para xadrez e adicionaria riscos regulatórios/políticos.

---

# 401. Gambling-like mechanics

Não.

---

# 402. Virtual currency

Não.

---

# 403. Battle pass

Não.

---

# 404. Energy system

Não.

---

# 405. Daily login rewards

Só se produto futuro justificar, não para manipular retenção.

---

# 406. Core identity

Gambitol deve continuar parecendo:

```text
um jogo de xadrez
```

não um funil de monetização.

---

# 407. Monetization UX hierarchy

```text
JOGAR
↓
PERSONALIZAR
↓
DESCOBRIR PREMIUM
↓
COMPRAR SE QUISER
```

---

# 408. Não inverter

```text
COMPRAR
↓
talvez jogar
```

---

# 409. First-time user

Não mostrar purchase sheet sem intenção explícita.

---

# 410. Kids/families

Se público infantil for escolhido:

reavaliar toda monetização e ads.

---

# 411. Parental gates

Podem ser exigidos em determinados casos/programas.

Pesquisar na implementação.

---

# 412. Target audience é decisão prévia à monetização

---

# 413. Data Safety update

Toda nova SDK monetária dispara review.

---

# 414. Privacy policy update

Também.

---

# 415. Content rating update

Se ads/conteúdo mudarem, revisar.

---

# 416. Store listing

Se premium/ads forem relevantes:

não esconder.

---

# 417. “Contains ads”

Play possui declaração correspondente.

Preencher verdadeiramente.

---

# 418. In-app purchases badge

Play pode indicar.

---

# 419. Support expectation

Usuário pagante espera resposta maior.

---

# 420. SLA informal

Não prometer prazo que não conseguimos cumprir.

---

# 421. Purchase receipt

Play cuida do fluxo, mas suporte pode precisar order info.

Não pedir dados sensíveis desnecessários.

---

# 422. Refund support

Usar ferramentas Play.

---

# 423. Security incident

Se chave/API/backend comprometido:

plano de resposta.

---

# 424. Billing dependency update

Prazos anuais tornam manutenção obrigatória.

---

# 425. Monetização aumenta maintenance burden

Mesmo feature “pronta” precisa atualização de Billing Library e policy.

---

# 426. Isso entra no custo total

---

# 427. Billing version check

Antes de cada release com compras:

confirmar deadline.

---

# 428. 2026 deadline próximo

Versão 7 expira em 31/08/2026.

Logo, se implementássemos Billing agora:

não faria sentido iniciar em 7.

---

# 429. Versão 9.1 atual

Mas confirmar novamente quando codificar.

---

# 430. Java integration

Billing Library possui APIs Java.

Não exige migrar Gambitol para Kotlin.

---

# 431. Kotlin DSL continua apenas build

---

# 432. KTX não é necessário para código Java

Usar dependency adequada à linguagem.

---

# 433. Billing thread/lifecycle

Implementação deve seguir docs atuais.

---

# 434. Purchase verification testability

Separar Play API do entitlement logic.

---

# 435. Fake billing gateway

Pode ser útil em testes.

Nome/classe final pendente.

---

# 436. Testar entitlement sem Play

Sim.

---

# 437. Instrumented billing tests

Somente integração real.

---

# 438. Unit tests

Entitlement/state pode ser local.

---

# 439. No billing in engine tests

Nunca.

---

# 440. CI

Não depender de Play real para toda suite.

---

# 441. Contract tests

Futuro.

---

# 442. Test track

Para fluxo real.

---

# 443. Purchase sandbox

Usar Play testing.

---

# 444. DoD para monetização

## PROPOSTO

Uma feature paga só está pronta quando:

- [ ] produto existe no Play Console;
- [ ] Billing version suportada;
- [ ] preço vem do Play;
- [ ] purchase success;
- [ ] cancel;
- [ ] pending;
- [ ] restore;
- [ ] acknowledgement;
- [ ] idempotência;
- [ ] refund/revoke behavior;
- [ ] offline behavior;
- [ ] UI acessível;
- [ ] Data Safety revisado;
- [ ] privacy revisada;
- [ ] release track testada;
- [ ] suporte preparado.

---

# 445. DoD para ads

- [ ] SDK aprovado;
- [ ] policy revisada;
- [ ] Data Safety;
- [ ] privacy/consent;
- [ ] no interference with board;
- [ ] no accidental clicks;
- [ ] no unexpected interstitial;
- [ ] frequency cap;
- [ ] offline no-fill safe;
- [ ] performance measured;
- [ ] crash-free monitored;
- [ ] target audience compatible.

---

# 446. DoD para subscription

Além de Billing DoD:

- [ ] recurring value real;
- [ ] terms clear;
- [ ] renewal clear;
- [ ] price/cycle clear;
- [ ] management/cancel route;
- [ ] RTDN/backend strategy;
- [ ] grace/hold handling;
- [ ] cancellation;
- [ ] resubscribe;
- [ ] price changes;
- [ ] lifecycle tests.

---

# 447. DoD para non-consumable premium

- [ ] permanent entitlement;
- [ ] restore;
- [ ] refund revocation;
- [ ] reinstallation;
- [ ] device switch test;
- [ ] clear free vs premium boundary.

---

# 448. Quality gate

Nenhuma monetização entra se:

- crash rate piora;
- board fica menor de forma ruim;
- política fica incerta;
- entitlement é inconsistente;
- compra pode cobrar sem entregar.

---

# 449. P0 monetization bugs

- cobrança duplicada;
- cobrar e não desbloquear;
- entitlement perdido massivamente;
- compra concedida incorretamente em escala;
- crash no launch por SDK de monetização;
- policy violation bloqueando distribuição.

---

# 450. P1

- restore falha;
- pending incorreto;
- refund não revoga;
- paywall bloqueia core gratuito;
- anúncio interrompe partida de forma proibida.

---

# 451. P2

- preço UI desatualizado;
- tela premium confusa;
- ad frequency irritante.

---

# 452. P3

- copy menor;
- spacing.

---

# 453. Rollback de monetização

Feature flag futura pode ajudar.

Sem isso:

release corretiva.

---

# 454. Remove ads purchase + ads bug

Se ads não carregam:

usuário premium ainda deve ser reconhecido.

---

# 455. Premium state local

Não depender de ad SDK.

---

# 456. Ads failure should fail open

Ou seja:

jogo funciona sem anúncio.

---

# 457. Billing failure should fail safe

Não cobrar/duplicar.

---

# 458. Entitlement verification unavailable

Preservar direitos previamente confiáveis conforme estratégia.

---

# 459. No network

Free core funciona.

---

# 460. Monetization architecture principle

```text
PLAY BILLING / ADS
↓
MONETIZATION ADAPTER
↓
ENTITLEMENT / PRODUCT STATE
↓
UI
```

Nunca:

```text
PLAY BILLING
↓
ENGINE
```

---

# 461. Nomes do diagrama são conceituais

Não são nomes de packages/classes aprovados.

---

# 462. Backend future

```text
APP
↓
BACKEND
↓
PLAY DEVELOPER API
```

quando segurança exigir.

---

# 463. Backend não valida movimentos por causa de compra

Se jogo continua local:

engine continua local.

O backend monetário tem responsabilidade distinta.

---

# 464. Future online changes that

Multiplayer backend pode validar jogo.

Decisão separada.

---

# 465. Observability monetization

Logar:

- purchase status code;
- entitlement transitions;
- errors.

Sem dados sensíveis.

---

# 466. Metrics privacy

Coletar apenas necessário.

---

# 467. Purchase analytics

Play Console já mostra bastante.

---

# 468. No third-party analytics by default

---

# 469. Revenue reporting

Usar Play inicialmente.

---

# 470. Financial reconciliation

Backend/API só quando volume justificar.

---

# 471. Automated refunds

Futuro.

---

# 472. Chargeback review

Futuro.

---

# 473. Customer support tooling

Futuro.

---

# 474. Monetization roadmap proposto

```text
ETAPA M0
V1 SEM MONETIZAÇÃO

ETAPA M1
medir interesse + feedback

ETAPA M2
definir benefício premium real

ETAPA M3
decidir modelo

ETAPA M4
Billing/ads spike em branch

ETAPA M5
test track

ETAPA M6
lançamento controlado

ETAPA M7
medir impacto

ETAPA M8
manter / ajustar / remover
```

---

# 475. M0 — V1

Sem Billing.

Sem Ads.

---

# 476. M1 — evidência

Observar:

- installs;
- reviews;
- feature requests;
- uso.

---

# 477. M2 — valor

Descobrir:

> o que alguém realmente pagaria?

---

# 478. M3 — modelo

Comparar:

- one-time;
- ads;
- subscription.

---

# 479. M4 — spike

Não misturar diretamente com main.

---

# 480. M5 — test

Internal/closed.

---

# 481. M6 — rollout

Se update e base suficiente:

staged rollout.

---

# 482. M7 — métricas

Receita + UX.

---

# 483. M8 — decisão

Não manter monetização ruim por orgulho.

---

# 484. Critério para iniciar M2

Existe base suficiente para algum feedback real.

Não precisa milhares de usuários, mas precisa mais que imaginação.

---

# 485. Premium idea backlog

Pode ser mantido.

Sem implementar.

---

# 486. Não desenhar 10 temas antes de ter usuários

---

# 487. Monetização como aprendizado

Também ensina:

- Play Billing;
- async;
- security;
- backend;
- state;
- product thinking.

Mas entra na hora certa.

---

# 488. 🎥 MOMENTO BOM PARA GRAVAR — produto único vs assinatura

Explicar:

```text
one-time
≠
subscription
```

com policy de valor recorrente.

---

# 489. 🎥 MOMENTO BOM PARA GRAVAR — Billing purchase lifecycle

Mostrar:

```text
PENDING
PURCHASED
ACKNOWLEDGED
ENTITLEMENT
```

---

# 490. 🎥 MOMENTO BOM PARA GRAVAR — compra fake

Criar teste demonstrando por que não confiar só no client.

---

# 491. 🎥 MOMENTO BOM PARA GRAVAR — restore após reinstalação

Muito bom para portfolio.

---

# 492. 🎥 MOMENTO BOM PARA GRAVAR — ads sem destruir UX

Comparar placements.

---

# 493. 🎥 MOMENTO BOM PARA GRAVAR — Data Safety após SDK de ads

Mostrar como uma dependência muda policy.

---

# 494. 🎥 MOMENTO BOM PARA GRAVAR — Billing Library deprecation

Mostrar por que tutorial antigo pode bloquear release.

---

# 495. COMO EXPLICAR EM ENTREVISTA — monetização

> “Eu mantive monetização fora do motor e tratei compras como entitlements da camada de aplicação. Isso evitou misturar regra de xadrez com Google Play Billing e tornou a lógica de premium testável separadamente.”

---

# 496. COMO EXPLICAR EM ENTREVISTA — segurança

> “Para compras, eu tratei purchase tokens de forma idempotente e concedia entitlement apenas depois do estado PURCHASED e da verificação apropriada. Para cenários de maior valor, a validação foi movida para backend usando a Google Play Developer API.”

Usar somente quando implementado.

---

# 497. COMO EXPLICAR EM ENTREVISTA — produto

> “Eu não comecei com assinatura porque a versão local do jogo não oferecia valor recorrente suficiente. A própria política do Play exige valor sustentado para subscriptions, então priorizei modelos compatíveis com o benefício real.”

---

# 498. COMO EXPLICAR EM ENTREVISTA — UX

> “Publicidade, quando avaliada, não podia interromper uma partida nem competir visualmente com o board. A monetização era subordinada ao fluxo principal do jogo.”

---

# 499. Decisões já vigentes

## DECIDIDO

1. monetização não pertence ao engine;
2. não existe pay-to-win;
3. a V1 pode ser lançada sem monetização;
4. monetização não bloqueia correção/UX/release;
5. Billing só entra quando existir produto pago real;
6. IDs de produtos não serão inventados antecipadamente;
7. ads não podem interromper a partida;
8. accessibility core não será monetizada;
9. free core, se adotado, precisa ser realmente jogável;
10. políticas/taxas Billing serão reverificadas antes da implementação.

---

# 500. Propostas fortes

## PROPOSTO

1. primeira V1 gratuita e sem anúncios;
2. avaliar primeiro uma compra única não consumível após validação;
3. cosméticos são candidatos melhores que consumíveis;
4. evitar assinatura até existir valor recorrente real;
5. evitar ads até existir base/razão econômica;
6. se ads entrarem, priorizar pontos naturais fora do jogo ativo;
7. backend pode ser introduzido quando segurança de Billing justificar.

---

# 501. Pendências

## PENDENTE

1. free ou paid na primeira produção;
2. existência de ads;
3. SDK de ads;
4. primeiro produto pago;
5. IDs;
6. preço;
7. premium scope;
8. owner financeiro/legal;
9. backend de Billing;
10. subscription futura;
11. país/price strategy;
12. tax/accounting structure;
13. consent tooling;
14. analytics;
15. entitlement storage;
16. service-fee program enrollment;
17. multi-store strategy.

---

# 502. Pontos que exigem ADR futuro

Provavelmente:

- introduzir backend;
- escolher SDK de ads;
- escolher subscription;
- escolher cross-platform entitlement;
- adotar Play Games/serviços pagos.

---

# 503. Fonte — Payments policy

## Google Play Payments

https://support.google.com/googleplay/android-developer/answer/9858738

Usado para:

- Billing obrigatório para digital goods;
- app features;
- subscriptions;
- ad-free versions;
- exceções.

Verificado em: 2026-08-22.

---

# 504. Fonte — Billing policy overview

https://support.google.com/googleplay/android-developer/answer/10281818?hl=pt-BR

Usado para:

- digital goods;
- one-time products;
- subscriptions;
- produtos não elegíveis.

---

# 505. Fonte — Billing Library release notes

https://developer.android.com/google/play/billing/release-notes

Usado para:

- versão 9.1.0;
- mudanças de APIs;
- Billing Choice.

Verificado em: 2026-08-22.

---

# 506. Fonte — Billing version deprecation

https://developer.android.com/google/play/billing/deprecation-faq

Usado para:

- prazo v7 2026;
- v8 2027;
- v9 2028;
- extension deadlines.

Verificado em: 2026-08-22.

---

# 507. Fonte — Billing integration

https://developer.android.com/google/play/billing/integrate

Usado para:

- BillingClient;
- purchase lifecycle;
- PENDING;
- PURCHASED;
- acknowledgement;
- 3-day rule.

Verificado em: 2026-08-22.

---

# 508. Fonte — Billing security

https://developer.android.com/google/play/billing/security

Usado para:

- purchase verification;
- backend;
- purchase token;
- idempotência;
- fraud;
- acknowledge/consume.

Verificado em: 2026-08-22.

---

# 509. Fonte — Billing backend

https://developer.android.com/google/play/billing/backend

Usado para:

- Google Play Developer API;
- RTDN;
- entitlement sync;
- reconciliation.

Verificado em: 2026-08-22.

---

# 510. Fonte — one-time products

https://developer.android.com/google/play/billing/one-time-products

Usado para:

- consumable;
- non-consumable;
- permanent benefits;
- ad-free/premium examples.

Verificado em: 2026-08-22.

---

# 511. Fonte — subscriptions

https://developer.android.com/google/play/billing/subscriptions

Usado para:

- recurring entitlements;
- base plans;
- offers;
- lifecycle.

Verificado em: 2026-08-22.

---

# 512. Fonte — Subscription policy

https://support.google.com/googleplay/android-developer/answer/9900533?hl=pt-BR

Usado para:

- recurring value;
- disclosure;
- renewal;
- transparency;
- one-time benefits not subscriptions.

Verificado em: 2026-08-22.

---

# 513. Fonte — manage subscriptions

https://support.google.com/googleplay/android-developer/answer/140504?hl=pt-BR

Usado para:

- product IDs;
- subscription management;
- cancellation UX;
- product setup.

Verificado em: 2026-08-22.

---

# 514. Fonte — service fees

https://support.google.com/googleplay/android-developer/answer/112622?hl=pt-BR

Usado para:

- service fee variability;
- first US$1M tier;
- subscription fee structure;
- 2026 regional changes.

Verificado em: 2026-08-22.

---

# 515. Fonte — lower service fees rollout

https://support.google.com/googleplay/android-developer/answer/16954621?hl=pt-BR

Usado para:

- 2026 new fee model;
- June rollout;
- September rollout;
- regional structure.

Verificado em: 2026-08-22.

---

# 516. Fonte — app pricing

https://support.google.com/googleplay/android-developer/answer/6334373

Usado para:

- paid/free;
- impossibilidade de free → paid no mesmo app;
- country pricing.

Verificado em: 2026-08-22.

---

# 517. Fonte — ads policy

https://support.google.com/googleplay/android-developer/answer/9857753?hl=pt-BR

Usado para:

- unexpected interstitials;
- game interruption;
- closeability;
- rewarded exceptions.

Verificado em: 2026-08-22.

---

# 518. Fonte — better ads experience

https://support.google.com/googleplay/android-developer/answer/12271244?hl=pt-BR

Usado para:

- experiência de ads;
- unexpected full-screen ads.

Verificado em: 2026-08-22.

---

# 519. Relação com `12_PLAY_STORE_E_RELEASE.md`

Doc 12 define:

- release;
- account;
- policies;
- Data Safety;
- signing.

Este documento adiciona:

- Billing;
- ads;
- product economics.

---

# 520. Relação com `09_UI_UX_GAMBITOL.md`

Monetização não pode contrariar:

- board dominante;
- touch targets;
- accessibility;
- premium restrained design.

---

# 521. Relação com `10_ROADMAP_E_ESCOPO.md`

Monetização permanece posterior ao core.

---

# 522. Relação com `11_DECISOES_TECNICAS.md`

Mudança de modelo monetário relevante deve ser registrada.

---

# 523. Checklist antes de monetizar

- [ ] V1 estável;
- [ ] problema/valor claro;
- [ ] free core definido;
- [ ] modelo selecionado;
- [ ] policy atual revisada;
- [ ] fees atuais revisadas;
- [ ] Billing/ads version atual;
- [ ] Data Safety impact;
- [ ] privacy impact;
- [ ] target audience;
- [ ] UX design;
- [ ] security plan;
- [ ] tests;
- [ ] support;
- [ ] pricing;
- [ ] owner financeiro.

---

# 524. Checklist antes de produto único

- [ ] benefício permanente definido;
- [ ] ID aprovado;
- [ ] preço;
- [ ] product configured;
- [ ] entitlement;
- [ ] verification;
- [ ] restore;
- [ ] acknowledgement;
- [ ] refunds;
- [ ] test accounts.

---

# 525. Checklist antes de subscription

- [ ] valor recorrente;
- [ ] frequência;
- [ ] disclosure;
- [ ] cancel;
- [ ] backend;
- [ ] RTDN;
- [ ] grace/hold;
- [ ] renewal;
- [ ] churn support;
- [ ] price-change policy.

---

# 526. Checklist antes de ads

- [ ] SDK;
- [ ] privacy;
- [ ] Data Safety;
- [ ] audience;
- [ ] placement;
- [ ] frequency;
- [ ] performance;
- [ ] no accidental clicks;
- [ ] no gameplay interruption;
- [ ] user testing.

---

# 527. Resumo da estratégia recomendada

```text
AGORA
→ construir excelente jogo de xadrez

PRIMEIRA RELEASE
→ preferencialmente simples

DEPOIS
→ observar usuários

SE HOUVER VALOR PAGO CLARO
→ considerar produto único premium

SE HOUVER BASE PARA ADS
→ testar sem interromper partida

SE HOUVER VALOR RECORRENTE REAL
→ considerar assinatura

SE BILLING CRESCER EM VALOR/RISCO
→ introduzir backend seguro
```

---

# 528. Frase norteadora

> **O Gambitol não deve ganhar dinheiro porque interrompe o jogador até ele pagar. Deve ganhar dinheiro, se ganhar, porque oferece um produto bom o suficiente para que pagar seja uma escolha de valor, não uma fuga da irritação.**

---

# 529. Próximo documento

Após aprovação:

`14_CONTEUDO_E_PORTFOLIO.md`

Ele deverá definir:

- o que vale gravar;
- o que não vale;
- evolução pública;
- GitHub;
- README;
- screenshots;
- vídeos;
- posts;
- demonstrações;
- explicações de Java;
- arquitetura;
- testes;
- Perft;
- Android;
- Play Store;
- como transformar o Gambitol em prova profissional sem fingir que cada `git add` foi uma saga épica.

O documento 13 define:

> **se, quando e como o Gambitol pode gerar receita sem prejudicar o produto.**

O documento 14 definirá:

> **como transformar a construção real do Gambitol em reputação, conteúdo e evidência profissional.**
