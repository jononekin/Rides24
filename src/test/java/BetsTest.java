import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class BetsTest {
	 static BLFacadeImplementation sut;
	 static TestFacadeImplementation testBL;

	private Event ev;
	
	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();
		
		// you can parametrize the DataAccess used by BLFacadeImplementation
		DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		sut=new BLFacadeImplementation(da);
		
		testBL= new TestFacadeImplementation();
	}
	
	@Test
	//sut.createQuestion:  There are not two questions with a same text 
	public void test1() {
		try {
			
			//DatuBasearen egoera definitu: Gertaera berri bat sortu galdera batekin	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			ev = testBL.addEvent("Proba gertaera",oneDate );

				sut.createQuestion(ev, "proba galdera", 2);
			//sut: An query is created 
			sut.createQuestion(ev, "proba galdera", 2);
		    fail();
		   } catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			} catch (EventFinished e) {
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				  //Remove the created objects in the database (cascade removing)   
		          boolean b=testBL.removeEvent(ev);
		           System.out.println("Finally "+b);          
		        }
		   }
}
