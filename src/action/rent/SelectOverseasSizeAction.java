package action.rent;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.SelectOverseasService;

import vo.ActionForward;
import vo.Rentcar;

public class SelectOverseasSizeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		SelectOverseasService selectOverseasService = new SelectOverseasService();
		ArrayList<Rentcar> selectOverseasList = selectOverseasService.getOverseasList();

		request.setAttribute("overseasList", selectOverseasList);
		request.setAttribute("showRent", "/rent/selectOverseas.jsp");

		forward = new ActionForward("rent_template.jsp", false);
		return forward;
	}
}
