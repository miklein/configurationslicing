package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationJslint extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationJslint() {
		super(new ViolationJslintSliceSpec());
	}

	public static class ViolationJslintSliceSpec extends AbstractViolationSliceSpec {

		public ViolationJslintSliceSpec() {
			super("Violation - JSLint", "violationjslint");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "jslint";
		}

	}
}
