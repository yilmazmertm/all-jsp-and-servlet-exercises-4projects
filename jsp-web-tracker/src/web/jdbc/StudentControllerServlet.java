package web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource (name= "jdbc/web_student_tracker")
	private DataSource dataSource;
	
	private StudentDbUtil studentDbUtil;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// list the students
		try {
			// read the "command"
			String theCommand = request.getParameter("command");
			
			if(theCommand == null) {
				theCommand = "LIST";
			}
			// route to the method
			switch (theCommand) {
			
			case "LIST":
				//list students
				listStudents(request, response);
				break;
			case "ADD":
				addStudent(request, response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request, response);
			default: 
				listStudents(request, response);
			}
		}
		catch (Exception exc){
			throw new ServletException(exc);
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// read student id
		String theStudentId = request.getParameter("studentId");
		
		// delete the student
		studentDbUtil.deleteStudent(theStudentId);
		
		// send back to listing page
		listStudents(request, response);

		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// read the form data 
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		
		// create new student based on the form 
		Student theStudent = new Student(id, firstName, lastName, email);
		
		// perform update on the database
		studentDbUtil.updateStudent(theStudent);
		
		// send them back to the "list students page"
		listStudents(request, response);	
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student id from form data
		String theStudentId = request.getParameter("studentId");
		
		// get student from database
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		// place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		// send to jsp 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request,response);
		
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read the info from form
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create new student
		Student theStudent = new Student(firstName, lastName, email);
		
		// add the student to database
		studentDbUtil.addStudent(theStudent);
		// send back to main
		listStudents(request, response);
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get students from db
		List<Student> students = studentDbUtil.getStudents();
		// add students to the request
		request.setAttribute("STUDENT_LIST", students);
	
		// send to JSP
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

}
