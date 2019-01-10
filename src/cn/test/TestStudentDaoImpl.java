package cn.test;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import cn.dao.IStudentDao;
import cn.dao.StudentDaoImpl;
import cn.db.ConnCreate;
import cn.model.Student;

public class TestStudentDaoImpl {

	private static IStudentDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Connection conn=ConnCreate.getConnection("jdbc:mysql://47.112.31.40:3306/student", "user1","user1");
		dao=new StudentDaoImpl(conn);
	}

	@Test
	public void testSave() {
		Student student=new Student(null,2005010,"linan",(byte) 1,"计算机科学与技术",new String[]{"编程","运动"},"hello","2005001.jpg");
		int res=dao.save(student);
		System.out.println("信息插入"+(res>0?"成功!":"失败!"));
	}

	@Test
	public void testUpdate() {
		Student student=new Student(14,2005010,"张三丰",(byte) 1,"计算机科学与技术",new String[]{"编程","运动"},"你好","2005001.jpg");
		int result=dao.update(student);
		System.out.println("信息更新"+(result>0?"成功!":"失败!"));
	}

	@Test
	public void testDelete() {
		int result=dao.delete(14);
		System.out.println("信息删除"+(result>0?"成功!":"失败!"));
	}

	@Test
	public void testGetById() {
		Student student=dao.getById(2005001);
		System.out.println(student);
	}

	@Test
	public void testGetAll() {
		List<Student> lists=dao.getAll();
		System.out.println(lists);
	}

	@Test
	public void testGetSize() {
		int count=dao.getSize();
		System.out.println(count);
	}

	@Test
	public void testGetByPage() {
		List<Student> lists=dao.getByPage(5, 3);
		for(Student stu:lists)
			System.out.print(stu.getId()+" , ");
	}

}
