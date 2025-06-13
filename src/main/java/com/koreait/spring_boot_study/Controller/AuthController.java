package com.koreait.spring_boot_study.Controller;

import com.koreait.spring_boot_study.Repository.AuthRepository;
import com.koreait.spring_boot_study.Sevice.AuthService;
import com.koreait.spring_boot_study.dto.SigninReqDto;
import com.koreait.spring_boot_study.dto.SigninRespDto;
import com.koreait.spring_boot_study.dto.SignupReqDto;
import com.koreait.spring_boot_study.dto.SignupRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
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

//    @PostMapping("/signup")
//    public String signup(@RequestBody SignupReqDto signupReqDto) {
//        System.out.println(signupReqDto);
//
//        return signupReqDto.getUsername() + "님 회원가입이 완료되었습니다."; // Json으로 이제 받아야 한다
//    }



    // Post요청 signin 로그인 로직
    // SigninReqDto 필요할 것 => email, password 받아야 함
    // 반환은 "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습니다."

//    @PostMapping("/signin")
//    public String signin(@RequestBody SigninReqDto signinReqDto) {
//
//        return "로그인 완료 : " + signinReqDto.getEmail() + "님 반갑습다.";
//    }

    // ResponseEntity
    // HTTP 응답 전체를 커스터마이징해서 보낼 수 있는 스프링 클래스
    // HTTP 상태코드, 응답바디, 응답헤더까지 모두 포함

    @PostMapping("/signin")
    public ResponseEntity<SigninRespDto> signin(@RequestBody SigninReqDto signinReqDto) {
        if(signinReqDto.getEmail() == null || signinReqDto.getEmail().trim().isEmpty()) {
            SigninRespDto signinRespDto = new SigninRespDto("failed", "이메일을 다시 입력해주세요.");
            return ResponseEntity.badRequest().body(signinRespDto);

        } else if (signinReqDto.getPassword() == null || signinReqDto.getPassword().trim().isEmpty()) {
            SigninRespDto signinRespDto = new SigninRespDto("failed", "비밀번호를 다시 입력해주세요.");
            return ResponseEntity.badRequest().body(signinRespDto);
        }

        SigninRespDto signinRespDto = new SigninRespDto("success", "로그인 성공");
        return ResponseEntity.status(HttpStatus.OK).body(signinRespDto);
        //     =   return ResponseEntity.ok().body(signinRespDto);
    }


    // 상태코드의 종류
    // 200 OK => 요청 성공
    // 400 BAD REQUEST => 잘못된 요청 (ex. 유효성 실패, JSON 파싱 오류)
    // 401 Unauthorized => 인증 실패 (ex. 로그인 안 됨, 토큰 없음)
    // 403 Forbidden => 접근 권한이 없음 (ex. 관리자만 접근이 가능)
    // 404 Not Found => 리소스 없음
    // 409 Conflict => 중복 등으로 인한 충돌 (ex. 이미 존재하는 이메일)
    // 500 Internal Server Error => 서버 내부 오류 (코드 문제, 예외 등)

    // 200은 정상적으로 됐다, 400은 네가 잘못 보낸거야, 500은 서버가 터졌다
    //

    @PostMapping("/signup")
    public ResponseEntity<SignupRespDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok().body(authService.signup(signupReqDto));

        // 중복 체크 같은 API 는 대부분 200 OK로 응답하고
        // 응답 본문 (JSON)에 "중복 여부"를 표시한다
        // 중복체크는 정상적인 요청에 대한 정상적인 응답이기 때문에 200OK
        // 중복이든 아니든 요청 자체는 정상적으로 처리되었기 때문에 400/409 같은 에러코드는
        // 대신에 JSON 응답 내부에서 중복됨 / 가능함을 구분한다
        // 그럼 언제 에러코드 (409 Conflict)를 쓰느냐?
        // 진짜 예외 상황일 때
        // 중복된 이메일로 회원가입을 실제로 시도했을 때 이럴 때  409를 쓴다
    }
}
