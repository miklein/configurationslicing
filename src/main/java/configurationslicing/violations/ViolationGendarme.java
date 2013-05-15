package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationGendarme extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationGendarme() {
		super(new ViolationGendarmeSliceSpec());
	}

	public static class ViolationGendarmeSliceSpec extends AbstractViolationSliceSpec {

		public ViolationGendarmeSliceSpec() {
			super("Violation - Gendarme", "violationgendarme");
		}

		@Override
		public String getViolationType() {
			// There is no way to get it from the plugin
			return "gendarme";
		}

	}
}
