package GUI;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.prefs.Preferences;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
import helper.BCrypt;

public class Log_In extends JFrame implements KeyListener {

    JPanel pnlMain, pnlLogIn;
    JLabel lblImage, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    InputForm txtUsername, txtPassword;
    JCheckBox chkRemember;
    Preferences prefs;

    Color FontColor = new Color(96, 125, 139);

    public Log_In() {
        prefs = Preferences.userNodeForPackage(Log_In.class);
        initComponent();
        loadSavedCredentials();
    }

    private void initComponent() {
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Đăng nhập");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlMain = new JPanel();
        pnlMain.setBackground(Color.white);
        pnlMain.setBorder(new EmptyBorder(20, 0, 0, 0));
        pnlMain.setPreferredSize(new Dimension(500, 740));
        pnlMain.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        lbl3 = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 20));
        pnlMain.add(lbl3);

        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.BLACK);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));

        txtUsername = new InputForm("Tên đăng nhập");
        paneldn.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        paneldn.add(txtPassword);

        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);


        pnlMain.add(paneldn);

        lbl6 = new JLabel("ĐĂNG NHẬP");
        lbl6.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lbl6.setForeground(Color.white);

        pnlLogIn = new JPanel();
        pnlLogIn.putClientProperty(FlatClientProperties.STYLE, "arc: 99");
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));

        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                try {

                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pnlLogIn.add(lbl6);

        JPanel pnlOptions = new JPanel();
        pnlOptions.setPreferredSize(new Dimension(380, 50));
        pnlOptions.setBackground(Color.WHITE);
        pnlOptions.setLayout(new BorderLayout()); // Sử dụng BorderLayout để căn chỉnh các phần tử

        chkRemember = new JCheckBox("Ghi nhớ mật khẩu");
        chkRemember.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        chkRemember.addActionListener(e -> saveCredentials());
        chkRemember.setBackground(Color.WHITE);

        lbl7 = new JLabel("Quên mật khẩu");
        lbl7.setFont(new Font(FlatRobotoFont.FAMILY, Font.ITALIC, 18));

        pnlOptions.add(chkRemember, BorderLayout.WEST); // Ghi nhớ mật khẩu dính vào lề trái
        pnlOptions.add(lbl7, BorderLayout.EAST); // Quên mật khẩu dính vào lề phải

        pnlMain.add(pnlOptions);
        pnlMain.add(pnlLogIn);

        this.add(pnlMain);
    }

    public void checkLogin() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
       if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            TaiKhoanDTO tk = TaiKhoanDAO.getInstance().selectByUser(usernameCheck);
            if (tk == null) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (tk.getTrangthai() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (BCrypt.checkpw(passwordCheck, tk.getMatkhau())) {
                        try {
                            this.dispose();
                            Main main = new Main(tk);
                            main.setVisible(true);
                        } catch (UnsupportedLookAndFeelException ex) {
                            Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        }
    }

    private void saveCredentials() {
        prefs.put("username", txtUsername.getText());
        prefs.put("password", txtPassword.getPass());
    }

    private void loadSavedCredentials() {
        String savedUsername = prefs.get("username", "");
        String savedPassword = prefs.get("password", "");
        txtUsername.setText(savedUsername);
        txtPassword.setPass(savedPassword);
        chkRemember.setSelected(!savedUsername.isEmpty() && !savedPassword.isEmpty());
    }

    private void clearSavedCredentials() {
        prefs.remove("username");
        prefs.remove("password");
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        Log_In login = new Log_In();
        login.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Log_In.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
     private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) throws UnsupportedLookAndFeelException {
        checkLogin();
    }
}
