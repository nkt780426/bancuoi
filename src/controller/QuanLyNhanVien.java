package controller;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.NhanVien;

public class QuanLyNhanVien {

	private HashMap<String, NhanVien> dsNhanVien = new HashMap<String, NhanVien>();
	public String header = String.format("%10s%25s%25s%25s%25s%25s%25s%25s", "Ma", "Ten", "Gioi Tinh", "Ngay Sinh",
			"SDT", "Dia chi", "Luong", "Ngay nhan Luong");
	Scanner sc = new Scanner(System.in);

	public NhanVien castToNhanVien(String[] line) {
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			NhanVien nv = new NhanVien(line[0], line[1], line[2], line[3], LocalDate.parse(line[4], f), line[5],
					Double.parseDouble(line[6]), Integer.parseInt(line[7]));
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	Path p = Paths.get("./data/NhanVien.txt");

	public QuanLyNhanVien() throws IOException {
		ArrayList<String> nvList = (ArrayList<String>) Files.readAllLines(p);
		nvList.stream().forEach((e) -> {
			String[] code = e.split("::");
			dsNhanVien.put(code[5], castToNhanVien(code));
		});
	}

	private void confirmData() {
		List<String> temp = new ArrayList<>();
		for (Map.Entry<String, NhanVien> e : dsNhanVien.entrySet()) {
			temp.add(e.getValue().luuVaoData());
		}
		try {
			Files.write(p, temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkSDT(String sdt) {
		if (sdt.charAt(0) != '0') {
			System.out.println("SDT bat dau tu 0!");
			return false;
		} else {
			for (int i = 1; i < sdt.length(); i++) {
				if (sdt.charAt(i) >= '0' && sdt.charAt(i) <= '9') {
					continue;
				} else {
					System.out.println("SDT khong duoc chua ki tu khac so!");
					return false;
				}
			}
		}
		return true;
	}

	// Them NV theo hash Map
	public void themNhanVien() {
		System.out.print("Nhap ten nhan vien: ");
		String ten = sc.nextLine();

		System.out.print("Nhap dia chi: ");
		String diaChi = sc.nextLine();

		String sdt;
		while (true) {
			System.out.print("Nhap so dien thoai: ");
			String sdt1 = sc.nextLine();
			if (checkSDT(sdt1)) {
				sdt = sdt1;
				break;
			}
		}

		System.out.print("Nhap gioi tinh (NAM or NU): ");
		String gioiTinh = sc.nextLine();

		LocalDate ngaySinh;
		while (true) {
			try {
				System.out.print("Nhap ngay sinh(dd-MM-yyyy): ");
				DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate ngaySinh1 = LocalDate.parse(sc.nextLine(), df);
				ngaySinh = ngaySinh1;
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Ngay thang nam sai dinh dang!");
			}
		}
		double luong;
		while (true) {
			try {
				System.out.print("Nhap luong: ");
				double luong1 = Double.parseDouble(sc.nextLine());
				if (luong1 <= 0) {
					System.out.println("Luong cua nhan vien phai > 0!");
					continue;
				}
				luong = luong1;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai xin vui long nhap lai!");
			}
		}
		int ngayNhanLuong;
		while (true) {
			try {
				System.out.print("Nhap ngay nhan luong (tu 1-28):");
				int ngayNhanLuong1 = Integer.parseInt(sc.nextLine());
				if (ngayNhanLuong1 < 1 || ngayNhanLuong1 > 28)
					throw new NumberFormatException();
				ngayNhanLuong = ngayNhanLuong1;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai xin vui long nhap lai!");
			}
		}
		String maNhanVien;
		while (true) {
			try {

				System.out.print("Nhap ma nhan vien: ");
				String maNhanVien1 = sc.nextLine();

				if (dsNhanVien.containsKey(maNhanVien1)) {
					System.out.println("Ma da ton tai,xin vui long nhap lai!");
				} else {
					maNhanVien = maNhanVien1;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai xin vui long nhap lai!");
			}
		}
		NhanVien nhanVien = new NhanVien(ten, gioiTinh, diaChi, sdt, ngaySinh, maNhanVien, luong, ngayNhanLuong);
		dsNhanVien.put(nhanVien.getMaNV(), nhanVien);
		confirmData();
		System.out.println("Them thanh cong!");
	}

	// xoa ok
	public void xoaNhanVien() {

		System.out.print("Nhap ma nhan vien muon xoa: ");
		String ma = sc.nextLine();

		if (dsNhanVien.containsKey(ma)) {
			dsNhanVien.remove(ma);
			confirmData();
			System.out.println("Da xoa thanh cong!");
		} else {
			System.out.println("Khong ton tai ma nhan vien!");
		}
	}

	// xem theo ma
	public void xemNhanVien() {
		System.out.print("Nhap ma nhan vien: ");
		String ma = sc.nextLine();
		System.out.println("Thong tin nhan vien: ");
		if (this.dsNhanVien.containsKey(ma)) {
			NhanVien nhanVien = this.dsNhanVien.get(ma);
			System.out.println(header);
			String row = String.format("%10s%25s%25s%25s%25s%25s%25s%25s", nhanVien.getMaNV(), nhanVien.getTen(),
					nhanVien.getGioiTinh(), nhanVien.getNgaySinh(), nhanVien.getSdt(), nhanVien.getDiaChi(),
					nhanVien.getLuong(), nhanVien.getNgayNhanLuong());
			System.out.println(row);
		} else {
			System.out.println("Ma nhan vien khong ton tai!");
		}
	}

	// Xem toan bo nhan vien
	public void xemToanBo() {
		System.out.println("Danh sach nhan vien: ");
		System.out.println(header);
		for (Map.Entry<String, NhanVien> entry : dsNhanVien.entrySet()) {
			String row = String.format("%10s%25s%25s%25s%25s%25s%25s%25s", entry.getValue().getMaNV(),
					entry.getValue().getTen(), entry.getValue().getGioiTinh(), entry.getValue().getNgaySinh(),
					entry.getValue().getSdt(), entry.getValue().getDiaChi(), entry.getValue().getLuong(),
					entry.getValue().getNgayNhanLuong());
			System.out.println(row);
		}
	}

	// cap nhat
	public void capNhat() {
		System.out.print("Nhap ma nhan vien: ");
		String ma = sc.nextLine();
		if (dsNhanVien.containsKey(ma)) {
			dsNhanVien.remove(ma);
			try {
				System.out.print("Nhap ten nhan vien: ");
				String ten = sc.nextLine();

				System.out.print("Nhap dia chi: ");
				String diaChi = sc.nextLine();

				String sdt;
				while (true) {
					System.out.print("Nhap so dien thoai: ");
					String sdt1 = sc.nextLine();
					if (checkSDT(sdt1)) {
						sdt = sdt1;
						break;
					}
				}

				System.out.print("Nhap gioi tinh (NAM or NU): ");
				String gioiTinh = sc.nextLine();

				LocalDate ngaySinh;
				while (true) {
					try {
						System.out.print("Nhap ngay sinh(dd-MM-yyyy): ");
						DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate ngaySinh1 = LocalDate.parse(sc.nextLine(), df);
						ngaySinh = ngaySinh1;
						break;
					} catch (DateTimeParseException e) {
						System.out.println("Ngay thang nam sai dinh dang!");
					}
				}

				double luong;
				while (true) {
					try {
						System.out.print("Nhap luong: ");
						double luong1 = Double.parseDouble(sc.nextLine());
						if (luong1 <= 0) {
							System.out.println("Luong cua nhan vien phai > 0!");
							continue;
						}
						luong = luong1;
						break;
					} catch (NumberFormatException e) {
						System.out.println("Ban da nhap sai xin vui long nhap lai!");
					}
				}
				int ngayNhanLuong;
				while (true) {
					try {
						System.out.print("Nhap ngay nhan luong (tu 1-28):");
						int ngayNhanLuong1 = Integer.parseInt(sc.nextLine());
						if (ngayNhanLuong1 < 1 || ngayNhanLuong1 > 28)
							throw new NumberFormatException();
						ngayNhanLuong = ngayNhanLuong1;
						break;
					} catch (NumberFormatException e) {
						System.out.println("Ban da nhap sai xin vui long nhap lai!");
					}
				}

				NhanVien nhanVien = new NhanVien(ten, gioiTinh, diaChi, sdt, ngaySinh, ma, luong, ngayNhanLuong);
				dsNhanVien.put(nhanVien.getMaNV(), nhanVien);
				confirmData();
			} catch (InputMismatchException e) {
				System.out.println("Ban da nhap sai gia tri, xin vui long nhap lai!");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Ma nhan vien khong ton tai!");
		}
	}

	// tinh tong luong
	public double tongLuong(LocalDate date1, LocalDate date2) {
		double tongLuong = 0;
		if (date1.getYear() == date2.getYear()) {
			if (date1.getMonthValue() == date2.getMonthValue()) {
				for (Map.Entry<String, NhanVien> e : dsNhanVien.entrySet()) {
					if (e.getValue().getNgayNhanLuong() >= date1.getDayOfMonth()
							&& e.getValue().getNgayNhanLuong() <= date2.getDayOfMonth()) {
						tongLuong += e.getValue().getLuong();
					}
				}
			} else {
				int m = date2.getMonthValue() - date1.getMonthValue() + 1;
				for (int i = 1; i <= m; i++) {
					if (i == 1) {
						for (Map.Entry<String, NhanVien> e : dsNhanVien.entrySet()) {
							if (e.getValue().getNgayNhanLuong() >= date1.getDayOfMonth()) {
								tongLuong += e.getValue().getLuong();
							}
						}
						continue;
					}
					if (i == m) {
						for (Map.Entry<String, NhanVien> e : dsNhanVien.entrySet()) {
							if (e.getValue().getNgayNhanLuong() <= date2.getDayOfMonth()) {
								tongLuong += e.getValue().getLuong();
							}
						}
					} else {
						for (Map.Entry<String, NhanVien> e : dsNhanVien.entrySet()) {
							tongLuong += e.getValue().getLuong();
						}
					}
				}
			}
		} else {
			tongLuong += tongLuong(date1, LocalDate.of(date1.getYear(), 12, 31));
			tongLuong += tongLuong(LocalDate.of(date2.getYear(), 1, 1), date2);
		}
		return tongLuong;
	}
}
