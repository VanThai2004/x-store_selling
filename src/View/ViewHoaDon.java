/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.HoaDon;
import Service.QuanLyHoaDon;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienBB
 */
public class ViewHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form ViewHoaDon
     */
    DefaultTableModel defaultTableModel;
    QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();

    public ViewHoaDon() {
        initComponents();
        loadDataHoaDon();
        loadDataHoaDonChiTiet();
        rdTatCa.setSelected(true);
    }

    void loadDataHoaDon() {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadMaHoaDon()) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

//    void loadDataHoaDonChiTiet() {
//        defaultTableModel = (DefaultTableModel) tblHoaDonChiTiet.getModel();
//        defaultTableModel.setRowCount(0);
//        int stt = 1;
//        for (HoaDon hoaDon1 : quanLyHoaDon.loadHoaDonChiTiet()) {
//            defaultTableModel.addRow(new Object[]{
//                stt++,
//                hoaDon1.getIdSP(),
//                hoaDon1.getMaSP(),
//                hoaDon1.getTenSP(),
//                hoaDon1.getSoLuong(),
//                hoaDon1.getGiaBan(),
//                hoaDon1.getPhanTram(),
//                hoaDon1.getGiaSau(),
//                hoaDon1.thanhTien(Integer.valueOf(hoaDon1.getSoLuong()), Float.valueOf(hoaDon1.getGiaSau()).intValue())
//            });
//        }
//    }
    void loadDataHoaDonChiTiet() {
        defaultTableModel = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon1 : quanLyHoaDon.loadHoaDonChiTiet()) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon1.getIdSP(),
                hoaDon1.getMaSP(),
                hoaDon1.getTenSP(),
                hoaDon1.getSoLuong(),
                hoaDon1.getGiaBan(),
                hoaDon1.getPhanTram(),
                hoaDon1.getGiaSau(),
                hoaDon1.thanhTien(Integer.valueOf(hoaDon1.getSoLuong()), Float.valueOf(hoaDon1.getGiaSau()).intValue())
            });
        }
    }

    void loadMaHDDataHoaDonChiTiet(String MaHDD) {
        defaultTableModel = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon1 : quanLyHoaDon.loadMaHDSeachHDCT(MaHDD)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon1.getIdSP(),
                hoaDon1.getMaSP(),
                hoaDon1.getTenSP(),
                hoaDon1.getSoLuong(),
                hoaDon1.getGiaBan(),
                hoaDon1.getPhanTram(),
                hoaDon1.getGiaSau(),
                hoaDon1.thanhTien(Integer.valueOf(hoaDon1.getSoLuong()), Float.valueOf(hoaDon1.getGiaSau()).intValue())
            });
        }
    }

    void loadMaSPDataHoaDonChiTiet(String MaSP) {
        defaultTableModel = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadIDSPSeachHDCT(MaSP)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getIdSP(),
                hoaDon.getMaSP(),
                hoaDon.getTenSP(),
                hoaDon.getSoLuong(),
                hoaDon.getGiaBan(),
                hoaDon.getPhanTram(),
                hoaDon.getGiaSau(),
                hoaDon.thanhTien(Integer.valueOf(hoaDon.getSoLuong()), Float.valueOf(hoaDon.getGiaSau()).intValue())
            });
        }
    }

    void loadDaThanhToan() {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachDaThanhToan()) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadDaHuy() {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachDaHuy()) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadChoThanhToan() {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachChoThanhToan()) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadSeachMaHoaDon(String maHD) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachMaHoaDon(maHD)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadSeachMaNhanVien(String maNV) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 0;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachMaNhanVien(maNV)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadSeachNgayBatDau(String ngayBD) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 0;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachNgayBD(ngayBD)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }

    void loadSeachNgayKetThuc(String ngayKT) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 0;
        for (HoaDon hoaDon : quanLyHoaDon.loadSeachNgayBD(ngayKT)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
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
        hoaDon = new javax.swing.JPanel();
        pnHoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon1 = new javax.swing.JTable();
        pnGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dcNgayBatDau = new com.toedter.calendar.JDateChooser();
        dcNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnTimKiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rdTatCa = new javax.swing.JRadioButton();
        rdDaHuy = new javax.swing.JRadioButton();
        rdDaThanhToan = new javax.swing.JRadioButton();
        rdChoThanhToán = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hoaDon.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tblHoaDon1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stt	", "Mã HD	", "Mã NV", "Mã KH	 ", "Mã VC	 ", "Ngày tạo	 ", "Tổng Tiền	 ", "Trạng thái"
            }
        ));
        tblHoaDon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon1);

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pnGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Hoá Đơn Chi Tiết", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT	", "IdSP	", "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Giảm Giá", "Giá Sau", "Thanh Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout pnGioHangLayout = new javax.swing.GroupLayout(pnGioHang);
        pnGioHang.setLayout(pnGioHangLayout);
        pnGioHangLayout.setHorizontalGroup(
            pnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnGioHangLayout.setVerticalGroup(
            pnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGioHangLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng điều khiển", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel1.setText("Tìm theo Mã HD:");

        txtMaHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHoaDonKeyReleased(evt);
            }
        });

        jLabel2.setText("Tìm theo ID SP: ");

        txtMaNhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaNhanVienKeyReleased(evt);
            }
        });

        jLabel3.setText("Ngày bắt đầu:");

        jLabel4.setText("Ngày kết thúc: ");

        dcNgayBatDau.setDateFormatString("yyyy-MM-dd");
        dcNgayBatDau.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dcNgayBatDauAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        dcNgayBatDau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dcNgayBatDauKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dcNgayBatDauKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcNgayBatDauKeyTyped(evt);
            }
        });

        dcNgayKetThuc.setDateFormatString("yyyy-MM-dd");
        dcNgayKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dcNgayKetThucKeyReleased(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addComponent(btnTimKiem)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        buttonGroup1.add(rdTatCa);
        rdTatCa.setText("Tất cả");
        rdTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdTatCaMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdDaHuy);
        rdDaHuy.setText("Đã hủy");
        rdDaHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaHuyActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdDaThanhToan);
        rdDaThanhToan.setText("Đã thanh toán");
        rdDaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDaThanhToanActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdChoThanhToán);
        rdChoThanhToán.setText("Chờ Thanh Toán");
        rdChoThanhToán.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChoThanhToánActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(rdTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(rdDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(rdDaHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(rdChoThanhToán, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTatCa)
                    .addComponent(rdDaHuy)
                    .addComponent(rdDaThanhToan)
                    .addComponent(rdChoThanhToán))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout hoaDonLayout = new javax.swing.GroupLayout(hoaDon);
        hoaDon.setLayout(hoaDonLayout);
        hoaDonLayout.setHorizontalGroup(
            hoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(hoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonLayout.createSequentialGroup()
                        .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(hoaDonLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(415, Short.MAX_VALUE))
                    .addComponent(pnGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        hoaDonLayout.setVerticalGroup(
            hoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(hoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHoaDonKeyReleased
        // TODO add your handling code here:
        String maHD = txtMaHoaDon.getText();
        quanLyHoaDon.loadSeachMaHoaDon(maHD);
        quanLyHoaDon.loadMaHDSeachHDCT(maHD);
        loadSeachMaHoaDon(maHD);
        loadMaHDDataHoaDonChiTiet(maHD);
    }//GEN-LAST:event_txtMaHoaDonKeyReleased

    private void rdTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdTatCaMouseClicked
        // TODO add your handling code here:

//        if (rdDaThanhToan.isSelected()) {
        loadDataHoaDon();
        loadDataHoaDonChiTiet();
//        }
    }//GEN-LAST:event_rdTatCaMouseClicked

    private void txtMaNhanVienKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaNhanVienKeyReleased
        // TODO add your handling code here:
        String idsp = txtMaNhanVien.getText();
//        String maSP = txtMaNhanVien.getText();
//        for (HoaDon hoaDon1 : quanLyHoaDon.loadMaHoaDon()) {
//            if (hoaDon1.getMaNV().equals(maNV)) {

        quanLyHoaDon.loadIDSPSeachHDCT(idsp);
        loadMaSPDataHoaDonChiTiet(idsp);
//            } else {
//                quanLyHoaDon.loadMaSPSeachHDCT(maSP);
//                loadMaSPDataHoaDonChiTiet(maSP);
//            }
//        }
    }//GEN-LAST:event_txtMaNhanVienKeyReleased

    private void rdDaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaThanhToanActionPerformed
        // TODO add your handling code here:
        loadDaThanhToan();
    }//GEN-LAST:event_rdDaThanhToanActionPerformed

    private void rdDaHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDaHuyActionPerformed
        // TODO add your handling code here:
        loadDaHuy();
    }//GEN-LAST:event_rdDaHuyActionPerformed

    private void rdChoThanhToánActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChoThanhToánActionPerformed
        // TODO add your handling code here:
        loadChoThanhToan();
    }//GEN-LAST:event_rdChoThanhToánActionPerformed

    void loadTimKiem(String a, String b) {
        defaultTableModel = (DefaultTableModel) tblHoaDon1.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (HoaDon hoaDon : quanLyHoaDon.timKiemTheoNgay(a, b)) {
            defaultTableModel.addRow(new Object[]{
                stt++,
                hoaDon.getMaHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaKH(),
                hoaDon.getMaVC(),
                hoaDon.getNgayTao(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai(),});
        }
    }
    private void dcNgayBatDauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcNgayBatDauKeyReleased

    }//GEN-LAST:event_dcNgayBatDauKeyReleased

    private void dcNgayBatDauAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dcNgayBatDauAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_dcNgayBatDauAncestorAdded

    private void dcNgayBatDauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcNgayBatDauKeyPressed

    }//GEN-LAST:event_dcNgayBatDauKeyPressed

    private void dcNgayBatDauKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcNgayBatDauKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcNgayBatDauKeyTyped

    private void dcNgayKetThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcNgayKetThucKeyReleased

    }//GEN-LAST:event_dcNgayKetThucKeyReleased

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        Date a = dcNgayBatDau.getDate();
        Date b = dcNgayKetThuc.getDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(a);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(b);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayBatDau = dateFormat.format(a);
        String ngayKetThuc = dateFormat.format(b);

        if (a.after(b)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            return;
        }
            if (ngayBatDau != null && ngayKetThuc == null) {
                quanLyHoaDon.loadSeachNgayBD(ngayBatDau);
                loadSeachNgayBatDau(ngayBatDau);
            } else if (ngayBatDau == null && ngayKetThuc != null) {
                quanLyHoaDon.loadSeachNgayKT(ngayKetThuc);
                loadSeachNgayKetThuc(ngayKetThuc);
            } else {
                quanLyHoaDon.timKiemTheoNgay(ngayBatDau, ngayKetThuc);
                loadTimKiem(ngayBatDau, ngayKetThuc);
            }
        


    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblHoaDon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon1MouseClicked
        // TODO add your handling code here:\
        int i = tblHoaDon1.getSelectedRow();
        String ma = (String) tblHoaDon1.getValueAt(i, 1);
        quanLyHoaDon.loadMaHDSeachHDCT(ma);
        loadMaHDDataHoaDonChiTiet(ma);
    }//GEN-LAST:event_tblHoaDon1MouseClicked

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
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dcNgayBatDau;
    private com.toedter.calendar.JDateChooser dcNgayKetThuc;
    private javax.swing.JPanel hoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnGioHang;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JRadioButton rdChoThanhToán;
    private javax.swing.JRadioButton rdDaHuy;
    private javax.swing.JRadioButton rdDaThanhToan;
    private javax.swing.JRadioButton rdTatCa;
    private javax.swing.JTable tblHoaDon1;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaNhanVien;
    // End of variables declaration//GEN-END:variables
}
