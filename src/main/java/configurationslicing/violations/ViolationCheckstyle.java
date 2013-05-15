package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationCheckstyle extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationCheckstyle() {
		super(new ViolationCheckstyleSliceSpec());
	}

	public static class ViolationCheckstyleSliceSpec extends AbstractViolationSliceSpec {

		public ViolationCheckstyleSliceSpec() {
			super("Violation - Checkstyle", "violationcheckstyle");
		}

		@Override
		public String getViolationType() {
			return "checkstyle";		// There is no way to get it from the plugin
		}

	}
}
