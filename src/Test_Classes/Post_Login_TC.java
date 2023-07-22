package Test_Classes;

import org.testng.Assert;
import Common_API_Methods.Post_Login_Method;
import Request_Repository.Post_Login_Repository;
import io.restassured.path.json.JsonPath;

public class Post_Login_TC {

	public static void extractor() {

		int statusCode = Post_Login_Method.ResponseStatusCode(Post_Login_Repository.BaseURI(), Post_Login_Repository.Post_Resource(),
				Post_Login_Repository.Post_Req_TC1());
		System.out.println(statusCode);

		String ResponseBody = Post_Login_Method.ResponseBody(Post_Login_Repository.BaseURI(), Post_Login_Repository.Post_Resource(),
				Post_Login_Repository.Post_Req_TC1());
		System.out.println(ResponseBody);

		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_token = JspResponse.getString("token");
		Assert.assertEquals(Res_token, "QpwL5tke4Pnpja7X4");
	}

}
