package sentences;

public class SentenceTransformer {


    public String shortenSentence(String sentence) {
        validate(sentence);

        var words = sentence.split(" ");
        int count = words.length;
        if (count < 5) {
            return sentence;
        }
        return words[0] + " ... " + words[count - 1];
    }


    private void validate(String sentence) {
        char first = sentence.charAt(0);
        if (!Character.isUpperCase(first)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }

        char last = sentence.charAt(sentence.length() - 1);
        if (last != '.' && last != '!' && last != '?') {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }

}
