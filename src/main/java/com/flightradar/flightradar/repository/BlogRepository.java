package com.flightradar.flightradar.repository;

import com.flightradar.flightradar.model.blog.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BlogRepository extends CrudRepository<Blog,Long> {
    Blog findById(long id);

}
