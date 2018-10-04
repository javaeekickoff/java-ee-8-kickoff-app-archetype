#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.business.exception;

/**
 * Thrown when login username does not exist in DB.
 */
public class InvalidUsernameException extends CredentialsException {

	private static final long serialVersionUID = 1L;

}