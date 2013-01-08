package org.uganda.constitution.model;

/**
 * enumerates the status of a record
 * 
 * @author ctumwebaze
 * 
 */
public enum RecordStatus {

    /**
     * indicates the the record is actively used
     */
    ACTIVE,

    /**
     * indicates that the record is deleted from the system. We never delete any
     * records from the system, we just mark them as deleted so that we have
     * historical data
     */
    DELETED
}
