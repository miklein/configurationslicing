package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationCodenarc extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationCodenarc() {
		super(new ViolationCodenarcSliceSpec());
	}

	public static class ViolationCodenarcSliceSpec extends AbstractViolationSliceSpec {

		public ViolationCodenarcSliceSpec() {
			super("Violation - CodeNarc", "violationcodenarc");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "codenarc";
		}

	}
}
