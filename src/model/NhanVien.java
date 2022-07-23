package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

public class NhanVien extends Nguoi {
	private String maNV;
	private double luong;
	private int ngayNhanLuong;// bat loi neu ngay nhan luong ko tu 1-28

	public NhanVien(String ten, String gioiTinh, String diaChi, String sdt, LocalDate ngaySinh, String maNV,
			double luong, int ngayNhanLuong) {
		super(ten, gioiTinh, diaChi, sdt, ngaySinh);
		this.maNV = maNV;
		this.luong = luong;
		this.ngayNhanLuong = ngayNhanLuong;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public int getNgayNhanLuong() {
		return ngayNhanLuong;
	}

	public void setNgayNhanLuong(int ngayNhanLuong) {
		this.ngayNhanLuong = ngayNhanLuong;
	}

	public String luuVaoData() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateFormat = this.getNgaySinh().format(f);
		return this.getTen() + "::" + this.getGioiTinh() + "::" + this.getDiaChi() + "::" + this.getSdt() + "::"
				+ dateFormat + "::" + this.maNV + "::" + String.format("%1f", this.luong) + "::"
				+ String.format("%1d", this.ngayNhanLuong);
	}
}