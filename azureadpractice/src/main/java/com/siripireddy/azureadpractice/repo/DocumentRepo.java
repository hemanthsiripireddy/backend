package com.siripireddy.azureadpractice.repo;

import com.siripireddy.azureadpractice.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface DocumentRepo extends CrudRepository<Document,Long> {
}
