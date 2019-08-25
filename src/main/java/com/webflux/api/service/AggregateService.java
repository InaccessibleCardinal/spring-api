package com.webflux.api.service;

import com.webflux.api.dto.CommentDTO;
import com.webflux.api.dto.PostDTO;
import com.webflux.api.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AggregateService {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @Autowired
    public AggregateService(PostsService postsService, CommentsService commentsService) {
        this.postsService = postsService;
        this.commentsService = commentsService;
    }




//    public Mono<ResponseDTO> getAggregate() {
//        return Mono.just(null);
//    }

}
