package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_API_Methods.Common_Utility_Method;
import Request_Repository.DataDriven_RequestRepository;
import io.restassured.path.json.JsonPath;

public class DataDriven_TC1 {

	public static void extractor() throws IOException {

		int statusCode = Common_Utility_Method.ResponseStatusCode(DataDriven_RequestRepository.BaseURI(),
				DataDriven_RequestRepository.Post_Resource(), DataDriven_RequestRepository.Post_TC2());
		System.out.println(statusCode);

		String ResponseBody = Common_Utility_Method.ResponseBody(DataDriven_RequestRepository.BaseURI(),
				DataDriven_RequestRepository.Post_Resource(), DataDriven_RequestRepository.Post_TC2());
		System.out.println(ResponseBody);
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
