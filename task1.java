package task.Task;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.task.pages.pages;

public class task1 {
	//declear pbject
	Properties prop;
	//declear pbject from class pages
	pages page=new pages();
	public task1() throws IOException {
		//create obj from prop
		

	}
	String name1="suheal",famname="abboud",city="amman",city1="amman",number="0775794944",ab=name1+" "+famname,petname1="lucy",petdate="2000-09-09",animal="bird";
	WebDriver driver;
	@BeforeMethod
	public void setup() throws IOException {
		prop=new Properties();
		//declear the config file
		FileInputStream fis=new FileInputStream("/Users/admin/git/Taskproject/Task/src/test/java/com/task/config/Config.properities");
		//upload the file
		prop.load(fis);


		//declear the directroy and driver obj
		System.setProperty("webdriver.chrome.driver", "/Users/admin/eclipse-workspace/chromedriver");
		driver=new ChromeDriver();
		//navigate url from config file
		driver.navigate().to(prop.getProperty("URL"));

	}
	@Override
	@AfterMethod
	public void finalize() {
		driver.close();
	}

	@Test
	public void CheckImage() {
		//assert the image true or false depends on the value return from page class
		Assert.assertTrue(page.image(driver),"it doesnt display");

	}
	@Test
	public void AllVeterinarians() {

		//calling veterinarians method
		page.veterinarians(driver);

	}
	@Test
	public void AllExistOwner() {
		//find button xpath and click on the button
		WebElement ownertable=driver.findElement(By.xpath("//a[@title = 'find owners']"));
		ownertable.click();
		//find button xpath and click on the button
		WebElement button=driver.findElement(By.xpath("//button[@type = 'submit']"));
		button.click();
		//create list of tr tags
		List<WebElement> Row=driver.findElements(By.xpath("//table[@id='owners']//tr"));
		String Firstcoulmn="//table[@id='owners']//tr[";
		String Lastcoulmn="]//td";
		String coulmn;
		//create list for coulmn
		List<WebElement> tablecoulmn;
		//for loop to check the rows
		for(int i=0;i<Row.size();i++) {
			coulmn = Firstcoulmn+i+Lastcoulmn;
			tablecoulmn=driver.findElements(By.xpath(coulmn));
			//for loop to check the coulmn
			for (WebElement element : tablecoulmn) {
				//print the all rows
				System.out.print(element.getText()+"     ");

			}
			//new line
			System.out.println();
		}


	}
	@Test
	public void AddOwnerAndPet() {

		//calling addowner method
		page.addowner(name1, famname, city, city1, number, driver);
		//calling addpet method
		page.addpet(petname1, petdate, driver);



	}
	@Test
	public void CheckData() {

		//create arrays for all input date
		String[] stringArray = new String[]{name1,ab, city ,city1,number,number};
		String[] stringArray1 = new String[]{petname1,petdate,animal};
		//cllaing addowner method
		page.addowner(name1, famname, city, city1, number, driver);
		//create list for all the rows
		List <WebElement> Row = driver.findElements(By.xpath("//table[@class='table table-striped']//tr"));
		String  rowc="//table[@class='table table-striped']//tr[";
		String row2="]//td";
		//for loop for rows
		for(int i=0;i<Row.size()+1;i++) {
			String coulmn=rowc+i+row2;
			//create list for all the coulmn
			List<WebElement> tablecoulmn = driver.findElements(By.xpath(coulmn));
			//for loop for coulmn
			for (WebElement element : tablecoulmn) {
				//System.out.print(tablecoulmn.get(j).getText()+"     ");

					//check the result from input array and the list for the owners
				Assert.assertEquals(element.getText(), stringArray[i], element.getText()+"is not equal"+stringArray[i]);


				}


			System.out.println();
		}
		page.addpet(petname1, petdate, driver);
		//create list for all pets data

		List <WebElement> Rowpet = driver.findElements(By.xpath("//dl[@class='dl-horizontal']//dd"));

		for(int r=0;r<Rowpet.size();r++) {



				System.out.print(Rowpet.get(r).getText()+"     ");
				//check the result from input array and the list for the pets
				Assert.assertEquals(Rowpet.get(r).getText(), stringArray1[r], Rowpet.get(r).getText()+"is not equal"+stringArray1[r]);





		}
		System.out.println();

	}




}
