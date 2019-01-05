package cn.dao;

import java.util.ArrayList;
import java.util.List;

import cn.model.Student;

public class StudentDao {
	private static List<Student> list = new ArrayList<Student>();

	static {
		for (int i = 1; i <= 21; i++)
			list.add(new Student(2005000 + i, "name" + i, "男", "计算机科学与技术", new String[] { "编程", "运动" }, "hello" + i,
					"2005005.jpg"));
	}

	public int getSize() {
		return list.size();
	}

	public List<Student> getByPage(int startIndex, int pageSize, int size) {
		System.out.println("start:" + startIndex + "size:" + pageSize);
		if (startIndex != 0) {
			if (size % pageSize != 0 && size / startIndex == 1)
				pageSize = size % pageSize;
		}
		List<Student> ls = new ArrayList<Student>();
		for (int i = 0; i < pageSize; i++)
			ls.add(list.get(startIndex + i));
		return ls;
	}

	public void save(Student student) {
		list.add(student);
	}

	public void update(Student student) {
		for (Student stu : list) {
			if (stu.getId().equals(student.getId())) {
				list.remove(stu);
				list.add(student);
				break;
			}
		}
	}

	public void delete(Integer id) {
		for (Student stu : list) {
			if (stu.getId().equals(id)) {
				list.remove(stu);
				break;
			}
		}
	}

	public Student getById(Integer id) {
		for (Student stu : list) {
			if (stu.getId().equals(id))
				return stu;
		}
		return null;
	}

	public List<Student> getAll() {
		return list;
	}

}
