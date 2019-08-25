package com.webflux.api.controller;

import com.webflux.api.dto.PostDTO;
import com.webflux.api.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService service;
    private static int compareIds(PostDTO m1, PostDTO m2) {
        return Integer.parseInt(m1.getId()) < Integer.parseInt(m2.getId()) ? -1 : 1;
    }
    @GetMapping
    public Flux<PostDTO> getPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    public Mono<PostDTO> getPost(@PathVariable("id") String id) {
        return service.getPost(id);
    }

    @GetMapping("/all")
    public Flux<PostDTO> getAll() {
        List<Mono<PostDTO>> allPMonos = new ArrayList<>();
        for (int i = 1; i < 50; ++i) {
            Mono<PostDTO> p = getPost(Integer.toString(i));
            allPMonos.add(p);
        }
        return Flux.fromIterable(allPMonos)
                .flatMap(s -> s)
                .sort(PostsController::compareIds);
    }

}
