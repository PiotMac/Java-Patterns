package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {
    @Test
    public void testChainTwoDecorators() {
        // Create the builder
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
        //Create a spy social channel
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
        //Chain decorators
        List<String> forbiddenWords = new ArrayList<>();
        forbiddenWords.add("Microsoft");
        forbiddenWords.add("Word");
        forbiddenWords.add("Soft");
        SocialChannel channel = builder.
                with(new MessageTruncator(10)).
                with(new URLAppender("http://jpereira.eu")).
                with(new WordCensor(forbiddenWords)).
                getDecoratedChannel(props);

        channel.deliverMessage("Word Micro, Microsoft Excel, Microsoft Microsof, Soft Microso");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("### Mi... http://jpereira.eu", spyChannel.lastMessagePublished());
    }

    @Test
    public void testChainTwoDecoratorsWithoutBuilder() {

        SocialChannel channel = new TestSpySocialChannel();

        SocialChannel urlAppenderChannel = new URLAppender("http://jpereira.eu", channel);

        //Now create a truncator
        SocialChannel messageTruncatorChannel = new MessageTruncator(10, urlAppenderChannel);
        //Now create a word censor
        List<String> forbiddenWords = new ArrayList<>();
        forbiddenWords.add("Microsoft");
        forbiddenWords.add("Word");
        forbiddenWords.add("Soft");
        SocialChannel wordCensorChannel = new WordCensor(forbiddenWords, messageTruncatorChannel);

        wordCensorChannel.deliverMessage("Word Micro, Microsoft Excel, Microsoft Microsof, Soft Microso");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spy = (TestSpySocialChannel)channel;
        assertEquals("### Mic... http://jpereira.eu", spy.lastMessagePublished());
    }

    @Test
    public void testOtherChainTwoDecorators() {
        // Create the builder
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        // create a spy social channel
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);

        List<String> forbiddenWords = new ArrayList<>();
        forbiddenWords.add("Microsoft");
        forbiddenWords.add("Word");
        forbiddenWords.add("Soft");

        // Chain decorators
        SocialChannel channel = builder.with(new URLAppender("http://jpereira.eu")).andWith(new MessageTruncator(30)).andWithWC(new WordCensor(forbiddenWords)).getDecoratedChannel(props);

        channel.deliverMessage("Word Micro, Microsoft Excel, Microsoft Microsof, Soft Microso");
        // Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("### Micro, ### Excel...", spyChannel.lastMessagePublished());
    }
}
