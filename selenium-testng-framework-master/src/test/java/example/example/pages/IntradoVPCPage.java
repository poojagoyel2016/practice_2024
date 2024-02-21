package example.example.pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IntradoVPCPage extends BasePage {

	/**
	 * Instantiates a new Intrado VPC page.
	 *
	 * @param driver the driver
	 */
	public IntradoVPCPage(WebDriver driver) {
	    super(driver);
	}

	@FindBy(xpath = "//h1[text()='V911 - DCP ComPS']")
	private WebElement headerV911DCPComPS;

	@FindBy(xpath = "//button[text()='Find by Phone Number']")
	private WebElement btnFindByPhoneNumber;

	@FindBy(id = "phoneNumberTN")
	private WebElement txtPhoneNumberTN;

	public WebElement getBtnSubmit() {
	    return btnSubmit;
	}

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement btnSubmit;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[text()='No records found']")
	private WebElement txtNoRecordsFound;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[2]")
	private WebElement txtRecordsFound;

	@FindBy(id="i0116")
	private WebElement txtUserName;

	@FindBy(id="i0118")
	private WebElement btnLoginId;

	@FindBy(id="idSIButton9")
	private WebElement btnNext;


	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnLoginSubmit;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[3]")
	private WebElement txtStreetAddress;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[4]")
	private WebElement txtStreetName;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[5]")
	private WebElement txtCity;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[6]")
	private WebElement txtState;

	@FindBy(xpath = "//p-table[@id='TNTable']//td[7]")
	private WebElement txtZipCode;
	@FindBy(xpath = "//a[@class='ng-star-inserted']")
	private WebElement customerNamelnk;
	@FindBy(xpath = "//input[@name='city']")
	private WebElement cityFullName;
	@FindBy(xpath = "//button[contains(text(),'Find By Phone Number')]")
	private WebElement btnFindByPhoneNumber1;
	@FindBy (xpath = "//input[@name='streetNumber']")
		private WebElement streetNumber;
	@FindBy (xpath = "//input[@name='street']")
	private WebElement txtStreetName1;
	@FindBy (xpath = "//input[@name='zip']")
	private WebElement txtZipCode1;
	@FindBy (xpath = "//input[@name='customerName']")
	private WebElement txtCustomerName;
	public WebElement getTxtStreetName1() {
		return txtStreetName1;
	}
	public WebElement getTxtCustomerName() {
		return txtCustomerName;
	}
	public WebElement getTxtZipCode1() {
		return txtZipCode1;
	}
	public WebElement getTxtStreetAddress() {
		return txtStreetAddress;
	    }
	public WebElement getStreetNumber() {
		return streetNumber;
	    }
	public WebElement getTxtStreetName() {
	    return txtStreetName;
	}

	public WebElement getTxtCity() {
	    return txtCity;
	}

	public WebElement getTxtState() {
	    return txtState;
	}

	public WebElement getTxtZipCode() {
	    return txtZipCode;
	}
	public WebElement getCustomerNamelnk(){
	    return customerNamelnk;
	}
	public WebElement getcityFullName(){
	    return cityFullName;
	}


	public WebElement getBtnLoginSubmit() {
		return btnLoginSubmit;
	    }

	public WebElement getTxtUserName() {
	    return txtUserName;
	}

	public void setTxtUserName(WebElement txtUserName) {
	    this.txtUserName = txtUserName;
	}

	public WebElement getBtnLoginId() {
	    return btnLoginId;
	}

	public WebElement getBtnNext() {
	    return btnNext;
	}

	public WebElement getTxtNoRecordsFound() {
	    return txtNoRecordsFound;
	}

	public WebElement getTxtRecordsFound() {
	    return txtRecordsFound;
	}

	public WebElement getHeaderV911DCPComPS() {
	    return headerV911DCPComPS;
	}

	public WebElement getBtnFindByPhoneNumber() {
	    return btnFindByPhoneNumber;
	}


	public WebElement getTxtPhoneNumberTN() {
	    return txtPhoneNumberTN;
	}

	public WebElement getBtnFindByPhoneNumber1() {
	    return btnFindByPhoneNumber1;
	}
	public List<String> readExcelColumnsandAddingittoList(String filePath) {
	    List<String> TNList = new ArrayList<>();
	    Map<String, Integer> requiredHeaders = new HashMap<>();
	    try {
		FileInputStream file = new FileInputStream(new File(filePath));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		DataFormatter formatter = new DataFormatter();
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (Cell cell : sheet.getRow(0)) {
		    requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
		}

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		    Row row = sheet.getRow(i);
		   TNList.add(formatter.formatCellValue(row.getCell(requiredHeaders.get("TELEPHONE_NUMBER"))));
		}
		workbook.close();
	    } catch (Exception e) {
		System.out.println("Exception Occured while reading excel file" + e);
	    }
	    return TNList;
	}
    }

