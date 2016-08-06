using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Coworkers
{
    public class PositionDto
    {
        [JsonProperty("companyName")]
        public string CompanyName { get; set; }
        [JsonProperty("id")]
        public int Id { get; set; }
        [JsonProperty("isCurrent")]
        public bool IsCurrent { get; set; }
        [JsonProperty("startDate")]
        public DateTime StartDate { get; set; }
        [JsonProperty("endDate")]
        public DateTime? EndDate { get; set; }
        [JsonProperty("title")]
        public string Title { get; set; }
    }
}