package com.devskiller.tasks.blog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devskiller.tasks.blog.model.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
    List<Comment> findByPostIdOrderByCreationDateDesc(Long id);

}
