package cn.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.dao.StudentDao;
import cn.dao.impl.StudentDaoImpl;
import cn.model.Student;

public class StudentDaoImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	StudentDao dao = new StudentDaoImpl();

	@Test
	public void testSave() {
		int res;
		try {
		for(int i=0;i<=100000;i++) {
			res = dao.save(new Student(null, 2005010+1, "linan"+i, (byte) 1, "计算机科学与技术"+i, new String[] { "编程", "运动" },
					"hello world"+i, "2005001.jpg+1"));
		}
//			System.out.println("信息插入" + (res > 0 ? "成功!" : "失败!"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		Student student = new Student(19, 2005010, "张三丰", (byte) 1, "计算机科学与技术", new String[] { "编程", "运动" }, "你好",
				"2005001.jpg");
		int result;
		try {
			result = dao.update(student);
			System.out.println("信息更新" + (result > 0 ? "成功!" : "失败!"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		int result;
		try {
			result = dao.delete(19);
			System.out.println("信息删除" + (result > 0 ? "成功!" : "失败!"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetById() {
		Student student;
		try {
			student = dao.getById(2);
			System.out.println(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAll() {
		List<Student> lists;
		try {
			lists = dao.getAll();
			System.out.println(lists);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSize() {
		Long count;
		try {
			count = dao.getSize();
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetByPage() {
		List<Student> lists;
		try {
			lists = dao.getByPage(5, 3);
			for (Student stu : lists)
				System.out.print(stu.getId() + " , ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
