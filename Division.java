import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Division extends JFrame implements ActionListener {

	private JFrame contentPane;
	private playAudio sound;
	private Clip clip, clip1;
	private JButton music_Btn, mute_Btn, next_Btn, repeat_Btn, menu_Btn;
	private JLabel bg, title;
	/* division page */
	private JButton StartBtn;
	private JPanel panel;
	/* panel1 */
	private JPanel panel1;
	private JLabel carrot, d_carrot, kids, g_carrot, e1, e1a, arrow;
	/* panel2 */
	private JPanel panel2;
	private JLabel books, g_books, officer;
	/* panel3 */
	private JPanel panel3;
	private JLabel output, number1, number2, number3, undefined, devide0, ways;
	/* panel4 */
	private JPanel panel4;
	private Clip clip4, clip5;
	private JLabel title_quiz, red_earth1, red_earth2, red_earth3, green_earth;
	private JButton q1a1, q1a2, q1a3, q1a4;
	/* panel5 */
	private JPanel panel5;
	private JButton q2a1, q2a2, q2a3, q2a4;
	/* panel6 */
	private JPanel panel6;
	private JButton q3a1, q3a2, q3a3, q3a4;
	private int correct_ans;

	private double seconds;
	public Timer timer = new Timer(500, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds=seconds+0.5;
			if(e.getActionCommand() == "lesson1_timer") {
				lesson1();
			} else if (e.getActionCommand() == "lesson2_timer") {
				lesson2();
			} else if (e.getActionCommand() == "lesson3_timer") {
				lesson3();
			} else if (e.getActionCommand() == "quiz1_timer") {
				quiz1_exe();
			} else if (e.getActionCommand() == "quiz2_timer") {
				quiz2_exe();
			} else if (e.getActionCommand() == "quiz3_timer") {
				quiz3_exe();
			} 
		}
	});
	
	public Division() {
		contentPane = new JFrame("Division!");
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
		clip = sound.playMusic("division(bgm).wav"); 
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
		} else if(e.getSource() == menu_Btn) {
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
		} else if (e.getActionCommand()== "lesson3_next"){
			panel3.setVisible(false);
			quiz1();
			quiz1_exe();
		} else if (e.getActionCommand()== "lesson3_repeat") {
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
			r.saveResult(4, correct_ans);
			menu_Btn.setVisible(true);
			}
			red_earth1.setVisible(true);
			red_earth2.setVisible(true);
			red_earth3.setVisible(true);
			green_earth.setVisible(true);	
		} 	
	}
	
	private void quiz3_exe() {
		timer.setActionCommand("quiz3_timer");
		timer.start();
		if(seconds==3) {
			panel5.setVisible(false);
			quiz3();
			panel6.setVisible(true);
		} 
	}

	private void quiz3() {
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
		
		title_quiz = new JLabel(formatImage("/q3.png"));
		title_quiz.setBounds(35, 25, 440, 166);
		panel6.add(title_quiz);
		
		q3a1 = new JButton(formatImage("/q3a.png", 130, 121));
		q3a1 = formatButton(q3a1, 40, 220, 130, 121); 
		q3a1.addActionListener(this);
		panel6.add(q3a1);
		
		q3a2 = new JButton(formatImage("/q3b.png", 130, 121));
		q3a2 = formatButton(q3a2, 200, 220, 130, 121); 
		q3a2.addActionListener(this);
		panel6.add(q3a2);
		
		q3a3 = new JButton(formatImage("/q3c.png", 130, 121));	
		q3a3 = formatButton(q3a3, 360, 220, 130, 121); 
		q3a3.addActionListener(this);
		panel6.add(q3a3);
		
		q3a4 = new JButton(formatImage("/q3d.png", 130, 121));	// correct answer
		q3a4 = formatButton(q3a4, 520, 220, 130, 121); 
		q3a4.addActionListener(this);
		panel6.add(q3a4);
		
		green_earth.setBounds(520, 220, 130, 121);
		green_earth.setVisible(false);
		panel6.add(green_earth);
		
		red_earth3.setBounds(360, 220, 130, 121);
		red_earth3.setVisible(false);
		panel6.add(red_earth3);
		
		red_earth1.setVisible(false);
		panel6.add(red_earth1);
		
		red_earth2.setVisible(false);
		panel6.add(red_earth2);
		
		panel6.add(bg);
	}
	
	private void quiz2_exe() {
		timer.setActionCommand("quiz2_timer");
		timer.start();
		if(seconds==3) {
			panel4.setVisible(false);
			quiz2();
			panel5.setVisible(true);
		} 
	}
	
	private void quiz2() {
		panel5 = new JPanel();
		panel5.setBounds(0, 0, 700, 500);
		panel5.setLayout(null);
		panel5.setVisible(false);
		contentPane.add(panel5);
		
		clip4 = sound.playMusic("correct_sound.wav"); 
		clip5 = sound.playMusic("wrong_sound.wav");
		
		panel5.add(music_Btn);
		
		panel5.add(mute_Btn);
		
		title_quiz = new JLabel(formatImage("/q2.png"));
		title_quiz.setBounds(35, 25, 440, 166);
		panel5.add(title_quiz);
		
		q2a1 = new JButton(formatImage("/q2a.png", 130, 121));
		q2a1 = formatButton(q2a1, 40, 220, 130, 121); 
		q2a1.addActionListener(this);
		panel5.add(q2a1);
		
		q2a2 = new JButton(formatImage("/q2b.png", 130, 121));
		q2a2 = formatButton(q2a2, 200, 220, 130, 121); 
		q2a2.addActionListener(this);
		panel5.add(q2a2);
		
		q2a3 = new JButton(formatImage("/q2c.png", 130, 121));	// correct answer
		q2a3 = formatButton(q2a3, 360, 220, 130, 121); 
		q2a3.addActionListener(this);
		panel5.add(q2a3);
		
		q2a4 = new JButton(formatImage("/q2d.png", 130, 121));
		q2a4 = formatButton(q2a4, 520, 220, 130, 121); 
		q2a4.addActionListener(this);
		panel5.add(q2a4);
		
		green_earth.setBounds(360, 220, 130, 121);
		green_earth.setVisible(false);
		panel5.add(green_earth);
		
		red_earth2.setBounds(40, 220, 130, 121);
		red_earth2.setVisible(false);
		panel5.add(red_earth2);
		
		red_earth1.setVisible(false);
		panel5.add(red_earth1);
		
		red_earth3.setVisible(false);
		panel5.add(red_earth3);
		
		panel5.add(bg);
	}

	private void quiz1_exe() {
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
	
	private void quiz1() {
		
		correct_ans=0;
		panel4 = new JPanel();
		panel4.setBounds(0, 0, 700, 500);
		panel4.setLayout(null);
		panel4.setVisible(false);
		contentPane.add(panel4);
			
		panel4.add(music_Btn);
		
		panel4.add(mute_Btn);
		
		clip4 = sound.playMusic("correct_sound.wav"); 
		clip5 = sound.playMusic("wrong_sound.wav");
		
		title = new JLabel(formatImage("/title(quiz).png"));
		title.setBounds(0, 0, 700,500);
		panel4.add(title);
		
		title_quiz = new JLabel(formatImage("/q1.png"));
		title_quiz.setBounds(20, 25, 600, 166);
		title_quiz.setVisible(false);
		panel4.add(title_quiz);
		
		q1a1 = new JButton(formatImage("/q1a.png", 130, 121));	// correct answer
		q1a1 = formatButton(q1a1, 40, 220,  130, 121); 
		q1a1.addActionListener(this);
		q1a1.setVisible(false);
		panel4.add(q1a1);
		
		q1a2 = new JButton(formatImage("/q1b.png", 130, 121));
		q1a2 = formatButton(q1a2, 200, 220, 130, 121); 
		q1a2.addActionListener(this);
		q1a2.setVisible(false);
		panel4.add(q1a2);
		
		q1a3 = new JButton(formatImage("/q1c.png", 130, 121));
		q1a3 = formatButton(q1a3, 360, 220, 130, 121); 
		q1a3.addActionListener(this);
		q1a3.setVisible(false);
		panel4.add(q1a3);
		
		q1a4 = new JButton(formatImage("/q1d.png", 130, 121));
		q1a4 = formatButton(q1a4, 520, 220, 130, 121); 
		q1a4.addActionListener(this);
		q1a4.setVisible(false);
		panel4.add(q1a4);
		
		green_earth = new JLabel(formatImage("/correct.png", 130, 121));
		green_earth.setBounds(40, 220,  130, 121);
		green_earth.setVisible(false);
		panel4.add(green_earth);
		
		red_earth1 = new JLabel(formatImage("/wrong.png", 130, 121));
		red_earth1.setBounds(200, 220, 130, 121);
		red_earth1.setVisible(false);
		panel4.add(red_earth1);
		
		red_earth2 = new JLabel(formatImage("/wrong.png", 130, 121));
		red_earth2.setBounds(360, 220, 130, 121);
		red_earth2.setVisible(false);
		panel4.add(red_earth2);
		
		red_earth3 = new JLabel(formatImage("/wrong.png", 130, 121));
		red_earth3.setBounds(520, 220, 130, 121);
		red_earth3.setVisible(false);
		panel4.add(red_earth3);
		
		bg = new JLabel(formatImage("/bg(quiz).jpg", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel4.add(bg);
	}

	private void lesson3() {
		
		timer.setActionCommand("lesson3_timer");
		timer.start();
		if (seconds==0) {
			panel3.setVisible(true);
		} if (seconds==2) {
			clip1.start();
			title.setVisible(false);
			ways.setVisible(true);
		} if (seconds==3.5) {
			ways.setVisible(true);
		} if (seconds==5.5) {
			ways.setVisible(false);
			number1.setVisible(true);
		} if(seconds==8) {
			number2.setVisible(true);
		} if(seconds==10) {
			number3.setVisible(true);
		} if(seconds==13) {
			output.setVisible(true);
		} if(seconds==15) {
			number1.setVisible(false);
			number2.setVisible(false);
			number3.setVisible(false);
			output.setVisible(false);	
		} if(seconds==15.5) {
			devide0.setVisible(true);
		} if (seconds==18) {
			undefined.setVisible(true);
		} if (seconds==19.5){
			next_Btn.setVisible(true);
			repeat_Btn.setVisible(true);
			clip1.stop();
			timer.stop();	
			seconds=0;
		}
	}

	private void init_lesson3() {
		panel3 = new JPanel();
		panel3.setBounds(0, 0, 700, 500);
		panel3.setLayout(null);
		panel3.setVisible(false);
		contentPane.add(panel3);
		
		clip1 = sound.playMusic("division(les3).wav"); 
		
		panel3.add(music_Btn);
		panel3.add(mute_Btn);
		
		panel3.add(next_Btn);
		next_Btn.setActionCommand("lesson3_next");
		next_Btn.setVisible(false);
		
		panel3.add(repeat_Btn);
		repeat_Btn.setActionCommand("lesson3_repeat");
		repeat_Btn.setVisible(false);
		
		ways = new JLabel(formatImage("/ways.png"));
		ways.setBounds(0, 0, 700, 500);
		panel3.add(ways);
		ways.setVisible(false);
		
		number1 = new JLabel(formatImage("/number1.png"));
		number1.setBounds(0, 0, 700, 500);
		panel3.add(number1);
		number1.setVisible(false);
		
		number2 = new JLabel(formatImage("/number2.png"));
		number2.setBounds(0, 0, 700, 500);
		panel3.add(number2);
		number2.setVisible(false);
		
		number3 = new JLabel(formatImage("/number3.png"));
		number3.setBounds(0, 0, 700, 500);
		panel3.add(number3);
		number3.setVisible(false);
		
		output = new JLabel(formatImage("/output.png"));	// glow books
		output.setBounds(0, 0, 700, 500);
		panel3.add(output);
		output.setVisible(false);
		
		undefined = new JLabel(formatImage("/undefined.png"));	
		undefined.setBounds(0, 0, 700, 500);
		panel3.add(undefined);
		undefined.setVisible(false);
		
		devide0 = new JLabel(formatImage("/devide0.png"));	
		devide0.setBounds(0, 0, 700, 500);
		panel3.add(devide0);
		devide0.setVisible(false);
		
		title = new JLabel(formatImage("/title3.png"));
		title.setBounds(0, 0, 700,500);
		panel3.add(title);
		
		bg = new JLabel(formatImage("/bg3.0.png", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel3.add(bg);
		
	}

	private void lesson2() {
		
		timer.setActionCommand("lesson2_timer");
		timer.start();
		if (seconds==0) {
			panel2.setVisible(true);
		} if (seconds==2) {
			clip1.start();
			title.setVisible(false);
			books.setVisible(true);
		} if(seconds==3.5) {
			g_books.setVisible(true);
		} if(seconds==4.5) {
			officer.setVisible(true);		
		} if(seconds==7) {
			g_books.setVisible(false);
			arrow.setVisible(true);
		} if(seconds==10.5) {
			arrow.setVisible(false);
			e1.setVisible(true);
		} if (seconds==14.0) {
			e1a.setVisible(true);
			g_books.setVisible(true);
		} if (seconds==16){
			next_Btn.setVisible(true);
			repeat_Btn.setVisible(true);
			clip1.stop();
			timer.stop();	
			seconds=0;
		}
	}

	private void init_lesson2() {
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 700, 500);
		panel2.setLayout(null);
		panel2.setVisible(false);
		contentPane.add(panel2);
		
		clip1 = sound.playMusic("division(les2).wav"); 
		
		panel2.add(music_Btn);
		panel2.add(mute_Btn);
		
		panel2.add(next_Btn);
		next_Btn.setActionCommand("lesson2_next");
		next_Btn.setVisible(false);
		
		panel2.add(repeat_Btn);
		repeat_Btn.setActionCommand("lesson2_repeat");
		repeat_Btn.setVisible(false);
		
		e1 = new JLabel(formatImage("/12d4.png"));  	// equation2 (12/4)
		e1.setBounds(100, 52 , 294, 122);
		e1.setVisible(false);
		panel2.add(e1);
		
		e1a = new JLabel(formatImage("/=3.png")); 	// equation1a (=3)
		e1a.setBounds(380, 48 , 146, 122);
		e1a.setVisible(false);
		panel2.add(e1a);
		
		books = new JLabel(formatImage("/books.png"));
		books.setBounds(-70, 40, 200, 432);
		panel2.add(books);
		books.setVisible(false);
		
		g_books = new JLabel(formatImage("/books(glow).png"));	// glow books
		g_books.setBounds(-70, 40, 200, 432);
		panel2.add(g_books);
		g_books.setVisible(false);
		
		officer = new JLabel(formatImage("/officer.png"));	
		officer.setBounds(165, 200, 500, 265);
		panel2.add(officer);
		officer.setVisible(false);
		
		arrow.setBounds(258, 2, 190, 179);
		panel2.add(arrow);
		arrow.setVisible(false);
		
		title = new JLabel(formatImage("/title2.png"));
		title.setBounds(0, 0, 700,500);
		panel2.add(title);
		
		bg = new JLabel(formatImage("/bg2.0.png", 700,500));
		bg.setBounds(0, 0, 700, 500);
		panel2.add(bg);
	}
	
	private void lesson1() {

		timer.setActionCommand("lesson1_timer");
		timer.start();
		if (seconds==0) {
			panel1.setVisible(true);
		} if (seconds==2) {
			title.setVisible(false);
			clip1.start();
			carrot.setVisible(true);
		} if(seconds ==2.5) {
			g_carrot.setVisible(true);
		} if (seconds==3.5) {
			kids.setVisible(true);
		} if (seconds==5) {
			g_carrot.setVisible(false);
		} if(seconds==7) {
			arrow.setVisible(true);
		} if(seconds==12) {
			arrow.setVisible(false);
			g_carrot.setVisible(true);
			e1.setVisible(true);
		} if(seconds==15) {
			g_carrot.setVisible(false);
		} if(seconds==17) {
			e1a.setVisible(true);
			d_carrot.setVisible(true);
		} if (seconds==19){
			next_Btn.setVisible(true);
			repeat_Btn.setVisible(true);
			clip1.stop();
			timer.stop();
			seconds=0;
		}		
	}
	
	private void init_lesson1() {
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 700, 500);
		panel1.setLayout(null);
		contentPane.add(panel1);
		panel1.setVisible(false);
		
		clip1 = sound.playMusic("division(les1).wav");
		
		e1 = new JLabel(formatImage("/3d3.png"));  	// equation1 (3/3)
		e1.setBounds(240, 52 , 183, 122);
		e1.setVisible(false);
		panel1.add(e1);
		
		e1a = new JLabel(formatImage("/=1.png")); 	// equation1a (=1)
		e1a.setBounds(408, 48 , 140, 122);
		e1a.setVisible(false);
		panel1.add(e1a);
		
		panel1.add(music_Btn);
		
		panel1.add(mute_Btn);
		
		next_Btn.setActionCommand("lesson1_next");
		panel1.add(next_Btn);
		next_Btn.setVisible(false);
		
		repeat_Btn.setActionCommand("lesson1_repeat");
		panel1.add(repeat_Btn);
		repeat_Btn.setVisible(false);
			
		carrot = new JLabel(formatImage("/carrot.gif"));	
		carrot.setBounds(31, 45, 200, 200);
		panel1.add(carrot);
		carrot.setVisible(false);
		
		g_carrot = new JLabel(formatImage("/carrot(glow).png"));	// glow carrot
		g_carrot.setBounds(31, 45, 200, 200);
		panel1.add(g_carrot);
		g_carrot.setVisible(false);
				
		d_carrot = new JLabel(formatImage("/carrot.png"));	// divided carrot
		d_carrot.setBounds(70, 240, 440, 183);
		panel1.add(d_carrot);
		d_carrot.setVisible(false);
		
		arrow = new JLabel(formatImage("/arrow.png"));	// divided carrot
		arrow.setBounds(269, 55, 190, 179);
		panel1.add(arrow);
		arrow.setVisible(false);
		
		kids = new JLabel(formatImage("/kid.png"));
		kids.setBounds(70, 240, 440, 183);
		panel1.add(kids);
		kids.setVisible(false);
		
		title = new JLabel(formatImage("/title1.png"));
		title.setBounds(0, 0, 700,500);
		panel1.add(title);
		
		bg = new JLabel(formatImage("/bg1.0.jpg", 700, 500));
		bg.setBounds(0, 0, 700, 500);
		panel1.add(bg);
	}

	public void division_page() {
		
		panel = new JPanel();
		panel.setBounds(0, 0, 700, 500);
		panel.setLayout(null);
		panel.setVisible(true);
		
		StartBtn = new JButton(formatImage("/startbtn.png"));
		StartBtn = formatButton(StartBtn, 234, 310, 230,89); 
		StartBtn.addActionListener(this);
		panel.add(StartBtn);
			
		bg = new JLabel(formatImage("/bg.jpg", 700, 480));
		bg.setBounds(0, 0, 700, 480);
		panel.add(bg);

		contentPane.add(panel);
	}

	public ImageIcon formatImage(String imageName, int width, int height) {
		ImageIcon image = new ImageIcon(getClass().getResource("/division"+imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
	}
	
	public ImageIcon formatImage(String imageName ) {
		ImageIcon image = new ImageIcon(getClass().getResource("/division"+imageName));
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
