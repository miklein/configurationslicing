package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.FailureTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailFailureTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailFailureTrigger() {
        super(new ExtEmailFailureTriggerSliceSpec());
    }

    public static class ExtEmailFailureTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailFailureTriggerSliceSpec() {
            super(",", "Editable Email Notification - Failure Trigger", "emailextfailuretrigger");
        }

        @Override
        public String getTrigger() {
            return FailureTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new FailureTrigger();
        }

    }

}