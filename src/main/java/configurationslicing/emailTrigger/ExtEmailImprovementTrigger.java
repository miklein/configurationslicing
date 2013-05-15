package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.ImprovementTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailImprovementTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailImprovementTrigger() {
        super(new ExtEmailImprovementTriggerSliceSpec());
    }

    public static class ExtEmailImprovementTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailImprovementTriggerSliceSpec() {
            super(",", "Editable Email Notification - Improvement Trigger", "emailextimprovementtrigger");
        }

        @Override
        public String getTrigger() {
            return ImprovementTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new ImprovementTrigger();
        }

    }

}