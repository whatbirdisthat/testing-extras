package net.tqxr.testframework.reporting;

import net.tqxr.testframework.categories.TestDescription;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * A unit test that does not use {see @Autowire}
 * And therefore is quicker and quieter - and most importantly:
 * more focused.
 */
@TestDescription("Verify the behaviour of the Test Name Transformer")
public class TestNameTransformerTest {
    private TestNameTransformer testNameTransformer = new TestNameTransformer();

    @Test
    public void canTransformFirstLetterToUpperCase() throws Exception {

        String fakeTestName = "firstLetter";
        String expectedString = "FirstLetter";

        String actualString = testNameTransformer.transformLowerCaseFirstLetterToUpperCase(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("First letter should be upper case.");

    }

    @Test
    public void canTransformCamelTestNameToSpacedWords() throws Exception {

        String fakeTestName = "canTransformArraylistToStringArray";
        String expectedString = "can Transform Arraylist To String Array";

        String actualString = testNameTransformer.transformCamelTestNameToSpacedWords(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("Expected string should equal actual string");

    }

    @Test
    public void canTransformCamelTestNameToSpacedWordsWithUpperFirstLetter() throws Exception {

        String fakeTestName = "canTransformArraylistToStringArray";
        String expectedString = "Can Transform Arraylist To String Array";

        String actualString = testNameTransformer.transformTestName(fakeTestName);

        assertThat(actualString)
                .isEqualTo(expectedString)
                .withFailMessage("Expected string should equal actual string");

    }

}
