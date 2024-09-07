package Project.Travel_Diary;

import Project.Travel_Diary.domain.User;
import Project.Travel_Diary.repository.UserRepository;
import Project.Travel_Diary.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;

    // 테스트용 데이터 추가
    @PostConstruct
    public void init() {

        User user = new User();
        user.setLoginId("dbqudgns");
        user.setPassword("1234");
        user.setName("유병훈");
        userRepository.save(user);

    }

}
