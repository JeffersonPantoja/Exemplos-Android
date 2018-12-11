package jefferson.br.com.exemplos.ui.Permissao

class PermissaoPresenter(val permissaoFragment: PermissaoFragment): PermissaoContrato.Presenter {

    override fun start() {
        permissaoFragment.configuraBotaoRequisitar()
    }

    init {
        permissaoFragment.presenter = this
    }


}