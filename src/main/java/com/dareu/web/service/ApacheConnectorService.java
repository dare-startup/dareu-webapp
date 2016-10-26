package com.dareu.web.service;

import com.dareu.web.conn.DareOperation;

/**
 *
 * @author jose.rubalcaba
 */
public interface ApacheConnectorService {
    public <T> T requestApiOperation(DareOperation operation, Class<?> type);
}
