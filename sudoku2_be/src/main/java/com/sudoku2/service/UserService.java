package com.sudoku2.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sudoku2.model.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import com.sudoku2.dto.RegisterDto;
import com.sudoku2.model.Role;
import com.sudoku2.model.User;

/**
 * https://kamer-dev.medium.com/spring-boot-user-registration-and-login-43a33ea19745
 * @author shivansh
 *
 */

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(email).
				orElseThrow(() -> new UsernameNotFoundException("Can't find user with given email"));
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), mapRolesToAuthorities(user.getRoles()));				
	}
	
	public User getUserById(int id) {
		
		if(userRepository.existsById(id)) {
			return userRepository.getReferenceById(id);
		}
		
		return null;
		
	}
	
	public User updateUser(int id, RegisterDto dto) {
		if(userRepository.existsById(id)) {
			
			User user = userRepository.getReferenceById(id);
			
			if(dto.getEmail() != null) {
				user.setEmail(dto.getEmail());
			}
			
			if(dto.getName() != null) {
				user.setName(dto.getName());
			}
			
			if(dto.getPassword() != null) {
				user.setPassword(passwordEncoder.encode(dto.getPassword()));
			}
			
			return userRepository.save(user);
			
		}
		
		return null;
	}
	
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}
