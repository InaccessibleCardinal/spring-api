package com.webflux.api.service;

import java.util.List;
import com.webflux.api.client.WebClientInitializer;
import com.webflux.api.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentsService {

    private final WebClientInitializer webClient;
    private final String url;

    @Autowired
    CommentsService(WebClientInitializer webClientInitializer, @Value("${comments.url}") String url) {
        this.webClient = webClientInitializer;
        this.url = url;
    }

    public Flux<CommentDTO> getComments() {
        return webClient.initialize(url)
                .get()
                .retrieve()
                .bodyToFlux(CommentDTO.class);
    }

    public List<CommentDTO> getCommentsSync() {
        return getComments().collectList().block();
    }

    public Mono<CommentDTO> getComment(String id) {
        return webClient.initialize(url)
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(CommentDTO.class);
    }

    public CommentDTO getCommentSync(String id) {
        return getComment(id).block();
    }
}
