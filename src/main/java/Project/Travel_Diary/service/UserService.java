package Project.Travel_Diary.service;

import Project.Travel_Diary.domain.User;
import Project.Travel_Diary.dto.UserLoginDto;
import Project.Travel_Diary.dto.UserRegisterDto;
import Project.Travel_Diary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
//사용자와 관련된 비즈니스 로직 서비스
public class UserService {

    private final UserRepository userRepository;

    //회원가입을 성공적으로 완료한 사용자의 정보를 메모리에 저장하기 위한 메소드
    public void save(UserRegisterDto userDto) {

        User user = new User(userDto.getLoginId(), userDto.getName(), userDto.getPassword());

        userRepository.save(user);

    }

    //로그인 한 정보가 메모리에 저장된 사용자의 정보(아이디, 패스워드)와 일치하는지 판단하는 메소드
    public User findUser(UserLoginDto userLoginDto) {

        Optional<User> userOptional = userRepository.findByLoginId(userLoginDto.getLoginId());

        User user = userOptional.orElse(null);

        if (user != null && user.getPassword().equals(userLoginDto.getPassword())) {
            return user;
        }
        else {
            return null;
        }

        /**
         * 위 로직을 축약하면 아래와 같다.
         *  return userRepository.findByLoginId(userLoginDto.getLoginId())
         *                 .filter(u -> u.getPassword().equals(userLoginDto.getPassword()))
         *                 .orElse(null);
         */

    }

    public boolean findId(String loginId) {

        Optional<User> userOptional = userRepository.findByLoginId(loginId);

        return userOptional.isEmpty();

    }

}
