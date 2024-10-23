package com.isenap5.boothrum.database

import com.isenap5.boothrum.model.Favourite
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<FavouriteCacheEntity, Favourite> {
    override fun mapFromEntity(entity: FavouriteCacheEntity): Favourite {
        return Favourite(
            id = entity.id,
            url = entity.url,
            tags = entity.tags,
            artist = entity.artist,
            image = entity.image
        )
    }

    override fun mapToEntity(domainModel: Favourite): FavouriteCacheEntity {
        return FavouriteCacheEntity(
            id = domainModel.id,
            url = domainModel.url,
            tags = domainModel.tags,
            artist = domainModel.artist,
            image = domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<FavouriteCacheEntity>): List<Favourite> {
        return entities.map { mapFromEntity(it) }
    }

}