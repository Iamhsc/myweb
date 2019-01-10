package cn.dao;

import java.util.List;
import cn.model.Student;

public interface IStudentDao {
	public int save(Student student);
	public int update(Student student);
	public int delete(int id);
	public Student getById(int id);
	public List<Student> getAll();
	public int getSize();
	public List<Student> getByPage(int startIndex,int pageSize);
}
