import org.choper.bingo.StringUtil
import org.junit.Test

class TestStringUtil {

    @Test
    fun TestGetUUID() {
        val uuid: String = StringUtil.getUUID("N");
        println(uuid);
    }

    @Test
    fun TestIsNullOrEmpty() {
        val result: Boolean = StringUtil.isNullOrEmpty("");
        println(result.toString());
    }

    @Test
    fun TestIsNullOrWhiteSpage() {
        val result: Boolean = StringUtil.isNullOrWhiteSpace("  ");
        println(result.toString());
    }
}