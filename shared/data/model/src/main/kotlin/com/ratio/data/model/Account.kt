package com.ratio.data.model

import com.ratio.data.model.primitive.AssetCode
import com.ratio.data.model.primitive.ColorInt
import com.ratio.data.model.primitive.IconAsset
import com.ratio.data.model.primitive.NotBlankTrimmedString
import com.ratio.data.model.sync.Identifiable
import com.ratio.data.model.sync.UniqueId
import java.util.UUID

@JvmInline
value class AccountId(override val value: UUID) : UniqueId

data class Account(
    override val id: AccountId,
    val name: NotBlankTrimmedString,
    val asset: AssetCode,
    val color: ColorInt,
    val icon: IconAsset?,
    val includeInBalance: Boolean,
    override val orderNum: Double,
) : Identifiable<AccountId>, Reorderable
