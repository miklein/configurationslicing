package configurationslicing.email;

import java.io.IOException;
import java.util.List;

import hudson.model.AbstractProject;

@SuppressWarnings("unchecked")
public interface ProjectHandler {

	String getRecipients(AbstractProject project);
	boolean removeMailer(AbstractProject project) throws IOException;
	boolean addMailer(AbstractProject project) throws IOException;
	boolean setRecipients(AbstractProject project, String recipients) throws IOException;

	boolean removeTrigger(AbstractProject project);
	boolean addTrigger(AbstractProject project, List<String> values);
}
