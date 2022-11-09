import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.tools.Tool;
import javax.swing.*;

public class playAudio {
	Clip playMusic(String musicLocation){
		try{
			File musicPath = new File(musicLocation);
			if(musicPath.exists()){
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				return clip;	
			}
			else{
				System.out.println("Can't find file");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	} //end of playMusic
} //end of class
