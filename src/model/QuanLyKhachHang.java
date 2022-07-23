package model;

import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuanLyKhachHang {
	private HashMap<String, KhachHang> dsKhachHang = new HashMap<>();
	public String header = String.format("%10s%25s%15s%25s%25s%25s", "Ma", "Ten", "Gioi Tinh", "Ngay Sinh", "SDT",
			"Dia chi");
	Scanner sc = new Scanner(System.in);

	Path p = Paths.get("./data/KhachHang.txt");

	public HashMap<String, KhachHang> getDsKhachHang() {
		return dsKhachHang;
	}

	public void setDsKhachHang(HashMap<String, KhachHang> dsKhachHang) {
		this.dsKhachHang = dsKhachHang;
	}

	public KhachHang castToKhachHang(String[] line) {
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			KhachHang kh = new KhachHang(line[0], line[1], line[2], line[3], line[4], LocalDate.parse(line[5], f));
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QuanLyKhachHang() throws IOException {
		ArrayList<String> khList = (ArrayList<String>) Files.readAllLines(p);

		khList.stream().forEach((e) -> {
			String[] code = e.split("::");
			dsKhachHang.put(code[1], castToKhachHang(code));
		});
	}

	private void confirmData() {
		List<String> temp = new ArrayList<>();
		for (Map.Entry<String, KhachHang> e : dsKhachHang.entrySet()) {
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

	// them khach hang
	public KhachHang themKhachHang() {
		System.out.print("Nhap ten khach hang: ");
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
		String maKhachHang;
		while (true) {
			try {

				System.out.print("Nhap ma khach hang: ");
				String maKhachHang1 = sc.nextLine();

				if (dsKhachHang.containsKey(maKhachHang1)) {
					System.out.println("Ma da ton tai,xin vui long nhap lai!");
				} else {
					maKhachHang = maKhachHang1;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai xin vui long nhap lai!");
			}
		}
		KhachHang khachHang = new KhachHang(ten, maKhachHang, gioiTinh, diaChi, sdt, ngaySinh);
		dsKhachHang.put(khachHang.getMaKh(), khachHang);
		confirmData();
		System.out.println("Them thanh cong!");
		return khachHang;
	}

	// xem khach hang
	public void xemKhachHang() {
		System.out.println("Nhap ma khach hang: ");
		String maKhachHang = sc.nextLine();
		System.out.println("Thong tin khach hang: ");
		if (this.dsKhachHang.containsKey(maKhachHang)) {
			KhachHang khachHang = this.dsKhachHang.get(maKhachHang);
			System.out.println(header);
			String row = String.format("%10s%25s%15s%25s%25s%25s", khachHang.getMaKh(), khachHang.getTen(),
					khachHang.getGioiTinh(), khachHang.getNgaySinh(), khachHang.getSdt(), khachHang.getDiaChi());
			System.out.println(row);
		} else {
			System.out.println("Ma khach hang khong ton tai!");
		}
	}

	// xem toan bo khach hang
	public void xemToanBo() {
		System.out.println("Danh sach khach hang");
		System.out.println(header);
		for (Map.Entry<String, KhachHang> entry : dsKhachHang.entrySet()) {
			String row = String.format("%10s%25s%15s%25s%25s%25s", entry.getValue().getMaKh(),
					entry.getValue().getTen(), entry.getValue().getGioiTinh(), entry.getValue().getNgaySinh(),
					entry.getValue().getSdt(), entry.getValue().getDiaChi());
			System.out.println(row);
		}
	}

	// cap nhat khach hang
	public void capNhat() {
		System.out.print("Nhap ma khach hang: ");
		String ma = sc.nextLine();
		if (dsKhachHang.containsKey(ma)) {
			dsKhachHang.remove(ma);
			try {
				System.out.print("Nhap ten khach hang: ");
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

				System.out.print("Nhap gioi tinh: ");
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

				KhachHang khachHang = new KhachHang(ten, ma, gioiTinh, diaChi, sdt, ngaySinh);
				dsKhachHang.put(khachHang.getMaKh(), khachHang);
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
}
