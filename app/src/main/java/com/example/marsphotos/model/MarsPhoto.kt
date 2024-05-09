package com.example.marsphotos.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This data class defines a Mars photo which includes an ID, and the image URL.
 */
@Serializable
data class MarsPhoto(
    val id: String,
//    val img_src: String

    /**
     * Sometimes the key names in a JSON response may not match recommended coding style.
     * To use variable names in your data class that differ from the key names in the JSON response,
     * use the @SerialName annotation.
     */

    @SerialName(value = "img_src")
    val imgSrc: String
)