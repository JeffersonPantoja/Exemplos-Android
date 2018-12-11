package jefferson.br.com.exemplos.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jefferson.br.com.exemplos.R
import jefferson.br.com.exemplos.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)


        val mainFragment = supportFragmentManager.findFragmentById(R.id.permissaoContentFrame)
                as MainFragment? ?: MainFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.permissaoContentFrame)
        }

        mainPresenter = MainPresenter(mainFragment)

    }
}
