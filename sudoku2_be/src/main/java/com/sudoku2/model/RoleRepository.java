package com.sudoku2.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java
 * @author shivansh
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(String name);
	boolean existsByName(String name);

}
