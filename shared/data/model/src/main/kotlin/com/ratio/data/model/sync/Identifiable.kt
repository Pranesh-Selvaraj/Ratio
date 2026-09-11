package com.ratio.data.model.sync

interface Identifiable<ID : UniqueId> {
    val id: ID
}
