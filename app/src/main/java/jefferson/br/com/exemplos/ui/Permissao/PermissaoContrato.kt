package jefferson.br.com.exemplos.ui.Permissao

import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView

interface PermissaoContrato {


    interface view: BaseView<Presenter>{
        fun configuraBotaoRequisitar()

    }

    interface Presenter: BasePresenter{

    }
}