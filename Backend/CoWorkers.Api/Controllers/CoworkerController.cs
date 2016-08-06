using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using Comeeting.Api.Models;
using Comeeting.Api.Models.Coworkers;
using Comeeting.Data;
using Comeeting.Domain;

namespace Comeeting.Api.Controllers
{
    public class CoworkerController : ApiController
    {
        private readonly UnitOfWork _uow;

        public CoworkerController()
        {
            _uow = new UnitOfWork();
        }
        [Route("api/coworker")]
        [HttpPost]
        public async Task<IHttpActionResult> Post([FromBody] CoworkerSignUpDto coworker)
        {
            if (ModelState.IsValid)
            {
                var existingCoworker = await _uow.CoworkerRepository.GetCoworkerAsync(coworker.LinkedInId);
                if (existingCoworker != null)
                    return Ok();

                var newCoworker = new Coworker()
                {
                    FirstName = coworker.FirstName,
                    LastName = coworker.LastName,
                    LinkedInId = coworker.LinkedInId,
                    PictureUrl = coworker.PictureUrl,
                    Summary = coworker.Summary,
                    Headline = coworker.Headline,
                    IsPresent = false,
                    Positions = new List<Position>()
                };
                foreach (var position in coworker.Positions)
                {
                    newCoworker.Positions.Add(
                        new Position()
                        {
                            Id = position.Id,
                            CompanyName = position.CompanyName,
                            StartDate = position.StartDate,
                            EndDate = position.EndDate,
                            IsCurrent = position.IsCurrent,
                            Title = position.Title
                        });
                }
                _uow.CoworkerRepository.AddCoworker(newCoworker);
                await _uow.SaveChangesAsync();

                return Ok();
            }
            else return BadRequest();
        }

        [Route("api/coworker/{linkedInId}")]
        [HttpGet]
        public async Task<IHttpActionResult> Get(string linkedInId)
        {
            var coworker = await _uow.CoworkerRepository.GetCoworkerAsync(linkedInId);

            if (coworker == null)
                return NotFound();

            var coworkerDto = new CoworkerDto()
            {
                LinkedInId = coworker.LinkedInId,
                FirstName = coworker.FirstName,
                LastName = coworker.LastName,
                Summary = coworker.Summary,
                PictureUrl = coworker.PictureUrl,
                Headline = coworker.Headline,
                IsPresent = coworker.IsPresent,
                Positions = new List<PositionDto>(),
                CurrentCoworkspace = coworker.CurrentCoworkspaceId,
                FavoriteCoworkspaces = new List<Guid>()
                
            };
            foreach (var position in coworker.Positions)
            {
                coworkerDto.Positions.Add(new PositionDto()
                {
                    Id = position.Id,
                    CompanyName = position.CompanyName,
                    IsCurrent = position.IsCurrent,
                    StartDate = position.StartDate,
                    EndDate = position.EndDate,
                    Title = position.Title
                });
            }
            foreach (var workspace in coworker.FavoriteCoworkspaces)
            {
                coworkerDto.FavoriteCoworkspaces.Add(workspace.Id);
            }

            return Ok(coworkerDto);
        }


    }
}
