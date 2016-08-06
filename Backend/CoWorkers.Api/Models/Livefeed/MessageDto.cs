using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Comeeting.Domain;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Livefeed
{
    public class MessageDto
    {
        [JsonProperty("title")]
        public string Title { get; set; }

        [JsonProperty("text")]
        public string Text { get; set; }

        [JsonProperty("type")]
        public LivefeedMessageType Type { get; private set; }

        [JsonProperty("dateTime")]
        public DateTime DateTime { get; set; }

        public MessageDto(LivefeedMessageType type)
        {
            this.Type = type;
        }

    }
}