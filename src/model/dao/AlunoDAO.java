package model.dao;

import java.util.List;

import model.entities.Aluno;

public interface AlunoDAO {

	void insert(Aluno obj);
	void update(Aluno obj);
	void deleteByid(Integer id);
	Aluno findByid(Integer id);
	List<Aluno> findAll();
}
