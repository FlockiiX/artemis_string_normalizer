import dev.flocki.artemis_string_normalizer.postfix.ArtemisStringNormalizer
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths

class NormalizerTest {
        @Test
        fun testFromFileCases() {
            val path = Paths.get("src/test/resources/normalizer_cases.txt")
            val lines = Files.readAllLines(path, Charsets.UTF_8)

            require(lines.size % 2 == 0) {
                "The test file must have an even number of lines (input/output pairs)."
            }

            for (i in lines.indices step 2) {
                print(i)
                val input = lines[i]
                val expected = lines[i + 1]

                val actual = ArtemisStringNormalizer.getTemplateString(input)

                assertEquals(
                    "Error in line ${i + 1}: input=$input",
                    expected,
                    actual
                )
            }
        }
}