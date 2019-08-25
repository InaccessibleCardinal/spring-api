package com.webflux.api.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import reactor.core.publisher.Flux;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {
    Flux<PostDTO> posts;
    Flux<CommentDTO> comments;

    public void setPosts(Flux<PostDTO> posts) {
        this.posts = posts;
    }

    public void setComments(Flux<CommentDTO> comments) {
        this.comments = comments;
    }

    public Flux<PostDTO> getPosts() {
        return posts;
    }
    public Flux<CommentDTO> getComments() {
        return comments;
    }


}
