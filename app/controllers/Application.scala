package controllers

import play.api._
import play.api.mvc._
import org.apache.spark.SparkContext._
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
import play.api.data._
import play.api.data.Forms._
import models.UserData
import datastore.PageRank

object Application extends Controller {

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> text
    )(UserData.apply)(UserData.unapply)
  )


  def index = Action {

    PageRank.runGraph("a", "b")


    //Ok(views.html.index("Your new application is now ready Grphax." + vertices))\
    Ok(views.html.index(userForm))
  }

}
