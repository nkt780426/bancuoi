package model;

public class DiaPhim extends SanPham {
	private String daoDien;
	private String dienVien;

	public DiaPhim(String maSp, String tenSp, int namPhatHanh, int soLuong, double giaBuon, double giaBan,
			String daoDien, String dienVien) {
		super(maSp, tenSp, namPhatHanh, soLuong, giaBuon, giaBan);
		this.daoDien = daoDien;
		this.dienVien = dienVien;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public String getDienVien() {
		return dienVien;
	}

	public void setDienVien(String dienVien) {
		this.dienVien = dienVien;
	}

	public String luuVaoData() {
		return this.getMaSp() + "::" + this.getTenSp() + "::" + String.format("%1d", this.getNamPhatHanh()) + "::"
				+ String.format("%1d", this.getSoLuong()) + "::" + String.format("%1f", this.getGiaBuon()) + "::"
				+ String.format("%1f", this.getGiaBan()) + "::" + this.daoDien + "::"
				+ this.dienVien;
	}

}
