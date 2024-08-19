/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Coupon;
import Model.KhachHang;
import Model.SanPham;
import Model.Voucher;
import Service.QuanLyKhuyenMai;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienBB
 */
public class ViewKhuyenMai extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    List<Voucher> listVC = new ArrayList<>();
    QuanLyKhuyenMai qlKM = new QuanLyKhuyenMai();

    private Timer timer = new Timer();

    List<SanPham> listSP = new ArrayList<>();

    public ViewKhuyenMai() {
        initComponents();
        LoadDataTable();
        LoadDataCoupon();
        LoadDataSanPham();
//        txtMaCP.setEnabled(false);
        timer.scheduleAtFixedRate(new UpdateHetHan(), 0, 24 * 60 * 60 * 1000); // Kiểm tra mỗi 24 giờ
    }

    public void LoadDataTable() {
        dtm = (DefaultTableModel) tbVoucher.getModel();
        listVC = qlKM.getAllVC();
        dtm.setRowCount(0);
        for (Voucher e : listVC) {
            String trangThai = e.getTrangThai().equalsIgnoreCase("0") ? "Hết hạn" : "Hoạt động";
            dtm.addRow(new Object[]{
                e.getMaVC(),
                e.getTenVC(),
                e.getGiamGia(),
                e.getNgayBatDau(),
                e.getNgayKetThuc(),
                e.getDieuKien(),
                e.getUuDai(),
                trangThai
            });
        }
    }

    public void LoadDataTableTheoMa(String ma) {
        dtm = (DefaultTableModel) tbVoucher.getModel();
        listVC = qlKM.getAllVC();
        dtm.setRowCount(0);
        for (Voucher e : listVC) {
            String trangThai = e.getTrangThai().equalsIgnoreCase("0") ? "Hết hạn" : "Hoạt động";
            dtm.addRow(new Object[]{
                e.getMaVC(),
                e.getTenVC(),
                e.getGiamGia(),
                e.getNgayBatDau(),
                e.getNgayKetThuc(),
                e.getDieuKien(),
                e.getUuDai(),
                trangThai
            });
        }
    }

    public void detail() {
        int i = tbVoucher.getSelectedRow();
        txtMaVC.setText(String.valueOf(tbVoucher.getValueAt(i, 0)));
        txtTenVC.setText(String.valueOf(tbVoucher.getValueAt(i, 1)));
        txtGiamGiaVC.setText(String.valueOf(tbVoucher.getValueAt(i, 2)));
        String dateBatDau = String.valueOf(tbVoucher.getValueAt(i, 3));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = (Date) dateFormat.parse(dateBatDau);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcBatDau.setDate(startDate);
        String dateKetThuc = String.valueOf(tbVoucher.getValueAt(i, 4)); // Chỉ số 4 nếu ngày kết thúc được lưu trong cột khác
        Date endDate = null;
        try {
            endDate = (Date) dateFormat.parse(dateKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcKetThuc.setDate(endDate);
        txtDieuKien.setText(String.valueOf(tbVoucher.getValueAt(i, 5)));
        String ttKH = String.valueOf(tbVoucher.getValueAt(i, 6));
        if (ttKH.equalsIgnoreCase("Khách VIP")) {
            cbKhachVip.setSelected(true);
        } else {
            cbKhachVip.setSelected(false);
        }
        String tt = String.valueOf(tbVoucher.getValueAt(i, 7));
        if (tt.equalsIgnoreCase("Hoạt động")) {
            rbHoatDong.setSelected(true);
        } else if (tt.equalsIgnoreCase("Hết hạn")) {
            rbHetHan.setSelected(true);
        } else {
            //none
        }
    }

    public Voucher getFormVoucher() {
        Voucher vc = new Voucher();
        vc.setMaVC(txtMaVC.getText());
        vc.setTenVC(txtTenVC.getText());
        vc.setGiamGia(txtGiamGiaVC.getText());

        Date a = dcBatDau.getDate();
        Date b = dcKetThuc.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String bd = df.format(a);
        String kt = df.format(b);
        vc.setNgayBatDau(bd + " 00:00:00");
        vc.setNgayKetThuc(kt + " 23:59:00");

        vc.setDieuKien(txtDieuKien.getText());
        if (rbHoatDong.isSelected()) {
            vc.setTrangThai("Hoạt động");
        } else if (rbHetHan.isSelected()) {
            vc.setTrangThai("Hết hạn");
        } else {
            vc.setTrangThai("Chưa kích hoạt");
        }
        return vc;
    }

    public boolean checkVC() {
        String mavc = txtMaVC.getText().trim();
        String tenvc = txtTenVC.getText().trim();
        String giamGia = txtGiamGiaVC.getText().trim();
        Date batDau = dcBatDau.getDate();
        Date hetHan = dcKetThuc.getDate();
        String dieukien = txtDieuKien.getText().trim();
        if (mavc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã voucher không được để trống");
            return false;
        }
        for (Voucher vc : listVC) {
            if (vc.getMaVC().equals(mavc)) {
                JOptionPane.showMessageDialog(this, "Mã voucher không được trùng nhau!");
                return false;
            }
        }
        if (tenvc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên voucher không được để trống");
            return false;
        }
        if (giamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giảm giá voucher không được để trống");
            return false;
        }
        try {
            double giaGiam = Double.parseDouble(giamGia);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giảm giá voucher phải là số");
            return false;
        }

        if (batDau == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống");
            return false;
        }
        if (hetHan == null) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn không được để trống");
            return false;
        }
        if (dieukien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điều kiện không được để trống");
            return false;
        }
        try {
            double dK = Double.parseDouble(dieukien);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Điều kiện phải là số");
            return false;
        }
        if (hetHan.before(batDau)) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn không được để trước ngày bắt đầu");
            return false;
        }
        return true;
    }

    public boolean checkTonTaiMa(String maMoi) {
        for (Voucher vc : listVC) {
            if (!vc.getMaVC().equals(txtMaVC.getText().trim()) && vc.getMaVC().equals(maMoi)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUpdateVC() {
        String mavc = txtMaVC.getText().trim();
        String tenvc = txtTenVC.getText().trim();
        String giamGia = txtGiamGiaVC.getText().trim();
        Date batDau = dcBatDau.getDate();
        Date hetHan = dcKetThuc.getDate();
        String dieukien = txtDieuKien.getText().trim();

        if (tenvc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên voucher không được để trống");
            return false;
        }

        if (giamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giảm giá voucher không được để trống");
            return false;
        }

        // Kiểm tra nếu giá trị giamGia không phải là số
        try {
            double giaGiam = Double.parseDouble(giamGia);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giảm giá voucher phải là một số");
            return false;
        }

        if (batDau == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống");
            return false;
        }

        if (hetHan == null) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn không được để trống");
            return false;
        }

        if (dieukien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điều kiện không được để trống");
            return false;
        }
        try {
            double dK = Double.parseDouble(dieukien);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Điều kiện phải là số");
            return false;
        }

        if (hetHan.before(batDau)) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn không được để trước ngày bắt đầu");
            return false;
        }

        if (checkTonTaiMa(mavc)) {
            JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại");
            return false;
        }

        return true;
    }

    public void addVC() {
        if (checkVC()) {
            try {
                qlKM.themVoucher(getFormVoucher());
                String maVC = txtMaVC.getText();
                if (cbKhachVip.isSelected()) {
                    qlKM.addKhachVIP(maVC);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Boolean checkUp() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = dcBatDau.getDate();
        Date kt = dcKetThuc.getDate();
        String batDau = df.format(bd);

        Date now = new Date();
        String hienTai = df.format(now);

        if (bd.compareTo(now) < 0) {
            JOptionPane.showMessageDialog(this, "Không được Add Voucher có NgayBatDau < NgayHienTai");
            return false;
        }
        for (Voucher vc : qlKM.getListVCUpdate(txtMaVC.getText(), Float.valueOf(txtDieuKien.getText()))) {
            try {
                Date a = df.parse(vc.getNgayBatDau());
                Date b = df.parse(vc.getNgayKetThuc());
                if (kt.compareTo(a) >= 0 && kt.compareTo(b) <= 0) {
                    JOptionPane.showMessageDialog(this, "NgayKetThuc của Voucher mới nằm giữa NgayBatDau va NgayKetThuc của Voucher khác. Vui lòng nhập lại");
                    return false;
                }
                if (bd.compareTo(a) >= 0 && bd.compareTo(b) <= 0) {
                    JOptionPane.showMessageDialog(this, "NgayBatDau của Voucher mới nằm giữa NgayBatDau va NgayKetThuc của Voucher khác. Vui lòng nhập lại");
                    return false;
                }
                if (a.compareTo(now) <= 0 && b.compareTo(now) >= 0 && bd.compareTo(b) <= 0 && kt.compareTo(b) >= 0) {
                    qlKM.suaVoucher(getFormVoucher(), hienTai, txtMaVC.getText());
                    JOptionPane.showMessageDialog(this, "Sửa thành công Voucher!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thành công Voucher!");
                    qlKM.suaVoucher(getFormVoucher(), batDau, txtMaVC.getText());
                    return true;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void updateVC() {
        if (checkUpdateVC()) {
            try {
                int i = tbVoucher.getSelectedRow();
                if (i != -1) { // Kiểm tra xem có hàng được chọn không
                    String maVC = txtMaVC.getText();
                    String trangThai = (String) tbVoucher.getValueAt(i, 6);
                    if (cbKhachVip.isSelected() && !trangThai.equals("Khách VIP")) {
                        qlKM.addKhachVIP(maVC);
                    } else if (cbKhachVip.isSelected() && trangThai.equals("Khách VIP")) {
                        System.out.println("Mã đã áp dụng cho khách VIP");
                    } else {
                        qlKM.dltKhachVIP(maVC);
                    }
                    checkUp();
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một Voucher!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//    ===========================COUPON==========================================

    public void LoadDataCoupon() {
        dtm = (DefaultTableModel) tbCoupon.getModel();
        dtm.setRowCount(0);
        for (Coupon e : qlKM.getAllCP()) {
            dtm.addRow(new Object[]{
                e.getMaCP(),
                e.getTenCP(),
                e.getIdSP(),
                e.getPhanTram(),
                e.getNgayBatDau(),
                e.getNgayKetThuc(),
                e.getTrangThai()
            });
        }
    }

    public void LoadDataCouponTheoMa(String ma) {
        dtm = (DefaultTableModel) tbCoupon.getModel();
        dtm.setRowCount(0);
        for (Coupon e : qlKM.getAllCP()) {
            dtm.addRow(new Object[]{
                e.getMaCP(),
                e.getTenCP(),
                e.getIdSP(),
                e.getPhanTram(),
                e.getNgayBatDau(),
                e.getNgayKetThuc(),
                e.getTrangThai()
            });
        }
    }

    public void DetailCouPon() {
        int i = tbCoupon.getSelectedRow();
        txtMaCP.setText(String.valueOf(tbCoupon.getValueAt(i, 0)));
        txtTenCP.setText(String.valueOf(tbCoupon.getValueAt(i, 1)));

        try {
            String idSP = String.valueOf(tbCoupon.getValueAt(i, 2));
            if (!idSP.isEmpty() && !idSP.equals("null")) {
                int id = Integer.parseInt(idSP);
                List<SanPham> spList = qlKM.selectSPbyID(id);
                String maSP = "";
                String tenSP = "";
                String soLuong = "";
                String giaBan = "";

                for (SanPham sp : spList) {
                    maSP = sp.getMaSanPham();
                    tenSP = sp.getTenSanPham();
                    soLuong = sp.getSoLuong();
                    giaBan = sp.getGiaBan();
                }
                txtIDSanPham.setText(idSP);
                txtMaSP.setText(maSP);
                txtTenSP.setText(tenSP);
                txtSoLuong.setText(soLuong);
                txtGiaBan.setText(giaBan);
            } else {
                // Xử lý khi mà idSP trống
                txtIDSanPham.setText("");
                txtMaSP.setText("");
                txtTenSP.setText("");
                txtSoLuong.setText("");
                txtGiaBan.setText("");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid idSP format");
            e.printStackTrace();
        }

        txtGiamGiaCP.setText(String.valueOf(tbCoupon.getValueAt(i, 3)));

        //Khỏi tạo simple dateFormat để ép kiểu từ table thành kiểu date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // detail ngày bắt đầu
        String dateBatDau = String.valueOf(tbCoupon.getValueAt(i, 4));
        Date startDate = null;
        try {
            startDate = dateFormat.parse(dateBatDau);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcNgayBatDau.setDate(startDate);
        // detail ngày kết thúc
        String dateKetThuc = String.valueOf(tbCoupon.getValueAt(i, 5));
        Date endDate = null;
        try {
            endDate = dateFormat.parse(dateKetThuc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dcNgayKetThuc.setDate(endDate);

        String tt = String.valueOf(tbCoupon.getValueAt(i, 6));
        if (tt.equalsIgnoreCase("Hoạt động")) {
            rbCPHoatDong.setSelected(true);
        } else if (tt.equalsIgnoreCase("Hết hạn")) {
            rbCPHetHan.setSelected(true);
        } else {
            rbChuaKH.setSelected(true);
        }
    }

    public boolean checkCP() throws ParseException {
        String macp = txtMaCP.getText().trim();
        String tencp = txtTenCP.getText().trim();
        String giamGiaStr = txtGiamGiaCP.getText().trim();
        // Kiểm tra xem ngày bắt đầu và ngày kết thúc có null không
        Date batDau = dcNgayBatDau.getDate();
        Date hetHan = dcNgayKetThuc.getDate();
        // Kiểm tra xem ngày bắt đầu và ngày kết thúc có null không
        if (tencp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên coupon");
            return false;
        }
        if (giamGiaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống giảm giá coupon");
            return false;
        }
        if (giamGiaStr.matches("\\D+")) {
            JOptionPane.showMessageDialog(this, "Không được để gõ chữ");
            return false;
        }
        if (batDau == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày bắt đầu");
            return false;
        }
        if (hetHan == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày kết thúc");
            return false;
        }
        // Kiểm tra ngày kết thúc phải lớn hơn ngày bắt đầu
        if (hetHan.before(batDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
            return false;
        }
        Date a = dcNgayBatDau.getDate();
        Date b = dcNgayKetThuc.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String bd = df.format(a);
        String kt = df.format(b);
        for (Coupon cp : qlKM.getAllCP()) {
            Date c = df.parse(cp.getNgayBatDau());
            Date d = df.parse(cp.getNgayKetThuc());
            if (Float.parseFloat(txtGiamGiaCP.getText()) == Float.parseFloat(cp.getPhanTram())
                    && a.equals(c) && b.equals(d)
                    && Float.parseFloat(cp.getPhanTram()) != 0 && c != null
                    && d != null) {
                JOptionPane.showMessageDialog(this, "Đã có Coupon giảm giá " + Float.parseFloat(txtGiamGiaCP.getText()) + " % và Thời gian: " + bd + " đến " + kt);
                return false;
            }
        }
        LocalDate ngayBatDau = LocalDate.parse(bd);
        LocalDate ngayKetThuc = LocalDate.parse(kt);
        LocalDate ngayHienTai = LocalDate.now();
        if (ngayKetThuc.isBefore(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Không thể Thêm mới Coupon có NgayKetThuc < NgayHienTai");
            return false;
        }
        if (ngayBatDau.isBefore(ngayHienTai)) {
            JOptionPane.showMessageDialog(this, "Không thể Thêm mới Coupon có NgayBatDau < NgayHienTai");
            return false;
        }
        return true;
    }

    public Coupon getFormCoupon() {
        Coupon cp = new Coupon();
        cp.setTenCP(txtTenCP.getText());
        cp.setPhanTram(txtGiamGiaCP.getText());

        Date a = dcNgayBatDau.getDate();
        Date b = dcNgayKetThuc.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String bd = df.format(a);
        String kt = df.format(b);

        cp.setNgayBatDau(bd + " 00:00:00");
        cp.setNgayKetThuc(kt + " 23:59:00");

        if (rbCPHoatDong.isSelected()) {
            cp.setTrangThai("Hoạt động");
        } else if (rbHetHan.isSelected()) {
            cp.setTrangThai("Hết hạn");
        } else {
            cp.setTrangThai("Chưa kích hoạt");
        }
        return cp;
    }

    public void addCoupon() {
        qlKM.addCP(getFormCoupon());
    }

    public void apDungSanPhamToCoupon() {
        int ma = Integer.valueOf(txtMaCP.getText());
        int id = Integer.valueOf(txtIDSanPham.getText());

        // Kiểm tra sản phẩm đã áp dụng coupon chưa
        if (qlKM.checkCouponAppliedToProduct(id, ma)) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã áp dụng coupon" + ma);
        } else {
            qlKM.addCoupontoSP(ma, id);
            JOptionPane.showMessageDialog(this, "Áp dụng thành công coupon!");
        }
    }

//    ============================SANPHAM========================================
    public void LoadDataSanPham() {
        dtm = (DefaultTableModel) tbSanPham.getModel();
        listSP = qlKM.getAllSP();
        dtm.setRowCount(0);
        for (SanPham e : listSP) {
            dtm.addRow(new Object[]{
                e.getIdspct(),
                e.getMaSanPham(),
                e.getTenSanPham(),
                e.getSoLuong(),
                e.getGiaBan()
            });
        }
    }

    public void DetailSanPham() {
        int i = tbSanPham.getSelectedRow();
        txtIDSanPham.setText(String.valueOf(tbSanPham.getValueAt(i, 0)));
        txtMaSP.setText(String.valueOf(tbSanPham.getValueAt(i, 1)));
        txtTenSP.setText(String.valueOf(tbSanPham.getValueAt(i, 2)));
        txtSoLuong.setText(String.valueOf(tbSanPham.getValueAt(i, 3)));
        txtGiaBan.setText(String.valueOf(tbSanPham.getValueAt(i, 4)));
    }
//=====================Kiểm tra thời gian=================================

    private class UpdateHetHan extends TimerTask {

        @Override
        public void run() {
            TrangThaiThoiGianVoucher();
            TrangThaiThoiGianCoupon();
        }
    }

    // Phương thức để cập nhật trạng thái khi hết hạn
    public void TrangThaiThoiGianVoucher() {
        Date batDau = dcBatDau.getDate();
        Date hetHan = dcKetThuc.getDate();
        if (hetHan != null) { // Kiểm tra xem hetHan có null không
            for (Voucher e : listVC) {
                if (hetHan.before(batDau)) {
                    rbHetHan.setSelected(true);
                    e.setTrangThai("Hết hạn");
                } else {
                    rbHoatDong.setSelected(true);
                    e.setTrangThai("Hoạt động");
                }
            }
            LoadDataTable(); // Cập nhật lại bảng hiển thị thông tin voucher
        }
    }

    public void TrangThaiThoiGianCoupon() {
        Date batDau = dcNgayBatDau.getDate();
        Date hetHan = dcNgayKetThuc.getDate();
        if (hetHan != null) { // Kiểm tra xem hetHan có null không
            for (Voucher e : listVC) {
                if (hetHan.before(batDau)) {
                    rbCPHetHan.setSelected(true);
                    e.setTrangThai("Hết hạn");
                } else {
                    rbCPHoatDong.setSelected(true);
                    e.setTrangThai("Hoạt động");
                }
            }
            LoadDataCoupon(); // Cập nhật lại bảng hiển thị thông tin voucher
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnKhuyenMai = new javax.swing.JTabbedPane();
        Voucher = new javax.swing.JPanel();
        btnAdd = new javax.swing.JToggleButton();
        btnUpdate = new javax.swing.JToggleButton();
        btnRefresh = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaVC = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenVC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGiamGiaVC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rbHoatDong = new javax.swing.JRadioButton();
        rbHetHan = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        cbKhachVip = new javax.swing.JCheckBox();
        dcBatDau = new com.toedter.calendar.JDateChooser();
        dcKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        txtDieuKien = new javax.swing.JTextField();
        rbChuaKichHoat = new javax.swing.JRadioButton();
        danhSachVC = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVoucher = new javax.swing.JTable();
        Coupon = new javax.swing.JPanel();
        pnThongTin = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIDSanPham = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnGiamGia = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtGiamGiaCP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dcNgayBatDau = new com.toedter.calendar.JDateChooser();
        dcNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtMaCP = new javax.swing.JTextField();
        txtTenCP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rbCPHoatDong = new javax.swing.JRadioButton();
        rbCPHetHan = new javax.swing.JRadioButton();
        rbChuaKH = new javax.swing.JRadioButton();
        pnCoupon = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCoupon = new javax.swing.JTable();
        pnSanPham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnApDung = new javax.swing.JButton();
        btnAddCoupon = new javax.swing.JButton();
        btnUpdateCP = new javax.swing.JButton();
        btnUpdateCP1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnKhuyenMai.setForeground(new java.awt.Color(255, 0, 0));
        pnKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnKhuyenMai.setPreferredSize(new java.awt.Dimension(1200, 700));

        Voucher.setPreferredSize(new java.awt.Dimension(1200, 700));

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

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã Voucher:");

        jLabel2.setText("Tên Voucher:");

        jLabel3.setText("Giảm giá:");

        jLabel4.setText("Ngày bắt đầu:");

        jLabel5.setText("Ngày kết  thúc:");

        jLabel6.setText("Trạng thái:");

        buttonGroup1.add(rbHoatDong);
        rbHoatDong.setSelected(true);
        rbHoatDong.setText("Hoạt động");

        buttonGroup1.add(rbHetHan);
        rbHetHan.setText("Hết hạn");

        jLabel15.setText("Ưu đãi riêng:");

        cbKhachVip.setText("Khách VIP");

        dcBatDau.setDateFormatString("yyyy-MM-dd");

        dcKetThuc.setDateFormatString("yyyy-MM-dd");

        jLabel19.setText("Điều kiện");

        buttonGroup1.add(rbChuaKichHoat);
        rbChuaKichHoat.setText("Chưa kích hoạt");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDieuKien))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenVC, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaVC, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiamGiaVC, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbKhachVip, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rbHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbChuaKichHoat)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtGiamGiaVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4))
                    .addComponent(dcBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dcKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rbHoatDong)
                    .addComponent(rbHetHan)
                    .addComponent(rbChuaKichHoat))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbKhachVip))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtDieuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        danhSachVC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách Voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã voucher", "Tên voucher", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Điều kiện", "Trạng thái KH", "Trạng thái"
            }
        ));
        tbVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbVoucher);

        javax.swing.GroupLayout danhSachVCLayout = new javax.swing.GroupLayout(danhSachVC);
        danhSachVC.setLayout(danhSachVCLayout);
        danhSachVCLayout.setHorizontalGroup(
            danhSachVCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhSachVCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addContainerGap())
        );
        danhSachVCLayout.setVerticalGroup(
            danhSachVCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(danhSachVCLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout VoucherLayout = new javax.swing.GroupLayout(Voucher);
        Voucher.setLayout(VoucherLayout);
        VoucherLayout.setHorizontalGroup(
            VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VoucherLayout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(danhSachVC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        VoucherLayout.setVerticalGroup(
            VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(danhSachVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(VoucherLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(VoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        pnKhuyenMai.addTab("Voucher", Voucher);

        Coupon.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel7.setText("ID SP:");

        txtIDSanPham.setEditable(false);

        txtMaSP.setEditable(false);

        jLabel8.setText("Mã SP:");

        txtTenSP.setEditable(false);

        jLabel9.setText("Tên SP:");

        txtSoLuong.setEditable(false);

        txtGiaBan.setEditable(false);

        jLabel13.setText("Giá bán:");

        jLabel14.setText("Số lượng:");

        javax.swing.GroupLayout pnThongTinLayout = new javax.swing.GroupLayout(pnThongTin);
        pnThongTin.setLayout(pnThongTinLayout);
        pnThongTinLayout.setHorizontalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThongTinLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        pnThongTinLayout.setVerticalGroup(
            pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnGiamGia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin Coupon", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel10.setText("Giảm giá:");

        jLabel11.setText("Ngày bắt đầu:");

        jLabel12.setText("Ngày kết thúc:");

        dcNgayBatDau.setDateFormatString("yyyy-MM-dd");

        dcNgayKetThuc.setDateFormatString("yyyy-MM-dd");

        jLabel16.setText("Mã Coupon");

        txtMaCP.setEditable(false);

        jLabel17.setText("Tên Coupon");

        jLabel18.setText("Trạng thái");

        buttonGroup2.add(rbCPHoatDong);
        rbCPHoatDong.setSelected(true);
        rbCPHoatDong.setText("Hoạt động");

        buttonGroup2.add(rbCPHetHan);
        rbCPHetHan.setText("Hết hạn");

        buttonGroup2.add(rbChuaKH);
        rbChuaKH.setText("Chưa kích hoạt");

        javax.swing.GroupLayout pnGiamGiaLayout = new javax.swing.GroupLayout(pnGiamGia);
        pnGiamGia.setLayout(pnGiamGiaLayout);
        pnGiamGiaLayout.setHorizontalGroup(
            pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnGiamGiaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiamGiaCP, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txtMaCP))
                .addGap(44, 44, 44)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(txtTenCP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnGiamGiaLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnGiamGiaLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(rbCPHoatDong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbCPHetHan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbChuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        pnGiamGiaLayout.setVerticalGroup(
            pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnGiamGiaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtMaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(txtTenCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbCPHoatDong)
                    .addComponent(rbCPHetHan)
                    .addComponent(rbChuaKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtGiamGiaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)))
                .addGap(17, 17, 17))
        );

        pnCoupon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COUPON", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbCoupon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Coupon", "Tên Coupon", "ID SP", "Giảm giá ", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbCoupon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCouponMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCoupon);

        javax.swing.GroupLayout pnCouponLayout = new javax.swing.GroupLayout(pnCoupon);
        pnCoupon.setLayout(pnCouponLayout);
        pnCouponLayout.setHorizontalGroup(
            pnCouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCouponLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnCouponLayout.setVerticalGroup(
            pnCouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCouponLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDSP", "Mã SP", "Tên sản phẩm", "Số lượng", "Giá bán"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSanPham);

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSanPhamLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnApDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnApDung.setText("Áp dụng");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        btnAddCoupon.setText("Add");
        btnAddCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCouponActionPerformed(evt);
            }
        });

        btnUpdateCP.setText("Update");
        btnUpdateCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCPActionPerformed(evt);
            }
        });

        btnUpdateCP1.setText("New");
        btnUpdateCP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CouponLayout = new javax.swing.GroupLayout(Coupon);
        Coupon.setLayout(CouponLayout);
        CouponLayout.setHorizontalGroup(
            CouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CouponLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CouponLayout.createSequentialGroup()
                        .addComponent(pnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CouponLayout.createSequentialGroup()
                        .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateCP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateCP1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CouponLayout.setVerticalGroup(
            CouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CouponLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CouponLayout.createSequentialGroup()
                        .addGroup(CouponLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateCP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateCP1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(pnCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pnKhuyenMai.addTab("Coupon", Coupon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVoucherMouseClicked
        int i = tbVoucher.getSelectedRow();
        detail();
//        LoadDataTableTheoMa(String.valueOf(tbVoucher.getValueAt(i, 0)));
    }//GEN-LAST:event_tbVoucherMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (checkVC()) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date bd = dcBatDau.getDate();
            Date kt = dcKetThuc.getDate();

            Date now = new Date();
            if (bd.compareTo(now) < 0) {
                JOptionPane.showMessageDialog(this, "Không được Add Voucher có NgayBatDau < NgayHienTai");
                return;
            }
            for (Voucher vc : qlKM.getAllVCAdd(Float.valueOf(txtDieuKien.getText()))) {
                try {
                    Date a = df.parse(vc.getNgayBatDau());
                    Date b = df.parse(vc.getNgayKetThuc());
                    if (kt.compareTo(a) >= 0 && kt.compareTo(b) <= 0) {
                        JOptionPane.showMessageDialog(this, "NgayKetThuc của Voucher mới nằm giữa NgayBatDau va NgayKetThuc của Voucher khác. Vui lòng nhập lại");
                        return;
                    }
                    if (bd.compareTo(a) >= 0 && bd.compareTo(b) <= 0) {
                        JOptionPane.showMessageDialog(this, "NgayBatDau của Voucher mới nằm giữa NgayBatDau va NgayKetThuc của Voucher khác. Vui lòng nhập lại");
                        return;
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            qlKM.addVoucher(getFormVoucher(), txtMaVC.getText());
            LoadDataTable();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        LoadDataTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateVC();
        LoadDataTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tbCouponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCouponMouseClicked
        try {
            int i = tbCoupon.getSelectedRow();
            DetailCouPon();
//            LoadDataCouponTheoMa(String.valueOf(tbCoupon.getValueAt(i, 0)));
        } catch (Exception e) {
            System.out.println("loi");
        }
    }//GEN-LAST:event_tbCouponMouseClicked

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int i = tbSanPham.getSelectedRow();
        DetailSanPham();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnAddCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCouponActionPerformed
        try {
            if (checkCP()) {
                Date now = new Date();
                Date a = dcNgayBatDau.getDate();
                Date b = dcNgayKetThuc.getDate();

                if (now.after(a) && now.before(b)) {
                    rbHoatDong.isSelected();
                } else {
                    rbChuaKH.isSelected();
                }

                addCoupon();
                LoadDataCoupon();
                JOptionPane.showMessageDialog(this, "Thêm mới coupon thành công!");

            }
        } catch (ParseException ex) {
            Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddCouponActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        if (txtIDSanPham.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "IdSp trong khong the ap dung");
            return;
        }
        int id = Integer.parseInt(txtIDSanPham.getText());

        Date a = dcNgayBatDau.getDate();
        Date b = dcNgayKetThuc.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Coupon cp : qlKM.getListTheoIdSP(id)) {
            try {
                Date c = dateFormat.parse(cp.getNgayBatDau());
                Date d = dateFormat.parse(cp.getNgayKetThuc());
                if ((b.after(c) && c != null && b.before(d) && d != null)) {
                    JOptionPane.showMessageDialog(this, "NgayKetThuc khong hop le");
                    return;
                }
                if ((b.equals(c) && c != null)) {
                    JOptionPane.showMessageDialog(this, "Qua NgayBatDau cua CP khac cung IdSP");
                    return;
                }
                if ((a.equals(d) && d != null)) {
                    JOptionPane.showMessageDialog(this, "Qua NgayKetThuc cua CP khac cung IdSP");
                    return;
                }
                if ((a.before(d) && d != null && a.after(c) && c != null)) {
                    JOptionPane.showMessageDialog(this, "NgayBatDau khong hop le");
                    return;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        apDungSanPhamToCoupon();
        LoadDataCoupon();
    }//GEN-LAST:event_btnApDungActionPerformed

    private void btnUpdateCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCPActionPerformed
        try {
            // TODO add your handling code here:
            if (checkCP()) {
                if (txtIDSanPham.getText().equals("")) {
                    qlKM.updCP1(getFormCoupon(), Integer.valueOf(txtMaCP.getText()));
                    LoadDataCoupon();
                    return;
                }
                int id = Integer.parseInt(txtIDSanPham.getText());
                int ma = Integer.parseInt(txtMaCP.getText());
                Date a1 = dcNgayBatDau.getDate();
                Date b1 = dcNgayKetThuc.getDate();
                Date a = new Date(a1.getTime());
                Date b = new Date(b1.getTime());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Coupon cp : qlKM.getListTheoIdSPVaMaVC(id, ma)) {
                    try {
                        Date c = dateFormat.parse(cp.getNgayBatDau());
                        Date d = dateFormat.parse(cp.getNgayKetThuc());
                        if ((b.after(c) && c != null && a.before(c) && d.after(b) && d != null)) {
                            JOptionPane.showMessageDialog(this, "NgayKetThuc khong hop le");
                            return;
                        }
                        if ((b.equals(c) && c != null)) {
                            JOptionPane.showMessageDialog(this, "Qua NgayBatDau cua CP khac cung IdSP");
                            return;
                        }
                        if ((a.equals(d) && d != null)) {
                            JOptionPane.showMessageDialog(this, "Qua NgayKetThuc cua CP khac cung IdSP");
                            return;
                        }
                        if ((a.before(d) && d != null && b.after(d) && c.before(a))) {
                            JOptionPane.showMessageDialog(this, "NgayBatDau khong hop le");
                            return;
                        }
                        if (a.after(c) && b.before(d) && c != null & d != null) {
                            JOptionPane.showMessageDialog(this, "NgayBatDau va NgayKetThuc trung voi CP khac");
                            return;
                        }
                        if (a.before(c) && b.after(d) && c != null & d != null) {
                            JOptionPane.showMessageDialog(this, "NgayBatDau va NgayKetThuc trung voi CP khac");
                            return;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Date hienTai = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String now = df.format(hienTai);

                Coupon cp = new Coupon();
                cp.setTenCP(txtTenCP.getText());
                cp.setPhanTram(txtGiamGiaCP.getText());
                cp.setIdSP(txtIDSanPham.getText());
                Date m = dcNgayBatDau.getDate();
                Date n = dcNgayKetThuc.getDate();
                String ngayBatDau = df.format(m);
                String ngayKetThuc = df.format(n);
                for (Coupon cou : qlKM.getListNgay(ma)) {
                    try {
                        Date i = df.parse(cou.getNgayBatDau());
                        Date j = df.parse(cou.getNgayKetThuc());

                        if (m != null && hienTai.before(m) || hienTai.after(n)) {
                            cp.setNgayBatDau(ngayBatDau);
                        } else {
                            cp.setNgayBatDau(now);
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                cp.setNgayKetThuc(ngayKetThuc);

//                Date nowDate = new Date();
                LocalDate nowDate = LocalDate.now();
                LocalDate dcNgayDatDauLocalDate = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (dcNgayDatDauLocalDate.equals(nowDate)) {
                    rbHoatDong.setSelected(true);
                    cp.setTrangThai("Hoạt động");
                } else {
                    rbChuaKH.setSelected(true);
                    cp.setTrangThai("Chưa kích hoạt");
                }
                qlKM.updCP(cp, Integer.valueOf(txtMaCP.getText()), now);
                LoadDataCoupon();
            }
        } catch (ParseException ex) {
            Logger.getLogger(ViewKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnUpdateCPActionPerformed

    private void btnUpdateCP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCP1ActionPerformed
        // TODO add your handling code here:
        txtMaCP.setText("");
        txtTenCP.setText("");
        txtGiamGiaCP.setText("");
        dcNgayBatDau.setDate(null);
        dcNgayKetThuc.setDate(null);
        rbCPHoatDong.setSelected(true);
        txtIDSanPham.setText("");
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        txtMaSP.setText("");
    }//GEN-LAST:event_btnUpdateCP1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewKhuyenMai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewKhuyenMai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Coupon;
    private javax.swing.JPanel Voucher;
    private javax.swing.JToggleButton btnAdd;
    private javax.swing.JButton btnAddCoupon;
    private javax.swing.JButton btnApDung;
    private javax.swing.JToggleButton btnRefresh;
    private javax.swing.JToggleButton btnUpdate;
    private javax.swing.JButton btnUpdateCP;
    private javax.swing.JButton btnUpdateCP1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbKhachVip;
    private javax.swing.JPanel danhSachVC;
    private com.toedter.calendar.JDateChooser dcBatDau;
    private com.toedter.calendar.JDateChooser dcKetThuc;
    private com.toedter.calendar.JDateChooser dcNgayBatDau;
    private com.toedter.calendar.JDateChooser dcNgayKetThuc;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnCoupon;
    private javax.swing.JPanel pnGiamGia;
    private javax.swing.JTabbedPane pnKhuyenMai;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnThongTin;
    private javax.swing.JRadioButton rbCPHetHan;
    private javax.swing.JRadioButton rbCPHoatDong;
    private javax.swing.JRadioButton rbChuaKH;
    private javax.swing.JRadioButton rbChuaKichHoat;
    private javax.swing.JRadioButton rbHetHan;
    private javax.swing.JRadioButton rbHoatDong;
    private javax.swing.JTable tbCoupon;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbVoucher;
    private javax.swing.JTextField txtDieuKien;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiamGiaCP;
    private javax.swing.JTextField txtGiamGiaVC;
    private javax.swing.JTextField txtIDSanPham;
    private javax.swing.JTextField txtMaCP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaVC;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenCP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenVC;
    // End of variables declaration//GEN-END:variables
}
