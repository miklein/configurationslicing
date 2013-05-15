package configurationslicing.violations;

import hudson.maven.MavenReporter;
import hudson.maven.MavenModuleSet;
import hudson.model.AbstractProject;
import hudson.model.Descriptor;
import hudson.model.FreeStyleProject;
import hudson.model.Hudson;
import hudson.plugins.violations.TypeConfig;
import hudson.plugins.violations.ViolationsPublisher;
import hudson.plugins.violations.hudson.maven.ViolationsMavenReporter;
import hudson.tasks.Publisher;
import hudson.util.DescribableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import configurationslicing.UnorderedStringSlicer.UnorderedStringSlicerSpec;

/**
 * @author Michael Klein
 */
public abstract class AbstractViolationSliceSpec extends
		UnorderedStringSlicerSpec<AbstractProject<?, ?>> {

	protected static final String DISABLED = "(Disabled)";
	protected static final String SEPARATOR = ",";

	private String name;
	private String url;

	protected AbstractViolationSliceSpec(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public abstract String getViolationType();

	@Override
	public String getConfiguredValueDescription() {
		return "Min(sunny), Max(stormy), Unstable <i>(e.g. 10,885,999)</i>";
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getName(AbstractProject<?, ?> item) {
		return item.getName();
	}

	public String getDefaultValueString() {
		return DISABLED;
	}

	public List<AbstractProject<?, ?>> getWorkDomain() {
		return (List) Hudson.getInstance().getItems(AbstractProject.class);
	}

	public Set<Entry<String, TypeConfig>> getViolationTypeConfigs(AbstractProject<?, ?> project) {
		Set<Entry<String, TypeConfig>> typeConfigs = null;

		if (project instanceof FreeStyleProject) {
			DescribableList<Publisher, Descriptor<Publisher>> publishers = project
					.getPublishersList();
			Descriptor<Publisher> descriptor = Hudson.getInstance()
					.getDescriptor(ViolationsPublisher.class);
			Publisher publisher = publishers.get(descriptor);
			ViolationsPublisher violationPublisher = (ViolationsPublisher) publisher;

			if (violationPublisher == null) {
				//violations not activated
				return typeConfigs;
			}

			typeConfigs = violationPublisher.getConfig().getTypeConfigs().entrySet();
		} else if (project instanceof MavenModuleSet) {
			DescribableList<MavenReporter, Descriptor<MavenReporter>> reporters = ((MavenModuleSet) project)
					.getReporters();

			for (MavenReporter mavenReporter : reporters) {
				if (mavenReporter instanceof ViolationsMavenReporter) {
					typeConfigs = ((ViolationsMavenReporter) mavenReporter)
							.getConfig().getTypeConfigs().entrySet();
				}
			}

		}
		return typeConfigs;
	}

	public List<String> getValues(AbstractProject<?, ?> project) {
		List<String> values = new ArrayList<String>();
		String violation = null;
		Set<Entry<String, TypeConfig>> typeConfigs = getViolationTypeConfigs(project);

		if (typeConfigs == null) {
			values.add(DISABLED);

			return values;
		}
		for (Entry<String, TypeConfig> entry : typeConfigs) {
			if (entry.getValue().getType().equals(getViolationType())) {
				violation = entry.getValue().getMin() + SEPARATOR
						+ entry.getValue().getMax() + SEPARATOR
						+ entry.getValue().getUnstable();
				values.add(violation);

			}
		}

		return values;
	}

	public boolean setValues(AbstractProject<?, ?> item, List<String> set) {
		String configLine;
		List<String> values = new ArrayList<String>();
		boolean disabled = false;
		configLine = set.iterator().next();

		if (DISABLED.equals(configLine) || StringUtils.isEmpty(configLine)
				|| configLine == null) {
			disabled = true;
		} else {
			final String[] split = configLine.split(SEPARATOR);
			if (split.length == 3) {
				values.add(split[0]);
				values.add(split[1]);
				values.add(split[2]);

			} else {
				disabled = true;
			}
		}

		boolean saved = false;
		boolean oneSaved = false;

		try {
			if (disabled) {
				oneSaved = removeViolations(item);
			} else {
				oneSaved = addViolations(item, values);
			}
			if (oneSaved) {
				try {
					item.save();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				saved = true;
			}
			return saved;
		} catch (IOException e) {
			return false;
		}
	}

	private void updateViolationValues(Set<Entry<String, TypeConfig>> typeConfigs, List<String> values){
		int min = Integer.parseInt(values.get(0));
		int max = Integer.parseInt(values.get(1));
		int unstable = Integer.parseInt(values.get(2));

		for (Entry<String, TypeConfig> entry : typeConfigs) {
			if (entry.getValue().getType().equals(getViolationType())) {
				entry.getValue().setMin(min);
				entry.getValue().setMax(max);
				entry.getValue().setUnstable(unstable);
			}
		}
	}

	public boolean addViolations(AbstractProject<?, ?> item, List<String> values) throws IOException {
		Set<Entry<String, TypeConfig>> typeConfigs = getViolationTypeConfigs(item);

		if (typeConfigs != null) {
			// update values
			updateViolationValues(typeConfigs, values);

			return true;
		}
		// violations not activated -> activate it
		if (item instanceof FreeStyleProject) {
			ViolationsPublisher violationPublisher = new ViolationsPublisher();
			typeConfigs = violationPublisher.getConfig().getTypeConfigs().entrySet();
			updateViolationValues(typeConfigs, values);
			item.getPublishersList().add(violationPublisher);

			return true;

		} else if (item instanceof MavenModuleSet) {
			ViolationsMavenReporter violationMavenReporter = new ViolationsMavenReporter();
			typeConfigs = violationMavenReporter.getConfig().getTypeConfigs().entrySet();
			updateViolationValues(typeConfigs, values);
			((MavenModuleSet) item).getReporters().add(violationMavenReporter);

			return true;
		}
		return false;
	}

	public boolean removeViolations(AbstractProject<?, ?> item) throws IOException {
		if (item instanceof FreeStyleProject) {
			DescribableList<Publisher, Descriptor<Publisher>> publishers = item
					.getPublishersList();
			Descriptor<Publisher> descriptor = Hudson.getInstance()
					.getDescriptor(ViolationsPublisher.class);
			Publisher publisher = publishers.get(descriptor);

			if (publisher != null) {
				item.getPublishersList().remove(descriptor);

				return true;
			}
			return false;
		}
		if (item instanceof MavenModuleSet) {
			DescribableList<MavenReporter, Descriptor<MavenReporter>> reporters = ((MavenModuleSet) item)
					.getReporters();

			for (MavenReporter mavenReporter : reporters) {
				if (mavenReporter instanceof ViolationsMavenReporter) {
					((MavenModuleSet) item).getReporters().remove(mavenReporter);

					return true;
				}
			}
		}
		return false;
	}

}
