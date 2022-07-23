package model;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class QuanLySanPham {
	private HashMap<String, Sach> dsSach = new HashMap<String, Sach>();
	private HashMap<String, DiaNhac> dsDiaNhac = new HashMap<String, DiaNhac>();
	private HashMap<String, DiaPhim> dsDiaPhim = new HashMap<String, DiaPhim>();
	Scanner sc = new Scanner(System.in);

	Path p1 = Paths.get("./data/Sach.txt");
	Path p2 = Paths.get("./data/DiaPhim.txt");
	Path p3 = Paths.get("./data/DiaNhac.txt");

	public Sach castToSach(String[] line) {
		try {
			Sach sach = new Sach(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
					Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6], line[7]);

			return sach;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DiaPhim castToDiaPhim(String[] line) {
		try {
			DiaPhim diaPhim = new DiaPhim(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
					Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6], line[7], line[8]);

			return diaPhim;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DiaNhac castToDiaNhac(String[] line) {
		try {
			DiaNhac diaNhac = new DiaNhac(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]),
					Double.parseDouble(line[4]), Double.parseDouble(line[5]), line[6], line[7], line[8]);
			return diaNhac;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public QuanLySanPham() throws IOException {
		ArrayList<String> sachList = (ArrayList<String>) Files.readAllLines(p1);
		sachList.stream().forEach((e) -> {
			String[] code = e.split("::");
			dsSach.put(code[0], castToSach(code));
		});

		ArrayList<String> diaPhimList = (ArrayList<String>) Files.readAllLines(p2);
		diaPhimList.stream().forEach((e) -> {
			String[] code = e.split("::");
			dsDiaPhim.put(code[0], castToDiaPhim(code));
		});

		ArrayList<String> diaNhac = (ArrayList<String>) Files.readAllLines(p3);
		diaNhac.stream().forEach((e) -> {
			String[] code = e.split("::");
			dsDiaNhac.put(code[0], castToDiaNhac(code));
		});
	}

	private void confirmDataSach() {
		List<String> temp = new ArrayList<>();
		for (Map.Entry<String, Sach> e : dsSach.entrySet()) {
			temp.add(e.getValue().luuVaoData());
		}
		try {
			Files.write(p1, temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void confirmDataDiaPhim() {
		List<String> temp = new ArrayList<>();
		for (Map.Entry<String, DiaPhim> e : dsDiaPhim.entrySet()) {
			temp.add(e.getValue().luuVaoData());
		}
		try {
			Files.write(p2, temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void confirmDataDiaNhac() {
		List<String> temp = new ArrayList<>();
		for (Map.Entry<String, DiaNhac> e : dsDiaNhac.entrySet()) {
			temp.add(e.getValue().luuVaoData());
		}
		try {
			Files.write(p3, temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Sach> getDsSach() {
		return dsSach;
	}

	public void setDsSach(HashMap<String, Sach> dsSach) {
		this.dsSach = dsSach;
	}

	public HashMap<String, DiaNhac> getDsDiaNhac() {
		return dsDiaNhac;
	}

	public void setDsDiaNhac(HashMap<String, DiaNhac> dsDiaNhac) {
		this.dsDiaNhac = dsDiaNhac;
	}

	public HashMap<String, DiaPhim> getDsDiaPhim() {
		return dsDiaPhim;
	}

	public void setDsDiaPhim(HashMap<String, DiaPhim> dsDiaPhim) {
		this.dsDiaPhim = dsDiaPhim;
	}

	// 1.Kiem tra ma san pham có tồn tại không tra ve true neu ton tai
	public boolean maTonTai(String s) {
		if (dsSach.containsKey(s)) {
			return true;
		}
		if (dsDiaNhac.containsKey(s)) {
			return true;
		}
		if (dsDiaPhim.containsKey(s)) {
			return true;
		}
		return false;
	}

	// 2.Tim kiem san pham theo ma (ham nay khong kiem tra no co ton tai hay khong
	// luc lam ham mian phai chu y
	public SanPham timKiemTheoMa(String s) {
		if (maTonTai(s)) {
			if (dsSach.containsKey(s)) {
				SanPham sanPham = dsSach.get(s);
				return sanPham;
			}
			if (dsDiaNhac.containsKey(s)) {
				SanPham sanPham = dsDiaNhac.get(s);
				return sanPham;
			}
			if (dsDiaPhim.containsKey(s)) {
				SanPham sanPham = dsDiaPhim.get(s);
				return sanPham;
			}
		} else {
			System.out.println("Ma san pham khong ton tai!");
			return null;
		}
		return null;
	}

	// them san pham:

	public void themSach() {
		System.out.println("Nhap thong tin san pham: ");

		String maSanPham;
		while (true) {
			System.out.println("Ma san pham");
			String maSanPham1 = sc.nextLine();
			if (dsSach.containsKey(maSanPham1)) {
				System.out.println("Ma san pham da ton tai, vui long nhap lai!");
			} else {
				maSanPham = maSanPham1;
				break;
			}

		}

		System.out.println("Ten san pham: ");
		String tenSP = sc.nextLine();
		int namPhatHanh;
		while (true) {
			try {
				System.out.println("Nam phat hanh: ");
				int namPhatHanh1 = sc.nextInt();
				if (namPhatHanh1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				namPhatHanh = namPhatHanh1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}
		double giaBuon;
		while (true) {
			try {
				System.out.println("Gia buon: ");
				double giaBuon1 = sc.nextDouble();
				if (giaBuon1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBuon = giaBuon1;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		double giaBan;
		while (true) {
			try {
				System.out.println("Gia ban: ");
				double giaBan1 = sc.nextDouble();
				if (giaBan1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBan = giaBan1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		int soLuong;
		while (true) {
			try {
				System.out.println("So luong: ");
				int soLuong1 = sc.nextInt();
				if (soLuong1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				soLuong = soLuong1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}

		sc.nextLine();

		System.out.println("Nha xuat ban: ");
		String nhaXuatBan = sc.nextLine();

		System.out.println("Tac gia: ");
		String tacGia = sc.nextLine();

		Sach sach = new Sach(maSanPham, tenSP, namPhatHanh, soLuong, giaBuon, giaBan, tacGia, nhaXuatBan);
		this.dsSach.put(sach.getMaSp(), sach);
		confirmDataSach();
		System.out.println("Ban da them thanh cong");

	}

	public void themDiaPhim() {
		System.out.println("Nhap thong tin san pham: ");

		String maSanPham;
		while (true) {
			System.out.println("Ma san pham");
			String maSanPham1 = sc.nextLine();
			if (dsSach.containsKey(maSanPham1)) {
				System.out.println("Ma san pham da ton tai, vui long nhap lai!");
			} else {
				maSanPham = maSanPham1;
				break;
			}

		}

		System.out.println("Ten san pham: ");
		String tenSP = sc.nextLine();
		int namPhatHanh;
		while (true) {
			try {
				System.out.println("Nam phat hanh: ");
				int namPhatHanh1 = sc.nextInt();
				if (namPhatHanh1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				namPhatHanh = namPhatHanh1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}
		double giaBuon;
		while (true) {
			try {
				System.out.println("Gia buon: ");
				double giaBuon1 = sc.nextDouble();
				if (giaBuon1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBuon = giaBuon1;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		double giaBan;
		while (true) {
			try {
				System.out.println("Gia ban: ");
				double giaBan1 = sc.nextDouble();
				if (giaBan1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBan = giaBan1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		int soLuong;
		while (true) {
			try {
				System.out.println("So luong: ");
				int soLuong1 = sc.nextInt();
				if (soLuong1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				soLuong = soLuong1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}
		System.out.println("Nhap thoi luong");
		String thoiLuong = sc.nextLine();

		System.out.println("Dao dien: ");
		String daoDien = sc.nextLine();

		System.out.println("Dien vien: ");
		String dienVien = sc.nextLine();

		DiaPhim diaPhim = new DiaPhim(maSanPham, tenSP, namPhatHanh, soLuong, giaBuon, giaBan, thoiLuong, daoDien,
				dienVien);
		this.dsDiaPhim.put(diaPhim.getMaSp(), diaPhim);
		confirmDataDiaPhim();
		System.out.println("Ban da them thanh cong");

	}

	public void themDiaNhac() {
		System.out.println("Nhap thong tin san pham: ");

		String maSanPham;
		while (true) {
			System.out.println("Ma san pham");
			String maSanPham1 = sc.nextLine();
			if (dsSach.containsKey(maSanPham1)) {
				System.out.println("Ma san pham da ton tai, vui long nhap lai!");
			} else {
				maSanPham = maSanPham1;
				break;
			}

		}

		System.out.println("Ten san pham: ");
		String tenSP = sc.nextLine();
		int namPhatHanh;
		while (true) {
			try {
				System.out.println("Nam phat hanh: ");
				int namPhatHanh1 = sc.nextInt();
				if (namPhatHanh1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				namPhatHanh = namPhatHanh1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}
		double giaBuon;
		while (true) {
			try {
				System.out.println("Gia buon: ");
				double giaBuon1 = sc.nextDouble();
				if (giaBuon1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBuon = giaBuon1;
				break;
			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		double giaBan;
		while (true) {
			try {
				System.out.println("Gia ban: ");
				double giaBan1 = sc.nextDouble();
				if (giaBan1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				giaBan = giaBan1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}
		}
		int soLuong;
		while (true) {
			try {
				System.out.println("So luong: ");
				int soLuong1 = sc.nextInt();
				if (soLuong1 <= 0) {
					System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
					continue;
				}
				soLuong = soLuong1;
				break;

			} catch (NumberFormatException e) {
				System.out.println("Ban da nhap sai vui long nhap lai!");
			}

		}
		System.out.println("Nhap thoi luong");
		String thoiLuong = sc.nextLine();

		System.out.println("Ca sy: ");
		String caSy = sc.nextLine();

		System.out.println("Danh sach bai hat: ");
		String danhSachBaiHat = sc.nextLine();

		DiaNhac diaNhac = new DiaNhac(maSanPham, tenSP, namPhatHanh, soLuong, giaBuon, giaBan, thoiLuong, caSy,
				danhSachBaiHat);
		this.dsDiaNhac.put(diaNhac.getMaSp(), diaNhac);
		confirmDataDiaNhac();
		System.out.println("Ban da them thanh cong");
	}

	public void xoaSanPham() {
		try {
			System.out.println("Nhap ma san pham: ");
			String maSp = sc.nextLine();
			if (maTonTai(maSp)) {
				SanPham sanPham = timKiemTheoMa(maSp);
				if (sanPham instanceof Sach) {
					dsSach.remove(maSp);
					confirmDataSach();
				}
				if (sanPham instanceof DiaPhim) {
					dsDiaPhim.remove(maSp);
					confirmDataDiaPhim();
				}
				if (sanPham instanceof DiaNhac) {
					dsDiaNhac.remove(maSp);
					confirmDataDiaNhac();
				}
			} else {
				System.out.println(" San pham khong ton tai");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void capNhatThongTinSanPham() {
		System.out.print("Nhap ma san pham: ");
		String ma = sc.nextLine();
		if ((dsSach.containsKey(ma)) || (dsDiaPhim.containsKey(ma)) || (dsDiaNhac.containsKey(ma))) {
			if (dsSach.containsKey(ma)) {
				dsSach.remove(ma);
				try {
					System.out.print("Nhap ten san pham: ");
					String tenSp = sc.nextLine();

					int namPhatHanh;
					while (true) {
						try {
							System.out.println("Nam phat hanh: ");
							int namPhatHanh1 = sc.nextInt();
							if (namPhatHanh1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							namPhatHanh = namPhatHanh1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}

					}
					double giaBuon;
					while (true) {
						try {
							System.out.println("Gia buon: ");
							double giaBuon1 = sc.nextDouble();
							if (giaBuon1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBuon = giaBuon1;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					double giaBan;
					while (true) {
						try {
							System.out.println("Gia ban: ");
							double giaBan1 = sc.nextDouble();
							if (giaBan1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBan = giaBan1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					int soLuong;
					while (true) {
						try {
							System.out.println("So luong: ");
							int soLuong1 = sc.nextInt();
							if (soLuong1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							soLuong = soLuong1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}

					}

					sc.nextLine();

					System.out.println("Nha xuat ban: ");
					String nhaXuatBan = sc.nextLine();

					System.out.println("Tac gia: ");
					String tacGia = sc.nextLine();

					Sach sach = new Sach(ma, tenSp, namPhatHanh, soLuong, giaBuon, giaBan, tacGia, nhaXuatBan);
					this.dsSach.put(sach.getMaSp(), sach);
					confirmDataSach();
					System.out.println("Ban da them thanh cong");

				} catch (InputMismatchException e) {
					System.out.println("Ban da nhap sai gia tri, xin vui long nhap lai!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (dsDiaPhim.containsKey(ma)) {
				dsDiaPhim.remove(ma);
				try {
					System.out.print("Nhap ten san pham: ");
					String tenSp = sc.nextLine();

					int namPhatHanh;
					while (true) {
						try {
							System.out.println("Nam phat hanh: ");
							int namPhatHanh1 = sc.nextInt();
							if (namPhatHanh1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							namPhatHanh = namPhatHanh1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}

					}
					double giaBuon;
					while (true) {
						try {
							System.out.println("Gia buon: ");
							double giaBuon1 = sc.nextDouble();
							if (giaBuon1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBuon = giaBuon1;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					double giaBan;
					while (true) {
						try {
							System.out.println("Gia ban: ");
							double giaBan1 = sc.nextDouble();
							if (giaBan1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBan = giaBan1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					int soLuong;
					while (true) {
						try {
							System.out.println("So luong: ");
							int soLuong1 = sc.nextInt();
							if (soLuong1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							soLuong = soLuong1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}

					sc.nextLine();

					System.out.println("Thoi luong: ");
					String thoiLuong = sc.nextLine();

					System.out.println("Dao dien: ");
					String daoDien = sc.nextLine();

					System.out.println("Dien vien: ");
					String dienVien = sc.nextLine();

					DiaPhim diaPhim = new DiaPhim(ma, tenSp, namPhatHanh, soLuong, giaBuon, giaBan, thoiLuong, daoDien,
							dienVien);
					this.dsDiaPhim.put(diaPhim.getMaSp(), diaPhim);
					confirmDataDiaPhim();
					System.out.println("Ban da them thanh cong");

				} catch (InputMismatchException e) {
					System.out.println("Ban da nhap sai gia tri, xin vui long nhap lai!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			if (dsDiaNhac.containsKey(ma)) {
				dsDiaNhac.remove(ma);
				try {
					System.out.print("Nhap ten san pham: ");
					String tenSp = sc.nextLine();

					int namPhatHanh;
					while (true) {
						try {
							System.out.println("Nam phat hanh: ");
							int namPhatHanh1 = sc.nextInt();
							if (namPhatHanh1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							namPhatHanh = namPhatHanh1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}

					}
					double giaBuon;
					while (true) {
						try {
							System.out.println("Gia buon: ");
							double giaBuon1 = sc.nextDouble();
							if (giaBuon1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBuon = giaBuon1;
							break;
						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					double giaBan;
					while (true) {
						try {
							System.out.println("Gia ban: ");
							double giaBan1 = sc.nextDouble();
							if (giaBan1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							giaBan = giaBan1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}
					}
					int soLuong;
					while (true) {
						try {
							System.out.println("So luong: ");
							int soLuong1 = sc.nextInt();
							if (soLuong1 <= 0) {
								System.out.println("Nam phat hanh phai lon hon 0, vui long nhap lai!");
								continue;
							}
							soLuong = soLuong1;
							break;

						} catch (NumberFormatException e) {
							System.out.println("Ban da nhap sai vui long nhap lai!");
						}

					}

					sc.nextLine();

					System.out.println("Thoi luong: ");
					String thoiLuong = sc.nextLine();

					System.out.println("Ca sy: ");
					String caSy = sc.nextLine();

					System.out.println("Danh sach bai hat: ");
					String danhSachBaiHat = sc.nextLine();

					DiaNhac diaNhac = new DiaNhac(ma, tenSp, namPhatHanh, soLuong, giaBuon, giaBan, thoiLuong, caSy,
							danhSachBaiHat);
					this.dsDiaNhac.put(diaNhac.getMaSp(), diaNhac);
					confirmDataDiaNhac();
					System.out.println("Ban da them thanh cong");
				} catch (InputMismatchException e) {
					System.out.println("Ban da nhap sai gia tri, xin vui long nhap lai!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} else {
			System.out.println("Ma san pham khong ton tai!");
		}
	}

	// thiet ke laoi ham main

	public void xemToanBoSach() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
				"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Tac gia", "Nha xuat ban");
		System.out.println(header);
		for (Map.Entry<String, Sach> entry : dsSach.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBuon(), entry.getValue().getGiaBan(), entry.getValue().getTacGia(),
					entry.getValue().getNhaXuatBan());
			System.out.println(row);
		}
	}

	public void xemToanBoDiaPhim() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
				"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Thoi luong", "Dao dien", "Dien vien");
		System.out.println(header);
		for (Map.Entry<String, DiaPhim> entry : dsDiaPhim.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBuon(), entry.getValue().getGiaBan(), entry.getValue().getThoiLuong(),
					entry.getValue().getDaoDien(), entry.getValue().getDienVien());
			System.out.println(row);
		}
	}

	public void xemToanBoDiaNhac() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
				"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Thoi luong", "Ca sy", "Danh sach bai hat");
		System.out.println(header);
		for (Map.Entry<String, DiaNhac> entry : dsDiaNhac.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBuon(), entry.getValue().getGiaBan(), entry.getValue().getThoiLuong(),
					entry.getValue().getCaSy(), entry.getValue().getDanhsachBaiHat());
			System.out.println(row);
		}
	}

	public void xemThongTinSanPhamTheoMa() {
		System.out.println("Nhap ma san pham: ");
		String maSp = sc.nextLine();
		if (maTonTai(maSp)) {
			SanPham sanPham = timKiemTheoMa(maSp);
			if (sanPham instanceof Sach) {
				Sach sach = this.dsSach.get(maSp);
				String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
						"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Tac gia", "Nha xuat ban");
				System.out.println(header);
				String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", sach.getMaSp(), sach.getTenSp(),
						sach.getNamPhatHanh(), sach.getSoLuong(), sach.getGiaBuon(), sach.getGiaBan(), sach.getTacGia(),
						sach.getNhaXuatBan());
				System.out.println(row);
			}
			if (sanPham instanceof DiaPhim) {
				DiaPhim diaPhim = this.dsDiaPhim.get(maSp);
				String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
						"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Thoi luong", "Dao dien", "Dien vien");
				System.out.println(header);
				String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", diaPhim.getMaSp(),
						diaPhim.getTenSp(), diaPhim.getNamPhatHanh(), diaPhim.getSoLuong(), diaPhim.getGiaBuon(),
						diaPhim.getGiaBan(), diaPhim.getThoiLuong(), diaPhim.getDaoDien(), diaPhim.getDienVien());
				System.out.println(row);
			}
			if (sanPham instanceof DiaNhac) {
				DiaNhac diaNhac = this.dsDiaNhac.get(maSp);
				String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
						"Nam phat hanh", "So luong", "Gia buon", "Gia ban", "Thoi luong",
						"Ca sy" + "Danh sach bai hat");
				System.out.println(header);
				String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s%30s", diaNhac.getMaSp(),
						diaNhac.getTenSp(), diaNhac.getNamPhatHanh(), diaNhac.getSoLuong(), diaNhac.getGiaBuon(),
						diaNhac.getGiaBan(), diaNhac.getThoiLuong(), diaNhac.getCaSy(), diaNhac.getDanhsachBaiHat());
				System.out.println(row);
			}
		} else {
			System.out.println(" San pham khong ton tai");
		}
	}

	public void xemToanBoSachChoKhachHang() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham", "Nam phat hanh",
				"So luong", "Gia ban", "Tac gia", "Nha xuat ban");
		System.out.println(header);
		for (Map.Entry<String, Sach> entry : dsSach.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBan(), entry.getValue().getTacGia(), entry.getValue().getNhaXuatBan());
			System.out.println(row);
		}
	}

	public void xemToanBoDiaPhimChoKhachHang() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
				"Nam phat hanh", "So luong", "Gia ban", "Thoi luong", "Dao dien" + "Dien vien");
		System.out.println(header);
		for (Map.Entry<String, DiaPhim> entry : dsDiaPhim.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBan(), entry.getValue().getThoiLuong(), entry.getValue().getDaoDien(),
					entry.getValue().getDienVien());
			System.out.println(row);
		}
	}

	public void xemToanBoDiaNhacChoKhachHang() {
		System.out.println("Danh sach san pham");
		String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
				"Nam phat hanh", "So luong", "Gia ban", "Thoi luong", "Ca sy", "Danh sach bai hat");
		System.out.println(header);
		for (Map.Entry<String, DiaNhac> entry : dsDiaNhac.entrySet()) {
			String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", entry.getValue().getMaSp(),
					entry.getValue().getTenSp(), entry.getValue().getNamPhatHanh(), entry.getValue().getSoLuong(),
					entry.getValue().getGiaBan(), entry.getValue().getThoiLuong(), entry.getValue().getCaSy(),
					entry.getValue().getDanhsachBaiHat());
			System.out.println(row);
		}
	}

	public void xemThongTinSanPhamChoKhachHang() {
		try {
			System.out.println("Nhap ma san pham: ");
			String maSp = sc.nextLine();
			if (maTonTai(maSp)) {
				SanPham sanPham = timKiemTheoMa(maSp);
				if (sanPham instanceof Sach) {
					Sach sach = this.dsSach.get(maSp);
					String header = String.format("%10s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
							"Nam phat hanh", "So luong", "Gia ban", "Tac gia", "Nha xuat ban");
					System.out.println(header);
					String row = String.format("%10s%30s%30s%30s%30s%30s%30s", sach.getMaSp(), sach.getTenSp(),
							sach.getNamPhatHanh(), sach.getSoLuong(), sach.getGiaBan(), sach.getTacGia(),
							sach.getNhaXuatBan());
					System.out.println(row);
				}
				if (sanPham instanceof DiaPhim) {
					DiaPhim diaPhim = this.dsDiaPhim.get(maSp);
					String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
							"Nam phat hanh", "So luong", "Gia ban", "Thoi luong", "Dao dien" + "Dien vien");
					System.out.println(header);
					String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", diaPhim.getMaSp(),
							diaPhim.getTenSp(), diaPhim.getNamPhatHanh(), diaPhim.getSoLuong(), diaPhim.getGiaBan(),
							diaPhim.getThoiLuong(), diaPhim.getDaoDien(), diaPhim.getDienVien());
					System.out.println(row);
				}
				if (sanPham instanceof DiaNhac) {
					DiaNhac diaNhac = this.dsDiaNhac.get(maSp);
					String header = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", "Ma san pham", "Ten san pham",
							"Nam phat hanh", "So luong", "Gia ban", "Ca sy", "Thoi luong", "Danh sach bai hat");
					System.out.println(header);
					String row = String.format("%10s%30s%30s%30s%30s%30s%30s%30s", diaNhac.getMaSp(),
							diaNhac.getTenSp(), diaNhac.getNamPhatHanh(), diaNhac.getSoLuong(), diaNhac.getGiaBan(),
							diaNhac.getThoiLuong(), diaNhac.getCaSy(), diaNhac.getDanhsachBaiHat());
					System.out.println(row);
				}
			} else {
				System.out.println(" San pham khong ton tai");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
