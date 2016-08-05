﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Comeeting.Api.Models;
using Comeeting.Api.Models.Coworkers;

namespace Comeeting.Api.Controllers
{
    public class CoworkerController : ApiController
    {
        [Route("api/coworker")]
        [HttpPost]
        public IHttpActionResult Post([FromBody] CoworkerDto coworker)
        {
            if (ModelState.IsValid)
                return Ok();
            else return BadRequest();
        }

        [Route("api/coworker/{linkedInId}")]
        [HttpGet]
        public IHttpActionResult Get(string linkedInId)
        {
            return Ok(new CoworkerDto()
            {
                LinkedInId = "81920_aezan_aza", FirstName = "Mathias", LastName = "Biard", Summary = "The naked one", PictureUrl = "http://www.mathiasbiard.com/images/me.jpg",
                FavoriteCoworkspaces = new List<Guid>() { new Guid("6CA3598B-09D3-43A4-A74E-6EC2D9B0BE89")}
            });
        }


    }
}
