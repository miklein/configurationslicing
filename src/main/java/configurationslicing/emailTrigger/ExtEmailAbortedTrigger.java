package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.AbortedTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailAbortedTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailAbortedTrigger() {
        super(new ExtEmailAbortedTriggerSliceSpec());
    }

    public static class ExtEmailAbortedTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailAbortedTriggerSliceSpec() {
            super(",", "Editable Email Notification - Aborted Trigger", "emailextabortedtrigger");
        }

        @Override
        public String getTrigger() {
            return AbortedTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new AbortedTrigger();
        }

    }

}