package com.ss.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.ss.entity.Product;
import com.ss.util.DB;
import com.ss.util.LoggerUtil;

public class ProductService {
	private static ProductService productService = null;
	private Logger logger = LoggerUtil.getInstance();
	
	public static ProductService getInstance() {
		if(productService == null) {
			productService = new ProductService();
		}
		return productService;
	}
	
	/**
	 * 通过关键字查找符合的产品
	 * @param keyWords 关键字
	 * @return 包含产品的List容器，若没有查找到符合产品，返回null。
	 */
	public List<Product> queryProduct(String keyWords) {
		List<Product> products = new ArrayList<Product>(); 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			Boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = DB.createStmt(conn);
			String sql = "select * from t_product where productName like '%" + keyWords + "%'";
			logger.error("--------------------" + sql + "---------------------");
			rs = DB.executeQuery(stmt, sql);
			while(rs.next()) {
				Product p = new Product();
				p.initFromRs(rs);
				products.add(p);
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return products;
	}
	/**
	 * 通过关键字查找符合的产品,返回对应商品要返回的字符串。
	 * @param keyWords 关键字
	 * @return
	 */
	/*public String queryProduct(String keyWords) {
		String products = ""; 
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getConn();
			//products = products + conn.toString();
			Boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			stmt = DB.createStmt(conn);
			String sql = "select * from t_product where productName like '%" + keyWords + "%'";
			//products = products + sql;
			rs = DB.executeQuery(stmt, sql);
			while(rs.next()) {
				Product p = new Product();
				p.initFromRs(rs);
				products = products + "产品名字：" + p.getProductName() + "\n" + "产品描述：" + p.getProductDes() + "\n" + "产品价格：" + p.getProductPrice() + "\n\n"; 
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		
		return products;
	}*/
}
