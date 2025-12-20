package com.epoool.approvalepoool.Models;

import com.google.gson.annotations.SerializedName;

public class Plant {

	@SerializedName("plant")
	private String plant;

	@SerializedName("nama_gudang")
	private String namaGudang;

	public String getPlant(){
		return plant;
	}

	public String getNamaGudang(){
		return namaGudang;
	}
}