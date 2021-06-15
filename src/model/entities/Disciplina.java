package model.entities;

import java.util.Objects;

public class Disciplina {
    private int idDisciplina;
    private String nomeDisciplina;
    private int cargahoraria;


    public Disciplina() {}

    public Disciplina(int idDisciplina, String nomeDisciplina, int cargahoraria) {
        this.idDisciplina = idDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.cargahoraria = cargahoraria;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(int cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disciplina)) return false;
        Disciplina that = (Disciplina) o;
        return getIdDisciplina() == that.getIdDisciplina() && getCargahoraria() == that.getCargahoraria() && getNomeDisciplina().equals(that.getNomeDisciplina());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdDisciplina(), getNomeDisciplina(), getCargahoraria());
    }
}
