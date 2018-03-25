package Controller;

import static org.junit.Assert.*;
import Excpetions.*;
import Model.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class UserControllerTester {

	private UserController controller; 
	private UserService service;
	User user = new User();
	@Before
	public void setUp() throws Exception {
		//A service-t mockoljuk mert nincs megirva az egesz...
		controller = new UserController();
		service = mock(UserService.class);
		controller.setUserService(service);
		
		//Mockito betanitasa a testcasekre:
		when(service.authenticateUser("a", "b")).thenReturn(user);
		when(service.authenticateUser("asd", "dsa")).thenThrow(new NoSearchResultException("No kinda user"));
		when(service.authenticateUser("boi", "qwerty")).thenThrow(new InvalidPasswordException("Invalid password you dumbfuck"));
		when(service.authenticateUser("", "dunno")).thenThrow(new NoSearchResultException("No kinda user. Jeeez"));
		when(service.authenticateUser(".", ".")).thenThrow(new DatabaseConnectionException("Bruh, didn't know what kind of parameters can cause db connection exception."));
	}
	
	@Test
	public void testValidLogin() throws DatabaseConnectionException, NoSearchResultException, InvalidPasswordException {
		//Ha nem azok kozul irunk be amik exceptiont dobnak akkor elfogadja...
		//a hashmap az mindegy, csak letre kell hozni egyet...
		String asd = controller.login("a", "b", new HashMap<String, Object>());
		assertEquals("redirect:home", asd);
		verify(service).authenticateUser("a","b");
		
	}
	
	@Test
	public void testNoSearchResultLogin() throws DatabaseConnectionException, NoSearchResultException, InvalidPasswordException {
		String asd = controller.login("asd", "dsa", new HashMap<String, Object>());
		assertEquals("index", asd);
		verify(service).authenticateUser("asd","dsa");
	}
	
	@Test
	public void testNoSearchResultLoginWithEmptyUserName() throws DatabaseConnectionException, NoSearchResultException, InvalidPasswordException {
		
		
		String asd = controller.login("", "dunno", new HashMap<String, Object>());
		assertEquals("index", asd);
		verify(service).authenticateUser("","dunno");
	}
	

	@Test
	public void testInvalidPasswordLogin() throws DatabaseConnectionException, NoSearchResultException, InvalidPasswordException {
		
		
		String asd = controller.login("boi", "qwerty", new HashMap<String, Object>());
		assertEquals("index", asd);
		verify(service).authenticateUser("boi","qwerty");
		
	}
	
	@Test
	public void testDataBaseConnection() throws DatabaseConnectionException, NoSearchResultException, InvalidPasswordException {
		
		
		String asd = controller.login(".", ".", new HashMap<String, Object>());
		assertEquals("index", asd);
		verify(service).authenticateUser(".",".");
		
	}
	

}
