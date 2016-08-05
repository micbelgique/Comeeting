using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models
{
    public class CoworkspaceDto
    {
        [JsonProperty("name")]
        public string Name { get; set; }
        [JsonProperty("pictureUrl")]
        public string PictureUrl { get; set; }
        [JsonProperty("description")]
        public string Description { get; set; }
        [JsonProperty("geolocationLongitude")]
        public double GeolocationLongitude { get; set; }
        [JsonProperty("geolocationLatitude")]
        public double GeolocationLatitude { get; set; }
        [JsonProperty("geofencingRadius")]
        public int GeofencingRadius { get; set; }

        [JsonProperty("coworkers")]
        public IEnumerable<CoworkerDto> Coworkers { get; set; }
    }
}