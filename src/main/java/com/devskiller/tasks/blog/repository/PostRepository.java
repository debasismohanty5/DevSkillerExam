package com.devskiller.tasks.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devskiller.tasks.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
