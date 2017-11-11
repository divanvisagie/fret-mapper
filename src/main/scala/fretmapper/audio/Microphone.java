package fretmapper.audio;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author Sammons
 */
public class Microphone {


    private AudioFormat soundFormat;
    private AudioFileFormat.Type fileFormatType;
    private TargetDataLine targetInputLine;


    public Microphone(/*want to put options here that will configure audio format*/)  {
        fileFormatType = AudioFileFormat.Type.WAVE;
        soundFormat = getAudioFormat();
        targetInputLine = getTargetLine(soundFormat);
    }

    //function that determines how the audio will be recorded
    //needs to have a settings page that is a GUIWISH
    private AudioFormat getAudioFormat(){
        float sampleRate = 8000.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = true;
        //true,false
        return new AudioFormat(sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    //function that gets an available line in
    private TargetDataLine getTargetLine(AudioFormat soundFormat) {
        try {
            Mixer.Info infoo[] = AudioSystem.getMixerInfo();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, soundFormat);
            Line.Info infol[] = AudioSystem.getTargetLineInfo(info);
            if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                return (TargetDataLine) AudioSystem.getLine(Port.Info.MICROPHONE);
            } else if (AudioSystem.isLineSupported(Port.Info.LINE_IN)) {
                return (TargetDataLine) AudioSystem.getLine(Port.Info.LINE_IN);
            } else {
                return (TargetDataLine) AudioSystem.getLine(infol[0]);
            }

        } catch (LineUnavailableException ex) {
            Logger.getLogger(Microphone.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    //begins recording
//    public void Open() {
//        try {
//            targetInputLine.open(soundFormat);
//            targetInputLine.start();
//            //write stream, note that this occurs in a new thread
//            WriteSound soundWriter = new WriteSound(new AudioInputStream(targetInputLine), fileFormatType);
//            new Thread(soundWriter).start();
//        } catch (LineUnavailableException ex) {
//            Logger.getLogger(Microphone.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    //ends recording
    public void Close() {
        targetInputLine.stop();
        targetInputLine.close();

    }

}
