package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationStylecop extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationStylecop() {
		super(new ViolationStylecopSliceSpec());
	}

	public static class ViolationStylecopSliceSpec extends AbstractViolationSliceSpec {

		public ViolationStylecopSliceSpec() {
			super("Violation - StyleCop", "violationstylecop");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "stylecop";
		}

	}
}
