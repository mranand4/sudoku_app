package com.sudoku2.model;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nonnull
	@Column(unique = true)
	private String email;
	
	@NonNull
	@JsonIgnore
	private String password;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.DELETE)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@JsonIgnore
    private List<Role> roles = new ArrayList<>();
	
	/**
	 * https://tenmilesquare.com/resources/software-development/spring-boot-jpa-relationship-quick-guide/
	 */
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private Set<Bookmark> bookmarks = new HashSet<>();
	
	
	/**
	 * https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion
	 * https://stackoverflow.com/questions/57380667/com-fasterxml-jackson-databind-exc-invaliddefinitionexception-no-serializer-fou
	 */
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private Set<Save> saves = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private Set<SolvedStats> solved = new HashSet<>();
	
	public User() {
		
	}
	
	public Set<Save> getSaves() {
		return saves;
	}

	public void setSaves(Set<Save> saves) {
		this.saves = saves;
	}

	public User(int id) {
		this.id = id;
	}
		
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password, String name) {
		this(email, password);
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return email;
	}

	public void setUsername(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<SolvedStats> getSolved() {
		return solved;
	}

	public void setSolved(Set<SolvedStats> solved) {
		this.solved = solved;
	}
	
	
}
