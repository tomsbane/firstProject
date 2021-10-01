package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class ShortRentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShortRentListService shortRentListService  = new ShortRentListService(); 
		
		ArrayList<Rentcar> carList = shortRentListService.getCarList();
		
		request.setAttribute("carList", carList);
		
		ActionForward forward =new ActionForward("shortrent.jsp", false);//"디스패치 방식"으로 포워딩함
		return forward;
	}

}
