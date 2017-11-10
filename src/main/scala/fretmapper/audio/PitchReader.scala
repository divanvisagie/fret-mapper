package fretmapper.audio

import be.tarsos.dsp.{AudioDispatcher, AudioEvent}
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import be.tarsos.dsp.pitch.{PitchDetectionHandler, PitchDetectionResult, PitchProcessor}


class PitchReader {

  val sampleRate = 8000
  val audioBufferSize = 32
  val bufferOverlap = 0

  def readPitch(): Unit = {
    val handler = new PitchDetectionHandler {
      override def handlePitch(pitchDetectionResult: PitchDetectionResult, audioEvent: AudioEvent): Unit = {
        println(audioEvent.getTimeStamp + " " + pitchDetectionResult.getPitch)
      }
    }

    val adp = AudioDispatcherFactory.fromDefaultMicrophone(audioBufferSize, 0)

    adp.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, sampleRate, audioBufferSize, handler))
  }

}
