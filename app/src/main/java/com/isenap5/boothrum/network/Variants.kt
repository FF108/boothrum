package com.isenap5.boothrum.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Variants (

  @SerialName("type"     ) var type    : String? = null,
  @SerialName("url"      ) var url     : String? = null,
  @SerialName("width"    ) var width   : Int?    = null,
  @SerialName("height"   ) var height  : Int?    = null,
  @SerialName("file_ext" ) var fileExt : String? = null

)