package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationSimian extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationSimian() {
		super(new ViolationSimianSliceSpec());
	}

	public static class ViolationSimianSliceSpec extends AbstractViolationSliceSpec {

		public ViolationSimianSliceSpec() {
			super("Violation - Simian", "violationsimian");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "simian";
		}

	}
}
