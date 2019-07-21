import java.io.IOException;

/**
 * Conta
 */
public class Conta {

    Integer id;
    String nome;
    String senha;
    float saldo;

    public Conta(String nome, String senha, Integer ultimaConta) throws IOException{
        this.id = ultimaConta +1;
        this.nome = nome;
        this.senha = senha;
        this.saldo = 0;
    }

    public void deposita(Float Valor) {
        this.saldo = +Valor;
    }

}