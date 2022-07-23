package model;

public class Sach extends SanPham {
	private String tacGia;
	private String nhaXuatBan;

	public Sach(String maSp, String tenSp, int namPhatHanh, int soLuong, double giaBuon, double giaBan, String tacGia,
			String nhaXuatBan) {
		super(maSp, tenSp, namPhatHanh, soLuong, giaBuon, giaBan);
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public String luuVaoData() {
		return this.getMaSp() + "::" + this.getTenSp() + "::" + String.format("%1d", this.getNamPhatHanh()) + "::"
				+ String.format("%1d", this.getSoLuong()) + "::" + String.format("%1f", this.getGiaBuon()) + "::"
				+ String.format("%1f", this.getGiaBan()) + "::" + this.tacGia + "::" + this.nhaXuatBan;
	}
}