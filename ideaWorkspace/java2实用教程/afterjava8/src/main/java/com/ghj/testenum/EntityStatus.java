package com.ghj.testenum;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */

public enum EntityStatus {

    /**
     * Unchanged
     */
    Unchanged,
    /**
     * Insert
     */
    Insert,
    /**
     * Update
     */
    Update,
    /**
     * Delete
     */
    Delete,
    /**
     * LOGICDELETE
     */
    LOGICDELETE;

    EntityStatus() {
    }

    public static EntityStatus getEntityStatus(String value) {
        if (value == null) {
            return null;
        }
        for (EntityStatus entityStatus : EntityStatus.values()) {
            if (value.equals(entityStatus.name())) {
                return entityStatus;
            }
        }
        return null;
    }
}