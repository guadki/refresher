package other

final case class MaxRetriesReachedException(private val message: String = "Maximum number of retries reached") extends Throwable(message)
