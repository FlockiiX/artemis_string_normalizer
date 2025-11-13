package dev.flocki.artemis_string_normalizer.postfix

import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateExpressionSelector
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.util.Function

class UniversalSelector: PostfixTemplateExpressionSelector {
    override fun hasExpression(
        context: PsiElement,
        document: Document,
        offset: Int
    ): Boolean {
       return true
    }

    override fun getExpressions(
        context: PsiElement,
        document: Document,
        offset: Int
    ): MutableList<PsiElement> {
        return mutableListOf(context)
    }

    override fun getRenderer(): Function<PsiElement?, String?> {
       return Function {element -> element?.text ?: ""}
    }
}