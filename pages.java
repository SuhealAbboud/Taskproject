package com.task.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class pages {
	WebDriver driver;
	public void veterinarians(WebDriver driver) {
		this.driver=driver;
		// find the veterinarians and click on it
		WebElement veterinarians=driver.findElement(By.xpath("//a[@title = 'veterinarians']"));
		veterinarians.click();
		//create list for all the item
	List<WebElement> Row=driver.findElements(By.xpath("//table[@id='vets']//tr"));
	String Firstcoulmn="//table[@id='vets']//tr[";
	String Lastcoulmn="]//td";
	String coulmn;
	//list for the rows
	List<WebElement> tablecoulmn;
	// declear all rows
	for(int i=0;i<Row.size();i++) {
		coulmn = Firstcoulmn+i+Lastcoulmn;
		tablecoulmn=driver.findElements(By.xpath(coulmn));
		// declear all coulmn
		for (WebElement element : tablecoulmn) {
			System.out.print(element.getText()+"     ");

		}
		System.out.println();
	}


}
	public void addowner(String name1,String famname,String city,String city1,String number,WebDriver driver) {
		this.driver=driver;
		WebElement ownertable=driver.findElement(By.xpath("//a[@title = 'find owners']"));
		ownertable.click();
		WebElement ownerbtn = driver.findElement(By.xpath("//*[text()='Add Owner']"));
		ownerbtn.click();
		//insert first name
		WebElement Fname = driver.findElement(By.xpath("//input[@id='firstName']"));
		Fname.sendKeys(name1);
		//insert last name
		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		lastName.sendKeys(famname);
		//insert address
		WebElement address = driver.findElement(By.xpath("//input[@id='address']"));
		address.sendKeys(city);
		//insert city
		WebElement city2 = driver.findElement(By.xpath("//input[@id='city']"));
		city2.sendKeys(city1);
		//insert phone number
		WebElement telephone = driver.findElement(By.xpath("//input[@id='telephone']"));
		telephone.sendKeys(number);
		//click on add owner
		WebElement saveowner = driver.findElement(By.xpath("//*[text()='Add Owner']"));
		saveowner.click();

	}
	public void addpet(String petname1,String petdate,WebDriver driver) {
		this.driver=driver;
		WebElement s = driver.findElement(By.xpath("//*[text()='Add\n"
				+ "      New Pet']"));
		s.click();
		WebElement petname = driver.findElement(By.xpath("//input[@id='name']"));
		petname.sendKeys(petname1);
		WebElement Bdate = driver.findElement(By.xpath("//input[@id='birthDate']"));
		Bdate.sendKeys(petdate);
		WebElement w = driver.findElement(By.xpath("//*[text()='Add Pet']"));
		w.click();

	}
	public Boolean image(WebDriver driver) {
		this.driver=driver;
		//find image path
		WebElement ImageFile = driver.findElement(By.xpath("//img[@class='img-responsive']"));
		//check if the image display or not
		Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	    return ImagePresent;
	}

}
