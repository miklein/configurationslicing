package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationFindbugs extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationFindbugs() {
		super(new ViolationFindbugsSliceSpec());
	}

	public static class ViolationFindbugsSliceSpec extends AbstractViolationSliceSpec {

		public ViolationFindbugsSliceSpec() {
			super("Violation - Findbugs", "violationfindbugs");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "findbugs";
		}

	}
}
