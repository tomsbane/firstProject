package action.rent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.SelectFullSizeService;
import svc.rent.SelectMidSizeService;
import svc.rent.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class SelectFullSizeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		SelectFullSizeService selectFullSizeService = new SelectFullSizeService();
		ArrayList<Rentcar> selectFullSizeList = selectFullSizeService.getFullSizeList();

		request.setAttribute("fullSize", selectFullSizeList);
		request.setAttribute("showRent", "/rent/selectFullSize.jsp");

		forward = new ActionForward("rent_template.jsp", false);
		return forward;
	}
}
