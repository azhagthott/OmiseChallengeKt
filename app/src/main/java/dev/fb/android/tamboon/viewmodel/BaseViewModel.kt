package dev.fb.android.tamboon.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

class BaseViewModel(override val coroutineContext: CoroutineContext) : ViewModel(), CoroutineScope {
}