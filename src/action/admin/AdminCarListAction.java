//개 상품 목록보기 요청을 처리하는 Action클래스
package action.admin;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.CarListService;
import svc.rent.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class AdminCarListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ShortRentListService shortRentListService  = new ShortRentListService(); 
		
		ArrayList<Rentcar> carList = shortRentListService.getCarList();
		
		request.setAttribute("carList", carList);
		request.setAttribute("showAdmin", "/admin/adminCarList.jsp");
		
		forward = new ActionForward("admin_template.jsp", false);//---▶이 파일 소스 작성하기
		
		return forward;
	}

}








