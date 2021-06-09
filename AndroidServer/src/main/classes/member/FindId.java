package member;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberService;
import dto.MemberInfo;
import exception.EmptyMemberInfoException;
import exception.OverflowMemberInfoException;

/**
 * Servlet implementation class Find
 */
@WebServlet("/member/findid")
public class FindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MemberInfo memberFindIdInfo = new MemberInfo(request,email);//email을 매개변수로 추가시켜줘서 쿼리로 보내줄 email값을 받아옴
			
			MemberService ms = new MemberService();
			String isfindId = ms.findId(memberFindIdInfo);//수정된 memberFindIdInfo는 String값을 반환함

			if(isfindId == null) {
				// 아이디 찾기가 성공적으로 됬다면
				// 아이디를 보여준다
				// 여기에 우리가 찾은 회원의 아이디를 안드로이드 스튜디오에 보낼 메서드를 찾아야함
				response.setStatus(201);
			} else {
				// 아이디 찾기가 되지 않았다면(이메일을 잘못 침)
				response.setStatus(400);
			}
		} catch(EmptyMemberInfoException | OverflowMemberInfoException e) {
			response.setStatus(400);
		} catch(SQLException e) {
			// 서버 문제
			response.setStatus(500);
		}catch(NotFoundMemberInfoException  e){
			response.setStatus(404);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
