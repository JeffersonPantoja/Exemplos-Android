package jefferson.br.com.exemplos.ui.main

class MainPresenter(val mainFragment: MainContrato.view): MainContrato.Presenter{

    override fun atualizaLista(){

    }

    init {
        mainFragment.presenter = this
    }

    override fun start() {
        val exemplos = listOf("Requisitando Permissões", "Outro Exemplo")
        mainFragment.carregaLista(exemplos)
    }
}