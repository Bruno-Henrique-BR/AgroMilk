package com.agromilk.br.service;

import com.agromilk.br.entity.FuncionarioEntity;
import com.agromilk.br.repository.FuncionarioRepository;
import com.agromilk.br.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<FuncionarioEntity> user = repository.findByEmail(email);

		if (user.isPresent()) {
			return new UserSS(user.get().getIdFuncionario(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
		}

		throw new UsernameNotFoundException(email);
	}



}
