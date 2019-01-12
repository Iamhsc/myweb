package cn.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import cn.dao.StudentDao;
import cn.model.Student;
import util.C3P0Util;

public class StudentDaoImpl implements StudentDao {

	QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

	//插入数据
	@Override
	public int save(Student student) throws SQLException {
		String sql = "insert into s_user(stu_id,name,sex,professional,hobby,self,photo) values(?,?,?,?,?,?,?)";
		int result = 0;
		try {
			result = qr.update(sql, student.getStu_id(), student.getName(), student.getSex(), student.getProfessional(),
					Arrays.toString(student.getHobby()), student.getSelf(), student.getPhoto());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//更新数据
	@Override
	public int update(Student student) throws SQLException {
		String sql = "update s_user set name=?,sex=?,professional=?,hobby=?,self=?,photo=? where id=?";
		int result = 0;
		try {
			result = qr.update(sql, student.getName(), student.getSex(), student.getProfessional(),
					Arrays.toString(student.getHobby()), student.getSelf(), student.getPhoto(), student.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//根据id删除数据
	@Override
	public int delete(int id) throws SQLException {
		int result = 0;
		String sql = "delete from s_user where id=?";
		try {
			result = qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//根据id获取数据
	@Override
	public Student getById(int id) throws SQLException {
		String sql = "select id,stu_id,name,sex,professional,hobby as hobbys,self,photo from s_user where id=?";
		Student student = null;
		try {
			student = qr.query(sql, new BeanHandler<>(Student.class), id);
			String hobbys=student.getHobbys();
			hobbys=hobbys.substring(1,hobbys.length()-1);
			student.setHobby(hobbys.split(","));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	//获取全部数据
	@Override
	public List<Student> getAll() throws SQLException {
		List<Student> lists = new ArrayList<Student>();
		try {
			String sql = "select id,stu_id,name,sex,professional,hobby as hobbys,self,photo from s_user";
			 lists = qr.query(sql, new BeanListHandler<Student>(Student.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	//获取数据总条数
	@Override
	public Long getSize() throws SQLException {
		Long count = null;
		try {
			String sql = "select count(*) from s_user";
			count = qr.query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	//分页查询
	@Override
	public List<Student> getByPage(int startIndex, int pageSize) throws SQLException {
		List<Student> lists = new ArrayList<Student>();
		try {
			String sql = "select id,stu_id,name,sex,professional,hobby as hobbys,self,photo from s_user limit ?,?";
			lists = qr.query(sql, new BeanListHandler<Student>(Student.class),startIndex,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
}
