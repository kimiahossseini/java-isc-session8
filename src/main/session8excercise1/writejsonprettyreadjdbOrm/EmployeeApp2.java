package writejsonprettyreadjdbOrm;

import java.util.List;
import writejsonprettyreadjdbOrm.Employeee;

public class EmployeeApp2 {
	
	public static void main(String[] args) throws Exception {
	//read specific employees from table EMPLOYEE  in h2 and map them to java object
	List<Employeee> employeess=readjdbcORM.readjdbc();
	System.err.println("list specific employees");
	employeess.forEach(t -> System.out.println(t) );
	
	//write list employees pretty in json file 
	 writejsonpretty.writejson(employeess);
	 System.out.println("see created file  "+ "data-employees-pretty.json"+ "in your project");
	 
	
	
	
	

	}

}
