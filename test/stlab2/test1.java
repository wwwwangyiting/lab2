package stlab2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test1 {
  private WebDriver driver;
  private String baseUrl;
  @Before
  public void setUp() throws Exception {
	  String driverPath = System.getProperty("user.dir") + "/test/stlab2/geckodriver.exe";
	  System.setProperty("webdriver.gecko.driver", driverPath);
	  driver = new FirefoxDriver();
	  baseUrl = "http://121.193.130.195:8800/login";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
  }

  @Test
  public void test() throws Exception {
	  File excelFile = new File("/Users/lenovo/workspace/�½��ļ���/stlab2/�����������.xlsx"); 
	  XSSFWorkbook wb=null;
	  Thread thread = new Thread();
	  try { 
		  InputStream in = new FileInputStream(excelFile); //�����������
		  wb = new XSSFWorkbook(in);
		  XSSFSheet sheet = wb.getSheetAt(0);
	      for(int i=2;i<sheet.getLastRowNum();i++){
	    	  XSSFRow row=sheet.getRow(i);
	    	  XSSFCell id1=row.getCell(1);
	    	  DataFormatter formatter = new DataFormatter();
	    	  String id = formatter.formatCellValue(id1);
	    	  String pwd=id.substring(4);
	    	  XSSFCell name1=row.getCell(2);
	    	  String name3=name1.toString();
	    	  XSSFCell git1=row.getCell(3);
	    	  String git=git1.toString();//�����������ݲ�ת����������
	    	  WebDriver driver = new FirefoxDriver();
	    	  driver.get(baseUrl);//�򿪵�¼��ҳ
	    	  WebDriverWait wait = new WebDriverWait(driver, 3000);
	    	  WebElement textid = wait.until(ExpectedConditions.elementToBeClickable(By.name("id")));
	    	  WebElement textpwd = driver.findElement(By.name("password"));//����ҳ�ҵ���ӦԪ��
	    	  textid.sendKeys(id);
	    	  textpwd.sendKeys(pwd);//����Ӧ���������Ԫ��
	    	  WebElement btn = driver.findElement(By.id("btn_login"));
	    	  btn.sendKeys(Keys.ENTER);//�����¼
	          Thread.sleep(1000); //�߳�˯�ߣ�����ҳ����ع������´���
	          WebElement name2 = driver.findElement(By.id("student-name"));
	    	  WebElement git2 = driver.findElement(By.id("student-git"));//�ҵ��µ�ҳ���Ӧ��Ԫ��
	          String gitt=git2.getText();
	          String name=name2.getText();//ת����������
	          assertEquals(name3,name);
	          assertEquals(git,gitt);//���бȽϲ���
	          driver.close();//�رյ�ǰ��ҳ
	      }
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}

