using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain
{
    public class Coworker
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string PictureUrl { get; set; }
        public string Summary { get; set; }

        public virtual Coworkspace CurrentCoworkspace { get; set; }

        public void SignUpWithLinkedIn(string linkedInToken)
        {

        }

        public void CheckInToWorkspace(Guid workspaceId)
        {
        }

        public void CheckOutOfWorkspace()
        {

        }
    }
}
