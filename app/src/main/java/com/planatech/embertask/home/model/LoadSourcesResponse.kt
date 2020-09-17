package com.planatech.embertask.home.model

import java.io.Serializable

data class LoadSourcesResponse(
    val status: String,
    val sources: List<Source>
) : Serializable