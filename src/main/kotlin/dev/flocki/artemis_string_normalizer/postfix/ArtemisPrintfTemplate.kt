package dev.flocki.artemis_string_normalizer.postfix

import com.intellij.codeInsight.template.postfix.templates.StringBasedPostfixTemplate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

class ArtemisPrintfTemplate(
    provider: ArtemisStringNormalizerTemplateProvider
) : StringBasedPostfixTemplate(
    "artemisf",
    "string.artemisf",
    UniversalSelector(),
    provider
) {

    override fun isApplicable(
        context: PsiElement,
        doc: Document,
        offset: Int
    ): Boolean {
        val t = context.text ?: return false
        return (t.startsWith("\"") && t.endsWith("\"")) ||
                (t.startsWith("'") && t.endsWith("'")) ||
                (t.startsWith("\"\"\"") && t.endsWith("\"\"\""))
    }

    override fun getTemplateString(element: PsiElement): String {
        val raw = ArtemisStringNormalizer.extractStringContent(element.text)
        val result = ArtemisStringNormalizer.buildPrintf(raw)

        val args = if (result.args.isNotEmpty())
            ", " + result.args.joinToString(", ")
        else
            ""

        return """System.out.printf("${result.format}"$args);"""
    }
}
