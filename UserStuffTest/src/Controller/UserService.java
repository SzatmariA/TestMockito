package Controller;
import Excpetions.*;
import Model.*;
public interface UserService {
	 /** Checks if the provided username and password is correct.
	 *
	 * @throws DatabaseConnectionException if the database can not be contacted (network error, etc.)
	 * @throws NoSearchResultException if the user is not found in the database
	 * @throws InvalidPasswordException if the user and the password is not valid
	 */
	 User authenticateUser(String username, String password) throws DatabaseConnectionException,
	 NoSearchResultException, InvalidPasswordException;

}
