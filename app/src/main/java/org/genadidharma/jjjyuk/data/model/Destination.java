package org.genadidharma.jjjyuk.data.model;

import com.google.gson.annotations.SerializedName;

public class Destination {

	@SerializedName("jam_tutup")
	private String jamTutup;

	@SerializedName("harga_tiket")
	private String hargaTiket;

	@SerializedName("rating")
	private double rating;

	@SerializedName("nama_wisata")
	private String namaWisata;

	@SerializedName("protokol")
	private String protokol;

	@SerializedName("video")
	private String video;

	@SerializedName("long")
	private double jsonMemberLong;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("jam_buka")
	private String jamBuka;

	@SerializedName("ulasan")
	private int ulasan;

	@SerializedName("tempat")
	private String tempat;

	@SerializedName("foto")
	private String foto;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("id")
	private String id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("lat")
	private double lat;

	@SerializedName("status")
	private String status;

	@SerializedName("jarak")
	private String jarak;

	public String getJamTutup(){
		return jamTutup;
	}

	public String getHargaTiket(){
		return hargaTiket;
	}

	public double getRating(){
		return rating;
	}

	public String getNamaWisata(){
		return namaWisata;
	}

	public String getProtokol(){
		return protokol;
	}

	public String getVideo(){
		return video;
	}

	public double getJsonMemberLong(){
		return jsonMemberLong;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getJamBuka(){
		return jamBuka;
	}

	public int getUlasan(){
		return ulasan;
	}

	public String getTempat(){
		return tempat;
	}

	public String getFoto(){
		return foto;
	}

	public String getJenis(){
		return jenis;
	}

	public String getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public double getLat(){
		return lat;
	}

	public String getStatus(){
		return status;
	}

	public String getJarak(){
		return jarak;
	}
}