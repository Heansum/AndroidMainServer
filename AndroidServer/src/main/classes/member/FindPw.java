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
@WebServlet("/member/findpw")
public class FindPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MemberInfo memberFindPwInfo = new MemberInfo(request);
			
			MemberService ms = new MemberService();
			boolean isfindPw = ms.findPw(memberFindPwInfo);

			if(isfindPw) {
				// 비밀번호 찾기가 성공적으로 됬다면
				// 비밀번호를 보여준다
				response.setStatus(201);
			} else {
				// 비밀번호 찾기가 되지 않았다면(아이디나 이메일을 잘못입력한 것)
				response.setStatus(400);
			}
		} catch(EmptyMemberInfoException | OverflowMemberInfoException e) {
			response.setStatus(400);
		} catch(SQLException e) {
			// 서버 문제
			response.setStatus(500);
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
