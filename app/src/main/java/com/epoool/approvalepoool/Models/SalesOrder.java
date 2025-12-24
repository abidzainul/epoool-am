package com.epoool.approvalepoool.Models;

import com.google.gson.annotations.SerializedName;

public class SalesOrder {

	@SerializedName("code_district")
	private String codeDistrict;

	@SerializedName("note")
	private Object note;

	@SerializedName("code_distributor")
	private String codeDistributor;

	@SerializedName("code_material")
	private String codeMaterial;

	@SerializedName("real_tahap_1")
	private Object realTahap1;

	@SerializedName("real_tahap_2")
	private Object realTahap2;

	@SerializedName("line")
	private String line;

	@SerializedName("release")
	private String release;

	@SerializedName("jatah")
	private int jatah;

	@SerializedName("pallet")
	private String pallet;

	@SerializedName("total_released_qty")
	private String totalReleasedQty;

	@SerializedName("province_code")
	private Object provinceCode;

	@SerializedName("id_gudang")
	private String idGudang;

	@SerializedName("price_list")
	private String priceList;

	@SerializedName("release_total_do")
	private String releaseTotalDo;

	@SerializedName("province")
	private Object province;

	@SerializedName("no_expeditur")
	private Object noExpeditur;

	@SerializedName("id_originator")
	private String idOriginator;

	@SerializedName("no_so")
	private String noSo;

	@SerializedName("name_ship_to")
	private String nameShipTo;

	@SerializedName("nama_expeditur")
	private Object namaExpeditur;

	@SerializedName("address_ship_to")
	private String addressShipTo;

	@SerializedName("id_expeditur")
	private Object idExpeditur;

	@SerializedName("distributor")
	private String distributor;

	@SerializedName("total_requested_qty")
	private String totalRequestedQty;

	@SerializedName("alasan")
	private Object alasan;

	@SerializedName("delivery_date")
	private String deliveryDate;

	@SerializedName("unit")
	private String unit;

	@SerializedName("material")
	private String material;

	@SerializedName("id_so")
	private String idSo;

	@SerializedName("incoterm")
	private String incoterm;

	@SerializedName("plant")
	private String plant;

	@SerializedName("district")
	private String district;

	@SerializedName("type_truck")
	private String typeTruck;

	@SerializedName("id_pickuppoint")
	private String idPickuppoint;

	@SerializedName("nama_gudang")
	private String namaGudang;

	@SerializedName("persen")
	private String persen;

	@SerializedName("ship_to")
	private String shipTo;

	public String getCodeDistrict(){
		return codeDistrict;
	}

	public Object getNote(){
		return note;
	}

	public String getCodeDistributor(){
		return codeDistributor;
	}

	public String getCodeMaterial(){
		return codeMaterial;
	}

	public Object getRealTahap1(){
		return realTahap1;
	}

	public Object getRealTahap2(){
		return realTahap2;
	}

	public String getLine(){
		return line;
	}

	public String getRelease(){
		return release;
	}

	public int getJatah(){
		return jatah;
	}

	public String getPallet(){
		return pallet;
	}

	public String getTotalReleasedQty(){
		return totalReleasedQty;
	}

	public Object getProvinceCode(){
		return provinceCode;
	}

	public String getIdGudang(){
		return idGudang;
	}

	public String getPriceList(){
		return priceList;
	}

	public String getReleaseTotalDo(){
		return releaseTotalDo;
	}

	public Object getProvince(){
		return province;
	}

	public Object getNoExpeditur(){
		return noExpeditur;
	}

	public String getIdOriginator(){
		return idOriginator;
	}

	public String getNoSo(){
		return noSo;
	}

	public String getNameShipTo(){
		return nameShipTo;
	}

	public Object getNamaExpeditur(){
		return namaExpeditur;
	}

	public String getAddressShipTo(){
		return addressShipTo;
	}

	public Object getIdExpeditur(){
		return idExpeditur;
	}

	public String getDistributor(){
		return distributor;
	}

	public String getTotalRequestedQty(){
		return totalRequestedQty;
	}

	public Object getAlasan(){
		return alasan;
	}

	public String getDeliveryDate(){
		return deliveryDate;
	}

	public String getUnit(){
		return unit;
	}

	public String getMaterial(){
		return material;
	}

	public String getIdSo(){
		return idSo;
	}

	public String getIncoterm(){
		return incoterm;
	}

	public String getPlant(){
		return plant;
	}

	public String getDistrict(){
		return district;
	}

	public String getTypeTruck(){
		return typeTruck;
	}

	public String getIdPickuppoint(){
		return idPickuppoint;
	}

	public String getNamaGudang(){
		return namaGudang;
	}

	public String getPersen(){
		return persen;
	}

	public String getShipTo(){
		return shipTo;
	}
}