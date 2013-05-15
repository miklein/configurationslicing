package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.NotBuiltTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailNotBuildTrigger extends UnorderedStringSlicer<AbstractProject<?,?>>{

    public ExtEmailNotBuildTrigger(){
        super(new ExtEmailNotBuildTriggerSliceSpec());
    }

    public static class ExtEmailNotBuildTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailNotBuildTriggerSliceSpec() {
            super(",", "Editable Email Notification - Not-Build Trigger", "emailextnotbuildtrigger");
        }

        @Override
        public String getTrigger() {
            return NotBuiltTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new NotBuiltTrigger();
        }
    }

}