package com.epoool.approvalepoool.Models;

import com.google.gson.annotations.SerializedName;

public class DeliveryRequest {

	@SerializedName("note")
	private String note;

	@SerializedName("add_by")
	private String addBy;

	@SerializedName("last_edited")
	private String lastEdited;

	@SerializedName("total_qty")
	private String totalQty;

	@SerializedName("line_no")
	private String lineNo;

	@SerializedName("id_item")
	private String idItem;

	@SerializedName("random_date")
	private String randomDate;

	@SerializedName("date_add")
	private String dateAdd;

	@SerializedName("deleted")
	private String deleted;

	@SerializedName("id_so")
	private Object idSo;

	@SerializedName("qty")
	private String qty;

	@SerializedName("no_so")
	private String noSo;

	@SerializedName("id")
	private String id;

	@SerializedName("edited_by")
	private String editedBy;

	@SerializedName("delivery_request_no")
	private String deliveryRequestNo;

	public String getNote(){
		return note;
	}

	public String getAddBy(){
		return addBy;
	}

	public String getLastEdited(){
		return lastEdited;
	}

	public String getTotalQty(){
		return totalQty;
	}

	public String getLineNo(){
		return lineNo;
	}

	public String getIdItem(){
		return idItem;
	}

	public String getRandomDate(){
		return randomDate;
	}

	public String getDateAdd(){
		return dateAdd;
	}

	public String getDeleted(){
		return deleted;
	}

	public Object getIdSo(){
		return idSo;
	}

	public String getQty(){
		return qty;
	}

	public String getNoSo(){
		return noSo;
	}

	public String getId(){
		return id;
	}

	public String getEditedBy(){
		return editedBy;
	}

	public String getDeliveryRequestNo(){
		return deliveryRequestNo;
	}
}