using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain
{
    public class Position
    {
        public int Id { get; set; }
        public string CompanyName { get; set; }
        public bool IsCurrent { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public string Title { get; set; }

        public string CoworkerLinkedInId { get; set; }

        public Coworker Coworker { get; set; }
    }
}
