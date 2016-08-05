using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Coworkers
{
    public class CoworkerDto
    {
        [JsonProperty("linkedInId")]
        public string LinkedInId { get; set; }
        [JsonProperty("firstName")]
        public string FirstName { get; set; }
        [JsonProperty("lastName")]
        public string LastName { get; set; }
        [JsonProperty("pictureUrl")]
        public string PictureUrl { get; set; }
        [JsonProperty("summary")]
        public string Summary { get; set; }

        [JsonProperty("favoriteCoworkspaces")]
        public IEnumerable<Guid> FavoriteCoworkspaces { get; set; }


    }
}