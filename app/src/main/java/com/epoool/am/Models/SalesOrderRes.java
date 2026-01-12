package com.epoool.am.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SalesOrderRes {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<SalesOrder> data;

	@SerializedName("plant")
	private List<Plant> plant;

	public String getPesan(){
		return pesan;
	}

	public int getCode(){
		return code;
	}

	public List<SalesOrder> getData(){
		return data;
	}

	public List<Plant> getPlant(){
		return plant;
	}
}