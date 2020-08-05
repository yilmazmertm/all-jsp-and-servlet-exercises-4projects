package web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// getting a connection
			myConn = dataSource.getConnection();
			
			// create some sql
			String sql = "select * from student order by last_name";
			
			myStmt = myConn.createStatement();
			// exec the query
			myRs = myStmt.executeQuery(sql);
			
			// process the result
			while(myRs.next()) {
				// retrieve data from one row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				// create new student obj 
				Student tempStudent = new Student(id , firstName, lastName, email);
				
				// add to the list of students
				students.add(tempStudent);
			}
			return students;
			
		}
		finally {
			// close JDBC object
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}

	public void addStudent(Student theStudent) {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db conn
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into student" + "(first_name, last_name, email)" + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values 
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());

			// execute sql insert
			myStmt.execute();
			
		}
		catch (Exception exc){
			// clean up JDBC object
			close(myConn, myStmt, null);
		}
	}

	public Student getStudent(String theStudentId) throws Exception{
		
		Student theStudent = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to select
			String sql = "select * from student where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, studentId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				//use the studentId
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find the student id: " + studentId);
			}
			return theStudent;
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}

	public void updateStudent(Student theStudent) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update command
			String sql = "update student " + "set first_name=?, last_name=?, email=? " + "where id=?"; 
			// prep the statement 
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			// execute sql
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteStudent(String theStudentId) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete
			String sql = "delete from student where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean the JDBC
			close(myConn, myStmt, null);
		}
	}
	
}
