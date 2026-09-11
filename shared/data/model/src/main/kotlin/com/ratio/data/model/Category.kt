package com.ratio.data.model

import com.ratio.data.model.primitive.ColorInt
import com.ratio.data.model.primitive.IconAsset
import com.ratio.data.model.primitive.NotBlankTrimmedString
import com.ratio.data.model.sync.Identifiable
import com.ratio.data.model.sync.UniqueId
import java.util.UUID

@JvmInline
value class CategoryId(override val value: UUID) : UniqueId

data class Category(
    override val id: CategoryId,
    val name: NotBlankTrimmedString,
    val color: ColorInt,
    val icon: IconAsset?,
    override val orderNum: Double,
) : Identifiable<CategoryId>, Reorderable