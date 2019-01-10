package cn.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.dao.IStudentDao;
import cn.dao.StudentDaoImpl;
import cn.db.ConnCreate;
import cn.model.PageBean;
import cn.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IStudentDao dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        Connection conn=ConnCreate.getConnection("jdbc:mysql://47.112.31.40:3306/student", "user1","user1");
		dao=new StudentDaoImpl(conn);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if(action!=null){
			if(action.equals("save")){
				Student stu=doForm(request);
				dao.save(stu);
				response.sendRedirect("student");
			}else if(action.equals("update")){
				Student stu=doForm(request);
				dao.update(stu);
				response.sendRedirect("student");
			}else if(action.equals("delete")){
				Integer id=Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				response.sendRedirect("student");
			}else if(action.equals("more")){
				Integer id=Integer.parseInt(request.getParameter("id"));
				Student stu=dao.getById(id);
				System.out.println(stu);
				request.setAttribute("stu", stu);
				request.getRequestDispatcher("more.jsp").forward(request, response);
			}else if(action.equals("edit")){
				Integer id=Integer.parseInt(request.getParameter("id"));
				Student stu=dao.getById(id);
				request.setAttribute("stu", stu);
				request.getRequestDispatcher("edit.jsp").forward(request, response);
			}
		}else{
			int num=1;
			String pageNum=request.getParameter("pageNum");
			if(pageNum!=null)
				num=Integer.parseInt(pageNum);
			int totalRecord=dao.getSize();
			int pageSize=5;
			PageBean<Student> pageBean=new PageBean<>(num, pageSize, totalRecord);
			System.out.println("start:"+pageBean.getStartIndex());
			List<Student> list=dao.getByPage(pageBean.getStartIndex(),pageSize);
			pageBean.setList(list);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("listJSTL.jsp").forward(request, response);
		}
	}
	
	private Student doForm(HttpServletRequest request){
		Integer id = 0;
		Integer stu_id=Integer.parseInt(request.getParameter("stu_id"));
		String name=request.getParameter("name");
		Byte sex=Byte.parseByte(request.getParameter("sex"));
		String professional=request.getParameter("professional");
		String[] hobby=request.getParameterValues("hobby");
		String self=request.getParameter("self");
		String photo=request.getParameter("photo");
		Student stu=new Student(id,stu_id, name, sex, professional, hobby, self, photo);
		return stu;
	}

}
