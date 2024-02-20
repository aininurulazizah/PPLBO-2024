package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.blog.repository.PostJpaRepository;
import com.blog.repository.PostRepository;
import com.blog.vo.Post;

@Service
public class PostService {
	private static List<Post> posts;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostJpaRepository jpaRepository;

	public List<Post> getListPosts() {
		
		List<Post> postList = postRepository.findPost();
		
		return postList;
	}
	
	public List<Post> getPosts(){
		List<Post> posts = jpaRepository.findAllByOrderByUpdtDateDesc();
		return posts;
	}
	
	public List<Post> getPostsOrderByUpdtAsc() {

		List<Post> postList = jpaRepository.findAllByOrderByUpdtDateAsc();
		
		return postList;
	}
	
	public List<Post> getPostsOrderByRegDesc() {

		List<Post> postList = postRepository.findPostOrderByRegDateDesc();
		
		return postList;
	}
	
//	public List<Post> searchPostByTitle(String query) {
//
//		List<Post> postList = postRepository.findPostLikeTitle(query);
//		
//		return postList;
//	}
	
//	public List<Post> searchPostByContent(String query) {
//
//		List<Post> postList = postRepository.findPostLikeContent(query);
//		
//		return postList;
//	}
	
	public Post getPost(Long id) {
		Post post = jpaRepository.findOneById(id);
		return post;
	}
	
	public boolean savePost(Post post) {
		Post result = jpaRepository.save(post);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}

	public boolean deletePost(Long id) {
		Post result = jpaRepository.findOneById(id);
		
		if(result == null)
			return false;
		
		jpaRepository.deleteById(id);
		return true;
	}
	
	public boolean updatePost(Post post) {
		Post result = jpaRepository.findOneById(post.getId());
		
		if(result == null)
			return false;
		
		if(!StringUtils.isEmpty(post.getTitle())) {
			result.setTitle(post.getTitle());
		}
		
		if(!StringUtils.isEmpty(post.getContent())) {
			result.setContent(post.getContent());
		}
		
		jpaRepository.save(result);
		
		return true;
	}
	
	public List<Post> searchPostByTitle(String query){
		
		List<Post> posts = jpaRepository.findByTitleContainingOrderByUpdtDateDesc(query);
		
		return posts;
	}
	
	public List<Post> searchPostByContent(String query) {

		List<Post> postList = jpaRepository.findByContentContainingOrderByUpdtDateDesc(query);
		
		return postList;
	}
	
}
