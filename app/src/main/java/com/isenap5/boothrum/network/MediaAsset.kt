package com.isenap5.boothrum.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class MediaAsset (

  @SerialName("id"           ) var id          : Int?                = null,
  @SerialName("created_at"   ) var createdAt   : String?             = null,
  @SerialName("updated_at"   ) var updatedAt   : String?             = null,
  @SerialName("md5"          ) var md5         : String?             = null,
  @SerialName("file_ext"     ) var fileExt     : String?             = null,
  @SerialName("file_size"    ) var fileSize    : Int?                = null,
  @SerialName("image_width"  ) var imageWidth  : Int?                = null,
  @SerialName("image_height" ) var imageHeight : Int?                = null,
  @SerialName("duration"     ) var duration    : String?             = null,
  @SerialName("status"       ) var status      : String?             = null,
  @SerialName("file_key"     ) var fileKey     : String?             = null,
  @SerialName("is_public"    ) var isPublic    : Boolean?            = null,
  @SerialName("pixel_hash"   ) var pixelHash   : String?             = null,
  @SerialName("variants"     ) var variants    : ArrayList<Variants> = arrayListOf()

)