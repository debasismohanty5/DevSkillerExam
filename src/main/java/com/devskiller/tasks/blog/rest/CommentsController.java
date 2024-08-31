package com.devskiller.tasks.blog.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentsService;

@RestController
@RequestMapping("/posts/{postId}")
public class CommentsController {
	
	private final CommentsService commentService;

    public CommentsController(CommentsService commentService) {
        this.commentService = commentService;
    }
    
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentDto> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }
    
    @PostMapping
    public ResponseEntity<Long> addComment(@PathVariable Long postId, @RequestBody NewCommentDto newCommentDto) {
        Long commentId = commentService.addComment(postId, newCommentDto);
        return new ResponseEntity<>(commentId, HttpStatus.CREATED);
    }

}
