import com.twitter.cassovary.graph.TestGraphs

object HelloGraph {
  def Analyze {
    val numNodes = 3
    val graph = TestGraphs.generateCompleteGrant(numNodes)
    printf (graph.nodeCount)
  }
}
