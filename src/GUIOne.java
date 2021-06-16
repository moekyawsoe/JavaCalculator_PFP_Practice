
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUIOne {
    private JPanel palMain;
    private JTextField txtInput;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton a8Button;
    private JButton a9Button;
    private JButton xButton;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button12;
    private JButton a2Button;
    private JButton a3Button;
    private JButton button16;
    private JButton btnEqual;
    private JButton button18;
    private JButton cButton;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton a0Button;
    private JLabel lblResult;

    private String log = "";
    private int in = 0;

    public GUIOne() {

        lblResult.setBorder(new EmptyBorder(0, 0, 0, 10));
        txtInput.setBorder(new EmptyBorder(0, 0, 0, 10));
        txtInput.setText(String.valueOf(in));

        //click event on key
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = e.getActionCommand();
                log += data;
                txtInput.setText(log);
            }
        };

        a8Button.addActionListener(listener);
        a1Button.addActionListener(listener);
        a9Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a7Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a0Button.addActionListener(listener);

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = e.getActionCommand();
                char c = action.charAt(0);
                transferExpression(c);
            }
        };
        button2.addActionListener(listener1);
        button3.addActionListener(listener1);
        xButton.addActionListener(listener1);
        button12.addActionListener(listener1);
        button16.addActionListener(listener1);

        cButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.setText("0");
                lblResult.setText("0");
                log = "";
            }
        });
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computeResult();
            }
        });

    }

    private void transferExpression(Character operation) {
        if (lblResult.getText().matches(".*[-+/*]")) {
            lblResult.setText(lblResult.getText() + " " + txtInput.getText() + " " + operation);
            log = "";
        } else {
            lblResult.setText(txtInput.getText() + " " + operation);
            log = "";
        }
    }

    private void computeResult() {
        transferExpression('=');
        txtInput.setText(String.valueOf(ExpressionEvaluator.evaluate(lblResult.getText())));
        System.out.println(lblResult.getText());
    }

    public static void main(String[] args) {

//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            e.printStackTrace();
//        }
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        JFrame frame = new JFrame("GUIOne");
        frame.setContentPane(new GUIOne().palMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("src\\assets\\img.png");
        frame.setIconImage(img.getImage());

        frame.setVisible(true);
    }

}
