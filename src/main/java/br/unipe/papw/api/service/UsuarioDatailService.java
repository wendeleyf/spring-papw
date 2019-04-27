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
package br.unipe.papw.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.unipe.papw.api.model.Group;
import br.unipe.papw.api.model.Role;
import br.unipe.papw.api.model.User;
import br.unipe.papw.api.repository.GroupRepository;
import br.unipe.papw.api.repository.RolesRepository;
import br.unipe.papw.api.repository.UserRepository;
import br.unipe.papw.config.security.UserLogin;

@Component
public class UsuarioDatailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		return new UserLogin(user.getName(), user.getLogin(), user.getPass(), authorities(user));
	}

	public Collection<? extends GrantedAuthority> authorities(User usuario) {
		return authorities(groupRepository.findByUsersIn(usuario));
	}

	public Collection<? extends GrantedAuthority> authorities(List<Group> grupos) {
		Collection<GrantedAuthority> auths = new ArrayList<>();

		for (Group grupo : grupos) {
			List<Role> lista = rolesRepository.findByGroupsIn(grupo);

			for (Role role : lista) {
				auths.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			}
		}

		return auths;
	}
}
