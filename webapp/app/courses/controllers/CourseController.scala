package courses.controllers

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import play.api.libs.json._
import play.api.mvc._
import sangria.execution._
import sangria.marshalling.playJson._
import sangria.parser.{QueryParser, SyntaxError}
import sangria.renderer.SchemaRenderer
import courses.persistence.{CourseRepository, UserRepository}
import courses.schema.{CourseContext, SchemaDefinition}

class CourseController(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def graphql(query: String, variables: Option[String], operation: Option[String]) = Action.async { _ =>
    executeQuery(query, variables map parseVariables, operation)
  }

  def graphqlBody = Action.async(parse.json) { request =>
    val query = (request.body \ "query").as[String]
    val operation = (request.body \ "operationName").asOpt[String]

    val variables = (request.body \ "variables").toOption.flatMap {
      case JsString(vars) => Some(parseVariables(vars))
      case obj: JsObject => Some(obj)
      case _ => None
    }

    executeQuery(query, variables, operation)
  }

  private def parseVariables(variables: String) =
    if (variables.trim == "" || variables.trim == "null") Json.obj() else Json.parse(variables).as[JsObject]

  private def executeQuery(query: String, variables: Option[JsObject], operation: Option[String]) =
    QueryParser.parse(query) match {

      // query parsed successfully, time to execute it!
      case Success(queryAst) =>
        Executor.execute(
          SchemaDefinition.CoursesSchema,
          queryAst,
          new CourseContext(new UserRepository, new CourseRepository), // todo move this
          operationName = operation,
          variables = variables getOrElse Json.obj(),
          deferredResolver = SchemaDefinition.Resolver,
          exceptionHandler = exceptionHandler
        )
          .map(Ok(_))
          .recover {
            case error: QueryAnalysisError => BadRequest(error.resolveError)
            case error: ErrorWithResolver => InternalServerError(error.resolveError)
          }

      // can't parse GraphQL query, return error
      case Failure(error: SyntaxError) =>
        Future.successful(BadRequest(Json.obj(
          "syntaxError" → error.getMessage,
          "locations" → Json.arr(Json.obj(
            "line" → error.originalError.position.line,
            "column" → error.originalError.position.column)))))

      case Failure(error) =>
        throw error
    }

  def renderSchema = Action {
    Ok(SchemaRenderer.renderSchema(SchemaDefinition.CoursesSchema))
  }

  lazy val exceptionHandler = ExceptionHandler {
    case (_, error@TooComplexQueryError) => HandledException(error.getMessage)
    case (_, error@MaxQueryDepthReachedError(_)) => HandledException(error.getMessage)
  }

  case object TooComplexQueryError extends Exception("Query is too expensive.")

}
