/**
 * 
 */
package org.irods.jargon.ga4gh.dos.console.commands;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DrsListBundlesCommand {

	private static final Logger log = LoggerFactory.getLogger(DrsListBundlesCommand.class);

	/**
	 * 
	 */
	public DrsListBundlesCommand() {
	}

	@ShellMethod("List Bundles")
	public List<String> listBundles() {
		log.info("listBundles");
		List<String> bundleList = new ArrayList<>();
		bundleList.add("foo");
		bundleList.add("bar");
		return bundleList;
	}

}
