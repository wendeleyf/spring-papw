/**
 * Projeto das trilhas de treinamento de Java básico ou avançado
 * com foco nas certificações java e em treinamentos corporativos. 
 * Fontes dispon�veis em https://github.com/rodrigofujioka
 * 
 * Professor: Rodrigo da Cruz Fujioka
 * Ano: 2019
 * http://www.rodrigofujioka.com
 * http://www.fujideia.com.br
 * http://lattes.cnpq.br/0843668802633139
 * 
 * Contato: rcf4@cin.ufpe.br 
 * 
 */
package br.unipe.papw.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserLogin extends User {

	private static final long serialVersionUID = 1L;

	private String name;

	public UserLogin(String name, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

		this.name = name;
	}

	public String getName() {
		return name;
	}
}
