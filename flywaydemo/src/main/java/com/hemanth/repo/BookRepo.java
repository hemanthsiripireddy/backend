package com.hemanth.repo;

import com.hemanth.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book,Integer> {
}
