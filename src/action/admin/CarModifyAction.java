package action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.admin.CarModifyService;
import vo.ActionForward;
import vo.Rentcar;

public class CarModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//미리 만들어놓고 시작함
		
		int maxSize=1024*1024*5;//한 번에 올릴 수 있는 최대용량 5M로 제한
		
		//파일을 업로드할 서버상의 디렉토리경로
		String saveFolder = "/carImages";
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//서버 상에 업로드 된 파일이름을 얻어와
		//String imageFile = multi.getFilesystemName("imgeFile");
		
		int car_no = Integer.parseInt(multi.getParameter("car_no"));
		String car_name =multi.getParameter("car_name");
		String car_group =multi.getParameter("car_group");
		int car_year =Integer.parseInt(multi.getParameter("car_year"));
		String car_reserve =multi.getParameter("car_reserve");
		int car_price =Integer.parseInt(multi.getParameter("car_price"));
		String car_brand =multi.getParameter("car_brand");
		String car_image =multi.getOriginalFileName("car_image");
		
		Rentcar car =  new Rentcar(car_no, car_name, car_group, car_year, car_reserve, car_price, car_brand, car_image);
		
		CarModifyService carModifyService= new CarModifyService();
		boolean isCarModifySuccess = carModifyService.carModify(car);
		
		if(!isCarModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('차량수정에 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("adminCarList.ad", true);
		}
		return forward;
	}

}
