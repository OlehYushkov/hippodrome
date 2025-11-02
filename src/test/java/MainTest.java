import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled
    void checkThat_mainMethodIsExecutedNoLongerThan22Seconds() {
        try {
            Main.main(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
