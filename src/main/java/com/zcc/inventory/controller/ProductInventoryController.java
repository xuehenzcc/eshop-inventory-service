/**
 * 版权：zcc
 * 作者：c0z00k8
 * @data 2018年7月5日
 */
package com.zcc.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.inventory.model.ProductInventory;
import com.zcc.inventory.service.ProductInventoryService;

/**
 * @author c0z00k8
 *
 */
@Controller
@RequestMapping("/product-inventory")
public class ProductInventoryController {

	@Autowired
	private ProductInventoryService productInventoryService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(ProductInventory productPrice) {
		try {
			productInventoryService.add(productPrice);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(ProductInventory productPrice) {
		try {
			productInventoryService.update(productPrice); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete") 
	@ResponseBody
	public String delete(Long id) {
		try {
			productInventoryService.delete(id); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public ProductInventory findById(Long id){
		try {
			return productInventoryService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductInventory();
	}
	
	@RequestMapping("/findByProductId") 
	@ResponseBody
	public ProductInventory findByProductId(Long productId){
		try {
			return productInventoryService.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new ProductInventory();
	}
	
	
}
