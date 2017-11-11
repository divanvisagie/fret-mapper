package fretmapper.audio;

import com.synthbot.jasiohost.AsioChannel;
import com.synthbot.jasiohost.AsioDriver;
import com.synthbot.jasiohost.AsioDriverListener;
import com.synthbot.jasiohost.AsioDriverState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AsioReader implements AsioDriverListener {

    private final Logger logger = LoggerFactory.getLogger(AsioReader.class);

    private AsioDriver asioDriver;
    private Set<AsioChannel> activeChannels;
    private int sampleIndex;
    private int bufferSize;
    private double sampleRate;

    public AsioReader() {
        activeChannels = new HashSet<AsioChannel>();
    }

    @Override
    public void sampleRateDidChange(double sampleRate) {
        logger.info("Sample rate changed to " + sampleRate);
    }

    @Override
    public void resetRequest() {
        /*
        * This thread will attempt to shut down the ASIO driver. However, it will
        * block on the AsioDriver object at least until the current method has returned.
        */
        new Thread(() -> {
            System.out.println("resetRequest() callback received. Returning driver to INITIALIZED state.");
            asioDriver.returnToState(AsioDriverState.INITIALIZED);
        }).start();
    }

    @Override
    public void resyncRequest() {
        logger.info("Received resync request");
    }

    @Override
    public void bufferSizeChanged(int bufferSize) {
        logger.info("Buffer size changed to " + bufferSize);
    }

    @Override
    public void latenciesChanged(int inputLatency, int outputLatency) {
        logger.info("latencies changed to " + inputLatency + ", " + outputLatency);
    }

    @Override
    public void bufferSwitch(long systemTime, long samplePosition, Set<AsioChannel> switchActiveChannels) {
//        for (AsioChannel channelInfo : channels) {
//            int value = channelInfo.getByteBuffer().getInt() / Integer.MAX_VALUE;
//            logger.info("v" + value);
//        }

        float[] outputLeftArray = new float[bufferSize];

        float[] outputRightArray = new float[bufferSize];

        for(AsioChannel activeChannel : switchActiveChannels)
        {
            if (activeChannel.isInput())
            {

                for (int i = 0; i < bufferSize; i++)
                {

                    outputLeftArray[i] += ((float) activeChannel.getByteBuffer().getInt()) / Integer.MAX_VALUE;

                    outputRightArray[i] += ((float) activeChannel.getByteBuffer().getInt()) / Integer.MAX_VALUE;

                    logger.info("test"+activeChannel.getChannelName() +outputLeftArray.length);
                    logger.info("right" +activeChannel.getChannelName() + outputRightArray.length);


                }

            }
        }

    }

    private void setUpAndRun() {
        asioDriver.addAsioDriverListener(host);
        activeChannels.add(asioDriver.getChannelInput(0));
        activeChannels.add(asioDriver.getChannelInput(1));
        sampleIndex = 0;

        bufferSize = asioDriver.getBufferPreferredSize();
        sampleRate = asioDriver.getSampleRate();

        asioDriver.createBuffers(activeChannels);
        asioDriver.start();
    }

    final AsioDriverListener host = this;
    public void start(AsioDriver ad) {
        this.asioDriver = ad;
        setUpAndRun();
    }
}
