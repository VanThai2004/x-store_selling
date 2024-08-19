/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.KhachHang;
import Model.NhanVien;
import Model.TaiKhoan;
import Service.QuanLyNhanVien;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author TienBB
 */
public class ViewNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form ViewNhanVien
     */
    DefaultTableModel defaultTableModel;
    QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();

    public ViewNhanVien() {
        initComponents();
        loadNhanVien();
        loadQuanLyTaiKhoan();
        loadTaiKhoanDKi();
        loadCaLamDKi();
        loadCaLamNhanVien();
    }

    void loadNhanVien() {
        defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (NhanVien nhanVien : quanLyNhanVien.getAllNhanVien()) {
            defaultTableModel.addRow(new Object[]{
                i++,
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getCccd(),
                nhanVien.getEmail(),
                nhanVien.getNgaySinh(),
                nhanVien.isGioiTinh() ? "Nam" : "Nữ",
                nhanVien.getSĐT(),
                nhanVien.getNgayVaoLam(),
                nhanVien.getVaiTro(),
                nhanVien.getMaCL(),
                nhanVien.getDiaChi(),
                nhanVien.getTrangThai(),});
        }
    }

    void SeachNhanVien(String maa) {
        defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (NhanVien nhanVien : quanLyNhanVien.seachNhanVien(maa)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getCccd(),
                nhanVien.getEmail(),
                nhanVien.getNgaySinh(),
                nhanVien.isGioiTinh() ? "Nam" : "Nữ",
                nhanVien.getSĐT(),
                nhanVien.getNgayVaoLam(),
                nhanVien.getVaiTro(),
                nhanVien.getMaCL(),
                nhanVien.getDiaChi(),
                nhanVien.getTrangThai(),});
        }
    }

    void SeachMaCLNhanVien(String maa) {
        defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (NhanVien nhanVien : quanLyNhanVien.seachMaCLNhanVien(maa)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                nhanVien.getMaNV(),
                nhanVien.getTenNV(),
                nhanVien.getCccd(),
                nhanVien.getEmail(),
                nhanVien.getNgaySinh(),
                nhanVien.isGioiTinh() ? "Nam" : "Nữ",
                nhanVien.getSĐT(),
                nhanVien.getNgayVaoLam(),
                nhanVien.getVaiTro(),
                nhanVien.getMaCL(),
                nhanVien.getDiaChi(),
                nhanVien.getTrangThai(),});
        }
    }

    void loadTaiKhoanDKi() {
        defaultTableModel = (DefaultTableModel) tblTaiKhoanDKi.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        String matKhauChe = "*****";
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllTaiKhoan()) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaTK(),
                taiKhoan.getTenDN(),
                matKhauChe,
                //                taiKhoan.getMatKhau(),
                taiKhoan.getMaNV(),});
        }

    }

    void loadSeachDKi(String ma) {
        defaultTableModel = (DefaultTableModel) tblTaiKhoanDKi.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        String matKhauChe = "*****";
        for (TaiKhoan taiKhoan : quanLyNhanVien.SeachTaiKhoan(ma)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaTK(),
                taiKhoan.getTenDN(),
                matKhauChe,
                //                taiKhoan.getMatKhau(),
                taiKhoan.getMaNV(),});
        }

    }

    void loadSeachTaiKhoanNV(String ma) {
        defaultTableModel = (DefaultTableModel) tblQuanLyTaiKhoan.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        String matKhauChe = "*****";

        for (TaiKhoan taiKhoan : quanLyNhanVien.SeachTKhoanNhanVien(ma)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaTK(),
                taiKhoan.getTenDN(),
                matKhauChe,
                //                taiKhoan.getMatKhau(),
                taiKhoan.getMaNV(),});
        }

    }

    void loadQuanLyTaiKhoan() {
        defaultTableModel = (DefaultTableModel) tblQuanLyTaiKhoan.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        String matKhauChe = "*****";
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllTaiKhoan()) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaTK(),
                taiKhoan.getTenDN(),
                matKhauChe,
                //                taiKhoan.getMatKhau(),
                taiKhoan.getMaNV(),});
        }
    }

    void loadCaLamDKi() {
        defaultTableModel = (DefaultTableModel) tblCaLamDki.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllCaLam()) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaCL(),
                taiKhoan.getTenCL(),
                taiKhoan.getGioBatDau(),
                taiKhoan.getGioKetThuc(),
                taiKhoan.getGhiChu()
            });
        }
    }

    void loadSeachCaLamDki(String maa) {
        defaultTableModel = (DefaultTableModel) tblCaLamDki.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (TaiKhoan taiKhoan : quanLyNhanVien.SeachCaLam(maa)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaCL(),
                taiKhoan.getTenCL(),
                taiKhoan.getGioBatDau(),
                taiKhoan.getGioKetThuc(),
                taiKhoan.getGhiChu()
            });
        }
    }

    void loadSeachCaLamNV(String maa) {
        defaultTableModel = (DefaultTableModel) tblCaLam.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (TaiKhoan taiKhoan : quanLyNhanVien.SeachCaLam(maa)) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaCL(),
                taiKhoan.getTenCL(),
                taiKhoan.getGioBatDau(),
                taiKhoan.getGioKetThuc(),
                taiKhoan.getGhiChu()
            });
        }
    }

    void loadCaLamNhanVien() {
        defaultTableModel = (DefaultTableModel) tblCaLam.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllCaLam()) {
            defaultTableModel.addRow(new Object[]{
                i++,
                taiKhoan.getMaCL(),
                taiKhoan.getTenCL(),
                taiKhoan.getGioBatDau(),
                taiKhoan.getGioKetThuc(),
                taiKhoan.getGhiChu()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        NhanVien = new javax.swing.JPanel();
        pnCaLam = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCaLam = new javax.swing.JTable();
        pnNhanVien = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        pnQuanLyTK = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtCaLam = new javax.swing.JTextField();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtVaiTro = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgayVaoLam = new com.toedter.calendar.JDateChooser();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtCccd = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnNghiLam = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblQuanLyTaiKhoan = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        XuattExel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMaTaiKhoanDki = new javax.swing.JTextField();
        txtDangNhapDKi = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        txtMaNVDKi = new javax.swing.JTextField();
        btnAddDKi = new javax.swing.JButton();
        btnUpDateDKi = new javax.swing.JButton();
        btnNewDangKi = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaiKhoanDKi = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiemTaiKhoan = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtTiemKiemCaLam = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtMaCaLam = new javax.swing.JTextField();
        txtTenCaLam = new javax.swing.JTextField();
        txtGioKetThuc = new javax.swing.JTextField();
        btnAddCaLam = new javax.swing.JButton();
        btnUpDateCalLam = new javax.swing.JButton();
        btnDeleteCaLam = new javax.swing.JButton();
        btnNewCaLam = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        txtGioBatDau = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCaLamDki = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setForeground(new java.awt.Color(255, 0, 51));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1200, 700));

        NhanVien.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnCaLam.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Ca làm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblCaLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Ma CL", "Tên CL", "Giờ BĐ ", "Giờ KT", "Ghi Chú"
            }
        ));
        tblCaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaLamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCaLam);

        javax.swing.GroupLayout pnCaLamLayout = new javax.swing.GroupLayout(pnCaLam);
        pnCaLam.setLayout(pnCaLamLayout);
        pnCaLamLayout.setHorizontalGroup(
            pnCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCaLamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        pnCaLamLayout.setVerticalGroup(
            pnCaLamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCaLamLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pnNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel9.setText("Tìm Kiếm Nhân Viên");

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã NV", "Tên NV", "Cccd", "Email", "Ngày Sinh", "Giới Tính", "SĐT", "Ngày Vào", "Vai Trò", "Mã CL", "Địa Chỉ", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnQuanLyTK.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Quản lý tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null));

        jLabel1.setText("Mã nhân viên");

        jLabel2.setText("Tên nhân viên");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Vai trò");

        jLabel7.setText("Giới tính");

        jLabel8.setText("Mã Ca làm");

        buttonGroup1.add(rdNam);
        rdNam.setText("Nam");

        buttonGroup1.add(rdNu);
        rdNu.setText("Nữ");

        jLabel11.setText("Trạng Thái");

        jLabel10.setText("Ngày vào làm");

        txtNgayVaoLam.setDateFormatString("yyyy-MM-dd");

        txtNgaySinh.setDateFormatString("yyyy-MM-dd");

        jLabel23.setText("CCCD");

        jLabel24.setText("Email");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtDiaChi)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtCccd)
                            .addComponent(txtEmail))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(rdNam)
                                    .addGap(48, 48, 48)
                                    .addComponent(rdNu))
                                .addComponent(txtTrangThai)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtVaiTro)
                                .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCccd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdNam)
                            .addComponent(rdNu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnNghiLam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNghiLam.setText("Think Do");
        btnNghiLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNghiLamMouseClicked(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setText("New");
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewMouseClicked(evt);
            }
        });

        tblQuanLyTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã TK", "Tên Đăng Nhập", "Mật Khẩu", "Mã NV"
            }
        ));
        tblQuanLyTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblQuanLyTaiKhoan);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        XuattExel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        XuattExel.setText("Xuất Exel");
        XuattExel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XuattExelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnQuanLyTKLayout = new javax.swing.GroupLayout(pnQuanLyTK);
        pnQuanLyTK.setLayout(pnQuanLyTKLayout);
        pnQuanLyTKLayout.setHorizontalGroup(
            pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnQuanLyTKLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(XuattExel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnQuanLyTKLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(pnQuanLyTKLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(btnNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );
        pnQuanLyTKLayout.setVerticalGroup(
            pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnQuanLyTKLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnQuanLyTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XuattExel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NhanVienLayout = new javax.swing.GroupLayout(NhanVien);
        NhanVien.setLayout(NhanVienLayout);
        NhanVienLayout.setHorizontalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnQuanLyTK, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, Short.MAX_VALUE)
                    .addGroup(NhanVienLayout.createSequentialGroup()
                        .addComponent(pnCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        NhanVienLayout.setVerticalGroup(
            NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnQuanLyTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân Viên", NhanVien);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tài Khoản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel12.setText("Mã TK ");

        jLabel13.setText("Tên Đăng Nhập");

        jLabel14.setText("Mật Khẩu");

        jLabel15.setText("Mã NV");

        btnAddDKi.setText("ADD");
        btnAddDKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddDKiMouseClicked(evt);
            }
        });

        btnUpDateDKi.setText("UpDate");
        btnUpDateDKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpDateDKiMouseClicked(evt);
            }
        });

        btnNewDangKi.setText("New");
        btnNewDangKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewDangKiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNVDKi, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(txtMatKhau)
                    .addComponent(txtDangNhapDKi)
                    .addComponent(txtMaTaiKhoanDki))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddDKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpDateDKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewDangKi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMaTaiKhoanDki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDKi))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtDangNhapDKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpDateDKi))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewDangKi))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMaNVDKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblTaiKhoanDKi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã TK", "Tên Đăng Nhập", "Mật Khẩu", "Mã NV"
            }
        ));
        tblTaiKhoanDKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanDKiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTaiKhoanDKi);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel16.setText("Tìm Kiếm Tài Khoản");

        txtTimKiemTaiKhoan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTaiKhoanKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ca Làm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel17.setText("Tìm Kiếm Ca Làm");

        txtTiemKiemCaLam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiemKiemCaLamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel17)
                .addGap(40, 40, 40)
                .addComponent(txtTiemKiemCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTiemKiemCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setText("Mã CL ");

        jLabel19.setText("Tên Ca Làm");

        jLabel20.setText("Giờ Bắt Đầu");

        jLabel21.setText("Giờ Kết Thúc");

        btnAddCaLam.setText("ADD");
        btnAddCaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCaLamMouseClicked(evt);
            }
        });

        btnUpDateCalLam.setText("UpDate");
        btnUpDateCalLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpDateCalLamMouseClicked(evt);
            }
        });

        btnDeleteCaLam.setText("Delete");
        btnDeleteCaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteCaLamMouseClicked(evt);
            }
        });

        btnNewCaLam.setText("New");

        jLabel22.setText("Ghi Chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane6.setViewportView(txtGhiChu);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(txtTenCaLam)
                            .addComponent(txtGioKetThuc)
                            .addComponent(txtGioBatDau))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddCaLam)
                            .addComponent(btnUpDateCalLam)
                            .addComponent(btnDeleteCaLam)
                            .addComponent(btnNewCaLam))
                        .addGap(15, 15, 15))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMaCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddCaLam))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTenCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpDateCalLam))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteCaLam))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewCaLam))
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblCaLamDki.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã CL", "Tên Ca Làm", "Giờ Bắt Đầu ", "Giờ Kết Thúc", "Ghi Chú"
            }
        ));
        tblCaLamDki.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaLamDkiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCaLamDki);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài Khoản", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        String ma = txtMaNhanVien.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Nhân Viên");
            return;
        }
        for (NhanVien nhanVien : quanLyNhanVien.getAllNhanVien()) {
            if (nhanVien.getMaNV().equals(ma)) {
                JOptionPane.showMessageDialog(this, "Không Được Trùng Mã Nhân Viên");
                return;
            }
        }
        String ten = txtTenNhanVien.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên");
            return;
        }
        String Cccd = txtCccd.getText();
        if (Cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Căn Cước Công Dân");
            return;
        }

        try {
            Float cccd = Float.parseFloat(txtCccd.getText());
            Float.valueOf(cccd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Căn Cước Công Dân Phải Là Số");
            return;
        }
        String Email = txtEmail.getText();
        if (Email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Email");
            return;
        }
        String emailRegex = "^[a-z0-9._%+-]+@gmail\\.com$";
//        String emailRegex2 = "^[a-z0-9._%+-]+@fpt\\.edu.vn$";
//|| !Email.matches(emailRegex2)
        if (!Email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return;
        }
        Date ngaySinh = txtNgaySinh.getDate();
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ngày Sinh");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strNgaySinh = dateFormat.format(ngaySinh);
        Calendar dob = Calendar.getInstance();
        dob.setTime(ngaySinh);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (age < 18) {
            JOptionPane.showMessageDialog(this, "Nhân Viên Phải Lớn Hơn 18 Tuổi ");
            return;
        }
         if (age > 50) {
            JOptionPane.showMessageDialog(this, "Nhân Viên Phải Nhỏ Hơn 50 Tuổi ");
            return;
        }
        Boolean gioiTinh;
        if (rdNam.isSelected()) {
            gioiTinh = true;
        } else if (rdNu.isSelected()) {
            gioiTinh = false;
        } else {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giới Tính");
            return;
        }

        String sdt = txtSoDienThoai.getText();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Số Diện Thoại");
            return;
        }
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải Là 10 số");
            return;
        }
        try {
            Integer sdtt = Integer.parseInt(txtSoDienThoai.getText());
            Integer.valueOf(sdtt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải Là Số");
            return;
        }

        if (!sdt.startsWith("0")) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Bắt Đầu Bằng số 0");
            return;
        }
        Date NgayVaoLam = txtNgayVaoLam.getDate();
        if (txtNgayVaoLam.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ngày Vào Làm");
            return;
        }
        SimpleDateFormat dateNgayVao = new SimpleDateFormat("yyyy-MM-dd");
        String NgayVao = dateNgayVao.format(NgayVaoLam);
        System.out.println(NgayVaoLam);
        String vaiTro = txtVaiTro.getText();
        if (vaiTro.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Vai Trò");
            return;
        }
        String maCL = txtCaLam.getText();
        if (maCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Ca Làm");
            return;
        }
        String trangThai = txtTrangThai.getText();
        if (trangThai.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Trạng Thái");
            return;
        }
        String diaChi = txtDiaChi.getText();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Địa Chỉ");
            return;
        }
        NhanVien nhanVien = new NhanVien(ma, ten, Cccd, Email, strNgaySinh, gioiTinh, sdt, NgayVao, vaiTro, maCL, diaChi, trangThai);
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Thêm Nhân Viên Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.AddNhanVien(nhanVien);
            JOptionPane.showMessageDialog(this, "Thêm Nhân Viên Thành Công");
            loadNhanVien();
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnNghiLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNghiLamMouseClicked
        // TODO add your handling code here:
        String ma = txtMaNhanVien.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Nhân Viên");
            return;
        }
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Cập Nhật NV Nghỉ Làm Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.deleteNhanVien(ma);
            JOptionPane.showMessageDialog(this, "Cập Nhật Nhân Viên Nghỉ Làm");
            loadNhanVien();
        }
    }//GEN-LAST:event_btnNghiLamMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int i = tblNhanVien.getSelectedRow();

        String ma = (String) tblNhanVien.getValueAt(i, 1);
        txtMaNhanVien.setText(ma);

        String ten = (String) tblNhanVien.getValueAt(i, 2);
        txtTenNhanVien.setText(ten);
        String Cccd = (String) tblNhanVien.getValueAt(i, 3);
        txtCccd.setText(Cccd);
        String Email = (String) tblNhanVien.getValueAt(i, 4);
        txtEmail.setText(Email);

        String NgaySinh = (String) tblNhanVien.getValueAt(i, 5);
        if (NgaySinh == null) {
            txtNgaySinh.setDate(null);
        } else {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            try {
                startDate = dateFormat.parse(NgaySinh);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            txtNgaySinh.setDate(startDate);
        }
        String gioiTinh = (String) tblNhanVien.getValueAt(i, 6);
        if (gioiTinh.equals("Nam")) {
            rdNam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

        String diaChi = (String) tblNhanVien.getValueAt(i, 11);
        txtDiaChi.setText(diaChi);

        String sdt = (String) tblNhanVien.getValueAt(i, 7);
        txtSoDienThoai.setText(sdt);
        String NgayVaoLam = (String) tblNhanVien.getValueAt(i, 8);
        if (NgayVaoLam == null) {
            txtNgayVaoLam.setDate(null);
        } else {
            SimpleDateFormat dateNgayVao = new SimpleDateFormat("yyyy-MM-dd");
            Date startNgayVao = null;
            try {
                startNgayVao = dateNgayVao.parse(NgayVaoLam);
            } catch (ParseException e) {
                startNgayVao = null;
                e.printStackTrace();
            }
//          System.out.println(NgayVaoLam);
//          System.out.println(dateNgayVao);
//        System.out.println(startNgayVao+"ss");
            txtNgayVaoLam.setDate(startNgayVao);
        }
        String vaiTro = (String) tblNhanVien.getValueAt(i, 9);
        txtVaiTro.setText(vaiTro);
        String maCL = (String) tblNhanVien.getValueAt(i, 10);
        txtCaLam.setText(maCL);

        String trangThai = (String) tblNhanVien.getValueAt(i, 12);
        txtTrangThai.setText(trangThai);

//        if (i == 0) {
//            String MaCL = (String) tblNhanVien.getValueAt(0, 9);
//            String MaTK = (String) tblNhanVien.getValueAt(0, 1);
//            loadSeachCaLamNV(MaCL);
//            loadSeachTaiKhoanNV(MaTK);
////            System.out.println(MaTK);
//        } else {
        loadSeachCaLamNV(maCL);
        loadSeachTaiKhoanNV(ma);
//        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:
        int i = tblNhanVien.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "Mời Chọn Dòng");
            return;

        }
        String ma = txtMaNhanVien.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Nhân Viên");
            return;
        }
        String ten = txtTenNhanVien.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên");
            return;
        }
        String Cccd = txtCccd.getText();
        if (Cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Căn Cước Công Dân");
            return;
        }
        try {
            Float cccd = Float.parseFloat(txtCccd.getText());
            Float.valueOf(cccd);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Căn Cước Công Dân Phải Là Số");
            return;
        }

        String Email = txtEmail.getText();
        if (Email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Email");
            return;
        }
        String emailRegex = "^[a-z0-9._%+-]+@gmail\\.com$";
//        String emailRegex2 = "^[a-z0-9._%+-]+@fpt\\.edu\\.vn$";
//||!Email.matches(emailRegex2)
        if (!Email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Định dạng email không hợp lệ.");
            return;
        }
        Date ngaySinh = txtNgaySinh.getDate();
        if (ngaySinh == null) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ngày Sinh");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strNgaySinh = dateFormat.format(ngaySinh);
        Calendar dob = Calendar.getInstance();
        dob.setTime(ngaySinh);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (age < 18) {
            JOptionPane.showMessageDialog(this, "Nhân Viên Phải Lớn Hơn 18 Tuổi ");
            return;
        }
        if (age > 50) {
            JOptionPane.showMessageDialog(this, "Nhân Viên Phải Nhỏ Hơn 50 Tuổi ");
            return;
        }
        Boolean gioiTinh;
        if (rdNam.isSelected()) {
            gioiTinh = true;
        } else if (rdNu.isSelected()) {
            gioiTinh = false;
        } else {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giới Tính");
            return;
        }
        String diaChi = txtDiaChi.getText();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Địa Chỉ");
            return;
        }
        String sdt = txtSoDienThoai.getText();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Số Diện Thoại");
            return;
        }
        try {
            Integer sdtt = Integer.parseInt(txtSoDienThoai.getText());
            Integer.valueOf(sdtt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải Là Số");
            return;
        }
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Phải Là 10 số");
            return;
        }
        if (!sdt.startsWith("0")) {
            JOptionPane.showMessageDialog(this, "Số Điện Thoại Bắt Đầu Bằng số 0");
            return;
        }
//        System.out.println(sdt);
//        String regex = "^0\\d{9}&";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher  matcher = pattern.matcher(sdt);
//        if (!matcher.matches()) {
//            JOptionPane.showMessageDialog(this, "Số Điện Thoại Bắt Đầu Bằng số 0");
//            return;
//        }
        Date NgayVaoLam = txtNgayVaoLam.getDate();
        if (NgayVaoLam == null) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ngày Ngày Vào Làm");
            return;
        }
        SimpleDateFormat dateNgayVao = new SimpleDateFormat("yyyy-MM-dd");
        String NgayVao = dateNgayVao.format(NgayVaoLam);

        String vaiTro = txtVaiTro.getText();
        if (vaiTro.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Vai Trò");
            return;
        }
        String maCL = txtCaLam.getText();
        if (maCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Ca Làm");
            return;
        }
        String trangThai = txtTrangThai.getText();
        if (trangThai.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Trạng Thái");
            return;
        }

        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Cập Nhật Nhân Viên Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            NhanVien nhanVien = new NhanVien(ma, ten, Cccd, Email, strNgaySinh, gioiTinh, sdt, NgayVao, vaiTro, maCL, diaChi, trangThai);
            quanLyNhanVien.UpDateNhanVien(nhanVien);
            JOptionPane.showMessageDialog(this, "Cập Nhật Nhân Viên Thành Công");
            loadNhanVien();
        }
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseClicked
        // TODO add your handling code here:
        txtMaNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtDiaChi.setText("");
        txtCaLam.setText("");
        txtNgaySinh.setDateFormatString("");
        txtSoDienThoai.setText("");
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        txtNgayVaoLam.setDate(null);
        txtTrangThai.setText("");
        txtVaiTro.setText("");
        loadCaLamNhanVien();
        loadQuanLyTaiKhoan();
        loadNhanVien();
        txtCccd.setText("");
        txtEmail.setText("");
    }//GEN-LAST:event_btnNewMouseClicked
//-------------------------------Phần Đăng Kí--------------------------------------------------------
    private void btnAddDKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddDKiMouseClicked
        // TODO add your handling code here:
        String ma = txtMaTaiKhoanDki.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Đăng Kí ");
            return;
        }
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllTaiKhoan()) {
            if (taiKhoan.getMaTK().equals(ma)) {
                JOptionPane.showMessageDialog(this, "Không Được Trùng Mã Tài Khoản ");
                return;
            }
        }
        String tenDN = txtDangNhapDKi.getText();
        if (tenDN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên Đăng Kí ");
            return;
        }
        String MatKhau = txtMatKhau.getText();
        if (MatKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mật Khẩu ");
            return;
        }
        String MaNV = txtMaNVDKi.getText();
        if (MaNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Nhân Viên ");
            return;
        }

        TaiKhoan taiKhoan = new TaiKhoan(ma, tenDN, MatKhau, MaNV);
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Thêm Tài Khoản Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.AddTaiKhoan(taiKhoan);
            loadTaiKhoanDKi();
            loadQuanLyTaiKhoan();
        }
    }//GEN-LAST:event_btnAddDKiMouseClicked

    private void btnUpDateDKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpDateDKiMouseClicked
        // TODO add your handling code here:
        String ma = txtMaTaiKhoanDki.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Tài Khoản");
            return;
        }
        String tenDN = txtDangNhapDKi.getText();
        if (tenDN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên Tài Khoản");
            return;
        }
        String MatKhau = txtMatKhau.getText();
        if (MatKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mật Khẩu ");
            return;
        }
        String MaNV = txtMaNVDKi.getText();
        if (MaNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Nhân Viên ");
            return;
        }
        TaiKhoan taiKhoan = new TaiKhoan(ma, tenDN, MatKhau, MaNV);
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Cập Nhật Tài Khoản Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.UpDateTaiKhoan(taiKhoan);
            loadTaiKhoanDKi();
            loadQuanLyTaiKhoan();
        }
    }//GEN-LAST:event_btnUpDateDKiMouseClicked

//    void MK() {
//        int MK = tblTaiKhoanDKi.getSelectedColumn();
//        if (MK ==3) {
//            return;
//        }
//    }

    private void tblTaiKhoanDKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanDKiMouseClicked
        // TODO add your handling code here:
        int i = tblTaiKhoanDKi.getSelectedRow();
        int MK = tblTaiKhoanDKi.getSelectedColumn();
        String ma = (String) tblTaiKhoanDKi.getValueAt(i, 1);
        txtMaTaiKhoanDki.setText(ma);
        String ten = (String) tblTaiKhoanDKi.getValueAt(i, 2);
        txtDangNhapDKi.setText(ten);

        String matKhau = (String) tblTaiKhoanDKi.getValueAt(i, 3);
        txtMatKhau.setText(matKhau);

        String maNV = (String) tblTaiKhoanDKi.getValueAt(i, 4);
        txtMaNVDKi.setText(maNV);
    }//GEN-LAST:event_tblTaiKhoanDKiMouseClicked

    private void btnNewDangKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewDangKiMouseClicked
        // TODO add your handling code here:
        txtMaNVDKi.setText("");
        txtMaTaiKhoanDki.setText("");
        txtMatKhau.setText("");
        txtDangNhapDKi.setText("");
    }//GEN-LAST:event_btnNewDangKiMouseClicked

    private void tblCaLamDkiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaLamDkiMouseClicked
        // TODO add your handling code here:
        int i = tblCaLamDki.getSelectedRow();
//        System.out.println(i);
        String ma = (String) tblCaLam.getValueAt(i, 1);
        txtMaCaLam.setText(ma);

        String ten = (String) tblCaLam.getValueAt(i, 2);
        txtTenCaLam.setText(ten);

        String gioBatDau = (String) tblCaLam.getValueAt(i, 3);
        txtGioBatDau.setText(gioBatDau);

        String gioKetThuc = (String) tblCaLam.getValueAt(i, 4);
        txtGioKetThuc.setText(gioKetThuc);

        String ghiChu = (String) tblCaLam.getValueAt(i, 5);
        txtGhiChu.setText(ghiChu);
    }//GEN-LAST:event_tblCaLamDkiMouseClicked

    private void btnAddCaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCaLamMouseClicked
        // TODO add your handling code here:
        String ma = txtMaCaLam.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Ca Làm");
            return;
        }
        for (TaiKhoan taiKhoan : quanLyNhanVien.getAllCaLam()) {
            if (taiKhoan.getMaCL().equals(ma)) {
                JOptionPane.showMessageDialog(this, "Không Được Trùng Mã Ca Làm");
                return;
            }
        }
        String tenCL = txtTenCaLam.getText();
        if (tenCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên Ca Làm");
            return;
        }
        String gioBatDau = txtGioBatDau.getText();
        if (gioBatDau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giờ Bắt Đầu");
            return;
        }
        String gioketThuc = txtGioKetThuc.getText();
        if (gioketThuc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giờ Kết Thúc");
            return;
        }
        String ghiChu = txtGhiChu.getText();
//        if (ghiChu.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ghi Chú");
//            return;
//        }
        TaiKhoan taiKhoan = new TaiKhoan(ma, tenCL, gioBatDau, gioketThuc, ghiChu);
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Thêm Ca Làm Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.AddCaLam(taiKhoan);
            loadCaLamDKi();
            loadCaLamNhanVien();
        }
    }//GEN-LAST:event_btnAddCaLamMouseClicked

    private void btnUpDateCalLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpDateCalLamMouseClicked
        // TODO add your handling code here:
        String ma = txtMaCaLam.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Ca Làm");
            return;
        }
        String tenCL = txtTenCaLam.getText();
        if (tenCL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Tên Ca Làm");
            return;
        }
        String gioBatDau = txtGioBatDau.getText();
        if (gioBatDau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giờ Bắt Đầu");
            return;
        }
        String gioketThuc = txtGioKetThuc.getText();
        if (gioketThuc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Giờ Kết Thúc");
            return;
        }
        String ghiChu = txtGhiChu.getText();
//        if (ghiChu.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Ghi Chú");
//            return;
//        }
        TaiKhoan taiKhoan = new TaiKhoan(ma, tenCL, gioBatDau, gioketThuc, ghiChu);
        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Cập Nhật Ca Làm Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.UpDateCaLam(taiKhoan);
            loadCaLamDKi();
            loadCaLamNhanVien();
        }
    }//GEN-LAST:event_btnUpDateCalLamMouseClicked

    private void btnDeleteCaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteCaLamMouseClicked
        // TODO add your handling code here:
        String ma = txtMaCaLam.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không Bỏ Trống Mã Ca Làm");
            return;
        }

        int ret = JOptionPane.showConfirmDialog(this, "Bạn Muốn Xoá Ca Làm Hay Không?", "Thông Báo", JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION) {
            quanLyNhanVien.DeleteCaLam(ma);
            loadCaLamDKi();
            loadCaLamNhanVien();
            loadNhanVien();
        }
    }//GEN-LAST:event_btnDeleteCaLamMouseClicked

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked


    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String ma = txtTimKiem.getText();
        if (ma.isEmpty()) {
            loadNhanVien();
        } else {

            quanLyNhanVien.seachNhanVien(ma);
            SeachNhanVien(ma);
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemTaiKhoanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTaiKhoanKeyReleased
        // TODO add your handling code here:
        String ma = txtTimKiemTaiKhoan.getText();

        if (ma.isEmpty()) {
            loadTaiKhoanDKi();
        } else {
            quanLyNhanVien.SeachTaiKhoan(ma);
            loadSeachDKi(ma);
        }
    }//GEN-LAST:event_txtTimKiemTaiKhoanKeyReleased

    private void txtTiemKiemCaLamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiemKiemCaLamKeyReleased
        // TODO add your handling code here:
        String ma = txtTiemKiemCaLam.getText();
        if (ma.isEmpty()) {
            loadCaLamDKi();
        } else {
            quanLyNhanVien.SeachCaLam(ma);
            loadSeachCaLamDki(ma);
        }
    }//GEN-LAST:event_txtTiemKiemCaLamKeyReleased

    private void tblCaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaLamMouseClicked
        // TODO add your handling code here:
        int i = tblCaLam.getSelectedRow();
        String ma = (String) tblCaLam.getValueAt(i, 1);
        quanLyNhanVien.seachMaCLNhanVien(ma);
        SeachMaCLNhanVien(ma);
    }//GEN-LAST:event_tblCaLamMouseClicked

    private void tblQuanLyTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyTaiKhoanMouseClicked
        // TODO add your handling code here:
        int i = tblQuanLyTaiKhoan.getSelectedRow();
        String ma = (String) tblQuanLyTaiKhoan.getValueAt(i, 4);
        quanLyNhanVien.seachNhanVien(ma);
        SeachNhanVien(ma);
    }//GEN-LAST:event_tblQuanLyTaiKhoanMouseClicked

    private void XuattExelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XuattExelMouseClicked
        // TODO add your handling code here:
        try {

//            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("NhanVien");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(2);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SACH NHAN VIEN");

            row = sheet.createRow(6);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Stt");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("MaNV");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("TênNV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Cccd");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày Sinh");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Giới Tính");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("SĐT");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Ngày Vào");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Vai Trò");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Mã CL");
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Trạng Thái");

            try {

                ArrayList<NhanVien> listKH = quanLyNhanVien.getAllNhanVien();
                if (listKH != null) {
                    int s = listKH.size();
                    for (int i = 0; i < s; i++) {
                        NhanVien nhanVien = listKH.get(i);
                        row = sheet.createRow(10 + i);

                        cell = row.createCell(0, CellType.NUMERIC);
                        cell.setCellValue(i + 1);

                        cell = row.createCell(1, CellType.STRING);
                        cell.setCellValue(nhanVien.getMaNV());

                        cell = row.createCell(2, CellType.STRING);
                        cell.setCellValue(nhanVien.getTenNV());

                        cell = row.createCell(3, CellType.STRING);
                        cell.setCellValue(nhanVien.getCccd());

                        cell = row.createCell(4, CellType.STRING);
                        cell.setCellValue(nhanVien.getEmail());

                        cell = row.createCell(5, CellType.STRING);
                        cell.setCellValue(nhanVien.getNgaySinh());

                        cell = row.createCell(6, CellType.STRING);
                        cell.setCellValue(nhanVien.isGioiTinh() ? "Nam" : "Nữ");

                        cell = row.createCell(7, CellType.STRING);
                        cell.setCellValue(nhanVien.getSĐT());

                        cell = row.createCell(8, CellType.STRING);
                        cell.setCellValue(nhanVien.getNgayVaoLam());

                        cell = row.createCell(9, CellType.STRING);
                        cell.setCellValue(nhanVien.getVaiTro());

                        cell = row.createCell(10, CellType.STRING);
                        cell.setCellValue(nhanVien.getMaCL());

                        cell = row.createCell(11, CellType.STRING);
                        cell.setCellValue(nhanVien.getTrangThai());

                    }
                }

                // LƯU file exel
                File file = new File("D://NhanVien.xlsx");
                try {
                    FileOutputStream fis = new FileOutputStream(file);
                    workbook.write(fis);
                    fis.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "IN Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Loi in");
        }
    }//GEN-LAST:event_XuattExelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVien;
    private javax.swing.JButton XuattExel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCaLam;
    private javax.swing.JButton btnAddDKi;
    private javax.swing.JButton btnDeleteCaLam;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNewCaLam;
    private javax.swing.JButton btnNewDangKi;
    private javax.swing.JButton btnNghiLam;
    private javax.swing.JButton btnUpDateCalLam;
    private javax.swing.JButton btnUpDateDKi;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnCaLam;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JPanel pnQuanLyTK;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tblCaLam;
    private javax.swing.JTable tblCaLamDki;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblQuanLyTaiKhoan;
    private javax.swing.JTable tblTaiKhoanDKi;
    private javax.swing.JTextField txtCaLam;
    private javax.swing.JTextField txtCccd;
    private javax.swing.JTextField txtDangNhapDKi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGioBatDau;
    private javax.swing.JTextField txtGioKetThuc;
    private javax.swing.JTextField txtMaCaLam;
    private javax.swing.JTextField txtMaNVDKi;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaTaiKhoanDki;
    private javax.swing.JPasswordField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private com.toedter.calendar.JDateChooser txtNgayVaoLam;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenCaLam;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTiemKiemCaLam;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemTaiKhoan;
    private javax.swing.JTextField txtTrangThai;
    private javax.swing.JTextField txtVaiTro;
    // End of variables declaration//GEN-END:variables
}
