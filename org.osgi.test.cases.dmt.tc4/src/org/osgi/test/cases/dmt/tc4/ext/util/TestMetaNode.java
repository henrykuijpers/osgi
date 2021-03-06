package org.osgi.test.cases.dmt.tc4.ext.util;

import org.osgi.service.dmt.DmtData;
import org.osgi.service.dmt.MetaNode;

public class TestMetaNode implements MetaNode {

    private final boolean isLeaf;

    public TestMetaNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
	public int getScope() {
        return MetaNode.DYNAMIC;
    }

    @Override
	public boolean can(int operation) {
        return true;
    }

    @Override
	public String getDescription() {
        return null;
    }

    @Override
	public boolean isLeaf() {
        return isLeaf;
    }

    @Override
	public boolean isZeroOccurrenceAllowed() {
        return true;
    }

    @Override
	public int getMaxOccurrence() {
        return 0;
    }

    @Override
	public String[] getRawFormatNames() {
        return null;
    }

    @Override
	public boolean isValidName(String name) {
        return true;
    }

    @Override
	public String[] getValidNames() {
        return null;
    }

    @Override
	public int getFormat() {
        return 0;
    }

    @Override
	public String[] getMimeTypes() {
        return null;
    }

    @Override
	public DmtData getDefault() {
        return null;
    }

    @Override
	public double getMin() {
        return 0;
    }

    @Override
	public double getMax() {
        return 0;
    }

    @Override
	public boolean isValidValue(DmtData value) {
        return true;
    }

    @Override
	public DmtData[] getValidValues() {
        return null;
    }

    @Override
	public String[] getExtensionPropertyKeys() {
        return null;
    }

    @Override
	public Object getExtensionProperty(String key) {
        return null;
    }
}
