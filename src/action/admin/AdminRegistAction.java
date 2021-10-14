package action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.admin.AdminInsertCarService;
import vo.ActionForward;
import vo.Rentcar;

public class AdminRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		int maxSize=1024*1024*5;//한 번에 올릴 수 있는 최대용량 5M로 제한
		
		//파일을 업로드할 서버상의 디렉토리경로
		String saveFolder = "/carImages";//미리 폴더만들기
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String car_name = multi.getParameter("car_name");
		String car_group = multi.getParameter("car_group");
		int car_year = Integer.parseInt(multi.getParameter("car_year"));
		String car_reserve = multi.getParameter("car_reserve");
		int car_price = Integer.parseInt(multi.getParameter("car_price"));
		String car_brand = multi.getParameter("car_brand");
		String car_image = multi.getOriginalFileName("car_image");
		
		Rentcar newCar = new Rentcar(car_name, car_group, car_year, car_reserve, car_price, car_brand, car_image);
		
		AdminInsertCarService adminInsertCarService=new AdminInsertCarService();
		
		boolean isAdminInsertCarSuccess=adminInsertCarService.registCar(newCar);
		
		if(isAdminInsertCarSuccess) {
			forward = new ActionForward("customerList.ad", true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('어드민 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
