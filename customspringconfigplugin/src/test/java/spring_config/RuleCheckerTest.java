package spring_config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

@Slf4j
public class RuleCheckerTest {

    private static final String FILES_LOCATION = "src/test/resources/files/";

    @Test
    @DisplayName("Test rule checker")
    public void test() {
        String classLocation =
                String.format("%s%s.java", FILES_LOCATION, "SpringConfigRuleChecker");
        log.debug("Location: {}", classLocation);

        JavaCheckVerifier.verify(classLocation, new RuleChecker());
    }
}
