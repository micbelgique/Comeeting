using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Livefeed
{
    public class TwitterMessageDto : MessageDto
    {
        [JsonProperty("tweetLink")]
        public string TweetLink { get; set; }

        [JsonProperty("sender")]
        public string Sender { get; set; }

        public TwitterMessageDto() : base(MessageType.Twitter)
        {
        }
    }
}