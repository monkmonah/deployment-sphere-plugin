package com.epam.grandhackathon.deployment.sphere.plugin.metadata.collector;

import static java.lang.String.format;

import org.joda.time.DateTime;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.scm.ChangeLogSet;
import lombok.extern.java.Log;

import com.epam.grandhackathon.deployment.sphere.plugin.metadata.model.BuildMetaData;

@Log
public class BuildVersionMetadataCollector<P extends AbstractProject<P, R>, R extends AbstractBuild<P, R>> {

    public BuildMetaData collect(final AbstractBuild<P, R> build) {
        int buildNumber = build.getNumber();

        String buildId = build.getId();
        ChangeLogSet<? extends hudson.scm.ChangeLogSet.Entry> changeSet = build.getChangeSet();
        log.fine(format("Resolved build number: %s, build id: %s, changeSet emptiness: %s", buildNumber, buildId, changeSet.isEmptySet()));
        BuildMetaData buildMetaData = new BuildMetaData();
        buildMetaData.setNumber(buildNumber);
        buildMetaData.setApplicationName("Some app name");
        buildMetaData.setJobName(build.getDisplayName());
        buildMetaData.setBuiltAt(new DateTime(build.due()));
        buildMetaData.setBuildVersion(String.format("0.0.%s", buildNumber));
        return buildMetaData;
    }
}
