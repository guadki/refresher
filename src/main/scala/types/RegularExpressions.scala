package types

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

object RegularExpressions {

  case class Date(day: String, month: String, year: String)

  val dateStr = "2034-12-16"
  val longerText = "X happened in 2019-09-27. Then in 2020-02-13 A found letter from B. No later than 2020-04-15 he was..."

  val dateCheck: Regex = "(\\d{4})-(\\d{2})-(\\d{2})".r

  /**
   * `raw` interpolator allows to omit double backslashes
   */
  val rawDateCheck: Regex = raw"(\d{4})-(\d{2})-(\d{2})".r


  val extractedAll: Option[Date] = dateStr match {
    //extract everything
    case dateCheck(year, month, day) => Some(Date(day, month, year))
    //only check if strings matches
    case dateCheck(_*) => None
    //extract only month
    case dateCheck(_, month, _) => None
    case _ => None
  }

  //true
  rawDateCheck.matches(dateStr)
  //false
  rawDateCheck.matches(longerText)
  //true
  rawDateCheck.unanchored.matches(longerText)

  //iterator of strings
  rawDateCheck.findAllIn(longerText)
  //iterator of Match objects
  rawDateCheck.findAllMatchIn(longerText)

  //replace all matches: 2019-09-27 into [REDACTED]
  rawDateCheck.replaceAllIn(longerText, "[REDACTED]")

  private val censoredMonths = Map("09" -> "[REDACTED]", "04" -> "[REDACTED]")
  private val censoredMonthsMapper: Match => Option[String] = (m: Match) => {
    for {month <- censoredMonths.get(m.group(2))} yield s"${m.group(1)}-$month-${m.group(3)}"
  }

  //replace only months from censoredMonths: 2019-09-27 into 2019-[REDACTED]-27
  rawDateCheck.replaceSomeIn(longerText, censoredMonthsMapper)

}
