package Project.Travel_Diary.controller.login;

import Project.Travel_Diary.domain.User;
import Project.Travel_Diary.dto.UserLoginDto;
import Project.Travel_Diary.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
//로그인 컨트롤러
public class LoginController {

    private final UserService userService;

    //사용자가 로그인을 하기 위해 로그인 화면으로 이동시켜주는 메소드
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginDto") UserLoginDto loginDto) {return "login/loginForm";}

    //사용자가 로그인 완료 시 해당 사용자의 정보가 메모리에 있다면 메인화면(로그인 된 메인화면)으로 보내주는 메소드
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") UserLoginDto loginDto, BindingResult bindingResult,
                        HttpServletRequest request, RedirectAttributes redirectAttributes) {

        //만약 loginDto에 검증 오류가 있다면 다시 로그인 화면으로 보내주는 조건문
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //사용자가 로그인 시 입력한 아이디와 비밀번호를 이용해 서비스 메소드(findUser)를 호출해서 일치 여부를 판단한다.
        User loginUser = userService.findUser(loginDto);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 옳지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 처리 로직

        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성한다. => 세션을 생성하면 쿠키(JSESSIONID)가 생성된다.
        HttpSession session = request.getSession();

        //세션에 로그인한 사용자 정보를 보관한다. ex : [key : UserSession123| value : loginUser]
        session.setAttribute("UserSession" + loginUser.getId(), loginUser);

        /**
         *  사용자의 고유 번호를 넣은 이유는 세션 보관소에 객체가 저장될 때 "UserSession"으로만 설정하면
         *  사이트를 다중 사용자가 로그인 할 시 중복 값으로 저장되기 때문에 구별하기 위해 추가하였다.
         */

        //위 로직까지 로그인이 성공적으로 처리된 사용자는 세션 보관소에 사용자의 정보가 보관되고 브라우저에게는 쿠키값으로 임의의 값 JSESSIONID가 전달된다.

        redirectAttributes.addAttribute("userId", loginUser.getId());

        return "redirect:/home/{userId}";
    }



}
