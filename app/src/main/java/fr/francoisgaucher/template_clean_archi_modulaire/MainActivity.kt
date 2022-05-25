package fr.francoisgaucher.template_clean_archi_modulaire

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.airQualityLiveData.observe(this) { state ->
            when (state) {
                State.Error -> Log.d(TAG, "Error")
                State.Loading -> Log.d(TAG, "Loading")
                is State.Succes -> {
                    Log.d(TAG, "Success ${state.airQuality}")
                    findViewById<TextView>(R.id.air_quality_content).text =
                        state.airQuality.toString()
                }
            }
        }

        findViewById<View>(R.id.refresh_action).setOnClickListener {
            mainViewModel.refreshAirQuality()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
