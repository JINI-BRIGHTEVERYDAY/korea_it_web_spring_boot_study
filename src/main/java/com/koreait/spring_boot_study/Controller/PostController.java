package com.koreait.spring_boot_study.Controller;

import com.koreait.spring_boot_study.Sevice.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//controller 두 가지 방식이 존재함
//1. controller
//html(웹페이지) 반환하는 서버 사이드 렌더링 방식 (SSR)
//2. RestController
// JSON, 문자열 등 다양한 데이터를 반환한다 => REST API 서버 (웹서버)
// 서버 사이드 렌더링은 서버 측에서 프론트의 웹페이지까지 통째로 반환 그것을 브라우저에서 띄운다
// 그럼 해당 요청 경로에 따라서 웹페이지가 다 할당되어서 느림

// 하지만 우리가 하게 될 프론트엔드(리액트) + 백엔드(스프링부트 웹서버)
// 리액트는 클라이언트 사이드 렌더링 ( CSR) 즉, 프론트엔드 쪽에서 웹페이지를 로드
// 백엔드는 요청에 해당하는 데이터만 반환시켜준다
// 이러한 조합은 => Single Page Application (SPA) 방식


    @RestController
    @RequestMapping("/post")
    public class PostController {

        //Autowired
        @Autowired //필요한 객체를 자동으로 주입해주는 어노테이션
        private PostService postService;
        //PostService를 주입되기 전 시점에서 사용하게 되면, NPE이 발생할 수도 있다.
        // 예를 들어서, 생성자에서 바로 쓴다던가 서비스, 레퍼지토리, 어노테이션을 안붙였거나
        // 이런 상황에서

//        private final PostService postService;

    //Inversion Of Control => 제어의 역전
    //객체 생성과 제어의 주도권을 개발자가 아닌, 스프링부트가 가지는 것
    //ioc container => 스프링부트가 만든 객체들을 담아두고 관리하는 창고
    //필요한 곳이 있으면 꺼내서 넣어준다
    //ioc 컨테이너에서 해당 객체를 찾아서 자동으로 넣어주므로 우리는 new 할 필요가 없다
    //이미 실행될 때 ioc 컨테이너에 객체들이 생성되어서 보관되어있다.

    //의존성 주입, Dependency Injection => DI
    //필요한 객체 (의존성)를 직접 만들지 않고, 외부(스프링 부트)에서 대신 넣어주는 것

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

//        public PostController(PostService postService) {
//            postService.getPost();
//        }

        // 생성자가 어노테이션보다 더 권장된다
        // 명시적이고 명확하다
        // final이 붙어있기 때문에 불변을 보장한다
        // 생성자로 주입하면 객체가 생성될 때 필수로 의존성을 받아야 하기 때문에
        // 이후에 그 의존성을 바꿀 수 없어서 안정적이다
        // 애초에 객체 생성이 되기도 전에 생성자를 통해 주입이 완료된 상태
        // 생성 전부터 준비가 완료됨



        @GetMapping("/get")
        public String getPost() {
            System.out.println("get으로 들어오는 요청입니다.");
            return postService.getPost();
        }

        @GetMapping("/user")
        public String getPostUser() {
            System.out.println("get/user로 들어온 요청입니다.");
            return "어떤 게시물의 유저 정보";
        }

    }
