package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationCpd extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationCpd() {
		super(new ViolationCpdSliceSpec());
	}

	public static class ViolationCpdSliceSpec extends AbstractViolationSliceSpec {

		public ViolationCpdSliceSpec() {
			super("Violation - CPD", "violationcpd");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "cpd";
		}

	}
}
