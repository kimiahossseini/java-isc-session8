package writejsonprettyreadjdbOrm;

import java.io.File;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class writejsonpretty {
	
	public static File writejson(List<Employeee> employees) throws Exception {
		File jsonpretty= new File("data-employees-pretty.json");
		ObjectMapper objectmapper=new ObjectMapper();
		objectmapper.writerWithDefaultPrettyPrinter()
			.writeValue(jsonpretty, employees);

		return jsonpretty  ;
		
	}


}
