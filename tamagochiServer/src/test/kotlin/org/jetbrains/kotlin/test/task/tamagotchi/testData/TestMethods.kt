package org.jetbrains.kotlin.test.task.tamagotchi.testData

import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.method.TestMethod
import org.jetbrains.academy.test.system.core.models.variable.TestVariable

val commandsStorageGetter = TestMethod(
    name = "getCommands",
    returnType = TestKotlinType("ArrayDeque", params = listOf("Command")),
    returnTypeJava = "ArrayDeque",
    visibility = Visibility.PUBLIC
)


val getCommandTestMethod = TestMethod(
    name = "getCommand",
    returnType = TestKotlinType("Command", isNullable = true),
    returnTypeJava = "Command",
    arguments = listOf(
        TestVariable(
            name = "mode",
            javaType = "Mode"
        )
    )
)

val addCommandTestMethod = TestMethod(
    name = "addCommand",
    returnType = TestKotlinType("Boolean"),
    returnTypeJava = "Boolean",
    arguments = listOf(
        TestVariable(
            name = "cmd",
            javaType = "Command"
        )
    )
)