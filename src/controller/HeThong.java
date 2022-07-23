package controller;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.QuanLyHoaDon;
import model.QuanLyKhachHang;
import model.QuanLyNhanVien;
import model.QuanLySanPham;

public class HeThong {

	public static void luaChon() {
		System.out.println("1.Ban la khach hang:");
		System.out.println("2.Ban la quan ly:");
		System.out.println("3.Thoat he thong:");
	}

	public static void menuKhachHang() {
		// Quan ly san pham
		System.out.println("1.Xem toan bo san pham:");
		System.out.println("2.Xem thong tin san pham theo ma:");
		// Quan ly khach hang+quan ly hoa don
		System.out.println("3.Mua hang:");
		System.out.println("4.Cap nhat thong tin:");
		// luc mua goi ham them khach hang
		// sau do hoi so loai hang muon mua va goi n lan hàm mua hàng
		System.out.println("5.Quay lai:");
	}

	public static void menuQuanLy() {
		// Quan ly san pham
		System.out.println("1.Xem toan bo san pham: ");
		System.out.println("2.Xem thong tin san pham theo ma: ");
		System.out.println("3.Cap nhat thong tin san pham:");
		System.out.println("4.Them san pham moi: ");
		System.out.println("5.Xoa san pham");
		// Quan ly khach hang
		System.out.println("6.Xem toan bo khach hang: ");
		System.out.println("7.Xem thong tin khach hang theo ma:");
		// Quan ly nhan vien
		System.out.println("8.Xem toan bo nhan vien: ");
		System.out.println("9.Xem thong tin nhan vien theo ma: ");
		System.out.println("10.Cap nhat thong tin nhan vien");
		System.out.println("11.Them nhan vien: ");
		System.out.println("12.Xoa nhan vien: ");
		System.out.println("13.Xem doanh thu va loi nhuan: ");
		System.out.println("14.Quay lai:");
	}

	public static void menuXemToanBoSanPham() {
		System.out.println("1.Xem toan bo sach: ");
		System.out.println("2.Xem toan bo dia phim: ");
		System.out.println("3.Xem toan bo dia nhac: ");
		System.out.println("4.Quay lai.");
	}

	public static void menuThemSanPham() {
		System.out.println("1.Them sach: ");
		System.out.println("2.Them dia phim: ");
		System.out.println("3.Them dia nhac: ");
		System.out.println("4.Quay lai: ");
	}

	public static void menuXemToanBoSanPhamChoKhachHang() {
		System.out.println("1.Xem toan bo sach cho khach hang: ");
		System.out.println("2.Xem toan bo dia phim cho khach hang: ");
		System.out.println("3.Xem toan bo dia nhac cho khach hang: ");
		System.out.println("4.Quay lai.");
	}

	public static void main(String[] args) throws IOException {
		QuanLySanPham quanLySanPham = new QuanLySanPham();
		QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
		QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang();
		QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
		// doc toan bo dl tu phai vao cac mang
		int luaChon = 0;
		do {
			luaChon();
			Scanner sc = new Scanner(System.in);
			System.out.print("Nhap lua chon: ");
			try {
				luaChon = sc.nextInt();
				switch (luaChon) {
				// Khach hang
				case 1:
					try {
						menuKhachHang();
						System.out.println("Nhap lua chon: ");
						int luaChonKhachHang = sc.nextInt();
						switch (luaChonKhachHang) {
						case 1:
							try {
								boolean a = true;
								while (a) {
									menuXemToanBoSanPhamChoKhachHang();
									System.out.print("Nhap lua chon: ");
									int luaChonXemToanBoChoKhachHang = sc.nextInt();
									switch (luaChonXemToanBoChoKhachHang) {
									case 1:
										quanLySanPham.xemToanBoSachChoKhachHang();
										break;
									case 2:
										quanLySanPham.xemToanBoDiaPhimChoKhachHang();
										break;
									case 3:
										quanLySanPham.xemToanBoDiaNhacChoKhachHang();
										break;
									case 4:
										a = false;
										break;
									default:
										System.out.println("Ban da nhap sai xin vui long nhap lai: ");
										break;
									}
								}
							} catch (InputMismatchException e) {
								System.out.println("Ban da nhap sai xin vui long nhap lai: ");
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
							break;
						case 2:
							quanLySanPham.xemThongTinSanPhamChoKhachHang();
							break;
						case 3:
							quanLyHoaDon.xuatHoaDon();
							;
							break;
						case 4:
							break;
						default:
							System.out.println("Ban da nhap sai xin vui long nhap lai:");
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println("Ban da nhap sai xin vui long nhap lai!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				// Quan ly
				case 2:
					try {
						boolean i = true;
						while (i) {
							menuQuanLy();
							System.out.print("Nhap lua chon: ");
							int luaChonQuanLy = sc.nextInt();
							switch (luaChonQuanLy) {
							case 1:
								try {
									boolean j = true;
									while (j) {
										menuXemToanBoSanPham();
										System.out.print("Nhap lua chon: ");
										int luaChonHienThi = sc.nextInt();
										switch (luaChonHienThi) {
										case 1:
											quanLySanPham.xemToanBoSach();
											break;
										case 2:
											quanLySanPham.xemToanBoDiaPhim();
											break;
										case 3:
											quanLySanPham.xemToanBoDiaNhac();
											break;
										case 4:
											j = false;
											break;
										default:
											System.out.println("Ban da nhap sai xin vui long nhap lai: ");
											break;
										}
									}
								} catch (InputMismatchException e) {
									System.out.println("Ban da nhap sai xin vui long nhap lai: ");
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								break;
							case 2:
								quanLySanPham.xemThongTinSanPhamTheoMa();
								break;
							case 3:
								quanLySanPham.capNhatThongTinSanPham();
								break;
							case 4:
								try {
									boolean k = true;
									while (k) {
										menuThemSanPham();
										System.out.print("Nhap lua chon: ");
										int luaChonThem = sc.nextInt();
										switch (luaChonThem) {
										case 1:
											quanLySanPham.themSach();
											break;
										case 2:
											quanLySanPham.themDiaPhim();
											break;
										case 3:
											quanLySanPham.themDiaNhac();
											break;
										case 4:
											k = false;
											break;
										default:
											System.out.println("Ban da nhap sai xin vui long nhap lai: ");
											break;
										}
									}
								} catch (InputMismatchException e) {
									System.out.println("Ban da nhap sai xin vui long nhap lai: ");
								} catch (Exception e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								break;
							case 5:
								quanLySanPham.xoaSanPham();
								break;
							case 6:
								quanLyKhachHang.xemToanBo();
								break;
							case 7:
								quanLyKhachHang.xemKhachHang();
								break;
							case 8:
								quanLyNhanVien.xemToanBo();
								break;
							case 9:
								quanLyNhanVien.xemNhanVien();
								break;
							case 10:
								quanLyNhanVien.capNhat();
								break;
							case 11:
								quanLyNhanVien.themNhanVien();
								break;
							case 12:
								quanLyNhanVien.xoaNhanVien();
								break;
							case 13:
								break;
							case 14:
								i = false;
								break;
							default:
								System.out.println("Ban da nhap sai xin vui long nhap lai:");
								break;
							}
						}
					} catch (InputMismatchException e) {
						System.out.println("Ban da nhap sai xin vui long nhap lai!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Ban da nhap sai xin vui long nhap lai!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Ban da nhap sai xin vui long nhap lai!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (luaChon != 3);
		// luu toan bo du lieu vao file
		System.out.println("Cam on ban da su dung he thong!");
	}
}
