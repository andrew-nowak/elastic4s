package com.sksamuel.elastic4s.requests.reindex

import com.sksamuel.elastic4s.ext.OptionImplicits._
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.script.Script
import com.sksamuel.elastic4s.requests.searches.queries.Query
import com.sksamuel.elastic4s.{Index, Indexes}

import scala.concurrent.duration.FiniteDuration

case class ReindexRequest(sourceIndexes: Indexes,
                          targetIndex: Index,
                          filter: Option[Query] = None,
                          requestsPerSecond: Option[Float] = None,
                          refresh: Option[RefreshPolicy] = None,
                          maxRetries: Option[Int] = None,
                          waitForCompletion: Option[Boolean] = None,
                          waitForActiveShards: Option[Int] = None,
                          timeout: Option[FiniteDuration] = None,
                          retryBackoffInitialTime: Option[FiniteDuration] = None,
                          shouldStoreResult: Option[Boolean] = None,
                          proceedOnConflicts: Option[Boolean] = None,
                          remoteHost: Option[String] = None,
                          remoteUser: Option[String] = None,
                          remotePass: Option[String] = None,
                          // It’s also possible to limit the number of processed documents by setting size.
                          maxDocs: Option[Int] = None,
                          script: Option[Script] = None,
                          scroll: Option[String] = None,
                          size: Option[Int] = None) {

  def remote(uri: String): ReindexRequest = copy(remoteHost = Option(uri))
  def remote(uri: String, user: String, pass: String): ReindexRequest =
    copy(remoteHost = Option(uri), remoteUser = Option(user), remotePass = Option(pass))

  def timeout(timeout: FiniteDuration): ReindexRequest = copy(timeout = timeout.some)

  def refresh(refresh: RefreshPolicy): ReindexRequest = copy(refresh = refresh.some)

  def filter(filter: Query): ReindexRequest = copy(filter = filter.some)

  def requestsPerSecond(requestsPerSecond: Float): ReindexRequest =
    copy(requestsPerSecond = requestsPerSecond.some)

  def maxRetries(maxRetries: Int): ReindexRequest = copy(maxRetries = maxRetries.some)

  def waitForActiveShards(count: Int): ReindexRequest =
    copy(waitForActiveShards = count.some)

  def waitForCompletion(waitForCompletion: Boolean): ReindexRequest =
    copy(waitForCompletion = waitForCompletion.some)

  def retryBackoffInitialTime(retryBackoffInitialTime: FiniteDuration): ReindexRequest =
    copy(retryBackoffInitialTime = retryBackoffInitialTime.some)

  def maxDocs(maxDocs: Int): ReindexRequest = copy(maxDocs = maxDocs.some)

  def shouldStoreResult(shouldStoreResult: Boolean): ReindexRequest =
    copy(shouldStoreResult = shouldStoreResult.some)

  def proceedOnConflicts(proceedOnConflicts: Boolean): ReindexRequest =
    copy(proceedOnConflicts = proceedOnConflicts.some)

  def script(script: Script): ReindexRequest = copy(script = script.some)

  def scroll(scroll: String): ReindexRequest = copy(scroll = scroll.some)
  def scroll(duration: FiniteDuration): ReindexRequest = copy(scroll = Some(duration.toSeconds + "s"))
  def size(size: Int): ReindexRequest = copy(size = size.some)
}
