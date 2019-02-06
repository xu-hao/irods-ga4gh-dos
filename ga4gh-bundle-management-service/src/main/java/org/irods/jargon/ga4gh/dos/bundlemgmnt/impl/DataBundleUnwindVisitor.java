/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.query.AVUQueryElement;
import org.irods.jargon.core.query.AVUQueryElement.AVUQueryPart;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent;
import org.irods.jargon.datautils.visitor.HierComposite;
import org.irods.jargon.datautils.visitor.HierLeaf;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hierarchical visitor implementation that removes relevant metadata from a
 * data bundle, leaving the base collection in place.
 * 
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataBundleUnwindVisitor extends AbstractIrodsVisitorComponent {

	private final DataObjectAO dataObjectAO;
	private final DataObjectChecksumUtilitiesAO dataObjectChecksumUtilitiesAO;
	private static Logger log = LoggerFactory.getLogger(DataBundleUnwindVisitor.class);

	public DataBundleUnwindVisitor(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {

		super(irodsAccessObjectFactory, irodsAccount);

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

		/*
		 * query ga4gh metadata values and then delete
		 */

		List<AVUQueryElement> avuQuery = new ArrayList<AVUQueryElement>();

		try {
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.ATTRIBUTE, QueryConditionOperators.EQUAL,
					ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE));

			List<MetaDataAndDomainData> metadata = dataObjectAO.findMetadataValuesForDataObjectUsingAVUQuery(avuQuery,
					node.getAbsolutePath());

			if (!metadata.isEmpty()) {
				log.info("removing metadata:{}", metadata.get(0));
				AvuData data = AvuData.instance(metadata.get(0).getAvuAttribute(), metadata.get(0).getAvuValue(),
						metadata.get(0).getAvuUnit());
				dataObjectAO.deleteAVUMetadata(node.getAbsolutePath(), data);
			}

		} catch (JargonQueryException | JargonException e) {
			log.error("Error creating query for bundles", e);
			throw new JargonRuntimeException("bundle query error", e);
		}

		return true;
	}

}
