package action.rent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.SelectSmallSizeService;
import vo.ActionForward;
import vo.Rentcar;

public class SelectSmallSizeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		SelectSmallSizeService selectSmallSizeService = new SelectSmallSizeService();
		ArrayList<Rentcar> smallSizeList= selectSmallSizeService.getSmallSizeList();
		
		
		request.setAttribute("smallSize", smallSizeList);
		request.setAttribute("showRent", "/rent/selectSmallSize.jsp");
		
		return new ActionForward("rent_template.jsp", false);
	}

}
