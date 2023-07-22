package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_API_Methods.Common_Utility_Method;
import Common_API_Methods.Retry_API_Method;
import Request_Repository.Post_Retry_Repository;
import io.restassured.path.json.JsonPath;

public class Retry_TC1 {
	
	public static void extractor() throws IOException {
		for (int i = 0; i < 5; i++) {
			int statusCode = Retry_API_Method.ResponseStatusCode(Post_Retry_Repository.BaseURI(),
					Post_Retry_Repository.Post_Resource(), Post_Retry_Repository.Post_Req_TC1());
			if (statusCode == 201) {
				System.out.println("StatusCode is Correct:" + statusCode);
				String ResponseBody = Retry_API_Method.ResponseBody(Post_Retry_Repository.BaseURI(),
						Post_Retry_Repository.Post_Resource(), Post_Retry_Repository.Post_Req_TC1());
				System.out.println(ResponseBody);
				String RequestBody = Post_Retry_Repository.Post_Req_TC1();
				Common_Utility_Method.EvidenceCreator("Retry_TC1", RequestBody, ResponseBody, statusCode);
				validator(ResponseBody, RequestBody);
				break;
			} else {
				System.out.println("StatusCode Is Incorrect");
			}
		}
	}

	public static void validator(String responseBody, String requestBody) {
		JsonPath jspRequest = new JsonPath(requestBody);
		String Req_name = jspRequest.getString("name");
		String Req_job = jspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);

		JsonPath jspResponse = new JsonPath(responseBody);
		String Res_name = jspResponse.getString("name");
		String Res_job = jspResponse.getString("job");
		String Res_id = jspResponse.getString("id");
		String Res_createdAt = jspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0, 11);

		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertNotNull(Res_id);
		Assert.assertNotEquals(Res_id, 0);
		Assert.assertEquals(Res_createdAt, expecteddate);

	}

}
