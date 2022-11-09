import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;    //for FileWriter
import java.util.*;  //for scanner

class Result{
    private JFrame frame;
    private JPanel panel_result;
    private JButton btn_menu;
    private JLabel menu, result_bg;
    private JLabel[] quiz_mark = new JLabel[6];
    private String[] quiz = {"-", "-", "-", "-", "-", "-"};

    /*topics are represented by index 0-5 as follows:
     (0) Numbers        (3) Multiplication
     (1) Addition       (4) Division
     (2) Subtraction    (5) Shapes */

    public Result(){
        frame = new JFrame("Quiz Results");     
        panel_result = new JPanel();

        readFromFile();

        for(int i=0; i<6; i++){
            quiz_mark[i] = new JLabel();
            quiz_mark[i].setForeground(Color.RED);
            quiz_mark[i].setHorizontalAlignment(SwingConstants.LEFT);
            quiz_mark[i].setFont(new Font("Arial Black", Font.PLAIN, 36));
            quiz_mark[i].setText(quiz[i]);
        }
       
        menu = new JLabel(formatImage("/home_icon.png", 65, 65)); 
        menu.setBounds(600, 15, 65, 65);
        menu.setVisible(false);
        panel_result.add(menu);
        btn_menu = new JButton();
        btn_menu.setContentAreaFilled(false);
        btn_menu.setBorderPainted(false);
        btn_menu.setBounds(600, 15, 65, 65);
        btn_menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Menu m = new Menu();
                m.showMenu();
                frame.setVisible(false);
            }
        });

        frame.pack();
        frame.setSize(700, 500);            //set size of frame, setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //quit when X is clicked
        frame.setResizable(false);          //do not allow user to resize window
        frame.setLocationRelativeTo(null);  //center the window on the screen           
    } //end of constructor

    public void saveResult(int quizNum, int r){
        String result = String.valueOf(r); 
        quiz[quizNum] = result;
        saveToFile();
    } //end of saveResult

    public void saveToFile(){
        try{ //write to file
            FileWriter fileWriter = new FileWriter("result.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int i=0; i<6; i++ ){
                 printWriter.println(quiz[i]);
            }
            printWriter.close();
        }
        catch (IOException ioe) {
            System.out.println("Error writing to file");
        }
    } //end of saveToFile

    public void readFromFile(){
        try{ //read from file
            Scanner input = new Scanner(new File("result.txt"));
            while (input.hasNextLine()){
                for(int i=0; i<6; i++ ){
                    quiz[i] = input.nextLine();
                }
            }
            input.close();
        } 
        catch (FileNotFoundException e) {}
    } //end of readFromFile

    public void showResult(){
        menu.setVisible(true);
        panel_result.add(btn_menu);

        quiz_mark[0].setBounds(528, 60, 60, 100);
        quiz_mark[1].setBounds(528, 124, 60, 100);
        quiz_mark[2].setBounds(528, 185, 60, 100);
        quiz_mark[3].setBounds(528, 246, 60, 100);
        quiz_mark[4].setBounds(528, 310, 60, 100);
        quiz_mark[5].setBounds(528, 372, 60, 100);

        for(int i=0; i<6; i++){
            panel_result.add(quiz_mark[i]);
        }

        ImageIcon result = new ImageIcon(getClass().getResource("/quiz_result.png"));
        result_bg = new JLabel(result);
        result_bg.setBounds(0, -15, 700, 500);
        
        panel_result.add(result_bg);
        panel_result.setLayout(null);
        frame.add(panel_result);
        frame.setVisible(true);
    } //end of showResult

    public ImageIcon formatImage(String imageName, int width, int height ) {
        ImageIcon image = new ImageIcon(getClass().getResource(imageName));
        Image getimage = image.getImage();
        Image resizeimage = getimage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(resizeimage);
        return image;
    } //end of formatImage
} //end of class