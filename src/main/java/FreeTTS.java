import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTS {

    public static void speakWord(String word, int speed) {
        Voice voice;
        voice = VoiceManager.getInstance().getVoice("kevin");
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(150);
            voice.setPitch(30 + 30 * speed);
            voice.setVolume(100);
            voice.speak(word);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        speakWord("information", 2);
    }

}
