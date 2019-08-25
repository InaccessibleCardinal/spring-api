package com.webflux.api.service;

import com.webflux.api.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.webflux.api.client.WebClientInitializer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class PostsService {
    @Autowired
    private WebClientInitializer webClientInitializer;
    private String url;

    public PostsService(WebClientInitializer webClientInitializer, @Value("${posts.url}") String url) {
        this.webClientInitializer = webClientInitializer;
        this.url = url;
    }

    public Flux<PostDTO> getPosts() {
        return webClientInitializer
                .initialize(url)
                .get()
                .retrieve()
                .bodyToFlux(PostDTO.class);
    }

    public List<PostDTO> getPostsSync() {
        return getPosts().collectList().block();
    }

    public Mono<PostDTO> getPost(String id) {
        return webClientInitializer
                .initialize(url)
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(PostDTO.class);
    }

    public PostDTO getPostSync(String id) {
        return getPost(id).block();
    }

}
