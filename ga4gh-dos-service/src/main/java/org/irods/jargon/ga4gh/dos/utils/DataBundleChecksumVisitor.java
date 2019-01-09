/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent;
import org.irods.jargon.datautils.visitor.HierComposite;
import org.irods.jargon.datautils.visitor.HierLeaf;

/**
 * Hierarchical visitor implementation that can compute a checksum of checksums
 * in a collection, and can cause a checksum to be computed at each leaf if not
 * currently available.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DataBundleChecksumVisitor extends AbstractIrodsVisitorComponent {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent#visitEnter(
	 * org.irods.jargon.datautils.visitor.HierComposite)
	 */
	@Override
	public boolean visitEnter(HierComposite node) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent#visitLeave(
	 * org.irods.jargon.datautils.visitor.HierComposite, boolean)
	 */
	@Override
	public boolean visitLeave(HierComposite node, boolean visitorEntered) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent#visit(org.
	 * irods.jargon.datautils.visitor.HierLeaf)
	 */
	@Override
	public boolean visit(HierLeaf node) {
		// TODO Auto-generated method stub
		return false;
	}

}
