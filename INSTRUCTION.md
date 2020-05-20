# ROTA DE VIAGEM USANDO DIJKSTRA 

O desenvolvimento para esta demanda foi aplicada em JAVA, para atender as demandas de um código que funcione tanto via console quanto via endpoint eu apliquei esta implantação utilizando Spring-Boot
(tentei não utilizar todo o framework, mas para não perder muito tempo quebrando a cabeça com as duas interfaces eu tive que aplicar usando Spring-Boot, usei também a injeção de depência para escalonar entre o rest, e ou console) 


## Estratégia implementada

Como nunca havia ouvido falar neste tipo de calculo procurei entender do que realmente se tratava a implemetação deste projeto, aí que eu descobri que é um algoritmo famoso "O algoritmo de Dijkstra",  procurei por videos que me explicassem como ele funciona. ( para não "roubar", não procurei o algoritmo pronto, mas sim explicações teoricas). então encontrei o video_1, como não lembrava de como era uma estrutura de Grafos pesquisei e encontrei o video_2, analisando a opção dos grafos optei por aplicar a "Lista de Adjacencia".o
video_1 = https://www.youtube.com/watch?v=ovkITlgyJ2svideo_2 =https://www.youtube.com/watch?v=k9DJn-COtKg8

## Tecnologias

Este projeto utilizou as seguintes tecnologias

 - Apache Maven
 - Java 14
 - Spring-Boot
 - Spring-Web
 - SonarQube*
 - JUnit*

*Devido ao tempo, não consegui aplicar esta tarefa com sucesso

## Setup da Aplicação
Configurações no Eclipse
 
Main Class
`br.com.bexs.dijktra.airport.DijkstraAirportApplication`

Program Arguments (console)
`console` 
`--filePath=<<caminho do seu arquivo aqui>>\input-file.txt`

Program Arguments (web)
`rest` 
`--filePath=<<caminho do seu arquivo aqui>>\input-file.txt`

### Linha de comando - Via executável

### WEB
`java -jar spring-dijkstra-airport-0.0.1-SNAPSHOT.jar rest --filePath=C:\ambiente-de-testes\input-file.txt`

### Console
`java -jar spring-dijkstra-airport-0.0.1-SNAPSHOT.jar console --filePath=C:\ambiente-de-testes\input-file.txt`
 
## Execução da Aplicaçãoo


### WEB
	no modo web foram criados dois endpoints
		GET > 
		POST >

### CONSOLE
	no modo console, quando aparecer a seguinte mensagem.
		`Selecione a origem e Destino conforme o template: "ORI-DST"`

	digite respeitando a regra de origem e destino e processo mostrará o retorno conforme abaixo:
		`Melhor caminho: GRU-BRC-SCL-ORL-CDG > $40`


## Encerrar a aplicaçãoo

### WEBB

	Para a aplicação web apenas utilizando CTRL+C no terminal que estava executando a aplicação.

### Consolee
	A aplicação via console se encerra após consultar uma unica vez 



## Estrutura da aplicação

### br.com.bexs.dijktra.airport

	A classe deste pacote é a responsável por fazer o escalonamento entre REST e CONSOLE
### br.com.bexs.dijktra.airport.business

	A classe deste pacote é a responsável: 
	- Por aplicar a lógica do algoritmo na consulta do melhor caminho
	- Por validar e aplicar um insert correto no arquivo

### br.com.bexs.dijktra.airport.executor
	A classe deste pacote é a responsável: 
	- ExecutorRest aplica as chamadas de insert, e consulta
	- ExecutorConsole aplica as de consulta
	
### br.com.bexs.dijktra.airport.helper
	E a classe responsavel pela validação da entrada do usuário, não permitindo códigos que quebrem o sistema.
	
### br.com.bexs.dijktra.airport.model
	Onde fica os modelos do Sistema, são três:
	- Modelo de Vertices (Que é utilizando na fila de Grafos)
	- Modelo de Entrada (Onde é recebida a ação do Usuário)
	- Modelo de Resposta (São informações disponibilizadas na tela)
	 
### br.com.bexs.dijktra.airport.repository
	Onde fica a classe de Acesso ao arquivo
