#language: pt

Funcionalidade: Detalhes de Deputado

	Cen�rio: Ver detalhes de deputados
	Dado Eu sou eleitor buscando informa��es de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conhe�a os Deputados"
	Ent�o devo ver uma p�gina de pesquisa com texto "Pesquisa de Deputados"
		E vejo o bot�o "Pesquisar".
	Quando Eu informo o nome "Tiririca" no campo nome do deputado
		E seleciono UF com o valor "SP"
		E clico sobre bot�o "Pesquisar"
	Ent�o vejo uma p�gina com o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Projetos de sua autoria"
	Ent�o vejo uma p�gina com o texto "Ementa: Altera a Lei n� 10.753, de 30 de outubro de 2003, que institui a Pol�tica Nacional do Livro, para dispor sobre a cria��o do Vale-Livro."
	Quando Eu clico sobre o link "pr�xima"
	Ent�o vejo a p�gina dois.
	Quando Eu volto para a p�gina de detalhes do deputado
	Ent�o vejo o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Discursos em plen�rio"
	Ent�o vejo a p�gina de "Discursos e Notas Taquigr�ficas" do deputado.
	Quando Eu clico sobre o texto "Retorna a pesquisa."
	Ent�o vejo o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Biografia"
	Ent�o vejo o texto "SILVA, Francisco Everardo Oliveira (Co-autor). As Piadas fant�rdigas do Tiririca. S�o Paulo: Matrix, 2006."
	Quando Eu clico sobre o link "Proposi��es Transformadas em Norma Jur�dica"
	Ent�o vejo o texto "PL 4682/2012"
	Quando Eu clico sobre o link "Atividade Legislativa" na barra de navega��o do site
	Ent�o vejo o link "Agenda"
		E vejo o link "Comiss�es"
		E vejo o link "Conhe�a o Processo Legislativo"
		
	Cen�rio: Verificar presen�a em plen�rio de deputados
	Dado Eu sou eleitor buscando informa��es da presen�a em plen�rio de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conhe�a os Deputados"
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando Eu seleciono o deputado "Tiririca"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: TIRIRICA - PR/SP"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Jean Wyllys"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: JEAN WYLLYS - PSOL/RJ"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Jo�o Arruda"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: JO�O ARRUDA - PMDB/PR"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Zoinho"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ZOINHO - PR/RJ"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Anthony Garotinho"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ANTHONY GAROTINHO - PR/RJ"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Ariosto Holanda"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ARIOSTO HOLANDA - PROS/CE"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Artur Bruno"
		E marco a op��o "Presen�a em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ARTUR BRUNO - PT/CE"
	
	Cen�rio: Verificar vota��es em plen�rio de deputados
	Dado Eu sou eleitor buscando informa��es dos votos em plen�rio de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conhe�a os Deputados"
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando Eu seleciono o deputado "Tiririca"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: TIRIRICA - PR/SP"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Jean Wyllys"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: JEAN WYLLYS - PSOL/RJ"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Jo�o Arruda"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: JO�O ARRUDA - PMDB/PR"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Zoinho"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ZOINHO - PR/RJ"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Anthony Garotinho"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ANTHONY GAROTINHO - PR/RJ"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Ariosto Holanda"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ARIOSTO HOLANDA - PROS/CE"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"
	Quando eu volto para a p�gina de pesquisa
	Ent�o devo ver uma p�gina de pesquisa com texto "Legislatura Atual - Deputados em exerc�cio"
		E vejo o bot�o "Pesquisar".
	Quando eu seleciono o deputado "Artur Bruno"
		E marco a op��o "Vota��es em plen�rio"
		E clico sobre o bot�o "Pesquisar"
	Ent�o vejo o texto "Nome Parlamentar: ARTUR BRUNO - PT/CE"
		E vejo o t�tulo da p�gina "Relat�rio de Vota��es em Plen�rio"