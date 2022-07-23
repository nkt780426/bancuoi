package model;

import java.time.LocalDate;

public class HoaDon {
	private String maKhachHang;
	private String maSanPham;
	private LocalDate ngayMua;
	private double soLuong;

	public HoaDon(String maKhachHang, String maSanPham, LocalDate ngayMua, double soLuong) {
		super();
		this.maKhachHang = maKhachHang;
		this.maSanPham = maSanPham;
		this.ngayMua = ngayMua;
		this.soLuong = soLuong;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public LocalDate getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(LocalDate ngayMua) {
		this.ngayMua = ngayMua;
	}

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}

}