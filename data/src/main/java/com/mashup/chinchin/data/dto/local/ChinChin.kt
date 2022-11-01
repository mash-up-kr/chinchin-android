package com.mashup.chinchin.data.dto.local

import androidx.room.*
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Friend

@Entity(tableName = "friend")
data class FriendEntity(
    @PrimaryKey
    val friendId: Long,
    val name: String,
    val dataOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String = "",
    val kakaoId: String = "",
) : DomainMapper<Friend> {
    override fun toDomainModel(): Friend {
        return Friend(
            id = friendId,
            name = name,
            thumbnailImageUrl = thumbnailImageUrl,
        )
    }
}

@Entity(
    tableName = "questionnaire",
    foreignKeys = [
        ForeignKey(
            entity = FriendEntity::class,
            parentColumns = arrayOf("friendId"),
            childColumns = arrayOf("questionnaireId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QuestionnaireEntity(
    @PrimaryKey
    val questionnaireId: Long
)

data class FriendAndQuestionnaireEntity(
    @Embedded val friend: FriendEntity,
    @Relation(
        parentColumn = "friendId",
        entityColumn = "questionnaireId",
    )
    val questionnaireEntity: QuestionnaireEntity
)

@Entity(
    tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = QuestionnaireEntity::class,
            parentColumns = arrayOf("questionnaireId"),
            childColumns = arrayOf("questionnaireId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    var questionId: Long = 0,
    val questionnaireId: Long,
    val question: String,
    val answer: String
)

data class QuestionnaireWithQuestionEntity(
    @Embedded val questionnaireEntity: QuestionnaireEntity,
    @Relation(
        parentColumn = "questionnaireId",
        entityColumn = "questionnaireId",
    )
    val questionEntityList: List<QuestionEntity>
)