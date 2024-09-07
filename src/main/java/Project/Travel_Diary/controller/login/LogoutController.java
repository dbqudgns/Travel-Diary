package Project.Travel_Diary.controller.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//로그아웃 컨트롤러
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        //요청에 있는 JSESSIONID 값을 통해 세션을 반환한다. 없으면 생성하지 않는다.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); //세션을 삭제한다.
        }

        //로그아웃 한 후 로그인 되지 않은 메인화면으로 이동
        return "redirect:/";

    }
}
