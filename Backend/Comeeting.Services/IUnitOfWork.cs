using Comeeting.Domain.Interfaces;

namespace Comeeting.Services
{
    public interface IUnitOfWork
    {
        ICoworkerRepository CoworkerRepository { get; set; }
        ILivefeedMessageRepository LivefeedMessageRepository { get; set; }
        ICoworkspaceRepository CoworkspaceRepository { get; set; }

        void Save();

    }
}