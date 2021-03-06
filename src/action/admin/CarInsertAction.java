//새로운 개 상품 정보 등록을 처리하는 Action클래스
package action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.admin.AdminRegistService;
import svc.admin.CarInsertService;
import vo.ActionForward;
import vo.Rentcar;

//먼저, cos.jar를 lib안에 붙여넣기
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;//서버상에 a.txt있다면 a1.txt로 수정

public class CarInsertAction implements action.Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;//미리 만들어놓고 시작함
		
		String saveFolder = "/carImages";
		int maxSize=1024*1024*5;//한 번에 올릴 수 있는 최대용량 5M로 제한
		
		//파일을 업로드할 서버상의 디렉토리경로
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//서버 상에 업로드 된 파일이름을 얻어와
		//String imageFile = multi.getFilesystemName("imgeFile");
		
		String car_name = multi.getParameter("car_name");
		String car_group = multi.getParameter("car_group");
		int car_year = Integer.parseInt(multi.getParameter("car_year"));
		String car_reserve = multi.getParameter("car_reserve");
		int car_price = Integer.parseInt(multi.getParameter("car_price"));
		String car_brand = multi.getParameter("car_brand");
		String car_image = multi.getOriginalFileName("car_image");
		
		Rentcar newCar = new Rentcar(car_name, car_group, car_year, car_reserve, car_price, car_brand, car_image);
		
		CarInsertService carInsertService = new CarInsertService();
		//새로운 개 정보(dog)를 dog 테이블에  insert함
		boolean isInsertSuccess = carInsertService.registCar(newCar);
		//ActionForward forward = null;
		if(isInsertSuccess) {//새 개 상품 등록 성공
			forward=new ActionForward("adminCarList.ad", true);//주의 :
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
