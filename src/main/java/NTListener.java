import edu.wpi.first.networktables.*;
import edu.wpi.first.util.CombinedRuntimeLoader;
import edu.wpi.first.util.WPIUtilJNI;

import java.io.IOException;

public class NTListener implements AutoCloseable {
    static {
        WPIUtilJNI.Helper.setExtractOnStaticLoad(false);
        NetworkTablesJNI.Helper.setExtractOnStaticLoad(false);

        try {
            CombinedRuntimeLoader.loadLibraries(
                    NTListener.class,
                    "wpiutiljni",
                    "ntcorejni"
            );
        } catch (final IOException io) {
            throw new RuntimeException(io);
        }
    }
    private final NetworkTableInstance networkTableInstance;
    private final StringPublisher branchPublisher;
    private final StringSubscriber selectedBranchSubscriber;

    public NTListener() {
        this.networkTableInstance = NetworkTableInstance.getDefault();
        this.networkTableInstance.startClient4(Constants.CLIENT_NAME);
        this.networkTableInstance.setServer(Constants.HOSTNAME); // where TEAM=190, 294, etc, or use inst.setServer("hostname") or similar
        this.networkTableInstance.startDSClient();

        final NetworkTable ntNodeTable = this.networkTableInstance.getTable(Constants.REEF_NETWORK_TABLE);
        this.branchPublisher = ntNodeTable
                .getStringTopic(Constants.DASH_SELECTED_BRANCH)
                .publish(PubSubOption.keepDuplicates(true));

        this.selectedBranchSubscriber = ntNodeTable
                .getStringTopic(Constants.SELECTED_BRANCH)
                .subscribe(Constants.DEFAULT_BRANCH.toString());

        this.branchPublisher.set(Constants.DEFAULT_BRANCH.toString());
    }

    public NetworkTableInstance getNetworkTableInstance() {
        return networkTableInstance;
    }

    public void selectBranch(final Constants.Branch branch) {
        branchPublisher.set(branch.toString());
    }

    public StringSubscriber getSelectedBranchSubscriber() {
        return selectedBranchSubscriber;
    }

    public Constants.Branch getSelectedBranch() {
        return Constants.Branch.valueOf(selectedBranchSubscriber.get());
    }

    @Override
    public void close() {
        branchPublisher.close();
        selectedBranchSubscriber.close();
    }
}

