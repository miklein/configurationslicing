package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationJcreport extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationJcreport() {
		super(new ViolationJcreportSliceSpec());
	}

	public static class ViolationJcreportSliceSpec extends AbstractViolationSliceSpec {

		public ViolationJcreportSliceSpec() {
			super("Violation - JcReport", "violationjcreport");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "jcreport";
		}

	}
}
