package datastore

import org.apache.spark.SparkContext._
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD
 
object PageRank {


  val conf = new SparkConf()
    .setAppName("Load graph")
    .setMaster("local[2]")
    .set("spark.driver.allowMultipleContexts", "true")
    .setJars(SparkContext.jarOfClass(this.getClass).toList)

def runGraph (a: String, b: String) : Long = {

    val sc = new SparkContext(conf)

    // Create an RDD for the vertices
    val users: RDD[(VertexId, (String, String))] =
      sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")),
        (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))
    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
      sc.parallelize(Array(Edge(3L, 7L, "collab"),    Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
    // Define a default user in case there are relationship with missing user
    val defaultUser = ("John Doe", "Missing")
    // Build the initial Graph
    val graph = Graph(users, relationships, defaultUser)

    // Count all users which are postdocs
    val vertices = graph.vertices.filter { case (id, (name, pos)) => pos == "prof" }.count
    // Count all the edges where src > dst
    val edges = graph.edges.filter(e => e.srcId > e.dstId).count
    return edges
}}
