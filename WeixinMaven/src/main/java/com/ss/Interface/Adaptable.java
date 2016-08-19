package com.ss.Interface;

import java.util.List;
/**
 * 定义接口，表示可修改的。
 * @author Administrator。
 * @param <E>
 *
 */
public interface Adaptable<T> {
	/**
	 * 得到装有实体类的List容器。
	 * @param c Class<T>类的对象。
	 * @return 装有实体类E的List<E>的对象。
	 */
	public List<T> getEntitys(Class<T> c);
	/**
	 * 添加实体类操作。
	 * @param t 实体类的对象。
	 * @return 若添加成功，返回true；否则返回false。
	 */
	public boolean add(T t);
	/**
	 * 更新实体类操作。
	 * @param t 实体类的对象。
	 * @return 若更新成功，返回true；若更新失败返回false。
	 */
	public boolean update(T t);
	/**
	 * 通过主键获得实体类（规定实体类的主键要放在第一个位置，且暂时不支持主键对象包含多个属性，即不支持联合主键）。
	 * @param pk Object类型的主键对象。
	 * @param c Class<T>类型。
	 * @return 获得的实体类对象。若没有改该对象，返回null。
	 */
	public T getEntityByPK(Class<T> c, Object pk);
	/**
	 * 删除实体类操作。
	 * @param t 实体类的对象。
	 * @return 若删除成功，返回true；若删除失败返回false。
	 */
	public boolean delete(T t);
	/**
	 * 批量删除操作。
	 * @param pks Object[]类型，装有主键的数列。
	 */
	public void deleteEntitys(Object[] pks);
}
