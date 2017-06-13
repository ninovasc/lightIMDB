# Light IMDB
 
Uma implementação de um sistema web para 
avaliar filmes (ou outros trabalhos artísticos). 

### Requisitos

   * instalar o MySQL e setar a senha do usuário root como *root* 
   * instalar o play-framwork (na essência, instalar o sbt... consulte o site) 

### Executar o projeto
   * clonar esse repositório via o git 
   * criar o banco de dados imdb no mysql 
   * definir o esquema do banco de dados (verificar o script em conf/evolutions/1.sql)
   * executar o sbt (isso no diretório raiz do projeto) 
   * no shell do sbt, executar "run" 
   * acessar o projeto em http://localhost:9000  

### Instalar o sbt no Ubuntu:
	http://www.scala-sbt.org/release/docs/Installing-sbt-on-Linux.html

	echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
	sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
	sudo apt-get update
	sudo apt-get install sbt 
