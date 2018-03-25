package Controller;
import java.util.Map;

import Excpetions.*;
import Model.*;

public class UserController {
	private UserService userService;

	 public void setUserService(UserService u) {
	 userService = u;
	 }

	 /** Logs in the user based on the provided username and password.
	 * @param username the userId
	 * @param password the user's password
	 * @param model objects provided for the view layer to display login information
	 * @return the name of the view to display
	 */
	 public String login(String username, String password, Map<String, Object> model) {
	 try {
	 User userToLogin = userService.authenticateUser(username, password);
	 model.put("user", userToLogin);
	 return "redirect:home";
	 } catch (DatabaseConnectionException e) {
	 model.put("errorMessage", e.getMessage());
	 return "index";
	 } catch (NoSearchResultException e) {
	 model.put("errorMessage", e.getMessage());
	 return "index";
	 } catch (InvalidPasswordException e) {
	 model.put("errorMessage", e.getMessage());
	 return "index";
	 }
}
	 }
