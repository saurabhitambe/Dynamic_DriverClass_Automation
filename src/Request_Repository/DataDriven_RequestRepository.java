package Request_Repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_API_Methods.Common_Utility_Method;

public class DataDriven_RequestRepository {
public static String BaseURI() {
		
        String baseURI = "https://reqres.in/";
        return baseURI; 
	}
	
	public static String Post_Resource() {
		String Post_Resource = "api/users";
		return Post_Resource;
	}
	
	public static String Post_TC2() throws IOException {
		ArrayList<String> Req_Data=Common_Utility_Method.ReadDataExcel("PostAPI","TC3");
		System.out.println(Req_Data); 
		String Req_Name=Req_Data.get(1);
		String Req_Job=Req_Data.get(2);
		
		String RequestBody = "{\r\n"
				+ "    \"name\": \""+Req_Name+"\",\r\n"
				+ "    \"job\": \""+Req_Job+"\"\r\n"
				+ "}";
		return RequestBody;
	
 }

}
