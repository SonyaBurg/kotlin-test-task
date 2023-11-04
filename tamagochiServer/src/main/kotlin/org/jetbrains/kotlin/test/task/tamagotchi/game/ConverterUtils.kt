package org.jetbrains.kotlin.test.task.tamagotchi.game

import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode

fun Int.toCommand() = when (this) {
    0 -> Command.Eat
    1 -> Command.Sleep
    2 -> Command.Clean
    3 -> Command.Play
    else -> null
}

fun String.toMode() = when (this) {
    "\"Queue\"" -> Mode.Queue
    "\"Stack\"" -> Mode.Stack
    else -> null
}
