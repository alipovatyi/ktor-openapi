package dev.arli.openapi.util

import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

internal val KType.isEnum: Boolean get() = jvmErasure.isSubclassOf(Enum::class)
