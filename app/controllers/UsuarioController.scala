package controllers

import play.api.mvc._
import models.Usuario
import models.UsuarioDAO
import javax.inject.Inject
import play.api.data._
import play.api.data.Forms._
import javax.inject.Singleton
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

@Singleton
class UsuarioController @Inject()(dao: UsuarioDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val usuarioForm = Form(
    mapping(
      "Email" -> nonEmptyText,
      "Senha" -> nonEmptyText
    )(UsuarioVO.apply)(UsuarioVO.unapply)
  )

  def novoUsuario = Action {
    Ok(views.html.login.sigin(usuarioForm))
  }

  def novoUsuarioSubmissao = Action { implicit request =>
    usuarioForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login.novoUsuario())
      },
      usuario => {
        val novoUsuario = Usuario(0, usuario.email, usuario.senha)
        dao.salvar(novoUsuario)
        Created(views.html.login.novoUsuario())
      }
    )
  }

  def login = Action { implicit request =>
    usuarioForm.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(views.html.login.novoUsuario())
      },
      usuario => {
        val logUsuario = Usuario(0, usuario.email, usuario.senha)
        var usr = dao.listar

        for (u <- usr) {
          if (u.email == usuario.email && u.senha == usuario.senha)
            Ok(views.html.index(true))
        }
        Ok(views.html.index(false))
      }
    )
  }
}

case class UsuarioVO(email: String, senha: String)