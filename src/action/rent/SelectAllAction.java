package action.rent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import svc.rent.ShortRentListService;
import vo.ActionForward;

import vo.Rentcar;

public class SelectAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		ShortRentListService shortRentListService = new ShortRentListService();
		ArrayList<Rentcar> selectAllList = shortRentListService.getCarList();

		request.setAttribute("AllList", selectAllList);
		request.setAttribute("showRent", "/rent/selectAll.jsp");
		
		forward=new ActionForward("rent_template.jsp", false);
		return forward;
	}

}
