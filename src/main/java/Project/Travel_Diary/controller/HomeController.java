package Project.Travel_Diary.controller;

import Project.Travel_Diary.domain.User;
import Project.Travel_Diary.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
/**
 * 로그인 하지 않은 사용자 : 메인화면
 * 로그인한 사용자 : 로그인 한 사용자 이름을 보여주는 메인화면
 */
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/home/{userId}")
    public String home(@PathVariable("userId") Long userId, HttpServletRequest request, Model model) {

        //요청에 있는 쿠키 값(JSESSIONID)을 통해 세션을 반환하는데 없으면 신규 세션을 생성하지 않는다(false).
        HttpSession session = request.getSession(false);

        //로그인 하지 않은 사용자 : 메인화면
        if (session == null) {
            return "home";
        }

        //로그인한 사용자 : 로그인 한 사용자 이름을 보여주는 메인화면
        User loginUser = (User)session.getAttribute("UserSession" + userId);

        //세션에 사용자 데이터(정보)가 없으면 로그인 하지 않은 메인화면으로 이동
        if (loginUser == null) {
            return "home";
        }

        //만약 다른 로그인된 사용자가 다른 사용자의 고유 번호를 URL에 입력할 경우 메인화면으로 이동시킨다.
        if (!loginUser.getId().equals(userId)) {
            return "home";
        }

        //세션에 사용자 데이터(정보)가 있으면 로그인된 메인화면으로 이동
        model.addAttribute("user", loginUser);

        return "loginHome";

    }



}
