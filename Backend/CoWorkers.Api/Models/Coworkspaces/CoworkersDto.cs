using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json;

namespace Comeeting.Api.Models.Coworkspaces
{
    public class CoworkersDto
    {
        [JsonProperty("coworkers")]
        public ICollection<CoworkerDto> Coworkers { get; set; }
    }
}