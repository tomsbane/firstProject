package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.user.CustomerJoinAction;
import action.user.CustomerLoginAction;
import action.user.CustomerModifyAction;
import action.user.CustomerViewAction;
import vo.ActionForward;


/**
 * Servlet implementation class UserFrontController
 */
@WebServlet("*.usr")
public class CustomerFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//반드시 첫줄
		//요청객체로부터 '프로젝트명+파일경로'까지 가져옴(예)/project/boardWriteForm.dog
		String requestURI=request.getRequestURI();
		//요청 URL : http://localhost:8090/project/boardWriteForm.dog
		//요청 URI : /project/userLogin.me
		
		//요청객체로부터 '프로젝트 path'만 가져옴(예)/project
		String contextPath=request.getContextPath();
		
		/* URI에서 contextPath길이만큼 잘라낸 나머지 문자열
		 * /project/userLogin.me - /project = "/userLogin.me"
		 */
		String command=requestURI.substring(contextPath.length());//(index 8~끝까지) 부분문자열 반환
		
		/* 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해서 동일한 타입(Action)으로 참조하기 위해
		 * 'Action 인터페이스' 타입의 변수 선언(혜574p) 
		 */
		Action action = null;
		ActionForward forward = null;
		/*---'로그인 폼 보기' -> 처리-----------------------------------*/
		if(command.equals("/userLogin.usr")) {//'로그인 폼 보기' 요청이면
			request.setAttribute("showPage", "/user/loginForm.jsp");
			forward = new ActionForward("user.jsp", false); //반드시 디스패치 방식으로 포워딩
			//forward = new ActionForward(false, "/user/loginForm.jsp");
		}else if(command.equals("/userLoginAction.usr")) {//'로그인 처리' 요청이면
			action = new CustomerLoginAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*----id 찾기 폼 보기 -> 처리----------------------------------*/

		/*----비밀번호 찾기 폼 보기 -> 처리----------------------------------*/

		/*----비밀번호 변경 폼 보기 -> 처리----------------------------------*/
		
		/*----회원가입 폼 보기 -> 처리----------------------------------*/
		else if(command.equals("/userJoin.usr")) {//'회원가입 폼 보기' 요청이면
			request.setAttribute("showPage", "/user/joinForm.jsp");
			forward = new ActionForward("user.jsp", false); //반드시 디스패치 방식으로 포워딩
		}else if(command.equals("/userJoinAction.usr")) {//'회원가입 처리' 요청이면
			action = new CustomerJoinAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*----'로그아웃' -> 처리 후 userMain.jsp-------------------*/
		else if(command.equals("/userLogout.usr")) {//'로그아웃' 요청이면
			HttpSession session= request.getSession();
			session.invalidate();//세션의 모든 속성을 삭제
			forward = new ActionForward("userMain.jsp", true);//리다이렉트 방식
		}
		/*----'사용자 한명 상세 정보 보기' -> 정보 수정 처리-------------------*/
		else if(command.equals("/userView.usr")) {//'사용자 한명 상세 정보 보기' 요청이면
			action = new CustomerViewAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/userModifyAction.usr")) {//'사용자 한명 상세 정보 수정' 요청이면
			action = new CustomerModifyAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*----'회원탈퇴'-------------------*/
		
		/***************************************************************
		 * 포워딩
		 * 
		 ***************************************************************/
		if(forward != null) {
			if(forward.isRedirect()) {//true:리다이렉트
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
