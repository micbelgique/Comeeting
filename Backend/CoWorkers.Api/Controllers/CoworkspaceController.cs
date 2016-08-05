using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Comeeting.Api.Models;
using Comeeting.Domain;

namespace Comeeting.Api.Controllers
{
    public class CoworkspaceController : ApiController
    {
        [Route("api/coworkspaces")]
        [HttpGet]
        public IHttpActionResult Get()
        {
            return Ok(new List<CoworkspaceDto>()
            {
                new CoworkspaceDto()
                {
                    Id = new Guid("6CA3598B-09D3-43A4-A74E-6EC2D9B0BE89"),
                    Name = "MIC Mons",
                    Description = "Microsoft Innovation Center",
                    GeolocationLatitude = 50.4611474,
                    GeolocationLongitude = 3.9292573,
                    GeofencingRadius = 50,
                    PictureUrl = "http://www.regional-it.be/wp-content/uploads/2012/06/Microsoft-Innovation-Center-Mons-.jpg",
                    Coworkers = new List<CoworkerDto>() { new CoworkerDto() { LinkedInId = "azazaz12214OIFD21og", FirstName = "Valentin", LastName = "Taleb",Summary = "The kung-fu fighter", PictureUrl = "https://cdn5.f-cdn.com/ppic/11626023/logo/13334637/Wz5KE/profile_logo_.png" } }
                },
                new CoworkspaceDto()
                {
                    Id = new Guid("C9D16208-5DA0-4D51-BEB6-1B9F7944F1C8"),
                    Name = "Betacowork",
                    Description = "Coworkspace in Brussels",
                    GeolocationLatitude = 50.8267747,
                    GeolocationLongitude = 4.3980635,
                    GeofencingRadius = 200,
                    PictureUrl = "http://www.betacowork.com/wp-content/uploads/2013/05/betacowork3-550x355.jpg",
                    Coworkers = new List<CoworkerDto>()
                    {
                        new CoworkerDto() { LinkedInId = "dza91jkelzale_azaz", FirstName = "Laurent", LastName = "Vandenbosch",Summary = "Ninjaaaa", PictureUrl = "https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAdqAAAAJDRmZGU3ZDBhLTNhNTYtNGUyZS04NjgzLWY1MjVhMDUwYWViNg.jpg" },
                        new CoworkerDto() { LinkedInId = "81920_aezan_aza", FirstName = "Mathias", LastName = "Biard",Summary = "The naked one", PictureUrl = "http://www.mathiasbiard.com/images/me.jpg" }
                    }
                },
            });
        }

        [Route("api/coworkspace/{id}/coworkers")]
        [HttpGet]
        public IHttpActionResult Get(string id)
        {
            return Ok(new List<CoworkerDto>()
                    {
                        new CoworkerDto() { LinkedInId = "dza91jkelzale_azaz", FirstName = "Laurent", LastName = "Vandenbosch",Summary = "Ninjaaaa", PictureUrl = "https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAdqAAAAJDRmZGU3ZDBhLTNhNTYtNGUyZS04NjgzLWY1MjVhMDUwYWViNg.jpg" },
                        new CoworkerDto() { LinkedInId = "81920_aezan_aza", FirstName = "Mathias", LastName = "Biard",Summary = "The naked one", PictureUrl = "http://www.mathiasbiard.com/images/me.jpg" }
                    });
        }

        [Route("api/coworkspace/{id}/coworker/{linkedInId}")]
        [HttpPost]
        public IHttpActionResult Post(string id, string linkedInId)
        {
            return Ok();
        }

        [Route("api/coworkspace/{id}/coworker/{linkedInId}")]
        [HttpDelete]
        public IHttpActionResult Delete(string id, string linkedInId)
        {
            return Ok();
        }





    }
}
