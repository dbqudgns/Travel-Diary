package Project.Travel_Diary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NotBlank //null, "", " " 을 허용하지 않는다.
    private String loginId; //회원가입 시 입력 받아야 할 사용자 아이디

    @NotBlank
    private String password; //회원가입 시 입력 받아야 할 사용자 비밀번호

    @NotBlank
    private String name; //회원가입 시 입력 받아야 할 사용자 이름

}
