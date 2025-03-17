import edu.wpi.first.networktables.NetworkTableEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class UI extends JFrame {
    private final NTListener ntListener;

    private final JLabel selectedBranch;

    public UI(final NTListener ntListener) {
        this.ntListener = ntListener;

        this.selectedBranch = new JLabel();

        EventQueue.invokeLater(() -> {
            initComponents();
            setVisible(true);
            ntEventListeners();
        });
    }

    private void ntEventListeners() {
        ntListener.getNetworkTableInstance().addListener(
                ntListener.getSelectedBranchSubscriber(),
                EnumSet.of(NetworkTableEvent.Kind.kValueAll),
                (event) -> selectedBranch.setText(ntListener.getSelectedBranch().toString())
        );

        ntListener.getNetworkTableInstance().addConnectionListener(
                true,
                (event) -> {
                    if (event.is(NetworkTableEvent.Kind.kConnected)) {
                        setTitle("BranchSelector | Connected");
                        selectedBranch.setText(ntListener.getSelectedBranch().toString());
                    } else if (event.is(NetworkTableEvent.Kind.kDisconnected)) {
                        setTitle("BranchSelector | Disconnected");
                        selectedBranch.setText("");
                    } else {
                        setTitle("BranchSelector | Unknown");
                        selectedBranch.setText(ntListener.getSelectedBranch().toString());
                    }
                }
        );
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("BranchSelector | Disconnected");
        setMinimumSize(new Dimension(450, 450));
        setPreferredSize(new Dimension(450, 450));
        setLocationRelativeTo(null);

        selectedBranch.setHorizontalAlignment(SwingConstants.CENTER);
        selectedBranch.setFont(new Font("Arial", Font.PLAIN, 50));
        selectedBranch.setForeground(Color.WHITE);
        add(selectedBranch);
    }
}
