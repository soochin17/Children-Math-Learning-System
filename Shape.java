import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shape extends JFrame implements ActionListener {
	private JFrame frame;
	private JButton btn_start, b2, b3, home, btn_mute, btn_music;
	private JPanel mainPanel, p1, p2, p3, quizPanel, q1, q2, q3, lastPanel;
	private JLabel shapetitle, shape, square, circle, triangle, quizpage_main, quiz1_main, quiz2_main, quiz3_main, last, lasttitle;
	private JLabel sq_lesson, cir_lesson, tri_lesson; 
	private JLabel hint_learnagain, hint_startquiz;
	private JLabel question1, question2, question3; //quiz title
	private JLabel cir_quiz, sq_quiz, tri_quiz; //quiz image

	private JButton btn_learnagain, btn_startquiz;
	private JLabel what1,what2,what3; //title: what is this shape?
	private JButton q11,q12,q13; //answer for q1 (white)
	private JButton q21,q22,q23; //answer for q2
	private JButton q31,q32,q33; //answer for q3
	private JLabel c1,c2,c3; //choice 1,2,3 (green/red cloud)
	private JLabel whoami; //quiz question
	private JLabel title; //quiz title
	
	private JLabel blackboard, clock, barn, speak;
	private JLabel smile, monkey, blackboard2, speak2;
	private JLabel watermelon, dragon, blackboard3, speak3;

	private double seconds;
	public Timer timer = new Timer(500, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds = seconds + 0.5;
			if(e.getActionCommand() == "p1_timer") {
				panel1_timer();
			}else if (e.getActionCommand() == "p2_timer") {
				panel2_timer();
			}else if (e.getActionCommand() == "p3_timer") {
				panel3_timer();
			}else if (e.getActionCommand() == "quiz1_timer") {
				quiz1_timer();
			} else if (e.getActionCommand() == "quiz2_timer") {
				quiz2_timer();
			} else if (e.getActionCommand() == "quiz3_timer") {
				quiz3_timer();
			} 
		}
	});
	
	//music
	private playAudio s = new playAudio();
	private Clip clip; //bgm
	private Clip clip1, clip2; //p1
	private Clip clip3, clip4; //p2
	private Clip clip5, clip6; //p3
	private Clip clip7, clip8; //quiz
	private Clip soundwhoami; //quiz sound: Who am I?

	private int correct_ans = 0; // initialize marks to 0
	

	public Shape (){
		frame = new JFrame("Let's learn shape! ");
		frame.pack();
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
		
		btn_music = new JButton(formatImage("/resource/btn_music.png"));
		btn_music.setBounds(600,25,65,65);
		btn_music.setOpaque(false);
		btn_music.setContentAreaFilled(false);
		btn_music.setBorderPainted(false);
		btn_music.setFocusPainted(false);
		btn_music.addActionListener(this);
		btn_music.setVisible(true);

		
		btn_mute = new JButton(formatImage("/resource/btn_mute.png"));
		btn_mute.setBounds(600,25,65,65);
		btn_mute.setOpaque(false);
		btn_mute.setContentAreaFilled(false);
		btn_mute.setBorderPainted(false);
		btn_mute.setFocusPainted(false);
		btn_mute.addActionListener(this);
		btn_mute.setVisible(false);

		
		/* b2 = back button */
		b2 = new JButton(formatImage("/resource/btn_back.png"));
		b2.setBounds(610,50,55,55);
		b2.setOpaque(false);
		b2.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b2.setFocusPainted(false);
		b2.addActionListener(this);
		
		/* b3 = next button */
		b3 = new JButton(formatImage("/resource/btn_next.png"));
		b3.setBounds(610,115,55,55);
		b3.setOpaque(false);
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);
		b3.setFocusPainted(false);	
		b3.addActionListener(this);
	
		/* home = menu button */
		home = new JButton(formatImage("/resource/btn_home.png"));
		home.setBounds(270,230,150,65);
		home.setOpaque(false);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		home.setFocusPainted(false);
		home.addActionListener(this);
		home.setVisible(false);
		
		s = new playAudio();
		clip = s.playMusic("bgm.wav"); 
		clip.loop(clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void lastpage() {
		lastPanel = new JPanel();
		lastPanel.setBounds(0, 0, 700, 500);
		lastPanel.setLayout(null);
		lastPanel.setVisible(true);
		
		last = new JLabel(formatImage("/resource/end.gif"));
		last.setBounds(0,0,700,500);
		
		home.setActionCommand("go_to_mainMenu");
		home.setVisible(true);
		
		lasttitle = new JLabel(formatImage("/resource/lasttitle.png"));
		lasttitle.setBounds(160,100,400,110);
		
		lastPanel.add(lasttitle);
		lastPanel.add(home);
		lastPanel.add(last);
		frame.setContentPane(lastPanel);
	}
	
	public void quiz3_timer() {
		timer.setActionCommand("quiz3_timer");
		timer.start();
		if(seconds == 3) {
			q2.setVisible(false);
			quiz3();
			q3.setVisible(true);
		} 
		if(seconds == 3.5) {
			soundwhoami.start();
		}
		if(seconds == 5) {
			soundwhoami.stop();
		}
	}
	
	/* Square Quiz */
	public void quiz3() {
		q3 = new JPanel();
		q3.setBounds(0, 0, 700, 500);
		q3.setLayout(null);
		q3.setVisible(false);
		
		clip7 = s.playMusic("correct_sound.wav"); 
		clip8 = s.playMusic("wrong_sound.wav");
		soundwhoami = s.playMusic("whoami3.wav");
		
		b3.setActionCommand("go_to_lastPanel");
		b3.setVisible(false);
		
		question3 = new JLabel(formatImage("/resource/question3.png"));
		question3.setBounds(10, 15, 200, 70);
		
		whoami = new JLabel(formatImage("/resource/whoami.png"));
		whoami.setBounds(320,160,250,75);
		
		sq_quiz = new JLabel(formatImage("/resource/sq3.gif"));
		sq_quiz.setBounds(50,70,300,200);
		
		/* Show wrong or correct cloud ... */
		c1 = new JLabel(formatImage("/resource/q2331.png"));
		c1.setBounds(40,300,180,110);
		c1.setVisible(false);

		c2 = new JLabel(formatImage("/resource/q1132.png"));
		c2.setBounds(250,300,180,110);
		c2.setVisible(false);

		c3 = new JLabel(formatImage("/resource/q33.png")); //correct
		c3.setBounds(460,300,180,110);
		c3.setVisible(false);

		/* Choices here ... */
		q31 = new JButton(formatImage("/resource/qcir.png")); //wrong
		q31.setBounds(40,300,180,110);
		q31.setOpaque(false);
		q31.setContentAreaFilled(false);
		q31.setBorderPainted(false);
		q31.setFocusPainted(false);
		q31.addActionListener(this);
		
		q32 = new JButton(formatImage("/resource/qtri.png")); //wrong
		q32.setBounds(250,300,180,110);
		q32.setOpaque(false);
		q32.setContentAreaFilled(false);
		q32.setBorderPainted(false);
		q32.setFocusPainted(false);
		q32.addActionListener(this);
		
		q33 = new JButton(formatImage("/resource/qsq.png")); //correct
		q33.setBounds(460,300,180,110);
		q33.setOpaque(false);
		q33.setContentAreaFilled(false);
		q33.setBorderPainted(false);
		q33.setFocusPainted(false);
		q33.addActionListener(this);
		
		quiz3_main = new JLabel(formatImage("/resource/bg_panel8.gif"));
		quiz3_main.setBounds(0, 0, 700, 500);
		
		q3.add(question3);
		q3.add(whoami);
		q3.add(sq_quiz);		
		q3.add(c1);
		q3.add(c2);
		q3.add(c3);
		q3.add(b3);
		q3.add(q31);
		q3.add(q32);		
		q3.add(q33);
		q3.add(quiz3_main);
		frame.setContentPane(q3);
	}
	
	public void quiz2_timer() {
			timer.setActionCommand("quiz2_timer");
			timer.start();
			if(seconds == 3) {
				q1.setVisible(false);
				quiz2(); //call quiz2
				q2.setVisible(true);
			} 
			if(seconds == 3.5) {
				soundwhoami.start();
			}
			if(seconds == 5) {
				soundwhoami.stop();
			}
	}
	
	/* Triangle Quiz */
	public void quiz2() {
		q2 = new JPanel();
		q2.setBounds(0, 0, 700, 500);
		q2.setLayout(null);
		q2.setVisible(false);
		
		clip7 = s.playMusic("correct_sound.wav"); 
		clip8 = s.playMusic("wrong_sound.wav");
		soundwhoami = s.playMusic("whoami2.wav");
		
		question2 = new JLabel(formatImage("/resource/question2.png"));
		question2.setBounds(10, 15, 200, 70);
		
		whoami = new JLabel(formatImage("/resource/whoami.png"));
		whoami.setBounds(320,160,250,75);
		
		tri_quiz = new JLabel(formatImage("/resource/t1.gif"));
		tri_quiz.setBounds(50,70,300,200);

		/* Show wrong or correct cloud ... */
		c1 = new JLabel(formatImage("/resource/q21.png")); //correct
		c1.setBounds(40,300,180,110);
		c1.setVisible(false);
		
		c2 = new JLabel(formatImage("/resource/q1322.png"));
		c2.setBounds(250,300,180,110);
		c2.setVisible(false);
		
		c3 = new JLabel(formatImage("/resource/q2331.png"));
		c3.setBounds(460,300,180,110);
		c3.setVisible(false);

		/* Choices here ... */
		q21 = new JButton(formatImage("/resource/qtri.png")); 
		q21.setBounds(40,300,180,110);
		q21.setOpaque(false);
		q21.setContentAreaFilled(false);
		q21.setBorderPainted(false);
		q21.setFocusPainted(false);
		q21.addActionListener(this);

		q22 = new JButton(formatImage("/resource/qsq.png")); 
		q22.setBounds(250,300,180,110);
		q22.setOpaque(false);
		q22.setContentAreaFilled(false);
		q22.setBorderPainted(false);
		q22.setFocusPainted(false);
		q22.addActionListener(this);
		
		q23 = new JButton(formatImage("/resource/qcir.png")); 
		q23.setBounds(460,300,180,110);
		q23.setOpaque(false);
		q23.setContentAreaFilled(false);
		q23.setBorderPainted(false);
		q23.setFocusPainted(false);
		q23.addActionListener(this);
		
		quiz2_main = new JLabel(formatImage("/resource/bg_panel7.gif"));
		quiz2_main.setBounds(0, 0, 700, 500);
		
		q2.add(question2);
		q2.add(whoami);
		q2.add(tri_quiz);
		q2.add(c1);
		q2.add(c2);
		q2.add(c3);
		q2.add(q21);
		q2.add(q22);		
		q2.add(q23);		
		q2.add(quiz2_main);	
		frame.setContentPane(q2);
	}
	
	public void quiz1_timer() {
		timer.setActionCommand("quiz1_timer");
		timer.start();
		if(seconds == 0) {
			q1.setVisible(true);
		} 
		if (seconds == 1.5) {
			soundwhoami.start();
		}
		if(seconds == 3) {
			soundwhoami.stop();
		}
	}

	/* Circle Quiz */
	public void quiz1() {
		q1 = new JPanel();
		q1.setBounds(0, 0, 700, 500);
		q1.setLayout(null);
		q1.setVisible(false);
		
		clip7 = s.playMusic("correct_sound.wav"); 
		clip8 = s.playMusic("wrong_sound.wav");
		soundwhoami = s.playMusic("whoami1.wav");
		
		question1 = new JLabel(formatImage("/resource/question1.png"));
		question1.setBounds(10, 15, 200, 70);
		
		whoami = new JLabel(formatImage("/resource/whoami.png"));
		whoami.setBounds(320,160,250,75);
		
		cir_quiz = new JLabel(formatImage("/resource/c3.gif"));
		cir_quiz.setBounds(50,70,300,200);
		
		/* Show wrong or correct cloud ... */
		c1 = new JLabel(formatImage("/resource/q1132.png"));
		c1.setBounds(40,300,180,110);
		c1.setVisible(false);
		
		c2 = new JLabel(formatImage("/resource/q12.png")); //correct
		c2.setBounds(250,300,180,110);
		c2.setVisible(false);
		
		c3 = new JLabel(formatImage("/resource/q1322.png"));
		c3.setBounds(460,300,180,110);
		c3.setVisible(false);
		
		/* Choice here ... */
		q11 = new JButton(formatImage("/resource/qtri.png")); //wrong
		q11.setBounds(40,300,180,110);
		q11.setOpaque(false);
		q11.setContentAreaFilled(false);
		q11.setBorderPainted(false);
		q11.setFocusPainted(false);
		q11.addActionListener(this);
		
		q12 = new JButton(formatImage("/resource/qcir.png")); //correct
		q12.setBounds(250,300,180,110);
		q12.setOpaque(false);
		q12.setContentAreaFilled(false);
		q12.setBorderPainted(false);
		q12.setFocusPainted(false);
		q12.addActionListener(this);
		
		q13 = new JButton(formatImage("/resource/qsq.png")); //wrong
		q13.setBounds(460,300,180,110);
		q13.setOpaque(false);
		q13.setContentAreaFilled(false);
		q13.setBorderPainted(false);
		q13.setFocusPainted(false);
		q13.addActionListener(this);
		
		quiz1_main = new JLabel(formatImage("/resource/bg_panel4.gif"));
		quiz1_main.setBounds(0, 0, 700, 500);
		
		q1.add(question1);
		q1.add(whoami);
		q1.add(cir_quiz);
		q1.add(c1);
		q1.add(c2);
		q1.add(c3);
		q1.add(q11);
		q1.add(q12);		
		q1.add(q13);		
		q1.add(quiz1_main);	
		frame.setContentPane(q1);
	}

	/* Main page of Shape Quiz*/
	public void quizpage() {
		quizPanel = new JPanel();
		quizPanel.setBounds(0, 0, 700, 500);
		quizPanel.setLayout(null);
		quizPanel.setVisible(true);
		
		quizpage_main = new JLabel(formatImage("/resource/bg_quizpage.png")); 
		quizpage_main.setBounds(0,0,700,500);
		
		title = new JLabel(formatImage("/resource/quiztitle.png"));
		title.setBounds(185, 100, 320, 100);
		
		Font f = new Font("Arial Black", Font.ITALIC, 15);
		hint_learnagain = new JLabel("Not ready yet ...");
		hint_learnagain.setBounds(170,200,100,100);
		hint_learnagain.setFont(f);
		hint_learnagain.setForeground(Color.BLACK);
		
		hint_startquiz = new JLabel("I am ready! ");
		hint_startquiz.setBounds(425,200,100,100);
		hint_startquiz.setFont(f);
		hint_startquiz.setForeground(Color.BLACK);
		
		btn_learnagain = new JButton(formatImage("/resource/btn_learnagain.png"));
		btn_learnagain.setBounds(140,265,155,65);
		btn_learnagain.setOpaque(false);
		btn_learnagain.setContentAreaFilled(false);
		btn_learnagain.setBorderPainted(false);
		btn_learnagain.addActionListener(this);
		btn_learnagain.setActionCommand("learn again"); //go back to panel1
		
		btn_startquiz = new JButton(formatImage("/resource/btn_startquiz.png"));
		btn_startquiz.setBounds(400,265,155,65);
		btn_startquiz.setOpaque(false);
		btn_startquiz.setContentAreaFilled(false);
		btn_startquiz.setBorderPainted(false);
		btn_startquiz.addActionListener(this);
		btn_startquiz.setActionCommand("start quiz"); //go to quiz1
		
		quizPanel.add(title);
		quizPanel.add(hint_learnagain);
		quizPanel.add(hint_startquiz);
		quizPanel.add(btn_learnagain);
		quizPanel.add(btn_startquiz);
		quizPanel.add(quizpage_main); //background
		frame.setContentPane(quizPanel);
		
	}
	
	public void panel3_timer() {
		timer.setActionCommand("p3_timer");
		timer.start();
		if (seconds == 0) {
			p3.setVisible(true);
		} 
		if (seconds == 2) {
			clip5.start();
		} 
		if (seconds == 2.5) {
			watermelon.setVisible(true);
		} 
		if(seconds == 4) {
			speak3.setVisible(true);
			tri_lesson.setVisible(true);
			clip6.start();
		} 
		if (seconds == 5.5){
			b2.setVisible(true);
			b3.setVisible(true);
			clip5.stop();
			clip6.stop();
			timer.stop();
			seconds=0;
		}	
	}
	
	/* Triangle */
	public void panel3() {
		p3 = new JPanel();
		p3.setBounds(0, 0, 700, 500);
		p3.setLayout(null);
		p3.setVisible(false);
		
		clip5 = s.playMusic("whatisthis3.wav");
		clip6 = s.playMusic("triangle.wav");
		
		/* triangle background */
		triangle = new JLabel(formatImage("/resource/triangle3.png"));
		triangle.setBounds(0,0,700,500);
		
		what3 = new JLabel(formatImage("/resource/whatisthis3.png"));
		what3.setBounds(70,50,500,90);
		what3.setVisible(true);
		
		watermelon = new JLabel(formatImage("/resource/tri.png"));
		watermelon.setBounds(245,125,150,155);
		watermelon.setVisible(false);
		
		dragon = new JLabel(formatImage("/resource/dragon.png"));
		dragon.setBounds(20,300,165,160);
		dragon.setVisible(true);
		
		speak3 = new JLabel(formatImage("/resource/speak.png"));
		speak3.setBounds(175,310,55,88);
		speak3.setVisible(false);
		
		blackboard3 = new JLabel(formatImage("/resource/board.png"));
		blackboard3.setBounds(45,5,550,300);
		blackboard3.setVisible(true);
		
		tri_lesson = new JLabel(formatImage("/resource/triangle.png"));
		tri_lesson.setBounds(250,320,250,100);
		tri_lesson.setVisible(false);
				
		b2.setActionCommand("back_to_p2");
		b2.setVisible(false);
		b3.setActionCommand("go_to_quizpage");
		b3.setVisible(false);
	
		p3.add(what3);
		p3.add(watermelon);
		p3.add(dragon);
		p3.add(blackboard3);
		p3.add(speak3);
		p3.add(tri_lesson);
		p3.add(b2);
		p3.add(b3);
		p3.add(triangle); //background
		frame.setContentPane(p3);
	}

	public void panel2_timer() {
		
		timer.setActionCommand("p2_timer");
		timer.start();
		if (seconds == 0) {
			p2.setVisible(true);
		} 
		if (seconds == 2) {
			clip3.start();
		} 
		if (seconds == 2.5) {
			smile.setVisible(true);
		} 
		if(seconds == 4) {
			speak2.setVisible(true);
			cir_lesson.setVisible(true);
			clip4.start();
		} 
		if (seconds == 5.5){
			b2.setVisible(true);
			b3.setVisible(true);
			clip3.stop();
			clip4.stop();
			timer.stop();
			seconds=0;
		}
	}

	
	/* Circle */
	public void panel2() {
		p2 = new JPanel();
		p2.setBounds(0, 0, 700, 500);
		p2.setLayout(null);
		p2.setVisible(false);
		
		clip3 = s.playMusic("whatisthis2.wav");
		clip4 = s.playMusic("circle.wav");
		
		/* circle background */
		circle = new JLabel(formatImage("/resource/circle3.png"));
		circle.setBounds(0,0,700,500);
		
		what2 = new JLabel(formatImage("/resource/whatisthis2.png"));
		what2.setBounds(70,50,500,90);
		what2.setVisible(true);
		
		smile = new JLabel(formatImage("/resource/cir1.png"));
		smile.setBounds(250,125,140,140);
		smile.setVisible(false);
		
		monkey = new JLabel(formatImage("/resource/monkey.png"));
		monkey.setBounds(20,300,150,150);
		monkey.setVisible(true);
		
		speak2 = new JLabel(formatImage("/resource/speak.png"));
		speak2.setBounds(160,310,55,88);
		speak2.setVisible(false);
		
		blackboard2 = new JLabel(formatImage("/resource/board.png"));
		blackboard2.setBounds(45,5,550,300);
		blackboard2.setVisible(true);
		
		cir_lesson = new JLabel(formatImage("/resource/circle.png"));
		cir_lesson.setBounds(220,320,250,100);
		cir_lesson.setVisible(false);
				
		b2.setActionCommand("back_to_p1");
		b2.setVisible(false);
		b3.setActionCommand("go_to_p3");
		b3.setVisible(false);
	
		p2.add(what2);
		p2.add(smile);
		p2.add(monkey);
		p2.add(blackboard2);
		p2.add(speak2);
		p2.add(cir_lesson);
		p2.add(b2);
		p2.add(b3);
		p2.add(circle); //background
		frame.setContentPane(p2);
	}

	public void panel1_timer() {
		
		timer.setActionCommand("p1_timer");
		timer.start();
		if (seconds == 0) {
			p1.setVisible(true);
		} 
		if (seconds == 2) {
			clip1.start();
		} 
		if (seconds == 2.5) {
			clock.setVisible(true);
		} 
		if(seconds == 4) {
			speak.setVisible(true);
			sq_lesson.setVisible(true);
			clip2.start();
		} 
		if (seconds == 5.5){
			b3.setVisible(true);
			clip1.stop();
			clip2.stop();
			timer.stop();
			seconds=0;
		}		
	}
	
	/* Square */
	public void panel1() {
		p1 = new JPanel();
		p1.setBounds(0, 0, 700, 500);
		p1.setLayout(null);
		p1.setVisible(false);
		
		clip1 = s.playMusic("whatisthis1.wav");
		clip2 = s.playMusic("square.wav");
		
		/* square background */
		square = new JLabel(formatImage("/resource/square3.gif"));
		square.setBounds(0,0,700,500);
		
		what1 = new JLabel(formatImage("/resource/whatisthis1.png"));
		what1.setBounds(70,50,500,90);
		what1.setVisible(true);
		
		clock = new JLabel(formatImage("/resource/sq.png"));
		clock.setBounds(250,125,150,150);
		clock.setVisible(false);
		
		barn = new JLabel(formatImage("/resource/barn.png"));
		barn.setBounds(20,300,135,170);
		barn.setVisible(true);
		
		speak = new JLabel(formatImage("/resource/speak.png"));
		speak.setBounds(130,330,55,88);
		speak.setVisible(false);
		
		blackboard = new JLabel(formatImage("/resource/board.png"));
		blackboard.setBounds(45,5,550,300);
		blackboard.setVisible(true);
		
		sq_lesson = new JLabel(formatImage("/resource/square.png"));
		sq_lesson.setBounds(200,320,250,100);
		sq_lesson.setVisible(false);
				
		b3.setActionCommand("go_to_p2");
		b3.setVisible(false);
	
		p1.add(what1);
		p1.add(clock);
		p1.add(barn);
		p1.add(blackboard);
		p1.add(speak);
		p1.add(sq_lesson);
		p1.add(b3);
		p1.add(square); //background
		frame.setContentPane(p1);
	}

	/* Main page of Shape */
	public void mainpage() {
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 700, 500);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		
		btn_start = new JButton(formatImage("/resource/btn_start.png"));
		btn_start.setBounds(240,280,200,50);
		btn_start.setOpaque(false);
		btn_start.setContentAreaFilled(false);
		btn_start.setBorderPainted(false);
		btn_start.setFocusPainted(false);
		btn_start.addActionListener(this);
		btn_start.setActionCommand("start");
		
		shapetitle = new JLabel(formatImage("/resource/shape.png"));
		shapetitle.setBounds(225,100,250,75);
		
		shape = new JLabel(formatImage("/resource/bg1.gif"));
		shape.setBounds(0, 0, 700, 500);
		
		mainPanel.add(shapetitle);
		mainPanel.add(btn_start);
		mainPanel.add(btn_music);
		mainPanel.add(btn_mute);
		mainPanel.add(shape);

		frame.setContentPane(mainPanel);
	}
	
	
	public ImageIcon formatImage(String imageName ) {
		ImageIcon image = new ImageIcon(getClass().getResource(imageName));
		return image;
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "start") {
			mainPanel.setVisible(false);
			clip.stop();
			panel1();
			panel1_timer();
		} 
		else if(e.getSource() == btn_music) {
			clip.stop();
			btn_music.setVisible(false); 
			btn_mute.setVisible(true);
		} 
		else if(e.getSource() == btn_mute) {
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
			btn_music.setVisible(true);
			btn_mute.setVisible(false);
		} 
		else if (e.getActionCommand() == "go_to_mainMenu") {
			Menu m = new Menu();
			m.showMenu();
			s = null;
			frame.dispose();
		} 
		else if (e.getActionCommand() == "go_to_p2"){
			p1.setVisible(false);
			panel2();
			panel2_timer();
		} 
		else if (e.getActionCommand() == "back_to_p1") {
			p2.setVisible(false);
			p1.removeAll();
			p1.repaint();
			frame.remove(p2);
			panel1();
			panel1_timer();
		} 
		else if (e.getActionCommand()== "go_to_p3"){
			p2.setVisible(false);
			frame.remove(p2);
			panel3();
			panel3_timer();
		} 
		else if (e.getActionCommand() == "back_to_p2") {
			p3.setVisible(false);
			p2.removeAll();
			p2.repaint();
			frame.remove(p3);
			panel2();
			panel2_timer();
		}
		else if (e.getActionCommand() == "go_to_quizpage"){
			p3.setVisible(false);
			frame.remove(p3);
			quizpage();
		}
		else if(e.getActionCommand() == "learn again") {
			quizPanel.setVisible(false);
			frame.remove(quizPanel);
			panel1();
			panel1_timer();
		}
		else if(e.getActionCommand() == "start quiz") {
			quizPanel.setVisible(false);
			frame.remove(quizPanel);
			quiz1();
			quiz1_timer();
		}
		else if(e.getActionCommand() == "go_to_lastPanel") {
			q3.setVisible(false);
			frame.remove(q3);
			lastpage();
		}
		else if ((e.getSource() == q11) || (e.getSource() == q12) || (e.getSource() == q13) || (e.getSource() == q21) || (e.getSource() == q22) || (e.getSource() == q23) || (e.getSource() == q31) || (e.getSource() == q32) || (e.getSource() == q33)){
			timer.stop();
			seconds=0;
			if(e.getSource() == q12) {
				correct_ans = correct_ans + 1;
				clip7.start();
				c2.setVisible(true);
				q11.setEnabled(false); //prevent users to choose another choices
				q12.setEnabled(false);
				q13.setEnabled(false);
				quiz2_timer();
			}
			if(e.getSource() == q11){
				clip8.start();
				c1.setVisible(true);
				q11.setEnabled(false);
				q12.setEnabled(false);
				q13.setEnabled(false);
				quiz2_timer();
			}
			if(e.getSource() == q13) {
				clip8.start();
				c3.setVisible(true);
				q11.setEnabled(false);
				q12.setEnabled(false);
				q13.setEnabled(false);
				quiz2_timer();
			}
			if(e.getSource() == q21) {
				correct_ans = correct_ans + 1;
				clip7.start();
				c1.setVisible(true);
				q21.setEnabled(false);
				q22.setEnabled(false);
				q23.setEnabled(false);
				quiz3_timer();
			}
			if(e.getSource() == q22){
				clip8.start();
				c2.setVisible(true);
				q21.setEnabled(false);
				q22.setEnabled(false);
				q23.setEnabled(false);
				quiz3_timer();
			}
			if(e.getSource() == q23) {
				clip8.start();
				c3.setVisible(true);
				q21.setEnabled(false);
				q22.setEnabled(false);
				q23.setEnabled(false);
				quiz3_timer();
			}
			if((e.getSource() == q31) || (e.getSource() == q32) || (e.getSource() == q33)) {
				if(e.getSource() == q33) {
					correct_ans = correct_ans + 1;
					clip7.start();
					c3.setVisible(true);
					q31.setEnabled(false);
					q32.setEnabled(false);
					q33.setEnabled(false);
					b3.setVisible(true);
				}
				if(e.getSource() == q31){
					clip8.start();
					c1.setVisible(true);
					q31.setEnabled(false);
					q32.setEnabled(false);
					q33.setEnabled(false);
					b3.setVisible(true);
				}
				if(e.getSource() == q32) {
					clip8.start();
					c2.setVisible(true);
					q31.setEnabled(false);
					q32.setEnabled(false);
					q33.setEnabled(false);
					b3.setVisible(true);
				}
				Result r = new Result();
				r.saveResult(5, correct_ans);
			} 		
		}
	}
	
}