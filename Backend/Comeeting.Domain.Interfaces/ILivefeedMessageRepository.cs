using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain.Interfaces
{
    public interface ILivefeedMessageRepository
    {
        IEnumerable<LivefeedMessage> GetLivefeed();
        void AddLivefeedMessage(LivefeedMessage livefeedMessage);
    }
}
