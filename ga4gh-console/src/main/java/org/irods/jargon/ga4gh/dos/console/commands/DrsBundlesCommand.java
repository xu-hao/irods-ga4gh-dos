/**
 * 
 */
package org.irods.jargon.ga4gh.dos.console.commands;

import java.io.IOException;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.irods.jargon.core.connection.IRODSServerProperties;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.ga4gh.dos.console.context.DrsConsoleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class DrsBundlesCommand {

	@Autowired
	private DrsConsoleContext drsConsoleContext;

	private static final Logger log = LoggerFactory.getLogger(DrsBundlesCommand.class);

	/**
	 * 
	 */
	public DrsBundlesCommand() {
	}

	@ShellMethod("Initialize connection")
	public String iinit(@NotEmpty String host, @ShellOption(defaultValue = "1247") int port, @NotEmpty String zone,
			@NotEmpty String user, @NotEmpty String password, @ShellOption(defaultValue = "STANDARD") String auth) {
		log.info("connect()");

		try {
			IRODSServerProperties props = drsConsoleContext.init(host, zone, port, user, password, auth);
			log.debug("IRODSServerProperties:{}", props);
			return "Connected to " + zone + " as user: " + user;
		} catch (JargonException e) {
			log.error("error connecting", e);
			throw new JargonRuntimeException("error connecting", e);
		} finally {
			drsConsoleContext.getIrodsAccessObjectFactory().closeSessionAndEatExceptions();
		}

	}

	@ShellMethod("Print working directory in iRODS")
	public String ipwd() {
		log.info("ipwd");
		return drsConsoleContext.getCwd();
	}

	public Availability ipwdAvailability() {
		return drsConsoleContext.isInitd() ? Availability.available()
				: Availability.unavailable("you are not connected, please do the iinit command");
	}

	@ShellMethod("Change working directory in iRODS")
	public String icd(String directory) {
		log.info("icd");
		String wd = drsConsoleContext.getCwd();
		try {
			IRODSFile wdFile = drsConsoleContext.getIrodsFileFactory().instanceIRODSFile(wd, directory);
			if (wdFile.exists()) {
				log.debug("file exists");
				drsConsoleContext.setCwd(wdFile.getCanonicalPath());
				return drsConsoleContext.getCwd();
			} else {
				return "No file found at path";
			}
		} catch (JargonException | IOException e) {
			log.error("exception getting file:{}", e);
			throw new JargonRuntimeException("error getting file", e);
		} finally {
			drsConsoleContext.getIrodsAccessObjectFactory().closeSessionAndEatExceptions();
		}
	}

	public Availability icdAvailability() {
		return drsConsoleContext.isInitd() ? Availability.available()
				: Availability.unavailable("you are not connected, please do the iinit command");
	}

	@ShellMethod("List Bundles")
	public List<String> listBundles() {
		log.info("listBundles");

		return null;
	}

	public Availability listBundlesAvailability() {
		return drsConsoleContext.isInitd() ? Availability.available()
				: Availability.unavailable("you are not connected, please do the iinit command");
	}

	public DrsConsoleContext getDrsConsoleContext() {
		return drsConsoleContext;
	}

	public void setDrsConsoleContext(DrsConsoleContext drsConsoleContext) {
		this.drsConsoleContext = drsConsoleContext;
	}

}
