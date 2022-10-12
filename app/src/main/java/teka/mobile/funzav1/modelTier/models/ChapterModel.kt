package teka.mobile.funzav1.modelTier.models

import kotlinx.serialization.Serializable

@Serializable
data class ChapterModel(val chapterName:String? = null,
                        val pdfUrl:String? = null)
