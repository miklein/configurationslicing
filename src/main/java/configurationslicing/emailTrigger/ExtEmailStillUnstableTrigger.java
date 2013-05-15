package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.StillUnstableTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailStillUnstableTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailStillUnstableTrigger() {
        super(new ExtEmaiStilllUnstableTriggerSliceSpec());
    }

    public static class ExtEmaiStilllUnstableTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmaiStilllUnstableTriggerSliceSpec() {
            super(",", "Editable Email Notification - Still-Unstable Trigger", "emailextstillunstabletrigger");
        }

        @Override
        public String getTrigger() {
            return StillUnstableTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new StillUnstableTrigger();
        }

    }

}
