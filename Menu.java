import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer.*;
import javax.sound.sampled.Clip;
import java.io.File; 

public class Menu{
	private JFrame frame;
	private JPanel panel_menu, panel_show;
	private JLabel menu, menu_scene;
	private JButton btn_num, btn_add, btn_subtract, btn_multiply, btn_divide, btn_shape, btn_result;
	private Timer timer;
	private playAudio s = new playAudio();
	private double seconds;
	private Clip bgm = s.playMusic("menu_bgm.wav");

	public static void main(String[] args){
		Menu m = new Menu();
		m.start();
	}

	public Menu(){
		bgm.start();
		bgm.loop(bgm.LOOP_CONTINUOUSLY);
		frame = new JFrame("Children Math Learning System");
		panel_menu = new JPanel();
		panel_show = new JPanel();

		ImageIcon menu_gif = new ImageIcon(getClass().getResource("/menu_image/menu.gif"));
		menu = new JLabel(menu_gif);
		menu.setBounds(0, -15, 700, 500);

		menu_scene = new JLabel(formatImage("/menu_image/menu_scene.png", 700, 500));
		menu_scene.setBounds(0, -15, 700, 500);

		btn_num = new JButton();
		btn_num.setContentAreaFilled(false);
		btn_num.setBorderPainted(false);
		btn_num.setBounds(60, 30, 280, 80);
		btn_num.addActionListener(choice);

		btn_add = new JButton();
		btn_add.setContentAreaFilled(false);
		btn_add.setBorderPainted(false);
		btn_add.setBounds(60, 141, 280, 80);
		btn_add.addActionListener(choice);

		btn_subtract = new JButton();
		btn_subtract.setContentAreaFilled(false);
		btn_subtract.setBorderPainted(false);
		btn_subtract.setBounds(60, 252, 280, 80);
		btn_subtract.addActionListener(choice);

		btn_multiply = new JButton();
		btn_multiply.setContentAreaFilled(false);
		btn_multiply.setBorderPainted(false);
		btn_multiply.setBounds(361, 33, 280, 80);
		btn_multiply.addActionListener(choice);

		btn_divide = new JButton();
		btn_divide.setContentAreaFilled(false);
		btn_divide.setBorderPainted(false);
		btn_divide.setBounds(361, 142, 280, 80);
		btn_divide.addActionListener(choice);

		btn_shape = new JButton();
		btn_shape.setContentAreaFilled(false);
		btn_shape.setBorderPainted(false);
		btn_shape.setBounds(361, 251, 280, 80);
		btn_shape.addActionListener(choice);

		btn_result = new JButton();
		btn_result.setContentAreaFilled(false);
		btn_result.setBorderPainted(false);
		btn_result.setBounds(210, 359, 280, 80);
		btn_result.addActionListener(choice);

		frame.pack();
		frame.setSize(700, 500);			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//quit when X is clicked
		frame.setResizable(false);			
		frame.setLocationRelativeTo(null);	
	} //end of constructor

	public void start(){
		menu_scene.setVisible(false);
		panel_menu.add(menu);
		panel_menu.add(menu_scene);	
		panel_menu.setLayout(null);
		frame.add(panel_menu);
		File result_file = new File("result.txt");
		result_file.delete();

		seconds = 0;
		timer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				seconds = seconds + 0.5;
				if(seconds == 3.5){
					panel_menu.add(btn_num);
					panel_menu.add(btn_add);
					panel_menu.add(btn_subtract);
					panel_menu.add(btn_multiply);
					panel_menu.add(btn_divide);
					panel_menu.add(btn_shape);
					panel_menu.add(btn_result);
					menu.setVisible(false);
					menu_scene.setVisible(true);
					timer.stop();
				}
			}
		});
		timer.start();	
		frame.setVisible(true);
	} //end of start

	public void showMenu(){
		panel_show.add(menu_scene);
		panel_show.add(btn_num);
		panel_show.add(btn_add);
		panel_show.add(btn_subtract);
		panel_show.add(btn_multiply);
		panel_show.add(btn_divide);
		panel_show.add(btn_shape);
		panel_show.add(btn_result);
		panel_show.setLayout(null);
		frame.add(panel_show);
		frame.setVisible(true);
	} //end of showMenu

	public ImageIcon formatImage(String imageName, int width, int height ) {
    	ImageIcon image = new ImageIcon(getClass().getResource(imageName));
		Image getimage = image.getImage();
		Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizeimage);
		return image;
    }

    ActionListener choice = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			bgm.stop();
			s = null;
			if(e.getSource() == btn_num){
				num n = new num();
				n.mainPanel();
			} else if(e.getSource() == btn_add){
				Addition a = new Addition();
				a.homepage();
			} else if(e.getSource() == btn_subtract){
				Subtraction s = new Subtraction();
				s.home();
			} else if(e.getSource() == btn_multiply){
				Multiply m = new Multiply();
				m.multiply_page();
			} else if(e.getSource() == btn_divide){
				Division d = new Division();
				d.division_page();
			} else if(e.getSource() == btn_shape){
				Shape sh = new Shape();
				sh.mainpage();
			}  else if(e.getSource() == btn_result){
				Result r = new Result();
				r.showResult();
			}
			frame.setVisible(false);
		}
	};
} //end of class Menu