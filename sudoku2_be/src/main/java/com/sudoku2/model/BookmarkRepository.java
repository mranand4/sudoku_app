package com.sudoku2.model;

import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, UserPuzzleCompositeId> {
	

}
