package Project.Travel_Diary.interceptor;

import Project.Travel_Diary.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Iterator;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("사용자가 {}를 사용할 수 있는지 확인하기 위해 인터셉터 실행", requestURI);

        //사용자의 JSESSIONID 을 통해 세션 저장소에서 세션을 반환
        HttpSession session = request.getSession(false);

        //세션이 없으면 로그인 화면으로 리다이렉트
        if ((session == null)) {
            response.sendRedirect("/home");
            return false;
        }

        //세션에서 로그인한 사용자 객체(정보)를 가져오기
        Iterator<String> sessionAttributes = session.getAttributeNames().asIterator();
        User loginUser = null;

        while (sessionAttributes.hasNext()) {
            String sessionAttribute = sessionAttributes.next();

            //ex) 세션 속 Key : UserSession123
            if (sessionAttribute.startsWith("UserSession")) {
                loginUser = (User) session.getAttribute(sessionAttribute);
                break;
            }

        }

        //회원가입 한 사용자가 아니라면 로그인 화면으로 리다이렉트
        if (loginUser == null) {
            response.sendRedirect("/login");
            return false;
        }

        //회원가입 한 사용자면 해당 requestURI 매핑된 핸들러(컨트롤러)로 이동!
        return true;

    }
}
