package GUI.Dialog;

import BUS.KhuVucKhoBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.ThuongHieuBUS;
import BUS.XuatXuBUS;
import DAO.PBSanPhamDao;
import DAO.SanPhamDAO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import helper.Formater;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class SanPhamDialog extends JDialog implements ActionListener {

    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
    private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCTCauHinh, btnDeleteCauHinh, btnResetCauHinh, btnAddSanPham, btnBack, btnViewCauHinh;
    InputForm tenSP;
    InputForm txtgianhap, txtgiaxuat;
    SelectForm xuatxu;
    SelectForm thuonghieu, khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh;
    JScrollPane scrolltblcauhinh;
    DefaultTableModel tblModelch;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;

    
    KhuVucKhoBUS kvkhoBus = new KhuVucKhoBUS();
    ThuongHieuBUS thuonghieuBus = new ThuongHieuBUS();
    XuatXuBUS xuatXuBUS = new XuatXuBUS();

        ArrayList<PhienBanSanPhamDTO> listch = new ArrayList<>();
    SanPhamDTO sp;
    String[] arrkhuvuc;
    String[] arrthuonghieu;
    String[] arrXX;
    int masp;
    int mach;
    private ButtonCustom btnEditCT;
    private ButtonCustom btnSaveCH;
    private ButtonCustom btnAddCauHinhEdit;
    private ButtonCustom btnEditCTCauHinhEdit;
    private ButtonCustom btnDeleteCauHinhEdit;
    private ButtonCustom btnResetCauHinhEdit;

    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
        masp = jpSP.spBUS.spDAO.getAutoIncrement();
        mach = PBSanPhamDao.getInstance().getAutoIncrement();
        arrkhuvuc = kvkhoBus.getArrTenKhuVuc();
        arrthuonghieu = thuonghieuBus.getArrTenThuongHieu();
        arrXX = xuatXuBUS.getArrTenXuatXu();
        
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        initComponents(title, type);
    }

    public void initCardOne(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

        tenSP = new InputForm("Tên sản phẩm");
        xuatxu = new SelectForm("Xuất xứ", arrXX);

        thuonghieu = new SelectForm("Thương hiệu", arrthuonghieu);
        khuvuc = new SelectForm("Khu vực kho", arrkhuvuc);
        hinhanh = new InputImage("Hình minh họa");
         pninfosanpham.add(tenSP);
        pninfosanpham.add(xuatxu);
        pninfosanpham.add(thuonghieu);
        pninfosanpham.add(khuvuc);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        switch (type) {
            case "view" -> {
                btnViewCauHinh = new ButtonCustom("Xem cấu hình", "warning", 14);
                btnViewCauHinh.addActionListener(this);
                pnbottom.add(btnViewCauHinh);
            }
            case "update" -> {
                btnSaveCH = new ButtonCustom("Lưu thông tin", "success", 14);
                btnEditCT = new ButtonCustom("Sửa cấu hình", "warning", 14);
                btnSaveCH.addActionListener(this);
                btnEditCT.addActionListener(this);
                pnbottom.add(btnSaveCH);
                pnbottom.add(btnEditCT);
            }
            case "create" -> {
                btnThemCHMS = new ButtonCustom("Tạo cấu hình", "success", 14);
                btnThemCHMS.addActionListener(this);
                pnbottom.add(btnThemCHMS);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initCardTwo(String type) {
        pncard2 = new JPanel(new BorderLayout());
        JPanel cauhinhtop = new JPanel(new GridLayout(1, 5));
        txtgianhap = new InputForm("Giá nhập");
        PlainDocument nhap = (PlainDocument)txtgianhap.getTxtForm().getDocument();
        nhap.setDocumentFilter((new NumericDocumentFilter()));
        txtgiaxuat = new InputForm("Giá xuất");
        PlainDocument xuat = (PlainDocument)txtgiaxuat.getTxtForm().getDocument();
        xuat.setDocumentFilter((new NumericDocumentFilter()));
        cauhinhtop.add(txtgianhap);
        cauhinhtop.add(txtgiaxuat);

        JPanel cauhinhcenter = new JPanel(new BorderLayout());

        JPanel cauhinhcenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(cauhinhcenter_left, BoxLayout.Y_AXIS);
        cauhinhcenter_left.setPreferredSize(new Dimension(100, 226));
        cauhinhcenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        cauhinhcenter_left.setLayout(bl);
        cauhinhcenter_left.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        tblcauhinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = getRowCauHinh();
                if (index != -1) {
                    setInfoCauHinh(listch.get(index));
                }
            }
        });

        scrolltblcauhinh = new JScrollPane(tblcauhinh);
        tblModelch = new DefaultTableModel();
        String[] header = new String[]{"STT", "RAM", "ROM", "Màu sắc", "Giá nhập", "Giá xuất"};
        tblModelch.setColumnIdentifiers(header);
        tblcauhinh.setModel(tblModelch);
        scrolltblcauhinh.setViewportView(tblcauhinh);
        tblcauhinh.setDefaultRenderer(Object.class, centerRenderer);
        cauhinhcenter_left.add(scrolltblcauhinh);

        JPanel cauhinhcenter_right = new JPanel(new FlowLayout());
        cauhinhcenter_right.setPreferredSize(new Dimension(180, 10));
        cauhinhcenter_right.setBackground(Color.white);
        cauhinhcenter_right.setBorder(new EmptyBorder(0, 0, 0, 10));
        if(!type.equals("update")){
        btnAddCauHinh = new ButtonCustom("Thêm cấu hình", "success", 14);
        btnEditCTCauHinh = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btnDeleteCauHinh = new ButtonCustom("Xoá cấu hình", "danger", 14);
        btnResetCauHinh = new ButtonCustom("Làm mới", "excel", 14);

        btnAddCauHinh.addActionListener(this);
        btnEditCTCauHinh.addActionListener(this);
        btnDeleteCauHinh.addActionListener(this);
        btnResetCauHinh.addActionListener(this);
        cauhinhcenter_right.add(btnAddCauHinh);
        cauhinhcenter_right.add(btnEditCTCauHinh);
        cauhinhcenter_right.add(btnDeleteCauHinh);
        cauhinhcenter_right.add(btnResetCauHinh);
        } else {
        btnAddCauHinhEdit = new ButtonCustom("Thêm cấu hình", "success", 14);
        btnEditCTCauHinhEdit = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btnDeleteCauHinhEdit = new ButtonCustom("Xoá cấu hình", "danger", 14);
        btnResetCauHinhEdit = new ButtonCustom("Làm mới", "excel", 14);
        
        btnAddCauHinhEdit.addActionListener(this);
        btnEditCTCauHinhEdit.addActionListener(this);
        btnDeleteCauHinhEdit.addActionListener(this);
        btnResetCauHinhEdit.addActionListener(this);
        
        cauhinhcenter_right.add(btnAddCauHinhEdit);
        cauhinhcenter_right.add(btnEditCTCauHinhEdit);
        cauhinhcenter_right.add(btnDeleteCauHinhEdit);
        cauhinhcenter_right.add(btnResetCauHinhEdit);
        }
        

        
        cauhinhcenter.add(cauhinhcenter_left, BorderLayout.CENTER);
        cauhinhcenter.add(cauhinhcenter_right, BorderLayout.EAST);

        JPanel cauhinhbottom = new JPanel(new FlowLayout());
        cauhinhbottom.setBackground(Color.white);
        cauhinhbottom.setBorder(new EmptyBorder(0, 0, 10, 0));

        switch (type) {
            case "view" -> {
                loadDataToTableCauHinh(listch);
                btnAddCauHinh.setVisible(false);
                btnEditCTCauHinh.setVisible(false);
                btnDeleteCauHinh.setVisible(false);
                btnResetCauHinh.setVisible(false);
                cauhinhcenter.remove(cauhinhcenter_right);
            }
            case "update" -> loadDataToTableCauHinh(listch);
            case "create" -> {
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                cauhinhbottom.add(btnAddSanPham);
            }
        }
        
        btnBack = new ButtonCustom("Quay lại trang trước", "warning", 14);
        btnBack.addActionListener(this);
        cauhinhbottom.add(btnBack);

        pncard2.add(cauhinhtop, BorderLayout.NORTH);
        pncard2.add(cauhinhcenter, BorderLayout.CENTER);
        pncard2.add(cauhinhbottom, BorderLayout.SOUTH);

    }

    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCardOne(type);
        initCardTwo(type);

        pnmain.add(pnCenter);
        pnmain.add(pncard2);

        switch (type) {
            case "view" -> setInfo(sp);
            case "update" -> setInfo(sp);
            default -> {
            }
        }
//                throw new AssertionError();

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/img_product";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnThemCHMS && validateCardOne()) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnBack) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.previous(pnmain);
        } else if (source == btnAddCauHinh) {
            if (validateCardTwo() && checkTonTai()) {
                listch.add(getCauHinh());
                loadDataToTableCauHinh(this.listch);
                resetFormCauHinh();
            }
        } else if (source == btnResetCauHinh) {
            resetFormCauHinh();
            loadDataToTableCauHinh(this.listch);
        } else if (source == btnDeleteCauHinh) {
            int index = getRowCauHinh();
            this.listch.remove(index);
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
        } else if (source == btnEditCTCauHinh) {
            eventEditCauHinh();
           loadDataToTableCauHinh(this.listch);
        } else if (source == btnAddSanPham) {
            eventAddSanPham();
        } else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnEditCT){
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if(source == btnSaveCH){
            SanPhamDTO snNew = getInfo();
            if(!snNew.getHinhanh().equals(this.sp.getHinhanh())){
                snNew.setHinhanh(addImage(snNew.getHinhanh()));
            }
            snNew.setMasp(this.sp.getMasp());
            SanPhamDAO.getInstance().update(sp);
            this.jpSP.spBUS.update(snNew);
            this.jpSP.loadDataTalbe(this.jpSP.spBUS.getAll());
            int input = JOptionPane.showConfirmDialog(this, 
                "Bạn có muốn chỉnh sửa chi tiết sản phẩm?", "Chỉnh sửa chi tiết", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            // 0=ok, 2=cancel
            if(input == 0){
                CardLayout c = (CardLayout) pnmain.getLayout();
                c.next(pnmain);
            }
        }
        if(source == btnEditCTCauHinhEdit){
            if (validateCardTwo()) {
            int index = getRowCauHinh();
            if(index < 0){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình");
            } else {
            listch.get(index).setGianhap(Integer.parseInt(txtgianhap.getText()));
            listch.get(index).setGiaxuat(Integer.parseInt(txtgiaxuat.getText()));
            PBSanPhamDao.getInstance().update(listch.get(index));
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
            }
            
        }
        }
        if(source == btnDeleteCauHinhEdit){
            int index = getRowCauHinh();
            PhienBanSanPhamDAO.getInstance().delete(this.listch.get(index).getMaphienbansp()+"");
            this.listch.remove(index);
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
        }
        if( source == btnAddCauHinhEdit){
            if(validateCardTwo() && checkTonTai()){
            PhienBanSanPhamDAO.getInstance().insert(getCauHinh(sp.getMasp()));
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
            }
        }
        if(source == btnResetCauHinhEdit){
            resetFormCauHinh();
            loadDataToTableCauHinh(this.listch);
        }
        if(source == btnHuyBo){
            dispose();
        }
    }

    public void eventAddSanPham() {
        SanPhamDTO sp = getInfo();
        sp.setHinhanh(addImage(sp.getHinhanh()));
        if (jpSP.spBUS.add(sp, listch)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        }
    }

    public void eventEditCauHinh() {
        if (validateCardTwo()) {
            int index = getRowCauHinh();
            if(index < 0){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình");
            } else { 
            listch.get(index).setGianhap(Integer.parseInt(txtgianhap.getText()));
            listch.get(index).setGiaxuat(Integer.parseInt(txtgiaxuat.getText()));
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
            }
            
        }
    }

    public int getRowCauHinh() {
        int index = tblcauhinh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình !");
        }
        return index;
    }

    public SanPhamDTO getInfo() {
        String hinhanh = this.hinhanh.getUrl_img();
        String vtensp = tenSP.getText();
        int vxuatxu = xuatXuBUS.getAll().get(this.xuatxu.getSelectedIndex()).getMaxuatxu();
   
        int vthuonghieu = thuonghieuBus.getAll().get(this.thuonghieu.getSelectedIndex()).getMathuonghieu();
        int khuvuckho = kvkhoBus.getAll().get(this.khuvuc.getSelectedIndex()).getMakhuvuc();
        SanPhamDTO result = new SanPhamDTO(masp, vtensp, hinhanh, vxuatxu,  vthuonghieu, khuvuckho, 0);
        return result;
    }

    public void setInfo(SanPhamDTO sp) {
        hinhanh.setUrl_img(sp.getHinhanh());
        tenSP.setText(sp.getTensp());
        xuatxu.setSelectedItem(sp.getXuatxu());
 
        thuonghieu.setSelectedIndex(thuonghieuBus.getIndexByMaLH(sp.getThuonghieu()));
        khuvuc.setSelectedIndex(jpSP.spBUS.getIndexByMaSP(sp.getKhuvuckho()));
    }

    public PhienBanSanPhamDTO getCauHinh() {
      
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        PhienBanSanPhamDTO chsp = new PhienBanSanPhamDTO(mach, masp, gianhap, giaban,0);
        mach++;
        return chsp;
    }
    
       public PhienBanSanPhamDTO getCauHinh(int masanpham) {
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        PhienBanSanPhamDTO chsp = new PhienBanSanPhamDTO(PBSanPhamDao.getInstance().getAutoIncrement(), masanpham, gianhap, giaban);
        this.listch.add(chsp);
        return chsp;
    }

    public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) || Validation.isEmpty((String) xuatxu.getSelectedItem()) ) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            // Check number 
        }
        return check;
    }

    public boolean validateCardTwo() {
        boolean check = true;
        if (Validation.isEmpty(txtgianhap.getText()) && Validation.isEmpty(txtgiaxuat.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            check = false;
        } else {

        }
        return check;
    }

    public boolean checkTonTai() {
        boolean check = true;
        if (PhienBanSanPhamBUS.checkDuplicate(listch, getCauHinh())) {
            JOptionPane.showMessageDialog(this, "Cấu hình đã tồn tại !");
            check = false;
        }
        return check;
    }

    public void loadDataToTableCauHinh(ArrayList<PhienBanSanPhamDTO> ch) {
        tblModelch.setRowCount(0);
        for (int i = 0; i < ch.size(); i++) {
            tblModelch.addRow(new Object[]{i + 1,
              Formater.FormatVND(ch.get(i).getGiaxuat())
            });
        }
    }

    public void resetFormCauHinh() {        
        txtgianhap.setText("");
        txtgiaxuat.setText("");
    }

     public void setInfoCauHinh(PhienBanSanPhamDTO ch) {
        txtgianhap.setText(Integer.toString(ch.getGianhap()));
        txtgiaxuat.setText(Integer.toString(ch.getGiaxuat()));
    }
 
}
