import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainFrame extends JFrame {
    private JLabel jlbs[]=new JLabel[6];
    private JButton btnClose=new JButton("CLOSE");
    private JButton btnGenerate=new JButton("Generate");
    private JPanel jpn= new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1= new JPanel(new GridLayout(1,2,5,5));
    private int data[]=new int[6];
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
    public  MainFrame(){
        init();
    }
    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);

        CP=jif.getContentPane();
        CP.setLayout(new BorderLayout((5,5));
        CP.add(jpn,BorderLayout.CENTER);
        CP.add(jpn1,BorderLayout.SOUTH);
        jpn1.add(btnClose);
        jpn1.add(btnGenerate);

        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        jmF.add(jmFexit);
        jmG.add(jmLotto);
        jmFexit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmFexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setVisible(true);
                jif.setBounds(0,0,200,150);
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
