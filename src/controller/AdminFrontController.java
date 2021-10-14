package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import action.Action;
import action.admin.AdminCarListAction;
import action.admin.AdminListAction;
import action.admin.AdminRegistAction;
import action.admin.CarRegistAction;
import action.admin.CarRegistFormAction;
import action.admin.CarViewAction;
import action.admin.CustomerListAction;
import action.admin.NormalRegistAction;
import action.admin.ReserveCarAction;
import action.admin.ReserveChangeAction;
import action.admin.SelectCustomerAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
//확장자가 do이면 무조건 DogFrontController로 이동하여 doProcess()메서드 실행함
@WebServlet("*.ad")//마지막 url이 *.dog로 끝나는 요청을 매핑
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	//이 서블릿으로 들어오는 post나 get방식의 모든 요청은 doProcess()를 호출하여 처리
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//반드시 첫줄
		
		//요청객체로부터 '프로젝트명+파일경로'까지 가져옴(예)/project/boardWriteForm.dog
		String requestURI=request.getRequestURI();
		//요청 URL : http://localhost:8090/project/boardWriteForm.dog
		//요청 URI : /project/boardWriteForm.dog
		
		//요청객체로부터 '프로젝트 path'만 가져옴(예)/project
		String contextPath=request.getContextPath();
		
		/* URI에서 contextPath길이만큼 잘라낸 나머지 문자열
		 * /project/boardWriteForm.dog - /project = "/boardWriteForm.dog"
		 */
		String command=requestURI.substring(contextPath.length());//(index 8~끝까지) 부분문자열 반환
		
		/* 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해서 동일한 타입(Action)으로 참조하기 위해
		 * 'Action 인터페이스' 타입의 변수 선언(혜574p) 
		 */
		Action action = null;
		ActionForward forward = null;
		
		/* 글쓰기 페이지를 열어주는 요청인 경우는 특별한 비지니스 로직을 실행할 필요없이
		 * 글쓰기 할 수 있는 뷰페이지로만 포워딩하면 됨
		 */
		if(command.equals("/adminCarList.ad")) {//'상품 목록보기'요청이면
			action = new AdminCarListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/carInsert.ad")) {//'상품 등록'요청이면
			action = new AdminListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		/*
		if(command.equals("/carList.ad")) {//'개 상품 목록보기'요청이면
			action = new AdminCarListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		*/
		
		else if(command.equals("/carView.ad")) {//'특정 카 상품의 상세 정보 보기' 요청이면						
			action = new CarViewAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reserveChange.ad")) {//'예약 값변경' 요청이면						
			action = new ReserveChangeAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/reserveCarList.ad")) {//'예약한 카 리스트 정보 보기' 요청이면						
			action = new ReserveCarAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/carRegistForm.ad")) {//'새로운 카 상품 정보 등록 폼의 뷰페이지 보기' 요청이면						
			
			action = new CarRegistFormAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/carRegist.ad")) {//'새로운 카 등록을 처리하는' 요청이면						
			action = new CarRegistAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/customerList.ad")) {//'전체 회원정보 보기' 요청이면						
			action = new CustomerListAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/customerView.ad")) {//'특정 회원정보 보기' 요청이면						
			action = new CustomerListAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/selectCustomer.ad")) {//'회원id로 찾기' 요청이면						
			action = new SelectCustomerAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/adminRegist.ad")) {//'회원id로 관리자등록' 요청이면						
			action = new AdminRegistAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/normalRegist.ad")) {//'회원id로 일반고객등록' 요청이면						
			action = new NormalRegistAction();			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		/***************************************************************
		 * 포워딩
		 * 
		 ***************************************************************/
		if(forward != null) {
			if(forward.isRedirect()) {//isRedirect=true : 주소변경(새요청), request 공유 못함
				response.sendRedirect(forward.getPath());//응답-리다이렉트 방식
				//
			}else {//isRedirect=false :디스패치 방식
				//RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				//dispatcher.forward(request, response);//기존요청,기존응답 그대로 보내므로 주소 그대로.request공유
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	}

}
