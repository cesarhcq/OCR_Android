package engenoid.tessocrtest.dominio.entidade;

import java.io.Serializable;

public class Dados implements Serializable {

    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String EMAIL = "EMAIL";

    private long id;
    private String nome;
    private String email;

    public Dados()
    {
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return nome + " / " + email;
    }
}
