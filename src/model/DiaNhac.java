package model;

public class DiaNhac extends SanPham {
	private String caSy;
	private String danhSachBaiHat;

	public DiaNhac(String maSp, String tenSp, int namPhatHanh, int soLuong, double giaBuon, double giaBan,
			String caSy, String danhsachBaiHat) {
		super(maSp, tenSp, namPhatHanh, soLuong, giaBuon, giaBan);
		this.caSy = caSy;
		this.danhSachBaiHat = danhsachBaiHat;
	}

	public String getCaSy() {
		return caSy;
	}


	public void setCaSy(String caSy) {
		this.caSy = caSy;
	}


	public String getDanhSachBaiHat() {
		return danhSachBaiHat;
	}


	public void setDanhSachBaiHat(String danhSachBaiHat) {
		this.danhSachBaiHat = danhSachBaiHat;
	}


	public String luuVaoData() {
		return this.getMaSp() + "::" + this.getTenSp() + "::" + String.format("%1d", this.getNamPhatHanh()) + "::"
				+ String.format("%1d", this.getSoLuong()) + "::" + String.format("%1f", this.getGiaBuon()) + "::"
				+ String.format("%1f", this.getGiaBan()) + "::" + this.caSy + "::"
				+ this.danhSachBaiHat;
	}
}