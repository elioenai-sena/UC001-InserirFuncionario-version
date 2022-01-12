package br.ifpe.web2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.dao.FuncionarioDAO;
import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.util.ServiceException;
import br.ifpe.web2.util.Util;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;

	public void inserirFuncionario(Funcionario funcionario) throws ServiceException {

		// 1. Buscar funcionario ja existente com o mesmo CPF
		Funcionario funcExistente = this.funcionarioDAO.findByCpf(funcionario.getCpf());

		if (funcExistente != null) {
			throw new ServiceException("Já existe um funcionário cadastrado com este CPF");
		}
		// 2. Verificar maior de idade
		if (Util.calcularIdade(funcionario.getDataNascimento()) < 18) {
			throw new ServiceException("Funcionário deve ter 18 anos ou mais");
		}
		// 3. Fluxo principal - inserir funcionario no banco de dados
		this.funcionarioDAO.save(funcionario);

	}

	public <S extends Funcionario> S atualizarFuncionario(S entity) {
		System.out.println(entity.getCodigo());
		return funcionarioDAO.save(entity);
	}

	public List<Funcionario> buscarTodos() {
		return funcionarioDAO.findAll();
	}

	public Optional<Funcionario> buscarPorId(Integer id) {
		return funcionarioDAO.findById(id);
	}

	public void deletarPorId(Integer id) {
		funcionarioDAO.deleteById(id);
	}

}
