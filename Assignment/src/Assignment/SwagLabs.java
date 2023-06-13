package Assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagLabs {
	
	
	WebDriver driver;
	
	//DRIVER
	
    @Test(priority=0)
    public void setup() throws InterruptedException {
	  
		System.setProperty("webdriver.chrome.driver", "E:\\Automation\\Selenium\\chromedriver_win32 (2)\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		String expected1="https://www.saucedemo.com/";
		Assert.assertEquals(driver.getCurrentUrl(), expected1);
    }
		
		
	//LOGIN	
		
		@Test(priority=1)
	    public void login() throws InterruptedException {
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
        String expected2="https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(driver.getCurrentUrl(), expected2);
		}
        
      
     //SORT
		
		@Test(priority=2)
	    public void sort() throws InterruptedException {
			
  		Select s=new Select(driver.findElement(By.className("product_sort_container")));
  		s.selectByValue("lohi");
  		Thread.sleep(3000);
  		
  		//String expected3="Price (low to high)";
  		//WebElement container=driver.findElement(By.className("product_sort_container"));
		//Assert.assertEquals(expected3, container.getText());
		}
  		
  	
        
	  //ADD TO CART
        
		@Test(priority=3)
	    public void addtocart() throws InterruptedException {
			
			
        List <WebElement> products=driver.findElements(By.className("inventory_item"));
		for(WebElement all:products) {
			WebElement carts=all.findElement((By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")));
			carts.click();
			}
  		Thread.sleep(3000);
  		
  			//WebElement button=driver.findElement(By.id("shopping_cart_container"));
  			//Assert.assertEquals(button.getText(), 6);
  		
		}
  		
  		

  	  //CART PAGE
		
		@Test(priority=4)
	    public void removefromcart() throws InterruptedException {
  		
  		driver.findElement(By.id("shopping_cart_container")).click();
  		Thread.sleep(1000);

        List <WebElement> selected=driver.findElements(By.className("inventory_item_price"));
		for(WebElement all:selected) {
            String priceText = all.getText();
            double price = Double.parseDouble(priceText.substring(1));

            if (price < 15) {
            	WebElement remove=driver.findElement(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));
    			remove.click();
            }
        }
		
  		Thread.sleep(3000);
  		
  		
		}
  		
  		
  		
  		//CHECKOUT
  		
		@Test(priority=5)
	    public void checkout() throws InterruptedException {
  		
  		 WebElement checkoutButton = driver.findElement(By.id("checkout"));
         checkoutButton.click();
         
         WebElement firstNameField = driver.findElement(By.id("first-name"));
         WebElement lastNameField = driver.findElement(By.id("last-name"));
         WebElement zipCodeField = driver.findElement(By.id("postal-code"));
         WebElement continueButton = driver.findElement(By.id("continue"));

         firstNameField.sendKeys("Amala");
         lastNameField.sendKeys("Mathew");
         zipCodeField.sendKeys("12345");
         continueButton.click();
   		 Thread.sleep(3000);

         WebElement finishButton = driver.findElement(By.id("finish"));
         finishButton.click();
   		 Thread.sleep(3000);
   		 String expected5="https://www.saucedemo.com/checkout-complete.html";
		 Assert.assertEquals(driver.getCurrentUrl(), expected5);
   		 
		}

         
         //BACK TO HOME
         
		@Test(priority=6)
	    public void backtoproducts() throws InterruptedException {
			
        driver.findElement(By.id("back-to-products")).click();
   		Thread.sleep(3000);
   		String expected2="https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(driver.getCurrentUrl(), expected2);
		
		}
 
         
         //LOGOUT
		
		@Test(priority=7)
	    public void logout() throws InterruptedException {
			
         driver.findElement(By.id("react-burger-menu-btn")).click();
   		 WebElement logout = driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
   		 JavascriptExecutor jse = (JavascriptExecutor) driver;
   		 jse.executeScript("arguments[0].click();", logout);
  		 Thread.sleep(3000);
  		 String expected1="https://www.saucedemo.com/";
		 Assert.assertEquals(driver.getCurrentUrl(), expected1);
		 driver.quit();
		}

 
		  
  }	
  

  
  
  

