package Test_Classes;

import java.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;
import Common_API_Methods.Post_Create_Method;
import Request_Repository.Post_Create_Repository;
import io.restassured.path.json.JsonPath;

public class Post_Create_TC {
	@Test
	public static void extractor() {

		int statusCode = Post_Create_Method.ResponseStatusCode(Post_Create_Repository.BaseURI(),
				Post_Create_Repository.Post_Resource(), Post_Create_Repository.Post_TC2());
		System.out.println(statusCode);

		String ResponseBody = Post_Create_Method.ResponseBody(Post_Create_Repository.BaseURI(),
				Post_Create_Repository.Post_Resource(), Post_Create_Repository.Post_TC2());
		System.out.println(ResponseBody);
		

		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate = currentdate.toString().substring(0, 11);
		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_createdAt = JspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0, 11);

        //Validate the ResponseBody parameters
		Assert.assertEquals(Res_name, "Saurabhi");
		Assert.assertEquals(Res_job, "Client Service Engineer");
		Assert.assertEquals(Res_createdAt, expecteddate);

	}
}
