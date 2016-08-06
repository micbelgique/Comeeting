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
    public class CoworkspaceRepository : ICoworkspaceRepository
    {

        private readonly ComeetingDbContext _comeetingDbContext;

        public CoworkspaceRepository(ComeetingDbContext comeetingDbContext)
        {
            _comeetingDbContext = comeetingDbContext;
        }

        public void AddCoworkspace(Coworkspace coworkspace)
        {
            _comeetingDbContext.Coworkspaces.Add(coworkspace);
        }

        public Task<List<Coworkspace>> GetCoworkspacesAsync()
        {
            return _comeetingDbContext.Coworkspaces.ToListAsync();
        }

        public Task<Coworkspace> GetCoworkspaceAsync(Guid id)
        {
            return _comeetingDbContext.Coworkspaces.FirstOrDefaultAsync(c => c.Id == id);
        }
    }
}
