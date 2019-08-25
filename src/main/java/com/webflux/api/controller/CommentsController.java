package com.webflux.api.controller;

import com.webflux.api.dto.CommentDTO;
import com.webflux.api.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService service;

    @GetMapping

    public Flux<CommentDTO> getComments() {
        return service.getComments();
    }
    @GetMapping("/{id}")
    public Mono<CommentDTO> getComment(@PathVariable("id") String id) {
        return service.getComment(id);
    }
}
