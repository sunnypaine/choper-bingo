package org.choper.bingo

import org.choper.bingo.enums.TrimType
import java.util.*

object StringUtil {
    //region 静态常量
    /**
     * 表示空字符串。 此字段为只读。
     */
    const val EMPTY: String = "";
    //endregion


    //region 私有方法
    /**
     * 剪切字符串首或尾的空白字符串辅助方法。
     * @param value    被操作的字符串。
     * @param trimType 剪切类型。
     * @return 返回被剪切首或尾空白字符串后的字符串。
     */
    private fun trimHelper(value: String, trimType: TrimType): String {
        if (value.isEmpty()) {
            return EMPTY;
        }

        var len = value.length;
        var st: Int = 0;
        var _value: CharArray = value.toCharArray();

        if (trimType != TrimType.TRIM_TIAL) {
            while ((st < len) && (_value[st] <= ' ')) {
                st++;
            }
        }
        if (trimType != TrimType.TRIM_HEAD) {
            while ((st < len) && (_value[len - 1] <= ' ')) {
                len--;
            }
        }

        if (st > 0 || len < value.length) {
            return value.substring(st, len);
        }
        else {
            return value;
        }
    }
    //endregion


    //region 公共方法
    /**
     * 返回的字符串表示形式的值UUID实例。
     *
     * @param format 一个单格式说明符，它指示如何格式化此 UUID 的值。 format 参数可以是“N”、 “D” 或不赋值。
     *
     * 如果format的值为N，则为32位小写字符串，如aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *
     * 如果format的值为L，则为32位连接符分割的小写字符串，如aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa
     *
     * 如果format的值为P，则为32位连接符分割的字符串，如AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
     *
     * 如果format的值为U，则为32位连接符分割的字符串，如AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA
     *
     * @return 返回的字符串表示形式的值UUID实例。
     */
    fun getUUID(format: String = "D"): String {
        val uuid: String = UUID.randomUUID().toString();
        return when {
            "N".equals(format) -> uuid.replace("-", "").toLowerCase()
            "L".equals(format) -> uuid.toLowerCase()
            "P".equals(format) -> uuid.replace("-", "").toUpperCase()
            "U".equals(format) -> uuid.toUpperCase()
            else -> uuid
        }
    }

    /**
     * 比较两个字符串的值是否相同。
     *
     * @param strA       字符串A。
     * @param strB       字符串B。
     * @return
     */
    fun compare(strA: String?, strB: String?): Boolean {
        if (strA == null && strB != null) {
            return false;
        }
        if (strA != null && strB == null) {
            return false;
        }
        if (strA != null && strB != null) {
            return strA.equals(strB);
        }
        return false;
    }

    /**
     * 比较两个字符串的值是否相同。
     *
     * @param strA       字符串A。
     * @param strB       字符串B。
     * @param ignoreCase 是否无视大小写。true：无视大小写；false：区分大小写。
     * @return
     */
    fun compare(strA: String?, strB: String?, ignoreCase: Boolean): Boolean {
        if (strA == null && strB != null) {
            return false;
        }
        if (strA != null && strB == null) {
            return false;
        }
        if (strA != null && strB != null) {
            if (ignoreCase) {
                return strA.equals(strB);
            }
            else {
                return strA.toLowerCase().equals(strB.toLowerCase());
            }
        }
        return false;
    }

    /**
     * 连接连接指定的String数组的元素到目标 String 元素。
     *
     * @param source   目标String元素。
     * @param elements String数组元素。
     * @return
     */
    fun contact(source: String, vararg elements: String): String {
        var sb: StringBuilder = StringBuilder(source);
        for (item: String in elements) {
            sb.append(item);
        }

        return sb.toString();
    }

    /**
     * 将指定字符串中的格式项替换为指定数组中相应对象的字符串表示形式。
     *
     * @param formatString 复合格式字符串。
     * @param args         一个对象数组，其中包含零个或多个要设置格式的对象。
     * @return format的副本，其中格式项已替换为 args 中相应对象的字符串表示形式。
     */
    fun fomart(formatString: String, vararg args: Any): String {
        if (args.size <= 0) {
            return formatString;
        }

        var indexOf: Int = 1;
        var count: Int = 0;
        while (indexOf > 0) {
            indexOf = formatString.indexOf("{" + count + "}");
            if (indexOf > 0) {
                count++;
            }
        }

        if (args.size != count) {
            throw IndexOutOfBoundsException("试图访问索引超出界限的数组或集合的元素时引发的异常。");
        }

        var index: Int = 0;
        var result: String = formatString;
        for (item: Any in args) {
            result = formatString.replace("{" + index++ + "}", item.toString());
        }

        return result;
    }

    /**
     * 指示指定的字符串是null还是StringUtils.EMPTY字符串。
     *
     * @param value 要测试的字符串。
     * @return 如果true参数为value或空字符串(" ")，则为null；否则为false。
     */
    fun isNullOrEmpty(value: String?): Boolean {
        return (value == null || value.length == 0);
    }

    /**
     * 指示指定的字符串是 null、空还是仅由空白字符组成。
     *
     * @param value 要测试的字符串。
     * @return 如果true参数为value或null，或者如果StringUtils.EMPTY仅由空白字符组成，则为value。
     */
    fun isNullOrWhiteSpace(value: String?): Boolean {
        if (value == null) {
            return true;
        }

        val length: Int = value.length;
        for (i: Int in 0 until length) {
            if (value[i] != ' ') {
                return false;
            }
        }

        return true;
    }

    /**
     * 串联对象数组的各个元素，其中在每个元素之间使用指定的分隔符。
     *
     * @param separator 要用作分隔符的字符串。
     * @param elements  一个数组，其中包含要连接的元素。
     * @return 一个由elements的元素组成的字符串，这些元素以separator字符串分隔。如果elements为空数组，该方法将返回StringUtils.EMPTY。
     */
    fun join(separator: String, vararg elements: String): String {
        val length: Int = elements.size;
        if (length <= 0) {
            return EMPTY;
        }
        if (length == 1) {
            return elements[0];
        }

        var sb: StringBuilder = StringBuilder(elements[0]);
        for (i: Int in 1 until length) {
            sb.append(separator + elements[i]);
        }
        return sb.toString();
    }

    /**
     * 串联对象数组的各个元素，其中在每个元素之间使用指定的分隔符。
     *
     * @param separator 要用作分隔符的字符串。
     * @param source    目标String元素。
     * @param elements  一个数组，其中包含要连接的元素。
     * @return 一个由elements的元素组成的字符串，这些元素以separator字符串分隔。如果elements为空数组，该方法将返回source。
     */
    fun join(separator: String, source: String, vararg elements: String): String {
        var length: Int = elements.size;
        if (length <= 0) {
            return source;
        }

        var sb: StringBuilder = StringBuilder(source);
        for (item: String in elements) {
            sb.append(separator + item);
        }
        return sb.toString();
    }

    /**
     * 返回一个新的字符串，在此实例中的指定的索引位置插入指定的字符串。
     *
     * @param startIndex 插入的从零开始的索引位置。
     * @param value      要插入的字符串。
     * @return 返回插入了新值后的字符串。
     */
    fun insert(startIndex: Int, value: String, insertValue: String): String {
        val length: Int = value.length;
        if (startIndex < 0 || startIndex > length) {
            throw IndexOutOfBoundsException("startIndex");
        }

        //首位置
        if (startIndex == 0) {
            return insertValue + value;
        }
        //末位置
        if (startIndex == length) {
            return value + insertValue;
        }
        return value.substring(0, startIndex) + insertValue + value.substring(startIndex);
    }

    /**
     * 返回一个新字符串，该字符串通过在此实例中的字符左侧填充指定的Unicode字符来达到指定的总长度，从而使这些字符右对齐。
     *
     * @param value       要被操作的字符串源。
     * @param totalWidth  结果字符串中的字符数，等于原始字符数加上任何其他填充字符。
     * @param paddingChar Unicode填充字符。
     * @return 与此实例等效的一个新字符串，但该字符串为右对齐，因此，在左侧填充所需任意数量的paddingChar字符，使长度达到totalWidth。但是，如果
     * totalWidth小于此实例的长度，则此方法返回对现有实例的引用。如果totalWidth等于此实例的长度，则此方法返回与此实例相同的新字符串。
     */
    fun padLeft(value: String, totalWidth: Int, paddingChar: Char): String {
        val length: Int = value.length;
        if (length >= totalWidth) {
            return value;
        }

        val paddingCount: Int = totalWidth - length;
        var sb: StringBuilder = StringBuilder();
        for (i: Int in 0 until paddingCount) {
            sb.append(paddingChar);
        }
        return sb.toString() + trim(value);
    }

    /**
     * 返回一个新字符串，该字符串通过在此字符串中的字符右侧填充指定的Unicode字符来达到指定的总长度，从而使这些字符左对齐。
     *
     * @param value       要被操作的字符串源。
     * @param totalWidth  结果字符串中的字符数，等于原始字符数加上任何其他填充字符。
     * @param paddingChar Unicode填充字符。
     * @return 与此实例等效的一个新字符串，但该字符串为左对齐，因此，在右侧填充所需任意数量的paddingChar字符，使长度达到totalWidth。但是，如果
     * totalWidth小于此实例的长度，则此方法返回对现有实例的引用。如果totalWidth等于此实例的长度，则此方法返回与此实例相同的新字符串。
     */
    fun padRight(value: String, totalWidth: Int, paddingChar: Char): String {
        val length: Int = value.length;
        if (length >= totalWidth) {
            return value;
        }

        val paddingCount: Int = totalWidth - length;
        var sb: StringBuilder = StringBuilder();
        for (i: Int in 0 until paddingCount) {
            sb.append(paddingChar);
        }
        return trim(value) + sb.toString();
    }

    /**
     * 从String对象移除所有前导空白字符和尾部空白字符。
     *
     * @return 从当前字符串的开头和结尾删除所有空白字符后剩余的字符串。 如果从当前实例无法删除字符，此方法返回未更改的当前实例。
     */
    fun trim(value: String): String {
        return trimHelper(value, trimType = TrimType.TRIM_BOTH)
    }

    /**
     * 从String对象移除数组中指定的一组字符的所有尾部匹配项。
     *
     * @param value
     * @return 从字符串的结尾删除所有空白字符后剩余的字符串。如果从当前实例无法删除字符，此方法返回未更改的当前实例。
     */
    fun trimEnd(value: String): String {
        return trimHelper(value, trimType = TrimType.TRIM_TIAL);
    }

    /**
     * 从当前String对象移除数组中指定的一组字符的所有前导匹配项。
     *
     * @param value 从字符串的开头删除所有空白字符后剩余的字符串。如果从当前实例无法删除字符，此方法返回未更改的当前实例。
     * @return
     */
    fun trimStart(value: String): String {
        return trimHelper(value, trimType = TrimType.TRIM_HEAD);
    }
    //endregion

}