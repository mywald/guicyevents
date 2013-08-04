import com.google.inject.*;
import de.mywald.guicyevents.example.*;
import org.junit.*;

public class IntegrationTest {
    protected static Injector injector;

    @BeforeClass
    public static void setUpClass() {
        injector = Guice.createInjector(new GuiceModule());
    }

    @Before
    public void setupGuice() {
        injector.injectMembers(this);
    }

}
