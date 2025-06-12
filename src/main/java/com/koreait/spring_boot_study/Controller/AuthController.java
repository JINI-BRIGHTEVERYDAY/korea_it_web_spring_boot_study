package com.koreait.spring_boot_study.Controller;

import com.koreait.spring_boot_study.dto.SignupReqDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    //@RequestParam
    //클라이언트가 URL 쿼리스트링으로 넘긴 값을 메소드 파라미터로 전달
    //get으로

    @GetMapping("/get")
    public String getUser(@RequestParam String userId) {
        System.out.println("RequestParam으로 들어온 값 : " + userId);
        return "RequestParam으로 들어온 값 : " + userId;

        // http://localhost:8080/auth/get?userId=10
    }

    @GetMapping("get/name")
    public String getUsername(@RequestParam(value="name", defaultValue = "홍길동") String username, @RequestParam(required = false) Integer age) {
        //defaultValue
        // http://localhost:8080/auth/get/name
        // 홍길동null
        // 기본값도 설정이 가능하다
        // 다른 타입도 가능하며 여러 개의 RequestParam도 받을 수 있다
        // int는 null을 허용하지 않기 때문에 값이 없음의 상태가 될 수 있다
        // 그래서 required = false를 했지만 에러가 뜨고 => Integer로 해야 null로 받을 수 있다
        // 만약 필수값이 false이고, 기본값이 설정되어 있다면, 필수값 설정이 무의미해진다



        // (required = false)
        // 받아도 되고 안받아도 되고
        // int 와 integer

        // int로 받으면 오류, integer로 받으면 null
        //http://localhost:8080/auth/get/name?name=JINI
         System.out.println(username + age);
        return username + age;

        //http://localhost:8080/auth/get/name?name=JINI&age=29

        // http://localhost:8080/auth/get/name?username=JINI&age=29
        // ( value="name") 없을 때
    }
    //안에서 사용하는 변수명과 쿼리스트링의 키값이 다른 경우 괄호안에 표기를 해주면 된다
    //그리고 기본값도 설정이 가능하다
    //

    @GetMapping("/get/names")
    public String getUsernames(@RequestParam List<String> names) {
        return names.toString();
    }

    //http://localhost:8080/auth/get/name?name=JINI,JINI2,JINI3

    //RequestParam 주의사항
    //파라미터가 없으면? 500 에러 뜰 것
    //타입이 안맞을 때
    //이름이 불일치할 때
    //민감한 정보는 들어가서는 안된다


    //실습
    //요청주소는 /search => name, email
    //name은 필수X, email은 기본값으로 no-email
    //요청을 =? /auth/search?name=lee
    //반환 => "검색 조건 - 이름 : ***, 이메일 : ***"


    @GetMapping("search")
    public String searchUser(@RequestParam(required = false) String name, @RequestParam(defaultValue = "no-email") String email){
//    public String searchUser(@RequestParam(value="name") String name, @RequestParam(required = false, value="email", defaultValue = "no-email") String email){
//      틀린 코드
//        System.out.println(name + email);
        return "검색 조건 - 이름 : " + name + ", 이메일 : " + email;

        //http://localhost:8080/auth/search?name=JINI
    }

    //@RequestBody
    //HTTP 요청의 바디에 들어있는 JSON 데이터를 자바 객체 (DTO)로 변환해서 주입해주는 어노테이션
    //클라이언트가 JSON 형식으로 데이터 보냄
    //백엔드 서버는 그 JSON을 @RequestBody가 붙은 DTO로 자동 매핑
    //일반적으로 POST, PUT, PATCH에서 사용

    //DTO (Data Transfer Object)
    //데이터를 전달하기 위한 객체
    //클라이언트간에 데이터를 주고받을 때 사용하는 중간 객체
    //

    @PostMapping("/signup")
    public String signup(@RequestBody SignupReqDto signupReqDto) {
        System.out.println(signupReqDto);

        return signupReqDto.getUsername() + "님 회원가입이 완료되었습니다."; // Json으로 이제 받아야 한다
    }

    // Post요청 signin 로그인 로직
    // SigninReqDto 필요할 것 => email, password 받아야 함
    // 반환은 "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다."

//    @PostMapping("/signin")
//    public String signin(@RequestBody SigninReqDto signinReqDto) {
//
//        return "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습다.";
//    }

}
