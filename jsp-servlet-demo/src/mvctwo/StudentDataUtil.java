package mvctwo;

import java.util.*;

public class StudentDataUtil {
	
	public static List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		
		students.add(new Student("Mary", "Perl", "mary@perl.com"));
		students.add(new Student("Mert", "Yilmaz", "mert@yilmaz.com"));
		students.add(new Student("Sample", "Person", "sample@person.com"));
		
		return students;
	}

}
