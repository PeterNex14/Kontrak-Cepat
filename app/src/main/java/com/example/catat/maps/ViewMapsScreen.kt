package com.example.catat.maps

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@Composable
fun ViewMapsScreen() {
    val context = LocalContext.current

    Configuration.getInstance().load(context, androidx.preference.PreferenceManager.getDefaultSharedPreferences(context))

    val indonesiaCenter = GeoPoint(-6.2088, 106.8456)

    AndroidView(factory = {
        MapView(it).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setBuiltInZoomControls(true)
            setMultiTouchControls(true)
            setBackgroundColor(Color.TRANSPARENT)

            controller.setCenter(indonesiaCenter)
            controller.setZoom(6.0)
        }
    })
}
