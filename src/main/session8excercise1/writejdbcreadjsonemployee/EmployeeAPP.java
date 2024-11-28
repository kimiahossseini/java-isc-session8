package writejdbcreadjsonemployee;

import java.util.List;

import model.Employee;
import serviceandexception.EmployeeService;
import serviceandexception.EmployeeServiceException;

public class EmployeeAPP {
    //when number of constant element in your java is many you should constant them------
	private static final String DATA_EMPLOYEES_JSON = "data-employees.json";

	public static void main(String[] args) throws EmployeeServiceException {
		//------------read json file contains list employee and fill object Employee--------------------------------
		List<Employee> employees1=EmployeeService.readjson(DATA_EMPLOYEES_JSON);
		employees1.forEach(t -> System.out.println(t));
		//----------------process list employee-----------------------------------------------------
		List<Employee> employees2=EmployeeService.process(employees1);
		System.err.println("********new version list employees*********");
		employees2.forEach(t -> System.out.println(t));
		//-----------------------save list employee in database-----------------------------------
		int numberrows=EmployeeService.saveemployee(employees2);
		System.out.println("generated rows number is "+ numberrows);
	}

}
