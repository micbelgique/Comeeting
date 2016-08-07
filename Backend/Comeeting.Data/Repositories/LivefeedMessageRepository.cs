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
    public class LivefeedMessageRepository : ILivefeedMessageRepository
    {
        private readonly ComeetingDbContext _comeetingDbContext;

        public LivefeedMessageRepository(ComeetingDbContext comeetingDbContext)
        {
            _comeetingDbContext = comeetingDbContext;
        }

        public void AddLivefeedMessage(LivefeedMessage livefeedMessage)
        {
            _comeetingDbContext.LivefeedMessages.Add(livefeedMessage);
        }

        public Task<List<LivefeedMessage>> GetLivefeedAsync()
        {
            return _comeetingDbContext.LivefeedMessages.ToListAsync();
        }
    }
}
