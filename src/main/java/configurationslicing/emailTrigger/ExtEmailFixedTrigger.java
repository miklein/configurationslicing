package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.FixedTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailFixedTrigger extends UnorderedStringSlicer<AbstractProject<?,?>>{

    public ExtEmailFixedTrigger() {
        super(new ExtEmailFixedTriggerSliceSpec());
    }

    public static class ExtEmailFixedTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailFixedTriggerSliceSpec(){
            super(",", "Editable Email Notification - Fixed Trigger", "emailextfixedtrigger");
        }

        @Override
        public String getTrigger() {
            return FixedTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new FixedTrigger();
        }

    }

}