import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatest.selenium.WebBrowser
import org.scalatest.{MustMatchers, WordSpec}

class HelloSelenium extends WordSpec with MustMatchers with WebBrowser {

  implicit val webDriver: WebDriver = new ChromeDriver()
  sys.addShutdownHook(webDriver.close())

  "The home page" must {
    "have the correct title and content" in {
      go to "https://www.gov.uk"

      pageTitle mustEqual "Welcome to GOV.UK"
      find(cssSelector("h1")).get.text mustEqual "Welcome to GOV.UK"

      click on linkText("Benefits")
      pageTitle mustEqual "Benefits - GOV.UK"

      goBack()
      pageTitle mustEqual "Welcome to GOV.UK"
      find(cssSelector("h2")).get.text mustEqual "Popular on GOV.UK"

    }
  }

  "The example.com home page" must {
    "have the correct title and content" in {
      go to "https://www.example.com"

      pageTitle mustEqual "Example Domain"
      find(cssSelector("h1")).get.text mustEqual "Example Domain"

      click on linkText("More information...")
      pageTitle mustEqual "IANA — IANA-managed Reserved Domains"

      goBack()
      pageTitle mustEqual "Example Domain"
      find(cssSelector("p")).get.text mustEqual "This domain is established to be used for illustrative examples in documents. You may use this domain in examples without prior coordination or asking for permission."

    }
  }

  "The NUFC wikipedia page" must {
    "have the correct title and content" in {
      go to "https://en.wikipedia.org/wiki/Newcastle_United_F.C."

      pageTitle mustEqual "Newcastle United F.C. - Wikipedia"
      find(cssSelector("h1")).get.text mustEqual "Newcastle United F.C."
      find(id("Early_history")).get.text mustEqual "Early history"
    }
  }
}