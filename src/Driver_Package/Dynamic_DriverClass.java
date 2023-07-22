package Driver_Package;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import Common_API_Methods.Common_Utility_Method;

public class Dynamic_DriverClass {

		public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			ArrayList<String> TestCaseToRun = Common_Utility_Method.ReadDataExcel("TestCase", "TestCaseToExecute");
			System.out.println(TestCaseToRun);
			
			int Count=TestCaseToRun.size();
			for (int i=1; i<Count;i++)
			{
			String TestCaseName = TestCaseToRun.get(i);
			System.out.println(TestCaseName);
			
			// call the TestCaseClass on runtime by using java.lang.reflect package
			Class<?> testclassname=Class.forName("Test_Classes."+TestCaseName);

			// call the execute method belonging to test class captured in variable TestClassName by using java.lang.reflect.method class
			Method executemethod=testclassname.getDeclaredMethod("extractor");
			
			//set the accessibility of method true 
			executemethod.setAccessible(true);

			//create the instance of TestClass captured in variable name TestClassName
			Object instanceoftestclass=testclassname.getDeclaredConstructor().newInstance();

			// execute the TestClass captured in variable name TestClass name
			executemethod.invoke(instanceoftestclass);
	}
}
}