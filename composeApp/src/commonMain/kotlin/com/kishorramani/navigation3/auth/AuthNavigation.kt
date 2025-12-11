package com.kishorramani.navigation3.auth

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
import com.kishorramani.navigation3.navigation.Route
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AuthNavigation(
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val authBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Auth.Login::class, Route.Auth.Login.serializer())
                    subclass(Route.Auth.Register::class, Route.Auth.Register.serializer())
                }
            }
        },
        Route.Auth.Login
    )

    //This viewmodel is shared in both the screen (Login and Register)
    val sharedAuthViewModel = viewModel { SharedAuthViewModel() }

    NavDisplay(
        modifier = modifier,
        backStack = authBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Auth.Login> {
                //Screen local view model
                val viewModel = viewModel { LoginViewModel() }
                LoginScreen(
                    viewModel = viewModel,
                    sharedAuthViewModel = sharedAuthViewModel,
                    onLogin = onLogin,      //We should bubble this up, because Login screen click event let user to todo screen
                    onRegister = {
                        authBackStack.add(Route.Auth.Register)
                    }
                )
            }

            entry<Route.Auth.Register> {
                //Screen local view model
                val viewModel = viewModel { RegisterViewModel() }
                RegisterScreen(
                    viewModel = viewModel,
                    sharedAuthViewModel = sharedAuthViewModel,
                )
            }
        }
    )
}