package com.koreait.spring_boot_study.Sevice;

import com.koreait.spring_boot_study.Repository.PostRepository;
import org.springframework.stereotype.Service;

    @Service
    public class PostService {
        private final PostRepository postRepository;

        public PostService(PostRepository postRepository) {
            this.postRepository = postRepository;
        }

        public String getPost() {
            String result = postRepository.getPost();
            return result;
        }

        // public class PostService {
        //      public String getPost() {
        //          return "어떠한 게시물의 데이터"
        //      }
        // }

}
