using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Comeeting.Domain;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Livefeed
{
    public class TwitterMessageDto : MessageDto
    {
        [JsonProperty("tweetLink")]
        public string TweetLink { get; set; }

        public TwitterMessageDto() : base(LivefeedMessageType.Twitter)
        {
        }
    }
}