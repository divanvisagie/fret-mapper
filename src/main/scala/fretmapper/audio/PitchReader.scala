package fretmapper.audio

import java.util
import javax.sound.sampled._

import be.tarsos.dsp.{AudioDispatcher, AudioEvent}
import be.tarsos.dsp.io.jvm.{AudioDispatcherFactory, JVMAudioInputStream}
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import be.tarsos.dsp.pitch.{PitchDetectionHandler, PitchDetectionResult, PitchProcessor}
import com.synthbot.jasiohost.{AsioChannel, AsioDriver, AsioDriverListener}




class PitchReader {

  val sampleRate = 9600
  val audioBufferSize = 24
  val bufferOverlap = 0

  import javax.sound.sampled.AudioFormat
  import javax.sound.sampled.AudioSystem
  import javax.sound.sampled.DataLine
  import javax.sound.sampled.Line
  import javax.sound.sampled.LineUnavailableException
  import javax.sound.sampled.Mixer
  import javax.sound.sampled.Port
  import javax.sound.sampled.TargetDataLine

  private def getTargetLine(soundFormat: AudioFormat): TargetDataLine = {
    try {
      val infoo = AudioSystem.getMixerInfo
      val info = new DataLine.Info(classOf[TargetDataLine], soundFormat)
      val infol = AudioSystem.getTargetLineInfo(info)
      if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
        AudioSystem.getLine(Port.Info.MICROPHONE).asInstanceOf[TargetDataLine]
      }
      else if (AudioSystem.isLineSupported(Port.Info.LINE_IN)) {
        AudioSystem.getLine(Port.Info.LINE_IN).asInstanceOf[TargetDataLine]
      }
      else {
         AudioSystem.getLine(infol(0)).asInstanceOf[TargetDataLine]
      }
    } catch {
      case ex: LineUnavailableException =>
        println("nnooooooo" + ex)
//        Logger.getLogger(classOf[Nothing].getName).log(Level.SEVERE, null, ex)
    }
    null
  }


  def readPitch(): Unit = {

    val driverNameList = AsioDriver.getDriverNames

    driverNameList.toArray.foreach(println)
    val asioDriver = AsioDriver.getDriver(driverNameList.get(1))

    val asioReader = new AsioReader()
    asioReader.start(asioDriver)

//    val asioChannel = asioDriver.getChannelInput(1)

//    println(s"Reading on channel ${asioChannel.toString}")

//    val value = asioChannel.getByteBuffer.getInt()


//    val handler = new PitchDetectionHandler {
//      override def handlePitch(pitchDetectionResult: PitchDetectionResult, audioEvent: AudioEvent): Unit = {
//        println(audioEvent.getTimeStamp + " " + pitchDetectionResult.getPitch)
//      }
//    }
//    val format = new AudioFormat(audioBufferSize, 16, 2, true, true)
//    val dataLineInfo = new DataLine.Info(classOf[TargetDataLine],format)

//    AudioSystem.getLine(Port.Info.SPEAKER)
//    val format = new AudioFormat(96000.0f, 24, 1, true, true)
//    val microphone = AudioSystem.getTargetDataLine(format)
//      var mic = new Microphone

//    if(!AudioSystem.isLineSupported(dataLineInfo)) {
//      println(s"Error: audio input not supported, $dataLineInfo")
//
//      val mixerInfo = AudioSystem.getMixerInfo
//      println(mixerInfo..get)
//
//    }

    // Obtain and open the line.// Obtain and open the line.


//    val var4: TargetDataLine = AudioSystem.getTargetDataLine(var3)
//    var4.open(var3, sampleRate)
//    var4.start()
//    val var5: AudioInputStream = new AudioInputStream(var4)
//    val var6: JVMAudioInputStream = new JVMAudioInputStream(var5)
//    val adp = new AudioDispatcher(var6, sampleRate, bufferOverlap)



//    val adp = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate, audioBufferSize, 0)
//    adp.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, sampleRate, audioBufferSize, handler))
  }

}
