import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 * clienteHandler
 */
public class clienteHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean logado = false;
    private Conta contaThread;

    public clienteHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        // new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    @Override
    public void run() {
        boolean contaEncontrada = false;
        Conta cont = null;
        boolean operacaoEscolhida = false;
        Integer operacao = 0;
        float valorOp = 0;
        try {
            while (true) {
                String request = in.readLine();
                if (!this.logado) {
                    if (!contaEncontrada) {
                        out.println("Qual id da conta?");
                        while (request.isEmpty()) {
                            request = in.readLine();
                        }
                        Integer id = Integer.parseInt(request);
                        cont = Server.encontraConta(id);
                        request = "";
                        contaEncontrada = true;
                    } else if (contaEncontrada) {
                        out.println("Qual a senha?");
                        request = "";
                        while (request.isEmpty()) {
                            request = in.readLine();
                        }
                        if (cont.senha.equals(request)) {
                            out.println("Logado com sucesso");
                            this.contaThread = cont;
                            this.logado = true;
                            request = "";
                        }else {
                            out.println("Senha incorreta");
                            request = "";
                            contaEncontrada = false;
                        }
                    }

                } else if (this.logado) {
                    if (!operacaoEscolhida) {
                        out.println(
                                "Seja bem vindo ao banco " + this.contaThread.nome +", escolha sua operacao: 1 - Consultar seu saldo   2-Depositar   3-Sacar dinheiro");
                        while (request.isEmpty()) {
                            request = in.readLine();
                        }
                        operacao = Integer.parseInt(request);
                        request = "";
                        operacaoEscolhida = true;
                    }

                    else if (operacaoEscolhida) {
                        switch (operacao) {
                        case 1:
                            out.println("Saldo:  " + this.contaThread.saldo);

                            /*
                             * while (request.isEmpty() || request == null) { //
                             * out.println("para no while"); }
                             */
                            operacaoEscolhida = false;
                            operacao = 0;
                            // out.println("Sua conta é a conta de: " + cont.nome);
                            // out.println(Server.encontraConta(1).saldo);

                            break;

                        case 2:
                            if (valorOp == 0) {
                                out.println("Entre com o valor a ser depositado");
                                while (request.isEmpty()) {
                                    request = in.readLine();
                                }
                                valorOp = Float.parseFloat(request);
                            } else {

                                out.println("Saldo anterior:  " + this.contaThread.saldo);
                                Integer index = Server.contas.indexOf(this.contaThread);

                                this.contaThread.saldo = this.contaThread.saldo + valorOp;

                                Server.contas.set(index, this.contaThread);

                                out.println("Saldo depois:  " + Server.contas.get(index).saldo);
                                valorOp = 0;

                                operacaoEscolhida = false;
                                operacao = 0;

                            }

                            break;

                        case 3:
                            if (valorOp == 0) {
                                out.println("Entre com o valor a ser sacado");
                                while (request.isEmpty()) {
                                    request = in.readLine();
                                }
                                valorOp = Float.parseFloat(request);

                                if (valorOp > this.contaThread.saldo) {
                                    out.println("Voce nao possui esse valor em conta");
                                    operacaoEscolhida = false;
                                    operacao = 0;
                                    valorOp = 0;
                                    break;
                                }
                            } else {

                                out.println("Saldo anterior:  " + this.contaThread.saldo);
                                Integer index = Server.contas.indexOf(this.contaThread);

                                this.contaThread.saldo = this.contaThread.saldo - valorOp;

                                Server.contas.set(index, this.contaThread);

                                out.println("Saldo depois:  " + Server.contas.get(index).saldo);
                                valorOp = 0;

                                operacaoEscolhida = false;
                                operacao = 0;

                            }

                            break;
                        default:
                            break;
                        }
                    }
                }
                /*
                 * if (request.contains("name")) { out.println(Server.contas.get(0).nome); }
                 * else { out.println("Digite a operacao"); }
                 */
            }
        } catch (

        IOException e) {
            System.err.println("Erro de IO ");
            System.err.println(e);
        } finally {
            out.close();

            try {
                in.close();
            } catch (IOException e) {
                System.err.println("Erro de IO ");
                System.err.println(e.getStackTrace());
            }

        }

    }
}