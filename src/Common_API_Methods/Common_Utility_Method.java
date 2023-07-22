package Common_API_Methods;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import io.restassured.RestAssured;

public class Common_Utility_Method {

	public static void EvidenceCreator(String Filename, String RequestBody, String ResponseBody, int StatusCode)
			throws IOException {

		File Textfile = new File("D:\\Saurabhi_Doc\\Auto\\RestAssured\\Evidence" + Filename + ".txt");
		System.out.println("New blank text file of name : " + Textfile.getName());

		FileWriter dataWrite = new FileWriter(Textfile);

		dataWrite.write("Request Body is : " + RequestBody + "\n\n");
		dataWrite.write("Status Code is : " + StatusCode + "\n\n");
		dataWrite.write("Response Body is : " + ResponseBody);
		dataWrite.close();
		System.out.println("Request body and response body written in textfile : " + Textfile.getName());
	}

	public static int ResponseStatusCode(String BaseURI, String Resource, String RequestBody) {

		RestAssured.baseURI = BaseURI;
		int statusCode = given().header("Content-Type", "application/json").body(RequestBody).when().post(Resource)
				.then().extract().statusCode();
		return statusCode;

	}

	public static String ResponseBody(String BaseURI, String Resource, String RequestBody) {

		RestAssured.baseURI = BaseURI;
		String ResponseBody = given().header("Content-Type", "application/json").body(RequestBody).when().post(Resource)
				.then().extract().response().asPrettyString();
		return ResponseBody;

	}

	public static ArrayList<String> ReadDataExcel(String sheetname, String Testcasename) throws IOException {
		ArrayList<String> ArrayData = new ArrayList<String>();
		// Step1 : Create the object of file input stream to locate the Excel file
		FileInputStream Fis = new FileInputStream("D:\\Saurabhi_Doc\\Auto\\RestAssured\\DataDriven.xlsx");
		// Step2 : Open the Excel File by creating the object XSSFWorkbook
		XSSFWorkbook WorkBook = new XSSFWorkbook(Fis);
		// Step3 : Open the Desired Sheet
		int countofsheet = WorkBook.getNumberOfSheets();
		for (int i = 0; i < countofsheet; i++) {
			String Sheetname = WorkBook.getSheetName(i);
			// Step4 : Access the desired sheet
			if (Sheetname.equalsIgnoreCase(sheetname))
			
			{
				// Use XSSFSheet to save the sheet into your variable
				XSSFSheet Sheet = WorkBook.getSheetAt(i);
				// Create Iterator to iterate through rows and find out in which column the test case names are found.
				Iterator<Row> Rows = Sheet.iterator();
				Row FirstRow = Rows.next();
				// Create the Iterator through cells of first row to find out which cell contains test case name.
				Iterator<Cell> CellsofFirstRow = FirstRow.cellIterator();
				int k = 0;
				int TC_Column = 0;
				while (CellsofFirstRow.hasNext()) {
					Cell CellValue = CellsofFirstRow.next();
					if (CellValue.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
						TC_Column = k;

						// System.out.println("Expected column for test case name:" +k);
						break;
					}
					k++;
				}
				// Verify The Row Where The Desired Test Case Is Found And Fetch The Entire Row
				while (Rows.hasNext()) {
					Row Datarow = Rows.next();
					String TCName = Datarow.getCell(TC_Column).getStringCellValue();
					if (TCName.equalsIgnoreCase(Testcasename)) {
						Iterator<Cell> CellValues = Datarow.cellIterator();
						while (CellValues.hasNext()) {
							String Data = "";
							Cell CurrentCell = CellValues.next();
							try {
								String StringData = CurrentCell.getStringCellValue();
								Data = StringData;
							} catch (IllegalStateException e) {
								double doubledata = CurrentCell.getNumericCellValue();
								Data = Double.toString(doubledata);
							}
							ArrayData.add(Data);
						}
						break;
					}

				}
			}
		}
		return ArrayData;
	}
}
