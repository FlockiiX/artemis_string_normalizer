package dev.flocki.artemis_string_normalizer.postfix

import com.intellij.codeInsight.template.postfix.templates.PostfixTemplate
import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class ArtemisStringNormalizerTemplateProvider : PostfixTemplateProvider {
    private val templates: Set<PostfixTemplate> = setOf(ArtemisStringNormalizerTemplate(this))

    override fun getTemplates(): Set<PostfixTemplate> = templates

    override fun preExpand(file: PsiFile, editor: Editor) {}

    override fun afterExpand(file: PsiFile, editor: Editor) {}

    override fun preCheck(
        file: PsiFile,
        editor: Editor,
        offset: Int
    ): PsiFile {
        return file
    }

    override fun isTerminalSymbol(c0: Char): Boolean = c0  == '.'
}