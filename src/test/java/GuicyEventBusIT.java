import com.google.inject.*;
import de.mywald.guicyevents.example.*;
import org.junit.*;

import static org.junit.Assert.*;

public class GuicyEventBusIT extends IntegrationTest {

    @Inject
    private AnyEventSource eventSource;

    @Test
    public void testIfObserverHasBeenCalled() {
        eventSource.businessMethod("published value");
        assertEquals("published value", AnyEventObserver.HAS_BEEN_CALLED_WITH);
    }

}
