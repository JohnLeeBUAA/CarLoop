package com.maker.dao;

import java.io.Serializable;
import java.util.ArrayList;

public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int id;
	private int eid;
	private int uid;
	private ArrayList<String> goods;
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		Photo.id = id;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Photo(){
		setGoods(new ArrayList<String>());
	}
	public ArrayList<String> getGoods() {
		return goods;
	}
	public void setGoods(ArrayList<String> goods) {
		this.goods = goods;
	}
	
}