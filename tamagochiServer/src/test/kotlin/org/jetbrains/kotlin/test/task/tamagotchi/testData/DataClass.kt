@file:Suppress("FunctionOnlyReturningConstant", "VarCouldBeVal")

package org.jetbrains.kotlin.test.task.tamagotchi.testData

import org.jetbrains.academy.test.system.core.models.TestKotlinType
import org.jetbrains.academy.test.system.core.models.Visibility
import org.jetbrains.academy.test.system.core.models.classes.TestClass
import org.jetbrains.academy.test.system.core.models.variable.TestVariable
import org.jetbrains.academy.test.system.core.models.variable.VariableMutability


val testClass = TestClass(
    "GameService",
    "org.jetbrains.kotlin.test.task.tamagotchi.game",
    declaredFields = listOf(
        TestVariable(
            name = "commands",
            javaType = "ArrayDeque",
            kotlinType = TestKotlinType(
                "ArrayDeque",
                params = listOf("Command")
            ),
            visibility = Visibility.PUBLIC,
            mutability = VariableMutability.VAL
        )
    ),
    customMethods = listOf(
        addCommandTestMethod,
        getCommandTestMethod
    )
)
