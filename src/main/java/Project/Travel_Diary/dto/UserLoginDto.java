package Project.Travel_Diary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto {

    @NotBlank //null, "", " " 을 허용하지 않는다.
    private String loginId; //로그인 시 입력 받아야 할 사용자 아이디

    @NotBlank
    private String password; //로그인 시 입력 받아야 할 사용자 비밀번호

}
