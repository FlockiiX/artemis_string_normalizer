package dev.flocki.artemis_string_normalizer.postfix

import com.intellij.codeInsight.template.postfix.templates.StringBasedPostfixTemplate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

class ArtemisStringNormalizerTemplate(provider: ArtemisStringNormalizerTemplateProvider): StringBasedPostfixTemplate(
    "artemis",
    "string.artemis",
    UniversalSelector(),
    provider
) {
    override fun isApplicable(context: PsiElement, doc: Document, offset: Int): Boolean {
        val t = context.text ?: return false
        return t.startsWith("\"") && t.endsWith("\"") ||
                t.startsWith("'") && t.endsWith("'") ||
                t.startsWith("\"\"\"") && t.endsWith("\"\"\"")
    }

    override fun getTemplateString(element: PsiElement): String {
        val text = extractStringContent(element.text).replace("⎵", " ")
        return buildExpression(text)
    }

    private fun extractStringContent(s: String): String = when {
        s.startsWith("\"\"\"") -> s.removeSurrounding("\"\"\"")
        s.startsWith("\"")     -> s.removeSurrounding("\"")
        s.startsWith("'")      -> s.removeSurrounding("'")
        else -> s
    }

    private fun buildExpression(text: String): String {
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

    private fun escape(s: String): String =
        s.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("$", "\\$")
}