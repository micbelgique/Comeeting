using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain
{
    public class Coworkspace
    {
        public string Name { get; set; }
        public string PictureUrl { get; set; }
        public string Description { get; set; }
        public double GeolocationLongitude { get; set; }
        public double GeolocationLatitude { get; set; }
        public int GeofencingRadius { get; set; }

        public virtual IEnumerable<Coworker> Coworkers { get; set; }

    }
}
