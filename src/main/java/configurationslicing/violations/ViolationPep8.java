package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationPep8 extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationPep8() {
		super(new ViolationPep8SliceSpec());
	}

	public static class ViolationPep8SliceSpec extends AbstractViolationSliceSpec {

		public ViolationPep8SliceSpec() {
			super("Violation - Pep8", "violationpep8");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "pep8";
		}

	}
}
