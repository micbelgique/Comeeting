using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain
{
    public class Coworker
    {
        public string LinkedInId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string PictureUrl { get; set; }
        public string Summary { get; set; }
        public string Headline { get; set; }
        public bool IsPresent { get; set; }

        public Guid? CurrentCoworkspaceId { get; set; }
        
        public virtual ICollection<Coworkspace> FavoriteCoworkspaces { get; set; }
        public virtual Coworkspace CurrentCoworkspace { get; set; }
        public virtual ICollection<Position> Positions { get; set; }

    }
}
