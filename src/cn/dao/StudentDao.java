package cn.dao;

import java.sql.SQLException;
import java.util.List;
import cn.model.Student;

public interface StudentDao {
	public int save(Student student) throws SQLException;
	public int update(Student student) throws SQLException;
	public int delete(int id) throws SQLException;
	public Student getById(int id) throws SQLException;
	public List<Student> getAll() throws SQLException;
	public Long getSize() throws SQLException;
	public List<Student> getByPage(int startIndex,int pageSize) throws SQLException;
}
