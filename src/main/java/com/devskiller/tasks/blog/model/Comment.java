package com.devskiller.tasks.blog.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
	@Id
    @GeneratedValue
    private Long id;
	
	private String comment;
    
    private String author;
    
    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @CreationTimestamp
    private LocalDateTime creationDate;

}
