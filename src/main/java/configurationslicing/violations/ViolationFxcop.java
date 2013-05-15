package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationFxcop extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationFxcop() {
		super(new ViolationFxcopSliceSpec());
	}

	public static class ViolationFxcopSliceSpec extends AbstractViolationSliceSpec {

		public ViolationFxcopSliceSpec() {
			super("Violation - FxCop", "violationfxcop");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "fxcop";
		}

	}
}
