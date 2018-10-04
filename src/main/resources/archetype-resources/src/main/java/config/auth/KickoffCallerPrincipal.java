#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.config.auth;

import javax.security.enterprise.CallerPrincipal;

import ${groupId}.model.User;

/**
 * @see KickoffIdentityStore
 * @see ActiveUser
 */
public class KickoffCallerPrincipal extends CallerPrincipal {

	private final User user;

	public KickoffCallerPrincipal(User user) {
		super(user.getEmail());
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}