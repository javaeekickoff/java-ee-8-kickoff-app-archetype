#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.config.auth;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import static ${groupId}.model.Group.USER;

import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import ${groupId}.business.exception.EmailNotVerifiedException;
import ${groupId}.business.exception.InvalidGroupException;
import ${groupId}.business.exception.CredentialsException;
import ${groupId}.business.service.UserService;
import ${groupId}.model.User;

@ApplicationScoped
public class KickoffIdentityStore implements IdentityStore {

	@Inject
	private UserService userService;

	@Override
	public CredentialValidationResult validate(Credential credential) {
		Supplier<User> userSupplier = null;

		if (credential instanceof UsernamePasswordCredential) {
			String email = ((UsernamePasswordCredential) credential).getCaller();
			String password = ((UsernamePasswordCredential) credential).getPasswordAsString();
			userSupplier = () -> userService.getByEmailAndPassword(email, password);
		}
		else if (credential instanceof CallerOnlyCredential) {
			String email = ((CallerOnlyCredential) credential).getCaller();
			userSupplier = () -> userService.getByEmail(email);
		}

		return validate(userSupplier);
	}

	static CredentialValidationResult validate(Supplier<User> userSupplier) {
		if (userSupplier == null) {
			return NOT_VALIDATED_RESULT;
		}

		try {
			User user = userSupplier.get();

			if (!user.getGroups().contains(USER)) {
				throw new InvalidGroupException();
			}

			if (!user.isEmailVerified()) {
				throw new EmailNotVerifiedException();
			}

			return new CredentialValidationResult(new KickoffCallerPrincipal(user), user.getRolesAsStrings());
		}
		catch (CredentialsException e) {
			return INVALID_RESULT;
		}
	}

}