package configurationslicing.emailTrigger;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.emailext.plugins.EmailTrigger;
import hudson.plugins.emailext.plugins.trigger.RegressionTrigger;
import configurationslicing.UnorderedStringSlicer;

@Extension
public class ExtEmailRegressionTrigger extends UnorderedStringSlicer<AbstractProject<?, ?>> {

    public ExtEmailRegressionTrigger() {
        super(new ExtEmailRegressionTriggerSliceSpec());
    }

    public static class ExtEmailRegressionTriggerSliceSpec extends AbstractTriggerSliceSpec {

        public ExtEmailRegressionTriggerSliceSpec() {
            super(",", "Editable Email Notification - Regression Trigger", "emailextregressiontrigger");
        }

        @Override
        public String getTrigger() {
            return RegressionTrigger.TRIGGER_NAME;
        }

        @Override
        public EmailTrigger createTrigger() {
            return new RegressionTrigger();
        }

    }

}