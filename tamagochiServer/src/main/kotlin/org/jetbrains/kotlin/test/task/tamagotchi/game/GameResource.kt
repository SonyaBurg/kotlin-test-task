package org.jetbrains.kotlin.test.task.tamagotchi.game

import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/command/")
class GameResource(val service: GameService) {
    @CrossOrigin
    @PostMapping("/get")
    fun getCommand(@RequestBody mode: String): Command? {
        return service.getCommand(mode.toMode() ?: return null)
    }

    @CrossOrigin
    @PostMapping("/add")
    fun addCommand(@RequestBody command: Int): Boolean {
        return service.addCommand(command.toCommand() ?: return false)
    }

    @CrossOrigin
    @GetMapping("/all")
    fun getAllCommands(): ArrayDeque<Command> = service.commands
}
