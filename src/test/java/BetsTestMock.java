import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BetsTestMock {
     DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Event mockedEvent=Mockito.mock(Event.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	//Same Test diferent alternatives
	//sut.createQuestion:  There are not two questions with a same text 

	@Test
	public void test1() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();

			Mockito.when(dataAccess.createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class))).thenThrow(QuestionAlreadyExist.class);
			

			//sut: An query is created 
			Question q=sut.createQuestion(mockedEvent, "proba galdera", 2);
		    fail();
		   } catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			} catch (EventFinished e) {
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	
	
	
	@Test
	//sut.createQuestion:  There are not two questions with a same text 
	public void test2() {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date oneDate=null;;
				try {
					oneDate = sdf.parse("05/10/2022");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
				Mockito.doReturn(new Question("proba galdera", 2,mockedEvent)).when(dataAccess).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				

				//sut: An query is created 
				Question q=sut.createQuestion(mockedEvent, "proba galdera", 2);
				
				Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));
				
				
				ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
				ArgumentCaptor<String> questionStringCaptor = ArgumentCaptor.forClass(String.class);
				ArgumentCaptor<Float> betMinimunCaptor = ArgumentCaptor.forClass(Float.class);
				
				Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(eventCaptor.capture(),questionStringCaptor.capture(), betMinimunCaptor.capture());
				Float f=betMinimunCaptor.getValue();
				System.out.println(f);

				assertTrue(eventCaptor.getValue().equals(mockedEvent));
				assertTrue(questionStringCaptor.getValue().equals("proba galdera"));
				assertTrue(betMinimunCaptor.getValue().equals(new Float(3)));

			   } catch (QuestionAlreadyExist e) {
				// TODO Auto-generated catch block
				assertTrue(true);
				} catch (EventFinished e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
		
	
/*	@Test
	//sut.createQuestion:  There are not two questions with a same text 
	public void test2() {
		try {
			
			Mockito.doReturn(true).when(dataAccess).existQuestion(Mockito.any(Event.class),Mockito.any(String.class));

			Mockito.doReturn(new Date()).when(mockedEvent).getEventDate();

			//DatuBasearen egoera definitu: Gertaera berri bat sortu galdera batekin	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
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
			}} */
}
