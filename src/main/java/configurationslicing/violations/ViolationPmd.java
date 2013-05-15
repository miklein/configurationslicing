package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationPmd extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationPmd() {
		super(new ViolationPmdSliceSpec());
	}

	public static class ViolationPmdSliceSpec extends AbstractViolationSliceSpec {

		public ViolationPmdSliceSpec() {
			super("Violation - PMD", "violationpmd");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "pmd";
		}

	}
}
