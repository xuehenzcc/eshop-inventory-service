/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.inventory.mapper;

import com.zcc.inventory.model.ProductInventory;

/**
 * @author c0z00k8
 *
 */
public interface ProductInventoryMapper {

	public void add(ProductInventory productInventory);
	
	public void update(ProductInventory productInventory);
	
	public void delete(Long id);
	
	public ProductInventory findById(ProductInventory inventory);
	
}
