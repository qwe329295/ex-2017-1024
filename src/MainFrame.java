

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Random;

public class MainFrame extends JFrame {
    //    ----------------樂透-------------------
    private JLabel jlbs[] = new JLabel[6];
    private JButton btnClose = new JButton("CLOSE");
    private JButton btnGenerate = new JButton("Generate");
    private JPanel jpn = new JPanel(new GridLayout(1, 6, 5, 5));
    private JPanel jpn1 = new JPanel(new GridLayout(1, 2, 5, 5));
    private int data[] = new int[6];
    private JMenuItem jmiSetFont = new JMenuItem("Font");
    //-----------------------改字體-------------
    private JPanel jpanel1 = new JPanel(new GridLayout(2, 3, 5, 5));
    private JLabel jlbFamily = new JLabel("Family");
    private JLabel jlbStyle = new JLabel("Style");
    private JLabel jlbSize = new JLabel("Size");
    private JTextField jtfFamily = new JTextField("BOLD");
    //    private JTextField jtfStyle =new JTextField("font");
    private JTextField jtfSize = new JTextField("24");
    private String[] options = {"PLAIN", "BOLD", "ITALIC", "BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);

    private Random rnd = new Random(System.currentTimeMillis());
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmS = new JMenu("Set");
    private JMenu jmG = new JMenu("Game");
    private JMenu jmA = new JMenu("About");
    private JMenuItem jmFexit = new JMenuItem("Exit");
    private JMenuItem jmLotto = new JMenuItem("lotto");
    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jif = new JInternalFrame();
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 700, frmH = 600;
    private Login login;
    private Container CP;
    //    ----------------亂數鍵盤---------
    private JMenuItem jmrandom = new JMenuItem("亂數鍵盤");
    private JInternalFrame jifrandom = new JInternalFrame();
    private JTextField jtf1 = new JTextField();
    private JButton btn[] = new JButton[12];
    private Random rnd1 = new Random();

    private JPanel jpn3 = new JPanel(new GridLayout(4, 3, 5, 5));
    private Container CP2;


    private JInternalFrame jIFAddCategory = new JInternalFrame();
    private JFileChooser jfc = new JFileChooser();
    private Container jIFAddCategoryCP;
    private JMenuBar jIFAddCategoryJmb = new JMenuBar();
    private JMenuItem jmData = new JMenuItem("data");
    private JMenu jmfile = new JMenu("file");
    private JTextArea jta = new JTextArea();
    private JMenuItem jIFAddCategoryjmiL = new JMenuItem("Load");
    private JMenuItem jIFAddCategoryjmiN = new JMenuItem("New");
    private JMenuItem jIFAddCategoryjmiC = new JMenuItem("Close");
    private JScrollPane jsp = new JScrollPane(jta);
    private String name[] = {"書名", "作者", "出版商", "價格", "類別"};


    public MainFrame(Login log) {
        login = log;
        init();
    }

    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        CP = jif.getContentPane();
        CP.setLayout(new BorderLayout(5, 5));
        CP.add(jpn, BorderLayout.CENTER);
        CP.add(jpn1, BorderLayout.SOUTH);

        jpn1.add(btnClose);
        jpn1.add(btnGenerate);

        jmS.add(jmiSetFont);

        jpanel1.add(jlbFamily);
        jpanel1.add(jlbStyle);
        jpanel1.add(jlbSize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFStyle);
        jpanel1.add(jtfSize);

        for (int i = 0; i < 6; i++) {
            jlbs[i] = new JLabel();
            jlbs[i].setOpaque(true);
            jlbs[i].setBackground(new Color(255, 255, 0));
            jpn.add(jlbs[i]);
        }

        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmFexit);
        jmF.add(jmData);

        jmData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jIFAddCategory);
                jIFAddCategory.setBounds(10, 10, 400, 400);
                jIFAddCategory.setVisible(true);
            }
        });
        jIFAddCategory.setJMenuBar(jIFAddCategoryJmb);
        jIFAddCategoryJmb.add(jmfile);
        jmfile.add(jIFAddCategoryjmiL);
        jmfile.add(jIFAddCategoryjmiC);
        jmfile.add(jIFAddCategoryjmiN);
        jta.setEditable(false);
        jsp.setPreferredSize(new Dimension(200, 200));
        jIFAddCategoryCP = jIFAddCategory.getContentPane();
        jIFAddCategoryCP.add(jsp);

        jIFAddCategoryjmiL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText("");
                try {
                    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File inFile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(inFile));
                        String str = "";
                        int count = 0;
                        while ((str = br.readLine()) != null) {
                            count++;
                        }
                        br = new BufferedReader(new FileReader(inFile));
                        String[][] G = new String[count][5];
                        int sum = 0;
                        while ((str = br.readLine()) != null) {
                            String[] SS = str.split(", ");
                            boolean Boo = true;
                            while (sum < count && Boo) {
                                for (int i = 0; i < 5; i++) {
                                    G[sum][i] = SS[i];
                                }
                                Boo = false;
                                if (Boo == false) {
                                    sum++;
                                    break;
                                }
                            }
                        }
                        int j = 0;
                        while (j < 5) {
                            sum = 0;
                            jta.append(name[j] + "\t");
                            while (sum < count) {
                                jta.append(G[sum][j] + "\t");
                                if (j != 0) {
                                    jta.append("\t     ");
                                }
                                sum++;
                            }
                            jta.append("\n");
                            j++;
                        }
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        jIFAddCategoryjmiN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File inFile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(inFile));
                        String str = "";
                        while ((str = br.readLine()) != null) {
                            jta.append(str + "\n");
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        jIFAddCategoryjmiC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jIFAddCategory.dispose();
            }
        });


        jmG.add(jmLotto);
        jmG.add(jmrandom);

        jmFexit.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmLotto.setAccelerator(KeyStroke.getKeyStroke('L', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmrandom.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

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
                jif.setBounds(20, 20, 300, 100);
                jif.setVisible(true);
                lottoGenerate();
            }
        });


        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this, jpanel1, "Font Setting", JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbFStyle.getSelectedIndex()) {
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if (result == JOptionPane.OK_OPTION) {
                    UIManager.put("Menu.font", new Font(jtfFamily.getText(), fontStyle, Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font", new Font(jtfFamily.getText(), fontStyle, Integer.parseInt(jtfSize.getText())));
                }
            }
        });
//        ----------亂數鍵盤-------------

        CP2 = jifrandom.getContentPane();
        CP2.setLayout(new BorderLayout(5, 5));
        CP2.add(jpn3, BorderLayout.CENTER);
        CP2.add(jtf1, BorderLayout.NORTH);
        jtf1.setEditable(false);

        jmrandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jifrandom);
                jifrandom.setBounds(20, 20, 300, 300);
                jifrandom.setVisible(true);
                for (int i = 0; i < 10; i++) {
                    btn[i].setText(String.valueOf(rnd1.nextInt(10)));
                    boolean repeat = false;
                    for (int j = 0; j < i; j++) {
                        if (btn[j].getText().equals(btn[i].getText())) {
                            repeat = true;
                            break;
                        }
                    }
                    if (repeat == true) {
                        i--;
                    }
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            btn[i] = new JButton();
            jpn3.add(btn[i]);
            btn[i].addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tmpButton = (JButton) e.getSource();
                    jtf1.setText(jtf1.getText() + tmpButton.getText());
                }
            });
        }


        btn[10] = new JButton("Close");
        btn[11] = new JButton("CLEAR");
        jpn3.add(btn[10]);
        jpn3.add(btn[11]);
        btn[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                        jtf1.setText(jtf1.getText()+".");
                jifrandom.dispose();
            }
        });

        btn[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf1.setText(" ");
            }
        });

    }

    private void lottoGenerate() {
        int i = 0;
        while (i < 6) {
            data[i] = rnd.nextInt(42) + 1;
            int j = 0;
            boolean flag = true;
            while (j < i && flag) {
                if (data[i] == data[j]) {
                    flag = false;
                }
                j++;
            }
            if (flag) {
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}
