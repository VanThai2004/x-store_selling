/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.HoaDon;
import Model.KhachHang;
import Model.SanPham;
import Model.Voucher;
import Service.QuanLyBanHang;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.PrinterName;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author TienBB
 */
public class ViewBanHang extends javax.swing.JFrame {

    /**
     * Creates new form ViewBanHang
     */
    DefaultTableModel model;
    QuanLyBanHang ql = new QuanLyBanHang();
    private Double bHeight = 0.0;

    public ViewBanHang(String layMaNV) {
        initComponents();
        txtMaNV.setText(layMaNV);
        txtMaNV.setEnabled(false);
        txtTongTien.setEnabled(false);
        txtTienThua.setEnabled(false);
        btnInHD.setEnabled(false);
        loadcbVC();
        loadHoaDon();
        loadSanPham(ql.getListSanPham());
        txtTongTien.setText("");
    }

    private ViewBanHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void loadcbVC() {
        cbVoucher.removeAllItems();
        cbVoucher.addItem("Mời chọn");
        for (Voucher v : ql.getListV()) {
            cbVoucher.addItem(v.getTenVC());
        }
    }

    void tongTien() {
        String mhd = txtMaHD.getText();
        int tongTien = 0;
        for (HoaDon h : ql.getListGioHang(mhd)) {
            tongTien += h.thanhTien(Integer.valueOf(h.getSoLuong()), Float.valueOf(h.getGiaSau()).intValue());
        }
        String a = cbVoucher.getSelectedItem().toString();
        for (Voucher v : ql.getListV()) {
            if (v.getTenVC().equals(a)) {
                tongTien -= Float.valueOf(v.getGiamGia()).intValue();
                break;
            }
        }
        for (Voucher v : ql.getListVV(txtMaKH.getText())) {

            if (v.getTenVC().equals(a)) {
                tongTien -= Float.valueOf(v.getGiamGia()).intValue();
                break;

            }
        }
        txtTongTien.setText(String.valueOf(tongTien));
        txtTienKD.setText("");
        txtTienThua.setText("");
    }

    void loadHoaDon() {
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (HoaDon hd : ql.getListHoaDon()) {
            if (hd.getTrangThai().equals("Chờ thanh toán")) {
                stt++;
                model.addRow(new Object[]{
                    stt,
                    hd.getMaHD(),
                    hd.getNgayTao(),
                    hd.getMaNV(),
                    hd.getTrangThai()
                });
            }
        }
    }

    void loadSanPham(ArrayList<SanPham> list) {
        model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        for (SanPham sp : list) {
            if (!sp.getSoLuong().equals("0")) {
                model.addRow(new Object[]{
                    sp.getIdspct(), sp.getMaSanPham(), sp.getTenSanPham(),
                    sp.getMauSac(), sp.getSize(), sp.getChatLieu(),
                    sp.getHang(), sp.getSoLuong(), Double.valueOf(sp.getGiaBan()).intValue(),
                    Float.valueOf(sp.getPhanTram()).intValue(), sp.giaSau()
                });
            }

        }
    }

    void loadGioHang(String mhd) {

        model = (DefaultTableModel) tblGioHang.getModel();
        model.setRowCount(0);
        int stt = 0;
        for (HoaDon sp : ql.getListGioHang(mhd)) {
            stt++;
            model.addRow(new Object[]{
                stt, sp.getIdSP(), sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getPhanTram(),
                sp.getGiaSau(),
                sp.thanhTien(Integer.valueOf(sp.getSoLuong()), Float.valueOf(sp.getGiaSau()).intValue())
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

        BanHang = new javax.swing.JPanel();
        pnHoaDon = new javax.swing.JPanel();
        btnTaoHD = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        pnThongTin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbVoucher = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTienKD = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnInHD = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        pnGioHang = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        pnSanPham = new javax.swing.JPanel();
        aaaa = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BanHang.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnTaoHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHD.setText("Tạo hóa đơn");
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày tạo", "Mã NV", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout pnHoaDonLayout = new javax.swing.GroupLayout(pnHoaDon);
        pnHoaDon.setLayout(pnHoaDonLayout);
        pnHoaDonLayout.setHorizontalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHD, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnHoaDonLayout.setVerticalGroup(
            pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHoaDonLayout.createSequentialGroup()
                .addGroup(pnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnHoaDonLayout.createSequentialGroup()
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Mã HD:");

        jLabel3.setText("Ngày tạo:");

        jLabel4.setText("Mã NV:");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel5.setText("Mã KH:");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKHKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });

        jLabel6.setText("Voucher:");

        cbVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVoucherItemStateChanged(evt);
            }
        });
        cbVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbVoucherMouseClicked(evt);
            }
        });
        cbVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVoucherActionPerformed(evt);
            }
        });
        cbVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbVoucherKeyReleased(evt);
            }
        });

        jLabel7.setText("Tổng tiền:");

        jLabel8.setText("Tiền khách đưa:");

        txtTienKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKDKeyReleased(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnInHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInHD.setText("In hóa đơn");
        btnInHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHDActionPerformed(evt);
            }
        });

        jLabel9.setText("Tiền thừa:");

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(29, 29, 29)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTienKD)
                            .addComponent(txtNgayTao)
                            .addComponent(txtMaHD)
                            .addComponent(txtMaNV)
                            .addComponent(txtMaKH)
                            .addComponent(cbVoucher, 0, 194, Short.MAX_VALUE)
                            .addComponent(txtTongTien)
                            .addComponent(txtTienThua))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtTienKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnGioHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Id san pham", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Phần Trăm", "Giá Sau", "Thành tiền"
            }
        ));
        tblGioHang.setPreferredSize(new java.awt.Dimension(75, 80));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout pnGioHangLayout = new javax.swing.GroupLayout(pnGioHang);
        pnGioHang.setLayout(pnGioHangLayout);
        pnGioHangLayout.setHorizontalGroup(
            pnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGioHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnGioHangLayout.setVerticalGroup(
            pnGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnGioHangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IDSP", "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Kích cỡ", "Chất liệu", "Hãng", "Số lượng", "Giá bán", "Giảm (%)", "Giá sau"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        aaaa.setViewportView(tblSanPham);

        jLabel1.setText("Tìm kiếm theo tên");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aaaa)
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(aaaa, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout BanHangLayout = new javax.swing.GroupLayout(BanHang);
        BanHang.setLayout(BanHangLayout);
        BanHangLayout.setHorizontalGroup(
            BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BanHangLayout.createSequentialGroup()
                        .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BanHangLayout.createSequentialGroup()
                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );
        BanHangLayout.setVerticalGroup(
            BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnGioHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 380, Short.MAX_VALUE))
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        // TODO add your handling code here:
        String mhd = "HD" + (ql.getListHoaDon().size() + 1);
        String ngayTao = java.time.LocalDate.now().toString();
        String mnv = txtMaNV.getText();
        String tt = "Chờ thanh toán";
        HoaDon h = new HoaDon(mhd, ngayTao, null, mnv, null, null, tt, null, null, null, null, null, null, null, null);
        ql.themHoaDon(h);
        loadHoaDon();
        txtMaHD.setText(h.getMaHD());
        txtNgayTao.setText(h.getNgayTao());
        txtMaNV.setText(h.getMaNV());
        loadGioHang(mhd);
        tongTien();
        btnInHD.setEnabled(false);
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int i = tblHoaDon.getSelectedRow();
        loadGioHang(String.valueOf(tblHoaDon.getValueAt(i, 1)));

        txtMaHD.setText((String) tblHoaDon.getValueAt(i, 1));
        txtNgayTao.setText((String) tblHoaDon.getValueAt(i, 2));
        txtMaNV.setText((String) tblHoaDon.getValueAt(i, 3));
        tongTien();
    }//GEN-LAST:event_tblHoaDonMouseClicked
    int checkSP() {
        if (txtMaHD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
            return 2;
        }
        return 3;
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (checkSP() == 2) {
            return;
        }
        try {

            int i = tblSanPham.getSelectedRow();
            String a = JOptionPane.showInputDialog("Mời nhập số lượng:");
            String id = (String) tblSanPham.getValueAt(i, 0);
            String soLuong = (String) tblSanPham.getValueAt(i, 7);
            String giaSau = String.valueOf(tblSanPham.getValueAt(i, 10));
//            String phanTram = (String) tblSanPham.getValueAt(i, 9);

            String phanTram = null;
            String maCP = null;
            for (SanPham s : ql.getListSanPham()) {
                if (tblSanPham.getValueAt(i, 0).equals(s.getIdspct())) {
                    maCP = s.getMaCP();
                    phanTram = s.getPhanTram();
                    break;
                }
            }
            if (Integer.valueOf(a) <= Integer.valueOf(soLuong) && Integer.valueOf(a) > 0) {
                Integer so = Integer.valueOf(soLuong) - Integer.valueOf(a);
                String so2 = so.toString();
                ql.suaSanPham(so2, id);
                loadSanPham(ql.getListSanPham());
                String maHD = txtMaHD.getText();
                for (HoaDon h : ql.getListGioHang(maHD)) {
                    if (id.equals(h.getIdSP())) {
                        Integer a2 = Integer.valueOf(a) + Integer.valueOf(h.getSoLuong());
                        ql.suaGioHang(String.valueOf(a2), id, maHD);
                        loadGioHang(maHD);
                        tongTien();
                        return;
                    }
                }

                HoaDon h = new HoaDon(maHD, null, null, null, null, null, null, id, null, null, a, giaSau, phanTram, maCP, null);
                ql.themGioHang(h);
                loadGioHang(maHD);

            } else {
                JOptionPane.showMessageDialog(this, "Mời nhập lại!");
            }
            tongTien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thao tác. Vui long nhập lại!");
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:

        try {
            int i = tblGioHang.getSelectedRow();
            String mhd = txtMaHD.getText();
            String idsp = String.valueOf(tblGioHang.getValueAt(i, 1));

            String b = JOptionPane.showInputDialog("Mời nhập lại số lượng:");

            String slgh = (String) tblGioHang.getValueAt(i, 4);
            try {
                for (SanPham s : ql.getListSanPham()) {
                    if (idsp.equals(s.getIdspct())) {
                        if (Integer.valueOf(b) > (Integer.valueOf(s.getSoLuong()) + Integer.valueOf(slgh))) {
                            JOptionPane.showMessageDialog(this, "nhap lai so luong");
                            return;
                        }
                        Integer sL = Integer.valueOf(s.getSoLuong()) + Integer.valueOf(slgh) - Integer.valueOf(b);
                        String sl = sL.toString();
                        ql.suaSanPham(sl, idsp);
                        loadSanPham(ql.getListSanPham());
                    }
                }
            } catch (Exception e) {
            }

            if (Integer.valueOf(b) == 0) {
                ql.xoaGioHang(idsp, mhd);
                loadGioHang(mhd);
            } else {
                ql.suaGioHang(b, idsp, mhd);
                loadGioHang(mhd);
            }

            cbVoucher.setSelectedIndex(0);
            tongTien();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thao tác. Bé Khánh đừng nghịch nhé(^_^)");
        }

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        ArrayList<SanPham> list = new ArrayList<>();
        for (SanPham s : ql.getListSanPham()) {
            if (txtTimKiem.getText().contains(s.getMaSanPham()) || txtTimKiem.getText().contains(s.getIdspct())) {
                list.add(s);
            }
        }
        if (txtTimKiem.getText().equals("")) {
            loadSanPham(ql.getListSanPham());
        } else {
            loadSanPham(list);
        }

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed
    int checkHuy(String mhd) {
        ArrayList<HoaDon> list = ql.getListGioHang(mhd);
        for (HoaDon g : list) {
            if (list.size() > 0) {
                JOptionPane.showMessageDialog(this, "Mời bỏ hết sản phẩm ra giỏ hàng");
                return 1;
            }
        }

        return 2;
    }
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:

        int i = tblHoaDon.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn cần hủy");
            return;
        }
        String mhd = (String) tblHoaDon.getValueAt(i, 1);
        // String trangThai = "Đã thanh toán";
        if (checkHuy(mhd) == 1) {
            return;
        }
        Boolean checkHuyHD = ql.huyHoaDon(mhd);
        if (checkHuyHD) {
            JOptionPane.showMessageDialog(this, "Huỷ thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Huỷ thất bại");

        }
        loadHoaDon();
        loadGioHang(mhd);
        tongTien();


    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtTienKDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKDKeyReleased
        // TODO add your handling code here
        String tienKD = txtTienKD.getText();
        String tongTien = txtTongTien.getText();
        int tienThua = Integer.valueOf(tienKD) - Integer.valueOf(tongTien);
        txtTienThua.setText(String.valueOf(tienThua));
    }//GEN-LAST:event_txtTienKDKeyReleased

    private void cbVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVoucherActionPerformed
        // TODO add your handling code here:
        try {
            if (txtMaHD.getText().equals("")) {
                cbVoucher.setSelectedIndex(0);
                return;
            }
            if (txtTongTien.getText().equals("")) {
                return;
            }
            for (Voucher v : ql.getListV()) {
                if (cbVoucher.getSelectedItem().toString().equals(v.getTenVC())) {
                    String a = v.getDieuKien();
                    String b = txtTongTien.getText();
                    if (Float.valueOf(a) > Float.valueOf(b)) {
                        JOptionPane.showMessageDialog(this, "Không đủ điều kiện");
                        cbVoucher.setSelectedIndex(0);
                        tongTien();
                        return;
                    }
                }

            }
            String mKH = txtMaKH.getText();
            for (Voucher v : ql.getListVV(mKH)) {
                if (cbVoucher.getSelectedItem().toString().equals(v.getTenVC())) {
                    if (Integer.valueOf(v.getDieuKien()) > Integer.valueOf(txtTongTien.getText())) {
                        JOptionPane.showMessageDialog(this, "Không đủ điều kiện");
                        cbVoucher.setSelectedIndex(0);
                        tongTien();
                        return;
                    }
                }
            }
            tongTien();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbVoucherActionPerformed

    private void txtMaKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHKeyReleased
        // TODO add your handling code here:
        for (KhachHang k : ql.getKhachHang()) {
            if (txtMaKH.getText().equals(k.getMaKH())) {
                cbVoucher.removeAllItems();
                cbVoucher.addItem("Mời chọn");
                for (Voucher v : ql.getListVV(k.getMaKH())) {
                    cbVoucher.addItem(v.getTenVC());
                }
//                if (ql.getListVV(txtMaKH.getText()).size() == 0) {
//                    loadcbVC();
//                }
                return;
            }
        }
        loadcbVC();
    }//GEN-LAST:event_txtMaKHKeyReleased

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed
    int checkThanhToan() {
        if (txtMaHD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn");
            return 0;
        } else if (ql.getListGioHang(txtMaHD.getText()).size() == 0) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng không có gì");
            return 0;
        } else if (txtTienKD.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa có tiền khách đưa");
            return 0;
        } else if (Integer.valueOf(txtTienThua.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa chưa đủ");
            return 0;
        }
        return 1;
    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (checkThanhToan() == 0) {
            return;
        }
        String maVC = null;
        for (Voucher v : ql.getListV()) {
            String a = cbVoucher.getSelectedItem() + "";

            if (v.getTenVC().equals(a)) {
                maVC = v.getMaVC();
                break;
            }
        }
        for (Voucher v : ql.getListVV(txtMaKH.getText())) {
            String a = cbVoucher.getSelectedItem() + "";

            if (v.getTenVC().equals(a)) {
                maVC = v.getMaVC();
                break;
            }
        }

        String maKH = null;
        if (!txtMaKH.getText().equals("")) {
            maKH = txtMaKH.getText();
        }

        HoaDon h = new HoaDon(txtMaHD.getText(), txtNgayTao.getText(), maKH, txtMaNV.getText(), maVC, txtTongTien.getText(), null, null, null, null, null, null, null, null, null);
        if (ql.ThanhToan(h)) {
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thanh toán thất bại");

        }
        loadHoaDon();
        loadGioHang(txtMaHD.getText());
        btnInHD.setEnabled(true);
        loadcbVC();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVoucherItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbVoucherItemStateChanged

    private void cbVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbVoucherMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbVoucherMouseClicked

    private void cbVoucherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbVoucherKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVoucherKeyReleased

    private void btnInHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHDActionPerformed
        // TODO add your handling code here:

        String path = "D://";
//        JFileChooser j = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int x = j.showSaveDialog(this);
//         
//        if (x==JFileChooser.APPROVE_OPTION) {
//            path = j.getSelectedFile().getPath();
//            
//        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "HoaDon.pdf"));
            doc.open();
            doc.add(new Paragraph("------------CUA HANG GIAY XSTORE XIN KINH CHAO --------------------"));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Ma Hoa Don:     " + txtMaHD.getText()));
            doc.add(new Paragraph("Ngay Tao:       " + txtNgayTao.getText()));
            doc.add(new Paragraph("Ma Nhan Vien:   " + txtMaNV.getText()));
            doc.add(new Paragraph("Ma Khach Hang:  " + txtMaKH.getText()));
            doc.add(new Paragraph("Tong Tien:      " + txtTongTien.getText()));
            doc.add(new Paragraph("Tien Khach Dua: " + txtTienKD.getText()));
            doc.add(new Paragraph("Tien Thua:      " + txtTienThua.getText()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("---------------CUA HANG GIAY XSTORE XIN CAM ON --------------------"));
            doc.add(new Paragraph("Dia Chi:  57P.QUAN HOA, QUAN HOA, CAU GIAY, HA NOI"));
            doc.add(new Paragraph("So Dien Thoai Lien He:  0912387654"));
            doc.add(new Paragraph("--------------------------HEN GAP LAI QUY KHACH----------------------------"));

//            PdfPTable tbl = new PdfPTable(5);
//            tbl.addCell("STT");
//            tbl.addCell("Mã HD");
//            tbl.addCell("Ngày Tạo");
//            tbl.addCell("Mã NV");
//            tbl.addCell("Trạng Thái");
//            tbl.addCell("STT");
//            tbl.addCell("STT");
//            tbl.addCell("STT");
//            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
//                String id = tblHoaDon.getValueAt(i, 0).toString();
//                String MaHD = tblHoaDon.getValueAt(i, 1).toString();
//                String NgayTao = tblHoaDon.getValueAt(i, 2).toString();
//                String MaNV = tblHoaDon.getValueAt(i, 3).toString();
//                String TrangThai = tblHoaDon.getValueAt(i, 4).toString();
////                String diaChi = "--------------- Trịnh Văn Bô---------------";
//                tbl.addCell(id);
//                tbl.addCell(MaHD);
//                tbl.addCell(NgayTao);
//                tbl.addCell(MaNV);
//                tbl.addCell(TrangThai);
//                tbl.addCell(diaChi);
//            }
//            doc.add(tbl);
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "In Thất Bại");
            return;
        }
        doc.close();
        JOptionPane.showMessageDialog(this, "In thành công");
        txtMaHD.setText("");
        txtNgayTao.setText("");
        txtMaKH.setText("");
        txtTongTien.setText("");
        txtTienKD.setText("");
        txtTienThua.setText("");
        cbVoucher.setSelectedIndex(1);
        
    }//GEN-LAST:event_btnInHDActionPerformed

    private void txtMaKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHKeyPressed

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
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BanHang;
    private javax.swing.JScrollPane aaaa;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnInHD;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> cbVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnGioHang;
    private javax.swing.JPanel pnHoaDon;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnThongTin;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTienKD;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
