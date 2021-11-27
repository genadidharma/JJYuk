package org.genadidharma.jjjyuk.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "table_name")
public class Dest implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="destid")
    public int destid;

    @ColumnInfo(name="jenis")
    public String jenis;

    @ColumnInfo(name="foto")
    public String foto;

    @ColumnInfo(name="video")
    public String video;

    @ColumnInfo(name="protokol")
    public String protokol;

    @ColumnInfo(name="nama_wisata")
    public String nama_wisata;

    @ColumnInfo(name="deskripsi")
    public String deskripsi;

    @ColumnInfo(name="jam_buka")
    public String jam_buka;

    @ColumnInfo(name="jam_tutup")
    public String jam_tutup;

    @ColumnInfo(name="alamat")
    public String alamat;

    @ColumnInfo(name="jarak")
    public String jarak;

    @ColumnInfo(name="tempat")
    public String tempat;

    @ColumnInfo(name="harga_tiket")
    public String harga_tiket;

    @ColumnInfo(name="rating")
    public double rating;

    @ColumnInfo(name="ulasan")
    public int ulasan;

    @ColumnInfo(name="status")
    public String status;

    public int getDestid() {
        return destid;
    }

    public void setDestid(int destid) {
        this.destid = destid;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getProtokol() {
        return protokol;
    }

    public void setProtokol(String protokol) {
        this.protokol = protokol;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJam_buka() {
        return jam_buka;
    }

    public void setJam_buka(String jam_buka) {
        this.jam_buka = jam_buka;
    }

    public String getJam_tutup() {
        return jam_tutup;
    }

    public void setJam_tutup(String jam_tutup) {
        this.jam_tutup = jam_tutup;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getHarga_tiket() {
        return harga_tiket;
    }

    public void setHarga_tiket(String harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUlasan() {
        return ulasan;
    }

    public void setUlasan(int ulasan) {
        this.ulasan = ulasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @ColumnInfo(name="lat")
    public double latitude;

    @ColumnInfo(name="long")
    public double longitude;

    public Dest(){

    }


}
