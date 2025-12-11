package com.kishorramani.navigation3.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kishorramani.navigation3.auth.LoginScreen
import com.kishorramani.navigation3.auth.LoginViewModel
import com.kishorramani.navigation3.auth.RegisterScreen
import com.kishorramani.navigation3.auth.RegisterViewModel
import com.kishorramani.navigation3.auth.SharedAuthViewModel
import com.kishorramani.navigation3.navigation.Route
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun TodoNavigation(
    modifier: Modifier = Modifier
) {
    val todoBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Todo.TodoList::class, Route.Todo.TodoList.serializer())
                    subclass(Route.Todo.TodoDetail::class, Route.Todo.TodoDetail.serializer())
                }
            }
        },
        Route.Todo.TodoList
    )

    NavDisplay(
        modifier = modifier,
        backStack = todoBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Todo.TodoList> {
                TodoListScreen(
                    onTodoClick = {
                        todoBackStack.add(Route.Todo.TodoDetail(it))
                    }
                )
            }

            entry<Route.Todo.TodoDetail> { key ->
                TodoDetailsScreen(
                    todo = key.todo
                )
            }
        }
    )
}