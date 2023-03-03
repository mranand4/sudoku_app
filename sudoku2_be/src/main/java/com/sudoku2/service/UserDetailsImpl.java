package com.sudoku2.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sudoku2.model.User;

public class UserDetailsImpl implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
    private Set<GrantedAuthority> authorities = new HashSet<>();

	public UserDetailsImpl(int id, String name, String username, String email, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		authorities.add(new SimpleGrantedAuthority("USER"));
	}
	
	  public static UserDetailsImpl build(User user) {
		    return new UserDetailsImpl(
		        user.getId(), 
		        user.getName(),
		        user.getUsername(), 
		        user.getEmail(),
		        user.getPassword()
		       );
	  }
	  
	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	  }

	  public int getId() {
	    return id;
	  }

	  public String getEmail() {
	    return email;
	  }
	  
	  public String getName() {
		    return name;
	  }

	  @Override
	  public String getPassword() {
	    return password;
	  }

	  @Override
	  public String getUsername() {
	    return username;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }	
	
	

}
