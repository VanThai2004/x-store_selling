/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SanPham;
import Service.QuanLySanPham;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienBB
 */
public class ViewSanPham extends javax.swing.JFrame {

    /**
     * Creates new form ViewSanPham
     */
    DefaultTableModel defau;
    QuanLySanPham qlsp = new QuanLySanPham();
    String loadImg = null;

    public ViewSanPham() {
        initComponents();
        force();
        loadSP();
        loadCTSP();
        loadSelectThuocTinh();
//        hienThiSP();
//        hienThiCTSP();
    }

    void force() {
        txtSoLuongTong.setEnabled(false);
        txtIDSP.setEnabled(false);
        txtIdThuocTinh.setEnabled(false);
        rdMauSac.setSelected(true);
        loadTblMauSac();
        cbConHang.setEnabled(false);
//        pCTSP.setVisible(false);
    }

    void loadSP() {
        defau = (DefaultTableModel) tblSanPham.getModel();
        defau.setRowCount(0);
        int stt = 0;
        for (SanPham sp : qlsp.getListSanPham()) {
            stt++;
            defau.addRow(new Object[]{
                stt,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                qlsp.getSoLuongTong(sp.getMaSanPham()),
                sp.getTrangThai()
            });
        }
    }

    void loadCTSP() {
        defau = (DefaultTableModel) tblCTSP.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getListCTSP()) {
            defau.addRow(new Object[]{
                sp.getIdspct(),
                sp.getMaSanPham(),
                sp.getNgayNhap(),
                sp.getMauSac(),
                sp.getSize(),
                sp.getChatLieu(),
                sp.getHang(),
                sp.getSoLuong(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getHinhAnh()
            });
        }
    }

    void loadCTSPDaHet() {
        defau = (DefaultTableModel) tblCTSP.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getListCTSPDaHet()) {
            defau.addRow(new Object[]{
                sp.getIdspct(),
                sp.getMaSanPham(),
                sp.getNgayNhap(),
                sp.getMauSac(),
                sp.getSize(),
                sp.getChatLieu(),
                sp.getHang(),
                sp.getSoLuong(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getHinhAnh()
            });
        }
    }

    void loadCTSPTheoMa(String a) {
        defau = (DefaultTableModel) tblCTSP.getModel();
        defau.setRowCount(0);
        int i = tblCTSP.getSelectedRow();
        for (SanPham sp : qlsp.getListCTSPTheoMaSP(a)) {
            defau.addRow(new Object[]{
                sp.getIdspct(),
                sp.getMaSanPham(),
                sp.getNgayNhap(),
                sp.getMauSac(),
                sp.getSize(),
                sp.getChatLieu(),
                sp.getHang(),
                sp.getSoLuong(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getHinhAnh()
            });
        }
    }

    void loadCTSPDaHetTheoMa(String a) {
        defau = (DefaultTableModel) tblCTSP.getModel();
        defau.setRowCount(0);
        int i = tblCTSP.getSelectedRow();
        for (SanPham sp : qlsp.getListCTSPDaHetTheoMaSP(a)) {
            defau.addRow(new Object[]{
                sp.getIdspct(),
                sp.getMaSanPham(),
                sp.getNgayNhap(),
                sp.getMauSac(),
                sp.getSize(),
                sp.getChatLieu(),
                sp.getHang(),
                sp.getSoLuong(),
                sp.getGiaNhap(),
                sp.getGiaBan(),
                sp.getHinhAnh()
            });
        }
    }

    void loadSPTimKiem() {
        defau = (DefaultTableModel) tblSanPham.getModel();
        defau.setRowCount(0);
        int stt = 0;
        for (SanPham sp : qlsp.timKiem(txtTimKiemSP.getText())) {
            stt++;
            defau.addRow(new Object[]{
                stt,
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                qlsp.getSoLuongTong(sp.getMaSanPham()),
                sp.getTrangThai()
            });
        }
    }

    SanPham getFormSP() {
        SanPham sp = new SanPham();
        sp.setMaSanPham(txtMaSP.getText());
        sp.setTenSanPham(txtTenSP.getText());

        if (cbConHang.isSelected()) {
            sp.setTrangThai("Còn hàng");
        } else {
            sp.setTrangThai("Hết hàng");
        }
        return sp;
    }

    SanPham getFormCTSP() {
        Date selectedDate = dcNgayNhap.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedDate);

        SanPham sp = new SanPham();
//        sp.setIdspct(txtIDSP.getText());
        sp.setMaSanPham(txtMaSP.getText());
        sp.setNgayNhap(formattedDate.toString());
        sp.setMauSac((String) String.valueOf(cbbMauSac.getSelectedIndex() + 1));
        sp.setSize((String) String.valueOf(cbbSize.getSelectedIndex() + 1));
        sp.setChatLieu((String) String.valueOf(cbbChatLieu.getSelectedIndex() + 1));
        sp.setHang((String) String.valueOf(cbbHang.getSelectedIndex() + 1));
//        sp.setSize((String) cbbSize.getSelectedItem());
//        sp.setChatLieu((String) cbbChatLieu.getSelectedItem());
//        sp.setHang((String) cbbHang.getSelectedItem());
        sp.setGiaNhap(txtGiaNhap.getText());
        sp.setGiaBan(txtGiaBan.getText());
        sp.setSoLuong(txtSoLuong.getText());
        if (loadImg == null) {
            sp.setHinhAnh("No_img");
        } else {
            sp.setHinhAnh(loadImg);
        }
        return sp;
    }

    void setFormSP() {
        int i = tblSanPham.getSelectedRow();
        txtMaSP.setText((String) tblSanPham.getValueAt(i, 1));
        txtTenSP.setText((String) tblSanPham.getValueAt(i, 2));
        txtSoLuongTong.setText(tblSanPham.getValueAt(i, 3).toString());
        String tt = (String) tblSanPham.getValueAt(i, 4);
        if (tt.equals("Còn hàng")) {
            cbConHang.setSelected(true);
        } else {
            cbConHang.setSelected(false);
        }
    }

    void setFormCTSP() {
        int i = tblCTSP.getSelectedRow();
        txtIDSP.setText((String) tblCTSP.getValueAt(i, 0));
        txtMaSP.setText((String) tblCTSP.getValueAt(i, 1));

        String dateString = tblCTSP.getValueAt(i, 2).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date selectedDate = null;
        try {
            selectedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcNgayNhap.setDate(selectedDate);

        cbbMauSac.setSelectedItem(tblCTSP.getValueAt(i, 3).toString());
        cbbSize.setSelectedItem(tblCTSP.getValueAt(i, 4).toString());
        cbbChatLieu.setSelectedItem(tblCTSP.getValueAt(i, 5).toString());
        cbbHang.setSelectedItem(tblCTSP.getValueAt(i, 6).toString());
        txtSoLuong.setText((String) tblCTSP.getValueAt(i, 7));
        txtGiaNhap.setText((String) tblCTSP.getValueAt(i, 8));
        txtGiaBan.setText((String) tblCTSP.getValueAt(i, 9));
        lbHinhAnh.setText("");
        String a = (String) tblCTSP.getValueAt(i, 10);
        if (a.equalsIgnoreCase("No_img")) {
            lbHinhAnh.setText("No_img");
            lbHinhAnh.setIcon(null);
        } else {
            ImageIcon imgIcon = new ImageIcon(getClass().getResource("/GiayTheThaoList/" + a));
//            Image img = imgIcon.getImage();
//            img.getScaledInstance(lbHinhAnh.getWidth(), lbHinhAnh.getHeight(), 0);
//            ImageIcon imageIcon = new ImageIcon(a);
            lbHinhAnh.setIcon(imgIcon);
            loadImg = a;
        }
    }

    void loadSelectThuocTinh() {
        cbbMauSac.removeAllItems();
        qlsp.getSelectMauSac().clear();
        for (SanPham sp : qlsp.getSelectMauSac()) {
            cbbMauSac.addItem(sp.getMauSac());
        }

        cbbSize.removeAllItems();
        qlsp.getSelectSize().clear();
        for (SanPham sp : qlsp.getSelectSize()) {
            cbbSize.addItem(sp.getSize());
        }

        cbbChatLieu.removeAllItems();
        qlsp.getSelectChatLieu().clear();
        for (SanPham sp : qlsp.getSelectChatLieu()) {
            cbbChatLieu.addItem(sp.getChatLieu());
        }

        cbbHang.removeAllItems();
        qlsp.getSelectHang().clear();
        for (SanPham sp : qlsp.getSelectHang()) {
            cbbHang.addItem(sp.getHang());
        }
    }

    void hienThiSP() {
        txtMaSP.setText((String) tblSanPham.getValueAt(1, 1));
        txtTenSP.setText((String) tblSanPham.getValueAt(1, 2));
        txtSoLuongTong.setText(tblSanPham.getValueAt(1, 3).toString());
        String tt = "";
        if (tt.equals("Còn hàng")) {
            cbConHang.setSelected(true);
        } else {
            cbConHang.setSelected(false);
        }
    }

    void hienThiCTSP() {
        txtIDSP.setText((String) tblCTSP.getValueAt(1, 0));
        txtMaSP.setText((String) tblCTSP.getValueAt(1, 1));

        String dateString = tblCTSP.getValueAt(1, 2).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date selectedDate = null;
        try {
            selectedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcNgayNhap.setDate(selectedDate);

        cbbMauSac.setSelectedItem(tblCTSP.getValueAt(1, 3).toString());
        cbbSize.setSelectedItem(tblCTSP.getValueAt(1, 4).toString());
        cbbChatLieu.setSelectedItem(tblCTSP.getValueAt(1, 5).toString());
        cbbHang.setSelectedItem(tblCTSP.getValueAt(1, 6).toString());
        txtSoLuong.setText((String) tblCTSP.getValueAt(1, 7));
        txtGiaBan.setText((String) tblCTSP.getValueAt(1, 8));
    }

//    void luaChon() {
//        String[] options = {"Add SP", "Add CTSP"};
//        int a = JOptionPane.showOptionDialog(this, "", "Hãy chọn chức năng", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
//        if (a == JOptionPane.YES_OPTION) {
//            qlsp.AddSP(getFormSP());
//            qlsp.UpSoLuongTong(txtMaSP.getText(), txtSoLuongTong.getText());
//            loadSP();
//        } else {
//            qlsp.AddCTSP(getFormCTSP());
//            loadCTSP();
//        }
//    }
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
        Sanpham = new javax.swing.JTabbedPane();
        pnSanPham = new javax.swing.JPanel();
        pnThongTin = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbHinhAnh = new javax.swing.JLabel();
        txtSoLuongTong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbConHang = new javax.swing.JCheckBox();
        pnLoc = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtIDSP = new javax.swing.JTextField();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbHang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dcNgayNhap = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        btnAddCTSP = new javax.swing.JButton();
        btnUpdateCTSP = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        pSanPham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        pCTSP = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();
        cbDaHet = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        pnSanPhamChiTiet = new javax.swing.JPanel();
        pnDanhSach = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiemTT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        btnAddTT = new javax.swing.JButton();
        btnUpdateTT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        txtIdThuocTinh = new javax.swing.JTextField();
        rdMauSac = new javax.swing.JRadioButton();
        rdSize = new javax.swing.JRadioButton();
        rdChatLieu = new javax.swing.JRadioButton();
        rdHang = new javax.swing.JRadioButton();
        btnNewTT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Sanpham.setForeground(new java.awt.Color(255, 0, 0));
        Sanpham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Sanpham.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnSanPhamMouseClicked(evt);
            }
        });

        pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel4.setText("Mã SP:");

        jLabel5.setText("Tên SP:");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHinhAnh.setText("ảnh");
        lbHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbHinhAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        jLabel8.setText("Số lượng Tổng:");

        jLabel16.setText("Trrạng thái:");

        cbConHang.setText("Còn hàng");

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuongTong, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbConHang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuongTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cbConHang)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnLoc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Chi tiết SP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel13.setText("Màu sắc:");

        jLabel14.setText("Size:");

        jLabel15.setText("Chất liệu:");

        jLabel17.setText("Hãng:");

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("ID:");

        cbbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Ngày nhập:");

        jLabel11.setText("Giá bán:");

        jLabel12.setText("Số lượng:");

        dcNgayNhap.setDateFormatString("yyyy-MM-dd");

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAddCTSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddCTSP.setText("Add CTSP");
        btnAddCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCTSPActionPerformed(evt);
            }
        });

        btnUpdateCTSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateCTSP.setText("Update CTSP");
        btnUpdateCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnAddCTSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btnUpdateCTSP)
                .addGap(36, 36, 36))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnUpdateCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel18.setText("Giá nhập:");

        javax.swing.GroupLayout pnLocLayout = new javax.swing.GroupLayout(pnLoc);
        pnLoc.setLayout(pnLocLayout);
        pnLocLayout.setHorizontalGroup(
            pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocLayout.createSequentialGroup()
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnLocLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnLocLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnLocLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnLocLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(pnLocLayout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnLocLayout.createSequentialGroup()
                                    .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIDSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pnLocLayout.createSequentialGroup()
                                    .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbbHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dcNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                            .addGroup(pnLocLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnLocLayout.setVerticalGroup(
            pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIDSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(dcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng tổng", "Trạng thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel3.setText("Tìm kiếm:");

        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pSanPhamLayout = new javax.swing.GroupLayout(pSanPham);
        pSanPham.setLayout(pSanPhamLayout);
        pSanPhamLayout.setHorizontalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)))
        );
        pSanPhamLayout.setVerticalGroup(
            pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pCTSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Ngày nhập", "Màu sắc ", "Size", "Chất liệu", "Hãng", "Số lượng", "Giá nhập", "Giá bán", "Hình ảnh"
            }
        ));
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCTSP);

        cbDaHet.setText("Đã Hết");
        cbDaHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDaHetActionPerformed(evt);
            }
        });

        jLabel10.setText("Mặt hàng:");

        javax.swing.GroupLayout pCTSPLayout = new javax.swing.GroupLayout(pCTSP);
        pCTSP.setLayout(pCTSPLayout);
        pCTSPLayout.setHorizontalGroup(
            pCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pCTSPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDaHet)))
                .addContainerGap())
        );
        pCTSPLayout.setVerticalGroup(
            pCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCTSPLayout.createSequentialGroup()
                .addGroup(pCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDaHet)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(pnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnSanPhamLayout.createSequentialGroup()
                        .addGroup(pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnSanPhamLayout.createSequentialGroup()
                                .addComponent(pSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(pCTSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        Sanpham.addTab("Sản phẩm", pnSanPham);

        pnDanhSach.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null), "Danh sách thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel6.setText("Tìm Kiếm");

        txtTimKiemTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTTKeyReleased(evt);
            }
        });

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID thuộc tính", "Tên thuộc tính", "Ghi chú"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout pnDanhSachLayout = new javax.swing.GroupLayout(pnDanhSach);
        pnDanhSach.setLayout(pnDanhSachLayout);
        pnDanhSachLayout.setHorizontalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnDanhSachLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemTT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnDanhSachLayout.setVerticalGroup(
            pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhSachLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnDanhSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnAddTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddTT.setText("Add");
        btnAddTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTTActionPerformed(evt);
            }
        });

        btnUpdateTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateTT.setText("Update");
        btnUpdateTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTTActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Màu sắc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("ID thuộc tính:");

        jLabel2.setText("Tên thuộc tính:");

        buttonGroup1.add(rdMauSac);
        rdMauSac.setText("Màu sắc");
        rdMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdSize);
        rdSize.setText("Size");
        rdSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSizeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdChatLieu);
        rdChatLieu.setText("Chất liệu");
        rdChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdHang);
        rdHang.setText("Hãng");
        rdHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rdMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(rdSize, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(rdChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(rdHang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(45, 45, 45)
                        .addComponent(txtIdThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2)
                        .addGap(87, 87, 87)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdMauSac)
                    .addComponent(rdSize)
                    .addComponent(rdChatLieu)
                    .addComponent(rdHang))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnNewTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNewTT.setText("New");

        javax.swing.GroupLayout pnSanPhamChiTietLayout = new javax.swing.GroupLayout(pnSanPhamChiTiet);
        pnSanPhamChiTiet.setLayout(pnSanPhamChiTietLayout);
        pnSanPhamChiTietLayout.setHorizontalGroup(
            pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                        .addComponent(btnAddTT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateTT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNewTT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        pnSanPhamChiTietLayout.setVerticalGroup(
            pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateTT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewTT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(pnDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        Sanpham.addTab("Thuộc tính", pnSanPhamChiTiet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Sanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        setFormSP();
        int i = tblSanPham.getSelectedRow();
        if (cbDaHet.isSelected()) {
            loadCTSPDaHetTheoMa((String) tblSanPham.getValueAt(i, 1));
            if (tblCTSP.getRowCount() > 0) {
                tblCTSP.setRowSelectionInterval(0, 0);
                setFormCTSP();
            }

        } else {
            loadCTSPTheoMa((String) tblSanPham.getValueAt(i, 1));
            if (Integer.valueOf((String) tblSanPham.getValueAt(i, 3)) != 0) {
                if (tblCTSP.getRowCount() > 0) {
                    tblCTSP.setRowSelectionInterval(0, 0);
                    setFormCTSP();
                }
            }
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    Boolean checkAdd() {
        for (SanPham sp : qlsp.getListSanPham()) {
            if (txtMaSP.getText().equals(sp.getMaSanPham())) {
                JOptionPane.showMessageDialog(this, "Trung ma");
                return false;
            }
        }
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ma SP khong duoc de trong");
            return false;
        }
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ten SP khong duoc de trong");
            return false;
        }
        return true;
    }

    Boolean checkAddCTSP() {
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Khong the Them SP khi chua co Ma");
            return false;
        }
        if (dcNgayNhap.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngay Nhap khong duoc de trong");
            return false;
        }
        if (txtGiaBan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Gia Ban khong duoc de trong");
            return false;
        }
        if (txtGiaBan.getText().matches("\\D+")) {
            JOptionPane.showMessageDialog(this, "Gia Ban chi duoc go so");
            return false;
        }
        if (txtSoLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "So Luong khong duoc de trong");
            return false;
        }
        if (txtSoLuong.getText().matches("\\D+")) {
            JOptionPane.showMessageDialog(this, "So Luong chi duoc go so");
            return false;
        }
        if (cbbMauSac.getSelectedItem() == null || cbbSize.getSelectedItem() == null || cbbChatLieu.getSelectedItem() == null || cbbHang.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Vui long chon du thuoc tinh");
            return false;
        }
        if (lbHinhAnh == null) {
            JOptionPane.showMessageDialog(this, "Vui long chon anh");
            return false;
        }
        return true;
    }

    Boolean checkTTSP() {
        int i = tblSanPham.getSelectedRow();
        String a;
        for (SanPham sp : qlsp.getListCTSPTT()) {
            a = sp.getMaSanPham();
            if (cbbMauSac.getSelectedItem().equals(sp.getMauSac())
                    && cbbMauSac.getSelectedItem().equals(sp.getMauSac())
                    && cbbSize.getSelectedItem().equals(sp.getSize())
                    && cbbChatLieu.getSelectedItem().equals(sp.getChatLieu())
                    && cbbHang.getSelectedItem().equals(sp.getHang())
                    && txtMaSP.getText().equals(a)) {
                JOptionPane.showMessageDialog(this, "Trùng lặp khi có SP đã có các thuộc tính trên!!!");
                return false;
            }
        }
        for (SanPham sp : qlsp.getListCTSP()) {
            if (lbHinhAnh.getIcon() != null && loadImg != null && loadImg.equals(sp.getHinhAnh())) {
                JOptionPane.showMessageDialog(this, "Da co SP dung anh nay vui long chon anh khac");
                return false;
            }
        }
        return true;
    }

    Boolean checkUpTTSP() {
        int i = tblSanPham.getSelectedRow();
        String a;
        String b;
        for (SanPham sp : qlsp.getListCTSPTT()) {
            a = sp.getMaSanPham();
            b = sp.getIdspct();
            if (cbbMauSac.getSelectedItem().equals(sp.getMauSac())
                    && cbbMauSac.getSelectedItem().equals(sp.getMauSac())
                    && cbbSize.getSelectedItem().equals(sp.getSize())
                    && cbbChatLieu.getSelectedItem().equals(sp.getChatLieu())
                    && cbbHang.getSelectedItem().equals(sp.getHang())
                    && txtMaSP.getText().equals(a)
                    && !txtIDSP.getText().equals(b)) {
                JOptionPane.showMessageDialog(this, "Trùng lặp khi có SP đã có các thuộc tính trên!!!");
                return false;
            }
        }
        for (SanPham sp : qlsp.getListCTSP()) {
            b = sp.getIdspct();
            if (lbHinhAnh.getIcon() != null && loadImg != null && loadImg.equals(sp.getHinhAnh()) && !txtIDSP.getText().equals(b)) {
                JOptionPane.showMessageDialog(this, "Da co SP dung anh nay vui long chon anh khac");
                return false;
            }
        }
        return true;
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (checkAdd()) {
            qlsp.AddSP(getFormSP());
            JOptionPane.showMessageDialog(this, "Them thanh cong");
            qlsp.TongSoLuongSP(txtMaSP.getText());
            loadSP();
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked
        // TODO add your handling code here:
        int i = tblSanPham.getSelectedRow();
        if (i < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon SP");
        } else {
            setFormCTSP();
        }


    }//GEN-LAST:event_tblCTSPMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (checkAdd()) {
            int i = tblSanPham.getSelectedRow();
            String MaSP = (String) tblSanPham.getValueAt(i, 1);
            qlsp.UpdateSP(getFormSP(), MaSP, txtSoLuongTong.getText());
            JOptionPane.showMessageDialog(this, "Sua thanh cong");
            loadSP();
            loadCTSPTheoMa((String) tblSanPham.getValueAt(i, 1));
//                loadCTSPDaHetTheoMa((String) tblSanPham.getValueAt(i, 1));
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCTSPActionPerformed
        // TODO add your handling code here:
        if (checkAddCTSP()) {
            if (checkTTSP()) {
                qlsp.AddCTSP(getFormCTSP());
                loadCTSP();
                loadCTSPTheoMa(txtMaSP.getText());
                if (cbDaHet.isSelected()) {
                    loadCTSPDaHet();
                    loadCTSPDaHetTheoMa(txtMaSP.getText());
                }
                qlsp.TongSoLuongSP(txtMaSP.getText());
                JOptionPane.showMessageDialog(this, "Them thanh cong");
                loadSP();
            }
        }
    }//GEN-LAST:event_btnAddCTSPActionPerformed

    private void btnUpdateCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCTSPActionPerformed
        // TODO add your handling code here:
        if (checkAddCTSP()) {
            if (checkUpTTSP()) {
                int i = tblCTSP.getSelectedRow();
                int IdSP = (int) Integer.valueOf((String) tblCTSP.getValueAt(i, 0));
                qlsp.UpdateCTSP(getFormCTSP(), IdSP);
                loadCTSP();
                loadCTSPTheoMa(txtMaSP.getText());
                if (cbDaHet.isSelected()) {
                    loadCTSPDaHet();
                    loadCTSPDaHetTheoMa(txtMaSP.getText());
                }
                qlsp.TongSoLuongSP(txtMaSP.getText());
                loadSP();
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
            }
        }


    }//GEN-LAST:event_btnUpdateCTSPActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtSoLuongTong.setText("");
        cbConHang.setSelected(false);
        txtIDSP.setText("");
        dcNgayNhap.setDate(null);
        cbbMauSac.setSelectedIndex(0);
        cbbSize.setSelectedIndex(0);
        cbbChatLieu.setSelectedIndex(0);
        cbbHang.setSelectedIndex(0);
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        cbDaHet.setSelected(false);
        loadSP();
        loadCTSP();
    }//GEN-LAST:event_btnNewActionPerformed

    void loadTblMauSac() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getSelectMauSac()) {
            defau.addRow(new Object[]{
                sp.getIdMauSac(), sp.getMauSac()
            });
        }
    }

    void loadTblSize() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getSelectSize()) {
            defau.addRow(new Object[]{
                sp.getIdSize(), sp.getSize()
            });
        }
    }

    void loadTblChatLieu() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getSelectChatLieu()) {
            defau.addRow(new Object[]{
                sp.getIdChatLieu(), sp.getChatLieu()
            });
        }
    }

    void loadTblHang() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.getSelectHang()) {
            defau.addRow(new Object[]{
                sp.getIdHang(), sp.getHang()
            });
        }
    }
    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed
        // TODO add your handling code here:
        loadTblMauSac();
    }//GEN-LAST:event_rdMauSacActionPerformed

    private void rdSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSizeActionPerformed
        // TODO add your handling code here:
        loadTblSize();
    }//GEN-LAST:event_rdSizeActionPerformed

    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        loadTblChatLieu();
    }//GEN-LAST:event_rdChatLieuActionPerformed

    private void rdHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHangActionPerformed
        // TODO add your handling code here:
        loadTblHang();
    }//GEN-LAST:event_rdHangActionPerformed

    SanPham getFormTT() {
        SanPham sp = new SanPham();
        if (rdMauSac.isSelected()) {
            sp.setMauSac(txtTenThuocTinh.getText());
        } else if (rdSize.isSelected()) {
            sp.setSize(txtTenThuocTinh.getText());
        } else if (rdChatLieu.isSelected()) {
            sp.setChatLieu(txtTenThuocTinh.getText());
        } else {
            sp.setHang(txtTenThuocTinh.getText());
        }
        return sp;
    }

    void setFormTT() {
        int i = tblThuocTinh.getSelectedRow();
        txtIdThuocTinh.setText((String) tblThuocTinh.getValueAt(i, 0));
        txtTenThuocTinh.setText((String) tblThuocTinh.getValueAt(i, 1));
    }

    Boolean checkTT() {
        if (!rdMauSac.isSelected() && !rdSize.isSelected() && !rdChatLieu.isSelected() && !rdHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui long chon thuoc tinh");
            return false;
        }
        if (txtTenThuocTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ten Thuoc Tinh khong duoc de trong");
            return false;
        }
        for (SanPham sp : qlsp.getSelectMauSac()) {
            if (txtTenThuocTinh.getText().equals(sp.getMauSac())) {
                JOptionPane.showMessageDialog(this, "Ten Mau Sac da ton tai");
                return false;
            }
        }
        for (SanPham sp : qlsp.getSelectSize()) {
            if (txtTenThuocTinh.getText().equals(sp.getSize())) {
                JOptionPane.showMessageDialog(this, "Ten Size da ton tai");
                return false;
            }
        }
        for (SanPham sp : qlsp.getSelectChatLieu()) {
            if (txtTenThuocTinh.getText().equals(sp.getChatLieu())) {
                JOptionPane.showMessageDialog(this, "Ten Chat Lieu da ton tai");
                return false;
            }
        }
        for (SanPham sp : qlsp.getSelectHang()) {
            if (txtTenThuocTinh.getText().equals(sp.getHang())) {
                JOptionPane.showMessageDialog(this, "Ten Hang da ton tai");
                return false;
            }
        }
        if (txtTenThuocTinh.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ten Thuoc Tinh khong duoc de trong");
            return false;
        }
        return true;
    }

    private void btnAddTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTTActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            if (rdMauSac.isSelected()) {
                qlsp.addMauSac(getFormTT());
                loadTblMauSac();
                loadSelectThuocTinh();
            } else if (rdSize.isSelected()) {
                qlsp.addSize(getFormTT());
                loadTblSize();
                loadSelectThuocTinh();
            } else if (rdChatLieu.isSelected()) {
                qlsp.addChatLieu(getFormTT());
                loadTblChatLieu();
                loadSelectThuocTinh();
            } else {
                qlsp.addHang(getFormTT());
                loadTblHang();
                loadSelectThuocTinh();
            }
        }
    }//GEN-LAST:event_btnAddTTActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        setFormTT();
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void btnUpdateTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTTActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            if (rdMauSac.isSelected()) {
                qlsp.UpMauSac(getFormTT(), txtIdThuocTinh.getText());
                loadTblMauSac();
                loadSelectThuocTinh();

                cbDaHet.setSelected(false);
                loadCTSP();
                if (!txtMaSP.getText().equals("")) {
                    loadCTSPTheoMa(txtMaSP.getText());
                }

            } else if (rdSize.isSelected()) {
                qlsp.UpMSize(getFormTT(), txtIdThuocTinh.getText());
                loadTblSize();
                loadSelectThuocTinh();

                cbDaHet.setSelected(false);
                loadCTSP();
                if (!txtMaSP.getText().equals("")) {
                    loadCTSPTheoMa(txtMaSP.getText());
                }
            } else if (rdChatLieu.isSelected()) {
                qlsp.UpChatLieu(getFormTT(), txtIdThuocTinh.getText());
                loadTblChatLieu();
                loadSelectThuocTinh();

                cbDaHet.setSelected(false);
                loadCTSP();
                if (!txtMaSP.getText().equals("")) {
                    loadCTSPTheoMa(txtMaSP.getText());
                }
            } else {
                qlsp.UpHang(getFormTT(), txtIdThuocTinh.getText());
                loadTblHang();
                loadSelectThuocTinh();

                cbDaHet.setSelected(false);
                loadCTSP();
                if (!txtMaSP.getText().equals("")) {
                    loadCTSPTheoMa(txtMaSP.getText());
                }
            }
        }
    }//GEN-LAST:event_btnUpdateTTActionPerformed

    private void lbHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHinhAnhMouseClicked
        try {
            // TODO add your handling code here:
            JFileChooser jfc = new JFileChooser("C:\\Users\\LongThank\\Documents\\NetBeansProjects\\CuaHangXstore\\src\\GiayTheThaoList");
//            jfc.showOpenDialog(null);
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                Image img = ImageIO.read(file);
                loadImg = file.getName();
                lbHinhAnh.setText("");
                int width = lbHinhAnh.getWidth();
                int height = lbHinhAnh.getHeight();
                lbHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
            } else {
                loadImg = "No_img";
                System.out.println("Chua chon anh");
            }
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_lbHinhAnhMouseClicked

    private void txtTimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyReleased
        // TODO add your handling code here:
        qlsp.timKiem(txtTimKiemSP.getText());
        loadSPTimKiem();
    }//GEN-LAST:event_txtTimKiemSPKeyReleased

    void loadTblTimKiemMauSac() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.timKiemMauSac(txtTimKiemTT.getText())) {
            defau.addRow(new Object[]{
                sp.getIdMauSac(), sp.getMauSac()
            });
        }
    }

    void loadTblTimKiemSize() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.timKiemSize(txtTimKiemTT.getText())) {
            defau.addRow(new Object[]{
                sp.getIdSize(), sp.getSize()
            });
        }
    }

    void loadTblTimKiemChatLieu() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.timKiemChatLieu(txtTimKiemTT.getText())) {
            defau.addRow(new Object[]{
                sp.getIdChatLieu(), sp.getChatLieu()
            });
        }
    }

    void loadTblTimKiemHang() {
        defau = (DefaultTableModel) tblThuocTinh.getModel();
        defau.setRowCount(0);
        for (SanPham sp : qlsp.timKiemHang(txtTimKiemTT.getText())) {
            defau.addRow(new Object[]{
                sp.getIdHang(), sp.getHang()
            });
        }
    }

    private void txtTimKiemTTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTTKeyReleased
        // TODO add your handling code here:
        if (rdMauSac.isSelected()) {
            qlsp.timKiemMauSac(txtTimKiemTT.getText());
            loadTblTimKiemMauSac();

        } else if (rdSize.isSelected()) {
            qlsp.timKiemSize(txtTimKiemTT.getText());
            loadTblTimKiemSize();

        } else if (rdChatLieu.isSelected()) {
            qlsp.timKiemChatLieu(txtTimKiemTT.getText());
            loadTblTimKiemChatLieu();

        } else {
            qlsp.timKiemHang(txtTimKiemTT.getText());
            loadTblTimKiemHang();

        }
    }//GEN-LAST:event_txtTimKiemTTKeyReleased

    private void cbDaHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDaHetActionPerformed
        // TODO add your handling code here:
//        int i = tblSanPham.getSelectedRow();
        String a = txtMaSP.getText();
        if (cbDaHet.isSelected()) {
            if (!a.equals("")) {
                loadCTSPDaHetTheoMa(a);
            } else {
                loadCTSPDaHet();
            }
        } else {
            if (!a.equals("")) {
                loadCTSPTheoMa(a);
            } else {
                loadCTSP();
            }
        }
    }//GEN-LAST:event_cbDaHetActionPerformed

    private void pnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnSanPhamMouseClicked
        // TODO add your handling code here:
//        loadSP();
//        loadCTSP();
//        btnNew.doClick();
    }//GEN-LAST:event_pnSanPhamMouseClicked

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
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Sanpham;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddCTSP;
    private javax.swing.JButton btnAddTT;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNewTT;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateCTSP;
    private javax.swing.JButton btnUpdateTT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox cbConHang;
    private javax.swing.JCheckBox cbDaHet;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private com.toedter.calendar.JDateChooser dcNgayNhap;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbHinhAnh;
    private javax.swing.JPanel pCTSP;
    private javax.swing.JPanel pSanPham;
    private javax.swing.JPanel pnDanhSach;
    private javax.swing.JPanel pnLoc;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnSanPhamChiTiet;
    private javax.swing.JPanel pnThongTin;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdHang;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JRadioButton rdSize;
    private javax.swing.JTable tblCTSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtIDSP;
    private javax.swing.JTextField txtIdThuocTinh;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongTong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimKiemTT;
    // End of variables declaration//GEN-END:variables
}
