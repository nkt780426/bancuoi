package model;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class HoaDonXuat extends HoaDon {
	private String giaBan;

	public HoaDonXuat(String maKhachHang, String maSanPham, LocalDate ngayMua, double soLuong, String giaBan) {
		super(maKhachHang, maSanPham, ngayMua, soLuong);
		this.giaBan = giaBan;
	}

	public String getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(String giaBan) {
		this.giaBan = giaBan;
	}

	public String luuVaoData() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateFormat = this.getNgayMua().format(f);
		return this.getMaKhachHang() + "::" + this.getMaSanPham() + "::" + dateFormat + "::"
				+ String.format("%1f", this.getSoLuong()) + "::" + this.getGiaBan();
	}
}