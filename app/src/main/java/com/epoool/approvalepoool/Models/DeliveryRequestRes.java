package com.epoool.approvalepoool.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DeliveryRequestRes {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DeliveryRequest> data;

	public String getPesan(){
		return pesan;
	}

	public int getCode(){
		return code;
	}

	public List<DeliveryRequest> getData(){
		return data;
	}
}