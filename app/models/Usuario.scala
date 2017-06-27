package models

import anorm.SQL
import anorm.SqlQuery
import anorm.RowParser
import anorm.Macro
import anorm.SqlStringInterpolation
import anorm.SqlParser
import play.api.db.DB
import play.api.Play.current
import javax.inject.Inject
import play.api.db.Database
import javax.inject.Singleton

case class Usuario(id: Int, email: String, senha: String)

class UsuarioDAO @Inject() (database: Database) {
  val parser : RowParser[models.Usuario] = Macro.namedParser[models.Usuario]

  def salvar (usuario: Usuario) = database.withConnection { implicit connection =>
    val id: Option[Long] = SQL(
      """INSERT INTO TB_USUARIO(EMAIL, SENHA)
            values ({email}, {senha})""")
      .on('email -> usuario.email,
          'senha -> usuario.senha).executeInsert()
  }

  def listar = database.withConnection { implicit connection =>
    SQL"SELECT ID, TITULO, DIRETOR, ANO_PRODUCAO AS anoProducao FROM TB_FILME".as(parser.*)
  }
}