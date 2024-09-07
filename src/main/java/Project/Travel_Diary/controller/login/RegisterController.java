package Project.Travel_Diary.controller.login;

import Project.Travel_Diary.dto.UserRegisterDto;
import Project.Travel_Diary.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
//회원가입 컨트롤러
public class RegisterController {

    private final UserService userService;

    //사용자가 회원가입을 할 시 회원가입 페이지로 이동되게 하는 메소드
    @GetMapping("/add")
    public String addForm(@ModelAttribute("userDto") UserRegisterDto userDto) {
        return "user/addUser";
    }

    //사용자가 회원가입 페이지에서 정보 입력 후 회원 정보를 저장하는 메소드
    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("userDto") UserRegisterDto userDto, BindingResult bindingResult) {

        //사용자가 회원가입 시 정보를 잘못 입력하면 다시 회원가입 페이지로 이동하게 하는 조건문
        if (bindingResult.hasErrors()) {

            log.info("errors={} ", bindingResult);

            return "user/addUser";
        }

        //회원 가입 성공 시 회원 정보를 메모리에 저장하기 위해 서비스 메소드 호출
        userService.save(userDto);

        //회원가입 성공 후 메인화면(로그인 된 상태로 메인화면으로 보내는거 아님)으로 이동
        return "redirect:/";

    }

    //회원가입시 아이디 중복 체크 API
    @GetMapping("api/id-Check")
    public ResponseEntity<Boolean> idCheck(@RequestParam("loginId") String loginId, Model model) {

        boolean b = userService.findId(loginId);
        log.info("{} => true : 아이디 사용 가능 / false : 아이디 사용 불가능 ", b);

        return ResponseEntity.ok(b);

    }
}
