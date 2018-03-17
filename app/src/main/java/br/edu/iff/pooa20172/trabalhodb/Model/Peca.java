package br.edu.iff.pooa20172.trabalhodb.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Wesley Gomes on 17/03/2018.
 */

public class Peca extends RealmObject {
    @PrimaryKey
    private int id;
    private String nome, descricao;

    public Peca() {
    }

    public Peca(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
