package jefferson.br.com.exemplos.ui.Permissao

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jefferson.br.com.exemplos.R
import jefferson.br.com.exemplos.util.replaceFragmentInActivity

class PermissaoActivity : AppCompatActivity() {

    private lateinit var permissaoPresenter: PermissaoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.permissao_act)


        val permissaoFragment = supportFragmentManager.findFragmentById(R.id.permissaoContentFrame)
                as PermissaoFragment? ?: PermissaoFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.permissaoContentFrame)
        }

        permissaoPresenter = PermissaoPresenter(permissaoFragment)
    }
}
