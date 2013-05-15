package configurationslicing.violations;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.plugins.violations.types.perlcritic.PerlCriticDescriptor;
import configurationslicing.UnorderedStringSlicer;

/**
 * @author Michael Klein
 */
@Extension
public class ViolationPerlcritic extends UnorderedStringSlicer<AbstractProject<?, ?>> {

	public ViolationPerlcritic() {
		super(new ViolationPerlcriticSliceSpec());
	}

	public static class ViolationPerlcriticSliceSpec extends AbstractViolationSliceSpec {

		public ViolationPerlcriticSliceSpec() {
			super("Violation - PerlCritic", "violationperlcritic");
		}

		@Override
		public String getViolationType() {
			return PerlCriticDescriptor.TYPE_NAME;
		}

	}
}
