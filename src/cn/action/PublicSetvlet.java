package cn.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dao.StudentDao;
import cn.dao.impl.StudentDaoImpl;
import cn.model.Student;

/**
 * Servlet implementation class PublicSetvlet
 */
@WebServlet("/public")
public class PublicSetvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDao dao = new StudentDaoImpl();
	Student stu = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicSetvlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.addHeader("Content-Type","application/json");
		String action = request.getParameter("action");
		if (action.equals("register")) {
		}
		if (action.equals("login")) {
			dao = new StudentDaoImpl();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				stu = dao.login(username);
				if (stu == null) {
					System.out.println("用户不存在");
					request.setAttribute("loginerr", "用户不存在！");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					if (password.equals(stu.getPassword())) {
						System.out.println("登陆成功密码是"+stu.getPassword());
						HttpSession session = request.getSession();
						session.setAttribute("s_stuID", username);
						session.setAttribute("s_id", stu.getId());
						session.setAttribute("s_name", stu.getName());
						response.sendRedirect("student");
					} else {
						System.out.println("户名或密码不正确");
						request.setAttribute("loginerr", "用户名或密码不正确！");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				}
				System.out.println(stu);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
