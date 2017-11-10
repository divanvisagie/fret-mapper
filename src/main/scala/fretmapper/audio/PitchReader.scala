package fretmapper.audio

import be.tarsos.dsp.{AudioDispatcher, AudioEvent}
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm
import be.tarsos.dsp.pitch.{PitchDetectionHandler, PitchDetectionResult, PitchProcessor}


class PitchReader {

  def readPitch(): Unit = {
    val handler = new PitchDetectionHandler {
      override def handlePitch(pitchDetectionResult: PitchDetectionResult, audioEvent: AudioEvent): Unit =
        println(audioEvent.getTimeStamp() + " " + pitchDetectionResult.getPitch())
    }

    val adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0)
    adp.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, 44100, 2048, handler))
  }

}
