package Lesson4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertJExamplesTest {
    @Test
    void assertJTest() {
        assertThat(Functions.isPalindrome("123321")).isTrue().isEqualTo(true);

        List<String> stringList = new ArrayList<>(Arrays.asList("test", "test1", "testt"));
        assertThat(stringList).containsAnyOf("test", "testtest");

        assertThat(1).isGreaterThan(0).isLessThan(10);
    }

}
