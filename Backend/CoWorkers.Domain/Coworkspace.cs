using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Comeeting.Domain;

namespace Comeeting.Domain
{
    public class Coworkspace
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public string PictureUrl { get; set; }
        public string Description { get; set; }
        public string Address { get; set; }
        public string ZipCode { get; set; }
        public string City { get; set; }
        public double GeolocationLongitude { get; set; }
        public double GeolocationLatitude { get; set; }
        public int GeofencingRadius { get; set; }

        public virtual ICollection<Coworker> Coworkers { get; set; }
        public virtual ICollection<LivefeedMessage> LivefeedMessages { get; set; }

        public virtual ICollection<Coworker> CoworkerFavorites { get; set; }

    }
}
