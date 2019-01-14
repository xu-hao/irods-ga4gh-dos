/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.UUID;

import org.irods.jargon.core.checksum.ChecksumValue;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent;
import org.irods.jargon.datautils.visitor.HierComposite;
import org.irods.jargon.datautils.visitor.HierLeaf;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hierarchical visitor implementation that can compute a checksum of checksums
 * in a collection, and can cause a checksum to be computed at each leaf if not
 * currently available.
 * <p>
 * Note that the checksum is accumulated as a binary stream of iRODS checksum
 * values as byte arrays, not by a hex or base64 representation.
 * <p>
 * The contained {@code checksumAccumulator} is a {@link DigestOutputStream}
 * that needs to be closed and the checksum value needs to be extracted by the
 * calling process.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataBundleChecksumVisitor extends AbstractIrodsVisitorComponent {

	private final DigestOutputStream checksumAccumulator;
	private final DataObjectAO dataObjectAO;
	private final DataObjectChecksumUtilitiesAO dataObjectChecksumUtilitiesAO;
	private static Logger log = LoggerFactory.getLogger(DataBundleChecksumVisitor.class);

	public DataBundleChecksumVisitor(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			MessageDigest messageDigest) {

		super(irodsAccessObjectFactory, irodsAccount);

		if (messageDigest == null) {
			throw new IllegalArgumentException("null messageDigest");
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		checksumAccumulator = new DigestOutputStream(bos, messageDigest);
		try {
			dataObjectAO = this.getIrodsAccessObjectFactory().getDataObjectAO(getIrodsAccount());
			dataObjectChecksumUtilitiesAO = this.getIrodsAccessObjectFactory()
					.getDataObjectChecksumUtilitiesAO(getIrodsAccount());
		} catch (JargonException e) {
			log.error("unable to connect to iRODS", e);
			throw new JargonRuntimeException("unable to connect to iRODS", e);
		}
	}

	@Override
	public boolean visitEnter(HierComposite node) {
		return true;
	}

	@Override
	public boolean visitLeave(HierComposite node, boolean visitorEntered) {
		return true;
	}

	@Override
	public boolean visit(HierLeaf node) {
		log.info("visiting leaf:{}", node);
		// generate and add the id
		String guid = UUID.randomUUID().toString();
		log.debug("adding id:{}", guid);

		AvuData dataObjectAvu = ExplodedBundleMetadataUtils.createBundleDataObjectMarkerAvu(guid);
		try {
			dataObjectAO.addAVUMetadata(node.getAbsolutePath(), dataObjectAvu);
		} catch (JargonException e) {
			log.error("Unable to create bundle avu", e);
			throw new JargonRuntimeException("cannot add bundle AVU", e);
		}

		// compute a checksum and digest it if not done already
		try {
			IRODSFile nodeFile = this.getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
					.instanceIRODSFile(node.getAbsolutePath());
			ChecksumValue checksum = dataObjectChecksumUtilitiesAO.computeChecksumOnDataObject(nodeFile);
			checksumAccumulator.write(checksum.getBinaryChecksumValue());
		} catch (JargonException | IOException e) {
			log.error("unable to checksum a data object", e);
			throw new JargonRuntimeException("unable to checksum a data object", e);
		}

		return true;
	}

	public DigestOutputStream getChecksumAccumulator() {
		return checksumAccumulator;
	}

}
