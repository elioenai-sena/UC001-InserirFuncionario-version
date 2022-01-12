package br.ifpe.web2.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpe.web2.model.cadastro.Funcionario;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer>{

	// select * from Funcionario where cpf = 34343434
	// findBy__________ 
	public Funcionario findByCpf(String cpf);
	
	// select * from Funcionario where dataNascimento = ????
	public Funcionario findByDataNascimento(Date dataNascimento);
	
}
