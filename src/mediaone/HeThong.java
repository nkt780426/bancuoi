package mediaone;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.QuanLyHoaDon;
import controller.QuanLyKhachHang;
import controller.QuanLyNhanVien;
import controller.QuanLySanPham;
import view.KhachHangView;
import view.QuanLyView;

public class HeThong {

	public static void luaChon() {
		System.out.println("1.Ban la khach hang:");
		System.out.println("2.Ban la quan ly:");
		System.out.println("3.Thoat he thong:");
	}

	public static void main(String[] args) throws IOException {
		// controller
		QuanLySanPham quanLySanPham = new QuanLySanPham();
		QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();
		QuanLyKhachHang quanLyKhachHang = new QuanLyKhachHang();
		QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
		// view
		KhachHangView khachHangView = new KhachHangView();
		QuanLyView quanLyView = new QuanLyView();
		int luaChon = 0;
		do {
			luaChon();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.print("Nhap lua chon: ");
			try {
				luaChon = sc.nextInt();
				switch (luaChon) {
				// Khach hang
				case 1:
					try {
						khachHangView.menuKhachHang();
						System.out.println("Nhap lua chon: ");
						int luaChonKhachHang = sc.nextInt();
						switch (luaChonKhachHang) {
						case 1:
							try {
								boolean a = true;
								while (a) {
									khachHangView.menuXemToanBoSanPhamChoKhachHang();
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
								e.printStackTrace();
							}
							break;
						case 2:
							quanLySanPham.xemThongTinSanPhamChoKhachHang();
							break;
						case 3:
							quanLyHoaDon.xuatHoaDon();
							break;
						case 4:
							quanLyKhachHang.capNhat();
							break;
						case 5:
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
							quanLyView.menuQuanLy();
							System.out.print("Nhap lua chon: ");
							int luaChonQuanLy = sc.nextInt();
							switch (luaChonQuanLy) {
							case 1:
								try {
									boolean j = true;
									while (j) {
										quanLyView.menuXemToanBoSanPham();
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
										quanLyView.menuThemSanPham();
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
								quanLyHoaDon.xuatToanBoHoaDonNhap();
								break;
							case 14:
								quanLyHoaDon.xuatToanBoHoaDonXuat();
								break;
							case 15:
								quanLyHoaDon.hienThiDoanhThuVaLai();
								break;
							case 16:
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
				case 3:
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
