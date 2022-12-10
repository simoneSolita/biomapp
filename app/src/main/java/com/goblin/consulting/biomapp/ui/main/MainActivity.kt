package com.goblin.consulting.biomapp.ui.main

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.goblin.consulting.biomapp.R
import com.goblin.consulting.biomapp.navigation.Screen
import com.goblin.consulting.biomapp.navigation.setupNavGraph
import com.goblin.consulting.biomapp.ui.components.DexterPermissions
import com.goblin.consulting.biomapp.ui.map.MapViewModel
import com.goblin.consulting.biomapp.ui.theme.BioMappTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun mapViewModelFactory(): MapViewModel.Factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BioMappTheme {
                Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
                    Navigation(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(
    viewModel: MainActivityViewModel
) {
    val navController = rememberNavController()
    setupNavGraph(navController = navController, viewModel = viewModel)
}

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MainActivityViewModel
) {
//    Background Image
    Image(
        painter = painterResource(
            id = R.drawable.splash_background_image
        ),
        contentDescription = stringResource(id = R.string.splash_background_image_cd),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

//    Background vector
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.splash_background_vector),
            contentDescription = stringResource(
                id = R.string.splash_background_vector
            )
        )
    }

    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .offset(0.dp, 80.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name).uppercase(),
            color = Color.Black,
            style = MaterialTheme.typography.h1,

            )
    }


    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        viewModel.onSplashLoadingFinished()
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }

    DexterPermissions(
        { viewModel.onPermissionGiven() },
        { viewModel.onPermissionRefused() }
    )

    if (viewModel.goToMap) {
        DisposableEffect(key1 = true) {
            navController.navigate(Screen.Map.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }

            //OnStop
            onDispose {
            }
        }
    }
}
