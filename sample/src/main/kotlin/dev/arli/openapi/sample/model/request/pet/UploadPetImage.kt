package dev.arli.openapi.sample.model.request.pet

import dev.arli.openapi.annotation.Path
import dev.arli.openapi.annotation.Query
import dev.arli.openapi.annotation.Request
import dev.arli.openapi.annotation.RequestBody
import dev.arli.openapi.annotation.Response
import dev.arli.openapi.model.MediaType
import dev.arli.openapi.sample.routing.Tags
import java.io.File

@Request(
    tags = [Tags.Pet],
    summary = "Uploads an image"
)
data class UploadPetImageRequest(
    @Path(description = "ID of pet to update")
    val petId: Long,
    @Query(description = "Additional Metadata")
    val additionalMetadata: String,
    @RequestBody(MediaType.MULTIPART_FORM)
    val uploadImageDto: UploadPetImageDto
)

data class UploadPetImageDto(val image: File)

@Response(description = "Successful operation")
object UploadPetImageResponse
