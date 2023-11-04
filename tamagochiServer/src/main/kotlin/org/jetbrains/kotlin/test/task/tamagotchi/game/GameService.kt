package org.jetbrains.kotlin.test.task.tamagotchi.game

import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode
import org.springframework.stereotype.Service

@Service
class GameService {
    val commands = ArrayDeque<Command>()
    fun addCommand(cmd: Command): Boolean {
        if (commands.size >= MAX_CAPACITY) {
            return false
        }
        commands.add(cmd)
        return true
    }

    fun getCommand(mode: Mode): Command? {
        if (commands.isEmpty()) {
            return null
        }
        return when (mode) {
            Mode.Queue -> commands.removeFirst()
            Mode.Stack -> commands.removeLast()
        }
    }

    companion object {
        private const val MAX_CAPACITY = 16
    }
}
