#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.view.viewhandler;

import static org.omnifaces.util.Beans.getReference;
import static org.omnifaces.util.FacesLocal.getRequestContextPath;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;

import ${groupId}.view.ActiveLocale;

public class LocaleAwareViewHandler extends ViewHandlerWrapper {

	public LocaleAwareViewHandler(ViewHandler wrapped) {
		super(wrapped);
	}

	@Override
	public String getActionURL(FacesContext context, String viewId) {
		String contextPath = getRequestContextPath(context);
		String localePath = getReference(ActiveLocale.class).getPath();
		String uri = super.getActionURL(context, viewId).substring(contextPath.length());
		return contextPath + localePath + uri;
	}

}
