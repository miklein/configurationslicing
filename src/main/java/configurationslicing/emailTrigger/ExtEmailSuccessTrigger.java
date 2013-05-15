package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.SuccessTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailSuccessTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailSuccessTrigger() {
        super(new ExtEmailSuccessTriggerSliceSpec());
    }

    public static class ExtEmailSuccessTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailSuccessTriggerSliceSpec() {
            super(",", "Editable Email Notification - Success Trigger", "emailextsuccesstrigger");
        }

        @Override
        public String getTrigger() {
            return SuccessTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new SuccessTrigger();
        }

    }

}