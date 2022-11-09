import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer.*;
import javax.sound.sampled.Clip;

public class Addition{
	private JFrame frame;
	private JPanel panel_home, panel_lesson1, panel_lesson2, panel_lesson3, panel_quiz;
	private JLabel home_title, home_bg;
	private JLabel text, text2, text3;
	private JLabel lesson1, lesson1_scene, lesson2, lesson2_2, lesson2_3, lesson2_scene1, lesson2_scene2, lesson2_scene3, lesson3, lesson3_scene;
	private JLabel quiz, quiz_bg, end, quiz1, quiz2, quiz3, quiz1_answer, quiz2_answer, quiz3_answer;
	private JLabel next, menu, repeat, music, mute; 	//image for buttons
	private JButton btn_start, btn_music, btn_next, btn_menu, btn_repeat;
	private int correct_ans;
	private JButton btn_correct, btn_wrong1, btn_wrong2, btn_wrong3;
	private playAudio s = new playAudio();
	private Clip lesson1_audio, lesson2_audio, lesson3_audio, quiz_audio;
	private double seconds;
	private boolean music_on;
	private Timer timer = new Timer(500, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			seconds = seconds + 0.5;
			if(e.getActionCommand() == "lesson1_timer"){
				lesson1();
			} else if(e.getActionCommand() == "lesson2_timer"){
				lesson2();
			} else if(e.getActionCommand() == "lesson3_timer"){
				lesson3();
			} else if(e.getActionCommand() == "quiz_timer"){
				quiz();
			}	
		}
	});
	private Clip bgm = s.playMusic("addition_bgm.wav");

	public Addition(){ //constructor
		bgm.start();
		bgm.loop(bgm.LOOP_CONTINUOUSLY);

		frame = new JFrame("Let's Learn Addition!");
		panel_home = new JPanel();
		panel_lesson1 = new JPanel();
		panel_lesson2 = new JPanel();
		panel_lesson3 = new JPanel();
		panel_quiz = new JPanel();

		correct_ans = 0;

		text = new JLabel();
		text.setForeground(Color.BLACK);
		text.setHorizontalAlignment(SwingConstants.LEFT);
		text.setFont(new Font("Arial", Font.BOLD, 25));

		//btn_next
		next = new JLabel(formatImage("/next.png", 65, 65));
		next.setBounds(600, 105, 65, 65);
		next.setVisible(false);
		btn_next = new JButton();
		btn_next.setContentAreaFilled(false);
		btn_next.setBorderPainted(false);
		btn_next.setBounds(600, 105, 65, 65);
		btn_next.addActionListener(act);

		//btn_repeat
		repeat = new JLabel(formatImage("/repeat.png", 65, 65));
		repeat.setBounds(600, 185, 65, 65);
		repeat.setVisible(false);
		btn_repeat = new JButton();
		btn_repeat.setContentAreaFilled(false);
		btn_repeat.setBorderPainted(false);
		btn_repeat.setBounds(600, 185, 65, 65);
		btn_repeat.addActionListener(act);

		//btn_music
		mute = new JLabel(formatImage("/mute.png", 65, 65));
		mute.setBounds(600, 25, 65, 65);
		mute.setVisible(false);
		music = new JLabel(formatImage("/music.png", 65, 65));
		music.setBounds(600, 25, 65, 65);
		
		music_on = true;
		btn_music = new JButton();
		btn_music.setContentAreaFilled(false);
		btn_music.setBorderPainted(false);
		btn_music.setBounds(600, 25, 65, 65);
		btn_music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(music_on == true){
					mute.setVisible(true);
					music.setVisible(false);
					bgm.stop();
					music_on = false;
				} else if(music_on == false){
					mute.setVisible(false);
					music.setVisible(true);
					bgm.start();
					bgm.loop(bgm.LOOP_CONTINUOUSLY);
					music_on = true;
				}
			}
		});

		frame.pack();
		frame.setSize(700, 500);			//set size of frame, setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//quit when X is clicked
		frame.setResizable(false);			//do not allow user to resize window
		frame.setLocationRelativeTo(null);	//center the window on the screen   		
		frame.setVisible(true);				//show the frame
	} //end of constuctor

	public void homepage(){ 
		home_title = new JLabel(formatImage("/addition_image/addition.png", 500, 100));
		home_title.setBounds(100, 120, 500, 100);
		panel_home.add(home_title);

		btn_start = new JButton("start");
		btn_start.setBackground(new Color(255, 255, 153));
		btn_start.setForeground(new Color(102, 51, 51));
		btn_start.setFont(new Font("Fugaz One", Font.PLAIN, 35));
		btn_start.setBounds(273, 262, 154, 57);
		btn_start.setVisible(true);
		btn_start.addActionListener(act);
		panel_home.add(btn_start);

		home_bg = new JLabel(formatImage("/addition_image/bg.jpg", 700, 500));
		home_bg.setBounds(0, -18, 700, 500);
		panel_home.add(home_bg);

		panel_home.setLayout(null);		//position components absolutely, using xy-coordinates
		frame.add(panel_home);
	} //end of homepage

	public void init_lesson1() {
		text.setText("Annie has 2 apples...");
		text.setBounds(70, 15, 430, 110);
		text.setVisible(false);
		panel_lesson1.add(text);

		//btn_next
		next.setVisible(false);
		panel_lesson1.add(next);
		btn_next.setActionCommand("lesson1_next");

		//btn_repeat
		repeat.setVisible(false);
		panel_lesson1.add(repeat);
		btn_repeat.setActionCommand("lesson1_repeat");

		//btn_music
		panel_lesson1.add(mute);
		panel_lesson1.add(music);
		panel_lesson1.add(btn_music);

		ImageIcon lesson1_gif = new ImageIcon(getClass().getResource("/addition_image/lesson1.gif"));
		lesson1 = new JLabel(lesson1_gif);
		lesson1.setBounds(0, -10, 700, 500);
		lesson1.setVisible(false);
		panel_lesson1.add(lesson1);

		ImageIcon lesson1_image2 = new ImageIcon(getClass().getResource("/addition_image/lesson1_scene.png"));
		lesson1_scene = new JLabel(lesson1_image2);
		lesson1_scene.setBounds(0, -10, 700, 500);
		lesson1_scene.setVisible(false);
		panel_lesson1.add(lesson1_scene);

		panel_lesson1.setLayout(null);			
		frame.add(panel_lesson1);
	} //end of init_lesson1

	public void lesson1(){
		timer.setActionCommand("lesson1_timer");
		timer.start();
		if(seconds == 0){ lesson1.setVisible(true); }
		else if(seconds == 4){
			lesson1_audio = s.playMusic("lesson1_2apples.wav");
			lesson1_audio.start();
			text.setVisible(true);
		} else if (seconds == 7){
			lesson1_audio = s.playMusic("lesson1_3apples.wav");
			lesson1_audio.start();
			text.setText("Henry gave Annie 3 apples...");		
		} else if (seconds == 11){
			text.setVisible(false);
			lesson1_audio = s.playMusic("lesson1_2+3apples.wav");
			lesson1_audio.start();
		} else if (seconds == 17.5){
			lesson1_audio = s.playMusic("lesson1_5apples.wav");
			lesson1_audio.start();
		} else if (seconds == 19){
			text.setText("Now Annie has 5 apples.");
			text.setBounds(190, 65, 430, 110);
			text.setVisible(true);
		} else if (seconds == 21){
			lesson1.setVisible(false);
			lesson1_scene.setVisible(true);
			next.setVisible(true);
			repeat.setVisible(true);
			panel_lesson1.add(btn_next);
			panel_lesson1.add(btn_repeat);
			timer.stop();
		}
	} //end of lesson1

	public void init_lesson2() {
		//btn_next
		next.setVisible(false);
		panel_lesson2.add(next);
		btn_next.setActionCommand("lesson2_next");

		//btn_repeat
		repeat.setVisible(false);
		panel_lesson2.add(repeat);
		btn_repeat.setActionCommand("lesson2_repeat");

		//btn_music
		panel_lesson2.add(mute);
		panel_lesson2.add(music);
		panel_lesson2.add(btn_music);

		ImageIcon lesson2_gif1 = new ImageIcon(getClass().getResource("/addition_image/lesson2_1.gif"));
		lesson2 = new JLabel(lesson2_gif1);
		lesson2.setBounds(0, -15, 700, 500);
		lesson2.setVisible(false);
		panel_lesson2.add(lesson2);

		ImageIcon lesson2_gif2 = new ImageIcon(getClass().getResource("/addition_image/lesson2_2.gif"));
		lesson2_2 = new JLabel(lesson2_gif2);
		lesson2_2.setBounds(0, -15, 700, 500);
		lesson2_2.setVisible(false);
		panel_lesson2.add(lesson2_2);

		ImageIcon lesson2_gif3 = new ImageIcon(getClass().getResource("/addition_image/lesson2_3.gif"));
		lesson2_3 = new JLabel(lesson2_gif3);
		lesson2_3.setBounds(0, -15, 700, 500);
		lesson2_3.setVisible(false);
		panel_lesson2.add(lesson2_3);

		ImageIcon lesson2_image = new ImageIcon(getClass().getResource("/addition_image/lesson2_scene1.png"));
		lesson2_scene1 = new JLabel(lesson2_image);
		lesson2_scene1.setBounds(0, -15, 700, 500);
		lesson2_scene1.setVisible(false);
		panel_lesson2.add(lesson2_scene1);

		ImageIcon lesson2_image2 = new ImageIcon(getClass().getResource("/addition_image/lesson2_scene2.png"));
		lesson2_scene2 = new JLabel(lesson2_image2);
		lesson2_scene2.setBounds(0, -15, 700, 500);
		lesson2_scene2.setVisible(false);
		panel_lesson2.add(lesson2_scene2);

		ImageIcon lesson2_image3 = new ImageIcon(getClass().getResource("/addition_image/lesson2_scene3.png"));
		lesson2_scene3 = new JLabel(lesson2_image3);
		lesson2_scene3.setBounds(0, -15, 700, 500);
		lesson2_scene3.setVisible(false);
		panel_lesson2.add(lesson2_scene3);


		panel_lesson2.setLayout(null);			
		frame.add(panel_lesson2);
	} //end of init_lesson2

	public void lesson2(){
		timer.setActionCommand("lesson2_timer");
		timer.start();
		if (seconds == 0){ 
			lesson2.setVisible(true); 
		} else if(seconds == 4 || seconds == 12){ //paper clips
			lesson2_audio = s.playMusic("lesson2_ask_paperclips.wav");
			lesson2_audio.start();
		} else if (seconds == 8 || seconds == 16){
			lesson2_audio = s.playMusic("lesson2_2paperclips.wav");
			lesson2_audio.start();
		} else if (seconds == 20){
			lesson2_audio = s.playMusic("lesson2_2+2paperclips.wav");
			lesson2_audio.start();
		} else if(seconds == 28){
			lesson2_audio = s.playMusic("lesson2_4paperclips.wav");
			lesson2_audio.start();
		} else if(seconds == 29.5){
			lesson2.setVisible(false);
			lesson2_scene1.setVisible(true);
		} else if (seconds == 34 || seconds == 42.5){ //pencils
			lesson2_2.setVisible(true); 
			lesson2_audio = s.playMusic("lesson2_ask_pencils.wav");
			lesson2_audio.start();
		} else if (seconds == 39){
			lesson2_scene1.setVisible(false);
			lesson2_audio = s.playMusic("lesson2_5pencils.wav");
			lesson2_audio.start();
		} else if (seconds == 47){
			lesson2_audio = s.playMusic("lesson2_2pencils.wav");
			lesson2_audio.start();
		} else if (seconds == 51.5){
			lesson2_audio = s.playMusic("lesson2_5+2pencils.wav");
			lesson2_audio.start();
		} else if (seconds == 57.5){
			lesson2_audio = s.playMusic("lesson2_7pencils.wav");
			lesson2_audio.start();
			lesson2_2.setVisible(false);
			lesson2_scene2.setVisible(true);
		} else if (seconds == 63.5 || seconds == 72){ //scissors
			lesson2_3.setVisible(true); 
			lesson2_audio = s.playMusic("lesson2_ask_scissors.wav");
			lesson2_audio.start();
		} else if(seconds == 69){
			lesson2_audio = s.playMusic("lesson2_4scissors.wav");
			lesson2_audio.start();
			lesson2_scene2.setVisible(false);
		} else if(seconds == 77){
			lesson2_audio = s.playMusic("lesson2_5scissors.wav");
			lesson2_audio.start();
		} else if(seconds == 81){
			lesson2_audio = s.playMusic("lesson2_4+5scissors.wav");
			lesson2_audio.start();
		} else if(seconds == 87.5){
			lesson2_audio = s.playMusic("lesson2_9scissors.wav");
			lesson2_audio.start();
			lesson2_3.setVisible(false);
			lesson2_scene3.setVisible(true);
		} else if(seconds == 88){
			next.setVisible(true);
			panel_lesson2.add(btn_next);
			repeat.setVisible(true);
			panel_lesson2.add(btn_repeat);
			timer.stop();
		}
	} //end of lesson2

	public void init_lesson3() {
		//text
		text.setBounds(70, 15, 430, 110);
		text.setVisible(false);
		panel_lesson3.add(text);

		text2 = new JLabel();
		text2.setForeground(Color.BLACK);
		text2.setHorizontalAlignment(SwingConstants.LEFT);
		text2.setFont(new Font("Arial", Font.BOLD, 50));
		text2.setBounds(215, 15, 379, 110);
		text2.setVisible(false);
		panel_lesson3.add(text2);

		text3 = new JLabel();
		text3.setForeground(Color.BLACK);
		text3.setHorizontalAlignment(SwingConstants.LEFT);
		text3.setFont(new Font("Arial", Font.BOLD, 25));
		text3.setBounds(70, 50, 430, 110);
		text3.setVisible(false);
		panel_lesson3.add(text3);

		//btn_next
		next.setVisible(false);
		panel_lesson3.add(next);
		btn_next.setActionCommand("lesson3_next");

		//btn_repeat
		repeat.setVisible(false);
		panel_lesson3.add(repeat);
		btn_repeat.setActionCommand("lesson3_repeat");

		//btn_music
		panel_lesson3.add(mute);
		panel_lesson3.add(music);
		panel_lesson3.add(btn_music);

		ImageIcon lesson3_gif = new ImageIcon(getClass().getResource("/addition_image/lesson3.gif"));
		lesson3 = new JLabel(lesson3_gif);
		lesson3.setBounds(-2, -15, 700, 500);
		lesson3.setVisible(false);
		panel_lesson3.add(lesson3);

		ImageIcon lesson3_image = new ImageIcon(getClass().getResource("/addition_image/lesson3_scene.png"));
		lesson3_scene = new JLabel(lesson3_image);
		lesson3_scene.setBounds(-2, -15, 700, 500);
		lesson3_scene.setVisible(false);
		panel_lesson3.add(lesson3_scene);

		panel_lesson3.setLayout(null);
		frame.add(panel_lesson3);
	} //end of init_lesson3

	public void lesson3(){
		timer.setActionCommand("lesson3_timer");
		timer.start();
		if (seconds == 0){
			lesson3.setVisible(true); 
		}
		else if (seconds == 3){
			lesson3_audio = s.playMusic("lesson3_rm7.wav");
			lesson3_audio.start();
			text.setText("Annie has RM7...");
			text.setVisible(true);
		} else if (seconds == 6 ||seconds == 20){
			lesson3_audio = s.playMusic("lesson3_rm5.wav");
			lesson3_audio.start();
			text.setText("Her mother gave her RM5...");
			if(seconds == 20){
				text3.setVisible(false);
			}
		} else if (seconds == 9.5 || seconds == 33){
			lesson3_audio = s.playMusic("lesson3_rm3.wav");
			lesson3_audio.start();
			text.setText("Then, her father gave her RM3...");
			if(seconds == 33)
				text3.setVisible(false);
		} else if(seconds == 14){
			lesson3_audio = s.playMusic("lesson3_ask_rm.wav");
			lesson3_audio.start();
			text.setText("How much money does");
			text3.setText("Annie have now?");
			text3.setVisible(true);
		} else if (seconds == 23){
			lesson3_audio = s.playMusic("lesson3_rm7+5.wav");
			lesson3_audio.start();
			text.setVisible(false);
			text3.setVisible(false);
			text2.setVisible(true);
			text2.setText("7 + 5 = 12");
		} else if(seconds == 27){
			lesson3_audio = s.playMusic("lesson3_rm12.wav");
			lesson3_audio.start();
		} else if(seconds == 28.5){
			text.setVisible(true);
			text2.setVisible(false);
			text.setText("For now, Annie has 12 ringgit.");
		} else if(seconds == 37.5){
			lesson3_audio = s.playMusic("lesson3_rm12+3.wav");
			lesson3_audio.start();	
			text.setVisible(false);
			text3.setVisible(false);
			text2.setVisible(true);
			text2.setText("12 + 3 = 15");
		} else if (seconds == 42){
			lesson3_audio = s.playMusic("lesson3_rm15.wav");
			lesson3_audio.start();
		} else if (seconds == 43.5){
			text2.setText("7 + 5 + 3 = 15");
			text2.setBounds(120, 10, 379, 110);
			text3.setBounds(145, 55, 430, 110);
			text3.setText("Annie has 15 ringgit.");
			lesson3.setVisible(false);
			lesson3_scene.setVisible(true);
			text3.setVisible(true);
		} else if (seconds == 48){
			next.setVisible(true);
			panel_lesson3.add(btn_next);
			repeat.setVisible(true);
			panel_lesson3.add(btn_repeat);
			timer.stop();
		} 
	} //end of lesson3

	public void init_quiz() {
		quiz = new JLabel(formatImage("/addition_image/quiz.png", 500, 100));
		quiz.setBounds(100, 180, 500, 100);
		quiz.setVisible(false);
		panel_quiz.add(quiz);

		end = new JLabel(formatImage("/addition_image/the_end.png", 500, 100));
		end.setBounds(100, 180, 500, 100);
		end.setVisible(false);
		panel_quiz.add(end);

		//quizzes
		ImageIcon image = new ImageIcon(getClass().getResource("/addition_image/quiz1.png"));
		quiz1 = new JLabel(image);
		quiz1.setBounds(0, 0, 700, 500);
		quiz1.setVisible(false);
		panel_quiz.add(quiz1);

		image = new ImageIcon(getClass().getResource("/addition_image/quiz2.png"));
		quiz2= new JLabel(image);
		quiz2.setBounds(0, 0, 700, 500);
		quiz2.setVisible(false);
		panel_quiz.add(quiz2);

		image = new ImageIcon(getClass().getResource("/addition_image/quiz3.png"));
		quiz3 = new JLabel(image);
		quiz3.setBounds(0, 0, 700, 500);
		quiz3.setVisible(false);
		panel_quiz.add(quiz3);

		//answers
		image = new ImageIcon(getClass().getResource("/addition_image/quiz1_answer.png"));
		quiz1_answer = new JLabel(image);
		quiz1_answer.setBounds(0, 0, 700, 500);
		quiz1_answer.setVisible(false);
		panel_quiz.add(quiz1_answer);

		image = new ImageIcon(getClass().getResource("/addition_image/quiz2_answer.png"));
		quiz2_answer = new JLabel(image);
		quiz2_answer.setBounds(0, 0, 700, 500);
		quiz2_answer.setVisible(false);
		panel_quiz.add(quiz2_answer);

		image = new ImageIcon(getClass().getResource("/addition_image/quiz3_answer.png"));
		quiz3_answer = new JLabel(image);
		quiz3_answer.setBounds(0, 0, 700, 500);
		quiz3_answer.setVisible(false);
		panel_quiz.add(quiz3_answer);

		//option buttons
		btn_correct = new JButton();
		btn_correct.setContentAreaFilled(false);
		btn_correct.setBorderPainted(false);
		btn_correct.setBounds(380, 255, 110, 80);
		btn_correct.addActionListener(act);
		
		btn_wrong1 = new JButton();
		btn_wrong1.setContentAreaFilled(false);
		btn_wrong1.setBorderPainted(false);
		btn_wrong1.setBounds(217, 255, 110, 80);
		btn_wrong1.addActionListener(act);

		btn_wrong2 = new JButton();
		btn_wrong2.setContentAreaFilled(false);
		btn_wrong2.setBorderPainted(false);
		btn_wrong2.setBounds(51, 255, 110, 80);
		btn_wrong2.addActionListener(act);

		btn_wrong3 = new JButton();
		btn_wrong3.setContentAreaFilled(false);
		btn_wrong3.setBorderPainted(false);
		btn_wrong3.setBounds(540, 255, 110, 80);
		btn_wrong3.addActionListener(act);

		//btn_menu
		menu = new JLabel(formatImage("/home_icon.png", 65, 65)); 
		menu.setBounds(600, 105, 65, 65);
		menu.setVisible(false);
		panel_quiz.add(menu);
		btn_menu = new JButton();
		btn_menu.setContentAreaFilled(false);
		btn_menu.setBorderPainted(false);
		btn_menu.setBounds(600, 105, 65, 65);
		btn_menu.addActionListener(act);

		//btn_music
		panel_quiz.add(mute);
		panel_quiz.add(music);
		panel_quiz.add(btn_music);

		quiz_bg = new JLabel(formatImage("/addition_image/bg3.jpg", 700, 500));
		quiz_bg.setBounds(0, 0, 700, 500);
		panel_quiz.add(quiz_bg);
		panel_quiz.setLayout(null);
		frame.add(panel_quiz);
	} //end of init_quiz

	public void quiz(){
		timer.setActionCommand("quiz_timer");
		timer.start();
		if (seconds == 0.5){ quiz.setVisible(true); }
		else if(seconds == 3){ //quiz1
			btn_correct.setActionCommand("quiz1_correct");
			btn_wrong1.setActionCommand("quiz1_wrong1");
			btn_wrong2.setActionCommand("quiz1_wrong2");
			btn_wrong3.setActionCommand("quiz1_wrong3");
			panel_quiz.add(btn_correct);
			panel_quiz.add(btn_wrong1);
			panel_quiz.add(btn_wrong2);
			panel_quiz.add(btn_wrong3);
			quiz.setVisible(false);
			quiz1.setVisible(true);
			timer.stop();
		} else if (seconds == 6){ //quiz2
			panel_quiz.remove(quiz1_answer);
			quiz2.setVisible(true);
			btn_correct.setBounds(217, 255, 110, 80);
			btn_wrong1.setBounds(380, 255, 110, 80);
			btn_correct.setActionCommand("quiz2_correct");
			btn_wrong1.setActionCommand("quiz2_wrong1");
			btn_wrong2.setActionCommand("quiz2_wrong2");
			btn_wrong3.setActionCommand("quiz2_wrong3");
			panel_quiz.add(btn_correct);
			panel_quiz.add(btn_wrong1);
			panel_quiz.add(btn_wrong2);
			panel_quiz.add(btn_wrong3);
			timer.stop();
		} else if (seconds == 9) { //quiz3
			panel_quiz.remove(quiz2_answer);
			quiz3.setVisible(true);
			btn_wrong1.setBounds(217, 255, 110, 80);
			btn_correct.setBounds(380, 255, 110, 80);
			btn_correct.setActionCommand("quiz3_correct");
			btn_wrong1.setActionCommand("quiz3_wrong1");
			btn_wrong2.setActionCommand("quiz3_wrong2");
			btn_wrong3.setActionCommand("quiz3_wrong3");
			panel_quiz.add(btn_correct);
			panel_quiz.add(btn_wrong1);
			panel_quiz.add(btn_wrong2);
			panel_quiz.add(btn_wrong3);
			timer.stop();
		} else if (seconds == 12){
			timer.stop();
			menu.setVisible(true);
			panel_quiz.add(btn_menu);
			quiz3_answer.setVisible(false);
			end.setVisible(true);
		}
	} //end of quiz

    public ImageIcon formatImage(String imageName, int width, int height ) {
    	ImageIcon image = new ImageIcon(getClass().getResource(imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
    } //end of formatImage

	ActionListener act = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn_start){
				frame.remove(panel_home);
				seconds = 0;
				init_lesson1();
				lesson1();
			}else if(e.getSource() == btn_menu){
				Menu m = new Menu();
				m.showMenu();
				bgm.stop();
				s = null;
				frame.dispose();
			} else if(e.getActionCommand() == "lesson1_next"){
				frame.remove(panel_lesson1);
				seconds = 0;
				init_lesson2();
				lesson2();
			} else if(e.getActionCommand() == "lesson2_next"){
				frame.remove(panel_lesson2);
				seconds = 0;
				init_lesson3();
				lesson3();
			} else if(e.getActionCommand() == "lesson3_next"){
				frame.remove(panel_lesson3);
				seconds = 0;
				init_quiz();
				quiz();
			} else if(e.getActionCommand() == "lesson1_repeat"){
				panel_lesson1.removeAll();
				panel_lesson1.repaint();
				frame.remove(panel_lesson1);
				seconds = 0;
				init_lesson1();
				lesson1();
			} else if(e.getActionCommand() == "lesson2_repeat"){
				panel_lesson2.removeAll();
				panel_lesson2.repaint();
				frame.remove(panel_lesson2);
				seconds = 0;
				init_lesson2();
				lesson2();
			} else if(e.getActionCommand() == "lesson3_repeat"){
				panel_lesson3.removeAll();
				panel_lesson3.repaint();
				frame.remove(panel_lesson3);
				seconds = 0;
				init_lesson3();
				lesson3();
			} else if(e.getActionCommand() == "quiz1_correct" || e.getActionCommand() == "quiz1_wrong1" || e.getActionCommand() == "quiz1_wrong2" || e.getActionCommand() == "quiz1_wrong3"){
				if(e.getActionCommand() == "quiz1_correct"){
					quiz_audio = s.playMusic("correct_sound.wav");
					quiz_audio.start();
					correct_ans++;
				} else{
					quiz_audio = s.playMusic("wrong_sound.wav");
					quiz_audio.start();
				}
				quiz1.setVisible(false);
				quiz1_answer.setVisible(true);
				timer.start();
				panel_quiz.remove(btn_correct);
				panel_quiz.remove(btn_wrong1);
				panel_quiz.remove(btn_wrong2);
				panel_quiz.remove(btn_wrong3);
			} else if(e.getActionCommand() == "quiz2_correct" || e.getActionCommand() == "quiz2_wrong1" || e.getActionCommand() == "quiz2_wrong2" || e.getActionCommand() == "quiz2_wrong3"){
				if(e.getActionCommand() == "quiz2_correct"){
					quiz_audio = s.playMusic("correct_sound.wav");
					quiz_audio.start();
					correct_ans++;
				} else{
					quiz_audio = s.playMusic("wrong_sound.wav");
					quiz_audio.start();
				}
				quiz2.setVisible(false);
				quiz2_answer.setVisible(true);
				timer.start();
				panel_quiz.remove(btn_correct);
				panel_quiz.remove(btn_wrong1);
				panel_quiz.remove(btn_wrong2);
				panel_quiz.remove(btn_wrong3);
			} else if(e.getActionCommand() == "quiz3_correct" || e.getActionCommand() == "quiz3_wrong1" || e.getActionCommand() == "quiz3_wrong2" || e.getActionCommand() == "quiz3_wrong3"){
				if(e.getActionCommand() == "quiz3_correct"){
					quiz_audio = s.playMusic("correct_sound.wav");
					quiz_audio.start();
					correct_ans++;
				} else{
					quiz_audio = s.playMusic("wrong_sound.wav");
					quiz_audio.start();
				}
				quiz3.setVisible(false);
				quiz3_answer.setVisible(true);
				timer.start();
				panel_quiz.remove(btn_correct);
				panel_quiz.remove(btn_wrong1);
				panel_quiz.remove(btn_wrong2);
				panel_quiz.remove(btn_wrong3);
				Result r = new Result();
				r.saveResult(1, correct_ans);
			}
		}
	}; //end of ActionListener
} //end of class Addition