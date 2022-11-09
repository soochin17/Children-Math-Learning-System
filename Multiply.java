import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer.*;

public class Multiply extends JFrame implements ActionListener {
	
	private JFrame contentPane;
	private playAudio sound;
	private Clip clip;
	/* main panel */
	private JPanel panel;
	private JButton StartBtn;
	private JLabel bg, title;
	/* panel1 */
	private JPanel panel1;
	private JLabel fish2, fish, fishtank2, fishtank, g_fishtank2, g_fishtank, sent1, sent2, equation1;
	private Clip clip1;
	private JButton next_Btn, repeat_Btn, mute_Btn, music_Btn, menu_Btn;
	/* panel2 */
	private JPanel panel2;
	private JLabel bird, bird2, g_bird, g_bird2,sent3, sent4, equation2;
	/* panel3 */
	private JPanel panel3;
	private JLabel leaf, leaf2, leaf_g1, leaf_g2, sent5, equation3;
	/* Quiz */
	private JPanel panel4, panel5, panel6;
	private JLabel title_quiz;
	private JButton q1a1, q1a2, q1a3, q1a4;
	private JButton q2a1, q2a2, q2a3, q2a4;
	private JButton q3a1, q3a2, q3a3, q3a4;
	private JLabel greenbear, redbear1, redbear2, redbear3;
	private Clip clip4, clip5;
	private int correct_ans;
	
	private double seconds;
	public Timer timer = new Timer(500, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds=seconds+0.5;
			if(e.getActionCommand() == "lesson1_timer") {
				lesson1();
			}else if (e.getActionCommand() == "lesson2_timer") {
				lesson2();
			}else if (e.getActionCommand() == "lesson3_timer") {
				lesson3();
			}else if (e.getActionCommand() == "quiz1_timer") {
				quiz1_exe();
			} else if (e.getActionCommand() == "quiz2_timer") {
				quiz2_exe();
			} else if (e.getActionCommand() == "quiz3_timer") {
				quiz3_exe();
			} 
		}
	});
	
	public Multiply (){
		contentPane = new JFrame("Multiplication!");
		contentPane.pack();
		contentPane.setSize(700,500);
		contentPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setResizable(false);
		contentPane.setLocationRelativeTo(null);
		contentPane.setVisible(true);	
		
		ImageIcon image_music = new ImageIcon(getClass().getResource("/music.png"));
		Image getimage_music = image_music.getImage();
		Image resizeimage_music = getimage_music.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		image_music = new ImageIcon(resizeimage_music);
		music_Btn = new JButton(image_music);
		music_Btn = formatButton(music_Btn, 600, 25, 65, 65); 
		music_Btn.addActionListener(this);
		
		ImageIcon image_mute = new ImageIcon(getClass().getResource("/mute.png"));
		Image getimage_mute = image_mute.getImage();
		Image resizeimage_mute = getimage_mute.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		image_mute = new ImageIcon(resizeimage_mute);
		mute_Btn = new JButton(image_mute);
		mute_Btn = formatButton(mute_Btn, 600, 25, 65, 65); 
		mute_Btn.addActionListener(this);
		mute_Btn.setVisible(false);
		
		ImageIcon image_next = new ImageIcon(getClass().getResource("/next.png"));
		Image getimage_next = image_next.getImage();
		Image resizeimage_next = getimage_next.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		image_next = new ImageIcon(resizeimage_next);
		next_Btn = new JButton(image_next);
		next_Btn = formatButton(next_Btn, 600, 105, 65, 65); 
		next_Btn.addActionListener(this);
		
		ImageIcon image_repeat = new ImageIcon(getClass().getResource("/repeat.png"));
		Image getimage_repeat = image_repeat.getImage();
		Image resizeimage_repeat = getimage_repeat.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		image_repeat = new ImageIcon(resizeimage_repeat);
		repeat_Btn = new JButton(image_repeat);
		repeat_Btn = formatButton(repeat_Btn, 600, 185, 65, 65);
		repeat_Btn.addActionListener(this);
	
		ImageIcon image_menu = new ImageIcon(getClass().getResource("/home_icon.png"));
		Image getimage_menu = image_menu.getImage();
		Image resizeimage_menu = getimage_menu.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
		image_menu = new ImageIcon(resizeimage_menu);
		menu_Btn = new JButton(image_menu);
		menu_Btn = formatButton(menu_Btn, 600, 105, 65, 65); 
		menu_Btn.addActionListener(this);
		menu_Btn.setVisible(false);
		
		sound = new playAudio();
		clip = sound.playMusic("multiply(bgm).wav"); 
		clip.loop(clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == StartBtn) {
			panel.setVisible(false);
			init_lesson1();
			lesson1();
		} else if(e.getSource() == music_Btn) {
			clip.stop();
			music_Btn.setVisible(false);
			mute_Btn.setVisible(true);
		} else if(e.getSource() == mute_Btn) {
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
			mute_Btn.setVisible(false);
			music_Btn.setVisible(true);
		} else if (e.getSource() == menu_Btn) {
			Menu m = new Menu();
			m.showMenu();
			clip.stop();
			sound = null;
			contentPane.dispose();
		} else if (e.getActionCommand()== "lesson1_next"){
			panel1.setVisible(false);
			init_lesson2();
			lesson2();
		} else if (e.getActionCommand()== "lesson1_repeat") {
			panel1.removeAll();
			panel1.repaint();
			contentPane.remove(panel1);
			init_lesson1();
			lesson1();
		} else if (e.getActionCommand()== "lesson2_next"){
			panel2.setVisible(false);
			init_lesson3();
			lesson3();
		} else if (e.getActionCommand()== "lesson2_repeat") {
			panel2.removeAll();
			panel2.repaint();
			contentPane.remove(panel2);
			init_lesson2();
			lesson2();
		}else if (e.getActionCommand()== "lesson3_next"){
			panel3.setVisible(false);
			quiz1();
			quiz1_exe();
		}
		else if (e.getActionCommand()== "lesson3_repeat") {
			panel3.removeAll();
			panel3.repaint();
			contentPane.remove(panel3);
			init_lesson3();
			lesson3();
		} else if ((e.getSource() == q1a1) || (e.getSource() == q1a2) || (e.getSource() == q1a3)|| (e.getSource() == q1a4) || (e.getSource() == q2a1) || (e.getSource() == q2a2) || (e.getSource() == q2a3) || (e.getSource() == q2a4) || (e.getSource() == q3a1) || (e.getSource() == q3a2) || (e.getSource() == q3a3) || (e.getSource() == q3a4)) {
			timer.stop();
			seconds=0;
			if(e.getSource() == q1a1) {
				correct_ans = correct_ans+1;
				clip4.start();
				quiz2_exe();}
			if((e.getSource() == q1a2) || (e.getSource() == q1a3)|| (e.getSource() == q1a4)) {
				clip5.start();
				quiz2_exe();}
			if(e.getSource() == q2a3) {
				correct_ans = correct_ans+1;
				clip4.start();
				quiz3_exe();}
			if((e.getSource() == q2a1) || (e.getSource() == q2a2)||(e.getSource() == q2a4)){
				clip5.start();
				quiz3_exe();}
			if((e.getSource() == q3a1) || (e.getSource() == q3a2)||(e.getSource() == q3a3)|| (e.getSource() == q3a4)) {
				if(e.getSource() == q3a4) {
					correct_ans = correct_ans+1;
					clip4.start();
				}
				if((e.getSource() == q3a1) || (e.getSource() == q3a2)||(e.getSource() == q3a3)){
					clip5.start();
				}
			Result r = new Result();
			r.saveResult(3, correct_ans);
			menu_Btn.setVisible(true);
			}
			redbear1.setVisible(true);
			redbear2.setVisible(true);
			redbear3.setVisible(true);
			greenbear.setVisible(true);
			
		} 
	}

	public void quiz3_exe() {
		
		timer.setActionCommand("quiz3_timer");
		timer.start();
		if(seconds==3) {
			panel5.setVisible(false);
			quiz3();
			panel6.setVisible(true);
		} 
	}
	
	public void quiz3() {
		panel6 = new JPanel();
		panel6.setBounds(0, 0, 700, 500);
		panel6.setLayout(null);
		panel6.setVisible(false);
		contentPane.add(panel6);
		
		clip4 = sound.playMusic("correct_sound.wav"); 
		clip5 = sound.playMusic("wrong_sound.wav");
		
		panel6.add(music_Btn);
		panel6.add(mute_Btn);
		
		panel6.add(menu_Btn);
		menu_Btn.setVisible(false);
		
		title_quiz = new JLabel(formatImage("/Q3.png"));
		title_quiz.setBounds(30, 25, 549, 104);
		panel6.add(title_quiz);
		
		greenbear.setBounds(530, 310,  120, 82);
		greenbear.setVisible(false);
		panel6.add(greenbear);
		
		redbear3.setBounds(370, 310, 120, 82);
		redbear3.setVisible(false);
		panel6.add(redbear3);
		
		redbear1.setVisible(false);
		panel6.add(redbear1);
		
		redbear2.setVisible(false);
		panel6.add(redbear2);
		
		q3a1 = new JButton(formatImage("/q3a.png", 120, 82));
		q3a1 = formatButton(q3a1, 50, 310, 120, 82); 
		q3a1.addActionListener(this);
		panel6.add(q3a1);
		
		q3a2 = new JButton(formatImage("/q3b.png", 120, 82));
		q3a2 = formatButton(q3a2, 210, 310, 120, 82); 
		q3a2.addActionListener(this);
		panel6.add(q3a2);
		
		q3a3 = new JButton(formatImage("/q3c.png", 120, 82));
		q3a3 = formatButton(q3a3, 370, 310, 120, 82); 
		q3a3.addActionListener(this);
		panel6.add(q3a3);
		
		q3a4 = new JButton(formatImage("/q3d.png", 120, 82));	// correct answer
		q3a4 = formatButton(q3a4, 530, 310, 120, 82); 
		q3a4.addActionListener(this);
		panel6.add(q3a4);

		panel6.add(bg);
	}
	
	public void quiz2_exe() {
		
			timer.setActionCommand("quiz2_timer");
			timer.start();
			if(seconds==3) {
				panel4.setVisible(false);
				quiz2();
				panel5.setVisible(true);
			} 
	}
	
	public void quiz2() {

		panel5 = new JPanel();
		panel5.setBounds(0, 0, 700, 500);
		panel5.setLayout(null);
		panel5.setVisible(false);
		contentPane.add(panel5);
		
		clip4 = sound.playMusic("correct_sound.wav"); 
		clip5 = sound.playMusic("wrong_sound.wav");
		
		title_quiz = new JLabel(formatImage("/Q2.png"));
		title_quiz.setBounds(30, 25, 549, 104);
		panel5.add(title_quiz);
		
		panel5.add(music_Btn);
		panel5.add(mute_Btn);
		
		greenbear.setBounds(370, 310,  120, 82);
		greenbear.setVisible(false);
		panel5.add(greenbear);
		
		redbear2.setBounds(50, 310, 120, 82);
		redbear2.setVisible(false);
		panel5.add(redbear2);
		
		redbear1.setVisible(false);
		panel5.add(redbear1);
		
		redbear3.setVisible(false);
		panel5.add(redbear3);
		
		q2a1 = new JButton(formatImage("/q2a.png", 120, 82));
		q2a1 = formatButton(q2a1, 50, 310, 120, 82); 
		q2a1.addActionListener(this);
		panel5.add(q2a1);
		
		q2a2 = new JButton(formatImage("/q2b.png", 120, 82));
		q2a2 = formatButton(q2a2, 210, 310, 120, 82); 
		q2a2.addActionListener(this);
		panel5.add(q2a2);
		
		q2a3 = new JButton(formatImage("/q2c.png", 120, 82));	// correct answer
		q2a3 = formatButton(q2a3, 370, 310, 120, 82); 
		q2a3.addActionListener(this);
		panel5.add(q2a3);
		
		q2a4 = new JButton(formatImage("/q2d.png", 120, 82));
		q2a4 = formatButton(q2a4, 530, 310, 120, 82); 
		q2a4.addActionListener(this);
		panel5.add(q2a4);

		panel5.add(bg);
	}
	
	public void quiz1_exe() {
		timer.setActionCommand("quiz1_timer");
		timer.start();
		if(seconds==0) {
			panel4.setVisible(true);
		} if (seconds==2) {
			title.setVisible(false);
			q1a1.setVisible(true);
			q1a2.setVisible(true);
			q1a3.setVisible(true);
			q1a4.setVisible(true);
			title_quiz.setVisible(true);
		}
	}
	
	public void quiz1() {
		correct_ans =0;
		panel4 = new JPanel();
		panel4.setBounds(0, 0, 700, 500);
		panel4.setLayout(null);
		panel4.setVisible(false);
		contentPane.add(panel4);
			
		panel4.add(music_Btn);
		panel4.add(mute_Btn);
		
		clip4 = sound.playMusic("correct_sound.wav"); 
		clip5 = sound.playMusic("wrong_sound.wav");
		
		title_quiz = new JLabel(formatImage("/Q1.png"));
		title_quiz.setBounds(30, 25, 471, 104);
		title_quiz.setVisible(false);
		panel4.add(title_quiz);
		
		greenbear = new JLabel(formatImage("/greenbear.png", 120, 82));
		greenbear.setBounds(50, 310,  120, 82);
		greenbear.setVisible(false);
		panel4.add(greenbear);
		
		redbear1 = new JLabel(formatImage("/redbear.png", 120, 82));
		redbear1.setBounds(210, 310, 120, 82);
		redbear1.setVisible(false);
		panel4.add(redbear1);
		
		redbear2 = new JLabel(formatImage("/redbear.png", 120, 82));
		redbear2.setBounds(370, 310, 120, 82);
		redbear2.setVisible(false);
		panel4.add(redbear2);
		
		redbear3 = new JLabel(formatImage("/redbear.png", 120, 82));
		redbear3.setBounds(530, 310, 120, 82);
		redbear3.setVisible(false);
		panel4.add(redbear3);
		
		q1a1 = new JButton(formatImage("/q124.png", 120, 82));	// correct answer
		q1a1 = formatButton(q1a1, 50, 310, 120, 82); 
		q1a1.addActionListener(this);
		q1a1.setVisible(false);
		panel4.add(q1a1);
		
		q1a2 = new JButton(formatImage("/q130.png", 120, 82));
		q1a2 = formatButton(q1a2, 210, 310, 120, 82); 
		q1a2.addActionListener(this);
		q1a2.setVisible(false);
		panel4.add(q1a2);
		
		q1a3 = new JButton(formatImage("/q110.png", 120, 82));
		q1a3 = formatButton(q1a3, 370, 310, 120, 82); 
		q1a3.addActionListener(this);
		q1a3.setVisible(false);
		panel4.add(q1a3);
		
		q1a4 = new JButton(formatImage("/q120.png", 120, 82));
		q1a4 = formatButton(q1a4, 530, 310, 120, 82); 
		q1a4.addActionListener(this);
		q1a4.setVisible(false);
		panel4.add(q1a4);
		
		title = new JLabel(formatImage("/title(quiz).png"));
		title.setBounds(0, 0, 700,500);
		panel4.add(title);
		
		bg = new JLabel(formatImage("/bg(quiz).jpg", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel4.add(bg);	
	}

	public void lesson3() {
		
		timer.setActionCommand("lesson3_timer");
		timer.start();
		if (seconds==0) {
			clip1.start();
			panel3.setVisible(true);
		} if (seconds==2) {
			title.setVisible(false);
			leaf.setVisible(true);
			leaf2.setVisible(true);
		} if (seconds==4) {
			leaf_g1.setVisible(true);
			leaf_g2.setVisible(true);
		} if (seconds==5) {
			leaf_g1.setVisible(false);
			leaf_g2.setVisible(false);
		} if (seconds==7.0) {
			leaf_g1.setVisible(true);
		} if (seconds==7.5) {
			leaf_g2.setVisible(true);
		} if (seconds==10.5) {
			leaf_g1.setVisible(false);
			leaf_g2.setVisible(false);
			sent5.setVisible(true);
		} if (seconds==12.5) {
			sent5.setVisible(false);
			equation3.setVisible(true);
		} if (seconds==17) {
			leaf_g1.setVisible(true);
			leaf_g2.setVisible(true);
		} if(seconds==24) {
			repeat_Btn.setVisible(true);
			menu_Btn.setVisible(true);
			next_Btn.setVisible(true);
			clip1.stop();
			timer.stop();
			seconds=0;
		}		
	}

	public void init_lesson3() {
		
		panel3 = new JPanel();
		panel3.setBounds(0, 0, 700, 500);
		panel3.setLayout(null);
		panel3.setVisible(false);
		contentPane.add(panel3);
	
		clip1 = sound.playMusic("multiply(les3).wav"); 
		
		panel3.add(music_Btn);
		panel3.add(mute_Btn);
		
		panel3.add(repeat_Btn);
		repeat_Btn.setActionCommand("lesson3_repeat");
		repeat_Btn.setVisible(false);
					
		panel3.add(next_Btn);
		next_Btn.setActionCommand("lesson3_next");
		next_Btn.setVisible(false);
		
		sent5 = new JLabel(formatImage("/L5.png"));
		sent5.setBounds(10, 330, 595, 150);
		panel3.add(sent5);
		sent5.setVisible(false);

		equation3 = new JLabel(formatImage("/6x3=18.png"));
		equation3.setBounds(54, 345, 479, 88);
		panel3.add(equation3);
		equation3.setVisible(false);
		
		leaf = new JLabel(formatImage("/leaf.gif"));
		leaf.setBounds(10, 110, 500, 100);
		panel3.add(leaf);
		leaf.setVisible(false);
		
		leaf2 = new JLabel(formatImage("/leaf.gif"));
		leaf2.setBounds(85, 230, 500, 100);
		panel3.add(leaf2);
		leaf2.setVisible(false);
		
		leaf_g1 = new JLabel(formatImage("/leaf_glow.png"));
		leaf_g1.setBounds(10, 110, 500, 100);
		panel3.add(leaf_g1);
		leaf_g1.setVisible(false);
		
		leaf_g2 = new JLabel(formatImage("/leaf_glow.png"));
		leaf_g2.setBounds(85, 230, 500, 100);
		panel3.add(leaf_g2);
		leaf_g2.setVisible(false);
		
		title = new JLabel(formatImage("/title3.png"));
		title.setBounds(0, 0, 700, 500);
		panel3.add(title);
			
		bg = new JLabel(formatImage("/bg3.0.jpg", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel3.add(bg);
	}

	public void lesson2() {
		
		timer.setActionCommand("lesson2_timer");
		timer.start();
		if (seconds==0) {
			clip1.start();
			panel2.setVisible(true);
		} if (seconds==2) {
			title.setVisible(false);
			bird.setVisible(true);
			bird2.setVisible(true);
		} if(seconds==3.5) {
			sent3.setVisible(true);
			g_bird.setVisible(true);
			g_bird2.setVisible(true);
		} if(seconds==5) {
			sent3.setVisible(false);
			g_bird2.setVisible(false);
			g_bird.setVisible(false);
		} if(seconds==6.5) {
			sent4.setVisible(true);
			g_bird2.setVisible(true);
			g_bird.setVisible(true);
		} if(seconds==9) {
			sent4.setVisible(false);
			g_bird2.setVisible(false);
			g_bird.setVisible(false);
		} if (seconds==11) {
			equation2.setVisible(true);
		} if(seconds==16.5) {
			g_bird2.setVisible(true);
			g_bird.setVisible(true);
		} if (seconds==18){
			next_Btn.setVisible(true);
			repeat_Btn.setVisible(true);
			clip1.stop();
			timer.stop();	
			seconds=0;
		}
	}

	public void init_lesson2() {
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 700, 500);
		panel2.setLayout(null);
		panel2.setVisible(false);
		contentPane.add(panel2);
		
		clip1 = sound.playMusic("multiply(les2).wav"); 
		
		panel2.add(music_Btn);
		panel2.add(mute_Btn);
		
		panel2.add(next_Btn);
		next_Btn.setActionCommand("lesson2_next");
		next_Btn.setVisible(false);
		
		panel2.add(repeat_Btn);
		repeat_Btn.setActionCommand("lesson2_repeat");
		repeat_Btn.setVisible(false);
		
		sent3 = new JLabel();
		sent3 = new JLabel(formatImage("/L3.png")); 
		sent3.setBounds(213, 230 , 595, 88);
		sent3.setVisible(false);
		panel2.add(sent3);
		
		sent4 = new JLabel();
		sent4 = new JLabel(formatImage("/L4.png")); 
		sent4.setBounds(205, 230 , 595, 88);
		sent4.setVisible(false);
		panel2.add(sent4);
		
		equation2 = new JLabel();
		equation2 = new JLabel(formatImage("/2x5=10.png")); 
		equation2.setBounds(280, 230 , 427, 106);
		equation2.setVisible(false);
		panel2.add(equation2);
			
		bird = new JLabel(formatImage("/bird.gif"));
		bird.setBounds(170, -10, 400, 300);
		panel2.add(bird);
		bird.setVisible(false);
		
		bird2 = new JLabel(formatImage("/bird.gif"));
		bird2.setBounds(-20, 190, 400, 300);
		panel2.add(bird2);
		bird2.setVisible(false);
		
		g_bird = new JLabel(formatImage("/bird_glow.png"));
		g_bird.setBounds(170, -10, 400, 300);
		panel2.add(g_bird);
		g_bird.setVisible(false);
		
		g_bird2 = new JLabel(formatImage("/bird_glow.png"));
		g_bird2.setBounds(-20, 190, 400, 300);
		panel2.add(g_bird2);
		g_bird2.setVisible(false);
		
		title = new JLabel(formatImage("/title2.png"));
		title.setBounds(0, 0, 700,500);
		panel2.add(title);
		
		bg = new JLabel(formatImage("/bg2.0.jpg", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel2.add(bg);
	}

	public void lesson1() {
		
		timer.setActionCommand("lesson1_timer");
		timer.start();
		if (seconds==0) {
			panel1.setVisible(true);
		} if (seconds==2) {
			title.setVisible(false);
			clip1.start();
			g_fishtank.setVisible(true);
			fishtank.setVisible(true);
			fishtank2.setVisible(true);
			fish.setVisible(true);
			fish2.setVisible(true);
		} if (seconds==5) {
			g_fishtank.setVisible(false);
			g_fishtank2.setVisible(true);
		} if(seconds==7) {
			g_fishtank2.setVisible(false);
		} if(seconds==8) {
			sent1.setVisible(true);
		} if(seconds==12) {
			sent1.setVisible(false);
			sent2.setVisible(true);
		} if(seconds==14.5) {
			sent2.setVisible(false);
			g_fishtank.setVisible(true);
			equation1.setVisible(true);
		} if(seconds==15) {
			g_fishtank2.setVisible(true);
		} if(seconds==16) {
			g_fishtank.setVisible(false);
			g_fishtank2.setVisible(false);
		} if(seconds==19.5) {
			g_fishtank.setVisible(true);
			g_fishtank2.setVisible(true);
		} if (seconds==21){
			next_Btn.setVisible(true);
			repeat_Btn.setVisible(true);
			clip1.stop();
			timer.stop();
			seconds=0;
		}		
	}

	public void init_lesson1() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 700, 500);
		panel1.setLayout(null);
		contentPane.add(panel1);
		panel1.setVisible(false);
		
		clip1 = sound.playMusic("multiply(les1).wav");
		
		sent1 = new JLabel();
		sent1 = new JLabel(formatImage("/L1.png")); 
		sent1.setBounds(41, 280 , 595, 150);
		sent1.setVisible(false);
		panel1.add(sent1);
		
		sent2 = new JLabel();
		sent2 = new JLabel(formatImage("/L2.png")); 
		sent2.setBounds(41, 280 , 595, 150);
		sent2.setVisible(false);
		panel1.add(sent2);
		
		equation1 = new JLabel();
		equation1 = new JLabel(formatImage("/2x2=4.png")); 
		equation1.setBounds(60, 330 , 479, 88);
		equation1.setVisible(false);
		panel1.add(equation1);
				
		panel1.add(music_Btn);
		panel1.add(mute_Btn);
		
		next_Btn.setActionCommand("lesson1_next");
		next_Btn.setVisible(false);
		panel1.add(next_Btn);
	
		repeat_Btn.setActionCommand("lesson1_repeat");
		repeat_Btn.setVisible(false);
		panel1.add(repeat_Btn);
		
		fish2 = new JLabel(formatImage("/fish.gif"));
		fish2.setBounds(380, 160, 120, 60);
		panel1.add(fish2);
		fish2.setVisible(false);
				
		fish = new JLabel(formatImage("/fish.gif"));
		fish.setBounds(115, 160, 120, 60);
		panel1.add(fish);
		fish.setVisible(false);
		
		fishtank2 = new JLabel(formatImage("/fishtank.png"));
		fishtank2.setBounds(287, 18, 300, 300);
		panel1.add(fishtank2);
		fishtank2.setVisible(false);
		
		fishtank = new JLabel(formatImage("/fishtank.png"));
		fishtank.setBounds(31, 18, 300, 300);
		panel1.add(fishtank);
		fishtank.setVisible(false);
		
		g_fishtank2 = new JLabel(formatImage("/fishtank1.png"));
		g_fishtank2.setBounds(287, 18, 300, 300);
		panel1.add(g_fishtank2); 
		g_fishtank2.setVisible(false);
		
		g_fishtank = new JLabel(formatImage("/fishtank1.png"));
		g_fishtank.setBounds(31, 18, 300, 300);
		panel1.add(g_fishtank);
		g_fishtank.setVisible(false);
		
		title = new JLabel(formatImage("/title1.png"));
		title.setBounds(0, 0, 700,500);
		panel1.add(title);
		
		bg = new JLabel(formatImage("/bg1.0.jpg", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel1.add(bg);
	}

	public void multiply_page() {
		
		panel = new JPanel();
		panel.setBounds(0, 0, 700, 500);
		panel.setLayout(null);
		panel.setVisible(true);
		
		StartBtn = new JButton(formatImage("/startbtn.png"));
		StartBtn = formatButton(StartBtn, 231, 225, 190, 79); 
		StartBtn.addActionListener(this);
		panel.add(StartBtn);
			
		bg = new JLabel(formatImage("/bg1.jpg"));
		bg.setBounds(0, 0, 700, 500);
		panel.add(bg);

		contentPane.add(panel);
	}
	
	public ImageIcon formatImage(String imageName, int width, int height) {
		ImageIcon image = new ImageIcon(getClass().getResource("/multiply"+imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
	}
	
	public ImageIcon formatImage(String imageName) {
		ImageIcon image = new ImageIcon(getClass().getResource("/multiply"+imageName));
		return image;
	}

	public JButton formatButton(JButton button, int x, int y, int x1, int y1) {
		button.setBackground(Color.WHITE); 
		button.setBorderPainted(false);
		button.setBounds(x,y,x1,y1);
		button.setContentAreaFilled(false);
		return button;
	}
}
