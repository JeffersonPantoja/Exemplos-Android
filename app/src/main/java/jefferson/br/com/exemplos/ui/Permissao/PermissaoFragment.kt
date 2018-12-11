package jefferson.br.com.exemplos.ui.Permissao


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import jefferson.br.com.exemplos.R
import kotlinx.android.synthetic.main.permissao_frag.view.*


class PermissaoFragment : Fragment(), PermissaoContrato.view {
    override lateinit var presenter: PermissaoContrato.Presenter

    private lateinit var root: View

    companion object {
        fun newInstance() = PermissaoFragment()
        private const val CALL_PHONE_RESULT_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.permissao_frag, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun configuraBotaoRequisitar() {
        root.permissao_requisitar.setOnClickListener {

            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
                // Permissão ainda não foi concedida

                if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    // Usuário negou acesso
                    // Mostrar uma dialog explicando a importância do app ter acesso a funcionalidade
                    exibeMsgDeImportanciaDaPermissao()
                } else {
                    // Usuário não negou acesso ou negou e marcou a caixa de "não perguntar novamente"
                    // Fazer a requisição da permissão
                    requisitaPermissao()
                }

            } else {
                // Permissão já foi concedida, já é possível usufruir da funcionalidade
                Toast.makeText(context,"permssão concedida", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CALL_PHONE_RESULT_CODE -> {
                // Gerenciar o resultado da requisição
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    // Permissão foi concedida, já é possível usufruir da funcionalidade
                    Toast.makeText(context,"permssão concedida", Toast.LENGTH_LONG).show()
                } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    // Usuário marcou a caixa "não perguntar novamente"
                    // Mostrar uma dialog explicando a importância do app ter acesso a funcionalidade
                    exibeMsgNucaExibirDialogoNovamente()
                } else {
                    // Usuário negou acesso à permissão
                    // Bloquear trecho que utilizava a funcionalidade ou informar o usuário da necessidade de ter acesso à funcionalidade
                    Toast.makeText(context, "Permissão negada", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun exibeMsgNucaExibirDialogoNovamente() {
        AlertDialog.Builder(requireContext()).apply {
            setMessage("marcou não mostrar dialogo")
            setTitle("permissão")
            setPositiveButton("ir Para configurações") { d, i ->
                // Cria intent para a tela de detalhes do app onde é possível o usuário conceder permissão à funcionalidade
                irParaConfiguracoesDoApp()
            }
            setNegativeButton("não") { d, i -> d.dismiss() }
        }.show()
    }

    private fun irParaConfiguracoesDoApp() {
        val appSettings = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", requireActivity().packageName, null)
        }
        startActivity(appSettings)
    }


    private fun exibeMsgDeImportanciaDaPermissao() {
        AlertDialog.Builder(requireContext()).apply {
            setMessage("A permissão é importante")
            setTitle("Permissão")
            setPositiveButton("sim") { d, i ->
                // Se o usuário quiser, requere novamente permissão à funcionalidade
                requisitaPermissao()
            }
            // Se o usuário optar por não conceder a permissão, fecha a dialog
            setNegativeButton("Não") { d, i -> d.dismiss() }
        }.show()
    }

    private fun requisitaPermissao() {
        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), CALL_PHONE_RESULT_CODE)
    }




}
