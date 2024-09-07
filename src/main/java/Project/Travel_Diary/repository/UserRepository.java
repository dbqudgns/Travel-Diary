package Project.Travel_Diary.repository;

import Project.Travel_Diary.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository //사용자(회원) 저장소
public class UserRepository {

    /**
     * 동시성(Thread) 문제를 고려하기 위해 ConcurrentHashMap, AtomicLong을 사용한다.
     */
    private static final Map<Long, User> store = new ConcurrentHashMap<>();
    private static AtomicLong nextId =  new AtomicLong(1L);

    //사용자 회원가입 시 메모리에 저장
    public User save(User user) {

        //AtomicLong의 현재 값을 가져오고 1씩 증가시켜 id로 설정
        user.setId(nextId.getAndIncrement());

        store.put(user.getId(), user);

        log.info("사용자 회원가입 완료 = {}", user);

        return user;

    }

    //사용자의 정보를 가져오기 위해 메모리에서 사용자 반환
    public Optional<User> findByLoginId(String loginId) {

        return findUsers().stream()
                .filter(u -> u.getLoginId().equals(loginId))
                .findFirst();

    }

    //메모리에 저장된 모든 사용자 객체를 반환
    public List<User> findUsers() {

        return new ArrayList<>(store.values());

    }

    //메모리에 저장된 모든 사용자 정보 삭제
    public void clear() {
        store.clear();
    }


}
