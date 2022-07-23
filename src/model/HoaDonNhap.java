package model;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class HoaDonNhap extends HoaDon {
	private String giaBuon;

	public HoaDonNhap(String maKhachHang, String maSanPham, LocalDate ngayMua, double soLuong, String giaBuon) {
		super(maKhachHang, maSanPham, ngayMua, soLuong);
		this.giaBuon = giaBuon;
	}

	public String getGiaBuon() {
		return giaBuon;
	}

	public void setGiaBuon(String giaBuon) {
		this.giaBuon = giaBuon;
	}

	public String luuVaoData() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateFormat = this.getNgayMua().format(f);
		return this.getMaKhachHang() + "::" + this.getMaSanPham() + "::" + dateFormat + "::"
				+ String.format("%1f", this.getSoLuong()) + "::" + this.getGiaBuon();
	}
}