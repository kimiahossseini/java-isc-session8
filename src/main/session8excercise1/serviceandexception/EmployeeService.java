package serviceandexception;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Employee;

public class EmployeeService {
	 private static final Logger log= Logger.getLogger(EmployeeService.class.getName());
	
	//-------------method readfile json-----------------------------------------
	/**
	 * 
	 * @param filename this is your json file contains list employee
	 * @return list employee
	 * @throws EmployeeServiceException
	 */
	 public static List<Employee> readjson(String filename) throws EmployeeServiceException{
		
		File myfile=new File(filename);	
		log.info("Reading file: " + filename);
		ObjectMapper objectmapper=new ObjectMapper();
			List<Employee> employeelist1;
			try {
				employeelist1 = objectmapper.readValue(myfile, new TypeReference<List<Employee>>(){});
				log.info("your json file is read" + myfile);
				return employeelist1;
			} catch (StreamReadException e) {
				log.warning("your json file has syntax error");
				throw new EmployeeServiceException("Error while reading the JSON stream: ", e);		
			} catch (DatabindException e) {
				log.warning("json elements couldn't be mapped to java object");
				throw new EmployeeServiceException("Error while mapping JSON to Java object ", e);
			} catch (IOException e) {
				log.warning("Could not read file: " + filename);
				throw new EmployeeServiceException("I/O error occurred:", e);
			}
			
	
	}
	/**
	 * process on list employee we want to create new list that employees' phonenumber doesn't start with + and
	 * there aren't - between numbers
	 * @param employeelist2
	 * @return
	 */
	//-----------------method----------------process------remove"+"and"-"------------
	public static List<Employee> process(List<Employee> employeelist2){
		List<Employee> employeelist3=employeelist2.stream()
        .peek(t -> t.setPhoneNumber(t.getPhoneNumber().replaceAll("[+\\-]", "")))
        	.collect(Collectors.toList());
		
		return employeelist3 ;	
	}
	//-----------------method save list employee in db---------------------------
	/**
	 * this method save list employees in table employees1 in data base h2
	 * @param employeelist3
	 * @return
	 * @throws EmployeeServiceException
	 */
	public static int saveemployee(List<Employee> employeelist3) throws EmployeeServiceException {
		String sql="""
				INSERT INTO EMPLOYEES1 (FIRST_NAME ,LAST_NAME ,EMAIL ,PHONE_NUMBER ,SALARY) 
				VALUES(?,?,?,?,?)
				""";
		try (Connection connect = ConnectionManager.getConnection();
				PreparedStatement preparedstatement = connect.prepareStatement(sql)) 
			
		{ 	log.info("connected to h2 for saving...");
			int rowaffect=0;
			for (Employee emplo : employeelist3) {
			   preparedstatement.setString(1, emplo.getFirstName());
			   preparedstatement.setString(2, emplo.getLastName());
			   preparedstatement.setString(3, emplo.getEmail());
			   preparedstatement.setString(4, emplo.getPhoneNumber());
			   preparedstatement.setString(5, emplo.getSalary());
			   preparedstatement.executeUpdate();
			   rowaffect++;
			   log.info("Employee inserted. Total added rows : " + rowaffect);
			}
			
			return rowaffect;
		} 
		catch (SQLException e) {
			log.warning("Error in database opreation");
			throw new EmployeeServiceException("we couldn't connect to h2", e);	
		}
		
		
		
	}
	

}
