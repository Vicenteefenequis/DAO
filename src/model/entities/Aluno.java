package model.entities;

import java.util.Date;

public class Aluno {
	
	private Integer id;
	private String nome;
	private String sexo;
	private Date dt_nasc;
	private Curso curso;
	
	public Aluno() {
	
	}
	
	public Aluno(Integer id, String nome, String sexo, Date dt_nasc) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dt_nasc = dt_nasc;
	}
	
	public Aluno(Integer id, String nome, String sexo, Date dt_nasc, Curso curso) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.dt_nasc = dt_nasc;
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(Date dt_nasc) {
		this.dt_nasc = dt_nasc;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dt_nasc == null) ? 0 : dt_nasc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (dt_nasc == null) {
			if (other.dt_nasc != null)
				return false;
		} else if (!dt_nasc.equals(other.dt_nasc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo == null) {
			if (other.sexo != null)
				return false;
		} else if (!sexo.equals(other.sexo))
			return false;
		return true;
	}
}
