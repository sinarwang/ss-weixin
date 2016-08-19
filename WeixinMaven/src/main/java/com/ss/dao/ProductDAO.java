package com.ss.dao;

import java.util.List;
import com.ss.adapter.DaoAdapter;
import com.ss.entity.Product;

public class ProductDAO extends DaoAdapter<Product> {
	private static ProductDAO productDAO = null;
	/**
	 * 获取ProductDAO的单例
	 * @return ProductDAO的单例
	 */
	public static ProductDAO getInstance() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}
		return productDAO;
	}
	
	public List<Product> getEntitys() {
		List<Product> products = super.getEntitys(Product.class);
		logger.error("----------------get products succeed!-----------------");
		return products;
	}
	/*@Override
	public boolean add(Product t) {
		boolean b = super.add(t);
		if(b) {
			logger.error("----------------Product saved!-----------------");		
		}
		return b;
	}*/
	
	@Override
	public boolean update(Product t) {
		if(super.update(t)) {
			logger.error("----------------Product modified succeed!-----------------");		
			return true;
		} else {
			logger.error("----------------Product modified failed!-----------------");		
			return false;
		}
	}
	@Override
	public Product getEntityByPK(Class<Product> c, Object pk) {
		Product product = super.getEntityByPK(c, pk);
		logger.error("----------------get Product by productId!-----------------");
		return product;
	}
	@Override
	public boolean delete(Product t) {
		if(super.delete(t)) {
			logger.error("----------------product delete succeed!-----------------");		
			return true;
		} else {
			logger.error("----------------Product delete failed!-----------------");		
			return false;
		}
	}
	@Override
	public void deleteEntitys(Object[] pks) {
		super.deleteEntitys(pks);
		logger.error("----------------products delete succeed!-----------------");		
	}
	/**
	 * 通过关键字查找产品。
	 * @param keyWords 关键字。
	 * @return 包含产品对象的List<Product>类的对象。
	 */
	public List<Product> queryProduct(String keyWords) {
		String sql = "where productName like '%" + keyWords + "%'";
		List<Product> products = super.getViewEntitys(Product.class, sql);
		logger.error("----------------queryProduct method succeed!-----------------");
		return products;
	}

}
