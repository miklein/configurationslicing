package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationCpplint extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationCpplint() {
		super(new ViolationCpplintSliceSpec());
	}

	public static class ViolationCpplintSliceSpec extends AbstractViolationSliceSpec {

		public ViolationCpplintSliceSpec() {
			super("Violation - Cpplint", "violationcpplint");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "cpplint";
		}

	}
}
