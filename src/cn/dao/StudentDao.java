package cn.dao;

import java.util.ArrayList;
import java.util.List;

import cn.model.Student;

public class StudentDao {
	
	private static List<Student> list = new ArrayList<Student>();

	/**
	 * 程序运行时添加21个学生
	 */
	static {
		for (int i = 1; i <= 21; i++)
			list.add(new Student(2005000 + i, "name" + i, "男", "计算机科学与技术", new String[] { "编程", "运动" }, "hello" + i,
					"2005005.jpg"));
	}

	/**
	 * 获取动态数组大小，即学生总数
	 * @return
	 */
	public int getSize() {
		return list.size();
	}

	/**
	 * 获取分页数据
	 * @param startIndex 起始页
	 * @param pageSize 每一页数据条数
	 * @param size	学生总数
	 * @return
	 */
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

	/**
	 * 添加一个学生
	 * @param student
	 */
	public void save(Student student) {
		list.add(student);
	}

	/**
	 * 更新某个学生，因为是动态数组，所以是先删除原有的，再添加新的
	 * @param student
	 */
	public void update(Student student) {
		for (Student stu : list) {
			if (stu.getId().equals(student.getId())) {
				list.remove(stu);
				list.add(student);
				break;
			}
		}
	}

	/**
	 * 删除对应id的学生
	 * @param id
	 */
	public void delete(Integer id) {
		for (Student stu : list) {
			if (stu.getId().equals(id)) {
				list.remove(stu);
				break;
			}
		}
	}

	/**
	 * 根据id获取对应学生
	 * @param id
	 * @return
	 */
	public Student getById(Integer id) {
		for (Student stu : list) {
			if (stu.getId().equals(id))
				return stu;
		}
		return null;
	}

	/**
	 * 获取全部学生
	 * @return
	 */
	public List<Student> getAll() {
		return list;
	}

}
