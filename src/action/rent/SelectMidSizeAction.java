package action.rent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.SelectMidSizeService;
import svc.rent.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class SelectMidSizeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		SelectMidSizeService selectMidSizeService = new SelectMidSizeService();
		ArrayList<Rentcar> selectMidSizeList = selectMidSizeService.getMidSizeList();

		request.setAttribute("midSize", selectMidSizeList);
		request.setAttribute("showRent", "/rent/selectMidSize.jsp");

		forward = new ActionForward("rent_template.jsp", false);
		return forward;
	}
}
