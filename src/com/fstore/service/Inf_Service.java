
package com.fstore.service;

import java.util.List;

/**
 *
 * @author ThongHTPH43253
 */
public interface Inf_Service<M,K> {
    public int insert(M model);
    public int update(M model,K id);
    public int delete(K id);
    public List<M> selectAll();
    public M selectByID(K id);
}
