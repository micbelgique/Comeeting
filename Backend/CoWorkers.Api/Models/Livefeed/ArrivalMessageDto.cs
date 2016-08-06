using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Comeeting.Domain;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Livefeed
{
    public class ArrivalMessageDto:MessageDto
    {
        [JsonProperty("coworkerLinkedInId")]
        public string CoworkerLinkedInId { get; set; }

        [JsonProperty("isBirthday")]
        public bool IsBirthday { get; set; }

        [JsonProperty("pictureUrl")]
        public string PictureUrl { get; set; }

        public ArrivalMessageDto():base(LivefeedMessageType.Arrival)
        {
        }
    }
}