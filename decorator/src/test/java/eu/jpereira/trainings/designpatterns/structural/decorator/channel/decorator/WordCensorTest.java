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

public class WordCensorTest extends AbstractSocialChanneldDecoratorTest {
    @Test
    public void testWordCensoring() {
        //Create a builder
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();
        //Set forbidden words
        List<String> forbiddenWords = new ArrayList<>();
        forbiddenWords.add("Microsoft");
        forbiddenWords.add("Word");
        forbiddenWords.add("Soft");
        //Create a spy social channel
        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
        SocialChannel channel = builder.with(new WordCensor(forbiddenWords)).getDecoratedChannel(props);

        //Now call channel.deliverMessage
        channel.deliverMessage("Micro Word, Microsoft Excel, Microsoft Microsof, Soft Microso");

        //Spy channel. Should get the one created before
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);
        assertEquals("Micro ###, ### Excel, ### Microsof, ### Microso", spyChannel.lastMessagePublished());
    }
}
