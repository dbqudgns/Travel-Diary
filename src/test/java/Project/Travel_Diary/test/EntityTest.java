package Project.Travel_Diary.test;

import Project.Travel_Diary.domain.Board;
import Project.Travel_Diary.domain.Enum.Continent;
import Project.Travel_Diary.domain.UploadFile;
import Project.Travel_Diary.domain.User;
import Project.Travel_Diary.repository.BoardRepository;
import Project.Travel_Diary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Project.Travel_Diary.domain.Enum.Continent.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class EntityTest {

    UserRepository user_repository = new UserRepository();
    BoardRepository board_repository = new BoardRepository();

    //LocalDate 객체의 특징을 파악하기 위한 테스트
    void Board_domain() {

        Board board = new Board();

        /**
         * LocalDate.parse("2024-01-08")는 날짜를 기본 형식 "yyyy-MM-dd"을 사용하여
         * 문자열을 LocalDate 객체로 변환합니다.
         * 이후 이 LocalDate 객체를 board.setTravelDateTime()에 보내게 된다. 여기서 중요한 점은 객체(Board) 내부에 저장되는 값은 단순히 LocalDate 객체라는 점!!
         */
        board.setStartTime(LocalDate.parse("2024-01-08"));

        log.info("Date : {}", board.getStartTime()); //getXXX() 메소드 수행 시 YYYY-MM-DD 형식으로 반환된다.

        assertThat(board.getStartTime()).isEqualTo(LocalDate.parse("2024-01-08"));

    }

    @Test
    //AtomicLong이 정상 실행되는지 확인하기 위한 테스트
    public void userTest() {

        User user1 = new User();

        user_repository.save(user1);

        Long id1 = user1.getId();
        log.info("id = {}", id1);
        assertThat(id1).isNotNull();

        User user2 = new User();

        user_repository.save(user2);

        Long id2 = user2.getId();
        log.info("id = {}", id2);
        assertThat(id1).isNotNull();

    }

    @Test
    //전체적으로 엔티티 관계를 테스트
    public void EntityTest() {

        User user1 = new User();
        user1.setLoginId("EntityTest");
        user1.setName("엔티티 관계 테스트");
        user1.setPassword("1234");
        user_repository.save(user1);

        Board board1 = new Board();

        board1.setTitle("일본 도쿄 방문");
        board1.setContent("도쿄 너무 재밌었다. 다음에 또 가고 싶다.");
        board1.setExpense(600000);
        board1.setContinent(ASIA);
        board1.setNation("일본");
        board1.setStartTime(LocalDate.parse("2024-08-25"));
        board1.setEndTime(LocalDate.parse("2024-08-30"));
        board_repository.save(user1, board1);

        UploadFile uploadFile1 = new UploadFile();
        UploadFile uploadFile2 = new UploadFile();

        List<UploadFile> uploadFiles = new ArrayList<>();
        uploadFiles.add(uploadFile1);
        uploadFiles.add(uploadFile2);

        board1.setFileList(uploadFiles);

        Optional<User> findUser = user_repository.findByLoginId("EntityTest");
        User user11 = findUser.get();

        Optional<Board> findBoard = board_repository.findById(user1, board1.getId());
        Board board11 = findBoard.get();

        assertThat(user11).isEqualTo(user1); //동등성 : 두 개의 객체의 필드 값이 같은지 판단
        log.info("user11 = {}", user11);
        log.info("user1 = {}", user1);

        assertThat(board11).isEqualTo(board1);
        log.info("board11 = {}", board11);
        log.info("board1 = {}", board1);


    }

}
