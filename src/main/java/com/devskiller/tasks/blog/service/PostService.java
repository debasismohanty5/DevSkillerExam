package com.devskiller.tasks.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	//Find the Post with the provided ID
	public PostDto getPostById(Long id) {
	    return postRepository.findById(id)
	            .map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
	            .orElse(null);
	            //.orElseThrow(() -> new IllegalArgumentException("Blog post not found with id " + id));
	}

	//Save a POST with ID
	public Post savePost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.title());
		post.setContent(postDto.content());
		post.setCreationDate(postDto.creationDate());
		
		return postRepository.save(post);
	}

}
