package main;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class VoiceRSS {
    private static final String API_KEY = "ee1a861047db41e3aed6cca75554a826";
    private static final String AUDIO_PATH = "src/main/resource/media/audio.wav";

    public static String voiceNameUS;
    public static String voiceNameUK;
    public static String language = "en-gb";
    public static String Name = "Linda";
    public static int speed = 1;
    public static void speakWord(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(word, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);
        params.setVoice(Name);
        params.setRate(-8+7*speed);

        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        InputStream input = new FileInputStream(AUDIO_PATH);
        AudioStream audio = new AudioStream(input);
        AudioPlayer.player.start(audio);
    }

    public static void main (String[] args) throws Exception {
        String[] voiceUS = {"Linda", "Amy", "Mary", "John", "Mike"};
        String[] voiceUK = {"Alice", "Nancy", "Lily", "Harry"};
        String[] voiceVN = {"Chi"};
        speakWord("information");
        // speakWord("Xin chào tất cả các bạn", "vi-vn", voiceVN[0], 0);
    }
}


