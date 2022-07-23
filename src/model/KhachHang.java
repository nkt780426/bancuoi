package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class KhachHang extends Nguoi {
	private String maKh;

	public KhachHang(String ten, String gioiTinh, String diaChi, String sdt, LocalDate ngaySinh, String maKh) {
		super(ten, gioiTinh, diaChi, sdt, ngaySinh);
		this.maKh = maKh;
	}

	public String getMaKh() {
		return maKh;
	}

	public void setMaKh(String maKh) {
		this.maKh = maKh;
	}

	public String luuVaoData() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateFormat = super.getNgaySinh().format(f);
		return super.getTen() + "::" + super.getGioiTinh() + "::" + super.getDiaChi() + "::" + super.getSdt() + "::"
				+ dateFormat + "::" + this.maKh;
	}
}
