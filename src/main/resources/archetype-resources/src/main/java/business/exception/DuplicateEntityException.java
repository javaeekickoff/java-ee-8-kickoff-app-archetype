#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.business.exception;

/**
 * Thrown when an unique constraint is violated.
 */
public class DuplicateEntityException extends EntityException {

	private static final long serialVersionUID = 1L;

}