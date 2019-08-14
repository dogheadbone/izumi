package izumi.logstage.api.config

import izumi.logstage.api.Log
import izumi.logstage.api.logger.LogSink

final case class LoggerPathConfig(threshold: Log.Level, sinks: Seq[LogSink])

final case class LoggerConfig(root: LoggerPathConfig, entries: Map[String, LoggerPathConfig])
