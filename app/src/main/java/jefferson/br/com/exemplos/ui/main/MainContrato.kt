package jefferson.br.com.exemplos.ui.main

import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView

interface MainContrato {


    interface view: BaseView<Presenter>{
        fun carregaLista(lista: List<String>)
    }

    interface Presenter: BasePresenter {
        fun atualizaLista()
    }
}