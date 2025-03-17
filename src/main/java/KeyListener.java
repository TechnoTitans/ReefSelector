import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyListener implements NativeKeyListener {
    final NTListener ntListener;

    public KeyListener(final NTListener ntListener) {
        this.ntListener = ntListener;

        try {
            GlobalScreen.registerNativeHook();
        } catch (final NativeHookException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void nativeKeyPressed(final NativeKeyEvent e) {
        final Constants.Branch state = Constants.KEYBOARD_TO_BRANCH.get(e.getKeyCode());
        if (state != null) {
            ntListener.selectBranch(state);
        }
    }
}
