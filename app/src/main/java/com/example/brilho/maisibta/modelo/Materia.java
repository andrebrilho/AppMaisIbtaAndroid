package com.example.brilho.maisibta.modelo;

import java.io.Serializable;

/**
 * Created by Brilho on 07/05/2017.
 */

public class Materia implements Serializable {

    private Long id;
    private String materia;
    private String professor;
    private String notaProva1;
    private String notaProva2;
    private String notaEd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getNotaProva1() {
        return notaProva1;
    }

    public void setNotaProva1(String notaProva1) {
        this.notaProva1 = notaProva1;
    }

    public String getNotaProva2() {
        return notaProva2;
    }

    public void setNotaProva2(String notaProva2) {
        this.notaProva2 = notaProva2;
    }

    public String getNotaEd() {
        return notaEd;
    }

    public void setNotaEd(String notaEd) {
        this.notaEd = notaEd;
    }

    @Override
    public String toString() {
        return " "  + getMateria();

    }
}
