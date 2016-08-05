using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Comeeting.Api.Models.Livefeed;

namespace Comeeting.Api.Controllers
{
    public class LivefeedController : ApiController
    {
        [Route("api/coworkspace/{id}/livefeed/messages")]
        [HttpGet]
        public IHttpActionResult Get(string id)
        {
            return Ok(new List<MessageDto>()
            {
                new MessageDto(MessageType.Closure) {DateTime = new DateTime(2016,08,05,17,45,0), Text = "The coworkspace will close in 15 minutes." },
                new TwitterMessageDto() { DateTime = new DateTime(2016,08,05,17,0,0), Sender = "@ThomasFKN", Text = "Awesome day at the MIC MONS with @Mathias, the naked one #MICCOWORKING",TweetLink = "https://twitter.com/KilopDual/status/571379939611680768" },
                new ArrivalMessageDto() { DateTime = new DateTime(2016,08,05,14,0,0), Text = "Valentin Taleb arrived at the coworkspace.", IsBirthday = true, CoworkerLinkedInId = "123456789"},
                new MessageDto(MessageType.Info) { DateTime = new DateTime(2016,08,05,12,0,0), Text = "The lunch has arrival at the reception." },
                new ArrivalMessageDto() { DateTime = new DateTime(2016,08,05,9,0,0), Text = "Mathias Biard arrived at the coworkspace.", IsBirthday = false },
                new ArrivalMessageDto() { DateTime = new DateTime(2016,08,05,8,12,0), Text = "Thomas Fekenne arrived at the coworkspace.", IsBirthday = false }
            });
        }
    }
}
