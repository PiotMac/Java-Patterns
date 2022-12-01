package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

import java.util.List;
import java.util.Objects;

public class WordCensor extends SocialChannelDecorator {
    private List<String> forbiddenWords;
    public WordCensor(List<String> forbiddenWords) {
        this.forbiddenWords = forbiddenWords;
    }

    public WordCensor(List<String> forbiddenWords, SocialChannel decoratedChannel) {
        this.forbiddenWords = forbiddenWords;
        this.delegate = decoratedChannel;
    }

    @Override
    public void deliverMessage(String message) {
        for (int i = 0; i < forbiddenWords.size(); i++) {
                message = message.replaceAll(forbiddenWords.get(i),"###");
        }
        delegate.deliverMessage(message);
    }
}
