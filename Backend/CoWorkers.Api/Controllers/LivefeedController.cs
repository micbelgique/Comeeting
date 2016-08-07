using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using Comeeting.Api.Models.Livefeed;
using Comeeting.Data;
using Comeeting.Domain;

namespace Comeeting.Api.Controllers
{
    public class LivefeedController : ApiController
    {
        private readonly UnitOfWork _uow;

        public LivefeedController()
        {
            _uow = new UnitOfWork();
        }
        [Route("api/coworkspace/{id}/livefeed/messages")]
        [HttpGet]
        public async Task<IHttpActionResult> Get(string id)
        {
            var livefeedMessages = await _uow.LivefeedMessageRepository.GetLivefeedAsync();

            var livefeedMessagesDto = new List<MessageDto>();
            foreach (var message in livefeedMessages)
            {
                MessageDto messageDto = null;
                switch (message.Type)
                {
                    case LivefeedMessageType.Arrival:
                        messageDto = new ArrivalMessageDto()
                        {
                            IsBirthday = message.IsBirthday.HasValue ? message.IsBirthday.Value : false,
                            CoworkerLinkedInId = message.CoworkerLinkedInId,
                            PictureUrl = message.PictureUrl
                        };
                        break;
                    case LivefeedMessageType.Twitter:
                        messageDto = new TwitterMessageDto()
                        {
                            TweetLink = message.TweetLink,
                        };
                        break;
                    case LivefeedMessageType.Info:
                        messageDto = new MessageDto(LivefeedMessageType.Info);
                        break;
                    case LivefeedMessageType.Closure:
                        messageDto = new MessageDto(LivefeedMessageType.Closure);
                        break;
                }
                messageDto.DateTime = message.DateTime;
                messageDto.Text = message.Text;
                messageDto.Title = message.Title;
                livefeedMessagesDto.Add(messageDto);
            }
            return Ok(livefeedMessagesDto);
        }
    }
}
