using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain.Interfaces
{
    public interface ICoworkerRepository
    {
        Task<IEnumerable<Coworker>> GetCoworkersAsync();
        Task<Coworker> GetCoworkerAsync(string linkedInId);
        void AddCoworker(Coworker coworker);
    }
}
