package cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cn.db.ConnCreate;
import cn.model.Student;

public class StudentDaoImpl implements IStudentDao{

private Connection conn=null;
	
	

	public StudentDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public int save(Student student) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "insert into s_user(stu_id,name,sex,professional,hobby,self,photo) values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student.getStu_id());
			stmt.setString(2, student.getName());
			stmt.setByte(3, student.getSex());
			stmt.setString(4, student.getProfessional());
			stmt.setString(5, Arrays.toString(student.getHobby()));
			stmt.setString(6, student.getSelf());
			stmt.setString(7, student.getPhoto());
			result=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int update(Student student) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "update s_user set name=?,sex=?,professional=?,hobby=?,self=?,photo=? where id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, student.getName());
			stmt.setByte(2, student.getSex());
			stmt.setString(3, student.getProfessional());
			stmt.setString(4, Arrays.toString(student.getHobby()));
			stmt.setString(5, student.getSelf());
			stmt.setString(6, student.getPhoto());
			stmt.setInt(7, student.getId());
			
			result=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int delete(int id) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			String sql = "delete from s_user where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			result=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Student getById(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Student	student =null;
		try {
			String sql = "select * from s_user where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				student=new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getByte("sex"));
				student.setProfessional(rs.getString("professional"));
				student.setHobbys(rs.getString("hobby"));
				student.setSelf(rs.getString("self"));
				student.setPhoto(rs.getString("photo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return student;

	}

	@Override
	public List<Student> getAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Student	student =null;
		List<Student> lists=new ArrayList<Student>();
		try {
			String sql = "select * from s_user";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				student=new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getByte("sex"));
				student.setProfessional(rs.getString("professional"));
				student.setHobbys(rs.getString("hobby"));
				student.setSelf(rs.getString("self"));
				student.setPhoto(rs.getString("photo"));
				lists.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	@Override
	public int getSize() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			String sql = "select count(*) from s_user";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;

	}

	@Override
	public List<Student> getByPage(int startIndex, int pageSize) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Student	student =null;
		List<Student> lists=new ArrayList<Student>();
		try {
			String sql = "select * from s_user limit ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startIndex);
			stmt.setInt(2, pageSize);
			rs = stmt.executeQuery();
			while(rs.next()){
				student=new Student();
				student.setId(rs.getInt("id"));
				student.setStu_id(rs.getInt("stu_id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getByte("sex"));
				student.setProfessional(rs.getString("professional"));
				student.setHobbys(rs.getString("hobby"));
				student.setSelf(rs.getString("self"));
				student.setPhoto(rs.getString("photo"));
				lists.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ConnCreate.close(null, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}
}
