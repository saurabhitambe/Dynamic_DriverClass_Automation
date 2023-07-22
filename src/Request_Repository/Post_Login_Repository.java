package Request_Repository;

public class Post_Login_Repository {

	public static String BaseURI() {
		String baseURI = "https://reqres.in/";
		return baseURI;
	}

	public static String Post_Resource() {
		String Post_Resource = "api/login";
		return Post_Resource;
	}

	public static String Post_Req_TC1() {
		String RequestBody = "{\r\n" + "\"email\": \"eve.holt@reqres.in\",\r\n"
				+ "    \"password\": \"cityslicka\"\r\n" + "}";
		return RequestBody;
	}

}
