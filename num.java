import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.Clip;

public class num extends JFrame implements ActionListener {
    //numPanel
    private JLabel apple1, apple2, apple3, apple4, apple5, apple6, apple7, apple8, apple9, apple10, 
           apple11, apple12, apple13, apple14, apple15, apple16,apple17,apple18, apple19, apple20,
           num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14,
           num15, num16, num17, num18, num19, num20;
    private JLabel tree, tree1, tree2, birds, bird1, bird2, bird3, bird4, bird5, bird6, bird7, cats, cat1, 
            cat2, cat3, cat4, two, seven, four;
    private JLabel quizbg, num_q1, num_q2, num_q3;
    private JButton nbtn1, nbtn2, nbtn3, nbtn4;
    //comPanel
    private JLabel com_bg, com_bg1, com1, less, same, more, small, big, eq;
    private JLabel com2, ex1, ex2, ex3, expr1, expr2, expr3, com_quizbg, q1, q2, q3,q1_ans,q2_ans; 
    private JButton btn1, btn2, btn3;
    
    private JPanel mainPanel = new JPanel();
    private JPanel numPanel1 = new JPanel();;
    private JPanel numPanel2 = new JPanel();;
    private JPanel numPanel3 = new JPanel(); ;
    private JPanel comPanel1 = new JPanel();;
    private JPanel comPanel2 = new JPanel();;
    private JPanel comPanel3 = new JPanel();;
    private JPanel comQuiz = new JPanel();;
    private JPanel lastPanel = new JPanel();;
    private JButton startB ;
    private JLabel next,repeat,music,mute; 
    private JButton btn_next, btn_repeat,btn_mute;
    //music 
    playAudio s = new playAudio();
    Clip bgm = s.playMusic("bgm1.wav");
    Clip clipCom1 = s.playMusic("compare1.wav");
    Clip correct = s.playMusic("correct_sound.wav");
    Clip wrong = s.playMusic("wrong_sound.wav");
    
    private boolean music_on;
    private int seconds = 0; 
    private int correct_ans = 0;
    private Timer timer = new Timer(1000,new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            if(ae.getActionCommand() == "numPanel1_timer"){
                numPanel1_timer();
            } else if (ae.getActionCommand() == "numPanel2_timer"){
                numPanel2_timer();
            } else if (ae.getActionCommand() == "numPanel3_timer"){
                numPanel3_timer();
            } else if (ae.getActionCommand() == "comPanel1_timer"){
                comPanel1_timer();
            }else if (ae.getActionCommand() == "comPanel2_timer"){
                comPanel2_timer();
            } else if (ae.getActionCommand() == "comPanel3_timer"){
                comPanel3_timer();
            } else if (ae.getActionCommand() == "comQuiz_timer"){
                comQuiz_timer();
            }
        }
    }); 

    public num(){
        //frame 
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);  
        setTitle("Numbers Learning");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(numPanel1,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(numPanel2,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(numPanel3,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(comPanel1,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(comPanel2,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(comPanel3,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(comQuiz,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
            .addComponent(lastPanel,GroupLayout.DEFAULT_SIZE,700,Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(numPanel1,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(numPanel2,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(numPanel3,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(comPanel1,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(comPanel2,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(comPanel3,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(comQuiz,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
            .addComponent(lastPanel,GroupLayout.DEFAULT_SIZE,500,Short.MAX_VALUE)
        ); 
        pack();
        bgm.start();
        bgm.loop(bgm.LOOP_CONTINUOUSLY);
        
        //btn_next
        next = new JLabel(formatImage("/next.png", 65, 65)); 
        next.setBounds(600, 105, 65, 65);
        next.setVisible(false);
        btn_next = new JButton();
        btn_next.setContentAreaFilled(false);
        btn_next.setBorderPainted(false);
        btn_next.setBounds(600, 105, 65, 65);
        btn_next.setVisible(false);
        btn_next.addActionListener(this);

        repeat = new JLabel(formatImage("/repeat.png", 65, 65));
        repeat.setBounds(600, 185, 65, 65);
        repeat.setVisible(false);
        btn_repeat = new JButton();
        btn_repeat.setBorderPainted(false);
        btn_repeat.setContentAreaFilled(false);
        btn_repeat.setBounds(600, 185, 65, 65);
        btn_repeat.setVisible(false);
        btn_repeat.addActionListener(this);
        //btn_mute
        mute = new JLabel(formatImage("/mute.png", 65, 65));
        mute.setBounds(600, 25, 65, 65);
        mute.setVisible(false); //at first is playing music, so let invisible first

        //when playing music, use this
        music = new JLabel(formatImage("/music.png", 65, 65));
        music.setBounds(600, 25, 65, 65);

        btn_mute = new JButton();
        btn_mute.setContentAreaFilled(false);
        btn_mute.setBorderPainted(false);
        btn_mute.setBounds(600, 25, 65, 65);
        music_on = true;
        btn_mute.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		if(music_on == true){
			mute.setVisible(true);
			bgm.stop();
			music_on = false;
		} else if(music_on == false){
			mute.setVisible(false);
			bgm.start();
            bgm.loop(bgm.LOOP_CONTINUOUSLY);
			music_on = true;
		}
	}
    });
    }
    
    public void mainPanel(){
        mainPanel.setLayout(null);
 
        //start button 
        JLabel start = new JLabel();
        start.setIcon(new ImageIcon(getClass().getResource("/number_image/start.gif")));
        mainPanel.add(start);
        start.setBounds(270, 270, 160, 90);

        startB = new JButton();
        startB.setBorderPainted( false );
        startB.setContentAreaFilled(false);
        startB.setBounds(270, 270, 160, 90);
        mainPanel.add(startB);
        startB.addActionListener(this);

        // background image
        JLabel backImg = new JLabel();
        backImg.setIcon(new ImageIcon(getClass().getResource("/number_image/bg.png")));
        mainPanel.add(backImg); 
        backImg.setBounds(0, 0, 700, 500);   
    }
    public void numPanel1_timer(){   
    seconds++ ;
        switch (seconds) {
            case 1:
                apple1.setVisible(true);
                num1.setVisible(true);
                break;
            case 2:
                apple2.setVisible(true);
                num1.setVisible(false);
                num2.setVisible(true);
                break;
            case 3:
                apple3.setVisible(true);
                num2.setVisible(false);
                num3.setVisible(true);
                break;
            case 4:
                apple4.setVisible(true);
                num3.setVisible(false);
                num4.setVisible(true);
                break;
            case 5:
                apple5.setVisible(true);
                num4.setVisible(false);
                num5.setVisible(true);
                break;
            case 6:
                apple6.setVisible(true);
                num5.setVisible(false);
                num6.setVisible(true);
                break;
            case 7:
                apple7.setVisible(true);
                num6.setVisible(false);
                num7.setVisible(true);
                break;
            case 8:
                apple8.setVisible(true);
                num7.setVisible(false);
                num8.setVisible(true);
                break;
            case 9:
                apple9.setVisible(true);
                num8.setVisible(false);
                num9.setVisible(true);
                break;
            case 10:
                apple10.setVisible(true);
                num9.setVisible(false);
                num10.setVisible(true);
                break;
            case 11:
                apple11.setVisible(true);
                num10.setVisible(false);
                num11.setVisible(true);
                break;
            case 12:
                apple12.setVisible(true);
                num11.setVisible(false);
                num12.setVisible(true);
                break;
            case 13:
                apple13.setVisible(true);
                num12.setVisible(false);
                num13.setVisible(true);
                break;
            case 14:
                apple14.setVisible(true);
                num13.setVisible(false);
                num14.setVisible(true);
                break;
            case 16:
                apple15.setVisible(true);
                num14.setVisible(false);
                num15.setVisible(true);
                break;
            case 17:
                apple16.setVisible(true);
                num15.setVisible(false);
                num16.setVisible(true);
                break;
            case 18:
                apple17.setVisible(true);
                num16.setVisible(false);
                num17.setVisible(true);
                break;
            case 19:
                apple18.setVisible(true);
                num17.setVisible(false);
                num18.setVisible(true);
                break;
            case 21:
                apple19.setVisible(true);
                num18.setVisible(false);
                num19.setVisible(true);     
                break;
            case 22:
                apple20.setVisible(true); 
                num19.setVisible(false);
                num20.setVisible(true);
                break;
            case 23: 
                next.setVisible(true);
                btn_next.setVisible(true);
                repeat.setVisible(true);
                btn_repeat.setVisible(true);
                timer.stop();
                break;
            default: 
                break;
        }
    } 
    
    public void numPanel1(){
        numPanel1.setLayout(null);
        //buttons
        numPanel1.add(next);
        next.setVisible(false);
        btn_next.setActionCommand("next2");
        btn_next.setVisible(false);
        numPanel1.add(btn_next);
       
        numPanel1.add(repeat);
        repeat.setVisible(false);
        numPanel1.add(btn_repeat);
        btn_repeat.setVisible(false);
        btn_repeat.setActionCommand("numpanel1_repeat");
        
        numPanel1.add(mute);
        numPanel1.add(music);
        numPanel1.add(btn_mute); 
        
        //number images
        num1 = new JLabel();
        num1.setIcon(new ImageIcon(getClass().getResource("/number_image/1.png")));
        numPanel1.add(num1);
        num1.setBounds(220, 330, 260, 70);
        num1.setVisible(false);
        
        num2 = new JLabel();
        num2.setIcon(new ImageIcon(getClass().getResource("/number_image/2.png")));
        numPanel1.add(num2);
        num2.setBounds(220, 330, 260, 70);
        num2.setVisible(false);
        
        num3 = new JLabel();
        num3.setIcon(new ImageIcon(getClass().getResource("/number_image/3.png")));
        numPanel1.add(num3);
        num3.setBounds(220, 330, 260, 70);
        num3.setVisible(false);
        
        num4 = new JLabel();
        num4.setIcon(new ImageIcon(getClass().getResource("/number_image/4.png")));
        numPanel1.add(num4);
        num4.setBounds(220, 330, 260, 70);
        num4.setVisible(false);

        num5 = new JLabel();
        num5.setIcon(new ImageIcon(getClass().getResource("/number_image/5.png")));
        numPanel1.add(num5);
        num5.setBounds(220, 330, 260, 70);
        num5.setVisible(false);
        
        num6 = new JLabel();
        num6.setIcon(new ImageIcon(getClass().getResource("/number_image/6.png")));
        numPanel1.add(num6);
        num6.setBounds(220, 330, 260, 70);
        num6.setVisible(false);
        
        num7 = new JLabel();
        num7.setIcon(new ImageIcon(getClass().getResource("/number_image/7.png")));
        numPanel1.add(num7);
        num7.setBounds(220, 330, 260, 70);
        num7.setVisible(false);
        
        num8 = new JLabel();
        num8.setIcon(new ImageIcon(getClass().getResource("/number_image/8.png")));
        numPanel1.add(num8);
        num8.setBounds(220, 330, 260, 70);
        num8.setVisible(false);
        
        num9 = new JLabel();
        num9.setIcon(new ImageIcon(getClass().getResource("/number_image/9.png")));
        numPanel1.add(num9);
        num9.setBounds(220, 330, 260, 70);
        num9.setVisible(false);
        
        num10 = new JLabel();
        num10.setIcon(new ImageIcon(getClass().getResource("/number_image/10.png")));
        numPanel1.add(num10);
        num10.setBounds(220, 330, 260, 70);
        num10.setVisible(false);
        
        num11 = new JLabel();
        num11.setIcon(new ImageIcon(getClass().getResource("/number_image/11.png")));
        numPanel1.add(num11);
        num11.setBounds(220, 330, 260, 70);
        num11.setVisible(false);
        
        num12 = new JLabel();
        num12.setIcon(new ImageIcon(getClass().getResource("/number_image/12.png")));
        numPanel1.add(num12);
        num12.setBounds(220, 330, 260, 70);
        num12.setVisible(false);
        
        num13 = new JLabel();
        num13.setIcon(new ImageIcon(getClass().getResource("/number_image/13.png")));
        numPanel1.add(num13);
        num13.setBounds(220, 330, 260, 70);
        num13.setVisible(false);
        
        num14 = new JLabel();
        num14.setIcon(new ImageIcon(getClass().getResource("/number_image/14.png")));
        numPanel1.add(num14);
        num14.setBounds(190, 330, 300, 70);
        num14.setVisible(false);
        
        num15 = new JLabel();
        num15.setIcon(new ImageIcon(getClass().getResource("/number_image/15.png")));
        numPanel1.add(num15);
        num15.setBounds(220, 330, 300, 70);
        num15.setVisible(false);
        
        num16 = new JLabel();
        num16.setIcon(new ImageIcon(getClass().getResource("/number_image/16.png")));
        numPanel1.add(num16);
        num16.setBounds(190, 330, 300, 70);
        num16.setVisible(false);       
        
        num17 = new JLabel();
        num17.setIcon(new ImageIcon(getClass().getResource("/number_image/17.png")));
        numPanel1.add(num17);
        num17.setBounds(190, 330, 300, 70);
        num17.setVisible(false); 
        
        num18 = new JLabel();
        num18.setIcon(new ImageIcon(getClass().getResource("/number_image/18.png")));
        numPanel1.add(num18);
        num18.setBounds(190, 330, 300, 70);
        num18.setVisible(false); 
        
        num19 = new JLabel();
        num19.setIcon(new ImageIcon(getClass().getResource("/number_image/19.png")));
        numPanel1.add(num19);
        num19.setBounds(190, 330, 300, 70);
        num19.setVisible(false); 
        
        num20 = new JLabel();
        num20.setIcon(new ImageIcon(getClass().getResource("/number_image/20.png")));
        numPanel1.add(num20);
        num20.setBounds(190, 330, 300, 70);
        num20.setVisible(false); 
        
        //sound clip
        Clip clipNum1 = s.playMusic("1-20.wav");
        clipNum1.start();
        
        //apples
        apple1 = new JLabel ();
        apple1.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple1);
        apple1.setBounds(30, 100, 80, 80);
        apple1.setVisible(false);
        
        apple2 = new JLabel();
        apple2.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple2);
        apple2.setBounds(90, 100, 80, 80);
        apple2.setVisible(false);
        
        apple3 = new JLabel ();
        apple3.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple3);
        apple3.setBounds(150, 100, 80, 80);
        apple3.setVisible(false);

        apple4 = new JLabel ();
        apple4.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple4);
        apple4.setBounds(210, 100, 80, 80);
        apple4.setVisible(false);
        
        apple5 = new JLabel ();
        apple5.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple5);
        apple5.setBounds(270, 100, 80, 80);
        apple5.setVisible(false);
        
        apple6 = new JLabel ();
        apple6.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple6);
        apple6.setBounds(330, 100, 80, 80);
        apple6.setVisible(false);
        
        apple7 = new JLabel ();
        apple7.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple7);
        apple7.setBounds(390, 100, 80, 80);
        apple7.setVisible(false);
        
        apple8 = new JLabel ();
        apple8.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple8);
        apple8.setBounds(30, 160, 80, 80);
        apple8.setVisible(false);
        
        apple9 = new JLabel ();
        apple9.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple9);
        apple9.setBounds(90, 160, 80, 80);
        apple9.setVisible(false);
        
        apple10 = new JLabel ();
        apple10.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple10);
        apple10.setBounds(150, 160, 80, 80);
        apple10.setVisible(false);
        
        apple11 = new JLabel ();
        apple11.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple11);
        apple11.setBounds(210, 160, 80, 80);
        apple11.setVisible(false); 
        
        apple12 = new JLabel ();
        apple12.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple12);
        apple12.setBounds(270, 160, 80, 80);
        apple12.setVisible(false); 
        
        apple13 = new JLabel ();
        apple13.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple13);
        apple13.setBounds(330, 160, 80, 80);
        apple13.setVisible(false); 
        
        apple14 = new JLabel ();
        apple14.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple14);
        apple14.setBounds(390, 160, 80, 80);
        apple14.setVisible(false); 
        
        apple15 = new JLabel ();
        apple15.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple15);
        apple15.setBounds(30, 220, 80, 80);
        apple15.setVisible(false); 
        
        apple16 = new JLabel ();
        apple16.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple16);
        apple16.setBounds(90, 220, 80, 80);
        apple16.setVisible(false); 
        
        apple17 = new JLabel ();
        apple17.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple17);
        apple17.setBounds(150, 220, 80, 80);
        apple17.setVisible(false); 
        
        apple18 = new JLabel ();
        apple18.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple18);
        apple18.setBounds(210, 220, 80, 80);
        apple18.setVisible(false); 
        
        apple19 = new JLabel ();
        apple19.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple19);
        apple19.setBounds(270, 220, 80, 80);            
        apple19.setVisible(false); 
        
        apple20 = new JLabel ();
        apple20.setIcon(new ImageIcon(getClass().getResource("/number_image/apple.png")));
        numPanel1.add(apple20);
        apple20.setBounds(330, 220, 80, 80);
        apple20.setVisible(false); 

        //background image
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(getClass().getResource("/number_image/panel1.jpg")));
        numPanel1.add(bg);
        bg.setBounds(0, 0, 700, 500);
    }    

    public void numPanel2_timer(){
        seconds++;
        switch(seconds){
            case 1 : 
                tree.setVisible(true);
                break;
            case 10: 
                two.setVisible(true);
                break;
            case 11: 
                tree1.setVisible(true);
                break;
            case 12:
                tree2.setVisible(true);
                break;
            case 15: 
                two.setVisible(false);
                tree.setVisible(false);
                tree1.setVisible(false);
                tree2.setVisible(false);
                birds.setVisible(true);
                break;
            case 22:
                seven.setVisible(true);
                break;
            case 23: 
                bird1.setVisible(true);
                break;
            case 24: 
                bird2.setVisible(true);
                break;
            case 25: 
                bird3.setVisible(true);
                break;
            case 27: 
                bird4.setVisible(true);
                break;
            case 28: 
                bird5.setVisible(true);
                break;
            case 29: 
                bird6.setVisible(true);
                break;
            case 30: 
                bird7.setVisible(true);
                break;
            case 32: 
                seven.setVisible(false);
                birds.setVisible(false);
                bird1.setVisible(false);
                bird2.setVisible(false);
                bird3.setVisible(false);
                bird4.setVisible(false);
                bird5.setVisible(false);
                bird6.setVisible(false);
                bird7.setVisible(false);
                cats.setVisible(true);
                break;
            case 40: 
                four.setVisible(true);
                break;
            case 42: 
                cat1.setVisible(true);
                break;
            case 43: 
                cat2.setVisible(true);
                break;
            case 44: 
                cat3.setVisible(true);
                break;
            case 45: 
                cat4.setVisible(true);
                break;
            case 46: 
                next.setVisible(true);
                btn_next.setVisible(true);
                repeat.setVisible(true);
                btn_repeat.setVisible(true);
                break;
            case 47: 
                timer.stop();
                break;
            default: 
                break;
        }
        
    }
    
    public void numPanel2(){
        numPanel2.setLayout(null);

        tree1 = new JLabel();
        tree1.setIcon(new ImageIcon(getClass().getResource("/number_image/tree.png")));
        numPanel2.add(tree1);
        tree1.setBounds(260, 110, 110, 110);
        tree1.setVisible(false);
        
        tree2 = new JLabel();
        tree2.setIcon(new ImageIcon(getClass().getResource("/number_image/tree.png")));
        numPanel2.add(tree2);
        tree2.setBounds(350, 110, 110, 110);
        tree2.setVisible(false);
        
        tree = new JLabel();
        tree.setIcon(new ImageIcon(getClass().getResource("/number_image/trees.png")));
        numPanel2.add(tree);
        tree.setBounds(260, 110, 196, 112);
        tree.setVisible(false);
        
        bird1 = new JLabel();
        bird1.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird1);
        bird1.setBounds(210, 130, 110, 67);
        bird1.setVisible(false);
        
        bird2 = new JLabel();
        bird2.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird2);
        bird2.setBounds(270, 90, 110, 67);
        bird2.setVisible(false);
        
        bird3 = new JLabel();
        bird3.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird3);
        bird3.setBounds(360, 90, 110, 67);
        bird3.setVisible(false);
        
        bird4 = new JLabel();
        bird4.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird4);
        bird4.setBounds(300, 140, 110, 67);
        bird4.setVisible(false);
        
        bird5 = new JLabel();
        bird5.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird5);
        bird5.setBounds(400, 140, 110, 67);
        bird5.setVisible(false);
        
        bird6 = new JLabel();
        bird6.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird6);
        bird6.setBounds(250, 180, 110, 67);
        bird6.setVisible(false);
        
        bird7 = new JLabel();
        bird7.setIcon(new ImageIcon(getClass().getResource("/number_image/bird.png")));
        numPanel2.add(bird7);
        bird7.setBounds(340, 180, 110, 67);
        bird7.setVisible(false);
                
        birds = new JLabel();
        birds.setIcon(new ImageIcon(getClass().getResource("/number_image/birds.png")));
        numPanel2.add(birds);
        birds.setBounds(210, 90, 300, 160);
        birds.setVisible(false);
        
        cat3 = new JLabel();
        cat3.setIcon(new ImageIcon(getClass().getResource("/number_image/cat.png")));
        numPanel2.add(cat3);
        cat3.setBounds(270, 175, 110, 100);
        cat3.setVisible(false);
        
        cat4 = new JLabel();
        cat4.setIcon(new ImageIcon(getClass().getResource("/number_image/cat.png")));
        numPanel2.add(cat4);
        cat4.setBounds(340, 175, 110, 100);
        cat4.setVisible(false);
        
        cat1 = new JLabel();
        cat1.setIcon(new ImageIcon(getClass().getResource("/number_image/cat.png")));
        numPanel2.add(cat1);
        cat1.setBounds(220, 85, 110, 100);
        cat1.setVisible(false);
        
        cat2 = new JLabel();
        cat2.setIcon(new ImageIcon(getClass().getResource("/number_image/cat.png")));
        numPanel2.add(cat2);
        cat2.setBounds(290, 85, 110, 100);
        cat2.setVisible(false);
        
        cats = new JLabel();
        cats.setIcon(new ImageIcon(getClass().getResource("/number_image/cats.png")));
        numPanel2.add(cats);
        cats.setBounds(230, 90, 210, 180);
        cats.setVisible(false);
        
        two = new JLabel();
        two.setIcon(new ImageIcon(getClass().getResource("/number_image/two.png")));
        numPanel2.add(two);
        two.setBounds(300, 300, 100, 90);
        two.setVisible(false);
        
        seven = new JLabel();
        seven.setIcon(new ImageIcon(getClass().getResource("/number_image/seven.png")));
        numPanel2.add(seven);
        seven.setBounds(300, 300, 100, 90);
        seven.setVisible(false);
        
        four = new JLabel();
        four.setIcon(new ImageIcon(getClass().getResource("/number_image/four.png")));
        numPanel2.add(four);
        four.setBounds(300, 300, 100, 90);
        four.setVisible(false);

        //buttons
        numPanel2.add(next);
        next.setVisible(false);
        btn_next.setActionCommand("next3");
        btn_next.setVisible(false);
        numPanel2.add(btn_next);
        
        numPanel2.add(repeat);
        repeat.setVisible(false);
        numPanel2.add(btn_repeat);
        btn_repeat.setVisible(false);
        btn_repeat.setActionCommand("numpanel2_repeat");

        numPanel2.add(mute);
        numPanel2.add(music);
        numPanel2.add(btn_mute);

       Clip clipNum2 = s.playMusic("ask_num.wav");
       clipNum2.start();
        
       JLabel bg = new JLabel();
       bg.setIcon(new ImageIcon(getClass().getResource("/number_image/panel2.jpg")));
       numPanel2.add(bg);
       bg.setBounds(0,0,700,500);
        
    }
    public void numPanel3_timer(){
        seconds++;
        switch(seconds){
            case 1: 
                quizbg.setVisible(true);
                break;
            case 4: 
                quizbg.setVisible(false);
                num_q1.setVisible(true);
                nbtn1.setVisible(true);
                nbtn1.setActionCommand("wrong1");
                nbtn1.addActionListener(this);
                nbtn2.setVisible(true);
                nbtn2.setActionCommand("wrong1");
                nbtn2.addActionListener(this);
                nbtn3.setVisible(true);
                nbtn3.setActionCommand("wrong1");
                nbtn3.addActionListener(this);
                nbtn4.setVisible(true);
                nbtn4.setActionCommand("correct1");
                nbtn4.addActionListener(this);
                timer.stop();
                break;
            case 7 :
                q1_ans.setVisible(false);
                num_q1.setVisible(false);
                num_q2.setVisible(true);
                nbtn1.setVisible(true);
                nbtn1.setActionCommand("wrong2");
                nbtn2.setVisible(true);
                nbtn2.setActionCommand("wrong2");
                nbtn3.setVisible(true);
                nbtn3.setActionCommand("correct2");
                nbtn4.setVisible(true);
                nbtn4.setActionCommand("wrong2");
                timer.stop();
                break;
            case 10: 
                q2_ans.setVisible(false);
                num_q2.setVisible(false);
                num_q3.setVisible(true);
                nbtn1.setVisible(true);
                nbtn1.setActionCommand("wrong2");
                nbtn2.setVisible(true);
                nbtn2.setActionCommand("wrong2");
                nbtn3.setVisible(true);
                nbtn3.setActionCommand("correct2");
                nbtn4.setVisible(true);
                nbtn4.setActionCommand("wrong2");
                timer.stop();
                break;
            case 13 : 
                numPanel3.setVisible(false);
                comPanel1();
                seconds = 0;
                timer.setActionCommand("comPanel1_timer");
                timer.start();
            default: 
                break;
        }
        
    }
    public void numPanel3(){

        numPanel3.setLayout(null);
        
        //button
        numPanel3.add(mute);
        numPanel3.add(music);
        numPanel3.add(btn_mute);
        
        nbtn1 = new JButton();
        nbtn1.setContentAreaFilled(false);
        nbtn1.setBorderPainted(false);
        nbtn1.setBounds(80, 360, 120, 60);
        numPanel3.add(nbtn1);
        nbtn1.setVisible(false);
        
        nbtn2 = new JButton();
        nbtn2.setContentAreaFilled(false);
        nbtn2.setBorderPainted(false);
        nbtn2.setBounds(220, 360, 120, 60);
        numPanel3.add(nbtn2);
        nbtn2.setVisible(false);
        
        nbtn3 = new JButton();
        nbtn3.setContentAreaFilled(false);
        nbtn3.setBorderPainted(false);
        nbtn3.setBounds(370, 360, 120, 60);
        numPanel3.add(nbtn3);
        nbtn3.setVisible(false);
        
        nbtn4 = new JButton();
        nbtn4.setContentAreaFilled(false);
        nbtn4.setBorderPainted(false);
        nbtn4.setBounds(520, 360, 120, 60);
        numPanel3.add(nbtn4);
        nbtn4.setVisible(false);

        q1_ans = new JLabel();
        q1_ans.setIcon(new ImageIcon(getClass().getResource("/number_image/numq1_ans.png")));
        numPanel3.add(q1_ans);
        q1_ans.setBounds(0,0,700,500);
        q1_ans.setVisible(false);
        
        q2_ans = new JLabel();
        q2_ans.setIcon(new ImageIcon(getClass().getResource("/number_image/numq2_ans.png")));
        numPanel3.add(q2_ans);
        q2_ans.setBounds(0,0,700,500);
        q2_ans.setVisible(false);
        
        num_q3 = new JLabel();
        num_q3.setIcon(new ImageIcon(getClass().getResource("/number_image/num_q3.png")));
        numPanel3.add(num_q3);
        num_q3.setBounds(0,0,700,500);
        num_q3.setVisible(false);
        
        num_q2 = new JLabel();
        num_q2.setIcon(new ImageIcon(getClass().getResource("/number_image/num_q2.png")));
        numPanel3.add(num_q2);
        num_q2.setBounds(0,0,700,500);
        num_q2.setVisible(false);
        
        num_q1 = new JLabel();
        num_q1.setIcon(new ImageIcon(getClass().getResource("/number_image/num_q1.png")));
        numPanel3.add(num_q1);
        num_q1.setBounds(0,0,700,500);
        num_q1.setVisible(false);
        
        quizbg = new JLabel();
        quizbg.setIcon(new ImageIcon(getClass().getResource("/number_image/quizbg.png")));
        numPanel3.add(quizbg);
        quizbg.setBounds(0,0,700,500);
        quizbg.setVisible(false);
        
    }
    
    public void comPanel1_timer(){
        seconds ++ ;
        switch(seconds){
            case 4: 
                com_bg.setVisible(false);
                com_bg1.setVisible(true);
                clipCom1.start();
                break;
            case 14: 
                small.setVisible(true);
                break;
            case 17: 
                eq.setVisible(true);
                break;
            case 20: 
                big.setVisible(true);
                break;
            case 21 :
                next.setVisible(true);
                btn_next.setVisible(true);
                repeat.setVisible(true);
                btn_repeat.setVisible(true);
                timer.stop();
                break;
            default: 
                break;
        }        
    }
    
    public void comPanel1(){
        comPanel1.setLayout(null);
        //music button
        comPanel1.add(mute);
        comPanel1.add(music);
        comPanel1.add(btn_mute);
            
        Font f = new Font("Tahoma", 0, 24);
        small = new JLabel();
        small.setText("smaller than");
        small.setFont(f);
        comPanel1.add(small);
        small.setBounds(130, 390, 150, 20);
        small.setVisible(false);
        
        eq = new JLabel();
        eq.setText("equal");
        eq.setFont(f);
        comPanel1.add(eq);
        eq.setBounds(320, 380, 70, 30);
        eq.setVisible(false);
        
        big = new JLabel();
        big.setText("bigger than");
        big.setFont(f);
        comPanel1.add(big);
        big.setBounds(460, 380, 140, 40);
        big.setVisible(false);
        
        //btn_next / repeat
        comPanel1.add(next);
        next.setVisible(false);
        btn_next.setActionCommand("com_next1");
        btn_next.setVisible(false);
        comPanel1.add(btn_next);
        
        comPanel1.add(repeat);
        repeat.setVisible(false);
        comPanel1.add(btn_repeat);
        btn_repeat.setVisible(false);
        btn_repeat.setActionCommand("comPanel1_repeat");
        
        com_bg1 = new JLabel();
        com_bg1.setIcon(new ImageIcon(getClass().getResource("/number_image/comPanel.jpg")));
        comPanel1.add(com_bg1);
        com_bg1.setBounds(0,0,700,500);
        com_bg1.setVisible(false);
        
        com_bg = new JLabel();
        com_bg.setIcon(new ImageIcon(getClass().getResource("/number_image/com_bg.jpg")));
        comPanel1.add(com_bg);
        com_bg.setBounds(0,0,700,500);
    }
    public void comPanel2_timer(){
        seconds++;
        switch(seconds){
            case 7: 
                big.setVisible(true);
                break;
            case 10: 
                less.setVisible(true);
                small.setVisible(true);
                break;
            case 14: 
                same.setVisible(true);
                break;
            case 19: 
                eq.setVisible(true);
                break; 
            case 20: 
                next.setVisible(true);
                btn_next.setVisible(true);
                repeat.setVisible(true);
                btn_repeat.setVisible(true);
                timer.stop();
            default: 
                break;     
        }
    }
    
    public void comPanel2(){
        comPanel2.setLayout(null);
        
        comPanel2.add(mute);
        comPanel2.add(music);
        comPanel2.add(btn_mute);
        
        Clip clipCom2 = s.playMusic("compare2.wav");
        clipCom2.start();
    
        //btn_next / repeat
        comPanel2.add(next);
        next.setVisible(false);
        btn_next.setActionCommand("com_next2");
        btn_next.setVisible(false);
        comPanel2.add(btn_next);
        
        comPanel2.add(repeat);
        repeat.setVisible(false);
        btn_repeat.setActionCommand("comPanel2_repeat");
        comPanel2.add(btn_repeat);
        btn_repeat.setVisible(false);
        
        eq = new JLabel();
        eq.setIcon(new ImageIcon(getClass().getResource("/number_image/eq.png")));
        comPanel2.add(eq);
        eq.setBounds(280, 340, 120, 80);
        eq.setVisible(false);
        
        big = new JLabel();
        big.setIcon(new ImageIcon(getClass().getResource("/number_image/big.png")));
        comPanel2.add(big);
        big.setBounds(270, 80, 120, 130);
        big.setVisible(false);
        
        small = new JLabel();
        small.setIcon(new ImageIcon(getClass().getResource("/number_image/small.png")));
        comPanel2.add(small);
        small.setBounds(230, 190, 160, 130);
        small.setVisible(false);
        
        less = new JLabel();
        less.setIcon(new ImageIcon(getClass().getResource("/number_image/less.png")));
        comPanel2.add(less);
        less.setBounds(100, 220, 500, 90);
        less.setVisible(false);
        
        same = new JLabel();
        same.setIcon(new ImageIcon(getClass().getResource("/number_image/same.png")));
        comPanel2.add(same);
        same.setBounds(110, 320, 450, 110);
        same.setVisible(false);
        
        more = new JLabel();
        more.setIcon(new ImageIcon(getClass().getResource("/number_image/more.png")));
        comPanel2.add(more);
        more.setBounds(60, 100, 500, 100);
        
        com1 = new JLabel();
        com1.setIcon(new ImageIcon(getClass().getResource("/number_image/comPanel1.jpg")));
        comPanel2.add(com1);
        com1.setBounds(0,0,700,500);
    }
    
    public void comPanel3_timer(){
        seconds++;
        switch(seconds){
            case 13: 
                ex1.setVisible(true);
                break;
            case 21: 
                ex2.setVisible(true);
                break;
            case 27: 
                ex3.setVisible(true);
                break;
            case 29: 
                next.setVisible(true);
                btn_next.setVisible(true);
                repeat.setVisible(true);
                btn_repeat.setVisible(true);
                timer.stop();
                break;
            default: 
                break;
        }
    }
    
    public void comPanel3(){
        comPanel3.setLayout(null);
        
        comPanel3.add(mute);
        comPanel3.add(music);
        comPanel3.add(btn_mute);
        
        Clip clipcom3 = s.playMusic("compare3.wav");
        clipcom3.start();
                        
        Font f = new Font("Tahoma", 1, 36); // BOLD
        Font f1 = new Font("Tahoma", 1, 60);
        Color c = new Color(0, 51, 204);
        expr1 = new JLabel();
        expr1.setFont(f); 
        expr1.setForeground(c);
        expr1.setText("17                       5");
        comPanel3.add(expr1);
        expr1.setBounds(130, 310, 350, 60);

        expr2 = new JLabel();
        expr2.setFont(f); 
        expr2.setForeground(c);
        expr2.setText("20                      19");
        comPanel3.add(expr2);
        expr2.setBounds(130, 220, 340, 50);

        expr3 = new JLabel();
        expr3.setFont(f); 
        expr3.setForeground(c);
        expr3.setText("10                      13");
        comPanel3.add(expr3);
        expr3.setBounds(130, 110, 340, 90);
        
        ex1 = new JLabel();
        ex1.setFont(f1);
        ex1.setText("<");
        comPanel3.add(ex1);
        ex1.setBounds(270, 110, 60, 80);
        ex1.setVisible(false);
        
        ex2 = new JLabel();
        ex2.setFont(f1);
        ex2.setText(">");
        comPanel3.add(ex2);
        ex2.setBounds(270, 200, 60, 80);
        ex2.setVisible(false);
        
        ex3 = new JLabel();
        ex3.setFont(f1);
        ex3.setText(">");
        comPanel3.add(ex3);
        ex3.setBounds(270, 300, 60, 50);
        ex3.setVisible(false);
        
        //btn_next / repeat
        comPanel3.add(next);
        next.setVisible(false);
        btn_next.setActionCommand("comQuiz");
        btn_next.setVisible(false);
        comPanel3.add(btn_next);
        
        comPanel3.add(repeat);
        repeat.setVisible(false);
        comPanel3.add(btn_repeat);
        btn_repeat.setVisible(false);
        btn_repeat.setActionCommand("comPanel3_repeat");
        
        com2 = new JLabel();
        com2.setIcon(new ImageIcon(getClass().getResource("/number_image/comPanel2.jpg")));
        comPanel3.add(com2);
        com2.setBounds(0,0,700,500);      
    }
    public void comQuiz_timer(){
        seconds++;
        switch(seconds){
            case 1: 
                quizbg.setVisible(true);
                break;
            case 4: 
                quizbg.setVisible(false);
                com_quizbg.setVisible(true);
                q1.setVisible(true);
                btn1.setVisible(true);
                timer.stop();
                btn1.setActionCommand("com_right1");
                btn2.setVisible(true);
                btn2.setActionCommand("com_wrong1");
                btn3.setVisible(true);
                btn3.setActionCommand("com_wrong1");
                break;
            case 7: 
                q1.setVisible(false);
                q1_ans.setVisible(false);
                q2.setVisible(true);
                btn1.setVisible(true);
                btn1.setActionCommand("com_wrong2");
                btn2.setVisible(true);
                btn2.setActionCommand("com_wrong2");
                btn3.setVisible(true);
                btn3.setActionCommand("com_right2");
                timer.stop();
                break;
            case 10: 
                q2.setVisible(false);
                q2_ans.setVisible(false);
                q3.setVisible(true);
                btn1.setVisible(true);
                btn1.setActionCommand("com_right1");
                btn2.setVisible(true);
                btn2.setActionCommand("com_wrong1");
                btn3.setVisible(true);
                btn3.setActionCommand("com_wrong1");
                timer.stop();
                break;
            case 13: 
                Result r = new Result();
                r.saveResult(0,correct_ans);
                timer.stop();
                comQuiz.setVisible(false);
                lastPanel();
                break;
            default: 
                break;
        }
        
    }
    public void comQuiz(){
        comQuiz.setLayout(null);
        
        comQuiz.add(mute);
        comQuiz.add(music);
        comQuiz.add(btn_mute);
        
        btn1 = new JButton();
        btn1.setContentAreaFilled(false);
        btn1.setBorderPainted(false);
        btn1.setBounds(80, 340, 90, 90);
        comQuiz.add(btn1);
        btn1.setVisible(false);
        btn1.addActionListener(this);
        
        btn2 = new JButton();
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        btn2.setBounds(210, 340, 90, 90);
        comQuiz.add(btn2);
        btn2.setVisible(false);
        btn2.addActionListener(this);
        
        btn3 = new JButton();
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn3.setBounds(350, 340, 90, 90);
        comQuiz.add(btn3);
        btn3.setVisible(false);
        btn3.addActionListener(this);
        
        q1_ans.setIcon(new ImageIcon(getClass().getResource("/number_image/q1_ans.png")));
        comQuiz.add(q1_ans);
        q1_ans.setBounds(0,0,700,500);
        q1_ans.setVisible(false);
        
        q2_ans.setIcon(new ImageIcon(getClass().getResource("/number_image/q2_ans.png")));
        comQuiz.add(q2_ans);
        q2_ans.setBounds(0,0,700,500);
        q2_ans.setVisible(false);
        
        q1 = new JLabel();
        q1.setIcon(new ImageIcon(getClass().getResource("/number_image/banana.png")));
        comQuiz.add(q1);
        q1.setBounds(60, 110, 590, 190);
        q1.setVisible(false);
        
        q2 = new JLabel();
        q2.setIcon(new ImageIcon(getClass().getResource("/number_image/cake.png")));
        comQuiz.add(q2);
        q2.setBounds(110, 110, 510, 190);
        q2.setVisible(false);
        
        q3 = new JLabel();
        q3.setIcon(new ImageIcon(getClass().getResource("/number_image/carrot.png")));
        comQuiz.add(q3);
        q3.setBounds(100, 150, 500, 150);
        q3.setVisible(false);
        
        com_quizbg = new JLabel();
        com_quizbg.setIcon(new ImageIcon(getClass().getResource("/number_image/com_ques.png")));
        comQuiz.add(com_quizbg);
        com_quizbg.setBounds(0,0,700,500);
        com_quizbg.setVisible(false);
        
        quizbg = new JLabel();
        quizbg.setIcon(new ImageIcon(getClass().getResource("/number_image/quizbg.png")));
        comQuiz.add(quizbg);
        quizbg.setBounds(0,0,700,500);
        comQuiz.add(quizbg);
        quizbg.setVisible(false);
    }
    
    public void lastPanel(){

        lastPanel.setLayout(null);
        
        lastPanel.add(mute);
        lastPanel.add(music);
        lastPanel.add(btn_mute);
        
        JLabel menu = new JLabel(formatImage("/home_icon.png", 65, 65)); 
        menu.setBounds(600, 105, 65, 65);
        lastPanel.add(menu);

        JButton btn_menu = new JButton();
        btn_menu.setContentAreaFilled(false);
        btn_menu.setBorderPainted(false);
        btn_menu.setBounds(600, 105, 65, 65);
        lastPanel.add(btn_menu);
        btn_menu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                        lastPanel.setVisible(false);
                        Menu m = new Menu();
                        m.showMenu();
                        bgm.stop();
                        s = null;
                        dispose();
                }
        });
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(getClass().getResource("/number_image/end.png")));
        bg.setBounds(0,0,700,500);
        lastPanel.add(bg);

    }
    public ImageIcon formatImage(String imageName, int width, int height ) { 
    ImageIcon image = new ImageIcon(getClass().getResource(imageName));
	Image getimage = image.getImage();
	Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	image = new ImageIcon(resizeimage);
	return image;
}
   
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startB){
            mainPanel.setVisible(false);
            numPanel1();
            timer.setActionCommand("numPanel1_timer");
            timer.setInitialDelay(6000);
            timer.start(); 
        } else if(e.getActionCommand() == "next2"){
            numPanel1.setVisible(false);
            numPanel2();
            seconds = 0;
            timer.setActionCommand("numPanel2_timer");
            timer.setInitialDelay(0);
            timer.start();
        } else if (e.getActionCommand() == "next3"){
            numPanel2.setVisible(false);
            numPanel3();
            seconds = 0;
            timer.setActionCommand("numPanel3_timer");
            timer.start();
        }  else if (e.getActionCommand() == "wrong1" || e.getActionCommand() == "correct1"){
            if (e.getActionCommand() == "correct1"){
                correct.setMicrosecondPosition(0);
                correct.start();
                correct_ans++;
            } else {
                wrong.setMicrosecondPosition(0);
                wrong.start();
            }
            q1_ans.setVisible(true);
            timer.start();
        } else if (e.getActionCommand() == "wrong2" || e.getActionCommand() == "correct2"){
            if (e.getActionCommand() == "correct2"){
                correct.setMicrosecondPosition(0);
                correct.start();
                correct_ans++;
            } else {
                wrong.setMicrosecondPosition(0);
                wrong.start();
            }
            q2_ans.setVisible(true);
            timer.start();
        } else if (e.getActionCommand() == "com_next1"){
            comPanel1.setVisible(false);
            comPanel2();
            seconds = 0;
            timer.setActionCommand("comPanel2_timer");
            timer.start();    
        }  else if (e.getActionCommand() == "com_next2"){
            comPanel2.setVisible(false);
            comPanel3();
            seconds = 0;
            timer.setActionCommand("comPanel3_timer");
            timer.start();    
        } else if (e.getActionCommand() == "comQuiz"){
            comPanel3.setVisible(false);
            comQuiz();
            seconds = 0;
            timer.setActionCommand("comQuiz_timer");
            timer.start();    
        }else if (e.getActionCommand() == "com_wrong1" || e.getActionCommand() == "com_right1"){
            if (e.getActionCommand() == "com_right1"){
                correct.setMicrosecondPosition(0);
                correct.start();
                correct_ans++;
            } else {
                wrong.setMicrosecondPosition(0);
                wrong.start();
            }
            q1_ans.setVisible(true);
            timer.start();
        } else if (e.getActionCommand() == "com_wrong2" || e.getActionCommand() == "com_right2"){
            if (e.getActionCommand() == "com_right2"){
                correct.setMicrosecondPosition(0);
                correct.start();
                correct_ans++;
            } else {
                wrong.setMicrosecondPosition(0);
                wrong.start();
            }
            q2_ans.setVisible(true);
            timer.start();
        } else if (e.getActionCommand() == "numpanel1_repeat"){
            numPanel1.removeAll();
            numPanel1();
            seconds = 0;
            timer.setActionCommand("numPanel1_timer");
            timer.setInitialDelay(6000);
            timer.start(); 
        } else if (e.getActionCommand() == "numpanel2_repeat"){
            numPanel2.removeAll();
            numPanel2();
            seconds = 0;
            timer.setActionCommand("numPanel2_timer");
            timer.start();
        } else if (e.getActionCommand() == "comPanel1_repeat"){
            comPanel1.removeAll();
            comPanel1();
            seconds = 3;
            timer.setActionCommand("comPanel1_timer");
            timer.start();
        } else if (e.getActionCommand() == "comPanel2_repeat"){
            comPanel2.removeAll();
            comPanel2();
            seconds = 0;
            timer.setActionCommand("comPanel2_timer");
            timer.start();  
        } else if (e.getActionCommand() == "comPanel3_repeat"){
            comPanel3.removeAll();
            comPanel3();
            seconds = 0;
            timer.setActionCommand("comPanel3_timer");
            timer.start();   
        }
    }     
}

