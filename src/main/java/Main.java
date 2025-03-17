import com.formdev.flatlaf.FlatDarkLaf;
import com.github.kwhat.jnativehook.GlobalScreen;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        final NTListener ntListener = new NTListener();
        final KeyListener keyListener = new KeyListener(ntListener);
        GlobalScreen.addNativeKeyListener(keyListener);

        new UI(ntListener);
    }
}
