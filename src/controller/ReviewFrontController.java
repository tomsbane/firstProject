package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import action.Action;
import action.rent.ShortRentAction;
import action.rent.ShortRentCheckAction;
import action.rent.ShortRentFinalCheckAction;
import action.rent.ShortRentListAction;
import action.review.ReviewListAction;
import action.review.ReviewWriteAction;
import vo.ActionForward;


@WebServlet("*.re")//마지막 url이 *.dog로 끝나는 요청을 매핑
public class ReviewFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFrontController() {
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
		
		
		String requestURI=request.getRequestURI();

		String contextPath=request.getContextPath();
		
	
		String command=requestURI.substring(contextPath.length());
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		/* 글쓰기 페이지를 열어주는 요청인 경우는 특별한 비지니스 로직을 실행할 필요없이
		 * 글쓰기 할 수 있는 뷰페이지로만 포워딩하면 됨
		 */
		if(command.equals("/review.re")) {//'상품 목록보기'요청이면
			action = new ReviewWriteAction();
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
