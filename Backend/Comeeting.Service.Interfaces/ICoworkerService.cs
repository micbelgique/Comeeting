using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Domain;

namespace Comeeting.Service.Interfaces
{
    public interface ICoworkerService
    {
        Coworker GetCoworker(string linkedInId);
        void SignUpCoworker(Coworker coworker);
    }
}
