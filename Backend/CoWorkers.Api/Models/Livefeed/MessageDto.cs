using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Livefeed
{
    public class MessageDto
    {
        [JsonProperty("text")]
        public string Text { get; set; }

        [JsonProperty("type")]
        public MessageType Type { get; private set; }

        [JsonProperty("dateTime")]
        public DateTime DateTime { get; set; }

        public MessageDto(MessageType type)
        {
            this.Type = type;
        }

    }

    public enum MessageType
    {
        Arrival = 0,
        Twitter = 1,
        Info = 2,
        Closure = 3
    }
}