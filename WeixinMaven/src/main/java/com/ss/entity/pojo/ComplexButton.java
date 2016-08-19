package com.ss.entity.pojo;
/**
 * 一级Button的pojo类。
 * @author Administrator
 *
 */
public class ComplexButton extends Button {
	private CommonButton[] sub_button ;

	public CommonButton[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(CommonButton[] sub_button) {
		this.sub_button = sub_button;
	}
}
