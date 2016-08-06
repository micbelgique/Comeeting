using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Data.Repositories;

namespace Comeeting.Data
{
    public class UnitOfWork
    {
        private readonly ComeetingDbContext _context;
        public CoworkerRepository CoworkerRepository { get; private set; }
        public CoworkspaceRepository CoworkspaceRepository { get; private set; }

        public UnitOfWork()
        {
            _context = new ComeetingDbContext();
            CoworkerRepository = new CoworkerRepository(_context);
            CoworkspaceRepository = new CoworkspaceRepository(_context);
        }

        public Task<int> SaveChangesAsync()
        {
            return _context.SaveChangesAsync();
        }

    }
}
