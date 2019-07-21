# java-sockets-bank 

Esse repositório serve de exemplo para uma aplicação multi-thread usando sockets. É feito uma simulação de um serviço de banco como exemplo. 
Fique a vontade para tirar suas dúvidas comigo e usar o código de referência.

Como rodar: 

Compile cada um dos arquivos no terminal com o javac (lembre-se de estar no diretório src), no terminal execute:

javac Conta.java
javac Server.java
javac ServerConnection.java
javac clienteHandler.java


então execute o servidor:

java Server

E então  abra o cliente ( é possivel abrir vários clientes, limitados pelo numero de threads descritas no server (pool);

java cliente
