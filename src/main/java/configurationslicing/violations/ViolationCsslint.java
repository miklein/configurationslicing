package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationCsslint extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationCsslint() {
		super(new ViolationCsslintSliceSpec());
	}

	public static class ViolationCsslintSliceSpec extends AbstractViolationSliceSpec {

		public ViolationCsslintSliceSpec() {
			super("Violation - CSSLint", "violationcsslint");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "csslint";
		}

	}
}
