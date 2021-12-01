package org.genadidharma.jjjyuk.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Destination")
public class Destination implements Serializable {

	@PrimaryKey
	@NonNull
	@ColumnInfo(name="id")
	@SerializedName("id")
	private String id;

	@ColumnInfo(name="harga_tiket")
	@SerializedName("harga_tiket")
	private String hargaTiket;

	@ColumnInfo(name="rating")
	@SerializedName("rating")
	private double rating;

	@ColumnInfo(name="nama_wisata")
	@SerializedName("nama_wisata")
	private String namaWisata;

	@ColumnInfo(name="protokol")
	@SerializedName("protokol")
	private String protokol;

	@ColumnInfo(name="video")
	@SerializedName("video")
	private String video;

	@ColumnInfo(name="alamat")
	@SerializedName("alamat")
	private String alamat;

	@ColumnInfo(name="jam_buka")
	@SerializedName("jam_buka")
	private String jamBuka;

	@ColumnInfo(name="jam_tutup")
	@SerializedName("jam_tutup")
	private String jamTutup;

	@ColumnInfo(name="ulasan")
	@SerializedName("ulasan")
	private int ulasan;

	@ColumnInfo(name="tempat")
	@SerializedName("tempat")
	private String tempat;

	@ColumnInfo(name="foto")
	@SerializedName("foto")
	private String foto;

	@ColumnInfo(name="jenis")
	@SerializedName("jenis")
	private String jenis;

	@ColumnInfo(name="deskripsi")
	@SerializedName("deskripsi")
	private String deskripsi;

	@ColumnInfo(name="lat")
	@SerializedName("lat")
	private double lat;

	@ColumnInfo(name="long")
	@SerializedName("long")
	private double jsonMemberLong;

	@ColumnInfo(name="status")
	@SerializedName("status")
	private String status;

	@ColumnInfo(name="jarak")
	@SerializedName("jarak")
	private String jarak;

	@SerializedName("favorite")
	private boolean favorite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHargaTiket() {
		return hargaTiket;
	}

	public void setHargaTiket(String hargaTiket) {
		this.hargaTiket = hargaTiket;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getNamaWisata() {
		return namaWisata;
	}

	public void setNamaWisata(String namaWisata) {
		this.namaWisata = namaWisata;
	}

	public String getProtokol() {
		return protokol;
	}

	public void setProtokol(String protokol) {
		this.protokol = protokol;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getJamBuka() {
		return jamBuka;
	}

	public void setJamBuka(String jamBuka) {
		this.jamBuka = jamBuka;
	}

	public String getJamTutup() {
		return jamTutup;
	}

	public void setJamTutup(String jamTutup) {
		this.jamTutup = jamTutup;
	}

	public int getUlasan() {
		return ulasan;
	}

	public void setUlasan(int ulasan) {
		this.ulasan = ulasan;
	}

	public String getTempat() {
		return tempat;
	}

	public void setTempat(String tempat) {
		this.tempat = tempat;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getJsonMemberLong() {
		return jsonMemberLong;
	}

	public void setJsonMemberLong(double jsonMemberLong) {
		this.jsonMemberLong = jsonMemberLong;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJarak() {
		return jarak;
	}

	public void setJarak(String jarak) {
		this.jarak = jarak;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}