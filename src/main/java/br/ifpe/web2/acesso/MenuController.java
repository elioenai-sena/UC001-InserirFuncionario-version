package br.ifpe.web2.acesso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/cargos")
	public String exibirMenuCargos() {
		return "/cargo/cargos-form";
	}

	@GetMapping("/empresas")
	public String exibirMenuEmpresas() {
		return "/empresa/empresas-form";
	}

}
