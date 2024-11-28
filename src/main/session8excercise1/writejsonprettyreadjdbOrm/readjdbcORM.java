package writejsonprettyreadjdbOrm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class readjdbcORM {
	
	public static List<Employeee> readjdbc() throws Exception{
		 String salary="15000";
			String sql="""
					SELECT 
	    			e.EMPLOYEE_ID,e.FIRST_NAME,e.LAST_NAME,e.EMAIL,e.SALARY,d.DEPARTMENT_NAME
	    		FROM 
	    			EMPLOYEES e
	    		JOIN 
	                DEPARTMENTS d
	    		ON 
	    			e.DEPARTMENT_ID = d.DEPARTMENT_ID
	    		WHERE 
	    		e.SALARY > ?
					""";
			Connection connection=ConnectionManager.getConnection();
			PreparedStatement preparedstatement=connection.prepareStatement(sql);
			System.out.println("Connected to the H2 database successfully! ");
			preparedstatement.setString(1, salary);
			
			List<Employeee> listemployeee=new ArrayList<>();
			
			ResultSet resultset=preparedstatement.executeQuery();
			  
			while (resultset.next()) {
				Employeee employeee=new Employeee();
				employeee.setId(resultset.getNString("EMPLOYEE_ID"));
				employeee.setFirstName(resultset.getNString("FIRST_NAME"));;
				employeee.setLastName(resultset.getNString("LAST_NAME"));
				employeee.setEmail(resultset.getNString("EMAIL"));
				employeee.setSalary(resultset.getNString("SALARY"));
				
				Departmentt department=new Departmentt();
				department.setName(resultset.getNString("DEPARTMENT_NAME"));
				employeee.setDepartmentt(department);
				listemployeee.add(employeee);
				
			}
			return listemployeee;
		
	}

}
