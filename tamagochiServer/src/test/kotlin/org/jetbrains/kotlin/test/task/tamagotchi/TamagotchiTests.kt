package org.jetbrains.kotlin.test.task.tamagotchi

import org.jetbrains.academy.test.system.core.findMethod
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.ConstructorGetter
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.method.TestMethodInvokeData
import org.jetbrains.kotlin.test.task.tamagotchi.game.GameService
import org.jetbrains.kotlin.test.task.tamagotchi.models.Command
import org.jetbrains.kotlin.test.task.tamagotchi.models.Mode
import org.jetbrains.kotlin.test.task.tamagotchi.testData.addCommandTestMethod
import org.jetbrains.kotlin.test.task.tamagotchi.testData.testClass
import org.jetbrains.kotlin.test.task.tamagotchi.testData.commandsStorageGetter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TamagotchiTests {

    @Test
    fun commandFieldTest() {
        val clazz = testClass.checkBaseDefinition()
        val constructor = testClass.checkConstructors(
            clazz, listOf(
                ConstructorGetter(),
            )
        )
        val instance = constructor.newInstance()

        val publicValGetterMethod = clazz.methods.findMethod(commandsStorageGetter)
        val publicValActual = testClass.invokeMethodWithoutArgs(clazz, instance, publicValGetterMethod)
        Assertions.assertTrue((publicValActual as ArrayDeque<Command>).isEmpty()) {
            "The initial size of the commands storage should be 0"
        }
    }

    @Test
    fun gameServiceTestClassTest() {
        val clazz = testClass.checkBaseDefinition()
        testClass.checkFieldsDefinition(clazz, false)
        testClass.checkDeclaredMethods(clazz)

    }

    @Test
    fun addCommandTestMethodTest() {
        val clazz = testClass.checkBaseDefinition()
        testClass.checkFieldsDefinition(clazz, false)
        val constructor = testClass.checkConstructors(
            clazz, listOf(
                ConstructorGetter(),
            )
        )
        constructor.newInstance()

        val addCommandMethod = clazz.methods.findMethod(addCommandTestMethod)
        val invokeData = addCommandTestMethod.getInvokeData()

        for (i in 0..20) {
            val expected = i < 16
            val cmd = getRandomCommand()
            val actualSum = testClass.invokeMethodWithArgs(
                cmd,
                invokeData = invokeData,
                isPrivate = addCommandTestMethod.visibility == Visibility.PRIVATE
            ).toString()
            Assertions.assertEquals(
                expected.toString(),
                actualSum
            ) { "For cmd = $cmd the method ${addCommandMethod.name} must return $expected." }
        }
    }

    @Test
    fun getCommandTestMethodStackQueueTest() {
        getCommandTestMethodTest(Mode.Queue)
        getCommandTestMethodTest(Mode.Stack)
    }

    private fun getCommandTestMethodTest(mode: Mode) {
        val gameService = GameService()
        val commands = List(16) { getRandomCommand() }
        commands.forEachIndexed { index, i ->
            Assertions.assertTrue(gameService.addCommand(i)) {
                "Should return true, since only ${index + 1} commands were added, the storage cannot be full."
            }
        }
        Assertions.assertFalse(gameService.addCommand(Command.Clean)) {
            "Storage should be full"
        }

        val commandsInRemovalOrder = if (mode == Mode.Queue) commands else commands.reversed()
        commandsInRemovalOrder.forEach {
            val cmd = gameService.getCommand(mode)
            Assertions.assertEquals(it, cmd) {
                "In mode $mode expected: ${it}, actual: $cmd. Check the order of removing items from storage."
            }
        }
    }

    private fun getRandomCommand() = Command.entries.toList().shuffled().first()

    private fun TestMethod.getInvokeData() = TestMethodInvokeData(
        testClass,
        this,
    )
}