package br.edu.iff.pooa20172.trabalhodb.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Wesley Gomes on 17/03/2018.
 */

public class Servico extends RealmObject {
    @PrimaryKey
    private int id;
    private String nome, horas, mecanico;

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

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public Servico(String nome, String horas, String mecanico) {

        this.nome = nome;
        this.horas = horas;
        this.mecanico = mecanico;
    }

    public Servico() {

    }
}
