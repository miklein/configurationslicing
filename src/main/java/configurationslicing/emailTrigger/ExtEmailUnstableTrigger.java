package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.UnstableTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailUnstableTrigger extends UnorderedStringSlicer<AbstractProject<?,?>>{

    public ExtEmailUnstableTrigger() {
        super(new ExtEmailUnstableTriggerSliceSpec());
    }

    public static class ExtEmailUnstableTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailUnstableTriggerSliceSpec(){
            super(",", "Editable Email Notification - Unstable Trigger", "emailextunstabletrigger");
        }

        @Override
        public String getTrigger() {
            return UnstableTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new UnstableTrigger();
        }

    }

}