package dev.fb.android.tamboon.data.repository.mapper

import dev.fb.android.tamboon.data.entity.CharityEntity
import dev.fb.android.tamboon.domain.model.Charity

sealed class CharityEntityToDomainMapper() {

    fun map(charity: Charity, charityEntity: CharityEntity): Charity {
        charity.charityId = charityEntity.id
        charity.charityName = charityEntity.name
        charity.charityLogoUrl = charityEntity.logo_url
        return charity
    }

    fun reverseMap(charityEntity: CharityEntity, charity: Charity): CharityEntity {
        charityEntity.id = charity.charityId
        charityEntity.name = charity.charityName
        charityEntity.logo_url = charity.charityLogoUrl
        return charityEntity
    }

}