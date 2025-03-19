import java.util.Map;

public class Constants {
    public enum Branch {
        LEFT_L4,
        RIGHT_L4,
        LEFT_L3,
        RIGHT_L3,
        LEFT_L2,
        RIGHT_L2,
        LEFT_L1,
        RIGHT_L1,
    }

    public static final String HOSTNAME = "localhost";
    public static final String CLIENT_NAME = "BranchSelector";
    public static final String REEF_NETWORK_TABLE = "BranchSelector";
    public static final String SELECTED_BRANCH = "BranchPublisher";
    public static final String DASH_SELECTED_BRANCH = "SelectedBranch";

    public static Map<Integer, Branch> KEYBOARD_TO_BRANCH = Map.of(
            5, Branch.LEFT_L4,
            9, Branch.RIGHT_L4,
            4, Branch.LEFT_L3,
            8, Branch.RIGHT_L3,
            3, Branch.LEFT_L2,
            7, Branch.RIGHT_L2,
            2, Branch.LEFT_L1,
            6, Branch.RIGHT_L1
    );
}
