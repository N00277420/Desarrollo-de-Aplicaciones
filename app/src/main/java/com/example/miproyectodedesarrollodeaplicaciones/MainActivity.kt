package com.example.miproyectodedesarrollodeaplicaciones

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.miproyectodedesarrollodeaplicaciones.databinding.ActivityMainBinding
import com.example.miproyectodedesarrollodeaplicaciones.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 Detectar clic en el ícono dentro del EditText para abrir el Drawer (botón hamburguesa)
        @Suppress("ClickableViewAccessibility")
        binding.cuadroTexto.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableStart = 0 // índice del drawableStart (izquierda)
                val iconWidth = binding.cuadroTexto.compoundDrawables[drawableStart]?.bounds?.width() ?: 0

                if (event.rawX <= (binding.cuadroTexto.left + iconWidth + binding.cuadroTexto.paddingStart)) {
                    // 👉 Abrir el menú lateral
                    binding.drawerLayout.openDrawer(GravityCompat.START)
                    return@setOnTouchListener true
                }
            }
            false
        }

        // Opcional: si quieres que al iniciar se muestre el HomeFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }
    }
}
