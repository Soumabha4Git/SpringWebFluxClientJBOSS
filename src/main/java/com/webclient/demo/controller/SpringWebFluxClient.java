package com.webclient.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;



import com.webclient.demo.model.Course;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class SpringWebFluxClient {
	
	private WebClient webClient = WebClient.create("http://localhost:8083");
	
	@GetMapping("get/all")
	public Flux<Course> getAllCourses() {
		return webClient.get().uri("NodeJSRestApiOracle/get/all")
							  .accept(MediaType.APPLICATION_JSON)
							  .retrieve()
							  .bodyToFlux(Course.class)
							  .log("retrieve allEmps :: ");
	}
	
	
	@GetMapping("get/{id}")
	public Mono<Course> getCourseById(@PathVariable("id") String id) {
		return webClient.get().uri("NodeJSRestApiOracle/get/{id}", id)
							  .accept(MediaType.APPLICATION_JSON)
							  .retrieve()
							  .bodyToMono(Course.class)
							  .log("retrieve empById :: ");
	}
	
	
	@PostMapping("/post")
	public Mono<Course> addCourse(@RequestBody Course course) {
		return webClient.post().uri("NodeJSRestApiOracle/post")
				  .accept(MediaType.APPLICATION_JSON)
				  .body(BodyInserters.fromObject(course)).retrieve()
				  .bodyToMono(Course.class)
				  .log("insert using body :: ");		
	}
	
	
	@PutMapping("put/{id}")
	public Mono<Course> updateCourseById(@RequestBody Course course, @PathVariable("id") String id) {
		return webClient.put().uri("NodeJSRestApiOracle/put/{id}", id)
							  .accept(MediaType.APPLICATION_JSON)
							  .syncBody(course)
							  .retrieve()
							  .bodyToMono(Course.class)
							  .log("update empById :: ");
	}
	
	
	@DeleteMapping("delete/{id}")
	public Mono<Course> removeCourseById(@PathVariable("id") String id) {
		return webClient.delete().uri("NodeJSRestApiOracle/delete/{id}", id)
							  .accept(MediaType.APPLICATION_JSON)
							  .retrieve()
							  .bodyToMono(Course.class)
							  .log("remove empById :: ");
	}

}
