package Project.Travel_Diary.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
//사용자
public class User {

    private Long id; //사용자 고유 번호

    @NotBlank //null, "", " " 을 허용하지 않는다.
    private String loginId; //회원가입 시 입력 받아야 할 사용자 아이디

    @NotBlank
    private String name; //회원가입 시 입력 받아야 할 사용자 이름

    @NotBlank
    private String password; //회원가입 시 입력 받아야 할 사용자 비밀번호

    List<Board> boardList = new ArrayList<>(); //사용자 별 저장된 게시글 리스트

    public User(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

}
