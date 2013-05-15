package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.StillFailingTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailStillFailingTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailStillFailingTrigger() {
        super(new ExtEmailStillFailingTriggerSliceSpec());
    }

    public static class ExtEmailStillFailingTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailStillFailingTriggerSliceSpec() {
            super(",", "Editable Email Notification - Still-Failing Trigger", "emailextstillfailingtrigger");
        }

        @Override
        public String getTrigger() {
            return StillFailingTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new StillFailingTrigger();
        }

    }

}
