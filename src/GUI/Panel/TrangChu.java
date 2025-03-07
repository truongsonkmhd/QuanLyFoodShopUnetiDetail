package GUI.Panel;
import java.awt.*;
import javax.swing.*;
import GUI.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class TrangChu extends JPanel {
    JPanel top, center, bar1, bar2;
    PanelShadow content[];
    JPanel info[];
    JLabel title, subTit, infoDetail[], objDetail[], objDetail1[], infoIcon[];
    String[][] getSt = {
        {"Tính chính xác", "tinhchinhxac_128px.svg", "<html>Với sự quản lý chặt chẽ <br>cho từng loại sản phẩm,<br> do đó hệ thống quản lý thực phẩm<br> sẽ đảm bảo , uy tín tính  <br>chính xác và độ tin cậy cao.</html>"},
        {"Tính bảo mật", "tinhbaomat_128px.svg", "<html>Ngăn chặn việc thiếu hụt hàng<br> hoá một cách đáng tiếc.<br> Điều này giúp tăng hiệu suất cho  <br>doanh thu và người quản lý.</html>"},
        {"Tính hiệu quả", "tinhhieuqua_128px.svg", "<html>Dễ dàng quản lý được dòng tiền<br>về từng thiết bị của bạn <br>nhanh chóng và chính xác, giúp <br>cho việc quản lý thực phẩm được <br>thực hiện một cách hiệu quả hơn.</html>"},
    };
    
    // Bảng màu chính cho ứng dụng
    Color MainColor = new Color(255, 255, 255);          // Màu trắng cho nền chính
    Color FontColor = new Color(96, 125, 139);           // Màu xám xanh cho font
    Color BackgroundColor = new Color(240, 247, 250);    // Màu nền xanh nhạt
    Color HowerFontColor = new Color(225, 230, 232);     // Màu font khi hover
    
    // Màu sắc bổ sung
    Color PrimaryColor = new Color(0, 102, 204);         // Màu xanh dương cho tiêu đề
    Color AccentColor = new Color(255, 87, 34);          // Màu cam đậm cho nhấn mạnh
    Color SecondaryColor = new Color(0, 150, 136);       // Màu xanh lá cho các chi tiết phụ
    Color CardBackgroundColor = new Color(250, 250, 250); // Màu nền card nhẹ hơn
    Color BorderColor = new Color(224, 224, 224);        // Màu viền
    Color FooterColor = new Color(33, 43, 54);           // Màu tối cho footer
    
    private void initComponent() {
        // Thiết lập nền tối cho panel chính
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        
        // Panel trên cùng
        top = new JPanel();
        top.setBackground(new Color(232, 245, 255)); // Màu nền nhẹ hơn cho phần top
        top.setPreferredSize(new Dimension(1100, 200));
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30)); // Điều chỉnh padding để căn giữa tiêu đề
        
        // Tiêu đề chính với đường viền
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(new Color(232, 245, 255));
        
        JLabel slogan = new JLabel("QUẢN LÝ HỆ THỐNG BÁN HÀNG THỰC PHẨM UNETI");
        slogan.setFont(new Font("Arial", Font.BOLD, 40));
        slogan.setForeground(PrimaryColor);
        slogan.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa text
        
        // Tạo đường viền dưới cho tiêu đề
        JPanel underline = new JPanel();
        underline.setPreferredSize(new Dimension(800, 3));
        underline.setBackground(AccentColor);
        
        titlePanel.add(slogan, BorderLayout.CENTER);
        titlePanel.add(underline, BorderLayout.SOUTH);
        
        top.add(titlePanel);
        this.add(top, BorderLayout.NORTH);
        
        // Panel trung tâm
        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        
        // Panel cho nội dung chính
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(BackgroundColor);
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        // Khởi tạo các phần tử
        content = new PanelShadow[getSt.length];
        info = new JPanel[3];
        infoDetail = new JLabel[3];
        objDetail = new JLabel[3];
        objDetail1 = new JLabel[3];
        infoIcon = new JLabel[3];
        
        // Khởi tạo và thêm các panel thông tin với màu sắc
        Color[] cardColors = {
            new Color(232, 245, 233), // Xanh lá nhạt
            new Color(232, 234, 246), // Tím nhạt
            new Color(255, 243, 224)  // Cam nhạt
        };
        
        Color[] titleColors = {
            SecondaryColor,           // Xanh lá cho tiêu đề thẻ 1
            new Color(123, 31, 162),  // Tím cho tiêu đề thẻ 2
            AccentColor               // Cam cho tiêu đề thẻ 3
        };
        
        for (int i = 0; i < getSt.length; i++) {
            content[i] = new PanelShadow(getSt[i][1], getSt[i][0], getSt[i][2]);
            contentPanel.add(content[i]);
        }
        
        // Thêm vào panel trung tâm
        center.add(contentPanel);
        
        // Thêm khoảng trống giữa nội dung và footer
        JPanel spacer = new JPanel();
        spacer.setBackground(BackgroundColor);
        spacer.setPreferredSize(new Dimension(1100, 80)); // Khoảng cách 80px
        spacer.setMinimumSize(new Dimension(1100, 80));
        spacer.setMaximumSize(new Dimension(Short.MAX_VALUE, 80));
        center.add(spacer);
        
        this.add(center, BorderLayout.CENTER);
        
        // Thêm footer
        JPanel footer = new JPanel();
        footer.setBackground(FooterColor); // Màu tối 
        footer.setPreferredSize(new Dimension(1100, 60));
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        
        JLabel footerText = new JLabel("© 2025 Hệ Thống Quản Lý Thực Phẩm UNETI");
        footerText.setForeground(Color.WHITE);
        footerText.setFont(new Font("Arial", Font.PLAIN, 14));
        
        footer.add(footerText);
        this.add(footer, BorderLayout.SOUTH);
    }
    
    public TrangChu() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }
}