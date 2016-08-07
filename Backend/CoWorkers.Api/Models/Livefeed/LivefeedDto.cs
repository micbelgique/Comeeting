using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Comeeting.Api.Models.Livefeed
{
    public class LivefeedDto
    {
        public List<MessageDto> Messages { get; set; }
    }
}