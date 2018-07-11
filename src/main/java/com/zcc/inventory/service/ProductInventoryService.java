/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zcc.inventory.mapper.ProductInventoryMapper;
import com.zcc.inventory.model.ProductInventory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author c0z00k8
 *
 */
@Service
public class ProductInventoryService {

	@Autowired
	private ProductInventoryMapper productInventoryMapper;
	@Autowired
	private JedisPool jedisPool;
	private final String key_head="product_inventory_";
	
	public void add(ProductInventory productPrice) {
		productInventoryMapper.add(productPrice); 
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_inventory_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void update(ProductInventory productPrice) {
		productInventoryMapper.update(productPrice); 
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_inventory_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void delete(Long id) {
		ProductInventory productPrice = findById(id);
		productInventoryMapper.delete(id); 
		Jedis jedis = jedisPool.getResource();
		jedis.del("product_inventory_" + productPrice.getProductId());
	}

	public ProductInventory findById(Long id) {
		ProductInventory inventory = new ProductInventory();
		inventory.setId(id);
		return productInventoryMapper.findById(inventory);
	}  
	
	public ProductInventory findByProductId(Long productId) {
		ProductInventory inventory = new ProductInventory();
		inventory.setProductId(productId);
		
		Jedis jedis = jedisPool.getResource();
		String jsonObj=jedis.get(key_head+productId);
		if(null != jsonObj && !"".equals(jsonObj)){
			return JSONObject.parseObject(jsonObj, ProductInventory.class);
		}else{
			inventory = productInventoryMapper.findById(inventory);
			jedis.set(key_head+productId, JSONObject.toJSONString(inventory));
		}
		return inventory;
	}  
}
