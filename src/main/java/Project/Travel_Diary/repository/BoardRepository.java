package Project.Travel_Diary.repository;

import Project.Travel_Diary.domain.Board;
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
@Repository
public class BoardRepository {

    /**
     * 동시성(Thread) 문제를 고려하기 위해 AtomicLong을 사용한다.
     */
    private static AtomicLong nextId =  new AtomicLong(1L);

    //사용자의 게시글 저장 메소드
    public Board save(User user, Board board) {

        board.setId(nextId.incrementAndGet());
        user.getBoardList().add(board);

        return board;

    }


    //사용자의 게시글을 가져오기 위해 메모리에서 게시글을 반환
    public Optional<Board> findById(User user, Long board_id) {

        return findBoards(user).stream()
                .filter(b -> b.getId().equals(board_id))
                .findFirst();

    }

    //사용자에 저장된 모든 게시글 객체를 반환
    public List<Board> findBoards(User user) {

        return new ArrayList<>(user.getBoardList());

    }

}
