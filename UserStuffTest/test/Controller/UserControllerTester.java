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

/*
 ___ __ __   ______   ______   ___   ___   ________  _________  ______           __  __   ______   __  __            ______   __  __   ______   ___   ___   ______   ________   ______   ______      
/__//_//_/\ /_____/\ /_____/\ /___/\/__/\ /_______/\/________/\/_____/\         /_/\/_/\ /_____/\ /_/\/_/\          /_____/\ /_/\/_/\ /_____/\ /___/\/__/\ /_____/\ /_______/\ /_____/\ /_____/\     
\::\| \| \ \\:::_ \ \\:::__\/ \::.\ \\ \ \\__.::._\/\__.::.__\/\:::_ \ \        \ \ \ \ \\:::_ \ \\:\ \:\ \         \::::_\/_\:\ \:\ \\:::__\/ \::.\ \\ \ \\::::_\/_\::: _  \ \\:::__\/ \::::_\/_    
 \:.      \ \\:\ \ \ \\:\ \  __\:: \/_) \ \  \::\ \    \::\ \   \:\ \ \ \        \:\_\ \ \\:\ \ \ \\:\ \:\ \         \:\/___/\\:\ \:\ \\:\ \  __\:: \/_) \ \\:\/___/\\::(_)  \ \\:\ \  __\:\/___/\   
  \:.\-/\  \ \\:\ \ \ \\:\ \/_/\\:. __  ( (  _\::\ \__  \::\ \   \:\ \ \ \        \::::_\/ \:\ \ \ \\:\ \:\ \         \:::._\/ \:\ \:\ \\:\ \/_/\\:. __  ( ( \:::._\/ \:: __  \ \\:\ \/_/\\::___\/_  
   \. \  \  \ \\:\_\ \ \\:\_\ \ \\: \ )  \ \/__\::\__/\  \::\ \   \:\_\ \ \         \::\ \  \:\_\ \ \\:\_\:\ \         \:\ \    \:\_\:\ \\:\_\ \ \\: \ )  \ \ \:\ \    \:.\ \  \ \\:\_\ \ \\:\____/\ 
    \__\/ \__\/ \_____\/ \_____\/ \__\/\__\/\________\/   \__\/    \_____\/          \__\/   \_____\/ \_____\/          \_\/     \_____\/ \_____\/ \__\/\__\/  \_\/     \__\/\__\/ \_____\/ \_____\/ 
                                                                                                                                                                                                                                                    |___/                                                                                                                                                                                                  
 */

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
