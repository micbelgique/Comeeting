using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using Comeeting.Api.Models.Coworkspaces;
using Comeeting.Data;
using Comeeting.Domain;

namespace Comeeting.Api.Controllers
{
    public class CoworkspaceController : ApiController
    {
        private readonly UnitOfWork _uow;

        public CoworkspaceController()
        {
            _uow = new UnitOfWork();
        }
        [Route("api/coworkspaces")]
        [HttpGet]
        public async Task<IHttpActionResult> Get()
        {
            var coworkspaces = await _uow.CoworkspaceRepository.GetCoworkspacesAsync();

            var coworkspacesDto = new List<CoworkspaceDto>();

            foreach (var coworkspace in coworkspaces)
            {
                var coworkspaceDto = new CoworkspaceDto()
                {
                    Id = coworkspace.Id,
                    Name = coworkspace.Name,
                    Description = coworkspace.Description,
                    Address = coworkspace.Address,
                    ZipCode = coworkspace.ZipCode,
                    City = coworkspace.City,
                    GeolocationLatitude = coworkspace.GeolocationLatitude,
                    GeolocationLongitude = coworkspace.GeolocationLongitude,
                    GeofencingRadius = coworkspace.GeofencingRadius,
                    PictureUrl = coworkspace.PictureUrl,
                    Coworkers = new List<CoworkerDto>()
                };
                foreach (var coworker in coworkspace.Coworkers)
                {
                    coworkspaceDto.Coworkers.Add(
                        new CoworkerDto()
                        {
                            LinkedInId = coworker.LinkedInId,
                            FirstName = coworker.FirstName,
                            LastName = coworker.LastName,
                            Summary = coworker.Summary,
                            PictureUrl = coworker.PictureUrl,
                            IsPresent = coworker.IsPresent
                        });
                }
                coworkspacesDto.Add(coworkspaceDto);
            }

            return Ok(coworkspacesDto);
        }

        [Route("api/coworkspace/{id}/coworkers")]
        [HttpGet]
        public async Task<IHttpActionResult> Get(Guid id)
        {
            var coworkspace = await _uow.CoworkspaceRepository.GetCoworkspaceAsync(id);
            var coworkers = new List<CoworkerDto>();
            foreach (var coworker in coworkspace.Coworkers)
            {
                coworkers.Add(
                    new CoworkerDto()
                    {
                        LinkedInId = coworker.LinkedInId,
                        FirstName = coworker.FirstName,
                        LastName = coworker.LastName,
                        Summary = coworker.Summary,
                        PictureUrl = coworker.PictureUrl,
                        IsPresent = coworker.IsPresent
                    });
            }

            return Ok(coworkers);
        }

        [Route("api/coworkspace/{id}/coworker/{linkedInId}")]
        [HttpPost]
        public async Task<IHttpActionResult> Post(Guid id, string linkedInId)
        {
            var coworker = await _uow.CoworkerRepository.GetCoworkerAsync(linkedInId);
            var coworkspace = await _uow.CoworkspaceRepository.GetCoworkspaceAsync(id);

            if (coworkspace == null)
                return BadRequest("Coworkspace ID not found");
            if (coworker == null)
                return BadRequest("Coworker ID not found");

            if (coworker.CurrentCoworkspaceId == id)
                return Ok();

            coworker.CurrentCoworkspaceId = id;

            await _uow.SaveChangesAsync();
            return Ok();
        }

        [Route("api/coworkspace/{id}/coworker/{linkedInId}")]
        [HttpDelete]
        public async Task<IHttpActionResult> Delete(Guid id, string linkedInId)
        {
            var coworkspace = await _uow.CoworkspaceRepository.GetCoworkspaceAsync(id);
            var coworker = await _uow.CoworkerRepository.GetCoworkerAsync(linkedInId);

            if (coworkspace == null)
                return BadRequest("Coworkspace ID not found");
            if (coworker == null)
                return BadRequest("Coworker ID not found");
            if(coworker.CurrentCoworkspaceId == id)
            { 
                coworker.CurrentCoworkspaceId = null;
                await _uow.SaveChangesAsync();
            }
            
            return Ok();
        }





    }
}
