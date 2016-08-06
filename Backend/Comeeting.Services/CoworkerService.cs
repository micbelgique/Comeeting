using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Domain;
using Comeeting.Domain.Interfaces;
using Comeeting.Service.Interfaces;

namespace Comeeting.Services
{
    public class CoworkerService : ICoworkerService
    {
        private readonly IUnitOfWork _uow;

        public CoworkerService(IUnitOfWork uow)
        {
            _uow = uow;
        }

        public Task<Coworker> GetCoworkerAsync(string linkedInId)
        {
            return _uow.CoworkerRepository.GetCoworkerAsync(linkedInId);
        }

        public void SignUpCoworker(Coworker coworker)
        {
            _uow.CoworkerRepository.AddCoworker(coworker);
            _uow.Save();
        }
    }
}
