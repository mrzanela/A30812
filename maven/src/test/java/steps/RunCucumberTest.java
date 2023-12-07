package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 *
 * @author Jean
 */

/**
 * Esta classe executa os testes do Cucumber.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "steps")
public class RunCucumberTest {
}
