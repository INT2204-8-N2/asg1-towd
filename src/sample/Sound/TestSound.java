package sample.Sound;


import com.sun.speech.freetts.VoiceManager;

public class TestSound {
    public TestSound(String text){

        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(text);
        syntheticVoice.deallocate();
    }

    public static void main(String[] args) {
        new TestSound("go go go");
    }
}
