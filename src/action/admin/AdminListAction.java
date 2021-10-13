package action.admin;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.rent.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class AdminListAction implements action.Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShortRentListService shortRentListService  = new ShortRentListService(); 
		
		ArrayList<Rentcar> carList = shortRentListService.getCarList();
		
		request.setAttribute("carList", carList);
		
		ActionForward forward =new ActionForward("admin/carInsert.jsp", false);//"디스패치 방식"으로 포워딩
		return forward;
	}

}
