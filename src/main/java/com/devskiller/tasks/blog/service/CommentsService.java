package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
public class CommentsService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent
	 *         first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		List<Comment> comments = null;
		Optional<Post> findById = postRepository.findById(postId);
		if(findById != null) {
			
		
		comments = commentRepository.findByPostIdOrderByCreationDateDesc(postId);
		
		}
		return comments.stream()
				.map(comment -> new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(), comment.getCreationDate()))
				.collect(Collectors.toList());
	}


	/**
	 * Creates a new comment
	 *
	 * @param postId        id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post
	 *                                  for passed postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		if (postId == null)
			throw new IllegalArgumentException("Post ID cannot be null");

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("Post not found for this " + postId));

		Comment comment = new Comment();
		comment.setPost(post);
		comment.setComment(newCommentDto.content());
		comment.setAuthor(newCommentDto.author());
		comment.setCreationDate(LocalDateTime.now());

		Comment savedComment = commentRepository.save(comment);
		return savedComment.getId();
	}

}
