#language: pt

Funcionalidade: Detalhes de Deputado

	Cenário: Ver detalhes de deputados
	Dado Eu sou eleitor buscando informações de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conheça os Deputados"
	Então devo ver uma página de pesquisa com texto "Pesquisa de Deputados"
		E vejo o botão "Pesquisar".
	Quando Eu informo o nome "Tiririca" no campo nome do deputado
		E seleciono UF com o valor "SP"
		E clico sobre botão "Pesquisar"
	Então vejo uma página com o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Projetos de sua autoria"
	Então vejo uma página com o texto "Ementa: Altera a Lei nº 10.753, de 30 de outubro de 2003, que institui a Política Nacional do Livro, para dispor sobre a criação do Vale-Livro."
	Quando Eu clico sobre o link "próxima"
	Então vejo a página dois.
	Quando Eu volto para a página de detalhes do deputado
	Então vejo o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Discursos em plenário"
	Então vejo a página de "Discursos e Notas Taquigráficas" do deputado.
	Quando Eu clico sobre o texto "Retorna a pesquisa."
	Então vejo o texto "Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA"
	Quando Eu clico sobre o link "Biografia"
	Então vejo o texto "SILVA, Francisco Everardo Oliveira (Co-autor). As Piadas fantárdigas do Tiririca. São Paulo: Matrix, 2006."
	Quando Eu clico sobre o link "Proposições Transformadas em Norma Jurídica"
	Então vejo o texto "PL 4682/2012"
	Quando Eu clico sobre o link "Atividade Legislativa" na barra de navegação do site
	Então vejo o link "Agenda"
		E vejo o link "Comissões"
		E vejo o link "Conheça o Processo Legislativo"
		
	Cenário: Verificar presença em plenário de deputados
	Dado Eu sou eleitor buscando informações da presença em plenário de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conheça os Deputados"
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando Eu seleciono o deputado "Tiririca"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: TIRIRICA - PR/SP"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Jean Wyllys"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: JEAN WYLLYS - PSOL/RJ"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "João Arruda"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: JOÃO ARRUDA - PMDB/PR"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Zoinho"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ZOINHO - PR/RJ"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Anthony Garotinho"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ANTHONY GAROTINHO - PR/RJ"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Ariosto Holanda"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ARIOSTO HOLANDA - PROS/CE"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Artur Bruno"
		E marco a opção "Presença em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ARTUR BRUNO - PT/CE"
	
	Cenário: Verificar votações em plenário de deputados
	Dado Eu sou eleitor buscando informações dos votos em plenário de deputados
	Quando Eu acesso o site "http://www.camara.leg.br/"
		E passo o mouse sobre o texto de menu "Deputados"
		E clico sobre o item de menu "Conheça os Deputados"
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando Eu seleciono o deputado "Tiririca"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: TIRIRICA - PR/SP"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Jean Wyllys"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: JEAN WYLLYS - PSOL/RJ"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "João Arruda"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: JOÃO ARRUDA - PMDB/PR"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Zoinho"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ZOINHO - PR/RJ"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Anthony Garotinho"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ANTHONY GAROTINHO - PR/RJ"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Ariosto Holanda"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ARIOSTO HOLANDA - PROS/CE"
		E vejo o título da página "Relatório de Votações em Plenário"
	Quando eu volto para a página de pesquisa
	Então devo ver uma página de pesquisa com texto "Legislatura Atual - Deputados em exercício"
		E vejo o botão "Pesquisar".
	Quando eu seleciono o deputado "Artur Bruno"
		E marco a opção "Votações em plenário"
		E clico sobre o botão "Pesquisar"
	Então vejo o texto "Nome Parlamentar: ARTUR BRUNO - PT/CE"
		E vejo o título da página "Relatório de Votações em Plenário"