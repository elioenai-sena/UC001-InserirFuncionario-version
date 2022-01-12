package br.ifpe.web2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.web2.model.cadastro.Funcionario;
import br.ifpe.web2.service.CargoService;
import br.ifpe.web2.service.EmpresaService;
import br.ifpe.web2.service.FuncionarioService;
import br.ifpe.web2.util.ServiceException;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcService;

	@Autowired
	private CargoService cargoService;

	@Autowired
	private EmpresaService empresaService;

	@GetMapping("/formFunc")
	public String exibirFormFunc(Funcionario funcionario, Model model) {
		model.addAttribute("listaCargos", this.cargoService.listarTodos(true));
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		model.addAttribute("listaFuncionarios", this.funcService.buscarTodos());
		return "/funcionario/funcionario-form";
	}

	@PostMapping("/inserirFuncionario")
	public String inserirFuncionario(@Valid Funcionario funcionario, BindingResult br, Model model,
			RedirectAttributes ra) {
		if (br.hasErrors()) {
			return exibirFormFunc(funcionario, model);
		}
		try {
			this.funcService.inserirFuncionario(funcionario);
			ra.addFlashAttribute("mensagem", "Funcionário Cadastrado com Sucesso");

			return "redirect:/formFunc";
		} catch (ServiceException e) {
			br.addError(new ObjectError("global", e.getMessage()));
			return exibirFormFunc(funcionario, model);
		}
	}

	@GetMapping("/deletarFuncionario")
	public String deletarFuncionario(Integer codigo) {
		this.funcService.deletarPorId(codigo);
		return "redirect:/formFunc";
	}

	@GetMapping("/buscarFuncionario")
	public String buscarFuncionario(Integer codigo, Model model) {

		model.addAttribute("funcionario", this.funcService.buscarPorId(codigo).orElse(null));
		
		model.addAttribute("listaCargos", this.cargoService.listarTodos(true));
		model.addAttribute("listaEmpresas", this.empresaService.listarTodos(true));
		model.addAttribute("listaFuncionarios", this.funcService.buscarTodos());
		
		return "/funcionario/funcionario-edit";
	}

	@PostMapping("/atualizarFuncionario")
	public String atualizarFuncionario(Funcionario funcionario) {
		this.funcService.atualizarFuncionario(funcionario);
		
		return "redirect:/formFunc";
	}
}

