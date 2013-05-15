package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationPylint extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationPylint() {
		super(new ViolationPylintSliceSpec());
	}

	public static class ViolationPylintSliceSpec extends AbstractViolationSliceSpec {

		public ViolationPylintSliceSpec() {
			super("Violation - Pylint", "violationpylint");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "pylint";
		}

	}
}
