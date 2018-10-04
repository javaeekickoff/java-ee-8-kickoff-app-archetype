#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.view.admin;

import static org.omnifaces.util.Messages.addGlobalWarn;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import ${groupId}.business.service.UserService;
import ${groupId}.model.User;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.optimusfaces.model.PagedDataModel;

@Named
@ViewScoped
public class UsersBacking implements Serializable {

	private static final long serialVersionUID = 1L;

	private PagedDataModel<User> users;

	@Inject
	private UserService userService;

	@PostConstruct
	public void init() {
		users = PagedDataModel.lazy(userService).build();
	}

	public void delete(User user) {
		// userService.delete(user);
		addGlobalWarn("This is just a demo, we won't actually delete users for now.");
	}

	public PagedDataModel<User> getUsers() {
		return users;
	}

}