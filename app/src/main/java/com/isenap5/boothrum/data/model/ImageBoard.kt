package com.isenap5.boothrum.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class ImageBoard (

  @SerialName("id"                     ) var id                  : Int?        = null,
  @SerialName("created_at"             ) var createdAt           : String?     = null,
  @SerialName("uploader_id"            ) var uploaderId          : Int?        = null,
  @SerialName("score"                  ) var score               : Int?        = null,
  @SerialName("source"                 ) var source              : String?     = null,
  @SerialName("md5"                    ) var md5                 : String?     = null,
  @SerialName("last_comment_bumped_at" ) var lastCommentBumpedAt : String?     = null,
  @SerialName("rating"                 ) var rating              : String?     = null,
  @SerialName("image_width"            ) var imageWidth          : Int?        = null,
  @SerialName("image_height"           ) var imageHeight         : Int?        = null,
  @SerialName("tag_string"             ) var tagString           : String?     = null,
  @SerialName("fav_count"              ) var favCount            : Int?        = null,
  @SerialName("file_ext"               ) var fileExt             : String?     = null,
  @SerialName("last_noted_at"          ) var lastNotedAt         : String?     = null,
  @SerialName("parent_id"              ) var parentId            : String?     = null,
  @SerialName("has_children"           ) var hasChildren         : Boolean?    = null,
  @SerialName("approver_id"            ) var approverId          : String?     = null,
  @SerialName("tag_count_general"      ) var tagCountGeneral     : Int?        = null,
  @SerialName("tag_count_artist"       ) var tagCountArtist      : Int?        = null,
  @SerialName("tag_count_character"    ) var tagCountCharacter   : Int?        = null,
  @SerialName("tag_count_copyright"    ) var tagCountCopyright   : Int?        = null,
  @SerialName("file_size"              ) var fileSize            : Int?        = null,
  @SerialName("up_score"               ) var upScore             : Int?        = null,
  @SerialName("down_score"             ) var downScore           : Int?        = null,
  @SerialName("is_pending"             ) var isPending           : Boolean?    = null,
  @SerialName("is_flagged"             ) var isFlagged           : Boolean?    = null,
  @SerialName("is_deleted"             ) var isDeleted           : Boolean?    = null,
  @SerialName("tag_count"              ) var tagCount            : Int?        = null,
  @SerialName("updated_at"             ) var updatedAt           : String?     = null,
  @SerialName("is_banned"              ) var isBanned            : Boolean?    = null,
  @SerialName("pixiv_id"               ) var pixivId             : String?     = null,
  @SerialName("last_commented_at"      ) var lastCommentedAt     : String?     = null,
  @SerialName("has_active_children"    ) var hasActiveChildren   : Boolean?    = null,
  @SerialName("bit_flags"              ) var bitFlags            : Int?        = null,
  @SerialName("tag_count_meta"         ) var tagCountMeta        : Int?        = null,
  @SerialName("has_large"              ) var hasLarge            : Boolean?    = null,
  @SerialName("has_visible_children"   ) var hasVisibleChildren  : Boolean?    = null,
  @SerialName("media_asset"            ) var mediaAsset          : MediaAsset? = MediaAsset(),
  @SerialName("tag_string_general"     ) var tagStringGeneral    : String?     = null,
  @SerialName("tag_string_character"   ) var tagStringCharacter  : String?     = null,
  @SerialName("tag_string_copyright"   ) var tagStringCopyright  : String?     = null,
  @SerialName("tag_string_artist"      ) var tagStringArtist     : String?     = null,
  @SerialName("tag_string_meta"        ) var tagStringMeta       : String?     = null,
  @SerialName("file_url"               ) var fileUrl             : String?     = null,
  @SerialName("large_file_url"         ) var largeFileUrl        : String?     = null,
  @SerialName("preview_file_url"       ) var previewFileUrl      : String?     = null

)