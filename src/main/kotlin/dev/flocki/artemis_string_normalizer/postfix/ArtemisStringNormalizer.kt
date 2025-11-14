package dev.flocki.artemis_string_normalizer.postfix

object ArtemisStringNormalizer {
    fun getTemplateString(element: String): String {
        val text = extractStringContent(element).replace("⎵", " ")
        return buildExpression(text)
    }

    fun extractStringContent(s: String): String = when {
        s.startsWith("\"\"\"") -> s.removeSurrounding("\"\"\"")
        s.startsWith("\"")     -> s.removeSurrounding("\"")
        s.startsWith("'")      -> s.removeSurrounding("'")
        else -> s
    }

    fun buildExpression(text: String): String {
        val out = StringBuilder()
        val lit = StringBuilder()

        fun flush() {
            if (lit.isNotEmpty()) {
                if (out.isNotEmpty()) out.append(" + ")
                out.append("\"").append(escape(lit.toString())).append("\"")
                lit.clear()
            }
        }

        var i = 0
        while (i < text.length) {
            if (text[i] == '<') {
                val end = text.indexOf('>', i + 1)
                if (end > i) {
                    val name = text.substring(i + 1, end)
                    if (name.isNotBlank() && !name.any(Char::isWhitespace)) {
                        flush()
                        if (out.isNotEmpty()) out.append(" + ")
                        out.append(name)
                    }
                    i = end + 1
                    continue
                }
            }
            lit.append(text[i])
            i++
        }

        flush()
        return if (out.isEmpty()) "\"\"" else out.toString()
    }

    fun escape(s: String): String =
        s.replace("\\", "\\\\")
            .replace("\"", "\\\"")
}