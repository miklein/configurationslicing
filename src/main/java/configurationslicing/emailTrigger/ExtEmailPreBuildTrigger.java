package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.PreBuildTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailPreBuildTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailPreBuildTrigger() {
        super(new ExtEmailPreBuildTriggerSliceSpec());
    }

    public static class ExtEmailPreBuildTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailPreBuildTriggerSliceSpec() {
            super(",", "Editable Email Notification - Pre-Build Trigger", "emailextprebuildtrigger");
        }

        @Override
        public String getTrigger() {
            return PreBuildTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new PreBuildTrigger();
        }

    }

}