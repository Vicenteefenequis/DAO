package model.entities;

public class Curso {
	
	private Integer idcurso;
	private String nomecurso;
	
	public Curso() {

	}
	
	public Curso(Integer idcurso, String nomecurso) {
		this.idcurso = idcurso;
		this.nomecurso = nomecurso;
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomecurso() {
		return nomecurso;
	}

	public void setNomecurso(String nomecurso) {
		this.nomecurso = nomecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcurso == null) ? 0 : idcurso.hashCode());
		result = prime * result + ((nomecurso == null) ? 0 : nomecurso.hashCode());
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
		Curso other = (Curso) obj;
		if (idcurso == null) {
			if (other.idcurso != null)
				return false;
		} else if (!idcurso.equals(other.idcurso))
			return false;
		if (nomecurso == null) {
			if (other.nomecurso != null)
				return false;
		} else if (!nomecurso.equals(other.nomecurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
	}
	
}
