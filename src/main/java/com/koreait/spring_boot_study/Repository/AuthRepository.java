package com.koreait.spring_boot_study.Repository;

import com.koreait.spring_boot_study.dto.SignupReqDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthRepository {
    private final Map<String, String> userDB = new HashMap<>();

    public AuthRepository() {
        userDB.put("test@example.com", "홍길동");

    }

    public int findByEmail(String email) {
        if(userDB.containsKey(email)) {
            return 0;
        } else {
            return 1;
        }
    }

    public int addUser(SignupReqDto signupReqDto) {
        userDB.put(signupReqDto.getEmail(), signupReqDto.getUsername());
        return 1;
    }
}
