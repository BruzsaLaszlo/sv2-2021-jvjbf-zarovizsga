package videos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class VideoPlatform {

    List<Channel> channels = Collections.emptyList();

    public void readDataFromFile(Path path) {
        channels = getLinesFromFile(path).stream()
                .skip(1)
                .map(this::parseChannel)
                .toList();
    }

    private List<String> getLinesFromFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

    private Channel parseChannel(String line) {
        var data = line.split(";");

        String name = data[0];
        int subscriptions = Integer.parseInt(data[1]);
        int numberOfVideos = Integer.parseInt(data[2]);

        return new Channel(name, subscriptions, numberOfVideos);
    }

    public int calculateSumOfVideos() {
        return channels.stream()
                .mapToInt(Channel::getNumberOfVideos)
                .sum();
    }

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }

}
