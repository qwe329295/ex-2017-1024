

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private JLabel jlbs[]=new JLabel[6];
    private JButton btnClose=new JButton("CLOSE");
    private JButton btnGenerate=new JButton("Generate");
    private JPanel jpn= new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1= new JPanel(new GridLayout(1,2,5,5));
    private int data[]=new int[6];
    private JMenuItem jmiSetFont =new JMenuItem("Font");

    private JPanel jpanel1 = new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlbFamily =new JLabel("Family");
    private JLabel jlbStyle =new JLabel("Style");
    private JLabel jlbSize =new JLabel("Size");
    private JTextField jtfFamily =new JTextField("BOLD");
    private JTextField jtfStyle =new JTextField("font");
    private JTextField jtfSize =new JTextField("24");
    private String[] options={"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbFStyle=new JComboBox(options);

    private Random rnd= new Random(System.currentTimeMillis());
    private  JMenuBar jmb = new JMenuBar();
    private  JMenu jmF= new JMenu("File");
    private  JMenu jmS= new JMenu("Set");
    private  JMenu jmG= new JMenu("Game");
    private  JMenu jmA= new JMenu("About");
    private JMenuItem jmFexit=new JMenuItem("Exit");
    private JMenuItem jmLotto =new JMenuItem("lotto");
    private JDesktopPane jdp=new JDesktopPane();
    private  JInternalFrame jif= new JInternalFrame();
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH =Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW=500,frmH=400;
    private Login login;
    private Container CP;

    private  JInternalFrame jIFAddCategory =new JInternalFrame();
    private Container jIFAddCategoryCP;
    private JMenuBar jIFAddCategoryJmb= new JMenuBar();
    private JMenu jmData = new JMenu("data");
    private JMenuItem jIFAddCategoryjmiL=new JMenuItem("Load");
    private JMenuItem jIFAddCategoryjmiN=new JMenuItem("New");
    private JMenuItem jIFAddCategoryjmiC=new JMenuItem("Close");


    public  MainFrame(Login log){
        login=log;
        init();
    }
    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                login.reset();
                login.setVisible(true);
            }
        });

        CP=jif.getContentPane();
        CP.setLayout(new BorderLayout(5,5));
        CP.add(jpn,BorderLayout.CENTER);
        CP.add(jpn1,BorderLayout.SOUTH);

        jpn1.add(btnClose);
        jpn1.add(btnGenerate);

        jmS.add(jmiSetFont);

        jpanel1.add(jlbFamily);
        jpanel1.add(jlbStyle);
        jpanel1.add(jlbSize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFStyle);
        jpanel1.add(jtfSize);

        for(int i=0;i<6;i++){
            jlbs[i]=new JLabel();
            jlbs[i].setOpaque(true);
            jlbs[i].setBackground(new Color(255,255,0));
            jpn.add(jlbs[i]);
        }

        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmFexit);
        jmG.add(jmLotto);
        jmFexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmLotto.setAccelerator(KeyStroke.getKeyStroke('L',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.dispose();
            }
        });
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();
            }
        });
        jmFexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.setVisible(true);
                dispose();
                login.reset();
            }
        });

        jmLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setBounds(20,20,300,100);
                jif.setVisible(true);
                lottoGenerate();
            }
        });


        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result =JOptionPane.showConfirmDialog(MainFrame.this,jpanel1,"Font Setting",JOptionPane.OK_CANCEL_OPTION);
                int fontStyle =0;
                switch (jcbFStyle.getSelectedIndex()){
                    case 0:
                        fontStyle= Font.PLAIN;
                        break;
                    case 1:
                        fontStyle=Font.BOLD;
                        break;
                    case 2:
                        fontStyle=Font.ITALIC;
                        break;
                    case 3:
                        fontStyle=Font.BOLD+Font.ITALIC;
                        break;
                }
                if(result == JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                }
            }
        });

    }

    private void  lottoGenerate(){
        int i=0;
        while(i<6){
            data[i]=rnd.nextInt(42)+1;
            int j =0;
            boolean flag =true;
            while(j<i && flag){
                if(data[i]==data[j]){
                    flag=false;
                }
                j++;
            }
            if(flag){
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
    }
