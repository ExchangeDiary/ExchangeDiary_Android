package com.voda.domain.entity

import com.voda.domain.vo.HashTag
import java.util.*


data class Diary(
     val id: String,
     val title: String,
     val owner: Int,
     val bgColor: String?,
     val bgUrl: String,
     val members: List<User>,
     val interval: Date,
     val invitationUrl: String,
     val turnCount: Int,
     val hashTags: List<HashTag>?
)
