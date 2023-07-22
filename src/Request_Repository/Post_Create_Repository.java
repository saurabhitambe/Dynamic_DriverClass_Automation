package Request_Repository;

public class Post_Create_Repository {

	public static String BaseURI() {
		String baseURI = "https://reqres.in/";
		return baseURI;
	}

	public static String Post_Resource() {
		String Post_Resource = "api/users";
		return Post_Resource;
	}

	public static String Post_TC2() {
		String RequestBody = "{\r\n" + "\"name\": \"Saurabhi\",\r\n"
				+ "\"job\": \"Client Service Engineer\"\r\n" + "}";
		return RequestBody;
	}

}