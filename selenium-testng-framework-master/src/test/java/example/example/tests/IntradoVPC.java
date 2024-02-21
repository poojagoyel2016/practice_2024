package example.example.tests;

import example.example.context.Constants;
import example.example.factory.PageinstancesFactory;
import example.example.pages.IntradoVPCPage;
import example.example.util.ReportUtil;
import example.example.util.TestProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Test(testName = "ValidateTN_IntradoVPC", description = "Validating for TN in IntradoVPC Application")
    public class IntradoVPC extends BaseTest {

    static int count = 0;
	@Test
	public void ValidateTN_IntradoVPC() {

	    driver.manage().window().maximize();
	    IntradoVPCPage intradoVPCPage = PageinstancesFactory.getInstance(IntradoVPCPage.class);

	    List<String> missingTNList = new ArrayList<>();


	    try {
		// Reading Manually Provided TN's
		//List<String> TNList = Arrays.asList("6173093204" , "6173093205"," 6173093206", "6173093207", "6173093208"," 6173093209"," 6173093210","6173093215");

		// Reading TN's from Excel
		List<String> TNList = intradoVPCPage.readExcelColumnsandAddingittoList("C:\\Users\\pgoyel401\\Downloads\\tn-config-export-BVE4200536283-01252024100744.xlsx");
		String zipCode = "60062";
		String cityAbr = "NORTHBRK";  // city name on summary page
		String cityFullName = "NORTHBROOK";  // city name on details page
		String streetAddress = "0000003400"; // street address on summary page
		String streetNumber = "3400";	// street number on details page
		String streetName = "DUNDEE RD";	// street name on details page
		String street="DUNDEE";		// street name on details page
		String state = "IL";
		String custName= "";


		// Reading TN's from Text File
		/*File file = new File("C:\\temp\\TN Validation.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st= FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		List<String> TNList = Arrays.asList(st.split(","));*/


		driver.get(Constants.IntradoVPCUrl);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
	/*	intradoVPCPage.getTxtUserName().sendKeys(TestProperties.getProperty("DCP.username"));  // Provide the username in Test.Properties file
		intradoVPCPage.getBtnNext().click();
		intradoVPCPage.getBtnLoginId().sendKeys(TestProperties.getProperty("DCP.password"));  // Provide the password in Test.Properties file
		wait.until(ExpectedConditions.elementToBeClickable(intradoVPCPage.getBtnLoginSubmit()));
		JavascriptExecutor js = (JavascriptExecutor)driver ;
		js.executeScript("arguments[0].click();", intradoVPCPage.getBtnLoginSubmit());*/

		wait.until(ExpectedConditions.visibilityOf(intradoVPCPage.getHeaderV911DCPComPS()));
		intradoVPCPage.getBtnFindByPhoneNumber().click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(intradoVPCPage.getTxtPhoneNumberTN()));

		    for (String TN : TNList) {
			try {
			    TN = TN.replaceAll("[^a-zA-Z0-9]", "");
			    intradoVPCPage.getTxtPhoneNumberTN().clear();
			    intradoVPCPage.getTxtPhoneNumberTN().sendKeys(TN);
			    intradoVPCPage.getBtnSubmit().click();
			    wait.until(ExpectedConditions.visibilityOf(intradoVPCPage.getTxtRecordsFound()));
			    if (intradoVPCPage.getTxtRecordsFound().isDisplayed()) {
				String tnFound = intradoVPCPage.getTxtRecordsFound().getText();
				if(tnFound.replaceAll("[^a-zA-Z0-9]", "").equals(TN)){
				    System.out.println("TN " + TN + " found in Intrado VPC Application");
				    ReportUtil.addScreenShot(TN+ " found in Intrado VPC Application");
				    //Verifying address details
				         custName = intradoVPCPage.getCustomerNamelnk().getText();
					if(!intradoVPCPage.getTxtStreetAddress().getText().equals(streetAddress)){ReportUtil.logMessage("Street Address details are not matching for TN: ",TN);}
					if(!intradoVPCPage.getTxtStreetName().getText().equals(streetName)){ReportUtil.logMessage("Street Name details are not matching for TN: ",TN);}
					if(!intradoVPCPage.getTxtCity().getText().equals(cityAbr)){ReportUtil.logMessage("City details are not matching for TN: ",TN);}
					if(!intradoVPCPage.getTxtState().getText().equals(state)){ReportUtil.logMessage("State details are not matching for TN: ",TN);}
					if(!intradoVPCPage.getTxtZipCode().getText().equals(zipCode)){ReportUtil.logMessage("Zip Code details are not matching for TN: ",TN);}
					intradoVPCPage.getCustomerNamelnk().click();
					wait.until(ExpectedConditions.textToBePresentInElementValue(intradoVPCPage.getcityFullName(), cityFullName));
					ReportUtil.addScreenShot(TN+ " Detailed address");
					if(!intradoVPCPage.getcityFullName().getAttribute("value").equals(cityFullName)){ReportUtil.logMessage("City full name details are not matching for TN: ",TN);}
				        if(!intradoVPCPage.getStreetNumber().getAttribute("value").equals(streetNumber)){ReportUtil.logMessage("Street number is not matching in details section for TN: ",TN);}
				        if(!intradoVPCPage.getTxtStreetName1().getAttribute("value").equals(street)){ReportUtil.logMessage("Street name is not matching in details section for TN: ",TN);}
					if(!intradoVPCPage.getTxtZipCode1().getAttribute("value").equals(zipCode)){ReportUtil.logMessage("Zip Code is not matching in details section for TN: ",TN);}
					if(!intradoVPCPage.getTxtCustomerName().getAttribute("value").equals(custName)){ReportUtil.logMessage("Customer Name is not matching in details section for TN: ",TN);}
					intradoVPCPage.getBtnFindByPhoneNumber1().click();
				    count++;
				}
			    }else{
				System.out.println("Record is displayed. But TN is not matching" + TN);
			    }


			} catch (TimeoutException timeoutExceptionout) {
			    if (intradoVPCPage.getTxtNoRecordsFound().isDisplayed()) {
				ReportUtil.addScreenShot(TN+ " not found in Intrado VPC Application");
				missingTNList.add(TN);
			    }
			    else {
				ReportUtil.logMessage("Expected city name is not displayed for TN: ",TN);
			    }

			}
		    }
	     }

	    catch (Exception e) {
		System.out.println("Exception Occured" + e);
	    }
	    finally{
		System.out.println("Total TNs found in Intrado VPC Application: "+count);
		if(missingTNList.size()>0){
		    System.out.println("TNs not found in Intrado VPC Application: "+missingTNList);
		}
	    }

	}


}
