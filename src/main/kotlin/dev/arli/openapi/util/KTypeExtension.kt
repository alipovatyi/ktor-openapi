package dev.arli.openapi.util

import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

val KType.isEnum: Boolean get() = jvmErasure.isSubclassOf(Enum::class)
