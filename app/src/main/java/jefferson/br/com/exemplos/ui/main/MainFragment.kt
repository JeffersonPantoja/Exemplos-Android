package jefferson.br.com.exemplos.ui.main


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import jefferson.br.com.exemplos.R
import jefferson.br.com.exemplos.ui.Permissao.PermissaoActivity
import kotlinx.android.synthetic.main.main_frag.view.*


class MainFragment : Fragment(), MainContrato.view {
    override lateinit var presenter: MainContrato.Presenter

    private lateinit var listView: ListView
    private lateinit var root: View
    private lateinit var listaDeExemplos: List<String>

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.main_frag, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun carregaLista(lista: List<String>) {
        listaDeExemplos = lista
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, lista)
        configuraListaDeExemplos(arrayAdapter)
    }

    private fun configuraListaDeExemplos(listaAdapter: ArrayAdapter<String>) {
        with(root) {
            listView = main_recycler.apply {
                adapter = listaAdapter
                onItemClickListener = cliqueNoExemplo()
            }
        }
    }

    private fun cliqueNoExemplo(): OnItemClickListener {
        return OnItemClickListener { parent, view, posicao, id ->
            when(posicao){
                0 -> {
                    val intent = Intent(context, PermissaoActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(context, listaDeExemplos[posicao], Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
