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
        return ArtemisStringNormalizer.getTemplateString(element.text)
    }
}