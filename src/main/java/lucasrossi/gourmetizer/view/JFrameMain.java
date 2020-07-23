package lucasrossi.gourmetizer.view;

import java.awt.GraphicsConfiguration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Classe MAIN
 */
public class JFrameMain {

    private static GraphicsConfiguration graphicsConf;
    private static ButtonActionListener buttonActionListener = new ButtonActionListener();

    public static void main(String[] args) {

        /**
         * Configurações do Frame
         */
        JFrame frame = new JFrame(graphicsConf);
        frame.setTitle("JOGO GOURMET !");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel fraseInicial = new JLabel("Pense em um prato que você gosta", SwingConstants.CENTER);
        fraseInicial.setBounds(30, 10, 250, 15);
        frame.add(fraseInicial);

        JButton buttonOk = new JButton("Já pensei!");
        buttonOk.setBounds(75, 45, 150, 30);
        buttonOk.addActionListener(buttonActionListener);
        frame.add(buttonOk);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}