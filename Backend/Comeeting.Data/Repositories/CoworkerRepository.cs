using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Domain;
using Comeeting.Domain.Interfaces;

namespace Comeeting.Data.Repositories
{
    public class CoworkerRepository : ICoworkerRepository
    {

        private readonly ComeetingDbContext _comeetingDbContext;

        public CoworkerRepository(ComeetingDbContext comeetingDbContext)
        {
            _comeetingDbContext = comeetingDbContext;
        }

        public void AddCoworker(Coworker coworker)
        {
            _comeetingDbContext.Coworkers.Add(coworker);
        }

        public async Task<Coworker> GetCoworkerAsync(string linkedInId)
        {
            return await _comeetingDbContext.Coworkers.FirstOrDefaultAsync(c => c.LinkedInId == linkedInId);
        }

        public Task<List<Coworker>> GetCoworkersAsync()
        {
            return _comeetingDbContext.Coworkers.ToListAsync();
        }
    }
}
