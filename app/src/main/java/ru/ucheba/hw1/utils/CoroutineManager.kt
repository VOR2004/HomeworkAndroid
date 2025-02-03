import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import ru.ucheba.hw1.R
import ru.ucheba.hw1.key.Logs
import ru.ucheba.hw1.utils.CoroutineCancellationMode
import ru.ucheba.hw1.utils.CoroutineExecutionMode
import ru.ucheba.hw1.utils.CoroutinePool
import ru.ucheba.hw1.utils.Tags
import kotlin.coroutines.CoroutineContext

class CoroutineManager(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val unconfinedDispatcher: CoroutineDispatcher = Dispatchers.Unconfined,
) {

    private var coroutineScope: CoroutineScope? = null
    private var runningJobs = mutableListOf<Job>()
    private var canceledCount = 0
    private var coroutinePool = CoroutinePool.DEFAULT
    private var coroutineExecutionMode = CoroutineExecutionMode.SEQUENTIAL
    private var coroutineCancellationMode = CoroutineCancellationMode.CANCEL_ON_BACKGROUND
    private var numberOfCoroutines = 0
    private var isError: Boolean by mutableStateOf(false)
    private var isRunning: Boolean by mutableStateOf(false)
    private val errorFlow = MutableStateFlow<String?>(null)

    fun startCoroutines() {

        if (isRunning) {
            Toast.makeText(context, R.string.cor_already_on, Toast.LENGTH_SHORT).show()
            return
        }
        if (numberOfCoroutines <= 0) {
            isError = true
            return
        }

        isRunning = true
        canceledCount = 0
        coroutineScope = CoroutineScope(getCoroutineContextByPool())
        runningJobs.clear()
        when (coroutineExecutionMode) {
            CoroutineExecutionMode.SEQUENTIAL -> startCoroutinesSequentially(numberOfCoroutines)
            CoroutineExecutionMode.PARALLEL -> startCoroutinesParallel(numberOfCoroutines)
        }
    }

    private fun startCoroutinesSequentially(numberOfCoroutines: Int) {
        coroutineScope?.launch {
            for (i in 1..numberOfCoroutines) {
                startSingleCoroutineJoin(i).await()
            }
        }
    }

    private fun startCoroutinesParallel(numberOfCoroutines: Int) {
        coroutineScope?.launch {
            for (i in 1..numberOfCoroutines) {
                launch { startSingleCoroutine(i) }
            }
        }
    }

    private suspend fun startSingleCoroutine(index: Int) {
        val job = coroutineScope?.launch {
            runCatching {
                try {
                    delay((2000L..15000L).random())
                    Log.d(Tags.COROUTINE_TAG, Logs.logCoroutineFinished(index, Thread.currentThread().name))
                } catch (e: CancellationException) {
                    Log.d(Tags.COROUTINE_TAG, Logs.logCoroutineCanceled(index))
                }
            }.onFailure {
                handleError(it)
            }
        }
        if (job != null) {
            runningJobs.add(job)
            job.invokeOnCompletion { runningJobs.remove(job) }
        }
    }

    private suspend fun startSingleCoroutineJoin(index: Int): Deferred<Result<Int>> {
        return coroutineScope!!.async {
            runCatching {
                try {
                    delay(1000)
                    Log.d(Tags.COROUTINE_TAG, Logs.logCoroutineFinished(index, Thread.currentThread().name))
                } catch (e: CancellationException) {
                    Log.d(Tags.COROUTINE_TAG, Logs.logCoroutineCanceled(index))
                }

            }.onFailure {
                handleError(it)
            }
        }.also { job ->
            runningJobs.add(job)
            job.invokeOnCompletion { runningJobs.remove(job) }
        }
    }

    private fun handleError(error: Throwable) {
        errorFlow.value = error.message
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
        }
        error.message?.let { Log.d(Tags.COROUTINE_TAG, it) }
    }

    fun cancelAllCoroutines() {
        if (!isRunning) return

        coroutineScope?.let { scope ->
            val jobsToCancel = runningJobs.toList()
            jobsToCancel.forEach { job ->
                if (job.isActive) {
                    job.cancel()
                    canceledCount++
                }
            }
            runningJobs.clear()
            scope.cancel()
            coroutineScope = null
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    context,
                    Logs.logCanceled(canceledCount),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        isRunning = false
    }


    fun setCoroutinePool(pool: CoroutinePool) {
        coroutinePool = pool
    }

    fun setCoroutineExecutionMode(mode: CoroutineExecutionMode) {
        coroutineExecutionMode = mode
    }

    fun setCoroutineCancellationMode(mode: CoroutineCancellationMode){
        coroutineCancellationMode = mode
    }

    private fun getCoroutineContextByPool(): CoroutineContext{
        return when (coroutinePool) {
            CoroutinePool.MAIN -> mainDispatcher
            CoroutinePool.IO -> ioDispatcher
            CoroutinePool.DEFAULT -> defaultDispatcher
            CoroutinePool.UNCONFINED -> unconfinedDispatcher
        }
    }

    fun onAppBackground() {
        if (coroutineCancellationMode == CoroutineCancellationMode.CANCEL_ON_BACKGROUND)
            cancelAllCoroutines()
    }

    fun setNumberOfCoroutines(n: Int) {
        numberOfCoroutines = n
    }

    fun getNumberOfCoroutines(): Int {
        return numberOfCoroutines
    }

    fun ifError(): Boolean {
        return isError
    }

    fun getCancelMode(): CoroutineCancellationMode {
        return coroutineCancellationMode
    }
}