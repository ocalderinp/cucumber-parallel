package org.example;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin={"pretty","json:report.json"},
        features = "src/test/resources/features",
        glue="org.example.steps",
        tags={"@regression"}
        )

public class RunCucumberTest extends AbstractTestNGCucumberTests {

        @DataProvider(parallel = true)
        @Override
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
