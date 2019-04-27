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
package br.unipe.papw.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"br.unipe.papw.config.security"})
public class PersonApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
}
